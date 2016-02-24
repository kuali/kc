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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.ValidRates;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This class processes Benefits Rates Business Rules
 */
public class AwardBenefitsRatesRuleImpl extends KcTransactionalDocumentRuleBase implements AwardBenefitsRatesRule {

    private static final String ON_CAMPUS_RATE = "onCampusRate";
    private static final String OFF_CAMPUS_RATE = "offCampusRate";
    private static final String BENEFITS_RATES = "benefitsRates";
    private ParameterService parameterService;
    BusinessObjectService businessObjectService;
    
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
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
    Collection<ValidRates> getValidRates(ScaleTwoDecimal specialEbRateOnCampus, ScaleTwoDecimal specialEbRateOffCampus){
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
                (BusinessObjectService) KcServiceLocator.getService("businessObjectService");
        }
        return businessObjectService;
    }
    /**
     * This method returns the valid rates from valid rates table that match the single rate attached to the award.
     * @return
     */
    @SuppressWarnings("unchecked")
    Collection<ValidRates> getValidRatesforSingleRate(String rateType,ScaleTwoDecimal benefitRate){
        Map<String, Object> rateValues = new HashMap<String, Object>();
        rateValues.put(rateType, benefitRate);
       
        return (Collection<ValidRates>) 
                getKraBusinessObjectService().findMatching(ValidRates.class, rateValues);
    }
    
    
}
