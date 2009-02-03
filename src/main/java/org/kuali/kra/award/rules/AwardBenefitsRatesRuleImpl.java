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

import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.ValidRates;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.rule.AwardBenefitsRatesRule;
import org.kuali.kra.award.rule.event.AwardBenefitsRatesRuleEvent;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * This class processes Benefits Rates Business Rules
 */
public class AwardBenefitsRatesRuleImpl extends ResearchDocumentRuleBase implements AwardBenefitsRatesRule {

    private static final String BENEFITS_RATES = "benefitsRates";
    
    /**
     * @see org.kuali.kra.award.rule.AwardBenefitsRatesRule#processBenefitsRatesBusinessRules
     * (org.kuali.kra.award.rule.event.AwardBenefitsRatesRuleEvent)
     */
    public boolean processBenefitsRatesBusinessRules
                       (AwardBenefitsRatesRuleEvent awardBenefitsRatesRuleEvent) {
        boolean valid = true;
        valid &= validateBenefitsRatesInValidRatesTable(awardBenefitsRatesRuleEvent);
        return valid;
    }
    
    /**
     * This method pulls in all Valid Rates from database and tests if the valid rates in the award are in the
     * valid rates table.
     * @param event
     * @return
     */
    private boolean validateBenefitsRatesInValidRatesTable(AwardBenefitsRatesRuleEvent event) {
        @SuppressWarnings("unused")
        boolean valid = true;
        AwardDocument awardDocument = event.getAwardDocument();
        Award award = awardDocument.getAward();
        valid &= checkValidRateInValidRatesTable(award, event);
        if(!valid){
            reportError(BENEFITS_RATES, 
                    KeyConstants.ERROR_BENEFITS_RATES);
        }
        
        return valid;
    }
    
    protected boolean checkValidRateInValidRatesTable(Award award, AwardBenefitsRatesRuleEvent event){
        boolean valid = true;
        Collection<ValidRates> validRates = getValidRates();
        if(award.getSpecialEbRateOffCampus()!=null
                || award.getSpecialEbRateOnCampus()!=null){
                    for (ValidRates validRate : validRates){
                        if(award.getSpecialEbRateOffCampus().intValue()==validRate.getOffCampusRate().intValue() 
                                && award.getSpecialEbRateOnCampus().intValue()==validRate.getOnCampusRate().intValue()) {
                            valid=true;
                            break; 
                        }
                        else{
                            valid=false;
                        }
                    }
            }
        return valid;
    }
    
    
    /**
     * This method...
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Collection<ValidRates> getValidRates(){
        Collection<ValidRates> validRates =
            (Collection<ValidRates>) getKraBusinessObjectService().findAll(ValidRates.class);
        return validRates;
    }
    
    /**
     * This method returns the Kra business object service.
     * @return
     */
    protected BusinessObjectService getKraBusinessObjectService() {
        return (BusinessObjectService) KraServiceLocator.getService("businessObjectService");
    }
    
    
}
