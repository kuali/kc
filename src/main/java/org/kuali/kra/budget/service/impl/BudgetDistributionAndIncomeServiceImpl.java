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
package org.kuali.kra.budget.service.impl;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.RateDecimal;
import org.kuali.kra.budget.bo.BudgetCostShare;
import org.kuali.kra.budget.bo.BudgetUnrecoveredFandA;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetDocument.FiscalYearSummary;
import org.kuali.kra.budget.service.BudgetDistributionAndIncomeService;

public class BudgetDistributionAndIncomeServiceImpl implements BudgetDistributionAndIncomeService {

    /**
     * @see org.kuali.kra.budget.service.BudgetDistributionAndIncomeService#initializeCollectionDefaults(org.kuali.kra.budget.document.BudgetDocument)
     */
    public void initializeCollectionDefaults(BudgetDocument budgetDocument) {
       initializeCostSharingCollectionDefaults(budgetDocument);
       initializeUnrecoveredFandACollectionDefaults(budgetDocument);
    }

    /**
     * @see org.kuali.kra.budget.service.BudgetDistributionAndIncomeService#initializeCostSharingCollectionDefaults(org.kuali.kra.budget.document.BudgetDocument)
     */
    public void initializeCostSharingCollectionDefaults(BudgetDocument budgetDocument) {
        if(budgetDocument.isCostSharingApplicable() && budgetDocument.isCostSharingAvailable() && budgetDocument.getBudgetCostShares().size() == 0) {
           for(FiscalYearSummary fiscalYearSummary: budgetDocument.getFiscalYearCostShareTotals()) {
               budgetDocument.add(createBudgetCostShare(fiscalYearSummary));  
           }
       }
    }
    
    /**
     * @see org.kuali.kra.budget.service.BudgetDistributionAndIncomeService#initializeUnrecoveredFandACollectionDefaults(org.kuali.kra.budget.document.BudgetDocument)
     */
    public void initializeUnrecoveredFandACollectionDefaults(BudgetDocument budgetDocument) {
        if(budgetDocument.isUnrecoveredFandAApplicable() && budgetDocument.isUnrecoveredFandAAvailable() && budgetDocument.getBudgetUnrecoveredFandAs().size() == 0) {
           for(FiscalYearSummary fiscalYearSummary: budgetDocument.getFiscalYearUnrecoveredFandATotals()) {
               budgetDocument.add(createBudgetUnrecoveredFandA(fiscalYearSummary, fiscalYearSummary.getFiscalYearRates().getOnCampusApplicableRate(), BudgetUnrecoveredFandA.ON_CAMPUS_RATE_FLAG));  
               budgetDocument.add(createBudgetUnrecoveredFandA(fiscalYearSummary, fiscalYearSummary.getFiscalYearRates().getOffCampusApplicableRate(), BudgetUnrecoveredFandA.OFF_CAMPUS_RATE_FLAG));
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
