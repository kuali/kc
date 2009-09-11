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
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.util.TypedArrayList;
import org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm;

/**
 * 
 * This is a form class for questionnaire maintenance.
 */
public class QuestionnaireMaintenanceForm extends KualiMaintenanceForm {
    private QuestionnaireUsage newQuestionnaireUsage;
    private List<QuestionnaireUsage> questionnaireUsages;
    private String editData;
    private Integer questionNumber;
    private String lookupResultsBOClassName;
    private String docStatus;
    private List<String> qnaireQuestions;
    private String lookedUpCollectionName;

    /**
     * Constructs a ResearchAreasForm.
     */
    public QuestionnaireMaintenanceForm() {
        super();
        questionnaireUsages = new ArrayList<QuestionnaireUsage>();
        qnaireQuestions = new ArrayList<String>();
        questionNumber = 1;
        

    }


    public String getLookupResultsBOClassName() {
        return lookupResultsBOClassName;
    }

    public void setLookupResultsBOClassName(String lookupResultsBOClassName) {
        this.lookupResultsBOClassName = lookupResultsBOClassName;
    }

    public String getLookedUpCollectionName() {
        return lookedUpCollectionName;
    }

    public void setLookedUpCollectionName(String lookedUpCollectionName) {
        this.lookedUpCollectionName = lookedUpCollectionName;
    }



    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // FIXME : just a temporary soln. it always get the methodtocall='refresh' after it started properly the first time.
        // need to investigate this.
        this.setMethodToCall("");
        qnaireQuestions = new TypedArrayList(String.class);
        // to prevent indexoutofbound exception when populate
        if (this.getDocument() != null) {
            Questionnaire qn = (Questionnaire) ((MaintenanceDocumentBase) this.getDocument()).getNewMaintainableObject()
                    .getBusinessObject();
            qn.setQuestionnaireUsages(new TypedArrayList(QuestionnaireUsage.class));
        }
        questionNumber = 1;
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

    public String getEditData() {
        return editData;
    }

    public void setEditData(String editData) {
        this.editData = editData;
    }

    @Override
    public boolean shouldPropertyBePopulatedInForm(String requestParameterName, HttpServletRequest request) {
        return true;
    }

    public String getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }

    @Override
    public void populate(HttpServletRequest request) {
        super.populate(request);


        List<QuestionnaireQuestion> qList = populateQuestionnaireQuestions();
        if (!qList.isEmpty()) {
            QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) this;
            ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getBusinessObject())
                    .setQuestionnaireQuestions(qList);
        }
    }

    /*
     * questionnairequestions properties are concatenated to a string
     * 1. tried hidden fields for each property of Qnairequestion, but there seem
     * to have problem with "request" object occassionally,i.e., sometimes, a property value will become null for no reason (when there are large amount of questions)
     * 2. also populated each property of questions as hidden fields affect the performance,ie, slows page load if the number of questions is large
     */
    private List<QuestionnaireQuestion> populateQuestionnaireQuestions() {
        List<QuestionnaireQuestion> qList = new ArrayList<QuestionnaireQuestion>();
        for (Object qstr : getQnaireQuestions()) {
            if (qstr instanceof String[]) {
                String[] splitstr = ((String[]) qstr)[0].split("#f#");
                if (splitstr.length == 11 && !("Y").equals(splitstr[10])) {
                    QuestionnaireQuestion question = new QuestionnaireQuestion();
                    // "null" is coming between js and java code
                    if (StringUtils.isNotBlank(splitstr[0]) && !splitstr[0].equals("null")) { 
                        question.setQuestionnaireQuestionsId(Long.parseLong(splitstr[0]));
                    }
                    if (StringUtils.isNotBlank(splitstr[1])) {
                        question.setQuestionnaireRefIdFk(Long.parseLong(splitstr[1]));
                    }
                    question.setQuestionRefIdFk(Long.parseLong(splitstr[2]));
                    question.setQuestionNumber(Integer.parseInt(splitstr[3]));
                    question.setParentQuestionNumber(Integer.parseInt(splitstr[4]));
                    question.setConditionFlag("Y".equals(splitstr[5]));
                    question.setCondition(splitstr[6]);
                    question.setConditionValue(splitstr[7]);
                    question.setQuestionSeqNumber(Integer.parseInt(splitstr[8]));
                    question.setVersionNumber(Long.parseLong(splitstr[9]));
                    question.setDeleted(splitstr[10]);
                    qList.add(question);
                }
            }
        }
        return qList;
    }
    
    public List<String> getQnaireQuestions() {
        return qnaireQuestions;
    }

    public void setQnaireQuestions(List<String> qnaireQuestions) {
        this.qnaireQuestions = qnaireQuestions;
    }

    public List<QuestionnaireUsage> getQuestionnaireUsages() {
        return questionnaireUsages;
    }

    public void setQuestionnaireUsages(List<QuestionnaireUsage> questionnaireUsages) {
        this.questionnaireUsages = questionnaireUsages;
    }


}
