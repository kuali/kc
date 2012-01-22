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
package org.kuali.kra.award;

import java.util.List;

import org.kuali.kra.award.home.AwardSponsorTerm;
import org.kuali.kra.bo.SponsorTerm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * This class applies sponsor term rules.
 */
public class AwardSponsorTermRuleImpl extends ResearchDocumentRuleBase implements AwardSponsorTermRule {

    /**
     * @see org.kuali.kra.award.AwardSponsorTermRule#processSponsorTermBusinessRules
     * (org.kuali.kra.award.AwardSponsorTermRuleEvent)
     */
    private static final String NEW_AWARD_SPONSOR_TERM = "newAwardSponsorTerm[";
    private AwardSponsorTerm awardSponsorTerm;
    
    /**
     * @see org.kuali.kra.award.rule.AwardSponsorTermRule#processSponsorTermBusinessRules
     * (org.kuali.kra.award.rule.event.AwardSponsorTermRuleEvent)
     */
    
    /**
     * This method processes Sponsor Term Business Rules on Add method
     * @param awardSponsorTermRuleEvent
     * @return
     */
    public boolean processAddSponsorTermBusinessRules(AwardSponsorTermRuleEvent awardSponsorTermRuleEvent) {
        this.awardSponsorTerm = awardSponsorTermRuleEvent.getAwardSponsorTermForValidation();
        int typeCode = awardSponsorTermRuleEvent.getSponsorTermTypeCode();
        String sponsorTermCode = awardSponsorTermRuleEvent.getSponsorTermCode();
        boolean valid = true;
        if (!validateAwardSponsorTermNotEmpty(sponsorTermCode, typeCode))
            valid = false;
        else if (!validateAwardSponsorTermValid(awardSponsorTermRuleEvent.getAwardSponsorTermForValidation().getSponsorTerm(), sponsorTermCode, typeCode))
            valid = false;
        else if (!validateAwardSponsorTermNotDuplicate(awardSponsorTerm,
                awardSponsorTermRuleEvent.getAwardDocument().getAward().getAwardSponsorTerms()))
            valid = false;
        return valid;
    }
    
    /**
     * This method reports an error if sponsorTermCode is null (which means the user didn't select a sponsor term).
     * @param sponsorTermCode
     * @param sponsorTermTypeCode
     * @return
     */
    private boolean validateAwardSponsorTermNotEmpty(String sponsorTermCode, int sponsorTermTypeCode) {
        boolean valid = true;
        
        if (sponsorTermCode == null) {
            reportError(NEW_AWARD_SPONSOR_TERM + sponsorTermTypeCode + Constants.RIGHT_SQUARE_BRACKET, 
                                        KeyConstants.ERROR_NO_SPONSOR_TERM);
            valid = false;
        }
        return valid;
    }
    
    /**
     * This method reports an error if sponsorTerm is null (which means the user entered an invalid sponsor term code).
     * @param sponsorTerm
     * @param sponsorTermCode the value from the HTTP request
     * @param sponsorTermTypeCode
     * @return
     */
    private boolean validateAwardSponsorTermValid(SponsorTerm sponsorTerm, String sponsorTermCode, int sponsorTermTypeCode) {
        boolean valid = true;
        
        if (sponsorTerm == null) {
            reportError(NEW_AWARD_SPONSOR_TERM + sponsorTermTypeCode + Constants.RIGHT_SQUARE_BRACKET, 
                                        KeyConstants.ERROR_INVALID_SPONSOR_TERM, new String[] { sponsorTermCode });
            valid = false;
        }
        return valid;
    }
    
    /**
    *
    * Test source and destination for equality in AwardCostShare.
    * @param AwardCostShare, MessageMap
    * @return Boolean
    */
    boolean validateAwardSponsorTermNotDuplicate(AwardSponsorTerm awardSponsorTerm, List<AwardSponsorTerm> awardSponsorTerms){
        boolean valid = true;
        
        for(AwardSponsorTerm tempAwardSponsorTerm : awardSponsorTerms){
            if (awardSponsorTerm.getSponsorTermId().equals(tempAwardSponsorTerm.getSponsorTermId())){
                valid = false;
                reportError(NEW_AWARD_SPONSOR_TERM + awardSponsorTerm.getSponsorTerm().getSponsorTermTypeCode() + Constants.RIGHT_SQUARE_BRACKET,
                                        KeyConstants.ERROR_DUPLICATE_SPONSOR_TERM);
            }
        }
        return valid;
    }
}
