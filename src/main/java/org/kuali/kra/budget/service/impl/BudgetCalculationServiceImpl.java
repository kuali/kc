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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetCategoryType;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.bo.CostElement;
import org.kuali.kra.budget.bo.RateClass;
import org.kuali.kra.budget.bo.RateType;
import org.kuali.kra.budget.calculator.BudgetPeriodCalculator;
import org.kuali.kra.budget.calculator.LineItemCalculator;
import org.kuali.kra.budget.calculator.PersonnelLineItemCalculator;
import org.kuali.kra.budget.calculator.QueryList;
import org.kuali.kra.budget.calculator.SalaryCalculator;
import org.kuali.kra.budget.calculator.query.And;
import org.kuali.kra.budget.calculator.query.Equals;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetCalculationService;
import org.kuali.kra.budget.service.BudgetDistributionAndIncomeService;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.ErrorMap;


/**
 * This class implements all methods declared in BudgetCalculationService
 */
public class BudgetCalculationServiceImpl implements BudgetCalculationService {
    private BusinessObjectService businessObjectService;
    private BudgetDistributionAndIncomeService budgetDistributionAndIncomeService;
    
    /**
     * @see org.kuali.kra.budget.service.BudgetCalculationService#calculateBudget(java.lang.String, java.lang.Integer)
     */
    public void calculateBudget(BudgetDocument budgetDocument){
        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudgetPeriods();
        String ohRateClassCodePrevValue = null;
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            if(isCalculationRequired(budgetDocument.isBudgetLineItemDeleted(),budgetPeriod)){
                String workOhCode = null;
                if(budgetDocument.getOhRateClassCode()!=null && ((BudgetForm)GlobalVariables.getKualiForm())!=null && budgetDocument.getBudgetPeriods().size() > budgetPeriod.getBudgetPeriod()){
                    workOhCode = ((BudgetForm)GlobalVariables.getKualiForm()).getOhRateClassCodePrevValue();
                }
                calculateBudgetPeriod(budgetDocument, budgetPeriod);
                if(budgetDocument.getOhRateClassCode()!=null && ((BudgetForm)GlobalVariables.getKualiForm())!=null && budgetDocument.getBudgetPeriods().size() > budgetPeriod.getBudgetPeriod()){
                        // this should be set at the last period, otherwise, only the first period will be updated properly because lots of places check prevohrateclass
                    ohRateClassCodePrevValue = ((BudgetForm)GlobalVariables.getKualiForm()).getOhRateClassCodePrevValue();
                    ((BudgetForm)GlobalVariables.getKualiForm()).setOhRateClassCodePrevValue(workOhCode);
                }
            }
        }
        if (((BudgetForm)GlobalVariables.getKualiForm())!=null && ((BudgetForm)GlobalVariables.getKualiForm()).getOhRateClassCodePrevValue() == null && ohRateClassCodePrevValue != null) {
            // if not all periods are calculated, then this code has potential to be null, and this will force
            // to create calamts again
            ((BudgetForm)GlobalVariables.getKualiForm()).setOhRateClassCodePrevValue(ohRateClassCodePrevValue);            
        }
        if(budgetPeriods!=null && !budgetPeriods.isEmpty()){
            syncCostsToBudgetDocument(budgetDocument);
        }
    }
    /**
     * Checks if a calculation is required where Budget periods must be synced in line items.
     *  
     * @param budgetLineItemDeleted whether of not a budget line item has been deleted.
     * @param budgetPeriod the current budget period.
     * 
     * @return true if calculation is required false if not
     */
    private boolean isCalculationRequired(final boolean budgetLineItemDeleted, final BudgetPeriod budgetPeriod){
        assert budgetPeriod != null : "The budget period is null";
        
        final boolean isLineItemsEmpty = budgetPeriod.getBudgetLineItems().isEmpty();
        
        if(isLineItemsEmpty && !budgetLineItemDeleted){
            final Map<Object, Object> fieldValues = new HashMap<Object, Object>();
            fieldValues.put("proposalNumber", budgetPeriod.getProposalNumber());
            fieldValues.put("budgetVersionNumber", budgetPeriod.getBudgetVersionNumber());
            fieldValues.put("budgetPeriod", budgetPeriod.getBudgetPeriod());
            
            @SuppressWarnings("unchecked")
            final Collection<BudgetLineItem> deletedLineItems
                = this.businessObjectService.findMatching(BudgetLineItem.class, fieldValues);
            return !deletedLineItems.isEmpty();
        }
            
        return true;
    }
    private void copyLineItemToPersonnelDetails(BudgetLineItem budgetLineItem, BudgetPersonnelDetails budgetPersonnelDetails) {
        budgetPersonnelDetails.setProposalNumber(budgetLineItem.getProposalNumber());
        budgetPersonnelDetails.setBudgetVersionNumber(budgetLineItem.getBudgetVersionNumber());
        budgetPersonnelDetails.setBudgetPeriod(budgetLineItem.getBudgetPeriod());
        budgetPersonnelDetails.setLineItemNumber(budgetLineItem.getLineItemNumber());
        budgetPersonnelDetails.setCostElement(budgetLineItem.getCostElement());
        budgetPersonnelDetails.setCostElementBO(budgetLineItem.getCostElementBO());
        budgetPersonnelDetails.setApplyInRateFlag(budgetLineItem.getApplyInRateFlag());
        budgetPersonnelDetails.setOnOffCampusFlag(budgetLineItem.getOnOffCampusFlag());
    }

    public void calculateBudgetLineItem(BudgetDocument budgetDocument,BudgetPersonnelDetails budgetLineItem){
        new PersonnelLineItemCalculator(budgetDocument,budgetLineItem).calculate();
    }
    /**
     * @see org.kuali.kra.budget.service.BudgetCalculationService#calculateBudgetLineItem(org.kuali.kra.budget.bo.BudgetLineItem)
     */
    public void calculateBudgetLineItem(BudgetDocument budgetDocument,BudgetLineItem budgetLineItem){
        BudgetLineItem budgetLineItemToCalc = budgetLineItem;
        List<BudgetPersonnelDetails> budgetPersonnelDetList = budgetLineItemToCalc.getBudgetPersonnelDetailsList();
        if(budgetLineItemToCalc.isBudgetPersonnelLineItemDeleted() || (budgetPersonnelDetList!=null && !budgetPersonnelDetList.isEmpty())){
            updatePersonnelBudgetRate(budgetLineItemToCalc);
            BudgetDecimal personnelLineItemTotal  = BudgetDecimal.ZERO;
            BudgetDecimal personnelTotalCostSharing  = BudgetDecimal.ZERO;
            Map<String, BudgetDecimal> totalCalculatedCost = new HashMap<String, BudgetDecimal> ();
            Map<String, BudgetDecimal> totalCalculatedCostSharing = new HashMap<String, BudgetDecimal> ();
             
            for (BudgetPersonnelDetails budgetPersonnelDetails : budgetPersonnelDetList) {
                copyLineItemToPersonnelDetails(budgetLineItemToCalc, budgetPersonnelDetails);
                new PersonnelLineItemCalculator(budgetDocument,budgetPersonnelDetails).calculate();
                personnelLineItemTotal = personnelLineItemTotal.add(budgetPersonnelDetails.getLineItemCost());
                personnelTotalCostSharing = personnelTotalCostSharing.add(budgetPersonnelDetails.getCostSharingAmount());
                List<BudgetPersonnelCalculatedAmount> calAmts = budgetPersonnelDetails.getBudgetCalculatedAmounts();
                if (CollectionUtils.isNotEmpty(calAmts)) {
                    String rateKey;
                    for (BudgetPersonnelCalculatedAmount personnelCalAmt :calAmts) {
                        rateKey = personnelCalAmt.getRateClassCode()+":"+personnelCalAmt.getRateTypeCode();
                        if (!totalCalculatedCost.containsKey(rateKey)) {
                            totalCalculatedCost.put(rateKey, personnelCalAmt.getCalculatedCost());
                            totalCalculatedCostSharing.put(rateKey, personnelCalAmt.getCalculatedCostSharing());
                        } else {
                            BudgetDecimal value = totalCalculatedCost.get(rateKey);
                            value = value.add(personnelCalAmt.getCalculatedCost());
                            totalCalculatedCost.put(rateKey, value);
                            
                            value = totalCalculatedCostSharing.get(rateKey);
                            value = value.add(personnelCalAmt.getCalculatedCostSharing());
                            totalCalculatedCostSharing.put(rateKey, value);
                            
                        }
                        
                    }
                    
                }
            }
            budgetLineItem.setLineItemCost(personnelLineItemTotal);
            budgetLineItem.setCostSharingAmount(personnelTotalCostSharing);
            // still need to populate budgetrateandbase ?
            new LineItemCalculator(budgetDocument,budgetLineItem).calculate();
            
            List <BudgetLineItemCalculatedAmount> budgetLineItemCalculatedAmounts = budgetLineItem.getBudgetLineItemCalculatedAmounts();
            if (CollectionUtils.isNotEmpty(budgetLineItemCalculatedAmounts)) {
                String rateKey;
                for (BudgetLineItemCalculatedAmount lineItemCalAmt : budgetLineItemCalculatedAmounts) {
                    rateKey = lineItemCalAmt.getRateClassCode()+":"+lineItemCalAmt.getRateTypeCode();
                    if (totalCalculatedCost.containsKey(rateKey)) {
                        lineItemCalAmt.setCalculatedCost(totalCalculatedCost.get(rateKey));
                        lineItemCalAmt.setCalculatedCostSharing(totalCalculatedCostSharing.get(rateKey));
                    }
                }
            }
        
        } else {
            new LineItemCalculator(budgetDocument,budgetLineItem).calculate();
        }
    }
    public void calculateAndSyncBudgetLineItem(BudgetDocument budgetDocument,BudgetLineItem budgetLineItem){
        new LineItemCalculator(budgetDocument,budgetLineItem).calculate();
        syncCostsToBudgetDocument(budgetDocument);
    }

    /**
     * @see org.kuali.kra.budget.service.BudgetCalculationService#calculateCalculatedAmount(org.kuali.kra.budget.bo.BudgetLineItem)
     */
    public void populateCalculatedAmount(BudgetDocument budgetDocument,BudgetLineItem budgetLineItem){
        new LineItemCalculator(budgetDocument,budgetLineItem).populateCalculatedAmountLineItems();
    }
    /**
     * @see org.kuali.kra.budget.service.BudgetCalculationService#calculateCalculatedAmount(org.kuali.kra.budget.bo.BudgetLineItem)
     */
    public void populateCalculatedAmount(BudgetDocument budgetDocument,BudgetPersonnelDetails budgetPersonnelDetails){
        new PersonnelLineItemCalculator(budgetDocument,budgetPersonnelDetails).populateCalculatedAmountLineItems();
    }

    /**
     * @see org.kuali.kra.budget.service.BudgetCalculationService#calculateSalary(org.kuali.kra.budget.bo.BudgetPersonnelDetails)
     */
    public void calculateSalary(BudgetDocument budgetDocument,BudgetPersonnelDetails budgetPersonnelLineItem){
        new SalaryCalculator(budgetDocument,budgetPersonnelLineItem).calculate();
    }

    public void calculateBudgetPeriod(BudgetDocument budgetDocument, BudgetPeriod budgetPeriod){
        if (isCalculationRequired(budgetDocument.isBudgetLineItemDeleted(), budgetPeriod)){
            new BudgetPeriodCalculator().calculate(budgetDocument, budgetPeriod);
        }
    }
    
    public void calculateAndSyncBudgetPeriod(BudgetDocument budgetDocument, BudgetPeriod budgetPeriod){
        calculateBudgetPeriod(budgetDocument, budgetPeriod);
        syncCostsToBudgetDocument(budgetDocument);
    }

    /**
     * Syncs the calculated costs in the budget document with the calculated costs in the budget
     * periods. If the certain costs are not positive then lists on items related to those costs 
     * are also cleared and reset (i.e. UnrecoveredFandAs).
     * This method modifies the passed in BudgetDocument.
     * 
     * @param budgetDocument the budget document
     */
    private void syncCostsToBudgetDocument(final BudgetDocument budgetDocument){
        assert budgetDocument != null : "The document was null";
        
        this.initCostDependentItems(budgetDocument);
        this.ensureBudgetPeriodHasSyncedCosts(budgetDocument);
        this.setBudgetDocumentCostsFromPeriods(budgetDocument);
    }
    
    /**
     * Initializes items that are dependent on a cost value. (i.e. UnrecoveredFandAs)
     * This method modifies the passed in BudgetDocument.
     * 
     * @param budgetDocument the budget document
     */
    private void initCostDependentItems(final BudgetDocument document) {
        assert document != null : "The document was null";
        
        if (!this.isPositiveTotalUnderreoveryAmount(document)) {
            this.initUnrecoveredFandAs(document);
        }
        
        if (!this.isPositiveTotalCostSharingAmount(document)) {
            this.initCostSharing(document);
        }
    }
    
    /**
     * Clears and initializes the UnrecoveredFandAs in a budget document.
     * This method modifies the passed in BudgetDocument.
     * 
     * @param document the budget document.
     */
    private void initUnrecoveredFandAs(final BudgetDocument document) {
        assert document != null : "the document was null";
        
        document.getBudgetUnrecoveredFandAs().clear();
        this.getBudgetDistributionAndIncomeService().initializeUnrecoveredFandACollectionDefaults(document);
    }
    
    /**
     * Clears and initializes the CostSharing in a budget document.
     * This method modifies the passed in BudgetDocument.
     * 
     * @param document the budget document.
     */
    private void initCostSharing(final BudgetDocument document) {
        assert document != null : "the document was null";
        
        document.getBudgetCostShares().clear();
        this.getBudgetDistributionAndIncomeService().initializeCostSharingCollectionDefaults(document);
    }

    /**
     * Ensures that a budget period has synced costs with other budget objects (i.e. line items)
     * 
     * @param budgetLineItemDeleted whether or not a budget line item has been deleted
     * @param currentPeriod the current period.
     */
    private void ensureBudgetPeriodHasSyncedCosts(final BudgetDocument document) {
        assert document != null : "the document was null";
        
        for (final BudgetPeriod budgetPeriod : document.getBudgetPeriods()) {
            if (this.isCalculationRequired(document.isBudgetLineItemDeleted(), budgetPeriod)) {
                this.setBudgetPeriodCostsFromLineItems(budgetPeriod);
            }
        }
    }
    
    /**
     * 
     * This method sets the budget document's costs from the budget periods' costs.
     * This method modifies the passed in budget document.
     * 
     * @param document the budget document to set the costs on.
     */
    private void setBudgetDocumentCostsFromPeriods(final BudgetDocument document) {
        assert document != null : "The document is null";

        document.setTotalDirectCost(document.getSumDirectCostAmountFromPeriods());
        document.setTotalIndirectCost(document.getSumIndirectCostAmountFromPeriods());
        document.setTotalCost(document.getSumTotalCostAmountFromPeriods());
        document.setUnderrecoveryAmount(document.getSumUnderreoveryAmountFromPeriods());
        document.setCostSharingAmount(document.getSumCostSharingAmountFromPeriods());
    }
    
    /**
     * 
     * This method sets the budget period costs from the line item costs.
     * This method modifies the passed in budget period.
     * 
     * @param budgetPeriod the budget periods to set the costs on.
     */
    private void setBudgetPeriodCostsFromLineItems(final BudgetPeriod budgetPeriod) {
        assert budgetPeriod != null : "The period is null";

        budgetPeriod.setTotalDirectCost(budgetPeriod.getSumDirectCostAmountFromLineItems());
        budgetPeriod.setTotalIndirectCost(budgetPeriod.getSumIndirectCostAmountFromLineItems());
        budgetPeriod.setTotalCost(budgetPeriod.getSumTotalCostAmountFromLineItems());
        budgetPeriod.setUnderrecoveryAmount(budgetPeriod.getSumUnderreoveryAmountFromLineItems());
        budgetPeriod.setCostSharingAmount(budgetPeriod.getSumTotalCostSharingAmountFromLineItems());
            }
    
    /**
     * Checks if a positive Total Underrecoverary Amount exists in a line item or in a budget period.
     * @param document The budget Document
     * @return true if positive.
     */
    private final boolean isPositiveTotalUnderreoveryAmount(final BudgetDocument document) {
        assert document != null : "The periods is null";
        
        BudgetDecimal lineItemsAmount = BudgetDecimal.ZERO;
        
        for (final BudgetPeriod budgetPeriod : document.getBudgetPeriods()) {
            lineItemsAmount = lineItemsAmount.add(budgetPeriod.getSumUnderreoveryAmountFromLineItems());
        }

        return lineItemsAmount.isPositive()
            || document.getSumUnderreoveryAmountFromPeriods().isPositive();
        } 
        
    /**
     * Checks if a positive Total CostSharing Amount exists in a line item or in a budget period.
     * @param document The budget Document
     * @return true if positive.
     */
    private final boolean isPositiveTotalCostSharingAmount(final BudgetDocument document) {
        assert document != null : "The document is null";
        
        BudgetDecimal lineItemsAmount = BudgetDecimal.ZERO;
        
        for (final BudgetPeriod budgetPeriod : document.getBudgetPeriods()) {
            lineItemsAmount = lineItemsAmount.add(budgetPeriod.getSumTotalCostSharingAmountFromLineItems());
        }        
        
        return lineItemsAmount.isPositive()
            || document.getSumCostSharingAmountFromPeriods().isPositive();
    }

    private SortedMap<BudgetCategoryType, List<CostElement>> categorizeObjectCodesByCategory(BudgetDocument budgetDocument) {
        SortedMap<CostElement, List<BudgetDecimal>> objectCodeTotals = budgetDocument.getObjectCodeTotals();
        SortedMap<BudgetCategoryType, List<CostElement>> objectCodeListByBudgetCategoryType = new TreeMap<BudgetCategoryType, List<CostElement>>();
        
        for(CostElement objectCode : objectCodeTotals.keySet()) {
            objectCode.refreshReferenceObject("budgetCategory");
            if(objectCode.getBudgetCategory() != null) {
                objectCode.getBudgetCategory().refreshReferenceObject("budgetCategoryType");
                objectCode.setBudgetCategoryTypeCode(objectCode.getBudgetCategory().getBudgetCategoryTypeCode());
            }
            if(!objectCodeListByBudgetCategoryType.containsKey(objectCode.getBudgetCategory().getBudgetCategoryType())) {
                List<CostElement> filteredObjectCodes = filterObjectCodesByBudgetCategoryType(objectCodeTotals.keySet(), objectCode.getBudgetCategoryTypeCode());
                objectCodeListByBudgetCategoryType.put(objectCode.getBudgetCategory().getBudgetCategoryType(), filteredObjectCodes);
            }
        }
        
        return objectCodeListByBudgetCategoryType;
    }
    
    private BudgetCategoryType getPersonnelCategoryType() {
        final Map<String, String> primaryKeys = new HashMap<String, String>();
        primaryKeys.put("budgetCategoryTypeCode", "P");
        return (BudgetCategoryType) this.getBusinessObjectService().findByPrimaryKey(BudgetCategoryType.class, primaryKeys);
    }
    
    public void calculateBudgetSummaryTotals(BudgetDocument budgetDocument){
        calculateBudgetTotals(budgetDocument);

        //Categorize all Object Codes per their Category Type
        SortedMap<BudgetCategoryType, List<CostElement>> objectCodeListByBudgetCategoryType = categorizeObjectCodesByCategory(budgetDocument);   
       
        SortedMap<CostElement, List<BudgetPersonnelDetails>> objectCodeUniquePersonnelList = new TreeMap<CostElement, List<BudgetPersonnelDetails>>();
        
        SortedMap<String, List<BudgetDecimal>> objectCodePersonnelSalaryTotals = new TreeMap<String, List<BudgetDecimal>>();
        SortedMap<String, List<BudgetDecimal>> objectCodePersonnelFringeTotals = new TreeMap<String, List<BudgetDecimal>>();
        
        //Temp collections for maintaining Sub Section Totals
        SortedSet<String> objectCodePersonnelSalaryTotalsByPeriod = new TreeSet<String>();
        SortedSet<String> objectCodePersonnelFringeTotalsByPeriod = new TreeSet<String>();

        SortedMap<RateType, List<BudgetDecimal>> personnelCalculatedExpenseTotals = new TreeMap<RateType, List<BudgetDecimal>>();
        SortedMap<RateType, List<BudgetDecimal>> nonPersonnelCalculatedExpenseTotals = new TreeMap<RateType, List<BudgetDecimal>>(); 

        List <BudgetDecimal> periodSummarySalaryTotals = new ArrayList<BudgetDecimal>();
        for (int i = 0; i < budgetDocument.getBudgetPeriods().size(); i++) {
            periodSummarySalaryTotals.add(i,BudgetDecimal.ZERO);
        }
        List <BudgetDecimal> periodSummaryFringeTotals = new ArrayList<BudgetDecimal>();
        for (int i = 0; i < budgetDocument.getBudgetPeriods().size(); i++) {
            periodSummaryFringeTotals.add(i,BudgetDecimal.ZERO);
        }
        SortedMap<String, List<BudgetDecimal>> subTotalsBySubSection = new TreeMap<String, List<BudgetDecimal>>();
        subTotalsBySubSection.put("personnelSalaryTotals", periodSummarySalaryTotals);
        subTotalsBySubSection.put("personnelFringeTotals", periodSummaryFringeTotals);
        
        //Loop thru the Personnel Object Codes - to calculate Salary, Fringe Totals etc.. per person
        BudgetCategoryType personnelCategory =  getPersonnelCategoryType();
        List<CostElement> personnelObjectCodes = objectCodeListByBudgetCategoryType.get(personnelCategory); 
        
        if(CollectionUtils.isNotEmpty(personnelObjectCodes)) {
            for(CostElement personnelCostElement : personnelObjectCodes) {
                if(!objectCodeUniquePersonnelList.containsKey(personnelCostElement)) {
                    objectCodeUniquePersonnelList.put(personnelCostElement, new ArrayList<BudgetPersonnelDetails>());
                }
                
                for (BudgetPeriod budgetPeriod: budgetDocument.getBudgetPeriods()) {
                    QueryList lineItemQueryList = new QueryList();
                    lineItemQueryList.addAll(budgetPeriod.getBudgetLineItems());
                    Equals objectCodeEquals = new Equals("costElement", personnelCostElement.getCostElement());
                    QueryList<BudgetLineItem> filteredLineItems = lineItemQueryList.filter(objectCodeEquals);
                    QueryList personnelQueryList = new QueryList();
                    
                    //Loop thru the matching Line Items to gather personnel info
                    for(BudgetLineItem matchingLineItem : filteredLineItems) {
                        personnelQueryList.addAll(matchingLineItem.getBudgetPersonnelDetailsList());
                    }
                   
                    int matchingLineItemIndex = 0;
                    for(BudgetLineItem matchingLineItem : filteredLineItems) {
                        for(BudgetPersonnelDetails budgetPersonnelDetails : matchingLineItem.getBudgetPersonnelDetailsList()) {
                            budgetPersonnelDetails.refreshReferenceObject("budgetPerson");
                            Equals personIdEquals = new Equals("personId", budgetPersonnelDetails.getPersonId());
                            QueryList personOccurrencesForSameObjectCode = personnelQueryList.filter(personIdEquals);
                            
                            //Calculate the Salary Totals for each Person
                            BudgetDecimal personSalaryTotalsForCurrentPeriod = personOccurrencesForSameObjectCode.sumObjects("salaryRequested");
                            
                            if (!objectCodePersonnelSalaryTotals.containsKey(matchingLineItem.getCostElement()+","+budgetPersonnelDetails.getPersonId())) {
                                objectCodeUniquePersonnelList.get(matchingLineItem.getCostElementBO()).add(budgetPersonnelDetails);
                                // set up for all periods and put into map
                                List <BudgetDecimal> periodTotals = new ArrayList<BudgetDecimal>();
                                for (int i = 0; i < budgetDocument.getBudgetPeriods().size(); i++) {
                                    periodTotals.add(i,BudgetDecimal.ZERO);
                                }
                                objectCodePersonnelSalaryTotals.put(matchingLineItem.getCostElement()+","+budgetPersonnelDetails.getPersonId(), periodTotals);
                            }
                            //Setting the total lines here - so that they'll be set just once for a unique person within an Object Code
                            objectCodePersonnelSalaryTotals.get(matchingLineItem.getCostElement()+","+budgetPersonnelDetails.getPersonId()).set(budgetPeriod.getBudgetPeriod() - 1, personSalaryTotalsForCurrentPeriod);
                            //subTotalsBySubSection.get("personnelSalaryTotals").set(budgetPeriod.getBudgetPeriod() - 1, ((BudgetDecimal) (subTotalsBySubSection.get("personnelSalaryTotals").get(budgetPeriod.getBudgetPeriod() - 1))).add(personSalaryTotalsForCurrentPeriod));
                            if (objectCodePersonnelSalaryTotalsByPeriod.add(budgetPeriod.getBudgetPeriod().toString() + ","+ matchingLineItem.getCostElement()+","+budgetPersonnelDetails.getPersonId())) {
                                subTotalsBySubSection.get("personnelSalaryTotals").set(budgetPeriod.getBudgetPeriod() - 1, ((BudgetDecimal) (subTotalsBySubSection.get("personnelSalaryTotals").get(budgetPeriod.getBudgetPeriod() - 1))).add(personSalaryTotalsForCurrentPeriod));
                            }
                            
                            //Calculate the Fringe Totals for each Person
                            BudgetDecimal personFringeTotalsForCurrentPeriod = BudgetDecimal.ZERO;
                            if (!objectCodePersonnelFringeTotals.containsKey(matchingLineItem.getCostElement()+","+budgetPersonnelDetails.getPersonId())) {
                                // set up for all periods and put into map
                                List <BudgetDecimal> periodFringeTotals = new ArrayList<BudgetDecimal>();
                                for (int i = 0; i < budgetDocument.getBudgetPeriods().size(); i++) {
                                    periodFringeTotals.add(i,BudgetDecimal.ZERO);
                                }
                                objectCodePersonnelFringeTotals.put(matchingLineItem.getCostElement()+","+budgetPersonnelDetails.getPersonId(), periodFringeTotals); 
                            }   
                            
                            //Calculate the Fringe Totals for that Person (cumulative fringe for all occurrences of the person)
                            for(Object person : personOccurrencesForSameObjectCode) {
                                BudgetPersonnelDetails personOccurrence = (BudgetPersonnelDetails) person;
                                for(BudgetPersonnelCalculatedAmount calcExpenseAmount : personOccurrence.getBudgetPersonnelCalculatedAmounts()) {
                                    calcExpenseAmount.refreshReferenceObject("rateClass");
                                    //Check for Employee Benefits RateClassType
                                    if(calcExpenseAmount.getRateClass().getRateClassType().equalsIgnoreCase("E")) {
                                        personFringeTotalsForCurrentPeriod = personFringeTotalsForCurrentPeriod.add(calcExpenseAmount.getCalculatedCost());
                                    }
                                }
                            }
                            objectCodePersonnelFringeTotals.get(matchingLineItem.getCostElement()+","+budgetPersonnelDetails.getPersonId()).set(budgetPeriod.getBudgetPeriod() - 1, personFringeTotalsForCurrentPeriod);
                            //subTotalsBySubSection.get("personnelFringeTotals").set(budgetPeriod.getBudgetPeriod() - 1, ((BudgetDecimal) (subTotalsBySubSection.get("personnelFringeTotals").get(budgetPeriod.getBudgetPeriod() - 1))).add(personFringeTotalsForCurrentPeriod));
                            
                            if (objectCodePersonnelFringeTotalsByPeriod.add(budgetPeriod.getBudgetPeriod().toString() + ","+ matchingLineItem.getCostElement()+","+budgetPersonnelDetails.getPersonId())) {
                                subTotalsBySubSection.get("personnelFringeTotals").set(budgetPeriod.getBudgetPeriod() - 1, ((BudgetDecimal) (subTotalsBySubSection.get("personnelFringeTotals").get(budgetPeriod.getBudgetPeriod() - 1))).add(personFringeTotalsForCurrentPeriod));
                            }
                        } 
                        
                        //Need to handle the Summary Items - if any
                        if(CollectionUtils.isEmpty(matchingLineItem.getBudgetPersonnelDetailsList())) {
                            //Include Summary Item Salary (Line Item Cost)
                            if (!objectCodePersonnelSalaryTotals.containsKey(matchingLineItem.getCostElement())) {
                                // set up for all periods and put into map
                                List <BudgetDecimal> periodTotals = new ArrayList<BudgetDecimal>();
                                for (int i = 0; i < budgetDocument.getBudgetPeriods().size(); i++) {
                                    periodTotals.add(i,BudgetDecimal.ZERO);
                                }
                                objectCodePersonnelSalaryTotals.put(matchingLineItem.getCostElement(), periodTotals);
                            }
                            objectCodePersonnelSalaryTotals.get(matchingLineItem.getCostElement()).set(budgetPeriod.getBudgetPeriod() - 1, ((BudgetDecimal) objectCodePersonnelSalaryTotals.get(matchingLineItem.getCostElement()).get(budgetPeriod.getBudgetPeriod() - 1)).add(matchingLineItem.getLineItemCost()));
                            
                            //Include Summary Item Fringe Amt
                            BudgetDecimal summaryFringeTotalsForCurrentPeriod = BudgetDecimal.ZERO;
                            if (!objectCodePersonnelFringeTotals.containsKey(matchingLineItem.getCostElement())) {
                                // set up for all periods and put into map
                                List <BudgetDecimal> periodFringeTotals = new ArrayList<BudgetDecimal>();
                                for (int i = 0; i < budgetDocument.getBudgetPeriods().size(); i++) {
                                    periodFringeTotals.add(i,BudgetDecimal.ZERO);
                                }
                                objectCodePersonnelFringeTotals.put(matchingLineItem.getCostElement(), periodFringeTotals); 
                            }
                            
                            for(BudgetLineItemCalculatedAmount lineItemCalculatedAmount : matchingLineItem.getBudgetLineItemCalculatedAmounts()) {
                                lineItemCalculatedAmount.refreshReferenceObject("rateClass");
                                //Check for Employee Benefits RateClassType
                                if(lineItemCalculatedAmount.getRateClass().getRateClassType().equalsIgnoreCase("E")) {
                                    summaryFringeTotalsForCurrentPeriod = summaryFringeTotalsForCurrentPeriod.add(lineItemCalculatedAmount.getCalculatedCost());
                                }
                            }
                            objectCodePersonnelFringeTotals.get(matchingLineItem.getCostElement()).set(budgetPeriod.getBudgetPeriod() - 1, ((BudgetDecimal) objectCodePersonnelFringeTotals.get(matchingLineItem.getCostElement()).get(budgetPeriod.getBudgetPeriod() - 1)).add(summaryFringeTotalsForCurrentPeriod));

                            //if(matchingLineItemIndex == filteredLineItems.size()-1) { 
                                 subTotalsBySubSection.get("personnelSalaryTotals").set(budgetPeriod.getBudgetPeriod() - 1, ((BudgetDecimal) (subTotalsBySubSection.get("personnelSalaryTotals").get(budgetPeriod.getBudgetPeriod() - 1))).add((BudgetDecimal) (objectCodePersonnelSalaryTotals.get(matchingLineItem.getCostElement()).get(budgetPeriod.getBudgetPeriod()-1))));
                                 subTotalsBySubSection.get("personnelFringeTotals").set(budgetPeriod.getBudgetPeriod() - 1, ((BudgetDecimal) (subTotalsBySubSection.get("personnelFringeTotals").get(budgetPeriod.getBudgetPeriod() - 1))).add((BudgetDecimal) (objectCodePersonnelFringeTotals.get(matchingLineItem.getCostElement()).get(budgetPeriod.getBudgetPeriod()-1))));
                            //}
                        }
                        
                        matchingLineItemIndex++;
                    }
                
                } //Budget Period Looping Ends here
            } //Personnel Object Code Looping Ends here
        }
        
        budgetDocument.setBudgetSummaryTotals(subTotalsBySubSection);
        personnelCalculatedExpenseTotals = calculateExpenseTotals(budgetDocument, true);
        nonPersonnelCalculatedExpenseTotals = calculateExpenseTotals(budgetDocument, false);
        
        budgetDocument.setObjectCodeListByBudgetCategoryType(objectCodeListByBudgetCategoryType);
        //budgetDocument.setObjectCodePersonnelList(objectCodePersonnelList);
        budgetDocument.setObjectCodePersonnelList(objectCodeUniquePersonnelList);
        budgetDocument.setObjectCodePersonnelSalaryTotals(objectCodePersonnelSalaryTotals);
        budgetDocument.setObjectCodePersonnelFringeTotals(objectCodePersonnelFringeTotals);
        budgetDocument.setPersonnelCalculatedExpenseTotals(personnelCalculatedExpenseTotals);
        budgetDocument.setNonPersonnelCalculatedExpenseTotals(nonPersonnelCalculatedExpenseTotals);
        calculateNonPersonnelSummaryTotals(budgetDocument);
    }  
    
    private void calculateNonPersonnelSummaryTotals(BudgetDocument budgetDocument) {
        for(BudgetCategoryType budgetCategoryType : budgetDocument.getObjectCodeListByBudgetCategoryType().keySet()) {
            if(!StringUtils.equals(budgetCategoryType.getBudgetCategoryTypeCode(), "P")) {
                List <BudgetDecimal> nonPersonnelTotals = new ArrayList<BudgetDecimal>();
                for (int i = 0; i < budgetDocument.getBudgetPeriods().size(); i++) {
                    nonPersonnelTotals.add(i,BudgetDecimal.ZERO);
                }
                budgetDocument.getBudgetSummaryTotals().put(budgetCategoryType.getBudgetCategoryTypeCode(), nonPersonnelTotals);
                
                List<CostElement> objectCodes = budgetDocument.getObjectCodeListByBudgetCategoryType().get(budgetCategoryType);
                for(CostElement objectCode : objectCodes) {
                    List<BudgetDecimal> objectCodePeriodTotals = budgetDocument.getObjectCodeTotals().get(objectCode);
                    for(BudgetPeriod budgetPeriod : budgetDocument.getBudgetPeriods()) {
                        budgetDocument.getBudgetSummaryTotals().get(budgetCategoryType.getBudgetCategoryTypeCode()).set(budgetPeriod.getBudgetPeriod() - 1, ((BudgetDecimal) (budgetDocument.getBudgetSummaryTotals().get(budgetCategoryType.getBudgetCategoryTypeCode()).get(budgetPeriod.getBudgetPeriod() - 1))).add(objectCodePeriodTotals.get(budgetPeriod.getBudgetPeriod() - 1)));
                    }
                }
            }
        }
    }
    
    private List<CostElement> filterObjectCodesByBudgetCategoryType(Set<CostElement> objectCodes, String budgetCategoryType) {
        List<CostElement> filteredObjectCodes = new ArrayList<CostElement>();
        for(CostElement costElement : objectCodes) {
            if(costElement.getBudgetCategory().getBudgetCategoryTypeCode().equalsIgnoreCase(budgetCategoryType)) {
                filteredObjectCodes.add(costElement);
            }
        }
        
        return filteredObjectCodes;
    }
    
    private SortedMap<RateType, List<BudgetDecimal>> calculateExpenseTotals(BudgetDocument budgetDocument, boolean personnelFlag){
        SortedMap<RateType, List<BudgetDecimal>> calculatedExpenseTotals = new TreeMap <RateType, List<BudgetDecimal>> ();
        
        List <BudgetDecimal> calculatedDirectCostSummaryTotals = new ArrayList<BudgetDecimal>();
        for (int i = 0; i < budgetDocument.getBudgetPeriods().size(); i++) {
            calculatedDirectCostSummaryTotals.add(i,BudgetDecimal.ZERO);
        }
        String totalsMapKey = null;
        if(personnelFlag) {
            totalsMapKey = "personnelCalculatedExpenseSummaryTotals";
        } else {
            totalsMapKey = "nonPersonnelCalculatedExpenseSummaryTotals"; 
        }
        budgetDocument.getBudgetSummaryTotals().put(totalsMapKey, calculatedDirectCostSummaryTotals);
        
        for (BudgetPeriod budgetPeriod: budgetDocument.getBudgetPeriods()) {
            for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
                if((personnelFlag && StringUtils.equals(budgetLineItem.getCostElementBO().getBudgetCategory().getBudgetCategoryTypeCode(), "P")) ||  
                        (!personnelFlag && !StringUtils.equals(budgetLineItem.getCostElementBO().getBudgetCategory().getBudgetCategoryTypeCode(), "P"))  ) {
                    // get calculated expenses                        
                    QueryList lineItemCalcAmtQueryList = new QueryList();
                    lineItemCalcAmtQueryList.addAll(budgetLineItem.getBudgetLineItemCalculatedAmounts());
                    List <RateType> rateTypes = new ArrayList<RateType>();
    
                    for ( Object item : budgetLineItem.getBudgetLineItemCalculatedAmounts()) {
                        BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmount = (BudgetLineItemCalculatedAmount) item;
                        if (budgetLineItemCalculatedAmount.getRateType() == null) {
                            budgetLineItemCalculatedAmount.refreshReferenceObject("rateType");
                        }
                        if (budgetLineItemCalculatedAmount.getRateType() != null && budgetLineItemCalculatedAmount.getRateType().getRateClass() == null) {
                            budgetLineItemCalculatedAmount.getRateType().refreshReferenceObject("rateClass");
                        }
                        RateType rateType = budgetLineItemCalculatedAmount.getRateType();
                        RateClass rateClass = null;
                        if(rateType != null) {
                            budgetLineItemCalculatedAmount.getRateType().refreshReferenceObject("rateClass");
                            rateClass = budgetLineItemCalculatedAmount.getRateType().getRateClass();
                        }
                        
                        if (((personnelFlag && rateClass != null && !StringUtils.equals(rateClass.getRateClassType(), "E")) || !personnelFlag) && !rateTypes.contains(rateType)) {
                            rateTypes.add(rateType);
                            Equals equalsRC = new Equals("rateClassCode", budgetLineItemCalculatedAmount.getRateClassCode());
                            Equals equalsRT = new Equals("rateTypeCode", budgetLineItemCalculatedAmount.getRateTypeCode());
                            And RCandRT = new And(equalsRC, equalsRT);
                            BudgetDecimal rateTypeTotalInThisPeriod = lineItemCalcAmtQueryList.sumObjects("calculatedCost", RCandRT);
                            if (!calculatedExpenseTotals.containsKey(rateType)) {
                                List <BudgetDecimal> rateTypePeriodTotals = new ArrayList<BudgetDecimal>();
                                for (int i = 0; i < budgetDocument.getBudgetPeriods().size(); i++) {
                                    rateTypePeriodTotals.add(i,BudgetDecimal.ZERO);
                                }
                                calculatedExpenseTotals.put(budgetLineItemCalculatedAmount.getRateType(), rateTypePeriodTotals);
                            }
                            calculatedExpenseTotals.get(rateType).set(budgetPeriod.getBudgetPeriod() - 1,((BudgetDecimal)calculatedExpenseTotals.get(rateType).get(budgetPeriod.getBudgetPeriod() - 1)).add(rateTypeTotalInThisPeriod));
                            
                            if(!StringUtils.equals(rateClass.getRateClassType(), "O")) {
                                budgetDocument.getBudgetSummaryTotals().get(totalsMapKey).set(budgetPeriod.getBudgetPeriod() - 1, ((BudgetDecimal) (budgetDocument.getBudgetSummaryTotals().get(totalsMapKey).get(budgetPeriod.getBudgetPeriod() - 1))).add(rateTypeTotalInThisPeriod));
                            }
                            
                            budgetPeriod.setExpenseTotal(budgetPeriod.getExpenseTotal().add(rateTypeTotalInThisPeriod));
                       }
                    }
                }

            }
        }
        
        return calculatedExpenseTotals;
    }

    /**
     * 
     * @see org.kuali.kra.budget.service.BudgetCalculationService#calculateBudgetTotals(org.kuali.kra.budget.document.BudgetDocument)
     */
    @SuppressWarnings("unchecked")
    public void calculateBudgetTotals(BudgetDocument budgetDocument){
        // do we need to cache the totals ?
        SortedMap<CostElement, List<BudgetDecimal>> objectCodeTotals = new TreeMap <CostElement, List<BudgetDecimal>> ();
        SortedMap<RateType, List<BudgetDecimal>> calculatedExpenseTotals = new TreeMap <RateType, List<BudgetDecimal>> ();
        for (BudgetPeriod budgetPeriod: budgetDocument.getBudgetPeriods()) {
            List <CostElement> objectCodes = new ArrayList<CostElement>();
            QueryList lineItemQueryList = new QueryList();
            lineItemQueryList.addAll(budgetPeriod.getBudgetLineItems());
            budgetPeriod.setExpenseTotal(BudgetDecimal.ZERO);
            // probably need to add '0' to the period that has no such object code or ratetype ?
            for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
                if (budgetLineItem.getCostElementBO() == null) {
                    budgetLineItem.refreshReferenceObject("costElementBO");
                }
                CostElement costElement = budgetLineItem.getCostElementBO();
                if (!objectCodes.contains(costElement)) {
                    objectCodes.add(costElement);
                    Equals equalsCostElement = new Equals("costElement", budgetLineItem.getCostElement());
                    BudgetDecimal objectCodeTotalInThisPeriod = lineItemQueryList.sumObjects("lineItemCost", equalsCostElement);
                    if (!objectCodeTotals.containsKey(costElement)) {
                        // set up for all periods and put into map
                        List <BudgetDecimal> periodTotals = new ArrayList<BudgetDecimal>();
                        for (int i = 0; i < budgetDocument.getBudgetPeriods().size(); i++) {
                            periodTotals.add(i,BudgetDecimal.ZERO);
                        }
                        objectCodeTotals.put(costElement, periodTotals);
                    }
                    objectCodeTotals.get(costElement).set(budgetPeriod.getBudgetPeriod() - 1, objectCodeTotalInThisPeriod);
                    budgetPeriod.setExpenseTotal(budgetPeriod.getExpenseTotal().add(objectCodeTotalInThisPeriod));
                }
                // get calculated expenses
                QueryList lineItemCalcAmtQueryList = new QueryList();
                lineItemCalcAmtQueryList.addAll(budgetLineItem.getBudgetLineItemCalculatedAmounts());
                List <RateType> rateTypes = new ArrayList<RateType>();

                for ( Object item : budgetLineItem.getBudgetLineItemCalculatedAmounts()) {
                    BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmount = (BudgetLineItemCalculatedAmount) item;
                    if (budgetLineItemCalculatedAmount.getRateType() == null) {
                        budgetLineItemCalculatedAmount.refreshReferenceObject("rateType");
                    }
                    RateType rateType = budgetLineItemCalculatedAmount.getRateType();
                    if (!rateTypes.contains(rateType)) {
                        rateTypes.add(rateType);
                        Equals equalsRC = new Equals("rateClassCode", budgetLineItemCalculatedAmount.getRateClassCode());
                        Equals equalsRT = new Equals("rateTypeCode", budgetLineItemCalculatedAmount.getRateTypeCode());
                        And RCandRT = new And(equalsRC, equalsRT);
                        BudgetDecimal rateTypeTotalInThisPeriod = lineItemCalcAmtQueryList.sumObjects("calculatedCost", RCandRT);
                        if (!calculatedExpenseTotals.containsKey(rateType)) {
                            List <BudgetDecimal> rateTypePeriodTotals = new ArrayList<BudgetDecimal>();
                            for (int i = 0; i < budgetDocument.getBudgetPeriods().size(); i++) {
                                rateTypePeriodTotals.add(i,BudgetDecimal.ZERO);
                            }
                            calculatedExpenseTotals.put(budgetLineItemCalculatedAmount.getRateType(), rateTypePeriodTotals);
                        }
                        calculatedExpenseTotals.get(rateType).set(budgetPeriod.getBudgetPeriod() - 1,((BudgetDecimal)calculatedExpenseTotals.get(rateType).get(budgetPeriod.getBudgetPeriod() - 1)).add(rateTypeTotalInThisPeriod));
                        budgetPeriod.setExpenseTotal(budgetPeriod.getExpenseTotal().add(rateTypeTotalInThisPeriod));
                   }
                }
            }
        }
        budgetDocument.setObjectCodeTotals(objectCodeTotals);
        budgetDocument.setCalculatedExpenseTotals(calculatedExpenseTotals);

    }

    public void syncToPeriodCostLimit(BudgetDocument budgetDocument, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem) {
        BudgetPeriodCalculator periodCalculator = new BudgetPeriodCalculator();
        periodCalculator.syncToPeriodCostLimit(budgetDocument, budgetPeriod, budgetLineItem);
        List<String> errors = periodCalculator.getErrorMessages();
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        if(!errors.isEmpty()){
            for (String error : errors) {
                errorMap.putError("document.budgetPeriod[" + (budgetPeriod.getBudgetPeriod() - 1) + "].budgetLineItem["+ (budgetLineItem.getLineItemNumber() - 1) +"].lineItemCost", error);
            }
        }
    }

    public void applyToLaterPeriods(BudgetDocument budgetDocument, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem) {
        BudgetPeriodCalculator periodCalculator = new BudgetPeriodCalculator();
        periodCalculator.applyToLaterPeriods(budgetDocument, budgetPeriod, budgetLineItem);
        List<String> errors = periodCalculator.getErrorMessages();
        if(!errors.isEmpty()){
            ErrorMap errorMap = GlobalVariables.getErrorMap();
            for (String error : errors) {
                errorMap.putError("document.budgetPeriod[" + (budgetPeriod.getBudgetPeriod() - 1) + "].budgetLineItem["+ (budgetLineItem.getLineItemNumber() - 1) +"].costElement",error);
            }
        }
    }
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * Gets the budgetDistributionAndIncomeService attribute. 
     * @return Returns the budgetDistributionAndIncomeService.
     */
    public BudgetDistributionAndIncomeService getBudgetDistributionAndIncomeService() {
        return this.budgetDistributionAndIncomeService;
    }
    
    /**
     * Sets the budgetDistributionAndIncomeService attribute value.
     * @param budgetDistributionAndIncomeService The budgetDistributionAndIncomeService to set.
     */
    public void setBudgetDistributionAndIncomeService(BudgetDistributionAndIncomeService service) {
        this.budgetDistributionAndIncomeService = service;
    }
    
    public void rePopulateCalculatedAmount(BudgetDocument budgetDocument, BudgetLineItem budgetLineItem) {
        budgetLineItem.getBudgetCalculatedAmounts().clear();
        new LineItemCalculator(budgetDocument,budgetLineItem).setCalculatedAmounts(budgetDocument, budgetLineItem);
    }
    public void rePopulateCalculatedAmount(BudgetDocument budgetDocument, BudgetPersonnelDetails newBudgetPersonnelDetails) {
        newBudgetPersonnelDetails.getBudgetCalculatedAmounts().clear();
        new PersonnelLineItemCalculator(budgetDocument,newBudgetPersonnelDetails).setCalculatedAmounts(budgetDocument, newBudgetPersonnelDetails);
    }
    
    public void updatePersonnelBudgetRate(BudgetLineItem budgetLineItem){
        int j = 0;
        for(BudgetPersonnelDetails budgetPersonnelDetails: budgetLineItem.getBudgetPersonnelDetailsList()){
            j=0;
            for(BudgetPersonnelCalculatedAmount budgetPersonnelCalculatedAmount:budgetPersonnelDetails.getBudgetPersonnelCalculatedAmounts()){
                Boolean updatedApplyRateFlag = null;
                
                for (BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmout : budgetLineItem.getBudgetLineItemCalculatedAmounts()) {
                    if(budgetLineItemCalculatedAmout.getRateClassCode().equalsIgnoreCase(budgetPersonnelCalculatedAmount.getRateClassCode()) && 
                            budgetLineItemCalculatedAmout.getRateTypeCode().equalsIgnoreCase(budgetPersonnelCalculatedAmount.getRateTypeCode())) {
                        updatedApplyRateFlag = budgetLineItemCalculatedAmout.getApplyRateFlag();
                    }
                }
                budgetPersonnelCalculatedAmount.setApplyRateFlag(updatedApplyRateFlag);                        
                j++;
            }
        }
    }

}
