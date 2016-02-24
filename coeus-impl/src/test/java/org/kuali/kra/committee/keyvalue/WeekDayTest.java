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
