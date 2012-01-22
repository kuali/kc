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

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.keywords.AwardScienceKeyword;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalScienceKeyword;

public class KeywordsDataFeedCommandTest extends BaseDataFeedCommandTest {

    private InstitutionalProposalScienceKeyword ipKey1;
    private InstitutionalProposalScienceKeyword ipKey2;
    private AwardScienceKeyword awardKey1;
    private AwardScienceKeyword awardKey2;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        ipKey1 = new InstitutionalProposalScienceKeyword();
        ipKey1.setScienceKeywordCode("1");
        ipKey2 = new InstitutionalProposalScienceKeyword();
        ipKey2.setScienceKeywordCode("2");
        proposal.getKeywords().add(ipKey1);
        proposal.getKeywords().add(ipKey2);
        awardKey1 = new AwardScienceKeyword();
        awardKey1.setScienceKeywordCode("1");
        awardKey2 = new AwardScienceKeyword();
        awardKey2.setScienceKeywordCode("3");
    }
    
    @Test
    public void testAddRates() {
        KeywordsDataFeedCommand command = new KeywordsDataFeedCommand(award, proposal, FundingProposalMergeType.NEWAWARD);
        command.performDataFeed();
        assertTrue(award.getKeywords().size() == proposal.getKeywords().size());
        assertTrue(containsKeyword(award, ipKey1));
        assertTrue(containsKeyword(award, ipKey2));
    }
    
    @Test
    public void testMergeRates() {
        KeywordsDataFeedCommand command = new KeywordsDataFeedCommand(award, proposal, FundingProposalMergeType.MERGE);
        award.getKeywords().add(awardKey2);
        command.performDataFeed();
        assertTrue(award.getKeywords().size() == proposal.getKeywords().size()+1);
        assertTrue(containsKeyword(award, ipKey1));
        assertTrue(containsKeyword(award, ipKey2));
    }
    
    @Test
    public void testDuplicates() {
        KeywordsDataFeedCommand command = new KeywordsDataFeedCommand(award, proposal, FundingProposalMergeType.MERGE);
        award.getKeywords().add(awardKey1);
        command.performDataFeed();
        assertTrue(award.getKeywords().size() == proposal.getKeywords().size());
        //both ip keyword codes should still be in the award
        assertTrue(containsKeyword(award, ipKey1));
        assertTrue(containsKeyword(award, ipKey2));
        
    }
    
    protected boolean containsKeyword(Award award, InstitutionalProposalScienceKeyword ipKey) {
        for (AwardScienceKeyword awardKey : award.getKeywords()) {
            if (StringUtils.equals(awardKey.getScienceKeywordCode(), ipKey.getScienceKeywordCode())) {
                return true;
            }
        }
        return false;
    }
}
