package org.kuali.coeus.propdev.impl.budget.nonpersonnel;


import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.common.budget.framework.nonpersonnel.ApplyToPeriodsBudgetEvent;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetDirectCostLimitEvent;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetPeriodCostLimitEvent;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.impl.nonpersonnel.BudgetExpensesRuleEvent;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetControllerBase;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetForm;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.web.form.DialogResponse;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/proposalBudget")
public class ProposalBudgetPeriodProjectCostController extends ProposalBudgetControllerBase {

	private static final String EDIT_NONPERSONNEL_PERIOD_DIALOG_ID = "PropBudget-NonPersonnelCostsPage-EditNonPersonnel-Dialog";
	private static final String EDIT_NONPERSONNEL_PARTICIPANT_DIALOG_ID = "PropBudget-NonPersonnelCostsPage-EditParticipantSupport-Dialog";
	protected static final String CONFIRM_PERIOD_CHANGES_DIALOG_ID = "PropBudget-ConfirmPeriodChangesDialog";
	protected static final String ADD_NONPERSONNEL_PERIOD_DIALOG_ID = "PropBudget-NonPersonnelCostsPage-AddNonPersonnel-Dialog";
	protected static final String CONFIRM_SYNC_TO_PERIOD_COST_LIMIT_DIALOG_ID = "PropBudget-NonPersonnelCosts-SyncToPeriodCostLimit";
	protected static final String CONFIRM_SYNC_TO_DIRECT_COST_LIMIT_DIALOG_ID = "PropBudget-NonPersonnelCosts-SyncToDirectCostLimit";
	

	@Transactional @RequestMapping(params="methodToCall=assignLineItemToPeriod")
	public ModelAndView assignLineItemToPeriod(@RequestParam("budgetPeriodId") String budgetPeriodId, @ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		ModelAndView modelAndView = getModelAndViewService().getModelAndView(form);
		Budget budget = form.getBudget();
        Long currentTabBudgetPeriodId = Long.parseLong(budgetPeriodId);
		BudgetPeriod budgetPeriod = getBudgetPeriod(currentTabBudgetPeriodId, budget);
        DialogResponse dialogResponse = form.getDialogResponse(CONFIRM_PERIOD_CHANGES_DIALOG_ID);
        if(dialogResponse == null && budgetPeriod.getBudgetPeriod() > 1 && !isBudgetLineItemExists(budget)) {
        	modelAndView = getModelAndViewService().showDialog(CONFIRM_PERIOD_CHANGES_DIALOG_ID, true, form);
        }else {
            boolean confirmResetDefault = dialogResponse == null ? true : dialogResponse.getResponseAsBoolean();
            if(confirmResetDefault) {
        		form.getAddProjectBudgetLineItemHelper().reset();
        		form.getAddProjectBudgetLineItemHelper().setCurrentTabBudgetPeriod(budgetPeriod);
        		modelAndView = getModelAndViewService().showDialog(ADD_NONPERSONNEL_PERIOD_DIALOG_ID, true, form);
             }
        }
 		return modelAndView;
	}
	
	@Transactional @RequestMapping(params="methodToCall=addLineItemToPeriod")
	public ModelAndView addLineItemToPeriod(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		Budget budget = form.getBudget();
		BudgetPeriod currentTabBudgetPeriod = form.getAddProjectBudgetLineItemHelper().getCurrentTabBudgetPeriod();
		BudgetLineItem newBudgetLineItem = form.getAddProjectBudgetLineItemHelper().getBudgetLineItem();
        getBudgetService().populateNewBudgetLineItem(newBudgetLineItem, currentTabBudgetPeriod);
        currentTabBudgetPeriod.getBudgetLineItems().add(newBudgetLineItem);            
        getBudgetCalculationService().populateCalculatedAmount(budget, newBudgetLineItem);
        getBudgetService().recalculateBudgetPeriod(budget, currentTabBudgetPeriod);
		form.getAddProjectBudgetLineItemHelper().reset();
	    validateBudgetExpenses(budget, currentTabBudgetPeriod);
		return getModelAndViewService().getModelAndView(form);
	}

	@Transactional @RequestMapping(params="methodToCall=editNonPersonnelPeriodDetails")
	public ModelAndView editNonPersonnelPeriodDetails(@RequestParam("budgetPeriodId") String budgetPeriodId, @ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
	    Budget budget = form.getBudget();
	    String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
        if (StringUtils.isNotEmpty(selectedLine)) {
		    Long currentTabBudgetPeriodId = Long.parseLong(budgetPeriodId);
		    BudgetPeriod budgetPeriod = getBudgetPeriod(currentTabBudgetPeriodId, budget);
        	form.getAddProjectBudgetLineItemHelper().reset();
        	BudgetLineItem editBudgetLineItem = form.getBudget().getBudgetLineItems().get(Integer.parseInt(selectedLine));
        	String editLineIndex = Integer.toString(budgetPeriod.getBudgetLineItems().indexOf(editBudgetLineItem));
		    form.getAddProjectBudgetLineItemHelper().setBudgetLineItem(getDataObjectService().copyInstance(editBudgetLineItem));
		    form.getAddProjectBudgetLineItemHelper().setEditLineIndex(editLineIndex);
		    form.getAddProjectBudgetLineItemHelper().setCurrentTabBudgetPeriod(budgetPeriod);
		    form.getAddProjectBudgetLineItemHelper().setBudgetCategoryTypeCode(editBudgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode());
	    }
    	return getModelAndViewService().showDialog(EDIT_NONPERSONNEL_PERIOD_DIALOG_ID, true, form);
	}

	@Transactional @RequestMapping(params="methodToCall=deleteBudgetLineItem")
	public ModelAndView deleteBudgetLineItem(@RequestParam("budgetPeriodId") String budgetPeriodId, @ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
	    Budget budget = form.getBudget();
	    String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
        if (StringUtils.isNotEmpty(selectedLine)) {
        	BudgetLineItem deletedBudgetLineItem = form.getBudget().getBudgetLineItems().get(Integer.parseInt(selectedLine));
		    Long currentTabBudgetPeriodId = Long.parseLong(budgetPeriodId);
		    BudgetPeriod budgetPeriod = getBudgetPeriod(currentTabBudgetPeriodId, budget);
		    budgetPeriod.getBudgetLineItems().remove(deletedBudgetLineItem);
		    validateBudgetExpenses(budget, budgetPeriod);
		    form.setAjaxReturnType("update-page");
	    }
		return getModelAndViewService().getModelAndView(form);
	}
	
	@Transactional @RequestMapping(params="methodToCall=saveBudgetLineItem")
	public ModelAndView saveBudgetLineItem(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
	    setEditedBudgetLineItem(form);
	    Budget budget = form.getBudget();
	    BudgetPeriod budgetPeriod = form.getAddProjectBudgetLineItemHelper().getCurrentTabBudgetPeriod();
	    validateBudgetExpenses(budget, budgetPeriod);
        return getModelAndViewService().getModelAndView(form);
	}
	
	protected void validateBudgetExpenses(Budget budget, BudgetPeriod budgetPeriod) {
	    getBudgetCalculationService().calculateBudgetPeriod(budget, budgetPeriod);
	    String errorPath = BudgetConstants.BudgetAuditRules.NON_PERSONNEL_COSTS.getPageId();
		getKcBusinessRulesEngine().applyRules(new BudgetExpensesRuleEvent(budget, errorPath));
	}
	
	@Transactional @RequestMapping(params="methodToCall=saveAndApplyToLaterPeriods")
	public ModelAndView saveAndApplyToLaterPeriods(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		Budget budget = form.getBudget();
		BudgetPeriod currentTabBudgetPeriod = form.getAddProjectBudgetLineItemHelper().getCurrentTabBudgetPeriod();
	    BudgetLineItem budgetLineItem = form.getAddProjectBudgetLineItemHelper().getBudgetLineItem();
	    setEditedBudgetLineItem(form);
	    getBudgetCalculationService().applyToLaterPeriods(budget,currentTabBudgetPeriod,budgetLineItem);
	    validateBudgetExpenses(budget, currentTabBudgetPeriod);
		return getModelAndViewService().getModelAndView(form);
	}
	
	private void setEditedBudgetLineItem(ProposalBudgetForm form) {
	    BudgetLineItem budgetLineItem = form.getAddProjectBudgetLineItemHelper().getBudgetLineItem();
	    BudgetPeriod budgetPeriod = form.getAddProjectBudgetLineItemHelper().getCurrentTabBudgetPeriod();
	    setLineItemBudgetCategory(budgetLineItem);
	    int editLineIndex = Integer.parseInt(form.getAddProjectBudgetLineItemHelper().getEditLineIndex());
		BudgetLineItem newBudgetLineItem = getDataObjectService().save(budgetLineItem);
		budgetPeriod.getBudgetLineItems().set(editLineIndex, newBudgetLineItem);
	}
	
	@Transactional @RequestMapping(params="methodToCall=syncToPeriodCostDirectLimit")
	public ModelAndView syncToPeriodCostDirectLimit(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		Budget budget = form.getBudget();
		BudgetPeriod currentTabBudgetPeriod = form.getAddProjectBudgetLineItemHelper().getCurrentTabBudgetPeriod();
	    int editLineIndex = Integer.parseInt(form.getAddProjectBudgetLineItemHelper().getEditLineIndex());
    	BudgetLineItem budgetLineItem = currentTabBudgetPeriod.getBudgetLineItems().get(editLineIndex);
        DialogResponse dialogResponse = form.getDialogResponse(CONFIRM_SYNC_TO_DIRECT_COST_LIMIT_DIALOG_ID);
        if(dialogResponse == null && currentTabBudgetPeriod.getTotalDirectCost().isGreaterThan(currentTabBudgetPeriod.getDirectCostLimit())) {
        	return getModelAndViewService().showDialog(CONFIRM_SYNC_TO_DIRECT_COST_LIMIT_DIALOG_ID, true, form);
        }else {
            boolean confirmResetDefault = dialogResponse == null ? true : dialogResponse.getResponseAsBoolean();
            if(confirmResetDefault) {
        	    BudgetLineItem editedBudgetLineItem = form.getAddProjectBudgetLineItemHelper().getBudgetLineItem();
        	    editedBudgetLineItem.setLineItemCost(budgetLineItem.getLineItemCost());
            	boolean rulePassed = getKcBusinessRulesEngine().applyRules(new ApplyToPeriodsBudgetEvent(budget, "addProjectBudgetLineItemHelper.budgetLineItem.", editedBudgetLineItem, 
            			currentTabBudgetPeriod)); 
            	rulePassed &= getKcBusinessRulesEngine().applyRules(new BudgetDirectCostLimitEvent(budget, currentTabBudgetPeriod, editedBudgetLineItem, 
            			"addProjectBudgetLineItemHelper.budgetLineItem."));
            	if(rulePassed) {
                    boolean syncComplete = getBudgetCalculationService().syncToPeriodDirectCostLimit(budget, currentTabBudgetPeriod, editedBudgetLineItem);
                    if(!syncComplete) {
                    	getGlobalVariableService().getMessageMap().putError("addProjectBudgetLineItemHelper.budgetLineItem.lineItemCost", 
                    			KeyConstants.INSUFFICIENT_AMOUNT_TO_PERIOD_DIRECT_COST_LIMIT_SYNC);
                    }
            	}
            }
        }
		return getModelAndViewService().getModelAndView(form);
	}
	
	@Transactional @RequestMapping(params="methodToCall=syncToPeriodCostLimit")
	public ModelAndView syncToPeriodCostLimit(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		Budget budget = form.getBudget();
		BudgetPeriod currentTabBudgetPeriod = form.getAddProjectBudgetLineItemHelper().getCurrentTabBudgetPeriod();
	    int editLineIndex = Integer.parseInt(form.getAddProjectBudgetLineItemHelper().getEditLineIndex());
    	BudgetLineItem budgetLineItem = currentTabBudgetPeriod.getBudgetLineItems().get(editLineIndex);
        DialogResponse dialogResponse = form.getDialogResponse(CONFIRM_SYNC_TO_PERIOD_COST_LIMIT_DIALOG_ID);
        if(dialogResponse == null && currentTabBudgetPeriod.getTotalCost().isGreaterThan(currentTabBudgetPeriod.getTotalCostLimit())) {
        	return getModelAndViewService().showDialog(CONFIRM_SYNC_TO_PERIOD_COST_LIMIT_DIALOG_ID, true, form);
        }else {
            boolean confirmResetDefault = dialogResponse == null ? true : dialogResponse.getResponseAsBoolean();
            if(confirmResetDefault) {
        	    BudgetLineItem editedBudgetLineItem = form.getAddProjectBudgetLineItemHelper().getBudgetLineItem();
        	    editedBudgetLineItem.setLineItemCost(budgetLineItem.getLineItemCost());
            	boolean rulePassed = getKcBusinessRulesEngine().applyRules(new ApplyToPeriodsBudgetEvent(budget, "addProjectBudgetLineItemHelper.budgetLineItem.", editedBudgetLineItem, 
            			currentTabBudgetPeriod)); 
            	rulePassed &= getKcBusinessRulesEngine().applyRules(new BudgetPeriodCostLimitEvent(budget, currentTabBudgetPeriod, editedBudgetLineItem, 
            			"addProjectBudgetLineItemHelper.budgetLineItem."));
            	if(rulePassed) {
                    boolean syncComplete = getBudgetCalculationService().syncToPeriodCostLimit(budget, currentTabBudgetPeriod, editedBudgetLineItem);
                    if(!syncComplete) {
                    	getGlobalVariableService().getMessageMap().putError("addProjectBudgetLineItemHelper.budgetLineItem.lineItemCost", 
                    			KeyConstants.INSUFFICIENT_AMOUNT_TO_SYNC);
                    }
            	}
            }
        }
 		return getModelAndViewService().getModelAndView(form);
	}
	
	@Transactional @RequestMapping(params="methodToCall=editParticipantDetails")
	public ModelAndView editParticipantDetails(@RequestParam("budgetPeriodId") String budgetPeriodId, @ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		Budget budget = form.getBudget();
	    Long currentTabBudgetPeriodId = Long.parseLong(budgetPeriodId);
		BudgetPeriod currentTabBudgetPeriod = getBudgetPeriod(currentTabBudgetPeriodId, budget);
	    form.getAddProjectBudgetLineItemHelper().setCurrentTabBudgetPeriod(currentTabBudgetPeriod);
	    String editLineIndex = Integer.toString(budget.getBudgetPeriods().indexOf(currentTabBudgetPeriod));
	    form.getAddProjectBudgetLineItemHelper().setEditLineIndex(editLineIndex);
    	return getModelAndViewService().showDialog(EDIT_NONPERSONNEL_PARTICIPANT_DIALOG_ID, true, form);
	}
	
	@Transactional @RequestMapping(params="methodToCall=saveParticipantDetails")
	public ModelAndView saveParticipantDetails(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		BudgetPeriod currentTabBudgetPeriod = form.getAddProjectBudgetLineItemHelper().getCurrentTabBudgetPeriod();
		getDataObjectService().save(currentTabBudgetPeriod);
 		return getModelAndViewService().getModelAndView(form);
	}
	
	private BudgetPeriod getBudgetPeriod(Long currentTabBudgetPeriodId, Budget budget) {
        for(BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
        	if(budgetPeriod.getBudgetPeriodId().equals(currentTabBudgetPeriodId)) {
        		return budgetPeriod;
        	}
        }
        return null;
	}
	
	private void setLineItemBudgetCategory(BudgetLineItem budgetLineItem) {
		getDataObjectService().wrap(budgetLineItem).fetchRelationship("costElementBO");
	    budgetLineItem.setBudgetCategoryCode(budgetLineItem.getCostElementBO().getBudgetCategoryCode());
		getDataObjectService().wrap(budgetLineItem).fetchRelationship("budgetCategory");
	}

	private boolean isBudgetLineItemExists(Budget budget) {
		boolean lineItemExists = false;
		for(BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			if(budgetPeriod.getBudgetPeriod() > 1 && budgetPeriod.getBudgetLineItems().size() > 0) {
				lineItemExists = true;
				break;
			}
		}
		return lineItemExists;
	}
	
}
