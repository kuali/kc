/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.kra.protocol;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.coi.framework.*;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.coeus.sys.framework.controller.KcTransactionalDocumentActionBase;
import org.kuali.coeus.sys.framework.controller.NonCancellingRecallQuestion;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.framework.krms.KrmsRulesExecutionService;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.kra.protocol.notification.ProtocolNotification;
import org.kuali.kra.protocol.notification.ProtocolNotificationContextBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonTrainingService;
import org.kuali.kra.protocol.personnel.ProtocolPersonnelService;
import org.kuali.kra.protocol.questionnaire.SaveProtocolQuestionnaireEvent;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireConstants;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireHelperBase;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.SaveQuestionnaireAnswerEvent;
import org.kuali.coeus.common.questionnaire.framework.print.QuestionnairePrintingService;
import org.kuali.kra.protocol.auth.UnitAclLoadService;
import org.kuali.rice.core.api.util.RiceConstants;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.coreservice.framework.CoreFrameworkServiceLocator;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kns.lookup.LookupResultsService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.event.DocumentEvent;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.KRADUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * The ProtocolActionBase is the base class for all ProtocolBase actions.  Each derived
 * Action class corresponds to one tab (web page).  The derived Action class handles
 * all user requests for that particular tab (web page).
 */
public abstract class ProtocolActionBase extends KcTransactionalDocumentActionBase {

    private transient ProjectPublisher projectPublisher;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        final ActionForward forward = super.execute(mapping, form, request, response);
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        if (protocolForm.isAuditActivated()) {
            protocolForm.setUnitRulesMessages(getUnitRulesMessages(protocolForm.getProtocolDocument()));
        }
        if(GlobalVariables.getAuditErrorMap().isEmpty()) {
            KcServiceLocator.getService(AuditHelper.class).auditConditionally((ProtocolFormBase) form);
        }
        
        return forward;
    }


    
    //invoke these hooks at appropriate points in action methods to get the actual forward name from the subclasses
    protected abstract String getProtocolForwardNameHook();
    protected abstract String getQuestionnaireForwardNameHook();
    protected abstract String getPersonnelForwardNameHook();
    protected abstract String getNoteAndAttachmentForwardNameHook();
    protected abstract String getProtocolActionsForwardNameHook();
    protected abstract String getProtocolOnlineReviewForwardNameHook();
    protected abstract String getProtocolPermissionsForwardNameHook();
    protected abstract String getSpecialReviewForwardNameHook();
    protected abstract String getCustomDataForwardNameHook();
    protected abstract ProtocolNotification getProtocolNotificationHook();
    protected abstract String getProtocolHistoryForwardNameHook();
    
    public ActionForward protocol(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProtocolFormBase protocolForm = (ProtocolFormBase)form;
        protocolForm.getProtocolHelper().prepareView();
        return branchToPanelOrNotificationEditor(mapping, protocolForm, getProtocolForwardNameHook());
    }

    public ActionForward permissions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolFormBase protocolForm = (ProtocolFormBase)form;
        protocolForm.getPermissionsHelper().prepareView();
        return branchToPanelOrNotificationEditor(mapping, protocolForm, getProtocolPermissionsForwardNameHook());
    }    

    public ActionForward personnel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProtocolFormBase protocolForm = (ProtocolFormBase)form;
        getProtocolPersonnelService().selectProtocolUnit(protocolForm.getProtocolDocument().getProtocol().getProtocolPersons());
        getProtocolPersonTrainingService().updatePersonTrained(protocolForm.getProtocolDocument().getProtocol().getProtocolPersons());
        protocolForm.getPersonnelHelper().prepareView();
        return branchToPanelOrNotificationEditor(mapping, protocolForm, getPersonnelForwardNameHook());
    }    
    
    /**
     * This method gets called upon navigation to Questionnaire tab.
     * @param mapping the Action Mapping
     * @param form the Action Form
     * @param request the Http Request
     * @param response Http Response
     * @return the Action Forward
     */
    public ActionForward questionnaire(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProtocolFormBase protocolForm = (ProtocolFormBase)form;
        protocolForm.getQuestionnaireHelper().prepareView();
        protocolForm.getQuestionnaireHelper().populateAnswers();
        protocolForm.getQuestionnaireHelper().setQuestionnaireActiveStatuses();
        return branchToPanelOrNotificationEditor(mapping, protocolForm, getQuestionnaireForwardNameHook());
    }

    /**
     * This method gets called upon navigation to Notes and attachments tab.
     * @param mapping the Action Mapping
     * @param form the Action Form
     * @param request the Http Request
     * @param response Http Response
     * @return the Action Forward
     */
    public ActionForward noteAndAttachment(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) throws Exception {        
        ProtocolFormBase protocolForm = (ProtocolFormBase)form;
        protocolForm.initializeNotesAttachments();
        protocolForm.getNotesAttachmentsHelper().prepareView();
        return branchToPanelOrNotificationEditor(mapping, protocolForm, getNoteAndAttachmentForwardNameHook());
    }
    
    /**
     * This method gets called upon navigation to Special Review tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward specialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProtocolFormBase protocolForm = (ProtocolFormBase)form;
        protocolForm.getSpecialReviewHelper().prepareView();
        return branchToPanelOrNotificationEditor(mapping, protocolForm, getSpecialReviewForwardNameHook());
    }

    
    /**
     * This method gets called upon navigation to ProtocolBase Actions tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward protocolActions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception  {
        // for protocol lookup copy link - rice 1.1 need this
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        String command = request.getParameter("command");
        if (KewApiConstants.DOCSEARCH_COMMAND.equals(command)) {
            String docIdRequestParameter = request.getParameter(KRADConstants.PARAMETER_DOC_ID);
            Document retrievedDocument = KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
            protocolForm.setDocument(retrievedDocument);
            request.setAttribute(KRADConstants.PARAMETER_DOC_ID, docIdRequestParameter);        
       }
       protocolForm.initializeProtocolAction();
       // make sure current submission is displayed when navigate to action page.
       protocolForm.getActionHelper().setCurrentSubmissionNumber(Constants.PROTOCOL_DEFAULT_SUBMISSION_NUMBER);
       protocolForm.getActionHelper().prepareView();
       protocolForm.getActionHelper().prepareCommentsView();
       saveQuestionnaire(protocolForm);
       return branchToPanelOrNotificationEditor(mapping, protocolForm, getProtocolActionsForwardNameHook());
    }
    
    protected void saveQuestionnaire(ProtocolFormBase protocolForm) {
        //When a user selects the Questionnaires tab, empty answerHeaders are generated and saved to the database so that subsequent methods relying
        //on that persisted data have it available to render panels.  Make Protocol Actions tab work in this same manner so it's sub-tab 
        //Print ==> Questionnaires will render when a user enters a protocol but does not select the Questionnaire tab to answer the questions.
        protocolForm.getQuestionnaireHelper().prepareView();
        protocolForm.getQuestionnaireHelper().populateAnswers();
        protocolForm.getQuestionnaireHelper().setQuestionnaireActiveStatuses();
        Document document = protocolForm.getDocument();
        List<AnswerHeader> answerHeaders = protocolForm.getQuestionnaireHelper().getAnswerHeaders();       
        if (applyRules(new SaveQuestionnaireAnswerEvent(document, answerHeaders)) && applyRules(new SaveProtocolQuestionnaireEvent(document, answerHeaders)) ) {
            protocolForm.getQuestionnaireHelper().preSave();
            getBusinessObjectService().save(answerHeaders);
        }
    }
    
    public ActionForward protocolHistory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception  {
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        protocolForm.initializeProtocolHistory();
        protocolForm.getActionHelper().setCurrentSubmissionNumber(Constants.PROTOCOL_DEFAULT_SUBMISSION_NUMBER);
        protocolForm.getActionHelper().prepareView();
        protocolForm.getActionHelper().prepareCommentsView();
        protocolForm.getActionHelper().initSubmissionDetails();
        return branchToPanelOrNotificationEditor(mapping, protocolForm, getProtocolHistoryForwardNameHook());
    }

    public ActionForward customData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProtocolFormBase protocolForm = (ProtocolFormBase)form;
        protocolForm.getCustomDataHelper().prepareCustomData();
        return branchToPanelOrNotificationEditor(mapping, protocolForm, getCustomDataForwardNameHook());
    }
    
    protected ActionForward branchToPanelOrNotificationEditor(ActionMapping mapping, ProtocolFormBase protocolForm, String ulitmateDestination) {
        if (protocolForm.isShowNotificationEditor()) {
            protocolForm.setShowNotificationEditor(false);
            protocolForm.getNotificationHelper().getNotificationContext().setForwardName(ulitmateDestination);
            return mapping.findForward(getProtocolNotificationEditorHook());
        } else {
            return mapping.findForward(ulitmateDestination);
        }
    
    }

    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        ProtocolBase protocol = protocolForm.getProtocolDocument().getProtocol();
        
        ProtocolTaskBase task = createNewModifyProtocolTaskInstanceHook(protocol);
        AuditHelper auditHelper = KcServiceLocator.getService(AuditHelper.class);
        
        if (isAuthorized(task)) {
            if ( !protocol.isCorrectionMode() || auditHelper.auditUnconditionally(protocolForm.getDocument()) ) {
                protocolForm.setShowNotificationEditor(isInitialSave(protocolForm.getProtocolDocument()));
                this.preSave(mapping, form, request, response);
                actionForward = super.save(mapping, form, request, response);
                this.postSave(mapping, form, request, response);
                final Project project = getProjectRetrievalService().retrieveProject(protocolForm.getProtocolDocument().getProtocol().getProtocolNumber());
                if (project != null) {
                    getProjectPublisher().publishProject(project);
                }
                if (KRADConstants.SAVE_METHOD.equals(protocolForm.getMethodToCall()) && protocolForm.isAuditActivated() 
                        && GlobalVariables.getMessageMap().hasNoErrors()) {
                    // hook invocation to get the forward name
                    actionForward = mapping.findForward(getProtocolActionsForwardNameHook());
                } else if (protocolForm.isShowNotificationEditor()) {
                    ProtocolNotificationContextBase context = getProtocolInitialSaveNotificationContextHook(protocol);
                    if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
                        protocolForm.getNotificationHelper().initializeDefaultValues(context);
                        if (KRADConstants.SAVE_METHOD.equals(protocolForm.getMethodToCall())) { 
                            return mapping.findForward(getProtocolNotificationEditorHook());
                        }
                    } else {
                        protocolForm.setShowNotificationEditor(false);
                        getNotificationService().sendNotificationAndPersist(context, getProtocolNotificationHook(), protocol);
                        return actionForward;
                    }
                }
            }
        }

        return actionForward;
    }

    protected abstract ProtocolNotificationContextBase getProtocolInitialSaveNotificationContextHook(ProtocolBase protocol);

    protected abstract String getProtocolNotificationEditorHook();

    protected abstract ProtocolTaskBase createNewModifyProtocolTaskInstanceHook(ProtocolBase protocol);


    /**
     * This method allows logic to be executed before a save, after authorization is confirmed.
     * 
     * @param mapping the Action Mapping
     * @param form the Action Form
     * @param request the Http Request
     * @param response Http Response
     * @throws Exception if bad happens
     */
    public void preSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //do nothing
    }
    
    /**
     * This method allows logic to be executed after a save, after authorization is confirmed.
     * 
     * @param mapping the Action Mapping
     * @param form the Action Form
     * @param request the Http Request
     * @param response Http Response
     * @throws Exception if bad happens
     */
    public void postSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //do nothing
    }


    /**
     * Create the original set of ProtocolBase Users for a new ProtocolBase Document.
     * The creator the protocol is assigned to the PROTOCOL_AGGREGATOR role.
     */
    @Override
    protected void initialDocumentSave(KualiDocumentFormBase form) throws Exception {
      
      // Assign the creator of the protocol the AGGREGATOR role.
      ProtocolFormBase protocolForm = (ProtocolFormBase) form;
      ProtocolDocumentBase doc = protocolForm.getProtocolDocument();
      String userId = GlobalVariables.getUserSession().getPrincipalId();

      // hack to indicate whether to send the Protocol Created notification later
      protocolForm.setShowNotificationEditor(true);
      
      initialDocumentSaveAddRolesHook(userId, doc.getProtocol());
      
      // Add the users defined in the access control list for the protocol's lead unit
      Permissionable permissionable = protocolForm.getProtocolDocument().getProtocol();
      UnitAclLoadService unitAclLoadService = getUnitAclLoadService();
      unitAclLoadService.loadUnitAcl(permissionable, userId);

//moved      sendNotification(protocolForm);
   }
    
    /**
     * 
     * This method is used in conjunction with initialDocumentSave to populuate the 
     * appropriate roles for the given protocl (iacuc or irb).
     * @param userId
     * @param protocol
     */
    protected abstract void initialDocumentSaveAddRolesHook(String userId, ProtocolBase protocol);
  
    protected abstract void sendNotification(ProtocolFormBase protocolForm);
    

    
    @Override
    protected ActionForward saveOnClose(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.saveOnClose(mapping, form, request, response);
        
        if (GlobalVariables.getMessageMap().hasErrors()) {
            forward = mapping.findForward(Constants.MAPPING_BASIC);
        }
        
        return forward;
    }

    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.refresh(mapping, form, request, response);
        
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        ProtocolDocumentBase protocolDocument = protocolForm.getProtocolDocument();
                     
        // KNS UI hook for lookup resultset, check to see if we are coming back from a lookup
        if (Constants.MULTIPLE_VALUE.equals(protocolForm.getRefreshCaller())) {
            // Multivalue lookup. Note that the multivalue keyword lookup results are returned persisted to avoid using session.
            // Since URLs have a max length of 2000 chars, field conversions can not be done.
            String lookupResultsSequenceNumber = protocolForm.getLookupResultsSequenceNumber();
            
            if (StringUtils.isNotBlank(lookupResultsSequenceNumber)) {
                
                @SuppressWarnings("unchecked")
                Class<BusinessObject> lookupResultsBOClass = (Class<BusinessObject>) Class.forName(protocolForm.getLookupResultsBOClassName());
                String userName = GlobalVariables.getUserSession().getPerson().getPrincipalId();
                LookupResultsService service = KcServiceLocator.getService(LookupResultsService.class);
                Collection<BusinessObject> selectedBOs
                    = service.retrieveSelectedResultBOs(lookupResultsSequenceNumber, lookupResultsBOClass, userName);
                
                processMultipleLookupResults(protocolDocument, lookupResultsBOClass, selectedBOs);
            }
        }
        
        // TODO : hack for rice 11 upgrade
        // when return from lookup
        if (StringUtils.isNotBlank(protocolForm.getFormKey())) {
            protocolForm.setFormKey("");
        }
        return mapping.findForward(Constants.MAPPING_BASIC );
    }
    
    /**
     * This method must be overridden by a derived class if that derived class has a field that requires a 
     * Lookup that returns multiple values.  The derived class should first check the class of the selected BOs.
     * Based upon the class, the ProtocolBase can be updated accordingly.  This is necessary since there may be
     * more than one multi-lookup on a web page.
     * 
     * @param protocolDocument the ProtocolBase Document
     * @param lookupResultsBOClass the class of the BOs that are returned by the Lookup
     * @param selectedBOs the selected BOs
     */
    protected <T extends BusinessObject> void processMultipleLookupResults(ProtocolDocumentBase protocolDocument,
        Class<T> lookupResultsBOClass, Collection<T> selectedBOs) {
        // do nothing
    }


    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
                  
        ActionForward forward = null;
        
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        String command = protocolForm.getCommand();
        String detailId;
          
        if (command.startsWith(KewApiConstants.DOCSEARCH_COMMAND+"detailId")) { 
            
        }
        if (KewApiConstants.ACTIONLIST_INLINE_COMMAND.equals(command)) {
            
        } else if (getProtocolActionsMappingNameHoook().equals(command) || getProtocolOnlineReviewMappingNameHoook().equals(command)) {       
            String docIdRequestParameter = request.getParameter(KRADConstants.PARAMETER_DOC_ID);
            Document retrievedDocument = KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
            protocolForm.setDocument(retrievedDocument);
            request.setAttribute(KRADConstants.PARAMETER_DOC_ID, docIdRequestParameter);
            loadDocument(protocolForm);
        } else {
            forward = super.docHandler(mapping, form, request, response);
        }

        if (KewApiConstants.INITIATE_COMMAND.equals(protocolForm.getCommand())) {
            protocolForm.getProtocolDocument().initialize();
        } else {
            protocolForm.initialize();
        }
        
        if (getProtocolActionsMappingNameHoook().equals(command)) {
            forward = protocolActions(mapping, protocolForm, request, response);
        }
        if (getProtocolOnlineReviewMappingNameHoook().equals(command)) {
            forward = onlineReview(mapping, protocolForm, request, response);
        }
        
        return forward;
    }
    
    

    protected abstract String getProtocolOnlineReviewMappingNameHoook();

    protected abstract String getProtocolActionsMappingNameHoook();



    /**
     * Get the Kuali Rule Service.
     * @return the Kuali Rule Service
     */
    @Override
    protected KualiRuleService getKualiRuleService() {
        return KcServiceLocator.getService(KualiRuleService.class);
    }
    
    /**
     * Use the Kuali Rule Service to apply the rules for the given event.
     * @param event the event to process
     * @return true if success; false if there was a validation error
     */
    protected final boolean applyRules(DocumentEvent event) {
        return getKualiRuleService().applyRules(event);
    }

    /**
     * This method gets called upon navigation to Online Review tab.
     * @param mapping the Action Mapping
     * @param form the Action Form
     * @param request the Http Request
     * @param response Http Response
     * @return the Action Forward
     */
    public ActionForward onlineReview(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {        
        return mapping.findForward(getProtocolOnlineReviewForwardNameHook());
    }
    
    protected KcAuthorizationService getKraAuthorizationService() {
        return KcServiceLocator.getService(KcAuthorizationService.class);
    }
    
    protected UnitAclLoadService getUnitAclLoadService() {
        return KcServiceLocator.getService(UnitAclLoadService.class);
    }

    /**
     * This method is to get protocol personnel service
     * @return ProtocolPersonnelService
     */
    protected abstract ProtocolPersonnelService getProtocolPersonnelService(); 
    
    /**
     * This method is to get protocol personnel training service
     * @return ProtocolPersonTrainingService
     */
    protected abstract ProtocolPersonTrainingService getProtocolPersonTrainingService(); 

    protected QuestionnairePrintingService getQuestionnairePrintingService() {
        return KcServiceLocator.getService(QuestionnairePrintingService.class);
    }
    
    // this method was added during IACUC refactoring solely to allow this method to be visible to a service to deal with
    // a very special case where the service seems to call back into the action.
    public String buildForwardUrl(String routeHeaderId) {
        return super.buildForwardUrl(routeHeaderId);
    }
    
    protected List<String> getUnitRulesMessages(ProtocolDocumentBase protocolDoc) {
        KrmsRulesExecutionService rulesService = KcServiceLocator.getService(KrmsRulesExecutionService.class);
        return rulesService.processUnitValidations(protocolDoc.getProtocol().getLeadUnitNumber(), protocolDoc);
    }
    
    public ActionForward printQuestionnaireAnswer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        // TODO : this is only available after questionnaire is saved ?
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        ProtocolBase protocol = protocolForm.getActionHelper().getProtocol();
        final int answerHeaderIndex = this.getSelectedLine(request);
        String methodToCall = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        String formProperty = StringUtils.substringBetween(methodToCall, ".printQuestionnaireAnswer.", ".line");
        QuestionnaireHelperBase helper = (QuestionnaireHelperBase) BeanUtilsBean.getInstance().getPropertyUtils().getProperty(form, formProperty);
        AnswerHeader answerHeader = helper.getAnswerHeaders().get(answerHeaderIndex);
        // TODO : a flag to check whether to print answer or not
        // for release 3 : if questionnaire questions has answer, then print answer. 
        reportParameters.put(QuestionnaireConstants.QUESTIONNAIRE_SEQUENCE_ID_PARAMETER_NAME,
                answerHeader.getQuestionnaire()
                        .getQuestionnaireSeqIdAsInteger());
        reportParameters.put("template",
                answerHeader.getQuestionnaire()
                        .getTemplate());
        reportParameters.put("coeusModuleSubItemCode", answerHeader.getModuleSubItemCode());
        
        AttachmentDataSource dataStream = getQuestionnairePrintingService().printQuestionnaireAnswer(protocol, reportParameters);
        if (dataStream.getData() != null) {
            streamToResponse(dataStream, response);
            forward = null;
        }
        return forward;
    }
    
    /**
     * 
     * This method is for the 'update' button to update questionnaire answer to new version
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateAnswerToNewVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String methodToCallStart = "methodToCall.updateAnswerToNewVersion.";
        String methodToCallEnd = ".line";
        String methodToCall = ((String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE));
        String questionnaireHelperPath = methodToCall.substring(methodToCallStart.length(), methodToCall.indexOf(methodToCallEnd));
        QuestionnaireHelperBase helper = (QuestionnaireHelperBase) PropertyUtils.getNestedProperty(form, questionnaireHelperPath);
        helper.updateQuestionnaireAnswer(getLineToDelete(request));
        getBusinessObjectService().save(helper.getAnswerHeaders());
        return mapping.findForward(Constants.MAPPING_BASIC);

    }
    
    
    
    /**
     * Class that encapsulates the workflow for obtaining an reason from an action prompt.
     */
    private class ReasonPrompt {
        final String questionId;
        final String questionTextKey;
        final String questionType;
        final String missingReasonKey;
        final String questionCallerMapping;
        final String abortButton;
        final String noteIntroKey;

        private class Response {
            final String question;
            final ActionForward forward;
            final String reason;
            final String button;
            Response(String question, ActionForward forward) {
                this(question, forward, null, null);
            }
            Response(String question, String reason, String button) {
                this(question, null, reason, button);
            }
            private Response(String question, ActionForward forward, String reason, String button) {
                this.question = question;
                this.forward = forward;
                this.reason = reason;
                this.button = button;
            }
        }

        /**
         * @param questionId the question id/instance, 
         * @param questionTextKey application resources key for question text
         * @param questionType the {@link org.kuali.rice.kns.question.Question} question type
         * @param questionCallerMapping mapping of original action
         * @param abortButton button value considered to abort the prompt and return (optional, may be null)
         * @param noteIntroKey application resources key for quesiton text prefix (optional, may be null)
         */
        private ReasonPrompt(String questionId, String questionTextKey, String questionType, String missingReasonKey, String questionCallerMapping, String abortButton, String noteIntroKey) {
            this.questionId = questionId;
            this.questionTextKey = questionTextKey;
            this.questionType = questionType;
            this.questionCallerMapping = questionCallerMapping;
            this.abortButton = abortButton;
            this.noteIntroKey = noteIntroKey;
            this.missingReasonKey = missingReasonKey;
        }

        /**
         * Obtain a validated reason and button value via a Question prompt.  Reason is validated against
         * sensitive data patterns, and max Note text length
         * @param mapping Struts mapping
         * @param form Struts form
         * @param request http request
         * @param response http response
         * @return Response object representing *either*: 1) an ActionForward due to error or abort 2) a reason and button clicked
         * @throws Exception
         */
        @SuppressWarnings("deprecation")
        public Response ask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
            String question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
            String reason = request.getParameter(KRADConstants.QUESTION_REASON_ATTRIBUTE_NAME);

            if (StringUtils.isBlank(reason)) {
                String context = request.getParameter(KRADConstants.QUESTION_CONTEXT);
                if (context != null && StringUtils.contains(context, KRADConstants.QUESTION_REASON_ATTRIBUTE_NAME + "=")) {
                    reason = StringUtils.substringAfter(context, KRADConstants.QUESTION_REASON_ATTRIBUTE_NAME + "=");
                }
            }

            String disapprovalNoteText = "";

            // start in logic for confirming the disapproval
            if (question == null) {
                // ask question if not already asked
                return new Response(question, performQuestionWithInput(mapping, form, request, response,
                        this.questionId,
                        getKualiConfigurationService().getPropertyValueAsString(this.questionTextKey),
                        this.questionType, this.questionCallerMapping, ""));
            }

            String buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
            if (this.questionId.equals(question) && abortButton != null && abortButton.equals(buttonClicked)) {
                // if no button clicked just reload the doc
                return new Response(question, mapping.findForward(RiceConstants.MAPPING_BASIC));
            }

            // have to check length on value entered
            String introNoteMessage = "";
            if (noteIntroKey != null) {
                introNoteMessage = getKualiConfigurationService().getPropertyValueAsString(this.noteIntroKey) + KRADConstants.BLANK_SPACE;
            }

            // build out full message
            disapprovalNoteText = introNoteMessage + reason;

            // check for sensitive data in note
            boolean warnForSensitiveData = CoreFrameworkServiceLocator.getParameterService().getParameterValueAsBoolean(
                    KRADConstants.KNS_NAMESPACE, ParameterConstants.ALL_COMPONENT,
                    KRADConstants.SystemGroupParameterNames.SENSITIVE_DATA_PATTERNS_WARNING_IND);
            if (warnForSensitiveData) {
                String context = KRADConstants.QUESTION_REASON_ATTRIBUTE_NAME + "=" + reason;
                ActionForward forward = checkAndWarnAboutSensitiveData(mapping, form, request, response,
                        KRADConstants.QUESTION_REASON_ATTRIBUTE_NAME, disapprovalNoteText, this.questionCallerMapping, context);
                if (forward != null) {
                    return new Response(question, forward);
                }
            } else {
                if (KRADUtils.containsSensitiveDataPatternMatch(disapprovalNoteText)) {
                    return new Response(question, performQuestionWithInputAgainBecauseOfErrors(mapping, form, request, response,
                            this.questionId, getKualiConfigurationService().getPropertyValueAsString(this.questionTextKey),
                            this.questionType, this.questionCallerMapping, "", reason,
                            RiceKeyConstants.ERROR_DOCUMENT_FIELD_CONTAINS_POSSIBLE_SENSITIVE_DATA,
                            KRADConstants.QUESTION_REASON_ATTRIBUTE_NAME, "reason"));
                }
            }

            int disapprovalNoteTextLength = disapprovalNoteText.length();

            // get note text max length from DD
            int noteTextMaxLength = getDataDictionaryService().getAttributeMaxLength(Note.class, KRADConstants.NOTE_TEXT_PROPERTY_NAME);

            if (StringUtils.isBlank(reason) || (disapprovalNoteTextLength > noteTextMaxLength)) {

                if (reason == null) {
                    // prevent a NPE by setting the reason to a blank string
                    reason = "";
                }
                return new Response(question, performQuestionWithInputAgainBecauseOfErrors(mapping, form, request, response,
                        this.questionId,
                        getKualiConfigurationService().getPropertyValueAsString(this.questionTextKey),
                        this.questionType, this.questionCallerMapping, "", reason,
                        this.missingReasonKey,
                        KRADConstants.QUESTION_REASON_ATTRIBUTE_NAME, Integer.toString(noteTextMaxLength)));
            }

            return new Response(question, disapprovalNoteText, buttonClicked);
        }
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public ActionForward recall(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward;  // the return value
        
        ReasonPrompt prompt = new ReasonPrompt(KRADConstants.DOCUMENT_RECALL_QUESTION, 
                                               Constants.NON_CANCELLING_RECALL_QUESTION_TEXT_KEY, 
                                               Constants.NON_CANCELLING_RECALL_QUESTION, 
                                               RiceKeyConstants.ERROR_DOCUMENT_RECALL_REASON_REQUIRED, 
                                               KRADConstants.MAPPING_RECALL, 
                                               NonCancellingRecallQuestion.NO, 
                                               RiceKeyConstants.MESSAGE_RECALL_NOTE_TEXT_INTRO);
        ReasonPrompt.Response resp = prompt.ask(mapping, form, request, response);
        
        if (resp.forward != null) {
            // forward either to a fresh display of the question, or to one with "blank reason" error message due to the previous answer, 
            // or back to the document if 'return to document' (abort button) was clicked
            forward = resp.forward; 
        }
        // recall to action only if the button was selected by the user
        else if(KRADConstants.DOCUMENT_RECALL_QUESTION.equals(resp.question) && NonCancellingRecallQuestion.YES.equals(resp.button)) {
            KualiDocumentFormBase kualiDocumentFormBase = (KualiDocumentFormBase) form;
            doProcessingAfterPost(kualiDocumentFormBase, request);
            getDocumentService().recallDocument(kualiDocumentFormBase.getDocument(), resp.reason, false);
            // we should return to the portal to avoid problems with workflow routing changes to the document. 
            //This should eventually return to the holding page, but currently waiting on KCINFR-760.
            forward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        }
        else {
            // they chose not to recall so return them back to document
            forward = mapping.findForward(RiceConstants.MAPPING_BASIC);
        }
        
        return forward;
    }
    
    
    
    
    
    
    protected boolean applyRules(ProtocolFormBase protocolForm, DocumentEvent event) {
        return applyRules(event) & !protocolForm.isUnitRulesErrorsExist();
    }

    protected boolean isInitialSave(Document document) {
        String status = document.getDocumentHeader().getWorkflowDocument().getStatus().getLabel();
        return GlobalVariables.getMessageMap().hasNoErrors() && StringUtils.equals("INITIATED", status);
    }

    protected KcNotificationService getNotificationService() {
        return KcServiceLocator.getService(KcNotificationService.class);
    }


    public ProjectPublisher getProjectPublisher() {
        if (projectPublisher == null) {
            projectPublisher = KcServiceLocator.getService(ProjectPublisher.class);
        }

        return projectPublisher;
    }

    public void setProjectPublisher(ProjectPublisher projectPublisher) {
        this.projectPublisher = projectPublisher;
    }

    public abstract ProjectRetrievalService getProjectRetrievalService();
}
