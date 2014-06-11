/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.budget.impl.distribution;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.distribution.BudgetDistributionService;
import org.kuali.coeus.common.budget.framework.distribution.BudgetCostShare;
import org.kuali.coeus.common.budget.framework.distribution.BudgetUnrecoveredFandA;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.Budget.FiscalYearSummary;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.framework.costshare.CostShareService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("budgetDistributionService")
public class BudgetDistributionServiceImpl implements BudgetDistributionService {

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;
    @Autowired
    @Qualifier("costShareService")
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

    public ParameterService getParameterService() {
        return parameterService;
    }

    public CostShareService getCostShareService() {
        return costShareService;
    }


    protected boolean isBudgetFinalAndComplete(Budget budget) {
        String budgetStatusCompleteValue = this.parameterService.getParameterValueAsString(
                BudgetDocument.class, Constants.BUDGET_STATUS_COMPLETE_CODE);
        return (budget.getFinalVersionFlag() && budgetStatusCompleteValue.equals(budget.getBudgetStatus()));
    }
    
    @Override
    public void initializeCollectionDefaults(Budget budget) {
        if ( !isBudgetFinalAndComplete(budget) ) {
           initializeCostSharingCollectionDefaults(budget);
           initializeUnrecoveredFandACollectionDefaults(budget);
        }
    }

    @Override
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
    
    @Override
    public void initializeUnrecoveredFandACollectionDefaults(Budget budget) {
        if(budget.isUnrecoveredFandAApplicable() && budget.isUnrecoveredFandAAvailable() && budget.getBudgetUnrecoveredFandAs().size() == 0 && !isBudgetFinalAndComplete(budget)) {
           for(FiscalYearSummary fiscalYearSummary: budget.getFiscalYearUnrecoveredFandATotals()) {
               budget.add(createBudgetUnrecoveredFandA(fiscalYearSummary, findApplicableRatesForFiscalYearUFAndA(budget, fiscalYearSummary, true), BudgetUnrecoveredFandA.ON_CAMPUS_RATE_FLAG));  
               budget.add(createBudgetUnrecoveredFandA(fiscalYearSummary, findApplicableRatesForFiscalYearUFAndA(budget, fiscalYearSummary, false), BudgetUnrecoveredFandA.OFF_CAMPUS_RATE_FLAG));
           }
       }
    }
    
    protected ScaleTwoDecimal findApplicableRatesForFiscalYearUFAndA(Budget budget, FiscalYearSummary fiscalYearSummary, boolean onCampus) {
        /**
         * Per KRACOEUS-5515 switching rate class code.
         */
        String unrecoveredFandARateClassCode = budget.getOhRateClassCode();
        if(unrecoveredFandARateClassCode == null || unrecoveredFandARateClassCode.trim().length() == 0) {
            return ScaleTwoDecimal.ZERO;
        } else {
            return findApplicableRateForRateClassCodeUFAndA(budget, fiscalYearSummary.getFiscalYear(), unrecoveredFandARateClassCode, onCampus);
        }
    }

    /*
     * In 'Budget', findApplicableRateForRateClassCode is only find matched year.  UFAndA need to be applied to years after if not matched
     * Not sure to change it in 'Budget' because there are so many things unknow.
     * so create here just for UFAnd A
     */
    protected ScaleTwoDecimal findApplicableRateForRateClassCodeUFAndA(Budget budget, Integer fiscalYear,
            String unrecoveredFandARateClassCode, boolean findOnCampusRate) {
        ScaleTwoDecimal applicableRate = ScaleTwoDecimal.ZERO;
        BudgetRate appliedRate = null;
        for (BudgetRate budgetRate : budget.getBudgetRates()) {
            if (StringUtils.equalsIgnoreCase(budgetRate.getRateClassCode(), unrecoveredFandARateClassCode)
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
            applicableRate = new ScaleTwoDecimal(appliedRate.getApplicableRate().bigDecimalValue());
        }
        return applicableRate;
    }

    /**
     * This method is a factory for BudgetCostShares
     * @param fiscalYearSummary The fiscal year summary data 
     * @return A BudgetCostShare
     */
    protected BudgetCostShare createBudgetCostShare(FiscalYearSummary fiscalYearSummary) {
        return new BudgetCostShare(fiscalYearSummary.getFiscalYear(), fiscalYearSummary.getCostShare(), new ScaleTwoDecimal(0.00), null);
    }
    
    protected BudgetCostShare createBudgetCostShare(int projectPeriod, ScaleTwoDecimal costShare) {
        return new BudgetCostShare(projectPeriod, costShare, new ScaleTwoDecimal(0.00), null);
    }
    
    /**
     * This method is a factory for BudgetUnrecoveredFandA
     * @param fiscalYearSummary The fiscal year summary data
     * @param applicableRate The applicable rate
     * @param onCampusFlag The on-CampusContract flag
     * @return
     */
    protected BudgetUnrecoveredFandA createBudgetUnrecoveredFandA(FiscalYearSummary fiscalYearSummary, ScaleTwoDecimal applicableRate, String onCampusFlag) {
        return new BudgetUnrecoveredFandA(fiscalYearSummary.getFiscalYear(), ScaleTwoDecimal.ZERO, applicableRate, onCampusFlag, null);
    }
}
