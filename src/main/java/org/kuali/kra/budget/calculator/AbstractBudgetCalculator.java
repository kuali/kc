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
package org.kuali.kra.budget.calculator;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.AbstractBudgetCalculatedAmount;
import org.kuali.kra.budget.bo.AbstractBudgetRate;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetLineItemBase;
import org.kuali.kra.budget.bo.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.bo.BudgetProposalLaRate;
import org.kuali.kra.budget.bo.BudgetProposalRate;
import org.kuali.kra.budget.bo.CostElement;
import org.kuali.kra.budget.bo.ValidCalcType;
import org.kuali.kra.budget.bo.ValidCeRateType;
import org.kuali.kra.budget.calculator.query.And;
import org.kuali.kra.budget.calculator.query.Equals;
import org.kuali.kra.budget.calculator.query.GreaterThan;
import org.kuali.kra.budget.calculator.query.LesserThan;
import org.kuali.kra.budget.calculator.query.NotEquals;
import org.kuali.kra.budget.calculator.query.Or;
import org.kuali.kra.budget.calculator.query.QueryEngine;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.service.KNSServiceLocator;
/**
 * 
 * Base class for <code>LineItemCalculator<code> and <code>PersonnelLineItemCalculator</code>.
 */
public abstract class AbstractBudgetCalculator {
    private static final String UNDER_REECOVERY_RATE_TYPE_CODE = "1";
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(AbstractBudgetCalculator.class);
    private BusinessObjectService businessObjectService;
    private DateTimeService dateTimeService;
    protected BudgetDocument budgetDocument;
    protected BudgetLineItemBase budgetLineItem;
    private QueryList<BudgetProposalLaRate> lineItemPropLaRates;
    private QueryList<BudgetProposalRate> lineItemPropRates;
    private List<BreakUpInterval> breakupIntervals;
    private QueryList<ValidCeRateType> infltionValidCalcCeRates;
    private QueryList<BudgetProposalRate> underrecoveryRates;
    private QueryList<BudgetProposalRate> inflationRates;

    /**
     * 
     * Constructs a CalculatorBase.java.
     * @param budgetDocument
     * @param budgetLineItem
     */
    public AbstractBudgetCalculator(BudgetDocument budgetDocument, BudgetLineItemBase budgetLineItem) {
        this.budgetDocument = budgetDocument;
        this.budgetLineItem = budgetLineItem;
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        dateTimeService = KNSServiceLocator.getDateTimeService();
        breakupIntervals = new ArrayList<BreakUpInterval>();
    }
    /**
     * 
     * Abstract method to populate the applicable cost and applicable cost sharing in boundary.
     * For LineItemCalculator, applicableCost would be the line item cost and
     * for PersonnelLineItemCalculator applicable cost would be cumulative salary of all personnel line item
     * @param boundary
     */
    public abstract void populateApplicableCosts(Boundary boundary);

    /**
     * This method is for filtering rates and lab allocation rates. 
     * @param rates
     * @return
     */
    public QueryList filterRates(List rates) {
//        if(rates.isEmpty()){
//            BudgetRatesService budgetRateService = KraServiceLocator.getService(BudgetRatesService.class);
//            budgetRateService.resetAllBudgetRates(budgetDocument);
//        }
        if (!rates.isEmpty() && rates.get(0) instanceof BudgetProposalRate) {
            QueryList qList = filterRates(rates, budgetLineItem.getStartDate(), budgetLineItem.getEndDate(), budgetDocument.getProposal().getActivityTypeCode());
            if (qList.isEmpty() && !budgetDocument.getActivityTypeCode().equals(budgetDocument.getProposal().getActivityTypeCode())) {
                qList = filterRates(rates, budgetLineItem.getStartDate(), budgetLineItem.getEndDate(), budgetDocument.getActivityTypeCode());                
            }
            return qList;
            //return filterRates(rates, budgetLineItem.getStartDate(), budgetLineItem.getEndDate(), budgetDocument.getActivityTypeCode());
        }
        else {
            return filterRates(rates, budgetLineItem.getStartDate(), budgetLineItem.getEndDate(), null);
        }
    }
    /**
     * 
     * This method is for filtering rates between startdate and enddate. 
     * <code>activityTypeCode</code> will be null if its a lab allocation rates
     * @param rates
     * @param startDate
     * @param endDate
     * @param activityTypeCode
     * @return list of filtered rates
     */
    private QueryList filterRates(List rates, Date startDate, Date endDate, String activityTypeCode) {
        //TODO: have to refactor to create new abstract class and extend both calc amount classes from that
//        List lineItemCalcAmts;
//        if(budgetLineItem instanceof BudgetLineItem){
//            lineItemCalcAmts = ((BudgetLineItem)budgetLineItem).getBudgetLineItemCalculatedAmounts();
//        }else{
//            lineItemCalcAmts = ((BudgetPersonnelDetails)budgetLineItem).getBudgetPersonnelCalculatedAmounts();
//        }
        List<AbstractBudgetCalculatedAmount> lineItemCalcAmts = budgetLineItem.getBudgetCalculatedAmounts();
        QueryList qlRates = new QueryList(rates);
        QueryList budgetProposalRates = new QueryList();

        /**
         * Get all rates from Proposal Rates & Proposal LA Rates which matches with the rates in line item cal amts
         */
//        if (lineItemCalcAmts != null && lineItemCalcAmts.size() > 0) {
//            int calAmtSize = lineItemCalcAmts.size();
//            for(int i=0;i<calAmtSize;i++){
            for (AbstractBudgetCalculatedAmount calAmtsBean : lineItemCalcAmts) {
//                BudgetLineItemCalculatedAmount calAmtsBean = (BudgetLineItemCalculatedAmount)lineItemCalcAmts.get(i);
                String rateClassCode = calAmtsBean.getRateClassCode();
                String rateTypeCode = calAmtsBean.getRateTypeCode();
                Equals equalsRC = new Equals("rateClassCode", rateClassCode);
                Equals equalsRT = new Equals("rateTypeCode", rateTypeCode);
                Equals equalsOnOff = new Equals("onOffCampusFlag", budgetLineItem.getOnOffCampusFlag());

                And RCandRT = new And(equalsRC, equalsRT);
                And RCRTandOnOff = new And(RCandRT, equalsOnOff);
                QueryList filteredRates = qlRates.filter(RCRTandOnOff);
                if (filteredRates != null && !filteredRates.isEmpty()) {
                    budgetProposalRates.addAll(qlRates.filter(RCRTandOnOff));
                }
            }

//        }
        if (activityTypeCode != null) {
            // Add inflation rates separately because, calculated amount list will not have inflation rates listed
            if (infltionValidCalcCeRates != null && !infltionValidCalcCeRates.isEmpty()) {
                for (ValidCeRateType inflationValidceRate : infltionValidCalcCeRates) {
                    Equals equalsRC = new Equals("rateClassCode", inflationValidceRate.getRateClassCode());
                    Equals equalsRT = new Equals("rateTypeCode", inflationValidceRate.getRateTypeCode());
                    Equals equalsOnOff = new Equals("onOffCampusFlag", budgetLineItem.getOnOffCampusFlag());
                    And RCandRT = new And(equalsRC, equalsRT);
                    And RCRTandOnOff = new And(RCandRT, equalsOnOff);
                    Equals eActType = new Equals("activityTypeCode", activityTypeCode);
                    And RCRTandOnOffandActType = new And(RCRTandOnOff,eActType);
                    QueryList<BudgetProposalRate> filteredRates = qlRates.filter(RCRTandOnOffandActType);
                    if (filteredRates != null && !filteredRates.isEmpty()) {
                        setInflationRates(filteredRates);
                        budgetProposalRates.addAll(filteredRates);
                    }
                }
            }
            // Add underrecovery rates
            if(!isUndercoveryMatchesOverhead()){
                Equals equalsRC = new Equals("rateClassCode", budgetDocument.getUrRateClassCode());
                Equals equalsRT = new Equals("rateTypeCode", UNDER_REECOVERY_RATE_TYPE_CODE);
                Equals equalsOnOff = new Equals("onOffCampusFlag", budgetLineItem.getOnOffCampusFlag());
                And RCandRT = new And(equalsRC, equalsRT);
                And RCRTandOnOff = new And(RCandRT, equalsOnOff);
                budgetProposalRates.addAll(qlRates.filter(RCRTandOnOff));
            }
            Equals eActType = new Equals("activityTypeCode", activityTypeCode);
            budgetProposalRates = budgetProposalRates.filter(eActType);
        }
        if (budgetProposalRates != null && !budgetProposalRates.isEmpty()) {
            LesserThan lesserThan = new LesserThan("startDate", endDate);
            Equals equals = new Equals("startDate", endDate);
            Or or = new Or(lesserThan, equals);
            budgetProposalRates = budgetProposalRates.filter(or);
        }

        return budgetProposalRates;
    }

    private boolean isUndercoveryMatchesOverhead() {
        return budgetDocument.getOhRateClassCode().equals(budgetDocument.getUrRateClassCode());
    }

    public void calculate() {
        budgetLineItem.setDirectCost(budgetLineItem.getLineItemCost());
        budgetLineItem.setTotalCostSharingAmount(budgetLineItem.getCostSharingAmount());
        budgetLineItem.setIndirectCost(BudgetDecimal.ZERO);
        budgetLineItem.setUnderrecoveryAmount(BudgetDecimal.ZERO);
        createAndCalculateBreakupIntervals();
        updateBudgetLineItemCalculatedAmounts();
        populateBudgetRateBaseList();
        // if (!uRMatchesOh && (!OHAvailable || cvLineItemCalcAmts == null || cvLineItemCalcAmts.size() == 0)) {
        // calculateURBase();
        // }

    }
    protected abstract void populateBudgetRateBaseList();
    
    protected void updateBudgetLineItemCalculatedAmounts() {
        
        List<AbstractBudgetCalculatedAmount> lineItemCalcAmts = budgetLineItem.getBudgetCalculatedAmounts();
        List<BreakUpInterval> cvLIBreakupIntervals = getBreakupIntervals();
        if (lineItemCalcAmts != null && lineItemCalcAmts.size() > 0 && cvLIBreakupIntervals != null
                && cvLIBreakupIntervals.size() > 0) {
            /*
             * Sum up all the calculated costs for each breakup interval and then update the line item cal amts.
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
            QueryList<RateAndCost> cvCombinedAmtDetails = new QueryList<RateAndCost>();
            // Loop and add all the amount details from all the breakup intervals
            for (BreakUpInterval brkUpInterval : cvLIBreakupIntervals) {
                cvCombinedAmtDetails.addAll(brkUpInterval.getRateAndCosts());
            }
            // loop thru all cal amount rates, sum up the costs and set it
            for (AbstractBudgetCalculatedAmount calculatedAmount : lineItemCalcAmts) {
//                if (!calculatedAmount.getApplyRateFlag()) {
//                    calculatedAmount.setCalculatedCost(BudgetDecimal.ZERO);
//                    calculatedAmount.setCalculatedCostSharing(BudgetDecimal.ZERO);
//                    continue;
//                }
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

            /*
             * Sum up all the underRecovery costs for each breakup interval and then update the line item details.
             */
            totalUnderRecovery = new QueryList<BreakUpInterval>(cvLIBreakupIntervals).sumObjects("underRecovery");
            budgetLineItem.setUnderrecoveryAmount(totalUnderRecovery);

            /*
             * Sum up all direct costs ie, rates for RateClassType <> 'O', for each breakup interval plus the line item cost, and
             * then update the line item details.
             */
            NotEquals notEqualsOH = new NotEquals("rateClassType", RateClassType.OVERHEAD.getRateClassType());
            boolean directCostRolledUp = false;
            boolean resetTotalUnderRecovery = false;
            BudgetDecimal newTotalUrAmount = BudgetDecimal.ZERO;
            BudgetDecimal newTotalCostSharing = BudgetDecimal.ZERO;
            if (budgetLineItem instanceof BudgetLineItem && CollectionUtils.isNotEmpty(((BudgetLineItem)budgetLineItem).getBudgetPersonnelDetailsList())) {
                for (BudgetPersonnelDetails budgetPersonnelDetail : ((BudgetLineItem)budgetLineItem).getBudgetPersonnelDetailsList()) {
                    List<BudgetPersonnelCalculatedAmount> personnelCalAmts = budgetPersonnelDetail.getBudgetCalculatedAmounts();
                    newTotalUrAmount = newTotalUrAmount.add(budgetPersonnelDetail.getUnderrecoveryAmount());
                    resetTotalUnderRecovery = true;
                    if (CollectionUtils.isNotEmpty(personnelCalAmts)) {
                        for (BudgetPersonnelCalculatedAmount personnelCalAmt : personnelCalAmts) {
                            if (personnelCalAmt.getRateClass() == null) {
                                personnelCalAmt.refreshReferenceObject("rateClass");
                            }
                            if (!personnelCalAmt.getRateClass().getRateClassType().equals("O")) {
                                directCost = directCost.add(personnelCalAmt.getCalculatedCost());
                            } else {
                                indirectCost = indirectCost.add(personnelCalAmt.getCalculatedCost());
                                
                            }
                            newTotalCostSharing = newTotalCostSharing.add(personnelCalAmt.getCalculatedCostSharing());
                            directCostRolledUp = true;

                            
                        }
                    }
                }
            }
            if (resetTotalUnderRecovery) {
                budgetLineItem.setUnderrecoveryAmount(newTotalUrAmount);
            }
            if (!directCostRolledUp) {
                directCost = cvCombinedAmtDetails.sumObjects("calculatedCost", notEqualsOH);
            }
            budgetLineItem.setDirectCost(directCost.add(budgetLineItem.getLineItemCost()));
            /*
             * Sum up all Indirect costs ie, rates for RateClassType = 'O', for each breakup interval and then update the line item
             * details.
             */
            Equals equalsOH = new Equals("rateClassType", RateClassType.OVERHEAD.getRateClassType());
            if (!directCostRolledUp) {
                indirectCost = cvCombinedAmtDetails.sumObjects("calculatedCost", equalsOH);
            }
            budgetLineItem.setIndirectCost(indirectCost);

            /*
             * Sum up all Cost Sharing amounts ie, rates for RateClassType <> 'O' and set in the calculatedCostSharing field of line
             * item details
             */
            if (!directCostRolledUp) {
                totalCalculatedCostSharing = cvCombinedAmtDetails.sumObjects("calculatedCostSharing");
            } else {
                totalCalculatedCostSharing = newTotalCostSharing;
            }

            budgetLineItem.setTotalCostSharingAmount(budgetLineItem.getCostSharingAmount() == null ? 
                        totalCalculatedCostSharing : 
                        budgetLineItem.getCostSharingAmount().add(totalCalculatedCostSharing));

        } else if (lineItemCalcAmts != null && lineItemCalcAmts.size() > 0 && (budgetLineItem.getLineItemCost().equals(BudgetDecimal.ZERO) || CollectionUtils.isEmpty(cvLIBreakupIntervals))) {
            // if total is 0 or no matching rate to calculate
            for (AbstractBudgetCalculatedAmount calculatedAmount : lineItemCalcAmts) {
                  calculatedAmount.setCalculatedCost(BudgetDecimal.ZERO);
                  calculatedAmount.setCalculatedCostSharing(BudgetDecimal.ZERO);
          }
        } 
    }


    protected void createAndCalculateBreakupIntervals() {
        populateCalculatedAmountLineItems();
        setQlLineItemPropLaRates(filterRates(budgetDocument.getBudgetProposalLaRates()));
        setQlLineItemPropRates(filterRates(budgetDocument.getBudgetProposalRates()));
        createBreakUpInterval();
        calculateBreakUpInterval();
    }

    /**
     * Combine the sorted Prop & LA rates, which should be in sorted order(asc). Now create the breakup boundaries and use it to
     * create breakup intervals and set all the values required for calculation. Then call calculateBreakupInterval method for each
     * AmountBean for setting the calculated cost & calculated cost sharing ie for each rate class & rate type.
     */
    protected void createBreakUpInterval() {
        LOG.info("Line item details before going to create breakup interval " + budgetLineItem);
        // Initialize the Message that should be shown if rate not avalilable for any period
        String messageTemplate = "";
        String multipleRatesMesgTemplate = "";
        String message = "";
        if (breakupIntervals == null)
            breakupIntervals = new ArrayList<BreakUpInterval>();

        if (budgetLineItem.getOnOffCampusFlag()) {
            messageTemplate = "On-Campus rate information not available for Rate Class - \'";
            multipleRatesMesgTemplate = "Multiple On-Campus rates found for the period ";
        }
        else {
            messageTemplate = "Off-Campus rate information not available for Rate Class - \'";
            multipleRatesMesgTemplate = "Multiple Off-Campus rates found for the period ";
        }

        QueryList<BudgetProposalLaRate> qlLineItemPropLaRates = getQlLineItemPropLaRates();
        LOG.info("Budget proposal LA rates size is " + qlLineItemPropLaRates.size());
        QueryList<BudgetProposalRate> qlLineItemPropRates = getQlLineItemPropRates();
        LOG.info("Budget proposal rates size is " + qlLineItemPropRates.size());

        // combine the sorted Prop & LA Rates
        QueryList qlCombinedRates = new QueryList();
        qlCombinedRates.addAll(qlLineItemPropRates);
        qlCombinedRates.addAll(qlLineItemPropLaRates);

        // qlCombinedRates.addAll(filterRates(bd.getBudgetProposalRates(),bli));
        // qlCombinedRates.addAll(filterRates(bd.getBudgetProposalLaRates(),bli));
        LOG.info("After filtering combined rates size is " + qlCombinedRates.size());
        // sort the combined rates in asc order
        qlCombinedRates.sort("startDate", true);
        // Now start creating breakup periods
        // First get the breakup boundaries
        Date liStartDate = budgetLineItem.getStartDate();
        Date liEndDate = budgetLineItem.getEndDate();
        List<Boundary> boundaries = createBreakupBoundaries(qlCombinedRates, liStartDate, liEndDate);
        LOG.info("Breakup boundaries size is " + boundaries.size());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM-dd-yyyy");
        // create breakup intervals based on the breakup boundaries
        if (boundaries != null && boundaries.size() > 0) {
            for (Boundary boundary : boundaries) {
                BreakUpInterval breakUpInterval = new BreakUpInterval();
                breakUpInterval.setBoundary(boundary);
                breakUpInterval.setProposalNumber(budgetLineItem.getProposalNumber());
                breakUpInterval.setVersionNumber(budgetLineItem.getBudgetVersionNumber());
                breakUpInterval.setBudgetPeriod(budgetLineItem.getBudgetPeriod());
                breakUpInterval.setLineItemNumber(budgetLineItem.getLineItemNumber());
                QueryList<RateAndCost> qlRateAndCosts = new QueryList<RateAndCost>();
                QueryList<BudgetProposalRate> qlBreakupPropRates = new QueryList<BudgetProposalRate>();
                QueryList<BudgetProposalLaRate> qlBreakupPropLARates = new QueryList<BudgetProposalLaRate>();
                QueryList qlTempRates = new QueryList();
                QueryList qlMultipleRates = new QueryList();
                String rateClassType;
                String rateClassCode;
                String rateTypeCode;
                Boolean applyRateFlag;
                // long noOfDays = 0;
                // // find the no. of days in this small period
                // noOfDays = boundary.getDateDifference();
                // find the applicable amount
                // breakUpInterval.setApplicableAmt(getPerDayCost().multiply(new BudgetDecimal(noOfDays)));
                // breakUpInterval.setApplicableAmtCostSharing(getPerDayCostSharing().multiply(new BudgetDecimal(noOfDays)));
                populateApplicableCosts(boundary);
                breakUpInterval.setApplicableAmt(boundary.getApplicableCost());
                breakUpInterval.setApplicableAmtCostSharing(boundary.getApplicableCostSharing());
                // Loop and add all data required in breakup interval
                
                //TODO: have to refactor to create new abstract class and extend both calc amount classes from that
                List<AbstractBudgetCalculatedAmount> qlLineItemCalcAmts = budgetLineItem.getBudgetCalculatedAmounts();
//                if(budgetLineItem instanceof BudgetLineItem){
//                    qlLineItemCalcAmts = ((BudgetLineItem)budgetLineItem).getBudgetLineItemCalculatedAmounts();
//                }else{
//                    qlLineItemCalcAmts = ((BudgetPersonnelDetails)budgetLineItem).getBudgetPersonnelCalculatedAmounts();
//                }
                
                List<String> warningMessages = new ArrayList<String>();
//                for(int i=0;i<qlLineItemCalcAmts.size();i++){
//                      BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmount = (BudgetLineItemCalculatedAmount)qlLineItemCalcAmts.get(i);

                for (AbstractBudgetCalculatedAmount budgetLineItemCalculatedAmount : qlLineItemCalcAmts) {
                    budgetLineItemCalculatedAmount.refreshNonUpdateableReferences();
                    applyRateFlag = budgetLineItemCalculatedAmount.getApplyRateFlag();
                    rateClassCode = budgetLineItemCalculatedAmount.getRateClassCode();
                    rateTypeCode = budgetLineItemCalculatedAmount.getRateTypeCode();
                    // form the rate not available message
                    // These two statements have to move to the populate method of calculatedAmount later.
                    budgetLineItemCalculatedAmount.refreshReferenceObject("rateClass");
                    budgetLineItemCalculatedAmount.refreshReferenceObject("rateType");
                    rateClassType = budgetLineItemCalculatedAmount.getRateClass().getRateClassType();
                    // end block to be moved
                    message = messageTemplate + budgetLineItemCalculatedAmount.getRateClass().getDescription()
                            + "\'  Rate Type - \'" + budgetLineItemCalculatedAmount.getRateType().getDescription()
                            + "\' for Period - ";

                    // if apply flag is false and rate class type is not Overhead then skip
                    if (!applyRateFlag && !rateClassType.equals(RateClassType.OVERHEAD.getRateClassType())) {
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

                    // filter & store the rates applicable for this rate class / rate type
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

                    if (rateClassType.equalsIgnoreCase(RateClassType.LAB_ALLOCATION.getRateClassType())
                            || rateClassType.equalsIgnoreCase(RateClassType.LA_WITH_EB_VA.getRateClassType())) {
                        qlTempRates = qlLineItemPropLaRates.filter(RCRTandLtStartDate);
                        if (qlTempRates != null && qlTempRates.size() > 0) {
                            /*
                             * Check if multiple rates are present got this period. If there, then show message and don't add any
                             * rates.
                             */
                            List cvMultipleRates = qlLineItemPropLaRates.filter(RCRTandgtStartDateAndltEndDate);
                            if (qlMultipleRates != null && qlMultipleRates.size() > 1) {
                                // Store the multiple rates available message in a message vector
                                message = multipleRatesMesgTemplate + simpleDateFormat.format(boundary.getStartDate()) + " to "
                                        + simpleDateFormat.format(boundary.getEndDate()) + " for Rate Class - \'"
                                        + budgetLineItemCalculatedAmount.getRateClass().getDescription() + "\'  Rate Type - \'"
                                        + budgetLineItemCalculatedAmount.getRateType().getDescription();
                                warningMessages.add(message);

                            }
                            else {
                                /**
                                 * sort the rates in desc order and take the first rate which is the latest
                                 */
                                qlTempRates.sort("startDate", false);
                                BudgetProposalLaRate tempPropLaRate = (BudgetProposalLaRate) qlTempRates.get(0);
                                qlBreakupPropLARates.add(tempPropLaRate);
                            }
                        }
                        else {
                            // Store the rate not available message in a message vector

                            message = message + simpleDateFormat.format(boundary.getStartDate()) + " to "
                                    + simpleDateFormat.format(boundary.getEndDate());
                            warningMessages.add(message);
                        }
                    }
                    else {
                        qlTempRates = qlLineItemPropRates.filter(RCRTandLtStartDate);

                        if (qlTempRates != null && qlTempRates.size() > 0) {

                            /**
                             * Check if multiple rates are present for this boundary. If there, then show message and don't add any
                             * rates.
                             */
                            qlMultipleRates = qlLineItemPropRates.filter(RCRTandgtStartDateAndltEndDate);
                            if (qlMultipleRates != null && qlMultipleRates.size() > 1) {

                                // Store the multiple rates available message in a message vector

                                message = multipleRatesMesgTemplate + simpleDateFormat.format(boundary.getStartDate()) + " to "
                                        + simpleDateFormat.format(boundary.getEndDate()) + " for Rate Class - \'"
                                        + budgetLineItemCalculatedAmount.getRateClass().getDescription() + "\'  Rate Type - \'"
                                        + budgetLineItemCalculatedAmount.getRateType().getDescription();
                                warningMessages.add(message);
                            }
                            else {
                                /**
                                 * sort the rates in desc order and take the first rate which is the latest
                                 */
                                qlTempRates.sort("startDate", false);
                                qlBreakupPropRates.add((BudgetProposalRate) qlTempRates.get(0));
                            }
                        }
                        else {
                            // Store the rate not available message in a message vector
                            message = message + simpleDateFormat.format(boundary.getStartDate()) + " to "
                                    + simpleDateFormat.format(boundary.getEndDate());
                            warningMessages.add(message);
                        }
                    }

                }// breakup interval data setting loop ends here

                // set the values for the breakup interval in the BreakupInterval bean
                if (qlRateAndCosts != null && qlRateAndCosts.size() > 0) {
                    breakUpInterval.setRateAndCosts(qlRateAndCosts);
                    breakUpInterval.setBudgetProposalRates(qlBreakupPropRates);
                    breakUpInterval.setBudgetProposalLaRates(qlBreakupPropLARates);
                    breakupIntervals.add(breakUpInterval);
                }
                // Set the URRates if required
                if (!isUndercoveryMatchesOverhead() && hasValidUnderRecoveryRate()) {
                    Equals equalsRC = new Equals("rateClassCode", budgetDocument.getUrRateClassCode());
                    Equals equalsRT = new Equals("rateTypeCode", UNDER_REECOVERY_RATE_TYPE_CODE);
                    Equals equalsOnOff = new Equals("onOffCampusFlag", budgetLineItem.getOnOffCampusFlag());
                    And RCandRT = new And(equalsRC, equalsRT);
                    And RCRTandOnOff = new And(RCandRT, equalsOnOff);
                    QueryList<BudgetProposalRate> qlUnderRecoveryRates = qlLineItemPropRates.filter(RCRTandOnOff);
                    if (qlUnderRecoveryRates != null && qlUnderRecoveryRates.size() > 0) {
                        LesserThan ltEndDate = new LesserThan("startDate", boundary.getEndDate());
                        Equals equalsEndDate = new Equals("startDate", boundary.getEndDate());
                        Or ltEndDateOrEqEndDate = new Or(ltEndDate, equalsEndDate);
                        qlTempRates = qlUnderRecoveryRates.filter(ltEndDateOrEqEndDate);
                        if (qlTempRates != null && qlTempRates.size() > 0) {
                            /*
                             * sort the rates in desc order and take the first rate which is the latest
                             */
                            qlTempRates.sort("startDate", false);
                            breakUpInterval.setURRatesBean((BudgetProposalRate) qlTempRates.get(0));
                        }
                    }
                }
            }// breakup interval creation loop ends here
        }// if for vecBoundaries checking ends here
    }

    private boolean hasValidUnderRecoveryRate() {
        Equals equalsRC = new Equals("rateClassCode", budgetDocument.getUrRateClassCode());
        Equals equalsRT = new Equals("rateTypeCode", UNDER_REECOVERY_RATE_TYPE_CODE);
        Equals equalsRCT = new Equals("rateClassType", RateClassType.OVERHEAD.getRateClassType());
        And RCandRT = new And(equalsRC, equalsRT);
        And RCRTandRCT = new And(RCandRT, equalsRCT);
        QueryList<ValidCeRateType> validCeRateTypes = new QueryList<ValidCeRateType>(budgetLineItem.getCostElementBO().getValidCeRateTypes());
        return !validCeRateTypes.filter(RCRTandRCT).isEmpty();
    }

    /**
     * Use the combined & sorted Prop & LA rates to create Boundary objects. Each Boundary will contain start date & end date. Check
     * whether any rate changes, and break at this point to create a new boundary.
     * 
     * @return List of boundary objects
     */
    public List<Boundary> createBreakupBoundaries(QueryList<AbstractBudgetRate> qlCombinedRates, Date liStartDate,
            Date liEndDate) {
        List<Boundary> boundaries = new ArrayList<Boundary>();
        if (qlCombinedRates != null && qlCombinedRates.size() > 0) {
            Date tempStartDate = liStartDate;
            Date tempEndDate = liEndDate;
            Date rateChangeDate;
//            BudgetProposalLaRate laRate;
            // take all rates greater than start date
            GreaterThan greaterThan = new GreaterThan("startDate", liStartDate);
            qlCombinedRates = qlCombinedRates.filter(greaterThan);
            // sort asc
            qlCombinedRates.sort("startDate", true);
            int size = qlCombinedRates.size();
            for (AbstractBudgetRate laRate : qlCombinedRates) {
//                
//            }
//            for (int index = 0; index < size; index++) {
//                laRate = qlCombinedRates.get(index);
                rateChangeDate = laRate.getStartDate();
                if (rateChangeDate.after(tempStartDate)) {
                    // rate changed so get the previous day date and use it as endDate
                    Calendar temEndCal = dateTimeService.getCalendar(rateChangeDate);
                    temEndCal.add(Calendar.DAY_OF_MONTH, -1);
//                    tempEndDate = new Date(rateChangeDate.getTime() - 86400000);
                    try {
                        tempEndDate = dateTimeService.convertToSqlDate(temEndCal.get(Calendar.YEAR)+"-"+(temEndCal.get(Calendar.MONTH)+1)+"-"+temEndCal.get(Calendar.DAY_OF_MONTH));
                    }
                    catch (ParseException e) {
                        tempEndDate = new Date(rateChangeDate.getTime() - 86400000);
                    }
                    Boundary boundary = new Boundary(tempStartDate, tempEndDate);
                    boundaries.add(boundary);
                    tempStartDate = rateChangeDate;
                }
            }
            /**
             * add one more boundary if no rate change on endDate and atleast one boundary is present
             */
            if (boundaries.size() > 0) {
                Boundary boundary = new Boundary(tempStartDate, liEndDate);
                boundaries.add(boundary);
            }
            /**
             * if no rate changes during the period create one boundary with startDate & endDate same as that for line item
             */
            if (boundaries.size() == 0) {
                Boundary boundary = new Boundary(liStartDate, liEndDate);
                boundaries.add(boundary);
            }
        }
        return boundaries;
    } // end createBreakupBoundaries

    protected void calculateBreakUpInterval() {
        int rateNumber = 0;
        List<BreakUpInterval> cvLIBreakupIntervals = getBreakupIntervals();
        for (BreakUpInterval breakUpInterval : cvLIBreakupIntervals) {
            // QueryList cvRateBaseData = new QueryList();
            breakUpInterval.setRateNumber(rateNumber);
            breakUpInterval.calculateBreakupInterval();
            // if(breakUpInterval.getRateBase()!= null && breakUpInterval.getRateBase().size() >0){
            // cvRateBaseData.addAll(breakUpInterval.getRateBase());
            // rateNumber += breakUpInterval.getRateAndCosts().size();
            // }
        }
        // cvRateBase = queryEngine.getDetails(key,BudgetRateBaseBean.class);
        // cvRateBase.addAll(cvRateBaseData);
        // queryEngine.addCollection(key,BudgetRateBaseBean.class,cvRateBase);
    }

    protected List<ValidCalcType> getValidCalcTypes() {
        return (List<ValidCalcType>) businessObjectService.findAll(ValidCalcType.class);
    }

    protected abstract void populateCalculatedAmountLineItems();

    private CostElement getCostElementForLineItem(BudgetLineItemBase lineItem) {
        Map<String, String> costElementQMap = new HashMap<String, String>();
        costElementQMap.put("costElement", lineItem.getCostElement());

        return (CostElement) businessObjectService.findByPrimaryKey(CostElement.class, costElementQMap);        
    }

    private <T> QueryList<T> createQueryList(List<T> immutableList) {
        if (immutableList == null) {
            return new QueryList();
        }

        return new QueryList(immutableList);
    }

    private void setInflationRateOnLineItem(BudgetLineItemBase lineItem) {
        QueryList<ValidCeRateType> qValidCeRateTypes = createQueryList(budgetLineItem.getCostElementBO().getValidCeRateTypes());

        // Check whether it contains Inflation Rate
        QueryList<ValidCeRateType> inflationValidCeRates = 
            qValidCeRateTypes.filter(new Equals("rateClassType", RateClassType.INFLATION.getRateClassType()));
        if (!inflationValidCeRates.isEmpty()) {
            if (lineItem.getApplyInRateFlag()) {
                setInfltionValidCalcCeRates(inflationValidCeRates);
            }
        }
        else {
            lineItem.setApplyInRateFlag(false);
        }
        
    }

    private Equals equalsOverHeadRateClassType() {
        return new Equals("rateClassType", RateClassType.OVERHEAD.getRateClassType());
    }

    private NotEquals notEqualsInflationRateClassType() {
        return new NotEquals("rateClassType", RateClassType.INFLATION.getRateClassType());
    }

    private Equals equalsOverHeadRateClassCode() {
        return new Equals("rateClassCode", "" + budgetDocument.getOhRateClassCode());
    }

    private NotEquals notEqualsOverHeadRateClassType() {
        return new NotEquals("rateClassType", RateClassType.OVERHEAD.getRateClassType());    
    }

    private And notEqualsLabAllocationRateClassType() {
        return new NotEquals("rateClassType", RateClassType.LAB_ALLOCATION.getRateClassType())
            .and(new NotEquals("rateClassType", RateClassType.LA_WITH_EB_VA.getRateClassType()));
    }

    
    private void setValidCeRateTypeCalculatedAmounts(BudgetLineItemBase lineItem) {
        QueryList<ValidCeRateType> qValidCeRateTypes = createQueryList(budgetLineItem.getCostElementBO().getValidCeRateTypes());
        qValidCeRateTypes = qValidCeRateTypes.filter(equalsOverHeadRateClassType()
                                                     .and(equalsOverHeadRateClassCode())
                                                     .or(notEqualsOverHeadRateClassType())
                                                     .and(notEqualsInflationRateClassType()));

        List<BudgetProposalLaRate> budgetProposalLaRates = budgetDocument.getBudgetProposalLaRates();
        if (budgetProposalLaRates == null || budgetProposalLaRates.size() == 0) {
            qValidCeRateTypes = qValidCeRateTypes.filter(notEqualsLabAllocationRateClassType());
        }

        addBudgetLineItemCalculatedAmountsForRateTypes(qValidCeRateTypes);
    }

    private void addBudgetLineItemCalculatedAmountsForRateTypes(List<ValidCeRateType> rateTypes) {
        if (CollectionUtils.isEmpty(rateTypes)) {
            return;
        }

        for (ValidCeRateType validCeRateType : rateTypes) {
            addBudgetLineItemCalculatedAmount( validCeRateType.getRateClassCode(), validCeRateType
                                               .getRateTypeCode(), validCeRateType.getRateClass().getRateClassType());
        }
    }

    private Equals equalsEmployeeBenefitsRateClassType() {
        return new Equals("rateClassType", RateClassType.EMPLOYEE_BENEFITS.getRateClassType());
    }

    private Equals equalsVacationRateClassType() {
        return new Equals("rateClassType", RateClassType.VACATION.getRateClassType());
    }

    private Equals equalsLabAllocationSalariesRateClassType() {
        return new Equals("rateClassType", RateClassType.LA_WITH_EB_VA.getRateClassType());
    }

    private void setLabAllocationSalariesCalculatedAmounts(BudgetLineItemBase lineItem) {
        QueryEngine queryEngine = new QueryEngine();        
        queryEngine.addDataCollection(ValidCalcType.class, getValidCalcTypes());

        QueryList<ValidCeRateType> qValidCeRateTypes = createQueryList(budgetLineItem.getCostElementBO().getValidCeRateTypes());
        QueryList<ValidCeRateType> qLabAllocSalRates = qValidCeRateTypes.filter(equalsLabAllocationSalariesRateClassType());

        if (CollectionUtils.isNotEmpty(qLabAllocSalRates)) {
            List<ValidCalcType> validCalCTypes = queryEngine.executeQuery(ValidCalcType.class, equalsEmployeeBenefitsRateClassType());
            if (CollectionUtils.isNotEmpty(validCalCTypes)) {
                ValidCalcType validCalcType = validCalCTypes.get(0);
                if (validCalcType.getDependentRateClassType().equals(RateClassType.LA_WITH_EB_VA.getRateClassType())) {
                    addBudgetLineItemCalculatedAmount(validCalcType.getRateClassCode(), validCalcType
                            .getRateTypeCode(), validCalcType.getRateClassType());
                }
            }
            validCalCTypes = queryEngine.executeQuery(ValidCalcType.class, equalsVacationRateClassType());
            if (!validCalCTypes.isEmpty()) {
                ValidCalcType validCalcType = (ValidCalcType) validCalCTypes.get(0);
                if (validCalcType.getDependentRateClassType().equals(RateClassType.LA_WITH_EB_VA.getRateClassType())) {
                    addBudgetLineItemCalculatedAmount(validCalcType.getRateClassCode(), validCalcType
                            .getRateTypeCode(), validCalcType.getRateClassType());
                }
            }
        }
    }
    
    public final void setCalculatedAmounts(BudgetDocument budgetDocument, BudgetLineItemBase budgetLineItem) {
        QueryEngine queryEngine = new QueryEngine();
        BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmt = null;

        budgetLineItem.setCostElementBO(getCostElementForLineItem(budgetLineItem));

        Map<String, String> validCeQMap = new HashMap<String, String>();
        validCeQMap.put("costElement", budgetLineItem.getCostElement());
        budgetLineItem.getCostElementBO().refreshReferenceObject("validCeRateTypes");

        QueryList<ValidCeRateType> qValidCeRateTypes = createQueryList(budgetLineItem.getCostElementBO().getValidCeRateTypes());
        setInflationRateOnLineItem(budgetLineItem);

        setValidCeRateTypeCalculatedAmounts(budgetLineItem);

        setLabAllocationSalariesCalculatedAmounts(budgetLineItem);
    }

    protected void setInfltionValidCalcCeRates(QueryList<ValidCeRateType> infltionValidCalcCeRates) {
        this.infltionValidCalcCeRates = infltionValidCalcCeRates;
    }

    protected abstract void addBudgetLineItemCalculatedAmount(String rateClassCode, String rateTypeCode, String rateClassType);

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

    /**
     * Gets the underrecoveryRates attribute.
     * 
     * @return Returns the underrecoveryRates.
     */
    public QueryList<BudgetProposalRate> getUnderrecoveryRates() {
        return underrecoveryRates;
    }

    /**
     * Sets the underrecoveryRates attribute value.
     * 
     * @param underrecoveryRates The underrecoveryRates to set.
     */
    public void setUnderrecoveryRates(QueryList<BudgetProposalRate> underrecoveryRates) {
        this.underrecoveryRates = underrecoveryRates;
    }

    public QueryList<BudgetProposalRate> getInflationRates() {
        return inflationRates;
    }

    public void setInflationRates(QueryList<BudgetProposalRate> inflationRates) {
        this.inflationRates = inflationRates;
    }
    /**
     * Gets the qlLineItemPropLaRates attribute. 
     * @return Returns the qlLineItemPropLaRates.
     */
    public QueryList<BudgetProposalLaRate> getQlLineItemPropLaRates() {
        return lineItemPropLaRates;
    }
    /**
     * Sets the qlLineItemPropLaRates attribute value.
     * @param qlLineItemPropLaRates The qlLineItemPropLaRates to set.
     */
    public void setQlLineItemPropLaRates(QueryList<BudgetProposalLaRate> qlLineItemPropLaRates) {
        this.lineItemPropLaRates = qlLineItemPropLaRates;
    }
    /**
     * Gets the qlLineItemPropRates attribute. 
     * @return Returns the qlLineItemPropRates.
     */
    public QueryList<BudgetProposalRate> getQlLineItemPropRates() {
        return lineItemPropRates;
    }
    /**
     * Sets the qlLineItemPropRates attribute value.
     * @param qlLineItemPropRates The qlLineItemPropRates to set.
     */
    public void setQlLineItemPropRates(QueryList<BudgetProposalRate> qlLineItemPropRates) {
        this.lineItemPropRates = qlLineItemPropRates;
    }
}
