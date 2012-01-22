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

import static org.kuali.rice.kns.util.KNSGlobalVariables.getAuditErrorMap;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.award.budget.calculator.AwardBudgetCalculationService;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * 
 * Audits the budget and warns if the budget limits set by the award have changed and
 * errors if any of the defined limits have been exceeded.
 */
public class AwardBudgetCostLimitAuditRule implements DocumentAuditRule {
    public static final String AWARD_BUDGET_COST_LIMIT_ERROR_KEY = "awardBudgetCostLimitAuditErrors";
    public static final String AWARD_BUDGET_COST_LIMIT_WARNING_KEY = "awardBudgetCostLimitAuditWarnings";
    
    private BusinessObjectService businessObjectService;
    private AwardBudgetCalculationService awardBudgetCalculationService;
    private AwardBudgetService awardBudgetService;

    public boolean processRunAuditBusinessRules(Document document) {
        AwardBudgetDocument awardBudgetDocument = (AwardBudgetDocument) document;
        AwardBudgetExt budget = (AwardBudgetExt)((BudgetDocument)document).getBudget();

        Award currentAward = getAwardBudgetService().getActiveOrNewestAward(((AwardDocument) awardBudgetDocument.getParentDocument()).getAward().getAwardNumber());
        boolean valid = true;
        valid &= limitsMatch(currentAward.getAwardBudgetLimits(),
                budget.getAwardBudgetLimits());
        
        AwardBudgetExt prevBudget = loadBudget(budget.getPrevBudget());
        if (prevBudget != null) {
            getAwardBudgetCalculationService().calculateBudgetSummaryTotals(prevBudget, true);
        }
        
        for (AwardBudgetLimit budgetLimit : budget.getAwardBudgetLimits()) {
            if (budgetLimit.getLimitType() == AwardBudgetLimit.LIMIT_TYPE.TOTAL_COST) {
                //total cost is validated as the budget change total so ignore here
                continue;
            }
            if (budgetLimit.getLimit() != null) {
                BudgetDecimal total = (BudgetDecimal) ObjectUtils.getPropertyValue(budget, budgetLimit.getLimitType().getBudgetProperty());
                if (prevBudget != null) {
                    total = total.add((BudgetDecimal) ObjectUtils.getPropertyValue(prevBudget, budgetLimit.getLimitType().getBudgetProperty()));
                }
                if (total.isGreaterThan(budgetLimit.getLimit())) {
                    getAuditErrors().add(new AuditError("document.budget." + budgetLimit.getLimitType().getBudgetProperty(),
                            KeyConstants.AUDIT_ERROR_COST_LIMIT,
                            Constants.BUDGET_PERIOD_PAGE + "." + "BudgetPeriodsTotals",
                            new String[]{budgetLimit.getLimitType().getDesc(), budgetLimit.getLimit().toString()}));
                    valid = false;
                }
            }
        }
        return valid;
    }
    
    private AwardBudgetExt loadBudget(BudgetVersionOverview budgetOverview) {
        AwardBudgetExt retval = null;
        if (budgetOverview != null && budgetOverview.getBudgetId() != null) { 
            retval = getBusinessObjectService().findBySinglePrimaryKey(AwardBudgetExt.class, budgetOverview.getBudgetId());
        }
        return retval;
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
        return new AwardBudgetLimit(type);
    }
    
    /**
     * 
     * Compares the budget limit lists to make sure they match.
     * @param awardLimits
     * @param budgetLimits
     * @return
     */
    protected boolean limitsMatch(List<AwardBudgetLimit> awardLimits, List<AwardBudgetLimit> budgetLimits) {
        if (awardLimits.size() < budgetLimits.size()) {
            getAuditWarnings().add(new AuditError("document.budget.awardBudgetLimits",
                    KeyConstants.AUDIT_ERROR_COST_LIMITS_CHANGED,
                    Constants.BUDGET_PERIOD_PAGE + "." + "BudgetPeriodsTotals"));
            return true;
        }
        
        for (AwardBudgetLimit limit : awardLimits) {
            AwardBudgetLimit budgetLimit = getBudgetLimit(limit.getLimitType(), budgetLimits);
            if (budgetLimit == null 
                    || !org.apache.commons.lang.ObjectUtils.equals(limit.getLimit(), budgetLimit.getLimit())) {
               getAuditWarnings().add(new AuditError("document.budget.awardBudgetLimits",
                        KeyConstants.AUDIT_ERROR_SPECIFIC_COST_LIMITS_CHANGED,
                        Constants.BUDGET_PERIOD_PAGE + "." + "BudgetPeriodsTotals",
                        new String[]{budgetLimit.getLimitType().getDesc(), 
                            budgetLimit == null || budgetLimit.getLimit() == null ? "N/A" : budgetLimit.getLimit().toString(), 
                            limit == null || limit.getLimit() == null ? "N/A" : limit.getLimit().toString()}));
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

    protected BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    protected AwardBudgetCalculationService getAwardBudgetCalculationService() {
        if (awardBudgetCalculationService == null) {
            awardBudgetCalculationService = KraServiceLocator.getService(AwardBudgetCalculationService.class);
        }
        return awardBudgetCalculationService;
    }

    public void setAwardBudgetCalculationService(AwardBudgetCalculationService awardBudgetCalculationService) {
        this.awardBudgetCalculationService = awardBudgetCalculationService;
    }

    protected AwardBudgetService getAwardBudgetService() {
        if (awardBudgetService == null) {
            awardBudgetService = KraServiceLocator.getService(AwardBudgetService.class);
        }
        return awardBudgetService;
    }

    public void setAwardBudgetService(AwardBudgetService awardBudgetService) {
        this.awardBudgetService = awardBudgetService;
    }
    
    



}
