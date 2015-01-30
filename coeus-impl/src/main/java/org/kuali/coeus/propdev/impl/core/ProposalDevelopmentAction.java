/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.core;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.framework.person.editable.PersonEditableService;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.propdev.impl.attachment.LegacyNarrativeService;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.propdev.impl.abstrct.ProposalAbstractsService;
import org.kuali.coeus.propdev.impl.budget.CostShareInfoDO;
import org.kuali.coeus.propdev.impl.copy.ProposalCopyCriteria;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentService.ProposalLockingRegion;
import org.kuali.coeus.propdev.impl.docperm.ProposalRoleTemplateService;
import org.kuali.coeus.propdev.impl.approve.ProposalDevelopmentApproverViewDO;
import org.kuali.coeus.propdev.impl.person.CoPiInfoDO;
import org.kuali.coeus.propdev.impl.person.KeyPersonnelService;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiographyService;
import org.kuali.coeus.propdev.impl.s2s.S2sSubmissionService;
import org.kuali.coeus.s2sgen.api.print.FormPrintResult;
import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.coeus.sys.framework.controller.NonCancellingRecallQuestion;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.coeus.common.framework.auth.KcTransactionalDocumentAuthorizerBase;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.core.BudgetParentActionBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.common.framework.krms.KrmsRulesExecutionService;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.common.budget.framework.print.BudgetPrintService;
import org.kuali.coeus.propdev.impl.budget.ProposalBudgetService;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarcyActionHelper;
import org.kuali.coeus.propdev.impl.person.question.ProposalPersonQuestionnaireHelper;
import org.kuali.coeus.propdev.impl.print.ProposalDevelopmentPrintingService;
import org.kuali.coeus.propdev.impl.s2s.S2sOppForms;
import org.kuali.coeus.propdev.impl.s2s.S2sOpportunity;
import org.kuali.coeus.propdev.impl.person.ProposalDevelopmentKeyPersonnelAction;
import org.kuali.coeus.s2sgen.api.generate.FormMappingInfo;
import org.kuali.coeus.s2sgen.api.generate.FormMappingService;
import org.kuali.coeus.s2sgen.api.print.FormPrintService;
import org.kuali.rice.core.api.util.RiceConstants;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.coreservice.framework.CoreFrameworkServiceLocator;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.DocumentHelperService;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.krad.rules.rule.event.DocumentEvent;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.KRADUtils;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;

public class ProposalDevelopmentAction extends BudgetParentActionBase {
    private static final String ERROR_NO_GRANTS_GOV_FORM_SELECTED = "error.proposalDevelopment.no.grants.gov.form.selected";
    private static final String PERSON_INDEX= "personIndex";
    private static final String COMMENTS= "comments";
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentAction.class);
    private ProposalHierarcyActionHelper hierarchyHelper;
    private KcNotificationService notificationService;
    private ProposalDevelopmentService proposalDevelopmentService;
    private ProposalDevelopmentPrintingService proposalDevelopmentPrintingService;
    private ProposalRoleTemplateService proposalRoleTemplateService;
    private DocumentHelperService documentHelperService;
    
	private KcDocumentRejectionService kcDocumentRejectionService;
    private KrmsRulesExecutionService krmsRulesExecutionService;

	private ProposalPersonBiographyService proposalPersonBiographyService;
    private LegacyNarrativeService narrativeService;
    private ProposalAbstractsService  proposalAbstractsService;
    private KeyPersonnelService keyPersonnelService;
    private PersonEditableService personEditableService;
    private FormPrintService printService;
    private S2sSubmissionService s2sSubmissionService;
    private BusinessObjectService businessObjectService;
       
    private BudgetPrintService budgetPrintService;
    private FormMappingService formMappingService;

    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;

        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        String command = proposalDevelopmentForm.getCommand();
        String createProposalFromGrantsGov=request.getParameter("createProposalFromGrantsGov");
        S2sOpportunity s2sOpportunity = new S2sOpportunity();
        if(createProposalFromGrantsGov!=null && createProposalFromGrantsGov.equals("true")){
            s2sOpportunity = proposalDevelopmentForm.getNewS2sOpportunity();
            proposalDevelopmentForm.getProposalDevelopmentDocument().getDevelopmentProposal().setS2sOpportunity(s2sOpportunity);
            proposalDevelopmentForm.setNewS2sOpportunity(new S2sOpportunity());
        }
        //KRACOEUS-5064
        if (proposalDevelopmentForm.getProposalDevelopmentDocument().getDocumentHeader().getDocumentNumber() == null && request.getParameter(KRADConstants.PARAMETER_DOC_ID) != null) {
            loadDocumentInForm(request, proposalDevelopmentForm);
        }
        if (KewApiConstants.ACTIONLIST_INLINE_COMMAND.equals(command)) {
            //forward = mapping.findForward(Constants.MAPPING_COPY_PROPOSAL_PAGE);
            //KRACOEUS-5064
            ProposalDevelopmentApproverViewDO approverViewDO = populateApproverViewDO(proposalDevelopmentForm);
            proposalDevelopmentForm.setApproverViewDO(approverViewDO);           
            forward = mapping.findForward(Constants.MAPPING_PROPOSAL_SUMMARY_PAGE);
            forward = new ActionForward(forward.getPath()+ "?" + KRADConstants.PARAMETER_DOC_ID + "=" + request.getParameter(KRADConstants.PARAMETER_DOC_ID));  
        } else {
            if (proposalDevelopmentForm.getDocTypeName() == null || proposalDevelopmentForm.getDocTypeName().equals("")) {
                proposalDevelopmentForm.setDocTypeName("ProposalDevelopmentDocument");
            }
            boolean rejectedDocument = false;
            if(proposalDevelopmentForm.getDocument().getDocumentNumber() != null){
                rejectedDocument = getKcDocumentRejectionService().isDocumentOnInitialNode(proposalDevelopmentForm.getDocument().getDocumentHeader().getWorkflowDocument());
            }

            if (canPerformWorkflowAction(proposalDevelopmentForm.getProposalDevelopmentDocument()) && !rejectedDocument) {

                ProposalDevelopmentApproverViewDO approverViewDO = populateApproverViewDO(proposalDevelopmentForm);
                proposalDevelopmentForm.setApproverViewDO(approverViewDO);
                
                loadDocument(proposalDevelopmentForm);
                return approverView(mapping, form, request, response);
            }
            else if (Constants.MAPPING_PROPOSAL_ACTIONS.equals(command)) {
                loadDocument(proposalDevelopmentForm);
                forward = actions(mapping, proposalDevelopmentForm, request, response);
            }
            else {
            forward = super.docHandler(mapping, form, request, response);
        }
        }
        
        if (proposalDevelopmentForm.getProposalDevelopmentDocument().isProposalDeleted()) {
            return mapping.findForward("deleted");
        }

        if (KewApiConstants.INITIATE_COMMAND.equals(proposalDevelopmentForm.getCommand())) {
            proposalDevelopmentForm.getProposalDevelopmentDocument().initialize();
        } else {
            proposalDevelopmentForm.initialize();
        }
        proposalDevelopmentForm.setProposalDevelopmentParameters(getProposalDevelopmentService().getProposalDevelopmentParameters());
        
        if (Constants.MAPPING_PROPOSAL_ACTIONS.equals(command)) {
            forward = actions(mapping, proposalDevelopmentForm, request, response);
        }
               
        if(createProposalFromGrantsGov!=null && createProposalFromGrantsGov.equals("true") && s2sOpportunity!=null){
            getS2sSubmissionService().createS2sOpportunityDetails(proposalDevelopmentForm.getProposalDevelopmentDocument().getDevelopmentProposal(),
                    s2sOpportunity, proposalDevelopmentForm.getVersionNumberForS2sOpportunity());
        }

        proposalDevelopmentForm.setSaveXmlPermission(getProposalDevelopmentService().canSaveProposalXml(proposalDevelopmentForm.getProposalDevelopmentDocument()));
        
        if(proposalDevelopmentForm.getProposalDevelopmentDocument().getDevelopmentProposal().getS2sOpportunity()!= null){
            proposalDevelopmentForm.setGrantsGovSelectFlag(true);
        }
        return forward;
    }
    
   protected ProposalHierarcyActionHelper getHierarchyHelper() {
        if (hierarchyHelper == null) {
            hierarchyHelper = new ProposalHierarcyActionHelper();
        }
        return hierarchyHelper;
    }
    
   protected ProposalDevelopmentApproverViewDO populateApproverViewDO (ProposalDevelopmentForm proposalDevelopmentForm) {

       ProposalDevelopmentApproverViewDO approverViewDO = new ProposalDevelopmentApproverViewDO();
       DevelopmentProposal proposal = proposalDevelopmentForm.getProposalDevelopmentDocument().getDevelopmentProposal();
       Budget budget = getProposalDevelopmentService().getFinalBudget(proposal);

       int numberOfCostShare = 0;
       int numberOfCoPI = 0;

       /* populate PI info */
       List<CoPiInfoDO> coPiInfos = new ArrayList<CoPiInfoDO>();
       coPiInfos = getProposalDevelopmentService().getCoPiPiInfo(proposal);
       approverViewDO.setCoPiInfos(coPiInfos);
       if (coPiInfos != null) {
           numberOfCoPI = coPiInfos.size();
       }

       /* populate cost share info */
       List<CostShareInfoDO> costShareInfos = new ArrayList<CostShareInfoDO>();
       if (budget != null) {
           costShareInfos = getProposalDevelopmentService().getCostShareInfo(budget);
           approverViewDO.setCostShareInfos(costShareInfos);
           if (costShareInfos != null) {
               numberOfCostShare = costShareInfos.size();
           }
       }

       /* populate budget cost info */
       if (budget != null) {
           approverViewDO.setDirectCost(budget.getTotalDirectCost());
           approverViewDO.setIndirectCost(budget.getTotalIndirectCost());
           approverViewDO.setTotalCost(budget.getTotalCost());
       }

       /* Fill the gap between CoPI number and Cost Share Number for JSP rendering purpose */
       if (numberOfCoPI > numberOfCostShare) {
           for (int i=0; i < numberOfCoPI - numberOfCostShare; i++) {
               CostShareInfoDO costShareInfo = new CostShareInfoDO();
               costShareInfos.add(costShareInfo);
           }
       } else if (numberOfCoPI < numberOfCostShare) {
           for (int i=0; i < numberOfCostShare - numberOfCoPI; i++) {
               CoPiInfoDO coPiInfo = new CoPiInfoDO();
               coPiInfos.add(coPiInfo);
           }
       }

       /* populate proposal info */
       approverViewDO.setActivityType(proposal.getActivityType().getDescription());
       approverViewDO.setDueDate(proposal.getDeadlineDate());
       approverViewDO.setStartDate(proposal.getRequestedStartDateInitial());
       approverViewDO.setEndDate(proposal.getRequestedEndDateInitial());
       approverViewDO.setProjectTitle(proposal.getTitle());
       approverViewDO.setLeadUnit(proposal.getOwnedByUnitNumber());
       approverViewDO.setProposalNumber(proposal.getProposalNumber());
       approverViewDO.setProposalType(proposal.getProposalType().getDescription());
       approverViewDO.setSponsorName(proposal.getSponsorName());
       approverViewDO.setPiName(proposal.getPrincipalInvestigatorName());

       return approverViewDO;
   }
   
   
   /* Check to see if the current user can perform workflow action in proposal development document */
   protected boolean canPerformWorkflowAction(ProposalDevelopmentDocument document) {

       // Not from the doc handler, don't show the approver view
       if (document.getDocumentHeader().getDocumentNumber() == null) {
           return false;
       }

       KcTransactionalDocumentAuthorizerBase documentAuthorizer = 
    		   (KcTransactionalDocumentAuthorizerBase) getDocumentHelperService().getDocumentAuthorizer(document);
       
       Person user = GlobalVariables.getUserSession().getPerson();
       Set<String> documentActions = documentAuthorizer.getDocumentActions(document, user, null);

       boolean canApprove= documentActions.contains(KRADConstants.KUALI_ACTION_CAN_APPROVE);
       boolean canDisapprove = documentActions.contains(KRADConstants.KUALI_ACTION_CAN_DISAPPROVE);

       return canApprove || canDisapprove;
   }

   protected DocumentHelperService getDocumentHelperService()  {  
       if (documentHelperService == null) 
    	   documentHelperService =  KNSServiceLocator.getDocumentHelperService();
       return documentHelperService;
   }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    
        ActionForward actionForward = super.execute(mapping, form, request, response);
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = proposalDevelopmentForm.getProposalDevelopmentDocument();
        String keywordPanelDisplay = this.getParameterService().getParameterValueAsString(
                    ProposalDevelopmentDocument.class, Constants.KEYWORD_PANEL_DISPLAY);        
        request.getSession().setAttribute(Constants.KEYWORD_PANEL_DISPLAY, keywordPanelDisplay);

        if (proposalDevelopmentForm.isAuditActivated()){
            proposalDevelopmentForm.setUnitRulesMessages(getUnitRulesMessages(proposalDevelopmentForm.getProposalDevelopmentDocument()));
        }
        if( GlobalVariables.getAuditErrorMap().isEmpty()) {
            KcServiceLocator.getService(AuditHelper.class).auditConditionally(proposalDevelopmentForm);
        }
        getProposalDevelopmentService().sortS2sForms(document.getDevelopmentProposal());    
                
        if(document.getDevelopmentProposal().getS2sOpportunity()!=null && document.getDevelopmentProposal().getS2sOpportunity().getS2sOppForms()!=null){
            Collections.sort(document.getDevelopmentProposal().getS2sOpportunity().getS2sOppForms(),new S2sOppFormsComparator1());
            Collections.sort(document.getDevelopmentProposal().getS2sOpportunity().getS2sOppForms(),new S2sOppFormsComparator3());
        }
        return actionForward;
    }
    
    protected KrmsRulesExecutionService getKrmsRulesExecutionService(){
    	if (krmsRulesExecutionService == null)
    		krmsRulesExecutionService = KcServiceLocator.getService(KrmsRulesExecutionService.class);
    		return krmsRulesExecutionService;
    }
    
    protected BudgetPrintService getBudgetPrintService(){
    	if (budgetPrintService == null)
    		budgetPrintService = KcServiceLocator.getService(BudgetPrintService.class);
    		return budgetPrintService;
    }

    protected KcDocumentRejectionService getKcDocumentRejectionService() {
    	if (kcDocumentRejectionService == null)
    		kcDocumentRejectionService = KcServiceLocator.getService(KcDocumentRejectionService.class);
    	return kcDocumentRejectionService;
	}

	protected BusinessObjectService getBusinessObjectService() {
		if (businessObjectService == null)
			businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
		return businessObjectService;
	}


    protected List<String> getUnitRulesMessages(ProposalDevelopmentDocument pdDoc) {
        return getKrmsRulesExecutionService().processUnitValidations(pdDoc.getDevelopmentProposal().getAllUnitNumbers(), pdDoc);

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

    @Override
    protected void loadDocument(KualiDocumentFormBase kualiDocumentFormBase) throws WorkflowException {
        super.loadDocument(kualiDocumentFormBase);
        ProposalDevelopmentDocument document = ((ProposalDevelopmentForm)kualiDocumentFormBase).getProposalDevelopmentDocument();
        getProposalDevelopmentService().loadDocument(document);
    }

    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // We will need to determine if the proposal is being saved for the first time.

        final ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        final ProposalDevelopmentDocument doc = proposalDevelopmentForm.getProposalDevelopmentDocument();
        //if the proposal hasn't been saved yet, the s2sopp proposal number will be null. We need to save it in the form until we
        //have a proposal number to set due to OJBs difficulty in dealing with 1-to-1 relationships.
        DevelopmentProposal developmentProposal = doc.getDevelopmentProposal();
		S2sOpportunity s2sOpportunity = developmentProposal.getS2sOpportunity();
        if(s2sOpportunity!=null && s2sOpportunity.getProposalNumber()==null){
            developmentProposal.setS2sOpportunity(null);
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
        if(s2sOpportunity!=null) {
        	developmentProposal.setS2sOpportunity(s2sOpportunity);
            s2sOpportunity.setDevelopmentProposal(doc.getDevelopmentProposal());
            getBusinessObjectService().save(s2sOpportunity);
            proposalDevelopmentForm.setS2sOpportunity(null);
        }
        
        developmentProposal.updateProposalNumbers();

        List<ProposalDevelopmentBudgetExt> budgets = developmentProposal.getBudgets();
        if (budgets != null && !budgets.isEmpty()) {
            for (Budget budget : budgets) {
                budget.setStartDate(developmentProposal.getRequestedStartDateInitial());
                budget.setEndDate(developmentProposal.getRequestedEndDateInitial());
                this.getBusinessObjectService().save(budget);
            }
        }

        return forward;
    }
    
    protected void updateProposalDocument(ProposalDevelopmentForm proposalDevelopmentForm) throws Exception {
        ProposalLockingRegion region = ProposalLockingRegion.GENERAL;
        if (proposalDevelopmentForm.getActionName().equalsIgnoreCase("ProposalDevelopmentBudgetVersionsAction")) {
            region = ProposalLockingRegion.BUDGET;
        } else if (proposalDevelopmentForm.getActionName().equalsIgnoreCase("ProposalDevelopmentAbstractsAttachmentsAction" )) {
            region = ProposalLockingRegion.ATTACHMENTS;
        }
        proposalDevelopmentForm.setDocument(getProposalDevelopmentService().updateProposalDocument(proposalDevelopmentForm.getProposalDevelopmentDocument(), region));        
    }

    /**
     * @see org.kuali.coeus.sys.framework.controller.KcTransactionalDocumentActionBase#saveOnClose(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected ActionForward saveOnClose(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.saveOnClose(mapping, form, request, response);
        
        final ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        final ProposalDevelopmentDocument doc = proposalDevelopmentForm.getProposalDevelopmentDocument();
       
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
        getKeyPersonnelService().populateDocument(pdform.getProposalDevelopmentDocument());
              
        // Let this be taken care of in KeyPersonnelAction execute() method
        if (this instanceof ProposalDevelopmentKeyPersonnelAction) {
            LOG.info("forwarding to keyPersonnel action");
            return mapping.findForward(Constants.KEY_PERSONNEL_PAGE);
        }

        new ProposalDevelopmentKeyPersonnelAction().prepare(form, request);

        return mapping.findForward(Constants.KEY_PERSONNEL_PAGE);
    }

    public ActionForward specialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        proposalDevelopmentForm.getProposalDevelopmentDocument().getDevelopmentProposal().refreshReferenceObject("propSpecialReviews");
        ((ProposalDevelopmentForm) form).getSpecialReviewHelper().prepareView();
        return mapping.findForward(Constants.SPECIAL_REVIEW_PAGE);
    }
        
    public ActionForward permissions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward(Constants.PERMISSIONS_PAGE);
    }

    /**
     * method for setting the proposal Summary person certification Details.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getProposalPersonCertification(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
       ProposalDevelopmentDocument document = pdform.getProposalDevelopmentDocument();
       String personIndex = request.getParameter(PERSON_INDEX);
       request.setAttribute(PERSON_INDEX, personIndex);
       return mapping.findForward(Constants.PERSON_CERTIFICATE); 
    }
    
    
    /**
     * method for setting the proposal Summary key person comment Details.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getProposalComment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = proposalDevelopmentForm.getProposalDevelopmentDocument();
        String personIndex = request.getParameter(PERSON_INDEX);
        String comments = request.getParameter(COMMENTS);
        request.setAttribute(COMMENTS, comments);
        request.setAttribute(PERSON_INDEX, personIndex);
        return mapping.findForward(Constants.PERSON_COMMENT); 
    }
    
    
    public ActionForward hierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm)form;
        pdForm.setHierarchyProposalSummaries(getHierarchyHelper().getHierarchyProposalSummaries(pdForm.getProposalDevelopmentDocument().getDevelopmentProposal().getProposalNumber()));
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
        
        return mapping.findForward(Constants.PD_BUDGET_VERSIONS_PAGE);
    }

    @SuppressWarnings("unchecked")
    public ActionForward abstractsAttachments(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument doc = proposalDevelopmentForm.getProposalDevelopmentDocument();
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
       getProposalPersonBiographyService().setPersonnelBioTimeStampUser(doc.getDevelopmentProposal().getPropPersonBios());
       getNarrativeService().setNarrativeTimeStampUser(doc.getDevelopmentProposal());
       getProposalAbstractsService().loadAbstractsUploadUserFullName(doc.getDevelopmentProposal().getProposalAbstracts());

        return mapping.findForward(Constants.ATTACHMENTS_PAGE);
    }

    public ActionForward customData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        SortedMap<String, List<CustomAttributeDocument>> customAttributeGroups = new TreeMap<String, List<CustomAttributeDocument>>();

        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument doc = proposalDevelopmentForm.getProposalDevelopmentDocument();
        proposalDevelopmentForm.getCustomDataHelper().prepareCustomData();
        return mapping.findForward(Constants.CUSTOM_ATTRIBUTES_PAGE);
    }

    public ActionForward actions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        if (proposalDevelopmentForm.getProposalDevelopmentDocument().getDocumentNumber() == null) {
            // If entering this action from copy link on doc search
            loadDocumentInForm(request, proposalDevelopmentForm);
        }
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();
        getProposalDevelopmentPrintingService ().populateSponsorForms(proposalDevelopmentForm.getSponsorFormTemplates(), proposalDevelopmentDocument.getDevelopmentProposal().getSponsorCode());
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
       if (proposalDevelopmentForm.getProposalDevelopmentDocument().getDocumentNumber() == null) {
           // If entering this action from the medusa link on the search
           loadDocumentInForm(request, proposalDevelopmentForm);
       }
       ProposalDevelopmentDocument document = proposalDevelopmentForm.getProposalDevelopmentDocument();
       String proposalNumber = document.getDevelopmentProposal().getProposalNumber();
       proposalDevelopmentForm.getMedusaBean().init("DP", Long.valueOf(proposalNumber));
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
        KcServiceLocator.getService(AuditHelper.class).auditConditionally((ProposalDevelopmentForm) form);
        return mapping.findForward("auditMode");
    }
    
    /**
     * Grabs the <code>{@link KeyPersonnelService} from Spring!
     * 
     * @return KeyPersonnelService
     */
    protected KeyPersonnelService getKeyPersonnelService() {
    	if (keyPersonnelService == null)
    		keyPersonnelService = KcServiceLocator.getService(KeyPersonnelService.class);
        return keyPersonnelService;
    }   
    
    protected PersonEditableService getPersonEditableService() {
        if (personEditableService == null)
        personEditableService = KcServiceLocator.getService(PersonEditableService.class);
        return personEditableService;
    }   
    
    protected FormPrintService getPrintService(){
        if (printService == null)
        	printService = KcServiceLocator.getService(FormPrintService.class);
        return printService;
    }   
    
    protected S2sSubmissionService getS2sSubmissionService(){
        if (s2sSubmissionService == null)
            s2sSubmissionService =KcServiceLocator.getService(S2sSubmissionService.class);
        return s2sSubmissionService;
    }   
   
    protected ProposalPersonBiographyService getProposalPersonBiographyService() {
		if (proposalPersonBiographyService ==null)
			proposalPersonBiographyService = KcServiceLocator.getService(ProposalPersonBiographyService.class);
    	return proposalPersonBiographyService;
	}

	protected LegacyNarrativeService getNarrativeService() {
		if (narrativeService==null)
			narrativeService = KcServiceLocator.getService(LegacyNarrativeService.class);
		return narrativeService;
	}

	protected ProposalAbstractsService getProposalAbstractsService() {
		if (proposalAbstractsService==null)
			proposalAbstractsService = KcServiceLocator.getService(ProposalAbstractsService.class);
			return proposalAbstractsService;
	}

    
    @Override
    protected void initialDocumentSave(KualiDocumentFormBase form) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument doc = pdForm.getProposalDevelopmentDocument();
        getProposalRoleTemplateService().initializeProposalUsers(doc);
        //on initialization of a new document the original lead unit will be blank
        //and so on first save we need to make sure to fix it
        if (pdForm.getCopyCriteria() != null) {
            pdForm.getCopyCriteria().setOriginalLeadUnitNumber(doc.getDevelopmentProposal().getOwnedByUnitNumber());
        }
    }
    
    protected void loadDocumentInForm(HttpServletRequest request, ProposalDevelopmentForm proposalDevelopmentForm)
    throws WorkflowException {
        String docIdRequestParameter = request.getParameter(KRADConstants.PARAMETER_DOC_ID);
        ProposalDevelopmentDocument retrievedDocument = (ProposalDevelopmentDocument)KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
        proposalDevelopmentForm.setDocument(retrievedDocument);
        proposalDevelopmentForm.setDocTypeName(retrievedDocument.getDocumentHeader().getWorkflowDocument().getDocumentTypeName());
        request.setAttribute(KRADConstants.PARAMETER_DOC_ID, docIdRequestParameter);
        
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
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();
        boolean grantsGovErrorExists = false;

        if(proposalDevelopmentDocument.getDevelopmentProposal().getSelectedS2sOppForms().isEmpty()){    // error, no form is selected
            GlobalVariables.getMessageMap().putError("noKey", ERROR_NO_GRANTS_GOV_FORM_SELECTED);
            return mapping.findForward(Constants.PROPOSAL_ACTIONS_PAGE);
        }
        FormPrintResult result = getPrintService().printForm(proposalDevelopmentDocument);
        setValidationErrorMessage(result.getErrors());
        KcFile attachmentDataSource = result.getFile();
        if(attachmentDataSource==null || attachmentDataSource.getData()==null || attachmentDataSource.getData().length==0){
            //KRACOEUS-3300 - there should be GrantsGov audit errors in this case, grab them and display them as normal errors on
            //the GrantsGov forms tab so we don't need to turn on auditing
            grantsGovErrorExists = ErrorUtils.copyAuditErrorsToPage(Constants.GRANTSGOV_ERRORS, "grantsGovFormValidationErrors");
        }
        if(grantsGovErrorExists){
            GlobalVariables.getMessageMap().putError("grantsGovFormValidationErrors", KeyConstants.VALIDATTION_ERRORS_BEFORE_GRANTS_GOV_SUBMISSION);
            return mapping.findForward(Constants.GRANTS_GOV_PAGE);
        }
        if (proposalDevelopmentDocument.getDevelopmentProposal().getGrantsGovSelectFlag()) {
            File grantsGovXmlDirectoryFile = getS2sSubmissionService().getGrantsGovSavedFile(proposalDevelopmentDocument);
            byte[] bytes = new byte[(int) grantsGovXmlDirectoryFile.length()];
            FileInputStream fileInputStream = new FileInputStream(grantsGovXmlDirectoryFile);
            fileInputStream.read(bytes);
            ByteArrayOutputStream baos = null;
            try {
                baos = new ByteArrayOutputStream(bytes.length);
                baos.write(bytes);
                WebUtils.saveMimeOutputStreamAsFile(response, "binary/octet-stream", baos, grantsGovXmlDirectoryFile.getName() + ".zip");
            }
            finally {
                try {
                    if (baos != null) {
                        baos.close();
                        baos = null;
                    }
                }
                catch (IOException ioEx) {
                    LOG.warn(ioEx.getMessage(), ioEx);
                }
            }
            proposalDevelopmentDocument.getDevelopmentProposal().setGrantsGovSelectFlag(false);
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        if(attachmentDataSource==null || attachmentDataSource.getData()==null){
            return mapping.findForward(Constants.MAPPING_PROPOSAL_ACTIONS);
        }
        streamToResponse(attachmentDataSource, response);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     *
     * This method is to put validation errors on UI
     *
     * @param errors
     *            List of validation errors which has to be displayed on UI.
     */

    protected void setValidationErrorMessage(List<org.kuali.coeus.s2sgen.api.core.AuditError> errors) {
        if (errors != null) {
            LOG.info("Error list size:" + errors.size() + errors.toString());
            List<org.kuali.rice.krad.util.AuditError> auditErrors = new ArrayList<>();
            for (org.kuali.coeus.s2sgen.api.core.AuditError error : errors) {
                auditErrors.add(new org.kuali.rice.krad.util.AuditError(error.getErrorKey(),
                        Constants.GRANTS_GOV_GENERIC_ERROR_KEY, error.getLink(),
                        new String[]{error.getMessageKey()}));
            }
            if (!auditErrors.isEmpty()) {
                GlobalVariables.getAuditErrorMap().put(
                        "grantsGovAuditErrors",
                        new AuditCluster(Constants.GRANTS_GOV_OPPORTUNITY_PANEL,
                                auditErrors, Constants.GRANTSGOV_ERRORS)
                );
            }
        }
    }

    /**
     * 
     * Handy method to stream the byte array to response object
     * @param attachmentDataSource
     * @param response
     * @throws Exception
     */
    @Override
    protected void streamToResponse(KcFile attachmentDataSource, HttpServletResponse response) throws Exception {
        byte[] xbts = attachmentDataSource.getData();
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream(xbts.length);
            baos.write(xbts);
            WebUtils.saveMimeOutputStreamAsFile(response, attachmentDataSource.getType(), baos, attachmentDataSource.getName());
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
        
        proposalDevelopmentForm.getQuestionnaireHelper().populateAnswers();        
        proposalDevelopmentForm.getS2sQuestionnaireHelper().populateAnswers();
        
        return mapping.findForward(Constants.QUESTIONS_PAGE);
    }
    
    /**
     * Action called to forward to approverView tab.
     * 
     * @param mapping 
     * @param form
     * @param request
     * @param response
     * @return ActionForward instance for forwarding to the tab.
     */
    public ActionForward approverView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{

        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = pdform.getProposalDevelopmentDocument();   
        getKeyPersonnelService().populateDocument(pdform.getProposalDevelopmentDocument());
        Budget budget = getProposalBudgetService().getFinalBudgetVersion(document);
        if(budget != null) {
            final Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("budgetId", budget.getBudgetId()); 
            List<BudgetPeriod> budgetPeriods = (List<BudgetPeriod>) getBusinessObjectService().findMatching(BudgetPeriod.class, fieldValues);
            budget.setBudgetPeriods(budgetPeriods);
            Collection<BudgetRate> rates = businessObjectService.findMatching(BudgetRate.class, fieldValues);   
            if(!CollectionUtils.isEmpty(rates)) {
                List<RateClassType> rateClassTypes =   (List<RateClassType>) getBusinessObjectService().findAll(RateClassType.class);
                budget.setRateClassTypes(rateClassTypes);
                pdform.setBudgetToSummarize(budget);
            }
            pdform.setBudgetToSummarize(budget);  
            if(budget.getBudgetPrintForms().isEmpty()){
                getBudgetPrintService().populateBudgetPrintForms(budget);
            }
        }
        getProposalDevelopmentPrintingService().populateSponsorForms(pdform.getSponsorFormTemplates(), document.getDevelopmentProposal().getSponsorCode());
        if (CollectionUtils.isEmpty(pdform.getQuestionnaireHelper().getAnswerHeaders())) {
            pdform.getQuestionnaireHelper().populateAnswers();
        } 
        List<ProposalPersonQuestionnaireHelper> proposalPersonQuestionnaireHelpers  = new ArrayList<ProposalPersonQuestionnaireHelper>();
        for (ProposalPerson person : document.getDevelopmentProposal().getProposalPersons()) {
            ProposalPersonQuestionnaireHelper helper = new ProposalPersonQuestionnaireHelper(person);
            proposalPersonQuestionnaireHelpers.add(helper);
        }
        pdform.setProposalPersonQuestionnaireHelpers(proposalPersonQuestionnaireHelpers);

        pdform.getSpecialReviewHelper().populatePropSpecialReviewApproverView(pdform.getProposalDevelopmentParameters().get(ProposalDevelopmentService.SUMMARY_SPECIAL_REVIEW_LIST).getValue());
        return mapping.findForward(Constants.MAPPING_PROPOSAL_APPROVER_PAGE);
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
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        if (pdForm.isHidePropDevDocDescriptionPanel()) {
            pdForm.getProposalDevelopmentDocument().setDefaultDocumentDescription();
        }
    }
    
    
    /**
     * Use the Kuali Rule Service to apply the rules for the given event.
     * @param event the event to process
     * @return true if success; false if there was a validation error
     */
    protected final boolean applyRules(DocumentEvent event) {
        return getKualiRuleService().applyRules(event);
    }
    
    protected String getFormProperty(HttpServletRequest request,String methodToCall) {
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        String formProperty = "";
        if (StringUtils.isNotBlank(parameterName)) {
            formProperty = StringUtils.substringBetween(parameterName, "."+methodToCall, ".line");
        }
        return formProperty;
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
    
    /**
     * This method print questionnaire answers.
     * 
     * @param mapping the Action Mapping
     * @param form the Action Form
     * @param request the Http Request
     * @param response Http Response
	 * @return the Action Forward
     * @throws Exception
     */
    public ActionForward printQuestionnaireAnswer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {        
       
        ActionForward forward = mapping.findForward(MAPPING_BASIC);
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = pdform.getProposalDevelopmentDocument();
        
        final int personIndex = this.getSelectedLine(request);
        ProposalPerson person = document.getDevelopmentProposal().getProposalPerson(personIndex);
        AttachmentDataSource dataStream =
                getProposalDevelopmentPrintingService().printPersonCertificationQuestionnaire(Lists.newArrayList(person));
        if (dataStream.getData() != null) {
            streamToResponse(dataStream, response);
            forward = null;
        }
        
        return forward;
    }    
    
    /**
     * This method print all questionnaire answers.
     * 
     * @param mapping the Action Mapping
     * @param form the Action Form
     * @param request the Http Request
     * @param response Http Response
     * @return the Action Forward
     * @throws Exception
     */        
    public ActionForward printAllQuestionnaireAnswer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = mapping.findForward(MAPPING_BASIC);
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        AttachmentDataSource dataStream = 
                getProposalDevelopmentPrintingService().printPersonCertificationQuestionnaire(pdForm.getProposalDevelopmentDocument().getDevelopmentProposal().getProposalPersons());

        if (dataStream.getData() != null) {
            streamToResponse(dataStream, response);
            forward = null;
        }
        
        return forward;
    }
   
   /**
    * 
    * @return
    */
   public ProposalBudgetService getProposalBudgetService() {
       return KcServiceLocator.getService(ProposalBudgetService.class);
   }

    protected ProposalDevelopmentService getProposalDevelopmentService() {
        if (proposalDevelopmentService == null) {
            proposalDevelopmentService = KcServiceLocator.getService(ProposalDevelopmentService.class);
        }
        return proposalDevelopmentService;
    }
    
    public void setProposalDevelopmentService(ProposalDevelopmentService proposalDevelopmentService) {
        this.proposalDevelopmentService = proposalDevelopmentService;
    }

    protected ProposalRoleTemplateService getProposalRoleTemplateService() {
        if (proposalRoleTemplateService == null) {
            proposalRoleTemplateService = KcServiceLocator.getService(ProposalRoleTemplateService.class);
        }
        return proposalRoleTemplateService;
    }

    public void setProposalRoleTemplateService(ProposalRoleTemplateService proposalRoleTemplateService) {
        this.proposalRoleTemplateService = proposalRoleTemplateService;
    }
       
    class S2sOppFormsComparator1 implements Comparator<S2sOppForms> {
	    public int compare(S2sOppForms s2sOppForms1, S2sOppForms s2sOppForms2) {
	        if (s2sOppForms2.getAvailable() && s2sOppForms1.getAvailable()) {
	            return 1;
	        }
	        return  s2sOppForms2.getAvailable().compareTo(s2sOppForms1.getAvailable());
	    }
    }
    
    class S2sOppFormsComparator3 implements Comparator<S2sOppForms> {
	    public int compare(S2sOppForms s2sOppForms1, S2sOppForms s2sOppForms2) {
	    	FormMappingInfo info1 = getFormMappingService().getFormInfo(s2sOppForms1.getS2sOppFormsId().getOppNameSpace());
	    	FormMappingInfo info2 = getFormMappingService().getFormInfo(s2sOppForms2.getS2sOppFormsId().getOppNameSpace());
	        if(info1 != null && info2 != null) {
	            Integer sortIndex1 = info1.getSortIndex();               
	            Integer sortIndex2 = info2.getSortIndex();  
	            return  sortIndex1.compareTo(sortIndex2);
	        }
	        return 1;
	    }    
	}

    protected FormMappingService getFormMappingService() {
        if (formMappingService == null)
            formMappingService = KcServiceLocator.getService(FormMappingService.class);
        return formMappingService;
    }

    protected ProposalDevelopmentPrintingService getProposalDevelopmentPrintingService() {
        if (proposalDevelopmentPrintingService == null)
        	proposalDevelopmentPrintingService = KcServiceLocator.getService(ProposalDevelopmentPrintingService.class);
        return proposalDevelopmentPrintingService;
    }


    @SuppressWarnings("deprecation")
    private static class ErrorUtils {

        /** private ctor. */
        private ErrorUtils() {
            throw new UnsupportedOperationException("do not call");
        }

        /**
         * Copies audit errors from audit error map to message map. Arguments are optional and can both be null.
         * @param auditClusterCategory optional cluster category string if errors should be restricted. If null all audit clusters are included
         * @param errorkey optional errorkey to use instead of the one included in the audit error. This is to display the errors even if the property
         * is not included in the current page
         * @return true if any audit clusters are processed.
         */
        public static boolean copyAuditErrorsToPage(String auditClusterCategory, String errorkey) {
            boolean auditClusterFound = false;
            Iterator<String> iter = GlobalVariables.getAuditErrorMap().keySet().iterator();
            while (iter.hasNext()) {
               String errorKey = (String) iter.next();
                AuditCluster auditCluster = (AuditCluster)GlobalVariables.getAuditErrorMap().get(errorKey);
                if(auditClusterCategory == null || StringUtils.equalsIgnoreCase(auditCluster.getCategory(), auditClusterCategory)){
                    auditClusterFound = true;
                    for (Object error : auditCluster.getAuditErrorList()) {
                        AuditError auditError = (AuditError)error;
                        GlobalVariables.getMessageMap().putError(errorKey == null ? auditError.getErrorKey() : errorKey,
                                auditError.getMessageKey(), auditError.getParams());
                    }
                }
            }
            return auditClusterFound;
        }
    }
}
