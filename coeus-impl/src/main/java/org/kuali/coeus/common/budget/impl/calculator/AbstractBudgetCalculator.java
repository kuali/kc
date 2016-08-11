/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.budget.impl.calculator;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.budget.api.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.calculator.*;
import org.kuali.coeus.common.budget.framework.query.*;
import org.kuali.coeus.common.budget.framework.query.operator.*;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.rate.BudgetRatesService;
import org.kuali.coeus.common.budget.framework.rate.RateClass;
import org.kuali.coeus.common.budget.framework.rate.RateType;
import org.kuali.coeus.common.budget.framework.rate.AbstractBudgetRate;
import org.kuali.coeus.common.budget.framework.rate.BudgetLaRate;
import org.kuali.coeus.common.budget.framework.rate.ValidCeRateType;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.commitments.FandaRateType;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.nonpersonnel.AbstractBudgetCalculatedAmount;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemBase;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelCalculatedAmount;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.LegacyDataAdapter;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class AbstractBudgetCalculator {
    public static final String RATE_CLASS_CODE = "rateClassCode";
    public static final String ON_OFF_CAMPUS_FLAG = "onOffCampusFlag";
    public static final String RATE_TYPE_CODE = "rateTypeCode";
    public static final String START_DATE = "startDate";
    public static final String RATE_CLASS = "rateClass";
    public static final String RATE_CLASS_TYPE = "rateClassType";
    public static final String CALCULATED_COST_SHARING = "calculatedCostSharing";
    public static final String ACTIVITY_TYPE_CODE = "activityTypeCode";
    public static final String VALID_CE_RATE_TYPES = "validCeRateTypes";
    public static final String CALCULATED_COST = "calculatedCost";
    private static final Log LOG = LogFactory.getLog(AbstractBudgetCalculator.class);

    private LegacyDataAdapter legacyDataAdapter;
    private DateTimeService dateTimeService;
    protected Budget budget;
    protected BudgetLineItemBase budgetLineItem;
    private QueryList<BudgetLaRate> lineItemPropLaRates;
    private QueryList<BudgetRate> lineItemPropRates;
    private List<BreakUpInterval> breakupIntervals;
    private QueryList<ValidCeRateType> infltionValidCalcCeRates;
    private QueryList<BudgetRate> underrecoveryRates;
    private QueryList<BudgetRate> inflationRates;
    private ParameterService parameterService;


    public AbstractBudgetCalculator(Budget budget, BudgetLineItemBase budgetLineItem) {
        this.budget = budget;
        this.budgetLineItem = budgetLineItem;
        legacyDataAdapter = KcServiceLocator.getService(LegacyDataAdapter.class);
        dateTimeService = CoreApiServiceLocator.getDateTimeService();
        breakupIntervals = new ArrayList<>();
    }
    
    /**
     * 
     * Abstract method to populate the applicable cost and applicable cost sharing in boundary.
     * For LineItemCalculator, applicableCost would be the line item cost and
     * for PersonnelLineItemCalculator applicable cost would be cumulative salary of all personnel line item
     * @param boundary
     */
    public abstract void populateApplicableCosts(Boundary boundary);

    protected abstract void populateBudgetRateBaseList();

    /**
     * This method is for filtering rates and lab allocation rates. 
     * @param rates
     * @return
     */
    public QueryList filterRates(List rates) {
        if (!rates.isEmpty() && rates.get(0) instanceof BudgetRate) {
            QueryList qList = filterRates(rates, budgetLineItem.getEndDate(), getActivityTypeCodeFromParent());
            if (qList.isEmpty() && !budget.getActivityTypeCode().equals(getActivityTypeCodeFromParent())) {
                qList = filterRates(rates, budgetLineItem.getEndDate(), budget.getActivityTypeCode());
            }
            return qList;
        }
        else {
            return filterRates(rates, budgetLineItem.getEndDate(), null);
        }
    }

    protected String getActivityTypeCodeFromParent() {
        return budget.getBudgetParent().getActivityTypeCode();
    }

    protected QueryList filterRates(List rates, Date lineItemEndDate, String activityTypeCode) {
        List<AbstractBudgetCalculatedAmount> lineItemCalcAmts = budgetLineItem.getBudgetCalculatedAmounts();
        QueryList unfilteredRates = new QueryList(rates);
        QueryList budgetProposalRates = new QueryList();

        filterBasedOnCalculatedAmounts(lineItemCalcAmts, unfilteredRates, budgetProposalRates);

        if (activityTypeCode != null) {
            // Add inflation rates separately because, calculated amount list
            // will not have inflation rates listed
            addInflationRates(activityTypeCode, unfilteredRates, budgetProposalRates);
            addUnderrecoveryRates(unfilteredRates, budgetProposalRates);
            budgetProposalRates = budgetProposalRates.filter(new Equals(ACTIVITY_TYPE_CODE, activityTypeCode));
        }
        budgetProposalRates = filterBasedOnDate(lineItemEndDate, budgetProposalRates);
        return budgetProposalRates;
    }

    protected void filterBasedOnCalculatedAmounts(List<AbstractBudgetCalculatedAmount> lineItemCalcAmts,
                                                  QueryList unfilteredRates, QueryList budgetProposalRates) {
        for (AbstractBudgetCalculatedAmount calAmtsBean : lineItemCalcAmts) {
            String rateClassCode = calAmtsBean.getRateClassCode();
            String rateTypeCode = calAmtsBean.getRateTypeCode();
            Equals equalsRC = new Equals(RATE_CLASS_CODE, rateClassCode);
            Equals equalsRT = new Equals(RATE_TYPE_CODE, rateTypeCode);
            Equals equalsOnOff = new Equals(ON_OFF_CAMPUS_FLAG, budgetLineItem.getOnOffCampusFlag());

            And RCandRT = new And(equalsRC, equalsRT);
            And RCRTandOnOff = new And(RCandRT, equalsOnOff);
            QueryList filteredRates = unfilteredRates.filter(RCRTandOnOff);
            if (filteredRates != null && !filteredRates.isEmpty()) {
                budgetProposalRates.addAll(unfilteredRates.filter(RCRTandOnOff));
            }
        }
    }

    protected QueryList filterBasedOnDate(Date lineItemEndDate, QueryList budgetProposalRates) {
        if (CollectionUtils.isNotEmpty(budgetProposalRates)) {
            Or startsBeforeLineItemEndDate = new Or(new LesserThan(START_DATE, lineItemEndDate), new Equals(START_DATE, lineItemEndDate));
            budgetProposalRates = budgetProposalRates.filter(startsBeforeLineItemEndDate);
        }
        return budgetProposalRates;
    }

    protected void addUnderrecoveryRates(QueryList unfilteredRates, QueryList budgetProposalRates) {
        if(!isUndercoveryMatchesOverhead()) {
            Equals equalsRC = new Equals(RATE_CLASS_CODE, budget.getUrRateClassCode());
            Equals equalsOnOff = new Equals(ON_OFF_CAMPUS_FLAG, budgetLineItem.getOnOffCampusFlag());
            And RCRTandOnOff = new And(equalsRC, equalsOnOff);
            budgetProposalRates.addAll(unfilteredRates.filter(RCRTandOnOff));
        }
    }

    protected void addInflationRates(String activityTypeCode, QueryList unfilteredRates, QueryList budgetProposalRates) {
        if (CollectionUtils.isNotEmpty(infltionValidCalcCeRates)) {
            infltionValidCalcCeRates.forEach(inflationValidceRate -> {
                Equals equalsRC = new Equals(RATE_CLASS_CODE, inflationValidceRate.getRateClassCode());
                Equals equalsRT = new Equals(RATE_TYPE_CODE, inflationValidceRate.getRateTypeCode());
                Equals equalsOnOff = new Equals(ON_OFF_CAMPUS_FLAG, budgetLineItem.getOnOffCampusFlag());
                And RCandRT = new And(equalsRC, equalsRT);
                And RCRTandOnOff = new And(RCandRT, equalsOnOff);
                Equals eActType = new Equals(ACTIVITY_TYPE_CODE, activityTypeCode);
                And RCRTandOnOffandActType = new And(RCRTandOnOff, eActType);
                QueryList<BudgetRate> filteredRates = unfilteredRates.filter(RCRTandOnOffandActType);
                if (CollectionUtils.isNotEmpty(filteredRates)) {
                    setInflationRates(filteredRates);
                    budgetProposalRates.addAll(filteredRates);
                }
            });
        }
    }

    protected boolean isUndercoveryMatchesOverhead() {
        return budget.getOhRateClassCode().equals(budget.getUrRateClassCode());
    }

    /**
     * 
     * This method does the calculation for each line item. Both BudgetLineItem and BudgetPersonnelLineItem invokes this method to do the calculation.
     */
    public void calculate() {
        if (isProposalParent()) {
            budgetLineItem.setDirectCost(budgetLineItem.getLineItemCost());
            budgetLineItem.setTotalCostSharingAmount(budgetLineItem.getCostSharingAmount());
            budgetLineItem.setIndirectCost(ScaleTwoDecimal.ZERO);

            populateSalaryIfPersonnel();

            QueryList<AbstractBudgetCalculatedAmount> calcAmts = new QueryList<>();
            calcAmts.addAll(budgetLineItem.getBudgetCalculatedAmounts());
            for (AbstractBudgetCalculatedAmount calcAmt : calcAmts) {
                calcAmt.refreshReferenceObject(RATE_CLASS);
                calcAmt.setRateClassType(calcAmt.getRateClass().getRateClassTypeCode());
            }
            NotEquals notEqualsOH = new NotEquals(RATE_CLASS_TYPE, RateClassType.OVERHEAD.getRateClassType());
            Equals equalsOH = new Equals(RATE_CLASS_TYPE, RateClassType.OVERHEAD.getRateClassType());
            budgetLineItem.setDirectCost(budgetLineItem.getDirectCost().add(calcAmts.sumObjects(CALCULATED_COST, notEqualsOH)));
            budgetLineItem.setIndirectCost(budgetLineItem.getIndirectCost().add(calcAmts.sumObjects(CALCULATED_COST, equalsOH)));
            budgetLineItem.setTotalCostSharingAmount(budgetLineItem.getTotalCostSharingAmount().add(
                    calcAmts.sumObjects(CALCULATED_COST_SHARING)));
            return;
        }
        budgetLineItem.setDirectCost(budgetLineItem.getLineItemCost());
        budgetLineItem.setTotalCostSharingAmount(budgetLineItem.getCostSharingAmount());
        budgetLineItem.setIndirectCost(ScaleTwoDecimal.ZERO);
        budgetLineItem.setUnderrecoveryAmount(ScaleTwoDecimal.ZERO);
        createAndCalculateBreakupIntervals();
        updateBudgetLineItemCalculatedAmounts();
        populateBudgetRateBaseList();
    }

    protected boolean isProposalParent() {
        return budget.getBudgetParent() instanceof DevelopmentProposal && ((DevelopmentProposal) budget.getBudgetParent()).isParent();
    }

    protected void populateSalaryIfPersonnel() {
        if (budgetLineItem instanceof BudgetPersonnelDetails) {
            BudgetPersonnelDetails budgetPersonnelLineItem = (BudgetPersonnelDetails)budgetLineItem;
            SalaryCalculator salaryCalculator = new SalaryCalculator(budget, budgetPersonnelLineItem);
            salaryCalculator.calculate();
            budgetPersonnelLineItem.setLineItemCost(budgetPersonnelLineItem.getSalaryRequested());
        }
    }

    /**
     * 
     * This method is for populating the calculated amounts by looking at the rate class and rate type for each line item gets applied to.
     */
    @SuppressWarnings("unchecked")
    protected void updateBudgetLineItemCalculatedAmounts() {
        
        List<AbstractBudgetCalculatedAmount> lineItemCalcAmts = budgetLineItem.getBudgetCalculatedAmounts();
        List<BreakUpInterval> cvLIBreakupIntervals = getBreakupIntervals();
        if (lineItemCalcAmts != null && lineItemCalcAmts.size() > 0 && cvLIBreakupIntervals != null
                && cvLIBreakupIntervals.size() > 0) {
            /*
             * Sum up all the calculated costs for each breakup interval and then update the line item cal amts.
             */
            String rateClassCode;
            String rateTypeCode;
            ScaleTwoDecimal totalCalculatedCost;
            ScaleTwoDecimal totalCalculatedCostSharing;
            ScaleTwoDecimal directCost = ScaleTwoDecimal.ZERO;
            ScaleTwoDecimal indirectCost = ScaleTwoDecimal.ZERO;
            Equals equalsRC;
            Equals equalsRT;
            And RCandRT = null;
            QueryList<RateAndCost> cvCombinedAmtDetails = new QueryList<RateAndCost>();
            for (BreakUpInterval brkUpInterval : cvLIBreakupIntervals) {
                cvCombinedAmtDetails.addAll(brkUpInterval.getRateAndCosts());
            }
            for (AbstractBudgetCalculatedAmount calculatedAmount : lineItemCalcAmts) {
                rateClassCode = calculatedAmount.getRateClassCode();
                rateTypeCode = calculatedAmount.getRateTypeCode();
                equalsRC = new Equals(RATE_CLASS_CODE, rateClassCode);
                equalsRT = new Equals(RATE_TYPE_CODE, rateTypeCode);
                RCandRT = new And(equalsRC, equalsRT);
                totalCalculatedCost = cvCombinedAmtDetails.sumObjects(CALCULATED_COST, RCandRT);

                calculatedAmount.setCalculatedCost(totalCalculatedCost);

                totalCalculatedCostSharing = cvCombinedAmtDetails.sumObjects("calculatedCostSharing", RCandRT);
                calculatedAmount.setCalculatedCostSharing(totalCalculatedCostSharing);
            }

            updateLineItemUnderrecovery(cvLIBreakupIntervals, budgetLineItem);

            /*
             * Sum up all direct costs ie, rates for RateClassType <> 'O', for each breakup interval plus the line item cost, and
             * then update the line item details.
             */
            NotEquals notEqualsOH = new NotEquals(RATE_CLASS_TYPE, RateClassType.OVERHEAD.getRateClassType());
            boolean directCostRolledUp = false;
            boolean resetTotalUnderRecovery = false;
            ScaleTwoDecimal newTotalUrAmount = ScaleTwoDecimal.ZERO;
            ScaleTwoDecimal newTotalCostSharing = ScaleTwoDecimal.ZERO;
            if (budgetLineItem instanceof BudgetLineItem && CollectionUtils.isNotEmpty(((BudgetLineItem)budgetLineItem).getBudgetPersonnelDetailsList())) {
                for (BudgetPersonnelDetails budgetPersonnelDetail : ((BudgetLineItem)budgetLineItem).getBudgetPersonnelDetailsList()) {
                    List<BudgetPersonnelCalculatedAmount> personnelCalAmts = budgetPersonnelDetail.getBudgetCalculatedAmounts();
                    newTotalUrAmount = newTotalUrAmount.add(budgetPersonnelDetail.getUnderrecoveryAmount());
                    resetTotalUnderRecovery = true;
                    if (CollectionUtils.isNotEmpty(personnelCalAmts)) {
                        for (BudgetPersonnelCalculatedAmount personnelCalAmt : personnelCalAmts) {
                            if (personnelCalAmt.getRateClass() == null) {
                                personnelCalAmt.refreshReferenceObject(RATE_CLASS);
                            }
                            if (!personnelCalAmt.getRateClass().getRateClassTypeCode().equals(RateClassType.OVERHEAD.getRateClassType())) {
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
                directCost = cvCombinedAmtDetails.sumObjects(CALCULATED_COST, notEqualsOH);
            }
            budgetLineItem.setDirectCost(directCost.add(budgetLineItem.getLineItemCost()));
            /*
             * Sum up all Indirect costs ie, rates for RateClassType = 'O', for each breakup interval and then update the line item
             * details.
             */
            Equals equalsOH = new Equals(AbstractBudgetCalculator.RATE_CLASS_TYPE, RateClassType.OVERHEAD.getRateClassType());
            if (!directCostRolledUp) {
                indirectCost = cvCombinedAmtDetails.sumObjects(CALCULATED_COST, equalsOH);
            }
            budgetLineItem.setIndirectCost(indirectCost);

            /*
             * Sum up all Cost Sharing amounts ie, rates for RateClassType <> 'O' and set in the calculatedCostSharing field of line
             * item details
             */
            if (!directCostRolledUp) {
                totalCalculatedCostSharing = cvCombinedAmtDetails.sumObjects(CALCULATED_COST_SHARING);
            } 
            else {
                totalCalculatedCostSharing = newTotalCostSharing;
            }

            budgetLineItem.setTotalCostSharingAmount(budgetLineItem.getCostSharingAmount() == null ? totalCalculatedCostSharing :
                    budgetLineItem.getCostSharingAmount().add(totalCalculatedCostSharing));

        } else if (lineItemCalcAmts != null && lineItemCalcAmts.size() > 0 && 
                (budgetLineItem.getLineItemCost().equals(ScaleTwoDecimal.ZERO) ||
                        CollectionUtils.isEmpty(cvLIBreakupIntervals))) {
            for (AbstractBudgetCalculatedAmount calculatedAmount : lineItemCalcAmts) {
                  calculatedAmount.setCalculatedCost(ScaleTwoDecimal.ZERO);
                  calculatedAmount.setCalculatedCostSharing(ScaleTwoDecimal.ZERO);
          }
        } 
    }

    protected void updateLineItemUnderrecovery(List<BreakUpInterval> cvLIBreakupIntervals, BudgetLineItemBase budgetLineItem) {
        boolean itemHasOverhead = cvLIBreakupIntervals.stream().anyMatch(breakupInterval -> doesLineItemHaveOverhead(breakupInterval.getRateAndCosts()));
        if (itemHasOverhead) {
            ScaleTwoDecimal totalUnderRecovery;
            totalUnderRecovery = new QueryList<>(cvLIBreakupIntervals).sumObjects("underRecovery");
            budgetLineItem.setUnderrecoveryAmount(totalUnderRecovery);
        }
    }


    protected void createAndCalculateBreakupIntervals() {
        populateCalculatedAmountLineItems();
        setQlLineItemPropLaRates(filterRates(getBudgetLaRates()));
        setQlLineItemPropRates(filterRates(getBudgetRates()));
        createBreakUpInterval();
        calculateBreakUpInterval();
    }

    protected abstract List<BudgetRate> getBudgetRates();
    protected abstract List<BudgetLaRate> getBudgetLaRates();
    /**
     * Combine the sorted Prop &amp; LA rates, which should be in sorted order(asc). Now create the breakup boundaries and use it to
     * create breakup intervals and set all the values required for calculation. Then call calculateBreakupInterval method for each
     * AmountBean for setting the calculated cost &amp; calculated cost sharing ie for each rate class &amp; rate type.
     */
    protected void createBreakUpInterval() {
        String messageTemplate;
        String multipleRatesMesgTemplate;
        String message;
        if (breakupIntervals == null)
            breakupIntervals = new ArrayList<>();

        if (budgetLineItem.getOnOffCampusFlag()) {
            messageTemplate = "On-Campus rate information not available for Rate Class - \'";
            multipleRatesMesgTemplate = "Multiple On-Campus rates found for the period ";
        }
        else {
            messageTemplate = "Off-Campus rate information not available for Rate Class - \'";
            multipleRatesMesgTemplate = "Multiple Off-Campus rates found for the period ";
        }

        QueryList<BudgetLaRate> lineItemPropLaRates = getQlLineItemPropLaRates();
        QueryList<BudgetRate> lineItemPropRates = getQlLineItemPropRates();
        QueryList combinedRates = combineRates(lineItemPropLaRates, lineItemPropRates);


        List<Boundary> boundaries = createBreakupBoundaries(combinedRates, budgetLineItem.getStartDate(), budgetLineItem.getEndDate());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM-dd-yyyy");
        // create breakup intervals based on the breakup boundaries
        if (boundaries != null && boundaries.size() > 0) {
            for (Boundary boundary : boundaries) {
                BreakUpInterval breakUpInterval = getNewBreakupInterval(boundary);
                QueryList<RateAndCost> rateAndCosts = new QueryList<>();
                QueryList<BudgetRate> breakupPropRates = new QueryList<>();
                QueryList<BudgetLaRate> breakupPropLARates = new QueryList<>();
                QueryList multipleRates = new QueryList();

                populateApplicableCosts(boundary);

                breakUpInterval.setApplicableAmt(boundary.getApplicableCost());
                breakUpInterval.setApplicableAmtCostSharing(boundary.getApplicableCostSharing());

                List<AbstractBudgetCalculatedAmount> budgetCalculatedAmounts = budgetLineItem.getBudgetCalculatedAmounts();
                for (AbstractBudgetCalculatedAmount budgetLineItemCalculatedAmount : budgetCalculatedAmounts) {
                    Boolean applyRateFlag = budgetLineItemCalculatedAmount.getApplyRateFlag();
                    refreshRateClass(budgetLineItemCalculatedAmount.getRateClassCode(), budgetLineItemCalculatedAmount);
                    String rateClassType = budgetLineItemCalculatedAmount.getRateClass().getRateClassTypeCode();
                    message = messageTemplate + budgetLineItemCalculatedAmount.getRateClass().getDescription()
                            + "\'  Rate Type - \'" + budgetLineItemCalculatedAmount.getRateTypeDescription()
                            + "\' for Period - ";

                    // if apply flag is false and rate class type is not Overhead then skip
                    if ((applyRateFlag == null || !applyRateFlag) && !rateClassType.equals(RateClassType.OVERHEAD.getRateClassType())) {
                        continue;
                    }

                    rateAndCosts.add(getNewRateCost(rateClassType, budgetLineItemCalculatedAmount.getRateClassCode(),
                            budgetLineItemCalculatedAmount.getRateTypeCode(), applyRateFlag));

                    List<String> warningMessages = new ArrayList<>();
                    if (isLARate(rateClassType)) {
                        breakupLARates(multipleRatesMesgTemplate, message, lineItemPropLaRates, simpleDateFormat, boundary, breakupPropLARates,
                                multipleRates, warningMessages, budgetLineItemCalculatedAmount);
                    }
                    else {
                        multipleRates = breakupOtherProposalRates(multipleRatesMesgTemplate, message, lineItemPropRates, simpleDateFormat,
                                boundary, breakupPropRates, multipleRates, warningMessages, budgetLineItemCalculatedAmount);
                    }
                    logWarningMessages(warningMessages);
                }
                setBreakupIntervalRates(lineItemPropRates, boundary, breakUpInterval, rateAndCosts, breakupPropRates, breakupPropLARates);
            }
        }
    }

    protected QueryList combineRates(QueryList<BudgetLaRate> lineItemPropLaRates, QueryList<BudgetRate> lineItemPropRates) {
        // combine the sorted Prop & LA Rates
        QueryList combinedRates = new QueryList();
        combinedRates.addAll(lineItemPropRates);
        combinedRates.addAll(lineItemPropLaRates);
        combinedRates.sort(START_DATE, true);
        return combinedRates;
    }

    protected void logWarningMessages(List<String> warningMessages) {
        for(String warningMessage : warningMessages) {
            LOG.warn(warningMessage);
        }
    }

    protected void setBreakupIntervalRates(QueryList<BudgetRate> lineItemPropRates, Boundary boundary, BreakUpInterval breakUpInterval, QueryList<RateAndCost> rateAndCosts, QueryList<BudgetRate> breakupPropRates, QueryList<BudgetLaRate> breakupPropLARates) {
        // set the values for the breakup interval in the BreakupInterval bean
        if (CollectionUtils.isNotEmpty(rateAndCosts)) {
            breakUpInterval.setRateAndCosts(rateAndCosts);
            breakUpInterval.setBudgetProposalRates(breakupPropRates);
            breakUpInterval.setBudgetProposalLaRates(breakupPropLARates);
            breakupIntervals.add(breakUpInterval);
        }

        setUnderrecoveryRateBean(lineItemPropRates, boundary, breakUpInterval);
        calculateUnderrecoveryForLineItemsWithoutOverhead(breakUpInterval, rateAndCosts);
    }

    protected void calculateUnderrecoveryForLineItemsWithoutOverhead(BreakUpInterval breakUpInterval, QueryList<RateAndCost> rateAndCosts) {
        if (breakUpInterval.getURRatesBean() != null && (CollectionUtils.isEmpty(rateAndCosts) || !doesLineItemHaveOverhead(rateAndCosts))) {
            ScaleTwoDecimal underrecoveryRate = breakUpInterval.getURRatesBean().getApplicableRate();
            ScaleTwoDecimal overheadRate = ScaleTwoDecimal.ZERO;
            underrecoveryRate = underrecoveryRate.subtract(overheadRate);
            ScaleTwoDecimal underrecoveryAmount = breakUpInterval.getApplicableAmt().percentage(underrecoveryRate);
            budgetLineItem.setUnderrecoveryAmount(underrecoveryAmount);
        }
    }

    protected boolean doesLineItemHaveOverhead(QueryList<RateAndCost> rateAndCosts) {
        return rateAndCosts.stream().anyMatch(rateAndCost -> RateClassType.OVERHEAD.getRateClassType().equalsIgnoreCase(rateAndCost.getRateClassType()));
    }

    /*
    Unrecovered F & A Rate - F&A Rate = underrecovery. This method gets the unrecoveredF&A rate.
     */
    public void setUnderrecoveryRateBean(QueryList<BudgetRate> lineItemPropRates, Boundary boundary, BreakUpInterval breakUpInterval) {
        QueryList<ValidCeRateType> underrecoveryRates = getUnderrecoveryRateMappedToCostElement(new QueryList<>(budgetLineItem.getCostElementBO().getValidCeRateTypes()));
        // get the rate class, rate type from the cost element and use that.
        if (!isUndercoveryMatchesOverhead()) {
            setEmptyUnderrecoveryRate(breakUpInterval);
            if (!underrecoveryRates.isEmpty()) {
                // you cannot have more than one rate mapped to this cost element, so always use the first.
                // if there's more than one mapped, it is wrong.
                QueryList<BudgetRate> underRecoveryRates = getUnderrecoveryRatesFromPropRates(lineItemPropRates, underrecoveryRates);
                if (CollectionUtils.isNotEmpty(underRecoveryRates)) {
                    QueryList filteredUnderrecoveryRates = filterUnderrecoveryByEndDate(boundary, underRecoveryRates);
                    if (CollectionUtils.isNotEmpty(filteredUnderrecoveryRates)) {
                        filteredUnderrecoveryRates.sort(START_DATE, false);
                        breakUpInterval.setURRatesBean((BudgetRate) filteredUnderrecoveryRates.get(0));
                    }
                }
            }
        }
    }

    protected void setEmptyUnderrecoveryRate(BreakUpInterval breakUpInterval) {
        // if there are no rates, it means the line item does not have overhead but
        // it can still have underrecovery.
        BudgetRate rate = new BudgetRate();
        rate.setInstituteRate(ScaleTwoDecimal.ZERO);
        breakUpInterval.setURRatesBean(rate);
    }

    protected And getRateClassRateTypeAndWithinStartAndEndDateCondition(Boundary boundary, AbstractBudgetCalculatedAmount calculatedAmount) {
        Equals equalsRC = new Equals(RATE_CLASS_CODE, calculatedAmount.getRateClassCode());
        Equals equalsRT = new Equals(RATE_TYPE_CODE, calculatedAmount.getRateTypeCode());
        return new And(new And(equalsRC, equalsRT), getDateCondition(boundary));
    }

    protected And getRateClassAndRateTypeAndLessThanStartDateCondition(Boundary boundary, AbstractBudgetCalculatedAmount calculatedAmount) {
        Equals equalsRC = new Equals(RATE_CLASS_CODE, calculatedAmount.getRateClassCode());
        Equals equalsRT = new Equals(RATE_TYPE_CODE, calculatedAmount.getRateTypeCode());
        return new And(new And(equalsRC, equalsRT), new Or(new LesserThan(START_DATE, boundary.getEndDate()), new Equals(START_DATE, boundary.getEndDate())));
    }

    protected And getDateCondition(Boundary boundary) {
        LesserThan ltEndDate = new LesserThan(START_DATE, boundary.getEndDate());
        Equals equalsEndDate = new Equals(START_DATE, boundary.getEndDate());
        final GreaterThan greaterThanStartDate = new GreaterThan(START_DATE, boundary.getStartDate());
        final Equals equalToStartDate = new Equals(START_DATE, boundary.getStartDate());
        final Or greaterThanOrEqualToStartDate = new Or(greaterThanStartDate, equalToStartDate);
        final Or lessThanOrEqualToEndDate = new Or(ltEndDate, equalsEndDate);
        return new And(greaterThanOrEqualToStartDate, lessThanOrEqualToEndDate);
    }

    protected BreakUpInterval getNewBreakupInterval(Boundary boundary) {
        BreakUpInterval breakUpInterval = new BreakUpInterval();
        breakUpInterval.setBoundary(boundary);
        breakUpInterval.setBudgetId(budgetLineItem.getBudgetId());
        breakUpInterval.setBudgetPeriod(budgetLineItem.getBudgetPeriod());
        breakUpInterval.setLineItemNumber(budgetLineItem.getLineItemNumber());
        return breakUpInterval;
    }

    protected void refreshRateClass(String rateClassCode, AbstractBudgetCalculatedAmount budgetLineItemCalculatedAmount) {
        if (budgetLineItemCalculatedAmount.getRateClass() == null && rateClassCode != null) {
            budgetLineItemCalculatedAmount.setRateClass(getLegacyDataAdapter().findBySinglePrimaryKey(RateClass.class, rateClassCode));
        }
    }

    protected QueryList filterUnderrecoveryByEndDate(Boundary boundary, QueryList<BudgetRate> underRecoveryRates) {
        QueryList tempRates;LesserThan ltEndDate = new LesserThan(START_DATE, boundary.getEndDate());
        Equals equalsEndDate = new Equals(START_DATE, boundary.getEndDate());
        Or ltEndDateOrEqEndDate = new Or(ltEndDate, equalsEndDate);
        tempRates = underRecoveryRates.filter(ltEndDateOrEqEndDate);
        return tempRates;
    }

    protected QueryList<BudgetRate>getUnderrecoveryRatesFromPropRates(QueryList<BudgetRate> lineItemPropRates, QueryList<ValidCeRateType> underrecoveryRates) {
        Equals equalsRC = new Equals(RATE_CLASS_CODE, underrecoveryRates.get(0).getRateClassCode());
        Equals equalsOnOff = new Equals(ON_OFF_CAMPUS_FLAG, budgetLineItem.getOnOffCampusFlag());
        Equals equalsRT = new Equals(RATE_TYPE_CODE, underrecoveryRates.get(0).getRateTypeCode());
        And RCandRT = new And(equalsRC, equalsRT);
        And RCRTandOnOff = new And(RCandRT, equalsOnOff);
        return lineItemPropRates.filter(RCRTandOnOff);
    }

    protected RateAndCost getNewRateCost(String rateClassType, String rateClassCode, String rateTypeCode, Boolean applyRateFlag) {
        RateAndCost rateCost = new RateAndCost();
        rateCost.setApplyRateFlag(applyRateFlag);
        rateCost.setRateClassType(rateClassType);
        rateCost.setRateClassCode(rateClassCode);
        rateCost.setRateTypeCode(rateTypeCode);
        rateCost.setCalculatedCost(ScaleTwoDecimal.ZERO);
        rateCost.setCalculatedCostSharing(ScaleTwoDecimal.ZERO);
        return rateCost;
    }

    protected QueryList breakupOtherProposalRates(String multipleRatesMesgTemplate, String message, QueryList<BudgetRate> lineItemPropRates, SimpleDateFormat simpleDateFormat, Boundary boundary,
                                                QueryList<BudgetRate> breakupPropRates, QueryList multipleRates, List<String> warningMessages, AbstractBudgetCalculatedAmount budgetLineItemCalculatedAmount) {
        And RCRTandLtStartDate = getRateClassAndRateTypeAndLessThanStartDateCondition(boundary, budgetLineItemCalculatedAmount);
        And RCRTandgtStartDateAndltEndDate = getRateClassRateTypeAndWithinStartAndEndDateCondition(boundary, budgetLineItemCalculatedAmount);

        QueryList validProposalRates = lineItemPropRates.filter(RCRTandLtStartDate);

        if (CollectionUtils.isNotEmpty(validProposalRates)) {
            multipleRates = lineItemPropRates.filter(RCRTandgtStartDateAndltEndDate);
            if (multipleRates != null && multipleRates.size() > 1) {
                message = multipleRatesMesgTemplate + simpleDateFormat.format(boundary.getStartDate()) + " to "
                        + simpleDateFormat.format(boundary.getEndDate()) + " for Rate Class - \'"
                        + budgetLineItemCalculatedAmount.getRateClass().getDescription() + "\'  Rate Type - \'"
                        + budgetLineItemCalculatedAmount.getRateTypeDescription();
                warningMessages.add(message);
            }
            else {
                validProposalRates.sort(START_DATE, false);
                breakupPropRates.add((BudgetRate) validProposalRates.get(0));
            }
        }
        else {
            message = message + simpleDateFormat.format(boundary.getStartDate()) + " to "
                    + simpleDateFormat.format(boundary.getEndDate());
            warningMessages.add(message);
        }
        return multipleRates;
    }

    protected void breakupLARates(String multipleRatesMesgTemplate, String message, QueryList<BudgetLaRate> lineItemPropLaRates,
                                SimpleDateFormat simpleDateFormat, Boundary boundary, QueryList<BudgetLaRate> breakupPropLARates,
                                  QueryList multipleRates, List<String> warningMessages, AbstractBudgetCalculatedAmount budgetLineItemCalculatedAmount) {
        And RCRTandLtStartDate = getRateClassAndRateTypeAndLessThanStartDateCondition(boundary, budgetLineItemCalculatedAmount);
        QueryList validLARates = lineItemPropLaRates.filter(RCRTandLtStartDate);
        if (CollectionUtils.isNotEmpty(validLARates)) {
            if (multipleRates != null && multipleRates.size() > 1) {
                message = multipleRatesMesgTemplate + simpleDateFormat.format(boundary.getStartDate()) + " to "
                        + simpleDateFormat.format(boundary.getEndDate()) + " for Rate Class - \'"
                        + budgetLineItemCalculatedAmount.getRateClass().getDescription() + "\'  Rate Type - \'"
                        + budgetLineItemCalculatedAmount.getRateTypeDescription();
                warningMessages.add(message);
            }
            else {
                // get latest rate
                validLARates.sort(START_DATE, false);
                breakupPropLARates.add((BudgetLaRate) validLARates.get(0));
            }
        }
        else {
            message = message + simpleDateFormat.format(boundary.getStartDate()) + " to "
                    + simpleDateFormat.format(boundary.getEndDate());
            warningMessages.add(message);
        }
    }

    protected boolean isLARate(String rateClassType) {
        return rateClassType.equalsIgnoreCase(RateClassType.LAB_ALLOCATION.getRateClassType()) || rateClassType.equalsIgnoreCase(RateClassType.LA_SALARIES.getRateClassType());
    }

    protected QueryList<ValidCeRateType> getUnderrecoveryRateMappedToCostElement(QueryList<ValidCeRateType> validCeRateTypes) {
        Equals equalsRC = new Equals(RATE_CLASS_CODE, budget.getUrRateClassCode());
        Equals equalsRCT = new Equals(RATE_CLASS_TYPE, RateClassType.OVERHEAD.getRateClassType());
        And RCRTandRCT = new And(equalsRC, equalsRCT);
        if(budgetLineItem.getCostElementBO()!=null && budgetLineItem.getCostElementBO().getValidCeRateTypes().isEmpty() ){
            budgetLineItem.getCostElementBO().refreshReferenceObject(VALID_CE_RATE_TYPES);
        }
        return validCeRateTypes.filter(RCRTandRCT);
    }

    public String getPersonnelBudgetCategoryTypeCode() {
        return getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_BUDGET, ParameterConstants.DOCUMENT_COMPONENT, Constants.BUDGET_CATEGORY_TYPE_PERSONNEL);
    }

    /**
     * Use the combined &amp; sorted Prop &amp; LA rates to create Boundary objects. Each Boundary will contain start date &amp; end date. Check
     * whether any rate changes, and break at this point to create a new boundary.
     * 
     * @return List of boundary objects
     */
    public List<Boundary> createBreakupBoundaries(QueryList<AbstractBudgetRate> qlCombinedRates, Date liStartDate,
            Date liEndDate) {
        List<Boundary> boundaries = new ArrayList<>();
        if (qlCombinedRates != null && qlCombinedRates.size() > 0) {
            Date tempStartDate = liStartDate;
            Date tempEndDate;
            Date rateChangeDate;
            GreaterThan greaterThan = new GreaterThan(START_DATE, liStartDate);
            qlCombinedRates = qlCombinedRates.filter(greaterThan);
            qlCombinedRates.sort(START_DATE, true);
            for (AbstractBudgetRate laRate : qlCombinedRates) {
                rateChangeDate = laRate.getStartDate();
                if (rateChangeDate.after(tempStartDate)) {
                    Calendar temEndCal = dateTimeService.getCalendar(rateChangeDate);
                    temEndCal.add(Calendar.DAY_OF_MONTH, -1);
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
             * add one more boundary if no rate change on endDate
             * and atleast one boundary is present
             */
            if (boundaries.size() > 0) {
                Boundary boundary = new Boundary(tempStartDate, liEndDate);
                boundaries.add(boundary);
            }
            /**
             * if no rate changes during the period create one boundary with
             * startDate &amp; endDate same as that for line item
             */
            if (boundaries.size() == 0) {
                Boundary boundary = new Boundary(liStartDate, liEndDate);
                boundaries.add(boundary);
            }
        }
        return boundaries;
    }

    protected void calculateBreakUpInterval() {
        int rateNumber = 0;
        List<BreakUpInterval> cvLIBreakupIntervals = getBreakupIntervals();
        for (BreakUpInterval breakUpInterval : cvLIBreakupIntervals) {
            breakUpInterval.setRateNumber(rateNumber);
            getBreakupIntervalService().calculate(breakUpInterval);
        }
    }

    protected BreakupIntervalService getBreakupIntervalService() {
        return KcServiceLocator.getService(BreakupIntervalService.class);
    }
    protected List<ValidCalcType> getValidCalcTypes() {
        return (List<ValidCalcType>) legacyDataAdapter.findAll(ValidCalcType.class);
    }

    protected abstract void populateCalculatedAmountLineItems();

    protected <T> QueryList<T> createQueryList(List<T> immutableList) {
        if (immutableList == null) {
            return new QueryList();
        }

        return new QueryList(immutableList);
    }

    protected void setInflationRateOnLineItem(BudgetLineItemBase lineItem) {
        QueryList<ValidCeRateType> qValidCeRateTypes = createQueryList(budgetLineItem.getCostElementBO().getValidCeRateTypes());

        // Check whether it contains Inflation Rate
        QueryList<ValidCeRateType> inflationValidCeRates = 
            qValidCeRateTypes.filter(new Equals(RATE_CLASS_TYPE, RateClassType.INFLATION.getRateClassType()));
        if (!inflationValidCeRates.isEmpty()) {
            if (lineItem.getApplyInRateFlag()) {
                setInfltionValidCalcCeRates(inflationValidCeRates);
            }
        }
    }

    protected Equals equalsOverHeadRateClassType() {
        return new Equals(RATE_CLASS_TYPE, RateClassType.OVERHEAD.getRateClassType());
    }

    protected NotEquals notEqualsInflationRateClassType() {
        return new NotEquals(RATE_CLASS_TYPE, RateClassType.INFLATION.getRateClassType());
    }

    protected Equals equalsOverHeadRateClassCode() {
        return new Equals(RATE_CLASS_CODE, "" + budget.getOhRateClassCode());
    }

    protected NotEquals notEqualsOverHeadRateClassType() {
        return new NotEquals(RATE_CLASS_TYPE, RateClassType.OVERHEAD.getRateClassType());
    }

    protected And notEqualsLabAllocationRateClassType() {
        return new NotEquals(RATE_CLASS_TYPE, RateClassType.LAB_ALLOCATION.getRateClassType())
            .and(new NotEquals(RATE_CLASS_TYPE, RateClassType.LA_SALARIES.getRateClassType()));
    }

    
    protected void setValidCeRateTypeCalculatedAmounts(BudgetLineItemBase lineItem) {
        QueryList<ValidCeRateType> qValidCeRateTypes = createQueryList(budgetLineItem.getCostElementBO().getValidCeRateTypes());
        qValidCeRateTypes = qValidCeRateTypes.filter(equalsOverHeadRateClassType()
                                                     .and(equalsOverHeadRateClassCode())
                                                     .or(notEqualsOverHeadRateClassType())
                                                     .and(notEqualsInflationRateClassType()));

        List<BudgetLaRate> budgetLaRates = budget.getBudgetLaRates();
        if (budgetLaRates == null || budgetLaRates.size() == 0) {
            qValidCeRateTypes = qValidCeRateTypes.filter(notEqualsLabAllocationRateClassType());
        }
        addBudgetLineItemCalculatedAmountsForRateTypes(qValidCeRateTypes);
    }

    protected void addBudgetLineItemCalculatedAmountsForRateTypes(List<ValidCeRateType> rateTypes) {
        if (CollectionUtils.isEmpty(rateTypes)) {
            return;
        }

        for (ValidCeRateType validCeRateType : rateTypes) {
            String rateClassType = validCeRateType.getRateClass().getRateClassTypeCode();
            if(rateClassType.equals(RateClassType.OVERHEAD.getRateClassType()) && 
                    !isProposalBudget()){
                addOHBudgetLineItemCalculatedAmountForAward( validCeRateType.getRateClassCode(), validCeRateType.getRateType(), 
                        validCeRateType.getRateClass().getRateClassTypeCode());
            }else{
                addBudgetLineItemCalculatedAmount( validCeRateType.getRateClassCode(), validCeRateType.getRateType(), 
                                            validCeRateType.getRateClass().getRateClassTypeCode());
            }
        }
    }

    protected boolean isProposalBudget() {
        return budget.getBudgetParent().isProposalBudget();
    }

    protected void addOHBudgetLineItemCalculatedAmountForAward(String rateClassCode,
            RateType rateType, String rateClassType) {
    	BudgetRate awardBudgetRate = budget.getBudgetRates().stream()
    		.filter(rate -> StringUtils.equals(rate.getRateClassCode(), rateClassCode))
    		.filter(rate -> Objects.equals(rate.getOnOffCampusFlag(), budgetLineItem.getOnOffCampusFlag()))
    		.filter(rate -> StringUtils.equals(rate.getRateTypeCode(), rateType.getRateTypeCode()))
    		.findAny().orElse(null);
    	if (awardBudgetRate != null) {
            awardBudgetRate.setBudget(budget);
            if(awardBudgetRate.getNonEditableRateFlag()){
                AbstractBudgetCalculatedAmount budgetCalculatedAmount = getNewBudgetCalculatedAmount(rateClassType, awardBudgetRate.getRateClassCode(),
                        awardBudgetRate.getRateTypeCode(), getAwardRateTypeDescription(awardBudgetRate.getRateTypeCode()));

                budgetCalculatedAmount.setRateClass(awardBudgetRate.getRateClass());
                addCalculatedAmount(budgetCalculatedAmount);
            }else{
                addBudgetLineItemCalculatedAmount( rateClassCode, rateType, rateClassType);
            }
        }

    }

    protected AbstractBudgetCalculatedAmount getNewBudgetCalculatedAmount(String rateClassType, String rateClassCode, String rateTypeCode,
                                                                          String rateTypeDescription) {
        AbstractBudgetCalculatedAmount budgetCalculatedAmount = getNewCalculatedAmountInstance();
        budgetCalculatedAmount.setBudgetId(budgetLineItem.getBudgetId());
        budgetCalculatedAmount.setBudgetPeriod(budgetLineItem.getBudgetPeriod());
        budgetCalculatedAmount.setBudgetPeriodId(budgetLineItem.getBudgetPeriodId());
        budgetCalculatedAmount.setLineItemNumber(budgetLineItem.getLineItemNumber());
        budgetCalculatedAmount.setRateClassType(rateClassType);
        budgetCalculatedAmount.setRateClassCode(rateClassCode);
        budgetCalculatedAmount.setRateTypeCode(rateTypeCode);
        budgetCalculatedAmount.setApplyRateFlag(true);
        budgetCalculatedAmount.setRateTypeDescription(rateTypeDescription);
        return budgetCalculatedAmount;
    }

    protected String getAwardRateTypeDescription(String rateTypeCode) {
        return getLegacyDataAdapter().findBySinglePrimaryKey(FandaRateType.class, rateTypeCode).getDescription();
        
    }
    protected Equals equalsEmployeeBenefitsRateClassType() {
        return new Equals(RATE_CLASS_TYPE, RateClassType.EMPLOYEE_BENEFITS.getRateClassType());
    }

    protected Equals equalsVacationRateClassType() {
        return new Equals(RATE_CLASS_TYPE, RateClassType.VACATION.getRateClassType());
    }

    protected Equals equalsLabAllocationSalariesRateClassType() {
        return new Equals(RATE_CLASS_TYPE, RateClassType.LA_SALARIES.getRateClassType());
    }

    protected void setLabAllocationSalariesCalculatedAmounts(BudgetLineItemBase lineItem) {
        QueryEngine queryEngine = new QueryEngine();
        queryEngine.addDataCollection(ValidCalcType.class, getValidCalcTypes());

        QueryList<ValidCeRateType> qValidCeRateTypes = createQueryList(budgetLineItem.getCostElementBO().getValidCeRateTypes());
        QueryList<ValidCeRateType> qLabAllocSalRates = qValidCeRateTypes.filter(equalsLabAllocationSalariesRateClassType());

        if (CollectionUtils.isNotEmpty(qLabAllocSalRates)) {
            List<ValidCalcType> validCalCTypes = queryEngine.executeQuery(ValidCalcType.class, equalsEmployeeBenefitsRateClassType());
            if (CollectionUtils.isNotEmpty(validCalCTypes)) {
                ValidCalcType validCalcType = validCalCTypes.get(0);
                if (validCalcType.getDependentRateClassType().equals(RateClassType.LA_SALARIES.getRateClassType())) {
                    addBudgetLineItemCalculatedAmount(validCalcType.getRateClassCode(), validCalcType
                            .getRateType(), validCalcType.getRateClassType());
                }
            }
            validCalCTypes = queryEngine.executeQuery(ValidCalcType.class, equalsVacationRateClassType());
            if (!validCalCTypes.isEmpty()) {
                ValidCalcType validCalcType = validCalCTypes.get(0);
                if (validCalcType.getDependentRateClassType().equals(RateClassType.LA_SALARIES.getRateClassType())) {
                    addBudgetLineItemCalculatedAmount(validCalcType.getRateClassCode(), validCalcType
                            .getRateType(), validCalcType.getRateClassType());
                }
            }
        }
    }
    
    public final void setCalculatedAmounts(BudgetLineItemBase budgetLineItem) {
    	
    	budgetLineItem.getBudgetCalculatedAmounts().clear();
    	
        if (budgetLineItem.getCostElementBO() == null) {
        	budgetLineItem.refreshReferenceObject("costElementBO");
        }

       if (budgetLineItem.getCostElementBO().getValidCeRateTypes().isEmpty()) {
        	budgetLineItem.getCostElementBO().refreshReferenceObject(VALID_CE_RATE_TYPES);
       }

        setInflationRateOnLineItem(budgetLineItem);

        setValidCeRateTypeCalculatedAmounts(budgetLineItem);

        setLabAllocationSalariesCalculatedAmounts(budgetLineItem);
    }

    protected void setInfltionValidCalcCeRates(QueryList<ValidCeRateType> infltionValidCalcCeRates) {
        this.infltionValidCalcCeRates = infltionValidCalcCeRates;
    }
    
    protected void addBudgetLineItemCalculatedAmount(String rateClassCode, RateType rateType, String rateClassType){
        
        QueryList<BudgetRate> budgetRates = new QueryList<>(budget.getBudgetRates());
        QueryList<BudgetLaRate> budgetLaRates = new QueryList<>(budget.getBudgetLaRates());
        Equals eqValidRateClassCode = new Equals(RATE_CLASS_CODE,rateClassCode);
        Equals eqValidRateTypeCode = new Equals(RATE_TYPE_CODE,rateType.getRateTypeCode());
        And eqRateClassCodeAndRateTypeCode = new And(eqValidRateClassCode,eqValidRateTypeCode);
        List<BudgetRate> filteredBudgetRates = budgetRates.filter(eqRateClassCodeAndRateTypeCode);
        List<BudgetLaRate> filteredBudgetLaRates = budgetLaRates.filter(eqRateClassCodeAndRateTypeCode);
        
        if(filteredBudgetRates.isEmpty() && filteredBudgetLaRates.isEmpty()) return;

        AbstractBudgetCalculatedAmount budgetCalculatedAmount = getNewBudgetCalculatedAmount(rateClassType, rateClassCode, rateType.getRateTypeCode(),
                rateType.getDescription());
        budgetCalculatedAmount.refreshReferenceObject(RATE_CLASS);
        addCalculatedAmount(budgetCalculatedAmount);
    }

    protected abstract AbstractBudgetCalculatedAmount getNewCalculatedAmountInstance();
    
    protected abstract void addCalculatedAmount(AbstractBudgetCalculatedAmount budgetCalculatedAmount);

    public LegacyDataAdapter getLegacyDataAdapter() {
        return legacyDataAdapter;
    }

    public void setLegacyDataAdapter(LegacyDataAdapter legacyDataAdapter) {
        this.legacyDataAdapter = legacyDataAdapter;
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

    public QueryList<BudgetRate> getUnderrecoveryRates() {
        return underrecoveryRates;
    }

    public void setUnderrecoveryRates(QueryList<BudgetRate> underrecoveryRates) {
        this.underrecoveryRates = underrecoveryRates;
    }

    public QueryList<BudgetRate> getInflationRates() {
        return inflationRates;
    }

    public void setInflationRates(QueryList<BudgetRate> inflationRates) {
        this.inflationRates = inflationRates;
    }

    public QueryList<BudgetLaRate> getQlLineItemPropLaRates() {
        return lineItemPropLaRates;
    }

    public void setQlLineItemPropLaRates(QueryList<BudgetLaRate> qlLineItemPropLaRates) {
        this.lineItemPropLaRates = qlLineItemPropLaRates;
    }

    public QueryList<BudgetRate> getQlLineItemPropRates() {
        return lineItemPropRates;
    }

    public void setQlLineItemPropRates(QueryList<BudgetRate> qlLineItemPropRates) {
        this.lineItemPropRates = qlLineItemPropRates;
    }
    
    protected BudgetRatesService getBudgetRateService() {
        return KcServiceLocator.getService(BudgetRatesService.class);
    }


    public ParameterService getParameterService() {
        if (parameterService == null) {
            parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return parameterService;
    }
}
