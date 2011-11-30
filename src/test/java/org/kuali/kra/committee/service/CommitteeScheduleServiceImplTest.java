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
package org.kuali.kra.committee.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.bo.ScheduleStatus;
import org.kuali.kra.committee.service.impl.CommitteeScheduleServiceImpl;
import org.kuali.kra.committee.web.struts.form.schedule.DailyScheduleDetails;
import org.kuali.kra.committee.web.struts.form.schedule.DayOfWeek;
import org.kuali.kra.committee.web.struts.form.schedule.MonthlyScheduleDetails;
import org.kuali.kra.committee.web.struts.form.schedule.ScheduleData;
import org.kuali.kra.committee.web.struts.form.schedule.StyleKey;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt;
import org.kuali.kra.committee.web.struts.form.schedule.WeeklyScheduleDetails;
import org.kuali.kra.committee.web.struts.form.schedule.YearlyScheduleDetails;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt.MERIDIEM;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.scheduling.expr.util.CronSpecialChars;
import org.kuali.kra.scheduling.sequence.DefaultScheduleSequence;
import org.kuali.kra.scheduling.sequence.ScheduleSequence;
import org.kuali.kra.scheduling.sequence.WeekScheduleSequenceDecorator;
import org.kuali.kra.scheduling.service.ScheduleService;
import org.kuali.kra.scheduling.util.Time24HrFmt;
import org.kuali.rice.kns.service.BusinessObjectService;

public class CommitteeScheduleServiceImplTest  {
    
    public static final String TIME24HR_0_1 = "0:1";
    
    public static final String TIME24HR_23_59 = "23:59";
    
    public static final String TIME12HR_11_59 = "11:59";
    
    public static final String TIME12HR_00_01 = "00:01";
    
    private Mockery context;
    
    private BusinessObjectService boService;
    
    private ScheduleService scheduleService;
    
    private CommitteeScheduleServiceImpl service;
    
    private CommitteeSchedule committeeSchedule;
    
    private ScheduleData scheduleData;
    
    private Committee committee;
    
    @Before
    public void setUp() throws Exception {
       context  = new JUnit4Mockery() {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};
       boService = context.mock(BusinessObjectService.class);       
       scheduleService = context.mock(ScheduleService.class);
       service = new CommitteeScheduleServiceImpl();
       service.setBusinessObjectService(boService);
       service.setScheduleService(scheduleService);
       committeeSchedule = new CommitteeSchedule();
    }

    /**
     * This method test yearly recurrence for option 2.
     * @throws Exception
     */
    @Test
    public void testAddScheduleYearlyRecurrenceCmplx() throws Exception {
        prerequisite();
        yearlyPrerequisite();
        context.checking(new Expectations() {{
            Date dt = scheduleData.getScheduleStartDate();
            Date endDt = scheduleData.getYearlySchedule().getScheduleEndDate();
            List<Date> dates = new LinkedList<Date>();
            dates.add(new Date());
            Time24HrFmt time  = new Time24HrFmt(TIME24HR_0_1);
            CronSpecialChars weekOfMonth = ScheduleData.getWeekOfMonth(scheduleData.getYearlySchedule().getSelectedMonthsWeek());
            CronSpecialChars dayOfWeek = ScheduleData.getDayOfWeek(scheduleData.getYearlySchedule().getSelectedDayOfWeek());
            CronSpecialChars month = ScheduleData.getMonthOfWeek(scheduleData.getYearlySchedule().getSelectedOption2Month());
            int frequency = scheduleData.getYearlySchedule().getOption2Year();
            one(scheduleService).getScheduledDates(dt,endDt,time, weekOfMonth, dayOfWeek, month, frequency, null);will(returnValue(dates));
        }});

        scheduleData.getYearlySchedule().setYearOption(YearlyScheduleDetails.yearOptionValues.CMPLX.toString());
        
        test(1);
    }
    
    /**
     * This method test's yearly recurrence for option 1.
     * @throws Exception
     */
    @Test
    public void testAddScheduleYearlyRecurrenceXday() throws Exception {
        prerequisite();
        yearlyPrerequisite();
        context.checking(new Expectations() {{
            Date dt = scheduleData.getScheduleStartDate();
            Date endDt = scheduleData.getYearlySchedule().getScheduleEndDate();
            List<Date> dates = new LinkedList<Date>();
            dates.add(new Date());
            Time24HrFmt time  = new Time24HrFmt(TIME24HR_0_1);
            CronSpecialChars month = ScheduleData.getMonthOfWeek(scheduleData.getYearlySchedule().getSelectedOption1Month());
            int day = scheduleData.getYearlySchedule().getDay();
            int frequency = scheduleData.getYearlySchedule().getOption1Year();
            one(scheduleService).getScheduledDates(dt,endDt,time, month, day, frequency,  null);will(returnValue(dates));
        }});
        
        scheduleData.getYearlySchedule().setYearOption(YearlyScheduleDetails.yearOptionValues.XDAY.toString());
        
        test(1);
    }
    
    /**
     * Private helper method to set prerequiste's for yearly options.
     */
    private void yearlyPrerequisite(){
        scheduleData.setRecurrenceType(StyleKey.YEARLY.toString());      
        scheduleData.setYearlySchedule(new YearlyScheduleDetails());
        int day = getDay(scheduleData.getScheduleStartDate(),Calendar.DATE);
        int month = getDay(scheduleData.getScheduleStartDate(), Calendar.MONTH);
        scheduleData.setScheduleStartDate(getDate(-day -(month*31)));
        scheduleData.getYearlySchedule().setScheduleEndDate(getDate(-day - (month*31)+10));
    }
    
    /**
     * This method test's Monthly recurrence for option 1.
     * @throws Exception
     */
    @Test
    public void testAddScheduleMonthlyRecurrenceXdayofweekAndXmonth() throws Exception {
        prerequisite(); 
        monthlyPrerequisite();
        
        int day = getDay(scheduleData.getScheduleStartDate(), Calendar.DATE);
        scheduleData.setScheduleStartDate(getDate(-day));
        scheduleData.getMonthlySchedule().setScheduleEndDate(getDate(-day+7));
        scheduleData.getMonthlySchedule().setMonthOption(MonthlyScheduleDetails.optionValues.XDAYOFWEEKANDXMONTH.toString());
        context.checking(new Expectations() {{
            Date dt = scheduleData.getScheduleStartDate();
            Date endDt = scheduleData.getMonthlySchedule().getScheduleEndDate();
            List<Date> dates = new LinkedList<Date>();
            dates.add(new Date());
            Time24HrFmt time  = new Time24HrFmt(TIME24HR_0_1);
            CronSpecialChars weekOfMonth = ScheduleData.getWeekOfMonth(scheduleData.getMonthlySchedule().getSelectedMonthsWeek());
            CronSpecialChars dayOfWeek = ScheduleData.getDayOfWeek(scheduleData.getMonthlySchedule().getSelectedDayOfWeek());
            int frequency = scheduleData.getMonthlySchedule().getOption2Month();
            one(scheduleService).getScheduledDates(dt,endDt,time,dayOfWeek, weekOfMonth, frequency,null);will(returnValue(dates));
        }});
        
        test(1);
    }

    /**
     * This method test's Monthly recurrence for option 1.
     * @throws Exception
     */
    @Test
    public void testAddScheduleMonthlyRecurrenceXdayAndXmonth() throws Exception {
        prerequisite();        
        monthlyPrerequisite();
        
        int day = getDay(scheduleData.getScheduleStartDate(), Calendar.DATE);
        scheduleData.getMonthlySchedule().setDay(day+1);
        scheduleData.getMonthlySchedule().setScheduleEndDate(getDate(1));
        scheduleData.getMonthlySchedule().setMonthOption(MonthlyScheduleDetails.optionValues.XDAYANDXMONTH.toString());
        context.checking(new Expectations() {{
            Date dt = scheduleData.getScheduleStartDate();
            Date endDt = scheduleData.getMonthlySchedule().getScheduleEndDate();
            List<Date> dates = new LinkedList<Date>();
            dates.add(new Date());
            Time24HrFmt time  = new Time24HrFmt(TIME24HR_0_1);
            int day = scheduleData.getMonthlySchedule().getDay();
            int frequency = scheduleData.getMonthlySchedule().getOption1Month();
            one(scheduleService).getScheduledDates(dt,endDt,time,day,frequency,null);will(returnValue(dates));
        }});
        
        test(1);
    }    
    
    /**
     * This method gets Day of month for given date.
     * @param dt
     * @param type
     * @return
     */
    private int getDay(Date dt, int type) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(dt);
        int day = cal.get(type);
        return day;
    }
    
    /**
     * Private helper method to set prerequiste's for monthly options.
     */
    private void monthlyPrerequisite() {
        scheduleData.setRecurrenceType(StyleKey.MONTHLY.toString());      
        scheduleData.setMonthlySchedule(new MonthlyScheduleDetails()); 
    }
    
    /**
     * This method test's Weekly recurrence.
     * @throws Exception
     */
    @Test
    public void testAddScheduleWeeklyRecurrence() throws Exception {
        prerequisite();
        
        scheduleData.setRecurrenceType(StyleKey.WEEKLY.toString());      
        scheduleData.setWeeklySchedule(new WeeklyScheduleDetails());
        scheduleData.getWeeklySchedule().setScheduleEndDate(getDate(7));
        List<String> daysOfWeek = new ArrayList<String>(2);
        daysOfWeek.add(DayOfWeek.Monday.name());
        daysOfWeek.add("Hidden");
        
        scheduleData.getWeeklySchedule().setDaysOfWeek(daysOfWeek);
        context.checking(new Expectations() {{
            Date dt = scheduleData.getScheduleStartDate();
            Date endDt = scheduleData.getWeeklySchedule().getScheduleEndDate();
            List<Date> dates = new LinkedList<Date>();
            dates.add(new Date());
            Time24HrFmt time  = new Time24HrFmt(TIME24HR_0_1); 
            CronSpecialChars[] dow = null;
            if(CollectionUtils.isNotEmpty(scheduleData.getWeeklySchedule().getDaysOfWeek())) {
                dow = ScheduleData.convertToWeekdays(scheduleData.getWeeklySchedule().getDaysOfWeek().toArray(new String[scheduleData.getWeeklySchedule().getDaysOfWeek().size()]));
            }
            ScheduleSequence scheduleSequence = new WeekScheduleSequenceDecorator(new DefaultScheduleSequence(),scheduleData.getWeeklySchedule().getWeek(),dow.length);
            one(scheduleService).getScheduledDates(dt,endDt,time,dow,scheduleSequence);will(returnValue(dates));
        }});
        
        test(1);
    }
    
    /**
     * This method test's daily recurrence option 2.
     * @throws Exception
     */
    @Test
    public void testAddScheduleDailyRecurrenceWeekday() throws Exception {
        prerequisite();
        
        scheduleData.setRecurrenceType(StyleKey.DAILY.toString());      
        scheduleData.setDailySchedule(new DailyScheduleDetails());
        scheduleData.getDailySchedule().setScheduleEndDate(getDate(1));
        scheduleData.getDailySchedule().setDayOption(DailyScheduleDetails.optionValues.WEEKDAY.toString());
        
        context.checking(new Expectations() {{
            Date dt = scheduleData.getScheduleStartDate();
            Date endDt = scheduleData.getDailySchedule().getScheduleEndDate();
            List<Date> dates = new LinkedList<Date>();
            dates.add(new Date());
            Time24HrFmt time  = new Time24HrFmt(TIME24HR_0_1); 
            CronSpecialChars[] dow = ScheduleData.convertToWeekdays(scheduleData.getDailySchedule().getDaysOfWeek());
            ScheduleSequence scheduleSequence = new WeekScheduleSequenceDecorator(new DefaultScheduleSequence(),1,dow.length);
            one(scheduleService).getScheduledDates(dt,endDt,time,dow,scheduleSequence);will(returnValue(dates));
        }});
        
        test(1);
    }
    
    /**
     * This method test's daily recurrence option 1.
     * @throws Exception
     */
    @Test
    public void testAddScheduleDailyRecurrenceXday() throws Exception {
        prerequisite();
        
        dailyPrerequisite();
        scheduleData.getDailySchedule().setDayOption(DailyScheduleDetails.optionValues.XDAY.toString());
        
        test(2);
    }

    /**
     * This method test's advance submission days for added event.
     * @throws Exception
     */
    @Test
    public void testAddScheduleAdvancedSubmissionDays() throws Exception {
        prerequisite();
        
        scheduleData.setRecurrenceType(StyleKey.NEVER.toString());             
        scheduleData.setTime(new Time12HrFmt(TIME12HR_11_59, MERIDIEM.PM));
        
        context.checking(new Expectations() {{
            Date dt = scheduleData.getScheduleStartDate();
            List<Date> dates = new LinkedList<Date>();
            dates.add(new Date());
            Time24HrFmt time  = new Time24HrFmt(TIME24HR_23_59); 
            one(scheduleService).getScheduledDates(dt,dt,time,null);will(returnValue(dates));
        }});
        
        test(1);
        java.sql.Date testDate = committee.getCommitteeSchedules().get(0).getProtocolSubDeadline();
        java.sql.Date expectedDate = new java.sql.Date(DateUtils.addDays(new Date(), -committee.getAdvancedSubmissionDaysRequired()).getTime());
        assertEquals(expectedDate.toString(), testDate.toString());
    }
    
    /**
     * This method for duplicate dates.
     * @throws Exception
     */
    @Test
    public void testAddScheduleDuplicateDates() throws Exception {
        prerequisite();
        
        scheduleData.setRecurrenceType(StyleKey.DAILY.toString());      
        scheduleData.setDailySchedule(new DailyScheduleDetails());
        scheduleData.getDailySchedule().setScheduleEndDate(getDate(2));
        context.checking(new Expectations() {{
            Date dt = scheduleData.getScheduleStartDate();
            Date endDt = scheduleData.getDailySchedule().getScheduleEndDate();
            List<Date> dates = new LinkedList<Date>();
            dates.add(new Date());
            dates.add(new Date());
            dates.add(DateUtils.addDays(new Date(),2));
            Time24HrFmt time  = new Time24HrFmt(TIME24HR_0_1); 
            int day = scheduleData.getDailySchedule().getDay();
            one(scheduleService).getIntervalInDaysScheduledDates(dt,endDt,time,day);will(returnValue(dates));
        }});
        scheduleData.getDailySchedule().setDayOption(DailyScheduleDetails.optionValues.XDAY.toString());
        
        test(2);
        assertEquals(1, scheduleData.getDatesInConflict().size());
    }
    
    /**
     * Private helper method to set prerequiste's for daily options.
     * @throws ParseException
     */
    private void dailyPrerequisite() throws ParseException {
        scheduleData.setRecurrenceType(StyleKey.DAILY.toString());      
        scheduleData.setDailySchedule(new DailyScheduleDetails());
        scheduleData.getDailySchedule().setScheduleEndDate(getDate(2));
        context.checking(new Expectations() {{
            Date dt = scheduleData.getScheduleStartDate();
            Date endDt = scheduleData.getDailySchedule().getScheduleEndDate();
            List<Date> dates = new LinkedList<Date>();
            dates.add(new Date());
            dates.add(DateUtils.addDays(new Date(),2));
            Time24HrFmt time  = new Time24HrFmt(TIME24HR_0_1); 
            int day = scheduleData.getDailySchedule().getDay();
            one(scheduleService).getIntervalInDaysScheduledDates(dt,endDt,time,day);will(returnValue(dates));
        }});
    }
    
    /**
     * This method test's never recurrence.
     * @throws Exception
     */
    @Test
    public void testAddScheduleNeverRecurrence() throws Exception {  
        prerequisite();
        
        scheduleData.setRecurrenceType(StyleKey.NEVER.toString());             
        scheduleData.setTime(new Time12HrFmt(TIME12HR_11_59, MERIDIEM.PM));
        
        context.checking(new Expectations() {{
            Date dt = scheduleData.getScheduleStartDate();
            List<Date> dates = new LinkedList<Date>();
            dates.add(new Date());
            Time24HrFmt time  = new Time24HrFmt(TIME24HR_23_59); 
            one(scheduleService).getScheduledDates(dt,dt,time,null);will(returnValue(dates));
        }});
        
        test(1);
        
    }  
    
    /**
     * This method test's list size added during recurrence.
     * @param count
     * @throws Exception
     */
    private void test(int count) throws Exception {
        service.addSchedule(scheduleData, committee);
        
        assertEquals(count, committee.getCommitteeSchedules().size());
    }
    
    /**
     * Private helper method to set prerequiste's for all options.
     */
    private void  prerequisite() {
        mockBusinessService();
        committee = new Committee();
        committee.setAdvancedSubmissionDaysRequired(10);
        
        scheduleData = new ScheduleData();
        scheduleData.setScheduleStartDate(getDate(0)); 
        scheduleData.setTime(new Time12HrFmt(TIME12HR_00_01, MERIDIEM.AM));
    }
    
    /**
     * This method test's if Protocol is deleteable.
     * @throws Exception
     */
    @Test
    public void testIsCommitteeScheduleDeletableWithProtocol() throws Exception {
        boolean flag = false;  
        Protocol protocol = context.mock(Protocol.class);       
        committeeSchedule.setProtocols(new ArrayList<Protocol>());
        committeeSchedule.getProtocols().add(protocol);
        flag = service.isCommitteeScheduleDeletable(committeeSchedule);
        assertFalse(flag);
    }
    
    /**
     * This method test's if Protocol is deletable.
     * @throws Exception
     */
    @Test
    public void testIsCommitteeScheduleDeletableWithPastDate() throws Exception {
        boolean flag = false;        
        java.sql.Date date = new java.sql.Date(new Date().getTime());
        committeeSchedule.setScheduledDate(date);
        flag  = service.isCommitteeScheduleDeletable(committeeSchedule);
        assertTrue(flag);
    }
    
    /**
     * This method test's if Protocol is deletable.
     * @throws Exception
     */
    @Test
    public void testIsCommitteeScheduleDeletableWithCurrentDate() throws Exception {
        boolean flag = false;                    
        Date newdate = DateUtils.addDays(new Date(), -1);
        committeeSchedule.setScheduledDate(new java.sql.Date(newdate.getTime()));
        flag = service.isCommitteeScheduleDeletable(committeeSchedule);
        assertTrue(flag);
    }
    
    /**
     * This method mocks Business Service.
     */
    private void mockBusinessService() {
        context.checking(new Expectations() {{
            List<ScheduleStatus> scheduleStatuses = new LinkedList<ScheduleStatus>();
            scheduleStatuses.add(new ScheduleStatus());
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("description", "Scheduled");
            allowing(boService).findMatching(ScheduleStatus.class, fieldValues);will(returnValue(scheduleStatuses));
        }});
    }

    /**
     * This method adds offset to today's date.
     * @param offset
     * @return
     */
    private java.sql.Date getDate(int offset) {
        java.sql.Date sqlDt = new java.sql.Date(new Date().getTime());
        Date dt = DateUtils.addDays(sqlDt, offset);
        return new java.sql.Date(dt.getTime());
    }
    
}
