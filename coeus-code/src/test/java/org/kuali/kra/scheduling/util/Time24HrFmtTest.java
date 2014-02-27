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
package org.kuali.kra.scheduling.util;

import org.junit.Test;
import org.kuali.coeus.sys.framework.scheduling.util.Time24HrFmt;

import static org.junit.Assert.assertTrue;


public class Time24HrFmtTest {
    
    @Test
    public void testParseTime() throws Exception {

        new Time24HrFmt("10:30");
        assertTrue(true);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParseTimeWithIncorrectMinutes() throws Exception {
            new Time24HrFmt("10:79");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseTimeWithIncorrectHour() throws Exception {
            new Time24HrFmt("24:0");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseTimeWithNoMinutes() throws Exception {
            new Time24HrFmt("0");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseTimeWithNonInteger() throws Exception {
            new Time24HrFmt("10:1d");
    }
}
