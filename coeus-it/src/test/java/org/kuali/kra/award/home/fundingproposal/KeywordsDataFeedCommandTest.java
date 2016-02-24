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
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.keywords.AwardScienceKeyword;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalScienceKeyword;
import static org.junit.Assert.*;
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
