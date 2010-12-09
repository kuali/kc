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
package org.kuali.kra.irb.questionnaire;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.questionnaire.QuestionnaireHelperBase;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * 
 * This is Helper class for protocol questionnaire.
 */
public class QuestionnaireHelper extends QuestionnaireHelperBase {


    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8923292365833681926L;

    /**
     * Each Helper must contain a reference to its document form so that it can access the actual document.
     */
    private ProtocolForm form;

    private String submissionActionTypeCode;
    private String protocolNumber;
    private String submissionNumber; 
    
    /**
     * 
     * Constructs a QuestionnaireHelper.java. To hook up with protocol form.
     * @param form
     */
    public QuestionnaireHelper(ProtocolForm form) {
        this.form = form;
    }

    /**
     * 
     * This method is to set up things for questionnaire page to be displayed.
     */
    public void prepareView() {
        initializePermissions(getProtocol());
    }


    /*
     * authorization check.
     */
    private void initializePermissions(Protocol protocol) {
        ProtocolTask task = new ProtocolTask(TaskName.ANSWER_PROTOCOL_QUESTIONNAIRE, protocol);
        setAnswerQuestionnaire(getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task));
    }

    public String getModuleCode() {
        return CoeusModule.IRB_MODULE_CODE;
    }

    public ModuleQuestionnaireBean getModuleQnBean() {
        //return new ModuleQuestionnaireBean(getModuleCode(), getProtocol());
        ModuleQuestionnaireBean moduleQuestionnaireBean = new ModuleQuestionnaireBean(CoeusModule.IRB_MODULE_CODE, getProtocol());
        if (StringUtils.isNotBlank(getSubmissionActionTypeCode())) {
            // TODO : need to figure out a way to set subitemkey which is submissionnumber.
            // however, submissionnumber will not be available until it is submitted
            moduleQuestionnaireBean.setModuleSubItemCode("2");
            moduleQuestionnaireBean.setModuleSubItemKey(getNextSubmissionNumber(getProtocol()).toString());
        }
        return moduleQuestionnaireBean;

    }

    private Protocol getProtocol() {
        ProtocolDocument document = form.getDocument();
        if (document == null || document.getProtocol() == null) {
            throw new IllegalArgumentException("invalid (null) ProtocolDocument in ProtocolForm");
        }
        return document.getProtocol();
    }

    private TaskAuthorizationService getTaskAuthorizationService() {
        return KraServiceLocator.getService(TaskAuthorizationService.class);
    }

    private String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }

    private Integer getNextSubmissionNumber(Protocol protocol) {
        Integer propNextValue = 1;
        String propertyName = "submissionNumber";
        // search for property and get the latest number - increment for next call
        for (DocumentNextvalue documentNextvalue : protocol.getProtocolDocument().getDocumentNextvalues()) {
            if(documentNextvalue.getPropertyName().equalsIgnoreCase(propertyName)) {
                propNextValue = documentNextvalue.getNextValue();
            }
        }
        return propNextValue;
    }

    public String getSubmissionActionTypeCode() {
        return submissionActionTypeCode;
    }

    public void setSubmissionActionTypeCode(String submissionActionTypeCode) {
        this.submissionActionTypeCode = submissionActionTypeCode;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public String getSubmissionNumber() {
        return submissionNumber;
    }

    public void setSubmissionNumber(String submissionNumber) {
        this.submissionNumber = submissionNumber;
    }
}
