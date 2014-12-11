package org.kuali.coeus.propdev.impl.budget.person;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.*;
import org.kuali.coeus.common.framework.person.PersonTypeConstants;
import org.kuali.coeus.common.view.wizard.framework.WizardControllerService;
import org.kuali.coeus.common.view.wizard.framework.WizardResultsDto;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetControllerBase;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetForm;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.web.form.DialogResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/proposalBudget")
public class ProposalBudgetProjectPersonnelController extends ProposalBudgetControllerBase {

    @Autowired
    @Qualifier("wizardControllerService")
    private WizardControllerService wizardControllerService;

	@Autowired
	@Qualifier("budgetPersonService")
	private BudgetPersonService budgetPersonService;
	
	@Autowired
	@Qualifier("budgetPersonnelBudgetService")
	BudgetPersonnelBudgetService budgetPersonnelBudgetService;

	private static final String EDIT_PROJECT_PERSONNEL_DIALOG_ID = "PropBudget-EditPersonnel-Section";
	private static final String EDIT_PERSONNEL_PERIOD_DIALOG_ID = "PropBudget-EditPersonnelPeriod-Section";
	private static final String EDIT_LINE_ITEM_DETAILS_DIALOG_ID = "PropBudget-AssignPersonnelToPeriodsPage-DetailsAndRates";
	private static final String CONFIRM_PERSONNEL_PERIOD_CHANGES_DIALOG_ID = "PropBudget-ConfirmPeriodChangesDialog";
	private static final String ADD_PERSONNEL_PERIOD_DIALOG_ID = "PropBudget-AssignPersonnelToPeriodsPage-AddPersonnel";
	private static final String ADD_PROJECT_PERSONNEL_DIALOG_ID = "PropBudget-ProjectPersonnelPage-Wizard";

	@Transactional @RequestMapping(params="methodToCall=searchProjectPersonnel")
	public ModelAndView searchProjectPersonnel(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		if(!StringUtils.isEmpty(form.getAddProjectPersonnelHelper().getLineType())) {
		       form.getAddProjectPersonnelHelper().getResults().clear();
		       form.getAddProjectPersonnelHelper().setResults(getWizardControllerService().performWizardSearch(form.getAddProjectPersonnelHelper().getLookupFieldValues(),form.getAddProjectPersonnelHelper().getLineType()));
		}
       return getModelAndViewService().getModelAndView(form);
	}

	@Transactional @RequestMapping(params="methodToCall=addProjectPersonnel")
	public ModelAndView addProjectPersonnel(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
	   if(StringUtils.equals(form.getAddProjectPersonnelHelper().getLineType(), PersonTypeConstants.TBN.getCode())) {
	       for (TbnPerson person : form.getAddProjectPersonnelHelper().getTbnPersons()) {
		       for (int index=0 ; index < person.getQuantity();  index++) {
		    	   BudgetPerson newPerson = new BudgetPerson(person);
	    	       getBudgetPersonService().addBudgetPerson(form.getBudget(), newPerson);
		       }
	       }
	   }else {
	       for (Object object : form.getAddProjectPersonnelHelper().getResults()) {
               WizardResultsDto wizardResult = (WizardResultsDto) object;
               BudgetPerson newBudgetPerson = new BudgetPerson();
	           if (wizardResult.isSelected() == true) {
                   if (wizardResult.isSelected() == true) {
                       if (wizardResult.getKcPerson() != null) {
                           newBudgetPerson.setPersonId(wizardResult.getKcPerson().getPersonId());
                           newBudgetPerson.setPersonName(wizardResult.getKcPerson().getFullName());
                           newBudgetPerson.setUserName(wizardResult.getKcPerson().getUserName());
                       } else if (wizardResult.getRolodex() != null) {
                           newBudgetPerson.setRolodexId(wizardResult.getRolodex().getRolodexId());
                           newBudgetPerson.setPersonName(wizardResult.getRolodex().getFullName());
                       }
                       getBudgetPersonService().addBudgetPerson(form.getBudget(), newBudgetPerson);
                   }
	           }
	       }
	   }
       form.getAddProjectPersonnelHelper().reset();
       return getModelAndViewService().getModelAndView(form);
	}

	@Transactional @RequestMapping(params="methodToCall=editPersonDetails")
	public ModelAndView editPersonDetails(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
	    String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
        if (StringUtils.isNotEmpty(selectedLine)) {
		    BudgetPerson editBudgetPerson = new BudgetPerson();
		    editBudgetPerson = getDataObjectService().copyInstance(form.getBudget().getBudgetPerson(Integer.parseInt(selectedLine)));
	        form.getAddProjectPersonnelHelper().setEditBudgetPerson(editBudgetPerson);
			form.getAddProjectPersonnelHelper().setEditLineIndex(selectedLine);
	    }
    	return getModelAndViewService().showDialog(EDIT_PROJECT_PERSONNEL_DIALOG_ID, true, form);
	}

	@Transactional @RequestMapping(params="methodToCall=prepareAddProjectPersonnel")
	public ModelAndView prepareAddProjectPersonnel(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
        form.getAddProjectPersonnelHelper().setLineType(PersonTypeConstants.EMPLOYEE.getCode());
        return getModelAndViewService().showDialog(ADD_PROJECT_PERSONNEL_DIALOG_ID,true,form);
	}
	
	@Transactional @RequestMapping(params="methodToCall=updatePersonDetails")
	public ModelAndView updatePersonDetails(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
	    int selectedLine = Integer.parseInt(form.getAddProjectPersonnelHelper().getEditLineIndex());
	    BudgetPerson editedBudgetPerson = form.getAddProjectPersonnelHelper().getEditBudgetPerson();
	    getBudgetPersonService().refreshBudgetPerson(editedBudgetPerson);
	    boolean rulePassed = getKcBusinessRulesEngine().applyRules(new BudgetSaveProjectPersonnelEvent(form.getBudget(), editedBudgetPerson,
	    		"addProjectPersonnelHelper.editBudgetPerson."));
	    if(rulePassed) {
		    form.getBudget().getBudgetPersons().set(selectedLine, form.getAddProjectPersonnelHelper().getEditBudgetPerson());
		    super.save(form);
	    }
	    return getModelAndViewService().getModelAndView(form);
	}
	
	@Transactional @RequestMapping(params="methodToCall=calculatePersonSalaryDetails")
	public ModelAndView calculatePersonSalaryDetails(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		calculatePersonSalary(form);
	    return getModelAndViewService().getModelAndView(form);
	}
	
	@Transactional @RequestMapping(params="methodToCall=syncFromProposal")
	public ModelAndView syncFromProposal(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
	    getBudgetPersonService().synchBudgetPersonsToProposal(form.getBudget());
	    return getModelAndViewService().getModelAndView(form);
	}

	@Transactional @RequestMapping(params="methodToCall=assignPersonnelToPeriod")
	public ModelAndView assignPersonnelToPeriod(@RequestParam("budgetPeriodId") String budgetPeriodId, @ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		ModelAndView modelAndView = getModelAndViewService().getModelAndView(form);
		Budget budget = form.getBudget();
        Long currentTabBudgetPeriodId = Long.parseLong(budgetPeriodId);
		BudgetPeriod budgetPeriod = getBudgetPeriod(currentTabBudgetPeriodId, budget);
        DialogResponse dialogResponse = form.getDialogResponse(CONFIRM_PERSONNEL_PERIOD_CHANGES_DIALOG_ID);
        if(dialogResponse == null && budgetPeriod.getBudgetPeriod() > 1 && !isPersonnelBudgetExists(budget)) {
        	modelAndView = getModelAndViewService().showDialog(CONFIRM_PERSONNEL_PERIOD_CHANGES_DIALOG_ID, true, form);
        }else {
            boolean confirmResetDefault = dialogResponse == null ? true : dialogResponse.getResponseAsBoolean();
            if(confirmResetDefault) {
        		form.getAddProjectPersonnelHelper().reset();
        		form.getAddProjectPersonnelHelper().setCurrentTabBudgetPeriod(budgetPeriod);
        		setBudgetPeriodStartDateAndEndDateOnLineItems(form, budgetPeriod);
        		modelAndView = getModelAndViewService().showDialog(ADD_PERSONNEL_PERIOD_DIALOG_ID, true, form);
            }
        }
		return modelAndView;
	}
	
	private void setBudgetPeriodStartDateAndEndDateOnLineItems(ProposalBudgetForm form, BudgetPeriod budgetPeriod) {
		form.getAddProjectPersonnelHelper().getBudgetLineItem().setStartDate(budgetPeriod.getStartDate());
		form.getAddProjectPersonnelHelper().getBudgetLineItem().setEndDate(budgetPeriod.getEndDate());
		form.getAddProjectPersonnelHelper().getBudgetPersonnelDetail().setStartDate(budgetPeriod.getStartDate());
		form.getAddProjectPersonnelHelper().getBudgetPersonnelDetail().setEndDate(budgetPeriod.getEndDate());
	}
	
	private boolean isPersonnelBudgetExists(Budget budget) {
		boolean lineItemExists = false;
		for(BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			if(budgetPeriod.getBudgetPeriod() > 1 ) {
				for(BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
					if(budgetLineItem.getBudgetPersonnelDetailsList().size() > 0) {
						lineItemExists = true;
						break;
					}
				}
			}
		}
		return lineItemExists;
	}
	
	private BudgetPeriod getBudgetPeriod(Long currentTabBudgetPeriodId, Budget budget) {
        for(BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
        	if(budgetPeriod.getBudgetPeriodId().equals(currentTabBudgetPeriodId)) {
        		return budgetPeriod;
        	}
        }
        return null;
	}

	@Transactional @RequestMapping(params="methodToCall=addPersonnelToPeriod")
	public ModelAndView addPersonnelToPeriod(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		Budget budget = form.getBudget();
		BudgetPeriod currentTabBudgetPeriod = form.getAddProjectPersonnelHelper().getCurrentTabBudgetPeriod();
		BudgetLineItem newBudgetLineItem = form.getAddProjectPersonnelHelper().getBudgetLineItem();
		BudgetPersonnelDetails newBudgetPersonnelDetail = form.getAddProjectPersonnelHelper().getBudgetPersonnelDetail();
		syncLineItemDates(newBudgetLineItem, newBudgetPersonnelDetail);
		refreshBudgetLineItemReferences(newBudgetLineItem);
		setBudgetLineItemGroupName(form, newBudgetLineItem);
		if(!isSummaryPersonnel(newBudgetPersonnelDetail)) {
			newBudgetPersonnelDetail.setBudget(budget);
			newBudgetPersonnelDetail.setBudgetId(budget.getBudgetId());
			getBudgetPersonnelBudgetService().refreshBudgetPersonnelLineItemReferences(newBudgetPersonnelDetail);
		}
		boolean rulePassed = isAddRulePassed(budget, currentTabBudgetPeriod, newBudgetLineItem, newBudgetPersonnelDetail);
		if(rulePassed) {
			getBudgetPersonnelBudgetService().addBudgetPersonnelToPeriod(currentTabBudgetPeriod, newBudgetLineItem, newBudgetPersonnelDetail);
			form.getAddProjectPersonnelHelper().reset();
		}
		return getModelAndViewService().getModelAndView(form);
	}

	private void setBudgetLineItemGroupName(ProposalBudgetForm form, BudgetLineItem newBudgetLineItem) {
		String groupName = form.getAddProjectPersonnelHelper().getBudgetPersonGroupName();
		if(!StringUtils.equals(groupName, BudgetConstants.BUDGET_PERSONNEL_NEW_GROUP_NAME)) {
			newBudgetLineItem.setGroupName(groupName);
		}
	}
	
    private void refreshBudgetLineItemReferences(BudgetLineItem newBudgetLineItem) {
		if(StringUtils.isNotEmpty(newBudgetLineItem.getCostElement())) {
			getDataObjectService().wrap(newBudgetLineItem).fetchRelationship("costElementBO");
		}
        newBudgetLineItem.setBudgetCategoryCode(newBudgetLineItem.getCostElementBO().getBudgetCategoryCode());
		if(StringUtils.isNotEmpty(newBudgetLineItem.getBudgetCategoryCode())) {
			getDataObjectService().wrap(newBudgetLineItem).fetchRelationship("budgetCategory");
		}
    }
	
    private void syncLineItemDates(BudgetLineItem budgetLineItem, BudgetPersonnelDetails budgetPersonnelDetails) {
		budgetLineItem.setStartDate(budgetPersonnelDetails.getStartDate());
		budgetLineItem.setEndDate(budgetPersonnelDetails.getEndDate());
	}
	
	private boolean isSaveRulePassed(Budget budget, BudgetPeriod budgetPeriod, BudgetLineItem newBudgetLineItem, BudgetPersonnelDetails newBudgetPersonnelDetail) {
		return getKcBusinessRulesEngine().applyRules(new BudgetSavePersonnelPeriodEvent(budget, budgetPeriod, newBudgetLineItem, newBudgetPersonnelDetail, 
				"addProjectPersonnelHelper.budgetPersonnelDetail."));
	}

    private boolean isAddRulePassed(Budget budget, BudgetPeriod budgetPeriod, BudgetLineItem newBudgetLineItem, BudgetPersonnelDetails newBudgetPersonnelDetail) {
        return getKcBusinessRulesEngine().applyRules(new BudgetAddPersonnelPeriodEvent(budget, budgetPeriod, newBudgetLineItem, newBudgetPersonnelDetail,
                "addProjectPersonnelHelper.budgetPersonnelDetail."));
    }

    @Transactional @RequestMapping(params="methodToCall=editPersonPeriodDetails")
	public ModelAndView editPersonPeriodDetails(@RequestParam("budgetPeriodId") String budgetPeriodId, @ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
	    Budget budget = form.getBudget();
	    String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
        if (StringUtils.isNotEmpty(selectedLine)) {
        	form.getAddProjectPersonnelHelper().reset();
        	BudgetPersonnelDetails budgetPersonnelDetails = form.getBudget().getBudgetPersonnelDetails().get(Integer.parseInt(selectedLine));
    		BudgetLineItem budgetLineItem = budgetPersonnelDetails.getBudgetLineItem();
		    form.getAddProjectPersonnelHelper().setEditLineIndex(Integer.toString(budgetLineItem.getBudgetPersonnelDetailsList().indexOf(budgetPersonnelDetails)));
		    form.getAddProjectPersonnelHelper().setBudgetLineItem(budgetLineItem);
		    form.getAddProjectPersonnelHelper().setBudgetPersonnelDetail(getDataObjectService().copyInstance(budgetPersonnelDetails));
		    Long currentTabBudgetPeriodId = Long.parseLong(budgetPeriodId);
		    BudgetPeriod budgetPeriod = getBudgetPeriod(currentTabBudgetPeriodId, budget);
		    form.getAddProjectPersonnelHelper().setCurrentTabBudgetPeriod(budgetPeriod);
	    }
    	return getModelAndViewService().showDialog(EDIT_PERSONNEL_PERIOD_DIALOG_ID, true, form);
	}

	@Transactional @RequestMapping(params="methodToCall=savePersonPeriodDetails")
	public ModelAndView savePersonPeriodDetails(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
	    Budget budget = form.getBudget();
	    int editLineIndex = Integer.parseInt(form.getAddProjectPersonnelHelper().getEditLineIndex());
		BudgetPeriod budgetPeriod = form.getAddProjectPersonnelHelper().getCurrentTabBudgetPeriod();
		BudgetPersonnelDetails editBudgetPersonnel = form.getAddProjectPersonnelHelper().getBudgetPersonnelDetail();
		BudgetLineItem budgetLineItem = form.getAddProjectPersonnelHelper().getBudgetLineItem();
	    int budgetLineItemIndex = budgetPeriod.getBudgetLineItems().indexOf(budgetLineItem);
		syncLineItemDates(budgetLineItem, editBudgetPersonnel);
		if(isSummaryPersonnel(editBudgetPersonnel)) {
			budgetLineItem.setLineItemCost(editBudgetPersonnel.getSalaryRequested());
		}else {
			getDataObjectService().wrap(editBudgetPersonnel).fetchRelationship("budgetPeriodType");
		}
		boolean rulePassed = isSaveRulePassed(budget, budgetPeriod, budgetLineItem, editBudgetPersonnel);
		if(rulePassed) {
			calculatePersonnelLineItem(form, true);
			budgetLineItem.getBudgetPersonnelDetailsList().set(editLineIndex, editBudgetPersonnel);
			BudgetLineItem newBudgetLineItem = getDataObjectService().save(budgetLineItem);
			budgetPeriod.getBudgetLineItems().set(budgetLineItemIndex, newBudgetLineItem);
		}
		return getModelAndViewService().getModelAndView(form);
	}

	protected boolean isSummaryPersonnel(BudgetPersonnelDetails budgetPersonnelDetails) {
		return budgetPersonnelDetails.getPersonSequenceNumber().equals(BudgetConstants.BudgetPerson.SUMMARYPERSON.getPersonSequenceNumber());
	}
	
	@Transactional @RequestMapping(params="methodToCall=calculatePersonnelPeriodLineItem")
	public ModelAndView calculatePersonnelPeriodLineItem(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		calculatePersonnelLineItem(form, false);
		return getModelAndViewService().getModelAndView(form);
	}
	
	protected void calculatePersonnelLineItem(ProposalBudgetForm form, boolean ruleChecked) {
	    Budget budget = form.getBudget();
		BudgetPeriod budgetPeriod = form.getAddProjectPersonnelHelper().getCurrentTabBudgetPeriod();
	    String selectedLine = form.getAddProjectPersonnelHelper().getEditLineIndex();
	    BudgetPersonnelDetails budgetPersonnelDetails = form.getAddProjectPersonnelHelper().getBudgetPersonnelDetail();
	    BudgetLineItem budgetLineItem = budgetPersonnelDetails.getBudgetLineItem();
        BudgetPersonnelDetails editBudgetPersonnel = form.getAddProjectPersonnelHelper().getBudgetPersonnelDetail();
        syncLineItemDates(budgetLineItem, editBudgetPersonnel);
		boolean rulePassed = ruleChecked ? ruleChecked : isSaveRulePassed(budget, budgetPeriod, budgetLineItem, budgetPersonnelDetails);
		if(rulePassed) {
		    getBudgetPersonnelBudgetService().calculateBudgetPersonnelLineItem(budget, budgetLineItem, budgetPersonnelDetails, Integer.parseInt(selectedLine));
		}
	}

	protected void calculatePersonSalary(ProposalBudgetForm form) {
	    Budget budget = form.getBudget();
	    int selectedLine = Integer.parseInt(form.getAddProjectPersonnelHelper().getEditLineIndex());
        List<BudgetPersonSalaryDetails> budgetPersonSalaryDetails = new ArrayList<BudgetPersonSalaryDetails>();
        BudgetPerson budgetPerson = form.getAddProjectPersonnelHelper().getEditBudgetPerson();
        budgetPersonSalaryDetails =  getBudgetPersonnelBudgetService().calculatePersonSalary(budget, selectedLine);        
        budgetPerson.setBudgetPersonSalaryDetails(budgetPersonSalaryDetails);
	}
	
	@Transactional @RequestMapping(params="methodToCall=calculateCurrentPeriod")
	public ModelAndView calculateCurrentPeriod(@RequestParam("budgetPeriodId") String budgetPeriodId, @ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		Budget budget = form.getBudget();
	    Long currentTabBudgetPeriodId = Long.parseLong(budgetPeriodId);
		BudgetPeriod currentTabBudgetPeriod = getBudgetPeriod(currentTabBudgetPeriodId, budget);
	    form.getAddProjectPersonnelHelper().setCurrentTabBudgetPeriod(currentTabBudgetPeriod);
		getBudgetPersonnelBudgetService().calculateCurrentBudgetPeriod(currentTabBudgetPeriod);
		return getModelAndViewService().getModelAndView(form);
	}

	@Transactional @RequestMapping(params="methodToCall=editBudgetDetailsAndRates")
	public ModelAndView editBudgetDetailsAndRates(@RequestParam("budgetPeriodId") String budgetPeriodId, @RequestParam("lineItemGroupKey") String lineItemGroupKey, @ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		Budget budget = form.getBudget();
	    Long currentTabBudgetPeriodId = Long.parseLong(budgetPeriodId);
		BudgetPeriod currentTabBudgetPeriod = getBudgetPeriod(currentTabBudgetPeriodId, budget);
	    BudgetLineItem budgetLineItem = getSelectedBudgetLineItem(lineItemGroupKey, currentTabBudgetPeriod);
	    form.getAddProjectPersonnelHelper().setBudgetLineItem(budgetLineItem);
	    form.getAddProjectPersonnelHelper().setCurrentTabBudgetPeriod(currentTabBudgetPeriod);
    	return getModelAndViewService().showDialog(EDIT_LINE_ITEM_DETAILS_DIALOG_ID, true, form);
	}

	@Transactional @RequestMapping(params="methodToCall=applyToLaterPeriods")
	public ModelAndView applyToLaterPeriods(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		Budget budget = form.getBudget();
		BudgetPeriod currentTabBudgetPeriod = form.getAddProjectPersonnelHelper().getCurrentTabBudgetPeriod();
	    BudgetLineItem budgetLineItem = form.getAddProjectPersonnelHelper().getBudgetLineItem();
	    getBudgetCalculationService().applyToLaterPeriods(budget,currentTabBudgetPeriod,budgetLineItem);
		return getModelAndViewService().getModelAndView(form);
	}

	@Transactional @RequestMapping(params="methodToCall=deletePersonnelLineItem")
	public ModelAndView deletePersonnelLineItem(@RequestParam("budgetPeriodId") String budgetPeriodId, @ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
	    Budget budget = form.getBudget();
	    String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
        if (StringUtils.isNotEmpty(selectedLine)) {
        	BudgetPersonnelDetails deletedPersonnelLineItem = form.getBudget().getBudgetPersonnelDetails().get(Integer.parseInt(selectedLine));
		    Long currentTabBudgetPeriodId = Long.parseLong(budgetPeriodId);
		    BudgetPeriod budgetPeriod = getBudgetPeriod(currentTabBudgetPeriodId, budget);
		    BudgetLineItem budgetLineItem = deletedPersonnelLineItem.getBudgetLineItem();
		    if(!isSummaryPersonnel(deletedPersonnelLineItem)) {
			    budgetLineItem.getBudgetPersonnelDetailsList().remove(deletedPersonnelLineItem);
		    }
		    if(budgetLineItem.getBudgetPersonnelDetailsList().size() == 0) {
		    	budgetPeriod.getBudgetLineItems().remove(budgetLineItem);
		    }
	    }
		return getModelAndViewService().getModelAndView(form);
	}

	private BudgetLineItem getSelectedBudgetLineItem(String lineItemGroupKey, BudgetPeriod budgetPeriod) {
        for(BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
        	if(budgetLineItem.getLineItemGroupDescription().equals(lineItemGroupKey)) {
        		return budgetLineItem;
        	}
        }
        return null;
	}
	
    public WizardControllerService getWizardControllerService() {
        return wizardControllerService;
    }

    public void setWizardControllerService(WizardControllerService wizardControllerService) {
        this.wizardControllerService = wizardControllerService;
    }

    public BudgetPersonService getBudgetPersonService() {
		return budgetPersonService;
	}

	public void setBudgetPersonService(BudgetPersonService budgetPersonService) {
		this.budgetPersonService = budgetPersonService;
	}

	public BudgetPersonnelBudgetService getBudgetPersonnelBudgetService() {
		return budgetPersonnelBudgetService;
	}

	public void setBudgetPersonnelBudgetService(
			BudgetPersonnelBudgetService budgetPersonnelBudgetService) {
		this.budgetPersonnelBudgetService = budgetPersonnelBudgetService;
	}

}
