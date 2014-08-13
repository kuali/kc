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
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

class SponsorDataFeedCommand extends ProposalDataFeedCommandBase {

    public SponsorDataFeedCommand(Award award, InstitutionalProposal proposal, FundingProposalMergeType mergeType) {
        super(award, proposal, mergeType);
    }

    @Override
    void performDataFeed() {
        if (mergeType == FundingProposalMergeType.NEWAWARD) {
            award.setSponsor(proposal.getSponsor());
            award.setSponsorCode(proposal.getSponsorCode());
        } 
        if (mergeType == FundingProposalMergeType.NEWAWARD 
                || mergeType == FundingProposalMergeType.REPLACE) {
            award.setPrimeSponsor(proposal.getPrimeSponsor());
            award.setPrimeSponsorCode(proposal.getPrimeSponsorCode());
            award.setCfdaNumber(proposal.getCfdaNumber());
            award.setNsfCode(proposal.getNsfCode());
        }
    }

}
