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
package org.kuali.coeus.common.budget.impl.calculator;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.api.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.calculator.*;
import org.kuali.coeus.common.budget.framework.query.QueryList;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.common.budget.framework.query.operator.And;
import org.kuali.coeus.common.budget.framework.query.operator.Equals;
import org.kuali.coeus.common.budget.framework.query.operator.Operator;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategoryType;
import org.kuali.coeus.common.budget.framework.core.*;
import org.kuali.coeus.common.budget.framework.distribution.BudgetDistributionService;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetRateAndBase;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelCalculatedAmount;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelRateAndBase;
import org.kuali.coeus.common.budget.framework.rate.RateClass;
import org.kuali.coeus.common.budget.framework.rate.RateType;
import org.kuali.coeus.common.framework.impl.LineItemGroup;
import org.kuali.coeus.common.framework.impl.LineItemObject;
import org.kuali.coeus.common.framework.impl.Period;
import org.kuali.coeus.propdev.impl.hierarchy.HierarchyStatusConstants;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.MessageMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;


/**
 * This class implements all methods declared in BudgetCalculationService
 */
@Component("budgetCalculationService")
public class BudgetCalculationServiceImpl implements BudgetCalculationService {
    
    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;
    @Autowired
    @Qualifier("budgetDistributionService")
    private BudgetDistributionService budgetDistributionService;

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;
    
    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    private static final String BUDGET_SUMMARY_PERIOD_HEADER_LABEL = "P";
    private static final String BUDGET_SUMMARY_PERSONNEL_GROUP_LABEL = "Personnel";
    private static final String BUDGET_SUMMARY_NONPERSONNEL_GROUP_LABEL = "Non-personnel";
    private static final String BUDGET_SUMMARY_TOTALS_GROUP_LABEL = "Totals";
    
    private enum BudgetSummaryConstants {
        CalculatedDirectCost ("calculatedDirectCosts", "Calculated Direct Costs"),
        TotalDirectCost ("totalDirectCost", "Total Direct Cost"),
        TotalFnACost ("totalFnACost", "Total F&A Costs"),
        PersonSalary("salary", "Salary"),
        PersonFringe("fringe", "Fringe");
        
        private final String key;   
        private final String label; 
        BudgetSummaryConstants(String key, String label) {
            this.key = key;
            this.label = label;
        }
        public String getKey() { 
            return key; 
        }
        public String getLabel() { 
            return label; 
        }
    }
    
    
    @Override
    public void calculateBudget(Budget budget){
        List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            calculateBudgetPeriod(budget, budgetPeriod);
        }
        if(budgetPeriods!=null && !budgetPeriods.isEmpty()){
            syncCostsToBudget(budget);
        }
    }
    /**
     * Checks if a calculation is required where Budget periods must be synced in line items.
     *
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

    @Override
    public void calculateBudgetLineItem(Budget budget,BudgetPersonnelDetails budgetLineItem){
        new PersonnelLineItemCalculator(budget,budgetLineItem).calculate();
    }

    @Override
    public void calculateBudgetLineItem(Budget budget,BudgetLineItem budgetLineItem){
        BudgetLineItem budgetLineItemToCalc = budgetLineItem;
        List<BudgetPersonnelDetails> budgetPersonnelDetList = budgetLineItemToCalc.getBudgetPersonnelDetailsList();
        if(budgetLineItemToCalc.isBudgetPersonnelLineItemDeleted() || (budgetPersonnelDetList!=null && !budgetPersonnelDetList.isEmpty())){
            updatePersonnelBudgetRate(budgetLineItemToCalc);
            ScaleTwoDecimal personnelLineItemTotal  = ScaleTwoDecimal.ZERO;
            ScaleTwoDecimal personnelTotalCostSharing  = ScaleTwoDecimal.ZERO;
            Map<String, ScaleTwoDecimal> totalCalculatedCost = new HashMap<String, ScaleTwoDecimal> ();
            Map<String, ScaleTwoDecimal> totalCalculatedCostSharing = new HashMap<String, ScaleTwoDecimal> ();
            ScaleTwoDecimal newTotalUrAmount = ScaleTwoDecimal.ZERO;
            budgetLineItem.getBudgetRateAndBaseList().clear();
            int rateNumber = 0;
            boolean resetTotalUnderRecovery = false;
            ScaleTwoDecimal calcDirectCost = ScaleTwoDecimal.ZERO;
            ScaleTwoDecimal calcIndirectCost = ScaleTwoDecimal.ZERO;
            ScaleTwoDecimal calcTotalCostSharing = ScaleTwoDecimal.ZERO;
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
                            ScaleTwoDecimal value = totalCalculatedCost.get(rateKey);
                            value = value.add(personnelCalAmt.getCalculatedCost());
                            totalCalculatedCost.put(rateKey, value);
                            
                            value = totalCalculatedCostSharing.get(rateKey);
                            value = value.add(personnelCalAmt.getCalculatedCostSharing());
                            totalCalculatedCostSharing.put(rateKey, value);
                            
                        }
                        
                        if (personnelCalAmt.getRateClass() == null) {
                            personnelCalAmt.refreshReferenceObject("rateClass");
                        }
                        if (!personnelCalAmt.getRateClass().getRateClassTypeCode().equals(RateClassType.OVERHEAD.getRateClassType())) {
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
            ScaleTwoDecimal appliedRate = budgetPersonnelRateAndBase.getAppliedRate();
            budgetRateBase.setAppliedRate(ScaleTwoDecimal.returnZeroIfNull(appliedRate));
            ScaleTwoDecimal calculatedCost = budgetPersonnelRateAndBase.getCalculatedCost();
            ScaleTwoDecimal calculatedCostSharing = budgetPersonnelRateAndBase.getCalculatedCostSharing();
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
            budgetRateBase.setBudgetLineItem(bli);
            budgetRateAndBaseList.add(budgetRateBase);
        }   

    }

    @Override
    public void populateCalculatedAmount(Budget budget,BudgetLineItem budgetLineItem){
        new LineItemCalculator(budget,budgetLineItem).populateCalculatedAmountLineItems();
    }
    @Override
    public void populateCalculatedAmount(Budget budget,BudgetPersonnelDetails budgetPersonnelDetails){
        new PersonnelLineItemCalculator(budget,budgetPersonnelDetails).populateCalculatedAmountLineItems();
    }
    @Override
    public void calculateBudgetPeriod(Budget budget, BudgetPeriod budgetPeriod){
        if (isCalculationRequired(budget, budgetPeriod)){
            new BudgetPeriodCalculator().calculate(budget, budgetPeriod);
        }else {
        	updateBudgetTotalCost(budget);
        }
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
        this.getBudgetDistributionService().initializeUnrecoveredFandACollectionDefaults(document);
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
        this.getBudgetDistributionService().initializeCostSharingCollectionDefaults(document);
    }

    /**
     * Ensures that a budget period has synced costs with other budget objects (i.e. line items)
     *
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
        
        ScaleTwoDecimal lineItemsAmount = ScaleTwoDecimal.ZERO;
        
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
        
        ScaleTwoDecimal lineItemsAmount = ScaleTwoDecimal.ZERO;
        
        for (final BudgetPeriod budgetPeriod : document.getBudgetPeriods()) {
            lineItemsAmount = lineItemsAmount.add(budgetPeriod.getSumTotalCostSharingAmountFromLineItems());
        }        
        
        return lineItemsAmount.isPositive()
            || document.getSumCostSharingAmountFromPeriods().isPositive();
    }

    protected SortedMap<BudgetCategoryType, List<CostElement>> categorizeObjectCodesByCategory(Budget budget) {
        SortedMap<CostElement, List<ScaleTwoDecimal>> objectCodeTotals = budget.getObjectCodeTotals();
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
        return getDataObjectService().find(BudgetCategoryType.class, getPersonnelBudgetCategoryTypeCode());
    }

    protected List<BudgetCategoryType> getAllBudgetCategoryTypes() {
        return getDataObjectService().findAll(BudgetCategoryType.class).getResults();
    }
    
    @Deprecated
    @Override
    public void calculateBudgetSummaryTotals(Budget budget){
        calculateBudgetTotals(budget);

        //Categorize all Object Codes per their Category Type
        SortedMap<BudgetCategoryType, List<CostElement>> objectCodeListByBudgetCategoryType = categorizeObjectCodesByCategory(budget);   
       
        SortedMap<CostElement, List<BudgetPersonnelDetails>> objectCodeUniquePersonnelList = new TreeMap<CostElement, List<BudgetPersonnelDetails>>();
        
        SortedMap<String, List<ScaleTwoDecimal>> objectCodePersonnelSalaryTotals = new TreeMap<String, List<ScaleTwoDecimal>>();
        SortedMap<String, List<ScaleTwoDecimal>> objectCodePersonnelFringeTotals = new TreeMap<String, List<ScaleTwoDecimal>>();
        
        //Temp collections for maintaining Sub Section Totals
        SortedSet<String> objectCodePersonnelSalaryTotalsByPeriod = new TreeSet<String>();
        SortedSet<String> objectCodePersonnelFringeTotalsByPeriod = new TreeSet<String>();

        SortedMap<RateType, List<ScaleTwoDecimal>> personnelCalculatedExpenseTotals = new TreeMap<RateType, List<ScaleTwoDecimal>>();
        SortedMap<RateType, List<ScaleTwoDecimal>> nonPersonnelCalculatedExpenseTotals = new TreeMap<RateType, List<ScaleTwoDecimal>>();

        List <ScaleTwoDecimal> periodSummarySalaryTotals = new ArrayList<ScaleTwoDecimal>();
        for (int i = 0; i < budget.getBudgetPeriods().size(); i++) {
            periodSummarySalaryTotals.add(i, ScaleTwoDecimal.ZERO);
        }
        List <ScaleTwoDecimal> periodSummaryFringeTotals = new ArrayList<ScaleTwoDecimal>();
        for (int i = 0; i < budget.getBudgetPeriods().size(); i++) {
            periodSummaryFringeTotals.add(i, ScaleTwoDecimal.ZERO);
        }
        SortedMap<String, List<ScaleTwoDecimal>> subTotalsBySubSection = new TreeMap<String, List<ScaleTwoDecimal>>();
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
                            ScaleTwoDecimal personSalaryTotalsForCurrentPeriod = personOccurrencesForSameObjectCode.sumObjects("salaryRequested");
                            
                            if (!objectCodePersonnelSalaryTotals.containsKey(matchingLineItem.getCostElement()+","+budgetPersonnelDetails.getPersonId())) {
                                objectCodeUniquePersonnelList.get(matchingLineItem.getCostElementBO()).add(budgetPersonnelDetails);
                                // set up for all periods and put into map
                                List <ScaleTwoDecimal> periodTotals = new ArrayList<ScaleTwoDecimal>();
                                for (int i = 0; i < budget.getBudgetPeriods().size(); i++) {
                                    periodTotals.add(i, ScaleTwoDecimal.ZERO);
                                }
                                objectCodePersonnelSalaryTotals.put(matchingLineItem.getCostElement()+","+budgetPersonnelDetails.getPersonId(), periodTotals);
                            }
                            //Setting the total lines here - so that they'll be set just once for a unique person within an Object Code
                            objectCodePersonnelSalaryTotals.get(matchingLineItem.getCostElement()+","+budgetPersonnelDetails.getPersonId()).set(budgetPeriod.getBudgetPeriod() - 1, personSalaryTotalsForCurrentPeriod);
                            if (objectCodePersonnelSalaryTotalsByPeriod.add(budgetPeriod.getBudgetPeriod().toString() + ","+ matchingLineItem.getCostElement()+","+budgetPersonnelDetails.getPersonId())) {
                                subTotalsBySubSection.get("personnelSalaryTotals").set(budgetPeriod.getBudgetPeriod() - 1, ((ScaleTwoDecimal) (subTotalsBySubSection.get("personnelSalaryTotals").get(budgetPeriod.getBudgetPeriod() - 1))).add(personSalaryTotalsForCurrentPeriod));
                            }
                            
                            //Calculate the Fringe Totals for each Person
                            if (!objectCodePersonnelFringeTotals.containsKey(matchingLineItem.getCostElement()+","+budgetPersonnelDetails.getPersonId())) {
                                // set up for all periods and put into map
                                List <ScaleTwoDecimal> periodFringeTotals = new ArrayList<ScaleTwoDecimal>();
                                for (int i = 0; i < budget.getBudgetPeriods().size(); i++) {
                                    periodFringeTotals.add(i, ScaleTwoDecimal.ZERO);
                                }
                                objectCodePersonnelFringeTotals.put(matchingLineItem.getCostElement()+","+budgetPersonnelDetails.getPersonId(), periodFringeTotals); 
                            }   
                            ScaleTwoDecimal personFringeTotalsForCurrentPeriod = ScaleTwoDecimal.ZERO;
                            //Calculate the Fringe Totals for that Person (cumulative fringe for all occurrences of the person)
                                for(Object person : personOccurrencesForSameObjectCode) {
                                    BudgetPersonnelDetails personOccurrence = (BudgetPersonnelDetails) person;
                                    for(BudgetPersonnelCalculatedAmount calcExpenseAmount : personOccurrence.getBudgetPersonnelCalculatedAmounts()) {
                                        calcExpenseAmount.refreshReferenceObject("rateClass");
                                        //Check for Employee Benefits RateClassType
                                        if(calcExpenseAmount.getRateClass().getRateClassTypeCode().equalsIgnoreCase("E")) {
                                            personFringeTotalsForCurrentPeriod = personFringeTotalsForCurrentPeriod.add(calcExpenseAmount.getCalculatedCost());
                                        }
                                    }
                                }
                                objectCodePersonnelFringeTotals.get(matchingLineItem.getCostElement()+","+budgetPersonnelDetails.getPersonId()).set(budgetPeriod.getBudgetPeriod() - 1, personFringeTotalsForCurrentPeriod);
                            
                            if (objectCodePersonnelFringeTotalsByPeriod.add(budgetPeriod.getBudgetPeriod().toString() + ","+ matchingLineItem.getCostElement()+","+budgetPersonnelDetails.getPersonId())) {
                                subTotalsBySubSection.get("personnelFringeTotals").set(budgetPeriod.getBudgetPeriod() - 1, ((ScaleTwoDecimal) (subTotalsBySubSection.get("personnelFringeTotals").get(budgetPeriod.getBudgetPeriod() - 1))).add(personFringeTotalsForCurrentPeriod));
                            }
                        } 
                        
                        //Need to handle the Summary Items - if any
                        if(CollectionUtils.isEmpty(matchingLineItem.getBudgetPersonnelDetailsList())) {
                            //Include Summary Item Salary (Line Item Cost)
                            if (!objectCodePersonnelSalaryTotals.containsKey(matchingLineItem.getCostElement())) {
                                // set up for all periods and put into map
                                List <ScaleTwoDecimal> periodTotals = new ArrayList<ScaleTwoDecimal>();
                                for (int i = 0; i < budget.getBudgetPeriods().size(); i++) {
                                    periodTotals.add(i, ScaleTwoDecimal.ZERO);
                                }
                                objectCodePersonnelSalaryTotals.put(matchingLineItem.getCostElement(), periodTotals);
                            }
                            objectCodePersonnelSalaryTotals.get(matchingLineItem.getCostElement()).set(budgetPeriod.getBudgetPeriod() - 1, ((ScaleTwoDecimal) objectCodePersonnelSalaryTotals.get(matchingLineItem.getCostElement()).get(budgetPeriod.getBudgetPeriod() - 1)).add(matchingLineItem.getLineItemCost()));
                            
                            //Include Summary Item Fringe Amt
                            ScaleTwoDecimal summaryFringeTotalsForCurrentPeriod = ScaleTwoDecimal.ZERO;
                            if (!objectCodePersonnelFringeTotals.containsKey(matchingLineItem.getCostElement())) {
                                // set up for all periods and put into map
                                List <ScaleTwoDecimal> periodFringeTotals = new ArrayList<ScaleTwoDecimal>();
                                for (int i = 0; i < budget.getBudgetPeriods().size(); i++) {
                                    periodFringeTotals.add(i, ScaleTwoDecimal.ZERO);
                                }
                                objectCodePersonnelFringeTotals.put(matchingLineItem.getCostElement(), periodFringeTotals); 
                            }
                            
                            for(BudgetLineItemCalculatedAmount lineItemCalculatedAmount : matchingLineItem.getBudgetLineItemCalculatedAmounts()) {
                                lineItemCalculatedAmount.refreshReferenceObject("rateClass");
                                //Check for Employee Benefits RateClassType
                                if(lineItemCalculatedAmount.getRateClass().getRateClassTypeCode().equalsIgnoreCase("E")) {
                                    summaryFringeTotalsForCurrentPeriod = summaryFringeTotalsForCurrentPeriod.add(lineItemCalculatedAmount.getCalculatedCost());
                                }
                            }
                            objectCodePersonnelFringeTotals.get(matchingLineItem.getCostElement()).set(budgetPeriod.getBudgetPeriod() - 1, ((ScaleTwoDecimal) objectCodePersonnelFringeTotals.get(matchingLineItem.getCostElement()).get(budgetPeriod.getBudgetPeriod() - 1)).add(summaryFringeTotalsForCurrentPeriod));

                            //if(matchingLineItemIndex == filteredLineItems.size()-1) { 
                                 subTotalsBySubSection.get("personnelSalaryTotals").set(budgetPeriod.getBudgetPeriod() - 1, ((ScaleTwoDecimal) (subTotalsBySubSection.get("personnelSalaryTotals").get(budgetPeriod.getBudgetPeriod() - 1))).add((ScaleTwoDecimal) (objectCodePersonnelSalaryTotals.get(matchingLineItem.getCostElement()).get(budgetPeriod.getBudgetPeriod()-1))));
                                 subTotalsBySubSection.get("personnelFringeTotals").set(budgetPeriod.getBudgetPeriod() - 1, ((ScaleTwoDecimal) (subTotalsBySubSection.get("personnelFringeTotals").get(budgetPeriod.getBudgetPeriod() - 1))).add((ScaleTwoDecimal) (objectCodePersonnelFringeTotals.get(matchingLineItem.getCostElement()).get(budgetPeriod.getBudgetPeriod()-1))));
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
        return BudgetCommonServiceFactory.createInstance(budget.getBudgetParent());
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
            if(!StringUtils.equals(budgetCategoryType.getCode(), "P")) {
                List <ScaleTwoDecimal> nonPersonnelTotals = new ArrayList<ScaleTwoDecimal>();
                for (int i = 0; i < budget.getBudgetPeriods().size(); i++) {
                    nonPersonnelTotals.add(i, ScaleTwoDecimal.ZERO);
                }
                budget.getBudgetSummaryTotals().put(budgetCategoryType.getCode(), nonPersonnelTotals);
                
                List<CostElement> objectCodes = budget.getObjectCodeListByBudgetCategoryType().get(budgetCategoryType);
                for(CostElement objectCode : objectCodes) {
                    if (!StringUtils.equalsIgnoreCase(objectCode.getCostElement(), getParameterService().getParameterValueAsString(Budget.class, "proposalHierarchySubProjectIndirectCostElement"))) {
                        List<ScaleTwoDecimal> objectCodePeriodTotals = budget.getObjectCodeTotals().get(objectCode);
                        for(BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
                            budget.getBudgetSummaryTotals().get(budgetCategoryType.getCode()).set(budgetPeriod.getBudgetPeriod() - 1, ((ScaleTwoDecimal) (budget.getBudgetSummaryTotals().get(budgetCategoryType.getCode()).get(budgetPeriod.getBudgetPeriod() - 1))).add(objectCodePeriodTotals.get(budgetPeriod.getBudgetPeriod() - 1)));
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
    
    protected SortedMap<RateType, List<ScaleTwoDecimal>> calculateExpenseTotals(Budget budget, boolean personnelFlag){
        SortedMap<RateType, List<ScaleTwoDecimal>> calculatedExpenseTotals = new TreeMap <RateType, List<ScaleTwoDecimal>> ();
        
        List <ScaleTwoDecimal> calculatedDirectCostSummaryTotals = new ArrayList<ScaleTwoDecimal>();
        for (int i = 0; i < budget.getBudgetPeriods().size(); i++) {
            calculatedDirectCostSummaryTotals.add(i, ScaleTwoDecimal.ZERO);
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
                        RateType rateType = createRateType(budgetLineItemCalculatedAmount);
                        RateClass rateClass = null;
                        if(rateType != null) {
                            rateType.refreshReferenceObject("rateClass");
                            rateClass = rateType.getRateClass();
                        }
                        
                        if (((personnelFlag && rateClass != null && !StringUtils.equals(rateClass.getRateClassTypeCode(), "E")) || !personnelFlag) && !rateTypes.contains(rateType)) {
                            rateTypes.add(rateType);
                            Equals equalsRC = new Equals("rateClassCode", budgetLineItemCalculatedAmount.getRateClassCode());
                            Equals equalsRT = new Equals("rateTypeCode", budgetLineItemCalculatedAmount.getRateTypeCode());
                            And RCandRT = new And(equalsRC, equalsRT);
                            ScaleTwoDecimal rateTypeTotalInThisPeriod = lineItemCalcAmtQueryList.sumObjects("calculatedCost", RCandRT);
                            if (!calculatedExpenseTotals.containsKey(rateType)) {
                                List <ScaleTwoDecimal> rateTypePeriodTotals = new ArrayList<ScaleTwoDecimal>();
                                for (int i = 0; i < budget.getBudgetPeriods().size(); i++) {
                                    rateTypePeriodTotals.add(i, ScaleTwoDecimal.ZERO);
                                }
                                calculatedExpenseTotals.put(rateType, rateTypePeriodTotals);
                            }
                            calculatedExpenseTotals.get(rateType).set(budgetPeriod.getBudgetPeriod() - 1,((ScaleTwoDecimal)calculatedExpenseTotals.get(rateType).get(budgetPeriod.getBudgetPeriod() - 1)).add(rateTypeTotalInThisPeriod));
                            
                            if(!StringUtils.equals(rateClass.getRateClassTypeCode(), RateClassType.OVERHEAD.getRateClassType())) {
                                budget.getBudgetSummaryTotals().get(totalsMapKey).set(budgetPeriod.getBudgetPeriod() - 1, ((ScaleTwoDecimal) (budget.getBudgetSummaryTotals().get(totalsMapKey).get(budgetPeriod.getBudgetPeriod() - 1))).add(rateTypeTotalInThisPeriod));
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

    @Deprecated
    @SuppressWarnings("unchecked")
    protected void calculateBudgetTotals(Budget budget){
        // do we need to cache the totals ?
        SortedMap<CostElement, List<ScaleTwoDecimal>> objectCodeTotals = new TreeMap <CostElement, List<ScaleTwoDecimal>> ();
        SortedMap<RateType, List<ScaleTwoDecimal>> calculatedExpenseTotals = new TreeMap <RateType, List<ScaleTwoDecimal>> ();
        for (BudgetPeriod budgetPeriod: budget.getBudgetPeriods()) {
            List <CostElement> objectCodes = new ArrayList<CostElement>();
            QueryList lineItemQueryList = new QueryList();
            lineItemQueryList.addAll(budgetPeriod.getBudgetLineItems());
            budgetPeriod.setExpenseTotal(ScaleTwoDecimal.ZERO);
            // probably need to add '0' to the period that has no such object code or ratetype ?
            for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
                if (budgetLineItem.getCostElementBO() == null) {
                    budgetLineItem.refreshReferenceObject("costElementBO");
                }
                CostElement costElement = budgetLineItem.getCostElementBO();
                if (!objectCodes.contains(costElement)) {
                    objectCodes.add(costElement);
                    Equals equalsCostElement = new Equals("costElement", budgetLineItem.getCostElement());
                    ScaleTwoDecimal objectCodeTotalInThisPeriod = lineItemQueryList.sumObjects("lineItemCost", equalsCostElement);
                    if (!objectCodeTotals.containsKey(costElement)) {
                        // set up for all periods and put into map
                        List <ScaleTwoDecimal> periodTotals = new ArrayList<ScaleTwoDecimal>();
                        for (int i = 0; i < budget.getBudgetPeriods().size(); i++) {
                            periodTotals.add(i, ScaleTwoDecimal.ZERO);
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
                    RateType rateType = createRateType(budgetLineItemCalculatedAmount);
                    if (!rateTypes.contains(rateType)) {
                        rateTypes.add(rateType);
                        Equals equalsRC = new Equals("rateClassCode", budgetLineItemCalculatedAmount.getRateClassCode());
                        Equals equalsRT = new Equals("rateTypeCode", budgetLineItemCalculatedAmount.getRateTypeCode());
                        And RCandRT = new And(equalsRC, equalsRT);
                        ScaleTwoDecimal rateTypeTotalInThisPeriod = lineItemCalcAmtQueryList.sumObjects("calculatedCost", RCandRT);
                        if (!calculatedExpenseTotals.containsKey(rateType)) {
                            List <ScaleTwoDecimal> rateTypePeriodTotals = new ArrayList<ScaleTwoDecimal>();
                            for (int i = 0; i < budget.getBudgetPeriods().size(); i++) {
                                rateTypePeriodTotals.add(i, ScaleTwoDecimal.ZERO);
                            }
                            calculatedExpenseTotals.put(rateType, rateTypePeriodTotals);
                        }
                        calculatedExpenseTotals.get(rateType).set(budgetPeriod.getBudgetPeriod() - 1,((ScaleTwoDecimal)calculatedExpenseTotals.get(rateType).get(budgetPeriod.getBudgetPeriod() - 1)).add(rateTypeTotalInThisPeriod));
                        budgetPeriod.setExpenseTotal(budgetPeriod.getExpenseTotal().add(rateTypeTotalInThisPeriod));
                   }
                }
            }
        }
        budget.setObjectCodeTotals(objectCodeTotals);
        budget.setCalculatedExpenseTotals(calculatedExpenseTotals);

    }
    @Override
    public void syncToPeriodCostLimit(Budget budget, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem) {
        BudgetPeriodCalculator periodCalculator = new BudgetPeriodCalculator();
        periodCalculator.syncToPeriodCostLimit(budget, budgetPeriod, budgetLineItem);
        List<String> errors = periodCalculator.getErrorMessages();
        MessageMap errorMap = globalVariableService.getMessageMap();
        if(!errors.isEmpty()){
            for (String error : errors) {
                errorMap.putError("document.budgetPeriod[" + (budgetPeriod.getBudgetPeriod() - 1) + "].budgetLineItem["+ (budgetLineItem.getLineItemNumber() - 1) +"].lineItemCost", error);
            }
        }
    }
    @Override
    public void syncToPeriodDirectCostLimit(Budget budget, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem) {
        BudgetPeriodCalculator periodCalculator = new BudgetPeriodCalculator();
        periodCalculator.syncToPeriodDirectCostLimit(budget, budgetPeriod, budgetLineItem);
        List<String> errors = periodCalculator.getErrorMessages();
        MessageMap errorMap = globalVariableService.getMessageMap();
        if(!errors.isEmpty()){
            for (String error : errors) {
                errorMap.putError("document.budgetPeriod[" + (budgetPeriod.getBudgetPeriod() - 1) + "].budgetLineItem["+ (budgetLineItem.getLineItemNumber() - 1) +"].lineItemCost", error);
            }
        }
    }
    @Override
    public void applyToLaterPeriods(Budget budget, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem) {
        BudgetPeriodCalculator periodCalculator = new BudgetPeriodCalculator();
        periodCalculator.applyToLaterPeriods(budget, budgetPeriod, budgetLineItem);
        List<String> errors = periodCalculator.getErrorMessages();
        if(!errors.isEmpty()){
            MessageMap errorMap = globalVariableService.getMessageMap();
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
     * Gets the budgetDistributionService attribute.
     * @return Returns the budgetDistributionService.
     */
    public BudgetDistributionService getBudgetDistributionService() {
        return this.budgetDistributionService;
    }
    
    /**
     * Sets the budgetDistributionService attribute value.
     * @param service The budgetDistributionService to set.
     */
    public void setBudgetDistributionService(BudgetDistributionService service) {
        this.budgetDistributionService = service;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @Override
    public void rePopulateCalculatedAmount(Budget budget, BudgetLineItem budgetLineItem) {
        budgetLineItem.getBudgetCalculatedAmounts().clear();
        new LineItemCalculator(budget,budgetLineItem).setCalculatedAmounts(budget, budgetLineItem);
    }
    @Override
    public void rePopulateCalculatedAmount(Budget budget, BudgetPersonnelDetails newBudgetPersonnelDetails) {
        newBudgetPersonnelDetails.getBudgetCalculatedAmounts().clear();
        new PersonnelLineItemCalculator(budget,newBudgetPersonnelDetails).setCalculatedAmounts(budget, newBudgetPersonnelDetails);
    }
    @Override
    public void updatePersonnelBudgetRate(BudgetLineItem budgetLineItem){
        for(BudgetPersonnelDetails budgetPersonnelDetails: budgetLineItem.getBudgetPersonnelDetailsList()){
            budgetPersonnelDetails.setApplyInRateFlag(budgetLineItem.getApplyInRateFlag());
            budgetPersonnelDetails.setOnOffCampusFlag(budgetLineItem.getOnOffCampusFlag());
            for(BudgetPersonnelCalculatedAmount budgetPersonnelCalculatedAmount:budgetPersonnelDetails.getBudgetPersonnelCalculatedAmounts()){
                Boolean updatedApplyRateFlag = null;
                for (BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmout : budgetLineItem.getBudgetLineItemCalculatedAmounts()) {
                    if(budgetLineItemCalculatedAmout.getRateClassCode().equalsIgnoreCase(budgetPersonnelCalculatedAmount.getRateClassCode()) && 
                            budgetLineItemCalculatedAmout.getRateTypeCode().equalsIgnoreCase(budgetPersonnelCalculatedAmount.getRateTypeCode())) {
                        updatedApplyRateFlag = budgetLineItemCalculatedAmout.getApplyRateFlag();
                    }
                }
                budgetPersonnelCalculatedAmount.setApplyRateFlag(updatedApplyRateFlag);                        
            }
        }
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }
    
    public void populateBudgetSummaryTotals(Budget budget){
    	BudgetCategoryType personnelCategoryType = getPersonnelCategoryType();
    	String personnelBudgetCategoryType = personnelCategoryType.getCode();
    	
    	List<BudgetLineItem> budgetLineItems = getAllBudgetSummaryLineItems(budget);
    	
    	SortedMap<BudgetCategoryType, SortedMap<CostElement, List<BudgetLineItem>>> uniqueBudgetCategoryLineItemCostElements = getBudgetSummaryUniqueBudgetCategoryLineItemCostElements(budgetLineItems);
    	SortedMap<CostElement, List<BudgetLineItem>> personnelCostElementLineItems = uniqueBudgetCategoryLineItemCostElements.get(personnelCategoryType);
    	
    	List<Period> budgetSummaryPeriods = new ArrayList<Period>();
        for (BudgetPeriod budgetPeriod: budget.getBudgetPeriods()) {
        	String periodHeader = BUDGET_SUMMARY_PERIOD_HEADER_LABEL.concat(budgetPeriod.getBudgetPeriod().toString());
           	Period summaryPeriod = new Period(periodHeader);
            summaryPeriod.setStartDate(budgetPeriod.getStartDate());
            summaryPeriod.setEndDate(budgetPeriod.getEndDate());

            if (personnelCostElementLineItems != null) {
                LineItemGroup personnelGroup = getPersonnelBudgetSummaryPeriods(budgetPeriod, personnelCostElementLineItems);
                LineItemObject calculatedPersonnelDirectCosts = new LineItemObject(BudgetSummaryConstants.CalculatedDirectCost.getKey(),
                        BudgetSummaryConstants.CalculatedDirectCost.getLabel(), ScaleTwoDecimal.ZERO);
                ScaleTwoDecimal personnelDirectCost = getCalculateBudgetSummaryExpenseTotal(budgetPeriod, true, personnelBudgetCategoryType);
                calculatedPersonnelDirectCosts.setAmount(personnelDirectCost);
                personnelGroup.getLineItems().add(calculatedPersonnelDirectCosts);
                summaryPeriod.getLineItemGroups().add(personnelGroup);
            }

            if (uniqueBudgetCategoryLineItemCostElements != null) {
                uniqueBudgetCategoryLineItemCostElements.remove(personnelCategoryType);
                LineItemGroup nonPersonnelGroup = getNonPersonnelBudgetSummaryPeriods(budgetPeriod, uniqueBudgetCategoryLineItemCostElements);
                LineItemObject calculatedNonPersonnelDirectCosts = new LineItemObject(BudgetSummaryConstants.CalculatedDirectCost.getKey(),
                        BudgetSummaryConstants.CalculatedDirectCost.getLabel(), ScaleTwoDecimal.ZERO);
                ScaleTwoDecimal nonPersonnelDirectCost = getCalculateBudgetSummaryExpenseTotal(budgetPeriod, false, personnelBudgetCategoryType);
                calculatedNonPersonnelDirectCosts.setAmount(nonPersonnelDirectCost);
                nonPersonnelGroup.getLineItems().add(calculatedNonPersonnelDirectCosts);
                summaryPeriod.getLineItemGroups().add(nonPersonnelGroup);
            }

            LineItemGroup totalsGroup = getBudgetSummaryTotals(budgetPeriod);
        	summaryPeriod.getLineItemGroups().add(totalsGroup);
            budgetSummaryPeriods.add(summaryPeriod);
       }
        budget.setBudgetSummaryDetails(budgetSummaryPeriods);
    	
    }

    /**
     * get all line items in budget (all periods)
     * @param budget
     * @return
     */
    private List<BudgetLineItem> getAllBudgetSummaryLineItems(Budget budget) {
    	List<BudgetLineItem> summaryLineItems = new ArrayList<BudgetLineItem>();
        for (BudgetPeriod budgetPeriod: budget.getBudgetPeriods()) {
        	for(BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
    			summaryLineItems.add(budgetLineItem);
        	}
        }
        return summaryLineItems;
    }
    
    /**
     * This method is to get list of line items based on budget category type grouped by cost element.
     * @param budgetLineItems
     * @param budgetCategoryTypeCode
     * @return
     */
    private SortedMap<CostElement, List<BudgetLineItem>> getBudgetSummaryUniqueLineItemCostElementsForBudgetCategory(List<BudgetLineItem> budgetLineItems, String budgetCategoryTypeCode) {
    	SortedMap<CostElement, List<BudgetLineItem>> uniqueLineItemCostElements = new TreeMap<CostElement, List<BudgetLineItem>>();
       	for(BudgetLineItem budgetLineItem : budgetLineItems) {
       		CostElement costElement = budgetLineItem.getCostElementBO();
       		String costElementBudgetCategoryTypeCode = costElement.getBudgetCategory().getBudgetCategoryTypeCode();
       		if(costElementBudgetCategoryTypeCode.equalsIgnoreCase(budgetCategoryTypeCode)) {
                if(!uniqueLineItemCostElements.containsKey(costElement)) {
                	uniqueLineItemCostElements.put(costElement, new ArrayList<BudgetLineItem>());
                }
                uniqueLineItemCostElements.get(costElement).add(budgetLineItem);
       		}
        }
       	return uniqueLineItemCostElements;
    }

    /**
     * This method is to get list of line items grouped by budget category type
     * @param budgetLineItems
     * @return
     */
    private SortedMap<BudgetCategoryType, SortedMap<CostElement, List<BudgetLineItem>>> getBudgetSummaryUniqueBudgetCategoryLineItemCostElements(List<BudgetLineItem> budgetLineItems) {
    	SortedMap<BudgetCategoryType, SortedMap<CostElement, List<BudgetLineItem>>> uniqueBudgetCategoryLineItemCostElements = new TreeMap<BudgetCategoryType, SortedMap<CostElement, List<BudgetLineItem>>>();
    	List<BudgetCategoryType> budgetCategoryTypes = getAllBudgetCategoryTypes();
    	for(BudgetCategoryType budgetCategoryType : budgetCategoryTypes) {
       		SortedMap<CostElement, List<BudgetLineItem>> costElementLineItems = getBudgetSummaryUniqueLineItemCostElementsForBudgetCategory(budgetLineItems, budgetCategoryType.getCode());
            if(!costElementLineItems.isEmpty()) {
            	uniqueBudgetCategoryLineItemCostElements.put(budgetCategoryType, new TreeMap<CostElement, List<BudgetLineItem>>());
                uniqueBudgetCategoryLineItemCostElements.put(budgetCategoryType, costElementLineItems);
            }
        }
       	return uniqueBudgetCategoryLineItemCostElements;
    }
    
    /**
     * This method is to group personnel items for budget summary
     * Start building data structure applicable for personnel items to format it with
     * required details for budget summary
     * @param budget
     * @param uniqueLineItemCostElements
     * @param budgetPeriodPersonnelLineItems
     * @return
     */
    private LineItemGroup getPersonnelBudgetSummaryPeriods(BudgetPeriod budgetPeriod, SortedMap<CostElement, List<BudgetLineItem>> uniqueBudgetLineItemCostElements) {
        LineItemGroup personnelGroup = new LineItemGroup(BUDGET_SUMMARY_PERSONNEL_GROUP_LABEL, true);
        LineItemObject personnelSalaries = new LineItemObject(BudgetSummaryConstants.PersonSalary.getKey(), BudgetSummaryConstants.PersonSalary.getLabel(), ScaleTwoDecimal.ZERO);
        LineItemObject personnelFringe = new LineItemObject(BudgetSummaryConstants.PersonFringe.getKey(), BudgetSummaryConstants.PersonFringe.getLabel(), ScaleTwoDecimal.ZERO);
        ScaleTwoDecimal totalSalary = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalFringe = ScaleTwoDecimal.ZERO;
       for(Map.Entry<CostElement, List<BudgetLineItem>> uniqueLineItem : uniqueBudgetLineItemCostElements.entrySet()) {
     		CostElement personnelCostElement = uniqueLineItem.getKey();
      		List<BudgetLineItem> personnelLineItemsForCostElement = uniqueLineItem.getValue();
            
            QueryList<BudgetLineItem> periodLineItemCostElementQueryList = getLineItemsFilteredByCostElement(budgetPeriod, personnelCostElement.getCostElement());
        	QueryList<BudgetPersonnelDetails> periodLineItemPersonnelDetailsQueryList = getBudgetPersonnelDetails(periodLineItemCostElementQueryList);
        	ScaleTwoDecimal totalSalaryForCostElement = periodLineItemPersonnelDetailsQueryList.sumObjects("salaryRequested");
        	ScaleTwoDecimal totalFringeForCostElement = periodLineItemPersonnelDetailsQueryList.sumObjects("calculatedFringe");

        	Map<String, String> uniquePersonList = getUniquePersonList(personnelLineItemsForCostElement);
            LineItemObject salaryLineItemObject = new LineItemObject(personnelCostElement.getCostElement(), personnelCostElement.getDescription(), totalSalaryForCostElement);
            LineItemObject fringeLineItemObject = new LineItemObject(personnelCostElement.getCostElement(), personnelCostElement.getDescription(), totalFringeForCostElement);
            for(Map.Entry<String, String> personInfo : uniquePersonList.entrySet()) {
         		String personId = personInfo.getKey();
          		String personName = personInfo.getValue();
                ScaleTwoDecimal personSalaryTotalsForCurrentPeriod = ScaleTwoDecimal.ZERO;
                ScaleTwoDecimal personFringeTotalsForCurrentPeriod = ScaleTwoDecimal.ZERO;
                Equals personIdEquals = new Equals("personId", personId);
                QueryList<BudgetPersonnelDetails> personOccurrencesForSameObjectCode = periodLineItemPersonnelDetailsQueryList.filter(personIdEquals);
                if(personOccurrencesForSameObjectCode != null && !personOccurrencesForSameObjectCode.isEmpty()) {
                    personSalaryTotalsForCurrentPeriod = personOccurrencesForSameObjectCode.sumObjects("salaryRequested");
                    personFringeTotalsForCurrentPeriod = personOccurrencesForSameObjectCode.sumObjects("calculatedFringe");
                }
                salaryLineItemObject.getLineItems().add(new LineItemObject(personId, personName, personSalaryTotalsForCurrentPeriod));
                fringeLineItemObject.getLineItems().add(new LineItemObject(personId, personName, personFringeTotalsForCurrentPeriod));
            }
            totalSalary = totalSalary.add(totalSalaryForCostElement);
            totalFringe = totalFringe.add(totalFringeForCostElement);
            personnelSalaries.getLineItems().add(salaryLineItemObject);
            personnelFringe.getLineItems().add(fringeLineItemObject);
       }
       personnelSalaries.setAmount(totalSalary);
       personnelFringe.setAmount(totalFringe);
       personnelGroup.getLineItems().add(personnelSalaries);
       personnelGroup.getLineItems().add(personnelFringe);
       return personnelGroup;
    }
    
    /**
     * Filter budget line items for given cost element
     * @param budgetPeriod
     * @param costElement
     * @return
     */
    private QueryList<BudgetLineItem> getLineItemsFilteredByCostElement(BudgetPeriod budgetPeriod, String costElement) {
  		QueryList<BudgetLineItem> lineItemQueryList = new QueryList<BudgetLineItem>();
        lineItemQueryList.addAll(budgetPeriod.getBudgetLineItems());
        Equals costElementEquals = new Equals("costElement", costElement);
        QueryList<BudgetLineItem> periodLineItemCostElementQueryList = lineItemQueryList.filter(costElementEquals);
        return periodLineItemCostElementQueryList;
    }

    /**
     * This method is to group non-personnel items for budget summary
     * Start building data structure applicable for non-personnel items to format it with
     * required details for budget summary
     * @param budgetPeriod
     * @param uniqueBudgetCategoryLineItemCostElements
     * @return
     */
    private LineItemGroup getNonPersonnelBudgetSummaryPeriods(BudgetPeriod budgetPeriod, SortedMap<BudgetCategoryType, SortedMap<CostElement, List<BudgetLineItem>>> uniqueBudgetCategoryLineItemCostElements) {
        LineItemGroup nonPersonnelGroup = new LineItemGroup(BUDGET_SUMMARY_NONPERSONNEL_GROUP_LABEL, true);
        for(Map.Entry<BudgetCategoryType, SortedMap<CostElement, List<BudgetLineItem>>> uniqueBudgetCategory : uniqueBudgetCategoryLineItemCostElements.entrySet()) {
            ScaleTwoDecimal totalForCategory = ScaleTwoDecimal.ZERO;
            BudgetCategoryType budgetCategoryType = uniqueBudgetCategory.getKey();
            SortedMap<CostElement, List<BudgetLineItem>> uniqueLineItemCostElements = uniqueBudgetCategory.getValue();
            LineItemObject lineItemCategory = new LineItemObject(budgetCategoryType.getCode(), budgetCategoryType.getDescription(), ScaleTwoDecimal.ZERO);

            for(Map.Entry<CostElement, List<BudgetLineItem>> uniqueLineItem : uniqueLineItemCostElements.entrySet()) {
         		CostElement costElement = uniqueLineItem.getKey();
                QueryList<BudgetLineItem> periodLineItemCostElementQueryList = getLineItemsFilteredByCostElement(budgetPeriod, costElement.getCostElement());
                ScaleTwoDecimal totalForCostElement = ScaleTwoDecimal.ZERO;
                if(periodLineItemCostElementQueryList != null) {
                    totalForCostElement = periodLineItemCostElementQueryList.sumObjects("lineItemCost");
                }
                LineItemObject costElementLineItemObject = new LineItemObject(costElement.getCostElement(), costElement.getDescription(), totalForCostElement);
                lineItemCategory.getLineItems().add(costElementLineItemObject);
                totalForCategory = totalForCategory.add(totalForCostElement);
            }
            lineItemCategory.setAmount(totalForCategory);
            nonPersonnelGroup.getLineItems().add(lineItemCategory);
        }
       return nonPersonnelGroup;
    }
    
    /**
     * This method is to group totals for budget summary
     * get budget summary totals - total direct and f&a
     * @param budgetPeriod
     * @return
     */
    private LineItemGroup getBudgetSummaryTotals(BudgetPeriod budgetPeriod) {
        LineItemGroup totalsGroup = new LineItemGroup(BUDGET_SUMMARY_TOTALS_GROUP_LABEL, true);
        LineItemObject totalDirectCost = new LineItemObject(BudgetSummaryConstants.TotalDirectCost.getKey(), BudgetSummaryConstants.TotalDirectCost.getLabel(), 
        		budgetPeriod.getTotalDirectCost());
        LineItemObject totalFnACost = new LineItemObject(BudgetSummaryConstants.TotalFnACost.getKey(), BudgetSummaryConstants.TotalFnACost.getLabel(), 
        		budgetPeriod.getTotalIndirectCost());
        totalsGroup.getLineItems().add(totalDirectCost);
        totalsGroup.getLineItems().add(totalFnACost);
       return totalsGroup;
    }
    
    /**
     * This method is to get personnel line item details associated with a cost element
     * @param personnelLineItemsForCostElement
     * @return
     */
    private QueryList<BudgetPersonnelDetails> getBudgetPersonnelDetails(List<BudgetLineItem> personnelLineItemsForCostElement) {
        QueryList<BudgetPersonnelDetails> personnelQueryList = new QueryList<BudgetPersonnelDetails>();
    	if(personnelLineItemsForCostElement != null) {
            for(BudgetLineItem budgetLineItem : personnelLineItemsForCostElement) {
                personnelQueryList.addAll(budgetLineItem.getBudgetPersonnelDetailsList());
            }
    	}
        return personnelQueryList;
    }
    
    /**
     * This method is to get a unique list of persons associated to personnel line items for all periods
     * @param personnelLineItems
     * @return
     */
    private Map<String, String> getUniquePersonList(List<BudgetLineItem> personnelLineItems) {
        Map<String, String> uniquePersonList = new HashMap<String,String>();
        for(BudgetLineItem budgetLineItem : personnelLineItems) {
            for(BudgetPersonnelDetails budgetPersonnelDetail : budgetLineItem.getBudgetPersonnelDetailsList()) {
            	uniquePersonList.put(budgetPersonnelDetail.getPersonId(), budgetPersonnelDetail.getBudgetPerson().getPersonName());
            }
        }
        return uniquePersonList;
    }
    
    private String getPersonnelBudgetCategoryTypeCode() {
        return this.getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_BUDGET, ParameterConstants.DOCUMENT_COMPONENT,Constants.BUDGET_CATEGORY_TYPE_PERSONNEL);
    }

    /**
     * Calculate direct cost for given period
     * @param budgetPeriod
     * @param personnelFlag
     * @param personnelCategoryTypeCode
     * @return
     */
    private ScaleTwoDecimal getCalculateBudgetSummaryExpenseTotal(BudgetPeriod budgetPeriod, boolean personnelFlag, String personnelCategoryTypeCode){
        ScaleTwoDecimal calculatedExpenseTotal = ScaleTwoDecimal.ZERO;
        SortedMap<RateType, QueryList<BudgetLineItemCalculatedAmount>> uniqueLineItemCalAmounts = getBudgetSummaryUniqueRateTypeCalAmounts(budgetPeriod.getBudgetLineItems(), 
        		personnelFlag, personnelCategoryTypeCode);
        for(Map.Entry<RateType, QueryList<BudgetLineItemCalculatedAmount>> uniqueLineItem : uniqueLineItemCalAmounts.entrySet()) {
        	RateType rateType = uniqueLineItem.getKey();
        	QueryList<BudgetLineItemCalculatedAmount> lineItemCalAmounts = uniqueLineItem.getValue();
            RateClass rateClass = rateType.getRateClass();
            if (((personnelFlag && rateClass != null && !StringUtils.equals(rateClass.getRateClassTypeCode(), RateClassType.EMPLOYEE_BENEFITS.getRateClassType())) || !personnelFlag)) {
                if(!StringUtils.equals(rateClass.getRateClassTypeCode(), RateClassType.OVERHEAD.getRateClassType())) {
                    ScaleTwoDecimal rateTypeTotalInThisPeriod = lineItemCalAmounts.sumObjects("calculatedCost");
                    calculatedExpenseTotal = calculatedExpenseTotal.add(rateTypeTotalInThisPeriod); 
                }
            }
        }
        return calculatedExpenseTotal;
    }
    
    /**
     * This method is to categorize line item calculated amounts based on rate type
     * @param budgetLineItems
     * @param personnelFlag
     * @param personnelCategoryTypeCode
     * @return
     */
    private SortedMap<RateType, QueryList<BudgetLineItemCalculatedAmount>> getBudgetSummaryUniqueRateTypeCalAmounts(List<BudgetLineItem> budgetLineItems, boolean personnelFlag, String personnelCategoryTypeCode) {
    	SortedMap<RateType, QueryList<BudgetLineItemCalculatedAmount>> uniqueLineItemCalAmounts = new TreeMap<RateType, QueryList<BudgetLineItemCalculatedAmount>>();
       	for(BudgetLineItem budgetLineItem : budgetLineItems) {
            if((personnelFlag && StringUtils.equals(budgetLineItem.getCostElementBO().getBudgetCategory().getBudgetCategoryTypeCode(), personnelCategoryTypeCode)) ||  
                    (!personnelFlag && !StringUtils.equals(budgetLineItem.getCostElementBO().getBudgetCategory().getBudgetCategoryTypeCode(), personnelCategoryTypeCode))  ) {
           		for(BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmount : budgetLineItem.getBudgetLineItemCalculatedAmounts()) {
               		RateType rateType = getRateType(budgetLineItemCalculatedAmount);
                    if(!uniqueLineItemCalAmounts.containsKey(rateType)) {
                    	uniqueLineItemCalAmounts.put(rateType, new QueryList<BudgetLineItemCalculatedAmount>());
                    }
                    uniqueLineItemCalAmounts.get(rateType).add(budgetLineItemCalculatedAmount);
           		}
       		}
        }
       	return uniqueLineItemCalAmounts;
    }
    
    private RateType getRateType(BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmount) {
    	if(budgetLineItemCalculatedAmount.getRateTypeCode() != null && budgetLineItemCalculatedAmount.getRateType() == null) {
    		getDataObjectService().wrap(budgetLineItemCalculatedAmount).fetchRelationship("rateType");
    	}
    	return budgetLineItemCalculatedAmount.getRateType();
    }

    public void updateBudgetTotalCost(Budget budget) {
        ScaleTwoDecimal totalDirectCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalIndirectCost = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalCost = ScaleTwoDecimal.ZERO;
        for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
            if (budgetPeriod.getTotalDirectCost().isGreaterThan(ScaleTwoDecimal.ZERO)
                    || budgetPeriod.getTotalIndirectCost().isGreaterThan(ScaleTwoDecimal.ZERO)) {
                budgetPeriod.setTotalCost(budgetPeriod.getTotalDirectCost().add(budgetPeriod.getTotalIndirectCost()));
            }
            totalDirectCost = totalDirectCost.add(budgetPeriod.getTotalDirectCost());
            totalIndirectCost = totalIndirectCost.add(budgetPeriod.getTotalIndirectCost());
            totalCost = totalCost.add(budgetPeriod.getTotalCost());
        }
        budget.setTotalDirectCost(totalDirectCost);
        budget.setTotalIndirectCost(totalIndirectCost);
        budget.setTotalCost(totalCost);
    }
    
	public DataObjectService getDataObjectService() {
		return dataObjectService;
	}
	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}
    
}
