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
