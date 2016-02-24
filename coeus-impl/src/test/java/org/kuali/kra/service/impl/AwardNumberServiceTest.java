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
package org.kuali.kra.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.AwardNumberService;
import org.kuali.kra.award.AwardNumberServiceImpl;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.service.SequenceAccessorService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AwardNumberServiceTest {
    
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
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

    /**
     * This method creates an instance of AwardNumberService, using a mock SequenceAccessorService which
     * always returns SEQUENCE_NUMBER.
     * @return
     */
    private AwardNumberService createAwardNumberService() {   
        final SequenceAccessorService sequenceAccessorService = context.mock(SequenceAccessorService.class);
        context.checking(new Expectations() {
            {
                one(sequenceAccessorService).getNextAvailableSequenceNumber(Constants.AWARD_SEQUENCE_AWARD_NUMBER, Award.class);
                will(returnValue(SEQUENCE_NUMBER));
            }
        });
        AwardNumberServiceImpl awardNumberService = new AwardNumberServiceImpl();
        awardNumberService.setSequenceAccessorService(sequenceAccessorService);
        return awardNumberService;
    }
}
