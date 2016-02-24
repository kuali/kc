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
package org.kuali.coeus.propdev.impl.budget.core;

import static org.kuali.kra.infrastructure.KeyConstants.QUESTION_RECALCULATE_BUDGET_CONFIRMATION;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.period.AddBudgetPeriodAndTotalEvent;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.period.GenerateBudgetPeriodEvent;
import org.kuali.coeus.common.budget.framework.period.SaveBudgetPeriodAndTotalEvent;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.component.BindingInfo;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.kuali.rice.krad.web.form.DialogResponse;
import org.kuali.rice.krad.web.service.impl.CollectionControllerServiceImpl.CollectionActionParameters;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/proposalBudget")
public class ProposalBudgetRateAndPeriodController extends ProposalBudgetControllerBase {

	private static final String CONFIRM_PERIOD_CHANGES_DIALOG_ID = "PropBudget-PeriodsPage-ConfirmPeriodChangesDialog";
	private static final String PERIOD_CHANGES_DIALOG_ID = "PropBudget-PeriodsPage-ChangePeriodDialog";
	private static final String BUDGET_PERIOD_ERROR_PATH_PREFIX = "budget.budgetPeriods";
	private static final String NEW_PERIOD_DIALOG_ID = "PropBudget-PeriodsPage-AddDialog";
	
	
	@MethodAccessible
    @Transactional @RequestMapping(params="methodToCall=resetToBudgetPeriodDefault")
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
                	getBudgetSummaryService().syncBudgetPersonSalaryDetails(budget);
                }
            }
        }else {
        	getBudgetSummaryService().defaultBudgetPeriods(budget);
        	getBudgetSummaryService().syncBudgetPersonSalaryDetails(budget);
        }
        return getModelAndViewService().getModelAndView(form);
    }    
	
	@MethodAccessible
    @Transactional @RequestMapping(params="methodToCall=recalculateBudgetWithChanges")
    public ModelAndView recalculateBudgetWithChanges(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentBudgetExt budget = form.getBudget();
    	getBudgetSummaryService().calculateBudget(budget);
        return getModelAndViewService().getModelAndView(form);
    }    
	
    @Transactional @RequestMapping(params="methodToCall=addBudgetPeriod")
    public ModelAndView addBudgetPeriod(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
        BudgetPeriod newBudgetPeriod = ((BudgetPeriod)form.getNewCollectionLines().get("budget.budgetPeriods"));
        Budget budget = form.getBudget();
        newBudgetPeriod.setBudget(budget);
        String addLineBindingPath = getAddLineBindingPath(form);
        String errorPath = addLineBindingPath + ".";
        if (getKcBusinessRulesEngine().applyRules(new AddBudgetPeriodAndTotalEvent(budget, newBudgetPeriod, errorPath))) {
            getBudgetSummaryService().addBudgetPeriod(budget, newBudgetPeriod);
            ObjectPropertyUtils.setPropertyValue(form, addLineBindingPath, new BudgetPeriod());
        }
    	form.setUpdateComponentId(NEW_PERIOD_DIALOG_ID);
        return getModelAndViewService().getModelAndView(form);
    }
    
    protected String getAddLineBindingPath(ProposalBudgetForm form) {
		final CollectionActionParameters parameters = new CollectionActionParameters(form, false);
        BindingInfo addLineBindingInfo = (BindingInfo) form.getViewPostMetadata().getComponentPostData(
                parameters.getSelectedCollectionId(), UifConstants.PostMetadata.ADD_LINE_BINDING_INFO);
        return addLineBindingInfo.getBindingPath();
    }
    
    @Transactional @RequestMapping(params="methodToCall=saveBudgetPeriod")
    public ModelAndView saveBudgetPeriod(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
    	Budget budget = form.getBudget();
    	ModelAndView modelAndView = getModelAndViewService().getModelAndView(form);
        int selectedLine = Integer.parseInt(form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX));
        BudgetPeriod budgetPeriod = budget.getBudgetPeriods().get(selectedLine);
        if (isBudgetPeriodDateChanged(budgetPeriod) && isOnlyLineItemDateError()) {
        	getGlobalVariableService().getMessageMap().clearErrorMessages();
            DialogResponse dialogResponse = form.getDialogResponse(PERIOD_CHANGES_DIALOG_ID);
            if(dialogResponse == null) {
            	return getModelAndViewService().showDialog(PERIOD_CHANGES_DIALOG_ID, true, form);
            }else {
                boolean confirmResetDefault = dialogResponse.getResponseAsBoolean();
                if(confirmResetDefault) {
                    getBudgetSummaryService().adjustStartEndDatesForLineItems(budgetPeriod);
                }
            }
        }

        boolean rulePassed = getKcBusinessRulesEngine().applyRules(new SaveBudgetPeriodAndTotalEvent(budget, BUDGET_PERIOD_ERROR_PATH_PREFIX));
        if(rulePassed) {
        	getBudgetCalculationService().calculateBudgetPeriod(budget, budgetPeriod);
            modelAndView = super.saveLine(form);
        }
        return modelAndView;
    }
		
	@MethodAccessible
    @Transactional @RequestMapping(params="methodToCall=generateAllPeriods")
    public ModelAndView generateAllPeriods(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentBudgetExt budget = form.getBudget();
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
                form.setSelectedBudget(budget);
                getBudgetCalculationService().populateBudgetSummaryTotals(form.getSelectedBudget());
            }
        }
        return getModelAndViewService().getModelAndView(form);
	}

    @Transactional @RequestMapping(params="methodToCall=readyGeneratePeriod")
    public ModelAndView readyGeneratePeriod(@ModelAttribute("KualiForm") ProposalBudgetForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DialogResponse dialogResponse = form.getDialogResponse("PropBudget-PeriodsPage-ReadyToGeneratePeriodsDialog");
        if (dialogResponse == null) {
            return getModelAndViewService().showDialog("PropBudget-PeriodsPage-ReadyToGeneratePeriodsDialog",true,form);
        } else if (dialogResponse.getResponseAsBoolean()) {
            form.setPageId(ProposalBudgetConstants.KradConstants.SUBAWARD_PAGE_ID);
            return generateAllPeriods(form,result,request,response);
        }

        return getModelAndViewService().getModelAndView(form);
    }

    @Transactional @RequestMapping(params={"methodToCall=save", "pageId=PropBudget-PeriodsPage"})
    public ModelAndView save(ProposalBudgetForm form) {
    	ModelAndView modelAndView = getModelAndViewService().getModelAndView(form);
        Budget budget = form.getBudget();
        if (isBudgetPeriodDateChanged(budget) && isOnlyLineItemDateError()) {
        	getGlobalVariableService().getMessageMap().clearErrorMessages();
            DialogResponse dialogResponse = form.getDialogResponse(PERIOD_CHANGES_DIALOG_ID);
            if(dialogResponse == null) {
            	return getModelAndViewService().showDialog(PERIOD_CHANGES_DIALOG_ID, true, form);
            }else {
                boolean confirmResetDefault = dialogResponse.getResponseAsBoolean();
                if(confirmResetDefault) {
                    getBudgetSummaryService().adjustStartEndDatesForLineItems(budget);
                }
            }
        }

        return super.save(form);
    }
    
    /**
     * Method is to verify whether budget dates changed.
     * @param budget
     * @return
     */
    private boolean isBudgetPeriodDateChanged(Budget budget) {
    	boolean budgetPeriodDateChanged = false;
        for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
        	budgetPeriodDateChanged = isBudgetPeriodDateChanged(budgetPeriod);
        	if(budgetPeriodDateChanged) {
        		break;
        	}
        }
        return budgetPeriodDateChanged;
    }
    
    private boolean isBudgetPeriodDateChanged(BudgetPeriod budgetPeriod) {
        if (budgetPeriod.getStartDate() != null && budgetPeriod.getOldStartDate() != null && 
                budgetPeriod.getEndDate() != null && budgetPeriod.getOldEndDate() != null && 
                (budgetPeriod.getStartDate().compareTo(budgetPeriod.getOldStartDate()) != 0
                || budgetPeriod.getEndDate().compareTo(budgetPeriod.getOldEndDate()) != 0)) {
            return true;
        }
        return false;
    }

    /**
     * This method is to check the error map to see if there is any error other than line item date error.
     * line item date date error should be resolved with adjustlineitem start/end date.
     * This is called after rule verification and before save.
     * @return
     */
    private boolean isOnlyLineItemDateError() {
    	Map<String, List<ErrorMessage>> errors = getGlobalVariableService().getMessageMap().getErrorMessages();
    	List<String> lineItemDateErrors = getLineItemDateErrors();
    	if (!errors.isEmpty()) {
            for (Map.Entry<String, List<ErrorMessage>> entry : errors.entrySet()) {
                List<ErrorMessage> errorMessages = entry.getValue();
                for(ErrorMessage errorMessage : errorMessages) {
                    if (!lineItemDateErrors.contains(errorMessage.getErrorKey())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    /**
     * List of line item errors specific to date that we can skip 
     * to adjust line item dates as per adjusted period dates
     * @return
     */
    private List<String> getLineItemDateErrors() {
    	List<String> lineItemDateErrors = new ArrayList<String>();
    	lineItemDateErrors.add("error.lineItem.dateDoesNotmatch");
    	lineItemDateErrors.add("error.line.item.start.date");
    	lineItemDateErrors.add("error.line.item.end.date");
    	return lineItemDateErrors;
    }

}
