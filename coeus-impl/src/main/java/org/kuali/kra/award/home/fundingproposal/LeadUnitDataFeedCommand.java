package org.kuali.kra.award.home.fundingproposal;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

/**
 *  Feed Proposal Lead unit into Award  
 */
class LeadUnitDataFeedCommand extends ProposalDataFeedCommandBase {
    LeadUnitDataFeedCommand(Award award, InstitutionalProposal proposal, FundingProposalMergeType mergeType) {
        super(award, proposal, mergeType);
    }

    @Override
    void performDataFeed() {
        if (mergeType != FundingProposalMergeType.NOCHANGE) {
            if (StringUtils.isBlank(award.getLeadUnitNumber())) {
                award.setLeadUnit(proposal.getLeadUnit());
                award.setUnitNumber(proposal.getUnitNumber());
            }
        }
    }
}
