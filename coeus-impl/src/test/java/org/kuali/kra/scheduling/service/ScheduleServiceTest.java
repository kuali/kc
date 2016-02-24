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
package org.kuali.kra.scheduling.service;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.coeus.sys.framework.scheduling.ScheduleService;
import org.kuali.coeus.sys.framework.scheduling.util.Time24HrFmt;
import org.kuali.coeus.sys.impl.scheduling.ScheduleServiceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ScheduleServiceTest {
    
    // Tests for other service methods will have to be added at some point. 
    // (ideally they should've been added when the service method implementations were being first created). 
    // I am adding a test for a newly created service method below.
    
    @Test
    public void testGetIntervalInDaysScheduledDates() throws Exception{
        int intervalInDays;
        Date startDate;
        Date endDate;
        Time24HrFmt meetingTime;
        DateFormat onlyDateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat dateTimeFormatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
        ScheduleService service = new ScheduleServiceImpl();
        
        List<Date> actualDates;
        
        // check that 99 days are being added correctly, also check that the dates before and after 
        // DST have the same meeting time (that it does not shift by an hour).
        intervalInDays = 99;
        startDate = onlyDateFormatter.parse("10/4/2011");
        endDate = onlyDateFormatter.parse("02/4/2012");
        meetingTime = new Time24HrFmt("16:00");
        
        actualDates = service.getIntervalInDaysScheduledDates(startDate, endDate, meetingTime, intervalInDays);
       
        Assert.assertTrue(actualDates.size() == 2);
        Assert.assertTrue(actualDates.contains(dateTimeFormatter.parse("10/04/2011 4:00 PM")));
        Assert.assertTrue(actualDates.contains(dateTimeFormatter.parse("01/11/2012 4:00 PM")));
        
        // check with an interval of 7 days and meeting time of 8 am
        intervalInDays = 7;
        startDate = onlyDateFormatter.parse("12/01/2011");
        endDate = onlyDateFormatter.parse("12/29/2011");
        meetingTime = new Time24HrFmt("08:00");
        
        actualDates = service.getIntervalInDaysScheduledDates(startDate, endDate, meetingTime, intervalInDays);
       
        Assert.assertTrue(actualDates.size() == 5);
        Assert.assertTrue(actualDates.contains(dateTimeFormatter.parse("12/01/2011 08:00 AM")));
        Assert.assertTrue(actualDates.contains(dateTimeFormatter.parse("12/08/2011 08:00 AM")));
        Assert.assertTrue(actualDates.contains(dateTimeFormatter.parse("12/15/2011 08:00 AM")));
        Assert.assertTrue(actualDates.contains(dateTimeFormatter.parse("12/22/2011 08:00 AM")));
        Assert.assertTrue(actualDates.contains(dateTimeFormatter.parse("12/29/2011 08:00 AM")));
       
    }

}
