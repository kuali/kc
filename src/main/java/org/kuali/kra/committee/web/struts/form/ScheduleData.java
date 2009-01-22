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
package org.kuali.kra.committee.web.struts.form;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts.util.LabelValueBean;

public class ScheduleData {
    
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ScheduleData.class);
    
    private Date scheduleStartDate;
       
    private String startTime;
    
    private List<LabelValueBean> timeSlots;
    
    private String place;
    
    private String recurrenceType;
    
    private Map<String,String> styleClasses;
    
    public static final String[] stylekey = {"NEVER","DAILY","WEEKLY","MONTHLY","YEARLY"};
    
    private DailyScheduleDetails dailySchedule;
    
    private WeeklyScheduleDetails weeklySchedule;
    
    private MonthlyScheduleDetails monthlySchedule;
    
    private YearlyScheduleDetails yearlySchedule;

    public ScheduleData() {
        super();
        this.setScheduleStartDate(new Date(new java.util.Date().getTime()));
        
        this.setTimeSlots(new ArrayList<LabelValueBean>());
        ScheduleOptionsUtil.populate(timeSlots, ScheduleOptionsUtil.time);
        
        this.setRecurrenceType(stylekey[0]);
        this.setStyleClasses(new HashMap<String,String>());
        populateStyleClass();
        
        this.setDailySchedule(new DailyScheduleDetails());
        this.setWeeklySchedule(new WeeklyScheduleDetails());
        this.setMonthlySchedule(new MonthlyScheduleDetails());
        this.setYearlySchedule(new YearlyScheduleDetails());
    }
    
    @SuppressWarnings("unchecked")
    public Map getStyleClasses() {
        return styleClasses;
    }
    
    @SuppressWarnings("unchecked")
    public void setStyleClasses(Map styleClasses) {
        this.styleClasses = styleClasses;
    }

    public String getRecurrenceType() {
        return recurrenceType.toString();
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

    public List<LabelValueBean> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<LabelValueBean> timeSlots) {
        this.timeSlots = timeSlots;
    }
    
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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
    
    @SuppressWarnings("unchecked")
    public void populateStyleClass(){
        
        for(String str: stylekey) {
            this.getStyleClasses().put(str, "display: none; background:#f4f4f4; border:solid; border-color:#CCCCCC; border-width:1px; padding:5px");
        }
        for(String str: stylekey) {
            if (getRecurrenceType().equalsIgnoreCase(str)) {
                this.getStyleClasses().put(str, "display: block; background:#f4f4f4; border:solid; border-color:#CCCCCC; border-width:1px; padding:5px");
                break;
            }
        }
        //TODO take it out
        Set s = this.getStyleClasses().keySet();
        Iterator itr = s.iterator();
        while(itr.hasNext()) {
            String obj = (String)itr.next();
            LOG.info("STYLE : ==== :" + obj.toString() + " ===== :" + getStyleClasses().get(obj));
        }        
    }
    
    public void printf() {
        LOG.info("=========================================================");
        LOG.info("ScheduleStartDate Date is :" + scheduleStartDate.toString());
        LOG.info("Start time is :" + startTime);
        LOG.info("Place is :" + place);
        LOG.info("recurrenceType is :" + recurrenceType);
        LOG.info("=========================================================");
        LOG.info("Schedule Daily End Date is :" + dailySchedule.getScheduleEndDate());
        LOG.info("Day is :" + dailySchedule.getDay());
        LOG.info("Radio Button Option is :" + dailySchedule.getDayOption());
        LOG.info("=========================================================");
        LOG.info("Schedule Weekly End Date is :" + weeklySchedule.getScheduleEndDate());
        LOG.info("Week is :" + weeklySchedule.getWeek());
        LOG.info("Days is :" + Arrays.toString(weeklySchedule.getDaysOfWeek()));
        LOG.info("=========================================================");
        LOG.info("Schedule Monthly End Date is :" + monthlySchedule.getScheduleEndDate());
        LOG.info("Day is :" + monthlySchedule.getDay());
        LOG.info("Month is :" + monthlySchedule.getMonth());
        LOG.info("Radio Button Option is :" + monthlySchedule.getMonthOption());
        LOG.info("Month's Week is :" + monthlySchedule.getSelectedMonthsWeek());
        LOG.info("Day Of Week is :" + monthlySchedule.getSelectedDayOfWeek());
        LOG.info("=========================================================");
        LOG.info("Schedule Yearly End Date is :" + yearlySchedule.getScheduleEndDate());
        LOG.info("Day is :" + yearlySchedule.getDay());
        LOG.info("Year is :" + yearlySchedule.getYear());
        LOG.info("Radio Button Option is :" + yearlySchedule.getYearOption());
        LOG.info("Month is :" + yearlySchedule.getSelectedMonth());
        LOG.info("Month's Week is :" + yearlySchedule.getSelectedMonthsWeek());
        LOG.info("Day Of Week is :" + yearlySchedule.getSelectedDayOfWeek());
        LOG.info("=========================================================");
    }
}
