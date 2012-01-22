/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.external.budget.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.QueryList;
import org.kuali.kra.budget.calculator.query.Equals;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetCategoryType;
import org.kuali.kra.budget.core.CostElement;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.rates.RateType;
import org.kuali.kra.external.budget.BudgetAdjustmentServiceHelper;
import org.kuali.kra.external.budget.RateClassRateType;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class is a helper that does all the required calculation for setting the accounting line amounts
 * for the Budget Adjustment Service.
 */
public class BudgetAdjustmentServiceHelperImpl implements BudgetAdjustmentServiceHelper{
    
    
    private BusinessObjectService businessObjectService;

    private static final Log LOG = LogFactory.getLog(BudgetAdjustmentServiceHelperImpl.class);

  
    /**
     * In order to decrease or increase, the change amount is used, so this can be sent as is without
     * subtracting from previous budget.
     * @return
     */
    public HashMap<String, BudgetDecimal> getNonPersonnelCost(Budget currentBudget, AwardBudgetExt previousBudget) {        
        HashMap<String, BudgetDecimal> netCost = new HashMap<String, BudgetDecimal>();
        // only do for one period, assume it is the first 
        int period = currentBudget.getBudgetPeriods().size() - 1;
        List<BudgetLineItem> currentLineItems = currentBudget.getBudgetPeriods().get(period).getBudgetLineItems();
        
        HashMap<String, BudgetDecimal> currentLineItemCosts = new HashMap<String, BudgetDecimal>();
        for (BudgetLineItem currentLineItem : currentLineItems) {
            if (!StringUtils.equalsIgnoreCase(currentLineItem.getBudgetCategory().getBudgetCategoryTypeCode(), "P")) {
                currentLineItemCosts.put(currentLineItem.getCostElement(), currentLineItem.getLineItemCost());
            }
        }
        
        return currentLineItemCosts;
    }
    
    /*
     * Returns the non personnel calculated direct cost.
     */
    public SortedMap<RateType, BudgetDecimal> getNonPersonnelCalculatedDirectCost(Budget currentBudget, AwardBudgetExt previousBudget) {
        SortedMap<RateType, List<BudgetDecimal>> currentNonPersonnelCalcDirectCost = currentBudget.getNonPersonnelCalculatedExpenseTotals();
        SortedMap<RateType, BudgetDecimal> netNonPersonnelCalculatedDirectCost = new TreeMap<RateType, BudgetDecimal>();
        int period = currentBudget.getBudgetPeriods().size() - 1;

        for (RateType rateType : currentNonPersonnelCalcDirectCost.keySet()) {
            List<BudgetDecimal> currentExpenses = currentNonPersonnelCalcDirectCost.get(rateType);     
                netNonPersonnelCalculatedDirectCost.put(rateType, currentExpenses.get(period));
              
        }     
        return netNonPersonnelCalculatedDirectCost;
    }
    
    /**
     * This method returns the indirect cost for the accounting line.
     * @return
     */
    public Map<RateClassRateType, BudgetDecimal> getIndirectCost(Budget currentBudget, AwardBudgetExt previousBudget) {
        int period = currentBudget.getBudgetPeriods().size() - 1;
        List<BudgetLineItem> currentLineItems = currentBudget.getBudgetPeriods().get(period).getBudgetLineItems();
        Map<RateClassRateType, BudgetDecimal> currentIndirectTotals = new HashMap<RateClassRateType, BudgetDecimal>();
        
        for (BudgetLineItem lineItem : currentLineItems) {
            for (BudgetLineItemCalculatedAmount lineItemCalculatedAmount : lineItem.getBudgetLineItemCalculatedAmounts()) {
                lineItemCalculatedAmount.refreshReferenceObject("rateClass");
                if (lineItemCalculatedAmount.getRateClass().getRateClassType().equalsIgnoreCase("O")) {
                    RateClassRateType currentKey = new RateClassRateType(lineItemCalculatedAmount.getRateClassCode(), 
                                                                         lineItemCalculatedAmount.getRateTypeCode());
                    if (currentIndirectTotals.containsKey(currentKey)) {
                        currentIndirectTotals.put(currentKey, currentIndirectTotals.get(currentKey).
                                                            add(lineItemCalculatedAmount.getCalculatedCost()));
                    } else {
                        currentIndirectTotals.put(currentKey, lineItemCalculatedAmount.getCalculatedCost());
                    }                   
                }
            }
        }
        
        Map<RateClassRateType, BudgetDecimal> netIndirectTotals = new HashMap<RateClassRateType, BudgetDecimal>();
        for (RateClassRateType rate : currentIndirectTotals.keySet()) {          
            netIndirectTotals.put(rate, currentIndirectTotals.get(rate));
           
        }
        
        return netIndirectTotals;
    }
    
    /**
     * This method returns the personnel calculated direct cost.
     * @return
     */
    public Map<RateClassRateType, BudgetDecimal> getPersonnelCalculatedDirectCost(Budget currentBudget, AwardBudgetExt previousBudget) {
        SortedMap<RateType, List<BudgetDecimal>> currentTotals = currentBudget.getPersonnelCalculatedExpenseTotals();
        int period = currentBudget.getBudgetPeriods().size() - 1;
        Map<RateClassRateType, BudgetDecimal> currentCost = new HashMap<RateClassRateType, BudgetDecimal>();
        Map<RateClassRateType, BudgetDecimal> netCost = new HashMap<RateClassRateType, BudgetDecimal>();
        for (RateType rate : currentTotals.keySet()) {
            // For some reason indirect cost shows up in this, remove it.
            if (!StringUtils.equalsIgnoreCase(rate.getRateClass().getRateClassType(), "O")) {
                LOG.info("Rate Class: " + rate.getRateClassCode() + "RateType: " + rate.getRateTypeCode() + "");
                currentCost.put(new RateClassRateType(rate.getRateClassCode(), rate.getRateTypeCode()), currentTotals.get(rate).get(period));
            }
        }
       
        for (RateClassRateType rate : currentCost.keySet()) {
            netCost.put(rate, currentCost.get(rate));
        }
       
        return netCost;
    }
    
    
    /*
     * The BudgetCalculationService does this by objectCodeType but we need by RateClass,RateType.
     */
    public Map<RateClassRateType, BudgetDecimal> getPersonnelFringeCost(Budget currentBudget, AwardBudgetExt previousBudget) {
        
        BudgetCategoryType personnelCategory =  getPersonnelCategoryType();
        List<CostElement> currentPersonnelObjectCodes = currentBudget.getObjectCodeListByBudgetCategoryType().get(personnelCategory); 
    
        Map<RateClassRateType, BudgetDecimal> currentFringeTotals = getFringeTotals(currentPersonnelObjectCodes, currentBudget);
        Map<RateClassRateType, BudgetDecimal> netFringeTotals = new HashMap<RateClassRateType, BudgetDecimal>();
        for (RateClassRateType rate : currentFringeTotals.keySet()) {    
            netFringeTotals.put(rate, currentFringeTotals.get(rate));
        }
       
        return netFringeTotals;
    }
    
    /**
     * This method returns the fringe totals.
     * @param currentPersonnelObjectCodes
     * @param budget
     * @return
     */
    protected Map<RateClassRateType, BudgetDecimal> getFringeTotals(List<CostElement> currentPersonnelObjectCodes, Budget budget) {
        /*
         * Things like Animal care and Travel also have a rateClassType of E so need to filter that
         * out to only get Personnel
         */
        Map<RateClassRateType, BudgetDecimal> fringeTotals = new HashMap<RateClassRateType, BudgetDecimal>();
        
        if  (CollectionUtils.isNotEmpty(currentPersonnelObjectCodes)) {
            for (CostElement personnelCostElement : currentPersonnelObjectCodes) {
                for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
                    List<BudgetLineItem> filteredLineItems = getPersonnelLineItems(budgetPeriod, personnelCostElement);
                    for (BudgetLineItem lineItem : filteredLineItems) {
                        for (BudgetLineItemCalculatedAmount lineItemCalculatedAmount : lineItem.getBudgetLineItemCalculatedAmounts()) {
                            lineItemCalculatedAmount.refreshReferenceObject("rateClass");
                            //Check for Employee Benefits RateClassType
                            if (lineItemCalculatedAmount.getRateClass().getRateClassType().equalsIgnoreCase("E")) {
                                RateClassRateType currentKey = new RateClassRateType(lineItemCalculatedAmount.getRateClassCode(), 
                                                                            lineItemCalculatedAmount.getRateTypeCode());
                                if (fringeTotals.containsKey(currentKey)) {
                                    fringeTotals.put(currentKey, 
                                                            fringeTotals.get(currentKey).add(lineItemCalculatedAmount.getCalculatedCost()));
                                } else {
                                    fringeTotals.put(currentKey, lineItemCalculatedAmount.getCalculatedCost());
                                }
                               
                            }
                        }
                    }
                }    
            }
        }
        return fringeTotals;
    }
    
    /**
     * This method returns the personnel line items.
     * @param budgetPeriod
     * @param personnelCostElement
     * @return
     */
    protected List<BudgetLineItem> getPersonnelLineItems(BudgetPeriod budgetPeriod, CostElement personnelCostElement) {
        QueryList lineItemQueryList = new QueryList();
        lineItemQueryList.addAll(budgetPeriod.getBudgetLineItems());
        Equals objectCodeEquals = new Equals("costElement", personnelCostElement.getCostElement());
        QueryList<BudgetLineItem> filteredLineItems = lineItemQueryList.filter(objectCodeEquals);
        return filteredLineItems;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * This method returns the business object service.
     * @return
     */
    public BusinessObjectService getBusinessObjectService() {
       return businessObjectService; 
    }
    
    /**
     * This method returns the budget category type with type code "P".
     * @return
     */
    protected BudgetCategoryType getPersonnelCategoryType() {
        final Map<String, String> primaryKeys = new HashMap<String, String>();
        primaryKeys.put("budgetCategoryTypeCode", "P");
        return (BudgetCategoryType) this.getBusinessObjectService().findByPrimaryKey(BudgetCategoryType.class, primaryKeys);
    }
    
    /**
     * This method returns the personnel salary cost.
     * @return
     * @throws Exception
     */
    public SortedMap<String, BudgetDecimal> getPersonnelSalaryCost(Budget currentBudget, AwardBudgetExt previousBudget) throws Exception {
        SortedMap<String, List<BudgetDecimal>> currentSalaryTotals = currentBudget.getObjectCodePersonnelSalaryTotals();
        SortedMap<String, BudgetDecimal> netSalary =  new TreeMap<String, BudgetDecimal>();
        int period = currentBudget.getBudgetPeriods().size() - 1;

        for (String person : currentSalaryTotals.keySet()) {
            String key = person;
            if (person.contains(",")) {
                String[] objectCode = getElements(key);
                key = objectCode[0];
            }
            BudgetDecimal currentSalary = currentSalaryTotals.get(person).get(period);
            netSalary.put(key, currentSalary);
           
        }
        
        return netSalary;
    }
    
    /**
     * This method...
     * @param person
     * @return
     * @throws Exception
     */
    protected String[] getElements(String person) throws Exception {
        if (person.contains(",")) {
                String[] personElements = person.split(",");
                return personElements;
        }
        LOG.error("The string is not in the format objectCode,personId  . Unable to retrieve object code.");
        throw new Exception("The string " + person + "is not in the format objectCode,personId  . Unable to retrieve object code.");
    }
   
    
    
    
}
