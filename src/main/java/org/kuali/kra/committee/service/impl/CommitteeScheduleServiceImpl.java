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
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.scheduling.expr.util.CronSpecialChars;
import org.kuali.kra.scheduling.sequence.DefaultScheduleSequence;
import org.kuali.kra.scheduling.sequence.ScheduleSequence;
import org.kuali.kra.scheduling.sequence.TrimDatesScheduleSequenceDecorator;
import org.kuali.kra.scheduling.sequence.WeekScheduleSequenceDecorator;
import org.kuali.kra.scheduling.service.ScheduleService;
import org.kuali.kra.scheduling.util.Time24HrFmt;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Committee Service implementation.
 */
@Transactional
public class CommitteeScheduleServiceImpl implements CommitteeScheduleService {
    
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CommitteeScheduleServiceImpl.class);
    
    private static final String COLON = ":";
    
    private static final String DESCRIPTION = "description";
    
    private static final String SCHEDULED = "Scheduled";
    
    private BusinessObjectService businessObjectService;

    private ScheduleService scheduleService;
    
    /**
     * Set the Business Object Service.
     * @param businessObjectService the Business Object Service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }    
    
    /**
     * Set the Schedule Service.
     * @param scheduleService
     */
    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * @see org.kuali.kra.committee.service.CommitteeScheduleService#isCommitteeScheduleDeletable(org.kuali.kra.committee.bo.CommitteeSchedule)
     */
    public Boolean isCommitteeScheduleDeletable(CommitteeSchedule committeeSchedule){
        
        boolean retVal = false;
        
        retVal = !isProtocolAssignedToScheduleDate(committeeSchedule);
        
        retVal = retVal && !isScheduleDateInPast(committeeSchedule);

        return retVal;
    }
    
    /**
     * Helper method to check if schedule date is in past.
     * @param committeeSchedule
     * @return
     */
    private Boolean isScheduleDateInPast(CommitteeSchedule committeeSchedule){
        boolean retVal = true;
        Date dt = new Date();
        dt = DateUtils.addDays(dt, -1);
        java.sql.Date today = new java.sql.Date(dt.getTime());        
        if(committeeSchedule.getScheduledDate().after(today)) {
               retVal = false;
        }        
        return retVal;
    }
    
    /**
     * Helper method to check if Protocol is assigned to CommitteeSchedule.
     * @param committeeSchedule
     * @return
     */
    private Boolean isProtocolAssignedToScheduleDate(CommitteeSchedule committeeSchedule){
        boolean retVal = true;
        List<Protocol> list = committeeSchedule.getProtocols();
        if(null == list || list.size() == 0)
            retVal = false;
        return retVal;
    }

    /**
     * @see org.kuali.kra.committee.service.CommitteeScheduleService#addSchedule(org.kuali.kra.committee.web.struts.form.schedule.ScheduleData, org.kuali.kra.committee.bo.Committee)
     */
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
                        weekdays = ScheduleData.convertToWeekdays(scheduleData.getDailySchedule().getDaysOfWeek());
                        ScheduleSequence scheduleSequence = new WeekScheduleSequenceDecorator(new TrimDatesScheduleSequenceDecorator(new DefaultScheduleSequence()),1,weekdays.length);
                        dates = scheduleService.getScheduledDates(dt, dtEnd, time, weekdays, scheduleSequence);
                        break;
                }
                break;
            case WEEKLY :
                dtEnd = scheduleData.getWeeklySchedule().getScheduleEndDate();
                weekdays = ScheduleData.convertToWeekdays(scheduleData.getWeeklySchedule().getDaysOfWeek());
                ScheduleSequence scheduleSequence = new WeekScheduleSequenceDecorator(new TrimDatesScheduleSequenceDecorator(new DefaultScheduleSequence()),scheduleData.getWeeklySchedule().getWeek(),weekdays.length);
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
        List<java.sql.Date> skippedDates = new ArrayList<java.sql.Date>();
        scheduleData.setDatesInConflict(skippedDates);
        addScheduleDatesToCommittee(dates, committee, scheduleData.getPlace(),skippedDates);

    }
    
    /**
     * Helper method to convert date and minutes into Time24HrFmt object.
     * @param date
     * @param min
     * @return
     * @throws ParseException
     */
    private Time24HrFmt getTime24hFmt(Date date, int min) throws ParseException{
        Date dt  = DateUtils.round(date, Calendar.DAY_OF_MONTH);            
        dt = DateUtils.addMinutes(dt, min);
        Calendar cl = new GregorianCalendar();
        cl.setTime(dt);
        StringBuffer sb = new StringBuffer();
        String str = sb.append(cl.get(Calendar.HOUR_OF_DAY)).append(COLON).append(cl.get(Calendar.MINUTE)).toString();       
        return new Time24HrFmt(str); 
    }
  
    /**
     * Helper method to add schedule to list.
     * @param dates
     * @param committee
     * @param location
     * @param skippedDates
     */
    private void addScheduleDatesToCommittee(List<Date> dates, Committee committee, String location, List<java.sql.Date> skippedDates){
        for(Date date: dates) {
            java.sql.Date sqldate = new java.sql.Date(date.getTime());
            
            if(!isDateAvailable(committee.getCommitteeSchedules(), sqldate)){
                skippedDates.add(sqldate);
                continue;
            }
          
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
    
    /**
     * Helper method to test if date is available (non conflicting).
     * @param committeeSchedules
     * @param date
     * @return
     */
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
    
    /**
     * Helper method to calculate advanced submission days.
     * @param startDate
     * @param days
     * @return
     */
    private java.sql.Date calculateAdvancedSubmissionDays(Date startDate, Integer days){
        Date deadlineDate = DateUtils.addDays(startDate, -days);
        return new java.sql.Date(deadlineDate.getTime());
    }
    
    /**
     * Helper method to retrieve default ScheduleStatus object.
     * @return
     */
    @SuppressWarnings("unchecked")
    private ScheduleStatus getDefaultScheduleStatus(){
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(DESCRIPTION, SCHEDULED);
        List<ScheduleStatus> scheduleStatuses = (List<ScheduleStatus>)businessObjectService.findMatching(ScheduleStatus.class, fieldValues);
        return scheduleStatuses.get(0);
    }
}
