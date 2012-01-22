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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetParent;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

public class BudgetUnrecoveredFandAAuditRule implements DocumentAuditRule {
    public static final String BUDGET_UNRECOVERED_F_AND_A_ERROR_KEY = "budgetUnrecoveredFandAAuditErrors";
    public static final String BUDGET_UNRECOVERED_F_AND_A_WARNING_KEY = "budgetUnrecoveredFandAAuditWarnings";
    
    String[] params = { "Unrecovered F and A" };
    private static final int YEAR_CONSTANT = 1900;

    public boolean processRunAuditBusinessRules(Document document) {
        Budget budget = ((BudgetDocument)document).getBudget();
        if (getAuditErrorMap().containsKey(BUDGET_UNRECOVERED_F_AND_A_ERROR_KEY)) {
            List auditErrors = ((AuditCluster) getAuditErrorMap().get(BUDGET_UNRECOVERED_F_AND_A_ERROR_KEY)).getAuditErrorList();
            auditErrors.clear();
        }
//        getAuditErrors().clear();
        
        // Returns if unrecovered f and a is not applicable
        if (!budget.isUnrecoveredFandAApplicable()) {
            return true;
        }
        
        List<BudgetUnrecoveredFandA> unrecoveredFandAs = budget.getBudgetUnrecoveredFandAs();
        boolean retval = true;
        
        // Forces full allocation of unrecovered f and a
        if (budget.getUnallocatedUnrecoveredFandA().isGreaterThan(BudgetDecimal.ZERO) && budget.isUnrecoveredFandAEnforced()) {
            retval = false;
            if (unrecoveredFandAs.size() ==0) {
                getAuditErrors().add(new AuditError("document.budget.budgetUnrecoveredFandA",
                        KeyConstants.AUDIT_ERROR_BUDGET_DISTRIBUTION_UNALLOCATED_NOT_ZERO,
                        Constants.BUDGET_DISTRIBUTION_AND_INCOME_PAGE + "." + Constants.BUDGET_UNRECOVERED_F_AND_A_PANEL_ANCHOR,
                        params));
                
            }            
            for (int i=0;i<unrecoveredFandAs.size(); i++) {
                getAuditErrors().add(new AuditError("document.budget.budgetUnrecoveredFandA["+i+"].amount",
                        KeyConstants.AUDIT_ERROR_BUDGET_DISTRIBUTION_UNALLOCATED_NOT_ZERO,
                        Constants.BUDGET_DISTRIBUTION_AND_INCOME_PAGE + "." + Constants.BUDGET_UNRECOVERED_F_AND_A_PANEL_ANCHOR,
                        params));
            }
        } 
        String source = null;
        Integer fiscalYear = null;
        
        int i=0;
        int j=0;
        BudgetParent budgetParent = budget.getBudgetDocument().getParentDocument().getBudgetParent();
        Date projectStartDate = budgetParent.getRequestedStartDateInitial();
        Date projectEndDate = budgetParent.getRequestedEndDateInitial();

        // Forces inclusion of source account
        boolean duplicateEntryFound = false;
        for (BudgetUnrecoveredFandA unrecoveredFandA : unrecoveredFandAs) {
            source = unrecoveredFandA.getSourceAccount();
            fiscalYear = unrecoveredFandA.getFiscalYear();
            
            if (null == source || source.length() == 0) {
                retval = false;
                getAuditErrors().add(new AuditError("document.budget.budgetUnrecoveredFandA["+i+"].sourceAccount",
                                                    KeyConstants.AUDIT_ERROR_BUDGET_DISTRIBUTION_SOURCE_MISSING,
                                                    Constants.BUDGET_DISTRIBUTION_AND_INCOME_PAGE + "." + Constants.BUDGET_UNRECOVERED_F_AND_A_PANEL_ANCHOR,
                                                    params));
            }
            if (null == fiscalYear || fiscalYear.intValue() <= 0) {
                retval = false;
                getAuditErrors().add(new AuditError("document.budget.budgetUnrecoveredFandA["+i+"].fiscalYear",
                                                    KeyConstants.AUDIT_ERROR_BUDGET_DISTRIBUTION_FISCALYEAR_MISSING,
                                                    Constants.BUDGET_DISTRIBUTION_AND_INCOME_PAGE + "." + Constants.BUDGET_UNRECOVERED_F_AND_A_PANEL_ANCHOR,
                                                    params));
            }
            
            if (fiscalYear != null && (fiscalYear < projectStartDate.getYear() + YEAR_CONSTANT || fiscalYear > projectEndDate.getYear() + YEAR_CONSTANT)) {
                getAuditWarnings().add(new AuditError("document.budget.budgetUnrecoveredFandA["+i+"].fiscalYear", 
                                                      KeyConstants.AUDIT_WARNING_BUDGET_DISTRIBUTION_FISCALYEAR_INCONSISTENT, 
                                                      Constants.BUDGET_DISTRIBUTION_AND_INCOME_PAGE + "." + Constants.BUDGET_UNRECOVERED_F_AND_A_PANEL_ANCHOR, 
                                                      params));
            }
            
            if(!duplicateEntryFound) {
                j=0;
                for (BudgetUnrecoveredFandA unrecoveredFandAForComparison : unrecoveredFandAs) {
                    if(i != j && unrecoveredFandA.getFiscalYear() != null && unrecoveredFandAForComparison.getFiscalYear() != null && 
                            unrecoveredFandA.getFiscalYear().intValue() == unrecoveredFandAForComparison.getFiscalYear().intValue() &&
                            unrecoveredFandA.getApplicableRate().equals( unrecoveredFandAForComparison.getApplicableRate()) && 
                            unrecoveredFandA.getOnCampusFlag().equalsIgnoreCase(unrecoveredFandAForComparison.getOnCampusFlag()) && 
                            StringUtils.equalsIgnoreCase(unrecoveredFandA.getSourceAccount(), unrecoveredFandAForComparison.getSourceAccount()) &&
                            unrecoveredFandA.getAmount().equals( unrecoveredFandAForComparison.getAmount())) {
                        retval = false;
                        getAuditErrors().add(new AuditError("document.budget.budgetUnrecoveredFandA["+i+"]",
                                KeyConstants.AUDIT_ERROR_BUDGET_DISTRIBUTION_DUPLICATE_UNRECOVERED_FA,
                                Constants.BUDGET_DISTRIBUTION_AND_INCOME_PAGE + "." + Constants.BUDGET_UNRECOVERED_F_AND_A_PANEL_ANCHOR,
                                params));
                        duplicateEntryFound = true;
                        break;
                    }
                    j++;
                }
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
        return getAuditProblems(BUDGET_UNRECOVERED_F_AND_A_ERROR_KEY, Constants.AUDIT_ERRORS);
    }
    
    /**
     * This method is a convenience method for obtaining audit warnings.
     * @return List of AuditError instances
     */
    private List<AuditError> getAuditWarnings() {
        return getAuditProblems(BUDGET_UNRECOVERED_F_AND_A_WARNING_KEY, Constants.AUDIT_WARNINGS);
    }
    
    /**
     * This method should only be called if an audit error is intending to be added because it will actually 
     * add a <code>{@link List<AuditError>}</code> to the auditErrorMap.
     * @return List of AuditError instances
     */
    private List<AuditError> getAuditProblems(String key, String problemType) {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        
        if (!getAuditErrorMap().containsKey(key)) {
            getAuditErrorMap().put(key, new AuditCluster(Constants.BUDGET_UNRECOVERED_F_AND_A_PANEL_NAME, auditErrors, problemType));
        }
        else {
            auditErrors = ((AuditCluster) getAuditErrorMap().get(key)).getAuditErrorList();
        }
        
        return auditErrors;
    }

}
