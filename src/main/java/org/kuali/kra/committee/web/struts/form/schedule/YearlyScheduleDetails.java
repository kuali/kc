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
package org.kuali.kra.committee.web.struts.form.schedule;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;
import org.kuali.kra.committee.web.struts.form.schedule.util.ScheduleOptionsUtil;

public class YearlyScheduleDetails extends ScheduleDetails {
    
    private int day;
    
    private int option1Year;
    
    private int option2Year;
    
    private String yearOption;
    
    public static String[] yearOptionValues = {"XDAY","CMPLX"};
    
    //private List<LabelValueBean> months;
    
    private String selectedOption1Month;
    
    private String selectedOption2Month;
   
    //private List<LabelValueBean> monthsweek;
    
    private String selectedMonthsWeek;
    
    //private List<LabelValueBean> dayofweek;
    
    private String selectedDayOfWeek; 
    
    public YearlyScheduleDetails() {
        super();
        this.setYearOption(yearOptionValues[0]);
        
        this.setDay(6);
        this.setOption1Year(1);
        this.setOption2Year(1);
        
        //this.setMonths(new ArrayList<LabelValueBean>());
        //ScheduleOptionsUtil.populate(months, ScheduleOptionsUtil.mths);
        this.setSelectedOption1Month(ScheduleOptionsUtil.mths[0]);
        this.setSelectedOption2Month(ScheduleOptionsUtil.mths[0]);
        
        //this.setMonthsweek(new ArrayList<LabelValueBean>());
        //ScheduleOptionsUtil.populate(monthsweek, ScheduleOptionsUtil.mthsweek);
        this.setSelectedMonthsWeek(ScheduleOptionsUtil.mthsweek[0]);
        
        //this.setDayofweek(new ArrayList<LabelValueBean>());
        //ScheduleOptionsUtil.populate(dayofweek, ScheduleOptionsUtil.dyofweek);
        this.setSelectedDayOfWeek(ScheduleOptionsUtil.dyofweek[4]);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    
    public int getOption1Year() {
        return option1Year;
    }

    public void setOption1Year(int option1Year) {
        this.option1Year = option1Year;
    }

    public void setOption2Year(int option2Year) {
        this.option2Year = option2Year;
    }

    public int getOption2Year() {
        return option2Year;
    }
    
    public String getYearOption() {
        return yearOption;
    }

    public void setYearOption(String yearOption) {
        this.yearOption = yearOption;
    }

/*    public List<LabelValueBean> getMonths() {
        return months;
    }

    public void setMonths(List<LabelValueBean> months) {
        this.months = months;
    }*/
    
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
    
/*    public List<LabelValueBean> getMonthsweek() {
        return monthsweek;
    }

    public void setMonthsweek(List<LabelValueBean> monthsweek) {
        this.monthsweek = monthsweek;
    }*/

    public String getSelectedMonthsWeek() {
        return selectedMonthsWeek;
    }

    public void setSelectedMonthsWeek(String selectedMonthsWeek) {
        this.selectedMonthsWeek = selectedMonthsWeek;
    }

/*    public List<LabelValueBean> getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(List<LabelValueBean> dayofweek) {
        this.dayofweek = dayofweek;
    }*/

    public String getSelectedDayOfWeek() {
        return selectedDayOfWeek;
    }

    public void setSelectedDayOfWeek(String selectedDayOfWeek) {
        this.selectedDayOfWeek = selectedDayOfWeek;
    }

}
