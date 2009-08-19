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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.award.home.AwardCommentFactory;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

/**
 * This class is the base class for all InstitutionalProposal-to-Award data feed command classes 
 */
abstract class ProposalDataFeedCommandBase {
    Award award;
    InstitutionalProposal proposal;
    AwardCommentFactory awardCommentFactory;
    
    ProposalDataFeedCommandBase(Award award, InstitutionalProposal proposal) {
        this.award = award;
        this.proposal = proposal;
        awardCommentFactory = new AwardCommentFactory();
    }

    /**
     * @param comment
     * @param newComments
     */
    void appendComments(AwardComment comment, String newComments) {
        String comments = comment.getComments();
        comment.setComments(StringUtils.isEmpty(comments) ? newComments : String.format("%s\n%s", comments, newComments));
    }
    
    /**
     * @param award
     * @return
     */
    AwardComment findGeneralComment() {
        return findOrCreateCommentOfSpecifiedType(new AwardCommentFactory().createGeneralComment());
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
    
    /**
     * Sets the awardCommentFactory attribute value.
     * @param awardCommentFactory The awardCommentFactory to set.
     */
    void setAwardCommentFactory(AwardCommentFactory awardCommentFactory) {
        this.awardCommentFactory = awardCommentFactory;
    }
}
