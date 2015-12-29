/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.irb.questionnaire;

import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.coeus.common.framework.krms.KrmsRulesContext;
import org.kuali.kra.protocol.questionnaire.ProtocolModuleQuestionnaireBeanBase;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProtocolModuleQuestionnaireBean extends ProtocolModuleQuestionnaireBeanBase {

    public ProtocolModuleQuestionnaireBean(Protocol protocol) {
        super(CoeusModule.IRB_MODULE_CODE, protocol.getProtocolNumber(), "0", protocol.getSequenceNumber().toString(), 
                                protocol.getProtocolDocument().getDocumentHeader().hasWorkflowDocument() ? protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument().isApproved() : false);
        setProtocolSubItemCode(protocol) ;
    }
    
    public ProtocolModuleQuestionnaireBean(String moduleItemCode, String moduleItemKey, String moduleSubItemCode, String moduleSubItemKey, boolean finalDoc) {
        super(moduleItemCode, moduleItemKey, moduleSubItemCode, moduleSubItemKey, finalDoc);
    }
    
    @Override
    public KrmsRulesContext getKrmsRulesContextFromBean() {
        if (getModuleItemKey() != null && getModuleSubItemKey() != null) {
            String protocolNumber = getModuleItemKey().indexOf("|")==-1 ? getModuleItemKey() : getModuleItemKey().substring(0, getModuleItemKey().indexOf("|"));
            Integer sequenceNumber = Integer.valueOf(getModuleSubItemKey());
            Map<String, Object> values = new HashMap<String, Object>();
            values.put("protocolNumber", protocolNumber);
            values.put("sequenceNumber", sequenceNumber);
            List<Protocol> protocols = 
                    (List<Protocol>) KcServiceLocator.getService(BusinessObjectService.class).findMatching(Protocol.class, values);
            if (protocols != null && !protocols.isEmpty()) {
                return protocols.get(0).getKrmsRulesContext();
            } 
        }
        
        return null;
    }

    private void setProtocolSubItemCode(Protocol protocol) {
        // For now check renewal/amendment.  will add 'Protocol Submission' when it is cleared
        String subModuleCode = CoeusSubModule.ZERO_SUBMODULE;
        
        if (protocol.isRenewal()) {
            subModuleCode = CoeusSubModule.AMENDMENT_RENEWAL;
            if (protocol.isRenewalWithoutAmendment()) {
                subModuleCode = CoeusSubModule.RENEWAL;
            }
        }
        if (protocol.isFYI()) {
            subModuleCode = CoeusSubModule.FYI;
        }
        if (protocol.isAmendment()) {
            subModuleCode = CoeusSubModule.AMENDMENT;
        }
        
        setModuleSubItemCode(subModuleCode);
    }
    
    public boolean equals(Object o) {
        boolean retVal = false;
        if( (o != null) && (o instanceof ProtocolModuleQuestionnaireBean) ) {
            ProtocolModuleQuestionnaireBean pmqb = (ProtocolModuleQuestionnaireBean)o;
            retVal = (  (this.getModuleItemCode().equals(pmqb.getModuleItemCode()))
                     && (this.getModuleItemKey().equals(pmqb.getModuleItemKey()))
                     && (this.getModuleSubItemCode().equals(pmqb.getModuleSubItemCode()))  
                     && (this.getModuleSubItemKey().equals(pmqb.getModuleSubItemKey()))
                     && (this.isFinalDoc() == pmqb.isFinalDoc()) );
        }
        return retVal;
    }

}
