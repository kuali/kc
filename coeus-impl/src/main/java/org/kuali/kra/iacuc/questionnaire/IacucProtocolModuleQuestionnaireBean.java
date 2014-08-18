/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.iacuc.questionnaire;

import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.coeus.common.framework.krms.KrmsRulesContext;
import org.kuali.kra.protocol.questionnaire.ProtocolModuleQuestionnaireBeanBase;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IacucProtocolModuleQuestionnaireBean extends ProtocolModuleQuestionnaireBeanBase {

    public IacucProtocolModuleQuestionnaireBean(IacucProtocol protocol) {
        super(CoeusModule.IACUC_PROTOCOL_MODULE_CODE, protocol.getProtocolNumber(), "0", protocol.getSequenceNumber().toString(), 
                protocol.getProtocolDocument().getDocumentHeader().hasWorkflowDocument() ? protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument().isApproved() : false);
        setProtocol(protocol);
        setProtocolSubItemCode(protocol) ;
    }
    
    public IacucProtocolModuleQuestionnaireBean(String moduleItemCode, String moduleItemKey, String moduleSubItemCode, String moduleSubItemKey, boolean finalDoc) {
        super(moduleItemCode, moduleItemKey, moduleSubItemCode, moduleSubItemKey, finalDoc);
    }
    
    protected void setProtocolSubItemCode(IacucProtocol protocol) {
        if (protocol.isContinuation()) {
            setModuleSubItemCode(CoeusSubModule.CONTINUATION);
        } else {
            super.setProtocolSubItemCode(protocol);
        }
    }

    @Override
    public KrmsRulesContext getKrmsRulesContextFromBean() {
        if (getProtocol() != null) {
            return getProtocol().getKrmsRulesContext();
        } else {
            Map<String, Object> values = new HashMap<String, Object>();
            values.put("protocolNumber", getModuleItemKey());
            values.put("sequenceNumber", getModuleSubItemKey());
            List<IacucProtocol> protocols = (List<IacucProtocol>) KcServiceLocator.getService(BusinessObjectService.class).findMatching(IacucProtocol.class, values);
            if (protocols != null && !protocols.isEmpty()) {
                return protocols.get(0).getIacucProtocolDocument();
            } else {
                return null;
            }
        }
    }
}
