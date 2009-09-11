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
package org.kuali.kra.budget.distributionincome;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.RateDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.Budget.FiscalYearSummary;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.KualiConfigurationService;

public class BudgetDistributionAndIncomeServiceImpl implements BudgetDistributionAndIncomeService {

    private KualiConfigurationService kualiConfigurationService = null;
    protected KualiConfigurationService getKualiConfigurationService() {
        if ( kualiConfigurationService == null ) {
            kualiConfigurationService = KraServiceLocator.getService(KualiConfigurationService.class);
        }
        return kualiConfigurationService;
    }
    protected void setKualiConfigurationService(KualiConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }
    
    
    protected boolean isBudgetFinalAndComplete(Budget budget) {
        String budgetStatusCompleteValue = getKualiConfigurationService().getParameter(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_STATUS_COMPLETE_CODE).getParameterValue();
        return (budget.getFinalVersionFlag() && budgetStatusCompleteValue.equals(budget.getBudgetStatus()));
    }
    
    /**
     * @see org.kuali.kra.budget.distributionincome.BudgetDistributionAndIncomeService#initializeCollectionDefaults(org.kuali.kra.budget.core.Budget)
     */
    public void initializeCollectionDefaults(Budget budget) {
        if ( !isBudgetFinalAndComplete(budget) ) {
           initializeCostSharingCollectionDefaults(budget);
           initializeUnrecoveredFandACollectionDefaults(budget);
        }
    }

    /**
     * @see org.kuali.kra.budget.distributionincome.BudgetDistributionAndIncomeService#initializeCostSharingCollectionDefaults(org.kuali.kra.budget.core.Budget)
     */
    public void initializeCostSharingCollectionDefaults(Budget budget) {
        if(budget.isCostSharingApplicable() && budget.isCostSharingAvailable() && budget.getBudgetCostShares().size() == 0 && !isBudgetFinalAndComplete(budget)) {
           for(FiscalYearSummary fiscalYearSummary: budget.getFiscalYearCostShareTotals()) {
               budget.add(createBudgetCostShare(fiscalYearSummary));  
           }
       }
    }
    
    /**
     * @see org.kuali.kra.budget.distributionincome.BudgetDistributionAndIncomeService#initializeUnrecoveredFandACollectionDefaults(org.kuali.kra.budget.core.Budget)
     */
    public void initializeUnrecoveredFandACollectionDefaults(Budget budget) {
        if(budget.isUnrecoveredFandAApplicable() && budget.isUnrecoveredFandAAvailable() && budget.getBudgetUnrecoveredFandAs().size() == 0 && !isBudgetFinalAndComplete(budget)) {
           for(FiscalYearSummary fiscalYearSummary: budget.getFiscalYearUnrecoveredFandATotals()) {
               budget.add(createBudgetUnrecoveredFandA(fiscalYearSummary, fiscalYearSummary.getFiscalYearRates().getOnCampusApplicableRate(), BudgetUnrecoveredFandA.ON_CAMPUS_RATE_FLAG));  
               budget.add(createBudgetUnrecoveredFandA(fiscalYearSummary, fiscalYearSummary.getFiscalYearRates().getOffCampusApplicableRate(), BudgetUnrecoveredFandA.OFF_CAMPUS_RATE_FLAG));
           }
       }
    }
    
    /**
     * This method is a factory for BudgetCostShares
     * @param fiscalYearSummary The fiscal year summary data 
     * @return A BudgetCostShare
     */
    private BudgetCostShare createBudgetCostShare(FiscalYearSummary fiscalYearSummary) {
        return new BudgetCostShare(fiscalYearSummary.getFiscalYear(), fiscalYearSummary.getCostShare(), new BudgetDecimal(0.00), null);
    }
    
    /**
     * This method is a factory for BudgetUnrecoveredFandA
     * @param fiscalYearSummary The fiscal year summary data
     * @param applicableRate The applicable rate
     * @param onCampusFlag The on-Campus flag
     * @return
     */
    private BudgetUnrecoveredFandA createBudgetUnrecoveredFandA(FiscalYearSummary fiscalYearSummary, RateDecimal applicableRate, String onCampusFlag) {
        return new BudgetUnrecoveredFandA(fiscalYearSummary.getFiscalYear(), BudgetDecimal.ZERO, applicableRate, onCampusFlag, null);
    }
}
