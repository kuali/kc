/*
 * Copyright 2005-2014 The Kuali Foundation
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
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalComment;

/**
 * This class will populate Award comments based on their corresponding Institutional Proposal comments.
 * The mapping is based on the Comment Type code, even though some of the comment names don't quite match.
 */
public class CommentsDataFeedCommand extends ProposalDataFeedCommandBase {
    
    private static final String FUNDING_PROPOSAL_ADDED_MSG_PATTERN = "Funding Proposal Number %s was added to Award";
    
    public CommentsDataFeedCommand(Award award, InstitutionalProposal proposal, FundingProposalMergeType mergeType) {
        super(award, proposal, mergeType);
    }

    @Override
    void performDataFeed() {
        if (mergeType != FundingProposalMergeType.NOCHANGE) {
            feedProposalComment();
            feedProposalSummaryComment();
            feedFandARateComment();
            feedCostShareComment();
            feedProposalIPReviewComment();
        }
    }
    
    void feedProposalComment() {
        InstitutionalProposalComment proposalDeliveryComment = proposal.getDeliveryComment();
        AwardComment awardProposalComment = award.getawardProposalComments();
        if (proposalDeliveryComment != null) {
            appendComments(awardProposalComment, proposalDeliveryComment.getComments());
        } else {
            appendComments(awardProposalComment, String.format(FUNDING_PROPOSAL_ADDED_MSG_PATTERN, proposal.getProposalNumber()));
        }
    }
    
    void feedProposalSummaryComment() {
        InstitutionalProposalComment proposalSummaryComment = proposal.getSummaryComment();
        if (proposalSummaryComment != null) {
            AwardComment awardProposalSummaryComment = award.getawardProposalSummary();
            appendComments(awardProposalSummaryComment, proposalSummaryComment.getComments());
        }
    }
    
    void feedFandARateComment() {
        InstitutionalProposalComment proposalFandAComment = proposal.getUnrecoveredFandAComment();
        if (proposalFandAComment != null) {
            AwardComment awardFandAComment = award.getAwardFandaRateComment();
            appendComments(awardFandAComment, proposalFandAComment.getComments());
        }
    }
    
    void feedCostShareComment() {
        InstitutionalProposalComment proposalCostShareComment = proposal.getCostShareComment();
        if (proposalCostShareComment != null) {
            AwardComment awardCostShareComment = award.getAwardCostShareComment();
            appendComments(awardCostShareComment, proposalCostShareComment.getComments());
        }
    }
    
    void feedProposalIPReviewComment() {
        InstitutionalProposalComment proposalGeneralComment = proposal.getGeneralComment();
        if (proposalGeneralComment != null) {
            AwardComment awardIpReviewComment = award.getAwardProposalIPReviewComment();
            appendComments(awardIpReviewComment, proposalGeneralComment.getComments());
        }
    }
}
