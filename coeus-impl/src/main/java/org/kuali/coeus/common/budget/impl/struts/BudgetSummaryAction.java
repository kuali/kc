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
import org.kuali.coeus.common.budget.framework.core.AwardBudgetSaveEvent;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.AddBudgetPeriodEvent;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.period.GenerateBudgetPeriodEvent;
import org.kuali.coeus.common.budget.framework.rate.BudgetRatesService;
import org.kuali.coeus.common.budget.framework.summary.BudgetSummaryService;
import org.kuali.coeus.common.budget.framework.core.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;
import org.springframework.util.AutoPopulatingList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
import static org.kuali.kra.infrastructure.KeyConstants.QUESTION_RECALCULATE_BUDGET_CONFIRMATION;
import static org.kuali.rice.krad.util.KRADConstants.QUESTION_INST_ATTRIBUTE_NAME;

public class BudgetSummaryAction extends BudgetAction {
    private static final String CONFIRM_RECALCULATE_BUDGET_KEY = "calculateAllPeriods";
    private static final String CONFIRM_SAVE_BUDGET_KEY = "saveAfterQuestion";
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
        getBudgetSummaryService().setupOldStartEndDate(
                budget, false);
        if (StringUtils.isNotBlank(budgetForm.getSyncBudgetRate()) && budgetForm.getSyncBudgetRate().equals("Y")) {
            getBudgetRatesService().syncAllBudgetRates(budget);
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
        if (!StringUtils.equalsIgnoreCase(budget.getOhRateClassCode(), budgetForm.getOhRateClassCodePrevValue())
                || !StringUtils.equalsIgnoreCase(budget.getUrRateClassCode(), budgetForm.getUrRateClassCodePrevValue())) {
            if (isBudgetPeriodDateChanged(budget) && isLineItemErrorOnly()) {
                GlobalVariables.setMessageMap(new MessageMap());
                return confirm(buildSaveBudgetSummaryConfirmationQuestion(mapping, form, request, response,
                        KeyConstants.QUESTION_SAVE_BUDGET_SUMMARY_FOR_RATE_AND_DATE_CHANGE), CONFIRM_SAVE_SUMMARY, DO_NOTHING);
            }
            else {
                return confirm(buildRecalculateBudgetConfirmationQuestion(mapping, form, request, response),
                        CONFIRM_SAVE_BUDGET_KEY, DO_NOTHING);
            }
        }
        else {

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
                updateBudgetPeriodDbVersion(budget);
                return super.save(mapping, form, request, response);
            }
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward saveAfterQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();
        
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
        budget.getBudgetSummaryService().adjustStartEndDatesForLineItems(budget);
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
        Budget budget = budgetForm.getBudget();
        BudgetPeriod newBudgetPeriod = budgetForm.getNewBudgetPeriod();
        if (getKcBusinessRulesEngine().applyRules(new AddBudgetPeriodEvent(budget, newBudgetPeriod))) {
            budget.getBudgetSummaryService().addBudgetPeriod(budget, newBudgetPeriod);
            /* set new period and calculate all periods */
            budgetForm.setNewBudgetPeriod(budget.getNewBudgetPeriod());
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
        Budget budget = budgetForm.getBudget();
        int delPeriod = getLineToDelete(request);
        int viewPeriod = 0;
        if (budgetForm.getViewBudgetPeriod() != null) {
            viewPeriod = budgetForm.getViewBudgetPeriod();
        }
        List <BudgetLineItem> budgetLineItems = budget.getBudgetPeriods().get(delPeriod).getBudgetLineItems();
        if (viewPeriod > 0 && ((delPeriod+1) == viewPeriod || budget.getBudgetPeriods().size() == viewPeriod)) {
            budgetForm.setViewBudgetPeriod(new Integer(1));
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
            budget.getBudgetSummaryService().deleteBudgetPeriod(budget, delPeriod);
            /* calculate all periods */
            getBudgetSummaryService().calculateBudget(budget);
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
        Budget budget = budgetForm.getBudget();
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
        BudgetForm budgetForm = (BudgetForm) form;
        boolean rulePassed = getKcBusinessRulesEngine().applyRules(
                new GenerateBudgetPeriodEvent(budgetForm.getBudget(), null));
        Budget budget = budgetForm.getBudget();
        if (rulePassed) {
            if (StringUtils.isBlank(budgetForm.getPrevOnOffCampusFlag())
                    || !budget.getOnOffCampusFlag().equals(budgetForm.getPrevOnOffCampusFlag())) {
                KcServiceLocator.getService(BudgetSummaryService.class).updateOnOffCampusFlag(budget,
                        budget.getOnOffCampusFlag());
            }
            /* generate all periods */
            getBudgetSummaryService().generateAllPeriods(budget);
        }
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

    @Override
    protected KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
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
     * @param budget
     */
    private void updateTotalCost(Budget budget) {
        ScaleTwoDecimal totalDirectCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalIndirectCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalCost = ScaleTwoDecimal.ZERO;
        for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
            budgetPeriod.setTotalCost(budgetPeriod.getTotalDirectCost().add(budgetPeriod.getTotalIndirectCost()));
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
     * @param budget
     * @return
     */
    private boolean isBudgetPeriodDateChanged(Budget budget) {
        for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
            if (budgetPeriod.getStartDate().compareTo(budgetPeriod.getOldStartDate()) != 0
                    || budgetPeriod.getEndDate().compareTo(budgetPeriod.getOldEndDate()) != 0) {
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

            for (Iterator i = GlobalVariables.getMessageMap().getErrorMessages().entrySet().iterator(); i.hasNext();) {
                Map.Entry e = (Map.Entry) i.next();

                AutoPopulatingList errorList = (AutoPopulatingList) e.getValue();
                for (Iterator j = errorList.iterator(); j.hasNext();) {
                    ErrorMessage em = (ErrorMessage) j.next();
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
        Budget budget = budgetForm.getBudget();
        String warningMessage = budget.getBudgetSummaryService().defaultWarningMessage(budget);
        
        if (StringUtils.isNotBlank(warningMessage)) {
            return confirm(buildDefaultBudgetPeriodsConfirmationQuestion(mapping, form, request, response,
                    warningMessage), CONFIRM_DEFAULT_BUDGET_PERIODS, "");
        } else {
                budget.getBudgetSummaryService().defaultBudgetPeriods(budget);
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
        budget.getBudgetSummaryService().defaultBudgetPeriods(budget);
        budget.getBudgetSummaryService().adjustStartEndDatesForLineItems(budget);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    
}
