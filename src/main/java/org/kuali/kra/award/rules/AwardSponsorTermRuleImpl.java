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

import org.kuali.kra.award.bo.AwardSponsorTerm;
import org.kuali.kra.award.rule.AwardSponsorTermRule;
import org.kuali.kra.award.rule.event.AwardSponsorTermRuleEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * This class...
 */
public class AwardSponsorTermRuleImpl extends ResearchDocumentRuleBase implements AwardSponsorTermRule {

    /**
     * @see org.kuali.kra.award.rule.AwardSponsorTermRule#processSponsorTermBusinessRules
     * (org.kuali.kra.award.rule.event.AwardSponsorTermRuleEvent)
     */
    private static final String NEW_AWARD_SPONSOR_TERM = "newAwardSponsorTerm[";
    private AwardSponsorTerm awardSponsorTerm;
    private AwardSponsorTermRuleEvent awardSponsorTermRuleEvent;
    
    /**
     * @see org.kuali.kra.award.rule.AwardSponsorTermRule#processSponsorTermBusinessRules
     * (org.kuali.kra.award.rule.event.AwardSponsorTermRuleEvent)
     */
    public boolean processSponsorTermBusinessRules(AwardSponsorTermRuleEvent awardSponsorTermRuleEvent) {
        this.awardSponsorTerm = awardSponsorTermRuleEvent.getAwardSponsorTermForValidation();
        this.awardSponsorTermRuleEvent = awardSponsorTermRuleEvent;
        return processCommonValidations(awardSponsorTerm);
    }
    
    public boolean processAddSponsorTermBusinessRules(AwardSponsorTermRuleEvent awardSponsorTermRuleEvent) {
        this.awardSponsorTerm = awardSponsorTermRuleEvent.getAwardSponsorTermForValidation();
        this.awardSponsorTermRuleEvent = awardSponsorTermRuleEvent;
        boolean validAwardSponsorTermNotDuplicate = validateAwardSponsorTermNotDuplicate(awardSponsorTerm);
        return processCommonValidations(awardSponsorTerm) && validAwardSponsorTermNotDuplicate;
    }
    
    /**
     * This method processes common validations for business rules
     * @param event
     * @return
     */
    public boolean processCommonValidations(AwardSponsorTerm awardSponsorTerm) {
        return true;
    }

    /**
    *
    * Test source and destination for equality in AwardCostShare.
    * @param AwardCostShare, ErrorMap
    * @return Boolean
    */
    public boolean validateAwardSponsorTermNotDuplicate(AwardSponsorTerm awardSponsorTerm){
        boolean valid = true;
        
        for(AwardSponsorTerm tempAwardSponsorTerm 
                   : awardSponsorTermRuleEvent.getAwardDocument().getAward().getAwardSponsorTerms()){
            if (awardSponsorTerm.getSponsorTermId().equals(tempAwardSponsorTerm.getSponsorTermId())){
                valid = false;
                reportError(NEW_AWARD_SPONSOR_TERM+awardSponsorTerm.getSponsorTerm().
                                        getSponsorTermTypeCode()+Constants.RIGHT_SQUARE_BRACKET, 
                                                            KeyConstants.ERROR_DUPLICATE_SPONSOR_TERM);
            }
        }
        return valid;
    }
}
