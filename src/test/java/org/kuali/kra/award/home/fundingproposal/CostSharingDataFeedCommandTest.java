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
import org.kuali.kra.award.commitments.AwardCostShare;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.CostShareType;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalCostShare;
import org.kuali.rice.core.api.util.type.KualiDecimal;

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
        retval.setAmount(new KualiDecimal(200.00));
        retval.setCostSharePercentage(new KualiDecimal(0.05));
        retval.setCostShareType(costShareType);
        retval.setProjectPeriod(fiscalYear);
        retval.setSourceAccount("abc123");
        return retval;
    }
    
    protected AwardCostShare createAwardCostShare(String fiscalYear) {
        AwardCostShare retval = new AwardCostShare();
        retval.setCommitmentAmount(new KualiDecimal(100.00));
        retval.setCostSharePercentage(new KualiDecimal(0.05));
        retval.setCostShareType(costShareType);
        retval.setProjectPeriod(fiscalYear);
        retval.setSource("abc123");
        return retval;
    } 
    
    protected boolean containsCostShare(Award award, InstitutionalProposalCostShare ipCostShare) {
        for (AwardCostShare awardCostShare : award.getAwardCostShares()) {
            if (StringUtils.equals(awardCostShare.getProjectPeriod(), ipCostShare.getProjectPeriod())
                    && StringUtils.equals(awardCostShare.getSource(), ipCostShare.getSourceAccount())
                    && awardCostShare.getAmount().equals(ipCostShare.getAmount())) {
                return true;
            }
        }
        return false;
    }
}
