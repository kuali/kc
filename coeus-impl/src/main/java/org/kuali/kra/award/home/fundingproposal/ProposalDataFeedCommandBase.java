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
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

/**
 * This class is the base class for all InstitutionalProposal-to-Award data feed command classes 
 */
abstract class ProposalDataFeedCommandBase {
    Award award;
    InstitutionalProposal proposal;
    FundingProposalMergeType mergeType;
    
    ProposalDataFeedCommandBase(Award award, InstitutionalProposal proposal, FundingProposalMergeType mergeType) {
        this.award = award;
        this.proposal = proposal;
        this.mergeType = mergeType;
    }

    /**
     * @param comment
     * @param newComments
     */
    void appendComments(AwardComment comment, String newComments) {
        if (!StringUtils.isEmpty(newComments)) {
            String comments = comment.getComments();
            comment.setComments(StringUtils.isEmpty(comments) ? newComments : String.format("%s\n%s", comments, newComments));
        }
    }
    
    /**
     * This method finds an AwardComment of a specified type, or creates a new one of that type using the template
     * @param template
     * @return
     */
    AwardComment findOrCreateCommentOfSpecifiedType(AwardComment template) {
        AwardComment comment = award.findCommentOfSpecifiedType(template);
        if(comment == null) {
            comment = template;
            award.add(comment);
        }
        return comment;
    }

    /**
     * This method performs the data feed
     */
    abstract void performDataFeed();
}
