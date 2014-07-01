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
package org.kuali.coeus.propdev.impl.budget.modular;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
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
                new BudgetModular(budgetPeriod.getBudgetId(), budgetPeriod.getBudgetPeriod());
            budgetModular.setBudgetPeriodId(budgetPeriod.getBudgetPeriodId());
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
                BudgetModular tmpBudgetModular = new BudgetModular(budgetPeriod.getBudgetId(), budgetPeriod.getBudgetPeriod());
                tmpBudgetModular.setBudgetPeriodId(budgetPeriod.getBudgetPeriodId());
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
                        BudgetDocument.class, Constants.PARAMETER_FNA_COST_ELEMENTS);
                
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
                            BudgetDocument.class, Constants.PARAMETER_FNA_RATE_CLASS_TYPE);
                  
                    if (budgetRateAndBase.getRateClass().getRateClassTypeCode().equals(fnaRateClassType)) {
                        BudgetModularIdc budgetModularIdc = new BudgetModularIdc();
                        budgetModularIdc.setRateNumber(budget.getHackedDocumentNextValue(RATE_NUMBER_PROPERTY_NAME));
                        budgetModularIdc.setDescription(budgetRateAndBase.getRateClassCode());
                        budgetModularIdc.setIdcRate(budgetRateAndBase.getAppliedRate());
                        budgetModularIdc.setFundsRequested(budgetRateAndBase.getCalculatedCost());  
                        budgetModularIdc.setIdcBase(budgetRateAndBase.getBaseCost());
                        //budgetModularIdc.setFundsRequested(budgetRateAndBase.getCalculatedCost());
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
