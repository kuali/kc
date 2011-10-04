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

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.committee.service.impl.CommitteeScheduleServiceImpl;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt.MERIDIEM;
import org.kuali.kra.committee.web.struts.form.schedule.util.ScheduleOptionsUtil;
import org.kuali.kra.scheduling.expr.util.CronSpecialChars;
import org.kuali.kra.scheduling.service.ScheduleService;
import org.kuali.kra.scheduling.util.Time24HrFmt;

/**
 * This class is form data helper class used to store UI based date for recurrence.
 */
public abstract class ScheduleData implements Serializable {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ScheduleData.class);
    
    public static final String NONE = "display: none; background:#f4f4f4; border:solid; border-color:#CCCCCC; border-width:1px; padding:5px";
    
    public static final String BLOCK = "display: block; background:#f4f4f4; border:solid; border-color:#CCCCCC; border-width:1px; padding:5px";
    
    public static final String DEFAULTTIME = "12:00";
    
    protected Date scheduleStartDate;
    
    private Time12HrFmt time;
    
    private String place;
    
    private String recurrenceType;
    
    private Map<String,String> styleClasses;
    
    protected DailyScheduleDetails dailySchedule;
    
    protected WeeklyScheduleDetails weeklySchedule;
    
    protected MonthlyScheduleDetails monthlySchedule;
    
    protected YearlyScheduleDetails yearlySchedule;
    
    private Date filterStartDate;
    
    private Date filterEndDate;    
    
    private List<Date> datesInConflict;

    public ScheduleData() {
        super();
        this.setScheduleStartDate(new Date(new java.util.Date().getTime()));
        
        this.setTime(new Time12HrFmt(DEFAULTTIME,MERIDIEM.PM));
        
        this.setRecurrenceType(StyleKey.NEVER.toString());
        this.setStyleClasses(new HashMap<String,String>());
        populateStyleClass();
        
        this.setDailySchedule(new DailyScheduleDetails());
        this.setWeeklySchedule(new WeeklyScheduleDetails());
        this.setMonthlySchedule(new MonthlyScheduleDetails());
        this.setYearlySchedule(new YearlyScheduleDetails());
    }
    
    public Map<String,String> getStyleClasses() {
        return styleClasses;
    }
    
    public void setStyleClasses(Map<String,String> styleClasses) {
        this.styleClasses = styleClasses;
    }

    public String getRecurrenceType() {
        return recurrenceType;
    }

    public void setRecurrenceType(String recurrenceType) {
        this.recurrenceType = recurrenceType;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setScheduleStartDate(Date scheduleStartDate) {
        this.scheduleStartDate = scheduleStartDate;       
    }

    public Date getScheduleStartDate() {
        return scheduleStartDate;
    }

    protected void setDailySchedule(DailyScheduleDetails dailySchedule) {
        this.dailySchedule = dailySchedule;
    }

    protected DailyScheduleDetails getDailySchedule() {
        return dailySchedule;
    }

    protected void setWeeklySchedule(WeeklyScheduleDetails weeklySchedule) {
        this.weeklySchedule = weeklySchedule;
    }

    protected WeeklyScheduleDetails getWeeklySchedule() {
        return weeklySchedule;
    }

    protected void setMonthlySchedule(MonthlyScheduleDetails monthlySchedule) {
        this.monthlySchedule = monthlySchedule;
    }

    protected MonthlyScheduleDetails getMonthlySchedule() {
        return monthlySchedule;
    }

    protected void setYearlySchedule(YearlyScheduleDetails yearlySchedule) {
        this.yearlySchedule = yearlySchedule;
    }

    protected YearlyScheduleDetails getYearlySchedule() {
        return yearlySchedule;
    }
    
    public Date getFilterStartDate() {
        return filterStartDate;
    }

    public void setFilterStartDate(Date filterStartDate) {
        this.filterStartDate = filterStartDate;
    }

    public Date getFilterEndDate() {
        return filterEndDate;
    }

    public void setFilterEndDate(Date filterEndDate) {
        this.filterEndDate = filterEndDate;
    }
    
    public Time12HrFmt getTime() {
        return time;
    }

    public void setTime(Time12HrFmt time) {
        this.time = time;
    }
    
    public List<Date> getDatesInConflict() {
        return datesInConflict;
    }

    public void setDatesInConflict(List<Date> datesInConflict) {
        this.datesInConflict = datesInConflict;
    }
    
    /**
     * Util method to support css style class.
     */
    public void populateStyleClass(){
        
        for(StyleKey str: StyleKey.values()) {
            getStyleClasses().put(str.toString(), NONE);
        }
        for(StyleKey str: StyleKey.values()) {
            if (str.equalsString(getRecurrenceType())) {
                getStyleClasses().put(str.toString(), BLOCK);
                break;
            }
        }      
    }

    public static CronSpecialChars getMonthOfWeek(String month) {
        return ScheduleOptionsUtil.getMonthOfWeek(month);
    }
    
    public static CronSpecialChars getDayOfWeek(String dayOfWeek) {
        return ScheduleOptionsUtil.getDayOfWeek(dayOfWeek);
    }
    
    public static CronSpecialChars getWeekOfMonth(String monthsWeek) {
        return ScheduleOptionsUtil.getWeekOfMonth(monthsWeek);
    }
    
    public static CronSpecialChars[] convertToWeekdays(String [] daysOfWeek){
        return ScheduleOptionsUtil.convertToWeekdays(daysOfWeek);
    }
    
    public List<java.util.Date> getScheduleDates(ScheduleService scheduleService, java.sql.Date dt, Time24HrFmt time) throws ParseException {
        return null;
    }

}
