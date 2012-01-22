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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewModule;
import org.kuali.kra.irb.actions.amendrenew.ProtocolModule;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.questionnaire.QuestionnaireHelperBase;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;

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
        ProtocolModuleQuestionnaireBean moduleQuestionnaireBean = new ProtocolModuleQuestionnaireBean(getProtocol());
        if (StringUtils.isNotBlank(getSubmissionActionTypeCode())) {
            // TODO : need to figure out a way to set subitemkey which is submissionnumber.
            // however, submissionnumber will not be available until it is submitted
            moduleQuestionnaireBean.setModuleSubItemCode("2");
            moduleQuestionnaireBean.setModuleSubItemKey(getNextSubmissionNumber(getProtocol()).toString());
        }
        return moduleQuestionnaireBean;

    }

    public void populateAnswers() {
        if (isAmendQuestionnaire()) {
            super.populateAnswers();
            List <AnswerHeader> answerHeaders = new ArrayList<AnswerHeader>();
            ModuleQuestionnaireBean moduleBean = getAmendModuleBean();
            answerHeaders = getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleBean);
            if (!answerHeaders.isEmpty() && answerHeaders.get(0).getAnswerHeaderId() == null){
                // this is based on usage association, and it is not saved qn answer.
                answerHeaders = new ArrayList<AnswerHeader>();
            }
            if (answerHeaders.isEmpty()) {
                answerHeaders = getAnswerHeadersForCurrentProtocol(moduleBean);
            } 
            if (!answerHeaders.isEmpty()) {
                for (AnswerHeader answerHeader : answerHeaders) {
                    // we now display inactive qnnrs after amendment
                    /*
                    if (answerHeader.isActiveQuestionnaire()) {
                       getAnswerHeaders().add(answerHeader);
                    }
                    */
                    getAnswerHeaders().add(answerHeader);
                }
                resetHeaderLabels();
           }     

        } else {
            super.populateAnswers();
        }
        setQuestionnaireActiveStatuses();
    }

    private ModuleQuestionnaireBean getAmendModuleBean() {
        ModuleQuestionnaireBean moduleBean = getModuleQnBean();
        moduleBean.setModuleSubItemCode(CoeusSubModule.ZERO_SUBMODULE);
        return moduleBean;

    }
    
    /**
     * need to override to take care of "0' modulesubitemcode for amend questionnaire
     * @see org.kuali.kra.questionnaire.QuestionnaireHelperBase#updateQuestionnaireAnswer(int)
     */
    public void updateQuestionnaireAnswer(int answerHeaderIndex) {
        AnswerHeader answerHeader = getAnswerHeaders().get(answerHeaderIndex);
        if (isAmendQuestionnaire() && CoeusSubModule.ZERO_SUBMODULE.equals(answerHeader.getModuleSubItemCode())) {
            ModuleQuestionnaireBean moduleBean = getAmendModuleBean();
            if (UPDATE_WITH_NO_ANSWER_COPY.equals(answerHeader.getUpdateOption())) {
                // no copy
                getAnswerHeaders().remove(answerHeaderIndex);
                getAnswerHeaders().add(answerHeaderIndex,
                        getQuestionnaireAnswerService().getNewVersionAnswerHeader(moduleBean, answerHeader.getQuestionnaire()));
            }
            else {
                AnswerHeader newAnswerHeader = getQuestionnaireAnswerService().getNewVersionAnswerHeader(moduleBean,
                        answerHeader.getQuestionnaire());
                getQuestionnaireAnswerService().copyAnswerToNewVersion(answerHeader, newAnswerHeader);
                getAnswerHeaders().remove(answerHeaderIndex);
                getAnswerHeaders().add(answerHeaderIndex, newAnswerHeader);
            }
            resetHeaderLabels();
        } else {
            super.updateQuestionnaireAnswer(answerHeaderIndex);
        }

    }


    /*
     * Get the current protocol that the amend/renewal is from.  Then get its
     * answerheader for amend questionnaire to use.
     */
    private List <AnswerHeader> getAnswerHeadersForCurrentProtocol(ModuleQuestionnaireBean moduleBean) {
        List <AnswerHeader> answerHeaders;    
        // try to retrieve from original protocol
        Protocol currentProtocol = getProtocolFinder().findCurrentProtocolByNumber(getProtocol().getProtocolNumber().substring(0, 10));
        moduleBean.setModuleItemKey(currentProtocol.getProtocolNumber());
        moduleBean.setModuleSubItemKey(currentProtocol.getSequenceNumber().toString());
        answerHeaders = getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleBean);
        if (!answerHeaders.isEmpty()) {
            Protocol protocol = getProtocol();
            for (AnswerHeader answerHeader : answerHeaders) {
                for (Answer answer : answerHeader.getAnswers()) {
                    answer.setAnswerHeaderIdFk(null);
                    answer.setId(null);
                }
                answerHeader.setAnswerHeaderId(null);
                answerHeader.setModuleItemKey(protocol.getProtocolNumber());
                answerHeader.setModuleSubItemKey(protocol.getSequenceNumber().toString());
            }
        }
        return answerHeaders;
    }
    
    private ProtocolFinderDao getProtocolFinder() {
        return KraServiceLocator.getService(ProtocolFinderDao.class);
    }

    private boolean isAmendQuestionnaire() {
        Protocol protocol = getProtocol();
        boolean isAmendQuestionnaire = false;
        if (protocol.isAmendment() || protocol.isRenewal()) {
            for (ProtocolAmendRenewModule module : protocol.getProtocolAmendRenewal().getModules()) {
                if (ProtocolModule.QUESTIONNAIRE.equals(module.getProtocolModuleTypeCode())) {
                    isAmendQuestionnaire = true;
                    break;
                }
            }
        }
        return isAmendQuestionnaire;
    }

    private Protocol getProtocol() {
        ProtocolDocument document = form.getDocument();
        if (document == null || document.getProtocol() == null) {
            throw new IllegalArgumentException("invalid (null) ProtocolDocument in ProtocolForm");
        }
        return document.getProtocol();
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
