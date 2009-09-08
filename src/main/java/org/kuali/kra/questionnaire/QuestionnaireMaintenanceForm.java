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

public class QuestionnaireMaintenanceForm extends KualiMaintenanceForm {
    private Questionnaire newQuestionnaire;
    private Questionnaire fromQuestionnaire;
    private QuestionnaireUsage newQuestionnaireUsage;
    private List<QuestionnaireQuestion> questionnaireQuestions;
    private List<QuestionnaireQuestion> removedQuestionnaireQuestions;
    private List<QuestionnaireUsage> questionnaireUsages;
    private Integer newQuestionId;
    private String sqlScripts;
    private String retData;
    private String editData;
    private Integer questionNumber;
    private Long questionnaireQuestionsId;
    private String lookupResultsBOClassName;
    private String action;
    private String docStatus;
    private Integer numOfQuestions;
    private Integer numOfUsages;
    private List qnaireQuestions;
    private boolean versioned;
    
    public boolean isVersioned() {
        return versioned;
    }

    public void setVersioned(boolean versioned) {
        this.versioned = versioned;
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

    private String lookedUpCollectionName;

    /**
     * Constructs a ResearchAreasForm.
     */
    public QuestionnaireMaintenanceForm() {
        super();
        newQuestionnaire = new Questionnaire();
        fromQuestionnaire = new Questionnaire();
        questionnaireQuestions = new ArrayList<QuestionnaireQuestion>();
        removedQuestionnaireQuestions = new ArrayList<QuestionnaireQuestion>();
        questionnaireUsages = new ArrayList<QuestionnaireUsage>();
        qnaireQuestions = new ArrayList<String>();
        // TODO : if it is newquestionnaire, then set questionnumber to 1
        questionNumber = 1;
        numOfQuestions = 0;
        

    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // FIXME : just a temporary soln. it always get the methodtocall='refresh' after it started properly the first time.
        // need to investigate this.
        this.setMethodToCall("");
        // TODO : if do lookup again to edit, 'form' is not initialized ? initialized here ?
        newQuestionnaire = new Questionnaire();
        fromQuestionnaire = new Questionnaire();
        questionnaireQuestions = new ArrayList<QuestionnaireQuestion>();
        qnaireQuestions = new TypedArrayList(String.class);
        // to prevent indexoutofbound exception when populate
        if (this.getDocument() != null) {
            Questionnaire qn = (Questionnaire) ((MaintenanceDocumentBase) this.getDocument()).getNewMaintainableObject()
                    .getBusinessObject();
            qn.setQuestionnaireUsages(new TypedArrayList(QuestionnaireUsage.class));
        }
        questionNumber = 1;
        sqlScripts = "";
        retData = "";
        // editData = "";
        action = "";
        //versioned=false;
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
        // This is from ajax before post page
//        if (StringUtils.isNotBlank(action) && action.equals("setnumq")) {
//            GlobalVariables.getUserSession().addObject("numOfQuestions", getNumOfQuestions());
//            GlobalVariables.getUserSession().addObject("numOfUsages", getNumOfUsages());
//        }
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public boolean shouldPropertyBePopulatedInForm(String requestParameterName, HttpServletRequest request) {
        // TODO Auto-generated method stub
        // fromquestionnaire is return false for some reason ??
        // return super.shouldPropertyBePopulatedInForm(requestParameterName, request);
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
        // TODO Auto-generated method stub
//        if (this.getDocument() != null) {
//            Questionnaire qn = (Questionnaire) ((MaintenanceDocumentBase) this.getDocument()).getNewMaintainableObject()
//                    .getBusinessObject();
//            if (qn != null
//                    && !((MaintenanceDocumentBase) this.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
//                            "Copy")) {
//                int num = (Integer) GlobalVariables.getUserSession().retrieveObject("numOfQuestions");
////                for (int i = 0; i < num; i++) {
////                    // qn.getQuestionnaireQuestions().add(new QuestionnaireQuestion());
////                    getQnaireQuestions().add("");
////                }
//                num = (Integer) GlobalVariables.getUserSession().retrieveObject("numOfUsages");
//                for (int i = 0; i < num; i++) {
//                    qn.getQuestionnaireUsages().add(new QuestionnaireUsage());
//                }
//            }
//        }
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
                if (splitstr.length == 11) {
                    QuestionnaireQuestion question = new QuestionnaireQuestion();
                    if (StringUtils.isNotBlank(splitstr[0]) && !splitstr[0].equals("null")) { // "null" is coming between js and
                                                                                              // java code
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
    
    public Integer getNumOfQuestions() {
        return numOfQuestions;
    }

    public void setNumOfQuestions(Integer numOfQuestions) {
        this.numOfQuestions = numOfQuestions;
    }

    public Integer getNumOfUsages() {
        return numOfUsages;
    }

    public void setNumOfUsages(Integer numOfUsages) {
        this.numOfUsages = numOfUsages;
    }

    public List getQnaireQuestions() {
        return qnaireQuestions;
    }

    public void setQnaireQuestions(List qnaireQuestions) {
        this.qnaireQuestions = qnaireQuestions;
    }

    public List<QuestionnaireUsage> getQuestionnaireUsages() {
        return questionnaireUsages;
    }

    public void setQuestionnaireUsages(List<QuestionnaireUsage> questionnaireUsages) {
        this.questionnaireUsages = questionnaireUsages;
    }

    public List<QuestionnaireQuestion> getRemovedQuestionnaireQuestions() {
        return removedQuestionnaireQuestions;
    }

    public void setRemovedQuestionnaireQuestions(List<QuestionnaireQuestion> removedQuestionnaireQuestions) {
        this.removedQuestionnaireQuestions = removedQuestionnaireQuestions;
    }


}
