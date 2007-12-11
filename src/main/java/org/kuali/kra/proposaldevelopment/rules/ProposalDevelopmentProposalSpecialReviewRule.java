/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.rules;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AddProposalSpecialReviewRule;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalSpecialReviewEvent;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

public class ProposalDevelopmentProposalSpecialReviewRule  extends ResearchDocumentRuleBase implements AddProposalSpecialReviewRule {
    private static final String NEW_PROPOSAL_SPECIAL_REVIEW = "newPropSpecialReview";

    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.rule.AddProposalSpecialReviewRule#processAddProposalSpecialReviewBusinessRules(org.kuali.kra.proposaldevelopment.rule.event.AddProposalSpecialReviewEvent)
     */
    public boolean processAddProposalSpecialReviewBusinessRules(AddProposalSpecialReviewEvent addProposalSpecialReviewEvent) {
        /*
         * Error upon add - 
         * 1.  Select a special review type
         * 2.  Select an approval status
         * 3.  Approval Date should be later than Application Date
         */
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument)addProposalSpecialReviewEvent.getDocument();
        ProposalSpecialReview proposalSpecialReview = addProposalSpecialReviewEvent.getProposalSpecialReview();
        boolean rulePassed = true;
        String errorPath = NEW_PROPOSAL_SPECIAL_REVIEW;
        String[] dateParams = {"Approval Date","Application Date"};

        if(StringUtils.isBlank(proposalSpecialReview.getApprovalTypeCode())){
            rulePassed = false;
            reportError(errorPath+".approvalTypeCode", KeyConstants.ERROR_REQUIRED_SELECT_APPROVAL_STATUS);
        }
        if(StringUtils.isBlank(proposalSpecialReview.getSpecialReviewCode())){
            rulePassed = false;
            reportError(errorPath+".specialReviewCode", KeyConstants.ERROR_REQUIRED_SELECT_SPECIAL_REVIEW_CODE);
        }
        if (proposalSpecialReview.getApplicationDate() !=null && proposalSpecialReview.getApprovalDate() != null && proposalSpecialReview.getApprovalDate().before(proposalSpecialReview.getApplicationDate())) {
            rulePassed = false;
            reportError(errorPath+".approvalDate", KeyConstants.ERROR_APPROVAL_DATE_BEFORE_APPLICATION_DATE_SPECIALREVIEW,dateParams);
        }

        return rulePassed;
    }

}
