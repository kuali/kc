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
package org.kuali.kra.budget.service.impl;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.BudgetException;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetLineItemBase;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.calculator.BudgetPeriodCalculator;
import org.kuali.kra.budget.calculator.LineItemCalculator;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetCalculationService;


/**
 * This class...
 */
public class BudgetCalculationServiceImpl implements BudgetCalculationService {

    /**
     * @see org.kuali.kra.budget.service.BudgetCalculationService#calculateBudget(java.lang.String, java.lang.Integer)
     */
    public void calculateBudget(BudgetDocument budgetDocument){

    }

    /**
     * @see org.kuali.kra.budget.service.BudgetCalculationService#calculateBudgetLineItem(org.kuali.kra.budget.bo.BudgetLineItem)
     */
    public void calculateBudgetLineItem(BudgetDocument budgetDocument,BudgetLineItemBase budgetLineItem){
        if(budgetLineItem instanceof BudgetLineItem){
            new LineItemCalculator(budgetDocument,(BudgetLineItem)budgetLineItem).calculate();
        }else if(budgetLineItem instanceof BudgetPersonnelDetails){
            
        }
    }

    /**
     * @see org.kuali.kra.budget.service.BudgetCalculationService#calculateCalculatedAmount(org.kuali.kra.budget.bo.BudgetLineItem)
     */
    public void calculateCalculatedAmount(BudgetLineItem budgetLineItem){
        String costElement = budgetLineItem.getCostElement();
        budgetLineItem.setDirectCost(budgetLineItem.getLineItemCost());
        budgetLineItem.setIndirectCost(BudgetDecimal.ZERO);
        budgetLineItem.setUnderrecoveryAmount(BudgetDecimal.ZERO);
        
    }

//    public void initValues() throws CoeusException{
//        
//        //set per day cost & per day cost sharing
//        setPerDayCost();
//        setPerDayCostSharing();
//        
//        cvLineItemPropLARates = new CoeusVector();
//        cvLineItemPropRates = new CoeusVector();
//        
//        //get the on-off campus flag
//        boolean onOffCampusFlag = budgetDetailBean.isOnOffCampusFlag();
//        Equals equalsOnOff = new Equals("onOffCampusFlag", onOffCampusFlag);
//        //System.out.println("onOffCampusFlag >> " + onOffCampusFlag);
//        
//        //get the line item cal amts
//        Equals equalsPeriod = new Equals("budgetPeriod", 
//                                    new Integer(budgetDetailBean.getBudgetPeriod()));
//        Equals equalsLineItemNo = new Equals("lineItemNumber", 
//                                    new Integer(budgetDetailBean.getLineItemNumber()));
//        And periodANDLineItemNo = new And(equalsPeriod, equalsLineItemNo);
//        
//        //Check whether to get cal amounts from Query Engine
//        if (getCalAmtFromQE) {
//            cvLineItemCalcAmts = queryEngine.getActiveData(key, 
//                            BudgetDetailCalAmountsBean.class, periodANDLineItemNo);
//        } else {
//            setGetCalAmtFromQE(true);
//        }
//        //System.out.println("cvLineItemCalcAmts.size() >> " + cvLineItemCalcAmts.size());
//        
//        /**
//         * Get all rates from Proposal Rates & Proposal LA Rates which matches 
//         * with the rates in line item cal amts
//         */
//        if (cvLineItemCalcAmts != null && cvLineItemCalcAmts.size() > 0) {
//            int calAmtSize = cvLineItemCalcAmts.size();
//            BudgetDetailCalAmountsBean calAmtsBean;
//            int rateClassCode, rateTypeCode = 0;
//            String rateClassType = "";
//            Equals equalsRC;
//            Equals equalsRT;
//            And RCandRT;
//            And RCRTandOnOff;
//            
//            for(int index=0; index < calAmtSize; index++) {
//                CoeusVector ratesVector = new CoeusVector();
//                calAmtsBean = (BudgetDetailCalAmountsBean) cvLineItemCalcAmts.get(index);
//                rateClassCode = calAmtsBean.getRateClassCode();
//                rateTypeCode = calAmtsBean.getRateTypeCode();
//                rateClassType = calAmtsBean.getRateClassType();
//        //System.out.println("rateClassCode >> " + rateClassCode);
//        //System.out.println("rateTypeCode >> " + rateTypeCode);
//        //System.out.println("rateClassType >> " + rateClassType);
//                equalsRC = new Equals("rateClassCode", new Integer(rateClassCode));
//                equalsRT = new Equals("rateTypeCode", new Integer(rateTypeCode));
//                RCandRT = new And(equalsRC, equalsRT);
//                RCRTandOnOff = new And(RCandRT, equalsOnOff);
//                
//                if (rateClassType.equalsIgnoreCase("L") ||
//                    rateClassType.equalsIgnoreCase("Y")) {
//                    ratesVector = queryEngine.getActiveData(key, 
//                                                ProposalLARatesBean.class, RCRTandOnOff);
//                    if (ratesVector != null && ratesVector.size() > 0) {
//                        cvLineItemPropLARates.addAll(ratesVector);
//                    }
//                } else {
//                    ratesVector = queryEngine.getActiveData(key, 
//                                                ProposalRatesBean.class, RCRTandOnOff);
//            //System.out.println("ratesVector.size() >> " + ratesVector.size());
//                    if (ratesVector != null && ratesVector.size() > 0) {
//                        cvLineItemPropRates.addAll(ratesVector);
//                    }
//                }
//                
//            }
//                
//            //Get all the Under-recovery rates if UR Rate differs from OH Rate
//            if (!isURMatchesOh()) {
//                equalsRC = new Equals("rateClassCode", new Integer(uRRateClassCode));
//                equalsRT = new Equals("rateTypeCode", new Integer(uRRateTypeCode));
//                RCandRT = new And(equalsRC, equalsRT);
//                RCRTandOnOff = new And(RCandRT, equalsOnOff);
//                cvUnderRecoveryRates = queryEngine.getActiveData(key, 
//                                            ProposalRatesBean.class, RCRTandOnOff);
//
//            }
//            //System.out.println("cvLineItemPropLARates.size() >> " + cvLineItemPropLARates.size());
//            //System.out.println("cvLineItemPropRates.size() >> " + cvLineItemPropRates.size());
//        }
//    } // end initValues    
    

    /**
     * @see org.kuali.kra.budget.service.BudgetCalculationService#calculateSalary(org.kuali.kra.budget.bo.BudgetPersonnelDetails)
     */
    public void calculateSalary(BudgetPersonnelDetails budgetPersnnelLineItem){
        // TODO Auto-generated method stub

    }

    public void calculateBudgetPeriod(BudgetDocument budgetDocument, BudgetPeriod budgetPeriod){
        new BudgetPeriodCalculator().calculate(budgetDocument, budgetPeriod);
    }

}
