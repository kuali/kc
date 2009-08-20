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
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
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
    private String lookupResultsBOClassName;
    private String action;
    private String docStatus;
    private Integer numOfQuestions;
    private Integer numOfUsages;

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
    public QuestionnaireForm() {
        super();
        newQuestionnaire = new Questionnaire();
        questionnaireQuestions = new ArrayList<QuestionnaireQuestion>();
        // TODO : if it is newquestionnaire, then set questionnumber to 1
        questionNumber = 1;

    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // FIXME : just a temporary soln. it always get the methodtocall='refresh' after it started properly the first time.
        // need to investigate this.
        this.setMethodToCall("");
        // TODO : if do lookup again to edit, 'form' is not initialized ? initialized here ?
        newQuestionnaire = new Questionnaire();
        questionnaireQuestions = new ArrayList<QuestionnaireQuestion>();
        questionNumber = 1;
        sqlScripts = "";
        retData = "";
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
                questionnaire.setSequenceNumber(1);

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
                        if (questionnaire.getQuestionnaireId() != null && getDocStatus().equals("I")) {
                            // TODO : is this a good way to make sure that it is 'edit' and first save ?
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
                retData = "<h3>qnaireID=" + questionnaire.getQuestionnaireRefId() + ";" + questionnaire.getQuestionnaireId()
                + ";" + questionnaire.getSequenceNumber() + "</h3>";
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
            else if (action.equals("setnumq")) {
                GlobalVariables.getUserSession().addObject("numOfQuestions", getNumOfQuestions());
                GlobalVariables.getUserSession().addObject("numOfUsages", getNumOfUsages());
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

    public String getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
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

}
