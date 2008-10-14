/*
 * Copyright 2006-2008 The Kuali Foundation
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
import org.kuali.kra.budget.bo.BudgetUnrecoveredFandA;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;

public class BudgetUnrecoveredFandAAuditRule implements DocumentAuditRule {
    public static final String BUDGET_UNRECOVERED_F_AND_A_ERROR_KEY = "budgetUnrecoveredFandAAuditErrors";

    public boolean processRunAuditBusinessRules(Document document) {
        BudgetDocument budgetDocument = (BudgetDocument)document;
        
        // Returns if unrecovered f and a is not applicable
        if (!budgetDocument.isUnrecoveredFandAApplicable()) {
            return true;
        }
        
        List<BudgetUnrecoveredFandA> unrecoveredFandAs = budgetDocument.getBudgetUnrecoveredFandAs();
        boolean retval = true;
        
        // Forces full allocation of unrecovered f and a
        if (budgetDocument.getUnallocatedUnrecoveredFandA().isNonZero() && budgetDocument.isUnrecoveredFandAEnforced()) {
            retval = false;
            for (int i=0;i<unrecoveredFandAs.size(); i++) {
                getAuditErrors().add(new AuditError("document.budgetUnrecoveredFandA["+i+"].amount",
                        KeyConstants.AUDIT_ERROR_BUDGET_DISTRIBUTION_UNALLOCATED_NOT_ZERO,
                        Constants.BUDGET_DISTRIBUTION_AND_INCOME_PAGE + "." + Constants.BUDGET_UNRECOVERED_F_AND_A_PANEL_ANCHOR));
            }
        }
        String source = null;
        int i=0;
        // Forces inclusion of source account
        for (BudgetUnrecoveredFandA unrecoveredFandA : unrecoveredFandAs) {
            source = unrecoveredFandA.getSourceAccount();
            if (null == source || source.length() == 0) {
                retval = false;
                getAuditErrors().add(new AuditError("document.budgetUnrecoveredFandA["+i+"].sourceAccount",
                                                    KeyConstants.AUDIT_ERROR_BUDGET_DISTRIBUTION_SOURCE_MISSING,
                                                    Constants.BUDGET_DISTRIBUTION_AND_INCOME_PAGE + "." + Constants.BUDGET_UNRECOVERED_F_AND_A_PANEL_ANCHOR));
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
        
        if (!getAuditErrorMap().containsKey(BUDGET_UNRECOVERED_F_AND_A_ERROR_KEY)) {
            getAuditErrorMap().put(BUDGET_UNRECOVERED_F_AND_A_ERROR_KEY, new AuditCluster(Constants.BUDGET_UNRECOVERED_F_AND_A_PANEL_NAME, auditErrors, problemType));
        }
        else {
            auditErrors = ((AuditCluster) getAuditErrorMap().get(BUDGET_UNRECOVERED_F_AND_A_ERROR_KEY)).getAuditErrorList();
        }
        
        return auditErrors;
    }

}
