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
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.SequenceAccessorService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.web.struts.action.KualiMaintenanceDocumentAction;

public class QuestionnaireMaintenanceDocumentAction extends KualiMaintenanceDocumentAction {
    private static final Log LOG = LogFactory.getLog(QuestionnaireMaintenanceDocumentAction.class);

    // TODO : big mess is that questionquestions and usages can't be included in xmldoccontent
    // because maintframework - questions & usages are not defined in 'maintsections'
    // then it will not be in xmldoccontent
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        // if (form instanceof QuestionnaireMaintenanceForm) {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Questionnaire oldBo = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getOldMaintainableObject()
                .getBusinessObject();
        Questionnaire newBo = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                .getBusinessObject();
        if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KNSConstants.MAINTENANCE_COPY_ACTION)) {
            // newBo.setName(qnForm.getNewQuestionnaire().getName());
            // newBo.setDescription(qnForm.getNewQuestionnaire().getDescription());
            // newBo.setIsFinal(qnForm.getNewQuestionnaire().getIsFinal());
            newBo.setSequenceNumber(1);
            // TODO : set doc# here may cause confusion
            // newBo.setDocumentNumber(qnForm.getDocument().getDocumentNumber());
            if (oldBo.getQuestionnaireId().equals(newBo.getQuestionnaireId())) {
                Integer questionnaireId = Integer.parseInt(KraServiceLocator.getService(SequenceAccessorService.class)
                        .getNextAvailableSequenceNumber("SEQ_QUESTIONNAIRE_ID").toString());
                newBo.setQuestionnaireId(questionnaireId);

            }
        }
        else {
            // if (StringUtils.isNotBlank(qnForm.getSqlScripts())) {
            saveQn(qnForm);
            // }
            Questionnaire questionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                    .getNewMaintainableObject().getBusinessObject();
            if (questionnaire.getSequenceNumber() == null) {
                // TODO : create new first time
                questionnaire.setSequenceNumber(1);
            }
            // questionnaire.refreshReferenceObject("questionnaireQuestions");
            // ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().setBusinessObject(questionnaire);
            String questions = assembleQuestions(qnForm);
            String usages = assembleUsages((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                    .getNewMaintainableObject().getBusinessObject());
            qnForm.setEditData(questions + "#;#" + usages);
            if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                    KNSConstants.MAINTENANCE_EDIT_ACTION)) {
                // TODO : force it to have the same key, so it can be approve later.
                // rice maintenance framework - 'edit' is expecting old & new have the same pk
                ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getOldMaintainableObject().getBusinessObject())
                        .setQuestionnaireRefId(((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                                .getNewMaintainableObject().getBusinessObject()).getQuestionnaireRefId());
            }
        }
        return super.save(mapping, form, request, response);

        // return forward;
    }

    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        if (!KraServiceLocator.getService(QuestionnaireAuthorizationService.class).hasPermission(
                PermissionConstants.MODIFY_QUESTIONNAIRE)) {
            if (!KraServiceLocator.getService(QuestionnaireAuthorizationService.class).hasPermission(
                    PermissionConstants.VIEW_QUESTIONNAIRE)) {
                throw new RuntimeException("Don't have permission to edit/view Questionnaire");
            }
            else {
                if (!qnForm.isReadOnly()) {
                    throw new RuntimeException("Don't have permission to view Questionnaire");

                }
            }
        }
        ActionForward forward = super.docHandler(mapping, form, request, response);
        // if (form instanceof QuestionnaireMaintenanceForm) {
        // Questionnaire questionnaire = ((QuestionnaireMaintenanceForm) form).getNewQuestionnaire();
        // questionnaire.refreshReferenceObject("questionnaireQuestions");
        if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KNSConstants.MAINTENANCE_COPY_ACTION)) {
            // view copied document
            if (qnForm.getDocument().getDocumentHeader().getWorkflowDocument().getRouteHeader().getDocRouteStatus().equals("F")) {
                Map<String, Object> fieldValues = new HashMap<String, Object>();
                fieldValues.put("documentNumber", qnForm.getDocument().getDocumentNumber());
                // use findbypk is little stretched. But is is actually doing findmatching has nothing to do with pk
                Questionnaire questionnaire = (Questionnaire) KraServiceLocator.getService(BusinessObjectService.class)
                        .findByPrimaryKey(Questionnaire.class, fieldValues);
                ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getBusinessObject())
                        .setQuestionnaireRefId(questionnaire.getQuestionnaireRefId());
                String questions = assembleQuestions(qnForm);
                String usages = assembleUsages((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                        .getNewMaintainableObject().getBusinessObject());
                qnForm.setEditData(questions + "#;#" + usages);

            }
            // qnForm.setFromQuestionnaire((Questionnaire) ((MaintenanceDocumentBase)
            // qnForm.getDocument()).getOldMaintainableObject()
            // .getBusinessObject());
            // qnForm.setNewQuestionnaire((Questionnaire) ((MaintenanceDocumentBase)
            // qnForm.getDocument()).getNewMaintainableObject()
            // .getBusinessObject());
        }
        else {
            // qnForm.setNewQuestionnaire((Questionnaire) ((MaintenanceDocumentBase)
            // qnForm.getDocument()).getNewMaintainableObject()
            // .getBusinessObject());
            if (!qnForm.getDocument().getDocumentHeader().getWorkflowDocument().getRouteHeader().getDocRouteStatus().equals(
                    KEWConstants.ROUTE_HEADER_CANCEL_CD)) {
                String questions = assembleQuestions(qnForm);
                String usages = assembleUsages((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                        .getNewMaintainableObject().getBusinessObject());
                qnForm.setEditData(questions + "#;#" + usages);
            }
        }
        // }
        return forward;
    }


    // utility methods
    private String assembleQuestions(QuestionnaireMaintenanceForm questionnaireForm) {

        Questionnaire newQuestionnaire = (Questionnaire) ((MaintenanceDocumentBase) questionnaireForm.getDocument())
        // Questionnaire questionnaire = (Questionnaire) ((MaintenanceDocumentBase) questionnaireForm.getDocument())
                .getNewMaintainableObject().getBusinessObject();
        // newQuestionnaire.refreshReferenceObject("questionnaireQuestions");
        // if kramaintdoc i used then the following should be included in xmldoc
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("questionnaireRefId", newQuestionnaire.getQuestionnaireRefId());
        // use findbypk is little stretched. But is is actually doing findmatching has nothing to do with pk
        Questionnaire questionnaire = (Questionnaire) KraServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(
                Questionnaire.class, fieldValues);
        // not sure why the collections are not retrieved ?
        // maybe these are added by runsql ?
        questionnaire.refreshReferenceObject("questionnaireQuestions");
        questionnaire.refreshReferenceObject("questionnaireUsages");
        /*
         * TODO : object retrieved by busobjservice then assign to newmain.busobj Then go thru document.save, always caused lots of
         * issue. so has copy it then assign to newmaint.busobj
         */
        Questionnaire copyQuestionnaire = (Questionnaire) ObjectUtils.deepCopy(questionnaire);
        // ((MaintenanceDocumentBase) questionnaireForm.getDocument()).getNewMaintainableObject().setBusinessObject(questionnaire);
        ((MaintenanceDocumentBase) questionnaireForm.getDocument()).getNewMaintainableObject().setBusinessObject(copyQuestionnaire);
        // questionnaire.refreshReferenceObject("questionnaireQuestions");
        questionnaireForm.setQuestionNumber(0);
        Collections.sort(questionnaire.getQuestionnaireQuestions(), new QuestionnaireQuestionComparator());
        String result = "parent-0";
        Integer parentNumber = 0;
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
            // TODO : for now just load the 1st level question for editing
            if (question.getParentQuestionNumber().equals(0)) {
                // if (question.getParentQuestionNumber().compareTo(parentNumber) > 0) {
                // parentNumber = question.getParentQuestionNumber();
                // result = result + "#q#parent-" + parentNumber;
                // }
                // qqid/qid/seq/desc/qtypeid/qnum/cond/condvalue/parentqnum/questionseqnum
                if (question.getQuestion() == null
                        || !question.getQuestionRefIdFk().equals(question.getQuestion().getQuestionRefId())) {
                    question.refreshReferenceObject("question");
                }
                String desc = question.getQuestion().getQuestion();
                // TODO : : need to deal with '"' in questio's description
                // also see QuestionLookupAction
                if (desc.indexOf("\"") > 0) {
                    desc = desc.replace("\"", "&#034;");
                }
                result = result + "#q#" + question.getQuestionnaireQuestionsId() + "#f#" + question.getQuestionRefIdFk() + "#f#"
                        + question.getQuestionSeqNumber() + "#f#" + desc + "#f#" + question.getQuestion().getQuestionTypeId()
                        + "#f#" + question.getQuestionNumber() + "#f#" + question.getCondition() + "#f#"
                        + question.getConditionValue() + "#f#" + question.getParentQuestionNumber() + "#f#"
                        + question.getQuestion().getSequenceNumber() + "#f#" + getQeustionResponse(question.getQuestion()) + "#f#"
                        + question.getVersionNumber() + "#f#" + question.getConditionFlag();
                String childrenResult = getChildren(question, remainQuestions);
                if (StringUtils.isNotBlank(childrenResult)) {
                    result = result + childrenResult;
                }

            }
        }
        questionnaireForm.setQuestionNumber(questionnaireForm.getQuestionNumber() + 1);
        // if (StringUtils.isNotBlank(result)) {
        // result = result.substring(0,result.length()-3);
        // }
        // TODO : test versioning
        // Questionnaire qnaire = null;
        // try {
        // VersioningService versionService = new VersioningServiceImpl();
        // qnaire = (Questionnaire) versionService.createNewVersion(questionnaire);
        // }
        // catch (Exception e) {
        //
        // }
        // qnaire.getQuestionnaireId();
        return result;


    }

    private String getQeustionResponse(Question question) {
        String retString = "";
        if (question.getQuestionTypeId().equals(new Integer(6))) {
            String className = question.getLookupClass();
            className = className.substring(className.lastIndexOf(".") + 1);
            retString = className + "#f#" + question.getMaxAnswers() + "#f#" + question.getLookupReturn();

        }
        else {
            retString = question.getDisplayedAnswers() + "#f#" + question.getMaxAnswers() + "#f#" + question.getAnswerMaxLength();
        }
        return retString;
    }

    private String getChildren(QuestionnaireQuestion questionnaireQuestion, List<QuestionnaireQuestion> questionnaireQuestions) {
        String result = "";
        List<QuestionnaireQuestion> remainQuestions = new ArrayList<QuestionnaireQuestion>();
        for (QuestionnaireQuestion question : questionnaireQuestions) {
            if (question.getParentQuestionNumber().equals(questionnaireQuestion.getQuestionNumber())) {
                if (question.getQuestion() == null
                        || !question.getQuestionRefIdFk().equals(question.getQuestion().getQuestionRefId())) {
                    question.refreshReferenceObject("question");
                }
                String desc = question.getQuestion().getQuestion();
                if (desc.indexOf("\"") > 0) {
                    desc = desc.replace("\"", "&#034;");
                }
                result = result + "#q#" + question.getQuestionnaireQuestionsId() + "#f#" + question.getQuestionRefIdFk() + "#f#"
                        + question.getQuestionSeqNumber() + "#f#" + desc + "#f#" + question.getQuestion().getQuestionTypeId()
                        + "#f#" + question.getQuestionNumber() + "#f#" + question.getCondition() + "#f#"
                        + question.getConditionValue() + "#f#" + question.getParentQuestionNumber() + "#f#"
                        + question.getQuestion().getSequenceNumber() + "#f#" + getQeustionResponse(question.getQuestion()) + "#f#"
                        + question.getVersionNumber() + "#f#" + question.getConditionFlag();
                // TODO : not efficient. should only check the questions that have not checked
                // remainQuestions = ObjectUtils.deepCopy(questionnaireQuestions);
                String childrenResult = getChildren(question, questionnaireQuestions);
                if (StringUtils.isNotBlank(childrenResult)) {
                    result = result + childrenResult;
                }
            }
        }
        return result;
    }

    private String assembleUsages(Questionnaire questionnaire) {
        String result = "";
        for (QuestionnaireUsage questionnaireUsage : questionnaire.getQuestionnaireUsages()) {
            // quid/modulecode/label
            result = result + questionnaireUsage.getQuestionnaireUsageId() + "#f#" + questionnaireUsage.getModuleItemCode() + "#f#"
                    + questionnaireUsage.getQuestionnaireLabel() + "#f#" + questionnaireUsage.getQuestionnaireSequenceNumber()
                    + "#f#" + questionnaireUsage.getModuleSubItemCode() + "#f#" + questionnaireUsage.getRuleId() + "#f#"
                    + questionnaireUsage.getVersionNumber() + "#u#";
        }
        if (StringUtils.isNotBlank(result)) {
            result = result.substring(0, result.length() - 3);
        }
        return result;

    }

    // private void setQuestionnaireRefid(Questionnaire questionnaire) {
    // Map<String, Object> fieldValues = new HashMap<String, Object>();
    // fieldValues.put("name", questionnaire.getName());
    // // use findbypk is little stretched. But is is actually doing findmatching has nothing to do with pk
    // Questionnaire questionnaire1 = (Questionnaire) KraServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(
    // Questionnaire.class, fieldValues);
    // if (questionnaire1 != null) {
    // questionnaire.setQuestionnaireRefId(questionnaire1.getQuestionnaireRefId());
    // questionnaire.setSequenceNumber(questionnaire1.getSequenceNumber());
    // questionnaire.setQuestionnaireId(questionnaire1.getQuestionnaireId());
    // }
    //
    // }

    @Override
    public ActionForward copy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 'document' is null at this point
        ActionForward forward = super.copy(mapping, form, request, response);
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("questionnaireRefId", request.getParameter("questionnaireRefId"));
        Questionnaire questionnaire = (Questionnaire) KraServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(
                Questionnaire.class, fieldValues);
        ((MaintenanceDocumentBase) qnForm.getDocument()).getOldMaintainableObject().setBusinessObject(questionnaire);
        Questionnaire newQuestionnaire = new Questionnaire();
        newQuestionnaire.setDescription(questionnaire.getDescription());
        newQuestionnaire.setName(questionnaire.getName());
        ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().setBusinessObject(newQuestionnaire);
        return forward;
    }

    @Override
    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 'document' is null at this point
        ActionForward forward = super.edit(mapping, form, request, response);
        // Integer questionnaireId = Integer.parseInt(request.getParameter("questionnaireId"));
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getBusinessObject())
                .setQuestionnaireRefId(Long.parseLong(request.getParameter("questionnaireRefId")));

        // Map<String, Object> fieldValues = new HashMap<String, Object>();
        // fieldValues.put("questionnaireId", request.getParameter("questionnaireId"));
        // use findbypk is little stretched. But is is actually doing findmatching has nothing to do with pk
        // Questionnaire questionnaire =
        // (Questionnaire)KraServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(Questionnaire.class,
        // fieldValues);
        // qnForm.setNewQuestionnaire(questionnaire);
        String questions = assembleQuestions(qnForm);
        String usages = assembleUsages(((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                .getBusinessObject()));
        qnForm.setEditData(questions + "#;#" + usages);
        // TODO : hack for 1st save to version
        // qnForm.getNewQuestionnaire().setQuestionnaireId(0);
        return forward;
    }

    @Override
    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        preRoute(form);
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Long questionnaireRefId = null;
        Long oldQuestionnaireRefId = null;
        if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KNSConstants.MAINTENANCE_NEW_ACTION)) {
            questionnaireRefId = ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                    .getBusinessObject()).getQuestionnaireRefId();
            oldQuestionnaireRefId = ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getOldMaintainableObject()
                    .getBusinessObject()).getQuestionnaireRefId();
        }
        ActionForward forward = null;
        try {
            // TODO : need super.save, so the run sql will be kind of committed to db
            // This will be confusing to developer, so need to find something else
            // super.save(mapping, form, request, response);
            forward = super.route(mapping, form, request, response);
        }
        catch (Exception e) {
            if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                    KNSConstants.MAINTENANCE_NEW_ACTION)
                    && questionnaireRefId != null) {
                // this is only for new action
                // for edit, oldqrefid is null, so should not set here
                ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getBusinessObject())
                        .setQuestionnaireRefId(oldQuestionnaireRefId);
            }
            throw e;
        }
        // if there is rule violation, then it will be redirected directly to jsp page. the following script will not be executed.
        postRoute(form);
        return forward;

    }

    private void preRoute(ActionForm form) {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Questionnaire oldBo = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getOldMaintainableObject()
                .getBusinessObject();
        Questionnaire newBo = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                .getBusinessObject();
        if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KNSConstants.MAINTENANCE_COPY_ACTION)) {
            newBo.setSequenceNumber(1);
            newBo.setDocumentNumber(qnForm.getDocument().getDocumentNumber());
            if (oldBo.getQuestionnaireId().equals(newBo.getQuestionnaireId())) {
                Integer questionnaireId = Integer.parseInt(KraServiceLocator.getService(SequenceAccessorService.class)
                        .getNextAvailableSequenceNumber("SEQ_QUESTIONNAIRE_ID").toString());
                newBo.setQuestionnaireId(questionnaireId);

            }
        }
        else {
            saveQn(qnForm);
            // if (StringUtils.isNotBlank(qnForm.getSqlScripts())) {
            String questions = assembleQuestions(qnForm);
            String usages = assembleUsages(((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                    .getNewMaintainableObject().getBusinessObject()));
            qnForm.setEditData(questions + "#;#" + usages);
            // }
        }
        // Long questionnaireRefId = null;
        // Long oldQuestionnaireRefId = null;
        if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KNSConstants.MAINTENANCE_NEW_ACTION)) {
            Long questionnaireRefId = KraServiceLocator.getService(SequenceAccessorService.class).getNextAvailableSequenceNumber(
                    "SEQ_QUESTIONNAIRE_ID");
            // oldQuestionnaireRefId = ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
            // .getBusinessObject()).getQuestionnaireRefId();
            Questionnaire newQuestionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                    .getNewMaintainableObject().getBusinessObject();
            // save original refid
            ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getOldMaintainableObject().getBusinessObject())
                    .setQuestionnaireRefId(newQuestionnaire.getQuestionnaireRefId());
            newQuestionnaire.setQuestionnaireRefId(questionnaireRefId);

        }
        if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KNSConstants.MAINTENANCE_EDIT_ACTION)) {
            Map fieldValues = new HashMap<String, Object>();
            fieldValues.put("questionnaireRefId", ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                    .getNewMaintainableObject().getBusinessObject()).getQuestionnaireRefId());
            Questionnaire oldQuestionnaire = (Questionnaire) KraServiceLocator.getService(BusinessObjectService.class)
                    .findByPrimaryKey(Questionnaire.class, fieldValues);
            Questionnaire newQuestionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                    .getNewMaintainableObject().getBusinessObject();
            ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getOldMaintainableObject().getBusinessObject())
                    .setQuestionnaireRefId(newQuestionnaire.getQuestionnaireRefId());
            newQuestionnaire.setDocumentNumber(qnForm.getDocument().getDocumentNumber());
            newQuestionnaire.setVersionNumber(oldQuestionnaire.getVersionNumber());
        }

    }

    @Override
    public ActionForward approve(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO if status is final, then should be handled like 'route'
        return super.approve(mapping, form, request, response);
    }

    @Override
    public ActionForward blanketApprove(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        preRoute(form);
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Long questionnaireRefId = null;
        Long oldQuestionnaireRefId = null;
        if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KNSConstants.MAINTENANCE_NEW_ACTION)) {
            questionnaireRefId = ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                    .getBusinessObject()).getQuestionnaireRefId();
            oldQuestionnaireRefId = ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getOldMaintainableObject()
                    .getBusinessObject()).getQuestionnaireRefId();
        }
        ActionForward forward = null;
        try {
            forward = super.blanketApprove(mapping, form, request, response);
        }
        catch (Exception e) {
            if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                    KNSConstants.MAINTENANCE_NEW_ACTION)
                    && questionnaireRefId != null) {
                // this is only for new action
                // for edit, oldqrefid is null, so should not set here
                ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getBusinessObject())
                        .setQuestionnaireRefId(oldQuestionnaireRefId);
            }
            throw e;
        }
        postRoute(form);
        return forward;
    }


    private void postRoute(ActionForm form) {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KNSConstants.MAINTENANCE_NEW_ACTION)) {
            Long questionnaireRefId = ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                    .getBusinessObject()).getQuestionnaireRefId();
            Long oldQuestionnaireRefId = ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                    .getOldMaintainableObject().getBusinessObject()).getQuestionnaireRefId();
            if (questionnaireRefId != null) {
                Map fieldValues = new HashMap<String, Object>();
                fieldValues.put("questionnaireRefId", oldQuestionnaireRefId);
                Questionnaire oldQuestionnaire = (Questionnaire) KraServiceLocator.getService(BusinessObjectService.class)
                        .findByPrimaryKey(Questionnaire.class, fieldValues);
                oldQuestionnaire.refreshReferenceObject("questionnaireQuestions");
                oldQuestionnaire.refreshReferenceObject("questionnaireUsages");
                Questionnaire copyQuestionnaire = (Questionnaire) ObjectUtils.deepCopy(oldQuestionnaire);
                fieldValues.put("questionnaireRefId", questionnaireRefId);
                Questionnaire newQuestionnaire = (Questionnaire) KraServiceLocator.getService(BusinessObjectService.class)
                        .findByPrimaryKey(Questionnaire.class, fieldValues);
                newQuestionnaire.setQuestionnaireQuestions(copyQuestionnaire.getQuestionnaireQuestions());
                newQuestionnaire.setQuestionnaireUsages(copyQuestionnaire.getQuestionnaireUsages());
                // newQuestionnaire.setVersionNumber(1L);
                newQuestionnaire.setDocumentNumber(qnForm.getDocument().getDocumentNumber());
                getBusinessObjectService().save(newQuestionnaire);
                // getBusinessObjectService().delete(delBos);
                getBusinessObjectService().delete(oldQuestionnaire);
            }
        }
        if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KNSConstants.MAINTENANCE_COPY_ACTION)) {
            Questionnaire newQuestionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                    .getNewMaintainableObject().getBusinessObject();
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("name", newQuestionnaire.getName());
            /*
             * TODO : this may have issue because same id with different version may have same name use findbypk is little
             * stretched. But is is actually doing findmatching has nothing to do with pk At this point, the new questionnaire is
             * created in db but not committed yet. Also, newmaintobj.busobj still is not updated with the refid/id, so have to
             * retrieve with 'name' if at this point,the name is unique because rule validation passed. but still like a little
             * hack. can we use documentnumber to retrieve ?
             */
            Questionnaire questionnaire = (Questionnaire) KraServiceLocator.getService(BusinessObjectService.class)
                    .findByPrimaryKey(Questionnaire.class, fieldValues);
            if (questionnaire != null) {
                fieldValues = new HashMap<String, Object>();
                fieldValues.put("questionnaireRefId", ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                        .getOldMaintainableObject().getBusinessObject()).getQuestionnaireRefId());
                Questionnaire oldQuestionnaire = (Questionnaire) KraServiceLocator.getService(BusinessObjectService.class)
                        .findByPrimaryKey(Questionnaire.class, fieldValues);
                KraServiceLocator.getService(QuestionnaireService.class).copyQuestionnaire(oldQuestionnaire, questionnaire);
            }

        }

    }

    private void saveQn(ActionForm form) {

        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Questionnaire questionnaire = ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                .getBusinessObject());

        // questionnaire.getQuestionnaireQuestions().remove(0);
        // questionnaire.getQuestionnaireQuestions().remove(0);
        if (!questionnaire.getQuestionnaireUsages().isEmpty()) {
            questionnaire.getQuestionnaireUsages().remove(0);
        }
        List<QuestionnaireQuestion> dropList = new ArrayList<QuestionnaireQuestion>();
        List<QuestionnaireQuestion> deleteList = new ArrayList<QuestionnaireQuestion>();
        // Map qMap = new HashMap();
        int i = 0;
        for (QuestionnaireQuestion question : questionnaire.getQuestionnaireQuestions()) {
            System.out.println("qnquestion " + (i++) + question.toStringMapper());
            if ("Y".equals(question.getDeleted())) {
                if (question.getQuestionnaireQuestionsId() == null) {
                    dropList.add(question);
                }
                else {
                    dropList.add(question);
                    deleteList.add(question);
                    // qMap.put("questionnaireQuestionsId", question.getQuestionnaireQuestionsId());
                    // QuestionnaireQuestion oldQuestion = (QuestionnaireQuestion)
                    // KraServiceLocator.getService(BusinessObjectService.class)
                    // .findByPrimaryKey(QuestionnaireQuestion.class, qMap);
                    // deleteList.add(oldQuestion);
                }
            }
            else if (question.getQuestionnaireQuestionsId() != null && questionnaire.getQuestionnaireRefId() != null
                    && question.getQuestionnaireRefIdFk() == null) {
                dropList.add(question);
            }
        }
        System.out.println("qnquestion " + qnForm.getEditData());
        questionnaire.getQuestionnaireQuestions().removeAll(dropList);
        if (questionnaire.getSequenceNumber() == null) {
            questionnaire.setSequenceNumber(1);
        }
        String desc = questionnaire.getDescription();
        if (questionnaire.getQuestionnaireRefId() != null) {
            Map pkMap = new HashMap();
            pkMap.put("questionnaireRefId", questionnaire.getQuestionnaireRefId());
            Questionnaire oldQuestionnaire = (Questionnaire) KraServiceLocator.getService(BusinessObjectService.class)
                    .findByPrimaryKey(Questionnaire.class, pkMap);
            // if (questionnaire.getQuestionnaireId().equals(0)) {
            if (questionnaire.getQuestionnaireId() != null
                    && qnForm.getMaintenanceAction().equals(KNSConstants.MAINTENANCE_EDIT_ACTION)
                    && qnForm.getDocStatus().equals(KEWConstants.ROUTE_HEADER_INITIATED_CD)) {
                versionQuestionnaire(questionnaire, oldQuestionnaire);
            }
            else {
                if (oldQuestionnaire != null) {
                    // cretae new : 1st try failed, then try again, oldquestionnaire is null
                    // not sure why the newmainobj.busobject at this point has qnquestions & usages
                    questionnaire.setVersionNumber(oldQuestionnaire.getVersionNumber());
                    questionnaire.setSequenceNumber(oldQuestionnaire.getSequenceNumber());
                    questionnaire.setQuestionnaireId(oldQuestionnaire.getQuestionnaireId());
                    // delete old, and repopulated from form
                    if (oldQuestionnaire.getQuestionnaireUsages().size() > 0) {
                        KraServiceLocator.getService(BusinessObjectService.class).delete(oldQuestionnaire.getQuestionnaireUsages());
                    }
                }
            }
        }
        // not sure if we set doc# here
        questionnaire.setDocumentNumber(qnForm.getDocument().getDocumentNumber());
        // TODO : this makes newquestionnaire hooked to bo saved in db but not yet committed yet
        Questionnaire copyQuestionnaire = (Questionnaire) ObjectUtils.deepCopy(questionnaire);
        if (deleteList.size() > 0) {
            KraServiceLocator.getService(BusinessObjectService.class).delete(deleteList);
        }
        KraServiceLocator.getService(BusinessObjectService.class).save(copyQuestionnaire);
        questionnaire.setVersionNumber(copyQuestionnaire.getVersionNumber());
        // for editing, 1st time the refid will be the new one because versioning
        if (questionnaire.getQuestionnaireRefId() == null
                || !questionnaire.getQuestionnaireRefId().equals(copyQuestionnaire.getQuestionnaireRefId())) {
            questionnaire.setQuestionnaireRefId(copyQuestionnaire.getQuestionnaireRefId());
            ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().setBusinessObject(questionnaire);
        }
        // TODO : edit newquestionnaire.refid changed
        // qnForm.getNewQuestionnaire().setQuestionnaireRefId(questionnaire.getQuestionnaireRefId());
        // qnForm.getNewQuestionnaire().setSequenceNumber(questionnaire.getSequenceNumber());
        // if (StringUtils.isNotBlank(qnForm.getSqlScripts())) {
        // runSql(qnForm);
        // Map pkMap = new HashMap();
        // pkMap.put("questionnaireRefId", questionnaire.getQuestionnaireRefId());
        // Questionnaire oldQuestionnaire = (Questionnaire) KraServiceLocator.getService(BusinessObjectService.class)
        // .findByPrimaryKey(Questionnaire.class, pkMap);
        // //KraServiceLocator.getService(BusinessObjectService.class).save(oldQuestionnaire);

    }


    private void versionQuestionnaire(Questionnaire questionnaire, Questionnaire oldQuestionnaire) {
        try {
            VersioningService versionService = new VersioningServiceImpl();
            // questionnaire = (Questionnaire) versionService.createNewVersion(oldQuestionnair);
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
            // questionnaire.setQuestionnaireQuestions(newQuestionnaire.getQuestionnaireQuestions());
            // questionnaire.setQuestionnaireUsages(newQuestionnaire.getQuestionnaireUsages());
            questionnaire.setDocumentNumber("");
        }
        catch (Exception e) {

        }

    }

    private void runSql(ActionForm form) {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Questionnaire questionnaire = new Questionnaire();
        questionnaire = ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                .getBusinessObject());
        String[] sqls = qnForm.getSqlScripts().split("#S#");
        for (int k = 0; k < sqls.length; k++) {
            KraServiceLocator.getService(QuestionnaireService.class).saveQuestionnaire(sqls[k], questionnaire);
        }
        String error = (String) GlobalVariables.getUserSession().retrieveObject("qnError");
        if (StringUtils.isNotBlank(error)) {
            qnForm.setRetData("<h3>" + error + "</h3>");
            GlobalVariables.getUserSession().addObject("qnError", (Object) null);
        }

    }

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
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
    public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward = super.cancel(mapping, form, request, response);
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Questionnaire questionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                .getBusinessObject();
        // TODO : not sure should delete it because if user bring up the calcelled document, then the questions and usages will be
        // gone because they are not
        // in xmldoccontent
        // if keep it in DB, then a couple of issues :
        // 1. coeus view should not include the cancelled Qnnaire
        // 2. Annaire lookup search should rework a little to exclude this cancelled Qnnaire
        if (!((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KNSConstants.MAINTENANCE_COPY_ACTION)) {
            Map pkMap = new HashMap();
            pkMap.put("questionnaireRefId", questionnaire.getQuestionnaireRefId());
            Object obj = KraServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(Questionnaire.class, pkMap);
            if (obj != null) {
                Questionnaire oldQuestionnaire = (Questionnaire) obj;
                List<PersistableBusinessObject> bos = new ArrayList<PersistableBusinessObject>();
                if (!oldQuestionnaire.getQuestionnaireQuestions().isEmpty()) {
                    bos.addAll(oldQuestionnaire.getQuestionnaireQuestions());
                }
                if (!oldQuestionnaire.getQuestionnaireUsages().isEmpty()) {
                    bos.addAll(oldQuestionnaire.getQuestionnaireUsages());
                }
                if (!bos.isEmpty()) {
                    KraServiceLocator.getService(BusinessObjectService.class).delete(bos);
                }
                KraServiceLocator.getService(BusinessObjectService.class).delete(oldQuestionnaire);
            }
        }
        return forward;
    }

    @Override
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Questionnaire newBo = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                .getBusinessObject();
        Questionnaire copyQuestionnaire = (Questionnaire) ObjectUtils.deepCopy(newBo);

        ActionForward forward = super.close(mapping, form, request, response);
        Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
        if (buttonClicked != null && ConfirmationQuestion.YES.equals(buttonClicked)) {
            // if yes button clicked - save the doc
            Questionnaire oldBo = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getOldMaintainableObject()
                    .getBusinessObject();
            ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().setBusinessObject(copyQuestionnaire);
            newBo = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getBusinessObject();
            if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                    KNSConstants.MAINTENANCE_COPY_ACTION)) {
                // newBo.setName(qnForm.getNewQuestionnaire().getName());
                // newBo.setDescription(qnForm.getNewQuestionnaire().getDescription());
                // newBo.setIsFinal(qnForm.getNewQuestionnaire().getIsFinal());
                newBo.setSequenceNumber(1);
                // TODO : set doc# here may cause confusion
                // newBo.setDocumentNumber(qnForm.getDocument().getDocumentNumber());
                if (oldBo.getQuestionnaireId().equals(newBo.getQuestionnaireId())) {
                    Integer questionnaireId = Integer.parseInt(KraServiceLocator.getService(SequenceAccessorService.class)
                            .getNextAvailableSequenceNumber("SEQ_QUESTIONNAIRE_ID").toString());
                    newBo.setQuestionnaireId(questionnaireId);

                }
            }
            else {
                // if (StringUtils.isNotBlank(qnForm.getSqlScripts())) {
                saveQn(qnForm);
                // }
                Questionnaire questionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                        .getNewMaintainableObject().getBusinessObject();
                if (questionnaire.getSequenceNumber() == null) {
                    // TODO : create new first time
                    questionnaire.setSequenceNumber(1);
                }
                // questionnaire.refreshReferenceObject("questionnaireQuestions");
                // ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().setBusinessObject(questionnaire);
                String questions = assembleQuestions(qnForm);
                String usages = assembleUsages((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                        .getNewMaintainableObject().getBusinessObject());
                qnForm.setEditData(questions + "#;#" + usages);
                if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                        KNSConstants.MAINTENANCE_EDIT_ACTION)) {
                    // TODO : force it to have the same key, so it can be approve later.
                    // rice maintenance framework - 'edit' is expecting old & new have the same pk
                    ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getOldMaintainableObject()
                            .getBusinessObject()).setQuestionnaireRefId(((Questionnaire) ((MaintenanceDocumentBase) qnForm
                            .getDocument()).getNewMaintainableObject().getBusinessObject()).getQuestionnaireRefId());
                }
            }
            getDocumentService().saveDocument(qnForm.getDocument());
        }
        else {
            Questionnaire questionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                    .getNewMaintainableObject().getBusinessObject();
            questionnaire.setQuestionnaireUsages(new ArrayList<QuestionnaireUsage>());
        }
        return forward;
    }

    // @Override
    // public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse
    // response)
    // throws Exception {
    // // TODO Auto-generated method stub
    // return super.execute(mapping, form, request, response);
    // }


}
