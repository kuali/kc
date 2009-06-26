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
package org.kuali.kra.award.home.approvedsubawards;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.Organization;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * the AwardApprovedSubawardRuleImpl 
 */
public class AwardApprovedSubawardRuleImpl extends ResearchDocumentRuleBase 
                                            implements AwardApprovedSubawardRule {
    
    private static final String NEW_AWARD_APPROVED_SUBAWARD = "approvedSubawardFormHelper.newAwardApprovedSubaward";
    private static final String ORGANIZATION_NAME = ".organizationName";
    private static final String AMOUNT = ".amount";
    
    List<AwardApprovedSubaward> awardApprovedSubawards;
    AwardApprovedSubaward awardApprovedSubaward;
    String errorPath;
    
    

    /**
     * @see org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubawardRule#processApprovedSubawardBusinessRules
     * (org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubawardRuleEvent)
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
     * @see org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubawardRule#processApprovedSubawardBusinessRules
     * (org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubawardRuleEvent)
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
        String organizationName = awardApprovedSubaward.getOrganizationName();
        if(organizationName == null) {
            valid = false;
            reportError(NEW_AWARD_APPROVED_SUBAWARD+ORGANIZATION_NAME, 
                    KeyConstants.ERROR_ORGANIZATION_NAME_IS_NULL);
        }else {
            valid = validateOrganizationExists(organizationName) && validateApprovedSubawardDuplicateOrganization();
        }
        return valid;
    }
    
    /**
     * This method tests whether a Organization BO with a given organization name exists.
     * @param organizationName
     * @return
     */
    private boolean validateOrganizationExists(String organizationName) {
        BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        Map<String,String> fieldValues = new HashMap<String,String>();
        fieldValues.put("organizationName", organizationName);
        
        boolean isValid = true;
        if (businessObjectService.countMatching(Organization.class, fieldValues) != 1) {
            this.reportError(NEW_AWARD_APPROVED_SUBAWARD+ORGANIZATION_NAME, KeyConstants.ERROR_ORGANIZATION_NAME_IS_INVALID, new String[] { organizationName });
            return false;
        }
        return isValid;
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
                  reportError(NEW_AWARD_APPROVED_SUBAWARD+ORGANIZATION_NAME, 
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
        KualiDecimal amount = awardApprovedSubaward.getAmount();
        boolean valid = true;
        if (amount == null)
            valid = false;   // a "required field" error is already reported by the framework, so don't call reportError
        else if(!amount.isGreaterThan(new KualiDecimal(0.00))) {
            valid = false;
            reportError(NEW_AWARD_APPROVED_SUBAWARD+AMOUNT, 
                    KeyConstants.ERROR_AMOUNT_IS_ZERO);
        }
        return valid;
    }

}
