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
package org.kuali.kra.questionnaire;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.web.struts.form.KualiForm;

public class QuestionnaireForm extends KualiForm {
    private Questionnaire newQuestionnaire;
    private Questionnaire fromQuestionnaire;
    private QuestionnaireUsage newQuestionnaireUsage;
    private List<QuestionnaireQuestion> questionnaireQuestions;
    private Integer newQuestionId;
    private String sqlScripts;
    private String retData;
    private String editData;
    private Integer questionNumber;
    private Long questionnaireQuestionsId; 

    /**
     * Constructs a ResearchAreasForm.
     */
    public QuestionnaireForm() {
        super();
        newQuestionnaire = new Questionnaire();
        questionnaireQuestions = new ArrayList<QuestionnaireQuestion>();
        // TODO : if it is newquestionnaire, then set questionnumber to 1
        questionNumber = 1;

    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // FIXME : just a temporary soln.  it always get the methodtocall='refresh' after it started properly the first time.  
        // need to investigate this.
        this.setMethodToCall("");
    }

    public Questionnaire getNewQuestionnaire() {
        return newQuestionnaire;
    }

    public void setNewQuestionnaire(Questionnaire newQuestionnaire) {
        this.newQuestionnaire = newQuestionnaire;
    }

    public List<QuestionnaireQuestion> getQuestionnaireQuestions() {
        return questionnaireQuestions;
    }

    public void setQuestionnaireQuestions(List<QuestionnaireQuestion> questionnaireQuestions) {
        this.questionnaireQuestions = questionnaireQuestions;
    }

    public Integer getNewQuestionId() {
        return newQuestionId;
    }

    public void setNewQuestionId(Integer newQuestionId) {
        this.newQuestionId = newQuestionId;
    }

    public String getSqlScripts() {
        return sqlScripts;
    }

    public void setSqlScripts(String sqlScripts) {
        this.sqlScripts = sqlScripts;
    }

    public String getRetData() {
        if (StringUtils.isNotBlank(sqlScripts)) {
            Questionnaire questionnaire = new Questionnaire();
            questionnaire = getNewQuestionnaire();
            KraServiceLocator.getService(QuestionnaireService.class).saveQuestionnaire(sqlScripts, questionnaire);   
            retData = "<h3>qnaireID=" + questionnaire.getQuestionnaireId() + "</h3>";
        } 
        return retData;
    }

    public void setRetData(String retData) {
        this.retData = retData;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    public QuestionnaireUsage getNewQuestionnaireUsage() {
        return newQuestionnaireUsage;
    }

    public void setNewQuestionnaireUsage(QuestionnaireUsage newQuestionnaireUsage) {
        this.newQuestionnaireUsage = newQuestionnaireUsage;
    }

    public Long getQuestionnaireQuestionsId() {
        return questionnaireQuestionsId;
    }

    public void setQuestionnaireQuestionsId(Long questionnaireQuestionsId) {
        this.questionnaireQuestionsId = questionnaireQuestionsId;
    }

    public Questionnaire getFromQuestionnaire() {
        return fromQuestionnaire;
    }

    public void setFromQuestionnaire(Questionnaire fromQuestionnaire) {
        this.fromQuestionnaire = fromQuestionnaire;
    }

    public String getEditData() {
        return editData;
    }

    public void setEditData(String editData) {
        this.editData = editData;
    }

}
