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
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolFinderDao;
import org.kuali.kra.iacuc.actions.amendrenew.IacucProtocolModule;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolFinderDao;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.kra.protocol.questionnaire.ProtocolModuleQuestionnaireBeanBase;
import org.kuali.kra.protocol.questionnaire.QuestionnaireHelperBase;

/**
 * 
 * This is Helper class for protocol questionnaire.
 */
public class IacucQuestionnaireHelper extends QuestionnaireHelperBase {

    private static final long serialVersionUID = -7998778375503716988L;

    /**
     * 
     * Constructs an IacucProtocolQuestionnaireHelper.java. To hook up with protocol form.
     * @param form
     */
    public IacucQuestionnaireHelper(ProtocolFormBase form) {
        super(form);
    }

    /*
     * authorization check.
     */
    protected ProtocolTaskBase getModifyQnnrTaskHook(ProtocolBase protocol) {
        return new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL_QUESTIONNAIRE, (IacucProtocol)protocol);
    }
    
    public String getModuleCode() {
        return CoeusModule.IACUC_PROTOCOL_MODULE_CODE;
    }

    protected ProtocolModuleQuestionnaireBeanBase getProtocolModuleQuestionnaireBeanHook(ProtocolBase protocol) {
        return new IacucProtocolModuleQuestionnaireBean((IacucProtocol) protocol);
    }

    @Override
    protected String getProtocolModuleCodeForQuestionnaireHook() {
        return IacucProtocolModule.QUESTIONNAIRE;
    }

    @Override
    protected Class<? extends ProtocolFinderDao> getProtocolFinderDaoClassHook() {
        return IacucProtocolFinderDao.class;
    }

}
