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
 * This class holds yearly recurrence UI data.
 */
public class YearlyScheduleDetails extends ScheduleDetails {
    
    private Integer day;
    
    private Integer option1Year;
    
    private Integer option2Year;
    
    private String yearOption;
    
    public static enum yearOptionValues {XDAY,CMPLX};
    
    private String selectedOption1Month;
    
    private String selectedOption2Month;
    
    private String selectedMonthsWeek;
    
    private String selectedDayOfWeek; 
    
    public YearlyScheduleDetails() {
        super();
        this.setYearOption(yearOptionValues.XDAY.toString());
        
        this.setDay(6);
        this.setOption1Year(1);
        this.setOption2Year(1);

        this.setSelectedOption1Month(Months.JANUARY.toString());
        this.setSelectedOption2Month(Months.JANUARY.toString());

        this.setSelectedMonthsWeek(WeekOfMonth.first.toString());

        this.setSelectedDayOfWeek(DayOfWeek.Monday.toString());
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
    
    public Integer getOption1Year() {
        return option1Year;
    }

    public void setOption1Year(Integer option1Year) {
        this.option1Year = option1Year;
    }

    public void setOption2Year(Integer option2Year) {
        this.option2Year = option2Year;
    }

    public Integer getOption2Year() {
        return option2Year;
    }
    
    public String getYearOption() {
        return yearOption;
    }

    public void setYearOption(String yearOption) {
        this.yearOption = yearOption;
    }
    
    public String getSelectedOption1Month() {
        return selectedOption1Month;
    }

    public void setSelectedOption1Month(String selectedOption1Month) {
        this.selectedOption1Month = selectedOption1Month;
    }


    public void setSelectedOption2Month(String selectedOption2Month) {
        this.selectedOption2Month = selectedOption2Month;
    }

    public String getSelectedOption2Month() {
        return selectedOption2Month;
    }

    public String getSelectedMonthsWeek() {
        return selectedMonthsWeek;
    }

    public void setSelectedMonthsWeek(String selectedMonthsWeek) {
        this.selectedMonthsWeek = selectedMonthsWeek;
    }

    public String getSelectedDayOfWeek() {
        return selectedDayOfWeek;
    }

    public void setSelectedDayOfWeek(String selectedDayOfWeek) {
        this.selectedDayOfWeek = selectedDayOfWeek;
    }

}
