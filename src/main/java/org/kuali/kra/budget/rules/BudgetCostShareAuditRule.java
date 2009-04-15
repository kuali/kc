/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.budget.rules;

import static org.kuali.core.util.GlobalVariables.getAuditErrorMap;

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.document.Document;
import org.kuali.core.rule.DocumentAuditRule;
import org.kuali.core.util.AuditCluster;
import org.kuali.core.util.AuditError;
import org.kuali.kra.budget.bo.BudgetCostShare;
import org.kuali.kra.budget.bo.BudgetUnrecoveredFandA;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;

public class BudgetCostShareAuditRule implements DocumentAuditRule {
    public static final String BUDGET_COST_SHARE_ERROR_KEY = "budgetCostShareAuditErrors";

    public boolean processRunAuditBusinessRules(Document document) {
        BudgetDocument budgetDocument = (BudgetDocument)document;

        // Returns if cost sharing is not applicable
        if (!budgetDocument.isCostSharingApplicable()) {
            return true;
        }

        List<BudgetCostShare> costShares = budgetDocument.getBudgetCostShares();
        boolean retval = true;
        String[] params = { "Cost Sharing" };

        // Forces full allocation of cost sharing
        if (budgetDocument.getUnallocatedCostSharing().isNonZero() && budgetDocument.isCostSharingEnforced()) {
            retval = false;
            for (int i=0;i<costShares.size();i++) {
                getAuditErrors().add(new AuditError("document.budgetCostShare["+i+"].shareAmount",
                        KeyConstants.AUDIT_ERROR_BUDGET_DISTRIBUTION_UNALLOCATED_NOT_ZERO,
                        Constants.BUDGET_DISTRIBUTION_AND_INCOME_PAGE + "." + Constants.BUDGET_COST_SHARE_PANEL_ANCHOR,
                        params));
            }
        }
        String source = null;
        Integer fiscalYear = null;
        
        int i=0;
        // Forces inclusion of source account
        for (BudgetCostShare costShare : costShares) {
            source = costShare.getSourceAccount();
            fiscalYear = costShare.getFiscalYear();
            if (null == source || source.length() == 0) {
                retval = false;
                getAuditErrors().add(new AuditError("document.budgetCostShare["+i+"].sourceAccount",
                                                    KeyConstants.AUDIT_ERROR_BUDGET_DISTRIBUTION_SOURCE_MISSING,
                                                    Constants.BUDGET_DISTRIBUTION_AND_INCOME_PAGE + "." + Constants.BUDGET_COST_SHARE_PANEL_ANCHOR,
                                                    params));
            }
            if (null == fiscalYear || fiscalYear.intValue() <= 0) {
                retval = false;
                getAuditErrors().add(new AuditError("document.budgetCostShare["+i+"].fiscalYear",
                                                    KeyConstants.AUDIT_ERROR_BUDGET_DISTRIBUTION_FISCALYEAR_MISSING,
                                                    Constants.BUDGET_DISTRIBUTION_AND_INCOME_PAGE + "." + Constants.BUDGET_COST_SHARE_PANEL_ANCHOR,
                                                    params));
            }
            i++;
        }
        return retval;
    }
    
    /**
     * This method is a convenience method for obtaining audit errors.
     * @return List of AuditError instances
     */    
    private List<AuditError> getAuditErrors() {
        return getAuditProblems(Constants.AUDIT_ERRORS);
    }
    
    /**
     * This method is a convenience method for obtaining audit warnings.
     * @return List of AuditError instances
     */
    private List<AuditError> getAuditWarnings() {
        return getAuditProblems(Constants.AUDIT_WARNINGS);
    }
    
    /**
     * This method should only be called if an audit error is intending to be added because it will actually 
     * add a <code>{@link List<AuditError>}</code> to the auditErrorMap.
     * TODO : should this method move up to parent class
     * @return List of AuditError instances
     */
    private List<AuditError> getAuditProblems(String problemType) {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        
        if (!getAuditErrorMap().containsKey(BUDGET_COST_SHARE_ERROR_KEY)) {
            getAuditErrorMap().put(BUDGET_COST_SHARE_ERROR_KEY, new AuditCluster(Constants.BUDGET_COST_SHARE_PANEL_NAME, auditErrors, problemType));
        }
        else {
            auditErrors = ((AuditCluster) getAuditErrorMap().get(BUDGET_COST_SHARE_ERROR_KEY)).getAuditErrorList();
        }
        
        return auditErrors;
    }

}
