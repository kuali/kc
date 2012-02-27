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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm;
import org.springframework.util.AutoPopulatingList;

/**
 * 
 * This is a form class for questionnaire maintenance.
 */
public class QuestionnaireMaintenanceForm extends KualiMaintenanceForm {
    
    private static final long serialVersionUID = -6379415207639699802L;
    
    private QuestionnaireUsage newQuestionnaireUsage;
    private List<QuestionnaireUsage> questionnaireUsages;
    private String editData;
    private Integer questionNumber;
    private String lookupResultsBOClassName;
    private String docStatus;
   // private List<String> qnaireQuestions;
    // "List" without parameter will cause Notserializableexception; so add "transient" here
    // List<String> will cause cast exception see kcirb-1306
    private transient List qnaireQuestions;
    private String lookedUpCollectionName;
    private transient FormFile templateFile;
    // following are ajax related.  consider to move to an ajaxbean
    private String moduleCode;
    private Integer qidx;
    private String questionId;
    private Question question;
    private Integer moveup;
    private Integer movedn;
    private String childNode;
    private String response;
    private String value;
    private boolean questionCurrentVersion = true;

    /**
     * Constructs a QuestionnaireMaintenanceForm.
     */
    @SuppressWarnings("unchecked")
    public QuestionnaireMaintenanceForm() {
        super();
        questionnaireUsages = new ArrayList<QuestionnaireUsage>();
        qnaireQuestions = new AutoPopulatingList<Object>(Object.class);
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



    @SuppressWarnings("unchecked")
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // FIXME : just a temporary soln. it always get the methodtocall='refresh' after it started properly the first time.
        // need to investigate this.
        this.setMethodToCall("");
        qnaireQuestions = new AutoPopulatingList<Object>(Object.class);
        // to prevent indexoutofbound exception when populate
        if (this.getDocument() != null) {
            Questionnaire qn = (Questionnaire) ((MaintenanceDocumentBase) this.getDocument()).getNewMaintainableObject()
                    .getBusinessObject();
            qn.setActive(false);
            qn.setQuestionnaireUsages(new AutoPopulatingList<QuestionnaireUsage>(QuestionnaireUsage.class));
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

    public String getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }

    @Override
    public void populate(HttpServletRequest request) {
        qnaireQuestions = new AutoPopulatingList<Object>(Object.class);
        
        for (Object key : request.getParameterMap().keySet()) {
            // TODO : AutoPopulatingList is suppose to lazyload list
            // but still get indexoutofbound exception
            // so prepolulate the list before data is populated
            String paraName = (String)key;
            if (StringUtils.isNotBlank(paraName) && paraName.startsWith("qnaireQuestions[")) {
                qnaireQuestions.add(new Object());
            }
            
        }
        super.populate(request);


        List<QuestionnaireQuestion> qList = populateQuestionnaireQuestions();
        if (!qList.isEmpty()) {
            QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) this;
            ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getDataObject())
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
            // TODO : qstr instanceof String[] is no longer working after rice upgrade
            // changed to AutoPopulatingList.  Should also investigate List<String> is working?
            if (qstr instanceof ArrayList) {
                String[] splitstr = ((ArrayList)qstr).get(0).toString().split("#f#");
                if (splitstr.length == 11 && !("Y").equals(splitstr[10])) {
                    QuestionnaireQuestion question = new QuestionnaireQuestion();
                    // "null" is coming between js and java code
                    if (StringUtils.isNotBlank(splitstr[0]) && !splitstr[0].equals("null")) { 
                        question.setQuestionnaireQuestionsId(Long.parseLong(splitstr[0]));
                    }
                    if (StringUtils.isNotBlank(splitstr[1])) {
                        question.setQuestionnaireRefIdFk(splitstr[1]);
                    }
                    question.setQuestionRefIdFk(Long.parseLong(splitstr[2]));
                    question.setQuestionNumber(Integer.parseInt(splitstr[3]));
                    question.setParentQuestionNumber(Integer.parseInt(splitstr[4]));
                    question.setConditionFlag("Y".equals(splitstr[5]));
                    if (StringUtils.isNotBlank(splitstr[6]) && !splitstr[6].equals("null")) { 
                        question.setCondition(splitstr[6]);
                    }
                    if (StringUtils.isNotBlank(splitstr[7]) && !splitstr[7].equals("null")) { 
                        question.setConditionValue(splitstr[7]);
                    }
                    question.setQuestionSeqNumber(Integer.parseInt(splitstr[8]));
                    question.setVersionNumber(Long.parseLong(splitstr[9]));
                    question.setDeleted(splitstr[10]);
                    qList.add(question);
                }
            }
        }
        return qList;
    }
    
    public List getQnaireQuestions() {
    //public List<String> getQnaireQuestions() {
        return qnaireQuestions;
    }

    public void setQnaireQuestions(List qnaireQuestions) {
    //public void setQnaireQuestions(List<String> qnaireQuestions) {
        this.qnaireQuestions = qnaireQuestions;
    }

    public List<QuestionnaireUsage> getQuestionnaireUsages() {
        return questionnaireUsages;
    }

    public void setQuestionnaireUsages(List<QuestionnaireUsage> questionnaireUsages) {
        this.questionnaireUsages = questionnaireUsages;
    }


    @Override
    public boolean isPropertyEditable(String propertyName) {
        if (propertyName.startsWith("document.newMaintainableObject.businessObject") 
                || propertyName.startsWith("qnaireQuestions")) {
            return true;
        } else {
            return super.isPropertyEditable(propertyName);
        }
    }


    public FormFile getTemplateFile() {
        return templateFile;
    }


    public void setTemplateFile(FormFile templateFile) {
        this.templateFile = templateFile;
    }


    public String getModuleCode() {
        return moduleCode;
    }


    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }


    public Integer getQidx() {
        return qidx;
    }


    public void setQidx(Integer qidx) {
        this.qidx = qidx;
    }


    public String getQuestionId() {
        return questionId;
    }


    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }


    public Question getQuestion() {
        return question;
    }


    public void setQuestion(Question question) {
        this.question = question;
    }


    public Integer getMoveup() {
        return moveup;
    }


    public void setMoveup(Integer moveup) {
        this.moveup = moveup;
    }


    public Integer getMovedn() {
        return movedn;
    }


    public void setMovedn(Integer movedn) {
        this.movedn = movedn;
    }


    public String getChildNode() {
        return childNode;
    }


    public void setChildNode(String childNode) {
        this.childNode = childNode;
    }


    public String getResponse() {
        return response;
    }


    public void setResponse(String response) {
        this.response = response;
    }


    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public String getBusinessObjectClassName() {
        // TODO Auto-generated method stub
        return "org.kuali.kra.questionnaire.Questionnaire";
    }

    /**
     * override this for view bootstrap data.  A new doc is initiated in this case.
     * this will make the document header looks 'Final'.
     * @see org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase#populateHeaderFields(org.kuali.rice.kew.api.WorkflowDocument)
     */
    @Override
    public void populateHeaderFields(WorkflowDocument workflowDocument) {
        super.populateHeaderFields(workflowDocument);

        // readOnly is changing several times during load.  so better with this approach
        if (this.isReadOnly() && workflowDocument.isInitiated()) {
//            getDocInfo().get(1).setDisplayValue("FINAL");
//            getDocInfo().get(2).setLookupAware(false);
//            getDocInfo().get(2).setDisplayValue("admin");
//            getDocInfo().get(3).setDisplayValue("00:00 AM 01/01/2010");
            getDocInfo().clear();
        }

    }


    public boolean isQuestionCurrentVersion() {
        return questionCurrentVersion;
    }


    public void setQuestionCurrentVersion(boolean questionCurrentVersion) {
        this.questionCurrentVersion = questionCurrentVersion;
    }


}
