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
package org.kuali.kra.external.budget;

import java.util.ArrayList;
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
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * This class is a helper that does all the required calculation for setting the accounting line amounts
 * for the Budget Adjustment Service.
 */
public class BudgetAdjustmentServiceHelper {
    
    private Budget currentBudget;
    private AwardBudgetExt previousBudget;
    private BusinessObjectService businessObjectservice;

    private static final Log LOG = LogFactory.getLog(BudgetAdjustmentServiceHelper.class);

    /**
     * Constructs a BudgetAdjustmentServiceHelper.java.
     * @param currentBudget
     * @param previousBudget
     */
    public BudgetAdjustmentServiceHelper(Budget currentBudget, AwardBudgetExt previousBudget) {
        this.currentBudget = currentBudget;
        this.previousBudget = previousBudget;
    }
  
    /**
     * In order to decrease or increase, the change amount is used, so this can be sent as is.
     * @return
     */
    public HashMap<String, BudgetDecimal> getNonPersonnelCost() {        
        HashMap<String, BudgetDecimal> netCost = new HashMap<String, BudgetDecimal>();
        // only do for one period, assume it is the first 
        List<BudgetLineItem> currentLineItems = currentBudget.getBudgetPeriods().get(0).getBudgetLineItems();
        
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
    public SortedMap<RateType, BudgetDecimal> getNonPersonnelCalculatedDirectCost() {
        SortedMap<RateType, List<BudgetDecimal>> currentNonPersonnelCalcDirectCost = currentBudget.getNonPersonnelCalculatedExpenseTotals();
        SortedMap<RateType, List<BudgetDecimal>> prevNonPersonnelCalcDirectCost = new TreeMap<RateType, List<BudgetDecimal>>();
        SortedMap<RateType, BudgetDecimal> netNonPersonnelCalculatedDirectCost = new TreeMap<RateType, BudgetDecimal>();
        if (ObjectUtils.isNotNull(previousBudget)) {
            prevNonPersonnelCalcDirectCost = previousBudget.getNonPersonnelCalculatedExpenseTotals();
        }
        
        List<RateType> prev = new ArrayList<RateType>();
        for (RateType rateType : currentNonPersonnelCalcDirectCost.keySet()) {
            List<BudgetDecimal> currentExpenses = currentNonPersonnelCalcDirectCost.get(rateType); 
            List<BudgetDecimal> prevExpenses = prevNonPersonnelCalcDirectCost.get(rateType);
            
            if (ObjectUtils.isNotNull(prevExpenses)) {
                netNonPersonnelCalculatedDirectCost.put(rateType, 
                                                        currentExpenses.get(0).subtract
                                                        (prevExpenses.get(0)));
                prev.add(rateType);
            } else {
                netNonPersonnelCalculatedDirectCost.put(rateType, currentExpenses.get(0));
            }      
        }
        // if cost elements have been removed from current budget
        if (ObjectUtils.isNotNull(prevNonPersonnelCalcDirectCost) && !prevNonPersonnelCalcDirectCost.isEmpty()) {
            for (RateType rateType : prevNonPersonnelCalcDirectCost.keySet()) {
                if (!prev.contains(rateType)) {
                    netNonPersonnelCalculatedDirectCost.put(rateType, prevNonPersonnelCalcDirectCost.get(rateType).get(0).negated());
                }
            }
        }
        return netNonPersonnelCalculatedDirectCost;
    }
    
    /**
     * This method returns the indirect cost for the accounting line.
     * @return
     */
    public Map<RateClassRateType, BudgetDecimal> getIndirectCost() {
        List<BudgetLineItem> currentLineItems = currentBudget.getBudgetPeriods().get(0).getBudgetLineItems();
        List<BudgetLineItem> prevLineItems = new ArrayList<BudgetLineItem>();
        Map<RateClassRateType, BudgetDecimal> currentIndirectTotals = new HashMap<RateClassRateType, BudgetDecimal>();
        Map<RateClassRateType, BudgetDecimal> prevIndirectTotals = new HashMap<RateClassRateType, BudgetDecimal>();
        
        if (ObjectUtils.isNotNull(previousBudget) && !previousBudget.getBudgetPeriods().isEmpty()) {
            prevLineItems = previousBudget.getBudgetPeriods().get(0).getBudgetLineItems();
            for (BudgetLineItem lineItem : prevLineItems) {
                for (BudgetLineItemCalculatedAmount lineItemCalculatedAmount : lineItem.getBudgetLineItemCalculatedAmounts()) {
                    lineItemCalculatedAmount.refreshReferenceObject("rateClass");
                    if (lineItemCalculatedAmount.getRateClass().getRateClassType().equalsIgnoreCase("O")) {
                        RateClassRateType currentKey = new RateClassRateType(lineItemCalculatedAmount.getRateClassCode(), 
                                                                             lineItemCalculatedAmount.getRateTypeCode());
                        if (prevIndirectTotals.containsKey(currentKey)) {
                            prevIndirectTotals.put(currentKey, prevIndirectTotals.get(currentKey).
                                                                add(lineItemCalculatedAmount.getCalculatedCost()));
                        } else {
                            prevIndirectTotals.put(currentKey, lineItemCalculatedAmount.getCalculatedCost());
                        }                   
                    }
                }
            }
        }   
        
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
        List<RateClassRateType> prev = new ArrayList<RateClassRateType>();
        for (RateClassRateType rate : currentIndirectTotals.keySet()) {
            if (prevIndirectTotals.containsKey(rate)) {
                netIndirectTotals.put(rate, currentIndirectTotals.get(rate).subtract(prevIndirectTotals.get(rate)));
                prev.add(rate);
            } else {
                netIndirectTotals.put(rate, currentIndirectTotals.get(rate));
            }
        }
        
        for (RateClassRateType rate : prevIndirectTotals.keySet()) {
            if (!prev.contains(rate)) {
                netIndirectTotals.put(rate, prevIndirectTotals.get(rate).negated());
            }
        }
        
       return netIndirectTotals;
    }
    
    /**
     * This method returns the personnel calculated direct cost.
     * @return
     */
    public Map<RateClassRateType, BudgetDecimal> getPersonnelCalculatedDirectCost() {
        SortedMap<RateType, List<BudgetDecimal>> currentTotals = currentBudget.getPersonnelCalculatedExpenseTotals();
        SortedMap<RateType, List<BudgetDecimal>> prevTotals = new TreeMap<RateType, List<BudgetDecimal>>();
        if (ObjectUtils.isNotNull(previousBudget)) {
            prevTotals = previousBudget.getPersonnelCalculatedExpenseTotals();
        }
        
        Map<RateClassRateType, BudgetDecimal> currentCost = new HashMap<RateClassRateType, BudgetDecimal>();
        Map<RateClassRateType, BudgetDecimal> prevCost = new HashMap<RateClassRateType, BudgetDecimal>();
        Map<RateClassRateType, BudgetDecimal> netCost = new HashMap<RateClassRateType, BudgetDecimal>();
        for (RateType rate : currentTotals.keySet()) {
            // For some reason indirect cost shows up in this, remove it.
            if (!StringUtils.equalsIgnoreCase(rate.getRateClass().getRateClassType(), "O")) {
                LOG.info("Rate Class: " + rate.getRateClassCode() + "RateType: " + rate.getRateTypeCode() + "");
                currentCost.put(new RateClassRateType(rate.getRateClassCode(), rate.getRateTypeCode()), currentTotals.get(rate).get(0));
            }
        }
        if  (ObjectUtils.isNotNull(prevTotals)) {
            for (RateType rate : prevTotals.keySet()) {
                if (!StringUtils.equalsIgnoreCase(rate.getRateClass().getRateClassType(), "O")) {
                    prevCost.put(new RateClassRateType(rate.getRateClassCode(), rate.getRateTypeCode()), prevTotals.get(rate).get(0));
                }
            }
        }
        List<RateClassRateType> prev = new ArrayList<RateClassRateType>();
        for (RateClassRateType rate : currentCost.keySet()) {
            if (prevCost.containsKey(rate)) {
                netCost.put(rate, currentCost.get(rate).subtract(prevCost.get(rate)));
                prev.add(rate);
               // prevCost.remove(rate);
            } else {
                netCost.put(rate, currentCost.get(rate));
            }
        }
        
        for (RateClassRateType rate : prevCost.keySet()) {
            if (!prev.contains(rate)) {
                netCost.put(rate, prevCost.get(rate).negated());
            }
        }
        
        return netCost;
    }
    
    
    /*
     * The BudgetCalculationService does this by objectCodeType but we need by RateClass,RateType.
     */
    public Map<RateClassRateType, BudgetDecimal> getPersonnelFringeCost() {
        
        BudgetCategoryType personnelCategory =  getPersonnelCategoryType();
        List<CostElement> currentPersonnelObjectCodes = currentBudget.getObjectCodeListByBudgetCategoryType().get(personnelCategory); 
        List<CostElement> prevPersonnelObjectCodes = new ArrayList<CostElement>();
        Map<RateClassRateType, BudgetDecimal> prevFringeTotals = new HashMap<RateClassRateType, BudgetDecimal>();

        if (ObjectUtils.isNotNull(previousBudget) && 
            ObjectUtils.isNotNull(previousBudget.getObjectCodeListByBudgetCategoryType())) {
                prevPersonnelObjectCodes = previousBudget.getObjectCodeListByBudgetCategoryType().get(personnelCategory);
                prevFringeTotals = getFringeTotals(prevPersonnelObjectCodes, previousBudget);
        }
        Map<RateClassRateType, BudgetDecimal> currentFringeTotals = getFringeTotals(currentPersonnelObjectCodes, currentBudget);
        List<RateClassRateType> prev = new ArrayList<RateClassRateType>();
        Map<RateClassRateType, BudgetDecimal> netFringeTotals = new HashMap<RateClassRateType, BudgetDecimal>();
        for (RateClassRateType rate : currentFringeTotals.keySet()) {
            if (prevFringeTotals.containsKey(rate)) { 
                netFringeTotals.put(rate, currentFringeTotals.get(rate).subtract(prevFringeTotals.get(rate)));
                prev.add(rate);
                //prevFringeTotals.remove(rate);
            } else {
                netFringeTotals.put(rate, currentFringeTotals.get(rate));
            }
        }
        
        for (RateClassRateType rate : prevFringeTotals.keySet()) {
            if (!prev.contains(rate)) {
                netFringeTotals.put(rate, prevFringeTotals.get(rate).negated());
            }
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
    
    
    /**
     * This method returns the business object service.
     * @return
     */
    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectservice == null) {
           businessObjectservice = KraServiceLocator.getService(BusinessObjectService.class);
        }
       return businessObjectservice; 
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
    public SortedMap<String, BudgetDecimal> getPersonnelSalaryCost() throws Exception {
        SortedMap<String, List<BudgetDecimal>> currentSalaryTotals = currentBudget.getObjectCodePersonnelSalaryTotals();
        SortedMap<String, List<BudgetDecimal>> prevSalaryTotals = new TreeMap<String, List<BudgetDecimal>>();
        SortedMap<String, BudgetDecimal> netSalary =  new TreeMap<String, BudgetDecimal>();
        
        if  (ObjectUtils.isNotNull(previousBudget)) {
            prevSalaryTotals = previousBudget.getObjectCodePersonnelSalaryTotals();
        }
        
        List<String> prev = new ArrayList<String>();
        for (String person : currentSalaryTotals.keySet()) {
            String key = person;
            if (person.contains(",")) {
                String[] objectCode = getElements(key);
                key = objectCode[0];
            }
            BudgetDecimal currentSalary = currentSalaryTotals.get(person).get(0);
            System.out.println("Salary of " + person + "is " + currentSalary);
            if (ObjectUtils.isNotNull(prevSalaryTotals) && ObjectUtils.isNotNull(prevSalaryTotals.get(person))) {           
                BudgetDecimal previousSalary = prevSalaryTotals.get(person).get(0);       
                netSalary.put(key, currentSalary.subtract(previousSalary));
                prev.add(person);
               // prevSalaryTotals.remove(person);
            } else {
                netSalary.put(key, currentSalary);
            }
        }
   
        if (ObjectUtils.isNotNull(prevSalaryTotals)) {
            for (String person : prevSalaryTotals.keySet()) {
                if (!prev.contains(person)) {
                    String key = person;
                    if (person.contains(",")) {
                        String[] objectCode = getElements(person);
                        key = objectCode[0];
                    }
                    netSalary.put(key, prevSalaryTotals.get(person).get(0).negated());
                }
            }
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
    
    public Budget getCurrentBudget() {
        return currentBudget;
    }
    public void setCurrentBudget(Budget currentBudget) {
        this.currentBudget = currentBudget;
    }
    public Budget getPreviousBudget() {
        return previousBudget;
    }
    public void setPreviousBudget(AwardBudgetExt previousBudget) {
        this.previousBudget = previousBudget;
    }
    
    
    
}
