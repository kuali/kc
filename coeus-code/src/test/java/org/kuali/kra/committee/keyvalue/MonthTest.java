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
