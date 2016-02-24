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
package org.kuali.coeus.common.budget.impl.distribution;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetAuditEvent;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.common.budget.framework.core.Budget.FiscalYearSummary;
import org.kuali.coeus.common.budget.framework.core.BudgetAuditRuleEvent;
import org.kuali.coeus.common.budget.framework.distribution.BudgetCostShare;
import org.kuali.coeus.common.framework.costshare.CostShareRuleResearchDocumentBase;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;

import java.util.ArrayList;
import java.util.List;

import static org.kuali.rice.krad.util.GlobalVariables.getAuditErrorMap;

/**
 * 
 * This class handels the budget cost share rules.
 */
@KcBusinessRule("budgetCostShareAuditRule")
public class BudgetCostShareAuditRule extends CostShareRuleResearchDocumentBase {
    public static final String BUDGET_COST_SHARE_ERROR_KEY = "budgetCostShareAuditErrors";
    
    @KcEventMethod
    @Deprecated
    public boolean processCostShareAuditRules(BudgetAuditEvent event) {
        Budget budget = event.getBudget();

        // Returns if cost sharing is not applicable
        if (!budget.isCostSharingApplicable()) {
            return true;
        }

        List<BudgetCostShare> costShares = budget.getBudgetCostShares();
        boolean retval = true;
        String[] params = { "Cost Sharing" };

        // Forces full allocation of cost sharing
        if (budget.getUnallocatedCostSharing().isNonZero() && budget.isCostSharingEnforced()) {
            retval = false;
            if (costShares.size() == 0) {
                getAuditErrors(BUDGET_COST_SHARE_ERROR_KEY, Constants.BUDGET_COST_SHARE_PANEL_NAME)
                        .add(
                                new AuditError("document.budget.budgetCostShare",
                                    KeyConstants.AUDIT_ERROR_BUDGET_DISTRIBUTION_UNALLOCATED_NOT_ZERO,
                                    Constants.BUDGET_DISTRIBUTION_AND_INCOME_PAGE + "." + Constants.BUDGET_COST_SHARE_PANEL_ANCHOR,
                                    params));

            }
            for (int i = 0; i < costShares.size(); i++) {
                getAuditErrors(BUDGET_COST_SHARE_ERROR_KEY, Constants.BUDGET_COST_SHARE_PANEL_NAME)
                        .add(
                                new AuditError("document.budget.budgetCostShare[" + i + "].shareAmount",
                                    KeyConstants.AUDIT_ERROR_BUDGET_DISTRIBUTION_UNALLOCATED_NOT_ZERO,
                                    Constants.BUDGET_DISTRIBUTION_AND_INCOME_PAGE + "." + Constants.BUDGET_COST_SHARE_PANEL_ANCHOR,
                                    params));
            }
        }
        String source = null;
        Integer fiscalYear = null;

        List<Integer> validFiscalYears = new ArrayList<Integer>();
        for (FiscalYearSummary fys : budget.getFiscalYearCostShareTotals()) {
            validFiscalYears.add(fys.getFiscalYear());
        }

        int i = 0;
        // Forces inclusion of source account
        for (BudgetCostShare costShare : costShares) {
            source = costShare.getSourceAccount();
            fiscalYear = costShare.getProjectPeriod();
            if (null == source || source.length() == 0) {
                retval = false;
                getAuditErrors(BUDGET_COST_SHARE_ERROR_KEY, Constants.BUDGET_COST_SHARE_PANEL_NAME)
                        .add(
                                new AuditError("document.budget.budgetCostShare[" + i + "].sourceAccount",
                                    KeyConstants.AUDIT_ERROR_BUDGET_DISTRIBUTION_SOURCE_MISSING,
                                    Constants.BUDGET_DISTRIBUTION_AND_INCOME_PAGE + "." + Constants.BUDGET_COST_SHARE_PANEL_ANCHOR,
                                    params));
            }
            int numberOfProjectPeriods = -1;
            if (budget.getBudgetPeriods() != null) {
                numberOfProjectPeriods = budget.getBudgetPeriods().size();
            }
            validateProjectPeriod(fiscalYear, "document.budget.budgetCostShare[" + i + "].projectPeriod", numberOfProjectPeriods);
            if (getCostShareService().validateProjectPeriodAsProjectPeriod()) {
                List<AuditError> errors = new ArrayList<AuditError>();
                validatePeriodNumber(costShare, "document.budget.budgetCostShare[" + i + "].projectPeriod", numberOfProjectPeriods, errors);
                if (!errors.isEmpty()) {
                    getAuditErrors(BUDGET_COST_SHARE_ERROR_KEY, Constants.BUDGET_COST_SHARE_PANEL_NAME).addAll(errors);
                }
            }
            i++;
        }
        return retval;
    }

    @KcEventMethod
    public boolean processCostShareAuditRules(BudgetAuditRuleEvent event) {
        Budget budget = event.getBudget();
        boolean retval = true;
        if (budget.isCostSharingApplicable() && budget.isCostSharingEnforced()) {
            List<BudgetCostShare> costShares = budget.getBudgetCostShares();
            String[] params = { "Cost Sharing" };
            retval &= verifyCostSharingAllocation(budget, costShares, params);
            retval &= verifySourceAccount(budget, costShares, params);
        }
        return retval;
    }
    
    protected boolean verifySourceAccount(Budget budget, List<BudgetCostShare> costShares, String[] params) {
        String source = null;
        Integer fiscalYear = null;
        BudgetConstants.BudgetAuditRules budgetCostSharingRule = BudgetConstants.BudgetAuditRules.COST_SHARING;

        boolean isValid = true;
        // Forces inclusion of source account
        for (BudgetCostShare costShare : costShares) {
            source = costShare.getSourceAccount();
            fiscalYear = costShare.getProjectPeriod();
            if (StringUtils.isEmpty(source) || source.length() == 0) {
                getAuditErrors(budgetCostSharingRule.getErrorKey(), budgetCostSharingRule.getLabel()).add(new AuditError(budgetCostSharingRule.getPageId(),
                                    KeyConstants.AUDIT_ERROR_BUDGET_DISTRIBUTION_SOURCE_MISSING,
                                    budgetCostSharingRule.getPageId(), params));
                isValid = false;
            }
            int numberOfProjectPeriods = budget.getBudgetPeriods() != null ? budget.getBudgetPeriods().size() : -1;
            validateProjectPeriod(fiscalYear, budgetCostSharingRule.getPageId(), numberOfProjectPeriods);
            if (getCostShareService().validateProjectPeriodAsProjectPeriod()) {
                isValid &= verifyPeriodNumber(costShare, budgetCostSharingRule.getPageId(), numberOfProjectPeriods);
            }
        }
        return isValid;
    }
    
    public boolean verifyPeriodNumber(BudgetCostShare costShare, String projectPeriodField, int numberOfProjectPeriods) {
        int projectPeriodInt = Integer.parseInt(costShare.getProjectPeriod().toString().trim());
        BudgetConstants.BudgetAuditRules budgetCostSharingRule = BudgetConstants.BudgetAuditRules.COST_SHARING;
       if (projectPeriodInt <= 0 || projectPeriodInt > numberOfProjectPeriods) {
            AuditError auditError = new AuditError(projectPeriodField, KeyConstants.ERROR_PROJECT_PERIOD_RANGE,
                    Constants.BUDGET_DISTRIBUTION_AND_INCOME_PAGE + "." + Constants.BUDGET_COST_SHARE_PANEL_ANCHOR,
                    new String[] {Integer.toString(projectPeriodInt), Integer.toString(numberOfProjectPeriods), Integer.toString(numberOfProjectPeriods)});
            getAuditErrors(budgetCostSharingRule.getErrorKey(), budgetCostSharingRule.getLabel()).add(auditError);
            return false;
        }
        return true;
    }
    
    protected boolean verifyCostSharingAllocation(Budget budget, List<BudgetCostShare> costShares, String[] params) {
        BudgetConstants.BudgetAuditRules budgetCostSharingRule = BudgetConstants.BudgetAuditRules.COST_SHARING;
        // Forces full allocation of cost sharing
        if (budget.getUnallocatedCostSharing().isNonZero()) {
            if (costShares.isEmpty()) {
                getAuditErrors(budgetCostSharingRule.getErrorKey(), budgetCostSharingRule.getLabel()).add(new AuditError("budget.budgetCostShare", 
                		KeyConstants.AUDIT_ERROR_BUDGET_DISTRIBUTION_UNALLOCATED_NOT_ZERO, budgetCostSharingRule.getPageId(), params));
            }else {
                for (int i = 0; i < costShares.size(); i++) {
                    getAuditErrors(budgetCostSharingRule.getErrorKey(), budgetCostSharingRule.getLabel()).add(new AuditError("budget.budgetCostShare[" + i + "].shareAmount",
                                        KeyConstants.AUDIT_ERROR_BUDGET_DISTRIBUTION_UNALLOCATED_NOT_ZERO,
                                        budgetCostSharingRule.getPageId(), params));
                }
            }
            return false;
        }
        return true;
    }

    /**
     * This method is a convenience method for obtaining audit errors.
     * 
     * @return List of AuditError instances
     */
    private List<AuditError> getAuditErrors(String costShareKey, String costShareLabel) {
        return getAuditProblems(Constants.AUDIT_ERRORS, costShareKey, costShareLabel);
    }

    /**
     * This method is a convenience method for obtaining audit warnings.
     * 
     * @return List of AuditError instances
     */
    private List<AuditError> getAuditWarnings(String costShareKey, String costShareLabel) {
        return getAuditProblems(Constants.AUDIT_WARNINGS, costShareKey, costShareLabel);
    }

    /**
     * This method should only be called if an audit error is intending to be added because it will actually add a
     * <code>{@link List&lt;AuditError&gt;}</code> to the auditErrorMap.
     * 
     * @return List of AuditError instances
     */
    private List<AuditError> getAuditProblems(String problemType, String costShareKey, String costShareLabel) {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        if (!getAuditErrorMap().containsKey(costShareKey)) {
            getAuditErrorMap().put(costShareKey,
                    new AuditCluster(costShareLabel, auditErrors, problemType));
        }else {
            auditErrors = ((AuditCluster) getAuditErrorMap().get(costShareKey)).getAuditErrorList();
        }

        return auditErrors;
    }

}
