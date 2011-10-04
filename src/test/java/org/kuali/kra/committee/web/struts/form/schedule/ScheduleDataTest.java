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
package org.kuali.kra.committee.web.struts.form.schedule;

import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;

public class ScheduleDataTest extends TestCase {
    
    public static final String RECURRENCE_TYPE = "WEEKLY";
    
    /**
     * This method test's the style class Map. 
     */
    @Test
    public void testPopulateStyleClass() {
        
        ScheduleData data = new DailyScheduleData();
        
        Map<String, String> mapbefore = data.getStyleClasses();
        assertEquals(ScheduleData.BLOCK, mapbefore.get("NEVER"));
        assertEquals(ScheduleData.NONE, mapbefore.get("DAILY"));
        assertEquals(ScheduleData.NONE, mapbefore.get("WEEKLY"));
        assertEquals(ScheduleData.NONE, mapbefore.get("MONTHLY"));
        assertEquals(ScheduleData.NONE, mapbefore.get("YEARLY"));
        
        data.setRecurrenceType(RECURRENCE_TYPE);
        data.populateStyleClass();        
        
        Map<String, String> mapAfter = data.getStyleClasses();
        assertEquals(ScheduleData.NONE, mapAfter.get("NEVER"));
        assertEquals(ScheduleData.NONE, mapAfter.get("DAILY"));
        assertEquals(ScheduleData.BLOCK, mapAfter.get("WEEKLY"));
        assertEquals(ScheduleData.NONE, mapAfter.get("MONTHLY"));
        assertEquals(ScheduleData.NONE, mapAfter.get("YEARLY"));
        
    }
}
