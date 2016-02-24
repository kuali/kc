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
package org.kuali.kra.infrastructure;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.util.DateUtils;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class DateUtilsTest {
    private static final Log LOG = LogFactory.getLog(DateUtilsTest.class);
    private static final ArrayList<String> TIME_LIST = new ArrayList<String>();
    
    private static String[][] valueChecks = { { "11:59 AM", "11:59 AM" }, { "01:59 AM", "1:59 AM" }, { "1:59 AM", "1:59 AM" },
                  { "1AM", "1:00 AM" }, { "3:00", "3:00 AM" }, { "3", "3:00 AM" }, { "12:00", "12:00 PM" }, { "17:00", "5:00 PM" },
                  { "1730", "5:30 PM" }, { "0800", "8:00 AM" }, { "5P", "5:00 PM" }, { "6:00a", "6:00 AM" }, { "6:00AM", "6:00 AM" },
                  { "8.30", "8:30 AM" }, { "2 p.m.", "2:00 PM" }, { "00:00", "12:00 AM" }, {null, ""}, {"23:36 pm", "11:36 PM"}, {"23:36 aM", "11:36 PM"},
                  { "   ", "" }};
              
    
    @Before
    public void setUp() {
        TIME_LIST.add("11:59 AM");
        TIME_LIST.add("1:00 AM");
        TIME_LIST.add("12:00 PM");
    }
    
    @After
    public void tearDown() {
        TIME_LIST.clear();
        
    }
    @Test
    public void testConvertToObject() {
        Assert.assertTrue(TIME_LIST.contains(DateUtils.formatFrom12Or24Str("1A")));
        Assert.assertTrue(TIME_LIST.contains(DateUtils.formatFrom12Or24Str("11:59")));
        
    }
    
    @Test
    public void testConvertToObject2() {
        for (String[] values : valueChecks) {
            String canonicalized = DateUtils.formatFrom12Or24Str(values[0]);
            LOG.info("Original Value: '" + values[0] + "'  calculated value: '" + canonicalized + "'  expected value: '" + values[1] + "'");
            assertEquals(values[1], canonicalized);
        }
    }
    

}
