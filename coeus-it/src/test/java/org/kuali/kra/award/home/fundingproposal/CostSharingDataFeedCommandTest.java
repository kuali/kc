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
import org.kuali.kra.award.commitments.AwardCostShare;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.CostShareType;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalCostShare;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import static org.junit.Assert.*;
public class CostSharingDataFeedCommandTest extends BaseDataFeedCommandTest {

    private CostShareType costShareType;
    private InstitutionalProposalCostShare ipCostShare1;
    private InstitutionalProposalCostShare ipCostShare2;
    private AwardCostShare awardCostShare1;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        costShareType = new CostShareType();
        costShareType.setCostShareTypeCode(1);
        ipCostShare1 = createIpCostShare("2010");
        ipCostShare2 = createIpCostShare("2011");
        awardCostShare1 = createAwardCostShare("2010");
        proposal.add(ipCostShare1);
        proposal.add(ipCostShare2);
    }
    
    @Test
    public void testAddCostShares() {
        CostSharingDataFeedCommand command = new CostSharingDataFeedCommand(award, proposal, FundingProposalMergeType.NEWAWARD);
        command.performDataFeed();
        assertTrue(award.getAwardCostShares().size() == proposal.getInstitutionalProposalCostShares().size());
        assertTrue(containsCostShare(award, ipCostShare1));
        assertTrue(containsCostShare(award, ipCostShare2));
    }
    
    @Test
    public void testMergeCostShares() {
        CostSharingDataFeedCommand command = new CostSharingDataFeedCommand(award, proposal, FundingProposalMergeType.MERGE);
        award.add(awardCostShare1);
        command.performDataFeed();
        assertTrue(award.getAwardCostShares().size() == proposal.getInstitutionalProposalCostShares().size()+1);
        assertTrue(containsCostShare(award, ipCostShare1));
        assertTrue(containsCostShare(award, ipCostShare2));
    }
    
    protected InstitutionalProposalCostShare createIpCostShare(String fiscalYear) {
        InstitutionalProposalCostShare retval = new InstitutionalProposalCostShare();
        retval.setAmount(new ScaleTwoDecimal(200.00));
        retval.setCostSharePercentage(new ScaleTwoDecimal(0.05));
        retval.setCostShareType(costShareType);
        retval.setProjectPeriod(fiscalYear);
        retval.setSourceAccount("abc123");
        retval.setUnitNumber("BL-BL");
        return retval;
    }
    
    protected AwardCostShare createAwardCostShare(String fiscalYear) {
        AwardCostShare retval = new AwardCostShare();
        retval.setCommitmentAmount(new ScaleTwoDecimal(100.00));
        retval.setCostSharePercentage(new ScaleTwoDecimal(0.05));
        retval.setCostShareType(costShareType);
        retval.setProjectPeriod(fiscalYear);
        retval.setSource("abc123");
        retval.setUnitNumber("BL-BL");
        return retval;
    } 
    
    protected boolean containsCostShare(Award award, InstitutionalProposalCostShare ipCostShare) {
        for (AwardCostShare awardCostShare : award.getAwardCostShares()) {
            if (StringUtils.equals(awardCostShare.getProjectPeriod(), ipCostShare.getProjectPeriod())
                    && StringUtils.equals(awardCostShare.getSource(), ipCostShare.getSourceAccount())
                    && awardCostShare.getAmount().equals(ipCostShare.getAmount())
                    && awardCostShare.getUnitNumber().equals(ipCostShare.getUnitNumber())) {
                return true;
            }
        }
        return false;
    }
}
