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
import org.kuali.rice.kns.service.SequenceAccessorService;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.web.struts.action.KualiMaintenanceDocumentAction;

public class QuestionnaireMaintenanceDocumentAction extends KualiMaintenanceDocumentAction {
    private static final Log LOG = LogFactory.getLog(QuestionnaireMaintenanceDocumentAction.class);

    /*
     * TODO : big issue is that questionnairequestions and usages can't be included in xmldoccontent because maintframework -
     * questions & usages are not defined in maint DD's 'maintsections' So, we have to save these data in tables. Right now we just
     * save then in questionnaire related tables. These created lots of extra work to manipulate data for different actions. Other
     * options may look into : 1. rice enhancement to let collections, that is not in maintain sections, be saved in xmldoccontent -
     * this will be the best solution 2. create identical intermediate tables that are identical to questionnaire tables, and save
     * data in those intermediate tables 3. also with option 2 and implemented this like a transaction document and saved to real
     * table when document is approved.
     * 
     * The hierarchical nature of data and heavily depending on js manipulation make this issue worse.
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (((MaintenanceDocumentBase) ((QuestionnaireMaintenanceForm) form).getDocument()).getNewMaintainableObject()
                .getMaintenanceAction().equals(KNSConstants.MAINTENANCE_COPY_ACTION)) {
            preSaveCopy(form);
        }
        else {
            preSaveEditNew(form);
        }
        ActionForward forward = null;
        try {
            forward = super.save(mapping, form, request, response);
        }
        catch (Exception e) {
            saveExceptionHandling(form);
            throw e;
        }
        ((QuestionnaireMaintenanceForm) form).setRemovedQuestionnaireQuestions(new ArrayList<QuestionnaireQuestion>());

        return forward;
    }

    private void preSaveCopy(ActionForm form) {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Questionnaire oldBo = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getOldMaintainableObject()
                .getBusinessObject();
        Questionnaire newBo = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                .getBusinessObject();
        newBo.setSequenceNumber(1);
        // TODO : set doc# here may cause confusion
        // newBo.setDocumentNumber(qnForm.getDocument().getDocumentNumber());
        if (oldBo.getQuestionnaireId().equals(newBo.getQuestionnaireId())) {
            Integer questionnaireId = Integer.parseInt(KraServiceLocator.getService(SequenceAccessorService.class)
                    .getNextAvailableSequenceNumber("SEQ_QUESTIONNAIRE_ID").toString());
            newBo.setQuestionnaireId(questionnaireId);

        }

    }

    private void preSaveEditNew(ActionForm form) {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        
        saveQn(qnForm);
        // }
        Questionnaire questionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                .getBusinessObject();
        if (questionnaire.getSequenceNumber() == null) {
            // TODO : create new first time
            questionnaire.setSequenceNumber(1);
        }
        // questionnaire.refreshReferenceObject("questionnaireQuestions");
        // ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().setBusinessObject(questionnaire);
        setUpQuestionAndUsage(form);
        if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KNSConstants.MAINTENANCE_EDIT_ACTION)) {
            // TODO : force it to have the same key, so it can be approve later.
            // rice maintenance framework - 'edit' is expecting old & new have the same pk
            ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getOldMaintainableObject().getBusinessObject())
                    .setQuestionnaireRefId(((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                            .getNewMaintainableObject().getBusinessObject()).getQuestionnaireRefId());
        }

    }

    private void saveRollBackData(ActionForm form) {
        // TODO need this before save to recover in case route failed
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        String questions = assembleQuestionsBeforeSave(qnForm);
        String usages = assembleUsages(((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                .getNewMaintainableObject().getBusinessObject()));
        //The first one is empty one because it started index with 1
//        if (usages.indexOf("#u#") > 0) {
//            usages = usages.substring(usages.indexOf("#u#")+3);
//        }
        qnForm.setRetData(questions + "#;#" + usages);
        
        
    }
    
    private void saveExceptionHandling(ActionForm form) {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        if (!((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KNSConstants.MAINTENANCE_COPY_ACTION)) {
            // roll back data specifically for ojb version number
            qnForm.setEditData(qnForm.getRetData());
            qnForm.setRetData("");
        }

    }

    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        permissionCheckForDocHandler(form);
        ActionForward forward = super.docHandler(mapping, form, request, response);
        if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KNSConstants.MAINTENANCE_COPY_ACTION)) {
            // view copied document
            if (qnForm.getDocument().getDocumentHeader().getWorkflowDocument().getRouteHeader().getDocRouteStatus().equals("F")) {
                setUpDataForCopy(form);
            }
        }
        else {
            if (!qnForm.getDocument().getDocumentHeader().getWorkflowDocument().getRouteHeader().getDocRouteStatus().equals(
                    KEWConstants.ROUTE_HEADER_CANCEL_CD)) {
                setUpQuestionAndUsage(form);
            }
        }
        // }
        qnForm.setRemovedQuestionnaireQuestions(new ArrayList<QuestionnaireQuestion>());
        return forward;
    }
    
    /*
     * retrieve questionnaire questions & usages and put them in a string and separated by separator
     * this will be saved in 'editdata' property. which will be retried by js and set up question node and usage items.
     */
    private void setUpQuestionAndUsage(ActionForm form) {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        String questions = assembleQuestions(qnForm);
        String usages = assembleUsages((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                .getNewMaintainableObject().getBusinessObject());
        qnForm.setEditData(questions + "#;#" + usages);
        
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
     * set up questionnaire questions & usages data for copied document which is final;
     * so user can view it.
     */
    private void setUpDataForCopy(ActionForm form) {

        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("documentNumber", qnForm.getDocument().getDocumentNumber());
        // use findbypk is little stretched. But is is actually doing findmatching has nothing to do with pk
        Questionnaire questionnaire = (Questionnaire) getBusinessObjectService().findByPrimaryKey(Questionnaire.class, fieldValues);
        ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getBusinessObject())
                .setQuestionnaireRefId(questionnaire.getQuestionnaireRefId());
        setUpQuestionAndUsage(form);

    }

    /*
     * utility method to get all qnquestions and put in a string which is separated by separator
     */
    private String assembleQuestions(QuestionnaireMaintenanceForm questionnaireForm) {

        Questionnaire newQuestionnaire = (Questionnaire) ((MaintenanceDocumentBase) questionnaireForm.getDocument())
                .getNewMaintainableObject().getBusinessObject();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("questionnaireRefId", newQuestionnaire.getQuestionnaireRefId());
        Questionnaire questionnaire = (Questionnaire) getBusinessObjectService().findByPrimaryKey(Questionnaire.class, fieldValues);
        // not sure why the collections are not retrieved ?
        // maybe these are added by runsql ?
        questionnaire.refreshReferenceObject("questionnaireQuestions");
        questionnaire.refreshReferenceObject("questionnaireUsages");
        /*
         * TODO : object retrieved by busobjservice then assign to newmain.busobj Then go thru document.save, always caused lots of
         * issue. so has copy it then assign to newmaint.busobj
         */
        Questionnaire copyQuestionnaire = (Questionnaire) ObjectUtils.deepCopy(questionnaire);
        ((MaintenanceDocumentBase) questionnaireForm.getDocument()).getNewMaintainableObject().setBusinessObject(copyQuestionnaire);
        questionnaireForm.setQuestionNumber(0);
        questionnaireForm.setQuestionNumber(questionnaireForm.getQuestionNumber() + 1);

        return getQuestionRetureResult(questionnaireForm, questionnaire);


    }

    /*
     * loop thru qnquestion and assemble results.  Also find the max questionnumber.
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
                result = result + "#q#" + getQnReturnfields(question);
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
     * This to get the return data before save to db which incremented ojb vernum
     * if route failed then this can be used for roll back.
     */
    private String assembleQuestionsBeforeSave(QuestionnaireMaintenanceForm questionnaireForm) {

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
            retString = className + "#f#" + question.getMaxAnswers() + "#f#" + question.getLookupReturn();

        }
        else {
            retString = question.getDisplayedAnswers() + "#f#" + question.getMaxAnswers() + "#f#" + question.getAnswerMaxLength();
        }
        return retString;
    }

    /*
     * get the children questions data
     */
    private String getChildren(QuestionnaireQuestion questionnaireQuestion, List<QuestionnaireQuestion> questionnaireQuestions) {
        String result = "";
        for (QuestionnaireQuestion question : questionnaireQuestions) {
            if (question.getParentQuestionNumber().equals(questionnaireQuestion.getQuestionNumber())) {
                result = result + "#q#" + getQnReturnfields(question);
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
        return question.getQuestionnaireQuestionsId() + "#f#" + question.getQuestionRefIdFk() + "#f#"
                + question.getQuestionSeqNumber() + "#f#" + desc + "#f#" + question.getQuestion().getQuestionTypeId() + "#f#"
                + question.getQuestionNumber() + "#f#" + question.getCondition() + "#f#" + question.getConditionValue() + "#f#"
                + question.getParentQuestionNumber() + "#f#" + question.getQuestion().getSequenceNumber() + "#f#"
                + getQeustionResponse(question.getQuestion()) + "#f#" + question.getVersionNumber() + "#f#"
                + question.getConditionFlag();

    }

    /*
     * Create a concatenated usage properties string.
     */
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

    @Override
    public ActionForward copy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 'document' is null at this point
        ActionForward forward = super.copy(mapping, form, request, response);
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("questionnaireRefId", request.getParameter("questionnaireRefId"));
        Questionnaire questionnaire = (Questionnaire) getBusinessObjectService().findByPrimaryKey(Questionnaire.class, fieldValues);
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
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
//        ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getBusinessObject())
//                .setQuestionnaireRefId(Long.parseLong(request.getParameter("questionnaireRefId")));
        Questionnaire questionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getBusinessObject();
        Questionnaire oldQuestionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getOldMaintainableObject().getBusinessObject();
        versionQuestionnaire(questionnaire, oldQuestionnaire);
        qnForm.setVersioned(true);

        String questions = assembleQuestionsBeforeSave(qnForm);
        String usages = assembleUsages(((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                .getNewMaintainableObject().getBusinessObject()));
        qnForm.setEditData(questions + "#;#" + usages);

        //setUpQuestionAndUsage(form);
        return forward;
    }

    @Override
    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
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
            forward = super.route(mapping, form, request, response);
        }
        catch (Exception e) {
            routeExceptionHandling(form, questionnaireRefId, oldQuestionnaireRefId);
            throw e;
        }
        // if there is rule violation, then it will be redirected directly to jsp page. the following code will not be executed.
        qnForm.setRetData("");
        qnForm.setRemovedQuestionnaireQuestions(new ArrayList<QuestionnaireQuestion>());
        postRoute(form);
        if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KNSConstants.MAINTENANCE_COPY_ACTION)) {
            setUpDataForCopy(form);
        }
        return forward;

    }

    /*
     * If there is exception, especially rule violation, during route. 1. reset qnrefid back for new questionnaire. 2. if data has
     * been saved and ojb version number was adjusted, so need to roll back. the roll back data was saved in 'retdata'
     */
    private void routeExceptionHandling(ActionForm form, Long questionnaireRefId, Long oldQuestionnaireRefId) {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KNSConstants.MAINTENANCE_NEW_ACTION)
                && questionnaireRefId != null) {
            // this is only for new action
            // for edit, oldqrefid is null, so should not set here
            ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getBusinessObject())
                    .setQuestionnaireRefId(oldQuestionnaireRefId);
        }
        if (!((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KNSConstants.MAINTENANCE_COPY_ACTION)) {
            // roll back data specifically for ojb version number
            qnForm.setEditData(qnForm.getRetData());
            qnForm.setRetData("");
        }

    }

    /*
     * 1. for copy action : set sequence to 1, and get a new questionnaireid from oracle sequence 
     * 2. save the current data and saved in 'retData'. It can be used for roll back if route has exception. 
     * 3. for new action : create new questionnaireid for new obj, and saved the existing in old obj 
     * 4. for edit action : set oldobj's questionnaired as the newobj's because maintenance
     * framework expecting they are the same The way versioning work, the oldobj has the questionnaireid of previous version; so we
     * have to manually do it.
     */
    private void preRoute(ActionForm form) {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Questionnaire newBo = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                .getBusinessObject();
        if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KNSConstants.MAINTENANCE_COPY_ACTION)) {
            preSaveCopy(qnForm);
            newBo.setDocumentNumber(qnForm.getDocument().getDocumentNumber());
        }
        else {
//            // TODO need this before save to recover in case route failed
//            String questions = assembleQuestionsBeforeSave(qnForm);
//            String usages = assembleUsages(((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
//                    .getNewMaintainableObject().getBusinessObject()));
//            //The first one is empty one because it started index with 1
//            if (usages.indexOf("#u#") > 0) {
//                usages = usages.substring(usages.indexOf("#u#")+3);
//            }
//            qnForm.setRetData(questions + "#;#" + usages);

            saveQn(qnForm);
            // if (StringUtils.isNotBlank(qnForm.getSqlScripts())) {
            setUpQuestionAndUsage(form);
            // }
        }
        if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KNSConstants.MAINTENANCE_NEW_ACTION)) {
            setNewQuestionaireid(form);

        }
        if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KNSConstants.MAINTENANCE_EDIT_ACTION)) {
            setEditQuestionaireForRoute(form);
        }

    }


    /*
     * set the questionnaire id for new questionnaire before route
     */
    private void setNewQuestionaireid(ActionForm form) {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
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
    
    /* 
     * set the questionnaire with proper version and document
     */
    private void setEditQuestionaireForRoute(ActionForm form) {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Map fieldValues = new HashMap<String, Object>();
        fieldValues.put("questionnaireRefId", ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                .getNewMaintainableObject().getBusinessObject()).getQuestionnaireRefId());
        Questionnaire oldQuestionnaire = (Questionnaire) getBusinessObjectService().findByPrimaryKey(Questionnaire.class,
                fieldValues);
        Questionnaire newQuestionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                .getNewMaintainableObject().getBusinessObject();
        ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getOldMaintainableObject().getBusinessObject())
                .setQuestionnaireRefId(newQuestionnaire.getQuestionnaireRefId());
        newQuestionnaire.setDocumentNumber(qnForm.getDocument().getDocumentNumber());
        newQuestionnaire.setVersionNumber(oldQuestionnaire.getVersionNumber());
       
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
            routeExceptionHandling(form, questionnaireRefId, oldQuestionnaireRefId);
            throw e;
        }
        postRoute(form);
        return forward;
    }


    /*
     * This will be called it route successfully, i.e., no exception. edit action : nothing need to be done. copy
     * action : retrieve original questionaire and call questionnaire service to copy/save it.
     */
    private void postRoute(ActionForm form) {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KNSConstants.MAINTENANCE_NEW_ACTION)) {
            saveNewQuestionnaireAfterPost(form);
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
            Questionnaire questionnaire = (Questionnaire) getBusinessObjectService().findByPrimaryKey(Questionnaire.class,
                    fieldValues);
            if (questionnaire != null) {
                fieldValues = new HashMap<String, Object>();
                fieldValues.put("questionnaireRefId", ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                        .getOldMaintainableObject().getBusinessObject()).getQuestionnaireRefId());
                Questionnaire oldQuestionnaire = (Questionnaire) getBusinessObjectService().findByPrimaryKey(Questionnaire.class,
                        fieldValues);
                KraServiceLocator.getService(QuestionnaireService.class).copyQuestionnaire(oldQuestionnaire, questionnaire);
            }

        }

    }

    /*
     * new action : we saved data before route, which is not the
     * way maintenancefrmework works, so we have to delete the old one and save the new one. 
     */
    private void saveNewQuestionnaireAfterPost(ActionForm form) {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Long questionnaireRefId = ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                .getBusinessObject()).getQuestionnaireRefId();
        Long oldQuestionnaireRefId = ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                .getOldMaintainableObject().getBusinessObject()).getQuestionnaireRefId();
        if (questionnaireRefId != null) {
            Map fieldValues = new HashMap<String, Object>();
            fieldValues.put("questionnaireRefId", oldQuestionnaireRefId);
            Questionnaire oldQuestionnaire = (Questionnaire) getBusinessObjectService().findByPrimaryKey(Questionnaire.class,
                    fieldValues);
            oldQuestionnaire.refreshReferenceObject("questionnaireQuestions");
            oldQuestionnaire.refreshReferenceObject("questionnaireUsages");
            Questionnaire copyQuestionnaire = (Questionnaire) ObjectUtils.deepCopy(oldQuestionnaire);
            fieldValues.put("questionnaireRefId", questionnaireRefId);
            Questionnaire newQuestionnaire = (Questionnaire) getBusinessObjectService().findByPrimaryKey(Questionnaire.class,
                    fieldValues);
            newQuestionnaire.setQuestionnaireQuestions(copyQuestionnaire.getQuestionnaireQuestions());
            newQuestionnaire.setQuestionnaireUsages(copyQuestionnaire.getQuestionnaireUsages());
            // newQuestionnaire.setVersionNumber(1L);
            newQuestionnaire.setDocumentNumber(qnForm.getDocument().getDocumentNumber());
            getBusinessObjectService().save(newQuestionnaire);
            // getBusinessObjectService().delete(delBos);
            getBusinessObjectService().delete(oldQuestionnaire);
        }
       
    }
    
    /*
     * from the populated data after post.  saved to DB.  This is needed because collections are not
     * saved to xmldoccontent.
     */
    private void saveQn(ActionForm form) {

        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Questionnaire questionnaire = ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                .getBusinessObject());

        // questionnaire.getQuestionnaireQuestions().remove(0);
        // questionnaire.getQuestionnaireQuestions().remove(0);
//        if (!questionnaire.getQuestionnaireUsages().isEmpty()) {
//            questionnaire.getQuestionnaireUsages().remove(0);
//        }
        List<QuestionnaireQuestion> deleteList = cleanupQuestionnaireQuestions(questionnaire);
        if (questionnaire.getSequenceNumber() == null) {
            questionnaire.setSequenceNumber(1);
        }

        if (questionnaire.getQuestionnaireRefId() != null) {
            Map pkMap = new HashMap();
            pkMap.put("questionnaireRefId", questionnaire.getQuestionnaireRefId());
            Questionnaire oldQuestionnaire = (Questionnaire) getBusinessObjectService()
                    .findByPrimaryKey(Questionnaire.class, pkMap);
            // if (questionnaire.getQuestionnaireId().equals(0)) {
            // if save has exception for the first edit save, then 
//            if (oldQuestionnaire != null && questionnaire.getQuestionnaireId() != null
//                    && qnForm.getMaintenanceAction().equals(KNSConstants.MAINTENANCE_EDIT_ACTION)
//                    && qnForm.getDocStatus().equals(KEWConstants.ROUTE_HEADER_INITIATED_CD)) {
//                versionQuestionnaire(questionnaire, oldQuestionnaire);
//                qnForm.setVersioned(true);
//            }
//            else {
                if (oldQuestionnaire != null) {
                    // cretae new : 1st try failed, then try again, oldquestionnaire is null
                    // not sure why the newmainobj.busobject at this point has qnquestions & usages
                    questionnaire.setVersionNumber(oldQuestionnaire.getVersionNumber());
                    questionnaire.setSequenceNumber(oldQuestionnaire.getSequenceNumber());
                    questionnaire.setQuestionnaireId(oldQuestionnaire.getQuestionnaireId());
                    // delete old, and repopulated from form
                    if (oldQuestionnaire.getQuestionnaireUsages().size() > 0) {
                        getBusinessObjectService().delete(oldQuestionnaire.getQuestionnaireUsages());
                    }
                }
//            }
        }
        // not sure if we set doc# here
        questionnaire.setDocumentNumber(qnForm.getDocument().getDocumentNumber());
        saveRollBackData(form);
        if (qnForm.getRemovedQuestionnaireQuestions().size() > 0) {
            deleteList.addAll(qnForm.getRemovedQuestionnaireQuestions());
            qnForm.setRemovedQuestionnaireQuestions(deleteList);
        } else {
            if (deleteList.size() > 0) {
                qnForm.setRemovedQuestionnaireQuestions(deleteList);                        
            }
        }
        // TODO : this makes newquestionnaire hooked to bo saved in db but not yet committed yet
        Questionnaire copyQuestionnaire = (Questionnaire) ObjectUtils.deepCopy(questionnaire);
        if (deleteList.size() > 0) {
            getBusinessObjectService().delete(deleteList);
        }
        getBusinessObjectService().save(copyQuestionnaire);
        questionnaire.setVersionNumber(copyQuestionnaire.getVersionNumber());
        // for editing, 1st time the refid will be the new one because versioning
        if (questionnaire.getQuestionnaireRefId() == null
                || !questionnaire.getQuestionnaireRefId().equals(copyQuestionnaire.getQuestionnaireRefId())) {
            questionnaire.setQuestionnaireRefId(copyQuestionnaire.getQuestionnaireRefId());
            ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().setBusinessObject(questionnaire);
        }

    }


    /*
     * This is to clean up questionnairequestions from the populated questionnairequestions.
     * 1. if 'delete == 'Y',& QuestionnaireQuestionsId!=null then this existing question has been removed during hierarchy maintenance
     * 2. if QuestionnaireQuestionsId = null, then this question is not in DB yet.
     * 3. return delete list for further processing
     */
    private List<QuestionnaireQuestion> cleanupQuestionnaireQuestions(Questionnaire questionnaire) {
        List<QuestionnaireQuestion> dropList = new ArrayList<QuestionnaireQuestion>();
        List<QuestionnaireQuestion> deleteList = new ArrayList<QuestionnaireQuestion>();
        // Map qMap = new HashMap();
        int i = 0;
        for (QuestionnaireQuestion question : questionnaire.getQuestionnaireQuestions()) {
            System.out.println("qnquestion " + (i++) + question.toStringMapper());
            if ("Y".equals(question.getDeleted())) {
                if (question.getQuestionnaireQuestionsId() == null) {
                    dropList.add(question);
                    System.out.println("drop qnquestion " + (i++) + question.toStringMapper());
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
        // System.out.println("qnquestion " + qnForm.getEditData());
        questionnaire.getQuestionnaireQuestions().removeAll(dropList);
        return deleteList;
        
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
            Object obj = getBusinessObjectService().findByPrimaryKey(Questionnaire.class, pkMap);
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
                    getBusinessObjectService().delete(bos);
                }
                getBusinessObjectService().delete(oldQuestionnaire);
            }
        }
        return forward;
    }

    @Override
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Questionnaire newBo = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getBusinessObject();
        Questionnaire copyQuestionnaire = (Questionnaire) ObjectUtils.deepCopy(newBo);

        ActionForward forward = null;
        Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
        List<QuestionnaireQuestion> deleteList = new ArrayList<QuestionnaireQuestion>();
        try {
            if (buttonClicked != null && ConfirmationQuestion.YES.equals(buttonClicked)) {
                Questionnaire questionnaire = ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                        .getBusinessObject());
                questionnaire.setQuestionnaireUsages(qnForm.getQuestionnaireUsages());
                deleteList = cleanupQuestionnaireQuestions(questionnaire);
                saveRollBackData(qnForm);
            }
            forward = super.close(mapping, form, request, response);
        }
        catch (Exception e) {
            if (buttonClicked != null && ConfirmationQuestion.YES.equals(buttonClicked)) {
                closeExceptionHandling(form, deleteList);
            }
            throw e;
        }
       // Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
        if (buttonClicked != null && ConfirmationQuestion.YES.equals(buttonClicked)) {
            // if yes button clicked - save the doc
            ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().setBusinessObject(copyQuestionnaire);
            closeToSave(form);
        }
        else {
            Questionnaire questionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                    .getNewMaintainableObject().getBusinessObject();
            qnForm.setQuestionnaireUsages(questionnaire.getQuestionnaireUsages());
            questionnaire.setQuestionnaireUsages(new ArrayList<QuestionnaireUsage>());
            // also need to save usages data in form
        }
        return forward;
    }

    private void closeToSave(ActionForm form) throws Exception {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KNSConstants.MAINTENANCE_COPY_ACTION)) {
           preSaveCopy(form);
        }
        else {
            ((Questionnaire)((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getBusinessObject()).setQuestionnaireUsages(qnForm.getQuestionnaireUsages());
            preSaveEditNew(form);
        }
        getDocumentService().saveDocument(qnForm.getDocument());
        qnForm.setRemovedQuestionnaireQuestions(new ArrayList<QuestionnaireQuestion>());
        
    }
    
    private void closeExceptionHandling(ActionForm form, List<QuestionnaireQuestion> deleteList) {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        if (qnForm.getRemovedQuestionnaireQuestions().size() > 0) {
            deleteList.addAll(qnForm.getRemovedQuestionnaireQuestions());
            qnForm.setRemovedQuestionnaireQuestions(deleteList);
        } else {
            if (deleteList.size() > 0) {
                qnForm.setRemovedQuestionnaireQuestions(deleteList);                        
            }
        }
        saveExceptionHandling(form);

    }
    
    private QuestionnaireAuthorizationService getQuestionnaireAuthorizationService() {
        return KraServiceLocator.getService(QuestionnaireAuthorizationService.class);
    }

}
