/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.questionnaire.impl.core;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.questionnaire.framework.core.Questionnaire;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireQuestion;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireUsage;
import org.kuali.coeus.common.questionnaire.framework.question.Question;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm;
import org.springframework.util.AutoPopulatingList;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
    // "List" without parameter will cause Notserializableexception; so add "transient" here
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
    private String ruleId;
    public String getRuleId() {
        return ruleId;
    }


    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }


    private boolean questionCurrentVersion = true;

    private boolean allQuestionsAreUpToDate = true;


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
        this.setMethodToCall("");
        qnaireQuestions = new AutoPopulatingList<Object>(Object.class);
        // to prevent indexoutofbound exception when populate
        if (this.getDocument() != null) {
            Questionnaire qn = (Questionnaire) ((MaintenanceDocumentBase) this.getDocument()).getNewMaintainableObject()
                    .getBusinessObject();
            qn.setActive(false);
            qn.setQuestionnaireUsages(new AutoPopulatingList<QuestionnaireUsage>(qn.getQuestionnaireUsages(), QuestionnaireUsage.class));
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
            if (qstr instanceof ArrayList) {
                String[] splitstr = ((ArrayList)qstr).get(0).toString().split("#f#");
                if (splitstr.length == 12 && !("Y").equals(splitstr[10])) {
                    QuestionnaireQuestion question = new QuestionnaireQuestion();
                    // "null" is coming between js and java code
                    if (StringUtils.isNotBlank(splitstr[0]) && !splitstr[0].equals("null")) { 
                        question.setId(Long.parseLong(splitstr[0]));
                    }
                    if (StringUtils.isNotBlank(splitstr[1])) {
                        question.setQuestionnaireId(Long.valueOf(splitstr[1]));
                    }
                    question.setQuestionId(Long.parseLong(splitstr[2]));
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
                    if (StringUtils.isNotBlank(splitstr[11]) && !splitstr[11].equals("null")) { 
                        question.setRuleId(splitstr[11]);
                    }else{
                        question.setRuleId(null);
                    }
                    qList.add(question);
                }
            }
        }
        return qList;
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

        return Questionnaire.class.getName();
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


    public void setAllQuestionsAreUpToDate(boolean allQuestionsAreUpToDate) {
        this.allQuestionsAreUpToDate = allQuestionsAreUpToDate;
    }


    public boolean isAllQuestionsAreUpToDate() {
        return allQuestionsAreUpToDate;
    }


}
