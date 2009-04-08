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
    
    /**
     * @see org.kuali.kra.award.rule.AwardCostShareRule#processCostShareBusinessRules(org.kuali.kra.award.rule.event.AwardCostShareRuleEvent)
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
     * This method tests that the period start date is prior to the period end date.
     * @return
     */
    private boolean isStartDatePriorToEndDate() {
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
    private boolean doTargetDatesFallWithinOpenPeriod(List<AwardDirectFandADistribution> awardDirectFandADistributions) {
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
    private boolean doDateRangesOverlap(AwardDirectFandADistribution testAwardDirectFandADistribution, 
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
    private boolean isTargetStartAfterProjectStartDate(AwardDirectFandADistributionRuleEvent awardDirectFandADistributionRuleEvent) {
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
    private boolean isTargetEndDatePriorToProjectEndDate(AwardDirectFandADistributionRuleEvent awardDirectFandADistributionRuleEvent) {
        Date targetEndDate = awardDirectFandADistribution.getEndDate();
        Date projectEndDate = awardDirectFandADistributionRuleEvent.getAwardDocument().getAward().getAwardExecutionDate();
        boolean valid = true;
        if (projectEndDate.before(targetEndDate)) {
            valid = false;
            reportError(NEW_AWARD_DIRECT_FNA_DISTRIBUTION+INVALID_TARGET_END_DATE, 
                    KeyConstants.ERROR_TARGET_END_DATE);
            
        }
        return valid;
    }
    
    
    

}
