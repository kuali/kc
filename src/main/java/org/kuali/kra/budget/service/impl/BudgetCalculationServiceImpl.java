/*
 * Copyright 2006-2008 The Kuali Foundation
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
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.bo.CostElement;
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
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;


/**
 * This class implements all methods declared in BudgetCalculationService
 */
public class BudgetCalculationServiceImpl implements BudgetCalculationService {
    private BusinessObjectService businessObjectService;
    /**
     * @see org.kuali.kra.budget.service.BudgetCalculationService#calculateBudget(java.lang.String, java.lang.Integer)
     */
    public void calculateBudget(BudgetDocument budgetDocument){
        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudgetPeriods();
        String ohRateClassCodePrevValue = null;
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            if(isCalculationRequired(budgetDocument,budgetPeriod)){
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
//            List<BudgetLineItem> cvLineItemDetails = budgetPeriod.getBudgetLineItems();
//            if(cvLineItemDetails.isEmpty() ){
//                Map fieldValues = new HashMap();
//                fieldValues.put("proposalNumber", budgetPeriod.getProposalNumber());
//                fieldValues.put("budgetVersionNumber", budgetPeriod.getBudgetVersionNumber());
//                fieldValues.put("budgetPeriod", budgetPeriod.getBudgetPeriod());
//                Collection<BudgetLineItem> deletedLineItems = businessObjectService.findMatching(BudgetLineItem.class, fieldValues);
//                if(!deletedLineItems.isEmpty()){
//                    
//                }
//            }
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
    private boolean isCalculationRequired(BudgetDocument budgetDocument,BudgetPeriod budgetPeriod){
        List<BudgetLineItem> lineItemDetails = budgetPeriod.getBudgetLineItems();
        if(lineItemDetails.isEmpty() && !budgetDocument.isBudgetLineItemDeleted()){
            Map fieldValues = new HashMap();
            fieldValues.put("proposalNumber", budgetPeriod.getProposalNumber());
            fieldValues.put("budgetVersionNumber", budgetPeriod.getBudgetVersionNumber());
            fieldValues.put("budgetPeriod", budgetPeriod.getBudgetPeriod());
            Collection<BudgetLineItem> deletedLineItems = businessObjectService.findMatching(BudgetLineItem.class, fieldValues);
            return !deletedLineItems.isEmpty();
        }else{
            return true;
        }
            
    }
    private void copyLineItemToPersonnelDetails(BudgetLineItem budgetLineItem, BudgetPersonnelDetails budgetPersonnelDetails) {
        budgetPersonnelDetails.setProposalNumber(budgetLineItem.getProposalNumber());
        budgetPersonnelDetails.setBudgetVersionNumber(budgetLineItem.getBudgetVersionNumber());
        budgetPersonnelDetails.setBudgetPeriod(budgetLineItem.getBudgetPeriod());
        budgetPersonnelDetails.setLineItemNumber(budgetLineItem.getLineItemNumber());
        budgetPersonnelDetails.setCostElement(budgetLineItem.getCostElement());
        budgetPersonnelDetails.setCostElementBO(budgetLineItem.getCostElementBO());
    }

    public void calculateBudgetLineItem(BudgetDocument budgetDocument,BudgetPersonnelDetails budgetLineItem){
        new PersonnelLineItemCalculator(budgetDocument,budgetLineItem).calculate();
    }
    /**
     * @see org.kuali.kra.budget.service.BudgetCalculationService#calculateBudgetLineItem(org.kuali.kra.budget.bo.BudgetLineItem)
     */
    public void calculateBudgetLineItem(BudgetDocument budgetDocument,BudgetLineItem budgetLineItem){
        BudgetLineItem budgetLineItemToCalc = (BudgetLineItem)budgetLineItem;
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
        if(isCalculationRequired(budgetDocument,budgetPeriod)){
            new BudgetPeriodCalculator().calculate(budgetDocument, budgetPeriod);
        }
    }
    
    public void calculateAndSyncBudgetPeriod(BudgetDocument budgetDocument, BudgetPeriod budgetPeriod){
        calculateBudgetPeriod(budgetDocument, budgetPeriod);
        syncCostsToBudgetDocument(budgetDocument);
    }
    private void syncCostsToBudgetDocument(BudgetDocument budgetDocument){
        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudgetPeriods();
        BudgetDecimal totalDirectCost = BudgetDecimal.ZERO;
        BudgetDecimal totalIndirectCost = BudgetDecimal.ZERO;
        BudgetDecimal totalCost = BudgetDecimal.ZERO;
        BudgetDecimal totalUnderrecoveryAmount = BudgetDecimal.ZERO; 
        BudgetDecimal totalCostSharingAmount = BudgetDecimal.ZERO;
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            List<BudgetLineItem> budgetLineItems = budgetPeriod.getBudgetLineItems();
//            if(budgetLineItems.isEmpty()){
            if(isCalculationRequired(budgetDocument,budgetPeriod)){
                QueryList<BudgetLineItem> qlBudgetLineItems = new QueryList<BudgetLineItem>(budgetLineItems);
                BudgetDecimal directCost = qlBudgetLineItems.sumObjects("directCost");
                BudgetDecimal indirectCost = qlBudgetLineItems.sumObjects("indirectCost");
                BudgetDecimal costSharingAmount = qlBudgetLineItems.sumObjects("totalCostSharingAmount");
                BudgetDecimal underrecoveryAmount = qlBudgetLineItems.sumObjects("underrecoveryAmount");
                budgetPeriod.setTotalDirectCost(directCost);
                budgetPeriod.setTotalIndirectCost(indirectCost);
                budgetPeriod.setTotalCost(directCost.add(indirectCost));
                budgetPeriod.setUnderrecoveryAmount(underrecoveryAmount);
                budgetPeriod.setCostSharingAmount(costSharingAmount);
                totalDirectCost = totalDirectCost.add(directCost);
                totalIndirectCost = totalIndirectCost.add(indirectCost);
                // change for jira-1341 - if only trc entered, then keep it, so it's not dc+idc
                //totalCost = totalCost.add(directCost.add(indirectCost));
                totalCost = totalCost.add(budgetPeriod.getTotalCost());
                totalUnderrecoveryAmount = totalUnderrecoveryAmount.add(underrecoveryAmount);
                totalCostSharingAmount = totalCostSharingAmount.add(costSharingAmount);
           }else{
               totalDirectCost = totalDirectCost.add(budgetPeriod.getTotalDirectCost());
               totalIndirectCost = totalIndirectCost.add(budgetPeriod.getTotalIndirectCost());
               //totalCost = totalCost.add(budgetPeriod.getTotalDirectCost().add(budgetPeriod.getTotalIndirectCost()));
               totalCost = totalCost.add(budgetPeriod.getTotalCost());
               totalUnderrecoveryAmount = totalUnderrecoveryAmount.add(budgetPeriod.getUnderrecoveryAmount());
               totalCostSharingAmount = totalCostSharingAmount.add(budgetPeriod.getCostSharingAmount());
            }
        }
        budgetDocument.setTotalDirectCost(totalDirectCost);
        budgetDocument.setTotalIndirectCost(totalIndirectCost);
        budgetDocument.setTotalCost(totalCost);
        budgetDocument.setUnderrecoveryAmount(totalUnderrecoveryAmount);
        budgetDocument.setCostSharingAmount(totalCostSharingAmount);
    }

    /**
     * 
     * @see org.kuali.kra.budget.service.BudgetCalculationService#calculateBudgetTotals(org.kuali.kra.budget.document.BudgetDocument)
     */
    @SuppressWarnings("unchecked")
    public void calculateBudgetTotals(BudgetDocument budgetDocument){
        // do we need to cache the totals ?
        SortedMap<CostElement, List> objectCodeTotals = new TreeMap <CostElement, List> ();
        SortedMap<RateType, List> calculatedExpenseTotals = new TreeMap <RateType, List> ();
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
        if(!errors.isEmpty()){
          //TODO:handle errors
        }
    }

    public void applyToLaterPeriods(BudgetDocument budgetDocument, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem) {
        BudgetPeriodCalculator periodCalculator = new BudgetPeriodCalculator();
        periodCalculator.applyToLaterPeriods(budgetDocument, budgetPeriod, budgetLineItem);
        List<String> errors = periodCalculator.getErrorMessages();
        if(!errors.isEmpty()){
            //TODO:handle errors
        }
    }
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
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
        for(BudgetPersonnelDetails budgetPersonnelDetails: budgetLineItem.getBudgetPersonnelDetailsList()){
            List<BudgetPersonnelCalculatedAmount> personnelCalculatedAmounts = budgetPersonnelDetails.getBudgetPersonnelCalculatedAmounts();
            List<BudgetLineItemCalculatedAmount> lineItemCalculatedAmounts = budgetLineItem.getBudgetLineItemCalculatedAmounts();

            int minSize = Math.min(lineItemCalculatedAmounts.size(), personnelCalculatedAmounts.size());

            for (int j = 0; j < minSize; j++) {
                personnelCalculatedAmounts.get(j).setApplyRateFlag(budgetLineItem.getBudgetLineItemCalculatedAmounts().get(j).getApplyRateFlag());                        
            }
        }
    }
}
