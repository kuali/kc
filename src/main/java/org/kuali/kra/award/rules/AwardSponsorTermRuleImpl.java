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
    
    /**
     * @see org.kuali.kra.award.rule.AwardSponsorTermRule#processSponsorTermBusinessRules
     * (org.kuali.kra.award.rule.event.AwardSponsorTermRuleEvent)
     */
    public boolean processSponsorTermBusinessRules(AwardSponsorTermRuleEvent awardSponsorTermRuleEvent) {
        this.awardSponsorTerm = awardSponsorTermRuleEvent.getAwardSponsorTermForValidation();
        return processCommonValidations(awardSponsorTerm);
    }
    
    /**
     * This method processes Sponsor Term Business Rules on Add method
     * @param awardSponsorTermRuleEvent
     * @return
     */
    public boolean processAddSponsorTermBusinessRules(AwardSponsorTermRuleEvent awardSponsorTermRuleEvent) {
        this.awardSponsorTerm = awardSponsorTermRuleEvent.getAwardSponsorTermForValidation();
        boolean validAwardSponsorTermNotDuplicate = validateAwardSponsorTermNotDuplicate(awardSponsorTerm,
                                                        awardSponsorTermRuleEvent.getAwardDocument().getAward().getAwardSponsorTerms());
        return processCommonValidations(awardSponsorTerm) && validAwardSponsorTermNotDuplicate;
    }
    
    /**
     * This method processes common validations for business rules
     * @param event
     * @return
     */
    boolean processCommonValidations(AwardSponsorTerm awardSponsorTerm) {
        return true;
    }

    /**
    *
    * Test source and destination for equality in AwardCostShare.
    * @param AwardCostShare, ErrorMap
    * @return Boolean
    */
    boolean validateAwardSponsorTermNotDuplicate(AwardSponsorTerm awardSponsorTerm, List<AwardSponsorTerm> awardSponsorTerms){
        boolean valid = true;
        
        for(AwardSponsorTerm tempAwardSponsorTerm : awardSponsorTerms){
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
