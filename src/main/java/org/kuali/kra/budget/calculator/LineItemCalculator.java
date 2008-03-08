/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.budget.calculator;

import java.util.List;

import org.kuali.core.service.DateTimeService;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetCalculationService;
import org.kuali.kra.infrastructure.KraServiceLocator;
/**
 * 
 * This class for calculating non personnel line item
 */
public class LineItemCalculator extends CalculatorBase {
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
    public void populateCalculatedAmountLineItems() {
        if (bli.getBudgetLineItemCalculatedAmounts().size() <= 0) {
            bli.refreshReferenceObject("budgetLineItemCalculatedAmounts");
        }
        if (bli.getBudgetLineItemCalculatedAmounts().size() <= 0) {
            setCalculatedAmounts(bd,bli);
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
        boundary.setApplicableCost(lineItemCost==null?BudgetDecimal.ZERO:
            lineItemCost.divide(new BudgetDecimal(totalNumOfDays)).multiply(new BudgetDecimal(boundaryNumOfDays)));
        boundary.setApplicableCostSharing(lineItemCostSharing==null?BudgetDecimal.ZERO:
            lineItemCostSharing.divide(new BudgetDecimal(totalNumOfDays)).
                multiply(new BudgetDecimal(boundaryNumOfDays)));
    }
}
