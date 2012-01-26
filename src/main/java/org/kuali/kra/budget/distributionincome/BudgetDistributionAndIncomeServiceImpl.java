/*
 * Copyright 2005-2010 The Kuali Foundation
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
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.Budget.FiscalYearSummary;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.rates.BudgetRate;
import org.kuali.kra.costshare.CostShareService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

public class BudgetDistributionAndIncomeServiceImpl implements BudgetDistributionAndIncomeService {

    private ParameterService parameterService;
    private CostShareService costShareService;
    
    /**
     * Sets the ParameterService.
     * @param parameterService the parameter service. 
     */ 
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    public void setCostShareService(CostShareService costShareService) {
        this.costShareService = costShareService;
    }
    
    
    protected boolean isBudgetFinalAndComplete(Budget budget) {
        String budgetStatusCompleteValue = this.parameterService.getParameterValueAsString(
                BudgetDocument.class, Constants.BUDGET_STATUS_COMPLETE_CODE);
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
        if (budget.isCostSharingApplicable() && budget.isCostSharingAvailable() && budget.getBudgetCostShares().size() == 0
                && !isBudgetFinalAndComplete(budget)) {
            if (this.costShareService.validateProjectPeriodAsFiscalYear()){
                for (FiscalYearSummary fiscalYearSummary : budget.getFiscalYearCostShareTotals()) {
                    budget.add(createBudgetCostShare(fiscalYearSummary));
                }
            } else if (this.costShareService.validateProjectPeriodAsProjectPeriod()) {
                int counter = 1;
                for (BudgetPeriod period : budget.getBudgetPeriods()) {
                    budget.add(createBudgetCostShare(counter, period.getCostSharingAmount()));
                    counter++;
                }
            }
        }
    }
    
    /**
     * @see org.kuali.kra.budget.distributionincome.BudgetDistributionAndIncomeService#initializeUnrecoveredFandACollectionDefaults(org.kuali.kra.budget.core.Budget)
     */
    public void initializeUnrecoveredFandACollectionDefaults(Budget budget) {
        if(budget.isUnrecoveredFandAApplicable() && budget.isUnrecoveredFandAAvailable() && budget.getBudgetUnrecoveredFandAs().size() == 0 && !isBudgetFinalAndComplete(budget)) {
           for(FiscalYearSummary fiscalYearSummary: budget.getFiscalYearUnrecoveredFandATotals()) {
               budget.add(createBudgetUnrecoveredFandA(fiscalYearSummary, findApplicableRatesForFiscalYearUFAndA(budget, fiscalYearSummary, true), BudgetUnrecoveredFandA.ON_CAMPUS_RATE_FLAG));  
               budget.add(createBudgetUnrecoveredFandA(fiscalYearSummary, findApplicableRatesForFiscalYearUFAndA(budget, fiscalYearSummary, false), BudgetUnrecoveredFandA.OFF_CAMPUS_RATE_FLAG));
           }
       }
    }
    
    protected BudgetDecimal findApplicableRatesForFiscalYearUFAndA(Budget budget, FiscalYearSummary fiscalYearSummary, boolean onCampus) {
        String unrecoveredFandARateClassCode = budget.getUrRateClassCode();
        if(unrecoveredFandARateClassCode == null || unrecoveredFandARateClassCode.trim().length() == 0) {
            return BudgetDecimal.ZERO;
        } else {
            return findApplicableRateForRateClassCodeUFAndA(budget, fiscalYearSummary.getFiscalYear(), unrecoveredFandARateClassCode, onCampus);
        }
    }

    /*
     * In 'Budget', findApplicableRateForRateClassCode is only find matched year.  UFAndA need to be applied to years after if not matched
     * Not sure to change it in 'Budget' because there are so many things unknow.
     * so create here just for UFAnd A
     */
    protected BudgetDecimal findApplicableRateForRateClassCodeUFAndA(Budget budget, Integer fiscalYear,
            String unrecoveredFandARateClassCode, boolean findOnCampusRate) {
        BudgetDecimal applicableRate = BudgetDecimal.ZERO;
        BudgetRate appliedRate = null;
        for (BudgetRate budgetRate : budget.getBudgetRates()) {
            if (budgetRate.getRateClassCode().equalsIgnoreCase(unrecoveredFandARateClassCode)
                    && findOnCampusRate == budgetRate.getOnOffCampusFlag()) {
                if (appliedRate == null || Integer.valueOf(appliedRate.getFiscalYear()) < Integer.valueOf(budgetRate.getFiscalYear()) 
                        && Integer.valueOf(budgetRate.getFiscalYear()) <= Integer.valueOf(fiscalYear)) {
                    appliedRate = budgetRate;
                } else if (appliedRate.getFiscalYear().equals(budgetRate.getFiscalYear())
                        && Integer.valueOf(budgetRate.getFiscalYear()) <= Integer.valueOf(fiscalYear)) {
                    appliedRate = budgetRate;
                    break;
                }
            }
        }
        if (appliedRate != null) {
            applicableRate = new BudgetDecimal(appliedRate.getApplicableRate().bigDecimalValue());
        }
        return applicableRate;
    }

    /**
     * This method is a factory for BudgetCostShares
     * @param fiscalYearSummary The fiscal year summary data 
     * @return A BudgetCostShare
     */
    protected BudgetCostShare createBudgetCostShare(FiscalYearSummary fiscalYearSummary) {
        return new BudgetCostShare(fiscalYearSummary.getFiscalYear(), fiscalYearSummary.getCostShare(), new BudgetDecimal(0.00), null);
    }
    
    protected BudgetCostShare createBudgetCostShare(int projectPeriod, BudgetDecimal costShare) {
        return new BudgetCostShare(projectPeriod, costShare, new BudgetDecimal(0.00), null);
    }
    
    /**
     * This method is a factory for BudgetUnrecoveredFandA
     * @param fiscalYearSummary The fiscal year summary data
     * @param applicableRate The applicable rate
     * @param onCampusFlag The on-CampusContract flag
     * @return
     */
    protected BudgetUnrecoveredFandA createBudgetUnrecoveredFandA(FiscalYearSummary fiscalYearSummary, BudgetDecimal applicableRate, String onCampusFlag) {
        return new BudgetUnrecoveredFandA(fiscalYearSummary.getFiscalYear(), BudgetDecimal.ZERO, applicableRate, onCampusFlag, null);
    }
}
