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
package org.kuali.coeus.common.budget.framework.rate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetAuditEvent;
import org.kuali.coeus.common.budget.framework.core.BudgetAuditRuleBase;
import org.kuali.coeus.common.budget.framework.core.BudgetAuditRuleEvent;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@KcBusinessRule("budgetRateAuditRule")
public class BudgetRateAuditRule extends BudgetAuditRuleBase{

    public static final String RATE_CLASS_TYPES_MESSAGE = "Rate class types - ";
    @Autowired
	@Qualifier("budgetRatesService")
	private BudgetRatesService budgetRatesService;
	
    /**
     * 
     * This method is to validate budget period start/end date against project start/end date if
     * project start/end date have been adjusted.
     */
	@KcEventMethod
	@Deprecated
    public boolean processRunAuditBusinessRules(BudgetAuditEvent event) {
        boolean retval = true;
        HashSet<String> outOfSyncRates = getOutOfSyncRatesForAudit(event.getBudget());
        if (!outOfSyncRates.isEmpty()) {
        	addAuditErrors(outOfSyncRates);
            retval = false;
        }
        return retval;
    }

	@KcEventMethod
    public boolean processRunAuditBusinessRules(BudgetAuditRuleEvent event) {
        HashSet<String> outOfSyncRates = getOutOfSyncRatesForAudit(event.getBudget());
        if (!outOfSyncRates.isEmpty()) {
            BudgetConstants.BudgetAuditRules budgetRatesRule = BudgetConstants.BudgetAuditRules.RATES;
			List<AuditError> auditErrors = getAuditErrors(budgetRatesRule, false);
			String additionalMessage = RATE_CLASS_TYPES_MESSAGE + outOfSyncRates.toString();
            auditErrors.add(new AuditError(budgetRatesRule.getPageId(), 
                    KeyConstants.AUDIT_WARNING_RATE_OUT_OF_SYNC, budgetRatesRule.getPageId(),
                    new String[]{additionalMessage}));
        }
        return true;
    }
	
	protected BudgetRatesService getBudgetRatesService() {
		return budgetRatesService;
	}

	public void setBudgetRatesService(BudgetRatesService budgetRatesService) {
		this.budgetRatesService = budgetRatesService;
	}

    public HashSet<String> getOutOfSyncRatesForAudit(Budget budget) {
    	getBudgetRatesService().populateInstituteRates(budget);
        HashSet<String> outOfSyncRateClassTypes = new HashSet<String>();
        outOfSyncRateClassTypes.addAll(getOutOfSyncRateClassTypes(budget.getInstituteRates(), budget.getBudgetRates()));
        outOfSyncRateClassTypes.addAll(getOutOfSyncRateClassTypes(budget.getInstituteLaRates(), budget.getBudgetLaRates()));
        return outOfSyncRateClassTypes;
    }

    /**
     * 
     * This method is to check to the class type level
     * @param instituteRates
     * @param budgetRates
     * @return
     */
    @SuppressWarnings("unchecked")
    protected HashSet<String> getOutOfSyncRateClassTypes(List instituteRates, List budgetRates) {
        HashSet<String> unmatchedRateClassTypes = new HashSet<String>();
        String rateClassType = getUnMatchedRateClassType(instituteRates,budgetRates);
        if(rateClassType != null) {
        	unmatchedRateClassTypes.add(rateClassType);
        }
        rateClassType = getUnMatchedRateClassType(budgetRates, instituteRates);
        if(rateClassType != null) {
        	unmatchedRateClassTypes.add(rateClassType);
        }
        return unmatchedRateClassTypes;
    }
    
    protected String getUnMatchedRateClassType(List<AbstractInstituteRate> fromRates, List<AbstractInstituteRate> toRates) {
        boolean isRateMatched = false;
        for (Object rate : fromRates) {
            AbstractInstituteRate budgetRate = (AbstractInstituteRate)rate;
            for (Object rate1 : toRates) {
                AbstractInstituteRate instituteRate = (AbstractInstituteRate)rate1;
                if ((instituteRate.getRateKeyAsString()+instituteRate.getInstituteRate()).equals(budgetRate.getRateKeyAsString()+budgetRate.getInstituteRate())) {
                    if (instituteRate instanceof InstituteRate) {
                        if (((InstituteRate)instituteRate).getActivityTypeCode().equals(((BudgetRate)budgetRate).getActivityTypeCode())) {
                            isRateMatched = true;
                            break;                            
                        }
                    } else {
                        isRateMatched = true;
                        break;
                    }
                }
            }
            if (!isRateMatched) {
            	return budgetRate.getRateClass().getRateClassType().getDescription();
            }
        }
        return null;
    }
    
    protected void addAuditErrors(HashSet<String> rateClassTypes) {
    	for(String rateClassType : rateClassTypes) {
            String errorPath = "document.budgetProposalRate[" + rateClassType + "]";
            getAuditErrors().add(new AuditError(errorPath, KeyConstants.AUDIT_WARNING_RATE_OUT_OF_SYNC, Constants.BUDGET_RATE_PAGE + "." + rateClassType));
    	}
    }
    
    @SuppressWarnings("unchecked")
    protected List<AuditError> getAuditErrors() {
        String BUDGET_RATE_AUDIT_WARNING_KEY = "budgetRateAuditWarnings";
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        if (!getGlobalVariableService().getAuditErrorMap().containsKey(BUDGET_RATE_AUDIT_WARNING_KEY)) {
        	getGlobalVariableService().getAuditErrorMap().put(BUDGET_RATE_AUDIT_WARNING_KEY, new AuditCluster(Constants.BUDGET_RATE_PANEL_NAME, auditErrors, Constants.AUDIT_WARNINGS));
        }
        else {
            auditErrors = ((AuditCluster)getGlobalVariableService().getAuditErrorMap().get(BUDGET_RATE_AUDIT_WARNING_KEY)).getAuditErrorList();
        }
        return auditErrors;
    }
    
}

