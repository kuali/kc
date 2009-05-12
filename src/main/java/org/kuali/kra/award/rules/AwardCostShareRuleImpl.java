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

import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.award.bo.AwardCostShare;
import org.kuali.kra.award.rule.AwardCostShareRule;
import org.kuali.kra.award.rule.event.AwardCostShareRuleEvent;
import org.kuali.kra.bo.CostShareType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * This class...
 */
public class AwardCostShareRuleImpl extends ResearchDocumentRuleBase implements AwardCostShareRule {

    
    private static final String NEW_AWARD_COST_SHARE = "newAwardCostShare";
    AwardCostShare awardCostShare;
    
    /**
     * @see org.kuali.kra.award.rule.AwardCostShareRule#processCostShareBusinessRules
     * (org.kuali.kra.award.rule.event.AwardCostShareRuleEvent)
     */
    public boolean processCostShareBusinessRules(AwardCostShareRuleEvent awardCostShareRuleEvent) {
        this.awardCostShare = awardCostShareRuleEvent.getCostShareForValidation();
        return processCommonValidations(awardCostShare);
    }
    
    /**
     * This method checks the Cost Share fields for validity.
     * @param awardCostShareRuleEvent
     * @return true if valid, false otherwise
     */
    public boolean processAddCostShareBusinessRules(AwardCostShareRuleEvent awardCostShareRuleEvent) {
        this.awardCostShare = awardCostShareRuleEvent.getCostShareForValidation();
        
        boolean isValid = processCommonValidations(awardCostShare);
        
        // test if percentage is entered and valid
        isValid &= validatePercentage(awardCostShare.getCostSharePercentage());
        
        // test if type is selected and valid
        isValid &= validateCostShareType(awardCostShare.getCostShareTypeCode());
        
        // test if commitment amount + cost share met are entered and valid
        isValid &= validateCommitmentAmount(awardCostShare.getCommitmentAmount());
        isValid &= validateCostShareMet(awardCostShare.getCostShareMet());
        
        return isValid;
    }
    
    /**
     * This method processes common validations for business rules
     * @param event
     * @return
     */
    public boolean processCommonValidations(AwardCostShare awardCostShare) {
        boolean validSourceAndDestination = validateCostShareSourceAndDestinationForEquality(awardCostShare);
        boolean validFiscalYearRange = validateCostShareFiscalYearRange(awardCostShare);
        
        return validSourceAndDestination && validFiscalYearRange;
    }
    
    /**
    *
    * Test source and destination for equality in AwardCostShare.
    * @param AwardCostShare, ErrorMap
    * @return Boolean
    */
    public boolean validateCostShareSourceAndDestinationForEquality(AwardCostShare awardCostShare){
        boolean valid = true;
        if(awardCostShare.getSource() != null && awardCostShare.getDestination() != null){
            if(awardCostShare.getSource().equals(awardCostShare.getDestination())) {
                valid = false;
                reportError(NEW_AWARD_COST_SHARE+".source", 
                        KeyConstants.ERROR_SOURCE_DESTINATION);
            }
        }
        return valid;
    }
    
   /**
    *
    * Test fiscal year for valid range.
    * @param AwardCostShare, ErrorMap
    * @return Boolean
    */
    public boolean validateCostShareFiscalYearRange(AwardCostShare awardCostShare){
        boolean valid = true;
        if (awardCostShare.getFiscalYear() != null) {
            try {
                int fiscalYear = Integer.parseInt(awardCostShare.getFiscalYear());
                if(fiscalYear < Constants.MIN_FISCAL_YEAR || fiscalYear > Constants.MAX_FISCAL_YEAR) {
                    valid = false;
                    reportError(NEW_AWARD_COST_SHARE+".fiscalYear", 
                            KeyConstants.ERROR_FISCAL_YEAR_RANGE);
                }
            } catch (NumberFormatException e) {
                valid = false;
                reportError(NEW_AWARD_COST_SHARE+".fiscalYear", 
                        KeyConstants.ERROR_FISCAL_YEAR_INCORRECT_FORMAT);
            }
        }
        else {
            valid = false;
            reportError(NEW_AWARD_COST_SHARE+".fiscalYear", 
                    KeyConstants.ERROR_FISCAL_YEAR_REQUIRED);
        }
        return valid;
    }

    private boolean validatePercentage(KualiDecimal percentage) {
        boolean isValid = true;
        if (percentage == null) {
            isValid = false;
            this.reportError(Constants.COST_SHARE_ADD_ACTION_PROPERTY_KEY + ".costSharePercentage", KeyConstants.ERROR_COST_SHARE_PERCENTAGE_REQUIRED);
        }
        else if (percentage.isLessThan(new KualiDecimal(0))) {
            isValid = false;
            this.reportError(Constants.COST_SHARE_ADD_ACTION_PROPERTY_KEY + ".costSharePercentage", KeyConstants.ERROR_COST_SHARE_PERCENTAGE_RANGE);
        }
        return isValid;
    }
    
    private boolean validateCostShareType(Integer costShareTypeCode) {
        boolean isValid = true;
        if (costShareTypeCode == null) {
            isValid = false;
            this.reportError(Constants.COST_SHARE_ADD_ACTION_PROPERTY_KEY + ".costShareTypeCode", KeyConstants.ERROR_COST_SHARE_TYPE_REQUIRED);
        }
        else {
            BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
            Map<String,Integer> fieldValues = new HashMap<String,Integer>();
            fieldValues.put("costShareTypeCode", costShareTypeCode);
            if (businessObjectService.countMatching(CostShareType.class, fieldValues) != 1) {
                isValid = false;
                this.reportError(Constants.COST_SHARE_ADD_ACTION_PROPERTY_KEY + ".costShareTypeCode", KeyConstants.ERROR_COST_SHARE_TYPE_INVALID, new String[] { costShareTypeCode.toString() });
            }
        }
        return isValid;
    }

    private boolean validateCommitmentAmount(KualiDecimal commitmentAmount) {
        boolean isValid = true;
        if (commitmentAmount == null) {
            isValid = false;
            this.reportError(Constants.COST_SHARE_ADD_ACTION_PROPERTY_KEY + ".commitmentAmount", KeyConstants.ERROR_COST_SHARE_COMMITMENT_AMOUNT_REQUIRED);
        }
        else if (commitmentAmount.isLessThan(new KualiDecimal(0))) {
            isValid = false;
            this.reportError(Constants.COST_SHARE_ADD_ACTION_PROPERTY_KEY + ".commitmentAmount", KeyConstants.ERROR_COST_SHARE_COMMITMENT_AMOUNT_INVALID, new String[] { commitmentAmount.toString() });
        }
        return isValid;
    }

    private boolean validateCostShareMet(KualiDecimal costShareMet) {
        boolean isValid = true;
        if (costShareMet == null) {
            isValid = false;
            this.reportError(Constants.COST_SHARE_ADD_ACTION_PROPERTY_KEY + ".costShareMet", KeyConstants.ERROR_COST_SHARE_MET_REQUIRED);
        }
        else if (costShareMet.isLessThan(new KualiDecimal(0))) {
            isValid = false;
            this.reportError(Constants.COST_SHARE_ADD_ACTION_PROPERTY_KEY + ".costShareMet", KeyConstants.ERROR_COST_SHARE_MET_INVALID, new String[] { costShareMet.toString() });
        }
        return isValid;
    }
}
