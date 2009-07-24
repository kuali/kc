/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.budget.rules;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Audit rules for budget periods.
 */
public class BudgetPeriodAuditRule extends ResearchDocumentRuleBase {

    private static final String BUDGET_PERIOD_DATE_AUDIT_ERROR_KEY = "budgetPeriodProjectDateAuditErrors";
    private static final String BUDGET_PERIOD_DATE_AUDIT_WARNING_KEY = "budgetPeriodProjectDateAuditWarnings";

    /**
     * Executes audit rules for budget periods.
     * @param document the budget document
     * @return true if valid false if not
     * @throws NullPointerException if the document is null
     */
    @Override
    public boolean processRunAuditBusinessRules(final Document document) {
        if (document == null) {
            throw new NullPointerException("the document is null");
        }
        
        final BudgetDocument budgetDocument = (BudgetDocument) document;
        return this.validatePeriodDates(budgetDocument);
    }
    
    /**
     * This method is to validate budget period start/end date against project start/end date if
     * project start/end date have been adjusted.
     * @param budgetDocument the budget document
     * @return true if valid false if not
     */
    private boolean validatePeriodDates(final BudgetDocument budgetDocument) {
        assert budgetDocument != null : "the document is null";
        boolean retval = true;
        
        final Date projectStartDate = budgetDocument.getProposal().getDevelopmentProposal().getRequestedStartDateInitial();
        final Date projectEndDate = budgetDocument.getProposal().getDevelopmentProposal().getRequestedEndDateInitial();
        
        int i = 0;
        for (final BudgetPeriod budgetPeriod : budgetDocument.getBudgetPeriods()) {
            if (budgetPeriod.getStartDate().before(projectStartDate)) {
                retval = false;
                this.addBudgetPeriodDateAuditError(new AuditError("document.budgetPeriods[" + i + "].startDate",
                    KeyConstants.AUDIT_ERROR_BUDGETPERIOD_START_BEFORE_PROJECT_START_DATE,
                    Constants.BUDGET_PERIOD_PAGE + "." + Constants.BUDGET_PERIOD_PANEL_ANCHOR, new String[] {Integer.toString(i+1)}));
            }
            if (budgetPeriod.getEndDate().after(projectEndDate)) {
                retval = false;
                this.addBudgetPeriodDateAuditError(new AuditError("document.budgetPeriods[" + i + "].endDate",
                    KeyConstants.AUDIT_ERROR_BUDGETPERIOD_END_AFTER_PROJECT_END_DATE,
                    Constants.BUDGET_PERIOD_PAGE + "." + Constants.BUDGET_PERIOD_PANEL_ANCHOR, new String[] {Integer.toString(i+1)}));              
            }

            if (i == 0 && budgetPeriod.getStartDate().after(projectStartDate)) {
                this.addBudgetPeriodDateAuditWarning(new AuditError("document.budgetPeriods[" + i + "].startDate",
                    KeyConstants.AUDIT_WARNING_BUDGETPERIOD_START_AFTER_PROJECT_START_DATE,
                    Constants.BUDGET_PERIOD_PAGE + "." + Constants.BUDGET_PERIOD_PANEL_ANCHOR, new String[] {Integer.toString(i+1)}));
            }
            
            if (i == budgetDocument.getBudgetPeriods().size() - 1 && budgetPeriod.getEndDate().before(projectEndDate)) {
                this.addBudgetPeriodDateAuditWarning(new AuditError("document.budgetPeriods[" + i + "].endDate",
                    KeyConstants.AUDIT_WARNING_BUDGETPERIOD_END_BEFORE_PROJECT_END_DATE,
                    Constants.BUDGET_PERIOD_PAGE + "." + Constants.BUDGET_PERIOD_PANEL_ANCHOR, new String[] {Integer.toString(i+1)}));
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

        this.addAuditError(warning, BUDGET_PERIOD_DATE_AUDIT_WARNING_KEY, Constants.BUDGET_PERIOD_PANEL_NAME, Constants.AUDIT_WARNINGS);
    }
    
    /**
     * Adds an budget period date error.
     * @param warning the error
     */
    private void addBudgetPeriodDateAuditError(final AuditError error) {
        assert error != null : "the error is null";

        this.addAuditError(error, BUDGET_PERIOD_DATE_AUDIT_ERROR_KEY, Constants.BUDGET_PERIOD_PANEL_NAME, Constants.AUDIT_ERRORS);
    }
    
    /**
     * Adds an budget period date error.
     * @param warning the error
     */
    private List<AuditError> getAuditErrors() {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        
        if (!GlobalVariables.getAuditErrorMap().containsKey(BUDGET_PERIOD_DATE_AUDIT_ERROR_KEY)) {
           GlobalVariables.getAuditErrorMap().put(BUDGET_PERIOD_DATE_AUDIT_ERROR_KEY, new AuditCluster(Constants.BUDGET_PERIOD_PANEL_NAME, auditErrors, Constants.AUDIT_ERRORS));
        }
        else {
            auditErrors = ((AuditCluster)GlobalVariables.getAuditErrorMap().get(BUDGET_PERIOD_DATE_AUDIT_ERROR_KEY)).getAuditErrorList();
        }
        
        return auditErrors;
    }
}
