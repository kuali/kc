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

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;
import static org.kuali.rice.KNSServiceLocator.getDateTimeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DateTimeService;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.bo.CostElement;
import org.kuali.kra.budget.bo.RateClass;
import org.kuali.kra.budget.bo.RateType;
import org.kuali.kra.budget.bo.ValidCalcType;
import org.kuali.kra.budget.calculator.query.And;
import org.kuali.kra.budget.calculator.query.Equals;
import org.kuali.kra.budget.calculator.query.NotEquals;
import org.kuali.kra.budget.calculator.query.QueryEngine;
import org.kuali.kra.budget.document.BudgetDocument;

public class LineItemCalculator extends CalculatorBase {
    private BudgetDocument bd;
    private BudgetLineItem bli;
    private QueryEngine queryEngine;
    private BusinessObjectService businessObjectService;
    private DateTimeService dateTimeService;
    public LineItemCalculator(BudgetDocument bd,BudgetLineItem bli){
        super(bd,bli);
        this.bli = bli;
        this.bd = bd;
        queryEngine = new QueryEngine();
        businessObjectService = getService(BusinessObjectService.class);
        dateTimeService = getDateTimeService();
    }
    
    public void calculate(){
        bli.setDirectCost(bli.getLineItemCost());
        bli.setIndirectCost(BudgetDecimal.ZERO);
        boolean OHAvailable = true;
        bli.setUnderrecoveryAmount(BudgetDecimal.ZERO);
        createAndCalculateBreakupIntervals();
        updateBudgetLineItemCostsAndCalAmts();
//        if (!uRMatchesOh) {
//            // Check whether any OH Rate is present
//            Equals eqRateClassType = new Equals("rateClassType", RateClassTypeConstants.OVERHEAD);
//            CoeusVector cvOHRate = cvLineItemCalcAmts.filter(eqRateClassType);
//            if (cvOHRate == null || cvOHRate.size() == 0) {
//                OHAvailable = false;
//            }
//        }else {
//            budgetDetailBean.setTotalCostSharing(budgetDetailBean.getCostSharingAmount());
//        }
//        if (!uRMatchesOh && (!OHAvailable || cvLineItemCalcAmts == null || cvLineItemCalcAmts.size() == 0)) {
//            calculateURBase();
//        }

    }

    private void updateBudgetLineItemCostsAndCalAmts() {
        List<BudgetLineItemCalculatedAmount> cvLineItemCalcAmts = bli.getBudgetLineItemCalculatedAmounts();
        List<BreakUpInterval> cvLIBreakupIntervals = getBreakupIntervals();
        if (cvLineItemCalcAmts != null && cvLineItemCalcAmts.size() > 0 
                && cvLIBreakupIntervals != null && cvLIBreakupIntervals.size() > 0) {
            /**
             * Sum up all the calculated costs for each breakup interval and 
             * then update the line item cal amts. 
             */  
            String rateClassCode = "0";
            String rateTypeCode = "0";
            BudgetDecimal totalCalculatedCost = BudgetDecimal.ZERO;
            BudgetDecimal totalCalculatedCostSharing = BudgetDecimal.ZERO;
            BudgetDecimal totalUnderRecovery = BudgetDecimal.ZERO;
            BudgetDecimal directCost = BudgetDecimal.ZERO;
            BudgetDecimal indirectCost = BudgetDecimal.ZERO;
            Equals equalsRC;
            Equals equalsRT;
            And RCandRT = null;
            QueryList cvCombinedAmtDetails = new QueryList();
            //Loop and add all the amount details from all the breakup intervals
            for (BreakUpInterval brkUpInterval : cvLIBreakupIntervals) {
                cvCombinedAmtDetails.addAll(brkUpInterval.getRateAndCosts());
            }
            //loop thru all cal amount rates, sum up the costs and set it
            for (BudgetLineItemCalculatedAmount calculatedAmount : cvLineItemCalcAmts) {
                //if this rate need not be applied skip
                if (!calculatedAmount.getApplyRateFlag()) {
                    continue;
                }
                rateClassCode = calculatedAmount.getRateClassCode();
                rateTypeCode = calculatedAmount.getRateTypeCode();
                equalsRC = new Equals("rateClassCode", rateClassCode);
                equalsRT = new Equals("rateTypeCode", rateTypeCode);
                RCandRT = new And(equalsRC, equalsRT);
                totalCalculatedCost = cvCombinedAmtDetails.sumObjects("calculatedCost", RCandRT);
                
                calculatedAmount.setCalculatedCost(totalCalculatedCost);
                
                totalCalculatedCostSharing = cvCombinedAmtDetails.sumObjects("calculatedCostSharing", RCandRT);
                calculatedAmount.setCalculatedCostSharing(totalCalculatedCostSharing);
            }
            
            /**
             * Sum up all the underRecovery costs for each breakup interval and then update the 
             * line item details.
             */       
            totalUnderRecovery = new QueryList(cvLIBreakupIntervals).sumObjects("underRecovery");
            bli.setUnderrecoveryAmount(totalUnderRecovery);
            
            /**
             * Sum up all direct costs ie, rates for RateClassType <> 'O', for each breakup interval
             * plus the line item cost, and then update the line item details.
             */
            NotEquals notEqualsOH = new NotEquals("rateClassType", RateClassType.OVERHEAD.getRateClassType());
            directCost = cvCombinedAmtDetails.sumObjects("calculatedCost", notEqualsOH);
            bli.setDirectCost(directCost.add(bli.getLineItemCost()));
            /**
             * Sum up all Indirect costs ie, rates for RateClassType = 'O', for each breakup interval 
             * and then update the line item details.
             */
            Equals equalsOH = new Equals("rateClassType", RateClassType.OVERHEAD.getRateClassType());
            indirectCost = cvCombinedAmtDetails.sumObjects("calculatedCost", equalsOH);
            bli.setIndirectCost(indirectCost);
            
            /**
             * Sum up all Cost Sharing amounts ie, rates for RateClassType <> 'O' and set 
             * in the calculatedCostSharing field of line item details
             */
            totalCalculatedCostSharing = cvCombinedAmtDetails.sumObjects("calculatedCostSharing");
//            bli.setTotalCostSharingAmount(bli.getCostSharingAmount().add(totalCalculatedCostSharing));
            
            bli.setCostSharingAmount(bli.getCostSharingAmount()==null?totalCalculatedCostSharing:bli.getCostSharingAmount().add(totalCalculatedCostSharing));
            
        }
    }
//    private void initValues() {
//        
//    }
//
//    private void intializeQueryEngine() {
//        queryEngine.addDataCollection(bd, "budgetProposalRates");
//        List<RateClass> rateClasses = (List)businessObjectService.findAll(RateClass.class);
//        List<RateType> rateTypes = (List)businessObjectService.findAll(RateType.class);
//        List<ValidCalcType> validCalculationTypes = (List)businessObjectService.findAll(ValidCalcType.class);
//        
//        queryEngine.addDataCollection(RateClass.class, rateClasses);
//        queryEngine.addDataCollection(RateClass.class, rateTypes);
//        queryEngine.addDataCollection(RateClass.class, validCalculationTypes);
//        
//        String costElement = bli.getCostElement();
//        Map<String,String> pkMap = new HashMap<String,String>();
//        pkMap.put("costElement", costElement);
//        CostElement costElementBO = (CostElement)businessObjectService.findByPrimaryKey(CostElement.class, pkMap);
//        costElementBO.refreshReferenceObject("validCeRateTypes");
//        queryEngine.addDataCollection(costElementBO, "validCeRateTypes");
//        if(bli.getBudgetLineItemCalculatedAmounts().isEmpty()){
//            bli.refreshReferenceObject("budgetLineItemCalculatedAmounts");
//        }
//    }

    @Override
    public BudgetDecimal getPerDayCost() {
        int noOfDays = dateTimeService.dateDiff(this.bli.getStartDate(), this.bli.getEndDate(), true);
        return bli.getLineItemCost()==null?BudgetDecimal.ZERO:bli.getLineItemCost().divide(new BudgetDecimal(noOfDays));
    }

    @Override
    public BudgetDecimal getPerDayCostSharing() {
        int noOfDays = dateTimeService.dateDiff(this.bli.getStartDate(), this.bli.getEndDate(), true);
        return bli.getCostSharingAmount()==null?BudgetDecimal.ZERO:bli.getCostSharingAmount().divide(new BudgetDecimal(noOfDays));
    }
}
