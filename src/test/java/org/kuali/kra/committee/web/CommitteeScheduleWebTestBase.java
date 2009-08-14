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
    
    private static final String SCHEDULEDATA_SCHEDULESTARTDATE = "committeeScheduleHelper.scheduleData.scheduleStartDate";
    
    private static final String SCHEDULEDATA_TIME_TIME = "committeeScheduleHelper.scheduleData.time.time";
    
    private static final String SCHEDULEDATA_PLACE = "committeeScheduleHelper.scheduleData.place";
    
    private static final String METHODTOCALL_SAVE = "methodToCall.headerTab.headerDispatch.save.navigateTo.committeeSchedule.x";
    
    private static final String TIME_10_10 = "10:10";
    
    private static final String PLACE_DAVIS_103 = "Davis 103";
    
    private static final String ADD_TO_SCHEDULE = "Add to Schedule";
    
    private static final String ZERO = "0";
    
    private static final String SLASH = "/";
    
    private enum WEEKDAY {SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY};
    
    private enum MONTH {JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER};
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * This method counts the occurrence of word in string.
     * @param str
     * @param substr
     * @return
     */
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
    
    /**
     * This method checks if date passed as param is Monday.
     * @param date
     * @return
     */
    protected boolean isMonday(Date date) {
        Calendar cl = new GregorianCalendar();
        cl.setTime(date);
        boolean retVal = false;
        if(Calendar.MONDAY == cl.get(Calendar.DAY_OF_WEEK))
            retVal = true;
        return retVal;   
    }
    
    /**
     * This method sets required fields to test.
     * @param page
     * @param date
     */
    protected void setFields(HtmlPage page, String date) {    
        setFieldValue(page, SCHEDULEDATA_SCHEDULESTARTDATE, date);     
        setFieldValue(page, SCHEDULEDATA_TIME_TIME, TIME_10_10);      
        setFieldValue(page, SCHEDULEDATA_PLACE, PLACE_DAVIS_103);
    }
    
    /**
     * This method asserts record inserted after adding event.
     * @param page
     * @param date
     */
    protected void assertRecord(HtmlPage page, Date date) {
        String datefmt = formatDate(date);
        assertContains(page,datefmt);
        
        String submissionDate = formatDate(DateUtils.addDays(date, -1));
        assertContains(page,submissionDate);
        
        String dayOfWeek = findDayOfWeek(date); 
        assertContains(page, dayOfWeek);
    }
    
    /**
     * This method sets prerequisite for test methods.
     * @return
     * @throws Exception
     */
    protected HtmlPage prerequisite() throws Exception  {
        HtmlPage committeePage = buildCommitteePage();        
        setDefaultRequiredFields(committeePage);
        setFieldValue(committeePage, COMMITTEE_ID_ID, new Integer(MY_COMMITTEE_ID++).toString());
        committeePage = saveDoc(committeePage);
        assertFalse(hasError(committeePage)); 
        
        HtmlPage schedulePage = clickOn(committeePage, METHODTOCALL_SAVE);   
        
        assertFalse(hasError(schedulePage));
        assertContains(schedulePage,ADD_TO_SCHEDULE);
        return schedulePage;
    }
    
    /**
     * This method formats given date to mm/dd/yyyy.
     * @param date
     * @return
     */
    protected String formatDate(Date date) {
        Calendar cl = new GregorianCalendar();
        cl.setTime(date);        
        StringBuffer sb = new StringBuffer();
        int month = cl.get(Calendar.MONTH);
        month += 1;
        if(month < 10)
            sb.append(ZERO).append(month).append(SLASH);
        else
            sb.append(month).append(SLASH);
        
        int day = cl.get(Calendar.DATE);
        if(day < 10)
            sb.append(ZERO).append(day).append(SLASH);
        else
            sb.append(day).append(SLASH);
        int year = cl.get(Calendar.YEAR);
        sb.append(year);
        return sb.toString();
    }
    
    /**
     * This method finds week's day of date.
     * @param date
     * @return
     */
    protected String findDayOfWeek(Date date) {
        Calendar cl = new GregorianCalendar();
        cl.setTime(date);
        String dayOfWeek = null;        
        switch (cl.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                dayOfWeek = WEEKDAY.SUNDAY.toString();
                break;
            case Calendar.MONDAY:
                dayOfWeek = WEEKDAY.MONDAY.toString();
                break;
            case Calendar.TUESDAY:
                dayOfWeek = WEEKDAY.TUESDAY.toString();
                break;
            case Calendar.WEDNESDAY:
                dayOfWeek = WEEKDAY.WEDNESDAY.toString();
                break;
            case Calendar.THURSDAY:
                dayOfWeek = WEEKDAY.THURSDAY.toString();
                break;
            case Calendar.FRIDAY:
                dayOfWeek = WEEKDAY.FRIDAY.toString();
                break;
            case Calendar.SATURDAY:
                dayOfWeek = WEEKDAY.SATURDAY.toString();
                break;
        }
        return dayOfWeek;
    }
    
    /**
     * This method finds month of year for date.
     * @param date
     * @return
     */
    protected String findMonth(Date date) {
        Calendar cl = new GregorianCalendar();
        cl.setTime(date);
        String month = null;
        switch (cl.get(Calendar.MONTH)) {
            case 0:
                month = MONTH.JANUARY.toString();
                break;
            case 1:
                month = MONTH.FEBRUARY.toString();
                break;
            case 2:
                month = MONTH.MARCH.toString();
                break;
            case 3:
                month = MONTH.APRIL.toString();
                break;    
            case 4:
                month = MONTH.MAY.toString();
                break; 
            case 5:
                month = MONTH.JUNE.toString();
                break;
            case 6:
                month = MONTH.JULY.toString();
                break;
            case 7:
                month = MONTH.AUGUST.toString();
                break;
            case 8:
                month = MONTH.SEPTEMBER.toString();
                break;
            case 9:
                month = MONTH.OCTOBER.toString();
                break;    
            case 10:
                month = MONTH.NOVEMBER.toString();
                break;
            case 11:
                month = MONTH.DECEMBER.toString();
                break;
        }
        
        return month;
    }
    
    /**
     * 
     * This method asserts the number of displayed schedule rows.
     * 
     * @param schedulePage     - the web page
     * @param expectedRowCount - numbers of schedule rows that are expected to be displayed
     */
    protected void assertScheduleRowCount(HtmlPage schedulePage, int expectedRowCount) {
        String textOfPage = schedulePage.asText();
        String place = PLACE_DAVIS_103;
        
        int count = getWordCount(textOfPage,place);
        
        assertEquals((expectedRowCount + 1), count);
    }
    
}
