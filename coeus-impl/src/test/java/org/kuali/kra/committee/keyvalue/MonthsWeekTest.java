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
