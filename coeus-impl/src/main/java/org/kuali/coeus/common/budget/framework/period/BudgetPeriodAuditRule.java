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
package org.kuali.coeus.common.budget.framework.period;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.coeus.common.budget.impl.core.BudgetAuditEvent;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.Date;

@KcBusinessRule("budgetPeriodAuditRule")
public class BudgetPeriodAuditRule {

    private static final String BUDGET_PERIOD_DATE_AUDIT_ERROR_KEY = "budgetPeriodProjectDateAuditErrors";
    private static final String BUDGET_PERIOD_DATE_AUDIT_WARNING_KEY = "budgetPeriodProjectDateAuditWarnings";

    @Autowired
    @Qualifier("errorReporter")
    private ErrorReporter errorReporter;
    
    /**
     * Executes audit rules for budget periods.
     * @param document the budget document
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
     * @param budgetDocument the budget document
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
