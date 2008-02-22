/*
 * Copyright 2007 The Kuali Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.web.struts.action;

import static org.kuali.RiceConstants.EMPTY_STRING;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.exceptions.AuthorizationException;
import org.kuali.core.rule.event.DocumentAuditEvent;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.service.KualiRuleService;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.struts.form.KualiDocumentFormBase;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.bo.CustomAttributeDocValue;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.web.struts.action.ProposalActionBase;

import edu.iu.uis.eden.clientapp.IDocHandler;
import edu.iu.uis.eden.exception.WorkflowException;

public class ProposalDevelopmentAction extends ProposalActionBase {
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentAction.class);

    /**
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#docHandler(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ActionForward forward = super.docHandler(mapping, form, request, response);
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;

        if (IDocHandler.INITIATE_COMMAND.equals(proposalDevelopmentForm.getCommand())) {
            proposalDevelopmentForm.getProposalDevelopmentDocument().initialize();
        }else{
            proposalDevelopmentForm.initialize();
        }
        return forward;
    }
    
    /**
     * By overriding the dispatchMethod(), we can check the user's authorization to
     * perform the given action/task.  
     * @see org.apache.struts.actions.DispatchAction#dispatchMethod(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String)
     */
    @Override
    protected ActionForward dispatchMethod(ActionMapping mapping,
                                           ActionForm form,
                                           HttpServletRequest request,
                                           HttpServletResponse response,
                                           String taskName) throws Exception {
        
        ActionForward actionForward = null;
        if (!isTaskAuthorized(taskName, form)) {
            actionForward = processAuthorizationViolation(taskName, mapping, form, request, response);
        } 
        else {
            actionForward = super.dispatchMethod(mapping, form, request, response, taskName);
        }
        return actionForward;
    }


    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ActionForward actionForward = super.execute(mapping, form, request, response);
            String keywordPanelDisplay = KraServiceLocator.getService(KualiConfigurationService.class).getParameterValue(
                    Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.KEYWORD_PANEL_DISPLAY);        
            request.getSession().setAttribute(Constants.KEYWORD_PANEL_DISPLAY, keywordPanelDisplay);
            // TODO: not sure it's should be here - for audit error display.
            ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
            if (proposalDevelopmentForm.isAuditActivated()) {
                getService(KualiRuleService.class).applyRules(new DocumentAuditEvent(proposalDevelopmentForm.getDocument()));
            }
            
            //if(proposalDevelopmentForm.getProposalDevelopmentDocument().getSponsorCode()!=null){
                proposalDevelopmentForm.setAdditionalDocInfo1(new KeyLabelPair("DataDictionary.Sponsor.attributes.sponsorCode",proposalDevelopmentForm.getProposalDevelopmentDocument().getSponsorCode()));    
            //}
            
            if (getKeyPersonnelService().hasPrincipalInvestigator(proposalDevelopmentForm.getProposalDevelopmentDocument())) {
                boolean found = false;
                
                for(Iterator<ProposalPerson> person_it = proposalDevelopmentForm.getProposalDevelopmentDocument().getInvestigators().iterator();
                    person_it.hasNext() && !found; ){
                    ProposalPerson investigator = person_it.next();
                    
                    if (getKeyPersonnelService().isPrincipalInvestigator(investigator)) {
                        found = true; // Will break out of the loop as soon as the PI is found
                        proposalDevelopmentForm.setAdditionalDocInfo2(new KeyLabelPair("DataDictionary.KraAttributeReferenceDummy.attributes.principalInvestigator", investigator.getFullName()));
                    }
                }
            }
            else {
                proposalDevelopmentForm.setAdditionalDocInfo2(new KeyLabelPair("DataDictionary.KraAttributeReferenceDummy.attributes.principalInvestigator", EMPTY_STRING));
            }
            
            //if(isPrincipalInvestigator){
            //}
            
            /*if(proposalDevelopmentForm.getProposalDevelopmentDocument().getSponsorCode()!=null){
                proposalDevelopmentForm.setAdditionalDocInfo1(new KeyLabelPair("datadictionary.Sponsor.attributes.sponsorCode.label",proposalDevelopmentForm.getProposalDevelopmentDocument().getSponsorCode()));
            }
            if(proposalDevelopmentForm.getProposalDevelopmentDocument().getPrincipalInvestigator()!=null){
                proposalDevelopmentForm.setAdditionalDocInfo2(new KeyLabelPair("${Document.DataDictionary.ProposalDevelopmentDocument.attributes.sponsorCode.label}",proposalDevelopmentForm.getProposalDevelopmentDocument().getPrincipalInvestigator().getFullName()));
            }*/
    
            // setup any Proposal Development System Parameters that will be needed
            KualiConfigurationService configService = getService(KualiConfigurationService.class);
            ((ProposalDevelopmentForm)form).getProposalDevelopmentParameters().put("deliveryInfoDisplayIndicator", configService.getParameter(Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, "deliveryInfoDisplayIndicator"));
            ((ProposalDevelopmentForm)form).getProposalDevelopmentParameters().put("proposalNarrativeTypeGroup", configService.getParameter(Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, "proposalNarrativeTypeGroup"));
        
        return actionForward;
    }
    
    /**
     * Do nothing.  Used when the Proposal is in view-only mode.  Instead of saving
     * the proposal when the tab changes, we simply do nothing.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward nullOp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#loadDocument(KualiDocumentFormBase)
     */
    @Override
    protected void loadDocument(KualiDocumentFormBase kualiDocumentFormBase) throws WorkflowException {
        super.loadDocument(kualiDocumentFormBase);
        getKeyPersonnelService().populateDocument(((ProposalDevelopmentForm) kualiDocumentFormBase).getProposalDevelopmentDocument());
    }

    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
            
        // We will need to determine if the proposal is being saved for the first time.
            
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument doc = proposalDevelopmentForm.getProposalDevelopmentDocument();
        String originalStatus = getStatus(doc);
            
        ActionForward forward = super.save(mapping, form, request, response);
           
        // Special processing on the initial save of a proposal goes here!
            
        if (isInitialSave(originalStatus)) {
            initializeProposalUsers(doc); 
        }
        return forward;
    }
    

    public ActionForward proposal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("proposal");
    }

    /**
     * Action called to forward to a new KeyPersonnel tab.
     * 
     * @param mapping 
     * @param form
     * @param request
     * @param response
     * @return ActionForward instance for forwarding to the tab.
     */
    public ActionForward keyPersonnel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        getKeyPersonnelService().populateDocument(pdform.getProposalDevelopmentDocument());
              
        // Let this be taken care of in KeyPersonnelAction execute() method
        if (this instanceof ProposalDevelopmentKeyPersonnelAction) {
            LOG.info("forwarding to keyPersonnel action");
            return mapping.findForward("keyPersonnel");
        }

        new ProposalDevelopmentKeyPersonnelAction().prepare(form, request);

        return mapping.findForward("keyPersonnel");
    }

    public ActionForward specialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("specialReview");
    }

    public ActionForward questions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("questions");
    }
    
    public ActionForward permissions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("permissions");
    }
    
    public ActionForward grantsGov(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("grantsGov");
    }
    
    public ActionForward budgetVersions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        pdForm.setFinalBudgetVersion(getFinalBudgetVersion(pdForm.getProposalDevelopmentDocument().getBudgetVersionOverviews()));
        setProposalStatuses(pdForm.getProposalDevelopmentDocument());
        return mapping.findForward("budgetVersions");
    }
    
    public ActionForward abstractsAttachments(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        // TODO temporarily to set up proposal person- remove this once keyperson is completed and htmlunit testing fine
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument doc = proposalDevelopmentForm.getProposalDevelopmentDocument();
        doc.populateNarrativeRightsForLoggedinUser();
//        if (doc.getProposalPersons().isEmpty()) {
//            List proposalPersons = new ArrayList();
//            ProposalPerson proposalPerson=new ProposalPerson();
//            proposalPerson.setProposalNumber(doc.getProposalNumber());
//            proposalPerson.setProposalPersonNumber(1);
//            proposalPerson.setPersonId("000000001");
//            proposalPerson.setProposalPersonRoleId("KP");
//            proposalPerson.setFirstName("Terry");
//            proposalPerson.setLastName("Durkin");
//            proposalPerson.setFullName("Durkin,Terry");
//            proposalPersons.add(proposalPerson);
//            ProposalPerson proposalPerson2=new ProposalPerson();
//            proposalPerson2.setProposalNumber(doc.getProposalNumber());
//            proposalPerson2.setProposalPersonNumber(2);
//            proposalPerson2.setProposalPersonRoleId("KP");
//            proposalPerson2.setPersonId("000000003");
//            proposalPerson2.setFirstName("Geoff");
//            proposalPerson2.setLastName("McGregor");
//            proposalPerson2.setFullName("McGregor,Geoff");
//            proposalPersons.add(proposalPerson2);
//            doc.setProposalPersons(proposalPersons);
//        }
        return mapping.findForward("abstractsAttachments");
    }

    public ActionForward customData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Map<String, List> customAttributeGroups = new HashMap<String, List>();

        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument doc = proposalDevelopmentForm.getProposalDevelopmentDocument();

        Map<String, CustomAttributeDocument> customAttributeDocuments = doc.getCustomAttributeDocuments();
        String documentNumber = doc.getDocumentNumber();
        for(Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry:customAttributeDocuments.entrySet()) {
            CustomAttributeDocument customAttributeDocument = customAttributeDocumentEntry.getValue();
            Map<String, Object> primaryKeys = new HashMap<String, Object>();
            primaryKeys.put("documentNumber", documentNumber);
            primaryKeys.put("customAttributeId", customAttributeDocument.getCustomAttributeId());

            CustomAttributeDocValue customAttributeDocValue = (CustomAttributeDocValue) KraServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(CustomAttributeDocValue.class, primaryKeys);
            if (customAttributeDocValue != null) {
                customAttributeDocument.getCustomAttribute().setValue(customAttributeDocValue.getValue());
                proposalDevelopmentForm.getCustomAttributeValues().put("id" + customAttributeDocument.getCustomAttributeId().toString(), new String[]{customAttributeDocValue.getValue()});
            }

            String groupName = customAttributeDocument.getCustomAttribute().getGroupName();
            List<CustomAttributeDocument> customAttributeDocumentList = customAttributeGroups.get(groupName);

            if (customAttributeDocumentList == null) {
                customAttributeDocumentList = new ArrayList<CustomAttributeDocument>();
                customAttributeGroups.put(groupName, customAttributeDocumentList);
            }
            customAttributeDocumentList.add(customAttributeDocument);
        }

        ((ProposalDevelopmentForm)form).setCustomAttributeGroups(customAttributeGroups);

        return mapping.findForward("customData");
    }

    public ActionForward actions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("actions");
    }

    /**
     * This method processes an auditMode action request
     *
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward to forward to ("auditMode")
     */
    public ActionForward auditMode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        if (proposalDevelopmentForm.isAuditActivated()) {
            getService(KualiRuleService.class).applyRules(new DocumentAuditEvent(proposalDevelopmentForm.getDocument()));
        }
         return mapping.findForward("auditMode");
    }
    
    /**
     * Grabs the <code>{@link KeyPersonnelService} from Spring!
     * 
     * @return KeyPersonnelService
     */
    protected KeyPersonnelService getKeyPersonnelService() {
        return getService(KeyPersonnelService.class);
    }
    
    /**
     * Locate in Spring the <code>{@link KualiConfigurationService}</code> singleton instance
     * 
     * @return KualiConfigurationService
     */
    protected KualiConfigurationService getConfigurationService() {
        return getService(KualiConfigurationService.class);
    }
    
    /**
     * Get the current status of the document.  
     * @param doc the Proposal Development Document
     * @return the status (INITIATED, SAVED, etc.)
     */
    private String getStatus(ProposalDevelopmentDocument doc) {
        return doc.getDocumentHeader().getWorkflowDocument().getStatusDisplayValue();
    }
    
    /**
     * Is this the initial save of the document?
     * @param status the original status before the save operation
     * @return true if the initial save; otherwise false
     */
    private boolean isInitialSave(String status) {
        return GlobalVariables.getErrorMap().isEmpty() &&
               StringUtils.equals("INITIATED", status);
    }
    
    /**
     * Create the original set of Proposal Users for a new Proposal Development Document.
     * The creator the proposal is assigned to the AGGREGATOR role.
     * @param doc the Proposal Development Document
     */
    private void initializeProposalUsers(ProposalDevelopmentDocument doc) {
        
        // Assign the creator of the proposal to the AGGREGATOR role.
        
        UniversalUser user = GlobalVariables.getUserSession().getUniversalUser();
        String username = user.getPersonUserIdentifier();
        ProposalAuthorizationService proposalAuthService = KraServiceLocator.getService(ProposalAuthorizationService.class);
        proposalAuthService.addRole(username, RoleConstants.AGGREGATOR, doc);
    }
    
    /**
     * Check the authorization for executing a task.  A task corresponds to a Struts action.
     * The name of a task always corresponds to the name of the Struts action method.  For
     * Proposal Development, we always submit a Proposal Task to the Task Authorization Service
     * along with the user's username to determine if he/she has the necessary authority.
     * @param form the submitted form
     * @param request the HTTP request
     * @throws AuthorizationException
     */
    private boolean isTaskAuthorized(String taskName, ActionForm form) {
        boolean isAuthorized = true;
        if (form instanceof ProposalDevelopmentForm) {
            TaskAuthorizationService taskAuthorizationService = KraServiceLocator.getService(TaskAuthorizationService.class);
            UniversalUser user = GlobalVariables.getUserSession().getUniversalUser();
            String username = user.getPersonUserIdentifier();
            ProposalTask task = buildTask(taskName, (ProposalDevelopmentForm)form);
            isAuthorized = taskAuthorizationService.isAuthorized( username, task );
            if (!isAuthorized) {
                LOG.error("User not authorized to perform " + taskName + " for document: " + ((KualiDocumentFormBase)form).getDocument().getClass().getName() );
            }
        }
        return isAuthorized;
    }
    
    /**
     * Build a Proposal Task.  Subclasses may override this method to create a
     * different type of Proposal Task, e.g. one with narrative data.
     * @param taskName the name of the task
     * @param form the Proposal Development Form
     * @return the Proposal Task
     */
    protected ProposalTask buildTask(String taskName, ProposalDevelopmentForm form) {
        return new ProposalTask(taskName, form.getProposalDevelopmentDocument());
    }
    
    /**
     * Process an Authorization Violation.
     * @param mapping the Action Mapping
     * @param form the form
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the next action to go to
     * @throws Exception
     */
    protected ActionForward processAuthorizationViolation(String taskName, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        errorMap.putErrorWithoutFullErrorPath(Constants.TASK_AUTHORIZATION, KeyConstants.AUTHORIZATION_VIOLATION);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
}
