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
package org.kuali.kra.irb.protocol;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.irb.Protocol;
import org.kuali.rice.krad.service.SequenceAccessorService;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * Test the ProtocolNumberService Implementation.
 */
@RunWith(JMock.class)
public class ProtocolNumberServiceTest {

    private static final Long sequenceNumber = new Long(562);
    
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    private String expectedProtocolNumber;

    @Before
    public void setUp() {
        Calendar calendar = Calendar.getInstance();
        expectedProtocolNumber = getYear(calendar) + getMonth(calendar) + "000" + sequenceNumber;
    }

    /**
     * Test the generation of the protocol number.  It should invoked the Sequence Accessor Service
     * only once.  The expected protocol number will be today's YYMM followed by 000562.
     */
    @Test
    public void testProtocolNumber() {
        ProtocolNumberServiceImpl protocolNumberService = new ProtocolNumberServiceImpl();

        final SequenceAccessorService sequenceAccessorService = context.mock(SequenceAccessorService.class);
        context.checking(new Expectations() {
            {
                one(sequenceAccessorService).getNextAvailableSequenceNumber("SEQ_PROTOCOL_ID", Protocol.class);
                will(returnValue(sequenceNumber));
            }
        });
        protocolNumberService.setSequenceAccessorService(sequenceAccessorService);

        String protocolNumber = protocolNumberService.generateProtocolNumber();
        assertEquals(expectedProtocolNumber, protocolNumber);
    }
    
    /**
     * Get the current year, only the last two digits.
     * @param calendar the current time
     * @return the year as 2 digits as a string
     */
    private String getYear(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR) - 2000;
        String s = Integer.toString(year);
        if (s.length() == 1) {
            s = "0" + s;
        }
        return s;
    }
    
    /**
     * Get the current month, always 2 digits.
     * @param calendar the current time
     * @return the month as 2 digits, e.g. 03 is March
     */
    private String getMonth(Calendar calendar) {
        int month = calendar.get(Calendar.MONTH) + 1;
        String s = Integer.toString(month);
        if (s.length() == 1) {
            s = "0" + s;
        }
        return s;
    }
}
