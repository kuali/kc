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
package org.kuali.kra.protocol.questionnaire;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.kra.irb.questionnaire.ProtocolModuleQuestionnaireBean;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireHelperBase;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;


public abstract class ProtocolSubmissionQuestionnaireHelper extends QuestionnaireHelperBase {

    private static final long serialVersionUID = -1675080999844454310L;
    private static final String SUFFIX_T = "T";

    private ProtocolBase protocol;
    private String actionTypeCode;
    private String submissionNumber;
    private boolean readOnlyView;
    
    public ProtocolSubmissionQuestionnaireHelper(ProtocolBase protocol, String actionTypeCode, String submissionNumber, boolean readOnlyView) {
        this.protocol = protocol;
        this.actionTypeCode = actionTypeCode;
        this.submissionNumber = submissionNumber;
        this.readOnlyView = readOnlyView;
    }

    @Override
    public abstract String getModuleCode();
    
    public abstract ProtocolModuleQuestionnaireBeanBase getBaseProtocolModuleQuestionnaireBean(String sequenceNumber);

    @Override
    public ModuleQuestionnaireBean getModuleQnBean() {
        String baseProtocolNumber = protocol.getProtocolNumber();
        baseProtocolNumber = baseProtocolNumber.replaceAll("[A-Z]+", "");
        return new ProtocolModuleQuestionnaireBean(getModuleCode(),
                baseProtocolNumber, 
                CoeusSubModule.PROTOCOL_SUBMISSION, 
                StringUtils.isNotBlank(submissionNumber) ? submissionNumber : actionTypeCode,
                StringUtils.isNotBlank(submissionNumber) || protocol.getProtocolNumber().endsWith(SUFFIX_T) || readOnlyView);
    }

    public String getActionTypeCode() {
        return actionTypeCode;
    }

    public void setActionTypeCode(String actionTypeCode) {
        this.actionTypeCode = actionTypeCode;
    }

    public String getSubmissionNumber() {
        return submissionNumber;
    }

    public void setSubmissionNumber(String submissionNumber) {
        this.submissionNumber = submissionNumber;
    }

    public ProtocolBase getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolBase protocol) {
        this.protocol = protocol;
    }


}
