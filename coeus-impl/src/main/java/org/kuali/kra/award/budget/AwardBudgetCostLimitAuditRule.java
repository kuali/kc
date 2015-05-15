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
package org.kuali.kra.award.budget;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.budget.calculator.AwardBudgetCalculationService;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

import static org.kuali.rice.krad.util.GlobalVariables.getAuditErrorMap;

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
        AwardBudgetExt budget = (AwardBudgetExt)((AwardBudgetDocument)document).getBudget();

        Award currentAward = getAwardBudgetService().getActiveOrNewestAward(((AwardDocument) awardBudgetDocument.getBudget().getBudgetParent().getDocument()).getAward().getAwardNumber());
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
                ScaleTwoDecimal total = (ScaleTwoDecimal) ObjectUtils.getPropertyValue(budget, budgetLimit.getLimitType().getBudgetProperty());
                if (prevBudget != null) {
                    total = total.add((ScaleTwoDecimal) ObjectUtils.getPropertyValue(prevBudget, budgetLimit.getLimitType().getBudgetProperty()));
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
    
    private AwardBudgetExt loadBudget(AwardBudgetExt budget) {
        AwardBudgetExt retval = null;
        if (budget != null && budget.getBudgetId() != null) { 
            retval = getBusinessObjectService().findBySinglePrimaryKey(AwardBudgetExt.class, budget.getBudgetId());
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
                    || !org.apache.commons.lang3.ObjectUtils.equals(limit.getLimit(), budgetLimit.getLimit())) {
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
     * add a <code>{@link List&lt;AuditError&gt;}</code> to the auditErrorMap.
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
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    protected AwardBudgetCalculationService getAwardBudgetCalculationService() {
        if (awardBudgetCalculationService == null) {
            awardBudgetCalculationService = KcServiceLocator.getService(AwardBudgetCalculationService.class);
        }
        return awardBudgetCalculationService;
    }

    public void setAwardBudgetCalculationService(AwardBudgetCalculationService awardBudgetCalculationService) {
        this.awardBudgetCalculationService = awardBudgetCalculationService;
    }

    protected AwardBudgetService getAwardBudgetService() {
        if (awardBudgetService == null) {
            awardBudgetService = KcServiceLocator.getService(AwardBudgetService.class);
        }
        return awardBudgetService;
    }

    public void setAwardBudgetService(AwardBudgetService awardBudgetService) {
        this.awardBudgetService = awardBudgetService;
    }
    
    



}
