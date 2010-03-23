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
package org.kuali.kra.institutionalproposal.web.struts.action;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalLockService;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.UnitAuthorizationService;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.authorization.DocumentAuthorizer;
import org.kuali.rice.kns.document.authorization.DocumentPresentationController;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.PessimisticLockService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;

/**
 * This class...
 */
public class InstitutionalProposalAction extends KraTransactionalDocumentActionBase {
    private static final String MODIFY_IP = "modifyIP";

    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        new AuditActionHelper().auditConditionally((InstitutionalProposalForm)form);
        return actionForward;
    }
    
    /**
     * @see org.kuali.rice.kns.web.struts.action.KualiTransactionalDocumentActionBase#populateAuthorizationFields(org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase)
     */
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
            } else if (documentActions.contains(KNSConstants.KUALI_ACTION_CAN_EDIT)) {
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
                if (!documentActions.contains(KNSConstants.KUALI_ACTION_CAN_EDIT__DOCUMENT_OVERVIEW)) {
                    documentActions.add(KNSConstants.KUALI_ACTION_CAN_EDIT__DOCUMENT_OVERVIEW);
                }
            } else {
                if (documentActions.contains(KNSConstants.KUALI_ACTION_CAN_EDIT__DOCUMENT_OVERVIEW)) {
                    documentActions.remove(KNSConstants.KUALI_ACTION_CAN_EDIT__DOCUMENT_OVERVIEW);
                }
            }
            
            if (editMode.containsKey(AuthorizationConstants.EditMode.VIEW_ONLY) &&
                    !editMode.containsKey(MODIFY_IP) && documentActions.contains(KNSConstants.KUALI_ACTION_CAN_RELOAD)) {
                documentActions.remove(KNSConstants.KUALI_ACTION_CAN_RELOAD);
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
        return KraServiceLocator.getService(UnitAuthorizationService.class);
    }

    private KcPersonService getKcPersonService() {
        return KraServiceLocator.getService(KcPersonService.class);
    }

    
    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
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
    
    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    
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
            , HttpServletRequest request, HttpServletResponse response) {        
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
        return institutionalProposalForm.getCustomDataHelper().institutionalProposalCustomData(mapping, form, request, response);
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
        institutionalProposalForm.getInstitutionalProposalDocument().populateCustomAttributes();
        return forward;
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
       return mapping.findForward(Constants.MAPPING_INSTITUTIONAL_PROPOSAL_MEDUSA_PAGE);
   }
   
   protected void loadDocumentInForm(HttpServletRequest request, InstitutionalProposalForm institutionalProposalForm) throws WorkflowException {
       String docIdRequestParameter = request.getParameter(KNSConstants.PARAMETER_DOC_ID);
       InstitutionalProposalDocument retrievedDocument = (InstitutionalProposalDocument)KNSServiceLocator.getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
       institutionalProposalForm.setDocument(retrievedDocument);
       request.setAttribute(KNSConstants.PARAMETER_DOC_ID, docIdRequestParameter);        
   }      
    
    @Override
    protected PessimisticLockService getPessimisticLockService() {
        return KraServiceLocator.getService(InstitutionalProposalLockService.class);
    }
    
}
