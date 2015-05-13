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
package org.kuali.coeus.common.questionnaire.impl.question;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.version.VersionException;
import org.kuali.coeus.common.framework.version.VersioningService;
import org.kuali.coeus.common.impl.version.VersioningServiceImpl;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireConstants;
import org.kuali.coeus.common.questionnaire.framework.question.Question;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.web.struts.action.KualiMaintenanceDocumentAction;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * This is the maintenance action class is for question.
 */
public class QuestionMaintenanceDocumentAction extends KualiMaintenanceDocumentAction {
    
    private static final String EDIT_QUESTION_OF_ACTIVE_QUESTIONNAIRE_QUESTION = "EditQuestionOfActiveQuestionnaire";

    /**
     * 
     * This method gets called upon clicking the refresh button to display the newly selected
     * question response type when javascript is turned off on the browser.
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
     */
    public ActionForward refreshPulldownOptions(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    
    /**
     * 
     * This method implements special behaviors for Question.
     */
    private void specialHandlingOfQuestion(QuestionMaintenanceForm questionMaintenanceForm, HttpServletRequest request) throws VersionException {
        MaintenanceDocumentBase maintenanceDocumentBase = (MaintenanceDocumentBase) questionMaintenanceForm.getDocument();

        boolean readOnly = !KcServiceLocator.getService(QuestionAuthorizationService.class).hasPermission(PermissionConstants.MODIFY_QUESTION)
                || ObjectUtils.equals(request.getParameter("readOnly"), "true");
                
        if (StringUtils.equals(questionMaintenanceForm.getMethodToCall(), "edit")) {
            if (readOnly) {
                questionMaintenanceForm.setReadOnly(true);
            } else {
                createNewQuestionVersion(maintenanceDocumentBase, Long.parseLong(request.getParameter(QuestionnaireConstants.QUESTION_ID)));
            }
        } else if (StringUtils.equals(questionMaintenanceForm.getMethodToCall(), "copy")) {
            initCopiedQuestion(maintenanceDocumentBase);
        }
    }
    
    /**
     * 
     * This method creates a new version of the question.
     */
    private void createNewQuestionVersion(MaintenanceDocumentBase maintenanceDocumentBase, Long oldQuestionId) throws VersionException {
        // Get most recent approved Question from DB
        Question approvedQuestion = getQuestionService().getQuestionByQuestionId(oldQuestionId);

        // Create new version of Question
        VersioningService versioningService = new VersioningServiceImpl();
        Question versionedQuestion = (Question) versioningService.createNewVersion(approvedQuestion);

        // Generate new questionRefId
        Long newQuestionRefId = KcServiceLocator.getService(SequenceAccessorService.class)
                .getNextAvailableSequenceNumber("SEQ_QUESTIONNAIRE_REF_ID", versionedQuestion.getClass());

        // Set old question to new questionRefId (needed so Rice doesn't complain that the key changed between the old
        // and new version)
        QuestionMaintainableImpl oldMaintainableObject = (QuestionMaintainableImpl) maintenanceDocumentBase
                .getOldMaintainableObject();
        Question oldQuestion = (Question) oldMaintainableObject.getBusinessObject();
        oldQuestion.setId(newQuestionRefId);

        // Set new question to versionedQuestion along with the new questionRefId
        QuestionMaintainableImpl newMaintainableObject = (QuestionMaintainableImpl) maintenanceDocumentBase
                .getNewMaintainableObject();
        versionedQuestion.setId(newQuestionRefId);
        versionedQuestion.setVersionNumber(oldQuestion.getVersionNumber());
        newMaintainableObject.setBusinessObject(versionedQuestion);
    }
    
    private QuestionService getQuestionService() {
        return (QuestionService) KcServiceLocator.getService(QuestionService.class);
    }

    /**
     * 
     * This method nulls out the questionId and sequenceNumber so new ones are created for the copied question
     */
    private void initCopiedQuestion(MaintenanceDocumentBase maintenanceDocumentBase) {
        QuestionMaintainableImpl oldMaintainableObject = (QuestionMaintainableImpl) maintenanceDocumentBase
                .getOldMaintainableObject();
        Question oldQuestion = (Question) oldMaintainableObject.getBusinessObject();
        oldQuestion.setQuestionSeqId(null);
        oldQuestion.setSequenceNumber(null);

        QuestionMaintainableImpl newMaintainableObject = (QuestionMaintainableImpl) maintenanceDocumentBase
                .getNewMaintainableObject();
        Question newQuestion = (Question) newMaintainableObject.getBusinessObject();
        newQuestion.setQuestionSeqId(null);
        newQuestion.setSequenceNumber(null);
    }
    
    @Override
    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward;
        
        QuestionService questionService = KcServiceLocator.getService(QuestionService.class);
        QuestionMaintenanceForm questionMaintenanceForm = (QuestionMaintenanceForm) form;

        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);

        String questionRefIdString = request.getParameter(QuestionnaireConstants.QUESTION_ID);
        Long questionRefId =  questionRefIdString == null ? null:  Long.parseLong(request.getParameter(QuestionnaireConstants.QUESTION_ID));

        if (!questionMaintenanceForm.isReadOnly() && (question != null || questionService.isQuestionUsed(questionService.getQuestionByQuestionId(questionRefId).getQuestionSeqId()))) {

            // logic for question
            if (question == null) {
                // Be sure not to call super.edit a second time when evaluating the user response.  Doing so will
                // cause the form data to be overridden and the desired question will not be edited.
                super.edit(mapping, form, request, response);
                // Version if a Question is selected for editing
                specialHandlingOfQuestion(questionMaintenanceForm, request);

                // ask question if not already asked
                ConfigurationService kualiConfiguration = CoreApiServiceLocator.getKualiConfigurationService();
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
        }
    }
}
