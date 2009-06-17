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

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetModular;
import org.kuali.kra.budget.bo.BudgetModularIdc;
import org.kuali.kra.budget.bo.BudgetModularSummary;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetRateAndBase;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetCalculationService;
import org.kuali.kra.budget.service.BudgetModularService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.ObjectUtils;

public class BudgetModularServiceImpl implements BudgetModularService {
    
    private static final String RATE_CLASS_PROPERTY_NAME = "rateClass";
    private static final String RATE_NUMBER_PROPERTY_NAME = "rateNumber";
    private static final BudgetDecimal TDC_NEXT_INCREMENT = new BudgetDecimal(25000);
    
    private BudgetCalculationService budgetCalculationService;
    private KualiConfigurationService kualiConfigurationService;
    
    public void generateModularPeriod(BudgetPeriod budgetPeriod) {

        if (ObjectUtils.isNull(budgetPeriod.getBudgetModular())) {
            // Modular period not initialized yet - create
            BudgetModular budgetModular = 
                new BudgetModular(budgetPeriod.getProposalNumber(), budgetPeriod.getBudgetVersionNumber(), budgetPeriod.getBudgetPeriod());
            budgetModular.setBudgetPeriodId(budgetPeriod.getBudgetPeriodId());
            budgetPeriod.setBudgetModular(budgetModular);
        }
        
        budgetPeriod.getBudgetModular().calculateAllTotals();
    }
    
    public BudgetModularSummary generateModularSummary(BudgetDocument budgetDocument) {
        BudgetModularSummary modularSummary = new BudgetModularSummary();
        BudgetDecimal directCostLessConsortiumFna = new BudgetDecimal(0);
        BudgetDecimal consortiumFna = new BudgetDecimal(0);
        BudgetDecimal totalDirectCost = new BudgetDecimal(0);
        BudgetDecimal totalFnaRequested = new BudgetDecimal(0);
        BudgetDecimal totalRequestedCost = new BudgetDecimal(0);
        List<BudgetModularIdc> budgetModularIdcs = new ArrayList<BudgetModularIdc>();
        
        for (BudgetPeriod budgetPeriod: budgetDocument.getBudgetPeriods()) {
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
    
    public void synchModularBudget(BudgetDocument budgetDocument) {
        
        if (budgetDocument == null) {
            throw new NullPointerException("the budgetDocument is null");
        }
        
        //each budget period has BudgetModular object associated with it.
        
        for (BudgetPeriod budgetPeriod: budgetDocument.getBudgetPeriods()) {
            
            if (ObjectUtils.isNull(budgetPeriod.getBudgetModular())) {
                // Modular period not initialized yet - create
                BudgetModular tmpBudgetModular = new BudgetModular(budgetPeriod.getProposalNumber(), budgetPeriod.getBudgetVersionNumber(), budgetPeriod.getBudgetPeriod());
                tmpBudgetModular.setBudgetPeriodId(budgetPeriod.getBudgetPeriodId());
                budgetPeriod.setBudgetModular(tmpBudgetModular);
            }
            //initialize budgetModular 
            BudgetModular budgetModular = budgetPeriod.getBudgetModular();
            budgetModular.setBudgetModularIdcs(new ArrayList<BudgetModularIdc>());
            BudgetDecimal directCostLessConsortiumFna = new BudgetDecimal(0);
            BudgetDecimal consortiumFna = new BudgetDecimal(0);
            
            for (BudgetLineItem budgetLineItem: budgetPeriod.getBudgetLineItems()) {
                
                budgetCalculationService.calculateBudgetLineItem(budgetDocument, budgetLineItem);
                List<String> consortiumFnaCostElements = kualiConfigurationService.getParameterValues(
                        Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.PARAMETER_FNA_COST_ELEMENTS);
                
                //is cost direct or indirect? Add cost to correct variable.
                if (consortiumFnaCostElements.contains(budgetLineItem.getCostElement())) {
                    consortiumFna = consortiumFna.add(budgetLineItem.getDirectCost());
                } else {
                    directCostLessConsortiumFna = directCostLessConsortiumFna.add(budgetLineItem.getDirectCost());
                }
                //for every indirect cost do this.
                for (BudgetRateAndBase budgetRateAndBase: budgetLineItem.getBudgetRateAndBaseList()) {
                    budgetRateAndBase.refreshReferenceObject(RATE_CLASS_PROPERTY_NAME);
                    String fnaRateClassType = kualiConfigurationService.getParameterValue(
                            Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.PARAMETER_FNA_RATE_CLASS_TYPE);
                  
                    if (budgetRateAndBase.getRateClass().getRateClassType().equals(fnaRateClassType)) {
                        BudgetModularIdc budgetModularIdc = new BudgetModularIdc();
                        budgetModularIdc.setRateNumber(budgetDocument.getHackedDocumentNextValue(RATE_NUMBER_PROPERTY_NAME));
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
            BudgetDecimal modularTdc = BudgetDecimal.ZERO;
            while (directCostLessConsortiumFna.isGreaterThan(modularTdc)) {
                modularTdc = modularTdc.add(TDC_NEXT_INCREMENT);
            }
            budgetModular.setDirectCostLessConsortiumFna(modularTdc);
            budgetModular.setConsortiumFna(consortiumFna);
            budgetModular.calculateAllTotals();
        }
    }
    
    protected List<BudgetModularIdc> generateBudgetModularIdcsForLineItem(BudgetLineItem budgetLineItem) {
        List<BudgetModularIdc> budgetModularIdcs = new ArrayList<BudgetModularIdc>();
        
        return budgetModularIdcs;
    }

    public void setKualiConfigurationService(KualiConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }

    public BudgetCalculationService getBudgetCalculationService() {
        return budgetCalculationService;
    }

    public void setBudgetCalculationService(BudgetCalculationService budgetCalculationService) {
        this.budgetCalculationService = budgetCalculationService;
    }
    
    
}
