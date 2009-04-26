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
package org.kuali.kra.committee.bo;

import org.kuali.rice.kns.bo.AttributeReferenceDummy;

public class CommitteeScheduleAttributeReferenceDummy extends AttributeReferenceDummy {

    private static final long serialVersionUID = 4004201645215456568L;

    private Integer intValue;
    
    private String monthsWeek;
    
    private String weekDay;
    
    private String month;
    
    private String time;
    
    private String meridiem;
    
    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setMonthsWeek(String monthsWeek) {
        this.monthsWeek = monthsWeek;
    }

    public String getMonthsWeek() {
        return monthsWeek;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMeridiem() {
        return meridiem;
    }

    public void setMeridiem(String meridiem) {
        this.meridiem = meridiem;
    }



}
