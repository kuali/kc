/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.web.struts.action;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.RiceConstants;
import org.kuali.RicePropertyConstants;
import org.kuali.core.bo.BusinessObject;
import org.kuali.core.bo.Note;
import org.kuali.core.bo.PersistableBusinessObject;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.document.Document;
import org.kuali.core.rule.event.DocumentAuditEvent;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.service.KualiRuleService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.ObjectUtils;
import org.kuali.core.web.struts.form.KualiDocumentFormBase;
import org.kuali.core.web.struts.form.KualiForm;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.bo.CustomAttributeDocValue;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.budget.web.struts.action.BudgetTDCValidator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.proposaldevelopment.service.NarrativeService;
import org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.proposaldevelopment.service.ProposalPersonBiographyService;
import org.kuali.kra.proposaldevelopment.service.ProposalRoleTemplateService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.kra.s2s.service.PrintService;
import org.kuali.kra.s2s.service.S2SService;
import org.kuali.kra.web.struts.action.ProposalActionBase;
import org.kuali.rice.KNSServiceLocator;

import edu.iu.uis.eden.clientapp.IDocHandler;
import edu.iu.uis.eden.exception.WorkflowException;

public class ProposalDevelopmentAction extends ProposalActionBase {
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentAction.class);

    /**
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#docHandler(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;
        
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        String command = proposalDevelopmentForm.getCommand();
        
        if (IDocHandler.ACTIONLIST_INLINE_COMMAND.equals(command)) {
             String docIdRequestParameter = request.getParameter(RiceConstants.PARAMETER_DOC_ID);
             Document retrievedDocument = KNSServiceLocator.getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
             proposalDevelopmentForm.setDocument(retrievedDocument);
             request.setAttribute(RiceConstants.PARAMETER_DOC_ID, docIdRequestParameter);
             forward = mapping.findForward(Constants.MAPPING_COPY_PROPOSAL_PAGE);
             forward = new ActionForward(forward.getPath()+ "?" + RiceConstants.PARAMETER_DOC_ID + "=" + docIdRequestParameter);  
        } else {
             forward = super.docHandler(mapping, form, request, response);
        }

        if (IDocHandler.INITIATE_COMMAND.equals(proposalDevelopmentForm.getCommand())) {
            proposalDevelopmentForm.getDocument().initialize();
        }else{
            proposalDevelopmentForm.initialize();
        }
        
        return forward;
    }
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ActionForward actionForward = super.execute(mapping, form, request, response);
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = proposalDevelopmentForm.getDocument();
         String keywordPanelDisplay = KraServiceLocator.getService(KualiConfigurationService.class).getParameterValue(
                    Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.KEYWORD_PANEL_DISPLAY);        
            request.getSession().setAttribute(Constants.KEYWORD_PANEL_DISPLAY, keywordPanelDisplay);
            // TODO: not sure it's should be here - for audit error display.
            if (proposalDevelopmentForm.isAuditActivated()) {
                KraServiceLocator.getService(KualiRuleService.class).applyRules(new DocumentAuditEvent(proposalDevelopmentForm.getDocument()));
                if (document != null &&
                    document.getS2sOpportunity() != null ) {
                    KraServiceLocator.getService(S2SService.class).validateApplication(document.getS2sOpportunity().getProposalNumber());
                }
            }

            assignSponsor(proposalDevelopmentForm);
            
            if (getKeyPersonnelService().hasPrincipalInvestigator(proposalDevelopmentForm.getDocument())) {
                boolean found = false;
                
                for(Iterator<ProposalPerson> person_it = proposalDevelopmentForm.getDocument().getInvestigators().iterator();
                    person_it.hasNext() && !found; ){
                    ProposalPerson investigator = person_it.next();
                    
                    if (getKeyPersonnelService().isPrincipalInvestigator(investigator)) {
                        found = true; // Will break out of the loop as soon as the PI is found
                        proposalDevelopmentForm.setAdditionalDocInfo2(new KeyLabelPair("DataDictionary.KraAttributeReferenceDummy.attributes.principalInvestigator", investigator.getFullName()));
                    }
                }
            }
            else {
                proposalDevelopmentForm.setAdditionalDocInfo2(new KeyLabelPair("DataDictionary.KraAttributeReferenceDummy.attributes.principalInvestigator", RiceConstants.EMPTY_STRING));
            }
    
            // setup any Proposal Development System Parameters that will be needed
            KualiConfigurationService configService = KraServiceLocator.getService(KualiConfigurationService.class);
            ((ProposalDevelopmentForm)form).getProposalDevelopmentParameters().put("deliveryInfoDisplayIndicator", configService.getParameter(Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, "deliveryInfoDisplayIndicator"));
            ((ProposalDevelopmentForm)form).getProposalDevelopmentParameters().put("proposalNarrativeTypeGroup", configService.getParameter(Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, "proposalNarrativeTypeGroup"));
            
            if(document.getS2sOpportunity()!=null && document.getS2sOpportunity().getS2sOppForms()!=null){
                Collections.sort(document.getS2sOpportunity().getS2sOppForms(),new S2sOppFormsComparator2());
                Collections.sort(document.getS2sOpportunity().getS2sOppForms(),new S2sOppFormsComparator1());
            }
         return actionForward;
    }

    /**
     * Assigns the {@link Sponsor} name of the {@link ProposalDevelopmentDocument} instance contained in the given
     * {@link ProposalDevelopmentForm}. If the {@link Sponsor} has not been set on the {@link ProposalDevelopmentDocument} (the {@link Sponsor} reference is <code>null</code>,)
     * then the value on the form is simply an empty {@link String}
     *
     * @param form the {@link ProposalDevelopmentForm} instance to assign the {@link Sponsor} name to
     */
    private void assignSponsor(ProposalDevelopmentForm form) {
        KeyLabelPair sponsorName = new KeyLabelPair("DataDictionary.Sponsor.attributes.sponsorName", "");

        if (form.getDocument().getSponsor() != null) {
            sponsorName.setLabel(form.getDocument().getSponsor().getSponsorName());
        }
        
        form.setAdditionalDocInfo1(sponsorName);
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
        getKeyPersonnelService().populateDocument(((ProposalDevelopmentForm) kualiDocumentFormBase).getDocument());
    }

    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // We will need to determine if the proposal is being saved for the first time.

        final ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        final ProposalDevelopmentDocument doc = proposalDevelopmentForm.getDocument();
        final String originalStatus = getStatus(doc);

        updateProposalDocument(proposalDevelopmentForm);
        ActionForward forward = super.save(mapping, form, request, response);

        // Special processing on the initial save of a proposal goes here!

        if (isInitialSave(originalStatus)) {
            initializeProposalUsers(doc);
        }

        proposalDevelopmentForm.setFinalBudgetVersion(getFinalBudgetVersion(doc.getBudgetVersionOverviews()));
        setBudgetStatuses(doc);

        //if not on budget page
        if ("ProposalDevelopmentBudgetVersionsAction".equals(proposalDevelopmentForm.getActionName())) {
            GlobalVariables.getErrorMap().addToErrorPath(RiceConstants.DOCUMENT_PROPERTY_NAME + ".proposal");

            final BudgetTDCValidator tdcValidator = new BudgetTDCValidator(request);
            tdcValidator.validateGeneratingErrorsAndWarnings(doc);
        }

        return forward;
    }

    protected void updateProposalDocument(ProposalDevelopmentForm pdForm) {
        ProposalDevelopmentDocument pdDocument = pdForm.getDocument();
        ProposalDevelopmentDocument updatedDocCopy = getProposalDoc(pdDocument.getDocumentNumber());
        
        //For Budget Lock region, this is the only way in which a Proposal Document might get updated
        if(StringUtils.isNotEmpty(pdForm.getActionName()) && !pdForm.getActionName().equalsIgnoreCase("ProposalDevelopmentBudgetVersionsAction" )) {
            if(updatedDocCopy != null && updatedDocCopy.getVersionNumber() > pdDocument.getVersionNumber()) {
                  //refresh the reference
                pdDocument.setBudgetVersionOverviews(updatedDocCopy.getBudgetVersionOverviews());
                pdDocument.setBudgetStatus(updatedDocCopy.getBudgetStatus());
                try {
                    fixVersionNumbers(updatedDocCopy, pdDocument, new ArrayList<Object>());
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                pdDocument.setVersionNumber(updatedDocCopy.getVersionNumber());
                pdDocument.getDocumentHeader().setVersionNumber(updatedDocCopy.getDocumentHeader().getVersionNumber());
                int noteIndex = 0;
                for(Object note: pdDocument.getDocumentHeader().getBoNotes()) {
                    Note updatedNote = updatedDocCopy.getDocumentHeader().getBoNote(noteIndex);
                    ((Note) note).setVersionNumber(updatedNote.getVersionNumber());
                    noteIndex++;
                }
                for(DocumentNextvalue documentNextValue : pdDocument.getDocumentNextvalues()) {
                    DocumentNextvalue updatedDocumentNextvalue = updatedDocCopy.getDocumentNextvalueBo(documentNextValue.getPropertyName());
                    if(updatedDocumentNextvalue != null) {
                        documentNextValue.setVersionNumber(updatedDocumentNextvalue.getVersionNumber());
                    }
                }
            }
            pdForm.setDocument(pdDocument);
        }
        
    }
    
    private boolean isPropertyGetterMethod(Method method, Method methods[]) {
        if (method.getName().startsWith("get") && method.getParameterTypes().length == 0) {
            String setterName = method.getName().replaceFirst("get", "set");
            for (Method m : methods) {
                if (m.getName().equals(setterName)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private void fixVersionNumbers(Object srcObject, Object object, List<Object> list) throws Exception {
        Class[] setterParamTypes = {Long.class};
        if (object != null && object instanceof PersistableBusinessObject) {
            if (list.contains(object)) return;
            list.add(object);
            
            Method getterMethod = object.getClass().getMethod("getVersionNumber");
            if(getterMethod != null) {
                Long currentVersionNumber = null;
                if(srcObject != null) 
                    currentVersionNumber = (Long) getterMethod.invoke(srcObject, new Object[]{});
                else
                    currentVersionNumber = (Long) getterMethod.invoke(object, new Object[]{});
                
                Method setterMethod = object.getClass().getMethod("setVersionNumber", setterParamTypes);
                if(currentVersionNumber != null) {
                    setterMethod.invoke(object, currentVersionNumber);
                }
            }
            
            Method[] methods = object.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (isPropertyGetterMethod(method, methods)) {
                    Object srcValue = null;
                    if(srcObject != null) {
                        srcValue = method.invoke(srcObject);
                    }
                    Object value = method.invoke(object);
                    if (value != null && value instanceof Collection) {
                        Collection c = (Collection) value;
                        Object[] srcC = c.toArray();
                        if(srcValue != null) {
                            srcC = ((Collection) srcValue).toArray();
                        } 
                        
                        Iterator iter = c.iterator();
                        int count = 0;
                        while (iter.hasNext()) {
                            Object srcEntry = null;
                            if(srcC.length > count) 
                                srcEntry = srcC[count];
                            Object entry = iter.next();
                            fixVersionNumbers(srcEntry, entry, list);
                            count++;
                        }
                    } else {
                        fixVersionNumbers(srcValue, value, list);
                    }   
                }
            }
        }
    }

    protected ProposalDevelopmentDocument getProposalDoc(String pdDocumentNumber) {
        BusinessObjectService boService = KraServiceLocator.getService(BusinessObjectService.class);
        Map<String, Object> keyMap = new HashMap<String, Object>();
        keyMap.put("documentNumber", pdDocumentNumber);
        ProposalDevelopmentDocument newCopy = (ProposalDevelopmentDocument) boService.findByPrimaryKey(ProposalDevelopmentDocument.class, keyMap);
        return newCopy;
    }
    
    public ActionForward proposal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward(Constants.PROPOSAL_PAGE);
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
        getKeyPersonnelService().populateDocument(pdform.getDocument());
              
        // Let this be taken care of in KeyPersonnelAction execute() method
        if (this instanceof ProposalDevelopmentKeyPersonnelAction) {
            LOG.info("forwarding to keyPersonnel action");
            return mapping.findForward(Constants.KEY_PERSONNEL_PAGE);
        }

        new ProposalDevelopmentKeyPersonnelAction().prepare(form, request);

        return mapping.findForward(Constants.KEY_PERSONNEL_PAGE);
    }

    public ActionForward specialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward(Constants.SPECIAL_REVIEW_PAGE);
    }

    public ActionForward questions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward(Constants.QUESTIONS_PAGE);
    }
    
    public ActionForward permissions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward(Constants.PERMISSIONS_PAGE);
    }
    
    public ActionForward grantsGov(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward(Constants.GRANTS_GOV_PAGE);
    }

    public ActionForward budgetVersions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        final ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        final String headerTabCall = getHeaderTabDispatch(request);
        if(StringUtils.isEmpty(headerTabCall)) {
            pdForm.getDocument().refreshPessimisticLocks();
        }
        pdForm.setFinalBudgetVersion(getFinalBudgetVersion(pdForm.getDocument().getBudgetVersionOverviews()));
        setBudgetStatuses(pdForm.getDocument());

        final BudgetTDCValidator tdcValidator = new BudgetTDCValidator(request);
        tdcValidator.validateGeneratingWarnings(pdForm.getDocument());

        return mapping.findForward(Constants.PD_BUDGET_VERSIONS_PAGE);
    }

    public ActionForward abstractsAttachments(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        // TODO temporarily to set up proposal person- remove this once keyperson is completed and htmlunit testing fine
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument doc = proposalDevelopmentForm.getDocument();
        doc.populateNarrativeRightsForLoggedinUser();

        /*
         * Save the current set of narratives.  In some cases, a user can view the
         * narrative panel info, but is not allowed to change it.  We will make a
         * copy of the original narratives to use for comparison when a save occurs.
         * If a user attempted to change a narrative they were not authorized to,
         * then an error will be posted.
         */
        List<Narrative> narratives = (List<Narrative>) ObjectUtils.deepCopy((Serializable) doc.getNarratives());
        proposalDevelopmentForm.setNarratives(narratives);
        KraServiceLocator.getService(ProposalPersonBiographyService.class).setPersonnelBioTimeStampUser(doc.getPropPersonBios());
        List<Narrative> narrativeList = new ArrayList<Narrative> ();
        narrativeList.addAll(doc.getNarratives());
        narrativeList.addAll(doc.getInstituteAttachments());
        KraServiceLocator.getService(NarrativeService.class).setNarrativeTimeStampUser(narrativeList);

        return mapping.findForward(Constants.ATTACHMENTS_PAGE);
    }

    public ActionForward customData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        SortedMap<String, List<CustomAttributeDocument>> customAttributeGroups = new TreeMap<String, List<CustomAttributeDocument>>();

        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument doc = proposalDevelopmentForm.getDocument();

        Map<String, CustomAttributeDocument> customAttributeDocuments = doc.getCustomAttributeDocuments();
        String documentNumber = doc.getDocumentNumber();
        for(Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry:customAttributeDocuments.entrySet()) {
            CustomAttributeDocument customAttributeDocument = customAttributeDocumentEntry.getValue();
            Map<String, Object> primaryKeys = new HashMap<String, Object>();
            primaryKeys.put(RicePropertyConstants.DOCUMENT_NUMBER, documentNumber);
            primaryKeys.put(Constants.CUSTOM_ATTRIBUTE_ID, customAttributeDocument.getCustomAttributeId());

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

        return mapping.findForward(Constants.CUSTOM_ATTRIBUTES_PAGE);
    }

    public ActionForward actions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        PrintService printService = KraServiceLocator.getService(PrintService.class);
        printService.populateSponsorForms(proposalDevelopmentForm.getSponsorFormTemplates(), proposalDevelopmentDocument.getSponsorCode());
        return mapping.findForward(Constants.PROPOSAL_ACTIONS_PAGE);
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
            KraServiceLocator.getService(KualiRuleService.class).applyRules(new DocumentAuditEvent(proposalDevelopmentForm.getDocument()));
        }
         return mapping.findForward("auditMode");
    }
    
    /**
     * Grabs the <code>{@link KeyPersonnelService} from Spring!
     * 
     * @return KeyPersonnelService
     */
    protected KeyPersonnelService getKeyPersonnelService() {
        return KraServiceLocator.getService(KeyPersonnelService.class);
    }
    
    /**
     * Locate in Spring the <code>{@link KualiConfigurationService}</code> singleton instance
     * 
     * @return KualiConfigurationService
     */
    protected KualiConfigurationService getConfigurationService() {
        return KraServiceLocator.getService(KualiConfigurationService.class);
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
    protected void initializeProposalUsers(ProposalDevelopmentDocument doc) {
        
        // Assign the creator of the proposal to the AGGREGATOR role.
        
        UniversalUser user = GlobalVariables.getUserSession().getUniversalUser();
        String username = user.getPersonUserIdentifier();
        ProposalAuthorizationService proposalAuthService = KraServiceLocator.getService(ProposalAuthorizationService.class);
        if (!proposalAuthService.hasRole(username, doc, RoleConstants.AGGREGATOR))
            proposalAuthService.addRole(username, RoleConstants.AGGREGATOR, doc);
        
        // Add the users defined in the role templates for the proposal's lead unit
        
        ProposalRoleTemplateService proposalRoleTemplateService = KraServiceLocator.getService(ProposalRoleTemplateService.class);
        proposalRoleTemplateService.addUsers(doc);
    }
    
    /**
     * Get the name of the action.  Every Proposal Action class has the
     * naming convention of
     * 
     *      ProposalDevelopment<name>Action
     * 
     * This method extracts the <name> from the above class name.
     * 
     * @return the action's name
     */
    protected String getActionName() {
        String name = getClass().getSimpleName();
        int endIndex = name.lastIndexOf("Action");
        return name.substring(19, endIndex);
    }
    
    /**
     * Overriding headerTab to customize how clearing tab state works on PDForm.
     */
    @Override
    public ActionForward headerTab(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ((KualiForm) form).setTabStates(new HashMap());
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposaldevelopmentdocument=pdform.getDocument();

        UniversalUser currentUser = GlobalVariables.getUserSession().getUniversalUser();
        for (Iterator<ProposalPerson> person_it = proposaldevelopmentdocument.getProposalPersons().iterator(); person_it.hasNext();) {
            ProposalPerson person = person_it.next();
            if((person!= null) && (person.getProposalPersonRoleId().equals(Constants.PRINCIPAL_INVESTIGATOR_ROLE))){
                if(StringUtils.isNotBlank(person.getUserName()) && StringUtils.equals(person.getUserName(), currentUser.getPersonUserIdentifier())){
                    pdform.setReject(true);

                }
            }else if((person!= null) && (person.getProposalPersonRoleId().equals(Constants.CO_INVESTIGATOR_ROLE))){
                if(StringUtils.isNotBlank(person.getUserName())&& StringUtils.equals(person.getUserName(), currentUser.getPersonUserIdentifier())){
                    pdform.setReject(true);
                }
                else if((person!= null) && (person.getProposalPersonRoleId().equals(Constants.KEY_PERSON_ROLE))){
                    if(StringUtils.isNotBlank(person.getUserName())&& StringUtils.equals(person.getUserName(), currentUser.getPersonUserIdentifier())){
                        pdform.setReject(true);
                    }
                }
            }
        }
        return super.headerTab(mapping, form, request, response);
    }


}

class S2sOppFormsComparator1 implements Comparator<S2sOppForms> {
    public int compare(S2sOppForms s2sOppForms1, S2sOppForms s2sOppForms2) {
        return  s2sOppForms2.getAvailable().compareTo(s2sOppForms1.getAvailable());
    }
  }

class S2sOppFormsComparator2 implements Comparator<S2sOppForms> {
    public int compare(S2sOppForms s2sOppForms1, S2sOppForms s2sOppForms2) {
        return  s2sOppForms2.getMandatory().compareTo(s2sOppForms1.getMandatory());
    }
  }
