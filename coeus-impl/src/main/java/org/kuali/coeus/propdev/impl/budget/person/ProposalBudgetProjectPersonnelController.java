/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.budget.person;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.Interval;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.*;
import org.kuali.coeus.common.budget.impl.nonpersonnel.BudgetExpensesRuleEvent;
import org.kuali.coeus.common.framework.person.PersonTypeConstants;
import org.kuali.coeus.common.view.wizard.framework.WizardControllerService;
import org.kuali.coeus.common.view.wizard.framework.WizardResultsDto;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetControllerBase;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.uif.UifConstants;
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

    private static final String ADD_PERSONNEL_HELPER_ERROR_PATH = "addProjectPersonnelHelper.editBudgetPersonnelDetail.";

	@Autowired
    @Qualifier("wizardControllerService")
    private WizardControllerService wizardControllerService;

	@Autowired
	@Qualifier("budgetPersonService")
	private BudgetPersonService budgetPersonService;
	
	@Autowired
	@Qualifier("budgetPersonnelBudgetService")
	BudgetPersonnelBudgetService budgetPersonnelBudgetService;

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

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
                       newPerson.setPersonName(newPerson.getPersonName() + " - " + (index +1));
                       newPerson.setTbnId(newPerson.getTbnId() + (index + 1));
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
                           newBudgetPerson.setNonEmployeeFlag(false);
                       } else if (wizardResult.getRolodex() != null) {
                           newBudgetPerson.setRolodexId(wizardResult.getRolodex().getRolodexId());
                           newBudgetPerson.setPersonName(wizardResult.getRolodex().getFullName());
                           newBudgetPerson.setNonEmployeeFlag(true);
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

	@Transactional @RequestMapping(params="methodToCall=deleteProjectPersonnel")
	public ModelAndView deleteProjectPersonnel(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
	    String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
        if (StringUtils.isNotEmpty(selectedLine)) {
        	Budget budget = form.getBudget();
		    BudgetPerson deleteBudgetPerson = budget.getBudgetPersons().get(Integer.parseInt(selectedLine));
		    if(isProjectPersonnelDeleteRulePassed(budget, deleteBudgetPerson)) {
		    	budget.getBudgetPersons().remove(deleteBudgetPerson);
		    }
	    }
	    return getModelAndViewService().getModelAndView(form);
	}
	
	@Transactional @RequestMapping(params="methodToCall=prepareAddProjectPersonnel")
	public ModelAndView prepareAddProjectPersonnel(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
        form.getAddProjectPersonnelHelper().setLineType(PersonTypeConstants.EMPLOYEE.getCode());
        return getModelAndViewService().showDialog(ADD_PROJECT_PERSONNEL_DIALOG_ID, true, form);
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
        		form.getAddProjectPersonnelHelper().getBudgetLineItem().setBudget(budget);
        		form.getAddProjectPersonnelHelper().getBudgetPersonnelDetail().setBudget(budget);
                form.getAddProjectPersonnelHelper().getBudgetPersonnelDetail().setPeriodTypeCode(getDefualtPeriodTypeCode());
        		modelAndView = getModelAndViewService().showDialog(ADD_PERSONNEL_PERIOD_DIALOG_ID, true, form);
            }
        }
		return modelAndView;
	}

    protected String getDefualtPeriodTypeCode() {
        return getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_BUDGET, ParameterConstants.DOCUMENT_COMPONENT,Constants.BUDGET_PERSON_DETAILS_DEFAULT_PERIODTYPE);
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
		boolean success = addNewPersonnelLineItemToPeriod(form, budget, currentTabBudgetPeriod, newBudgetLineItem, newBudgetPersonnelDetail, "addProjectPersonnelHelper.budgetPersonnelDetail.");
		if(success) {
			form.getAddProjectPersonnelHelper().reset();
		}
		return getModelAndViewService().getModelAndView(form);
	}
	
	protected boolean addNewPersonnelLineItemToPeriod(ProposalBudgetForm form, Budget budget, BudgetPeriod currentTabBudgetPeriod, BudgetLineItem newBudgetLineItem, 
			BudgetPersonnelDetails newBudgetPersonnelDetail, String errorPath) {
		syncLineItemDates(newBudgetLineItem, newBudgetPersonnelDetail);
		refreshBudgetLineItemReferences(newBudgetLineItem);
		setBudgetLineItemGroupName(form, newBudgetLineItem);
		if(!isSummaryPersonnel(newBudgetPersonnelDetail)) {
			getBudgetPersonnelBudgetService().refreshBudgetPersonnelLineItemReferences(newBudgetPersonnelDetail);
		}
		boolean rulePassed = isAddBudgetPersonnelRulePassed(budget, currentTabBudgetPeriod, newBudgetLineItem, newBudgetPersonnelDetail, errorPath);
		if(rulePassed) {
			getBudgetPersonnelBudgetService().addBudgetPersonnelToPeriod(currentTabBudgetPeriod, newBudgetLineItem, newBudgetPersonnelDetail);
		    getBudgetCalculationService().calculateBudgetPeriod(budget, currentTabBudgetPeriod);
		    getDataObjectService().save(budget);
		    return true;
		}
		return false;
	}

	protected boolean isAddBudgetPersonnelRulePassed(Budget budget, BudgetPeriod budgetPeriod, BudgetLineItem newBudgetLineItem, BudgetPersonnelDetails newBudgetPersonnelDetail, String errorPath) {
		return getKcBusinessRulesEngine().applyRules(new BudgetAddPersonnelPeriodEvent(budget, budgetPeriod, newBudgetLineItem, newBudgetPersonnelDetail,
				errorPath));
	}

	@Transactional @RequestMapping(params="methodToCall=refreshPageWithBudgetExpenseRules")
	public ModelAndView refreshPageWithBudgetExpenseRules(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
	    String errorPath = BudgetConstants.BudgetAuditRules.PERSONNEL_COSTS.getPageId();
		getKcBusinessRulesEngine().applyRules(new BudgetExpensesRuleEvent(form.getBudget(), errorPath));
        form.setAjaxReturnType("update-page");
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
		long longestDuration = 0;
		for (BudgetPersonnelDetails detail: budgetLineItem.getBudgetPersonnelDetailsList()) {
			Interval interval = new Interval(detail.getStartDate().getTime(),detail.getEndDate().getTime());
			if (interval.toDurationMillis() >= longestDuration) {
				longestDuration = interval.toDurationMillis();
				budgetLineItem.setStartDate(detail.getStartDate());
				budgetLineItem.setEndDate(detail.getEndDate());
			}
		}
	}
	
	private boolean isSaveRulePassed(Budget budget, BudgetPeriod budgetPeriod, BudgetLineItem newBudgetLineItem, BudgetPersonnelDetails newBudgetPersonnelDetail, int editLineIndex) {
		return getKcBusinessRulesEngine().applyRules(new BudgetSavePersonnelPeriodEvent(budget, budgetPeriod, newBudgetLineItem, newBudgetPersonnelDetail, 
				editLineIndex, ADD_PERSONNEL_HELPER_ERROR_PATH));
	}

    private boolean isProjectPersonnelDeleteRulePassed(Budget budget, BudgetPerson budgetPerson) {
        return getKcBusinessRulesEngine().applyRules(new DeleteBudgetPersonEvent(budget, budgetPerson, 
                "PropBudget-ProjectPersonnelPage-CollectionGroup"));
    }
    
    @Transactional @RequestMapping(params="methodToCall=editPersonPeriodDetails")
	public ModelAndView editPersonPeriodDetails(@RequestParam("budgetPeriodId") String budgetPeriodId, @ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
	    Budget budget = form.getBudget();
	    String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
        if (StringUtils.isNotEmpty(selectedLine)) {
        	form.getAddProjectPersonnelHelper().reset();
        	BudgetPersonnelDetails budgetPersonnelDetails = form.getBudget().getBudgetPersonnelDetails().get(Integer.parseInt(selectedLine));
    		BudgetLineItem budgetLineItem = budgetPersonnelDetails.getBudgetLineItem();
    		form.getAddProjectPersonnelHelper().setEditLineIndex(selectedLine);
		    form.getAddProjectPersonnelHelper().setBudgetLineItem(getDataObjectService().copyInstance(budgetLineItem));
		    form.getAddProjectPersonnelHelper().setEditBudgetPersonnelDetail(getDataObjectService().copyInstance(budgetPersonnelDetails));
		    Long currentTabBudgetPeriodId = Long.parseLong(budgetPeriodId);
		    BudgetPeriod budgetPeriod = getBudgetPeriod(currentTabBudgetPeriodId, budget);
		    form.getAddProjectPersonnelHelper().setCurrentTabBudgetPeriod(budgetPeriod);
	    }
    	return getModelAndViewService().showDialog(EDIT_PERSONNEL_PERIOD_DIALOG_ID, true, form);
	}

    protected boolean isSummaryPersonChanged(BudgetLineItem budgetLineItem, BudgetPersonnelDetails editedBudgetPersonnelDetails) {
    	if(!isSummaryPersonnel(editedBudgetPersonnelDetails) && !budgetLineItem.isPersonnelLineItem()) {
    		return true;
    	}
    	return false;
    }
    
    protected boolean isPersonnelChangeToSummaryPerson(BudgetLineItem budgetLineItem, BudgetPersonnelDetails editedBudgetPersonnelDetails) {
    	if(isSummaryPersonnel(editedBudgetPersonnelDetails) && budgetLineItem.isPersonnelLineItem()) {
    		return true;
    	}
    	return false;
    }
    
	@Transactional @RequestMapping(params="methodToCall=refreshEditBudgetLineItem")
	public ModelAndView refreshEditBudgetLineItem(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		BudgetPersonnelDetails editBudgetPersonnel = form.getAddProjectPersonnelHelper().getEditBudgetPersonnelDetail();
		getDataObjectService().wrap(editBudgetPersonnel).fetchRelationship("budgetPerson");
		return getModelAndViewService().getModelAndView(form);
	}
    
	@Transactional @RequestMapping(params="methodToCall=savePersonPeriodDetails")
	public ModelAndView savePersonPeriodDetails(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
	    Budget budget = form.getBudget();
	    int selectedLine = Integer.parseInt(form.getAddProjectPersonnelHelper().getEditLineIndex());
    	BudgetPersonnelDetails budgetPersonnelDetails = form.getBudget().getBudgetPersonnelDetails().get(selectedLine);
		BudgetLineItem budgetLineItem = budgetPersonnelDetails.getBudgetLineItem();
	    int editLineIndex = budgetLineItem.getBudgetPersonnelDetailsList().indexOf(budgetPersonnelDetails);
		BudgetPeriod budgetPeriod = form.getAddProjectPersonnelHelper().getCurrentTabBudgetPeriod();
		BudgetPersonnelDetails editBudgetPersonnel = form.getAddProjectPersonnelHelper().getEditBudgetPersonnelDetail();
		BudgetLineItem editBudgetLineItem = form.getAddProjectPersonnelHelper().getBudgetLineItem();
	    int budgetLineItemIndex = budgetPeriod.getBudgetLineItems().indexOf(budgetLineItem);
	    if(isSummaryPersonChanged(editBudgetLineItem, editBudgetPersonnel)) {
	    	addNewPersonnelLineItemToPeriod(form, budget, budgetPeriod, editBudgetLineItem, editBudgetPersonnel, ADD_PERSONNEL_HELPER_ERROR_PATH);
	    }else {
			syncLineItemDates(editBudgetLineItem, editBudgetPersonnel);
			if(!isSummaryPersonnel(editBudgetPersonnel)) {
				getDataObjectService().wrap(editBudgetPersonnel).fetchRelationship("budgetPeriodType");
			}
			boolean rulePassed = isSaveRulePassed(budget, budgetPeriod, editBudgetLineItem, editBudgetPersonnel, editLineIndex);
			if(rulePassed) {
				calculatePersonnelLineItem(form, true);
				if(isSummaryPersonnel(editBudgetPersonnel)) {
					editBudgetLineItem.setLineItemCost(editBudgetPersonnel.getSalaryRequested());
				}else {
					editBudgetLineItem.getBudgetPersonnelDetailsList().set(editLineIndex, editBudgetPersonnel);
				}
				if(isPersonnelChangeToSummaryPerson(editBudgetLineItem, editBudgetPersonnel)) {
					editBudgetLineItem.getBudgetPersonnelDetailsList().clear();
				}
				BudgetLineItem newBudgetLineItem = getDataObjectService().save(editBudgetLineItem);
				budgetPeriod.getBudgetLineItems().set(budgetLineItemIndex, newBudgetLineItem);
			    getBudgetCalculationService().calculateBudgetPeriod(budget, budgetPeriod);
			}
	    }
		return getModelAndViewService().getModelAndView(form);
	}

	protected boolean isSummaryPersonnel(BudgetPersonnelDetails budgetPersonnelDetails) {
		return budgetPersonnelDetails.getPersonSequenceNumber().equals(BudgetConstants.BudgetPerson.SUMMARYPERSON.getPersonSequenceNumber());
	}
	
	@Transactional @RequestMapping(params="methodToCall=calculatePersonnelPeriodLineItem")
	public ModelAndView calculatePersonnelPeriodLineItem(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		BudgetPersonnelDetails editBudgetPersonnelDetail = form.getAddProjectPersonnelHelper().getEditBudgetPersonnelDetail();
		BudgetLineItem budgetLineItem = form.getAddProjectPersonnelHelper().getBudgetLineItem();
		if(isSummaryPersonnel(editBudgetPersonnelDetail)) {
			budgetLineItem.setLineItemCost(editBudgetPersonnelDetail.getSalaryRequested());
			editBudgetPersonnelDetail.setBudgetLineItem(budgetLineItem);
		}
		calculatePersonnelLineItem(form, false);
		return getModelAndViewService().getModelAndView(form);
	}
	
	protected void calculatePersonnelLineItem(ProposalBudgetForm form, boolean ruleChecked) {
	    Budget budget = form.getBudget();
		BudgetPeriod budgetPeriod = form.getAddProjectPersonnelHelper().getCurrentTabBudgetPeriod();
	    int selectedLine = Integer.parseInt(form.getAddProjectPersonnelHelper().getEditLineIndex());
    	BudgetPersonnelDetails budgetPersonnelDetails = form.getBudget().getBudgetPersonnelDetails().get(selectedLine);
		BudgetLineItem budgetLineItem = budgetPersonnelDetails.getBudgetLineItem();
	    int editLineIndex = budgetLineItem.getBudgetPersonnelDetailsList().indexOf(budgetPersonnelDetails);
	    BudgetPersonnelDetails editBudgetPersonnel = form.getAddProjectPersonnelHelper().getEditBudgetPersonnelDetail();
        BudgetLineItem editBudgetLineItem = form.getAddProjectPersonnelHelper().getBudgetLineItem();
        syncLineItemDates(editBudgetLineItem, editBudgetPersonnel);
		boolean rulePassed = ruleChecked ? ruleChecked : isSaveRulePassed(budget, budgetPeriod, editBudgetLineItem, editBudgetPersonnel, editLineIndex);
		if(rulePassed) {
		    getBudgetPersonnelBudgetService().calculateBudgetPersonnelLineItem(budget, editBudgetLineItem, editBudgetPersonnel, editLineIndex);
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

    @Transactional @RequestMapping(params={"methodToCall=refresh", "refreshCaller=PropBudget-AssignPersonnelToPeriodsPage-DetailsAndRates"})
    public ModelAndView refreshBudgetDetailsAndRates(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
        form.setAjaxReturnType(UifConstants.AjaxReturnTypes.UPDATECOMPONENT.getKey());
        form.setUpdateComponentId("PropBudget-AssignPersonnelToPeriodsPage-DetailsAndRates");
        getDataObjectService().wrap(form.getAddProjectBudgetLineItemHelper().getBudgetLineItem()).fetchRelationship("costElementBO");
        return getRefreshControllerService().refresh(form);
    }

	@Transactional @RequestMapping(params="methodToCall=applyToLaterPeriods")
	public ModelAndView applyToLaterPeriods(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		Budget budget = form.getBudget();
		BudgetPeriod currentTabBudgetPeriod = form.getAddProjectPersonnelHelper().getCurrentTabBudgetPeriod();
	    BudgetLineItem budgetLineItem = form.getAddProjectPersonnelHelper().getBudgetLineItem();
	    if (getKcBusinessRulesEngine().applyRules(new PersonnelApplyToPeriodsBudgetEvent(budget, ADD_PERSONNEL_HELPER_ERROR_PATH, budgetLineItem, currentTabBudgetPeriod))) {
	    	getBudgetCalculationService().applyToLaterPeriods(budget, currentTabBudgetPeriod, budgetLineItem);
	    	return super.save(form);
	    } else {
	    	return getModelAndViewService().getModelAndView(form);
	    }
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
		    getBudgetCalculationService().calculateBudgetPeriod(budget, budgetPeriod);
		    refreshPageWithBudgetExpenseRules(form);
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

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
