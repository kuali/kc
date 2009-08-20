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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.kra.questionnaire.question.QuestionService;
import org.kuali.kra.service.VersioningService;
import org.kuali.kra.service.impl.VersioningServiceImpl;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.kns.service.SequenceAccessorService;
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
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        KualiMaintenanceForm maintenanceForm = (KualiMaintenanceForm) form;
        MaintenanceDocumentBase maintenanceDocument = (MaintenanceDocumentBase) maintenanceForm.getDocument();
        Question question = (Question) maintenanceDocument.getNewMaintainableObject().getBusinessObject();
        question.refreshReferenceObject("questionType");

        return mapping.findForward(Constants.MAPPING_BASIC );
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

        return mapping.findForward(Constants.MAPPING_BASIC );
    }
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
                if (form instanceof KraMaintenanceForm) {
                    KraMaintenanceForm kraMaintenanceForm = (KraMaintenanceForm) form;
                    if (kraMaintenanceForm.getBusinessObjectClassName().equals(Question.class.getName())
                            && kraMaintenanceForm.getMethodToCall().equals("edit")) {

                        // Get most recent approved Question from DB
                        Question approvedQuestion = getQuestionService().getQuestionByRefId(request.getParameter(QUESTION_REF_ID));

                        // Create new version of Question
                        VersioningService versioningService = new VersioningServiceImpl();
                        Question versionedQuestion = (Question) versioningService.createNewVersion(approvedQuestion);

                        // Get access to old and new questions
                        MaintenanceDocumentBase maintenanceDocumentBase = (MaintenanceDocumentBase) kraMaintenanceForm.getDocument();
                        QuestionMaintainableImpl oldMaintainableObject = (QuestionMaintainableImpl) maintenanceDocumentBase.getOldMaintainableObject();
                        Question oldQuestion = (Question) oldMaintainableObject.getBusinessObject();
                        QuestionMaintainableImpl newMaintainableObject = (QuestionMaintainableImpl) maintenanceDocumentBase.getNewMaintainableObject();
                        Question newQuestion = (Question) newMaintainableObject.getBusinessObject();
                        
//                        // First Try:
//                        //   Populate new question with versionedQuestion
//                        //   -> "The key(s) for this Maintenance Document (Question Ref Id) have changed from the Old version to the New. This is not allowed when editing an existing object."
//                        newMaintainableObject.setBusinessObject(versionedQuestion);
                        
                        
//                        // Second Try:
//                        //   Populate old and new question with versionedQuestion
//                        //   -> "The key(s) for this Maintenance Document (Question Ref Id) have changed from the Old version to the New. This is not allowed when editing an existing object."
//                        oldMaintainableObject.setBusinessObject(versionedQuestion);
//                        newMaintainableObject.setBusinessObject(versionedQuestion);
                        
//                        // Third Try:
//                        //   Null old question
//                        //   Populate new question with versionedQuestion
//                        //   -> "The key(s) for this Maintenance Document (Question Ref Id) have changed from the Old version to the New. This is not allowed when editing an existing object."
//                        oldMaintainableObject.setBusinessObject(null);
//                        newMaintainableObject.setBusinessObject(versionedQuestion);

                          // Fourth Try:
//                        //   Populate new question with versionedQuestion
//                        //   Generate new questionRefId and populate new and old question with it
                          Long questionRefId = KraServiceLocator.getService(SequenceAccessorService.class).getNextAvailableSequenceNumber("SEQ_QUESTIONNAIRE_ID");
                          versionedQuestion.setQuestionRefId(questionRefId);
                          oldQuestion.setQuestionRefId(questionRefId);
                          newMaintainableObject.setBusinessObject(versionedQuestion);

                          System.out.println(">> Versioning");
                    }
                }
                
        return actionForward;
    }
    
    private QuestionService getQuestionService() {
        return (QuestionService) KraServiceLocator.getService(QuestionService.class);
    }

}