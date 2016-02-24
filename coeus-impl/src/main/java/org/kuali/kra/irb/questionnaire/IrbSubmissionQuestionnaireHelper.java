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
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.questionnaire.ProtocolModuleQuestionnaireBeanBase;
import org.kuali.kra.protocol.questionnaire.ProtocolSubmissionQuestionnaireHelper;

public class IrbSubmissionQuestionnaireHelper extends ProtocolSubmissionQuestionnaireHelper {

    private static final long serialVersionUID = 4076949768477025326L;

    public IrbSubmissionQuestionnaireHelper(ProtocolBase protocol, String actionTypeCode, String submissionNumber, boolean readOnlyView) {
        super(protocol, actionTypeCode, submissionNumber, readOnlyView);
    }

    @Override
    public String getModuleCode() {
        return CoeusModule.IRB_MODULE_CODE;
    }

    @Override
    public ProtocolModuleQuestionnaireBeanBase getBaseProtocolModuleQuestionnaireBean(String sequenceNumber) {
        return new ProtocolModuleQuestionnaireBean(CoeusModule.IRB_MODULE_CODE, getProtocol().getProtocolNumber(), CoeusSubModule.ZERO_SUBMODULE, sequenceNumber, 
                getProtocol().getProtocolDocument().getDocumentHeader().getWorkflowDocument().isApproved());
    }

}
