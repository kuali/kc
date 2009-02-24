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
package org.kuali.kra.committee.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.bo.ScheduleStatus;
import org.kuali.kra.committee.service.CommitteeScheduleService;
import org.kuali.kra.committee.web.struts.form.schedule.DailyScheduleDetails;
import org.kuali.kra.committee.web.struts.form.schedule.MonthlyScheduleDetails;
import org.kuali.kra.committee.web.struts.form.schedule.ScheduleData;
import org.kuali.kra.committee.web.struts.form.schedule.StyleKey;
import org.kuali.kra.committee.web.struts.form.schedule.YearlyScheduleDetails;
import org.kuali.kra.scheduling.expr.util.CronSpecialChars;
import org.kuali.kra.scheduling.sequence.ScheduleSequence;
import org.kuali.kra.scheduling.sequence.WeekScheduleSequence;
import org.kuali.kra.scheduling.service.ScheduleService;
import org.kuali.kra.scheduling.util.Time24HrFmt;


/**
 * The Committee Service implementation.
 */
public class CommitteeScheduleServiceImpl implements CommitteeScheduleService {
    
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CommitteeScheduleServiceImpl.class);
    
    public static final Boolean TRUE = true;
    
    public static final Boolean FALSE = false;
    
    private BusinessObjectService businessObjectService;

    private ScheduleService scheduleService;
    
    /**
     * Set the Business Object Service.
     * @param businessObjectService the Business Object Service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }    
    
    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
    
    public Boolean isCommitteeScheduleDeleteAllowed(CommitteeSchedule committeeSchedule){
        Date today = new Date();
        java.sql.Date yesterday = new java.sql.Date(DateUtils.addDays(today,-1).getTime());
        java.sql.Date scheduleDate = committeeSchedule.getScheduledDate();
        boolean retVal = false;
        if(scheduleDate.after(yesterday)) {
            retVal = true;
        }
        return retVal;
    }
    
    public void deleteCommitteeSchedule(Committee committee, int lineNumber) {
        committee.getCommitteeSchedules().remove(lineNumber);  
    }
    
    public void filterCommitteeScheduleDates(ScheduleData scheduleData, Committee committee){
        List<CommitteeSchedule> committeeSchedules = committee.getCommitteeSchedules();
        Date startDate = scheduleData.getFilterStartDate();
        startDate = DateUtils.addDays(startDate, -1);
        Date endDate = scheduleData.getFilerEndDate();
        endDate = DateUtils.addDays(endDate, 1);
        Date scheduleDate = null;
        for(CommitteeSchedule committeeSchedule : committeeSchedules) {            
            scheduleDate = committeeSchedule.getScheduledDate();
            if(scheduleDate.after(startDate) && scheduleDate.before(endDate)) 
                committeeSchedule.setFilter(TRUE);            
            else
                committeeSchedule.setFilter(FALSE);
        }
    }

    public void resetCommitteeScheduleDates(Committee committee){
        List<CommitteeSchedule> committeeSchedules = committee.getCommitteeSchedules();
        for(CommitteeSchedule committeeSchedule : committeeSchedules) {
                committeeSchedule.setFilter(TRUE);            
        }
    }
    
    public void addSchedule(ScheduleData scheduleData, Committee committee) throws ParseException {
        
        List<Date> dates = null;
        Date dtEnd = null;
        int frequency = 0;
        int day = 0;
        CronSpecialChars[] weekdays = null;
        CronSpecialChars weekOfMonth = null;
        CronSpecialChars dayOfWeek = null;
        CronSpecialChars month = null;
        
        Time24HrFmt time = getTime24hFmt(scheduleData.getScheduleStartDate(), scheduleData.getTime().findMinutes());
        Date dt = scheduleData.getScheduleStartDate();       
        
        StyleKey key = StyleKey.valueOf(scheduleData.getRecurrenceType());        
        switch (key) {
            case NEVER :
                dates = scheduleService.getScheduledDates(dt, dt, time, null);
                break;
            case DAILY : 
                DailyScheduleDetails.optionValues dailyoption = DailyScheduleDetails.optionValues.valueOf(scheduleData.getDailySchedule().getDayOption());
                switch (dailyoption) {
                    case XDAY: 
                        dtEnd = scheduleData.getDailySchedule().getScheduleEndDate();
                        day = scheduleData.getDailySchedule().getDay();
                        dates = scheduleService.getScheduledDates(dt, dtEnd, time, day, null);
                        break;
                    case WEEKDAY : 
                        dtEnd = scheduleData.getDailySchedule().getScheduleEndDate();
                        day = scheduleData.getDailySchedule().getDay();
                        dates = scheduleService.getScheduledDates(dt, dtEnd, time, day, null);
                        break;
                }
                break;
            case WEEKLY :
                dtEnd = scheduleData.getWeeklySchedule().getScheduleEndDate();
                weekdays = ScheduleData.convertToWeekdays(scheduleData.getWeeklySchedule().getDaysOfWeek());
                ScheduleSequence scheduleSequence = new WeekScheduleSequence(scheduleData.getWeeklySchedule().getWeek(),weekdays.length);
                dates = scheduleService.getScheduledDates(dt, dtEnd, time, weekdays, scheduleSequence);
                break;
            case MONTHLY :
                MonthlyScheduleDetails.optionValues monthOption = MonthlyScheduleDetails.optionValues.valueOf(scheduleData.getMonthlySchedule().getMonthOption());
                switch(monthOption) {
                    case XDAYANDXMONTH :
                        dtEnd = scheduleData.getMonthlySchedule().getScheduleEndDate();
                        day = scheduleData.getMonthlySchedule().getDay();
                        frequency = scheduleData.getMonthlySchedule().getOption1Month();
                        dates = scheduleService.getScheduledDates(dt, dtEnd, time, day, frequency, null);
                        break;
                    case XDAYOFWEEKANDXMONTH :
                        dtEnd = scheduleData.getMonthlySchedule().getScheduleEndDate();
                        weekOfMonth = ScheduleData.getWeekOfMonth(scheduleData.getMonthlySchedule().getSelectedMonthsWeek());
                        dayOfWeek = ScheduleData.getDayOfWeek(scheduleData.getMonthlySchedule().getSelectedDayOfWeek());
                        frequency = scheduleData.getMonthlySchedule().getOption2Month();
                        dates = scheduleService.getScheduledDates(dt, dtEnd, time, dayOfWeek, weekOfMonth, frequency, null);
                        break;
                }
                break;
            case YEARLY : 
                YearlyScheduleDetails.yearOptionValues yearOption = YearlyScheduleDetails.yearOptionValues.valueOf(scheduleData.getYearlySchedule().getYearOption());
                switch(yearOption) {
                    case XDAY :
                        dtEnd = scheduleData.getYearlySchedule().getScheduleEndDate();
                        month = ScheduleData.getMonthOfWeek(scheduleData.getYearlySchedule().getSelectedOption1Month());
                        day = scheduleData.getYearlySchedule().getDay();
                        frequency = scheduleData.getYearlySchedule().getOption1Year();
                        dates = scheduleService.getScheduledDates(dt, dtEnd, time, month, day, frequency, null);
                        break;
                    case CMPLX:
                        dtEnd = scheduleData.getYearlySchedule().getScheduleEndDate();
                        weekOfMonth = ScheduleData.getWeekOfMonth(scheduleData.getYearlySchedule().getSelectedMonthsWeek());
                        dayOfWeek = ScheduleData.getDayOfWeek(scheduleData.getYearlySchedule().getSelectedDayOfWeek());
                        month = ScheduleData.getMonthOfWeek(scheduleData.getYearlySchedule().getSelectedOption2Month());
                        frequency = scheduleData.getYearlySchedule().getOption2Year();
                        dates = scheduleService.getScheduledDates(dt, dtEnd, time, weekOfMonth, dayOfWeek, month, frequency, null);
                        break;
                }
                break;            
        }
    
        addScheduleDatesToCommittee(dates, committee, scheduleData.getPlace());

    }
    
    private Time24HrFmt getTime24hFmt(Date date, int min) throws ParseException{
        Date dt  = DateUtils.round(date, Calendar.DAY_OF_MONTH);            
        dt = DateUtils.addMinutes(dt, min);
        Calendar cl = new GregorianCalendar();
        cl.setTime(dt);
        StringBuffer sb = new StringBuffer();
        String str = sb.append(cl.get(Calendar.HOUR_OF_DAY)).append(":").append(cl.get(Calendar.MINUTE)).toString();       
        return new Time24HrFmt(str); 
    }
  
    private void addScheduleDatesToCommittee(List<Date> dates, Committee committee, String location){
        for(Date date: dates) {
            java.sql.Date sqldate = new java.sql.Date(date.getTime());
            
            if(!isDateAvailable(committee.getCommitteeSchedules(), sqldate))
                continue;
            
            CommitteeSchedule committeeSchedule = new CommitteeSchedule();
            committeeSchedule.setCommitteeId(committee.getId());            
            committeeSchedule.setScheduledDate(sqldate);
            committeeSchedule.setPlace(location);
            committeeSchedule.setTime(new Timestamp(date.getTime()));

            int daysToAdd = committee.getAdvancedSubmissionDaysRequired();
            java.sql.Date sqlDate = calculateAdvancedSubmissionDays(date, daysToAdd);
            committeeSchedule.setProtocolSubDeadline(sqlDate);

            ScheduleStatus defaultStatus = getDefaultScheduleStatus();
            committeeSchedule.setScheduleStatusCode(defaultStatus.getScheduleStatusCode());
            committeeSchedule.setScheduleStatus(defaultStatus);
                         
            committee.getCommitteeSchedules().add(committeeSchedule);            
        }
    }
    
    private Boolean isDateAvailable(List<CommitteeSchedule>committeeSchedules, java.sql.Date date){
        boolean retVal = true;
        for(CommitteeSchedule committeeSchedule: committeeSchedules) {
            if(DateUtils.isSameDay(committeeSchedule.getScheduledDate(), date)){
                retVal = false;
                break;
            }
        }
        return retVal;
    }
    
    private java.sql.Date calculateAdvancedSubmissionDays(Date startDate, Integer days){
        Date deadlineDate = DateUtils.addDays(startDate, -days);
        return new java.sql.Date(deadlineDate.getTime());
    }
    
    @SuppressWarnings("unchecked")
    private ScheduleStatus getDefaultScheduleStatus(){
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("description", "Scheduled");
        List<ScheduleStatus> scheduleStatuses = (List<ScheduleStatus>)businessObjectService.findMatching(ScheduleStatus.class, fieldValues);
        return scheduleStatuses.get(0);
    }
}
