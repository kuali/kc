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
package org.kuali.kra.questionnaire.question;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.maintenance.QuestionMaintainableImpl;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersioningService;
import org.kuali.kra.service.impl.VersioningServiceImpl;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.web.struts.action.KualiMaintenanceDocumentAction;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.KRADConstants;

/**
 * 
 * This is the maintenance action class is for question.
 */
public class QuestionMaintenanceDocumentAction extends KualiMaintenanceDocumentAction {
    
    private static final String QUESTION_REF_ID = "questionRefId";
    private static final String EDIT_QUESTION_OF_ACTIVE_QUESTIONNAIRE_QUESTION = "EditQuestionOfActiveQuestionnaire";

    /**
     * 
     * This method gets called upon clicking the refresh button to display the newly selected
     * question response type when javascript is turned off on the browser. 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward loadQuestionResponse(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        KualiMaintenanceForm maintenanceForm = (KualiMaintenanceForm) form;
        MaintenanceDocumentBase maintenanceDocument = (MaintenanceDocumentBase) maintenanceForm.getDocument();
        Question question = (Question) maintenanceDocument.getNewMaintainableObject().getDataObject();
        question.refreshReferenceObject("questionType");

        return mapping.findForward(Constants.MAPPING_BASIC);
    }  

    /**
     * 
     * This method gets called upon clicking of refresh pulldown menu buttons on the screen
     * to populate the drop down menus afresh based on other parameters.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward refreshPulldownOptions(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    
    /**
     * 
     * This method implements special behaviors for Question.
     * @param questionMaintenanceForm
     * @param request
     * @throws VersionException 
     */
    private void specialHandlingOfQuestion(QuestionMaintenanceForm questionMaintenanceForm, HttpServletRequest request) throws VersionException {
        MaintenanceDocumentBase maintenanceDocumentBase = (MaintenanceDocumentBase) questionMaintenanceForm.getDocument();

        boolean readOnly = !KraServiceLocator.getService(QuestionAuthorizationService.class).hasPermission(PermissionConstants.MODIFY_QUESTION)
                || ObjectUtils.equals(request.getParameter("readOnly"), "true");
                
        if (StringUtils.equals(questionMaintenanceForm.getMethodToCall(), "edit")) {
            if (readOnly) {
                questionMaintenanceForm.setReadOnly(true);
            } else {
                createNewQuestionVersion(maintenanceDocumentBase, request.getParameter(QUESTION_REF_ID));
            }
        } else if (StringUtils.equals(questionMaintenanceForm.getMethodToCall(), "copy")) {
            initCopiedQuestion(maintenanceDocumentBase);
        }
    }
    
    /**
     * 
     * This method creates a new version of the question.
     * @param maintenanceDocumentBase
     * @param request
     * @throws VersionException
     */
    private void createNewQuestionVersion(MaintenanceDocumentBase maintenanceDocumentBase, String oldQuestionRefId) throws VersionException {
        // Get most recent approved Question from DB
        Question approvedQuestion = getQuestionService().getQuestionByRefId(oldQuestionRefId);

        // Create new version of Question
        VersioningService versioningService = new VersioningServiceImpl();
        Question versionedQuestion = (Question) versioningService.createNewVersion(approvedQuestion);

        // Generate new questionRefId
        Long newQuestionRefId = KraServiceLocator.getService(SequenceAccessorService.class)
                .getNextAvailableSequenceNumber("SEQ_QUESTION_ID");

        // Set old question to new questionRefId (needed so Rice doesn't complain that the key changed between the old
        // and new version)
        QuestionMaintainableImpl oldMaintainableObject = (QuestionMaintainableImpl) maintenanceDocumentBase
                .getOldMaintainableObject();
        Question oldQuestion = (Question) oldMaintainableObject.getBusinessObject();
        oldQuestion.setQuestionRefId(newQuestionRefId);

        // Set new question to versionedQuestion along with the new questionRefId
        QuestionMaintainableImpl newMaintainableObject = (QuestionMaintainableImpl) maintenanceDocumentBase
                .getNewMaintainableObject();
        versionedQuestion.setQuestionRefId(newQuestionRefId);
        versionedQuestion.setVersionNumber(oldQuestion.getVersionNumber());
        newMaintainableObject.setBusinessObject(versionedQuestion);
    }
    
    private QuestionService getQuestionService() {
        return (QuestionService) KraServiceLocator.getService(QuestionService.class);
    }

    /**
     * 
     * This method nulls out the questionId and sequenceNumber so new ones are created for the copied question
     * @param maintenanceDocumentBase
     */
    private void initCopiedQuestion(MaintenanceDocumentBase maintenanceDocumentBase) {
        QuestionMaintainableImpl oldMaintainableObject = (QuestionMaintainableImpl) maintenanceDocumentBase
                .getOldMaintainableObject();
        Question oldQuestion = (Question) oldMaintainableObject.getBusinessObject();
        oldQuestion.setQuestionId(null);
        oldQuestion.setSequenceNumber(null);

        QuestionMaintainableImpl newMaintainableObject = (QuestionMaintainableImpl) maintenanceDocumentBase
                .getNewMaintainableObject();
        Question newQuestion = (Question) newMaintainableObject.getBusinessObject();
        newQuestion.setQuestionId(null);
        newQuestion.setSequenceNumber(null);
    }
    
    @Override
    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward;
        
        QuestionService questionService = KraServiceLocator.getService(QuestionService.class);
        QuestionMaintenanceForm questionMaintenanceForm = (QuestionMaintenanceForm) form;
        
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        String questionRefId = request.getParameter(QUESTION_REF_ID);

        if (!questionMaintenanceForm.isReadOnly() && (question != null || questionService.isQuestionUsed(questionService.getQuestionByRefId(questionRefId).getQuestionId()))) {

            // logic for question
            if (question == null) {
                // Be sure not to call super.edit a second time when evaluating the user response.  Doing so will
                // cause the form data to be overridden and the desired question will not be edited.
                super.edit(mapping, form, request, response);
                // Version if a Question is selected for editing
                specialHandlingOfQuestion(questionMaintenanceForm, request);

                // ask question if not already asked
                ConfigurationService kualiConfiguration = KRADServiceLocator.getKualiConfigurationService();
                forward = performQuestionWithoutInput(mapping, form, request, response, EDIT_QUESTION_OF_ACTIVE_QUESTIONNAIRE_QUESTION, 
                        kualiConfiguration.getPropertyValueAsString(KeyConstants.QUESTION_EDIT_QUESTION_OF_ACTIVE_QUESTIONNAIRE), 
                        KRADConstants.CONFIRMATION_QUESTION, KRADConstants.MAPPING_CLOSE, "");
            } else {
                // Check to see if user has chosen to abort editing
                Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
                if ((EDIT_QUESTION_OF_ACTIVE_QUESTIONNAIRE_QUESTION.equals(question)) && ConfirmationQuestion.NO.equals(buttonClicked)) {
                    forward = returnToSender(request, mapping, questionMaintenanceForm);
                } else {
                    forward = mapping.findForward(Constants.MAPPING_BASIC);
                }
            } 
        } else {
            forward = super.edit(mapping, form, request, response);
            questionMaintenanceForm.getDocument().getDocumentHeader().setDocumentDescription("question - bootstrap data");

            // Version if a Question is selected for editing
            specialHandlingOfQuestion(questionMaintenanceForm, request);
        }

        return forward;
    }

    /**
     * Normally Maintenance documents don't have an explicit view mode (i.e. edit in read-only mode).  Because of that we need to
     * override the documentActions to ensure that the readOnly flag is preserved and only the close action is enabled when viewing
     * a question.
     * 
     * @see org.kuali.rice.kns.web.struts.action.KualiMaintenanceDocumentAction#populateAuthorizationFields(org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase)
     */
    @Override
    protected void populateAuthorizationFields(KualiDocumentFormBase formBase){
        QuestionMaintenanceForm questionMaintenanceForm = (QuestionMaintenanceForm) formBase;
        // kcirb-1502 : not sure why is retail isreadonly here.  the readonly is reset when calling
        // super.populateAuthorizationFields.
        // rewrite this way seems to  work.  this is not working for 3.1.1 for 'view' question.  which is using 'edit. 
        // 4.0 is fine because 'view' link has been rewritten
        boolean isReadOnly = questionMaintenanceForm.isReadOnly();

        // populateAuthorizationFields will override the isReadOnly property of the form. if it is 'view'
        super.populateAuthorizationFields(formBase);
        if (isReadOnly && StringUtils.equals(questionMaintenanceForm.getMethodToCall(), "edit")) {
            questionMaintenanceForm.setReadOnly(isReadOnly);
        }
        
        if (questionMaintenanceForm.isReadOnly() && formBase.getDocumentActions().containsKey(KRADConstants.KUALI_ACTION_CAN_CLOSE)) {
            Map<String, String> documentActions = new HashMap<String, String>();
            documentActions.put(KRADConstants.KUALI_ACTION_CAN_CLOSE, "TRUE");
            questionMaintenanceForm.setDocumentActions(documentActions);
            
           // questionMaintenanceForm.setReadOnly(isReadOnly);
        }
    }

    @Override
    public ActionForward copy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        ActionForward forward = super.copy(mapping, form, request, response);
        MaintenanceDocumentBase maintenanceDocumentBase = (MaintenanceDocumentBase) ((QuestionMaintenanceForm) form).getDocument();
        QuestionMaintainableImpl newMaintainableObject = (QuestionMaintainableImpl) maintenanceDocumentBase
                .getNewMaintainableObject();
        Question newQuestion = (Question) newMaintainableObject.getBusinessObject();
        newQuestion.setQuestionId(null);
        newQuestion.setSequenceNumber(1);
        for (QuestionExplanation questionExplanation : newQuestion.getQuestionExplanations()) {
            questionExplanation.setQuestionExplanationId(null);
            questionExplanation.setQuestionRefIdFk(null);
        }
        return forward;

    }
    
}
