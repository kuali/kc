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
package org.kuali.kra.award.web.struts.action;

import static org.kuali.rice.kns.util.KNSConstants.QUESTION_CLICKED_BUTTON;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.budget.AwardBudgetLimit;
import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetParent;
import org.kuali.kra.budget.core.BudgetService;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.rates.BudgetRate;
import org.kuali.kra.budget.rates.BudgetRatesService;
import org.kuali.kra.budget.rates.RateClass;
import org.kuali.kra.budget.summary.BudgetSummaryService;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.budget.web.struts.action.BudgetTDCValidator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.logging.BufferedLogger;
import org.kuali.kra.question.CopyPeriodsQuestion;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.kra.web.struts.action.AuditActionHelper.ValidationState;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.LookupResultsService;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KualiRuleService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.KualiDecimal;
import org.kuali.rice.kns.web.struts.action.AuditModeAction;
import org.kuali.rice.kns.web.struts.form.KualiForm;

/**
 * Struts Action class for the Propsoal Development Budget Versions page
 */
public class AwardBudgetsAction extends AwardAction implements AuditModeAction {
    private static final String TOGGLE_TAB = "toggleTab";
    private static final String CONFIRM_SYNCH_BUDGET_RATE = "confirmSynchBudgetRate";
    private static final String NO_SYNCH_BUDGET_RATE = "noSynchBudgetRate";
    private transient KualiRuleService ruleService;
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
        AwardForm awardForm = (AwardForm) form;
        //since the award budget versions panel is usually(always??) only usable when the award is
        //read only we need to make sure to perform post budget copy stuff here
        if (!StringUtils.equals(awardForm.getMethodToCall(), "save") && awardForm.isSaveAfterCopy()) {
            final List<BudgetDocumentVersion> overviews = awardForm.getAwardDocument().getBudgetDocumentVersions();
            final BudgetDocumentVersion copiedDocumentOverview = overviews.get(overviews.size() - 1);
            BudgetVersionOverview copiedOverview = copiedDocumentOverview.getBudgetVersionOverview();
            final String copiedName = copiedOverview.getDocumentDescription();
            copiedOverview.setDocumentDescription("copied placeholder");
            BufferedLogger.debug("validating ", copiedName);
            boolean valid = getBudgetService().isBudgetVersionNameValid(awardForm.getAwardDocument(), copiedName);
            copiedOverview.setDocumentDescription(copiedName);
            awardForm.setSaveAfterCopy(!valid);
            if (!valid) {
                return mapping.findForward(Constants.MAPPING_BASIC);
            } else {
                awardForm.getAwardDocument().updateDocumentDescriptions(awardForm.getAwardDocument().getBudgetDocumentVersions());
            }
        }
        
        request.setAttribute("rateClassMap", getBudgetRatesService().getBudgetRateClassMap("O"));
        ActionForward ac = super.execute(mapping, form, request, response);
        getAwardBudgetService().populateBudgetLimitSummary(awardForm.getBudgetLimitSummary(), awardForm.getAwardDocument());
        return ac;
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
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDoc = awardForm.getAwardDocument();
        ActionForward actionForward = activate(mapping, form, request, response);
            if(actionForward == mapping.findForward(Constants.MAPPING_BASIC)) { 
                BudgetDocument<Award> newBudgetDoc = getBudgetService().addBudgetVersion(awardDoc, awardForm.getNewBudgetVersionName());
                if(newBudgetDoc!=null){
                    awardForm.setNewBudgetVersionName("");
                }
            }
        
        return actionForward;
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
    public ActionForward rebudget(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDoc = awardForm.getAwardDocument();

        AwardBudgetDocument newBudgetDoc = getAwardBudgetService().rebudget(awardDoc, awardForm.getNewBudgetVersionName());
        if(newBudgetDoc!=null){
            awardForm.setNewBudgetVersionName("");
        }
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
    @SuppressWarnings(value={"unchecked","rawtypes"})
    public ActionForward openBudgetVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        BudgetService budgetService = KraServiceLocator.getService(BudgetService.class);
        AwardBudgetService awardBudgetService = KraServiceLocator.getService(AwardBudgetService.class);
        if ("TRUE".equals(awardForm.getEditingMode().get("modifyAwardBudget"))) {
            save(mapping, form, request, response);
        }
        
        AwardDocument awardDocument = awardForm.getAwardDocument();
        awardDocument.refreshBudgetDocumentVersions();
        BudgetDocumentVersion budgetDocumentToOpen = awardDocument.getBudgetDocumentVersion(getSelectedLine(request));
        BudgetVersionOverview budgetToOpen = budgetDocumentToOpen.getBudgetVersionOverview();
        Collection<BudgetRate> allBudgetRates = budgetService.getSavedProposalRates(budgetToOpen);
        Award newestAward = getAwardBudgetService().getActiveOrNewestAward(awardDocument.getAward().getAwardNumber());
        newestAward.refreshReferenceObject("awardFandaRate");
        List<AwardFandaRate> fandaRates = newestAward.getAwardFandaRate();
        List ebRates =new ArrayList();
        if(newestAward.getSpecialEbRateOffCampus()!=null)
        	ebRates.add(newestAward.getSpecialEbRateOffCampus());
        if(newestAward.getSpecialEbRateOnCampus()!=null)
        	ebRates.add(newestAward.getSpecialEbRateOnCampus());
        if(newestAward.getRequestedStartDateInitial()==null || newestAward.getRequestedEndDateInitial()==null){
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        
        if(awardBudgetService.checkRateChange(allBudgetRates, newestAward)){
    	     return confirm(syncBudgetRateConfirmationQuestion(mapping, form, request, response,
                KeyConstants.QUESTION_SYNCH_AWARD_RATE), CONFIRM_SYNCH_BUDGET_RATE, NO_SYNCH_BUDGET_RATE);
        }
        if (budgetService.checkActivityTypeChange(allBudgetRates, newestAward.getActivityTypeCode())) {
            return confirm(syncBudgetRateConfirmationQuestion(mapping, form, request, response,
                    KeyConstants.QUESTION_SYNCH_BUDGET_RATE), CONFIRM_SYNCH_BUDGET_RATE, NO_SYNCH_BUDGET_RATE);
        } else if(CollectionUtils.isEmpty(allBudgetRates)) {
            //Throw Empty Rates message
            return confirm(syncBudgetRateConfirmationQuestion(mapping, form, request, response,
                    KeyConstants.QUESTION_NO_RATES_ATTEMPT_SYNCH), CONFIRM_SYNCH_BUDGET_RATE, NO_SYNCH_BUDGET_RATE);
        } else {
            DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
            BudgetDocument<Award> budgetDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetToOpen.getDocumentNumber());
            Long routeHeaderId = budgetDocument.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
            Budget budget = budgetDocument.getBudget();
            String forward = buildForwardUrl(routeHeaderId);
            if (!budget.getActivityTypeCode().equals(newestAward.getActivityTypeCode()) || budget.isRateClassTypesReloaded()) {
                budget.setActivityTypeCode(newestAward.getActivityTypeCode());
                forward = forward.replace("awardBudgetParameters.do?", "awardBudgetParameters.do?syncBudgetRate=Y&");
            }
            if (awardForm.isAuditActivated()) {
                forward = StringUtils.replace(forward, "awardBudgetParameters.do?", "awardBudgetParameters.do?auditActivated=true&");
            }
            //add in the showAllBudgetVersions flag so it will be persisted until they leave the documents.
            forward = StringUtils.replace(forward, "Parameters.do?", "Parameters.do?showAllBudgetVersions=" + awardForm.isShowAllBudgetVersions() + "&");
            return new ActionForward(forward, true);
        }
    }
    
    
    private BudgetSummaryService getBudgetSummaryService() {
        return KraServiceLocator.getService(BudgetSummaryService.class);
    }

    private BudgetRatesService<Award> getBudgetRateService() {
        return KraServiceLocator.getService(BudgetRatesService.class);
    }

    public ActionForward confirmSynchBudgetRate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return synchBudgetRate(mapping, form, request, response, true);
    }

    public ActionForward noSynchBudgetRate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return synchBudgetRate(mapping, form, request, response, false);
    }

    private ActionForward synchBudgetRate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, boolean confirm) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDoc = awardForm.getAwardDocument();
        BudgetDocumentVersion budgetDocumentToOpen = awardDoc.getBudgetDocumentVersion(getSelectedLine(request));
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        BudgetDocument budgetDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetDocumentToOpen.getDocumentNumber());
        Long routeHeaderId = budgetDocument.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
        String forward = buildForwardUrl(routeHeaderId);
        if (confirm) {
            budgetDocument.getBudget().setActivityTypeCode(awardDoc.getBudgetParent().getActivityTypeCode());
            Budget budget = budgetDocument.getBudget();
          
          budget.setRateClassTypesReloaded(false);
          forward = forward.replace("awardBudgetParameters.do?", "awardBudgetParameters.do?syncBudgetRate=Y&");
        }
        if (awardForm.isAuditActivated()) {
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
        AwardForm pdForm = (AwardForm) form;
        BudgetVersionOverview versionToCopy = getSelectedVersion(pdForm, request);
        if (!getAwardBudgetService().validateAddingNewBudget(pdForm.getAwardDocument())) {
            return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
        if (StringUtils.isNotBlank(request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME))) {
            Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
            if (CopyPeriodsQuestion.ONE.equals(buttonClicked)) {
                pdForm.setSaveAfterCopy(true);
                return copyBudgetPeriodOne(mapping, form, request, response);
            }
            else if (CopyPeriodsQuestion.ALL.equals(buttonClicked)) {
                pdForm.setSaveAfterCopy(true);
                return copyBudgetAllPeriods(mapping, form, request, response);
            } else {
                // URL hack, just return
                return mapping.findForward(Constants.MAPPING_BASIC);
            }
        }
        
        return performQuestionWithoutInput(mapping, form, request, response, COPY_BUDGET_PERIOD_QUESTION, QUESTION_TEXT + versionToCopy.getBudgetVersionNumber() + ".", QUESTION_TYPE, pdForm.getMethodToCall(), "");
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        final AwardForm awardForm = (AwardForm) form;
        // check audit rules. If there is error, then budget can't have complete status
        boolean valid = true;
        if (awardForm.isSaveAfterCopy()) {
            final List<BudgetDocumentVersion> overviews = awardForm.getAwardDocument().getBudgetDocumentVersions();
            final BudgetDocumentVersion copiedDocumentOverview = overviews.get(overviews.size() - 1);
            BudgetVersionOverview copiedOverview = copiedDocumentOverview.getBudgetVersionOverview();
            final String copiedName = copiedOverview.getDocumentDescription();
            copiedOverview.setDocumentDescription("copied placeholder");
            BufferedLogger.debug("validating ", copiedName);
            valid = getBudgetService().isBudgetVersionNameValid(awardForm.getAwardDocument(), copiedName);
            copiedOverview.setDocumentDescription(copiedName);
            awardForm.setSaveAfterCopy(!valid);
        }

        if (awardForm.isAuditActivated()) {
            valid &= getBudgetService().validateBudgetAuditRuleBeforeSaveBudgetVersion(awardForm.getAwardDocument());
            if (!valid) {
                // set up error message to go to validate panel
                final int errorBudgetVersion = this.getTentativeFinalBudgetVersion(awardForm);
                if (errorBudgetVersion != -1) {
                    GlobalVariables.getErrorMap().putError(
                            "document.developmentProposalList[0].budgetVersionOverview[" + (errorBudgetVersion - 1)
                                    + "].budgetStatus", KeyConstants.CLEAR_AUDIT_ERRORS_BEFORE_CHANGE_STATUS_TO_COMPLETE);
                }
                return mapping.findForward(Constants.MAPPING_BASIC);
            }
        }

        // this.setProposalStatus(pdForm.getDocument());
        // this.setBudgetStatuses(pdForm.getDocument());
        final ActionForward forward = super.save(mapping, form, request, response);

        // Need to facilitate releasing the Budget locks if user is redirected to Actions page
        if (forward != null && forward.getName().equalsIgnoreCase("actions")) {
            awardForm.setMethodToCall("actions");
        }
        
        //force the save the award budgets
        if (awardForm.getAwardDocument().getBudgetDocumentVersions() != null 
                && !awardForm.getAwardDocument().getBudgetDocumentVersions().isEmpty()) {
            this.getBusinessObjectService().save(awardForm.getAwardDocument().getBudgetDocumentVersions());
        }

        return forward;
    }
    
    private int getTentativeFinalBudgetVersion(AwardForm awardForm) {
        if(awardForm.getFinalBudgetVersion() != null) {
            return awardForm.getFinalBudgetVersion().intValue();
        }
        
        AwardDocument document = awardForm.getAwardDocument();
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
    
    protected void updateAwardDocument(AwardForm awardForm) {
        BusinessObjectService boService = KraServiceLocator.getService(BusinessObjectService.class);
        AwardDocument awardDocument = awardForm.getAwardDocument();
//        DocumentHeader currentDocumentHeader = awardDocument.getDocumentHeader();
//        KualiWorkflowDocument workflowDoc = currentDocumentHeader.getWorkflowDocument();
//        AwardDocument updatedDocCopy = getProposalDoc(pdDocument.getDocumentNumber());

//        if(updatedDocCopy != null && updatedDocCopy.getVersionNumber().longValue() > pdDocument.getVersionNumber().longValue()) {
//              //refresh the reference
//            updatedDocCopy.getDevelopmentProposal().setBudgetDocumentVersions(pdDocument.getBudgetDocumentVersions());
//            updatedDocCopy.getDocumentHeader().setWorkflowDocument(workflowDoc);
//            awardForm.setDocument(updatedDocCopy);
//        }
        
        boService.save(awardDocument.getBudgetDocumentVersions());
    }    

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        final ActionForward forward = super.reload(mapping, form, request, response);
        final AwardForm awardForm = (AwardForm) form;
        awardForm.setFinalBudgetVersion(getFinalBudgetVersion(awardForm.getAwardDocument().getBudgetDocumentVersions()));
        setBudgetStatuses(awardForm.getAwardDocument());
        
        final BudgetTDCValidator tdcValidator = new BudgetTDCValidator(request);
        tdcValidator.validateGeneratingWarnings(awardForm.getAwardDocument());
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
    
    private BudgetVersionOverview getSelectedVersion(AwardForm proposalDevelopmentForm, HttpServletRequest request) {
        return proposalDevelopmentForm.getAwardDocument().getBudgetDocumentVersion(getSelectedLine(request)).getBudgetVersionOverview();
    }
    
    private void copyBudget(ActionForm form, HttpServletRequest request, boolean copyPeriodOneOnly) throws WorkflowException {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDoc = awardForm.getAwardDocument();
        BudgetVersionOverview budgetToCopy = getSelectedVersion(awardForm, request);
        copyBudget(awardDoc, budgetToCopy, copyPeriodOneOnly);
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
     * Gets the budgetService attribute. 
     * @return Returns the budgetService.
     */
    public BudgetService getBudgetService() {
        return KraServiceLocator.getService(BudgetService.class);
    }

    public ActionForward activate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = new AuditActionHelper().setAuditMode(mapping, (AwardForm) form, true);
        ValidationState state = new AuditActionHelper().isValidSubmission((AwardForm) form, false);
        if (state == ValidationState.ERROR) {
            actionForward = mapping.findForward(Constants.MAPPING_AWARD_ACTIONS_PAGE);
        }
        return actionForward;
    }

    public ActionForward deactivate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
