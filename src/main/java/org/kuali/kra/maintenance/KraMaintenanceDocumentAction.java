/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.maintenance;

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
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.kra.questionnaire.question.QuestionAuthorizationService;
import org.kuali.kra.questionnaire.question.QuestionService;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersioningService;
import org.kuali.kra.service.impl.VersioningServiceImpl;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.service.SequenceAccessorService;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.web.struts.action.KualiMaintenanceDocumentAction;
import org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm;

public class KraMaintenanceDocumentAction extends KualiMaintenanceDocumentAction{
    private static final String QUESTION_REF_ID = "questionRefId";

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
        Question question = (Question) maintenanceDocument.getNewMaintainableObject().getBusinessObject();
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
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);

        // Version if a Question is selected for editing
        if (form instanceof KraMaintenanceForm) {
            KraMaintenanceForm kraMaintenanceForm = (KraMaintenanceForm) form;
            MaintenanceDocumentBase maintenanceDocumentBase = (MaintenanceDocumentBase) kraMaintenanceForm.getDocument();
            
            if (maintenanceDocumentBase.getNewMaintainableObject().getBusinessObject() instanceof Question) {
                specialHandlingOfQuestion(kraMaintenanceForm, request);
            }
        }
                
        return actionForward;
    }
    
    /**
     * 
     * This method implements special behaviors for Question.
     * @param kraMaintenanceForm
     * @param request
     * @throws VersionException 
     */
    private void specialHandlingOfQuestion(KraMaintenanceForm kraMaintenanceForm, HttpServletRequest request) throws VersionException {
        MaintenanceDocumentBase maintenanceDocumentBase = (MaintenanceDocumentBase) kraMaintenanceForm.getDocument();

        boolean readOnly = !KraServiceLocator.getService(QuestionAuthorizationService.class).hasPermission(PermissionConstants.MODIFY_QUESTION)
                || ObjectUtils.equals(request.getParameter("readOnly"), "true");
                
        if (StringUtils.equals(kraMaintenanceForm.getMethodToCall(), "edit")) {
            if (readOnly) {
                setReadOnlyMode(kraMaintenanceForm);
            } else {
                createNewQuestionVersion(maintenanceDocumentBase, request.getParameter(QUESTION_REF_ID));
            }
        } else if (StringUtils.equals(kraMaintenanceForm.getMethodToCall(), "copy")) {
            initCopiedQuestion(maintenanceDocumentBase);
        }
    }
    
    /**
     * 
     * This method sets the form for read only mode by setting the readOnly form indicator and enabling only the close action.
     * @param kraMaintenanceForm
     */
    private void setReadOnlyMode(KraMaintenanceForm kraMaintenanceForm) {
        kraMaintenanceForm.setReadOnly(true);

        Map<String, String> documentActions = new HashMap<String, String>();
        documentActions.put(KNSConstants.KUALI_ACTION_CAN_CLOSE, "TRUE");
        kraMaintenanceForm.setDocumentActions(documentActions);
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

}