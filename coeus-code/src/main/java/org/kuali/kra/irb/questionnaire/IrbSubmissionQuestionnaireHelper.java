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
