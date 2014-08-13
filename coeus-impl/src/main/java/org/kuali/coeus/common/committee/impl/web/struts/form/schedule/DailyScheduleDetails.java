/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.committee.impl.web.struts.form.schedule;

/**
 * This class holds daily recurrence UI data.  
 */
public class DailyScheduleDetails extends ScheduleDetails {
    
    @SuppressWarnings("unused")
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(DailyScheduleDetails.class);
    
    private int defaultDay;

    private Integer day;
    
    private String dayOption;
    
    private String[] daysOfWeek; 
    
    public static enum optionValues {XDAY,WEEKDAY};
    
    public DailyScheduleDetails() {
        super();
        this.setDefaultDay(1);
        this.setDay(this.getDefaultDay());
        this.setDayOption(optionValues.XDAY.toString());
        this.daysOfWeek = new String[5];
        this.getDaysOfWeek()[0] = DayOfWeek.Monday.name();
        this.getDaysOfWeek()[1] = DayOfWeek.Tuesday.name();
        this.getDaysOfWeek()[2] = DayOfWeek.Wednesday.name();
        this.getDaysOfWeek()[3] = DayOfWeek.Thursday.name();
        this.getDaysOfWeek()[4] = DayOfWeek.Friday.name();
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getDay() {
        return day;
    }

    public int getDefaultDay() {
        return defaultDay;
    }

    public void setDefaultDay(int defaultDay) {
        this.defaultDay = defaultDay;
    }
    
    public void setDayOption(String dayOption) {
        this.dayOption = dayOption;
    }

    public String getDayOption() {
        return dayOption;
    }
    
    public String[] getDaysOfWeek() {
        return daysOfWeek;
    }
}
