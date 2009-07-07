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
package org.kuali.kra.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.query.Criteria;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.AwardNumberService;
import org.kuali.kra.award.AwardNumberServiceImpl;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.dao.KraLookupDao;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.service.SequenceAccessorService;

public class AwardNumberServiceTest {
    
    private Mockery context = new JUnit4Mockery();
    AwardNumberServiceImpl awardNumberServiceImpl;
    List<Award> awardList;
    Award rootAward;
    private long SEQUENCE_NUMBER = 1234;

    @Before
    public void setUp() throws Exception {
        awardNumberServiceImpl = new AwardNumberServiceImpl();
        rootAward = new Award();
        rootAward.setAwardNumber("000001-00001");
        Award node1 = new Award();
        node1.setAwardNumber("000001-00002");
        Award node2 = new Award();
        node2.setAwardNumber("000001-00003");
        awardList = new ArrayList<Award>();
        awardList.add(node1);
        awardList.add(node2);
        
    }

    @After
    public void tearDown() throws Exception {
        
    }

    @Test
    /**
     * This method checks that:
     *  - the number is 10 characters long
     *  - the first 6 characters are digits
     *  - the last 4 characters are "-001"
     */
    public final void testGetNextAwardNumber() {
        AwardNumberService awardNumberService = createAwardNumberService();
        String awardNumber = awardNumberService.getNextAwardNumber();
        assertNotNull(awardNumber);
        assertEquals(12, awardNumber.length());
        String first6Chars = awardNumber.substring(0, 6);
        assertTrue(StringUtils.isNumeric(first6Chars));
        String last6chars = awardNumber.substring(6, 12);
        assertEquals("-00001", last6chars);
    }
    
    @Test
    public final void testGenerateNextNodeNumber() {
        final KraLookupDao MOCKED_KRA_LOOKUP_DAO;
        MOCKED_KRA_LOOKUP_DAO = context.mock(KraLookupDao.class);
        context.checking(new Expectations() {{
            one(MOCKED_KRA_LOOKUP_DAO).findCollectionUsingWildCard(Award.class, "awardNumber", "000001%", true); 
            will(returnValue(awardList));
        }});
        awardNumberServiceImpl.setKraLookupDao(MOCKED_KRA_LOOKUP_DAO);
        Assert.assertTrue(awardNumberServiceImpl.getNextAwardNumberInHierarchy(rootAward.getAwardNumber()).equals("000001-00004"));
    }

    /**
     * This method creates an instance of AwardNumberService, using a mock SequenceAccessorService which
     * always returns SEQUENCE_NUMBER.
     * @return
     */
    private AwardNumberService createAwardNumberService() {   
        final SequenceAccessorService sequenceAccessorService = context.mock(SequenceAccessorService.class);
        context.checking(new Expectations() {
            {
                one(sequenceAccessorService).getNextAvailableSequenceNumber(Constants.AWARD_SEQUENCE_AWARD_NUMBER);
                will(returnValue(SEQUENCE_NUMBER));
            }
        });
        AwardNumberServiceImpl awardNumberService = new AwardNumberServiceImpl();
        awardNumberService.setSequenceAccessorService(sequenceAccessorService);
        return awardNumberService;
    }
}