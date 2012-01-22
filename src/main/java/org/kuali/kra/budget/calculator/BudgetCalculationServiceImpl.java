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
package org.kuali.kra.budget.calculator;

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
import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.budget.AwardBudgetLineItemCalculatedAmountExt;
import org.kuali.kra.award.budget.AwardBudgetLineItemExt;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.query.And;
import org.kuali.kra.budget.calculator.query.Equals;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetCategoryType;
import org.kuali.kra.budget.core.BudgetCommonService;
import org.kuali.kra.budget.core.BudgetCommonServiceFactory;
import org.kuali.kra.budget.core.BudgetParent;
import org.kuali.kra.budget.core.CostElement;
import org.kuali.kra.budget.distributionincome.BudgetDistributionAndIncomeService;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.nonpersonnel.BudgetRateAndBase;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.personnel.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.budget.personnel.BudgetPersonnelRateAndBase;
import org.kuali.kra.budget.rates.RateClass;
import org.kuali.kra.budget.rates.RateType;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.hierarchy.HierarchyStatusConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;


/**
 * This class implements all methods declared in BudgetCalculationService
 */
public class BudgetCalculationServiceImpl implements BudgetCalculationService {
    
    private static final String EQUIPMENT = "E";
    private static final String TRAVEL = "T";
    private static final String PARTICIPANT_SUPPORT = "S";
    private static final String OTHER_DIRECT = "O";
    private static final String CALCULATED_COST = "CalculatedCost";
    private static final String INDIRECT_COST = "IndirectCost";
    private static final String SALARY = "Salary";
    private static final String FRINGE = "Fringe";
    private BusinessObjectService businessObjectService;
    private BudgetDistributionAndIncomeService budgetDistributionAndIncomeService;
    
    /**
     * @see org.kuali.kra.budget.calculator.BudgetCalculationService#calculateBudget(java.lang.String, java.lang.Integer)
     */
    public void calculateBudget(Budget budget){
        List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();
        String ohRateClassCodePrevValue = null;
        
        BudgetForm form = getBudgetFormFromGlobalVariables();

        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            if(isCalculationRequired(budget,budgetPeriod)){
                String workOhCode = null;
                if(budget.getOhRateClassCode()!=null && form!=null && budget.getBudgetPeriods().size() > budgetPeriod.getBudgetPeriod()){
                    workOhCode = form.getOhRateClassCodePrevValue();
                }
                calculateBudgetPeriod(budget, budgetPeriod);
                if(budget.getOhRateClassCode()!=null && form!=null && budget.getBudgetPeriods().size() > budgetPeriod.getBudgetPeriod()){
                        // this should be set at the last period, otherwise, only the first period will be updated properly because lots of places check prevohrateclass
                    ohRateClassCodePrevValue = form.getOhRateClassCodePrevValue();
                    form.setOhRateClassCodePrevValue(workOhCode);
                }
            }
        }
        if (form!=null && form.getOhRateClassCodePrevValue() == null && ohRateClassCodePrevValue != null) {
            // if not all periods are calculated, then this code has potential to be null, and this will force
            // to create calamts again
            form.setOhRateClassCodePrevValue(ohRateClassCodePrevValue);            
        }
        if(budgetPeriods!=null && !budgetPeriods.isEmpty()){
            syncCostsToBudget(budget);
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
    protected boolean isCalculationRequired(Budget budget, final BudgetPeriod budgetPeriod){
        assert budgetPeriod != null : "The budget period is null";
        boolean budgetLineItemDeleted = budget.isBudgetLineItemDeleted();
        if(getBudgetCommonService(budget).isRateOverridden(budgetPeriod)){
            return false;
        }
        if (StringUtils.equals(budgetPeriod.getBudget().getBudgetParent().getHierarchyStatus(), 
                HierarchyStatusConstants.Parent.code())) {
            return true;
        }
        final boolean isLineItemsEmpty = budgetPeriod.getBudgetLineItems().isEmpty();
        
        if(isLineItemsEmpty && !budgetLineItemDeleted){
            final Map<String, Object> fieldValues = new HashMap<String, Object>();

            fieldValues.put("budgetId", budgetPeriod.getBudgetId());
            fieldValues.put("budgetPeriod", budgetPeriod.getBudgetPeriod());
            
            @SuppressWarnings("unchecked")
            final Collection<BudgetLineItem> deletedLineItems
                = this.businessObjectService.findMatching(BudgetLineItem.class, fieldValues);
            return !deletedLineItems.isEmpty();
        }
            
        return true;
    }
    protected void copyLineItemToPersonnelDetails(BudgetLineItem budgetLineItem, BudgetPersonnelDetails budgetPersonnelDetails) {
        budgetPersonnelDetails.setBudgetId(budgetLineItem.getBudgetId());
        budgetPersonnelDetails.setBudgetPeriod(budgetLineItem.getBudgetPeriod());
        budgetPersonnelDetails.setLineItemNumber(budgetLineItem.getLineItemNumber());
        budgetPersonnelDetails.setCostElement(budgetLineItem.getCostElement());
        budgetPersonnelDetails.setCostElementBO(budgetLineItem.getCostElementBO());
        budgetPersonnelDetails.setApplyInRateFlag(budgetLineItem.getApplyInRateFlag());
        budgetPersonnelDetails.setOnOffCampusFlag(budgetLineItem.getOnOffCampusFlag());
    }

    public void calculateBudgetLineItem(Budget budget,BudgetPersonnelDetails budgetLineItem){
        new PersonnelLineItemCalculator(budget,budgetLineItem).calculate();
    }
    /**
     * @see org.kuali.kra.budget.calculator.BudgetCalculationService#calculateBudgetLineItem(org.kuali.kra.budget.nonpersonnel.BudgetLineItem)
     */
    public void calculateBudgetLineItem(Budget budget,BudgetLineItem budgetLineItem){
        BudgetLineItem budgetLineItemToCalc = budgetLineItem;
        List<BudgetPersonnelDetails> budgetPersonnelDetList = budgetLineItemToCalc.getBudgetPersonnelDetailsList();
        if(budgetLineItemToCalc.isBudgetPersonnelLineItemDeleted() || (budgetPersonnelDetList!=null && !budgetPersonnelDetList.isEmpty())){
            updatePersonnelBudgetRate(budgetLineItemToCalc);
            BudgetDecimal personnelLineItemTotal  = BudgetDecimal.ZERO;
            BudgetDecimal personnelTotalCostSharing  = BudgetDecimal.ZERO;
            Map<String, BudgetDecimal> totalCalculatedCost = new HashMap<String, BudgetDecimal> ();
            Map<String, BudgetDecimal> totalCalculatedCostSharing = new HashMap<String, BudgetDecimal> ();
            BudgetDecimal newTotalUrAmount = BudgetDecimal.ZERO;
            budgetLineItem.getBudgetRateAndBaseList().clear();
            int rateNumber = 0;
            boolean resetTotalUnderRecovery = false;
            BudgetDecimal calcDirectCost = BudgetDecimal.ZERO;
            BudgetDecimal calcIndirectCost = BudgetDecimal.ZERO;
            BudgetDecimal calcTotalCostSharing = BudgetDecimal.ZERO;
            for (BudgetPersonnelDetails budgetPersonnelDetails : budgetPersonnelDetList) {
                copyLineItemToPersonnelDetails(budgetLineItemToCalc, budgetPersonnelDetails);
                new PersonnelLineItemCalculator(budget,budgetPersonnelDetails).calculate();
                personnelLineItemTotal = personnelLineItemTotal.add(budgetPersonnelDetails.getLineItemCost());
                personnelTotalCostSharing = personnelTotalCostSharing.add(budgetPersonnelDetails.getCostSharingAmount());
                newTotalUrAmount = newTotalUrAmount.add(budgetPersonnelDetails.getUnderrecoveryAmount());
                resetTotalUnderRecovery = true;
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
                        
                        if (personnelCalAmt.getRateClass() == null) {
                            personnelCalAmt.refreshReferenceObject("rateClass");
                        }
                        if (!personnelCalAmt.getRateClass().getRateClassType().equals("O")) {
                            calcDirectCost = calcDirectCost.add(personnelCalAmt.getCalculatedCost());
                        } else {
                            calcIndirectCost = calcIndirectCost.add(personnelCalAmt.getCalculatedCost());
                            
                        }
                        calcTotalCostSharing = calcTotalCostSharing.add(personnelCalAmt.getCalculatedCostSharing());
                        
                        
                    }
                }
                populateRateAndBase(budgetLineItem,budgetPersonnelDetails,rateNumber);
            }
            if (resetTotalUnderRecovery) {
                budgetLineItem.setUnderrecoveryAmount(newTotalUrAmount);
            }
            budgetLineItem.setLineItemCost(personnelLineItemTotal);
            budgetLineItem.setCostSharingAmount(personnelTotalCostSharing);
            budgetLineItem.setDirectCost(calcDirectCost.add(personnelLineItemTotal));
            budgetLineItem.setTotalCostSharingAmount(calcTotalCostSharing.add(personnelTotalCostSharing));
            budgetLineItem.setIndirectCost(calcIndirectCost);

            boolean lineItemCalcAmntsOutOfDate = false;
            if (budgetLineItem.getBudgetCalculatedAmounts().size() == totalCalculatedCost.size()) {
                for (BudgetLineItemCalculatedAmount lineItemCalAmt : budgetLineItem.getBudgetLineItemCalculatedAmounts()) {
                    String rateKey = lineItemCalAmt.getRateClassCode()+":"+lineItemCalAmt.getRateTypeCode();
                    if (!totalCalculatedCost.containsKey(rateKey)) {
                        lineItemCalcAmntsOutOfDate = true;
                        break;
                    }
                }
            } else {
                lineItemCalcAmntsOutOfDate = true;
            }
            if (lineItemCalcAmntsOutOfDate) {
                rePopulateCalculatedAmount(budget, budgetLineItemToCalc);
            }
            
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
            new LineItemCalculator(budget,budgetLineItem).calculate();
        }
    }
    protected void populateRateAndBase(BudgetLineItem bli, BudgetPersonnelDetails budgetPersonnelDetails,int rateNumber) {
        List<BudgetRateAndBase> budgetRateAndBaseList = bli.getBudgetRateAndBaseList();
        List<BudgetPersonnelRateAndBase> budgetPersonnelRateBaseList = budgetPersonnelDetails.getBudgetPersonnelRateAndBaseList();
        for (BudgetPersonnelRateAndBase budgetPersonnelRateAndBase : budgetPersonnelRateBaseList) {
            BudgetRateAndBase budgetRateBase = new BudgetRateAndBase();
            BudgetDecimal appliedRate = budgetPersonnelRateAndBase.getAppliedRate();
            budgetRateBase.setAppliedRate(BudgetDecimal.returnZeroIfNull(appliedRate));
            BudgetDecimal calculatedCost = budgetPersonnelRateAndBase.getCalculatedCost();
            BudgetDecimal calculatedCostSharing = budgetPersonnelRateAndBase.getCalculatedCostSharing();
            budgetRateBase.setBaseCostSharing(budgetPersonnelRateAndBase.getBaseCostSharing());
            budgetRateBase.setBaseCost(budgetPersonnelRateAndBase.getSalaryRequested());
            
            budgetRateBase.setBudgetPeriodId(budgetPersonnelRateAndBase.getBudgetPeriodId());
            budgetRateBase.setBudgetPeriod(budgetPersonnelRateAndBase.getBudgetPeriod());
            budgetRateBase.setCalculatedCost(calculatedCost);
            budgetRateBase.setCalculatedCostSharing(calculatedCostSharing);
            
            budgetRateBase.setEndDate(budgetPersonnelRateAndBase.getEndDate());
            budgetRateBase.setLineItemNumber(budgetPersonnelRateAndBase.getLineItemNumber());
            budgetRateBase.setOnOffCampusFlag(budgetPersonnelRateAndBase.getOnOffCampusFlag());
            budgetRateBase.setBudgetId(budgetPersonnelRateAndBase.getBudgetId());
            budgetRateBase.setRateClassCode(budgetPersonnelRateAndBase.getRateClassCode());
            budgetRateBase.setRateNumber(++rateNumber);
            budgetRateBase.setRateTypeCode(budgetPersonnelRateAndBase.getRateTypeCode());
            budgetRateBase.setStartDate(budgetPersonnelRateAndBase.getStartDate());
            budgetRateAndBaseList.add(budgetRateBase);
        }   

    }
    public void calculateAndSyncBudgetLineItem(Budget budget,BudgetLineItem budgetLineItem){
        new LineItemCalculator(budget,budgetLineItem).calculate();
        syncCostsToBudget(budget);
    }

    /**
     * @see org.kuali.kra.budget.calculator.BudgetCalculationService#calculateCalculatedAmount(org.kuali.kra.budget.nonpersonnel.BudgetLineItem)
     */
    public void populateCalculatedAmount(Budget budget,BudgetLineItem budgetLineItem){
        new LineItemCalculator(budget,budgetLineItem).populateCalculatedAmountLineItems();
    }
    /**
     * @see org.kuali.kra.budget.calculator.BudgetCalculationService#calculateCalculatedAmount(org.kuali.kra.budget.nonpersonnel.BudgetLineItem)
     */
    public void populateCalculatedAmount(Budget budget,BudgetPersonnelDetails budgetPersonnelDetails){
        new PersonnelLineItemCalculator(budget,budgetPersonnelDetails).populateCalculatedAmountLineItems();
    }

    /**
     * @see org.kuali.kra.budget.calculator.BudgetCalculationService#calculateSalary(org.kuali.kra.budget.personnel.BudgetPersonnelDetails)
     */
    public void calculateSalary(Budget budget,BudgetPersonnelDetails budgetPersonnelLineItem){
        new SalaryCalculator(budget,budgetPersonnelLineItem).calculate();
    }

    public void calculateBudgetPeriod(Budget budget, BudgetPeriod budgetPeriod){
        if (isCalculationRequired(budget, budgetPeriod)){
//            if(deleteSummaryCalcAmtsFlag){
//                getBudgetCommonService(budget).removeBudgetSummaryPeriodCalcAmounts(budgetPeriod);
//            }
            new BudgetPeriodCalculator().calculate(budget, budgetPeriod);
        }
    }
    
    public void calculateAndSyncBudgetPeriod(Budget budget, BudgetPeriod budgetPeriod){
        calculateBudgetPeriod(budget, budgetPeriod);
        syncCostsToBudget(budget);
    }

    /**
     * Syncs the calculated costs in the budget document with the calculated costs in the budget
     * periods. If the certain costs are not positive then lists on items related to those costs 
     * are also cleared and reset (i.e. UnrecoveredFandAs).
     * This method modifies the passed in Budget.
     * 
     * @param budget the budget document
     */
    protected void syncCostsToBudget(final Budget budget){
        assert budget != null : "The budget was null";
        
        this.initCostDependentItems(budget);
        this.ensureBudgetPeriodHasSyncedCosts(budget);
        this.setBudgetCostsFromPeriods(budget);
    }
    
    /**
     * Initializes items that are dependent on a cost value. (i.e. UnrecoveredFandAs)
     * This method modifies the passed in Budget.
     * 
     * @param budget the budget document
     */
    protected void initCostDependentItems(final Budget budget) {
        assert budget != null : "The budget was null";
        
        if (!this.isPositiveTotalUnderreoveryAmount(budget)) {
            this.initUnrecoveredFandAs(budget);
        }
        
        if (!this.isPositiveTotalCostSharingAmount(budget)) {
            this.initCostSharing(budget);
        }
    }
    
    /**
     * Clears and initializes the UnrecoveredFandAs in a budget document.
     * This method modifies the passed in Budget.
     * 
     * @param document the budget document.
     */
    protected void initUnrecoveredFandAs(final Budget document) {
        assert document != null : "the document was null";
        
        document.getBudgetUnrecoveredFandAs().clear();
        this.getBudgetDistributionAndIncomeService().initializeUnrecoveredFandACollectionDefaults(document);
    }
    
    /**
     * Clears and initializes the CostSharing in a budget document.
     * This method modifies the passed in Budget.
     * 
     * @param document the budget document.
     */
    protected void initCostSharing(final Budget document) {
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
    protected void ensureBudgetPeriodHasSyncedCosts(final Budget budget) {
        assert budget != null : "the document was null";
        
        for (final BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
            if (this.isCalculationRequired(budget, budgetPeriod)) {
                this.setBudgetPeriodCostsFromLineItems(budgetPeriod);
            }
        }
    }
    
    /**
     * 
     * This method sets the budget document's costs from the budget periods' costs.
     * This method modifies the passed in budget document.
     * 
     * @param budget the budget document to set the costs on.
     */
    protected void setBudgetCostsFromPeriods(final Budget budget) {
        assert budget != null : "The document is null";

        budget.setTotalDirectCost(budget.getSumDirectCostAmountFromPeriods());
        budget.setTotalIndirectCost(budget.getSumIndirectCostAmountFromPeriods());
        budget.setTotalCost(budget.getSumTotalCostAmountFromPeriods());
        budget.setUnderrecoveryAmount(budget.getSumUnderreoveryAmountFromPeriods());
        budget.setCostSharingAmount(budget.getSumCostSharingAmountFromPeriods());
    }
    
    /**
     * 
     * This method sets the budget period costs from the line item costs.
     * This method modifies the passed in budget period.
     * 
     * @param budgetPeriod the budget periods to set the costs on.
     */
    protected void setBudgetPeriodCostsFromLineItems(final BudgetPeriod budgetPeriod) {
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
    protected final boolean isPositiveTotalUnderreoveryAmount(final Budget document) {
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
    protected final boolean isPositiveTotalCostSharingAmount(final Budget document) {
        assert document != null : "The document is null";
        
        BudgetDecimal lineItemsAmount = BudgetDecimal.ZERO;
        
        for (final BudgetPeriod budgetPeriod : document.getBudgetPeriods()) {
            lineItemsAmount = lineItemsAmount.add(budgetPeriod.getSumTotalCostSharingAmountFromLineItems());
        }        
        
        return lineItemsAmount.isPositive()
            || document.getSumCostSharingAmountFromPeriods().isPositive();
    }

    protected SortedMap<BudgetCategoryType, List<CostElement>> categorizeObjectCodesByCategory(Budget budget) {
        SortedMap<CostElement, List<BudgetDecimal>> objectCodeTotals = budget.getObjectCodeTotals();
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
    
    protected BudgetCategoryType getPersonnelCategoryType() {
        final Map<String, String> primaryKeys = new HashMap<String, String>();
        primaryKeys.put("budgetCategoryTypeCode", "P");
        return (BudgetCategoryType) this.getBusinessObjectService().findByPrimaryKey(BudgetCategoryType.class, primaryKeys);
    }
    
    public void calculateBudgetSummaryTotals(Budget budget){
        calculateBudgetTotals(budget);

        //Categorize all Object Codes per their Category Type
        SortedMap<BudgetCategoryType, List<CostElement>> objectCodeListByBudgetCategoryType = categorizeObjectCodesByCategory(budget);   
       
        SortedMap<CostElement, List<BudgetPersonnelDetails>> objectCodeUniquePersonnelList = new TreeMap<CostElement, List<BudgetPersonnelDetails>>();
        
        SortedMap<String, List<BudgetDecimal>> objectCodePersonnelSalaryTotals = new TreeMap<String, List<BudgetDecimal>>();
        SortedMap<String, List<BudgetDecimal>> objectCodePersonnelFringeTotals = new TreeMap<String, List<BudgetDecimal>>();
        
        //Temp collections for maintaining Sub Section Totals
        SortedSet<String> objectCodePersonnelSalaryTotalsByPeriod = new TreeSet<String>();
        SortedSet<String> objectCodePersonnelFringeTotalsByPeriod = new TreeSet<String>();

        SortedMap<RateType, List<BudgetDecimal>> personnelCalculatedExpenseTotals = new TreeMap<RateType, List<BudgetDecimal>>();
        SortedMap<RateType, List<BudgetDecimal>> nonPersonnelCalculatedExpenseTotals = new TreeMap<RateType, List<BudgetDecimal>>(); 

        List <BudgetDecimal> periodSummarySalaryTotals = new ArrayList<BudgetDecimal>();
        for (int i = 0; i < budget.getBudgetPeriods().size(); i++) {
            periodSummarySalaryTotals.add(i,BudgetDecimal.ZERO);
        }
        List <BudgetDecimal> periodSummaryFringeTotals = new ArrayList<BudgetDecimal>();
        for (int i = 0; i < budget.getBudgetPeriods().size(); i++) {
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
                
                for (BudgetPeriod budgetPeriod: budget.getBudgetPeriods()) {
                    budgetPeriod.setBudget(budget);
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
                                for (int i = 0; i < budget.getBudgetPeriods().size(); i++) {
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
                            if (!objectCodePersonnelFringeTotals.containsKey(matchingLineItem.getCostElement()+","+budgetPersonnelDetails.getPersonId())) {
                                // set up for all periods and put into map
                                List <BudgetDecimal> periodFringeTotals = new ArrayList<BudgetDecimal>();
                                for (int i = 0; i < budget.getBudgetPeriods().size(); i++) {
                                    periodFringeTotals.add(i,BudgetDecimal.ZERO);
                                }
                                objectCodePersonnelFringeTotals.put(matchingLineItem.getCostElement()+","+budgetPersonnelDetails.getPersonId(), periodFringeTotals); 
                            }   
                            BudgetDecimal personFringeTotalsForCurrentPeriod = BudgetDecimal.ZERO;
//                            if(!isSummaryCalcAmountChanged(budget,budgetPeriod)){
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
//                            }
                            
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
                                for (int i = 0; i < budget.getBudgetPeriods().size(); i++) {
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
                                for (int i = 0; i < budget.getBudgetPeriods().size(); i++) {
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
        
        budget.setBudgetSummaryTotals(subTotalsBySubSection);
        personnelCalculatedExpenseTotals = calculateExpenseTotals(budget, true);
        nonPersonnelCalculatedExpenseTotals = calculateExpenseTotals(budget, false);
        
        budget.setObjectCodeListByBudgetCategoryType(objectCodeListByBudgetCategoryType);
        //budget.setObjectCodePersonnelList(objectCodePersonnelList);
        budget.setObjectCodePersonnelList(objectCodeUniquePersonnelList);
        budget.setObjectCodePersonnelSalaryTotals(objectCodePersonnelSalaryTotals);
        budget.setObjectCodePersonnelFringeTotals(objectCodePersonnelFringeTotals);
        budget.setPersonnelCalculatedExpenseTotals(personnelCalculatedExpenseTotals);
        budget.setNonPersonnelCalculatedExpenseTotals(nonPersonnelCalculatedExpenseTotals);
        calculateNonPersonnelSummaryTotals(budget);
        populateBudgetPeriodSummaryCalcAmounts(budget);
    }  
    @SuppressWarnings("unchecked")
    protected BudgetCommonService<BudgetParent> getBudgetCommonService(Budget budget) {
        return BudgetCommonServiceFactory.createInstance(budget.getBudgetDocument().getParentDocument());
    }
    private boolean isRateOveridden(Budget budget,BudgetPeriod budgetPeriod){
        BudgetCommonService<BudgetParent> budgetService = getBudgetCommonService(budget);
        return budgetService.isRateOverridden(budgetPeriod);
    }
    private void populateBudgetPeriodSummaryCalcAmounts(Budget budget) {
        List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            if(!isRateOveridden(budget,budgetPeriod)){
                getBudgetCommonService(budget).populateSummaryCalcAmounts(budget,budgetPeriod);
            }
        }
    }
    protected void calculateNonPersonnelSummaryTotals(Budget budget) {
        for(BudgetCategoryType budgetCategoryType : budget.getObjectCodeListByBudgetCategoryType().keySet()) {
            if(!StringUtils.equals(budgetCategoryType.getBudgetCategoryTypeCode(), "P")) {
                List <BudgetDecimal> nonPersonnelTotals = new ArrayList<BudgetDecimal>();
                for (int i = 0; i < budget.getBudgetPeriods().size(); i++) {
                    nonPersonnelTotals.add(i,BudgetDecimal.ZERO);
                }
                budget.getBudgetSummaryTotals().put(budgetCategoryType.getBudgetCategoryTypeCode(), nonPersonnelTotals);
                
                List<CostElement> objectCodes = budget.getObjectCodeListByBudgetCategoryType().get(budgetCategoryType);
                for(CostElement objectCode : objectCodes) {
                    if (!StringUtils.equalsIgnoreCase(objectCode.getCostElement(), KraServiceLocator.getService(ParameterService.class).getParameterValueAsString(BudgetDocument.class, "proposalHierarchySubProjectIndirectCostElement"))) {
                        List<BudgetDecimal> objectCodePeriodTotals = budget.getObjectCodeTotals().get(objectCode);
                        for(BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
                            budget.getBudgetSummaryTotals().get(budgetCategoryType.getBudgetCategoryTypeCode()).set(budgetPeriod.getBudgetPeriod() - 1, ((BudgetDecimal) (budget.getBudgetSummaryTotals().get(budgetCategoryType.getBudgetCategoryTypeCode()).get(budgetPeriod.getBudgetPeriod() - 1))).add(objectCodePeriodTotals.get(budgetPeriod.getBudgetPeriod() - 1)));
                        }
                    }
                }
            }
        }
    }
    
    protected List<CostElement> filterObjectCodesByBudgetCategoryType(Set<CostElement> objectCodes, String budgetCategoryType) {
        List<CostElement> filteredObjectCodes = new ArrayList<CostElement>();
        for(CostElement costElement : objectCodes) {
            if(costElement.getBudgetCategory().getBudgetCategoryTypeCode().equalsIgnoreCase(budgetCategoryType)) {
                filteredObjectCodes.add(costElement);
            }
        }
        
        return filteredObjectCodes;
    }
    
    protected SortedMap<RateType, List<BudgetDecimal>> calculateExpenseTotals(Budget budget, boolean personnelFlag){
        SortedMap<RateType, List<BudgetDecimal>> calculatedExpenseTotals = new TreeMap <RateType, List<BudgetDecimal>> ();
        
        List <BudgetDecimal> calculatedDirectCostSummaryTotals = new ArrayList<BudgetDecimal>();
        for (int i = 0; i < budget.getBudgetPeriods().size(); i++) {
            calculatedDirectCostSummaryTotals.add(i,BudgetDecimal.ZERO);
        }
        String totalsMapKey = null;
        if(personnelFlag) {
            totalsMapKey = "personnelCalculatedExpenseSummaryTotals";
        } else {
            totalsMapKey = "nonPersonnelCalculatedExpenseSummaryTotals"; 
        }
        budget.getBudgetSummaryTotals().put(totalsMapKey, calculatedDirectCostSummaryTotals);
        
        for (BudgetPeriod budgetPeriod: budget.getBudgetPeriods()) {
            for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
                if((personnelFlag && StringUtils.equals(budgetLineItem.getCostElementBO().getBudgetCategory().getBudgetCategoryTypeCode(), "P")) ||  
                        (!personnelFlag && !StringUtils.equals(budgetLineItem.getCostElementBO().getBudgetCategory().getBudgetCategoryTypeCode(), "P"))  ) {
                    // get calculated expenses                        
                    QueryList lineItemCalcAmtQueryList = new QueryList();
                    lineItemCalcAmtQueryList.addAll(budgetLineItem.getBudgetLineItemCalculatedAmounts());
                    List <RateType> rateTypes = new ArrayList<RateType>();
    
                    for ( Object item : budgetLineItem.getBudgetLineItemCalculatedAmounts()) {
                        BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmount = (BudgetLineItemCalculatedAmount) item;
//                        if (budgetLineItemCalculatedAmount.getRateType() == null) {
//                            budgetLineItemCalculatedAmount.refreshReferenceObject("rateType");
//                        }
//                        if (budgetLineItemCalculatedAmount.getRateType() != null && budgetLineItemCalculatedAmount.getRateType().getRateClass() == null) {
//                            budgetLineItemCalculatedAmount.getRateType().refreshReferenceObject("rateClass");
//                        }
                        RateType rateType = createRateType(budgetLineItemCalculatedAmount);
                        RateClass rateClass = null;
                        if(rateType != null) {
                            rateType.refreshReferenceObject("rateClass");
                            rateClass = rateType.getRateClass();
                        }
                        
                        if (((personnelFlag && rateClass != null && !StringUtils.equals(rateClass.getRateClassType(), "E")) || !personnelFlag) && !rateTypes.contains(rateType)) {
                            rateTypes.add(rateType);
                            Equals equalsRC = new Equals("rateClassCode", budgetLineItemCalculatedAmount.getRateClassCode());
                            Equals equalsRT = new Equals("rateTypeCode", budgetLineItemCalculatedAmount.getRateTypeCode());
                            And RCandRT = new And(equalsRC, equalsRT);
                            BudgetDecimal rateTypeTotalInThisPeriod = lineItemCalcAmtQueryList.sumObjects("calculatedCost", RCandRT);
                            if (!calculatedExpenseTotals.containsKey(rateType)) {
                                List <BudgetDecimal> rateTypePeriodTotals = new ArrayList<BudgetDecimal>();
                                for (int i = 0; i < budget.getBudgetPeriods().size(); i++) {
                                    rateTypePeriodTotals.add(i,BudgetDecimal.ZERO);
                                }
                                calculatedExpenseTotals.put(rateType, rateTypePeriodTotals);
                            }
                            calculatedExpenseTotals.get(rateType).set(budgetPeriod.getBudgetPeriod() - 1,((BudgetDecimal)calculatedExpenseTotals.get(rateType).get(budgetPeriod.getBudgetPeriod() - 1)).add(rateTypeTotalInThisPeriod));
                            
                            if(!StringUtils.equals(rateClass.getRateClassType(), "O")) {
                                budget.getBudgetSummaryTotals().get(totalsMapKey).set(budgetPeriod.getBudgetPeriod() - 1, ((BudgetDecimal) (budget.getBudgetSummaryTotals().get(totalsMapKey).get(budgetPeriod.getBudgetPeriod() - 1))).add(rateTypeTotalInThisPeriod));
                            }
                            
                            budgetPeriod.setExpenseTotal(budgetPeriod.getExpenseTotal().add(rateTypeTotalInThisPeriod));
                       }
                    }
                }

            }
        }
        
        return calculatedExpenseTotals;
    }

    protected RateType createRateType(BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmount) {
        RateType rateType = new RateType();
        rateType.setRateClassCode(budgetLineItemCalculatedAmount.getRateClassCode());
        rateType.setRateTypeCode(budgetLineItemCalculatedAmount.getRateTypeCode());
        rateType.setDescription(budgetLineItemCalculatedAmount.getRateTypeDescription());
        rateType.setRateClass(budgetLineItemCalculatedAmount.getRateClass());
        return rateType;
    }
    /**
     * 
     * @see org.kuali.kra.budget.calculator.BudgetCalculationService#calculateBudgetTotals(org.kuali.kra.budget.core.Budget)
     */
    @SuppressWarnings("unchecked")
    public void calculateBudgetTotals(Budget budget){
        // do we need to cache the totals ?
        SortedMap<CostElement, List<BudgetDecimal>> objectCodeTotals = new TreeMap <CostElement, List<BudgetDecimal>> ();
        SortedMap<RateType, List<BudgetDecimal>> calculatedExpenseTotals = new TreeMap <RateType, List<BudgetDecimal>> ();
        for (BudgetPeriod budgetPeriod: budget.getBudgetPeriods()) {
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
                        for (int i = 0; i < budget.getBudgetPeriods().size(); i++) {
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
//                    if (budgetLineItemCalculatedAmount.getRateType() == null) {
//                        budgetLineItemCalculatedAmount.refreshReferenceObject("rateType");
//                    }
                    RateType rateType = createRateType(budgetLineItemCalculatedAmount);
                    if (!rateTypes.contains(rateType)) {
                        rateTypes.add(rateType);
                        Equals equalsRC = new Equals("rateClassCode", budgetLineItemCalculatedAmount.getRateClassCode());
                        Equals equalsRT = new Equals("rateTypeCode", budgetLineItemCalculatedAmount.getRateTypeCode());
                        And RCandRT = new And(equalsRC, equalsRT);
                        BudgetDecimal rateTypeTotalInThisPeriod = lineItemCalcAmtQueryList.sumObjects("calculatedCost", RCandRT);
                        if (!calculatedExpenseTotals.containsKey(rateType)) {
                            List <BudgetDecimal> rateTypePeriodTotals = new ArrayList<BudgetDecimal>();
                            for (int i = 0; i < budget.getBudgetPeriods().size(); i++) {
                                rateTypePeriodTotals.add(i,BudgetDecimal.ZERO);
                            }
                            calculatedExpenseTotals.put(rateType, rateTypePeriodTotals);
                        }
                        calculatedExpenseTotals.get(rateType).set(budgetPeriod.getBudgetPeriod() - 1,((BudgetDecimal)calculatedExpenseTotals.get(rateType).get(budgetPeriod.getBudgetPeriod() - 1)).add(rateTypeTotalInThisPeriod));
                        budgetPeriod.setExpenseTotal(budgetPeriod.getExpenseTotal().add(rateTypeTotalInThisPeriod));
                   }
                }
            }
        }
        budget.setObjectCodeTotals(objectCodeTotals);
        budget.setCalculatedExpenseTotals(calculatedExpenseTotals);

    }

    public void syncToPeriodCostLimit(Budget budget, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem) {
        BudgetPeriodCalculator periodCalculator = new BudgetPeriodCalculator();
        periodCalculator.syncToPeriodCostLimit(budget, budgetPeriod, budgetLineItem);
        List<String> errors = periodCalculator.getErrorMessages();
        MessageMap errorMap = GlobalVariables.getMessageMap();
        if(!errors.isEmpty()){
            for (String error : errors) {
                errorMap.putError("document.budgetPeriod[" + (budgetPeriod.getBudgetPeriod() - 1) + "].budgetLineItem["+ (budgetLineItem.getLineItemNumber() - 1) +"].lineItemCost", error);
            }
        }
    }

    public void syncToPeriodDirectCostLimit(Budget budget, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem) {
        BudgetPeriodCalculator periodCalculator = new BudgetPeriodCalculator();
        periodCalculator.syncToPeriodDirectCostLimit(budget, budgetPeriod, budgetLineItem);
        List<String> errors = periodCalculator.getErrorMessages();
        MessageMap errorMap = GlobalVariables.getMessageMap();
        if(!errors.isEmpty()){
            for (String error : errors) {
                errorMap.putError("document.budgetPeriod[" + (budgetPeriod.getBudgetPeriod() - 1) + "].budgetLineItem["+ (budgetLineItem.getLineItemNumber() - 1) +"].lineItemCost", error);
            }
        }
    }
    public void applyToLaterPeriods(Budget budget, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem) {
        BudgetPeriodCalculator periodCalculator = new BudgetPeriodCalculator();
        periodCalculator.applyToLaterPeriods(budget, budgetPeriod, budgetLineItem);
        List<String> errors = periodCalculator.getErrorMessages();
        if(!errors.isEmpty()){
            MessageMap errorMap = GlobalVariables.getMessageMap();
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
    
    public void rePopulateCalculatedAmount(Budget budget, BudgetLineItem budgetLineItem) {
        budgetLineItem.getBudgetCalculatedAmounts().clear();
        new LineItemCalculator(budget,budgetLineItem).setCalculatedAmounts(budget, budgetLineItem);
    }
    public void rePopulateCalculatedAmount(Budget budget, BudgetPersonnelDetails newBudgetPersonnelDetails) {
        newBudgetPersonnelDetails.getBudgetCalculatedAmounts().clear();
        new PersonnelLineItemCalculator(budget,newBudgetPersonnelDetails).setCalculatedAmounts(budget, newBudgetPersonnelDetails);
    }

    public void updatePersonnelBudgetRate(BudgetLineItem budgetLineItem){
        int j = 0; 
        for(BudgetPersonnelDetails budgetPersonnelDetails: budgetLineItem.getBudgetPersonnelDetailsList()){
            if(budgetPersonnelDetails.getCostElement()==null){
                budgetPersonnelDetails.setCostElement(budgetLineItem.getCostElement());
                budgetPersonnelDetails.setCostElementBO(budgetLineItem.getCostElementBO());
            }
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
    
    public BudgetForm getBudgetFormFromGlobalVariables() {
        BudgetForm budgetForm = null;
        KualiForm form = KNSGlobalVariables.getKualiForm();
        if (form != null && form instanceof BudgetForm) {
            budgetForm = (BudgetForm)form;
        }
        return budgetForm;
    }
    
    // Attempt for budget limit panel
    public List<Map <String, List<BudgetDecimal>>> getBudgetLimitsTotals(String budgetId) {
        final Map<String, Object> fieldValues = new HashMap<String, Object>();
        List<Map <String, List<BudgetDecimal>>> retList = new ArrayList<Map <String, List<BudgetDecimal>>>();
      fieldValues.put("budgetId", budgetId);
      Budget budget
          = (Budget)this.businessObjectService.findByPrimaryKey(AwardBudgetExt.class, fieldValues);

        
        calculateBudgetTotals(budget);
        Map <String, List<BudgetDecimal>>  personnelBudgetLimits = getPersonnelMap();
        Map <String, List<BudgetDecimal>>  nonPersonnelBudgetLimits = getNonPersonnelMap();
        for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
//            budgetPeriod.refreshReferenceObject("budgetLineItems");
            for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
                AwardBudgetLineItemExt awardBudgetLineItem = (AwardBudgetLineItemExt)budgetLineItem;
                if (!"P".equals(awardBudgetLineItem.getCostElementBO().getBudgetCategory().getBudgetCategoryTypeCode())) {
                    addBudgetLimits(nonPersonnelBudgetLimits.get(awardBudgetLineItem.getCostElementBO().getBudgetCategory().getBudgetCategoryTypeCode()), awardBudgetLineItem, false);
                    getNonPersonnelCalAmt(nonPersonnelBudgetLimits, awardBudgetLineItem, false);
                    } else {
                    addBudgetLimits(personnelBudgetLimits.get(SALARY), awardBudgetLineItem, false);    
                    getFringeAndCalculatedCost(personnelBudgetLimits, awardBudgetLineItem, false);
                }
            }
        }
        if (((AwardBudgetExt)budget).getPrevBudget().getBudgetId() != null) {
            getPreviousBudgetLimits(((AwardBudgetExt)budget).getPrevBudget().getBudgetId(),
                    personnelBudgetLimits, nonPersonnelBudgetLimits);
        }
        Map <String, List<BudgetDecimal>>  totallBudgetLimits = getTotalMap();

        addTotals(personnelBudgetLimits, totallBudgetLimits);
        addTotals(nonPersonnelBudgetLimits, totallBudgetLimits);
        retList.add(personnelBudgetLimits);
        retList.add(nonPersonnelBudgetLimits);
        retList.add(totallBudgetLimits);
        return retList;
    }
    
    protected void getPreviousBudgetLimits(Long budgetId, Map<String, List<BudgetDecimal>>  personnelBudgetLimits, Map<String, List<BudgetDecimal>>  nonPersonnelBudgetLimits) {
        Map keyMap = new HashMap();
        keyMap.put("budgetId", budgetId);
        AwardBudgetExt budget = (AwardBudgetExt)getBusinessObjectService().findByPrimaryKey(AwardBudgetExt.class, keyMap);
        if (budget != null) {
            for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
//              budgetPeriod.refreshReferenceObject("budgetLineItems");
              for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
                  AwardBudgetLineItemExt awardBudgetLineItem = (AwardBudgetLineItemExt)budgetLineItem;
                  if (!"P".equals(awardBudgetLineItem.getCostElementBO().getBudgetCategory().getBudgetCategoryTypeCode())) {
                      addBudgetLimits(nonPersonnelBudgetLimits.get(awardBudgetLineItem.getCostElementBO().getBudgetCategory().getBudgetCategoryTypeCode()), awardBudgetLineItem, true);
                      getNonPersonnelCalAmt(nonPersonnelBudgetLimits, awardBudgetLineItem, true);
                      } else {
                      addBudgetLimits(personnelBudgetLimits.get(SALARY), awardBudgetLineItem, true);    
                      getFringeAndCalculatedCost(personnelBudgetLimits, awardBudgetLineItem, true);
                  }
              }
          }
            
        }
    }
    protected List<BudgetDecimal> initBudgetLimits() {
        
        List<BudgetDecimal> budgetLimits = new ArrayList<BudgetDecimal>();
        budgetLimits.add(BudgetDecimal.ZERO);
        budgetLimits.add(BudgetDecimal.ZERO);
        budgetLimits.add(BudgetDecimal.ZERO);
        return budgetLimits;
    }

    protected void addTotals(Map<String, List<BudgetDecimal>> budgetLimitMap, Map<String, List<BudgetDecimal>> totalsMap) {
        List<BudgetDecimal> budgetLimits = initBudgetLimits();
        for (Map.Entry entry : budgetLimitMap.entrySet()) {
            List<BudgetDecimal> limit = (List<BudgetDecimal>) entry.getValue();
            if (!INDIRECT_COST.equalsIgnoreCase((String) entry.getKey())) {
                addToLimit(budgetLimits, limit);
            } else {
                addToLimit(totalsMap.get("FAndA"), limit);
                addToLimit(totalsMap.get("Totals"), limit);
            }
        }
        budgetLimitMap.put("Totals", budgetLimits);
        addToLimit(totalsMap.get("Direct"), budgetLimits);
        addToLimit(totalsMap.get("Totals"), budgetLimits);
       // addToLimit(totalsMap.get("Totals"), totalsMap.get("FAndA"));
    }
    
    protected void addToLimit(List<BudgetDecimal> budgetLimits, List<BudgetDecimal> limits) {
        budgetLimits.set(0, budgetLimits.get(0).add(limits.get(0)));
        budgetLimits.set(1, budgetLimits.get(1).add(limits.get(1)));
        budgetLimits.set(2, budgetLimits.get(2).add(limits.get(2)));                    
    }
    
    protected void addBudgetLimits(List<BudgetDecimal> budgetLimits, AwardBudgetLineItemExt awardBudgetLineItem, boolean isPrevBudget) {
        if (isPrevBudget) {
            budgetLimits.set(1, budgetLimits.get(1).add(awardBudgetLineItem.getLineItemCost()));
        } else {
            budgetLimits.set(0, budgetLimits.get(0).add(awardBudgetLineItem.getLineItemCost()));
        }
        budgetLimits.set(2, budgetLimits.get(2).add(awardBudgetLineItem.getLineItemCost()));

    }

    protected void addBudgetLimits(List<BudgetDecimal> budgetLimits, AwardBudgetLineItemCalculatedAmountExt awardCalcAmt, boolean isPrevBudget) {
        if (isPrevBudget) {
            budgetLimits.set(1, budgetLimits.get(1).add(awardCalcAmt.getCalculatedCost()));
        } else {
            budgetLimits.set(0, budgetLimits.get(0).add(awardCalcAmt.getCalculatedCost()));
        }
        budgetLimits.set(2, budgetLimits.get(2).add(awardCalcAmt.getCalculatedCost()));
    }

    protected Map <String, List<BudgetDecimal>> getNonPersonnelMap() {
        Map <String, List<BudgetDecimal>>  nonPersonnelBudgetLimits = new HashMap<String, List<BudgetDecimal>>();
        nonPersonnelBudgetLimits.put(EQUIPMENT, initBudgetLimits());
        nonPersonnelBudgetLimits.put(TRAVEL, initBudgetLimits());
        nonPersonnelBudgetLimits.put(PARTICIPANT_SUPPORT, initBudgetLimits());
        nonPersonnelBudgetLimits.put(OTHER_DIRECT, initBudgetLimits());
        nonPersonnelBudgetLimits.put(CALCULATED_COST, initBudgetLimits());
        nonPersonnelBudgetLimits.put(INDIRECT_COST, initBudgetLimits());
        return nonPersonnelBudgetLimits;
    }
    
    protected Map <String, List<BudgetDecimal>> getPersonnelMap() {
        Map <String, List<BudgetDecimal>>  personnelBudgetLimits = new HashMap<String, List<BudgetDecimal>>();
        personnelBudgetLimits.put(SALARY, initBudgetLimits());
        personnelBudgetLimits.put(FRINGE, initBudgetLimits());
        personnelBudgetLimits.put(CALCULATED_COST, initBudgetLimits());
        personnelBudgetLimits.put(INDIRECT_COST, initBudgetLimits());
        return personnelBudgetLimits;
    }
    
    protected Map <String, List<BudgetDecimal>> getTotalMap() {
        Map <String, List<BudgetDecimal>>  personnelBudgetLimits = new HashMap<String, List<BudgetDecimal>>();
        personnelBudgetLimits.put("Direct", initBudgetLimits());
        personnelBudgetLimits.put("FAndA", initBudgetLimits());
        personnelBudgetLimits.put("Totals", initBudgetLimits());
        return personnelBudgetLimits;
    }
    
    protected void getFringeAndCalculatedCost(Map<String, List<BudgetDecimal>> personnelBudgetLimits,
            AwardBudgetLineItemExt awardBudgetLineItem, boolean isPrevBudget) {
        for (BudgetLineItemCalculatedAmount calcExpenseAmount : awardBudgetLineItem.getBudgetLineItemCalculatedAmounts()) {
                calcExpenseAmount.refreshReferenceObject("rateClass");
                AwardBudgetLineItemCalculatedAmountExt awardCalcAmt = (AwardBudgetLineItemCalculatedAmountExt) calcExpenseAmount;
                // Check for Employee Benefits RateClassType
                if (calcExpenseAmount.getRateClass().getRateClassType().equalsIgnoreCase("E")) {
                    addBudgetLimits(personnelBudgetLimits.get(FRINGE), awardCalcAmt, isPrevBudget);
                }
                else if (!calcExpenseAmount.getRateClass().getRateClassType().equalsIgnoreCase("O")){
                    addBudgetLimits(personnelBudgetLimits.get(CALCULATED_COST), awardCalcAmt, isPrevBudget);

                } else {
                    addBudgetLimits(personnelBudgetLimits.get(INDIRECT_COST), awardCalcAmt, isPrevBudget);
                }
        }
    }

    protected void getNonPersonnelCalAmt(Map<String, List<BudgetDecimal>> personnelBudgetLimits,
            AwardBudgetLineItemExt awardBudgetLineItem, boolean isPrevBudget) {
        for (BudgetLineItemCalculatedAmount calcExpenseAmount : awardBudgetLineItem.getBudgetLineItemCalculatedAmounts()) {
                calcExpenseAmount.refreshReferenceObject("rateClass");
                AwardBudgetLineItemCalculatedAmountExt awardCalcAmt = (AwardBudgetLineItemCalculatedAmountExt) calcExpenseAmount;
                // Check for Employee Benefits RateClassType
                if (!calcExpenseAmount.getRateClass().getRateClassType().equalsIgnoreCase("O")){
                    addBudgetLimits(personnelBudgetLimits.get(CALCULATED_COST), awardCalcAmt, isPrevBudget);

                } else {
                    addBudgetLimits(personnelBudgetLimits.get(INDIRECT_COST), awardCalcAmt, isPrevBudget);
                }
        }
    }
}
