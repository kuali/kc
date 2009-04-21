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

import java.util.List;

import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.award.bo.AwardApprovedSubaward;
import org.kuali.kra.award.rule.AwardApprovedSubawardRule;
import org.kuali.kra.award.rule.event.AwardApprovedSubawardRuleEvent;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * the AwardApprovedSubawardRuleImpl 
 */
public class AwardApprovedSubawardRuleImpl extends ResearchDocumentRuleBase 
                                            implements AwardApprovedSubawardRule {
    
    private static final String NEW_AWARD_APPROVED_SUBAWAD = "newAwardApprovedSubaward";
    private static final String ORGANIZATION_NAME = ".organizationName";
    
    //private static final Integer ZERO = 0;
    
    List<AwardApprovedSubaward> awardApprovedSubawards;
    AwardApprovedSubaward awardApprovedSubaward;
    String errorPath;
    
    

    /**
     * @see org.kuali.kra.award.rule.AwardApprovedSubawardRule#processApprovedSubawardBusinessRules
     * (org.kuali.kra.award.rule.event.AwardApprovedSubawardRuleEvent)
     */
    public boolean processApprovedSubawardBusinessRules(AwardApprovedSubawardRuleEvent 
                                                            awardApprovedSubawardRuleEvent) {
        this.awardApprovedSubawards = awardApprovedSubawardRuleEvent.getAwardApprovedSubawards();
        this.awardApprovedSubaward = awardApprovedSubawardRuleEvent.getApprovedSubaward();
        this.errorPath = awardApprovedSubawardRuleEvent.getErrorPathPrefix();
        return processCommonValidations();
    }
    
    /**
     * Gets the awardApprovedSubawards attribute. 
     * @return Returns the awardApprovedSubawards.
     */
    public List<AwardApprovedSubaward> getAwardApprovedSubawards() {
        return awardApprovedSubawards;
    }

    /**
     * Sets the awardApprovedSubawards attribute value.
     * @param awardApprovedSubawards The awardApprovedSubawards to set.
     */
    public void setAwardApprovedSubawards(List<AwardApprovedSubaward> awardApprovedSubawards) {
        this.awardApprovedSubawards = awardApprovedSubawards;
    }

    /**
     * Gets the awardApprovedSubaward attribute. 
     * @return Returns the awardApprovedSubaward.
     */
    public AwardApprovedSubaward getAwardApprovedSubaward() {
        return awardApprovedSubaward;
    }

    /**
     * Sets the awardApprovedSubaward attribute value.
     * @param awardApprovedSubaward The awardApprovedSubaward to set.
     */
    public void setAwardApprovedSubaward(AwardApprovedSubaward awardApprovedSubaward) {
        this.awardApprovedSubaward = awardApprovedSubaward;
    }

    /**
     * Gets the errorPath attribute. 
     * @return Returns the errorPath.
     */
    public String getErrorPath() {
        return errorPath;
    }

    /**
     * Sets the errorPath attribute value.
     * @param errorPath The errorPath to set.
     */
    public void setErrorPath(String errorPath) {
        this.errorPath = errorPath;
    }

    /**
     * @see org.kuali.kra.award.rule.AwardApprovedSubawardRule#processApprovedSubawardBusinessRules
     * (org.kuali.kra.award.rule.event.AwardApprovedSubawardRuleEvent)
     */
    public boolean processAddApprovedSubawardBusinessRules(AwardApprovedSubawardRuleEvent 
                                                            awardApprovedSubawardRuleEvent) {
        this.awardApprovedSubawards = awardApprovedSubawardRuleEvent.getAwardApprovedSubawards();
        this.awardApprovedSubaward = awardApprovedSubawardRuleEvent.getApprovedSubaward();
        this.errorPath = awardApprovedSubawardRuleEvent.getErrorPathPrefix();
        boolean validOrganization = validateApprovedSubawardOrganization();
        boolean commonValidations = processCommonValidations();
        return commonValidations && validOrganization;
    }
    
    /**
     * This method processes common validations for business rules
     * @param event
     * @return
     */
    public boolean processCommonValidations() {
        boolean validAmount = validateApprovedSubawardAmount();
        return validAmount;
    }
    
    /**
     * This method...
     * @return
     */
    public boolean validateApprovedSubawardOrganization(){
        boolean valid = true;
        if(awardApprovedSubaward.getOrganizationName() == null) {
            valid = false;
            reportError(NEW_AWARD_APPROVED_SUBAWAD+ORGANIZATION_NAME, 
                    KeyConstants.ERROR_ORGANIZATION_NAME_IS_NULL);
        }else {
            valid = validateApprovedSubawardDuplicateOrganization();
        }
        return valid;
    }
    
    /**
    *
    * Test Approved Subawards for duplicate organizations
    * @return Boolean
    */
    public boolean validateApprovedSubawardDuplicateOrganization(){
        boolean valid = true;
        for (AwardApprovedSubaward loopAwardApprovedSubaward : awardApprovedSubawards) {
              if(awardApprovedSubaward.getOrganizationName().equals
                      (loopAwardApprovedSubaward.getOrganizationName())){
                  valid = false;
                  reportError(NEW_AWARD_APPROVED_SUBAWAD+ORGANIZATION_NAME, 
                          KeyConstants.ERROR_DUPLICATE_ORGANIZATION_NAME);
                  break;
              }
          }
        return valid;
       }
    
    /**
    *
    * Test Approved Subaward amount for zero and negative value.
    * @param AwardApprovedSubaward, ErrorMap
    * @return Boolean
    */
    public boolean validateApprovedSubawardAmount(){
        boolean valid = awardApprovedSubaward.getAmount().isGreaterThan(new KualiDecimal(0.00));
        if(!valid) {
            reportError(NEW_AWARD_APPROVED_SUBAWAD+".amount", 
                    KeyConstants.ERROR_AMOUNT_IS_ZERO);
        }
        return valid;
    }

}
