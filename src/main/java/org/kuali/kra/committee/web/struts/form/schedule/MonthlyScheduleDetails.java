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

public class MonthlyScheduleDetails extends ScheduleDetails {
    
    private int day;
    
    private int option1Month;
    
    private int option2Month;
    
    private String monthOption;
    
    public static String[] optionValues = {"XDAYANDXMONTH","XDAYOFWEEKANDXMONTH"};
    
    //private List<LabelValueBean> monthsweek;
    
    private String selectedMonthsWeek;
    
    //private List<LabelValueBean> dayofweek;
    
    private String selectedDayOfWeek;    
    
    public MonthlyScheduleDetails() {
        super();
        this.monthOption = optionValues[0];
        this.setDay(6);
        this.setOption1Month(1);
        this.setOption2Month(1);
        
        //this.setMonthsweek(new ArrayList<LabelValueBean>());
        //ScheduleOptionsUtil.populate(monthsweek, ScheduleOptionsUtil.mthsweek);
        this.setSelectedMonthsWeek(ScheduleOptionsUtil.mthsweek[0]);
        
        //this.setDayofweek(new ArrayList<LabelValueBean>());
        //ScheduleOptionsUtil.populate(dayofweek, ScheduleOptionsUtil.dyofweek);
        this.setSelectedDayOfWeek(ScheduleOptionsUtil.dyofweek[4]);
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public void setOption1Month(int option1Month) {
        this.option1Month = option1Month;
    }

    public int getOption1Month() {
        return option1Month;
    }

    public void setOption2Month(int option2Month) {
        this.option2Month = option2Month;
    }

    public int getOption2Month() {
        return option2Month;
    }

    public void setMonthOption(String monthOption) {
        this.monthOption = monthOption;
    }

    public String getMonthOption() {
        return monthOption;
    }

/*    public void setMonthsweek(List<LabelValueBean> monthsweek) {
        this.monthsweek = monthsweek;
    }

    public List<LabelValueBean> getMonthsweek() {
        return monthsweek;
    }*/

    public void setSelectedMonthsWeek(String selectedMonthsWeek) {
        this.selectedMonthsWeek = selectedMonthsWeek;
    }

    public String getSelectedMonthsWeek() {
        return selectedMonthsWeek;
    }

/*    public void setDayofweek(List<LabelValueBean> dayofweek) {
        this.dayofweek = dayofweek;
    }

    public List<LabelValueBean> getDayofweek() {
        return dayofweek;
    }*/

    public void setSelectedDayOfWeek(String selectedDayOfWeek) {
        this.selectedDayOfWeek = selectedDayOfWeek;
    }

    public String getSelectedDayOfWeek() {
        return selectedDayOfWeek;
    }   
}
