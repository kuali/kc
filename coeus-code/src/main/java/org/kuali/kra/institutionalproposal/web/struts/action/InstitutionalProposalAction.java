/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal.web.struts.action;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.sys.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.sys.framework.controller.AuditActionHelper;
import org.kuali.coeus.sys.framework.controller.KcTransactionalDocumentActionBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalLockService;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.coeus.common.framework.krms.KrmsRulesExecutionService;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.document.authorization.DocumentAuthorizer;
import org.kuali.rice.kns.document.authorization.DocumentPresentationController;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.event.DocumentEvent;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.service.PessimisticLockService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;


public class InstitutionalProposalAction extends KcTransactionalDocumentActionBase {
    private static final String MODIFY_IP = "modifyIP";
    
    private KcNotificationService notificationService;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        
        if (KNSGlobalVariables.getAuditErrorMap().isEmpty()) {
            new AuditActionHelper().auditConditionally((InstitutionalProposalForm) form);
        }
        
        if (institutionalProposalForm.isAuditActivated()){
            institutionalProposalForm.setUnitRulesMessages(getUnitRulesMessages(institutionalProposalForm.getInstitutionalProposalDocument()));
        }
        
        return actionForward;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    // Overriding this because KraTransactionalDocumentActionBase assumes the authorizer is of type KcDocumentAuthorizerBase
    protected void populateAuthorizationFields(KualiDocumentFormBase formBase) {
        if (formBase.isFormDocumentInitialized()) {
            Document document = formBase.getDocument();
            Person user = GlobalVariables.getUserSession().getPerson();
            DocumentPresentationController documentPresentationController = KNSServiceLocator.getDocumentHelperService().getDocumentPresentationController(document);
            DocumentAuthorizer documentAuthorizer = getDocumentHelperService().getDocumentAuthorizer(document);
            Set<String> documentActions =  documentPresentationController.getDocumentActions(document);
            documentActions = documentAuthorizer.getDocumentActions(document, user, documentActions);

            if (getDataDictionaryService().getDataDictionary().getDocumentEntry(document.getClass().getName()).getUsePessimisticLocking()) {
                documentActions = getPessimisticLockService().getDocumentActions(document, user, documentActions);
            }
            
            Set<String> editModes = new HashSet<String>();
            if (!documentAuthorizer.canOpen(document, user)) {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            } else if (documentActions.contains(KRADConstants.KUALI_ACTION_CAN_EDIT)) {
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            } else {
                editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
            }
            if (hasPermission("Edit Institutional Proposal")) {
                editModes.add(MODIFY_IP);
            }
            Map editMode = this.convertSetToMap(editModes);
            if (getDataDictionaryService().getDataDictionary().getDocumentEntry(document.getClass().getName()).getUsePessimisticLocking()) {
                editMode = getPessimisticLockService().establishLocks(document, editMode, user);
            }
            
            // We don't want to use KNS way to determine can edit document overview
            // It should be the same as can edit
            if (editMode.containsKey(AuthorizationConstants.EditMode.FULL_ENTRY)) {
                if (!documentActions.contains(KRADConstants.KUALI_ACTION_CAN_EDIT_DOCUMENT_OVERVIEW)) {
                    documentActions.add(KRADConstants.KUALI_ACTION_CAN_EDIT_DOCUMENT_OVERVIEW);
                }
            } else {
                if (documentActions.contains(KRADConstants.KUALI_ACTION_CAN_EDIT_DOCUMENT_OVERVIEW)) {
                    documentActions.remove(KRADConstants.KUALI_ACTION_CAN_EDIT_DOCUMENT_OVERVIEW);
                }
            }
            
            if (editMode.containsKey(AuthorizationConstants.EditMode.VIEW_ONLY) &&
                    !editMode.containsKey(MODIFY_IP) && documentActions.contains(KRADConstants.KUALI_ACTION_CAN_RELOAD)) {
                documentActions.remove(KRADConstants.KUALI_ACTION_CAN_RELOAD);
            }
            formBase.setDocumentActions(convertSetToMap(documentActions));
            formBase.setEditingMode(editMode);
        }
        
    }
    
    private boolean hasPermission(String permissionName){
        KcPerson person = getKcPersonService().getKcPersonByUserName(getUserName());       
        return getUnitAuthorizationService().hasPermission(person.getPersonId(), "KC-IP", permissionName);

    }
    private String getUserName() {
        return GlobalVariables.getUserSession().getPerson().getPrincipalName();
    }

    private UnitAuthorizationService getUnitAuthorizationService() {
        return KcServiceLocator.getService(UnitAuthorizationService.class);
    }

    private KcPersonService getKcPersonService() {
        return KcServiceLocator.getService(KcPersonService.class);
    }

    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        forward = super.save(mapping, form, request, response);
            if (institutionalProposalForm.getMethodToCall().equals("save") && institutionalProposalForm.isAuditActivated()) {
                forward = mapping.findForward(Constants.MAPPING_INSTITUTIONAL_PROPOSAL_ACTIONS_PAGE);
            }
       

        return forward;
    }
    
    @Override
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        
        if (institutionalProposalForm.getViewFundingSource()) {
            return mapping.findForward(Constants.MAPPING_CLOSE_PAGE);
        } else {
            return super.close(mapping, form, request, response);
        }
    }
    
    /**
     * 
     * This method gets called upon navigation to Institute Proposal tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward home(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {        
        return mapping.findForward(Constants.MAPPING_INSTITUTIONAL_PROPOSAL_HOME_PAGE);
    }
    
    /**
     * 
     * This method gets called upon navigation to Contacts tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward contacts(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {        
        InstitutionalProposalForm ipForm = (InstitutionalProposalForm) form;
        ipForm.getCentralAdminContactsBean().initCentralAdminContacts();
        return mapping.findForward(Constants.MAPPING_INSTITUTIONAL_PROPOSAL_CONTACTS_PAGE);
    }
    
    /*
     * Hacktacular.... sorry
     */
    public ActionForward Contacts(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {        
        return contacts(mapping, form, request, response);
    }
    
    /**
     * 
     * This method gets called upon navigation to Custom Data tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward specialReview(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {
        ((InstitutionalProposalForm) form).getSpecialReviewHelper().prepareView();
        return mapping.findForward(Constants.MAPPING_INSTITUTIONAL_PROPOSAL_SPECIAL_REVIEW_PAGE);
    }
    
    /**
     * 
     * This method gets called upon navigation to Custom Data tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward intellectualPropertyReview(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {        
        return mapping.findForward(Constants.MAPPING_INSTITUTIONAL_PROPOSAL_INTELLECTUAL_PROPERTY_REVIEW_PAGE);
    }
    
    /**
     * 
     * This method gets called upon navigation to Custom Data tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward distribution(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {        
        return mapping.findForward(Constants.MAPPING_INSTITUTIONAL_PROPOSAL_DISTRIBUTION_PAGE);
    }
    
    /**
     * 
     * This method gets called upon navigation to Custom Data tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward institutionalProposalActions(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String command = request.getParameter(KewApiConstants.COMMAND_PARAMETER);
        if (StringUtils.isNotEmpty(command) && KewApiConstants.DOCSEARCH_COMMAND.equals(command)) {
            loadDocumentInForm(request, (InstitutionalProposalForm) form); 
        }
        
        return mapping.findForward(Constants.MAPPING_INSTITUTIONAL_PROPOSAL_ACTIONS_PAGE);
    }
    
    /**
     * 
     * This method gets called upon navigation to Custom Data tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward customData(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {     
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        institutionalProposalForm.getCustomDataHelper().prepareCustomData();
        return mapping.findForward(Constants.MAPPING_INSTITUTIONAL_PROPOSAL_CUSTOM_DATA_PAGE);
    }
    
    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @param institutionalProposalForm
     * @return
     * @throws Exception
     */
    ActionForward handleDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response, InstitutionalProposalForm institutionalProposalForm) throws Exception {       
        ActionForward forward = super.docHandler(mapping, form, request, response);
        return forward;
    }
    
    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        InstitutionalProposalDocument institutionalProposalDocument = (InstitutionalProposalDocument) institutionalProposalForm.getDocument();
        
        ActionForward forward = null;
        
        String command = institutionalProposalForm.getCommand();
        if (Constants.MAPPING_INSTITUTIONAL_PROPOSAL_ACTIONS_PAGE.equals(command)) {
            loadDocument(institutionalProposalForm);
            forward = institutionalProposalActions(mapping, form, request, response);
        } else {
            forward = super.docHandler(mapping, form, request, response);
        }

        if (!(request.getParameter("docOpenedFromIPSearch") == null)) {
            if (request.getParameter("docOpenedFromIPSearch").equals("true")) {
                institutionalProposalForm.setDocOpenedFromIPSearch(true);
            }
        }
        
        if (Constants.MAPPING_INSTITUTIONAL_PROPOSAL_ACTIONS_PAGE.equals(command)) {
            forward = institutionalProposalActions(mapping, form, request, response);
        }  
       
        return forward;
    }
    
    @Override
    protected void loadDocument(KualiDocumentFormBase kualiDocumentFormBase) throws WorkflowException {
        super.loadDocument(kualiDocumentFormBase);
        InstitutionalProposal proposal = ((InstitutionalProposalForm) kualiDocumentFormBase).getInstitutionalProposalDocument().getInstitutionalProposal();
        proposal.setSponsorNihMultiplePi(getSponsorHierarchyService().isSponsorNihMultiplePi(proposal.getSponsorCode()));
        //work around to make sure project person reference to inst prop is to the same instance as the document has
        //without this the references were different causing issues when the sponsor was changed.
        if (!proposal.getProjectPersons().isEmpty()) {
            proposal.getProjectPersons().get(0);
        }
    }

    
    /**
    *
    * This method gets called upon navigation to Medusa tab.
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return
    */
   public ActionForward medusa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
       
       InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
       if (institutionalProposalForm.getDocument().getDocumentNumber() == null) {
           //if we are loading this from the medusa link on the search
           loadDocumentInForm(request, institutionalProposalForm);
       }
       InstitutionalProposalDocument document = (InstitutionalProposalDocument) institutionalProposalForm.getDocument();
       
       institutionalProposalForm.getMedusaBean().setMedusaViewRadio("0");
       institutionalProposalForm.getMedusaBean().setModuleName("IP");
       institutionalProposalForm.getMedusaBean().setModuleIdentifier(document.getInstitutionalProposal().getProposalId());
       institutionalProposalForm.getMedusaBean().generateParentNodes();
       return mapping.findForward(Constants.MAPPING_INSTITUTIONAL_PROPOSAL_MEDUSA_PAGE);
   }
   
   protected void loadDocumentInForm(HttpServletRequest request, InstitutionalProposalForm institutionalProposalForm) throws WorkflowException {
       String docIdRequestParameter = request.getParameter(KRADConstants.PARAMETER_DOC_ID);
       InstitutionalProposalDocument retrievedDocument = (InstitutionalProposalDocument)KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
       institutionalProposalForm.setDocument(retrievedDocument);
       request.setAttribute(KRADConstants.PARAMETER_DOC_ID, docIdRequestParameter);        
   }
   
   /**
    * Use the Kuali Rule Service to apply the rules for the given event.
    * @param event the event to process
    * @return true if success; false if there was a validation error
    */
    protected final boolean applyRules(DocumentEvent event) {
        return getKualiRuleService().applyRules(event);
    }
    
    @Override
    protected PessimisticLockService getPessimisticLockService() {
        return KcServiceLocator.getService(InstitutionalProposalLockService.class);
    }
    
    protected SponsorHierarchyService getSponsorHierarchyService() {
        return KcServiceLocator.getService(SponsorHierarchyService.class);
    }
    
    protected KcNotificationService getNotificationService() {
        if (notificationService == null) {
            notificationService = KcServiceLocator.getService(KcNotificationService.class);
        }
        return notificationService;
    }

    public void setNotificationService(KcNotificationService notificationService) {
        this.notificationService = notificationService;
    }    
    
    protected List<String> getUnitRulesMessages(InstitutionalProposalDocument ipDoc) {
        KrmsRulesExecutionService rulesService = KcServiceLocator.getService(KrmsRulesExecutionService.class);
        return rulesService.processUnitValidations(ipDoc.getLeadUnitNumber(), ipDoc);
    }

}
