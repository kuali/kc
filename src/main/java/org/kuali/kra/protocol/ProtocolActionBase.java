/*
 * Copyright 2005-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.protocol;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.common.permissions.Permissionable;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitActionEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.krms.service.KrmsRulesExecutionService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.protocol.actions.ProtocolSubmissionBeanBase;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonTrainingService;
import org.kuali.kra.protocol.personnel.ProtocolPersonnelService;
import org.kuali.kra.questionnaire.QuestionnaireHelperBase;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.print.QuestionnairePrintingService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.UnitAclLoadService;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kns.lookup.LookupResultsService;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

/**
 * The ProtocolActionBase is the base class for all ProtocolBase actions.  Each derived
 * Action class corresponds to one tab (web page).  The derived Action class handles
 * all user requests for that particular tab (web page).
 */
public abstract class ProtocolActionBase extends KraTransactionalDocumentActionBase {
    
    private static final Log LOG = LogFactory.getLog(ProtocolActionBase.class);
    private static final String PROTOCOL_NUMBER = "protocolNumber";
    private static final String SUBMISSION_NUMBER = "submissionNumber";
    private static final String SUFFIX_T = "T";
    private static final String NOT_FOUND_SELECTION = "The attachment was not found for selection ";
    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;
    
   
    /** {@inheritDoc} */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        final ActionForward forward = super.execute(mapping, form, request, response);
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        if (protocolForm.isAuditActivated()) {
            protocolForm.setUnitRulesMessages(getUnitRulesMessages(protocolForm.getProtocolDocument()));
        }
        if(KNSGlobalVariables.getAuditErrorMap().isEmpty()) {
            new AuditActionHelper().auditConditionally((ProtocolFormBase) form);
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
    
    public ActionForward protocol(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ((ProtocolFormBase)form).getProtocolHelper().prepareView();
        // hook invocation to get the forward name
        return mapping.findForward(getProtocolForwardNameHook());
    }

    public ActionForward permissions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ((ProtocolFormBase)form).getPermissionsHelper().prepareView();
        return mapping.findForward(getProtocolPermissionsForwardNameHook());
    }    

    public ActionForward personnel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        getProtocolPersonnelService().selectProtocolUnit(((ProtocolFormBase) form).getProtocolDocument().getProtocol().getProtocolPersons());
        getProtocolPersonTrainingService().updatePersonTrained(((ProtocolFormBase) form).getProtocolDocument().getProtocol().getProtocolPersons());
        ((ProtocolFormBase)form).getPersonnelHelper().prepareView();
        return mapping.findForward(getPersonnelForwardNameHook());
    }    
//    

    
    /**
     * This method gets called upon navigation to Questionnaire tab.
     * @param mapping the Action Mapping
     * @param form the Action Form
     * @param request the Http Request
     * @param response Http Response
     * @return the Action Forward
     */
    public ActionForward questionnaire(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        ((ProtocolFormBase)form).getQuestionnaireHelper().prepareView();
        ((ProtocolFormBase)form).getQuestionnaireHelper().populateAnswers();
        ((ProtocolFormBase)form).getQuestionnaireHelper().setQuestionnaireActiveStatuses();
        return mapping.findForward(getQuestionnaireForwardNameHook());
    }
    
    protected ProtocolSubmissionBeanBase getSubmissionBean(ActionForm form,String submissionActionType) {
        ProtocolSubmissionBeanBase submissionBean = null;
        return submissionBean;
    }

    protected String getSubmitActionType(HttpServletRequest request) {
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        String actionTypeCode = "";
        if (StringUtils.isNotBlank(parameterName)) {
            actionTypeCode = StringUtils.substringBetween(parameterName, ".actionType", ".");
        }

        return actionTypeCode;
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
            , HttpServletRequest request, HttpServletResponse response) {        
        ((ProtocolFormBase) form).getNotesAttachmentsHelper().prepareView();
        return mapping.findForward(getNoteAndAttachmentForwardNameHook());
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
        ((ProtocolFormBase) form).getSpecialReviewHelper().prepareView();
        return mapping.findForward(getSpecialReviewForwardNameHook());
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
        // make sure current submission is displayed when navigate to action page.
        protocolForm.getActionHelper().setCurrentSubmissionNumber(-1);
       ((ProtocolFormBase)form).getActionHelper().prepareView();

       return mapping.findForward(getProtocolActionsForwardNameHook());
    }
    
    
    
    public ActionForward customData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProtocolFormBase protocolForm = (ProtocolFormBase)form;
        protocolForm.getCustomDataHelper().prepareCustomData();
        return mapping.findForward("iacucCustomData");
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#save(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public final ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;

        ProtocolTaskBase task = createNewModifyProtocolTaskInstanceHook(protocolForm.getProtocolDocument().getProtocol());
        AuditActionHelper auditActionHelper = new AuditActionHelper();
        
        if (isAuthorized(task)) {
            if ( !protocolForm.getProtocolDocument().getProtocol().isCorrectionMode() || 
                    auditActionHelper.auditUnconditionally(protocolForm.getDocument()) ) {
                this.preSave(mapping, form, request, response);
                actionForward = super.save(mapping, form, request, response);
                this.postSave(mapping, form, request, response);
                
                if (KRADConstants.SAVE_METHOD.equals(protocolForm.getMethodToCall()) && protocolForm.isAuditActivated() 
                        && GlobalVariables.getMessageMap().hasNoErrors()) {
                    // hook invocation to get the forward name
                    actionForward = mapping.findForward(getProtocolActionsForwardNameHook());
                }
            }
        }

        return actionForward;
    }


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
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#initialDocumentSave(org.kuali.core.web.struts.form.KualiDocumentFormBase)
     */
    @Override
    protected void initialDocumentSave(KualiDocumentFormBase form) throws Exception {
      
      // Assign the creator of the protocol the AGGREGATOR role.
     
      ProtocolFormBase protocolForm = (ProtocolFormBase) form;
      ProtocolDocumentBase doc = protocolForm.getProtocolDocument();
      String userId = GlobalVariables.getUserSession().getPrincipalId();
      
      initialDocumentSaveAddRolesHook(userId, doc.getProtocol());
      
      // Add the users defined in the access control list for the protocol's lead unit
      Permissionable permissionable = protocolForm.getProtocolDocument().getProtocol();
      UnitAclLoadService unitAclLoadService = getUnitAclLoadService();
      unitAclLoadService.loadUnitAcl(permissionable);

      sendNotification(protocolForm);
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

    /** {@inheritDoc} */
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
                LookupResultsService service = KraServiceLocator.getService(LookupResultsService.class);
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



    /**
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#docHandler(org.apache.struts.action.ActionMapping,
     * org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
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
        return KraServiceLocator.getService(KualiRuleService.class);
    }
    
    /**
     * Use the Kuali Rule Service to apply the rules for the given event.
     * @param event the event to process
     * @return true if success; false if there was a validation error
     */
    protected final boolean applyRules(KualiDocumentEvent event) {
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
        
    /*
     * This is to retrieve answer header based on answerheaderid
     */
    private AnswerHeader getAnswerHeader(HttpServletRequest request) {

        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("answerHeaderId", Integer.toString(this.getSelectedLine(request)));
        return  (AnswerHeader)getBusinessObjectService().findByPrimaryKey(AnswerHeader.class, fieldValues);
    }

    /*
     * get protocolnumber for answerheader moduleitemkey
     * a saved but not submitted answer has "T" at the end of protocolnumber
     */
    private String getProtocolNumber(AnswerHeader answerHeader) {
        String protocolNumber = answerHeader.getModuleItemKey();
        if (protocolNumber.endsWith(SUFFIX_T)) {
            protocolNumber = protocolNumber.substring(0, protocolNumber.length() - 1);
        }
        return protocolNumber;
    }
    
    protected KraAuthorizationService getKraAuthorizationService() {
        return KraServiceLocator.getService(KraAuthorizationService.class);
    }
    
    protected UnitAclLoadService getUnitAclLoadService() {
        return KraServiceLocator.getService(UnitAclLoadService.class);
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
        return KraServiceLocator.getService(QuestionnairePrintingService.class);
    }
    
    // this method was added during IACUC refactoring solely to allow this method to be visible to a service to deal with
    // a very special case where the service seems to call back into the action.
    public String buildForwardUrl(String routeHeaderId) {
        return super.buildForwardUrl(routeHeaderId);
    }
    
    protected List<String> getUnitRulesMessages(ProtocolDocumentBase protocolDoc) {
        KrmsRulesExecutionService rulesService = KraServiceLocator.getService(KrmsRulesExecutionService.class);
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
        reportParameters.put("questionnaireId",
                answerHeader.getQuestionnaire()
                        .getQuestionnaireIdAsInteger());
        reportParameters.put("template",
                answerHeader.getQuestionnaire()
                        .getTemplate());
        reportParameters.put("coeusModuleSubItemCode", answerHeader.getModuleSubItemCode());
        
        AttachmentDataSource dataStream = getQuestionnairePrintingService().printQuestionnaireAnswer(protocol, reportParameters);
        if (dataStream.getContent() != null) {
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
    
    protected boolean applyRules(ProtocolFormBase protocolForm, KualiDocumentEvent event) {
        return applyRules(event) & !protocolForm.isUnitRulesErrorsExist();
    }

}
