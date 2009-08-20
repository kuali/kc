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
package org.kuali.kra.award.commitments;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.document.AwardDocument;
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
     * @see org.kuali.kra.award.commitments.AwardCostShareRule#processCostShareBusinessRules
     * (org.kuali.kra.award.commitments.AwardCostShareRuleEvent)
     */
    public boolean processCostShareBusinessRules(AwardCostShareRuleEvent awardCostShareRuleEvent, int i) {
        this.awardCostShare = awardCostShareRuleEvent.getCostShareForValidation();
        return processCommonValidations(awardCostShare)&&
                validateCostShareDoesNotViolateUniqueConstraintOnSave(awardCostShareRuleEvent,awardCostShare, i);
    }
    
    /**
     * This method checks the Cost Share fields for validity.
     * @param awardCostShareRuleEvent
     * @return true if valid, false otherwise
     */
    public boolean processAddCostShareBusinessRules(AwardCostShareRuleEvent awardCostShareRuleEvent) {
        this.awardCostShare = awardCostShareRuleEvent.getCostShareForValidation();
        
        boolean isValid = processCommonValidations(awardCostShare);
        
        // test if percentage is valid
        isValid &= validatePercentage(awardCostShare.getCostSharePercentage());
        
        // test if type is selected and valid
        isValid &= validateCostShareType(awardCostShare.getCostShareTypeCode());
        
        // test if commitment amount is entered and valid
        isValid &= validateCommitmentAmount(awardCostShare.getCommitmentAmount());
        
        // test if cost share met is valid
        isValid &= validateCostShareMet(awardCostShare.getCostShareMet());
        
        // test if cost share is unique
        isValid &= validateCostShareDoesNotViolateUniqueConstraintOnAdd(awardCostShareRuleEvent, awardCostShare);
        
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
     * This method tests that Cost Share does not violate unique constraint on Database table.
     * @param awardCostShareRuleEvent
     * @param awardCostShare
     * @return
     */
    public boolean validateCostShareDoesNotViolateUniqueConstraintOnAdd (AwardCostShareRuleEvent awardCostShareRuleEvent,
                                                                     AwardCostShare awardCostShare) {
        AwardDocument awardDocument = (AwardDocument) awardCostShareRuleEvent.getDocument(); 
        boolean valid = true;
        for (AwardCostShare existingCostShare : awardDocument.getAward().getAwardCostShares()) {
            if(  awardCostShare.getFiscalYear().equals(existingCostShare.getFiscalYear()) &&
                    awardCostShare.getCostShareTypeCode().equals(existingCostShare.getCostShareTypeCode()) &&
                        awardCostShare.getSource().equals(existingCostShare.getSource()) &&
                            awardCostShare.getDestination().equals(existingCostShare.getDestination())) {
                valid = false;
                reportError(NEW_AWARD_COST_SHARE+".duplicateEntry", 
                        KeyConstants.ERROR_DUPLICATE_ENTRY);
            }
        }
        return valid;
    }
    
    /**
     * This method tests that Cost Share does not violate unique constraint on Database table.
     * @param awardCostShareRuleEvent
     * @param awardCostShare
     * @return
     */
    public boolean validateCostShareDoesNotViolateUniqueConstraintOnSave(AwardCostShareRuleEvent awardCostShareRuleEvent,
                                                                        AwardCostShare awardCostShare, int i) {
        AwardDocument awardDocument = (AwardDocument) awardCostShareRuleEvent.getDocument(); 
        boolean valid = true;
        int index = 0;
        for (AwardCostShare testCostShare : awardDocument.getAward().getAwardCostShares()) {
            if(index != i){
                if(testCostShare.getAwardNumber().equals(awardCostShare.getAwardNumber()) &&
                        testCostShare.getSequenceNumber().equals(awardCostShare.getSequenceNumber()) &&
                        testCostShare.getFiscalYear().equals(awardCostShare.getFiscalYear()) &&
                        testCostShare.getCostShareTypeCode().equals(awardCostShare.getCostShareTypeCode()) &&
                        testCostShare.getSource().equals(awardCostShare.getSource()) &&
                        testCostShare.getDestination().equals(awardCostShare.getDestination())) {
                    valid = false;
                    reportError(NEW_AWARD_COST_SHARE+".duplicateEntry", 
                            KeyConstants.ERROR_DUPLICATE_ENTRY);
                    break;
                }
            }
            index++;
        }
        return valid;
    }
    
//    /**
//     * This method...
//     * @param testCostShare
//     * @param awardCostShares
//     * @param valid
//     * @return
//     */
//    public boolean testCostShareForUniqueness(AwardCostShare testCostShare, List<AwardCostShare> awardCostShares, boolean valid, int index) {
//        int thisIndex = 1;
//        for (AwardCostShare compareCostShare : awardCostShares) {
//          if(thisIndex != index){
//              if(testCostShare.getAwardNumber().equals(compareCostShare.getAwardNumber()) &&
//                      testCostShare.getSequenceNumber().equals(compareCostShare.getSequenceNumber()) &&
//                      testCostShare.getFiscalYear().equals(compareCostShare.getFiscalYear()) &&
//                      testCostShare.getCostShareTypeCode().equals(compareCostShare.getCostShareTypeCode()) &&
//                      testCostShare.getSource().equals(compareCostShare.getSource()) &&
//                      testCostShare.getDestination().equals(compareCostShare.getDestination())) {
//                  valid = false;
//                  reportError(NEW_AWARD_COST_SHARE+".duplicateEntry", 
//                          KeyConstants.ERROR_DUPLICATE_ENTRY);
//                  break;
//              }
//          }
//          thisIndex++;
//      }
//        return valid;
//    }
    
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
        if (percentage!=null && percentage.isLessThan(new KualiDecimal(0))) {
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
        if (costShareMet!=null && costShareMet.isLessThan(new KualiDecimal(0))) {
            isValid = false;
            this.reportError(Constants.COST_SHARE_ADD_ACTION_PROPERTY_KEY + ".costShareMet", KeyConstants.ERROR_COST_SHARE_MET_INVALID, new String[] { costShareMet.toString() });
        }
        return isValid;
    }
}
