/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.kra.protocol.questionnaire;

import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;

public abstract class ProtocolModuleQuestionnaireBeanBase extends ModuleQuestionnaireBean {

    private ProtocolBase protocol;
    
    public ProtocolModuleQuestionnaireBeanBase(ProtocolBase protocol) {
        super(CoeusModule.IRB_MODULE_CODE, protocol.getProtocolNumber(), "0", protocol.getSequenceNumber().toString(), 
                                protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument().isApproved());
        this.protocol = protocol;
        setProtocolSubItemCode(protocol) ;
    }
    
    public ProtocolModuleQuestionnaireBeanBase(String moduleItemCode, String moduleItemKey, String moduleSubItemCode, String moduleSubItemKey, boolean finalDoc) {
        super(moduleItemCode, moduleItemKey, moduleSubItemCode, moduleSubItemKey, finalDoc);
    }
   
    protected void setProtocolSubItemCode(ProtocolBase protocol) {
        // For now check renewal/amendment.  will add 'ProtocolBase Submission' when it is cleared
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
        if( (o != null) && (o instanceof ProtocolModuleQuestionnaireBeanBase) ) {
            ProtocolModuleQuestionnaireBeanBase pmqb = (ProtocolModuleQuestionnaireBeanBase)o;
            retVal = (  (this.getModuleItemCode().equals(pmqb.getModuleItemCode()))
                     && (this.getModuleItemKey().equals(pmqb.getModuleItemKey()))
                     && (this.getModuleSubItemCode().equals(pmqb.getModuleSubItemCode()))  
                     && (this.getModuleSubItemKey().equals(pmqb.getModuleSubItemKey()))
                     && (this.isFinalDoc() == pmqb.isFinalDoc()) );
        }
        return retVal;
    }

    public ProtocolBase getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolBase protocol) {
        this.protocol = protocol;
    }
}
