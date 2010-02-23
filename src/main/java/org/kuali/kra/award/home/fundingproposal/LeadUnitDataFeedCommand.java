package org.kuali.kra.award.home.fundingproposal;

import org.apache.commons.lang.StringUtils;
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
        if (StringUtils.isBlank(award.getLeadUnitNumber())) {
            award.setLeadUnit(proposal.getLeadUnit());
            award.setUnitNumber(proposal.getUnitNumber());
        }
    }
}
