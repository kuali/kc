/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.common.customattributes.CustomDataAction;
import org.kuali.kra.common.permissions.Permissionable;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewForm;
import org.kuali.kra.irb.personnel.ProtocolPersonTrainingService;
import org.kuali.kra.irb.personnel.ProtocolPersonnelService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.UnitAclLoadService;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.lookup.LookupResultsService;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.rule.event.KualiDocumentEvent;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.service.KualiRuleService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.RiceKeyConstants;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;

/**
 * The ProtocolAction is the base class for all Protocol actions.  Each derived
 * Action class corresponds to one tab (web page).  The derived Action class handles
 * all user requests for that particular tab (web page).
 */
public abstract class ProtocolAction extends KraTransactionalDocumentActionBase {
    
    /** {@inheritDoc} */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        final ActionForward forward = super.execute(mapping, form, request, response);
        if(GlobalVariables.getAuditErrorMap().isEmpty()) {
            new AuditActionHelper().auditConditionally((ProtocolForm) form);
        }
        
        return forward;
    }
    
    public ActionForward protocol(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ((ProtocolForm)form).getProtocolHelper().prepareView();
        return mapping.findForward("protocol");
    }

    public ActionForward personnel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        getProtocolPersonnelService().selectProtocolUnit(((ProtocolForm) form).getDocument().getProtocol().getProtocolPersons());
        getProtocolPersonTrainingService().updatePersonTrained(((ProtocolForm) form).getDocument().getProtocol().getProtocolPersons());
        ((ProtocolForm)form).getPersonnelHelper().prepareView();
        return mapping.findForward("personnel");
    }
    
    public ActionForward permissions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ((ProtocolForm)form).getPermissionsHelper().prepareView();
        return mapping.findForward("permissions");
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
        
        ((ProtocolForm)form).getQuestionnaireHelper().prepareView();
        ((ProtocolForm)form).getQuestionnaireHelper().populateAnswers();
        return mapping.findForward("questionnaire");
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
        ((ProtocolForm) form).getAttachmentsHelper().prepareView();
        ((ProtocolForm) form).getNotepadHelper().prepareView();
        return mapping.findForward("noteAndAttachment");
    }
    
    public ActionForward specialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ((ProtocolForm)form).getSpecialReviewHelper().prepareView();
        return mapping.findForward("specialReview");
    }
    
    public ActionForward protocolActions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception  {
        // for protocol lookup copy link - rice 1.1 need this
        ProtocolForm protocolForm = (ProtocolForm) form;
        String command = request.getParameter("command");
        if (KEWConstants.DOCSEARCH_COMMAND.equals(command)) {
            String docIdRequestParameter = request.getParameter(KNSConstants.PARAMETER_DOC_ID);
            Document retrievedDocument = KNSServiceLocator.getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
            protocolForm.setDocument(retrievedDocument);
        }
        // make sure current submission is displayed when navigate to action page.
        protocolForm.getActionHelper().setCurrentSubmissionNumber(-1);
       ((ProtocolForm)form).getActionHelper().prepareView();
        return mapping.findForward("protocolActions");
    }
    
    public ActionForward customData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ((ProtocolForm)form).getCustomDataHelper().prepareView();
        return CustomDataAction.customData(mapping, form, request, response);
    }

    /**
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#save(org.apache.struts.action.ActionMapping,
     * org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public final ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL, protocolForm.getDocument().getProtocol());
        boolean validToSave = false;
        if (isAuthorized(task)) {
            if (isValidSave(protocolForm)) {
                this.preSave(mapping, form, request, response);
                actionForward = super.save(mapping, form, request, response);
                this.postSave(mapping, form, request, response);
                validToSave = true;
            }
        }
        
        if (protocolForm.getMethodToCall().equals("save") && protocolForm.isAuditActivated() && validToSave) {
            actionForward = mapping.findForward("protocolActions");
        }

        return actionForward;
    }
    
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
     * Can the protocol be saved?  This method is normally overridden by
     * a subclass in order to invoke business rules to verify that the
     * protocol can be saved.
     * @param protocolForm the Protocol Form
     * @return true if the protocol can be saved; otherwise false
     */
    protected boolean isValidSave(ProtocolForm protocolForm) {
        return true;
    }
    
    /**
     * Create the original set of Protocol Users for a new Protocol Document.
     * The creator the protocol is assigned to the PROTOCOL_AGGREGATOR role.
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#initialDocumentSave(org.kuali.core.web.struts.form.KualiDocumentFormBase)
     */
    @Override
    protected void initialDocumentSave(KualiDocumentFormBase form) throws Exception {
        
        // Assign the creator of the protocol the AGGREGATOR role.
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument doc = protocolForm.getDocument();
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        KraAuthorizationService kraAuthService = KraServiceLocator.getService(KraAuthorizationService.class);
        kraAuthService.addRole(userId, RoleConstants.PROTOCOL_AGGREGATOR, doc.getProtocol());
        
        // Add the users defined in the access control list for the protocol's lead unit
        
        Permissionable permissionable = protocolForm.getDocument().getProtocol();
        UnitAclLoadService unitAclLoadService = KraServiceLocator.getService(UnitAclLoadService.class);
        unitAclLoadService.loadUnitAcl(permissionable);

    }
    
    /**
     * Checks for a valid save on close, setting up the required variables, before proceeding with save.
     * 
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#saveOnClose(org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase)
     */
    @Override
    protected void saveOnClose(KualiDocumentFormBase form) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;

        if (isValidSave(protocolForm)) {
            super.saveOnClose(form);
        }
    }

    /** {@inheritDoc} */
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.refresh(mapping, form, request, response);
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = protocolForm.getDocument();
                     
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
     * Based upon the class, the Protocol can be updated accordingly.  This is necessary since there may be
     * more than one multi-lookup on a web page.
     * 
     * @param protocolDocument the Protocol Document
     * @param lookupResultsBOClass the class of the BOs that are returned by the Lookup
     * @param selectedBOs the selected BOs
     */
    protected <T extends BusinessObject> void processMultipleLookupResults(ProtocolDocument protocolDocument,
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
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        String command = protocolForm.getCommand();
        
        if (KEWConstants.ACTIONLIST_INLINE_COMMAND.equals(command)) {
             String docIdRequestParameter = request.getParameter(KNSConstants.PARAMETER_DOC_ID);
             Document retrievedDocument = KNSServiceLocator.getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
             protocolForm.setDocument(retrievedDocument);
             request.setAttribute(KNSConstants.PARAMETER_DOC_ID, docIdRequestParameter);
             forward = mapping.findForward(Constants.MAPPING_COPY_PROPOSAL_PAGE);
             forward = new ActionForward(forward.getPath()+ "?" + KNSConstants.PARAMETER_DOC_ID + "=" + docIdRequestParameter);  
        } else {
             forward = super.docHandler(mapping, form, request, response);
        }

        if (KEWConstants.INITIATE_COMMAND.equals(protocolForm.getCommand())) {
            protocolForm.getDocument().initialize();
        }else{
            protocolForm.initialize();
        }
        
        return forward;
    }

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
     * This method is to get protocol personnel training service
     * @return ProtocolPersonTrainingService
     */
    private ProtocolPersonTrainingService getProtocolPersonTrainingService() {
        return (ProtocolPersonTrainingService)KraServiceLocator.getService("protocolPersonTrainingService");
    }
    
    /**
     * This method is to get protocol personnel service
     * @return ProtocolPersonnelService
     */
    private ProtocolPersonnelService getProtocolPersonnelService() {
        return (ProtocolPersonnelService)KraServiceLocator.getService("protocolPersonnelService");
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
        return mapping.findForward("onlineReview");
    }
    
    

}
