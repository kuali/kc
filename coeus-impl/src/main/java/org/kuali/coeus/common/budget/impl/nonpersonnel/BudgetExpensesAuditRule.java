/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.budget.impl.nonpersonnel;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetAuditEvent;
import org.kuali.coeus.common.budget.framework.core.BudgetAuditRuleBase;
import org.kuali.coeus.common.budget.framework.core.BudgetAuditRuleEvent;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetExpenseService;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

@KcBusinessRule("budgetExpensesAuditRule")
public class BudgetExpensesAuditRule extends BudgetAuditRuleBase {

	@Autowired
	@Qualifier("budgetExpenseService")
	private BudgetExpenseService budgetExpenseService;
	
    /**
     * 
     * This method is to validate budget expense business rules
     * 
     */
	@KcEventMethod
	@Deprecated
    public boolean processRunAuditBusinessRules(BudgetAuditEvent event) {
		Budget budget = event.getBudget();
        boolean retval = true;
        int i = 0;
        
        if ( budget.getTotalCostLimit().isGreaterThan(new ScaleTwoDecimal(0)) &&
        		budget.getTotalCost().isGreaterThan(budget.getTotalCostLimit()) ) {
            String key = "budgetParametersOverviewWarnings";
            AuditCluster auditCluster = (AuditCluster) getGlobalVariableService().getAuditErrorMap().get(key);
            if (auditCluster == null) {
                List<AuditError> auditErrors = new ArrayList<AuditError>();
                auditCluster = new AuditCluster(Constants.BUDGET_PARAMETERS_OVERVIEW_PANEL_NAME, auditErrors, Constants.AUDIT_WARNINGS);
                getGlobalVariableService().getAuditErrorMap().put(key, auditCluster);
            }
            List<AuditError> auditErrors = auditCluster.getAuditErrorList();
            auditErrors.add(new AuditError("document.budget.totalCostLimit", 
                    KeyConstants.WARNING_TOTAL_COST_LIMIT_EXCEEDED, 
                    Constants.BUDGET_PARAMETERS_PAGE_METHOD + "." + Constants.BUDGET_PARAMETERS_OVERVIEW_PANEL_ANCHOR));
            retval=false;
            
        }
       for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
            if(budgetPeriod.getTotalCostLimit().isGreaterThan(new ScaleTwoDecimal(0)) && budgetPeriod.getTotalCost().isGreaterThan(budgetPeriod.getTotalCostLimit())){
                String key = "budgetPeriodProjectDateAuditWarnings";
                AuditCluster auditCluster = (AuditCluster) getGlobalVariableService().getAuditErrorMap().get(key);
                if (auditCluster == null) {
                    List<AuditError> auditErrors = new ArrayList<AuditError>();
                    auditCluster = new AuditCluster(Constants.BUDGET_PARAMETERS_TOTALS_PANEL_NAME, auditErrors, Constants.AUDIT_WARNINGS);
                    getGlobalVariableService().getAuditErrorMap().put(key, auditCluster);
                }
                List<AuditError> auditErrors = auditCluster.getAuditErrorList();
                auditErrors.add(new AuditError("document.budget.budgetPeriods[" + (budgetPeriod.getBudgetPeriod() - 1) + "].totalCostLimit", 
                        KeyConstants.WARNING_PERIOD_COST_LIMIT_EXCEEDED, 
                        Constants.BUDGET_PARAMETERS_PAGE_METHOD + "." + Constants.BUDGET_PARAMETERS_TOTALS_PANEL_ANCHOR));
                retval=false;
            } 
            
            // budget personnel budget effective warning 
            int j = 0;
            for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
                String panelName = budgetExpenseService.getBudgetExpensePanelName(budgetPeriod, budgetLineItem);
                
                if(budgetLineItem.getUnderrecoveryAmount() != null && budgetLineItem.getUnderrecoveryAmount().isNegative()) {
                    String key = "budgetNonPersonnelAuditWarnings" + budgetPeriod.getBudgetPeriod()+panelName;
                    AuditCluster auditCluster = (AuditCluster) getGlobalVariableService().getAuditErrorMap().get(key);
                    if (auditCluster == null) {
                        List<AuditError> auditErrors = new ArrayList<AuditError>();
                        auditCluster = new AuditCluster(panelName+" Budget Period "+budgetPeriod.getBudgetPeriod(), auditErrors, Constants.AUDIT_WARNINGS);
                        getGlobalVariableService().getAuditErrorMap().put(key, auditCluster);
                    }
                    List<AuditError> auditErrors = auditCluster.getAuditErrorList();
                    auditErrors.add(new AuditError("document.budgetPeriod[" + (budgetPeriod.getBudgetPeriod() - 1) + "].budgetLineItem["+j+"].underrecoveryAmount", KeyConstants.WARNING_UNRECOVERED_FA_NEGATIVE, Constants.BUDGET_EXPENSES_PAGE_METHOD + "." + budgetLineItem.getBudgetCategory().getBudgetCategoryType().getDescription() + "&viewBudgetPeriod=" + budgetPeriod.getBudgetPeriod() + "&selectedBudgetLineItemIndex=" + j + "&activePanelName=" + panelName));
                    retval=false;
                }
                    
                int k = 0;
                for (BudgetPersonnelDetails budgetPersonnelDetails : budgetLineItem.getBudgetPersonnelDetailsList()) {
                    if (StringUtils.isNotEmpty(budgetPersonnelDetails.getEffdtAfterStartdtMsg())) {
                        String key = "budgetPersonnelBudgetAuditWarnings"+budgetPeriod.getBudgetPeriod();
                        AuditCluster auditCluster = (AuditCluster) getGlobalVariableService().getAuditErrorMap().get(key);
                        if (auditCluster == null) {
                            List<AuditError> auditErrors = new ArrayList<AuditError>();
                            auditCluster = new AuditCluster(Constants.PERSONNEL_BUDGET_PANEL_NAME + " (Period " +budgetPeriod.getBudgetPeriod()+")", auditErrors, Constants.AUDIT_WARNINGS);
                            getGlobalVariableService().getAuditErrorMap().put(key, auditCluster);
                        }
                        List<AuditError> auditErrors = auditCluster.getAuditErrorList();
                        auditErrors.add(new AuditError("document.budgetPeriod[" + (budgetPeriod.getBudgetPeriod() - 1) + "].budgetLineItem["+j+"].budgetPersonnelDetailsList["+k+"].salaryRequested", KeyConstants.WARNING_EFFDT_AFTER_PERIOD_START_DATE, Constants.BUDGET_EXPENSES_PAGE_METHOD + "." + Constants.BUDGET_EXPENSES_OVERVIEW_PANEL_ANCHOR + "&viewBudgetPeriod=" + budgetPeriod.getBudgetPeriod() + "&selectedBudgetLineItemIndex=" + j + "&personnelDetailLine="+k, new String[]{budgetPersonnelDetails.getBudgetPerson().getPersonName()}));
                        retval=false;

                    }
                    if (budgetPersonnelDetails.getBudgetPerson().getCalculationBase().equals(ScaleTwoDecimal.ZERO)) {
                        String key = "budgetPersonnelBudgetAuditWarnings"+budgetPeriod.getBudgetPeriod();
                        AuditCluster auditCluster = (AuditCluster) getGlobalVariableService().getAuditErrorMap().get(key);
                        if (auditCluster == null) {
                            List<AuditError> auditErrors = new ArrayList<AuditError>();
                            auditCluster = new AuditCluster(Constants.PERSONNEL_BUDGET_PANEL_NAME+ " (Period " +budgetPeriod.getBudgetPeriod() +")", auditErrors, Constants.AUDIT_WARNINGS);
                            getGlobalVariableService().getAuditErrorMap().put(key, auditCluster);
                        }
                        List<AuditError> auditErrors = auditCluster.getAuditErrorList();
                        auditErrors.add(new AuditError("document.budgetPeriod[" + (budgetPeriod.getBudgetPeriod() - 1) + "].budgetLineItem["+j+"].budgetPersonnelDetailsList["+k+"].salaryRequested", KeyConstants.WARNING_BASE_SALARY_ZERO, Constants.BUDGET_EXPENSES_PAGE_METHOD + "." + Constants.BUDGET_EXPENSES_OVERVIEW_PANEL_ANCHOR + "&viewBudgetPeriod=" + budgetPeriod.getBudgetPeriod() + "&selectedBudgetLineItemIndex=" + j + "&personnelDetailLine="+k, new String[]{budgetPersonnelDetails.getBudgetPerson().getPersonName()}));
                        retval=false;

                    }

                    k++;
                }
                j++;
            }
        }
        
        return retval;

    }

	@KcEventMethod
    public boolean processRunAuditBusinessRules(BudgetAuditRuleEvent event) {
		Budget budget = event.getBudget();
		boolean auditRulePassed = verifyTotalCostLimit(budget);
        for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
        	auditRulePassed &= verifyPeriodCostLimit(budgetPeriod);
        }
       
        int budgetLineItemIndex = 0;
        for (BudgetLineItem budgetLineItem : budget.getBudgetLineItems()) {
        	auditRulePassed &= verifyUnderRecoveryAmount(budgetLineItem, budgetLineItemIndex);
            budgetLineItemIndex++;
        }
       
        for (BudgetPersonnelDetails budgetPersonnelDetails : budget.getBudgetPersonnelDetails()) {
        	auditRulePassed &= verifyPersonnelDetails(budget, budgetPersonnelDetails); 
        }
        return auditRulePassed;
    }

	protected boolean verifyTotalCostLimit(Budget budget) {
        if ( budget.getTotalCostLimit().isGreaterThan(new ScaleTwoDecimal(0)) &&
        		budget.getTotalCost().isGreaterThan(budget.getTotalCostLimit()) ) {
            BudgetConstants.BudgetAuditRules budgetSettingsRule = BudgetConstants.BudgetAuditRules.BUDGET_SETTINGS;
			List<AuditError> auditErrors = getAuditErrors(budgetSettingsRule, false);
            auditErrors.add(new AuditError("budget.totalCostLimit", KeyConstants.WARNING_TOTAL_COST_LIMIT_EXCEEDED, budgetSettingsRule.getPageId()));
            return false;
        }
        return true;
	}

	protected boolean verifyPeriodCostLimit(BudgetPeriod budgetPeriod) {
        if(budgetPeriod.getTotalCostLimit().isGreaterThan(new ScaleTwoDecimal(0)) && budgetPeriod.getTotalCost().isGreaterThan(budgetPeriod.getTotalCostLimit())){
            BudgetConstants.BudgetAuditRules budgetPeriodAndTotalRule = BudgetConstants.BudgetAuditRules.PERIODS_AND_TOTALS;
			List<AuditError> auditErrors = getAuditErrors(budgetPeriodAndTotalRule, false);
            auditErrors.add(new AuditError(budgetPeriodAndTotalRule.getPageId(), 
                    KeyConstants.WARNING_PERIOD_COST_LIMIT_EXCEEDED, budgetPeriodAndTotalRule.getPageId(), new String[]{budgetPeriod.getBudgetPeriod().toString()}));
            return false;
        } 
        return true;
	}
	
	protected boolean verifyUnderRecoveryAmount(BudgetLineItem budgetLineItem, int budgetLineItemIndex) {
        if(budgetLineItem.getUnderrecoveryAmount() != null && budgetLineItem.getUnderrecoveryAmount().isNegative()) {
        	BudgetPeriod budgetPeriod = budgetLineItem.getBudgetPeriodBO();
            BudgetConstants.BudgetAuditRules budgetNonPersonnelRule = BudgetConstants.BudgetAuditRules.NON_PERSONNEL_COSTS;
            String additionalMessage = " Budget Period "+ budgetPeriod.getBudgetPeriod() + "Line item " + budgetLineItem.getCostElementBO().getDescription();
			List<AuditError> auditErrors = getAuditErrors(budgetNonPersonnelRule, additionalMessage, false);
            auditErrors.add(new AuditError(budgetNonPersonnelRule.getPageId(), 
            		KeyConstants.WARNING_UNRECOVERED_FA_NEGATIVE, budgetNonPersonnelRule.getPageId(), 
            		new String[]{additionalMessage}));
            return false;
       }
        return true;
	}
	
	protected boolean verifyPersonnelDetails(Budget budget, BudgetPersonnelDetails budgetPersonnelDetails) {
		boolean salaryEffectiveAfterStartDate = budgetPersonnelDetails.isPersonSalaryEffectiveDateAfterStartDate();
		boolean personSalaryNotDefined = budgetPersonnelDetails.isPersonBaseSalaryZero();
        if (salaryEffectiveAfterStartDate || personSalaryNotDefined) {
        	BudgetPeriod budgetPeriod = budgetPersonnelDetails.getBudgetLineItem().getBudgetPeriodBO();
        	BudgetPerson budgetPerson = budgetPersonnelDetails.getBudgetPerson();
            BudgetConstants.BudgetAuditRules budgetProjectPersonnelRule = BudgetConstants.BudgetAuditRules.PROJECT_PERSONNEL;
			List<AuditError> auditErrors = getAuditErrors(budgetProjectPersonnelRule, " Budget Period "+ budgetPeriod.getBudgetPeriod(), false);
			String errorKey = budgetProjectPersonnelRule.getPageId();
			if(salaryEffectiveAfterStartDate) {
	             auditErrors.add(new AuditError(errorKey, KeyConstants.WARNING_EFFDT_AFTER_PERIOD_START_DATE,
	            		 budgetProjectPersonnelRule.getPageId(), new String[]{budgetPerson.getPersonName()}));
			}else {
	            auditErrors.add(new AuditError(errorKey, KeyConstants.WARNING_BASE_SALARY_ZERO, budgetProjectPersonnelRule.getPageId(), 
	            		new String[]{budgetPerson.getPersonName()}));
			}
	        return false;
        }
        return true;
	}
	
	protected BudgetExpenseService getBudgetExpenseService() {
		return budgetExpenseService;
	}

	public void setBudgetExpenseService(BudgetExpenseService budgetExpenseService) {
		this.budgetExpenseService = budgetExpenseService;
	}
}


