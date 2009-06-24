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
package org.kuali.kra.irb.protocol;

import java.util.Calendar;

import org.kuali.rice.kns.service.SequenceAccessorService;

/**
 * ProtocolNumberService Implementation.
 */
public class ProtocolNumberServiceImpl implements ProtocolNumberService {

    private static final String ZERO = "0";
    private static final String SEQUENCE_NAME = "SEQ_PROTOCOL_ID";
    private static final int MAX_NUMBER = 1000000;
    
    private SequenceAccessorService sequenceAccessorService;
    
    /**
     * Set the Sequence Accessor Service.
     * @param sequenceAccessorService the Sequence Accessor Service
     */
    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }
    
    /**
     * @see org.kuali.kra.irb.protocol.ProtocolNumberService#generateProtocolNumber()
     */
    public String generateProtocolNumber() {
        Calendar calendar = Calendar.getInstance();
        return getYear(calendar) + getMonth(calendar) + getNextNumber();
    }
    
    /**
     * Get the current year, only the last two digits.
     * @param calendar the current time
     * @return the year as 2 digits as a string
     */
    private String getYear(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        String s = Integer.toString(year).substring(2);
        if (s.length() == 1) {
            s = ZERO + s;
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
            s = ZERO + s;
        }
        return s;
    }
    
    /**
     * Get the next sequence number which is always 6 digits,
     * including leading zeros.  
     * @return the next sequence number
     */
    private String getNextNumber() {
        Long nextNumber = getSequenceNumber() % MAX_NUMBER;
        String s = nextNumber.toString();
        int length = s.length();
        for (int i = 0; i < 6 - length; i++) {
            s = ZERO + s;
        }
        return s;
    }
    
    /**
     * Get the next database sequence number. Not sure what would happen if
     * multiple threads were to call this method at the same time to access 
     * the database sequence.  Therefore, it is synchronzied to prevent any
     * possible conflicts.
     * @return the next database sequence number
     */
    private synchronized Long getSequenceNumber() {
        return sequenceAccessorService.getNextAvailableSequenceNumber(SEQUENCE_NAME);
    }
}
