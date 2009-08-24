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
package org.kuali.kra.award.rule;

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.award.rule.event.AwardCommentsRuleEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * This class implements rules for checking Award comments entered in text boxes.
 */
public class AwardCommentsRuleImpl extends ResearchDocumentRuleBase implements AwardCommentsRule {
    private static final Integer MAX_COMMENT_LENGTH = 5000;

    public boolean processAwardCommentsBusinessRules(AwardCommentsRuleEvent awardCommentsRuleEvent) {
        boolean valid = true;

        // Out of the comment types defined in Constants, these are the ones we actually use
        valid &= checkAwardComment(awardCommentsRuleEvent, Constants.PREAWARD_SPONSOR_AUTHORIZATION_COMMENT_TYPE_CODE, "awardPreAwardSponsorAuthorizationComment.comments");
        valid &= checkAwardComment(awardCommentsRuleEvent, Constants.PREAWARD_INSTITUTIONAL_AUTHORIZATION_COMMENT_TYPE_CODE, "awardPreAwardInstitutionalAuthorizationComment.comments");
        valid &= checkAwardComment(awardCommentsRuleEvent, Constants.COST_SHARE_COMMENT_TYPE_CODE, "awardCostShareComment.comments");
        valid &= checkAwardComment(awardCommentsRuleEvent, Constants.FANDA_RATE_COMMENT_TYPE_CODE, "awardFandaRateComment.comments");
        valid &= checkAwardComment(awardCommentsRuleEvent, Constants.BENEFITS_RATES_COMMENT_TYPE_CODE, "awardBenefitsRateComment.comments");
        valid &= checkAwardComment(awardCommentsRuleEvent, Constants.PAYMENT_AND_INVOICES_COMMENT_TYPE_CODE, "awardPaymentAndInvoiceRequirementsComments.comments");
        
        return valid;
    }

    /**
     * This method looks up the comment for a given type code in the AwardDocument and validates it.
     * @param awardCommentsRuleEvent
     * @param commentTypeCode
     * @param errorKey
     * @return
     */
    private boolean checkAwardComment(AwardCommentsRuleEvent awardCommentsRuleEvent, String commentTypeCode, String errorKey) {
        boolean valid = true;
        
        AwardDocument awardDocument = (AwardDocument)awardCommentsRuleEvent.getDocument();
        Award award = awardDocument.getAward();
        
        AwardComment awardComment = award.findCommentOfSpecifiedType(commentTypeCode);
        if (awardComment != null) {
            String commentString = awardComment.getComments();
            if (!isAwardCommentValid(commentString)) {
                String fullErrorPath = awardCommentsRuleEvent.getErrorPathPrefix() + "." + errorKey;
                reportError(fullErrorPath, KeyConstants.ERROR_MAXLENGTH, "Comment", MAX_COMMENT_LENGTH.toString());
                valid = false;
            }
        }
        
        return valid;
    }
    
    private boolean isAwardCommentValid(String comment) {
        return comment==null || comment.length()<=MAX_COMMENT_LENGTH;
    }
}