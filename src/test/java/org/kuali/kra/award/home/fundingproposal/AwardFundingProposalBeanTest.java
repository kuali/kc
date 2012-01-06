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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.home.AwardServiceImpl;
import org.kuali.kra.bo.NsfCode;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.proposaldevelopment.bo.ActivityType;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public class AwardFundingProposalBeanTest extends KcUnitTestBase {
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
        instProp.setNsfCode("0A");        
        instProp.setActivityType(new ActivityType());
        instProp.setSponsor(testSponsor);
        instProp.setPrimeSponsor(testSponsor);
        instProp.setNsfCodeBo(new NsfCode());
        
        bean = new AwardFundingProposalBean() {
            Award getAward() {
                return award3;
            }
            AwardService getAwardService() {
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
        List<Award> results = new ArrayList<Award>();
        results.add(award2);
        awardService.setResults(results);
        
        Assert.assertEquals(1, bean.getAllAwardsForAwardNumber().size());
    }
    
    @Test
    public void testGettingAllAwardsForAwardNumber_twoFound() {
        List<Award> cannedResults = new ArrayList<Award>();
        cannedResults.add(award3);
        cannedResults.add(award1);
        
        awardService.setResults(cannedResults);
        
        List<Award> actualResults = bean.getAllAwardsForAwardNumber();
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
        assertEquals(instProp.getNsfCode(), award3.getNsfCode());
    }
    
    @Test
    public void testPerformDataFeedsNoChange() {
        bean.setMergeType(FundingProposalMergeType.NOCHANGE);
        //hack to avoid trying to build custom data
        award3.getAwardCustomDataList().add(new AwardCustomData());
        bean.performDataFeeds(award3, instProp);
        assertFalse(StringUtils.equals(instProp.getTitle(), award3.getTitle()));
        assertFalse(StringUtils.equals(instProp.getActivityTypeCode(), award3.getActivityTypeCode()));
        assertFalse(StringUtils.equals(instProp.getSponsorCode(), award3.getSponsorCode()));
        assertFalse(StringUtils.equals(instProp.getPrimeSponsorCode(), award3.getPrimeSponsorCode()));
        assertFalse(StringUtils.equals(instProp.getCfdaNumber(), award3.getCfdaNumber()));
        assertFalse(StringUtils.equals(instProp.getNsfCode(), award3.getNsfCode()));
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
        
        public List<Award> findAwardsForAwardNumber(String awardNumber) {
            Set<Award> orderedSet = new TreeSet<Award>(new Comparator<Award>() {
                public int compare(Award a1, Award a2) {
                    return a1.getSequenceNumber().compareTo(a2.getSequenceNumber());
                }
                
            });
            orderedSet.addAll(results);
            return new ArrayList<Award>(orderedSet);
        }
        
    };
}
