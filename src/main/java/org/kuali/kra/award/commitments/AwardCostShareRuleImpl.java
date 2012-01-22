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
package org.kuali.kra.award.commitments;

import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.bo.CostShareType;
import org.kuali.kra.costshare.CostShareRuleResearchDocumentBase;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class...
 */
public class AwardCostShareRuleImpl extends CostShareRuleResearchDocumentBase implements AwardCostShareRule {

    
    //private static final String NEW_AWARD_COST_SHARE = "costShareFormHelper.newAwardCostShare";
    private AwardCostShare awardCostShare;
    private String fieldStarter = "";
    
    /**
     * @see org.kuali.kra.award.commitments.AwardCostShareRule#processCostShareBusinessRules
     * (org.kuali.kra.award.commitments.AwardCostShareRuleEvent)
     */
    public boolean processCostShareBusinessRules(AwardCostShareRuleEvent awardCostShareRuleEvent, int i) {
        this.fieldStarter = "document.awardList[0].awardCostShares[" + i + "]";
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
        this.fieldStarter = "costShareFormHelper.newAwardCostShare";
        boolean isValid = processCommonValidations(awardCostShare);
        
        // test if percentage is valid
        isValid &= validatePercentage(awardCostShare.getCostSharePercentage());
        
        // test if type is selected and valid
        isValid &= validateCostShareType(awardCostShare.getCostShareTypeCode());
        
        // test if commitment amount is entered and valid
        isValid &= validateCommitmentAmount(awardCostShare.getCommitmentAmount());
        
        // test if cost share met is valid
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
    * @param AwardCostShare, MessageMap
    * @return Boolean
    */
    public boolean validateCostShareSourceAndDestinationForEquality(AwardCostShare awardCostShare){
        boolean valid = true;
        if (awardCostShare.getSource() != null && awardCostShare.getDestination() != null) {
            if (awardCostShare.getSource().equals(awardCostShare.getDestination())) {
                valid = false;
                reportError(fieldStarter + ".source", KeyConstants.ERROR_SOURCE_DESTINATION);
            }
        }
        return valid;
    }
    
   /**
    *
    * Test fiscal year for valid range.
    * @param AwardCostShare
    * @return Boolean
    */
    public boolean validateCostShareFiscalYearRange(AwardCostShare awardCostShare) {
        String projectPeriodField = fieldStarter + ".projectPeriod";
        //int numberOfProjectPeriods = 51;
        //return this.validateProjectPeriod(awardCostShare.getProjectPeriod(), projectPeriodField, numberOfProjectPeriods);
        return this.validateProjectPeriod(awardCostShare.getProjectPeriod(), projectPeriodField);
    }
    
    /*
    private String getProjectPeriodLabel() {
        String label = KraServiceLocator.getService(CostShareService.class).getCostShareLabel();
        return label;
    }*/

    private boolean validatePercentage(KualiDecimal percentage) {
        boolean isValid = true;
        if (percentage != null && percentage.isLessThan(new KualiDecimal(0))) {
            isValid = false;
            this.reportError(fieldStarter + ".costSharePercentage", KeyConstants.ERROR_COST_SHARE_PERCENTAGE_RANGE);
        }
        return isValid;
    }
    
    private boolean validateCostShareType(Integer costShareTypeCode) {
        boolean isValid = true;
        String costShareTypeCodeField = fieldStarter + ".costShareTypeCode";
        if (costShareTypeCode == null) {
            isValid = false;
            this.reportError(costShareTypeCodeField, KeyConstants.ERROR_COST_SHARE_TYPE_REQUIRED);
        } else {
            BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
            Map<String,Integer> fieldValues = new HashMap<String,Integer>();
            fieldValues.put("costShareTypeCode", costShareTypeCode);
            if (businessObjectService.countMatching(CostShareType.class, fieldValues) != 1) {
                isValid = false;
                this.reportError(costShareTypeCodeField, KeyConstants.ERROR_COST_SHARE_TYPE_INVALID, new String[] { costShareTypeCode.toString() });
            }
        }
        return isValid;
    }

    private boolean validateCommitmentAmount(KualiDecimal commitmentAmount) {
        boolean isValid = true;
        String commitmentAmountField = fieldStarter + ".commitmentAmount";
        if (commitmentAmount == null) {
            isValid = false;
            this.reportError(commitmentAmountField, KeyConstants.ERROR_COST_SHARE_COMMITMENT_AMOUNT_REQUIRED);
        } else if (commitmentAmount.isLessThan(new KualiDecimal(0))) {
            isValid = false;
            this.reportError(commitmentAmountField, KeyConstants.ERROR_COST_SHARE_COMMITMENT_AMOUNT_INVALID, new String[] { commitmentAmount.toString() });
        }
        return isValid;
    }

    private boolean validateCostShareMet(KualiDecimal costShareMet) {
        boolean isValid = true;
        if (costShareMet != null && costShareMet.isLessThan(new KualiDecimal(0))) {
            isValid = false;
            this.reportError(fieldStarter + ".costShareMet", KeyConstants.ERROR_COST_SHARE_MET_INVALID, new String[] { costShareMet.toString() });
        }
        return isValid;
    }
}
