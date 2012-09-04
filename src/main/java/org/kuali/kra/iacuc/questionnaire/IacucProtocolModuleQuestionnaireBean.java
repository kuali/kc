/*
 * Copyright 2005-2010 The Kuali Foundation
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

import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.questionnaire.ProtocolModuleQuestionnaireBean;

public class IacucProtocolModuleQuestionnaireBean extends ProtocolModuleQuestionnaireBean {

    public IacucProtocolModuleQuestionnaireBean(IacucProtocol protocol) {
        super(CoeusModule.IACUC_PROTOCOL_MODULE_CODE, protocol.getProtocolNumber(), "0", protocol.getSequenceNumber().toString(), 
                protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument().isApproved());
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
}
