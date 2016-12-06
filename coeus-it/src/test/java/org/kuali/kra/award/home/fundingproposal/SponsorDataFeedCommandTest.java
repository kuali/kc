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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SponsorDataFeedCommandTest extends BaseDataFeedCommandTest {
    
    private ProposalDataFeedCommandBase command;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        initializeProposal();
    }
    
    @Test
    public void testSponsorFeed() {
        command = new SponsorDataFeedCommand(award, proposal, FundingProposalMergeType.NEWAWARD);
        command.performDataFeed();
        Assert.assertEquals(proposal.getSponsorCode(), award.getSponsorCode());
        Assert.assertEquals(proposal.getSponsor().getSponsorCode(), award.getSponsor().getSponsorCode());
        Assert.assertEquals(proposal.getSponsor().getSponsorName(), award.getSponsor().getSponsorName());
        Assert.assertEquals(proposal.getPrimeSponsorCode(), award.getPrimeSponsorCode());
        Assert.assertEquals(proposal.getPrimeSponsor().getSponsorName(), award.getPrimeSponsor().getSponsorName());
    }
    
    @Test
    public void testReplaceSponsorFeed() {
        command = new SponsorDataFeedCommand(award, proposal, FundingProposalMergeType.REPLACE);
        command.performDataFeed();
        Assert.assertNotSame(proposal.getSponsorCode(), award.getSponsorCode());
        Assert.assertEquals(proposal.getPrimeSponsorCode(), award.getPrimeSponsorCode());
        Assert.assertEquals(proposal.getPrimeSponsor().getSponsorName(), award.getPrimeSponsor().getSponsorName());        
    }
    
    private void initializeProposal() {
        proposal.setSponsorCode("000107");
        proposal.setPrimeSponsorCode("000107");
        proposal.refreshReferenceObject("sponsor");
        proposal.refreshReferenceObject("primeSponsor");
        proposal.setCfdaNumber("abc.123");
        proposal.setNsfSequenceNumber(1);
    }
}
