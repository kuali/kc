package org.kuali.coeus.propdev.impl.budget.core;

import static org.kuali.kra.infrastructure.KeyConstants.QUESTION_RECALCULATE_BUDGET_CONFIRMATION;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.period.GenerateBudgetPeriodEvent;
import org.kuali.coeus.common.budget.framework.summary.BudgetSummaryService;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.kuali.rice.krad.web.form.DialogResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/proposalBudget")
public class ProposalBudgetRateAndPeriodController extends ProposalBudgetControllerBase {

	private static final String CONFIRM_PERIOD_CHANGES_DIALOG_ID = "PropBudget-PeriodsPage-ConfirmPeriodChangesDialog";

    @Autowired
    @Qualifier("budgetSummaryService")
    private BudgetSummaryService budgetSummaryService;

	@MethodAccessible
    @RequestMapping(params="methodToCall=resetToBudgetPeriodDefault")
    public ModelAndView resetToBudgetPeriodDefault(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentBudgetExt budget = form.getBudget();
        String warningMessage = getBudgetSummaryService().defaultWarningMessage(budget);
        DialogResponse dialogResponse = form.getDialogResponse(CONFIRM_PERIOD_CHANGES_DIALOG_ID);
        if (StringUtils.isNotBlank(warningMessage)) {
            if(dialogResponse == null) {
            	form.setDefaultBudgetPeriodWarningMessage(warningMessage);
            	return getModelAndViewService().showDialog(CONFIRM_PERIOD_CHANGES_DIALOG_ID, true, form);
            }else {
                boolean confirmResetDefault = dialogResponse.getResponseAsBoolean();
                if(confirmResetDefault) {
                	getBudgetSummaryService().defaultBudgetPeriods(budget);
                	getBudgetSummaryService().adjustStartEndDatesForLineItems(budget);
                }
            }
        }else {
        	getBudgetSummaryService().defaultBudgetPeriods(budget);
        }
        return getModelAndViewService().getModelAndView(form);
    }    
	
	@MethodAccessible
    @RequestMapping(params="methodToCall=recalculateBudgetWithChanges")
    public ModelAndView recalculateBudgetWithChanges(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentBudgetExt budget = form.getBudget();
    	getBudgetSummaryService().updateOnOffCampusFlag(budget, budget.getOnOffCampusFlag());
    	getBudgetSummaryService().calculateBudget(budget);
        return getModelAndViewService().getModelAndView(form);
    }    
	
    @RequestMapping(params="methodToCall=saveBudgetPeriod")
    public ModelAndView saveBudgetPeriod(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
    	Budget budget = form.getBudget();
    	getBudgetCalculationService().updateBudgetTotalCost(budget);
		return super.saveLine(form);
    }
		
	@MethodAccessible
    @RequestMapping(params="methodToCall=generateAllPeriods")
    public ModelAndView generateAllPeriods(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Budget budget = form.getBudget();
        boolean rulePassed = getKcBusinessRulesEngine().applyRules(
                new GenerateBudgetPeriodEvent(form.getBudget(), null));
        if(rulePassed) {
            DialogResponse dialogResponse = form.getDialogResponse(CONFIRM_PERIOD_CHANGES_DIALOG_ID);
            Budget originalBudget = getOriginalBudget(form);
        	if(dialogResponse == null && isRateTypeChanged(originalBudget, budget)) {
        		form.setDefaultBudgetPeriodWarningMessage(getKualiConfigurationService().getPropertyValueAsString(QUESTION_RECALCULATE_BUDGET_CONFIRMATION));
            	return getModelAndViewService().showDialog(CONFIRM_PERIOD_CHANGES_DIALOG_ID, true, form);
        	}
            boolean confirmRecalculate = dialogResponse !=null ? dialogResponse.getResponseAsBoolean() : true;
            if(confirmRecalculate) {
            	getBudgetSummaryService().updateOnOffCampusFlag(budget, budget.getOnOffCampusFlag());
                getBudgetSummaryService().generateAllPeriods(budget);
            }
        }
        return getModelAndViewService().getModelAndView(form);
	}

    private Budget getOriginalBudget(ProposalBudgetForm form) {
    	return getDataObjectService().find(Budget.class, form.getBudget().getBudgetId());
    }

	private boolean isRateTypeChanged(Budget originalBudget, Budget currentBudget) {
        return (!StringUtils.equalsIgnoreCase(originalBudget.getOhRateClassCode(), currentBudget.getOhRateClassCode())
            || !StringUtils.equalsIgnoreCase(originalBudget.getUrRateClassCode(), currentBudget.getUrRateClassCode()));
    }
 	
    public BudgetSummaryService getBudgetSummaryService() {
        return budgetSummaryService;
    }

    public void setBudgetSummaryService(BudgetSummaryService budgetSummaryService) {
        this.budgetSummaryService = budgetSummaryService;
    }

}
