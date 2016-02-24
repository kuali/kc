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
package org.kuali.coeus.common.committee.impl.web.struts.form.schedule;

import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.Time12HrFmt.MERIDIEM;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.util.ScheduleOptionsUtil;
import org.kuali.coeus.sys.framework.scheduling.util.CronSpecialChars;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is form data helper class used to store UI based date for recurrence.
 */
public class ScheduleData implements Serializable {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ScheduleData.class);
    
    public static final String NONE = "display: none; background:#f4f4f4; border:solid; border-color:#CCCCCC; border-width:1px; padding:5px";
    
    public static final String BLOCK = "display: block; background:#f4f4f4; border:solid; border-color:#CCCCCC; border-width:1px; padding:5px";
    
    public static final String DEFAULTTIME = "12:00";
    
    private Date scheduleStartDate;
    
    private Time12HrFmt time;
    
    private String place;
    
    private String recurrenceType;
    
    private Map<String,String> styleClasses;
    
    private DailyScheduleDetails dailySchedule;
    
    private WeeklyScheduleDetails weeklySchedule;
    
    private MonthlyScheduleDetails monthlySchedule;
    
    private YearlyScheduleDetails yearlySchedule;
    
    private Date filterStartDate;
    
    private Date filerEndDate;    
    
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

    public void setDailySchedule(DailyScheduleDetails dailySchedule) {
        this.dailySchedule = dailySchedule;
    }

    public DailyScheduleDetails getDailySchedule() {
        return dailySchedule;
    }

    public void setWeeklySchedule(WeeklyScheduleDetails weeklySchedule) {
        this.weeklySchedule = weeklySchedule;
    }

    public WeeklyScheduleDetails getWeeklySchedule() {
        return weeklySchedule;
    }

    public void setMonthlySchedule(MonthlyScheduleDetails monthlySchedule) {
        this.monthlySchedule = monthlySchedule;
    }

    public MonthlyScheduleDetails getMonthlySchedule() {
        return monthlySchedule;
    }

    public void setYearlySchedule(YearlyScheduleDetails yearlySchedule) {
        this.yearlySchedule = yearlySchedule;
    }

    public YearlyScheduleDetails getYearlySchedule() {
        return yearlySchedule;
    }
    
    public Date getFilterStartDate() {
        return filterStartDate;
    }

    public void setFilterStartDate(Date filterStartDate) {
        this.filterStartDate = filterStartDate;
    }

    public Date getFilerEndDate() {
        return filerEndDate;
    }

    public void setFilerEndDate(Date filerEndDate) {
        this.filerEndDate = filerEndDate;
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
    
    public void printf() {
        LOG.info("=========================================================");
        LOG.info("ScheduleStartDate Date is :" + scheduleStartDate.toString());
        LOG.info("Start time is :" + getTime().getTime() + getTime().getMeridiem());
        LOG.info("Place is :" + place);
        LOG.info("recurrenceType is :" + recurrenceType);
        LOG.info("=========================================================");
        LOG.info("Schedule Daily End Date is :" + dailySchedule.getScheduleEndDate());
        LOG.info("Day is :" + dailySchedule.getDay());
        LOG.info("Radio Button Option is :" + dailySchedule.getDayOption());
        LOG.info("=========================================================");
        LOG.info("Schedule Weekly End Date is :" + weeklySchedule.getScheduleEndDate());
        LOG.info("Week is :" + weeklySchedule.getWeek());
        LOG.info("Days is :" + weeklySchedule.getDaysOfWeek().toString());
        LOG.info("=========================================================");
        LOG.info("Schedule Monthly End Date is :" + monthlySchedule.getScheduleEndDate());
        LOG.info("Day is :" + monthlySchedule.getDay());
        LOG.info("Month Option1 is :" + monthlySchedule.getOption1Month());
        LOG.info("Month Option2 is :" + monthlySchedule.getOption2Month());
        LOG.info("Radio Button Option is :" + monthlySchedule.getMonthOption());
        LOG.info("Month's Week is :" + monthlySchedule.getSelectedMonthsWeek());
        LOG.info("Day Of Week is :" + monthlySchedule.getSelectedDayOfWeek());
        LOG.info("=========================================================");
        LOG.info("Schedule Yearly End Date is :" + yearlySchedule.getScheduleEndDate());
        LOG.info("Day is :" + yearlySchedule.getDay());
        LOG.info("Year Option1 is :" + yearlySchedule.getOption1Year());
        LOG.info("Year Option2 is :" + yearlySchedule.getOption2Year());
        LOG.info("Radio Button Option is :" + yearlySchedule.getYearOption());
        LOG.info("Month Option1 is :" + yearlySchedule.getSelectedOption1Month());
        LOG.info("Month Option2 is :" + yearlySchedule.getSelectedOption2Month());
        LOG.info("Month's Week is :" + yearlySchedule.getSelectedMonthsWeek());
        LOG.info("Day Of Week is :" + yearlySchedule.getSelectedDayOfWeek());
        LOG.info("=========================================================");
    }
}
