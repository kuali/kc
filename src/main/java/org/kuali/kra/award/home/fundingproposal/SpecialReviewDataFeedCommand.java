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
package org.kuali.kra.award.home.fundingproposal;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardCommentFactory;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalSpecialReview;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalSpecialReviewExemption;

class SpecialReviewDataFeedCommand extends ProposalDataFeedCommandBase {
    private static final String SPECIAL_REVIEW_COMMENT_PATTERN = "Added Special Review from Proposal Number %s for Special Review #%d and Protocol #%s";
    
    public SpecialReviewDataFeedCommand(Award award, InstitutionalProposal proposal) {
        super(award, proposal);
    }

    @Override
    void performDataFeed() {
        for(InstitutionalProposalSpecialReview ipSpecialReview: proposal.getSpecialReviews()) {
            boolean duplicateFound = false;
            for(AwardSpecialReview awardSpecialReview: award.getSpecialReviews()) {
                if(isIdentical(awardSpecialReview, ipSpecialReview)) {
                    duplicateFound = true;
                    break;
                }                
            }
            if(!duplicateFound) {
                copySpecialReview(award, proposal, ipSpecialReview);
            }
        }
    }
    
    private void addSpecialReviewComment(Award award, InstitutionalProposal proposal, AwardSpecialReview copiedSpecialReview) {
        String newComment = String.format(SPECIAL_REVIEW_COMMENT_PATTERN, proposal.getProposalNumber(), 
                                            copiedSpecialReview.getSpecialReviewNumber(), copiedSpecialReview.getProtocolNumber());
        appendComments(findOrCreateCommentOfSpecifiedType(new AwardCommentFactory().createSpecialReviewComment()), newComment);
    }
    
    private void copySpecialReview(Award award, InstitutionalProposal proposal, InstitutionalProposalSpecialReview ipSpecialReview) {
        AwardSpecialReview copiedSpecialReview = copySpecialReview(ipSpecialReview);
        award.add(copiedSpecialReview);
        addSpecialReviewComment(award, proposal, copiedSpecialReview);
    }
    
    private boolean isIdentical(AwardSpecialReview awardSpecialReview, InstitutionalProposalSpecialReview ipSpecialReview) {
        String pattern = "%s:%s";
        String thisSignature = String.format(pattern, awardSpecialReview.getSpecialReviewCode(), awardSpecialReview.getProtocolNumber());
        String thatSignature = String.format(pattern, ipSpecialReview.getSpecialReviewCode(), ipSpecialReview.getProtocolNumber());
        
        return thisSignature.equalsIgnoreCase(thatSignature);
    }
    
    /**
     * This method copies a InstitutionalProposalSpecialReview into an AwardSpecialReview
     * @param ipSpecialReview
     * @return AwardSpecialReview
     */
    private AwardSpecialReview copySpecialReview(InstitutionalProposalSpecialReview ipSpecialReview) {
        AwardSpecialReview copiedSpecialReview = new AwardSpecialReview();
        copiedSpecialReview.setApplicationDate(ipSpecialReview.getApplicationDate());
        copiedSpecialReview.setApprovalDate(ipSpecialReview.getApprovalDate());
        copiedSpecialReview.setApprovalTypeCode(ipSpecialReview.getApprovalTypeCode());
        copiedSpecialReview.setComments(ipSpecialReview.getComments());
        copiedSpecialReview.setExemptionTypeCodes(ipSpecialReview.getExemptionTypeCodes());
        copiedSpecialReview.setExemptionTypes(ipSpecialReview.getExemptionTypes());
        copiedSpecialReview.setExpirationDate(ipSpecialReview.getExpirationDate());
        copiedSpecialReview.setProtocolNumber(ipSpecialReview.getProtocolNumber());
        copiedSpecialReview.setSpecialReview(ipSpecialReview.getSpecialReview());
        copiedSpecialReview.setSpecialReviewApprovalType(ipSpecialReview.getSpecialReviewApprovalType());
        copiedSpecialReview.setSpecialReviewCode(ipSpecialReview.getSpecialReviewCode());
        for(InstitutionalProposalSpecialReviewExemption ipExempt: ipSpecialReview.getSpecialReviewExemptions()) {
            copiedSpecialReview.addSpecialReviewExemption(ipExempt.getExemptionTypeCode());
        }
        copiedSpecialReview.setSpecialReviewNumber(ipSpecialReview.getSpecialReviewNumber());
        copiedSpecialReview.setValidSpecialReviewApproval(ipSpecialReview.getValidSpecialReviewApproval());
        
        return copiedSpecialReview;
    }
}
