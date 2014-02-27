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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.award.home.AwardCommentFactory;
import org.kuali.kra.bo.CommentType;

public class CommentsDataFeedCommandTest extends BaseDataFeedCommandTest {
    private static final String TEST_COMMENT = "Test Comment";
    private ProposalDataFeedCommandBase command;
    MockAwardCommentFactory awardCommentFactory;
    
    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        command = new CommentsDataFeedCommand(award, proposal, FundingProposalMergeType.REPLACE);
        awardCommentFactory = new MockAwardCommentFactory();
    }
    
    @After
    @Override
    public void tearDown() throws Exception {
        command = null;
        super.tearDown();
    }
    
    @Test
    public void testAddingComment() {
        proposal.getDeliveryComment().setComments(TEST_COMMENT);
        command.performDataFeed();
        AwardComment comment = award.findCommentOfSpecifiedType(awardCommentFactory.createProposalComment());
        Assert.assertNotNull(comment);
        Assert.assertEquals(TEST_COMMENT, comment.getComments());
    }

    class MockAwardCommentFactory extends AwardCommentFactory {
        @Override
        public CommentType findCommentType(String commentTypeCode) {
            CommentType commentType = new CommentType();
            commentType.setCommentTypeCode(commentTypeCode);
            commentType.setDescription("mock description");
            return commentType;
        }
    };
}
