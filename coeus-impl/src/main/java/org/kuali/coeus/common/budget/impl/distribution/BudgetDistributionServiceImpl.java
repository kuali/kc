/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
import org.kuali.coeus.common.budget.framework.distribution.BudgetDistributionService;
import org.kuali.coeus.common.budget.framework.distribution.BudgetCostShare;
import org.kuali.coeus.common.budget.framework.distribution.BudgetUnrecoveredFandA;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.Budget.FiscalYearSummary;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.framework.costshare.CostShareService;
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
    	//returning false as PD budget has moved and final and compelte are only applicable to PD
    	return false;
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
                for (BudgetPeriod period : budget.getBudgetPeriods()) {
                    budget.add(createBudgetCostShare(period.getBudgetPeriod().intValue(), period.getCostSharingAmount()));
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
