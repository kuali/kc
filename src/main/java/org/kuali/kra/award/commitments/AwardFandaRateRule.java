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
package org.kuali.kra.award.commitments;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * 
 * This class represents the AwardFandaRateRule
 */
public class AwardFandaRateRule  extends ResearchDocumentRuleBase implements AddFandaRateRule {
    private static final String NEW_AWARD_FANDA_RATE = "newAwardFandaRate";
    private static final int FISCAL_YEAR_LENGTH = 4;
    private static final String FISCAL_YEAR_STRING = ".fiscalYear";

    /**
     * 
     * @see org.kuali.kra.award.commitments.AddFandaRateRule
     * #processAddFandaRateBusinessRules(org.kuali.kra.award.commitments.AddAwardFandaRateEvent)
     */
    public boolean processAddFandaRateBusinessRules(AddAwardFandaRateEvent
            addAwardFandaRateEvent) {
        AwardFandaRate awardFandaRate = 
            addAwardFandaRateEvent.getAwardFandaRate();        
        boolean rulePassed = true;
        
        rulePassed &= evaluateRuleForApplicableFandaRate(awardFandaRate);
        rulePassed &= evaluateRuleForFandaRateTypeCode(awardFandaRate);
        rulePassed &= evaluateRuleForFiscalYear(awardFandaRate);
        rulePassed &= evaluateRuleForStartAndEndDates(awardFandaRate);

        return rulePassed;
    }
    
    /**
     * 
     * This is a convenience method for evaluating the rule for applicableFandaRate field.
     * @param awardFandaRate
     * @return
     */
    protected boolean evaluateRuleForApplicableFandaRate(AwardFandaRate awardFandaRate){        
        boolean rulePassed = awardFandaRate.getApplicableFandaRate()!=null 
                                && !StringUtils.isBlank(awardFandaRate.getApplicableFandaRate().toString());
        
        if(!rulePassed){            
            reportError(NEW_AWARD_FANDA_RATE+".applicableFandaRate"
                    , KeyConstants.ERROR_REQUIRED_APPLICABLE_INDIRECT_COST_RATE);
        }else{
            rulePassed = awardFandaRate.getApplicableFandaRate().isGreaterEqual(KualiDecimal.ZERO) ? true:false;
            if(!rulePassed){
                reportError(NEW_AWARD_FANDA_RATE+".applicableFandaRate"
                        , KeyConstants.ERROR_APPLICABLE_INDIRECT_COST_RATE_CAN_NOT_BE_NEGATIVE);
            }
        }
        
        return rulePassed;
    }
    
    /**
     * 
     * This is a convenience method for evaluating the rule for fandaRateTypeCode field.
     * @param awardFandaRate
     * @return
     */
    protected boolean evaluateRuleForFandaRateTypeCode(AwardFandaRate awardFandaRate){
        boolean rulePassed = !(awardFandaRate.getFandaRateTypeCode()==null 
                || StringUtils.isBlank(awardFandaRate.getFandaRateTypeCode().toString()));
        if(!rulePassed){            
            reportError(NEW_AWARD_FANDA_RATE+".fandaRateTypeCode"
                    , KeyConstants.ERROR_REQUIRED_INDIRECT_RATE_TYPE_CODE);
        }        
        return rulePassed;
    }
    
    /**
     * 
     * This is a convenience method for evaluating the rule for fiscalYear field.
     * @param awardFandaRate
     * @return
     */
    protected boolean evaluateRuleForFiscalYear(AwardFandaRate awardFandaRate){
        boolean rulePassed = true;        
        if(awardFandaRate.getFiscalYear()==null 
                || StringUtils.isBlank(awardFandaRate.getFiscalYear())){
            rulePassed = false;
            reportError(NEW_AWARD_FANDA_RATE+FISCAL_YEAR_STRING
                    , KeyConstants.ERROR_REQUIRED_FISCAL_YEAR);
        }else if(awardFandaRate.getFiscalYear().length()!=FISCAL_YEAR_LENGTH){
            rulePassed = false;
            reportError(NEW_AWARD_FANDA_RATE+FISCAL_YEAR_STRING
                    , KeyConstants.ERROR_FISCAL_YEAR_INCORRECT_FORMAT);
        }else{
            try{
                Integer.parseInt(awardFandaRate.getFiscalYear());
            }catch(NumberFormatException e){
                rulePassed = false;
                reportError(NEW_AWARD_FANDA_RATE+FISCAL_YEAR_STRING
                        , KeyConstants.ERROR_FISCAL_YEAR_INCORRECT_FORMAT);
            }
        }
        return rulePassed;
    }
    
    /**
     * 
     * This is a convenience method for evaluating the rule for startDate and endDate fields.
     * @param awardFandaRate
     * @return
     */
    protected boolean evaluateRuleForStartAndEndDates(AwardFandaRate awardFandaRate){
        boolean rule1Passed = !(awardFandaRate.getStartDate()==null 
                || StringUtils.isBlank(awardFandaRate.getStartDate().toString()));
        final String[] DATE_PARAMS = {"Start Date","End Date"};
        
        if(!rule1Passed){            
            reportError(NEW_AWARD_FANDA_RATE+".startDate", KeyConstants.ERROR_REQUIRED_START_DATE);
        }
        boolean rule2Passed =  !(awardFandaRate.getEndDate() !=null 
                && awardFandaRate.getStartDate() != null 
                && awardFandaRate.getEndDate().before(awardFandaRate.getStartDate()));
        if (!rule2Passed) {            
            reportError(NEW_AWARD_FANDA_RATE+".endDate"
                    , KeyConstants.ERROR_END_DATE_BEFORE_START_DATE_INDIRECT_COST_RATE,DATE_PARAMS);
        }
        return rule1Passed && rule2Passed;
    }
    
}
