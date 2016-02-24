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
