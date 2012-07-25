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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.ProtocolDocument;
import org.kuali.kra.protocol.ProtocolFinderDao;
import org.kuali.kra.protocol.ProtocolForm;
import org.kuali.kra.irb.actions.amendrenew.ProtocolModule;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewModule;
import org.kuali.kra.protocol.auth.ProtocolTask;
import org.kuali.kra.protocol.questionnaire.ProtocolModuleQuestionnaireBean;
import org.kuali.kra.protocol.questionnaire.QuestionnaireHelper;
import org.kuali.kra.questionnaire.QuestionnaireHelperBase;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;

/**
 * 
 * This is Helper class for protocol questionnaire.
 */
public class IacucProtocolQuestionnaireHelper extends QuestionnaireHelper {

    private static final long serialVersionUID = -7998778375503716988L;

    /**
     * 
     * Constructs an IacucProtocolQuestionnaireHelper.java. To hook up with protocol form.
     * @param form
     */
    public IacucProtocolQuestionnaireHelper(ProtocolForm form) {
        super(form);
    }

    /*
     * authorization check.
     */
    protected void initializePermissions(Protocol protocol) {
        ProtocolTask task = new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL_QUESTIONNAIRE, (IacucProtocol)protocol);
        setAnswerQuestionnaire(getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task));
    }
    
    public String getModuleCode() {
        return CoeusModule.IACUC_PROTOCOL_MODULE_CODE;
    }

    protected ProtocolModuleQuestionnaireBean getProtocolModuleQuestionnaireBean(Protocol protocol) {
        return new IacucProtocolModuleQuestionnaireBean(protocol);
    }

}
