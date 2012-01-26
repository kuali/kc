/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget.distributionincome;

import static org.kuali.rice.kns.util.KNSGlobalVariables.getAuditErrorMap;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.Budget.FiscalYearSummary;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.costshare.CostShareRuleResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

/**
 * 
 * This class handels the budget cost share rules.
 */
public class BudgetCostShareAuditRule extends CostShareRuleResearchDocumentBase implements DocumentAuditRule {
    public static final String BUDGET_COST_SHARE_ERROR_KEY = "budgetCostShareAuditErrors";
    
    /**
     * 
     * @see org.kuali.rice.krad.rules.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.krad.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        Budget budget = ((BudgetDocument) document).getBudget();

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
                getAuditErrors()
                        .add(
                                new AuditError("document.budget.budgetCostShare",
                                    KeyConstants.AUDIT_ERROR_BUDGET_DISTRIBUTION_UNALLOCATED_NOT_ZERO,
                                    Constants.BUDGET_DISTRIBUTION_AND_INCOME_PAGE + "." + Constants.BUDGET_COST_SHARE_PANEL_ANCHOR,
                                    params));

            }
            for (int i = 0; i < costShares.size(); i++) {
                getAuditErrors()
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
                getAuditErrors()
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
                validatePeriodNumber(costShare, "document.budget.budgetCostShare[" + i + "].projectPeriod", numberOfProjectPeriods, getAuditErrors());
            }
            i++;
        }
        return retval;
    }

    /**
     * This method is a convenience method for obtaining audit errors.
     * 
     * @return List of AuditError instances
     */
    private List<AuditError> getAuditErrors() {
        return getAuditProblems(Constants.AUDIT_ERRORS);
    }

    /**
     * This method is a convenience method for obtaining audit warnings.
     * 
     * @return List of AuditError instances
     */
    private List<AuditError> getAuditWarnings() {
        return getAuditProblems(Constants.AUDIT_WARNINGS);
    }

    /**
     * This method should only be called if an audit error is intending to be added because it will actually add a
     * <code>{@link List<AuditError>}</code> to the auditErrorMap.
     * 
     * @return List of AuditError instances
     */
    private List<AuditError> getAuditProblems(String problemType) {
        List<AuditError> auditErrors = new ArrayList<AuditError>();

        if (!getAuditErrorMap().containsKey(BUDGET_COST_SHARE_ERROR_KEY)) {
            getAuditErrorMap().put(BUDGET_COST_SHARE_ERROR_KEY,
                    new AuditCluster(Constants.BUDGET_COST_SHARE_PANEL_NAME, auditErrors, problemType));
        }
        else {
            auditErrors = ((AuditCluster) getAuditErrorMap().get(BUDGET_COST_SHARE_ERROR_KEY)).getAuditErrorList();
        }

        return auditErrors;
    }

}
