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
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.amendrenew.IacucProtocolModule;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.questionnaire.ProtocolModuleQuestionnaireBean;
import org.kuali.kra.protocol.questionnaire.ProtocolQuestionnaireAuditRule;

public class IacucProtocolQuestionnaireAuditRule extends ProtocolQuestionnaireAuditRule {
    
    protected String getModuleCodeHook() {
        return CoeusModule.IRB_MODULE_CODE;
    }

    protected ProtocolModuleQuestionnaireBean getProtocolModuleQuestionnaireBean(Protocol protocol) {
        return new IacucProtocolModuleQuestionnaireBean((IacucProtocol) protocol);
    }

    protected ProtocolModuleQuestionnaireBean getProtocolModuleQuestionnaireBean(String moduleItemCode, String moduleItemKey, String moduleSubItemCode, String moduleSubItemKey, boolean finalDoc) {
        return new IacucProtocolModuleQuestionnaireBean(moduleItemCode, moduleItemKey, moduleSubItemCode, moduleSubItemKey, finalDoc);
    }

    @Override
    protected String getQuestionnaireModuleCodeHook() {
        return IacucProtocolModule.QUESTIONNAIRE;
    }

}
