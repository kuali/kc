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
import org.kuali.kra.committee.web.struts.form.DailyScheduleDetails;
import org.kuali.kra.committee.web.struts.form.MonthlyScheduleDetails;
import org.kuali.kra.committee.web.struts.form.ScheduleData;
import org.kuali.kra.committee.web.struts.form.ScheduleOptionsUtil;
import org.kuali.kra.committee.web.struts.form.WeeklyScheduleDetails;
import org.kuali.kra.scheduling.ScheduleSequence;
import org.kuali.kra.scheduling.WeekScheduleSequence;
import org.kuali.kra.scheduling.expr.CronSpecialChars;
import org.kuali.kra.scheduling.service.impl.ScheduleServiceImpl;

/**
 * The Committee Service implementation.
 */
public class CommitteeScheduleServiceImpl implements CommitteeScheduleService {
    
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CommitteeScheduleServiceImpl.class);
    
    private BusinessObjectService businessObjectService;
    
    /**
     * Set the Business Object Service.
     * @param businessObjectService the Business Object Service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }    
    
    public void addSchedule(ScheduleData scheduleData, Committee committee) throws ParseException {
        
        List<Date> dates = null;
        if(scheduleData.getRecurrenceType().equalsIgnoreCase(ScheduleData.stylekey[0])){
            ScheduleServiceImpl service = new ScheduleServiceImpl();
            Date dt = addTime(scheduleData.getScheduleStartDate(), scheduleData.calculateMinutes());
            dates = service.getScheduledDates(dt, dt, scheduleData.convert24HrTimeFmt(), null);
        }
        if(scheduleData.getRecurrenceType().equalsIgnoreCase(ScheduleData.stylekey[1]) 
                && scheduleData.getDailySchedule().getDayOption().equalsIgnoreCase(DailyScheduleDetails.optionValues[0])){
            ScheduleServiceImpl service = new ScheduleServiceImpl();
            Date dt = addTime(scheduleData.getScheduleStartDate(), scheduleData.calculateMinutes());
            Date dtEnd = addTime(scheduleData.getDailySchedule().getScheduleEndDate(), scheduleData.calculateMinutes());
            dates = service.getScheduledDates(dt, dtEnd, scheduleData.convert24HrTimeFmt(), scheduleData.getDailySchedule().getDay(), null);
        }
        if(scheduleData.getRecurrenceType().equalsIgnoreCase(ScheduleData.stylekey[1]) 
                && scheduleData.getDailySchedule().getDayOption().equalsIgnoreCase(DailyScheduleDetails.optionValues[1])){
            ScheduleServiceImpl service = new ScheduleServiceImpl();
            Date dt = addTime(scheduleData.getScheduleStartDate(), scheduleData.calculateMinutes());
            Date dtEnd = addTime(scheduleData.getDailySchedule().getScheduleEndDate(), scheduleData.calculateMinutes());
            dates = service.getScheduledDates(dt, dtEnd, scheduleData.convert24HrTimeFmt(), scheduleData.getDailySchedule().getDefaultDay(), null);
        }        
        if(scheduleData.getRecurrenceType().equalsIgnoreCase(ScheduleData.stylekey[2])){
            ScheduleServiceImpl service = new ScheduleServiceImpl();
            Date dt = addTime(scheduleData.getScheduleStartDate(), scheduleData.calculateMinutes());
            Date dtEnd = addTime(scheduleData.getWeeklySchedule().getScheduleEndDate(), scheduleData.calculateMinutes());
            CronSpecialChars[] weekdays = convertToWeekdays(scheduleData.getWeeklySchedule().getDaysOfWeek());
            ScheduleSequence scheduleSequence = new WeekScheduleSequence(scheduleData.getWeeklySchedule().getWeek(),weekdays.length);
            dates = service.getScheduledDates(dt, dtEnd, scheduleData.convert24HrTimeFmt(), weekdays, scheduleSequence);
        } 
        if(scheduleData.getRecurrenceType().equalsIgnoreCase(ScheduleData.stylekey[3]) 
                && scheduleData.getMonthlySchedule().getMonthOption().equalsIgnoreCase(MonthlyScheduleDetails.optionValues[0])){
            ScheduleServiceImpl service = new ScheduleServiceImpl();
            Date dt = addTime(scheduleData.getScheduleStartDate(), scheduleData.calculateMinutes());
            Date dtEnd = addTime(scheduleData.getMonthlySchedule().getScheduleEndDate(), scheduleData.calculateMinutes());
            dates = service.getScheduledDates(dt, dtEnd, scheduleData.convert24HrTimeFmt(), scheduleData.getMonthlySchedule().getDay(), scheduleData.getMonthlySchedule().getMonth(), null);
        }           
        if(scheduleData.getRecurrenceType().equalsIgnoreCase(ScheduleData.stylekey[3]) 
                && scheduleData.getMonthlySchedule().getMonthOption().equalsIgnoreCase(MonthlyScheduleDetails.optionValues[1])){
            ScheduleServiceImpl service = new ScheduleServiceImpl();
            Date dt = addTime(scheduleData.getScheduleStartDate(), scheduleData.calculateMinutes());
            Date dtEnd = addTime(scheduleData.getMonthlySchedule().getScheduleEndDate(), scheduleData.calculateMinutes());
            Integer weekOfMonth = getWeekOfMonth(scheduleData.getMonthlySchedule().getSelectedMonthsWeek());
            CronSpecialChars dayOfWeek = getDayOfWeek(scheduleData.getMonthlySchedule().getSelectedDayOfWeek());
            dates = service.getScheduledDates(dt, dtEnd, scheduleData.convert24HrTimeFmt(), dayOfWeek, weekOfMonth, scheduleData.getMonthlySchedule().getMonth(), null);
        }         
        
        for(Date date: dates) {
            LOG.info("DATE Time added:" + date);
            java.sql.Date sqldate = new java.sql.Date(date.getTime());
            CommitteeSchedule committeeSchedule = new CommitteeSchedule();
            committeeSchedule.setCommitteeId(committee.getId());            
            committeeSchedule.setScheduledDate(sqldate);
            committeeSchedule.setPlace(scheduleData.getPlace());
            committeeSchedule.setTime(new Timestamp(date.getTime()));
            
            //Deadline needs to be calculated
            int daysToAdd = committee.getAdvancedSubmissionDaysRequired();
            java.sql.Date sqlDate = calculateAdvancedSubmissionDays(date, daysToAdd);
            committeeSchedule.setProtocolSubDeadline(sqlDate);
            
            //Set Schedule type
            ScheduleStatus defaultStatus = getDefaultScheduleStatus();
            committeeSchedule.setScheduleStatusCode(defaultStatus.getScheduleStatusCode());
            committeeSchedule.setScheduleStatus(defaultStatus);
                         
            committee.getCommitteeSchedules().add(committeeSchedule);            
        }

    }
    
    private CronSpecialChars getDayOfWeek(String dayOfWeek) {
        CronSpecialChars i = null;
        if(dayOfWeek.equalsIgnoreCase(ScheduleOptionsUtil.dyofweek[0])) 
            i = CronSpecialChars.SUN;
        if(dayOfWeek.equalsIgnoreCase(ScheduleOptionsUtil.dyofweek[1])) 
            i = CronSpecialChars.MON;
        if(dayOfWeek.equalsIgnoreCase(ScheduleOptionsUtil.dyofweek[2])) 
            i = CronSpecialChars.TUE;
        if(dayOfWeek.equalsIgnoreCase(ScheduleOptionsUtil.dyofweek[3])) 
            i = CronSpecialChars.WED;
        if(dayOfWeek.equalsIgnoreCase(ScheduleOptionsUtil.dyofweek[4])) 
            i = CronSpecialChars.THR;   
        if(dayOfWeek.equalsIgnoreCase(ScheduleOptionsUtil.dyofweek[5])) 
            i = CronSpecialChars.FRI; 
        if(dayOfWeek.equalsIgnoreCase(ScheduleOptionsUtil.dyofweek[6])) 
            i = CronSpecialChars.SAT;         
        return i;
    }
    
    private Integer getWeekOfMonth(String monthsWeek) {
        int i = 0;
        if(monthsWeek.equalsIgnoreCase(ScheduleOptionsUtil.mthsweek[0])) 
            i = 1;
        if(monthsWeek.equalsIgnoreCase(ScheduleOptionsUtil.mthsweek[1])) 
            i = 2;
        if(monthsWeek.equalsIgnoreCase(ScheduleOptionsUtil.mthsweek[2])) 
            i = 3;
        if(monthsWeek.equalsIgnoreCase(ScheduleOptionsUtil.mthsweek[3])) 
            i = 4;
        if(monthsWeek.equalsIgnoreCase(ScheduleOptionsUtil.mthsweek[4])) 
            i = 5;      
        return i;
    }
    
    private CronSpecialChars[] convertToWeekdays(String [] daysOfWeek){        
        List<CronSpecialChars> weekdays = new ArrayList<CronSpecialChars>();
        for(String str: daysOfWeek) {
            if(null != str){
                if(WeeklyScheduleDetails.days[0].equalsIgnoreCase(str)) 
                    weekdays.add(CronSpecialChars.SUN);
                if(WeeklyScheduleDetails.days[1].equalsIgnoreCase(str)) 
                    weekdays.add(CronSpecialChars.MON);
                if(WeeklyScheduleDetails.days[2].equalsIgnoreCase(str)) 
                    weekdays.add(CronSpecialChars.TUE);
                if(WeeklyScheduleDetails.days[3].equalsIgnoreCase(str)) 
                    weekdays.add(CronSpecialChars.WED);
                if(WeeklyScheduleDetails.days[4].equalsIgnoreCase(str)) 
                    weekdays.add(CronSpecialChars.THR);
                if(WeeklyScheduleDetails.days[5].equalsIgnoreCase(str)) 
                    weekdays.add(CronSpecialChars.FRI);
                if(WeeklyScheduleDetails.days[6].equalsIgnoreCase(str)) 
                    weekdays.add(CronSpecialChars.SAT);
            }
        }
        CronSpecialChars [] ary = new CronSpecialChars[weekdays.size()];
        return weekdays.toArray(ary);
    }
    
    private Date addTime(Date date, int min){
        Date dt  = DateUtils.round(date, Calendar.DAY_OF_MONTH);            
        return DateUtils.addMinutes(dt, min);
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
