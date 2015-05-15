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
package org.kuali.coeus.common.budget.impl.distribution;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetAuditEvent;
import org.kuali.coeus.common.budget.framework.core.BudgetAuditRuleBase;
import org.kuali.coeus.common.budget.framework.core.BudgetAuditRuleEvent;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.coeus.common.budget.framework.distribution.BudgetUnrecoveredFandA;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.ObjectUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.kuali.rice.krad.util.GlobalVariables.getAuditErrorMap;

@KcBusinessRule("budgetUnrecoveredFandAAuditRule")
public class BudgetUnrecoveredFandAAuditRule extends BudgetAuditRuleBase {
    public static final String BUDGET_UNRECOVERED_F_AND_A_ERROR_KEY = "budgetUnrecoveredFandAAuditErrors";
    public static final String BUDGET_UNRECOVERED_F_AND_A_WARNING_KEY = "budgetUnrecoveredFandAAuditWarnings";
    
    String[] params = { "Unrecovered F and A" };
    private static final int YEAR_CONSTANT = 1900;

    // Proposal Budget only event possibly
    @KcEventMethod
    @Deprecated
    public boolean processRunAuditBusinessRules(BudgetAuditEvent event) {
    	Budget budget = event.getBudget();
        if (getAuditErrorMap().containsKey(BUDGET_UNRECOVERED_F_AND_A_ERROR_KEY)) {
            List auditErrors = ((AuditCluster) getAuditErrorMap().get(BUDGET_UNRECOVERED_F_AND_A_ERROR_KEY)).getAuditErrorList();
            auditErrors.clear();
        }
        
        // Returns if unrecovered f and a is not applicable
        if (!budget.isUnrecoveredFandAApplicable()) {
            return true;
        }
        
        List<BudgetUnrecoveredFandA> unrecoveredFandAs = budget.getBudgetUnrecoveredFandAs();
        boolean retval = true;
        
        // Forces full allocation of unrecovered f and a
        if (budget.getUnallocatedUnrecoveredFandA().isGreaterThan(ScaleTwoDecimal.ZERO) && budget.isUnrecoveredFandAEnforced()) {
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
        BudgetParent budgetParent = budget.getBudgetParent();
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

    @KcEventMethod
    public boolean processRunAuditBusinessRules(BudgetAuditRuleEvent event) {
    	Budget budget = event.getBudget();
        boolean retval = true;
        List<BudgetUnrecoveredFandA> unrecoveredFandAs = budget.getBudgetUnrecoveredFandAs();
        if (budget.isUnrecoveredFandAApplicable()) {
        	retval &= verifyUnrecoveredFA(budget, unrecoveredFandAs);
        	retval &= verifySourceAccount(budget, unrecoveredFandAs);
        }
    	return retval;
    }
    
    protected boolean verifyUnrecoveredFA(Budget budget, List<BudgetUnrecoveredFandA> unrecoveredFandAs) {
        boolean retval = true;
        BudgetConstants.BudgetAuditRules budgetUnrecoveredFARule = BudgetConstants.BudgetAuditRules.UNRECOVERED_FA;
        
        // Forces full allocation of unrecovered f and a
        if (budget.getUnallocatedUnrecoveredFandA().isGreaterThan(ScaleTwoDecimal.ZERO) && budget.isUnrecoveredFandAEnforced()) {
            retval = false;
			List<AuditError> auditErrors = getAuditErrors(budgetUnrecoveredFARule, false);
            if (unrecoveredFandAs.isEmpty()) {
    			auditErrors.add(new AuditError("budget.budgetUnrecoveredFandA",
                        KeyConstants.AUDIT_ERROR_BUDGET_DISTRIBUTION_UNALLOCATED_NOT_ZERO,
                        budgetUnrecoveredFARule.getPageId(), params));
    			retval = false;
                
            }else {
                for (BudgetUnrecoveredFandA unrecoveredFandA : unrecoveredFandAs) {
                	auditErrors.add(new AuditError(budgetUnrecoveredFARule.getPageId(),
                            KeyConstants.AUDIT_ERROR_BUDGET_DISTRIBUTION_UNALLOCATED_NOT_ZERO,
                            budgetUnrecoveredFARule.getPageId(), params));
                	retval = false;
                }
            }
        }
        return retval;
    }

    protected boolean verifySourceAccount(Budget budget, List<BudgetUnrecoveredFandA> unrecoveredFandAs) {
        boolean retval = true;
        String source = null;
        Integer fiscalYear = null;
        
        int i=0;
        int j=0;
        BudgetParent budgetParent = budget.getBudgetParent();
        Date projectStartDate = budgetParent.getRequestedStartDateInitial();
        Date projectEndDate = budgetParent.getRequestedEndDateInitial();
        BudgetConstants.BudgetAuditRules budgetUnrecoveredFARule = BudgetConstants.BudgetAuditRules.UNRECOVERED_FA;
		List<AuditError> auditErrors = getAuditErrors(budgetUnrecoveredFARule, true);
		List<AuditError> auditWarnings = getAuditErrors(budgetUnrecoveredFARule, false);

        // Forces inclusion of source account
        boolean duplicateEntryFound = false;
        for (BudgetUnrecoveredFandA unrecoveredFandA : unrecoveredFandAs) {
            source = unrecoveredFandA.getSourceAccount();
            fiscalYear = unrecoveredFandA.getFiscalYear();
            
            if (StringUtils.isEmpty(source) || source.length() == 0) {
                auditErrors.add(new AuditError(budgetUnrecoveredFARule.getPageId(),
                                                    KeyConstants.AUDIT_ERROR_BUDGET_DISTRIBUTION_SOURCE_MISSING,
                                                    budgetUnrecoveredFARule.getPageId(), params));
                retval = false;
            }
            if (fiscalYear == null || fiscalYear.intValue() <= 0) {
                auditErrors.add(new AuditError(budgetUnrecoveredFARule.getPageId(),
                                                    KeyConstants.AUDIT_ERROR_BUDGET_DISTRIBUTION_FISCALYEAR_MISSING,
                                                    budgetUnrecoveredFARule.getPageId(), params));
                retval = false;
           }
            
            if (fiscalYear != null && (fiscalYear < projectStartDate.getYear() + YEAR_CONSTANT || fiscalYear > projectEndDate.getYear() + YEAR_CONSTANT)) {
            	auditWarnings.add(new AuditError(budgetUnrecoveredFARule.getPageId(), 
                                                      KeyConstants.AUDIT_WARNING_BUDGET_DISTRIBUTION_FISCALYEAR_INCONSISTENT, 
                                                      budgetUnrecoveredFARule.getPageId(), params));
                retval = false;
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
                        auditErrors.add(new AuditError(budgetUnrecoveredFARule.getPageId(),
                                KeyConstants.AUDIT_ERROR_BUDGET_DISTRIBUTION_DUPLICATE_UNRECOVERED_FA, 
                                budgetUnrecoveredFARule.getPageId(), params));
                        duplicateEntryFound = true;
                        retval = false;
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
     * add a <code>{@link List&lt;AuditError&gt;}</code> to the auditErrorMap.
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
