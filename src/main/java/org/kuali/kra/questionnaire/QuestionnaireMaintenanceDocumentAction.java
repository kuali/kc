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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.kra.service.VersioningService;
import org.kuali.kra.service.impl.VersioningServiceImpl;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.service.SequenceAccessorService;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.web.struts.action.KualiMaintenanceDocumentAction;

public class QuestionnaireMaintenanceDocumentAction extends KualiMaintenanceDocumentAction {
    private static final Log LOG = LogFactory.getLog(QuestionnaireMaintenanceDocumentAction.class);
    private static final String PCP = "#;#";
    private static final String PQP = "#q#";
    private static final String PUP = "#u#";
    private static final String PFP = "#f#";

    /*
     * Big issue is that questionnairequestions and usages can't be included in xmldoccontent because maintframework - questions &
     * usages are not defined in maint DD's 'maintsections'. Current work around is using KraMaintenanceDocument to make rice
     * maintenance framework to think that QnQuestions & QnUsages are defined in maintenance section. So, they will be saved in
     * xmldoccontent.
     * 
     * The hierarchical nature of data and heavily depending on js also needs some manipulation, so make these a little
     * complicated..
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Questionnaire newQuestionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                .getNewMaintainableObject().getBusinessObject();
        if (newQuestionnaire.getSequenceNumber() == null) {
            newQuestionnaire.setSequenceNumber(1);
        }
        setupQuestionAndUsage(form);
        newQuestionnaire.setDocumentNumber(((MaintenanceDocumentBase) qnForm.getDocument()).getDocumentNumber());
        ActionForward forward = super.save(mapping, form, request, response);
        return forward;

    }


    /*
     * set up question and usage data for JS to parse
     */
    private void setupQuestionAndUsage(ActionForm form) {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Questionnaire questionnaire = ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                .getBusinessObject());
        cleanupQuestionnaireQuestions(questionnaire);
        String questions = assembleQuestions(qnForm);
        String usages = assembleUsages(questionnaire);
        qnForm.setEditData(questions + PCP + usages);

    }

    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        permissionCheckForDocHandler(form);
        ActionForward forward = super.docHandler(mapping, form, request, response);
        setupQuestionAndUsage(form);
        return forward;
    }

    /*
     * check if user has modify or view questionnaire permission.
     */
    private void permissionCheckForDocHandler(ActionForm form) {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        if (!getQuestionnaireAuthorizationService().hasPermission(PermissionConstants.MODIFY_QUESTIONNAIRE)) {
            if (!getQuestionnaireAuthorizationService().hasPermission(PermissionConstants.VIEW_QUESTIONNAIRE)) {
                throw new RuntimeException("Don't have permission to edit/view Questionnaire");
            }
            else {
                if (!qnForm.isReadOnly()) {
                    // if user only has VIEW_QUESTIONNAIRE permission
                    // throw new RuntimeException("Don't have permission to view Questionnaire");
                    qnForm.setReadOnly(true);

                }
            }
        }

    }

    /*
     * loop thru qnquestion and assemble results. Also find the max questionnumber.
     */
    private String getQuestionRetureResult(QuestionnaireMaintenanceForm questionnaireForm, Questionnaire questionnaire) {
        Collections.sort(questionnaire.getQuestionnaireQuestions(), new QuestionnaireQuestionComparator());
        String result = "parent-0";
        List<QuestionnaireQuestion> remainQuestions = new ArrayList<QuestionnaireQuestion>();
        for (QuestionnaireQuestion question : questionnaire.getQuestionnaireQuestions()) {
            if (!question.getParentQuestionNumber().equals(0)) {
                remainQuestions.add((QuestionnaireQuestion) ObjectUtils.deepCopy(question));
            }
        }
        for (QuestionnaireQuestion question : questionnaire.getQuestionnaireQuestions()) {
            if (question.getQuestionNumber() > questionnaireForm.getQuestionNumber()) {
                questionnaireForm.setQuestionNumber(question.getQuestionNumber());
            }
            if (question.getParentQuestionNumber().equals(0)) {
                result = result + PQP + getQnReturnfields(question);
                String childrenResult = getChildren(question, remainQuestions);
                if (StringUtils.isNotBlank(childrenResult)) {
                    result = result + childrenResult;
                }

            }
        }
        questionnaireForm.setQuestionNumber(questionnaireForm.getQuestionNumber() + 1);
        return result;

    }

    /*
     * get all the questions data needed for JS to manipulate.
     */
    private String assembleQuestions(QuestionnaireMaintenanceForm questionnaireForm) {

        Questionnaire questionnaire = (Questionnaire) ((MaintenanceDocumentBase) questionnaireForm.getDocument())
                .getNewMaintainableObject().getBusinessObject();
        questionnaireForm.setQuestionNumber(0);
        return getQuestionRetureResult(questionnaireForm, questionnaire);


    }

    /*
     * get the question properties related to response
     */
    private String getQeustionResponse(Question question) {
        String retString = "";
        if (question.getQuestionTypeId().equals(new Integer(6))) {
            String className = question.getLookupClass();
            className = className.substring(className.lastIndexOf(".") + 1);
            retString = className + PFP + question.getMaxAnswers() + PFP + question.getLookupReturn();

        }
        else {
            retString = question.getDisplayedAnswers() + PFP + question.getMaxAnswers() + PFP + question.getAnswerMaxLength();
        }
        return retString;
    }

    /*
     * get the children questions data
     */
    private String getChildren(QuestionnaireQuestion questionnaireQuestion, List<QuestionnaireQuestion> questionnaireQuestions) {
        String result = "";
        List<QuestionnaireQuestion> remainQuestions = new ArrayList<QuestionnaireQuestion>();
        for (QuestionnaireQuestion question : questionnaireQuestions) {
            if (question.getParentQuestionNumber().equals(questionnaireQuestion.getQuestionNumber())) {
                result = result + PQP + getQnReturnfields(question);
                String childrenResult = getChildren(question, questionnaireQuestions);
                if (StringUtils.isNotBlank(childrenResult)) {
                    result = result + childrenResult;
                }
            }
        }
        return result;
    }

    /*
     * this method is to get the property from questionnairequestion and question and concatenate them with "#f#" as separator. This
     * will be parsed by js to construct question node
     */
    private String getQnReturnfields(QuestionnaireQuestion question) {

        if (question.getQuestion() == null || !question.getQuestionRefIdFk().equals(question.getQuestion().getQuestionRefId())) {
            question.refreshReferenceObject("question");
        }
        String desc = question.getQuestion().getQuestion();
        if (desc.indexOf("\"") > 0) {
            desc = desc.replace("\"", "&#034;");
        }
        return question.getQuestionnaireQuestionsId() + PFP + question.getQuestionRefIdFk() + PFP + question.getQuestionSeqNumber()
                + PFP + desc + PFP + question.getQuestion().getQuestionTypeId() + PFP + question.getQuestionNumber() + PFP
                + question.getCondition() + PFP + question.getConditionValue() + PFP + question.getParentQuestionNumber() + PFP
                + question.getQuestion().getSequenceNumber() + PFP + getQeustionResponse(question.getQuestion()) + PFP
                + question.getVersionNumber() + PFP + question.getConditionFlag();

    }

    /*
     * Create a concatenated usage properties string.
     */
    private String assembleUsages(Questionnaire questionnaire) {
        String result = "";
        for (QuestionnaireUsage questionnaireUsage : questionnaire.getQuestionnaireUsages()) {
            // quid/modulecode/label
            result = result + questionnaireUsage.getQuestionnaireUsageId() + PFP + questionnaireUsage.getModuleItemCode() + PFP
                    + questionnaireUsage.getQuestionnaireLabel() + PFP + questionnaireUsage.getQuestionnaireSequenceNumber() + PFP
                    + questionnaireUsage.getModuleSubItemCode() + PFP + questionnaireUsage.getRuleId() + PFP
                    + questionnaireUsage.getVersionNumber() + PUP;
        }
        if (StringUtils.isNotBlank(result)) {
            result = result.substring(0, result.length() - 3);
        }
        return result;

    }

    @Override
    public ActionForward copy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward = super.copy(mapping, form, request, response);
        return forward;
    }

    @Override
    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward = super.edit(mapping, form, request, response);
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Questionnaire questionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                .getBusinessObject();
        Questionnaire oldQuestionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                .getOldMaintainableObject().getBusinessObject();
        versionQuestionnaire(questionnaire, oldQuestionnaire);
        Long questionnaireRefId = KraServiceLocator.getService(SequenceAccessorService.class).getNextAvailableSequenceNumber(
                "SEQ_QUESTIONNAIRE_ID");
        qnForm.setVersioned(true);
        questionnaire.setQuestionnaireRefId(questionnaireRefId);
        oldQuestionnaire.setQuestionnaireRefId(questionnaireRefId);
        String questions = assembleQuestions(qnForm);
        String usages = assembleUsages(((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                .getBusinessObject()));
        qnForm.setEditData(questions + PCP + usages);

        return forward;
    }

    @Override
    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KNSConstants.MAINTENANCE_COPY_ACTION)) {
            preRouteCopy(form);
        }

        ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getBusinessObject())
                .setDocumentNumber(((MaintenanceDocumentBase) qnForm.getDocument()).getDocumentNumber());
        setupQuestionAndUsage(form);
        ActionForward forward = super.route(mapping, form, request, response);
        return forward;

    }


    @Override
    public ActionForward blanketApprove(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KNSConstants.MAINTENANCE_COPY_ACTION)) {
            preRouteCopy(form);
        }
        setupQuestionAndUsage(form);
        ActionForward forward = super.blanketApprove(mapping, form, request, response);
        return forward;
    }

    /*
     * For 'copy' action : create the copied questionnaire
     */
    private void preRouteCopy(ActionForm form) {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Map fieldValues = new HashMap<String, Object>();
        fieldValues.put("questionnaireRefId", ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                .getOldMaintainableObject().getBusinessObject()).getQuestionnaireRefId());
        Questionnaire oldQuestionnaire = (Questionnaire) getBusinessObjectService().findByPrimaryKey(Questionnaire.class,
                fieldValues);
        Questionnaire questionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                .getBusinessObject();
        KraServiceLocator.getService(QuestionnaireService.class).copyQuestionnaire(oldQuestionnaire, questionnaire);
        questionnaire.setSequenceNumber(1); // just in case
    }

    /*
     * This is to just get rid of 'Deleted' Questionnaire
     */
    private void cleanupQuestionnaireQuestions(Questionnaire questionnaire) {
        List<QuestionnaireQuestion> dropList = new ArrayList<QuestionnaireQuestion>();
        for (QuestionnaireQuestion question : questionnaire.getQuestionnaireQuestions()) {
            if ("Y".equals(question.getDeleted())) {
                dropList.add(question);
            }
            else if (question.getQuestionnaireQuestionsId() != null && questionnaire.getQuestionnaireRefId() != null
                    && question.getQuestionnaireRefIdFk() == null) {
                dropList.add(question);
            }
        }
        questionnaire.getQuestionnaireQuestions().removeAll(dropList);
    }

    /*
     * Create new version of questionnaire
     */
    private void versionQuestionnaire(Questionnaire questionnaire, Questionnaire oldQuestionnaire) {
        try {
            VersioningService versionService = new VersioningServiceImpl();
            Questionnaire newQuestionnaire = (Questionnaire) versionService.createNewVersion(oldQuestionnaire);
            questionnaire.setQuestionnaireRefId(null);
            questionnaire.setSequenceNumber(newQuestionnaire.getSequenceNumber());
            // both collections are populated from form, so can't use the old one
            // so versioning is kind like set new seq
            for (QuestionnaireQuestion qnaireQuestion : questionnaire.getQuestionnaireQuestions()) {
                qnaireQuestion.setQuestionnaireRefIdFk(null);
                qnaireQuestion.setQuestionnaireQuestionsId(null);
            }
            for (QuestionnaireUsage qnaireUsage : questionnaire.getQuestionnaireUsages()) {
                qnaireUsage.setQuestionnaireUsageId(null);
                qnaireUsage.setQuestionnaireRefIdFk(null);
            }
            questionnaire.setDocumentNumber("");
        }
        catch (Exception e) {

        }

    }

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward = super.start(mapping, form, request, response);
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        if (qnForm.getMaintenanceAction().equals(KNSConstants.MAINTENANCE_NEW_ACTION)
                && ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getBusinessObject())
                        .getSequenceNumber() == null) {
            ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getBusinessObject())
                    .setSequenceNumber(1);
        }
        return forward;
    }

    @Override
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward = null;
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
        if (buttonClicked != null && ConfirmationQuestion.YES.equals(buttonClicked)) {
            Questionnaire questionnaire = ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                    .getNewMaintainableObject().getBusinessObject());
            questionnaire.setQuestionnaireUsages(qnForm.getQuestionnaireUsages());
            setupQuestionAndUsage(qnForm);
        }
        forward = super.close(mapping, form, request, response);
        if (buttonClicked == null || ConfirmationQuestion.NO.equals(buttonClicked)) {
            Questionnaire questionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                    .getNewMaintainableObject().getBusinessObject();
            qnForm.setQuestionnaireUsages(questionnaire.getQuestionnaireUsages());
            // also need to save usages data in form to be restored if 'yes' is clicked
            // this is processed twice, so questionnaireUsage will be reset in qnform.reset
            // that's why we need to save this for restoration later
            questionnaire.setQuestionnaireUsages(new ArrayList<QuestionnaireUsage>());
        }
        return forward;
    }


    private QuestionnaireAuthorizationService getQuestionnaireAuthorizationService() {
        return KraServiceLocator.getService(QuestionnaireAuthorizationService.class);
    }

}
