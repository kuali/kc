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
package org.kuali.kra.budget.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.bo.BudgetRateAndBase;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetCalculationService;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.util.GlobalVariables;

import static org.kuali.kra.logging.BufferedLogger.*;

/**
 * 
 * This class for calculating non personnel line item
 */
public class LineItemCalculator extends AbstractBudgetCalculator {
    private BudgetDocument bd;
    private BudgetLineItem bli;
    private DateTimeService dateTimeService;
    private BudgetCalculationService budgetCalculationService;
    public LineItemCalculator(BudgetDocument bd,BudgetLineItem bli){
        super(bd,bli);
        this.bli = bli;
        this.bd = bd;
        dateTimeService = getDateTimeService();
        budgetCalculationService = KraServiceLocator.getService(BudgetCalculationService.class); 
    }

    private boolean isDocumentOhRateSameAsFormOhRate() {
        if(bd.getOhRateClassCode()!= null && ((BudgetForm)GlobalVariables.getKualiForm())!= null && StringUtils.equalsIgnoreCase(bd.getOhRateClassCode(), ((BudgetForm)GlobalVariables.getKualiForm()).getOhRateClassCodePrevValue())){
            return true;
        }
        
        return false;        
    }
    
    private Map<String, Boolean> saveApplyRateFlagsForReset() {
        Map<String, Boolean> applyRateFlags = new HashMap<String, Boolean>();
        if(bli != null && CollectionUtils.isNotEmpty(bli.getBudgetLineItemCalculatedAmounts())) {
            for(BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmount : bli.getBudgetLineItemCalculatedAmounts()) {
                applyRateFlags.put(budgetLineItemCalculatedAmount.getRateClassCode()+budgetLineItemCalculatedAmount.getRateTypeCode(), budgetLineItemCalculatedAmount.getApplyRateFlag());
            }
        }
        
        return applyRateFlags;
    }

    public void populateCalculatedAmountLineItems() {
        if (bli.getBudgetLineItemCalculatedAmounts().size() <= 0) {
            bli.refreshReferenceObject("budgetLineItemCalculatedAmounts");
        }
        if (bli.getBudgetLineItemCalculatedAmounts().size() <= 0) {
            setCalculatedAmounts(bd,bli);
        }

        if(!isDocumentOhRateSameAsFormOhRate()){
            Long versionNumber = null;
            if (CollectionUtils.isNotEmpty(bli.getBudgetLineItemCalculatedAmounts())) {
                versionNumber = bli.getBudgetLineItemCalculatedAmounts().get(0).getVersionNumber();
            }
            //Save applyRateFlag to set it back on the new Calculated Amounts
            Map<String, Boolean> applyRateFlags = saveApplyRateFlagsForReset();

            bli.setBudgetLineItemCalculatedAmounts(new ArrayList<BudgetLineItemCalculatedAmount>());
            
            setCalculatedAmounts(bd,bli);
            
            for(BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmount : bli.getBudgetLineItemCalculatedAmounts()){
                if (versionNumber != null) {
                        budgetLineItemCalculatedAmount.setVersionNumber(versionNumber);
                }
                
                if(applyRateFlags != null && applyRateFlags.get(budgetLineItemCalculatedAmount.getRateClassCode() + budgetLineItemCalculatedAmount.getRateTypeCode()) != null) {
                    budgetLineItemCalculatedAmount.setApplyRateFlag(applyRateFlags.get(budgetLineItemCalculatedAmount.getRateClassCode() + budgetLineItemCalculatedAmount.getRateTypeCode()));
                }
            }
        }
         
    }    
//    public void calculate(){
//        bli.setDirectCost(bli.getLineItemCost());
//        bli.setIndirectCost(BudgetDecimal.ZERO);
//        boolean OHAvailable = true;
//        bli.setUnderrecoveryAmount(BudgetDecimal.ZERO);
//        createAndCalculateBreakupIntervals();
//        updateBudgetLineItemCostsAndCalAmts();
////        if (!uRMatchesOh) {
////            // Check whether any OH Rate is present
////            Equals eqRateClassType = new Equals("rateClassType", RateClassTypeConstants.OVERHEAD);
////            CoeusVector cvOHRate = cvLineItemCalcAmts.filter(eqRateClassType);
////            if (cvOHRate == null || cvOHRate.size() == 0) {
////                OHAvailable = false;
////            }
////        }else {
////            budgetDetailBean.setTotalCostSharing(budgetDetailBean.getCostSharingAmount());
////        }
////        if (!uRMatchesOh && (!OHAvailable || cvLineItemCalcAmts == null || cvLineItemCalcAmts.size() == 0)) {
////            calculateURBase();
////        }
//
//    }
//
    /*
     * unitcost = (totalcost/totalnumofdays)*actualnumofdays
     * 
     */
    @Override
    public void populateApplicableCosts(Boundary boundary) {
        int totalNumOfDays = dateTimeService.dateDiff(this.bli.getStartDate(), this.bli.getEndDate(), true);
        int boundaryNumOfDays = boundary.getNumberOfDays();
        
        List<BudgetPersonnelDetails> personnelDetailsList = bli.getBudgetPersonnelDetailsList();
        BudgetDecimal salaryRequested = BudgetDecimal.ZERO;
        BudgetDecimal costSharingRequested = BudgetDecimal.ZERO;
        if(!personnelDetailsList.isEmpty()){
            for (BudgetPersonnelDetails budgetPersonnelDetails : personnelDetailsList) {
                budgetCalculationService.calculateBudgetLineItem(bd, budgetPersonnelDetails);
                salaryRequested = salaryRequested.add(budgetPersonnelDetails.getSalaryRequested());
                costSharingRequested = costSharingRequested.add(budgetPersonnelDetails.getCostSharingAmount());
            }
            bli.setLineItemCost(salaryRequested);
            bli.setCostSharingAmount(costSharingRequested);
        }
        BudgetDecimal lineItemCost = bli.getLineItemCost();
        BudgetDecimal lineItemCostSharing = bli.getCostSharingAmount();
        boundary.setApplicableCost(lineItemCost==null?BudgetDecimal.ZERO:new BudgetDecimal(lineItemCost.bigDecimalValue().multiply(((new BigDecimal(boundaryNumOfDays)))).divide(new BigDecimal(totalNumOfDays),2, RoundingMode.HALF_UP)));
        boundary.setApplicableCostSharing(lineItemCostSharing==null?BudgetDecimal.ZERO:new BudgetDecimal(lineItemCostSharing.bigDecimalValue().multiply(((new BigDecimal(boundaryNumOfDays)))).divide(new BigDecimal(totalNumOfDays),2, RoundingMode.HALF_UP)));
    }
    @Override
    protected void addBudgetLineItemCalculatedAmount(String rateClassCode, String rateTypeCode, String rateClassType) {
        BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmt = new BudgetLineItemCalculatedAmount();
        budgetLineItemCalculatedAmt.setProposalNumber(bli.getProposalNumber());
        budgetLineItemCalculatedAmt.setBudgetVersionNumber(bli.getBudgetVersionNumber());
        budgetLineItemCalculatedAmt.setBudgetPeriod(bli.getBudgetPeriod());
        budgetLineItemCalculatedAmt.setBudgetPeriodId(bli.getBudgetPeriodId());
        budgetLineItemCalculatedAmt.setLineItemNumber(bli.getLineItemNumber());
        budgetLineItemCalculatedAmt.setRateClassType(rateClassType);
        budgetLineItemCalculatedAmt.setRateClassCode(rateClassCode);
        budgetLineItemCalculatedAmt.setRateTypeCode(rateTypeCode);
        // budgetLineItemCalculatedAmt.setRateClassDescription(validCeRateType.getRateClass().getDescription());
        // budgetLineItemCalculatedAmt.setRateTypeDescription(validCERateTypesBean.getRateTypeDescription());
        budgetLineItemCalculatedAmt.setApplyRateFlag(true);
        budgetLineItemCalculatedAmt.refreshReferenceObject("rateType");
        budgetLineItemCalculatedAmt.refreshReferenceObject("rateClass");
        bli.getBudgetLineItemCalculatedAmounts().add(budgetLineItemCalculatedAmt);
    }
    @Override
    protected void populateBudgetRateBaseList() {
        List<BudgetRateAndBase> budgetRateAndBaseList = bli.getBudgetRateAndBaseList();
        List<BreakUpInterval> breakupIntervals = getBreakupIntervals();
        Long prevVersionNumber = null;
        if(!budgetRateAndBaseList.isEmpty()){
            prevVersionNumber = budgetRateAndBaseList.get(0).getVersionNumber();
            budgetRateAndBaseList.clear();
        }
        Integer rateNumber = 0;
        for (BreakUpInterval breakUpInterval : breakupIntervals) {
            List<RateAndCost> vecAmountBean = breakUpInterval.getRateAndCosts();
            for (RateAndCost rateAndCost : vecAmountBean) {
                BudgetRateAndBase budgetRateBase = new BudgetRateAndBase();
                BudgetDecimal appliedRate = rateAndCost.getAppliedRate();
                budgetRateBase.setAppliedRate(BudgetDecimal.returnZeroIfNull(appliedRate));
                BudgetDecimal calculatedCost = rateAndCost.getCalculatedCost();
                BudgetDecimal calculatedCostSharing = rateAndCost.getCalculatedCostSharing();
                
//                budgetRateBase.setBaseCost(breakUpInterval.getApplicableAmt());
                budgetRateBase.setBaseCostSharing(rateAndCost.getBaseCostSharingAmount());
                budgetRateBase.setBaseCost(rateAndCost.getBaseAmount());
//              budgetRateBase.setBaseCostSharing(breakUpInterval.getApplicableAmtCostSharing());
                
                budgetRateBase.setBudgetPeriodId(bli.getBudgetPeriodId());
                budgetRateBase.setBudgetPeriod(bli.getBudgetPeriod());
                budgetRateBase.setCalculatedCost(calculatedCost);
                budgetRateBase.setCalculatedCostSharing(calculatedCostSharing);
                
                java.util.Date endDate = breakUpInterval.getBoundary().getEndDate();
                budgetRateBase.setEndDate(new java.sql.Date(endDate.getTime()));
                
                budgetRateBase.setLineItemNumber(bli.getLineItemNumber());
                budgetRateBase.setOnOffCampusFlag(bli.getOnOffCampusFlag());
                budgetRateBase.setProposalNumber(bli.getProposalNumber());
                budgetRateBase.setRateClassCode(rateAndCost.getRateClassCode());
                budgetRateBase.setRateNumber(++rateNumber);
                budgetRateBase.setRateTypeCode(rateAndCost.getRateTypeCode());
                java.util.Date startDate = breakUpInterval.getBoundary().getStartDate();
                budgetRateBase.setStartDate(new java.sql.Date(startDate.getTime()));
                budgetRateBase.setBudgetVersionNumber(bli.getBudgetVersionNumber());
//                if(prevVersionNumber!=null) budgetRateBase.setVersionNumber(prevVersionNumber);
                budgetRateAndBaseList.add(budgetRateBase);
            }   
        }
    }
}
