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
 * This class holds monthly recurrence UI data.
 */
public class MonthlyScheduleDetails extends ScheduleDetails {
    
    private Integer day;
    
    private Integer option1Month;
    
    private Integer option2Month;
    
    private String monthOption;
    
    public static enum optionValues {XDAYANDXMONTH, XDAYOFWEEKANDXMONTH};
    
    private String selectedMonthsWeek;
    
    private String selectedDayOfWeek;    
    
    public MonthlyScheduleDetails() {
        super();
        this.monthOption = optionValues.XDAYANDXMONTH.toString();
        this.setDay(6);
        this.setOption1Month(1);
        this.setOption2Month(1);

        this.setSelectedMonthsWeek(WeekOfMonth.first.toString());

        this.setSelectedDayOfWeek(DayOfWeek.Monday.toString());
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getDay() {
        return day;
    }

    public void setOption1Month(Integer option1Month) {
        this.option1Month = option1Month;
    }

    public Integer getOption1Month() {
        return option1Month;
    }

    public void setOption2Month(Integer option2Month) {
        this.option2Month = option2Month;
    }

    public Integer getOption2Month() {
        return option2Month;
    }

    public void setMonthOption(String monthOption) {
        this.monthOption = monthOption;
    }

    public String getMonthOption() {
        return monthOption;
    }

    public void setSelectedMonthsWeek(String selectedMonthsWeek) {
        this.selectedMonthsWeek = selectedMonthsWeek;
    }

    public String getSelectedMonthsWeek() {
        return selectedMonthsWeek;
    }

    public void setSelectedDayOfWeek(String selectedDayOfWeek) {
        this.selectedDayOfWeek = selectedDayOfWeek;
    }

    public String getSelectedDayOfWeek() {
        return selectedDayOfWeek;
    }   
}
