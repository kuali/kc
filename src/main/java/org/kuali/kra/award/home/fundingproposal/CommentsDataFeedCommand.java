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
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.award.home.AwardCommentFactory;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalComments;

public class CommentsDataFeedCommand extends ProposalDataFeedCommandBase {
    private static final String FUNDING_PROPOSAL_ADDED_MSG_PATTERN = "Funding Proposal Number %s was added to Award";
    
    public CommentsDataFeedCommand(Award award, InstitutionalProposal proposal) {
        super(award, proposal);
    }

    /**
     * @see org.kuali.kra.award.home.fundingproposal.ProposalDataFeedCommandBase#performDataFeed()
     */
    void performDataFeed() {
        InstitutionalProposalComments proposalComments = proposal.getProposalComments();
        AwardComment proposalComment = findOrCreateCommentOfSpecifiedType(awardCommentFactory.createProposalComment());
        if(proposalComments != null) {
            appendComments(proposalComment, proposalComments.getComments());
        } else {
            appendComments(proposalComment, String.format(FUNDING_PROPOSAL_ADDED_MSG_PATTERN, proposal.getProposalNumber()));
        }
    }
}
