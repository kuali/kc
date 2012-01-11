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
package org.kuali.kra.budget.web.struts.action;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;
import static org.kuali.kra.logging.BufferedLogger.debug;
import static org.kuali.kra.logging.BufferedLogger.info;
import static org.kuali.rice.kns.util.KNSConstants.QUESTION_CLICKED_BUTTON;
import static org.kuali.rice.kns.util.KNSConstants.QUESTION_INST_ATTRIBUTE_NAME;

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
import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetCommonService;
import org.kuali.kra.budget.core.BudgetParent;
import org.kuali.kra.budget.core.BudgetService;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.rates.BudgetRate;
import org.kuali.kra.budget.rates.BudgetRatesService;
import org.kuali.kra.budget.rates.RateClass;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.logging.BufferedLogger;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.hierarchy.ProposalHierarcyActionHelper;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.question.CopyPeriodsQuestion;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.LookupResultsService;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiForm;

/**
 * Struts Action class for requests from the Budget Versions page.
 */
public class BudgetVersionsAction extends BudgetAction {
    private static final String TOGGLE_TAB = "toggleTab";
    private static final String CONFIRM_SYNCH_BUDGET_RATE_BUDGET_DOCUMENT = "confirmSynchBudgetRateForBudgetDocument";
    private static final String NO_SYNCH_BUDGET_RATE_BUDGET_DOCUMENT = "noSynchBudgetRateForBudgetDocument";
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
        
        final BudgetForm budgetForm = (BudgetForm) form;
        BudgetParentDocument parentDocument = getBudgetParentDocument(budgetForm);
        if (TOGGLE_TAB.equals(budgetForm.getMethodToCall())) {
            final BudgetTDCValidator tdcValidator = new BudgetTDCValidator(request);
            tdcValidator.validateGeneratingWarnings(parentDocument);
        }
   
        //when this is an award budget even though the budget cannot be saved a budget can still
        //be copied. By doing this here we make sure that it will still save
        //new budgets names even though the document itself cannot be saved
        if (!StringUtils.equals(budgetForm.getMethodToCall(), "save") && budgetForm.isSaveAfterCopy()) {
            List<BudgetDocumentVersion> overviews = parentDocument.getBudgetDocumentVersions();
            BudgetVersionOverview copiedOverview = overviews.get(overviews.size() - 1).getBudgetVersionOverview();
            String copiedName = copiedOverview.getDocumentDescription();
            copiedOverview.setDocumentDescription("copied placeholder");
            debug("validating ", copiedName);
            boolean valid = getBudgetService().isBudgetVersionNameValid(parentDocument, copiedName);
            copiedOverview.setDocumentDescription(copiedName);
            budgetForm.setSaveAfterCopy(!valid);
            if (!valid) {
                return mapping.findForward(Constants.MAPPING_BASIC);
            } else {
                budgetForm.getDocument().getParentDocument().updateDocumentDescriptions(overviews);
            }
        }        
        
        return super.execute(mapping, form, request, response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        final ActionForward forward = super.docHandler(mapping, form, request, response);
        final BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();
        BudgetParentDocument parentDocument = budgetDocument.getParentDocument();
        BudgetParent budgetParent = parentDocument.getBudgetParent();
        budgetForm.setFinalBudgetVersion(getFinalBudgetVersion(parentDocument.getBudgetDocumentVersions()));
        setBudgetStatuses(parentDocument);
        AwardBudgetService awardBudgetService = KraServiceLocator.getService(AwardBudgetService.class);
        BudgetService budgetService = KraServiceLocator.getService(BudgetService.class);
        Collection<BudgetRate> savedBudgetRates = budgetService.getSavedBudgetRates(budget);
        Collection<BudgetRate> allPropRates = budgetService.getSavedBudgetRates(budget);
        if (isAwardBudget(budgetDocument)) {
            Award award = (Award) budgetDocument.getParentDocument().getBudgetParent();
            if (awardBudgetService.checkRateChange(savedBudgetRates, award)) {
                return confirm(
                        syncAwardBudgetRateConfirmationQuestion(mapping, form, request, response,
                                KeyConstants.QUESTION_SYNCH_AWARD_RATE), CONFIRM_SYNCH_AWARD_RATES, NO_SYNCH_AWARD_RATES);
            }
        }
        if (budgetService.checkActivityTypeChange(allPropRates, budgetParent.getActivityTypeCode())) {
            //Rates-Refresh Scenario-2
            budget.setRateClassTypesReloaded(true);
            return confirm(syncBudgetRateConfirmationQuestion(mapping, form, request, response,
                    KeyConstants.QUESTION_SYNCH_BUDGET_RATE), CONFIRM_SYNCH_BUDGET_RATE_BUDGET_DOCUMENT, NO_SYNCH_BUDGET_RATE_BUDGET_DOCUMENT);
        } else if(CollectionUtils.isEmpty(allPropRates)) {
            //Throw Empty Rates message
            return confirm(syncBudgetRateConfirmationQuestion(mapping, form, request, response,
                    KeyConstants.QUESTION_NO_RATES_ATTEMPT_SYNCH), CONFIRM_SYNCH_BUDGET_RATE_BUDGET_DOCUMENT, NO_SYNCH_BUDGET_RATE_BUDGET_DOCUMENT);
        }

        final BudgetTDCValidator tdcValidator = new BudgetTDCValidator(request);
        tdcValidator.validateGeneratingWarnings(parentDocument);

        return forward;
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
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetParentDocument parentDocument = getBudgetParentDocument(budgetForm);
        BudgetDocument newBudgetDoc = getBudgetService().addBudgetVersion(parentDocument, budgetForm.getNewBudgetVersionName());
        budgetForm.setNewBudgetVersionName("");
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method...
     * @param budgetForm
     * @return
     */
    private BudgetParentDocument getBudgetParentDocument(BudgetForm budgetForm) {
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        BudgetParentDocument parentDocument = budgetDocument.getParentDocument();
        return parentDocument;
    }
    
    private BudgetService getBudgetService() {
        return KraServiceLocator.getService(BudgetService.class);
    }

    /**
     * This method opens a budget version.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward openBudgetVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        
        BudgetService budgetService = KraServiceLocator.getService(BudgetService.class);
        
        if (!"TRUE".equals(budgetForm.getEditingMode().get(AuthorizationConstants.EditMode.VIEW_ONLY))) {
            save(mapping, form, request, response);
        }
        BudgetDocument budgetDoc = budgetForm.getDocument();
        
        Budget budget = budgetDoc.getBudget();
        BudgetParentDocument budgetParentDocument = getBudgetParentDocument(budgetForm);
        BudgetParent budgetParent = budgetParentDocument.getBudgetParent();
        
        BudgetDocumentVersion budgetDocumentToOpen = budgetParentDocument.getBudgetDocumentVersion(getSelectedLine(request));
        BudgetVersionOverview budgetToOpen = budgetDocumentToOpen.getBudgetVersionOverview();
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        BudgetDocument budgetDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetToOpen.getDocumentNumber());
        Budget budgetOpen = budgetDocument.getBudget();
        Long routeHeaderId = budgetDocument.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
        
        
        Collection<BudgetRate> allPropRates = budgetService.getSavedBudgetRates(budgetOpen);
        if(getBudgetRateService().performSyncFlag(budgetDocument)){
            budget.setRateClassTypesReloaded(true);
        }
        if (budgetService.checkActivityTypeChange(allPropRates, budgetParent.getActivityTypeCode())) {
            //Rates-Refresh Scenario-2
            budget.setRateClassTypesReloaded(true);
            return confirm(syncBudgetRateConfirmationQuestion(mapping, form, request, response,
                    KeyConstants.QUESTION_SYNCH_BUDGET_RATE), CONFIRM_SYNCH_BUDGET_RATE, NO_SYNCH_BUDGET_RATE);
        } else if(CollectionUtils.isEmpty(allPropRates)) {
            //Throw Empty Rates message
            return confirm(syncBudgetRateConfirmationQuestion(mapping, form, request, response,
                    KeyConstants.QUESTION_NO_RATES_ATTEMPT_SYNCH), CONFIRM_SYNCH_BUDGET_RATE, NO_SYNCH_BUDGET_RATE);
        }        

        String forward = buildForwardUrl(routeHeaderId);
        return new ActionForward(forward, true);
    }
    protected StrutsConfirmation syncBudgetRateConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response, String message) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_SYNCH_BUDGET_RATE,
                message, "");
    }
    public ActionForward confirmSynchBudgetRateForBudgetDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getDocument();

        return synchBudgetRate(budgetDocument, true);
    }

    public ActionForward noSynchBudgetRateForBudgetDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getDocument();

        return synchBudgetRate(budgetDocument, false);
    }
    protected ActionForward synchBudgetRate(BudgetDocument budgetDocument, boolean confirm) throws Exception {
        Budget budgetOpen = budgetDocument.getBudget();
        Long routeHeaderId = budgetDocument.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
        budgetOpen.setActivityTypeCode(budgetDocument.getParentDocument().getBudgetParent().getActivityTypeCode());
        budgetOpen.setRateClassTypesReloaded(true);
        String forward = buildForwardUrl(routeHeaderId);
        if (confirm) {
            forward = forward.replace("budgetParameters.do?", "budgetParameters.do?syncBudgetRate=Y&");
        }
        return new ActionForward(forward, true);
    }


    
    public ActionForward confirmSynchBudgetRate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = getSelectedBudgetDocument(request, budgetForm);

        return synchBudgetRate(budgetDocument, true);
    }

    /**
     * This method...
     * @param request
     * @param budgetForm
     * @return
     * @throws WorkflowException
     */
    private BudgetDocument getSelectedBudgetDocument(HttpServletRequest request, BudgetForm budgetForm) throws WorkflowException {
        BudgetDocument budgetDoc = budgetForm.getDocument();
        BudgetParentDocument budgetParentDocument = budgetDoc.getParentDocument();
        BudgetDocumentVersion budgetDocumentToOpen = budgetParentDocument.getBudgetDocumentVersion(getSelectedLine(request));
        BudgetVersionOverview budgetToOpen = budgetDocumentToOpen.getBudgetVersionOverview();
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        BudgetDocument budgetDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetToOpen.getDocumentNumber());
        return budgetDocument;
    }

    public ActionForward noSynchBudgetRate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = getSelectedBudgetDocument(request, budgetForm);
        return synchBudgetRate(budgetDocument, false);
    }

    private BudgetRatesService getBudgetRateService() {
        return KraServiceLocator.getService(BudgetRatesService.class);
    }


    /**
     * This method copies a budget version's data to a new budget version.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward copyBudgetVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
      BudgetForm budgetForm = (BudgetForm) form;
      BudgetVersionOverview versionToCopy = getSelectedVersion(budgetForm, request);
      BudgetParentDocument parentDocument = budgetForm.getDocument().getParentDocument();
      BudgetCommonService<BudgetParent> budgetService = getBudgetCommonService(parentDocument);
      if (!budgetService.validateAddingNewBudget(parentDocument)) {
          return mapping.findForward(Constants.MAPPING_BASIC);
      }
      if (isNotBlank(request.getParameter(QUESTION_INST_ATTRIBUTE_NAME))) {
          Object buttonClicked = request.getParameter(QUESTION_CLICKED_BUTTON);
          if (CopyPeriodsQuestion.ONE.equals(buttonClicked)) {
              budgetForm.setSaveAfterCopy(true);
              return copyBudgetPeriodOne(mapping, form, request, response);
          }
          else if (CopyPeriodsQuestion.ALL.equals(buttonClicked)) {
              budgetForm.setSaveAfterCopy(true);
              return copyBudgetAllPeriods(mapping, form, request, response);
          } else {
              // URL hack, just return
              return mapping.findForward(Constants.MAPPING_BASIC);
          }
      }
      return performQuestionWithoutInput(mapping, form, request, response, COPY_BUDGET_PERIOD_QUESTION, QUESTION_TEXT + versionToCopy.getBudgetVersionNumber() + ".", QUESTION_TYPE, budgetForm.getMethodToCall(), "");

    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        boolean valid = true;
        BudgetDocument budgetDocument = budgetForm.getDocument();
        BudgetParentDocument parentDocument = budgetDocument.getParentDocument();
        Budget budget = budgetDocument.getBudget();

        if (parentDocument instanceof ProposalDevelopmentDocument && !(new ProposalHierarcyActionHelper()).checkParentChildStatusMatch((ProposalDevelopmentDocument)parentDocument)) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        
        try {
            valid &= getBudgetService().validateBudgetAuditRuleBeforeSaveBudgetVersion(parentDocument);
        }
        catch (Exception ex) {
            info("Audit rule check failed ", ex.getStackTrace());
        }
        if (!valid) {
            // set up error message to go to validate panel

            Integer budgetVersionNumber = budgetForm.getFinalBudgetVersion();
            // ask form for final version number... if it is null, ask current budget document its version number
            if (budgetVersionNumber == null || budgetVersionNumber.intValue() == -1) {
                budgetVersionNumber = budget.getBudgetVersionNumber();
            }
            GlobalVariables.getErrorMap().putError(
                    "document.parentDocument.budgetDocumentVersion[" + (budgetVersionNumber.intValue() - 1)
                            + "].budgetVersionOverview.budgetStatus",
                    KeyConstants.CLEAR_AUDIT_ERRORS_BEFORE_CHANGE_STATUS_TO_COMPLETE);
        }
        if (budgetForm.isSaveAfterCopy()) {
            List<BudgetDocumentVersion> overviews = parentDocument.getBudgetDocumentVersions();
            BudgetVersionOverview copiedOverview = overviews.get(overviews.size() - 1).getBudgetVersionOverview();
            String copiedName = copiedOverview.getDocumentDescription();
            copiedOverview.setDocumentDescription("copied placeholder");
            debug("validating ", copiedName);
            valid = getBudgetService().isBudgetVersionNameValid(parentDocument, copiedName);
            copiedOverview.setDocumentDescription(copiedName);
            budgetForm.setSaveAfterCopy(!valid);
        }

        if (!valid) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }

        updateThisBudget(budgetDocument);
        setBudgetParentStatus(parentDocument);
        return super.save(mapping, form, request, response);
    }
    
    public ActionForward copyBudgetPeriodOne(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        if (COPY_BUDGET_PERIOD_QUESTION.equals(question)) {
            copyBudget(form, request, true);
        }
        
        return mapping.findForward(MAPPING_BASIC);
    }
    
    public ActionForward copyBudgetAllPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        if (COPY_BUDGET_PERIOD_QUESTION.equals(question)) {
            copyBudget(form, request, false);
        }
        return mapping.findForward(MAPPING_BASIC);
    }
    
    private void updateThisBudget(BudgetDocument budgetDocument) {
        Budget budget = budgetDocument.getBudget();
        for (BudgetDocumentVersion documentVersion: budgetDocument.getParentDocument().getBudgetDocumentVersions()) {
            BudgetVersionOverview version = documentVersion.getBudgetVersionOverview();
            if (budget.getBudgetVersionNumber().equals(version.getBudgetVersionNumber())) {
                budget.setFinalVersionFlag(Boolean.valueOf(version.isFinalVersionFlag()));
                budget.setBudgetStatus(version.getBudgetStatus());
            }
        }
    }
    
    private BudgetVersionOverview getSelectedVersion(BudgetForm budgetForm, HttpServletRequest request) {
        return budgetForm.getDocument().getParentDocument().getBudgetDocumentVersion(getSelectedLine(request)).getBudgetVersionOverview();
    }
    
    private void copyBudget(ActionForm form, HttpServletRequest request, boolean copyPeriodOneOnly) throws WorkflowException {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDoc = budgetForm.getDocument();
        BudgetParentDocument pdDoc = budgetDoc.getParentDocument();
        BudgetVersionOverview budgetToCopy = getSelectedVersion(budgetForm, request);
        copyBudget(pdDoc, budgetToCopy, copyPeriodOneOnly);
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
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        AwardDocument parentDocument = (AwardDocument)budgetDocument.getParentDocument();

        AwardBudgetDocument newBudgetDoc = getAwardBudgetService().rebudget(parentDocument, 
                                                        budgetForm.getNewBudgetVersionName());
        if(newBudgetDoc!=null){
            budgetForm.setNewBudgetVersionName("");
        }
        return mapping.findForward(Constants.MAPPING_BASIC); 
    }

    private AwardBudgetService getAwardBudgetService() {
        return KraServiceLocator.getService(AwardBudgetService.class);
    }

    /**
     * Locate the {@link ProposalDevelopmentService} implementation
     *
     * @return ProposalDevelopmentService singleton instance
     */
    private ProposalDevelopmentService getProposalDevelopmentService() {
        return KraServiceLocator.getService(ProposalDevelopmentService.class);
    }

    private BudgetRatesService getBudgetRatesService() {
        return getService(BudgetRatesService.class);
    }
}
