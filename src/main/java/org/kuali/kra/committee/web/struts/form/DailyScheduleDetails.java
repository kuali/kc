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

public class DailyScheduleDetails extends ScheduleDetails {
    
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(DailyScheduleDetails.class);
    
    private int defaultDay;

    private int day;
    
    private String dayOption;
    
    public static final String[] optionValues = {"XDAY","WEEKDAY"};
    
    public DailyScheduleDetails() {
        super();
        this.setDefaultDay(1);
        this.setDay(this.getDefaultDay());
        this.setDayOption(optionValues[0]);
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDay() {
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

}
