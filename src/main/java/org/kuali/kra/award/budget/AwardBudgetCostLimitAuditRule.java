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
package org.kuali.kra.award.budget;

import static org.kuali.rice.kns.util.GlobalVariables.getAuditErrorMap;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.DocumentAuditRule;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;

/**
 * 
 * Audits the budget and warns if the budget limits set by the award have changed and
 * errors if any of the defined limits have been exceeded.
 */
public class AwardBudgetCostLimitAuditRule implements DocumentAuditRule {
    public static final String AWARD_BUDGET_COST_LIMIT_ERROR_KEY = "awardBudgetCostLimitAuditErrors";
    public static final String AWARD_BUDGET_COST_LIMIT_WARNING_KEY = "awardBudgetCostLimitAuditWarnings";

    public boolean processRunAuditBusinessRules(Document document) {
        AwardBudgetDocument awardBudgetDocument = (AwardBudgetDocument) document;
        AwardBudgetExt budget = (AwardBudgetExt)((BudgetDocument)document).getBudget();

        boolean valid = true;
        if (!limitsMatch(((AwardDocument) awardBudgetDocument.getParentDocument()).getAward().getAwardBudgetLimits(),
                budget.getAwardBudgetLimits())) {
            getAuditWarnings().add(new AuditError("document.budget.awardBudgetLimits",
                    KeyConstants.AUDIT_ERROR_COST_LIMITS_CHANGED,
                    Constants.BUDGET_PERIOD_PAGE + "." + "BudgetPeriodsTotals"));
            valid = false;  
            
        }
        AwardBudgetLimit directLimit = getBudgetLimit(AwardBudgetLimit.LIMIT_TYPE.DIRECT_COST, budget.getAwardBudgetLimits());
        BudgetDecimal totalDirect = budget.getTotalDirectCost();
        if (budget.getPrevBudget() != null) {
            totalDirect = totalDirect.add(budget.getPrevBudget().getTotalDirectCost());
        }
        if (directLimit.getLimit() != null 
                && !totalDirect.isLessEqual(directLimit.getLimit())) {
            getAuditErrors().add(new AuditError("document.budget.totalDirectCost",
                    KeyConstants.AUDIT_ERROR_COST_LIMIT,
                    Constants.BUDGET_PERIOD_PAGE + "." + "BudgetPeriodsTotals",
                    new String[]{"Total Direct", directLimit.getLimit().toString()}));
            valid = false;  
        }
        AwardBudgetLimit indirectLimit = getBudgetLimit(AwardBudgetLimit.LIMIT_TYPE.INDIRECT_COST, budget.getAwardBudgetLimits());
        BudgetDecimal totalIndirect = budget.getTotalIndirectCost();
        if (budget.getPrevBudget() != null) {
            totalIndirect = totalIndirect.add(budget.getPrevBudget().getTotalIndirectCost());
        }
        if (indirectLimit.getLimit() != null 
                && !totalIndirect.isLessEqual(indirectLimit.getLimit())) {
            getAuditErrors().add(new AuditError("document.budget.totalIndirectCost",
                    KeyConstants.AUDIT_ERROR_COST_LIMIT,
                    Constants.BUDGET_PERIOD_PAGE + "." + "BudgetPeriodsTotals",
                    new String[]{"Total F&A", indirectLimit.getLimit().toString()}));
            valid = false;
        } 

        return valid;
    }
    
    /**
     * This method is a convenience method for obtaining audit errors.
     * @return List of AuditError instances
     */    
    private List<AuditError> getAuditErrors() {
        return getAuditProblems(AWARD_BUDGET_COST_LIMIT_ERROR_KEY, Constants.AUDIT_ERRORS);
    }
    
    private List<AuditError> getAuditWarnings() {
        return getAuditProblems(AWARD_BUDGET_COST_LIMIT_WARNING_KEY, Constants.AUDIT_WARNINGS);
    }
    
    /**
     * 
     * Get the specific budget limit from the budget list
     * @param type
     * @param budgetLimits
     * @return
     */
    protected AwardBudgetLimit getBudgetLimit(AwardBudgetLimit.LIMIT_TYPE type, List<AwardBudgetLimit> budgetLimits) {
        for (AwardBudgetLimit limit : budgetLimits) {
            if (limit.getLimitType() == type) {
                return limit;
            }
        }
        //return empty budget limit to simplify logic above
        return new AwardBudgetLimit();
    }
    
    /**
     * 
     * Compares the budget limit lists to make sure they match.
     * @param awardLimits
     * @param budgetLimits
     * @return
     */
    protected boolean limitsMatch(List<AwardBudgetLimit> awardLimits, List<AwardBudgetLimit> budgetLimits) {
        if (awardLimits.size() != budgetLimits.size()) {
            return false;
        }
        
        for (AwardBudgetLimit limit : awardLimits) {
            AwardBudgetLimit budgetLimit = getBudgetLimit(limit.getLimitType(), budgetLimits);
            if (budgetLimit == null 
                    || !ObjectUtils.equals(limit.getLimit(), budgetLimit.getLimit())) {
                return false;
            }
        }
        return true;
        
    }
    
    
    /**
     * This method should only be called if an audit error is intending to be added because it will actually 
     * add a <code>{@link List<AuditError>}</code> to the auditErrorMap.
     * @return List of AuditError instances
     */
    private List<AuditError> getAuditProblems(String auditKey, String problemType) {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        
        if (!getAuditErrorMap().containsKey(auditKey)) {
            getAuditErrorMap().put(auditKey, new AuditCluster("Award Budget Limits", auditErrors, problemType));
        }
        else {
            auditErrors = ((AuditCluster) getAuditErrorMap().get(auditKey)).getAuditErrorList();
        }
        
        return auditErrors;
    }
    
    



}
