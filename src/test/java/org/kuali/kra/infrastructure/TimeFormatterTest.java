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
package org.kuali.kra.infrastructure;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TimeFormatterTest {
    private static final ArrayList<String> TIME_LIST = new ArrayList<String>();
    
    private TimeFormatter formatter;
    
    @Before
    public void setUp() {
        formatter = new TimeFormatter();
        TIME_LIST.add("11:59 AM");
        TIME_LIST.add("1:00 AM");
        TIME_LIST.add("12:00 PM");
    }
    
    @After
    public void tearDown() {
        formatter = null;
        TIME_LIST.clear();
        
    }
    @Test
    public void testConvertToObject() {
        Assert.assertTrue(TIME_LIST.contains((String)formatter.convertToObject("1A")));
        Assert.assertTrue(TIME_LIST.contains((String)formatter.convertToObject("11:59")));
        
    }
    

}
