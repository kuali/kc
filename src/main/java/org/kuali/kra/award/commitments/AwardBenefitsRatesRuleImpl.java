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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.ValidRates;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class processes Benefits Rates Business Rules
 */
public class AwardBenefitsRatesRuleImpl extends ResearchDocumentRuleBase implements AwardBenefitsRatesRule {

    private static final String ON_CAMPUS_RATE = "onCampusRate";
    private static final String OFF_CAMPUS_RATE = "offCampusRate";
    private static final String BENEFITS_RATES = "benefitsRates";
    private ParameterService parameterService;
    BusinessObjectService businessObjectService;
    
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KraServiceLocator.getService(ParameterService.class);        
        }
        return this.parameterService;
    }
    
    /**
     * @see org.kuali.kra.award.commitments.AwardBenefitsRatesRule#processBenefitsRatesBusinessRules
     * (org.kuali.kra.award.commitments.AwardBenefitsRatesRuleEvent)
     */
    public boolean processBenefitsRatesBusinessRules(AwardBenefitsRatesRuleEvent event) {
        return validateBenefitsRatesInValidRatesTable(event.getAward());
    }
    
    /**
     * This method pulls in all Valid Rates from database and tests if the valid rates in the award are in the
     * valid rates table.
     * @param event
     * @return
     */
    boolean validateBenefitsRatesInValidRatesTable(Award award) {
        boolean valid = true;
        if(StringUtils.equalsIgnoreCase(
                this.getParameterService().getParameterValueAsString(AwardDocument.class,
                        KeyConstants.ENABLE_AWARD_FNA_VALIDATION),
                        KeyConstants.ENABLED_PARAMETER_VALUE_ONE)){
            valid = checkValidRatesOrNullValues(award);
            if(!valid){
                reportError(BENEFITS_RATES, 
                        KeyConstants.ERROR_BENEFITS_RATES);
            }
        }else if(StringUtils.equalsIgnoreCase(
                this.getParameterService().getParameterValueAsString(AwardDocument.class,
                        KeyConstants.ENABLE_AWARD_FNA_VALIDATION),
                        KeyConstants.ENABLED_PARAMETER_VALUE_TWO)){
            valid = checkSingleValidRatesOrNullValues(award);
            if(!valid){
                if(StringUtils.equalsIgnoreCase(
                        this.getParameterService().getParameterValueAsString(AwardDocument.class,
                                KeyConstants.OPTION_WARNING_ERROR_AWARD_FANDA_VALIDATION),
                                KeyConstants.ERROR)){
                    reportError(BENEFITS_RATES, 
                            KeyConstants.ERROR_BENEFITS_RATES);
                }else{
                    valid = true;
                    reportWarning(BENEFITS_RATES, 
                            KeyConstants.ERROR_BENEFITS_RATES);
                }
               
            }
            
        }
        
        
        return valid;
    }
    
    /**
     * This method tests that the valid rates attached to the award are in the valid rates table.
     * @param award
     * @return
     */
    boolean checkValidRatesOrNullValues(Award award) {
        boolean valid = true;
        if(award.getSpecialEbRateOffCampus() != null 
                || award.getSpecialEbRateOnCampus() != null) {
            valid = getValidRates(award.getSpecialEbRateOnCampus(), 
                        award.getSpecialEbRateOffCampus()).size() > 0;
        }
        return valid;
    }
    /**
     * This method tests that the valid rates attached to the award are in the valid rates table for single rate.
     * @param award
     * @return
     */
    boolean checkSingleValidRatesOrNullValues(Award award) {
        boolean valid = true;
        if(award.getSpecialEbRateOffCampus() != null ) {
            valid = getValidRatesforSingleRate(OFF_CAMPUS_RATE, 
                        award.getSpecialEbRateOffCampus()).size() > 0;
        }
        if(award.getSpecialEbRateOnCampus() != null ) {
            valid = getValidRatesforSingleRate(ON_CAMPUS_RATE, 
                        award.getSpecialEbRateOnCampus()).size() > 0;
        }
        return valid;
    }
    
    /**
     * This method returns the valid rates from valid rates table that match the rates attached to the award.
     * @return
     */
    @SuppressWarnings("unchecked")
    Collection<ValidRates> getValidRates(KualiDecimal specialEbRateOnCampus, KualiDecimal specialEbRateOffCampus){
        Map<String, Object> rateValues = new HashMap<String, Object>();
        rateValues.put(ON_CAMPUS_RATE, specialEbRateOnCampus);
        rateValues.put(OFF_CAMPUS_RATE, specialEbRateOffCampus);
        return (Collection<ValidRates>) 
                getKraBusinessObjectService().findMatching(ValidRates.class, rateValues);
    }
    
    /**
     * This method returns the Kra business object service.
     * @return
     */
    BusinessObjectService getKraBusinessObjectService() {
        if(businessObjectService == null){
            businessObjectService = 
                (BusinessObjectService) KraServiceLocator.getService("businessObjectService");
        }
        return businessObjectService;
    }
    /**
     * This method returns the valid rates from valid rates table that match the single rate attached to the award.
     * @return
     */
    @SuppressWarnings("unchecked")
    Collection<ValidRates> getValidRatesforSingleRate(String rateType,KualiDecimal benefitRate){
        Map<String, Object> rateValues = new HashMap<String, Object>();
        rateValues.put(rateType, benefitRate);
       
        return (Collection<ValidRates>) 
                getKraBusinessObjectService().findMatching(ValidRates.class, rateValues);
    }
    
    
}
