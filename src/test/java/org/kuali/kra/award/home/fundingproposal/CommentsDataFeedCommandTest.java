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

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalComments;

public class CommentsDataFeedCommandTest extends BaseDataFeedCommandTest {
    private static final String TEST_COMMENT = "Test Comment";
    private ProposalDataFeedCommandBase command;
    private InstitutionalProposalComments ipComments;
    
    @Before
    public void setUp() {
        super.setUp();
        command = new CommentsDataFeedCommand(award, proposal);
        command.setAwardCommentFactory(awardCommentFactory);
    }
    
    @After
    public void tearDown() {
        command = null;
        super.tearDown();
    }
    
    @Test
    public void testAddingCommentWhenNoIpCommentExists() {
        proposal.setProposalComments(null);
        command.performDataFeed();
        AwardComment comment = award.findCommentOfSpecifiedType(awardCommentFactory.createProposalComment());
        Assert.assertNotNull(comment);
        Assert.assertEquals("Funding Proposal Number 1234 was added to Award", comment.getComments());
    }
    
    @Test
    public void testAddingCommentWhenIpCommentExists() {
        ipComments = new InstitutionalProposalComments();
        ipComments.setComments(TEST_COMMENT);
        proposal.setProposalComments(ipComments);
        command.performDataFeed();
        AwardComment comment = award.findCommentOfSpecifiedType(awardCommentFactory.createProposalComment());
        Assert.assertNotNull(comment);
        Assert.assertEquals(TEST_COMMENT, comment.getComments());
    }
}
