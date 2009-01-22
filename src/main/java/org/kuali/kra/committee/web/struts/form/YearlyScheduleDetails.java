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

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

public class YearlyScheduleDetails extends ScheduleDetails {
    
    private int day;
    
    private int year;
    
    private String yearOption;
    
    private List<LabelValueBean> months;
    
    private String selectedMonth;
    
    private List<LabelValueBean> monthsweek;
    
    private String selectedMonthsWeek;
    
    private List<LabelValueBean> dayofweek;
    
    private String selectedDayOfWeek; 
    
    public YearlyScheduleDetails() {
        super();
        this.setDay(6);
        this.setYear(1);
        
        this.setMonths(new ArrayList<LabelValueBean>());
        ScheduleOptionsUtil.populate(months, ScheduleOptionsUtil.mths);
        this.setSelectedMonth(ScheduleOptionsUtil.mths[0]);
        
        this.setMonthsweek(new ArrayList<LabelValueBean>());
        ScheduleOptionsUtil.populate(monthsweek, ScheduleOptionsUtil.mthsweek);
        this.setSelectedMonthsWeek(ScheduleOptionsUtil.mthsweek[0]);
        
        this.setDayofweek(new ArrayList<LabelValueBean>());
        ScheduleOptionsUtil.populate(dayofweek, ScheduleOptionsUtil.dyofweek);
        this.setSelectedDayOfWeek(ScheduleOptionsUtil.dyofweek[4]);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getYearOption() {
        return yearOption;
    }

    public void setYearOption(String yearOption) {
        this.yearOption = yearOption;
    }

    public List<LabelValueBean> getMonths() {
        return months;
    }

    public void setMonths(List<LabelValueBean> months) {
        this.months = months;
    }

    public String getSelectedMonth() {
        return selectedMonth;
    }

    public void setSelectedMonth(String selectedMonth) {
        this.selectedMonth = selectedMonth;
    }

    public List<LabelValueBean> getMonthsweek() {
        return monthsweek;
    }

    public void setMonthsweek(List<LabelValueBean> monthsweek) {
        this.monthsweek = monthsweek;
    }

    public String getSelectedMonthsWeek() {
        return selectedMonthsWeek;
    }

    public void setSelectedMonthsWeek(String selectedMonthsWeek) {
        this.selectedMonthsWeek = selectedMonthsWeek;
    }

    public List<LabelValueBean> getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(List<LabelValueBean> dayofweek) {
        this.dayofweek = dayofweek;
    }

    public String getSelectedDayOfWeek() {
        return selectedDayOfWeek;
    }

    public void setSelectedDayOfWeek(String selectedDayOfWeek) {
        this.selectedDayOfWeek = selectedDayOfWeek;
    }
}
