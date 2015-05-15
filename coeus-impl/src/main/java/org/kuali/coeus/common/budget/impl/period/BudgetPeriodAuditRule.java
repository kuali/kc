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
package org.kuali.coeus.common.budget.impl.period;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetAuditEvent;
import org.kuali.coeus.common.budget.framework.core.BudgetAuditRuleBase;
import org.kuali.coeus.common.budget.framework.core.BudgetAuditRuleEvent;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.rice.krad.util.AuditError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.Date;
import java.util.List;

@KcBusinessRule("budgetPeriodAuditRule")
public class BudgetPeriodAuditRule extends BudgetAuditRuleBase {

    private static final String BUDGET_PERIOD_DATE_AUDIT_ERROR_KEY = "budgetPeriodProjectDateAuditErrors";
    private static final String BUDGET_PERIOD_DATE_AUDIT_WARNING_KEY = "budgetPeriodProjectDateAuditWarnings";

    @Autowired
    @Qualifier("errorReporter")
    private ErrorReporter errorReporter;
    
    /**
     * Executes audit rules for budget periods.
     * @return true if valid false if not
     * @throws NullPointerException if the document is null
     */
    @KcEventMethod
    public boolean processRunAuditBusinessRules(BudgetAuditEvent event) {
        if (event == null || event.getBudget() == null) {
            throw new NullPointerException("the budget is null");
        }
        
        return this.validatePeriodDates(event.getBudget());
    }
    
    /**
     * This method is to validate budget period start/end date against project start/end date if
     * project start/end date have been adjusted.
     * @param budget the budget
     * @return true if valid false if not
     */
    private boolean validatePeriodDates(final Budget budget) {
        boolean retval = true;
        BudgetParent budgetParent = budget.getBudgetParent().getDocument().getBudgetParent();
        final Date projectStartDate = budgetParent.getRequestedStartDateInitial();
        final Date projectEndDate = budgetParent.getRequestedEndDateInitial();
        
        int i = 0;
        for (final BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
            Date budgetPeriodStartDate = budgetPeriod.getStartDate();
            Date budgetPeriodEndDate = budgetPeriod.getEndDate();
            if (budgetPeriodStartDate != null && budgetPeriodStartDate.before(projectStartDate)) {
                retval = false;
                this.addBudgetPeriodDateAuditError(new AuditError("document.budgetPeriods[" + i + "].startDate",
                    KeyConstants.AUDIT_ERROR_BUDGETPERIOD_START_BEFORE_PROJECT_START_DATE,
                    Constants.BUDGET_PERIOD_PAGE + "." + Constants.BUDGET_PERIOD_PANEL_ANCHOR, new String[] {Integer.toString(i + 1)}));
            }
            if (budgetPeriodEndDate != null && budgetPeriodEndDate.after(projectEndDate)) {
                retval = false;
                this.addBudgetPeriodDateAuditError(new AuditError("document.budgetPeriods[" + i + "].endDate",
                    KeyConstants.AUDIT_ERROR_BUDGETPERIOD_END_AFTER_PROJECT_END_DATE,
                    Constants.BUDGET_PERIOD_PAGE + "." + Constants.BUDGET_PERIOD_PANEL_ANCHOR, new String[] {Integer.toString(i + 1)}));              
            }

            if (i == 0 && (budgetPeriodStartDate != null && budgetPeriodStartDate.after(projectStartDate))) {
                this.addBudgetPeriodDateAuditWarning(new AuditError("document.budgetPeriods[" + i + "].startDate",
                    KeyConstants.AUDIT_WARNING_BUDGETPERIOD_START_AFTER_PROJECT_START_DATE,
                    Constants.BUDGET_PERIOD_PAGE + "." + Constants.BUDGET_PERIOD_PANEL_ANCHOR, new String[] {Integer.toString(i + 1)}));
            }
            
            if (i == budget.getBudgetPeriods().size() - 1 && (budgetPeriodEndDate != null && budgetPeriodEndDate.before(projectEndDate))) {
                this.addBudgetPeriodDateAuditWarning(new AuditError("document.budgetPeriods[" + i + "].endDate",
                    KeyConstants.AUDIT_WARNING_BUDGETPERIOD_END_BEFORE_PROJECT_END_DATE,
                    Constants.BUDGET_PERIOD_PAGE + "." + Constants.BUDGET_PERIOD_PANEL_ANCHOR, new String[] {Integer.toString(i + 1)}));
            }
            i++;
        }
        return retval;
    }

    @KcEventMethod
    public boolean processRunAuditBusinessRules(BudgetAuditRuleEvent event) {
    	return verifyBudgetPeriods(event.getBudget());
    }
    
	protected boolean verifyBudgetPeriods(Budget budget) {
        boolean retval = true;
        BudgetParent budgetParent = budget.getBudgetParent().getDocument().getBudgetParent();
        Date projectStartDate = budgetParent.getRequestedStartDateInitial();
        Date projectEndDate = budgetParent.getRequestedEndDateInitial();
        int periodIndex = 0;
        BudgetConstants.BudgetAuditRules budgetPeriodAndTotalRule = BudgetConstants.BudgetAuditRules.PERIODS_AND_TOTALS;
		List<AuditError> auditWarnings = getAuditErrors(budgetPeriodAndTotalRule, false);
		List<AuditError> auditErrors = getAuditErrors(budgetPeriodAndTotalRule, true);
        for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
            Date budgetPeriodStartDate = budgetPeriod.getStartDate();
            Date budgetPeriodEndDate = budgetPeriod.getEndDate();
            
            if (budgetPeriodStartDate != null && budgetPeriodStartDate.before(projectStartDate)) {
            	auditErrors.add(new AuditError(budgetPeriodAndTotalRule.getPageId(),
                    KeyConstants.AUDIT_ERROR_BUDGETPERIOD_START_BEFORE_PROJECT_START_DATE,
                    budgetPeriodAndTotalRule.getPageId(), new String[] {budgetPeriod.getBudgetPeriod().toString()}));
                retval = false;
            }
            if (budgetPeriodEndDate != null && budgetPeriodEndDate.after(projectEndDate)) {
            	auditErrors.add(new AuditError(budgetPeriodAndTotalRule.getPageId(),
                    KeyConstants.AUDIT_ERROR_BUDGETPERIOD_END_AFTER_PROJECT_END_DATE,
                    budgetPeriodAndTotalRule.getPageId(), new String[] {budgetPeriod.getBudgetPeriod().toString()}));              
                retval = false;
            }

            if (periodIndex == 0 && (budgetPeriodStartDate != null && budgetPeriodStartDate.after(projectStartDate))) {
            	auditWarnings.add(new AuditError(budgetPeriodAndTotalRule.getPageId(),
                    KeyConstants.AUDIT_WARNING_BUDGETPERIOD_START_AFTER_PROJECT_START_DATE,
                    budgetPeriodAndTotalRule.getPageId(), new String[] {budgetPeriod.getBudgetPeriod().toString()}));
                retval = false;
            }
            
            if (periodIndex == budget.getBudgetPeriods().size() - 1 && (budgetPeriodEndDate != null && budgetPeriodEndDate.before(projectEndDate))) {
            	auditWarnings.add(new AuditError(budgetPeriodAndTotalRule.getPageId(),
                    KeyConstants.AUDIT_WARNING_BUDGETPERIOD_END_BEFORE_PROJECT_END_DATE,
                    budgetPeriodAndTotalRule.getPageId(), new String[] {budgetPeriod.getBudgetPeriod().toString()}));
                retval = false;
            }
            periodIndex++;
        }
        return retval;
	}
    
    /**
     * Adds an budget period date warning.
     * @param warning the warning
     */
    private void addBudgetPeriodDateAuditWarning(final AuditError warning) {
        assert warning != null : "the warning is null";

        errorReporter.reportAuditError(warning, BUDGET_PERIOD_DATE_AUDIT_WARNING_KEY, Constants.BUDGET_PERIOD_PANEL_NAME, Constants.AUDIT_WARNINGS);
    }
    
    /**
     * Adds an budget period date error.
     * @param error the error
     */
    private void addBudgetPeriodDateAuditError(final AuditError error) {
        assert error != null : "the error is null";

        errorReporter.reportAuditError(error, BUDGET_PERIOD_DATE_AUDIT_ERROR_KEY, Constants.BUDGET_PERIOD_PANEL_NAME, Constants.AUDIT_ERRORS);
    }

	public ErrorReporter getErrorReporter() {
		return errorReporter;
	}

	public void setErrorReporter(ErrorReporter errorReporter) {
		this.errorReporter = errorReporter;
	}
}
