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
package org.kuali.kra.award.detailsdates;

import java.util.List;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardTransferringSponsor;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * Default implementation of AwardDetailsAndDatesRule
 * 
 * @author Kuali Coeus development team (kc.dev@kuali.org)
 */
public class AwardDetailsAndDatesRuleImpl extends ResearchDocumentRuleBase implements AwardDetailsAndDatesRule {
    
    private static final String SPONSOR_CODE_PROPERTY_NAME = "detailsAndDatesFormHelper.newAwardTransferringSponsor.sponsorCode";
    private static final String ANTICIPATED_AMOUNT_PROPERTY_NAME = "awardAmountInfos[0].anticipatedTotalAmount";
    private static final String OBLIGATED_AMOUNT_PROPERTY_NAME = "awardAmountInfos[0].amountObligatedToDate";
    private static final String AWARD_EFFECTIVE_DATE_PROPERTY_NAME = "awardEffectiveDate";
    private static final String OBLIGATION_EXPIRATION_DATE_PROPERTY_NAME = "obligationExpirationDate";
    

    
    /**
     * @see org.kuali.kra.award.detailsdates.AwardDetailsAndDatesRule#processAddAwardTransferringSponsorEvent
     * (org.kuali.kra.award.rule.event.AddAwardTransferringSponsorEvent)
     */
    public boolean processAddAwardTransferringSponsorEvent(AddAwardTransferringSponsorEvent addAwardTransferringSponsorEvent) {
        boolean valid = true;
        Sponsor sponsor = addAwardTransferringSponsorEvent.getSponsorToBecomeAwardTransferringSponsor();
        if (isUnknownSponsor(sponsor)) {
            valid = false;
            reportError(SPONSOR_CODE_PROPERTY_NAME, KeyConstants.ERROR_INVALID_AWARD_TRANSFERRING_SPONSOR);
        }
        if (isDuplicateSponsor(sponsor, addAwardTransferringSponsorEvent.getAward().getAwardTransferringSponsors())) {
            valid = false;
            reportError(SPONSOR_CODE_PROPERTY_NAME, KeyConstants.ERROR_DUPLICATE_AWARD_TRANSFERRING_SPONSOR);
        }
        return valid;
    }
    
    // Check whether the Sponsor has a record in the system.
    private boolean isUnknownSponsor(Sponsor sponsor) {
        Sponsor dbSponsor = (Sponsor) getBusinessObjectService().retrieve(sponsor);
        return dbSponsor == null;
    }
    
    // Check whether the Sponsor has already been added.
    private boolean isDuplicateSponsor(Sponsor sponsor, List<AwardTransferringSponsor> awardTransferringSponsors) {
        for (AwardTransferringSponsor awardTransferringSponsor: awardTransferringSponsors) {
            if (awardTransferringSponsor.getSponsorCode().equals(sponsor.getSponsorCode())) {
                return true;
            }
        }
        return false;
    }

    public boolean processSaveAwardDetailsAndDates(AwardDetailsAndDatesSaveEvent awardDetailsAndDatesSaveEvent) {
        boolean valid = true;
        Award award = awardDetailsAndDatesSaveEvent.getAward();
        if(award.getAnticipatedTotal().isLessThan(award.getObligatedTotal())) {
            valid = false;
            reportError(ANTICIPATED_AMOUNT_PROPERTY_NAME, KeyConstants.ERROR_ANTICIPATED_AMOUNT);
        }
        if (award.getObligatedTotal().isLessThan(KualiDecimal.ZERO)) {
            valid = false;
            reportError(OBLIGATED_AMOUNT_PROPERTY_NAME, KeyConstants.ERROR_OBLIGATED_AMOUNT_NEGATIVE);
        }
        if (award.getAnticipatedTotal().isLessThan(KualiDecimal.ZERO)) {
            valid = false;
            reportError(ANTICIPATED_AMOUNT_PROPERTY_NAME, KeyConstants.ERROR_ANTICIPATED_AMOUNT_NEGATIVE);
        }
        if(award.getObligatedTotal().isGreaterThan(new KualiDecimal(0)) &&
                //award.getAwardEffectiveDate() == null) {
                award.getAwardAmountInfos().get(award.getAwardAmountInfos().size() - 1).getCurrentFundEffectiveDate() == null) {
            valid = false;
            reportError(AWARD_EFFECTIVE_DATE_PROPERTY_NAME, KeyConstants.ERROR_AWARD_EFFECTIVE_DATE);
        }
        if(award.getObligatedTotal().isGreaterThan(new KualiDecimal(0)) &&
                //award.getObligationExpirationDate() == null) {
                award.getAwardAmountInfos().get(award.getAwardAmountInfos().size() - 1).getObligationExpirationDate() == null) {
            valid = false;
            reportError(OBLIGATION_EXPIRATION_DATE_PROPERTY_NAME, KeyConstants.ERROR_OBLIGATION_EXPIRATION_DATE);
        }


        return valid;
    }
    
}
