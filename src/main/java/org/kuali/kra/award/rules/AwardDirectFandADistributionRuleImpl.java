/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.rules;

import java.sql.Date;
import java.util.List;

import org.kuali.kra.award.bo.AwardDirectFandADistribution;
import org.kuali.kra.award.rule.AwardDirectFandADistributionRule;
import org.kuali.kra.award.rule.event.AwardDirectFandADistributionRuleEvent;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * This class contains all rule methods for actions on Award Direct F and A Distribution tab.
 */
public class AwardDirectFandADistributionRuleImpl extends ResearchDocumentRuleBase implements AwardDirectFandADistributionRule {

    private static final String NEW_AWARD_DIRECT_FNA_DISTRIBUTION = "newAwardDirectFandADistribution";
    private static final String INVALID_DATES = ".invalidDates";
    private static final String OVERLAPPING_DATE_RANGES = ".overlappingDateRanges";
    private static final String INVALID_TARGET_START_DATE = ".invalidStartDate";
    private static final String INVALID_TARGET_END_DATE = ".invalidEndDate";
    AwardDirectFandADistribution awardDirectFandADistribution;
    List<AwardDirectFandADistribution> awardDirectFandADistributions;
    
    /**
     *  @see org.kuali.kra.award.rule.AwardDirectFandADistributionRule#processAwardDirectFandADistributionRuleBusinessRules
     * (org.kuali.kra.award.rule.event.AwardDirectFandADistributionRuleEvent)
     */
    public boolean processAwardDirectFandADistributionBusinessRules(AwardDirectFandADistributionRuleEvent awardDirectFandADistributionRuleEvent) {
        this.awardDirectFandADistributions = awardDirectFandADistributionRuleEvent.getAwardDirectFandADistributionsForValidation();
        boolean validExistingDateRanges = existingDirectFandADistributionsDatesDontOverlap(awardDirectFandADistributions);
        boolean validStartDate = true;
        boolean validEndDate = true;
        if(awardDirectFandADistributions.size() > 0) {
            this.awardDirectFandADistribution = awardDirectFandADistributions.get(0);
            validStartDate = isTargetStartAfterProjectStartDate(awardDirectFandADistributionRuleEvent);
            this.awardDirectFandADistribution = awardDirectFandADistributions.get(awardDirectFandADistributions.size() - 1);
            validEndDate = isTargetEndDatePriorToProjectEndDate(awardDirectFandADistributionRuleEvent);
        }
        return validExistingDateRanges && validStartDate && validEndDate;
    }
    
    
    /**
     * @see org.kuali.kra.award.rule.AwardDirectFandADistributionRule#processAddAwardDirectFandADistributionRuleBusinessRules
     * (org.kuali.kra.award.rule.event.AwardDirectFandADistributionRuleEvent)
     */
    public boolean processAddAwardDirectFandADistributionBusinessRules(AwardDirectFandADistributionRuleEvent awardDirectFandADistributionRuleEvent) {
        this.awardDirectFandADistribution = awardDirectFandADistributionRuleEvent.getAwardDirectFandADistributionForValidation();
        List<AwardDirectFandADistribution> awardDirectFandADistributions = 
                                                awardDirectFandADistributionRuleEvent.getAwardDocument().getAward().getAwardDirectFandADistributions();
        boolean validStartAndEndDates = isStartDatePriorToEndDate();
        boolean validDatePeriod = doTargetDatesFallWithinOpenPeriod(awardDirectFandADistributions) 
                                        && isTargetStartAfterProjectStartDate(awardDirectFandADistributionRuleEvent) 
                                            && isTargetEndDatePriorToProjectEndDate(awardDirectFandADistributionRuleEvent);
        return validStartAndEndDates && validDatePeriod;
    }
    
    /**
     * This method tests for overlapping dates in existing AwardDirectFandADistribution list on the Award.
     * @param awardDirectFandADistributions
     * @return
     */
    boolean existingDirectFandADistributionsDatesDontOverlap(List<AwardDirectFandADistribution> awardDirectFandADistributions) {
        boolean valid = true;
        int currentIndex = 0;
        for(AwardDirectFandADistribution awardDirectFandADistribution : awardDirectFandADistributions) {
            if(targetOverlapsWithExistingPeriods(awardDirectFandADistribution, awardDirectFandADistributions, currentIndex)) {
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
     * This is a helper method for doExistingFandADistributionDatesOverlap.
     * @param awardDirectFandADistribution
     * @param awardDirectFandADistributions
     * @param currentIndex
     * @return
     */
    boolean targetOverlapsWithExistingPeriods(AwardDirectFandADistribution awardDirectFandADistribution,
                                                        List<AwardDirectFandADistribution> awardDirectFandADistributions, int currentIndex) {
        boolean invalid = false;
        Date testStartDate;
        Date testEndDate;
        Date startDate = awardDirectFandADistribution.getStartDate();
        Date endDate = awardDirectFandADistribution.getEndDate();
        int newCurrentIndex = 0;
        for(AwardDirectFandADistribution testAwardDirectFandADistribution : awardDirectFandADistributions) {
            testStartDate = testAwardDirectFandADistribution.getStartDate();
            testEndDate = testAwardDirectFandADistribution.getEndDate();
            if(newCurrentIndex != currentIndex){
                if (startDate.before(testEndDate) && startDate.after(testStartDate) || endDate.after(testStartDate) && endDate.before(testEndDate)) {
                    invalid = true;
                    break;
                }
            }
            newCurrentIndex++;
        }
        return invalid;
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
   boolean doTargetDatesFallWithinOpenPeriod(List<AwardDirectFandADistribution> awardDirectFandADistributions) {
        boolean valid = true;
        for(AwardDirectFandADistribution testAwardDirectFandADistribution : awardDirectFandADistributions) {
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
                                                AwardDirectFandADistribution awardDirectFandADistribution) {
        Date testStartDate = testAwardDirectFandADistribution.getStartDate();
        Date testEndDate = testAwardDirectFandADistribution.getEndDate();
        Date startDate = awardDirectFandADistribution.getStartDate();
        Date endDate = awardDirectFandADistribution.getEndDate();
        return startDate.before(testEndDate) && startDate.after(testStartDate) || endDate.after(testStartDate) && endDate.before(testEndDate);
    }
    
    /**
     * This method tests if target start date falls after project start date.
     * @param awardDirectFandADistributions
     * @return
     */
    boolean isTargetStartAfterProjectStartDate(AwardDirectFandADistributionRuleEvent awardDirectFandADistributionRuleEvent) {
        Date targetStartDate = awardDirectFandADistribution.getStartDate();
        Date projectStartDate = awardDirectFandADistributionRuleEvent.getAwardDocument().getAward().getBeginDate();
        boolean valid = true;
        if (projectStartDate.after(targetStartDate)) {
            valid = false;
            reportError(NEW_AWARD_DIRECT_FNA_DISTRIBUTION+INVALID_TARGET_START_DATE, 
                    KeyConstants.ERROR_TARGET_START_DATE);
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
        Date projectEndDate = awardDirectFandADistributionRuleEvent.getAwardDocument().getAward().getProjectEndDate();
        boolean valid = true;
        if (projectEndDate.before(targetEndDate)) {
            valid = false;
            reportError(NEW_AWARD_DIRECT_FNA_DISTRIBUTION+INVALID_TARGET_END_DATE, 
                    KeyConstants.ERROR_TARGET_END_DATE);
            
        }
        return valid;
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
