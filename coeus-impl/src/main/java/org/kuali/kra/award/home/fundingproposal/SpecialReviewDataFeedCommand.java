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
package org.kuali.kra.award.home.fundingproposal;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardCommentFactory;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.award.specialreview.AwardSpecialReviewExemption;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.specialreview.InstitutionalProposalSpecialReview;
import org.kuali.kra.institutionalproposal.specialreview.InstitutionalProposalSpecialReviewExemption;

class SpecialReviewDataFeedCommand extends ProposalDataFeedCommandBase {
    private static final String SPECIAL_REVIEW_COMMENT_PATTERN = "Added Special Review from Proposal Number %s for Special Review #%d and Protocol #%s";
    
    public SpecialReviewDataFeedCommand(Award award, InstitutionalProposal proposal, FundingProposalMergeType mergeType) {
        super(award, proposal, mergeType);
    }

    @Override
    void performDataFeed() {
        if (mergeType != FundingProposalMergeType.NOCHANGE) {
            //unsure why, but without the refresh special reviews were often incorrectly empty
            proposal.refreshReferenceObject("specialReviews");
            for(InstitutionalProposalSpecialReview ipSpecialReview: proposal.getSpecialReviews()) {
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
        copiedSpecialReview.setExpirationDate(ipSpecialReview.getExpirationDate());
        copiedSpecialReview.setProtocolNumber(ipSpecialReview.getProtocolNumber());
        copiedSpecialReview.setSpecialReviewType(ipSpecialReview.getSpecialReviewType());
        copiedSpecialReview.setApprovalType(ipSpecialReview.getApprovalType());
        copiedSpecialReview.setSpecialReviewTypeCode(ipSpecialReview.getSpecialReviewTypeCode());
        for (InstitutionalProposalSpecialReviewExemption ipExempt : ipSpecialReview.getSpecialReviewExemptions()) {
            if (StringUtils.isNotBlank(ipExempt.getExemptionTypeCode())) {
                AwardSpecialReviewExemption newAwardExempt = copiedSpecialReview.createSpecialReviewExemption(ipExempt.getExemptionTypeCode());
                copiedSpecialReview.getSpecialReviewExemptions().add(newAwardExempt);
            }
        }
        copiedSpecialReview.setSpecialReviewNumber(ipSpecialReview.getSpecialReviewNumber());
        
        return copiedSpecialReview;
    }
}
