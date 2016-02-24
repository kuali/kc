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
package org.kuali.kra.award.timeandmoney;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardAmountInfoService;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.timeandmoney.service.AwardFnaDistributionService;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.kns.util.KNSGlobalVariables;

import java.sql.Date;
import java.util.List;

/**
 * This class contains all rule methods for actions on Award Direct F and A Distribution tab.
 */
public class AwardDirectFandADistributionRuleImpl extends KcTransactionalDocumentRuleBase implements AwardDirectFandADistributionRule{

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
    AwardDirectFandADistribution awardDirectFandADistribution;
    List<AwardDirectFandADistribution> awardDirectFandADistributions;
    transient AwardAmountInfoService awardAmountInfoService;
    private transient AwardFnaDistributionService awardFnaDistributionService;

    public boolean processAwardDirectFandADistributionBusinessRules(AwardDirectFandADistributionRuleEvent awardDirectFandADistributionRuleEvent) {
        this.awardDirectFandADistributions = awardDirectFandADistributionRuleEvent.getAwardDirectFandADistributionsForValidation();
        boolean validExistingDateRanges = existingDirectFandADistributionsDatesDontOverlap(awardDirectFandADistributions);
        boolean validStartDate = true;
        boolean validEndDate = true;
        boolean validAmounts = existingAmountsAreValid(awardDirectFandADistributions);
        boolean hasTimeAndMoneyBeenSaved = StringUtils.isNotBlank(awardDirectFandADistributionRuleEvent.getTimeAndMoneyDocument().getObjectId());
        boolean validTotalAnticipated =  doTotalAnticipatedAmountValidOnExistingDistribution(awardDirectFandADistributions, hasTimeAndMoneyBeenSaved);
        boolean validConsecutiveDateRange = existingDirectFandADistributionsDatesNoBreak(awardDirectFandADistributions);
        if(awardDirectFandADistributions.size() > 0 && hasTimeAndMoneyBeenSaved) {
            this.awardDirectFandADistribution = awardDirectFandADistributions.get(0);
            validStartDate = isTargetStartAfterProjectStartDate(awardDirectFandADistributionRuleEvent);
            this.awardDirectFandADistribution = awardDirectFandADistributions.get(awardDirectFandADistributions.size() - 1);
            validEndDate = isTargetEndDatePriorToProjectEndDate(awardDirectFandADistributionRuleEvent);
        }
        return validExistingDateRanges && validStartDate && validEndDate;
    }

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
     */
    private boolean isDirectCostValidOnExistingDistribution(AwardDirectFandADistribution thisAwardDirectFandADistribution) {
        boolean valid = true;
        ScaleTwoDecimal directCost = thisAwardDirectFandADistribution.getDirectCost();
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
    
    private boolean doTotalAnticipatedAmountValidOnExistingDistribution(List<AwardDirectFandADistribution> thisAwardDirectFandADistributions, boolean hasTimeAndMoneyBeenSaved){
        if (getAwardFnaDistributionService().disableFAndADistributionEqualityValidation()) {
            return true;
        }
        boolean valid = false;
        ScaleTwoDecimal awardAnticipatedTotal = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal calculatedFNAAmount = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal calculatedDirAmount = ScaleTwoDecimal.ZERO;
        if (awardDirectFandADistributions.size() > 0) {
            awardAnticipatedTotal = awardDirectFandADistributions.get(0).getAward().getAnticipatedTotal();
            for (AwardDirectFandADistribution awardDirectFandADistribution : thisAwardDirectFandADistributions) {
                calculatedFNAAmount = calculatedFNAAmount.add(awardDirectFandADistribution.getDirectCost());
                calculatedDirAmount = calculatedDirAmount.add(awardDirectFandADistribution.getIndirectCost());  
            }
            if(awardAnticipatedTotal.equals(calculatedFNAAmount.add(calculatedDirAmount))) {
                valid = true;
            } else {
                if (getAwardFnaDistributionService().displayAwardFAndADistributionEqualityValidationAsError()) {
                    if (hasTimeAndMoneyBeenSaved) {
                        reportError(WARNING_AWARD_DIRECT_FNA_DISTRIBUTION_ANTICIPATED_MISMATCH, 
                                KeyConstants.WARNING_AWARD_FANDA_DISTRIB_LIMITNOTEQUAL_ANTICIPATED, 
                                new String[]{awardDirectFandADistributions.get(0).getAward().getAwardNumber()});
                        KNSGlobalVariables.getMessageList().add(KeyConstants.WARNING_AWARD_FANDA_DISTRIB_EXISTS, "");
                    }
                } else if (getAwardFnaDistributionService().displayAwardFAndADistributionEqualityValidationAsWarning()) {
                    reportWarning(WARNING_AWARD_DIRECT_FNA_DISTRIBUTION_ANTICIPATED_MISMATCH, 
                            KeyConstants.WARNING_AWARD_FANDA_DISTRIB_LIMITNOTEQUAL_ANTICIPATED, 
                            new String[]{awardDirectFandADistributions.get(0).getAward().getAwardNumber()});
                    KNSGlobalVariables.getMessageList().add(KeyConstants.WARNING_AWARD_FANDA_DISTRIB_EXISTS, "");
                } else {
                    //no validation warning
                    valid = true;
                }
            }
        }
        return valid;
    }
    
    protected AwardFnaDistributionService getAwardFnaDistributionService() {
        if (awardFnaDistributionService == null) {
            awardFnaDistributionService = KcServiceLocator.getService(AwardFnaDistributionService.class);
        }
        return awardFnaDistributionService;
    }
    /**
     * This method checks whether the user provided a valid Indirect Cost amount
     */
    private boolean isIndirectCostValidOnExistingDistribution(AwardDirectFandADistribution thisAwardDirectFandADistribution) {
        boolean valid = true;
        ScaleTwoDecimal indirectCost = thisAwardDirectFandADistribution.getIndirectCost();
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
     */
    private boolean isDirectCostValid() {
        boolean valid = true;
        ScaleTwoDecimal directCost = awardDirectFandADistribution.getDirectCost();
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
     */
    private boolean isIndirectCostValid() {
        boolean valid = true;
        ScaleTwoDecimal indirectCost = awardDirectFandADistribution.getIndirectCost();
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
     * This method tests if target end date falls before project end date.
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
        awardAmountInfoService = KcServiceLocator.getService(AwardAmountInfoService.class);
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
