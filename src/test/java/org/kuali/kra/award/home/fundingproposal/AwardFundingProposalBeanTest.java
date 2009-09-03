/*
 * Copyright 2006-2008 The Kuali Foundation
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
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.home.AwardServiceImpl;
import org.kuali.kra.service.BusinessObjectServiceAdapter;
import org.kuali.rice.kns.service.BusinessObjectService;

public class AwardFundingProposalBeanTest {
    private AwardFundingProposalBean bean;
    
    private Award award1;
    private Award award2;
    private Award award3;
    
    final MockAwardService awardService = new MockAwardService();
    
    @SuppressWarnings("serial")
    @Before
    public void setUp() {
        award1 = createAward(1L, "100001-001", 1);
        award2 = createAward(2L, "100002-001", 1);
        award3 = createAward(3L, "100001-001", 2);
        
        
        
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
