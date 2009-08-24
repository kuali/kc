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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.VersioningService;
import org.kuali.kra.service.impl.VersioningServiceImpl;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm;

public class QuestionnaireMaintenanceForm extends KualiMaintenanceForm {
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
    private String lookupResultsBOClassName;
    private String action;
    private String docStatus;
    private Integer numOfQuestions;
    private Integer numOfUsages;
    private List qnaireQuestions;

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
        questionNumber = 1;
        sqlScripts = "";
        retData = "";
        editData = "";
        action = "";

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
        if (StringUtils.isNotBlank(action)) {
            if (action.equals("savebo")) {
                // only pass questionnaire data
                Questionnaire questionnaire = getNewQuestionnaire();

                if (KraServiceLocator.getService(QuestionnaireService.class).isQuestionnaireNameExist(
                        questionnaire.getQuestionnaireId(), questionnaire.getName())) {
                    retData = "<h3>true</h3>";
                }
                else {

                    String desc = questionnaire.getDescription();
                    if (desc.indexOf(";amp") > 0) {
                        desc = desc.replace(";amp", "&");
                        questionnaire.setDescription(desc);
                    }

                    if (questionnaire.getQuestionnaireRefId() != null) {
                        Map pkMap = new HashMap();
                        pkMap.put("questionnaireRefId", questionnaire.getQuestionnaireRefId());
                        Questionnaire oldQuestionnair = (Questionnaire) KraServiceLocator.getService(BusinessObjectService.class)
                                .findByPrimaryKey(Questionnaire.class, pkMap);
                        // if (questionnaire.getQuestionnaireId().equals(0)) {
                        if (questionnaire.getQuestionnaireId() != null && getDocStatus().equals("I")) {
                            try {
                                VersioningService versionService = new VersioningServiceImpl();
                                questionnaire = (Questionnaire) versionService.createNewVersion(oldQuestionnair);
                                questionnaire.setQuestionnaireRefId(null);
                            }
                            catch (Exception e) {

                            }

                        }
                        else {
                            questionnaire.setVersionNumber(oldQuestionnair.getVersionNumber());
                            questionnaire.setSequenceNumber(oldQuestionnair.getSequenceNumber());
                            questionnaire.setQuestionnaireId(oldQuestionnair.getQuestionnaireId());
                        }
                    }
                    KraServiceLocator.getService(BusinessObjectService.class).save(questionnaire);
                    retData = "<h3>qnaireID=" + questionnaire.getQuestionnaireRefId() + ";" + questionnaire.getQuestionnaireId()
                            + ";" + questionnaire.getSequenceNumber() + "</h3>";
                }
            }
            else if (action.equals("savebo1")) {
                // 2nd half of the description
                Questionnaire questionnaire = getNewQuestionnaire();
                String desc = questionnaire.getDescription();
                if (desc.indexOf(";amp") > 0) {
                    desc = desc.replace(";amp", "&");
                    questionnaire.setDescription(desc);
                }
                if (questionnaire.getQuestionnaireRefId() != null) {
                    Map pkMap = new HashMap();
                    pkMap.put("questionnaireRefId", questionnaire.getQuestionnaireRefId());
                    Questionnaire oldQuestionnair = (Questionnaire) KraServiceLocator.getService(BusinessObjectService.class)
                            .findByPrimaryKey(Questionnaire.class, pkMap);
                    questionnaire.setVersionNumber(oldQuestionnair.getVersionNumber());
                    questionnaire.setDescription(oldQuestionnair.getDescription() + questionnaire.getDescription());
                }
                KraServiceLocator.getService(BusinessObjectService.class).save(questionnaire);
                retData = "<h3>qnaireID=" + questionnaire.getQuestionnaireRefId() + ";" + questionnaire.getQuestionnaireId() + ";"
                        + questionnaire.getSequenceNumber() + "</h3>";
            }
            else if (action.equals("checkname")) {
                Questionnaire questionnaire = getNewQuestionnaire();
                if (KraServiceLocator.getService(QuestionnaireService.class).isQuestionnaireNameExist(
                        questionnaire.getQuestionnaireId(), questionnaire.getName())) {
                    retData = "<h3>true</h3>";
                }
                else {
                    retData = "<h3>false</h3>";
                }
            }
            else {
                Questionnaire questionnaire = new Questionnaire();
                questionnaire = getNewQuestionnaire();
                KraServiceLocator.getService(QuestionnaireService.class).saveQuestionnaire(sqlScripts, questionnaire);
                String error = (String) GlobalVariables.getUserSession().retrieveObject("qnError");
                if (StringUtils.isNotBlank(error)) {
                    retData = "<h3>" + error + "</h3>";
                    GlobalVariables.getUserSession().addObject("qnError", (Object) null);
                }
                else {
                    retData = "<h3>qnaireID=" + questionnaire.getQuestionnaireRefId() + ";" + questionnaire.getQuestionnaireId()
                            + ";" + questionnaire.getSequenceNumber() + "</h3>";
                }
            }
            action = "";
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
        if (this.getDocument() != null) {
            Questionnaire qn = (Questionnaire) ((MaintenanceDocumentBase) this.getDocument()).getNewMaintainableObject()
                    .getBusinessObject();
            if (qn != null
                    && !((MaintenanceDocumentBase) this.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                            "Copy")) {
                int num = (Integer) GlobalVariables.getUserSession().retrieveObject("numOfQuestions");
                for (int i = 0; i < num; i++) {
                    // qn.getQuestionnaireQuestions().add(new QuestionnaireQuestion());
                    getQnaireQuestions().add("");
                }
                num = (Integer) GlobalVariables.getUserSession().retrieveObject("numOfUsages");
                for (int i = 0; i < num; i++) {
                    qn.getQuestionnaireUsages().add(new QuestionnaireUsage());
                }
            }
        }
        // for (Iterator iter = request.getParameterMap().keySet().iterator(); iter.hasNext();) {
        // String keypath = (String) iter.next();
        // if (keypath.contains("questionnaireQuestionsId") || keypath.contains("parentQuestionNumber") ||
        // keypath.contains("questionNumber") || keypath.contains("questionnaireRefId")) {
        // System.out.println(">> " + keypath+" - "+((String[])request.getParameterMap().get(keypath))[0]);
        // //Object param = request.getParameterMap().get(keypath);
        // }
        // }
        super.populate(request);

        // $("#"+jqprefix + idx+"\\]\\.questionnaireQuestionsId").attr("value",field[0]);
        // $("#"+jqprefix + idx+"\\]\\.questionnaireRefIdFk").attr("value",refid);
        // $("#"+jqprefix + idx+"\\]\\.questionRefIdFk").attr("value",field[1]);
        // $("#"+jqprefix + idx+"\\]\\.questionNumber").attr("value",field[5]);
        // $("#"+jqprefix + idx+"\\]\\.parentQuestionNumber").attr("value",field[8]);
        // $("#"+jqprefix + idx+"\\]\\.conditionFlag").attr("value",field[14]);
        // $("#"+jqprefix + idx+"\\]\\.condition").attr("value",field[6]);
        // $("#"+jqprefix + idx+"\\]\\.conditionValue").attr("value",field[7]);
        // $("#"+jqprefix + idx+"\\]\\.questionSeqNumber").attr("value",field[2]);
        // $("#"+jqprefix + idx+"\\]\\.versionNumber").attr("value",field[13]);
        // $("#"+jqprefix + idx+"\\]\\.deleted").attr("value","N");

        List<QuestionnaireQuestion> qList = new ArrayList<QuestionnaireQuestion>();
        for (Object qstr : getQnaireQuestions()) {
            if (qstr instanceof String[]) {
                String[] splitstr = ((String[])qstr)[0].split("#f#");
                if (splitstr.length == 11) {
                QuestionnaireQuestion question = new QuestionnaireQuestion();
                if (StringUtils.isNotBlank(splitstr[0])) {
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
        if (!qList.isEmpty()) {
            QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) this;
            ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
            .getNewMaintainableObject().getBusinessObject()).setQuestionnaireQuestions(qList);
        }
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

    // @Override
    // public String getDocTypeName() {
    // // TODO Auto-generated method stub
    // return "QuestionMaintenanceDocument";
    // //return super.getDocTypeName();
    // }

}
