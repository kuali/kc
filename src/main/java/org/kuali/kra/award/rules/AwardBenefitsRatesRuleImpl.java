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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.ValidRates;
import org.kuali.kra.award.rule.AwardBenefitsRatesRule;
import org.kuali.kra.award.rule.event.AwardBenefitsRatesRuleEvent;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * This class processes Benefits Rates Business Rules
 */
public class AwardBenefitsRatesRuleImpl extends ResearchDocumentRuleBase implements AwardBenefitsRatesRule {

    private static final String ON_CAMPUS_RATE = "onCampusRate";
    private static final String OFF_CAMPUS_RATE = "offCampusRate";
    private static final String BENEFITS_RATES = "benefitsRates";
    BusinessObjectService businessObjectService;
    
    /**
     * @see org.kuali.kra.award.rule.AwardBenefitsRatesRule#processBenefitsRatesBusinessRules
     * (org.kuali.kra.award.rule.event.AwardBenefitsRatesRuleEvent)
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
        boolean valid = checkValidRatesOrNullValues(award);
        if(!valid){
            reportError(BENEFITS_RATES, 
                    KeyConstants.ERROR_BENEFITS_RATES);
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
    
    
}
