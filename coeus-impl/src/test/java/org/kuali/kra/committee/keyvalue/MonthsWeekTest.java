/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
import org.kuali.coeus.common.committee.impl.keyvalue.MonthsWeek;

import java.util.Map;

public class MonthsWeekTest extends Assert{
    
    public static final String FIRST =  "first";
    public static final String SECOND = "second";
    public static final String THIRD = "third";
    public static final String FOURTH = "fourth";
    public static final String LAST = "last";
    
    /**
     * This method tests getKeyValues() method's return value.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testValues() throws Exception {
        MonthsWeek monthsWeek = new MonthsWeek();
        Map map = monthsWeek.getKeyLabelMap();
        assertTrue(((String)map.get(FIRST)).equalsIgnoreCase(FIRST));
        assertTrue(((String)map.get(SECOND)).equalsIgnoreCase(SECOND));
        assertTrue(((String)map.get(THIRD)).equalsIgnoreCase(THIRD));
        assertTrue(((String)map.get(LAST)).equalsIgnoreCase(LAST));
    }
}
