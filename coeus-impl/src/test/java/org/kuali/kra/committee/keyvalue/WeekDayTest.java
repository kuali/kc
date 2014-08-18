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
package org.kuali.kra.committee.keyvalue;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.coeus.common.committee.impl.keyvalue.WeekDay;

import java.util.Map;

public class WeekDayTest extends Assert{
    
    public static final String SUNDAY = "Sunday";
    public static final String MONDAY = "Monday";
    public static final String TUESDAY = "Tuesday";
    public static final String WEDNESDAY = "Wednesday";
    public static final String THURSDAY = "Thursday";
    public static final String FRIDAY = "Friday";
    public static final String SATURDAY = "Saturday";
    
    /**
     * This method tests getKeyValues() method's return value.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testValues() throws Exception {
        WeekDay weekday = new WeekDay();
        Map map = weekday.getKeyLabelMap();
        assertTrue(((String)map.get(SUNDAY)).equalsIgnoreCase(SUNDAY));
        assertTrue(((String)map.get(MONDAY)).equalsIgnoreCase(MONDAY));
        assertTrue(((String)map.get(TUESDAY)).equalsIgnoreCase(TUESDAY));
        assertTrue(((String)map.get(WEDNESDAY)).equalsIgnoreCase(WEDNESDAY));
        assertTrue(((String)map.get(THURSDAY)).equalsIgnoreCase(THURSDAY));
        assertTrue(((String)map.get(FRIDAY)).equalsIgnoreCase(FRIDAY));
        assertTrue(((String)map.get(SATURDAY)).equalsIgnoreCase(SATURDAY));
    }
}
