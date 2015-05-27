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
package org.kuali.coeus.propdev.impl.budget.modular;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetRateAndBase;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component("budgetModularService")
public class BudgetModularServiceImpl implements BudgetModularService {
    
    private static final String RATE_CLASS_PROPERTY_NAME = "rateClass";
    private static final String RATE_NUMBER_PROPERTY_NAME = "rateNumber";
    private static final ScaleTwoDecimal TDC_NEXT_INCREMENT = new ScaleTwoDecimal(25000);

    @Autowired
    @Qualifier("budgetCalculationService")
    private BudgetCalculationService budgetCalculationService;
    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;
    
    public void generateModularPeriod(BudgetPeriod budgetPeriod) {

        if (ObjectUtils.isNull(budgetPeriod.getBudgetModular())) {
            BudgetModular budgetModular =
                new BudgetModular(budgetPeriod);
            budgetModular.setBudgetPeriodObj(budgetPeriod);
            budgetPeriod.setBudgetModular(budgetModular);
        }
        
        budgetPeriod.getBudgetModular().calculateAllTotals();
    }
    
    public BudgetModularSummary generateModularSummary(Budget budget) {
        BudgetModularSummary modularSummary = new BudgetModularSummary();
        ScaleTwoDecimal directCostLessConsortiumFna = new ScaleTwoDecimal(0);
        ScaleTwoDecimal consortiumFna = new ScaleTwoDecimal(0);
        ScaleTwoDecimal totalDirectCost = new ScaleTwoDecimal(0);
        ScaleTwoDecimal totalFnaRequested = new ScaleTwoDecimal(0);
        ScaleTwoDecimal totalRequestedCost = new ScaleTwoDecimal(0);
        List<BudgetModularIdc> budgetModularIdcs = new ArrayList<BudgetModularIdc>();
        
        for (BudgetPeriod budgetPeriod: budget.getBudgetPeriods()) {
            BudgetModular budgetModular = budgetPeriod.getBudgetModular();
            if (!ObjectUtils.isNull(budgetModular)) {
                budgetModular.calculateAllTotals();
                directCostLessConsortiumFna = directCostLessConsortiumFna.add(budgetModular.getDirectCostLessConsortiumFna());
                consortiumFna = consortiumFna.add(budgetModular.getConsortiumFna());
                totalDirectCost = totalDirectCost.add(budgetModular.getTotalDirectCost());
                totalFnaRequested = totalFnaRequested.add(budgetModular.getTotalFnaRequested());
                totalRequestedCost = totalRequestedCost.add(budgetModular.getTotalRequestedCost());
                budgetModularIdcs.addAll(budgetModular.getBudgetModularIdcs());
            }
        }
        
        modularSummary.setDirectCostLessConsortiumFna(directCostLessConsortiumFna);
        modularSummary.setConsortiumFna(consortiumFna);
        modularSummary.setTotalDirectCost(totalDirectCost);
        modularSummary.setTotalFnaRequested(totalFnaRequested);
        modularSummary.setTotalRequestedCost(totalRequestedCost);
        modularSummary.setBudgetModularIdcs(budgetModularIdcs);
        
        return modularSummary;
    }

    public void synchModularBudget(Budget budget) {
        
        if (budget == null) {
            throw new NullPointerException("the budget is null");
        }
        
        //each budget period has BudgetModular object associated with it.
        
        for (BudgetPeriod budgetPeriod: budget.getBudgetPeriods()) {
            
            if (ObjectUtils.isNull(budgetPeriod.getBudgetModular())) {
                // Modular period not initialized yet - create
                BudgetModular tmpBudgetModular = new BudgetModular(budgetPeriod);
                tmpBudgetModular.setBudgetPeriodObj(budgetPeriod);
                budgetPeriod.setBudgetModular(tmpBudgetModular);
            }
            //initialize budgetModular 
            BudgetModular budgetModular = budgetPeriod.getBudgetModular();
            budgetModular.setBudgetModularIdcs(new ArrayList<BudgetModularIdc>());
            ScaleTwoDecimal directCostLessConsortiumFna = new ScaleTwoDecimal(0);
            ScaleTwoDecimal consortiumFna = new ScaleTwoDecimal(0);
            
            for (BudgetLineItem budgetLineItem: budgetPeriod.getBudgetLineItems()) {
                
                budgetCalculationService.calculateBudgetLineItem(budget, budgetLineItem);
                Collection<String> consortiumFnaCostElements = this.parameterService.getParameterValuesAsString(
                        Budget.class, Constants.PARAMETER_FNA_COST_ELEMENTS);

                //is cost direct or indirect? Add cost to correct variable.
                if (consortiumFnaCostElements.contains(budgetLineItem.getCostElement())) {
                    consortiumFna = consortiumFna.add(budgetLineItem.getDirectCost());
                } else {
                    directCostLessConsortiumFna = directCostLessConsortiumFna.add(budgetLineItem.getDirectCost());
                }
                //for every indirect cost do this.
                for (BudgetRateAndBase budgetRateAndBase: budgetLineItem.getBudgetRateAndBaseList()) {
                    budgetRateAndBase.refreshReferenceObject(RATE_CLASS_PROPERTY_NAME);
                    String fnaRateClassType = this.parameterService.getParameterValueAsString(
                            Budget.class, Constants.PARAMETER_FNA_RATE_CLASS_TYPE);

                    if (budgetRateAndBase.getRateClass().getRateClassTypeCode().equals(fnaRateClassType)) {
                        BudgetModularIdc budgetModularIdc = new BudgetModularIdc();
                        if (budgetModularIdc.getBudgetPeriod() == null)
                            budgetModularIdc.setBudgetPeriod(budgetModular.getBudgetPeriod());
                        if (budgetModularIdc.getBudgetId() == null)
                            budgetModularIdc.setBudgetId(budgetModular.getBudgetId());
                        if (budgetModularIdc.getBudgetPeriodId() == null)
                            budgetModularIdc.setBudgetPeriodId(budgetModular.getBudgetPeriodId());
                        budgetModularIdc.setRateNumber(budget.getNextValue(RATE_NUMBER_PROPERTY_NAME));
                        budgetModularIdc.setDescription(budgetRateAndBase.getRateClassCode());
                        budgetModularIdc.setIdcRate(budgetRateAndBase.getAppliedRate());
                        budgetModularIdc.setFundsRequested(budgetRateAndBase.getCalculatedCost());  
                        budgetModularIdc.setIdcBase(budgetRateAndBase.getBaseCost());
                        budgetModularIdc.setBudgetModular(budgetModular);
                        budgetModular.addNewBudgetModularIdc(budgetModularIdc);
                    }
                }
            }
            //for direct costs increase to the next $25000 dollar increment.
            ScaleTwoDecimal modularTdc = ScaleTwoDecimal.ZERO;
            while (directCostLessConsortiumFna.isGreaterThan(modularTdc)) {
                modularTdc = modularTdc.add(TDC_NEXT_INCREMENT);
            }
            budgetModular.setDirectCostLessConsortiumFna(modularTdc);
            budgetModular.setConsortiumFna(consortiumFna);
            budgetModular.calculateAllTotals();
        }
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    /**
     * Sets the ParameterService.
     * @param parameterService the parameter service. 
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public BudgetCalculationService getBudgetCalculationService() {
        return budgetCalculationService;
    }

    public void setBudgetCalculationService(BudgetCalculationService budgetCalculationService) {
        this.budgetCalculationService = budgetCalculationService;
    }
    
    
}
