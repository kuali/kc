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

import org.kuali.kra.award.commitments.AwardCostShare;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardCommentFactory;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalCostShare;

/**
 * This class handles comment data feeds
 */
class CostSharingDataFeedCommand extends ProposalDataFeedCommandBase {
    private static final String COST_SHARE_COMMENT_PATTERN = "Added Cost Shares from Proposal Number %s";
    
    public CostSharingDataFeedCommand(Award award, InstitutionalProposal proposal) {
        super(award, proposal);
    }

    /**
     * @see org.kuali.kra.award.home.fundingproposal.ProposalDataFeedCommandBase#performDataFeed()
     */
    @Override
    void performDataFeed() {
        int copyCount = 0;
        for(InstitutionalProposalCostShare ipCostShare: proposal.getInstitutionalProposalCostShares()) {
            boolean duplicateFound = false;
            for(AwardCostShare awardCostShare: award.getAwardCostShares()) {
                if(isIdentical(awardCostShare, ipCostShare)) {
                    duplicateFound = true;
                    break;
                }                
            }
            if(!duplicateFound) {
                award.add(copyCostShare(ipCostShare));
                copyCount++;
            }
        }
        if(copyCount > 0) {
            addCostShareComment(award, proposal);
        }
    }

    private void addCostShareComment(Award award, InstitutionalProposal proposal) {
        String newComment = String.format(COST_SHARE_COMMENT_PATTERN, proposal.getProposalNumber());
        appendComments(findOrCreateCommentOfSpecifiedType(new AwardCommentFactory().createSpecialReviewComment()), newComment);
    }
    
    /**
     * Copies an InstitutionalProposalCostShare to an AwardCostShare
     * @param ipCostShare
     * @return
     */
    private AwardCostShare copyCostShare(InstitutionalProposalCostShare ipCostShare) {
        AwardCostShare awardCostShare = new AwardCostShare();
        awardCostShare.setCommitmentAmount(ipCostShare.getAmount());
        awardCostShare.setCostSharePercentage(ipCostShare.getCostSharePercentage());
        awardCostShare.setCostShareType(ipCostShare.getCostShareType());
        awardCostShare.setCostShareTypeCode(ipCostShare.getCostShareTypeCode());
        awardCostShare.setCommitmentAmount(ipCostShare.getAmount());
        awardCostShare.setSource(ipCostShare.getSourceAccount());
        awardCostShare.setFiscalYear(ipCostShare.getFiscalYear());
        
        return awardCostShare;
    }
    
    private boolean isIdentical(AwardCostShare awardCostShare, InstitutionalProposalCostShare ipCostShare) {
        return awardCostShare.getCostShareTypeCode().equals(ipCostShare.getCostShareTypeCode()) && 
                awardCostShare.getFiscalYear().equals(ipCostShare.getFiscalYear()) &&
                awardCostShare.getCommitmentAmount().equals(ipCostShare.getAmount()) &&
                awardCostShare.getSource().equals(ipCostShare.getSourceAccount());
    }
}
