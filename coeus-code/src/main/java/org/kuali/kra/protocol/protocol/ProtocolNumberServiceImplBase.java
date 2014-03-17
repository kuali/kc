/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.protocol.protocol;

import org.kuali.rice.krad.service.SequenceAccessorService;

import java.util.Calendar;

/**
 * ProtocolNumberService Implementation.
 */
public abstract class ProtocolNumberServiceImplBase implements ProtocolNumberServiceBase {

    private static final String ZERO = "0";
    private static final int MAX_NUMBER = 1000000;
    
    private SequenceAccessorService sequenceAccessorService;
    
    /**
     * Set the Sequence Accessor Service.
     * @param sequenceAccessorService the Sequence Accessor Service
     */
    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }
    
    @Override
    public String generateProtocolNumber() {
        Calendar calendar = Calendar.getInstance();
        return getYear(calendar) + getMonth(calendar) + getNextNumber();
    }
    
    /**
     * Get the current year, only the last two digits.
     * @param calendar the current time
     * @return the year as 2 digits as a string
     */
    protected String getYear(Calendar calendar) {
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
    protected String getMonth(Calendar calendar) {
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
    protected String getNextNumber() {
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
    protected synchronized final Long getSequenceNumber() {
        // the actual sequence name is obtained from the subclass via the getSequenceNameHook()
        return sequenceAccessorService.getNextAvailableSequenceNumber(getSequenceNameHook(), getSequenceOwnerClass());
    }

    // hook for getting the sequence name
    protected abstract String getSequenceNameHook();
    
    protected abstract Class getSequenceOwnerClass();
    
}
