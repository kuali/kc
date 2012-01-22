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
package org.kuali.kra.questionnaire;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * This class is the questionnaire helper base to be shared by modules that are going to use questionnaire
 */
public abstract class QuestionnaireHelperBase implements Serializable {

    private static final long serialVersionUID = 180986754611233315L;
    protected static final String UPDATE_WITH_NO_ANSWER_COPY = "1";
    private boolean answerQuestionnaire = false;
    private List<AnswerHeader> answerHeaders;
    private List<String> headerLabels;
    transient private QuestionnaireAnswerService questionnaireAnswerService;



    public abstract String getModuleCode();

    public abstract ModuleQuestionnaireBean getModuleQnBean();


    /**
     * set up the tab labels for each questionnaire 
     */
    public void resetHeaderLabels() {
        List<String> labels = new ArrayList<String>();
        for (AnswerHeader answerHeader : answerHeaders) {
            labels.add(getQuestionnaireLabel(answerHeader.getQuestionnaire().getQuestionnaireUsages(), answerHeader.getModuleSubItemCode()));
        }
        setHeaderLabels(labels);     
    }
   
    /*
     * get questionnaire display label from the appropriate questionnaire usage
     */
    private String getQuestionnaireLabel(List<QuestionnaireUsage> usages, String moduleSubItemCode) {
        if (CollectionUtils.isNotEmpty(usages) && usages.size() > 1) {
            Collections.sort((List<QuestionnaireUsage>) usages);
           // Collections.reverse((List<QuestionnaireUsage>) usages);
        }
        for (QuestionnaireUsage usage : usages) {
            if (getModuleCode().equals(usage.getModuleItemCode()) && moduleSubItemCode.equals(usage.getModuleSubItemCode())) {
                return usage.getQuestionnaireLabel();
            }
        }
        return null;
    }
    
    
    /**
     * This method loops through the current list of answer headers, checking if the questionnaire for each is still active and 
     * sets the status for each answer header accordingly. 
     */
    public void setQuestionnaireActiveStatuses() {
        for (AnswerHeader answerHeader : answerHeaders) {
            if(isQuestionnaireActive(answerHeader)){
                answerHeader.setActiveQuestionnaire(true);
            }
            else{
                answerHeader.setActiveQuestionnaire(false);
            }
        }
    }


    private boolean isQuestionnaireActive(AnswerHeader answerHeader) {        
        Integer questionnaireId = answerHeader.getQuestionnaire().getQuestionnaireIdAsInteger();
        String coeusModuleCode = answerHeader.getModuleItemCode();
        String coeusSubModuleCode = answerHeader.getModuleSubItemCode(); 
        return getQuestionnaireAnswerService().checkIfQuestionnaireIsActiveForModule(questionnaireId, coeusModuleCode, coeusSubModuleCode);
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
            answerHeaders.add(answerHeaderIndex, getQuestionnaireAnswerService().getNewVersionAnswerHeader(getModuleQnBean(), answerHeader.getQuestionnaire()));
        } else {
            AnswerHeader newAnswerHeader = getQuestionnaireAnswerService().getNewVersionAnswerHeader(getModuleQnBean(), answerHeader.getQuestionnaire());
            getQuestionnaireAnswerService().copyAnswerToNewVersion(answerHeader, newAnswerHeader);
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
        getQuestionnaireAnswerService().preSave(answerHeaders);
    }
  
    /**
     * 
     * This method to update whether a child question answer is to be displayed or not.  This is specifically
     * used when 'lookup' value is returned because the js 'onchange' is not working in this case.
     * @param headerIndex
     */
    public void updateChildIndicator(int headerIndex) {
        getQuestionnaireAnswerService().setupChildAnswerIndicator(answerHeaders.get(headerIndex).getAnswers());
    }

    /**
     * 
     * This method get/setup questionnaire answers when 'questionnaire' page is clicked.
     */
    public void populateAnswers() {
        setAnswerHeaders(getQuestionnaireAnswerService().getQuestionnaireAnswer(getModuleQnBean()));
        resetHeaderLabels();
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

    public List<String> getHeaderLabels() {
        return headerLabels;
    }

    public void setHeaderLabels(List<String> headerLabels) {
        this.headerLabels = headerLabels;
    }

    protected QuestionnaireAnswerService getQuestionnaireAnswerService() {
        if (questionnaireAnswerService == null) {
            questionnaireAnswerService = KraServiceLocator.getService(QuestionnaireAnswerService.class);
        }
        return questionnaireAnswerService;
    }

    protected void setQuestionnaireAnswerService(QuestionnaireAnswerService questionnaireAnswerService) {
        this.questionnaireAnswerService = questionnaireAnswerService;
    }
    
    protected TaskAuthorizationService getTaskAuthorizationService() {
        return KraServiceLocator.getService(TaskAuthorizationService.class);
    }

    protected String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }


}
