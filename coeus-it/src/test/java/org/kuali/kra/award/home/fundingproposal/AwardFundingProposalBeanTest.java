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
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.type.ActivityType;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.home.AwardServiceImpl;
import org.kuali.kra.bo.NsfCode;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.util.*;
import static org.junit.Assert.*;
public class AwardFundingProposalBeanTest extends KcIntegrationTestBase {
    private AwardFundingProposalBean bean;
    
    private Award award1;
    private Award award2;
    private Award award3;
    
    private InstitutionalProposal instProp;
    
    final MockAwardService awardService = new MockAwardService();
    
    @SuppressWarnings("serial")
    @Before
    public void setUp() {
        award1 = createAward(1L, "100001-001", 1);
        award2 = createAward(2L, "100002-001", 1);
        award3 = createAward(3L, "100001-001", 2);
        
        Sponsor testSponsor = new Sponsor();
        testSponsor.setSponsorCode("testsp");
       
        instProp = new InstitutionalProposal();
        instProp.setProposalNumber(InstitutionalProposal.PROPOSAL_NUMBER_TEST_DEFAULT_STRING);
        instProp.setTitle("Test Title");
        instProp.setActivityTypeCode("RES");
        instProp.setCfdaNumber("11.111a");
        instProp.setNsfSequenceNumber(2);
        instProp.setActivityType(new ActivityType());
        instProp.setSponsor(testSponsor);
        instProp.setPrimeSponsor(testSponsor);
        instProp.setNsfCodeBo(new NsfCode());
        
        bean = new AwardFundingProposalBean() {
            @Override Award getAward() {
                return award3;
            }
            @Override public AwardService getAwardService() {
                return awardService;
            }
        };        
    }

    @After
    public void tearDown() {
        bean = null;
        award1 = null;
        award2 = null;
        award3 = null;
    }

    @Test
    public void testGettingAllAwardsForAwardNumber_OneFound() {
        List<Award> results = new ArrayList<>();
        results.add(award2);
        awardService.setResults(results);
        
        Assert.assertEquals(1, bean.getAllAwardsForAwardNumber(award2).size());
    }
    
    @Test
    public void testGettingAllAwardsForAwardNumber_twoFound() {
        List<Award> cannedResults = new ArrayList<>();
        cannedResults.add(award3);
        cannedResults.add(award1);
        
        awardService.setResults(cannedResults);
        
        List<Award> actualResults = bean.getAllAwardsForAwardNumber(award1);
        Assert.assertEquals(2, actualResults.size());
        Assert.assertEquals(1, actualResults.get(0).getSequenceNumber().intValue());
        Assert.assertEquals(2, actualResults.get(1).getSequenceNumber().intValue());
    }
    
    /**
     * Much of the functionality here is tested in respective feed commands.
     * This is just to ensure some of the basics are handled correctly but
     * more could be easily added.
     */
    @Test
    public void testPerformDataFeedsNewAward() {
        bean.setMergeType(FundingProposalMergeType.NEWAWARD);
        //hack to avoid trying to build custom data
        award3.getAwardCustomDataList().add(new AwardCustomData());
        bean.performDataFeeds(award3, instProp);
        assertEquals(instProp.getTitle(), award3.getTitle());
        assertEquals(instProp.getActivityTypeCode(), award3.getActivityTypeCode());
        assertEquals(instProp.getSponsorCode(), award3.getSponsorCode());
        assertEquals(instProp.getPrimeSponsorCode(), award3.getPrimeSponsorCode());
        assertEquals(instProp.getCfdaNumber(), award3.getCfdaNumber());
        assertEquals(instProp.getNsfSequenceNumber(), award3.getNsfSequenceNumber());
    }
    
    @Test
    public void testPerformDataFeedsNoChange() {
        bean.setMergeType(FundingProposalMergeType.NOCHANGE);
        //hack to avoid trying to build custom data
        award3.getAwardCustomDataList().add(new AwardCustomData());
        bean.performDataFeeds(award3, instProp);
        assertNotEquals(instProp.getTitle(), award3.getTitle());
        assertNotEquals(instProp.getActivityTypeCode(), award3.getActivityTypeCode());
        assertNotEquals(instProp.getSponsorCode(), award3.getSponsorCode());
        assertNotEquals(instProp.getPrimeSponsorCode(), award3.getPrimeSponsorCode());
        assertNotEquals(instProp.getCfdaNumber(), award3.getCfdaNumber());
        assertNotEquals(instProp.getNsfSequenceNumber(), award3.getNsfSequenceNumber());
    }
    
    
    private Award createAward(Long id, String awardNumber, Integer sequenceNumber) {
        Award award = new Award();
        award.setAwardId(id);
        award.setAwardNumber(awardNumber);
        award.setSequenceNumber(sequenceNumber);
        return award;
    }
    
    class MockAwardService extends AwardServiceImpl {
        private List<Award> results;
        
        public void setResults(List<Award> results) { this.results = results; }

        @Override public List<Award> findAwardsForAwardNumber(String awardNumber) {
            Set<Award> orderedSet = new TreeSet<>(Comparator.comparing(Award::getSequenceNumber));
            orderedSet.addAll(results);
            return new ArrayList<>(orderedSet);
        }
        
    }
}
