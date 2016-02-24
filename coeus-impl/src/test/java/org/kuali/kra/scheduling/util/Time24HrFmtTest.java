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
