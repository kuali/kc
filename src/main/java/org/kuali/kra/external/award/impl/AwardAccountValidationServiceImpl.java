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
package org.kuali.kra.external.award.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.ValidRates;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.external.award.AwardAccountValidationService;
import org.kuali.kra.external.award.FinancialIndirectCostRecoveryTypeCode;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

public class AwardAccountValidationServiceImpl implements AwardAccountValidationService {

    private static final String AWARD_EFFECTIVE_DATE_NOT_SPECIFIED = "error.award.createAccount.invalid.effectiveDate";
    private static final String AWARD_ID_NOT_SPECIFIED = "error.award.createAccount.invalid.awardId";
    private static final String AWARD_END_DATE_NOT_SPECIFIED = "error.award.createAccount.invalid.endDate";
    private static final String AWARD_ACTIVITY_TYPE_CODE = "error.award.createAccount.invalid.activityTypeCode";
    private static final String AWARD_PAYMENT_BASIS_NOT_SPECIFIED = "error.award.createAccount.invalid.paymentBasis";
    private static final String AWARD_PAYMENT_METHOD_NOT_SPECIFIED = "error.award.createAccount.invalid.paymentMethod";
    private static final String AWARD_ADDRESS_NOT_COMPLETE = "error.award.createAccount.invalid.piAddress";
    private static final String AWARD_PI_NOT_SPECIFIED = "error.award.createAccount.invalid.pi";
    private static final String AWARD_F_AND_A_RATE_NOT_SPECIFIED = "error.award.createAccount.invalid.rate";
    private static final String CURRENT_RATE_NOT_SPECIFIED = "error.award.createAccount.invalid.currentRate";
    private static final String AWARD_ACCOUNT_NUMBER_NOT_SPECIFIED = "error.award.createAccount.invalid.accountNumber";
    private BusinessObjectService businessObjectService;
    
    public boolean validateAwardAccountDetails(Award award) {
        boolean rulePassed = true;
        
        rulePassed &= isValidEffectiveDate(award);
        rulePassed &= isValidExpenseGuidelineText(award);
        rulePassed &= isValidExpirationDate(award);
        rulePassed &= isValidHigherEducationCode(award);
        rulePassed &= isValidPaymentBasis(award);
        rulePassed &= isValidPaymentMethod(award);
        rulePassed &= isValidAddress(award);
        rulePassed &= isValidFandarate(award);        
        /*
         * Not sure if this is a required field, commenting it out for now
         */
        //rulePassed &= isValidIdcRate(award);
        return rulePassed;
    }


    /**
     * This method check if the default address (PI address) is present.
     * @param award
     * @return isValid
     */
    protected boolean isValidAddress(Award award) {
       
        boolean isValid = true;
        if (ObjectUtils.isNull(award.getPrincipalInvestigator())) {
            GlobalVariables.getMessageMap().putError(AWARD_PI_NOT_SPECIFIED, 
                    KeyConstants.AWARD_PI_NOT_SPECIFIED);
            isValid = false;
        } else {
            KcPerson principalInvestigator = award.getPrincipalInvestigator().getPerson();
            if (ObjectUtils.isNotNull(principalInvestigator)) {
                String streetAddress = principalInvestigator.getAddressLine1();
                String cityName = principalInvestigator.getCity();
                String stateCode = principalInvestigator.getState();
                String postalCode = principalInvestigator.getPostalCode();
                if (StringUtils.isBlank(streetAddress) || StringUtils.isBlank(cityName)
                    || StringUtils.isBlank(stateCode) || StringUtils.isBlank(postalCode))  {
                    GlobalVariables.getMessageMap().putError(AWARD_ADDRESS_NOT_COMPLETE, 
                            KeyConstants.AWARD_ADDRESS_NOT_COMPLETE);
                    isValid = false;
                }
            } else {
                GlobalVariables.getMessageMap().putError(AWARD_ADDRESS_NOT_COMPLETE, 
                        KeyConstants.AWARD_ADDRESS_NOT_COMPLETE);
                isValid = false;
            }
        }
        
        return isValid;
    }
  
 
    /**
     * This method checks if there are F and A rates provided. And that there is at least one
     * for the current year.
     * @param award
     * @return isValid
     */
    protected boolean isValidFandarate(Award award) {
        List<AwardFandaRate> rates = award.getAwardFandaRate();
        boolean isValid = true;
        boolean currentYearRate = false;
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        if (ObjectUtils.isNull(rates) || rates.size() == 0) {
            GlobalVariables.getMessageMap().putError(AWARD_F_AND_A_RATE_NOT_SPECIFIED, 
                                                    KeyConstants.AWARD_F_AND_A_RATE_NOT_SPECIFIED);
            isValid = false;
        } else {
            for (AwardFandaRate rate : rates) {
                if (Integer.parseInt(rate.getFiscalYear()) == currentYear) {
                    currentYearRate = true;
                }      
            }
        }
        
        if (!currentYearRate) {
            GlobalVariables.getMessageMap().putError(CURRENT_RATE_NOT_SPECIFIED, 
                                                    KeyConstants.CURRENT_RATE_NOT_SPECIFIED, currentYear+"");
            isValid = false;
        }
        return isValid;
    }
    
    protected boolean isValidIdcRate(Award award) {
        List<AwardFandaRate> rates = award.getAwardFandaRate();
        boolean isValid = true;
        if (ObjectUtils.isNull(rates) || rates.size() == 0) {
            GlobalVariables.getMessageMap().putError(AWARD_F_AND_A_RATE_NOT_SPECIFIED, 
                                                    KeyConstants.AWARD_F_AND_A_RATE_NOT_SPECIFIED);
            isValid = false;
        } else {
            for (AwardFandaRate rate : rates) {
                String rateClassCode = rate.getFandaRateType().getRateClassCode();
                String rateTypeCode = rate.getFandaRateType().getRateTypeCode();
                FinancialIndirectCostRecoveryTypeCode icrCostTypeCode = getIndirectCostRecoveryTypeCode(rateClassCode, rateTypeCode);
                String icrRateCode = getIcrRateCode(rate);
                if (ObjectUtils.isNull(icrCostTypeCode) || StringUtils.isEmpty(icrCostTypeCode.getIcrTypeCode())) {
                    String errorParameter = "(" + rateClassCode + ", " + rateTypeCode + ")";
                    GlobalVariables.getMessageMap().putError(AWARD_F_AND_A_RATE_NOT_SPECIFIED, KeyConstants.AWARD_ICR_RATE_TYPE_CODE_EMPTY, errorParameter);
                    isValid &= false;
                }
                if (ObjectUtils.isNull(icrRateCode) || StringUtils.isEmpty(icrRateCode)) {
                    GlobalVariables.getMessageMap().putError(AWARD_F_AND_A_RATE_NOT_SPECIFIED, KeyConstants.AWARD_ICR_RATE_CODE_EMPTY, rate.getApplicableFandaRate()+"");
                }
            }
        }
        return isValid;
    }
    
    protected FinancialIndirectCostRecoveryTypeCode getIndirectCostRecoveryTypeCode(String rateClassCode, String rateTypeCode) {
        Map <String, Object> criteria = new HashMap<String, Object>();
        criteria.put("rateClassCode", rateClassCode);
        criteria.put("rateTypeCode", rateTypeCode);
        FinancialIndirectCostRecoveryTypeCode icrCostTypeCode= (FinancialIndirectCostRecoveryTypeCode) businessObjectService.findByPrimaryKey(FinancialIndirectCostRecoveryTypeCode.class, criteria);
        return icrCostTypeCode;
    }
    
    protected String getIcrRateCode(AwardFandaRate currentFandaRate) { 
        String icrRateCode = "";
        Map <String, Object> criteria = new HashMap<String, Object>();
        if (currentFandaRate.getOnCampusFlag().equalsIgnoreCase("N")) {
            criteria.put("onCampusRate", currentFandaRate.getApplicableFandaRate());
        } else {
            criteria.put("offCampusRate", currentFandaRate.getApplicableFandaRate());
        }
        // TODO Auto-generated method stub
        List<ValidRates> rates = (List<ValidRates>) businessObjectService.findMatching(ValidRates.class, criteria);
        
        // you should only find one rate that matches this criteria, this check happens in the award
        //business rules
        if (ObjectUtils.isNotNull(rates) && !rates.isEmpty()) {
            icrRateCode = rates.get(0).getIcrRateCode();
        } 
        return icrRateCode;
    }
    
    protected boolean isValidPaymentBasis(Award award) {
        if (award.getBasisOfPaymentCode() == null) {
            GlobalVariables.getMessageMap().putError(AWARD_PAYMENT_BASIS_NOT_SPECIFIED, 
                                                    KeyConstants.AWARD_PAYMENT_BASIS_NOT_SPECIFIED);
            return false;
        }
        return true;
    }
    
    protected boolean isValidPaymentMethod(Award award) {
        if (award.getMethodOfPaymentCode() == null) {
            GlobalVariables.getMessageMap().putError(AWARD_PAYMENT_METHOD_NOT_SPECIFIED, 
                                                    KeyConstants.AWARD_PAYMENT_METHOD_NOT_SPECIFIED);
            return false;
        }
        return true;
    }
    
    protected boolean isValidHigherEducationCode(Award award) {
        if (award.getActivityTypeCode() == null) {
            GlobalVariables.getMessageMap().putError(AWARD_ACTIVITY_TYPE_CODE, 
                                                    KeyConstants.AWARD_ACTIVITY_TYPE_CODE);
            return false;
        }
        return true;
    }

    
    /**
     * This method checks if the effective date is valid.
     * @param award
     * @return
     */
    protected boolean isValidEffectiveDate(Award award) {
        if (award.getAwardEffectiveDate() == null) {
            GlobalVariables.getMessageMap().putError(AWARD_EFFECTIVE_DATE_NOT_SPECIFIED, 
                                                    KeyConstants.AWARD_NO_VALID_EFFECTIVE_DATE);
            return false;
        }
        return true; 
    }
    
    
    /**
     * This method checks the award id which is the expense guideline text.
     * @param award
     * @return
     */
    protected boolean isValidExpenseGuidelineText(Award award) { 
        if (award.getAwardId() == null) {
            GlobalVariables.getMessageMap().putError(AWARD_ID_NOT_SPECIFIED, 
                                                    KeyConstants.AWARD_ID_NOT_SPECIFIED);
            return false;
        }
        return true;
    }
    
    
    /**
     * This method checks if the expiration date is valid.
     * @param award
     * @return
     */
    protected boolean isValidExpirationDate(Award award) {
        if (award.getProjectEndDate() == null) {
            GlobalVariables.getMessageMap().putError(AWARD_END_DATE_NOT_SPECIFIED, 
                                                    KeyConstants.AWARD_END_DATE_NOT_SPECIFIED);
            return false;
        }
        return true;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
