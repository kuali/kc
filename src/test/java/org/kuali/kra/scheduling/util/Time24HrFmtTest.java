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
package org.kuali.kra.scheduling.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Test;


public class Time24HrFmtTest {
    
    @Test
    public void testParseTime() throws Exception {
        
        try {
            new Time24HrFmt("10:30");
        }
        catch (ParseException e) {
            assertTrue(false);
        }
        assertTrue(true);
    }
    
    @Test
    public void testParseTimeWithIncorrectMinutes() throws Exception {
        try {
            new Time24HrFmt("10:79");
        }
        catch (ParseException e) {
            assertEquals("Time format exception, expects mm as 0-59", e.getMessage());
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }
    
    @Test
    public void testParseTimeWithIncorrectHour() throws Exception {
        try {
            new Time24HrFmt("24:0");   
        }
        catch (ParseException e) {
            assertEquals("Time format exception, expects hh as 0-23", e.getMessage());
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }
    
    @Test
    public void testParseTimeWithNoMinutes() throws Exception {
        try {
            new Time24HrFmt("0");
        }
        catch (ParseException e) {
            assertEquals("Time format exception, expects hh:mm", e.getMessage());
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }
    
    @Test
    public void testParseTimeWithNonInteger() throws Exception {
        try {
            new Time24HrFmt("10:1d");
        }
        catch (ParseException e) {
            assertEquals("Time format exception, expects hh as 0-23 & mm as 0-59", e.getMessage());
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }
}
