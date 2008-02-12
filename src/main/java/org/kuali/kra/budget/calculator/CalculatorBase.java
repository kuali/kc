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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DateTimeService;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetLineItemBase;
import org.kuali.kra.budget.bo.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetProposalLaRate;
import org.kuali.kra.budget.bo.BudgetProposalRate;
import org.kuali.kra.budget.bo.CostElement;
import org.kuali.kra.budget.bo.ValidCalcType;
import org.kuali.kra.budget.bo.ValidCeRateType;
import org.kuali.kra.budget.calculator.query.And;
import org.kuali.kra.budget.calculator.query.Equals;
import org.kuali.kra.budget.calculator.query.GreaterThan;
import org.kuali.kra.budget.calculator.query.NotEquals;
import org.kuali.kra.budget.calculator.query.Or;
import org.kuali.kra.budget.calculator.query.QueryEngine;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.budget.calculator.query.LesserThan;
import org.kuali.rice.KNSServiceLocator;

public abstract class CalculatorBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(CalculatorBase.class);
    private BusinessObjectService businessObjectService;
    private DateTimeService dateTimeService;
    private BudgetDocument bd;
    private BudgetLineItemBase bli;
    private List<BreakUpInterval> breakupIntervals;

    public CalculatorBase(BudgetDocument bd,BudgetLineItemBase bli) {
        this.bd = bd;
        this.bli = bli;
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        dateTimeService = KNSServiceLocator.getDateTimeService();
        breakupIntervals = new ArrayList<BreakUpInterval>();
    }

    protected QueryList filterRates(List rates, BudgetLineItemBase bli) {
        //Filter out all rates that lies outside the line item end date
        Date lineItemEndDate = bli.getEndDate();
        LesserThan lesserThan = new LesserThan("startDate", lineItemEndDate);
        Equals equals = new Equals("startDate", lineItemEndDate);
        Or or = new Or(lesserThan, equals);
        
        QueryList budgetProposalRates = new QueryList(rates);
        
        budgetProposalRates = budgetProposalRates.filter(or);
        
        return budgetProposalRates;
        
//        QueryList filteredList = new QueryList();
//        int size = budgetProposalRates.size();
//        while(size>0){
//            BudgetProposalLaRate budgetPropRate = (BudgetProposalLaRate)budgetProposalRates.get(0);
//            Equals eRtCls = new Equals("rateClassCode",budgetPropRate.getRateClassCode());
//            Equals eRtType = new Equals("rateTypeCode",budgetPropRate.getRateTypeCode());
//            Equals eCampFlag = new Equals("onOffCampusFlag",budgetPropRate.getOnOffCampusFlag());
//            Equals eUnitNumber = new Equals("unitNumber",budgetPropRate.getUnitNumber());
//            
//            And eRtClsAndeRtType = new And(eRtCls,eRtType);
//            And eRtClsAndeRtTypeAndeCampFlag = new And(eRtClsAndeRtType,eCampFlag);
//            And eRtClsAndeRtTypeAndeCampFlagAndeUnitNumber = new And(eRtClsAndeRtTypeAndeCampFlag,eUnitNumber);
//            And filterOperator = eRtClsAndeRtTypeAndeCampFlagAndeUnitNumber;
//            if(budgetPropRate instanceof BudgetProposalRate){
//                Equals eActType = new Equals("activityTypeCode",bd.getActivityTypeCode());
//                And eRtClsAndeRtTypeAndeCampFlagAndeUnitNumberAndActType = new And(eRtClsAndeRtTypeAndeCampFlagAndeUnitNumber,eActType);
//                filterOperator = eRtClsAndeRtTypeAndeCampFlagAndeUnitNumberAndActType;
//            }
//            
//            QueryList tempRates = budgetProposalRates.filter(filterOperator);
//            if(tempRates.isEmpty()){
//                break;
//            }
//            GreaterThan gtStDate = new GreaterThan("startDate",bli.getStartDate());
//            QueryList tempGtStDateRates = tempRates.filter(gtStDate);
//            filteredList.addAll(tempGtStDateRates);
//            budgetProposalRates.removeAll(tempRates);
//            size-=tempGtStDateRates.size();
//            LesserThan ltStDate = new LesserThan("startDate",bli.getStartDate());
//            Equals eStDate = new Equals("startDate",bli.getStartDate());
//            Or ltOrEqStDate = new Or(ltStDate,eStDate);
//            tempRates = tempRates.filter(ltOrEqStDate);
//            if(tempRates.size()>0){
//                tempRates.sort("startDate");
//                filteredList.add(tempRates.get(0));
//                size-=tempRates.size();
//            }
//        }
//        return filteredList;
    }
    
    public abstract BudgetDecimal getPerDayCost();
    public abstract BudgetDecimal getPerDayCostSharing();
    
    private boolean isUndercoveryMatchesOverhead(BudgetDocument bd){
        return bd.getOhRateClassCode().equals(bd.getUrRateClassCode());
    }
    protected void createAndCalculateBreakupIntervals(){
        populateCalculatedAmountLineItems();
        createBreakUpInterval();
        calculateBreakUpInterval();
    }
    /**
     * Combine the sorted Prop & LA rates, which should be in sorted
     * order(asc). Now create the breakup boundaries and use it to create 
     * breakup intervals and set all the values required for calculation.
     * Then call calculateBreakupInterval method for each AmountBean for
     * setting the calculated cost & calculated cost sharing ie for each rate
     * class & rate type.
     */
    protected void createBreakUpInterval() {
        LOG.info("Line item details before going to create breakup interval "+bli);
        //Initialize the Message that should be shown if rate not avalilable for any period
        String messageTemplate = "";
        String multipleRatesMesgTemplate = "";
        String message = "";
        if(breakupIntervals==null) breakupIntervals = new ArrayList<BreakUpInterval>();
        
        if (bli.getOnOffCampusFlag()) {
            messageTemplate = "On-Campus rate information not available for Rate Class - \'";
            multipleRatesMesgTemplate = "Multiple On-Campus rates found for the period ";
        } else {
            messageTemplate = "Off-Campus rate information not available for Rate Class - \'";
            multipleRatesMesgTemplate = "Multiple Off-Campus rates found for the period ";
        }
        
        QueryList<BudgetProposalLaRate> qlLineItemPropLaRates = filterRates(bd.getBudgetProposalLaRates(),bli);
        LOG.info("Budget proposal LA rates size is "+qlLineItemPropLaRates.size());
        QueryList<BudgetProposalRate> qlLineItemPropRates = filterRates(bd.getBudgetProposalRates(),bli);
        LOG.info("Budget proposal rates size is "+qlLineItemPropRates.size());
        
        // combine the sorted Prop & LA Rates
        QueryList qlCombinedRates = new QueryList();
        qlCombinedRates.addAll(qlLineItemPropRates);
        qlCombinedRates.addAll(qlLineItemPropLaRates);
        
//        qlCombinedRates.addAll(filterRates(bd.getBudgetProposalRates(),bli));
//        qlCombinedRates.addAll(filterRates(bd.getBudgetProposalLaRates(),bli));
        LOG.info("After filtering combined rates size is "+qlCombinedRates.size());
        //sort the combined rates in asc order
        qlCombinedRates.sort("startDate", true);
        //Now start creating breakup periods
        //First get the breakup boundaries
        Date liStartDate = bli.getStartDate();
        Date liEndDate = bli.getEndDate();
        List<Boundary> boundaries = createBreakupBoundaries(qlCombinedRates, liStartDate,liEndDate);
        LOG.info("Breakup boundaries size is "+boundaries.size());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM-dd-yyyy");
        //create breakup intervals based on the breakup boundaries
        if (boundaries != null && boundaries.size() > 0) {
            for (Boundary boundary : boundaries) {
                BreakUpInterval breakUpInterval = new BreakUpInterval();
                breakUpInterval.setBoundary(boundary);
                breakUpInterval.setProposalNumber(bli.getProposalNumber());
                breakUpInterval.setVersionNumber(bli.getBudgetVersionNumber());
                breakUpInterval.setBudgetPeriod(bli.getBudgetPeriod());
                breakUpInterval.setLineItemNumber(bli.getLineItemNumber());
                QueryList<RateAndCost> qlRateAndCosts = new QueryList<RateAndCost>();
                QueryList<BudgetProposalRate> qlBreakupPropRates = new QueryList<BudgetProposalRate>();
                QueryList<BudgetProposalLaRate> qlBreakupPropLARates = new QueryList<BudgetProposalLaRate>();
                QueryList qlTempRates = new QueryList();
                QueryList qlMultipleRates = new QueryList();
                String rateClassType;
                String rateClassCode;
                String rateTypeCode;
                Boolean applyRateFlag;
                long noOfDays = 0;
                // find the no. of days in this small period
                noOfDays = boundary.getDateDifference();
                // find the applicable amount
                breakUpInterval.setApplicableAmt(getPerDayCost().multiply(new BudgetDecimal(noOfDays)));
                breakUpInterval.setApplicableAmtCostSharing(getPerDayCostSharing().multiply(new BudgetDecimal(noOfDays)));
                //Loop and add all data required in breakup interval
                List<BudgetLineItemCalculatedAmount> qlLineItemCalcAmts = bli.getBudgetLineItemCalculatedAmounts();
                List<String> warningMessages = new ArrayList<String>();
                for (BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmount : qlLineItemCalcAmts) {
                    applyRateFlag = budgetLineItemCalculatedAmount.getApplyRateFlag();
                    rateClassType = budgetLineItemCalculatedAmount.getRateClassType();
                    rateClassCode = budgetLineItemCalculatedAmount.getRateClassCode();
                    rateTypeCode = budgetLineItemCalculatedAmount.getRateTypeCode();
                    //form the rate not available message
                    //These two statements have to move to the populate method of calculatedAmount later.
                    budgetLineItemCalculatedAmount.refreshReferenceObject("rateClass");
                    budgetLineItemCalculatedAmount.refreshReferenceObject("rateType");
                    //end block to be moved
                    message = messageTemplate + budgetLineItemCalculatedAmount.getRateClass().getDescription() + 
                                "\'  Rate Type - \'" + budgetLineItemCalculatedAmount.getRateType().getDescription() + "\' for Period - " ;
                    
                    //if apply flag is false and rate class type is not Overhead then skip
                    if ( !applyRateFlag && !rateClassType.equals(RateClassType.OVERHEAD.getRateClassType())) {
                        continue;
                    }
                    
                    RateAndCost rateCost = new RateAndCost();
                    rateCost.setApplyRateFlag(applyRateFlag);
                    rateCost.setRateClassType(rateClassType);
                    rateCost.setRateClassCode(rateClassCode);
                    rateCost.setRateTypeCode(rateTypeCode);
                    rateCost.setCalculatedCost(BudgetDecimal.ZERO);
                    rateCost.setCalculatedCostSharing(BudgetDecimal.ZERO);
                    
                    qlRateAndCosts.add(rateCost);
                    
                    //filter & store the rates applicable for this rate class / rate type
                    Equals equalsRC = new Equals("rateClassCode", rateClassCode);
                    Equals equalsRT = new Equals("rateTypeCode", rateTypeCode);
                    LesserThan ltEndDate = new LesserThan("startDate", boundary.getEndDate());
                    Equals equalsEndDate = new Equals("startDate", boundary.getEndDate());
                    GreaterThan gtStartDate = new GreaterThan("startDate", boundary.getStartDate());
                    Equals equalsStartDate = new Equals("startDate", boundary.getStartDate());
                    Or gtStartDateOrEqStartDate = new Or(gtStartDate, equalsStartDate);
                    Or ltEndDateOrEqEndDate = new Or(ltEndDate, equalsEndDate);
                    And gtOrEqStartDateAndltOrEqEndDate = new And(gtStartDateOrEqStartDate, ltEndDateOrEqEndDate);
                    And RCandRT = new And(equalsRC, equalsRT);
                    And RCRTandLtStartDate = new And(RCandRT, ltEndDateOrEqEndDate);
                    And RCRTandgtStartDateAndltEndDate = new And(RCandRT, gtOrEqStartDateAndltOrEqEndDate);
                    
                    if (rateClassType.equalsIgnoreCase(RateClassType.LAB_ALLOCATION.getRateClassType()) ||
                        rateClassType.equalsIgnoreCase(RateClassType.LA_WITH_EB_VA.getRateClassType())) {
                        qlTempRates = qlLineItemPropLaRates.filter(RCRTandLtStartDate);
                        if (qlTempRates != null && qlTempRates.size() > 0) {
                            /*
                             * Check if multiple rates are present got this period.
                             * If there, then show message and don't add any rates.
                             */
                            List cvMultipleRates = qlLineItemPropLaRates.filter(RCRTandgtStartDateAndltEndDate);
                            if (qlMultipleRates != null && qlMultipleRates.size() > 1) {
                                //Store the multiple rates available message in a message vector
                                message = multipleRatesMesgTemplate + 
                                            simpleDateFormat.format(boundary.getStartDate()) + 
                                            " to " +
                                            simpleDateFormat.format(boundary.getEndDate()) +
                                            " for Rate Class - \'"+ budgetLineItemCalculatedAmount.getRateClass().getDescription() + 
                                            "\'  Rate Type - \'" + budgetLineItemCalculatedAmount.getRateType().getDescription(); 
                                warningMessages.add(message);
                            
                            } else {
                                /**
                                 *sort the rates in desc order and take the first rate 
                                 *which is the latest
                                 */
                                qlTempRates.sort("startDate", false);
                                BudgetProposalLaRate tempPropLaRate = (BudgetProposalLaRate)qlTempRates.get(0);
                                qlBreakupPropLARates.add(tempPropLaRate);
                            }
                        } else {
                            //Store the rate not available message in a message vector
                            
                            message = message + 
                                        simpleDateFormat.format(boundary.getStartDate()) + 
                                        " to " +
                                        simpleDateFormat.format(boundary.getEndDate()); 
                            warningMessages.add(message);
                        }
                    } else {
                        qlTempRates = qlLineItemPropRates.filter(RCRTandLtStartDate);

                        if (qlTempRates != null && qlTempRates.size() > 0) {
                            
                            /**
                             * Check if multiple rates are present got this period.
                             * If there, then show message and don't add any rates.
                             */
                            qlMultipleRates = qlLineItemPropRates.filter(RCRTandgtStartDateAndltEndDate);
                            if (qlMultipleRates != null && qlMultipleRates.size() > 1) {
                                
                                //Store the multiple rates available message in a message vector

                                message = multipleRatesMesgTemplate + 
                                            simpleDateFormat.format(boundary.getStartDate()) + 
                                            " to " +
                                            simpleDateFormat.format(boundary.getEndDate()) +
                                            " for Rate Class - \'"+ budgetLineItemCalculatedAmount.getRateClass().getDescription() + 
                                            "\'  Rate Type - \'" + budgetLineItemCalculatedAmount.getRateType().getDescription(); 
                                warningMessages.add(message);
                            } else {
                                /**
                                 *sort the rates in desc order and take the first rate 
                                 *which is the latest
                                 */
                                qlTempRates.sort("startDate", false);
                                qlBreakupPropRates.add((BudgetProposalRate)qlTempRates.get(0));
                            }
                        } else {
                            //Store the rate not available message in a message vector
                            message = message + 
                                        simpleDateFormat.format(boundary.getStartDate()) + 
                                        " to " +
                                        simpleDateFormat.format(boundary.getEndDate()); 
                            warningMessages.add(message);
                        }
                    }
                    
                }//breakup interval data setting loop ends here
                    
                //set the values for the breakup interval in the BreakupInterval bean
                if (qlRateAndCosts != null && qlRateAndCosts.size() > 0) {
                    breakUpInterval.setRateAndCosts(qlRateAndCosts);
                    breakUpInterval.setBudgetProposalRates(qlBreakupPropRates);
                    breakUpInterval.setBudgetProposalLaRates(qlBreakupPropLARates);
                    breakupIntervals.add(breakUpInterval);
                }
                
                //Set the URRates if required
                if (!isUndercoveryMatchesOverhead(bd)) {
                    Equals equalsRC = new Equals("rateClassCode", bd.getUrRateClassCode());
                    Equals equalsRT = new Equals("rateTypeCode", "1");
                    Equals equalsOnOff = new Equals("onOffCampusFlag",bli.getOnOffCampusFlag());
                    And RCandRT = new And(equalsRC, equalsRT);
                    And RCRTandOnOff = new And(RCandRT, equalsOnOff);
                    QueryList<BudgetProposalRate> qlUnderRecoveryRates = qlLineItemPropRates.filter(RCRTandOnOff);
                    if (qlUnderRecoveryRates != null && qlUnderRecoveryRates.size() > 0) {
                        LesserThan ltEndDate = new LesserThan("startDate", boundary.getEndDate());
                        Equals equalsEndDate = new Equals("startDate", boundary.getEndDate());
                        Or ltEndDateOrEqEndDate = new Or(ltEndDate, equalsEndDate);
                        qlTempRates = qlUnderRecoveryRates.filter(ltEndDateOrEqEndDate);
                        if (qlTempRates != null && qlTempRates.size() > 0) {
                            /**
                             *sort the rates in desc order and take the first rate 
                             *which is the latest
                             */
                            qlTempRates.sort("startDate", false);
                            breakUpInterval.setURRatesBean((BudgetProposalRate)qlTempRates.get(0));
                        }
                    }
                }
                
            }//breakup interval creation loop ends here
        }//if for vecBoundaries checking ends here
    }
    /**
     * Use the combined & sorted Prop & LA rates to create Boundary objects. Each 
     * Boundary will contain start date & end date. Check whether any rate changes,
     * and break at this point to create a new boundary.
     *
     * @return List of boundary objects
     */
    public static List<Boundary> createBreakupBoundaries(QueryList<BudgetProposalLaRate> qlCombinedRates, Date liStartDate, 
                                                Date liEndDate) {        
        List<Boundary> boundaries = new ArrayList<Boundary>();
        if ( qlCombinedRates != null && qlCombinedRates.size() > 0) {
            Date tempStartDate = liStartDate;
            Date tempEndDate = liEndDate;
            Date rateChangeDate;
            BudgetProposalLaRate laRate;
            
            //take all rates greater than start date
            GreaterThan greaterThan = new GreaterThan("startDate", liStartDate);
            qlCombinedRates = qlCombinedRates.filter(greaterThan);

            //sort asc
            qlCombinedRates.sort("startDate", true);

            int size = qlCombinedRates.size();
            for (int index = 0; index < size; index++) {
                laRate = qlCombinedRates.get(index);
                rateChangeDate = laRate.getStartDate();
                if (rateChangeDate.compareTo(tempStartDate) > 0) {
                    // rate changed so get the previous day date and use it as endDate
                    tempEndDate = new Date(rateChangeDate.getTime() - 86400000);
                    Boundary boundary = new Boundary(tempStartDate, tempEndDate);
                    boundaries.add(boundary);
                    tempStartDate = rateChangeDate;
                }

            }
            /**
             *add one more boundary if no rate change on endDate and atleast
             *one boundary is present
             */
            if ( boundaries.size() > 0) {
                Boundary boundary = new Boundary(tempStartDate, liEndDate);
                boundaries.add(boundary);
            }

            /**
             *if no rate changes during the period create one boundary with
             *startDate & endDate same as that for line item
             */
            if( boundaries.size() == 0) {
                Boundary boundary = new Boundary(liStartDate, liEndDate);
                boundaries.add(boundary);
            }
            
        }
        return boundaries;
    } // end createBreakupBoundaries      

    protected void calculateBreakUpInterval() {
        int rateNumber = 0;
        QueryList cvRateBase = null;
        List<BreakUpInterval> cvLIBreakupIntervals = getBreakupIntervals();
        for (BreakUpInterval breakUpInterval : cvLIBreakupIntervals) {
            QueryList cvRateBaseData = new QueryList();
            breakUpInterval.setRateNumber(rateNumber);
            breakUpInterval.calculateBreakupInterval();
            //case Id #1811 - step3-start
            if(breakUpInterval.getRateBase()!= null && breakUpInterval.getRateBase().size() >0){
                cvRateBaseData.addAll(breakUpInterval.getRateBase());
                rateNumber = rateNumber+ breakUpInterval.getRateAndCosts().size();
            }
        }
//                cvRateBase = queryEngine.getDetails(key,BudgetRateBaseBean.class);
//                cvRateBase.addAll(cvRateBaseData);
//                queryEngine.addCollection(key,BudgetRateBaseBean.class,cvRateBase);
    }

    protected List<ValidCalcType> getValidCalcTypes() {
        return (List<ValidCalcType>) businessObjectService.findAll(ValidCalcType.class);
    }

    protected void populateCalculatedAmountLineItems() {
        if (bli.getBudgetLineItemCalculatedAmounts().size() <= 0) {
            bli.refreshReferenceObject("budgetLineItemCalculatedAmounts");
        }
        if (bli.getBudgetLineItemCalculatedAmounts().size() <= 0) {
            setCalculatedAmounts(bd,bli);
        }
    }

    private void setCalculatedAmounts(BudgetDocument budgetDocument, BudgetLineItemBase budgetLineItem) {
        QueryEngine queryEngine = new QueryEngine();
        BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmt = new BudgetLineItemCalculatedAmount();
        budgetLineItemCalculatedAmt.setProposalNumber(budgetLineItem.getProposalNumber());
        budgetLineItemCalculatedAmt.setVersionNumber(budgetLineItem.getVersionNumber());
        budgetLineItemCalculatedAmt.setBudgetPeriod(budgetLineItem.getBudgetPeriod());
        budgetLineItemCalculatedAmt.setLineItemNumber(budgetLineItem.getLineItemNumber());
        budgetLineItem.refreshReferenceObject("costElementBO");
        CostElement costElementBO = budgetLineItem.getCostElementBO();
        costElementBO.refreshReferenceObject("validCeRateTypes");
        List<ValidCeRateType> validCeRateTypes = costElementBO.getValidCeRateTypes();
        QueryList<ValidCeRateType> qValidCeRateTypes = validCeRateTypes == null ? new QueryList() : new QueryList(validCeRateTypes);
        // Check whether it contains Inflation Rate
        Equals eqInflation = new Equals("rateClassType", RateClassType.INFLATION.getRateClassType());
        QueryList vecInflation = qValidCeRateTypes.filter(eqInflation);
        if (vecInflation != null && vecInflation.size() > 0) {
            budgetLineItem.setApplyInRateFlag(true);
        }else {
            budgetLineItem.setApplyInRateFlag(false);
        }
        NotEquals nEqInflation = new NotEquals("rateClassType", RateClassType.INFLATION.getRateClassType());
        
        Equals eqOH = new Equals("rateClassType", RateClassType.OVERHEAD.getRateClassType());
        NotEquals nEqOH = new NotEquals("rateClassType", RateClassType.OVERHEAD.getRateClassType());
        Equals eqBudgetRateClass = new Equals("rateClassCode", ""+budgetDocument.getOhRateClassCode());
        
        And eqOHAndEqBudgetRateClass = new And(eqOH, eqBudgetRateClass);
        
        Or eqOHAndEqBudgetRateClassOrNEqOH = new Or(eqOHAndEqBudgetRateClass, nEqOH);
        
        And nEqInflationAndeqOHAndEqBudgetRateClassOrNEqOH = new And(nEqInflation, eqOHAndEqBudgetRateClassOrNEqOH);
        
        qValidCeRateTypes = qValidCeRateTypes.filter(nEqInflationAndeqOHAndEqBudgetRateClassOrNEqOH);
        
        List<BudgetProposalLaRate> budgetProposalLaRates = budgetDocument.getBudgetProposalLaRates();
        if (budgetProposalLaRates == null || budgetProposalLaRates.size() == 0) {
            NotEquals neqLA = new NotEquals("rateClassType", RateClassType.LAB_ALLOCATION.getRateClassType());
            NotEquals neqLASal = new NotEquals("rateClassType", RateClassType.LA_WITH_EB_VA.getRateClassType());
            And laAndLaSal = new And(neqLA, neqLASal);
            qValidCeRateTypes = qValidCeRateTypes.filter(laAndLaSal);
        }

        if (qValidCeRateTypes != null && qValidCeRateTypes.size() > 0) {
            for (ValidCeRateType validCeRateType : qValidCeRateTypes) {
                budgetLineItemCalculatedAmt = new BudgetLineItemCalculatedAmount();
                budgetLineItemCalculatedAmt.setProposalNumber(budgetLineItem.getProposalNumber());
                budgetLineItemCalculatedAmt.setVersionNumber(budgetLineItem.getVersionNumber());
                budgetLineItemCalculatedAmt.setBudgetPeriod(budgetLineItem.getBudgetPeriod());
                budgetLineItemCalculatedAmt.setLineItemNumber(budgetLineItem.getLineItemNumber());
                budgetLineItemCalculatedAmt.setRateClassType(validCeRateType.getRateClass().getRateClassType());
                budgetLineItemCalculatedAmt.setRateClassCode(validCeRateType.getRateClassCode());
                budgetLineItemCalculatedAmt.setRateTypeCode(validCeRateType.getRateTypeCode());
                // budgetLineItemCalculatedAmt.setRateClassDescription(validCeRateType.getRateClass().getDescription());
                // budgetLineItemCalculatedAmt.setRateTypeDescription(validCERateTypesBean.getRateTypeDescription());
                budgetLineItemCalculatedAmt.setApplyRateFlag(true);
                budgetLineItem.getBudgetLineItemCalculatedAmounts().add(budgetLineItemCalculatedAmt);
            }
        }
        Equals eqLabAllocSal = new Equals("rateClassType", RateClassType.LA_WITH_EB_VA.getRateClassType());
        QueryList<ValidCeRateType> qLabAllocSalRates = qValidCeRateTypes.filter(eqLabAllocSal);
        if (qLabAllocSalRates != null && qLabAllocSalRates.size() > 0) {
            // Has Lab allocation and Salaries Entry (i.e Rate Class Type = Y)
            Equals eqE = new Equals("rateClassType", RateClassType.EMPLOYEE_BENEFITS.getRateClassType());
            Equals eqV = new Equals("rateClassType", RateClassType.VACATION.getRateClassType());
            queryEngine.addDataCollection(ValidCalcType.class, getValidCalcTypes());
            List<ValidCalcType> validCalCTypes = queryEngine.executeQuery(ValidCalcType.class, eqE);
            if (validCalCTypes.size() > 0) {
                ValidCalcType validCalcType = (ValidCalcType) validCalCTypes.get(0);
                if (validCalcType.getDependentRateClassType().equals(RateClassType.LA_WITH_EB_VA.getRateClassType())) {
                    budgetLineItemCalculatedAmt = new BudgetLineItemCalculatedAmount();
                    budgetLineItemCalculatedAmt.setProposalNumber(budgetLineItem.getProposalNumber());
                    budgetLineItemCalculatedAmt.setVersionNumber(budgetLineItem.getVersionNumber());
                    budgetLineItemCalculatedAmt.setBudgetPeriod(budgetLineItem.getBudgetPeriod());
                    budgetLineItemCalculatedAmt.setLineItemNumber(budgetLineItem.getLineItemNumber());
                    budgetLineItemCalculatedAmt.setRateClassType(validCalcType.getRateClassType());
                    budgetLineItemCalculatedAmt.setRateClassCode(validCalcType.getRateClassCode());
                    budgetLineItemCalculatedAmt.setRateTypeCode(validCalcType.getRateTypeCode());
                    // budgetLineItemCalculatedAmt.setRateClassDescription(validCalcType.getRateClassDescription());
                    // budgetLineItemCalculatedAmt.setRateTypeDescription(validCalcType.getRateTypeDescription());
                    budgetLineItemCalculatedAmt.setApplyRateFlag(true);
                    budgetLineItem.getBudgetLineItemCalculatedAmounts().add(budgetLineItemCalculatedAmt);
                }
            }
            validCalCTypes = queryEngine.executeQuery(ValidCalcType.class, eqV);
            if (validCalCTypes.size() > 0) {
                ValidCalcType validCalcType = (ValidCalcType) validCalCTypes.get(0);
                if (validCalcType.getDependentRateClassType().equals(RateClassType.LA_WITH_EB_VA.getRateClassType())) {
                    budgetLineItemCalculatedAmt = new BudgetLineItemCalculatedAmount();
                    budgetLineItemCalculatedAmt.setProposalNumber(budgetLineItem.getProposalNumber());
                    budgetLineItemCalculatedAmt.setVersionNumber(budgetLineItem.getVersionNumber());
                    budgetLineItemCalculatedAmt.setBudgetPeriod(budgetLineItem.getBudgetPeriod());
                    budgetLineItemCalculatedAmt.setLineItemNumber(budgetLineItem.getLineItemNumber());
                    budgetLineItemCalculatedAmt.setRateClassType(validCalcType.getRateClassType());
                    budgetLineItemCalculatedAmt.setRateClassCode(validCalcType.getRateClassCode());
                    budgetLineItemCalculatedAmt.setRateTypeCode(validCalcType.getRateTypeCode());
                    // budgetLineItemCalculatedAmt.setRateClassDescription(validCalcType.getRateClassDescription());
                    // budgetLineItemCalculatedAmt.setRateTypeDescription(validCalcType.getRateTypeDescription());
                    budgetLineItemCalculatedAmt.setApplyRateFlag(true);
                    budgetLineItem.getBudgetLineItemCalculatedAmounts().add(budgetLineItemCalculatedAmt);
                }
            }
        }
    }

    /**
     * Gets the businessObjectService attribute.
     * 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    protected List<BreakUpInterval> getBreakupIntervals() {
        return breakupIntervals;
    }

    protected void setBreakupIntervals(List<BreakUpInterval> breakupIntervals) {
        this.breakupIntervals = breakupIntervals;
    }

    protected DateTimeService getDateTimeService() {
        return dateTimeService;
    }
}
