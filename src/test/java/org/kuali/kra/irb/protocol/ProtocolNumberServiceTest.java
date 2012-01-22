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
package org.kuali.kra.irb.protocol;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.rice.krad.service.SequenceAccessorService;

/**
 * Test the ProtocolNumberService Implementation.
 */
@RunWith(JMock.class)
public class ProtocolNumberServiceTest {

    private static final Long sequenceNumber = new Long(562);
    
    private Mockery context = new JUnit4Mockery();
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
                one(sequenceAccessorService).getNextAvailableSequenceNumber("SEQ_PROTOCOL_ID");
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
