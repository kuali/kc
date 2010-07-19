/*
 * Copyright 2005-2010 The Kuali Foundation
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
import org.junit.Before;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardCommentFactory;
import org.kuali.kra.bo.CommentType;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public abstract class BaseDataFeedCommandTest extends KcUnitTestBase {
    Award award;
    InstitutionalProposal proposal;
    MockAwardCommentFactory awardCommentFactory; 
    
    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        award = new Award();
        proposal = new InstitutionalProposal();
        proposal.setProposalNumber("1234");
        awardCommentFactory = new MockAwardCommentFactory();
    }
    
    @After
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        award = null;
        proposal = null;
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
