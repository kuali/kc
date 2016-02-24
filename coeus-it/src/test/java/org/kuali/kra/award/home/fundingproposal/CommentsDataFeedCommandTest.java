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
