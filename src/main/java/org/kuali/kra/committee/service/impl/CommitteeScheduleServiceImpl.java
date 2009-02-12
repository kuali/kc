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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.kuali.kra.committee.web.struts.form.schedule.WeeklyScheduleDetails;
import org.kuali.kra.committee.web.struts.form.schedule.YearlyScheduleDetails;
import org.kuali.kra.committee.web.struts.form.schedule.util.ScheduleOptionsUtil;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.scheduling.ScheduleSequence;
import org.kuali.kra.scheduling.WeekScheduleSequence;
import org.kuali.kra.scheduling.expr.CronSpecialChars;
import org.kuali.kra.scheduling.service.impl.ScheduleServiceImpl;

/**
 * The Committee Service implementation.
 */
public class CommitteeScheduleServiceImpl implements CommitteeScheduleService {
    
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CommitteeScheduleServiceImpl.class);
    
    public static final Boolean TRUE = true;
    
    public static final Boolean FALSE = false;
    
    private BusinessObjectService businessObjectService;
    
    private ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
    
    /**
     * Set the Business Object Service.
     * @param businessObjectService the Business Object Service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }    
    
    public void setScheduleService(ScheduleServiceImpl scheduleService) {
        this.scheduleService = scheduleService;
    }
    
    public void deleteCommitteeSchedule(Committee committee, int lineNumber) {
        committee.getCommitteeSchedules().remove(lineNumber);  
    }
    
    public void filterCommitteeScheduleDates(ScheduleData scheduleData, Committee committee){
        List<CommitteeSchedule> committeeSchedules = committee.getCommitteeSchedules();
        Date startDate = null;
        Date endDate = null;
        Date scheduleDate = null;
        for(CommitteeSchedule committeeSchedule : committeeSchedules) {
            startDate = scheduleData.getFilterStartDate();
            endDate = scheduleData.getFilerEndDate();
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
        Date dt = addTime(scheduleData.getScheduleStartDate(), scheduleData.calculateMinutes());
        String time24fmt = scheduleData.convert24HrTimeFmt();
        
        if(scheduleData.getRecurrenceType().equalsIgnoreCase(ScheduleData.stylekey[0])){       
            dates = scheduleService.getScheduledDates(dt, dt, time24fmt, null);
        }
        if(scheduleData.getRecurrenceType().equalsIgnoreCase(ScheduleData.stylekey[1]) 
                && scheduleData.getDailySchedule().getDayOption().equalsIgnoreCase(DailyScheduleDetails.optionValues[0])){
            Date dtEnd = addTime(scheduleData.getDailySchedule().getScheduleEndDate(), scheduleData.calculateMinutes());
            int day = scheduleData.getDailySchedule().getDay();
            dates = scheduleService.getScheduledDates(dt, dtEnd, time24fmt, day, null);
        }
        if(scheduleData.getRecurrenceType().equalsIgnoreCase(ScheduleData.stylekey[1]) 
                && scheduleData.getDailySchedule().getDayOption().equalsIgnoreCase(DailyScheduleDetails.optionValues[1])){
            Date dtEnd = addTime(scheduleData.getDailySchedule().getScheduleEndDate(), scheduleData.calculateMinutes());
            int day = scheduleData.getDailySchedule().getDay();
            dates = scheduleService.getScheduledDates(dt, dtEnd, time24fmt, day, null);
        }        
        if(scheduleData.getRecurrenceType().equalsIgnoreCase(ScheduleData.stylekey[2])){
            Date dtEnd = addTime(scheduleData.getWeeklySchedule().getScheduleEndDate(), scheduleData.calculateMinutes());
            CronSpecialChars[] weekdays = ScheduleData.convertToWeekdays(scheduleData.getWeeklySchedule().getDaysOfWeek());
            ScheduleSequence scheduleSequence = new WeekScheduleSequence(scheduleData.getWeeklySchedule().getWeek(),weekdays.length);
            dates = scheduleService.getScheduledDates(dt, dtEnd, time24fmt, weekdays, scheduleSequence);
        } 
        if(scheduleData.getRecurrenceType().equalsIgnoreCase(ScheduleData.stylekey[3]) 
                && scheduleData.getMonthlySchedule().getMonthOption().equalsIgnoreCase(MonthlyScheduleDetails.optionValues[0])){
             Date dtEnd = addTime(scheduleData.getMonthlySchedule().getScheduleEndDate(), scheduleData.calculateMinutes());
            int day = scheduleData.getMonthlySchedule().getDay();
            int frequency = scheduleData.getMonthlySchedule().getOption1Month();
            dates = scheduleService.getScheduledDates(dt, dtEnd, time24fmt, day, frequency, null);
        }           
        if(scheduleData.getRecurrenceType().equalsIgnoreCase(ScheduleData.stylekey[3]) 
                && scheduleData.getMonthlySchedule().getMonthOption().equalsIgnoreCase(MonthlyScheduleDetails.optionValues[1])){
            Date dtEnd = addTime(scheduleData.getMonthlySchedule().getScheduleEndDate(), scheduleData.calculateMinutes());
            CronSpecialChars weekOfMonth = ScheduleData.getWeekOfMonth(scheduleData.getMonthlySchedule().getSelectedMonthsWeek());
            CronSpecialChars dayOfWeek = ScheduleData.getDayOfWeek(scheduleData.getMonthlySchedule().getSelectedDayOfWeek());
            int frequency = scheduleData.getMonthlySchedule().getOption1Month();
            dates = scheduleService.getScheduledDates(dt, dtEnd, time24fmt, dayOfWeek, weekOfMonth, frequency, null);
        }         
        if(scheduleData.getRecurrenceType().equalsIgnoreCase(ScheduleData.stylekey[4]) 
                && scheduleData.getYearlySchedule().getYearOption().equalsIgnoreCase(YearlyScheduleDetails.yearOptionValues[0])){
            Date dtEnd = addTime(scheduleData.getYearlySchedule().getScheduleEndDate(), scheduleData.calculateMinutes());
            CronSpecialChars month = ScheduleData.getMonthOfWeek(scheduleData.getYearlySchedule().getSelectedOption1Month());
            int day = scheduleData.getYearlySchedule().getDay();
            int frequency = scheduleData.getYearlySchedule().getOption1Year();
            dates = scheduleService.getScheduledDates(dt, dtEnd, time24fmt, month, day, frequency, null);
        }    
        if(scheduleData.getRecurrenceType().equalsIgnoreCase(ScheduleData.stylekey[4]) 
                && scheduleData.getYearlySchedule().getYearOption().equalsIgnoreCase(YearlyScheduleDetails.yearOptionValues[1])){
            Date dtEnd = addTime(scheduleData.getYearlySchedule().getScheduleEndDate(), scheduleData.calculateMinutes());
            CronSpecialChars weekOfMonth = ScheduleData.getWeekOfMonth(scheduleData.getYearlySchedule().getSelectedMonthsWeek());
            CronSpecialChars dayOfWeek = ScheduleData.getDayOfWeek(scheduleData.getYearlySchedule().getSelectedDayOfWeek());
            CronSpecialChars month = ScheduleData.getMonthOfWeek(scheduleData.getYearlySchedule().getSelectedOption2Month());
            int frequency = scheduleData.getYearlySchedule().getOption2Year();
            dates = scheduleService.getScheduledDates(dt, dtEnd, time24fmt, weekOfMonth, dayOfWeek, month, frequency, null);
        }         
        addScheduleDatesToCommittee(dates, committee, scheduleData.getPlace());

    }
    
    private Date addTime(Date date, int min){
        Date dt  = DateUtils.round(date, Calendar.DAY_OF_MONTH);            
        return DateUtils.addMinutes(dt, min);
    }
  
    private void addScheduleDatesToCommittee(List<Date> dates, Committee committee, String location){
        for(Date date: dates) {
            java.sql.Date sqldate = new java.sql.Date(date.getTime());
            CommitteeSchedule committeeSchedule = new CommitteeSchedule();
            committeeSchedule.setCommitteeId(committee.getId());            
            committeeSchedule.setScheduledDate(sqldate);
            committeeSchedule.setPlace(location);
            LOG.info("Date before adding :" + date.toString());
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
    
    private java.sql.Date calculateAdvancedSubmissionDays(Date startDate, Integer days){
        Date deadlineDate = DateUtils.addDays(startDate, days);
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
