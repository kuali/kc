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
package org.kuali.kra.subaward.service;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
public class SubAwardServiceTest extends KcIntegrationTestBase {
    SubAwardService subAwardService;
    @Before
    public void setUp() throws Exception {
        subAwardService = KcServiceLocator.getService(SubAwardService.class);
    }

    @After
    public void tearDown() throws Exception {
        subAwardService = null;
    }

    @Test
    public void testGetFollowupDateDefaultLength() {
        String checkVal = subAwardService.getFollowupDateDefaultLength();
        assertEquals("6W", checkVal);
    }

    @Test
    public void testGetCalculatedFollowupDate() {
        Date checkDate = subAwardService.getCalculatedFollowupDate(new Date(2012, 1, 1));
        Date expectedDate = new Date(DateUtils.addWeeks(new Date(2012, 1, 1), 6).getTime());
        assertEquals(expectedDate, checkDate);
    }
    
    @Test
    public void testGetFollowupDateDefaultLengthInDays() {
        int followUpDays = subAwardService.getFollowupDateDefaultLengthInDays();
        int expectedFollowUpDays = 6 * 7;
        assertEquals(expectedFollowUpDays, followUpDays);
        
    }
}
