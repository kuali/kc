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
package org.kuali.kra.award;

import org.kuali.kra.award.home.AwardSponsorTerm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.common.framework.sponsor.term.SponsorTerm;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;

import java.util.List;

/**
 * This class applies sponsor term rules.
 */
public class AwardSponsorTermRuleImpl extends KcTransactionalDocumentRuleBase implements AwardSponsorTermRule {

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
