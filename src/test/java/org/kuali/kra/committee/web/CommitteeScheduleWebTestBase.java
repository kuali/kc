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
package org.kuali.kra.committee.web;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
@PerSuiteUnitTestData(
        @UnitTestData(
            sqlFiles = {
                @UnitTestFile(filename = "classpath:sql/dml/load_schedule_status.sql", delimiter = ";")
            }
        )
    )
public class CommitteeScheduleWebTestBase extends CommitteeWebTestBase {
    
    private static int MY_COMMITTEE_ID = 32767;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    protected int getWordCount(String str, String substr){
        String temp=str;
        int count=0;
        int i=temp.indexOf(substr);
        while(i>=0){
            count++;
            temp=temp.substring(i+1);
            i=temp.indexOf(substr);
        }
        return count;
    }
    
    protected boolean isMonday(Date date) {
        Calendar cl = new GregorianCalendar();
        cl.setTime(date);
        boolean retVal = false;
        if(Calendar.MONDAY == cl.get(Calendar.DAY_OF_WEEK))
            retVal = true;
        return retVal;   
    }
    
    protected void setFields(HtmlPage page, String date) {
    
        setFieldValue(page, "scheduleData.scheduleStartDate", date);     
        setFieldValue(page, "scheduleData.time.time", "10:10");      
        setFieldValue(page, "scheduleData.place", "Davis 103");
    }
    
    protected void assertRecord(HtmlPage page, Date date) {
        String datefmt = formatDate(date);
        assertContains(page,datefmt);
        
        String submissionDate = formatDate(DateUtils.addDays(date, -1));
        assertContains(page,submissionDate);
        
        String dayOfWeek = findDayOfWeek(date); 
        assertContains(page, dayOfWeek);
    }
    
    protected HtmlPage prerequisite() throws Exception  {
        
        HtmlPage committeePage = buildCommitteePage();        
        setDefaultRequiredFields(committeePage);
        setFieldValue(committeePage, COMMITTEE_ID_ID, new Integer(MY_COMMITTEE_ID++).toString());
        committeePage = saveDoc(committeePage);
        assertFalse(hasError(committeePage)); 
        
        HtmlPage schedulePage = clickOn(committeePage, "methodToCall.headerTab.headerDispatch.save.navigateTo.committeeSchedule.x");   
        
        assertFalse(hasError(schedulePage));
        assertContains(schedulePage,"Add to Schedule");
        return schedulePage;
    }
    
    protected String formatDate(Date date) {
        Calendar cl = new GregorianCalendar();
        cl.setTime(date);        
        StringBuffer sb = new StringBuffer();
        int month = cl.get(Calendar.MONTH);
        month += 1;
        if(month < 10)
            sb.append("0").append(month).append("/");
        else
            sb.append(month).append("/");
        
        int day = cl.get(Calendar.DATE);
        if(day < 10)
            sb.append("0").append(day).append("/");
        else
            sb.append(day).append("/");
        int year = cl.get(Calendar.YEAR);
        sb.append(year);
        return sb.toString();
    }
    
    protected String findDayOfWeek(Date date) {
        Calendar cl = new GregorianCalendar();
        cl.setTime(date);
        String dayOfWeek = null;        
        switch (cl.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                dayOfWeek = "SUNDAY";
                break;
            case Calendar.MONDAY:
                dayOfWeek = "MONDAY";
                break;
            case Calendar.TUESDAY:
                dayOfWeek = "TUESDAY";
                break;
            case Calendar.WEDNESDAY:
                dayOfWeek = "WEDNESDAY";
                break;
            case Calendar.THURSDAY:
                dayOfWeek = "THURSDAY";
                break;
            case Calendar.FRIDAY:
                dayOfWeek = "FRIDAY";
                break;
            case Calendar.SATURDAY:
                dayOfWeek = "SATURDAY";
                break;
        }
        return dayOfWeek;
    }
    
    protected String findMonth(Date date) {
        Calendar cl = new GregorianCalendar();
        cl.setTime(date);
        String month = null;
        switch (cl.get(Calendar.MONTH)) {
            case 0:
                month = "JANUARY";
                break;
            case 1:
                month = "FEBRUARY";
                break;
            case 2:
                month = "MARCH";
                break;
            case 3:
                month = "APRIL";
                break;    
            case 4:
                month = "MAY";
                break; 
            case 5:
                month = "JUNE";
                break;
            case 6:
                month = "JULY";
                break;
            case 7:
                month = "AUGUST";
                break;
            case 8:
                month = "SEPTEMBER";
                break;
            case 9:
                month = "OCTOBER";
                break;    
            case 10:
                month = "NOVEMBER";
                break;
            case 11:
                month = "DECEMBER";
                break;
        }
        
        return month;
    }
}
