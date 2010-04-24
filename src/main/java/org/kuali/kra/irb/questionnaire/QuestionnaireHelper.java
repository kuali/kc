/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.questionnaire.QuestionnaireUsage;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kns.util.GlobalVariables;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * 
 * This is Helper class for protocol questionnaire.
 */
public class QuestionnaireHelper implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 2799300472544313825L;
    private static final String UPDATE_WITH_NO_ANSWER_COPY = "1";

    /**
     * Each Helper must contain a reference to its document form so that it can access the actual document.
     */
    private ProtocolForm form;

    private boolean answerQuestionnaire = false;

    private List<AnswerHeader> answerHeaders;
    private List<String> headerLabels;
    transient private QuestionnaireAnswerService questionnaireAnswerService;

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
        answerQuestionnaire = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
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

    public boolean isAnswerQuestionnaire() {
        return answerQuestionnaire;
    }

    public void setAnswerQuestionnaire(boolean answerQuestionnaire) {
        this.answerQuestionnaire = answerQuestionnaire;
    }

    public List<AnswerHeader> getAnswerHeaders() {
        return answerHeaders;
    }

    public void setAnswerHeaders(List<AnswerHeader> answerHeaders) {
        this.answerHeaders = answerHeaders;
    }

    /**
     * 
     * This method get/setup questionnaire answers when 'questionnaire' page is clicked.
     */
    public void populateAnswers() {
        setAnswerHeaders(getQuestionnaireAnswerService().getQuestionnaireAnswer(new ModuleQuestionnaireBean(CoeusModule.IRB_MODULE_CODE, getProtocol())));
        resetHeaderLabels();
    }

    /*
     * set up the tab labels for each questionnaire 
     */
    private void resetHeaderLabels() {
        List<String> labels = new ArrayList<String>();
        for (AnswerHeader answerHeader : answerHeaders) {
            labels.add(getQuestionnaireLabel(answerHeader.getQuestionnaire().getQuestionnaireUsages()));
        }
        setHeaderLabels(labels);
     
    }
    
    /*
     * get questionnaire display label from the appropriate questionnaire usage
     */
    private String getQuestionnaireLabel(List<QuestionnaireUsage> usages) {
        if (CollectionUtils.isNotEmpty(usages) && usages.size() > 1) {
            Collections.sort((List<QuestionnaireUsage>) usages);
           // Collections.reverse((List<QuestionnaireUsage>) usages);
        }
        for (QuestionnaireUsage usage : usages) {
            if (CoeusModule.IRB_MODULE_CODE.equals(usage.getModuleItemCode())) {
                return usage.getQuestionnaireLabel();
            }
        }
        return null;
    }

    private QuestionnaireAnswerService getQuestionnaireAnswerService() {
        if (questionnaireAnswerService == null) {
            questionnaireAnswerService = KraServiceLocator.getService(QuestionnaireAnswerService.class);
        }
        return questionnaireAnswerService;
    }

    public List<String> getHeaderLabels() {
        return headerLabels;
    }

    public void setHeaderLabels(List<String> headerLabels) {
        this.headerLabels = headerLabels;
    }

    /**
     * 
     * This method is for the 'update' button to update questionnaire answer to newer version
     * either copy old answer to the new version or Not.
     * @param answerHeaderIndex
     */
    public void updateQuestionnaireAnswer(int answerHeaderIndex) {
        AnswerHeader answerHeader = answerHeaders.get(answerHeaderIndex);
        if (UPDATE_WITH_NO_ANSWER_COPY.equals(answerHeader.getUpdateOption())) {
            // no copy
            answerHeaders.remove(answerHeaderIndex);
            answerHeaders.add(answerHeaderIndex, questionnaireAnswerService.getNewVersionAnswerHeader(new ModuleQuestionnaireBean(
                CoeusModule.IRB_MODULE_CODE, getProtocol()), answerHeader.getQuestionnaire()));
        } else {
            AnswerHeader newAnswerHeader = questionnaireAnswerService.getNewVersionAnswerHeader(new ModuleQuestionnaireBean(
                CoeusModule.IRB_MODULE_CODE, getProtocol()), answerHeader.getQuestionnaire());
            questionnaireAnswerService.copyAnswerToNewVersion(answerHeader, newAnswerHeader);
            answerHeaders.remove(answerHeaderIndex);
            answerHeaders.add(answerHeaderIndex, newAnswerHeader);
        }
        resetHeaderLabels();

    }
    
    /**
     * 
     * This method is to do a couple of things, move question answer and re-evaluate 'completed' flag.
     */
    public void preSave() {
        questionnaireAnswerService.preSave(answerHeaders);
    }
    
    /**
     * 
     * This method to update whether a child question answer is to be displayed or not.  This is specifically
     * used when 'lookup' value is returned because the js 'onchange' is not working in this case.
     * @param headerIndex
     */
    public void updateChildIndicator(int headerIndex) {
        questionnaireAnswerService.setupChildAnswerIndicator(answerHeaders.get(headerIndex).getAnswers());
    }
}
