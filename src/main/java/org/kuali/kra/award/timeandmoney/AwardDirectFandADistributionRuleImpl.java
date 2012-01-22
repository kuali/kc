/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.award.timeandmoney;

import java.sql.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.kuali.kra.award.AwardAmountInfoService;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.kns.util.KNSGlobalVariables;

/**
 * This class contains all rule methods for actions on Award Direct F and A Distribution tab.
 */
public class AwardDirectFandADistributionRuleImpl extends ResearchDocumentRuleBase implements AwardDirectFandADistributionRule{

    private static final String NEW_AWARD_DIRECT_FNA_DISTRIBUTION = "newAwardDirectFandADistribution";
    private static final String START_DATE_REQUIRED = ".startDateRequired";
    private static final String END_DATE_REQUIRED = ".endDateRequired";
    private static final String DIRECT_COST_REQUIRED = ".directCostRequired";
    private static final String DIRECT_COST_POSITIVE = ".directCostPositive";
    private static final String INDIRECT_COST_REQUIRED = ".indirectCostRequired";
    private static final String INDIRECT_COST_POSITIVE = ".indirectCostPositive";
    private static final String INVALID_DATES = ".invalidDates";
    private static final String OVERLAPPING_DATE_RANGES = ".overlappingDateRanges";
    private static final String INVALID_TARGET_START_DATE = ".invalidStartDate";
    private static final String INVALID_TARGET_END_DATE = ".invalidEndDate";
    private static final String WARNING_AWARD_DIRECT_FNA_DISTRIBUTION_ANTICIPATED_MISMATCH = ".mismatchAnticipated";
    private static final String WARNING_BREAK_DATE_RANGE = ".breakDateRanges";
    private static final String WARNING_MESSAGE="There are warnings in Direct/F&A Funds Distribution section";
    AwardDirectFandADistribution awardDirectFandADistribution;
    List<AwardDirectFandADistribution> awardDirectFandADistributions;
    transient AwardAmountInfoService awardAmountInfoService;
    /**
     *  @see org.kuali.kra.award.timeandmoney.AwardDirectFandADistributionRule#processAwardDirectFandADistributionRuleBusinessRules
     * (org.kuali.kra.award.timeandmoney.AwardDirectFandADistributionRuleEvent)
     */
    public boolean processAwardDirectFandADistributionBusinessRules(AwardDirectFandADistributionRuleEvent awardDirectFandADistributionRuleEvent) {
        this.awardDirectFandADistributions = awardDirectFandADistributionRuleEvent.getAwardDirectFandADistributionsForValidation();
        boolean validExistingDateRanges = existingDirectFandADistributionsDatesDontOverlap(awardDirectFandADistributions);
        boolean validStartDate = true;
        boolean validEndDate = true;
        boolean validAmounts = existingAmountsAreValid(awardDirectFandADistributions);
        boolean validTotalAnticipated =  doTotalAnticipatedAmountValidOnExistingDistribution(awardDirectFandADistributions);
        boolean validConsecutiveDateRange = existingDirectFandADistributionsDatesNoBreak(awardDirectFandADistributions);
        if(awardDirectFandADistributions.size() > 0) {
            this.awardDirectFandADistribution = awardDirectFandADistributions.get(0);
            validStartDate = isTargetStartAfterProjectStartDate(awardDirectFandADistributionRuleEvent);
            this.awardDirectFandADistribution = awardDirectFandADistributions.get(awardDirectFandADistributions.size() - 1);
            validEndDate = isTargetEndDatePriorToProjectEndDate(awardDirectFandADistributionRuleEvent);
        }
        return validExistingDateRanges && validStartDate && validEndDate;
    }
    
    
    /**
     * @see org.kuali.kra.award.timeandmoney.AwardDirectFandADistributionRule#processAddAwardDirectFandADistributionRuleBusinessRules
     * (org.kuali.kra.award.timeandmoney.AwardDirectFandADistributionRuleEvent)
     */
    public boolean processAddAwardDirectFandADistributionBusinessRules(AwardDirectFandADistributionRuleEvent awardDirectFandADistributionRuleEvent) {
        this.awardDirectFandADistribution = awardDirectFandADistributionRuleEvent.getAwardDirectFandADistributionForValidation();
        List<AwardDirectFandADistribution> thisAwardDirectFandADistributions = 
                                                awardDirectFandADistributionRuleEvent.getTimeAndMoneyDocument().getAward().getAwardDirectFandADistributions();
        
        boolean isValid = true;
        
        if (!isStartDateEntered())
            isValid = false;
        if (!isEndDateEntered())
            isValid = false;

        // if start or end date is null, skip the remaining date checks
        if (isValid && !isStartDatePriorToEndDate())
            isValid = false;
            
        if (isValid) {
            boolean validDatePeriod = doTargetDatesFallWithinOpenPeriod(thisAwardDirectFandADistributions)
                && isTargetStartAfterProjectStartDate(awardDirectFandADistributionRuleEvent) 
                && isTargetEndDatePriorToProjectEndDate(awardDirectFandADistributionRuleEvent);
            if (!validDatePeriod) {
                isValid = false;
            }
        }

        // check cost fields
        if (!isDirectCostValid())
            isValid = false;
        if (!isIndirectCostValid())
            isValid = false;
            
        return isValid;
    }
    
    /**
     * This method tests for overlapping dates in existing AwardDirectFandADistribution list on the Award.
     * @param awardDirectFandADistributions
     * @return
     */
    boolean existingDirectFandADistributionsDatesDontOverlap(List<AwardDirectFandADistribution> thisAwardDirectFandADistributions) {
        boolean valid = true;
        int currentIndex = 0;
        for(AwardDirectFandADistribution thisAwardDirectFandADistribution : thisAwardDirectFandADistributions) {
            if(thisAwardDirectFandADistribution.getStartDate().after(thisAwardDirectFandADistribution.getEndDate())) {
                valid = false;
                reportError(NEW_AWARD_DIRECT_FNA_DISTRIBUTION+INVALID_DATES, 
                        KeyConstants.ERROR_AWARD_FANDA_DISTRIB_START_BEFORE_END_DATE);
                break;
            }else if(targetOverlapsWithExistingPeriods(thisAwardDirectFandADistribution, thisAwardDirectFandADistributions, currentIndex)) {
                valid = false;
                reportError(NEW_AWARD_DIRECT_FNA_DISTRIBUTION+INVALID_DATES, 
                        KeyConstants.ERROR_OVERLAPPING_EXISTING_DATES);
                break;
            }
            currentIndex++;
        }
        return valid;
    }
    
    /**
     * This method tests for break in date ranges of existing AwardDirectFandADistribution list on the Award.
     * @param awardDirectFandADistributions
     * @return
     */
    boolean existingDirectFandADistributionsDatesNoBreak(List<AwardDirectFandADistribution> thisAwardDirectFandADistributions) {
        boolean valid = true;
        int currentIndex = 0;
        for(AwardDirectFandADistribution thisAwardDirectFandADistribution : thisAwardDirectFandADistributions) {
            if(currentIndex < thisAwardDirectFandADistributions.size()-1 ){
                AwardDirectFandADistribution nextAwardDirectFandADistribution = thisAwardDirectFandADistributions.get(++currentIndex);
                if(DateUtils.addDays(thisAwardDirectFandADistribution.getEndDate(), 1).before(nextAwardDirectFandADistribution.getStartDate())) {
                    reportWarning(WARNING_BREAK_DATE_RANGE, KeyConstants.WARNING_AWARD_FANDA_DISTRIB_BREAKS);
                    KNSGlobalVariables.getMessageList().add(KeyConstants.WARNING_AWARD_FANDA_DISTRIB_EXISTS, "");
                    valid = false;
                }
            }
        }
        return valid;
    }
    
    /**
     * This is a helper method for doExistingFandADistributionDatesOverlap.
     * @param awardDirectFandADistribution
     * @param awardDirectFandADistributions
     * @param currentIndex
     * @return
     */
    boolean targetOverlapsWithExistingPeriods(AwardDirectFandADistribution thisAwardDirectFandADistribution,
                                                        List<AwardDirectFandADistribution> thisAwardDirectFandADistributions, int currentIndex) {
        boolean invalid = false;
        Date testStartDate;
        Date testEndDate;
        Date startDate = thisAwardDirectFandADistribution.getStartDate();
        Date endDate = thisAwardDirectFandADistribution.getEndDate();
        int newCurrentIndex = 0;
        for(AwardDirectFandADistribution testAwardDirectFandADistribution : thisAwardDirectFandADistributions) {
            testStartDate = testAwardDirectFandADistribution.getStartDate();
            testEndDate = testAwardDirectFandADistribution.getEndDate();
            if(newCurrentIndex != currentIndex){
                if (startDate.before(testEndDate) && startDate.after(testStartDate) || 
                        endDate.after(testStartDate) && endDate.before(testEndDate) ||
                            startDate.equals(testEndDate) ||
                                endDate.equals(testStartDate)) {
                    invalid = true;
                    break;
                }
            }
            newCurrentIndex++;
        }
        return invalid;
    }
        
    /**
     * This method checks whether the user provided a start date
     * @return
     */
    private boolean isStartDateEntered() {
        boolean valid = true;
        if (awardDirectFandADistribution.getStartDate() == null){
            valid = false;
            reportError(NEW_AWARD_DIRECT_FNA_DISTRIBUTION+START_DATE_REQUIRED, 
                    KeyConstants.ERROR_AWARD_FANDA_DISTRIB_START_DATE_REQUIRED);
        }
        return valid;
    }
    
    /**
     * This method checks whether the user provided a end date
     * @return
     */
    private boolean isEndDateEntered() {
        boolean valid = true;
        if (awardDirectFandADistribution.getEndDate() == null){
            valid = false;
            reportError(NEW_AWARD_DIRECT_FNA_DISTRIBUTION+END_DATE_REQUIRED, 
                    KeyConstants.ERROR_AWARD_FANDA_DISTRIB_END_DATE_REQUIRED);
        }
        return valid;
    }
    
    private boolean existingAmountsAreValid(List<AwardDirectFandADistribution> thisAwardDirectFandADistributions) {
        boolean valid = true;
        for (AwardDirectFandADistribution awardDirectFandADistribution : thisAwardDirectFandADistributions) {
            if(!isDirectCostValidOnExistingDistribution(awardDirectFandADistribution)) {
                valid = false;
            }
            if(!isIndirectCostValidOnExistingDistribution(awardDirectFandADistribution)) {
                valid = false;
            }
        }
        return valid;
    }
    
    /**
     * This method checks whether the user provided a valid Direct Cost amount
     * @return
     */
    private boolean isDirectCostValidOnExistingDistribution(AwardDirectFandADistribution thisAwardDirectFandADistribution) {
        boolean valid = true;
        KualiDecimal directCost = thisAwardDirectFandADistribution.getDirectCost();
        if (directCost == null){
            valid = false;
            reportError(NEW_AWARD_DIRECT_FNA_DISTRIBUTION+DIRECT_COST_REQUIRED, 
                    KeyConstants.ERROR_AWARD_FANDA_DISTRIB_DIRECT_COST_REQUIRED);
        }
        else if (directCost.isNegative()){
            valid = false;
            reportError(NEW_AWARD_DIRECT_FNA_DISTRIBUTION+DIRECT_COST_POSITIVE, 
                    KeyConstants.ERROR_AWARD_FANDA_DISTRIB_DIRECT_COST_NEGATIVE);
        }
        return valid;
    }
    
    private boolean doTotalAnticipatedAmountValidOnExistingDistribution(List<AwardDirectFandADistribution> thisAwardDirectFandADistributions){            
        boolean valid = false;
        KualiDecimal awardAnticipatedTotal = KualiDecimal.ZERO;
        KualiDecimal calculatedFNAAmount = KualiDecimal.ZERO;
        KualiDecimal calculatedDirAmount = KualiDecimal.ZERO;
        if(awardDirectFandADistributions.size() > 0){
            awardAnticipatedTotal = awardDirectFandADistributions.get(0).getAward().getAnticipatedTotal();
            for (AwardDirectFandADistribution awardDirectFandADistribution : thisAwardDirectFandADistributions) {
                calculatedFNAAmount = calculatedFNAAmount.add(awardDirectFandADistribution.getDirectCost());
                calculatedDirAmount = calculatedDirAmount.add(awardDirectFandADistribution.getIndirectCost());  
            }
            if(awardAnticipatedTotal.equals(calculatedFNAAmount.add(calculatedDirAmount)))
                valid = true;
            else{
            reportWarning(WARNING_AWARD_DIRECT_FNA_DISTRIBUTION_ANTICIPATED_MISMATCH, 
                    KeyConstants.WARNING_AWARD_FANDA_DISTRIB_LIMITNOTEQUAL_ANTICIPATED, 
                    new String[]{awardDirectFandADistributions.get(0).getAward().getAwardNumber()});
            KNSGlobalVariables.getMessageList().add(KeyConstants.WARNING_AWARD_FANDA_DISTRIB_EXISTS, "");
            }
        }
        return valid;
    }
    /**
     * This method checks whether the user provided a valid Indirect Cost amount
     * @return
     */
    private boolean isIndirectCostValidOnExistingDistribution(AwardDirectFandADistribution thisAwardDirectFandADistribution) {
        boolean valid = true;
        KualiDecimal indirectCost = thisAwardDirectFandADistribution.getIndirectCost();
        if (indirectCost == null){
            valid = false;
            reportError(NEW_AWARD_DIRECT_FNA_DISTRIBUTION+INDIRECT_COST_REQUIRED, 
                    KeyConstants.ERROR_AWARD_FANDA_DISTRIB_INDIRECT_COST_REQUIRED);
        }
        else if (indirectCost.isNegative()){
            valid = false;
            reportError(NEW_AWARD_DIRECT_FNA_DISTRIBUTION+INDIRECT_COST_POSITIVE, 
                    KeyConstants.ERROR_AWARD_FANDA_DISTRIB_INDIRECT_COST_POSITIVE);
        }
        return valid;
    }
    
    /**
     * This method checks whether the user provided a valid Direct Cost amount
     * @return
     */
    private boolean isDirectCostValid() {
        boolean valid = true;
        KualiDecimal directCost = awardDirectFandADistribution.getDirectCost();
        if (directCost == null){
            valid = false;
            reportError(NEW_AWARD_DIRECT_FNA_DISTRIBUTION+DIRECT_COST_REQUIRED, 
                    KeyConstants.ERROR_AWARD_FANDA_DISTRIB_DIRECT_COST_REQUIRED);
        }
        else if (!directCost.isPositive()){
            valid = false;
            reportError(NEW_AWARD_DIRECT_FNA_DISTRIBUTION+DIRECT_COST_POSITIVE, 
                    KeyConstants.ERROR_AWARD_FANDA_DISTRIB_DIRECT_COST_POSITIVE);
        }
        return valid;
    }
    
    /**
     * This method checks whether the user provided a valid Indirect Cost amount
     * @return
     */
    private boolean isIndirectCostValid() {
        boolean valid = true;
        KualiDecimal indirectCost = awardDirectFandADistribution.getIndirectCost();
        if (indirectCost == null){
            valid = false;
            reportError(NEW_AWARD_DIRECT_FNA_DISTRIBUTION+INDIRECT_COST_REQUIRED, 
                    KeyConstants.ERROR_AWARD_FANDA_DISTRIB_INDIRECT_COST_REQUIRED);
        }
        else if (indirectCost.isNegative()){
            valid = false;
            reportError(NEW_AWARD_DIRECT_FNA_DISTRIBUTION+INDIRECT_COST_POSITIVE, 
                    KeyConstants.ERROR_AWARD_FANDA_DISTRIB_INDIRECT_COST_POSITIVE);
        }
        return valid;
    }
    
    /**
     * This method tests that the period start date is prior to the period end date.
     * @return
     */
    boolean isStartDatePriorToEndDate() {
        boolean valid = true;
        if (awardDirectFandADistribution.getStartDate().compareTo(awardDirectFandADistribution.getEndDate()) != -1){
            valid = false;
            reportError(NEW_AWARD_DIRECT_FNA_DISTRIBUTION+INVALID_DATES, 
                    KeyConstants.ERROR_START_DATE_PRECEDES_END_DATE);
        }
        return valid;
    }
    
    /**
     * This method tests that the target date range falls into an open date range.
     * @param awardDirectFandADistributions
     * @return
     */
   boolean doTargetDatesFallWithinOpenPeriod(List<AwardDirectFandADistribution> thisAwardDirectFandADistributions) {
        boolean valid = true;
        for(AwardDirectFandADistribution testAwardDirectFandADistribution : thisAwardDirectFandADistributions) {
            if(doDateRangesOverlap(testAwardDirectFandADistribution, awardDirectFandADistribution)){
                valid = false;
                reportError(NEW_AWARD_DIRECT_FNA_DISTRIBUTION+OVERLAPPING_DATE_RANGES, 
                        KeyConstants.ERROR_OVERLAPPING_DATE_RANGES);
            }
        }
        return valid;
    }
    
    /**
     * This is a helper method for doTargetDatesFallWithinOpenPeriod.  
     * @param testAwardDirectFandADistribution
     * @param awardDirectFandADistribution
     * @return
     */
    boolean doDateRangesOverlap(AwardDirectFandADistribution testAwardDirectFandADistribution, 
                                                AwardDirectFandADistribution thisAwardDirectFandADistribution) {
        Date testStartDate = testAwardDirectFandADistribution.getStartDate();
        Date testEndDate = testAwardDirectFandADistribution.getEndDate();
        Date startDate = thisAwardDirectFandADistribution.getStartDate();
        Date endDate = thisAwardDirectFandADistribution.getEndDate();
        return startDate.before(testEndDate) && startDate.after(testStartDate) || endDate.after(testStartDate) && endDate.before(testEndDate);
    }
    
    /**
     * This method tests if target start date falls after project start date.
     * @param awardDirectFandADistributions
     * @return
     */
    boolean isTargetStartAfterProjectStartDate(AwardDirectFandADistributionRuleEvent awardDirectFandADistributionRuleEvent) {
        Date targetStartDate = awardDirectFandADistribution.getStartDate();
        Date projectStartDate = awardDirectFandADistributionRuleEvent.getTimeAndMoneyDocument().getAward().getAwardEffectiveDate();
        boolean valid = true;
        if (!(projectStartDate == null)) {
            if (projectStartDate.after(targetStartDate)) {
                valid = false;
                reportError(NEW_AWARD_DIRECT_FNA_DISTRIBUTION+INVALID_TARGET_START_DATE, 
                        KeyConstants.ERROR_TARGET_START_DATE);
            }
        }
        return valid;
    }
    
    /**
     * This method This method tests if target start date falls after project start date.
     * @param awardDirectFandADistributions
     * @return
     */
    boolean isTargetEndDatePriorToProjectEndDate(AwardDirectFandADistributionRuleEvent awardDirectFandADistributionRuleEvent) {
        Date targetEndDate = awardDirectFandADistribution.getEndDate();
        //Date projectEndDate = awardDirectFandADistributionRuleEvent.getTimeAndMoneyDocument().getAward().getProjectEndDate();
        Date projectEndDate = getAwardAmountInfoService().fetchAwardAmountInfoWithHighestTransactionId
                                                            (awardDirectFandADistributionRuleEvent.getTimeAndMoneyDocument().getAward().getAwardAmountInfos()).getFinalExpirationDate();
        boolean valid = true;
        if (projectEndDate.before(targetEndDate)) {
            valid = false;
            reportError(NEW_AWARD_DIRECT_FNA_DISTRIBUTION+INVALID_TARGET_END_DATE, 
                    KeyConstants.ERROR_TARGET_END_DATE);
            
        }
        return valid;
    }
    
    public AwardAmountInfoService getAwardAmountInfoService() {
        awardAmountInfoService = KraServiceLocator.getService(AwardAmountInfoService.class);
        return awardAmountInfoService;
    }


    /**
     * Gets the awardDirectFandADistribution attribute. 
     * @return Returns the awardDirectFandADistribution.
     */
    public AwardDirectFandADistribution getAwardDirectFandADistribution() {
        return awardDirectFandADistribution;
    }


    /**
     * Sets the awardDirectFandADistribution attribute value.
     * @param awardDirectFandADistribution The awardDirectFandADistribution to set.
     */
    public void setAwardDirectFandADistribution(AwardDirectFandADistribution awardDirectFandADistribution) {
        this.awardDirectFandADistribution = awardDirectFandADistribution;
    }


    /**
     * Gets the awardDirectFandADistributions attribute. 
     * @return Returns the awardDirectFandADistributions.
     */
    public List<AwardDirectFandADistribution> getAwardDirectFandADistributions() {
        return awardDirectFandADistributions;
    }


    /**
     * Sets the awardDirectFandADistributions attribute value.
     * @param awardDirectFandADistributions The awardDirectFandADistributions to set.
     */
    public void setAwardDirectFandADistributions(List<AwardDirectFandADistribution> awardDirectFandADistributions) {
        this.awardDirectFandADistributions = awardDirectFandADistributions;
    }
    
    
    

}
