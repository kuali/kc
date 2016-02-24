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
package org.kuali.kra.committee.web.struts.form.schedule;

import org.junit.Test;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.ScheduleData;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ScheduleDataTest {
    
    public static final String RECURRENCE_TYPE = "WEEKLY";
    
    /**
     * This method test's the style class Map. 
     */
    @Test
    public void testPopulateStyleClass() {
        
        ScheduleData data = new ScheduleData();
        
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
