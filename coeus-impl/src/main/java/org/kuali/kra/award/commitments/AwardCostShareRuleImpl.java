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
package org.kuali.kra.award.commitments;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.CostShareType;
import org.kuali.coeus.common.framework.costshare.CostShareRuleResearchDocumentBase;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.Map;


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
        String label = KcServiceLocator.getService(CostShareService.class).getCostShareLabel();
        return label;
    }*/

    private boolean validatePercentage(ScaleTwoDecimal percentage) {
        boolean isValid = true;
        if (percentage != null && percentage.isLessThan(new ScaleTwoDecimal(0))) {
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
            BusinessObjectService businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
            Map<String,Integer> fieldValues = new HashMap<String,Integer>();
            fieldValues.put("costShareTypeCode", costShareTypeCode);
            if (businessObjectService.countMatching(CostShareType.class, fieldValues) != 1) {
                isValid = false;
                this.reportError(costShareTypeCodeField, KeyConstants.ERROR_COST_SHARE_TYPE_INVALID, new String[] { costShareTypeCode.toString() });
            }
        }
        return isValid;
    }

    private boolean validateCommitmentAmount(ScaleTwoDecimal commitmentAmount) {
        boolean isValid = true;
        String commitmentAmountField = fieldStarter + ".commitmentAmount";
        if (commitmentAmount == null) {
            isValid = false;
            this.reportError(commitmentAmountField, KeyConstants.ERROR_COST_SHARE_COMMITMENT_AMOUNT_REQUIRED);
        } else if (commitmentAmount.isLessThan(new ScaleTwoDecimal(0))) {
            isValid = false;
            this.reportError(commitmentAmountField, KeyConstants.ERROR_COST_SHARE_COMMITMENT_AMOUNT_INVALID, new String[] { commitmentAmount.toString() });
        }
        return isValid;
    }

    private boolean validateCostShareMet(ScaleTwoDecimal costShareMet) {
        boolean isValid = true;
        if (costShareMet != null && costShareMet.isLessThan(new ScaleTwoDecimal(0))) {
            isValid = false;
            this.reportError(fieldStarter + ".costShareMet", KeyConstants.ERROR_COST_SHARE_MET_INVALID, new String[] { costShareMet.toString() });
        }
        return isValid;
    }
}
