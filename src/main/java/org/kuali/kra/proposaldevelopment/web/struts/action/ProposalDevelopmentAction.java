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
package org.kuali.kra.proposaldevelopment.web.struts.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import org.kuali.kra.bo.CustomAttributeDocValue;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.web.struts.action.BudgetParentActionBase;
import org.kuali.kra.budget.web.struts.action.BudgetTDCValidator;
import org.kuali.kra.common.customattributes.CustomDataHelperBase.LabelComparator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.bo.ProposalColumnsToAlter;
import org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria;
import org.kuali.kra.proposaldevelopment.bo.ProposalDevelopmentApproverViewDO;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.hierarchy.ProposalHierarcyActionHelper;
import org.kuali.kra.proposaldevelopment.printing.service.ProposalDevelopmentPrintingService;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.proposaldevelopment.service.NarrativeService;
import org.kuali.kra.proposaldevelopment.service.ProposalAbstractsService;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.proposaldevelopment.service.ProposalPersonBiographyService;
import org.kuali.kra.proposaldevelopment.service.ProposalRoleTemplateService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.service.S2SService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.KraWorkflowService;
import org.kuali.kra.service.PersonEditableService;
import org.kuali.kra.service.SponsorService;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.bo.Note;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.rule.event.KualiDocumentEvent;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.ParameterConstants;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.KNSPropertyConstants;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.springframework.util.CollectionUtils;

public class ProposalDevelopmentAction extends BudgetParentActionBase {
    private static final String PROPOSAL_NARRATIVE_TYPE_GROUP = "proposalNarrativeTypeGroup";
    private static final String DELIVERY_INFO_DISPLAY_INDICATOR = "deliveryInfoDisplayIndicator";
    private static final String ERROR_NO_GRANTS_GOV_FORM_SELECTED = "error.proposalDevelopment.no.grants.gov.form.selected";
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentAction.class);
    private ProposalHierarcyActionHelper hierarchyHelper;
    
    /**
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#docHandler(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;
        
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        String command = proposalDevelopmentForm.getCommand();
        String createProposalFromGrantsGov=request.getParameter("createProposalFromGrantsGov");
        S2sOpportunity s2sOpportunity = new S2sOpportunity();
        if(createProposalFromGrantsGov!=null && createProposalFromGrantsGov.equals("true")){
        s2sOpportunity = proposalDevelopmentForm.getDocument().getDevelopmentProposal().getS2sOpportunity();
        }
        //KRACOEUS-5064
        if (proposalDevelopmentForm.getDocument().getDocumentHeader().getDocumentNumber() == null && request.getParameter(KNSConstants.PARAMETER_DOC_ID) != null) {
            loadDocumentInForm(request, proposalDevelopmentForm);
        }
        if (KEWConstants.ACTIONLIST_INLINE_COMMAND.equals(command)) {
            //forward = mapping.findForward(Constants.MAPPING_COPY_PROPOSAL_PAGE);
            //KRACOEUS-5064
            KraWorkflowService workflowService = KraServiceLocator.getService(KraWorkflowService.class);
            ProposalDevelopmentApproverViewDO approverViewDO = workflowService.populateApproverViewDO(proposalDevelopmentForm);
            proposalDevelopmentForm.setApproverViewDO(approverViewDO);
            forward = mapping.findForward(Constants.MAPPING_PROPOSAL_SUMMARY_PAGE);
            forward = new ActionForward(forward.getPath()+ "?" + KNSConstants.PARAMETER_DOC_ID + "=" + request.getParameter(KNSConstants.PARAMETER_DOC_ID));  
        } //else if (Constants.MAPPING_PROPOSAL_ACTIONS.equals(command)) {
//            loadDocument(proposalDevelopmentForm);
//            forward = actions(mapping, proposalDevelopmentForm, request, response);
//        } else {
//            forward = super.docHandler(mapping, form, request, response);
//        }
        else {
            if (proposalDevelopmentForm.getDocTypeName() == null || proposalDevelopmentForm.getDocTypeName().equals("")) {
                proposalDevelopmentForm.setDocTypeName("ProposalDevelopmentDocument");
            }
            KraWorkflowService workflowService = KraServiceLocator.getService(KraWorkflowService.class);
            if (workflowService.canPerformWorkflowAction(proposalDevelopmentForm.getDocument())) {

                ProposalDevelopmentApproverViewDO approverViewDO = workflowService.populateApproverViewDO(proposalDevelopmentForm);
                proposalDevelopmentForm.setApproverViewDO(approverViewDO);
                
                super.docHandler(mapping, form, request, response);

                forward = mapping.findForward(Constants.MAPPING_PROPOSAL_APPROVER_VIEW_PAGE);
                forward = new ActionForward(forward.getPath()+ "?" + KNSConstants.PARAMETER_DOC_ID + "=" + request.getParameter(KNSConstants.PARAMETER_DOC_ID));
            }
            else if (Constants.MAPPING_PROPOSAL_ACTIONS.equals(command)) {
              loadDocument(proposalDevelopmentForm);
              forward = actions(mapping, proposalDevelopmentForm, request, response);
            }
            else {
                forward = super.docHandler(mapping, form, request, response);
            }
        }
    
        if (proposalDevelopmentForm.getDocument().isProposalDeleted()) {
            return mapping.findForward("deleted");
        }

        if (KEWConstants.INITIATE_COMMAND.equals(proposalDevelopmentForm.getCommand())) {
            proposalDevelopmentForm.getDocument().initialize();
        } else {
            proposalDevelopmentForm.initialize();
        }
        
        if (Constants.MAPPING_PROPOSAL_ACTIONS.equals(command)) {
            forward = actions(mapping, proposalDevelopmentForm, request, response);
        }
               
        if(createProposalFromGrantsGov!=null && createProposalFromGrantsGov.equals("true") && s2sOpportunity!=null){
            createS2sOpportunityDetails(proposalDevelopmentForm,s2sOpportunity);

        }
        
        return forward;
    }
    
      private void createS2sOpportunityDetails(ProposalDevelopmentForm proposalDevelopmentForm,S2sOpportunity s2sOpportunity) throws S2SException {

        Boolean mandatoryFormNotAvailable = false;
        if(s2sOpportunity.getCfdaNumber()!=null){
            proposalDevelopmentForm.getDocument().getDevelopmentProposal().setCfdaNumber(s2sOpportunity.getCfdaNumber());
        }
        if(s2sOpportunity.getOpportunityId()!=null){
            proposalDevelopmentForm.getDocument().getDevelopmentProposal().setProgramAnnouncementNumber(s2sOpportunity.getOpportunityId());
        }
        if(s2sOpportunity.getOpportunityTitle()!=null){
            proposalDevelopmentForm.getDocument().getDevelopmentProposal().setProgramAnnouncementTitle(s2sOpportunity.getOpportunityTitle());
        }
        List<S2sOppForms> s2sOppForms = new ArrayList<S2sOppForms>();
        if(s2sOpportunity.getSchemaUrl()!=null){
            s2sOppForms = KraServiceLocator.getService(S2SService.class).parseOpportunityForms(s2sOpportunity);
            if(s2sOppForms!=null){
                for(S2sOppForms s2sOppForm:s2sOppForms){
                    if(s2sOppForm.getMandatory() && !s2sOppForm.getAvailable()){
                        mandatoryFormNotAvailable = true;
                        break;
                    }
                }
            }
            if(!mandatoryFormNotAvailable){
                s2sOpportunity.setS2sOppForms(s2sOppForms);
                s2sOpportunity.setVersionNumber(proposalDevelopmentForm.getVersionNumberForS2sOpportunity());
                proposalDevelopmentForm.setVersionNumberForS2sOpportunity(null);
                proposalDevelopmentForm.getDocument().getDevelopmentProposal().setS2sOpportunity(s2sOpportunity);
            }else{
                GlobalVariables.getErrorMap().putError(Constants.NO_FIELD, KeyConstants.ERROR_IF_OPPORTUNITY_ID_IS_INVALID,proposalDevelopmentForm.getDocument().getDevelopmentProposal().getS2sOpportunity().getOpportunityId());
                proposalDevelopmentForm.getDocument().getDevelopmentProposal().setS2sOpportunity(new S2sOpportunity());
            }
        }
    }
    
    protected ProposalHierarcyActionHelper getHierarchyHelper() {
        if (hierarchyHelper == null) {
            hierarchyHelper = new ProposalHierarcyActionHelper();
        }
        return hierarchyHelper;
    }
    

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ActionForward actionForward = super.execute(mapping, form, request, response);
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = proposalDevelopmentForm.getDocument();
        String keywordPanelDisplay = this.getParameterService().getParameterValue(
                    ProposalDevelopmentDocument.class, Constants.KEYWORD_PANEL_DISPLAY);        
            request.getSession().setAttribute(Constants.KEYWORD_PANEL_DISPLAY, keywordPanelDisplay);
            // TODO: not sure it's should be here - for audit error display.
            // ES: Still do not know how exactly *how* this should be done, 
            // but I added a check to only call the auditConditionally when the audit error 
            // map is empty - otherwise the display during a submit 
            //check to see if the audit errors are filled out.  This happens on a submit
            //that fails.
            
            if( GlobalVariables.getAuditErrorMap().isEmpty())
                new AuditActionHelper().auditConditionally(proposalDevelopmentForm);
            proposalDevelopmentForm.setProposalDataOverrideMethodToCalls(this.constructColumnsToAlterLookupMTCs(proposalDevelopmentForm.getDocument().getDevelopmentProposal().getProposalNumber()));
//            if (proposalDevelopmentForm.isAuditActivated()) {
//                if (document != null && 
//                    document.getDevelopmentProposal().getS2sOpportunity() != null ) {
//                    getService(S2SService.class).validateApplication(document);            
//                }
//            }

            //if(isPrincipalInvestigator){
            //}
            
            /*if(proposalDevelopmentForm.getDocument().getSponsorCode()!=null){
                proposalDevelopmentForm.setAdditionalDocInfo1(new KeyLabelPair("datadictionary.Sponsor.attributes.sponsorCode.label",proposalDevelopmentForm.getDocument().getSponsorCode()));
            }
            if(proposalDevelopmentForm.getDocument().getPrincipalInvestigator()!=null){
                proposalDevelopmentForm.setAdditionalDocInfo2(new KeyLabelPair("${Document.DataDictionary.ProposalDevelopmentDocument.attributes.sponsorCode.label}",proposalDevelopmentForm.getDocument().getPrincipalInvestigator().getFullName()));
            }*/
    
            // setup any Proposal Development System Parameters that will be needed
            
            ((ProposalDevelopmentForm)form).getProposalDevelopmentParameters().put(DELIVERY_INFO_DISPLAY_INDICATOR, this.getParameterService().retrieveParameter(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, DELIVERY_INFO_DISPLAY_INDICATOR));
            ((ProposalDevelopmentForm)form).getProposalDevelopmentParameters().put(PROPOSAL_NARRATIVE_TYPE_GROUP, this.getParameterService().retrieveParameter(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, PROPOSAL_NARRATIVE_TYPE_GROUP));
            
            if(document.getDevelopmentProposal().getS2sOpportunity()!=null && document.getDevelopmentProposal().getS2sOpportunity().getS2sOppForms()!=null){
                Collections.sort(document.getDevelopmentProposal().getS2sOpportunity().getS2sOppForms(),new S2sOppFormsComparator2());
                Collections.sort(document.getDevelopmentProposal().getS2sOpportunity().getS2sOppForms(),new S2sOppFormsComparator1());
            }
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
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#loadDocument(KualiDocumentFormBase)
     */
    @Override
    protected void loadDocument(KualiDocumentFormBase kualiDocumentFormBase) throws WorkflowException {
        super.loadDocument(kualiDocumentFormBase);
        ProposalDevelopmentDocument document = ((ProposalDevelopmentForm)kualiDocumentFormBase).getDocument();
        loadDocument(document);
    }
    
    /**
     * 
     * Called to load all necessary elements in the proposal development document
     * @param document
     */
    protected void loadDocument(ProposalDevelopmentDocument document) {
        getKeyPersonnelService().populateDocument(document);
        updateNIHDescriptions(document);
        setBudgetStatuses(document);
    }
    
    protected SponsorService getSponsorService() {
        return KraServiceLocator.getService(SponsorService.class);
    }
    
    /**
     * 
     * Updates portions of the proposal that are not persisted and are based on whether the
     * sponsor is NIH or not
     * @param document
     */
    protected void updateNIHDescriptions(ProposalDevelopmentDocument document) {
        SponsorService sponsorService = getSponsorService();
        DevelopmentProposal proposal = document.getDevelopmentProposal();
        // Update the NIH related properties since this information is not persisted with the document
        // (isSponsorNih sets the nih property as a side effect)
        if (sponsorService.isSponsorNihMultiplePi(proposal)) {
            proposal.setNihDescription(getKeyPersonnelService().loadKeyPersonnelRoleDescriptions(true));
        }
        proposal.setSponsorNihMultiplePi(sponsorService.isSponsorNihMultiplePi(proposal));
        proposal.setSponsorNihOsc(sponsorService.isSponsorNihOsc(proposal));
    }
    

    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // We will need to determine if the proposal is being saved for the first time.

        final ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        final ProposalDevelopmentDocument doc = proposalDevelopmentForm.getDocument();
        S2sOpportunity s2sOpportunity= new S2sOpportunity();
        s2sOpportunity = proposalDevelopmentForm.getDocument().getDevelopmentProposal().getS2sOpportunity();
        if(s2sOpportunity!=null && s2sOpportunity.getProposalNumber()==null){
            proposalDevelopmentForm.getDocument().getDevelopmentProposal().setS2sOpportunity(null);
            proposalDevelopmentForm.setS2sOpportunity(s2sOpportunity);
        }
        updateProposalDocument(proposalDevelopmentForm);
        
        preSave(mapping, proposalDevelopmentForm, request, response);
        
        ActionForward forward = super.save(mapping, form, request, response);
        // If validation is turned on, take the user to the proposal actions page (which contains the validation panel, which auto-expands)
        if (proposalDevelopmentForm.isAuditActivated()) {
            forward = mapping.findForward(Constants.MAPPING_PROPOSAL_ACTIONS);
        }
        s2sOpportunity=proposalDevelopmentForm.getS2sOpportunity();
        if(s2sOpportunity!=null)
            doc.getDevelopmentProposal().setS2sOpportunity(s2sOpportunity);
        
        doc.getDevelopmentProposal().updateProposalNumbers();

        proposalDevelopmentForm.setFinalBudgetVersion(getFinalBudgetVersion(doc.getBudgetDocumentVersions()));
        setBudgetStatuses(doc);
        
        //if not on budget page
        if ("ProposalDevelopmentBudgetVersionsAction".equals(proposalDevelopmentForm.getActionName())) {
            GlobalVariables.getErrorMap().addToErrorPath(KNSConstants.DOCUMENT_PROPERTY_NAME + ".proposal");

            final BudgetTDCValidator tdcValidator = new BudgetTDCValidator(request);
            tdcValidator.validateGeneratingErrorsAndWarnings(doc);
        }

        return forward;
    }

    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#saveOnClose(org.apache.struts.action.ActionMapping, 
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected ActionForward saveOnClose(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.saveOnClose(mapping, form, request, response);
        
        final ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        final ProposalDevelopmentDocument doc = proposalDevelopmentForm.getDocument();
       
        updateProposalDocument(proposalDevelopmentForm);
        
        doc.getDevelopmentProposal().updateProposalNumbers();
        
        return forward;
    }
    
    @Override
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        
        if (proposalDevelopmentForm.getViewFundingSource()) {
            return mapping.findForward(Constants.MAPPING_CLOSE_PAGE);
        } else {
            return super.close(mapping, form, request, response);
        }
    }

    /**
     * 
     * This method attempts to deal with the multiple pessimistic locks that can be on the proposal development document
     * Proposal, Narratives, and Budget must all be treated separately and therefore the other portions of the document,
     * outside of the one currently being saved, must be updated from the database to make sure the sessions do not stomp
     * changes already persisted by another session.
     * @param pdForm
     * @throws Exception
     */
    protected void updateProposalDocument(ProposalDevelopmentForm pdForm) throws Exception {
        ProposalDevelopmentDocument pdDocument = pdForm.getDocument();
        ProposalDevelopmentDocument updatedDocCopy = getProposalDoc(pdDocument.getDocumentNumber());
        
        if (updatedDocCopy != null) {
            
            //For Budget and Narrative Lock regions, this is the only way in which a Proposal Document might get updated
            if (StringUtils.isNotEmpty(pdForm.getActionName()) && updatedDocCopy != null) {
                if (!pdForm.getActionName().equalsIgnoreCase("ProposalDevelopmentBudgetVersionsAction")) {
                    pdDocument.setBudgetDocumentVersions(updatedDocCopy.getBudgetDocumentVersions());
                    pdDocument.getDevelopmentProposal().setBudgetStatus(updatedDocCopy.getDevelopmentProposal().getBudgetStatus());
                } else {
                    //in case other parts of the document have been saved since we have saved,
                    //we save off possibly changed parts and reload the rest of the document
                    List<BudgetDocumentVersion> newVersions = pdDocument.getBudgetDocumentVersions();
                    String budgetStatus = pdDocument.getDevelopmentProposal().getBudgetStatus();
                    
                    pdForm.setDocument(updatedDocCopy);
                    pdDocument = updatedDocCopy;
                    loadDocument(pdDocument);
                    
                    pdDocument.setBudgetDocumentVersions(newVersions);
                    pdDocument.getDevelopmentProposal().setBudgetStatus(budgetStatus);                  
                }
                if (!pdForm.getActionName().equalsIgnoreCase("ProposalDevelopmentAbstractsAttachmentsAction" )) {
                    pdDocument.getDevelopmentProposal().setNarratives(updatedDocCopy.getDevelopmentProposal().getNarratives());
                    pdDocument.getDevelopmentProposal().setInstituteAttachments(updatedDocCopy.getDevelopmentProposal().getInstituteAttachments());
                    pdDocument.getDevelopmentProposal().setProposalAbstracts(updatedDocCopy.getDevelopmentProposal().getProposalAbstracts());
                    pdDocument.getDevelopmentProposal().setPropPersonBios(updatedDocCopy.getDevelopmentProposal().getPropPersonBios());
                    removePersonnelAttachmentForDeletedPerson(pdDocument);
               } else {
                   //in case other parts of the document have been saved since we have saved,
                   //we save off possibly changed parts and reload the rest of the document
                   List<Narrative> newNarratives = pdDocument.getDevelopmentProposal().getNarratives();
                   List<Narrative> instituteAttachments = pdDocument.getDevelopmentProposal().getInstituteAttachments();
                   List<ProposalAbstract> newAbstracts = pdDocument.getDevelopmentProposal().getProposalAbstracts();
                   List<ProposalPersonBiography> newBiographies = pdDocument.getDevelopmentProposal().getPropPersonBios();
    
                   pdForm.setDocument(updatedDocCopy);
                   pdDocument = updatedDocCopy;
                   loadDocument(pdDocument);
    
                   //now re-add narratives that could include changes and can't be modified otherwise
                   pdDocument.getDevelopmentProposal().setNarratives(newNarratives);
                   pdDocument.getDevelopmentProposal().setInstituteAttachments(instituteAttachments);
                   pdDocument.getDevelopmentProposal().setProposalAbstracts(newAbstracts);
                   pdDocument.getDevelopmentProposal().setPropPersonBios(newBiographies);
    
               }
            }
            
            //rice objects are still using optimistic locking so update rice BO versions
            //if no other session has saved this document we should be updating with same version number, but no easy way to know if it has been
            //I don't think anyway? So do it every time.
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
            //fix budget document version's document headers
            for (int i = 0; i < pdDocument.getBudgetDocumentVersions().size(); i++) {
                BudgetDocumentVersion curVersion = pdDocument.getBudgetDocumentVersion(i);
                BudgetDocumentVersion otherVersion = updatedDocCopy.getBudgetDocumentVersion(i);
                otherVersion.refreshReferenceObject("documentHeader");
                if (curVersion != null && otherVersion != null) {
                    curVersion.getDocumentHeader().setVersionNumber(otherVersion.getDocumentHeader().getVersionNumber());
                }
            }
            pdForm.setDocument(pdDocument);
        }
    }
    
    /*
     * The updatePD has some issue, such as if person is deleted, and the person attachment is also deleted.
     * However, the updateProposalDocument recover everything from DB.  so, add this method to delete the deleted, but not saved personnel attachment.
     */
    private void removePersonnelAttachmentForDeletedPerson(ProposalDevelopmentDocument proposaldevelopmentDocument) {

        List<ProposalPersonBiography> personAttachments = new ArrayList();
        for (ProposalPersonBiography proposalPersonBiography : proposaldevelopmentDocument.getDevelopmentProposal()
                .getPropPersonBios()) {
            boolean personFound = false;
            for (ProposalPerson person : proposaldevelopmentDocument.getDevelopmentProposal().getProposalPersons()) {
                if (proposalPersonBiography.getProposalPersonNumber().equals(person.getProposalPersonNumber())) {
                    personFound = true;
                    break;
                }
            }
            if (!personFound) {
                personAttachments.add(proposalPersonBiography);
            }

        }
        if (!personAttachments.isEmpty()) {
            proposaldevelopmentDocument.getDevelopmentProposal().getPropPersonBios().removeAll(personAttachments);
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
    
    @SuppressWarnings("unchecked")
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

    protected ProposalDevelopmentDocument getProposalDoc(String pdDocumentNumber) throws Exception {
        ProposalDevelopmentDocument newCopy;
        DocumentService docService = KraServiceLocator.getService(DocumentService.class);
        newCopy = (ProposalDevelopmentDocument)docService.getByDocumentHeaderId(pdDocumentNumber);        
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
        ((ProposalDevelopmentForm) form).getSpecialReviewHelper().prepareView();
        return mapping.findForward(Constants.SPECIAL_REVIEW_PAGE);
    }
        
    public ActionForward permissions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward(Constants.PERMISSIONS_PAGE);
    }
    
    public ActionForward hierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm)form;
        pdForm.setHierarchyProposalSummaries(getHierarchyHelper().getHierarchyProposalSummaries(pdForm.getDocument().getDevelopmentProposal().getProposalNumber()));
        return mapping.findForward(Constants.HIERARCHY_PAGE);
    }
    
    public ActionForward grantsGov(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (!((ProposalDevelopmentForm) form).isGrantsGovEnabled()) {
            GlobalVariables.getMessageMap().putWarning(Constants.NO_FIELD, KeyConstants.ERROR_IF_GRANTS_GOV_IS_DISABLED);
        }
        return mapping.findForward(Constants.GRANTS_GOV_PAGE);
    }

    public ActionForward budgetVersions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        final ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        final String headerTabCall = getHeaderTabDispatch(request);
        if(StringUtils.isEmpty(headerTabCall)) {
            pdForm.getDocument().refreshPessimisticLocks();
        }        
        pdForm.setFinalBudgetVersion(getFinalBudgetVersion(pdForm.getDocument().getBudgetDocumentVersions()));
        setBudgetStatuses(pdForm.getDocument());
        
        final BudgetTDCValidator tdcValidator = new BudgetTDCValidator(request);
        tdcValidator.validateGeneratingWarnings(pdForm.getDocument());
        
        return mapping.findForward(Constants.PD_BUDGET_VERSIONS_PAGE);
    }

    @SuppressWarnings("unchecked")
    public ActionForward abstractsAttachments(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        // TODO temporarily to set up proposal person- remove this once keyperson is completed and htmlunit testing fine
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument doc = proposalDevelopmentForm.getDocument();
        doc.getDevelopmentProposal().populateNarrativeRightsForLoggedinUser();

        /*
         * Save the current set of narratives.  In some cases, a user can view the
         * narrative panel info, but is not allowed to change it.  We will make a
         * copy of the original narratives to use for comparison when a save occurs.
         * If a user attempted to change a narrative they were not authorized to,
         * then an error will be posted.
         */
        List<Narrative> narratives = (List<Narrative>) ObjectUtils.deepCopy((Serializable) doc.getDevelopmentProposal().getNarratives());
        proposalDevelopmentForm.setNarratives(narratives);
        KraServiceLocator.getService(ProposalPersonBiographyService.class).setPersonnelBioTimeStampUser(doc.getDevelopmentProposal().getPropPersonBios());
        List<Narrative> narrativeList = new ArrayList<Narrative> ();
        narrativeList.addAll(doc.getDevelopmentProposal().getNarratives());
        narrativeList.addAll(doc.getDevelopmentProposal().getInstituteAttachments());
        KraServiceLocator.getService(NarrativeService.class).setNarrativeTimeStampUser(narrativeList);
        KraServiceLocator.getService(ProposalAbstractsService.class).loadAbstractsUploadUserFullName(doc.getDevelopmentProposal().getProposalAbstracts());

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
            primaryKeys.put(KNSPropertyConstants.DOCUMENT_NUMBER, documentNumber);
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
            Collections.sort(customAttributeDocumentList, new LabelComparator());
           
        }

        ((ProposalDevelopmentForm)form).setCustomAttributeGroups(customAttributeGroups);

        return mapping.findForward(Constants.CUSTOM_ATTRIBUTES_PAGE);
    }
    
    /**
     * Sorts custom data attributes by label for alphabetical order on custom data panels.
     */
    public class LabelComparator implements Comparator
    {    
        public LabelComparator(){}
        
        public int compare(Object cad1, Object cad2 )
        {    
            try
            {
                String label1 = ((CustomAttributeDocument)cad1).getCustomAttribute().getLabel();
                String label2 = ((CustomAttributeDocument)cad2).getCustomAttribute().getLabel();
                if (label1 == null)
                {
                    label1 = "";
                }
                if (label2 == null)
                {
                    label2 = "";
                }
                return label1.compareTo(label2);  
            }
            catch (Exception e)
            {
                return 0;
            }
        }
    }

    public ActionForward actions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        if (proposalDevelopmentForm.getDocument().getDocumentNumber() == null) {
            // If entering this action from copy link on doc search
            loadDocumentInForm(request, proposalDevelopmentForm);
        }
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        ProposalDevelopmentPrintingService printService = KraServiceLocator.getService(ProposalDevelopmentPrintingService.class);
        printService.populateSponsorForms(proposalDevelopmentForm.getSponsorFormTemplates(), proposalDevelopmentDocument.getDevelopmentProposal().getSponsorCode());
        return mapping.findForward(Constants.PROPOSAL_ACTIONS_PAGE);
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
       ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
       if (proposalDevelopmentForm.getDocument().getDocumentNumber() == null) {
           // If entering this action from the medusa link on the search
           loadDocumentInForm(request, proposalDevelopmentForm);
       }
       ProposalDevelopmentDocument document = proposalDevelopmentForm.getDocument();
       String proposalNumber = document.getDevelopmentProposal().getProposalNumber();
       proposalDevelopmentForm.getMedusaBean().setMedusaViewRadio("0");
       proposalDevelopmentForm.getMedusaBean().setModuleName("DP");
       proposalDevelopmentForm.getMedusaBean().setModuleIdentifier(Long.valueOf(proposalNumber));
       return mapping.findForward(Constants.MAPPING_PROPOSAL_MEDUSA_PAGE);
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
        new AuditActionHelper().auditConditionally((ProposalDevelopmentForm) form);
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
    
    protected PersonEditableService getPersonEditableService() {
        return KraServiceLocator.getService(PersonEditableService.class);
    }   
    
    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#initialDocumentSave(org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase)
     */
    @Override
    protected void initialDocumentSave(KualiDocumentFormBase form) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument doc = pdForm.getDocument();
        initializeProposalUsers(doc);
        //on initialization of a new document the original lead unit will be blank
        //and so on first save we need to make sure to fix it
        if (pdForm.getCopyCriteria() != null) {
            pdForm.getCopyCriteria().setOriginalLeadUnitNumber(doc.getDevelopmentProposal().getOwnedByUnitNumber());
        }
    }
    
   /**
    * Create the original set of Proposal Users for a new Proposal Development Document.
    * The creator the proposal is assigned to the AGGREGATOR role.
    */
    protected void initializeProposalUsers(ProposalDevelopmentDocument doc) {
        
        // Assign the creator of the proposal to the AGGREGATOR role.
        
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        KraAuthorizationService kraAuthService = KraServiceLocator.getService(KraAuthorizationService.class);
        if (!kraAuthService.hasRole(userId, doc, RoleConstants.AGGREGATOR))
            kraAuthService.addRole(userId, RoleConstants.AGGREGATOR, doc);
        
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
    
    protected void loadDocumentInForm(HttpServletRequest request, ProposalDevelopmentForm proposalDevelopmentForm)
    throws WorkflowException {
        String docIdRequestParameter = request.getParameter(KNSConstants.PARAMETER_DOC_ID);
        ProposalDevelopmentDocument retrievedDocument = (ProposalDevelopmentDocument)KNSServiceLocator.getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
        proposalDevelopmentForm.setDocument(retrievedDocument);
        request.setAttribute(KNSConstants.PARAMETER_DOC_ID, docIdRequestParameter);
        
        // Set lead unit on form when copying a document. This is needed so the lead unit shows up on the "Copy to New Document" panel under Proposal Actions.
        ProposalCopyCriteria cCriteria = proposalDevelopmentForm.getCopyCriteria();
        if (cCriteria != null) {
            cCriteria.setOriginalLeadUnitNumber(retrievedDocument.getDevelopmentProposal().getOwnedByUnitNumber());
        }
    }
    
    /**
     * Overriding headerTab to customize how clearing tab state works on PDForm.
     */
    @Override
    public ActionForward headerTab(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((KualiForm) form).setTabStates(new HashMap<String, String>());
        return super.headerTab(mapping, form, request, response);
    }
    
    /**
     * 
     * This method is called to print forms
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward printForms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        super.save(mapping, form, request, response);
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        boolean grantsGovErrorExists = false;

        if(proposalDevelopmentDocument.getDevelopmentProposal().getSelectedS2sOppForms().isEmpty()){    // error, no form is selected
            GlobalVariables.getMessageMap().putError("noKey", ERROR_NO_GRANTS_GOV_FORM_SELECTED);
            return mapping.findForward(Constants.PROPOSAL_ACTIONS_PAGE);
        }
        AttachmentDataSource attachmentDataSource = KraServiceLocator.getService(S2SService.class).printForm(proposalDevelopmentDocument);
        if(attachmentDataSource==null || attachmentDataSource.getContent()==null || attachmentDataSource.getContent().length==0){
            //KRACOEUS-3300 - there should be GrantsGov audit errors in this case, grab them and display them as normal errors on
            //the GrantsGov forms tab so we don't need to turn on auditing
            Iterator<String> iter = GlobalVariables.getAuditErrorMap().keySet().iterator();
            while (iter.hasNext()) {
               String errorKey = (String) iter.next();
                AuditCluster auditCluster = (AuditCluster)GlobalVariables.getAuditErrorMap().get(errorKey);
                if(StringUtils.equalsIgnoreCase(auditCluster.getCategory(),Constants.GRANTSGOV_ERRORS)){
                    grantsGovErrorExists = true;
                    for (Object error : auditCluster.getAuditErrorList()) {
                        AuditError auditError = (AuditError)error;
                        GlobalVariables.getErrorMap().putError("grantsGovFormValidationErrors", auditError.getMessageKey(), auditError.getParams());
                    }
                }
            }
        }
        if(grantsGovErrorExists){
            GlobalVariables.getErrorMap().putError("grantsGovFormValidationErrors", KeyConstants.VALIDATTION_ERRORS_BEFORE_GRANTS_GOV_SUBMISSION);
            return mapping.findForward(Constants.GRANTS_GOV_PAGE);
        }
        if(attachmentDataSource==null || attachmentDataSource.getContent()==null){
            return mapping.findForward(Constants.MAPPING_PROPOSAL_ACTIONS);
        }
        ByteArrayOutputStream baos = null;
        try{
            baos = new ByteArrayOutputStream(attachmentDataSource.getContent().length);
            baos.write(attachmentDataSource.getContent());
            WebUtils.saveMimeOutputStreamAsFile(response, attachmentDataSource.getContentType(), baos, attachmentDataSource.getFileName());
        }finally{
            try{
                if(baos!=null){
                    baos.close();
                    baos = null;
                }
            }catch(IOException ioEx){
                LOG.warn(ioEx.getMessage(), ioEx);
            }
        }        
        return null;
    }
    
    
    /**
     * This method produces a list of strings containg the methodToCall to be registered for each of the 
     * ProposalColumnsToAlter lookup buttons that can be rendered on the Proposal Data Override tab. The execute method in this class
     * puts this list into the form.  The Proposal Data Override tag file then calls registerEditableProperty on each when rendering the tab.
     * 
     * @param proposalNumber The proposal number for which we are generating the list for.
     * @return Possible editable properties that can be called from the page.
     */
    public List<String> constructColumnsToAlterLookupMTCs(String proposalNumber) {
        Map<String,Object> filterMap = new HashMap<String,Object>();
        ProposalDevelopmentService proposalDevelopmentService = KraServiceLocator.getService(ProposalDevelopmentService.class);
        Collection<ProposalColumnsToAlter> proposalColumnsToAlterCollection = (KraServiceLocator.getService(BusinessObjectService.class).findMatching(ProposalColumnsToAlter.class, filterMap));
        
        List<String> mtcReturn = new ArrayList<String>();
        
        for( ProposalColumnsToAlter pcta : proposalColumnsToAlterCollection ) {
            if( pcta.getHasLookup() ) {
                Map<String, Object> primaryKeys = new HashMap<String, Object>();
                primaryKeys.put("columnName", pcta.getColumnName());
                Object fieldValue = proposalDevelopmentService.getProposalFieldValueFromDBColumnName(proposalNumber, pcta.getColumnName());
                String displayAttributeName = pcta.getLookupReturn();
                String displayLookupReturnValue = proposalDevelopmentService.getDataOverrideLookupDisplayReturnValue(pcta.getLookupClass());
                mtcReturn.add("methodToCall.performLookup.(!!"+pcta.getLookupClass()+"!!).((("+displayLookupReturnValue+":newProposalChangedData.changedValue,"+displayAttributeName+":newProposalChangedData.displayValue))).((``)).((<>)).(([])).((**)).((^^)).((&&)).((//)).((~~)).anchorProposalDataOverride");
            }
        }
        return mtcReturn;
    }

    /**
     * 
     * Handy method to stream the byte array to response object
     * @param attachmentDataSource
     * @param response
     * @throws Exception
     */
    @Override
    protected void streamToResponse(AttachmentDataSource attachmentDataSource, HttpServletResponse response) throws Exception {
        byte[] xbts = attachmentDataSource.getContent();
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream(xbts.length);
            baos.write(xbts);
            WebUtils.saveMimeOutputStreamAsFile(response, attachmentDataSource.getContentType(), baos, attachmentDataSource.getFileName());
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                    baos = null;
                }
            } catch (IOException ioEx) {
                LOG.warn(ioEx.getMessage(), ioEx);
            }
        }
    }
    
    /**
     * This method gets called upon navigation to Questionnaire tab.
     * @param mapping the Action Mapping
     * @param form the Action Form
     * @param request the Http Request
     * @param response Http Response
     * @return the Action Forward
     */
    public ActionForward questions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm)form;
        
        proposalDevelopmentForm.getQuestionnaireHelper().prepareView();
        proposalDevelopmentForm.getS2sQuestionnaireHelper().prepareView();
        //((ProposalDevelopmentForm)form).getQuestionnaireHelper().setSubmissionActionTypeCode(getSubmitActionType(request));
        if (CollectionUtils.isEmpty(proposalDevelopmentForm.getQuestionnaireHelper().getAnswerHeaders())) {
            proposalDevelopmentForm.getQuestionnaireHelper().populateAnswers();
        } else {
            //nothing to do in this case right now..
        }
        
        proposalDevelopmentForm.getS2sQuestionnaireHelper().populateAnswers();
        
        return mapping.findForward(Constants.QUESTIONS_PAGE);
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
     * Use the Kuali Rule Service to apply the rules for the given event.
     * @param event the event to process
     * @return true if success; false if there was a validation error
     */
    protected final boolean applyRules(KualiDocumentEvent event) {
        return getKualiRuleService().applyRules(event);
    }
    
    protected String getFormProperty(HttpServletRequest request,String methodToCall) {
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        String formProperty = "";
        if (StringUtils.isNotBlank(parameterName)) {
            formProperty = StringUtils.substringBetween(parameterName, "."+methodToCall, ".line");
        }
        return formProperty;
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


