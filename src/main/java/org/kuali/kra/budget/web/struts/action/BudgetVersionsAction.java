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
package org.kuali.kra.budget.web.struts.action;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;
import static org.kuali.kra.logging.BufferedLogger.debug;
import static org.kuali.kra.logging.BufferedLogger.info;
import static org.kuali.rice.kns.util.KNSConstants.QUESTION_CLICKED_BUTTON;
import static org.kuali.rice.kns.util.KNSConstants.QUESTION_INST_ATTRIBUTE_NAME;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.budget.bo.BudgetProposalRate;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.budget.bo.RateClass;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetRatesService;
import org.kuali.kra.budget.service.BudgetService;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.question.CopyPeriodsQuestion;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Struts Action class for requests from the Budget Versions page.
 */
public class BudgetVersionsAction extends BudgetAction {
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
        
        final BudgetForm budgetForm = (BudgetForm) form;
        
        if (TOGGLE_TAB.equals(budgetForm.getMethodToCall())) {
            final BudgetTDCValidator tdcValidator = new BudgetTDCValidator(request);
            tdcValidator.validateGeneratingWarnings(budgetForm.getDocument().getProposal());
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
        budgetForm.setFinalBudgetVersion(getFinalBudgetVersion(budgetForm.getDocument().getProposal().getBudgetVersionOverviews()));
        setBudgetStatuses(budgetForm.getDocument().getProposal());
        
        final BudgetTDCValidator tdcValidator = new BudgetTDCValidator(request);
        tdcValidator.validateGeneratingWarnings(budgetForm.getDocument().getProposal());

        return forward;
    }
    
    /* There is a certain amount of shared logic in the below methods. If you find yourself maintaining/refactoring this, consider consolidation. */

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
        ProposalDevelopmentDocument pdDoc = budgetForm.getDocument().getProposal();
        getProposalDevelopmentService().addBudgetVersion(pdDoc, budgetForm.getNewBudgetVersionName());
        budgetForm.setNewBudgetVersionName("");
        
        return mapping.findForward(Constants.MAPPING_BASIC);
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
        ProposalDevelopmentDocument pdDoc = budgetDoc.getProposal();
        
        Collection<BudgetProposalRate> allPropRates = budgetService.getSavedProposalRates(pdDoc, budgetDoc.getBudgetVersionNumber().toString());
        if (budgetService.checkActivityTypeChange(allPropRates, pdDoc.getActivityTypeCode())) {
            //Rates-Refresh Scenario-2
            budgetDoc.setRateClassTypesReloaded(true);
            return confirm(syncBudgetRateConfirmationQuestion(mapping, form, request, response,
                    KeyConstants.QUESTION_SYNCH_BUDGET_RATE), CONFIRM_SYNCH_BUDGET_RATE, NO_SYNCH_BUDGET_RATE);
        } else if(CollectionUtils.isEmpty(allPropRates)) {
            //Throw Empty Rates message
            return confirm(syncBudgetRateConfirmationQuestion(mapping, form, request, response,
                    KeyConstants.QUESTION_NO_RATES_ATTEMPT_SYNCH), CONFIRM_SYNCH_BUDGET_RATE, NO_SYNCH_BUDGET_RATE);
        } else {
            BudgetVersionOverview budgetToOpen = pdDoc.getBudgetVersionOverview(getSelectedLine(request));
            DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
            BudgetDocument budgetDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetToOpen.getDocumentNumber());
            Long routeHeaderId = budgetDocument.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
            if (!budgetDocument.getActivityTypeCode().equals(budgetDocument.getProposal().getActivityTypeCode())) {
                budgetDocument.setActivityTypeCode(budgetDocument.getProposal().getActivityTypeCode());
            }
            String forward = buildForwardUrl(routeHeaderId);
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
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDoc = budgetForm.getDocument();

        ProposalDevelopmentDocument pdDoc = budgetDoc.getProposal();
        BudgetVersionOverview budgetToOpen = pdDoc.getBudgetVersionOverview(getSelectedLine(request));
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        BudgetDocument budgetDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetToOpen.getDocumentNumber());
        Long routeHeaderId = budgetDocument.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
        budgetDocument.setActivityTypeCode(budgetDocument.getProposal().getActivityTypeCode());
        String forward = buildForwardUrl(routeHeaderId);
        if (confirm) {
            forward = forward.replace("budgetParameters.do?", "budgetParameters.do?syncBudgetRate=Y&");
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
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward copyBudgetVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
      BudgetForm budgetForm = (BudgetForm) form;
      BudgetVersionOverview versionToCopy = getSelectedVersion(budgetForm, request);
      if (isNotBlank(request.getParameter(QUESTION_INST_ATTRIBUTE_NAME))) {
          Object buttonClicked = request.getParameter(QUESTION_CLICKED_BUTTON);
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
      budgetForm.setSaveAfterCopy(true);
      return performQuestionWithoutInput(mapping, form, request, response, COPY_BUDGET_PERIOD_QUESTION, QUESTION_TEXT + versionToCopy.getBudgetVersionNumber() + ".", QUESTION_TYPE, budgetForm.getMethodToCall(), "");

    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        //setFinalBudgetVersion(budgetForm.getFinalBudgetVersion(), budgetForm.getDocument().getProposal().getBudgetVersionOverviews());
        // TODO jira 780 - it indicated only from PD screen, not sure we need it here
        // if we don't implement it here, then it's not consistent.
        boolean valid = true;
        
        BudgetDocument budgetDocument = budgetForm.getDocument();

        if(budgetForm.isAuditActivated()) {
            try {
                valid &=getProposalDevelopmentService() .validateBudgetAuditRuleBeforeSaveBudgetVersion(budgetDocument.getProposal());
            } catch (Exception ex) {
                info("Audit rule check failed ", ex.getStackTrace());
            }
            if (!valid) {
                // set up error message to go to validate panel
                
                Integer budgetVersionNumber = budgetForm.getFinalBudgetVersion();
                // ask form for final version number... if it is null, ask current budget document its version number
                if (budgetVersionNumber == null || budgetVersionNumber.intValue() == -1) {
                    budgetVersionNumber = budgetDocument.getBudgetVersionNumber();
                }
                GlobalVariables
                    .getErrorMap()
                        .putError("document.proposal.budgetVersionOverview["+(budgetVersionNumber.intValue() - 1)+"].budgetStatus",
                                    KeyConstants.CLEAR_AUDIT_ERRORS_BEFORE_CHANGE_STATUS_TO_COMPLETE);
            } 
        }
        
        if (budgetForm.isSaveAfterCopy()) {
            List<BudgetVersionOverview> overviews = budgetForm.getDocument().getProposal().getBudgetVersionOverviews();
            BudgetVersionOverview copiedOverview = overviews.get(overviews.size() - 1);
            String copiedName = copiedOverview.getDocumentDescription();
            copiedOverview.setDocumentDescription("copied placeholder");
            debug("validating ", copiedName);
            valid = getProposalDevelopmentService().isBudgetVersionNameValid(budgetForm.getDocument().getProposal(), copiedName);
            copiedOverview.setDocumentDescription(copiedName);
            budgetForm.setSaveAfterCopy(!valid);
        }

        if (!valid) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }

        updateThisBudget(budgetForm.getDocument());
        setProposalStatus(budgetForm.getDocument().getProposal());
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
        for (BudgetVersionOverview version: budgetDocument.getProposal().getBudgetVersionOverviews()) {
            if (budgetDocument.getBudgetVersionNumber().equals(version.getBudgetVersionNumber())) {
                budgetDocument.setFinalVersionFlag(Boolean.valueOf(version.isFinalVersionFlag()));
                budgetDocument.setBudgetStatus(version.getBudgetStatus());
            }
        }
    }
    
    private BudgetVersionOverview getSelectedVersion(BudgetForm budgetForm, HttpServletRequest request) {
        return budgetForm.getDocument().getProposal().getBudgetVersionOverview(getSelectedLine(request));
    }
    
    private void copyBudget(ActionForm form, HttpServletRequest request, boolean copyPeriodOneOnly) throws WorkflowException {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDoc = budgetForm.getDocument();
        ProposalDevelopmentDocument pdDoc = budgetDoc.getProposal();
        BudgetVersionOverview budgetToCopy = getSelectedVersion(budgetForm, request);
        copyBudget(pdDoc, budgetToCopy, copyPeriodOneOnly);
    }
    
    
    private StrutsConfirmation syncBudgetRateConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response, String message) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_SYNCH_BUDGET_RATE,
                message, "");
    }

//    private StrutsConfirmation noRatesSyncConfirmationQuestion(ActionMapping mapping, ActionForm form,
//            HttpServletRequest request, HttpServletResponse response, String message) throws Exception {
//        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_SYNCH_BUDGET_RATE,
//                message, "");
//    }
    
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
