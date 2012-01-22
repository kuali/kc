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

import static org.kuali.kra.infrastructure.KeyConstants.QUESTION_RECALCULATE_BUDGET_CONFIRMATION;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;
import static org.kuali.rice.krad.util.KRADConstants.QUESTION_INST_ATTRIBUTE_NAME;

import java.util.Collection;
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
import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetService;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.AddBudgetPeriodEvent;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.parameters.GenerateBudgetPeriodEvent;
import org.kuali.kra.budget.parameters.SaveBudgetPeriodEvent;
import org.kuali.kra.budget.rates.BudgetRatesService;
import org.kuali.kra.budget.summary.BudgetSummaryService;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.kns.lookup.LookupResultsService;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;
import org.springframework.util.AutoPopulatingList;

public class BudgetParametersAction extends BudgetAction {
    private static final Log LOG = LogFactory.getLog(BudgetParametersAction.class);
    private static final String CONFIRM_RECALCULATE_BUDGET_KEY = "calculateAllPeriods";
    private static final String CONFIRM_SAVE_BUDGET_KEY = "saveAfterQuestion";
    private static final String CONFIRM_GENERATE_ALL_PERIODS = "confirmGenerateAllPeriods";
    private static final String CONFIRM_HEADER_TAB_KEY = "headerTabAfterQuestion";
    private static final String DO_NOTHING = "doNothing";
    private static final String CONFIRM_SAVE_SUMMARY = "confirmSaveSummary";
    private static final String CONFIRM_DELETE_BUDGET_PERIOD = "confirmDeleteBudgetPeriod";
    private static final String CONFIRM_DEFAULT_BUDGET_PERIODS = "confirmDefaultBudgetPeriods";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward = super.execute(mapping, form, request, response);
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();
        updateTotalCost(budget);
        getBudgetSummaryService().setupOldStartEndDate(budget, false);
        if (StringUtils.isNotBlank(budgetForm.getSyncBudgetRate()) && budgetForm.getSyncBudgetRate().equals("Y")) {
            budget.setRateClassTypesReloaded(true);
            getBudgetRatesService().syncAllBudgetRates(budgetDocument);
            budget.setRateSynced(true);
            budgetForm.setSyncBudgetRate("");
            // jira-1848 : force to calc budget after sync
            getBudgetSummaryService().calculateBudget(budget);
        }
        return forward;
    }


    private BudgetRatesService getBudgetRatesService() {
        return KraServiceLocator.getService(BudgetRatesService.class);
    }


    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();
        
        boolean rulePassed = getKualiRuleService().applyRules(
            new SaveBudgetPeriodEvent(Constants.EMPTY_STRING, budgetForm.getBudgetDocument()));
        if (isRateTypeChanged(budgetForm)) {
            if (isBudgetPeriodDateChanged(budget) && isLineItemErrorOnly()) {
                GlobalVariables.setMessageMap(new MessageMap());
                return confirm(buildSaveBudgetSummaryConfirmationQuestion(mapping, form, request, response,
                                                                          KeyConstants.QUESTION_SAVE_BUDGET_SUMMARY_FOR_RATE_AND_DATE_CHANGE), CONFIRM_SAVE_SUMMARY, DO_NOTHING);
            } else {
                return confirm(buildRecalculateBudgetConfirmationQuestion(mapping, form, request, response),
                               CONFIRM_SAVE_BUDGET_KEY, DO_NOTHING);
            }
        } else {
            // This whole else block is mostly the same as saveAfterQuestion, so why not use saveAfterQuestion here?
            updateThisBudgetVersion(budgetDocument);
            if (budgetForm.isUpdateFinalVersion()) {
                reconcileFinalBudgetFlags(budgetForm);
                setBudgetStatuses(budgetDocument.getParentDocument());
            }
            if (isBudgetPeriodDateChanged(budget) && isLineItemErrorOnly()) {
                GlobalVariables.setMessageMap(new MessageMap());
                return confirm(buildSaveBudgetSummaryConfirmationQuestion(mapping, form, request, response,
                                                                          KeyConstants.QUESTION_SAVE_BUDGET_SUMMARY), CONFIRM_SAVE_SUMMARY, "");
            }
            if (rulePassed) {
                // update campus flag if budget level flag is changed
                if (StringUtils.isBlank(budgetForm.getPrevOnOffCampusFlag())
                    || !budget.getOnOffCampusFlag().equals(budgetForm.getPrevOnOffCampusFlag())) {
                    KraServiceLocator.getService(BudgetSummaryService.class).updateOnOffCampusFlag(budget,
                                                                                                   budget.getOnOffCampusFlag());
                }
                boolean valid = true;
                if (Boolean.valueOf(budgetDocument.getProposalBudgetFlag())) {
                    valid = isValidToComplete(budgetDocument.getParentDocument());
                    int errorSize = GlobalVariables.getMessageMap().getErrorMessages().size();
                    final BudgetTDCValidator tdcValidator = new BudgetTDCValidator(request);
                    tdcValidator.validateGeneratingErrorsAndWarnings(budgetDocument.getParentDocument());
                    if (GlobalVariables.getMessageMap().getErrorMessages().size() > errorSize) {
                        valid = false;
                    }
                }
                if (budget.getFinalVersionFlag() && valid) {
                    budgetDocument.getParentDocument().getBudgetParent().setBudgetStatus(budget.getBudgetStatus());
                }
                if (valid) {
                    updateBudgetPeriodDbVersion(budget);
                    return super.save(mapping, form, request, response);
                }
                else {
                    mapping.findForward(Constants.MAPPING_BASIC);
                }
            }
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    private boolean isValidToComplete(BudgetParentDocument document) throws Exception {
        boolean valid = KraServiceLocator.getService(BudgetService.class).validateBudgetAuditRuleBeforeSaveBudgetVersion(document);

        if (!valid) {
            GlobalVariables.getMessageMap().putError("document.budgetDocumentVersion[" + 0 + "].budgetVersionOverview.budgetStatus",
                    KeyConstants.CLEAR_AUDIT_ERRORS_BEFORE_CHANGE_STATUS_TO_COMPLETE);
        }
        return valid;
    }
    
    /**
     * This method returns <CODE>true</CODE> if one of the two rate types has changed since the last save.
     * @param budgetForm
     * @return
     */
    private boolean isRateTypeChanged(BudgetForm budgetForm) {
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();
        BudgetDocument originalBudgetDocument = (BudgetDocument)KraServiceLocator.getService(BusinessObjectService.class).retrieve(budgetDocument);
        Budget originalBudget = originalBudgetDocument.getBudget();
        
        return (!StringUtils.equalsIgnoreCase(originalBudget.getOhRateClassCode(), budget.getOhRateClassCode())
            || !StringUtils.equalsIgnoreCase(originalBudget.getUrRateClassCode(), budget.getUrRateClassCode()));
    }
    
    public ActionForward saveAfterQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();

        // The following 3 lines are needed so the rate type is correctly propagated to the Personnel tab if
        // the rate type was changed after a validation error occured. We might want to consider getting rid of
        // ohRateClassCodePrevValue urRateClassCodePrevValue altogether in favor of using the BusinessObjectService
        // as below.
        BudgetDocument originalBudgetDocument = (BudgetDocument)KraServiceLocator.getService(BusinessObjectService.class).retrieve(budgetDocument);
        budgetForm.setOhRateClassCodePrevValue(originalBudgetDocument.getBudget().getOhRateClassCode());
        budgetForm.setUrRateClassCodePrevValue(originalBudgetDocument.getBudget().getUrRateClassCode());
        
        updateThisBudgetVersion(budgetDocument);
        if (budgetForm.isUpdateFinalVersion()) {
            reconcileFinalBudgetFlags(budgetForm);
            setBudgetStatuses(budgetDocument.getParentDocument());
        }
        boolean rulePassed = getKualiRuleService().applyRules(
            new SaveBudgetPeriodEvent(Constants.EMPTY_STRING, budgetDocument));
        if (rulePassed) {
            // update campus flag if budget level flag is changed
            if (StringUtils.isBlank(budgetForm.getPrevOnOffCampusFlag())
                || !budget.getOnOffCampusFlag().equals(budgetForm.getPrevOnOffCampusFlag())) {
                KraServiceLocator.getService(BudgetSummaryService.class).updateOnOffCampusFlag(budget,
                                                                                               budget.getOnOffCampusFlag());
            }
            if (budget.getFinalVersionFlag()) {
                budgetDocument.getParentDocument().getBudgetParent().setBudgetStatus(budget.getBudgetStatus());
            }
            updateBudgetPeriodDbVersion(budget);
            return super.save(mapping, form, request, response);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward confirmSaveSummary(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();
        if (isRateTypeChanged(budgetForm)) {
            BudgetDocument originalBudgetDocument = (BudgetDocument) KraServiceLocator.getService(BusinessObjectService.class)
                    .retrieve(budgetDocument);
            budgetForm.setOhRateClassCodePrevValue(originalBudgetDocument.getBudget().getOhRateClassCode());
            budgetForm.setUrRateClassCodePrevValue(originalBudgetDocument.getBudget().getUrRateClassCode());
        }
        updateThisBudgetVersion(budgetDocument);
        if (budgetForm.isUpdateFinalVersion()) {
            reconcileFinalBudgetFlags(budgetForm);
            setBudgetStatuses(budgetDocument.getParentDocument());
        }
        getBudgetSummaryService().adjustStartEndDatesForLineItems(budget);
        boolean rulePassed = getKualiRuleService().applyRules(
                new SaveBudgetPeriodEvent(Constants.EMPTY_STRING, budgetForm.getBudgetDocument()));
        if (rulePassed) {
            // update campus flag if budget level flag is changed
            if (StringUtils.isBlank(budgetForm.getPrevOnOffCampusFlag())
                    || !budget.getOnOffCampusFlag().equals(budgetForm.getPrevOnOffCampusFlag())) {
                KraServiceLocator.getService(BudgetSummaryService.class).updateOnOffCampusFlag(budget,
                        budget.getOnOffCampusFlag());
            }
            if (budget.getFinalVersionFlag()) {
                budgetDocument.getParentDocument().getBudgetParent().setBudgetStatus(budget.getBudgetStatus());
            }
            updateBudgetPeriodDbVersion(budget);
            return super.save(mapping, form, request, response);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is used to add a new Budget Period
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward addBudgetPeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetPeriod newBudgetPeriod = budgetForm.getNewBudgetPeriod();
        // List<BudgetPeriod> budgetPeriods = budgetForm.getBudgetDocument().getBudgetPeriods();
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();
        if (getKualiRuleService().applyRules(
                new AddBudgetPeriodEvent(Constants.EMPTY_STRING, budgetForm.getBudgetDocument(), newBudgetPeriod))) {
            getBudgetSummaryService().addBudgetPeriod(budget, newBudgetPeriod);
            /* set new period and calculate all periods */
            budgetForm.setNewBudgetPeriod(budget.getNewBudgetPeriod());
            // TODO : per conversation with Geo. comment it out for now.
            // calculate should get called only when you press calculate or while saving
            // budgetForm.getBudgetDocument().getBudgetSummaryService().calculateBudget(budget);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is used to delete an existing Budget Period
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward deleteBudgetPeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();
        int delPeriod = getLineToDelete(request);
        int viewPeriod = 0;
        if (budgetForm.getViewBudgetPeriod() != null) {
            viewPeriod = budgetForm.getViewBudgetPeriod();
        }
        List <BudgetLineItem> budgetLineItems = budget.getBudgetPeriods().get(delPeriod).getBudgetLineItems();
        if (viewPeriod > 0 && ((delPeriod+1) == viewPeriod || budget.getBudgetPeriods().size() == viewPeriod)) {
            budgetForm.setViewBudgetPeriod(Integer.valueOf(1));
        }
        if (budgetLineItems != null && budgetLineItems.size() > 0) {
            return confirm(buildDeleteBudgetPeriodConfirmationQuestion(mapping, form, request, response,
                     delPeriod+1), CONFIRM_DELETE_BUDGET_PERIOD, "");
        } else {
//            if (getKualiRuleService().applyRules(
//                    new DeleteBudgetPeriodEvent(Constants.EMPTY_STRING, budgetForm.getBudgetDocument(), delPeriod))) {
                getBudgetSummaryService().deleteBudgetPeriod(budget, delPeriod);
                /* calculate all periods */
                getBudgetSummaryService().calculateBudget(budget);
//            }
            return mapping.findForward(Constants.MAPPING_BASIC);            
        }
    }
    
    public ActionForward confirmDeleteBudgetPeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();
        int delPeriod = getLineToDelete(request);
//        if (getKualiRuleService().applyRules(
//                new DeleteBudgetPeriodEvent(Constants.EMPTY_STRING, budgetForm.getBudgetDocument(), delPeriod))) {
            getBudgetSummaryService().deleteBudgetPeriod(budget, delPeriod);
            /* calculate all periods */
            getBudgetSummaryService().calculateBudget(budget);
//        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is used to recalculate Budget Period data
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward recalculateBudgetPeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        /* calculate all periods */
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();
        getBudgetSummaryService().calculateBudget(budget);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is used to generate all Budget Period data
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward generateAllPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
//the rate type gets reset if there is a validation error. it shouldn't. 
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        BudgetForm budgetForm = (BudgetForm) form;
        boolean rulePassed = getKualiRuleService().applyRules(
                new GenerateBudgetPeriodEvent(Constants.EMPTY_STRING, budgetForm.getBudgetDocument()));
        if (rulePassed) {
            if (isRateTypeChanged(budgetForm)) {
                return confirm(buildRecalculateBudgetConfirmationQuestion(mapping, form, request, response), 
                               CONFIRM_GENERATE_ALL_PERIODS, DO_NOTHING);
            } else {
                return confirmGenerateAllPeriods(mapping, form, request, response);
            }
        }
        return forward;
    }

    public ActionForward confirmGenerateAllPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudgetDocument().getBudget();
        
        if (StringUtils.isBlank(budgetForm.getPrevOnOffCampusFlag())
                || !budget.getOnOffCampusFlag().equals(budgetForm.getPrevOnOffCampusFlag())) {
            KraServiceLocator.getService(BudgetSummaryService.class).updateOnOffCampusFlag(budget,
                    budget.getOnOffCampusFlag());
        }
        /* calculate first period - only period 1 exists at this point */
        getBudgetSummaryService().calculateBudget(budget);
        /* generate all periods */
        getBudgetSummaryService().generateAllPeriods(budget);

        /* calculate all periods */
        getBudgetSummaryService().calculateBudget(budget);
        // reset the old start/end date if it is changed for some reason
        getBudgetSummaryService().setupOldStartEndDate(budget, true);
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward questionCalculateAllPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();

        if (!StringUtils.equalsIgnoreCase(budget.getOhRateClassCode(), budgetForm.getOhRateClassCodePrevValue())
                || !StringUtils.equalsIgnoreCase(budget.getUrRateClassCode(), budgetForm.getUrRateClassCodePrevValue())) {
            return confirm(buildRecalculateBudgetConfirmationQuestion(mapping, form, request, response),
                    CONFIRM_RECALCULATE_BUDGET_KEY, DO_NOTHING);
        }
        else {
            if (StringUtils.isBlank(budgetForm.getPrevOnOffCampusFlag())
                    || !budget.getOnOffCampusFlag().equals(budgetForm.getPrevOnOffCampusFlag())) {
                KraServiceLocator.getService(BudgetSummaryService.class).updateOnOffCampusFlag(budget,
                        budget.getOnOffCampusFlag());
            }
            /* calculate all periods */
            getBudgetSummaryService().calculateBudget(budget);
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is used to calculate all Budget Period data
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward calculateAllPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();

        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_RECALCULATE_BUDGET_KEY.equals(question)) {
            if (StringUtils.isBlank(budgetForm.getPrevOnOffCampusFlag())
                    || !budget.getOnOffCampusFlag().equals(budgetForm.getPrevOnOffCampusFlag())) {
                KraServiceLocator.getService(BudgetSummaryService.class).updateOnOffCampusFlag(budget,
                        budget.getOnOffCampusFlag());
            }
            /* calculate all periods */
            getBudgetSummaryService().calculateBudget(budget);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward doNothing(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();
        budget.setOhRateClassCode(budgetForm.getOhRateClassCodePrevValue());
        budget.setUrRateClassCode(budgetForm.getUrRateClassCodePrevValue());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * TODO fix method description
     * This method builds a Opportunity Delete Confirmation Question as part of the Questions Framework
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    private StrutsConfirmation buildRecalculateBudgetConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_RECALCULATE_BUDGET_KEY,
                QUESTION_RECALCULATE_BUDGET_CONFIRMATION);
    }

    protected KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
    }

    private void reconcileFinalBudgetFlags(BudgetForm budgetForm) {
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();
        BudgetParentDocument parentDocument = budgetDocument.getParentDocument();
        if (budget.getFinalVersionFlag()) {
            // This version has been marked as final - update other versions.
            for (BudgetDocumentVersion documentVersion : parentDocument.getBudgetDocumentVersions()) {
                BudgetVersionOverview version = documentVersion.getBudgetVersionOverview();
                if (!budget.getBudgetVersionNumber().equals(version.getBudgetVersionNumber())) {
                    version.setFinalVersionFlag(false);
                }
            }
        }
        else {
            budgetForm.setFinalBudgetVersion(null);
        }
    }

    /**
     * 
     * This method to set the DB version# for budget periods. To eliminate optimistic locking problem. newly adjusted period has no
     * version number set, but its period may exist in DB.
     * 
     * @param budget
     */
    private void updateBudgetPeriodDbVersion(Budget budget) {
        // set version number for saving
        Map<String, Long> budgetPeriodMap = new HashMap<String, Long>();
        budgetPeriodMap.put("budgetId", budget.getBudgetId());
        Collection<BudgetPeriod> existBudgetPeriods = KraServiceLocator.getService(BusinessObjectService.class).findMatching(
                BudgetPeriod.class, budgetPeriodMap);
        for (BudgetPeriod budgetPeriod : existBudgetPeriods) {
            for (BudgetPeriod newBudgetPeriod : budget.getBudgetPeriods()) {
                if (budgetPeriod.getBudgetPeriodId().equals(newBudgetPeriod.getBudgetPeriodId())) {
                    newBudgetPeriod.setVersionNumber(budgetPeriod.getVersionNumber());
                }
            }
        }

    }

    /**
     * 
     * This method period's total cost based on input from direct/indirect total cost.
     * @param budget
     */
    private void updateTotalCost(Budget budget) {
        BudgetDecimal totalDirectCost = BudgetDecimal.ZERO;
        BudgetDecimal totalIndirectCost = BudgetDecimal.ZERO;
        BudgetDecimal totalCost = BudgetDecimal.ZERO;
        for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
            if (budgetPeriod.getTotalDirectCost().isGreaterThan(BudgetDecimal.ZERO)
                    || budgetPeriod.getTotalIndirectCost().isGreaterThan(BudgetDecimal.ZERO)) {
                budgetPeriod.setTotalCost(budgetPeriod.getTotalDirectCost().add(budgetPeriod.getTotalIndirectCost()));
            }
            totalDirectCost = totalDirectCost.add(budgetPeriod.getTotalDirectCost());
            totalIndirectCost = totalIndirectCost.add(budgetPeriod.getTotalIndirectCost());
            totalCost = totalCost.add(budgetPeriod.getTotalCost());
        }
        budget.setTotalDirectCost(totalDirectCost);
        budget.setTotalIndirectCost(totalIndirectCost);
        budget.setTotalCost(totalCost);

    }

    private void updateThisBudgetVersion(BudgetDocument budgetDocument) {
        Budget budget = budgetDocument.getBudget();
        BudgetParentDocument proposal = budgetDocument.getParentDocument();
        
        for (BudgetDocumentVersion documentVersion : proposal.getBudgetDocumentVersions()) {
            BudgetVersionOverview version = documentVersion.getBudgetVersionOverview();
            if (budget.getBudgetVersionNumber().equals(version.getBudgetVersionNumber())) {
                version.setFinalVersionFlag(budget.getFinalVersionFlag());
                version.setBudgetStatus(budget.getBudgetStatus());
                version.setModularBudgetFlag(budget.getModularBudgetFlag());
            }
        }
    }

    @Override
    public ActionForward headerTab(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();

        if (!StringUtils.equalsIgnoreCase(budget.getOhRateClassCode(), budgetForm.getOhRateClassCodePrevValue())
                || !StringUtils.equalsIgnoreCase(budget.getUrRateClassCode(), budgetForm.getUrRateClassCodePrevValue())) {
            return confirm(buildRecalculateBudgetConfirmationQuestion(mapping, form, request, response), CONFIRM_HEADER_TAB_KEY,
                    DO_NOTHING);
        }

        return super.headerTab(mapping, form, request, response);
    }

    public ActionForward headerTabAfterQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        return super.headerTab(mapping, form, request, response);
    }

    /**
     * 
     * This method to check if the period duration has been changed.
     * @param budget
     * @return
     */
    private boolean isBudgetPeriodDateChanged(Budget budget) {
        for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
            if (budgetPeriod.getStartDate() != null && budgetPeriod.getOldStartDate() != null && 
                    budgetPeriod.getEndDate() != null && budgetPeriod.getOldEndDate() != null && 
                    (budgetPeriod.getStartDate().compareTo(budgetPeriod.getOldStartDate()) != 0
                    || budgetPeriod.getEndDate().compareTo(budgetPeriod.getOldEndDate()) != 0)) {
                return true;
            }

        }
        return false;
    }

    /**
     * 
     * This method is to build the confirmation mesasage if period duration changed and trying to save.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @param message
     * @return
     * @throws Exception
     */
    private StrutsConfirmation buildSaveBudgetSummaryConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response, String message) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_SAVE_SUMMARY,
                message, "");
    }
    
    /**
     * 
     * This method is to build the confirmation on deleting period which has line items.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @param deletePeriod
     * @return
     * @throws Exception
     */
    private StrutsConfirmation buildDeleteBudgetPeriodConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response, int deletePeriod) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_HEADER_TAB_KEY,
                KeyConstants.QUESTION_DELETE_BUDGET_PERIOD, Integer.toString(deletePeriod));
    }

    /**
     * 
     * This method is to check the error map to see if there is any error other than line item date error.
     * line item date date error should be resolved with adjustlineitem start/end date.
     * This is called after rule verification and before save.
     * @return
     */
    private boolean isLineItemErrorOnly() {
        if (!GlobalVariables.getMessageMap().hasNoErrors()) {
            Map<String, AutoPopulatingList<ErrorMessage>> errors = GlobalVariables.getMessageMap().getErrorMessages();
            for (Map.Entry<String, AutoPopulatingList<ErrorMessage>> entry : errors.entrySet()) {
                Iterator<ErrorMessage> iter = entry.getValue().iterator();
                while (iter.hasNext()) {
                    ErrorMessage em = iter.next();
                    if (!em.getErrorKey().equals("error.lineItem.dateDoesNotmatch")) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 
     * This method generate default budget periods (each one with 1 year span).
     * This is generally needed when project period is adjusted.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward defaultPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        /* calculate all periods */
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();
        budget.getBudgetPeriods().clear();
        String warningMessage = getBudgetSummaryService().defaultWarningMessage(budget);
        
        if (StringUtils.isNotBlank(warningMessage)) {
            return confirm(buildDefaultBudgetPeriodsConfirmationQuestion(mapping, form, request, response,
                    warningMessage), CONFIRM_DEFAULT_BUDGET_PERIODS, "");
        } else {
                getBudgetSummaryService().defaultBudgetPeriods(budget);
                return mapping.findForward(Constants.MAPPING_BASIC);
        }
        
    }

    private StrutsConfirmation buildDefaultBudgetPeriodsConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response, String message) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_HEADER_TAB_KEY,
                KeyConstants.QUESTION_DEFAULT_BUDGET_PERIODS, message);
    }
   
    public ActionForward confirmDefaultBudgetPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();
        getBudgetSummaryService().defaultBudgetPeriods(budget);
        getBudgetSummaryService().adjustStartEndDatesForLineItems(budget);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        BudgetForm budgetForm = (BudgetForm)form;
        if (budgetForm.getLookupResultsBOClassName() != null && budgetForm.getLookupResultsSequenceNumber() != null) {
            String lookupResultsSequenceNumber = budgetForm.getLookupResultsSequenceNumber();
            
            @SuppressWarnings("unchecked")
            Class<BusinessObject> lookupResultsBOClass = (Class<BusinessObject>) Class.forName(budgetForm.getLookupResultsBOClassName());
            
            Collection<BusinessObject> rawValues = KraServiceLocator.getService(LookupResultsService.class)
                .retrieveSelectedResultBOs(lookupResultsSequenceNumber, lookupResultsBOClass,
                        GlobalVariables.getUserSession().getPerson().getPrincipalId());
            
            if (lookupResultsBOClass.isAssignableFrom(BudgetPeriod.class)) {
            	String budgetPeriod = budgetForm.getLookedUpCollectionName();
                getAwardBudgetService().copyLineItemsFromProposalPeriods(rawValues, 
                        budgetForm.getBudgetDocument().getBudget().getBudgetPeriod(Integer.parseInt(budgetPeriod)));
            }
        }
        return super.refresh(mapping, form, request, response);
    }
    
    private AwardBudgetService getAwardBudgetService() {
        return KraServiceLocator.getService(AwardBudgetService.class);
    }    

    
}
