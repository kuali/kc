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
package org.kuali.kra.irb.questionnaire;

import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.amendrenew.ProtocolModule;
import org.kuali.kra.irb.auth.ProtocolTask;
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
public class QuestionnaireHelper extends QuestionnaireHelperBase {



    private static final long serialVersionUID = -8923292365833681926L;

    public QuestionnaireHelper(ProtocolFormBase form) {
        super(form);
    }

    @Override
    protected ProtocolTaskBase getModifyQnnrTaskHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.ANSWER_PROTOCOL_QUESTIONNAIRE, (Protocol)protocol);
    }

    @Override
    public String getModuleCode() {
        return CoeusModule.IRB_MODULE_CODE;
    }

    @Override
    protected ProtocolModuleQuestionnaireBeanBase getProtocolModuleQuestionnaireBeanHook(ProtocolBase protocol) {
        return new ProtocolModuleQuestionnaireBean((Protocol) protocol);
    }

    @Override
    protected Class<? extends ProtocolFinderDao> getProtocolFinderDaoClassHook() {
        return org.kuali.kra.irb.ProtocolFinderDao.class;
    }

    @Override
    protected String getProtocolModuleCodeForQuestionnaireHook() {
        return ProtocolModule.QUESTIONNAIRE;
    }
}
