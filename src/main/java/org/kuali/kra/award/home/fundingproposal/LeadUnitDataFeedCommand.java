package org.kuali.kra.award.home.fundingproposal;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

/**
 *  Feed Proposal Lead unit into Award  
 */
class LeadUnitDataFeedCommand extends ProposalDataFeedCommandBase {
    LeadUnitDataFeedCommand(Award award, InstitutionalProposal proposal) {
        super(award, proposal);
    }

    @Override
    void performDataFeed() {
        award.setLeadUnit(proposal.getLeadUnit());
        award.setUnitNumber(proposal.getUnitNumber());
    }
}
