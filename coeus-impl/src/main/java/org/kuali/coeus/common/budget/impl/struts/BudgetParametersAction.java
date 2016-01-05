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
package org.kuali.coeus.common.budget.impl.struts;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.budget.framework.core.*;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.budget.AwardBudgetForm;
import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.AddBudgetPeriodEvent;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.period.GenerateBudgetPeriodEvent;
import org.kuali.coeus.common.budget.framework.rate.BudgetRatesService;
import org.kuali.coeus.common.budget.framework.summary.BudgetSummaryService;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRulesEngine;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.lookup.LookupResultsService;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
import static org.kuali.kra.infrastructure.KeyConstants.QUESTION_RECALCULATE_BUDGET_CONFIRMATION;
import static org.kuali.rice.krad.util.KRADConstants.QUESTION_INST_ATTRIBUTE_NAME;

public class BudgetParametersAction extends BudgetAction {
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
        Budget budget = budgetForm.getBudget();
        updateTotalCost(budget);
        getBudgetSummaryService().setupOldStartEndDate(budget, false);
        if (StringUtils.isNotBlank(budgetForm.getSyncBudgetRate()) && budgetForm.getSyncBudgetRate().equals("Y")) {
            budget.setRateClassTypesReloaded(true);
            getBudgetRatesService().syncAllBudgetRates(budget);
            budget.setRateSynced(true);
            budgetForm.setSyncBudgetRate("");
            getBudgetSummaryService().calculateBudget(budget);
        }
        return forward;
    }


    private BudgetRatesService getBudgetRatesService() {
        return KcServiceLocator.getService(BudgetRatesService.class);
    }


    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();
        
        boolean rulePassed = getKcBusinessRulesEngine().applyRules(new AwardBudgetSaveEvent(budget));
        
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
            if (isBudgetPeriodDateChanged(budget) && isLineItemErrorOnly()) {
                GlobalVariables.setMessageMap(new MessageMap());
                return confirm(buildSaveBudgetSummaryConfirmationQuestion(mapping, form, request, response,
                                                                          KeyConstants.QUESTION_SAVE_BUDGET_SUMMARY), CONFIRM_SAVE_SUMMARY, "");
            }
            if (rulePassed) {
                // update campus flag if budget level flag is changed
                if (StringUtils.isBlank(budgetForm.getPrevOnOffCampusFlag())
                    || !budget.getOnOffCampusFlag().equals(budgetForm.getPrevOnOffCampusFlag())) {
                    KcServiceLocator.getService(BudgetSummaryService.class).updateOnOffCampusFlag(budget,
                                                                                                   budget.getOnOffCampusFlag());
                }
                boolean valid = true;
                if (budget instanceof ProposalDevelopmentBudgetExt) {
                    valid = isValidToComplete(budget);
                    int errorSize = GlobalVariables.getMessageMap().getErrorMessages().size();
                    if (GlobalVariables.getMessageMap().getErrorMessages().size() > errorSize) {
                        valid = false;
                    }
                }
                if (valid) {
                    updateBudgetPeriodDbVersion(budget);
                    return super.save(mapping, form, request, response);
                } else {

                    if (form instanceof AwardBudgetForm) {
                        KcServiceLocator.getService(AuditHelper.class).setAuditMode(mapping, (AwardBudgetForm) form, true);
                    } else {
                        KcServiceLocator.getService(AuditHelper.class).setAuditMode(mapping, (BudgetForm) form, true);
                    }
                    
                    return mapping.findForward(Constants.BUDGET_ACTIONS_PAGE);
                }
            }
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    private boolean isValidToComplete(Budget budget) throws Exception {
        boolean valid = KcServiceLocator.getService(KcBusinessRulesEngine.class).applyRules(new BudgetAuditEvent(budget));

        if (!valid) {
            GlobalVariables.getMessageMap().putError("document.budgetDocumentVersion[" + 0 + "].budgetVersionOverview.budgetStatus",
                    KeyConstants.CLEAR_AUDIT_ERRORS_BEFORE_CHANGE_STATUS_TO_COMPLETE);
        }
        return valid;
    }
    
    /**
     * This method returns <CODE>true</CODE> if one of the two rate types has changed since the last save.
     */
    private boolean isRateTypeChanged(BudgetForm budgetForm) {
        Budget budget = budgetForm.getBudget();
        Budget originalBudget = (Budget) KcServiceLocator.getService(BusinessObjectService.class).retrieve(budget);
        
        return (!StringUtils.equalsIgnoreCase(originalBudget.getOhRateClassCode(), budget.getOhRateClassCode())
            || !StringUtils.equalsIgnoreCase(originalBudget.getUrRateClassCode(), budget.getUrRateClassCode()));
    }
    
    public ActionForward saveAfterQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();

        // The following 3 lines are needed so the rate type is correctly propagated to the Personnel tab if
        // the rate type was changed after a validation error occured. We might want to consider getting rid of
        // ohRateClassCodePrevValue urRateClassCodePrevValue altogether in favor of using the BusinessObjectService
        // as below.
        Budget originalBudget = (Budget) KcServiceLocator.getService(BusinessObjectService.class).retrieve(budget);
        budgetForm.setOhRateClassCodePrevValue(originalBudget.getOhRateClassCode());
        budgetForm.setUrRateClassCodePrevValue(originalBudget.getUrRateClassCode());

        boolean rulePassed = getKcBusinessRulesEngine().applyRules(new AwardBudgetSaveEvent(budget));
        if (rulePassed) {
            // update campus flag if budget level flag is changed
            if (StringUtils.isBlank(budgetForm.getPrevOnOffCampusFlag())
                || !budget.getOnOffCampusFlag().equals(budgetForm.getPrevOnOffCampusFlag())) {
                KcServiceLocator.getService(BudgetSummaryService.class).updateOnOffCampusFlag(budget,
                                                                                               budget.getOnOffCampusFlag());
            }
            updateBudgetPeriodDbVersion(budget);
            return super.save(mapping, form, request, response);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward confirmSaveSummary(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();
        if (isRateTypeChanged(budgetForm)) {
            Budget originalBudget = (Budget) KcServiceLocator.getService(BusinessObjectService.class)
                    .retrieve(budget);
            budgetForm.setOhRateClassCodePrevValue(originalBudget.getOhRateClassCode());
            budgetForm.setUrRateClassCodePrevValue(originalBudget.getUrRateClassCode());
        }
        getBudgetSummaryService().adjustStartEndDatesForLineItems(budget);
        boolean rulePassed = getKcBusinessRulesEngine().applyRules(new AwardBudgetSaveEvent(budget));
        if (rulePassed) {
            // update campus flag if budget level flag is changed
            if (StringUtils.isBlank(budgetForm.getPrevOnOffCampusFlag())
                    || !budget.getOnOffCampusFlag().equals(budgetForm.getPrevOnOffCampusFlag())) {
                KcServiceLocator.getService(BudgetSummaryService.class).updateOnOffCampusFlag(budget,
                        budget.getOnOffCampusFlag());
            }
            updateBudgetPeriodDbVersion(budget);
            return super.save(mapping, form, request, response);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is used to add a new Budget Period
     *
     */
    public ActionForward addBudgetPeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetPeriod newBudgetPeriod = budgetForm.getNewBudgetPeriod();
        Budget budget = budgetForm.getBudget();
        if (getKcBusinessRulesEngine().applyRules(new AddBudgetPeriodEvent(budget, newBudgetPeriod))) {
            getBudgetSummaryService().addBudgetPeriod(budget, newBudgetPeriod);
            /* set new period and calculate all periods */
            budgetForm.setNewBudgetPeriod(budget.getNewBudgetPeriod());
           }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is used to delete an existing Budget Period
     *
     */
    public ActionForward deleteBudgetPeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();
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
                getBudgetSummaryService().deleteBudgetPeriod(budget, delPeriod);
                /* calculate all periods */
                getBudgetSummaryService().calculateBudget(budget);
            return mapping.findForward(Constants.MAPPING_BASIC);            
        }
    }
    
    public ActionForward confirmDeleteBudgetPeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();
        int delPeriod = getLineToDelete(request);
        getBudgetSummaryService().deleteBudgetPeriod(budget, delPeriod);
            /* calculate all periods */
            getBudgetSummaryService().calculateBudget(budget);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is used to recalculate Budget Period data
     *
     */
    public ActionForward recalculateBudgetPeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        /* calculate all periods */
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();
        getBudgetSummaryService().calculateBudget(budget);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is used to generate all Budget Period data
     *
     */
    public ActionForward generateAllPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
//the rate type gets reset if there is a validation error. it shouldn't. 
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        BudgetForm budgetForm = (BudgetForm) form;
        boolean rulePassed = getKcBusinessRulesEngine().applyRules(
                new GenerateBudgetPeriodEvent(budgetForm.getBudget(), null));
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
            KcServiceLocator.getService(BudgetSummaryService.class).updateOnOffCampusFlag(budget,
                    budget.getOnOffCampusFlag());
        }
        /* generate all periods */
        getBudgetSummaryService().generateAllPeriods(budget);
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward questionCalculateAllPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();

        if (!StringUtils.equalsIgnoreCase(budget.getOhRateClassCode(), budgetForm.getOhRateClassCodePrevValue())
                || !StringUtils.equalsIgnoreCase(budget.getUrRateClassCode(), budgetForm.getUrRateClassCodePrevValue())) {
            return confirm(buildRecalculateBudgetConfirmationQuestion(mapping, form, request, response),
                    CONFIRM_RECALCULATE_BUDGET_KEY, DO_NOTHING);
        }
        else {
            if (StringUtils.isBlank(budgetForm.getPrevOnOffCampusFlag())
                    || !budget.getOnOffCampusFlag().equals(budgetForm.getPrevOnOffCampusFlag())) {
                KcServiceLocator.getService(BudgetSummaryService.class).updateOnOffCampusFlag(budget,
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
     */
    public ActionForward calculateAllPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();

        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_RECALCULATE_BUDGET_KEY.equals(question)) {
            if (StringUtils.isBlank(budgetForm.getPrevOnOffCampusFlag())
                    || !budget.getOnOffCampusFlag().equals(budgetForm.getPrevOnOffCampusFlag())) {
                KcServiceLocator.getService(BudgetSummaryService.class).updateOnOffCampusFlag(budget,
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
        Budget budget = budgetForm.getBudget();
        budget.setOhRateClassCode(budgetForm.getOhRateClassCodePrevValue());
        budget.setUrRateClassCode(budgetForm.getUrRateClassCodePrevValue());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     *
     * This method builds a Opportunity Delete Confirmation Question as part of the Questions Framework
     *
     */
    private StrutsConfirmation buildRecalculateBudgetConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_RECALCULATE_BUDGET_KEY,
                QUESTION_RECALCULATE_BUDGET_CONFIRMATION);
    }

    protected KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
    }

    /**
     * 
     * This method to set the DB version# for budget periods. To eliminate optimistic locking problem. newly adjusted period has no
     * version number set, but its period may exist in DB.
     *
     */
    private void updateBudgetPeriodDbVersion(Budget budget) {
        // set version number for saving
        Map<String, Long> budgetPeriodMap = new HashMap<String, Long>();
        budgetPeriodMap.put("budgetId", budget.getBudgetId());
        Collection<BudgetPeriod> existBudgetPeriods = KcServiceLocator.getService(BusinessObjectService.class).findMatching(
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
     */
    private void updateTotalCost(Budget budget) {
        ScaleTwoDecimal totalDirectCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalIndirectCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalCost = ScaleTwoDecimal.ZERO;
        for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
            if (budgetPeriod.getTotalDirectCost().isGreaterThan(ScaleTwoDecimal.ZERO)
                    || budgetPeriod.getTotalIndirectCost().isGreaterThan(ScaleTwoDecimal.ZERO)) {
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

    @Override
    public ActionForward headerTab(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();

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
     */
    private StrutsConfirmation buildSaveBudgetSummaryConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response, String message) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_SAVE_SUMMARY,
                message, "");
    }
    
    /**
     * 
     * This method is to build the confirmation on deleting period which has line items.
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
     */
    private boolean isLineItemErrorOnly() {
        if (!GlobalVariables.getMessageMap().hasNoErrors()) {
            Map<String, ? extends List<ErrorMessage>> errors = GlobalVariables.getMessageMap().getErrorMessages();
            for (Map.Entry<String, ? extends List<ErrorMessage>> entry : errors.entrySet()) {
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
     */
    public ActionForward defaultPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        /* calculate all periods */
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();
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
        Budget budget = budgetForm.getBudget();
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
            
            Collection<? extends BusinessObject> rawValues = KcServiceLocator.getService(LookupResultsService.class)
                .retrieveSelectedResultBOs(lookupResultsSequenceNumber, lookupResultsBOClass,
                        GlobalVariables.getUserSession().getPerson().getPrincipalId());
            
            if (lookupResultsBOClass.isAssignableFrom(BudgetPeriod.class)) {
            	String budgetPeriod = budgetForm.getLookedUpCollectionName();
                getAwardBudgetService().copyLineItemsFromProposalPeriods((Collection<BudgetPeriod>) rawValues,
                        budgetForm.getBudgetDocument().getBudget().getBudgetPeriod(Integer.parseInt(budgetPeriod)));
            }
        }
        return super.refresh(mapping, form, request, response);
    }
    
    private AwardBudgetService getAwardBudgetService() {
        return KcServiceLocator.getService(AwardBudgetService.class);
    }    

    
}
