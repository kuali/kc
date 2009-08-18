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

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetService;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.rates.BudgetProposalRate;
import org.kuali.kra.budget.rates.BudgetRatesService;
import org.kuali.kra.budget.rates.RateClass;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.budget.web.struts.action.BudgetTDCValidator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.logging.BufferedLogger;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.question.CopyPeriodsQuestion;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.bo.DocumentHeader;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

/**
 * Struts Action class for the Proposal Development Budget Versions page
 */
public class ProposalDevelopmentBudgetVersionsAction extends ProposalDevelopmentAction {
    private static final String TOGGLE_TAB = "toggleTab";
    private static final String CONFIRM_SYNCH_BUDGET_RATE = "confirmSynchBudgetRate";
    private static final String NO_SYNCH_BUDGET_RATE = "noSynchBudgetRate";

    /**
     * Main execute method that is run. Populates A map of rate types in the {@link HttpServletRequest} instance to be used
     * in the JSP. The map is called <code>rateClassMap</code> this is set everytime execute is called in this class. This should only
     * happen for the BudgetVersions tab. This ensures that even if {@link RateClass} persisted data may change, it will update the map
     * correctly.
     * 
     * @param mapping {@link ActionMapping}
     * @param form {@link ActionForm} instance
     * @param request {@link HttpServletRequest} instance
     * @param response {@link HttpServletResponse} instance 
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("rateClassMap", getBudgetRatesService().getBudgetRateClassMap("O"));
        
        final ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        final ProposalDevelopmentDocument pdDoc = pdForm.getDocument();
        
        if (TOGGLE_TAB.equals(pdForm.getMethodToCall())) {
            final BudgetTDCValidator tdcValidator = new BudgetTDCValidator(request);
            tdcValidator.validateGeneratingWarnings(pdDoc);
        }
        
        return super.execute(mapping, form, request, response);
    }

    /**
     * Action called to create a new budget version.
     * 
     * @param mapping 
     * @param form
     * @param request
     * @param response
     * @return ActionForward instance for forwarding to the tab.
     */
    public ActionForward addBudgetVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pdDoc = pdForm.getDocument();

        getProposalDevelopmentService().addBudgetVersion(pdDoc, pdForm.getNewBudgetVersionName());
        pdForm.setNewBudgetVersionName("");

        return mapping.findForward(Constants.MAPPING_BASIC); 
    }
    
    /**
     * This method opens a particular budget version.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward openBudgetVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        BudgetService budgetService = KraServiceLocator.getService(BudgetService.class);

        if ("TRUE".equals(pdForm.getEditingMode().get("modifyProposalBudget"))) {
            save(mapping, form, request, response);
        }
        
        ProposalDevelopmentDocument pdDoc = pdForm.getDocument();
        BudgetDocumentVersion budgetDocumentToOpen = pdDoc.getBudgetDocumentVersion(getSelectedLine(request));
        BudgetVersionOverview budgetToOpen = budgetDocumentToOpen.getBudgetVersionOverview();
        Collection<BudgetProposalRate> allPropRates = budgetService.getSavedProposalRates(budgetToOpen);
        if (budgetService.checkActivityTypeChange(allPropRates, pdDoc.getDevelopmentProposal().getActivityTypeCode())) {
            return confirm(syncBudgetRateConfirmationQuestion(mapping, form, request, response,
                    KeyConstants.QUESTION_SYNCH_BUDGET_RATE), CONFIRM_SYNCH_BUDGET_RATE, NO_SYNCH_BUDGET_RATE);
        } 
        else if(CollectionUtils.isEmpty(allPropRates)) {
            //Throw Empty Rates message
            return confirm(syncBudgetRateConfirmationQuestion(mapping, form, request, response,
                    KeyConstants.QUESTION_NO_RATES_ATTEMPT_SYNCH), CONFIRM_SYNCH_BUDGET_RATE, NO_SYNCH_BUDGET_RATE);
        }else {
            DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
            BudgetDocument budgetDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetToOpen.getDocumentNumber());
            Long routeHeaderId = budgetDocument.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
            BudgetParentDocument parentDocument = budgetDocument.getParentDocument();
            if(parentDocument==null){
                budgetDocument.refreshReferenceObject("parentDocument");
            }
            Budget budget = budgetDocument.getBudget();
            if (!budget.getActivityTypeCode().equals(pdDoc.getDevelopmentProposal().getActivityTypeCode())) {
                budget.setActivityTypeCode(pdDoc.getDevelopmentProposal().getActivityTypeCode());
            }
            String forward = buildForwardUrl(routeHeaderId);
            if (pdForm.isAuditActivated()) {
                forward = StringUtils.replace(forward, "budgetParameters.do?", "budgetParameters.do?auditActivated=true&");
            }
//            forward = "http://localhost:8080/kra-dev/en/DocHandler.do?command=displayDocSearchView&docId="+routeHeaderId;
//            response.sendRedirect(forward);
            
            return new ActionForward(forward, true);
        }
    }
    
    
    public ActionForward confirmSynchBudgetRate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return synchBudgetRate(mapping, form, request, response, true);
    }

    public ActionForward noSynchBudgetRate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return synchBudgetRate(mapping, form, request, response, false);
    }

    private ActionForward synchBudgetRate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, boolean confirm) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pdDoc = pdForm.getDocument();
        BudgetDocumentVersion budgetDocumentToOpen = pdDoc.getBudgetDocumentVersion(getSelectedLine(request));
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        BudgetDocument budgetDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetDocumentToOpen.getDocumentNumber());
        Long routeHeaderId = budgetDocument.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
        String forward = buildForwardUrl(routeHeaderId);
        if (confirm) {
            budgetDocument.getBudget().setActivityTypeCode(pdDoc.getDevelopmentProposal().getActivityTypeCode());
            forward = StringUtils.replace(forward, "budgetSummary.do?", "budgetSummary.do?syncBudgetRate=Y&");
        }
        if (pdForm.isAuditActivated()) {
            forward = StringUtils.replace(forward, "budgetParameters.do?", "budgetParameters.do?auditActivated=true&");
        }
        return new ActionForward(forward, true);
    }

    /**
     * This method copies a budget version's data to a new budget version.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward copyBudgetVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        BudgetVersionOverview versionToCopy = getSelectedVersion(pdForm, request);
        if (StringUtils.isNotBlank(request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME))) {
            Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
            if (CopyPeriodsQuestion.ONE.equals(buttonClicked)) {
                return copyBudgetPeriodOne(mapping, form, request, response);
            }
            else if (CopyPeriodsQuestion.ALL.equals(buttonClicked)) {
                return copyBudgetAllPeriods(mapping, form, request, response);
            } else {
                // URL hack, just return
                return mapping.findForward(Constants.MAPPING_BASIC);
            }
        }
        
        pdForm.setSaveAfterCopy(true);
        return performQuestionWithoutInput(mapping, form, request, response, COPY_BUDGET_PERIOD_QUESTION, QUESTION_TEXT + versionToCopy.getBudgetVersionNumber() + ".", QUESTION_TYPE, pdForm.getMethodToCall(), "");
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ActionForward save(ActionMapping mapping,
        ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        final ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        // check audit rules.  If there is error, then budget can't have complete status
        boolean valid = true;
        
        if (pdForm.isSaveAfterCopy()) {
            final List<BudgetDocumentVersion> overviews
                = pdForm.getDocument().getBudgetDocumentVersions();
            final BudgetDocumentVersion copiedDocumentOverview = overviews.get(overviews.size() - 1);
            BudgetVersionOverview copiedOverview = copiedDocumentOverview.getBudgetVersionOverview();
            final String copiedName = copiedOverview.getDocumentDescription();
            copiedOverview.setDocumentDescription("copied placeholder");
            BufferedLogger.debug("validating ", copiedName);
            valid = this.getProposalDevelopmentService().isBudgetVersionNameValid(
                pdForm.getDocument(), copiedName);
            copiedOverview.setDocumentDescription(copiedName);
            pdForm.setSaveAfterCopy(!valid);
        }

        if(pdForm.isAuditActivated()) {
            valid &= this.getProposalDevelopmentService().validateBudgetAuditRuleBeforeSaveBudgetVersion(
                pdForm.getDocument());
    
            if (!valid) {
                // set up error message to go to validate panel
                final int errorBudgetVersion = this.getTentativeFinalBudgetVersion(pdForm);
                if(errorBudgetVersion != -1) {
                    GlobalVariables.getErrorMap().putError("document.budgetDocumentVersion[0].budgetVersionOverview["
                        + (errorBudgetVersion-1) +"].budgetStatus",
                        KeyConstants.CLEAR_AUDIT_ERRORS_BEFORE_CHANGE_STATUS_TO_COMPLETE);
                }
                return mapping.findForward(Constants.MAPPING_BASIC);
            }
        }

        this.setProposalStatus(pdForm.getDocument());
        //this.setBudgetStatuses(pdForm.getDocument());
        final ActionForward forward = super.save(mapping, form, request, response);
            
            //Need to facilitate releasing the Budget locks if user is redirected to Actions page
            if(forward != null && forward.getName().equalsIgnoreCase("actions")) {
                pdForm.setMethodToCall("actions");
            }
            
            return forward;
    }
    
    private int getTentativeFinalBudgetVersion(ProposalDevelopmentForm pdForm) {
        if(pdForm.getFinalBudgetVersion() != null) {
            return pdForm.getFinalBudgetVersion().intValue();
        }
        
        ProposalDevelopmentDocument document = pdForm.getDocument();
        if(document != null && CollectionUtils.isNotEmpty(document.getBudgetDocumentVersions())) {
            for(BudgetDocumentVersion budgetDocumentVersion : document.getBudgetDocumentVersions()) {
                BudgetVersionOverview budget = budgetDocumentVersion.getBudgetVersionOverview();
                if(budget.isFinalVersionFlag()) {
                    return budget.getBudgetVersionNumber().intValue();
                }
            }
        }
        
        return -1;
    }
    
    @Override 
    protected void updateProposalDocument(ProposalDevelopmentForm pdForm) {
        BusinessObjectService boService = KraServiceLocator.getService(BusinessObjectService.class);
        ProposalDevelopmentDocument pdDocument = pdForm.getDocument();
        DocumentHeader currentDocumentHeader = pdDocument.getDocumentHeader();
        KualiWorkflowDocument workflowDoc = currentDocumentHeader.getWorkflowDocument();
        ProposalDevelopmentDocument updatedDocCopy = getProposalDoc(pdDocument.getDocumentNumber());

        if(updatedDocCopy != null && updatedDocCopy.getVersionNumber().longValue() > pdDocument.getVersionNumber().longValue()) {
              //refresh the reference
            updatedDocCopy.setBudgetDocumentVersions(pdDocument.getBudgetDocumentVersions());
            updatedDocCopy.getDocumentHeader().setWorkflowDocument(workflowDoc);
            pdForm.setDocument(updatedDocCopy);
        }
        
        boService.save(pdDocument.getBudgetDocumentVersions());
    }    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        final ActionForward forward = super.reload(mapping, form, request, response);
        final ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        pdForm.setFinalBudgetVersion(getFinalBudgetVersion(pdForm.getDocument().getBudgetDocumentVersions()));
        setBudgetStatuses(pdForm.getDocument());
        
        final BudgetTDCValidator tdcValidator = new BudgetTDCValidator(request);
        tdcValidator.validateGeneratingWarnings(pdForm.getDocument());
        return forward;
    }
    
    public ActionForward copyBudgetPeriodOne(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (COPY_BUDGET_PERIOD_QUESTION.equals(question)) {
            copyBudget(form, request, true);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward copyBudgetAllPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (COPY_BUDGET_PERIOD_QUESTION.equals(question)) {
            copyBudget(form, request, false);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private BudgetVersionOverview getSelectedVersion(ProposalDevelopmentForm proposalDevelopmentForm, HttpServletRequest request) {
        return proposalDevelopmentForm.getDocument().getBudgetDocumentVersion(getSelectedLine(request)).getBudgetVersionOverview();
    }
    
    private void copyBudget(ActionForm form, HttpServletRequest request, boolean copyPeriodOneOnly) throws WorkflowException {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pdDoc = proposalDevelopmentForm.getDocument();
        BudgetVersionOverview budgetToCopy = getSelectedVersion(proposalDevelopmentForm, request);
        copyBudget(pdDoc, budgetToCopy, copyPeriodOneOnly);
    }
    
    private StrutsConfirmation syncBudgetRateConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response, String message) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_SYNCH_BUDGET_RATE,
                message, "");
    }    
    
    private BudgetRatesService getBudgetRatesService() {
        return KraServiceLocator.getService(BudgetRatesService.class);
    }

    /**
     * Locate from Spring the <code>{@link ProposalDevelopmentService}</code> singleton
     * 
     * @return ProposalDevelopmentService
     */
    private ProposalDevelopmentService getProposalDevelopmentService() {
        return KraServiceLocator.getService(ProposalDevelopmentService.class);
    }

}
