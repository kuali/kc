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
