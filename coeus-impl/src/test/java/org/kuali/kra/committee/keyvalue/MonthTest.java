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
import org.kuali.coeus.common.committee.impl.keyvalue.Month;

import java.util.Map;

public class MonthTest extends Assert{
    
    public static final String JANUARY = "JANUARY";    
    public static final String FEBRUARY = "FEBRUARY"; 
    public static final String MARCH = "MARCH";   
    public static final String APRIL = "APRIL";  
    public static final String MAY = "MAY";   
    public static final String JUNE = "JUNE";   
    public static final String JULY = "JULY";    
    public static final String AUGUST = "AUGUST";    
    public static final String SEPTEMBER = "SEPTEMBER";    
    public static final String OCTOBER = "OCTOBER";   
    public static final String NOVEMBER = "NOVEMBER";  
    public static final String DECEMBER = "DECEMBER";
    
    /**
     * This method tests getKeyValues() method's return value.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testValues() throws Exception {
        Month month = new Month();
        Map map = month.getKeyLabelMap();
        assertTrue(((String)map.get(JANUARY)).equalsIgnoreCase(JANUARY));
        assertTrue(((String)map.get(FEBRUARY)).equalsIgnoreCase(FEBRUARY));
        assertTrue(((String)map.get(MARCH)).equalsIgnoreCase(MARCH));
        assertTrue(((String)map.get(APRIL)).equalsIgnoreCase(APRIL));
        assertTrue(((String)map.get(MAY)).equalsIgnoreCase(MAY));
        assertTrue(((String)map.get(JUNE)).equalsIgnoreCase(JUNE));
        assertTrue(((String)map.get(JULY)).equalsIgnoreCase(JULY));
        assertTrue(((String)map.get(AUGUST)).equalsIgnoreCase(AUGUST));
        assertTrue(((String)map.get(SEPTEMBER)).equalsIgnoreCase(SEPTEMBER));
        assertTrue(((String)map.get(OCTOBER)).equalsIgnoreCase(OCTOBER));
        assertTrue(((String)map.get(NOVEMBER)).equalsIgnoreCase(NOVEMBER));
        assertTrue(((String)map.get(DECEMBER)).equalsIgnoreCase(DECEMBER));
    }
}
