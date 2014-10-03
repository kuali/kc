package org.kuali.coeus.propdev.impl.budget.nonpersonnel;


import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetControllerBase;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetForm;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.web.form.DialogResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/proposalBudget")
public class ProposalBudgetPeriodProjectCostController extends ProposalBudgetControllerBase {

	private static final String EDIT_NONPERSONNEL_PERIOD_DIALOG_ID = "PropBudget-EditNonPersonnelPeriod-Section";
	private static final String EDIT_NONPERSONNEL_PARTICIPANT_DIALOG_ID = "PropBudget-EditNonPersonnelPeriod-ParticipantSupportSection";
	protected static final String CONFIRM_PERIOD_CHANGES_DIALOG_ID = "PropBudget-ConfirmPeriodChangesDialog";
	

	@RequestMapping(params="methodToCall=assignLineItemToPeriod")
	public ModelAndView assignLineItemToPeriod(@RequestParam("budgetPeriodId") String budgetPeriodId, @ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		Budget budget = form.getBudget();
        Long currentTabBudgetPeriodId = Long.parseLong(budgetPeriodId);
		BudgetPeriod budgetPeriod = getBudgetPeriod(currentTabBudgetPeriodId, budget);
        DialogResponse dialogResponse = form.getDialogResponse(CONFIRM_PERIOD_CHANGES_DIALOG_ID);
        if(dialogResponse == null && budgetPeriod.getBudgetPeriod() > 1 && !isBudgetLineItemExists(budgetPeriod)) {
        	return getModelAndViewService().showDialog(CONFIRM_PERIOD_CHANGES_DIALOG_ID, true, form);
        }else {
            boolean confirmResetDefault = dialogResponse == null ? true : dialogResponse.getResponseAsBoolean();
            if(confirmResetDefault) {
        		form.getAddProjectBudgetLineItemHelper().reset();
        		form.getAddProjectBudgetLineItemHelper().setCurrentTabBudgetPeriod(budgetPeriod);
             }
    		return getModelAndViewService().getModelAndView(form);
        }
	}
	
	@RequestMapping(params="methodToCall=addLineItemToPeriod")
	public ModelAndView addLineItemToPeriod(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		Budget budget = form.getBudget();
		BudgetPeriod currentTabBudgetPeriod = form.getAddProjectBudgetLineItemHelper().getCurrentTabBudgetPeriod();
		BudgetLineItem newBudgetLineItem = form.getAddProjectBudgetLineItemHelper().getBudgetLineItem();
        getBudgetService().populateNewBudgetLineItem(newBudgetLineItem, currentTabBudgetPeriod);
        currentTabBudgetPeriod.getBudgetLineItems().add(newBudgetLineItem);            
        getBudgetCalculationService().populateCalculatedAmount(budget, newBudgetLineItem);
        getBudgetService().recalculateBudgetPeriod(budget, currentTabBudgetPeriod);
		form.getAddProjectBudgetLineItemHelper().reset();
		budget.getBudgetLineItems().add(newBudgetLineItem);
		return getModelAndViewService().getModelAndView(form);
	}

	@RequestMapping(params="methodToCall=editNonPersonnelPeriodDetails")
	public ModelAndView editNonPersonnelPeriodDetails(@RequestParam("budgetPeriodId") String budgetPeriodId, @ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
	    Budget budget = form.getBudget();
	    String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
        if (StringUtils.isNotEmpty(selectedLine)) {
        	form.getAddProjectBudgetLineItemHelper().reset();
        	BudgetLineItem editBudgetLineItem = form.getBudget().getBudgetLineItems().get(Integer.parseInt(selectedLine));
		    form.getAddProjectBudgetLineItemHelper().setBudgetLineItem(editBudgetLineItem);
		    form.getAddProjectBudgetLineItemHelper().setEditLineIndex(selectedLine);
		    Long currentTabBudgetPeriodId = Long.parseLong(budgetPeriodId);
		    BudgetPeriod budgetPeriod = getBudgetPeriod(currentTabBudgetPeriodId, budget);
		    form.getAddProjectBudgetLineItemHelper().setCurrentTabBudgetPeriod(budgetPeriod);
		    form.getAddProjectBudgetLineItemHelper().setBudgetCategoryTypeCode(editBudgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode());
	    }
    	return getModelAndViewService().showDialog(EDIT_NONPERSONNEL_PERIOD_DIALOG_ID, true, form);
	}

	@RequestMapping(params="methodToCall=saveBudgetLineItem")
	public ModelAndView saveBudgetLineItem(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
	    BudgetLineItem budgetLineItem = form.getAddProjectBudgetLineItemHelper().getBudgetLineItem();
	    setLineItemBudgetCategory(budgetLineItem);
		getCollectionControllerService().saveLine(form);
		return getModelAndViewService().getModelAndView(form);
	}
	
	@RequestMapping(params="methodToCall=saveAndApplyToLaterPeriods")
	public ModelAndView saveAndApplyToLaterPeriods(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		Budget budget = form.getBudget();
		BudgetPeriod currentTabBudgetPeriod = form.getAddProjectBudgetLineItemHelper().getCurrentTabBudgetPeriod();
	    BudgetLineItem budgetLineItem = form.getAddProjectBudgetLineItemHelper().getBudgetLineItem();
	    setLineItemBudgetCategory(budgetLineItem);
		getCollectionControllerService().saveLine(form);
	    getBudgetCalculationService().applyToLaterPeriods(budget,currentTabBudgetPeriod,budgetLineItem);
		return getModelAndViewService().getModelAndView(form);
	}
	
	@RequestMapping(params="methodToCall=syncToPeriodCostDirectLimit")
	public ModelAndView syncToPeriodCostDirectLimit(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		Budget budget = form.getBudget();
		BudgetPeriod currentTabBudgetPeriod = form.getAddProjectBudgetLineItemHelper().getCurrentTabBudgetPeriod();
	    BudgetLineItem budgetLineItem = form.getAddProjectBudgetLineItemHelper().getBudgetLineItem();
		getBudgetCalculationService().syncToPeriodDirectCostLimit(budget, currentTabBudgetPeriod, budgetLineItem);
		return getModelAndViewService().getModelAndView(form);
	}
	
	@RequestMapping(params="methodToCall=syncToPeriodCostLimit")
	public ModelAndView syncToPeriodCostLimit(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		Budget budget = form.getBudget();
		BudgetPeriod currentTabBudgetPeriod = form.getAddProjectBudgetLineItemHelper().getCurrentTabBudgetPeriod();
	    BudgetLineItem budgetLineItem = form.getAddProjectBudgetLineItemHelper().getBudgetLineItem();
		getBudgetCalculationService().syncToPeriodCostLimit(budget, currentTabBudgetPeriod, budgetLineItem);
 		return getModelAndViewService().getModelAndView(form);
	}
	
	@RequestMapping(params="methodToCall=editParticipantDetails")
	public ModelAndView editParticipantDetails(@RequestParam("budgetPeriodId") String budgetPeriodId, @ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		Budget budget = form.getBudget();
	    Long currentTabBudgetPeriodId = Long.parseLong(budgetPeriodId);
		BudgetPeriod currentTabBudgetPeriod = getBudgetPeriod(currentTabBudgetPeriodId, budget);
	    form.getAddProjectBudgetLineItemHelper().setCurrentTabBudgetPeriod(currentTabBudgetPeriod);
	    String editLineIndex = Integer.toString(budget.getBudgetPeriods().indexOf(currentTabBudgetPeriod));
	    form.getAddProjectBudgetLineItemHelper().setEditLineIndex(editLineIndex);
    	return getModelAndViewService().showDialog(EDIT_NONPERSONNEL_PARTICIPANT_DIALOG_ID, true, form);
	}
	
	@RequestMapping(params="methodToCall=saveParticipantDetails")
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

	private boolean isBudgetLineItemExists(BudgetPeriod budgetPeriod) {
		return budgetPeriod.getBudgetLineItems().size() > 0 ;
	}
	
}
