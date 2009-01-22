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

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ScheduleData {
    
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ScheduleData.class);
    
    private Date scheduleStartDate;
    
    private Date scheduleEndDate;
    
    private Date scheduleEndDateDaily;
    
    private List<TimeSlot> timeSlots;
    
    private String place;
    
    private String recurrenceType;
    
    private Map<StyleKey,String> styleClasses;
    
    public enum StyleKey {NEVER,DAILY,WEEKLY,MONTHLY,YEARLY};

    public ScheduleData() {
        super();
        //TODO --kiltesh
        this.setScheduleStartDate(new Date(new java.util.Date().getTime()));
        this.setScheduleEndDateDaily(new Date(new java.util.Date().getTime()));
        this.setScheduleEndDate(new Date(new java.util.Date().getTime()));
        this.setTimeSlots(new ArrayList<TimeSlot>());
        this.populateTime(this.getTimeSlots());
        this.setRecurrenceType("Never");
        this.setStyleClasses(new HashMap<StyleKey,String>());
        populateStyleClass();
    }
    
    public Map getStyleClasses() {
        return styleClasses;
    }
    
    public void setStyleClasses(Map styleClasses) {
        this.styleClasses = styleClasses;
    }

    public String getRecurrenceType() {
        return recurrenceType.toString();
    }

    public void setRecurrenceType(String recurrenceType) {
        this.recurrenceType = recurrenceType;
        LOG.info("recurrenceType is  : =============== :" + recurrenceType);
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
        LOG.info("Place is  : =============== :" + place);
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    private String startTime;
    
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
        LOG.info("Start time is  : =============== :" + startTime);
    }

    public Date getScheduleEndDate() {
        return scheduleEndDate;
    }

    public void setScheduleEndDate(Date scheduleEndDate) {
        this.scheduleEndDate = scheduleEndDate;
        LOG.info("ScheduleEndDate Date is : =============== :" + scheduleEndDate.toString());
    }

    public void setScheduleStartDate(Date scheduleStartDate) {
        this.scheduleStartDate = scheduleStartDate;
        LOG.info("ScheduleStartDate Date is : =============== :" + scheduleStartDate.toString());
    }

    public Date getScheduleStartDate() {
        return scheduleStartDate;
    }
    
    private void populateTime(List<TimeSlot> timeSlots) {
        timeSlots.add(new TimeSlot("Select"));
        timeSlots.add(new TimeSlot("12:00 PM"));
        timeSlots.add(new TimeSlot("12:30 PM"));
    }
    
    public void populateStyleClass(){
        this.getStyleClasses().put(StyleKey.NEVER.toString(),    "display: none; background:#f4f4f4; border:solid; border-color:#CCCCCC; border-width:1px; padding:5px");
        this.getStyleClasses().put(StyleKey.DAILY.toString(),    "display: none; background:#f4f4f4; border:solid; border-color:#CCCCCC; border-width:1px; padding:5px");
        this.getStyleClasses().put(StyleKey.WEEKLY.toString(),   "display: none; background:#f4f4f4; border:solid; border-color:#CCCCCC; border-width:1px; padding:5px");
        this.getStyleClasses().put(StyleKey.MONTHLY.toString(),  "display: none; background:#f4f4f4; border:solid; border-color:#CCCCCC; border-width:1px; padding:5px");
        this.getStyleClasses().put(StyleKey.YEARLY.toString(),   "display: none; background:#f4f4f4; border:solid; border-color:#CCCCCC; border-width:1px; padding:5px");
        
        if(getRecurrenceType().equalsIgnoreCase(StyleKey.NEVER.toString()))
            this.getStyleClasses().put(StyleKey.NEVER.toString(),   "display: block; background:#f4f4f4; border:solid; border-color:#CCCCCC; border-width:1px; padding:5px");
        if(getRecurrenceType().equalsIgnoreCase(StyleKey.DAILY.toString()))
            this.getStyleClasses().put(StyleKey.DAILY.toString(),   "display: block; background:#f4f4f4; border:solid; border-color:#CCCCCC; border-width:1px; padding:5px");
        if(getRecurrenceType().equalsIgnoreCase(StyleKey.WEEKLY.toString()))
            this.getStyleClasses().put(StyleKey.WEEKLY.toString(),   "display: block; background:#f4f4f4; border:solid; border-color:#CCCCCC; border-width:1px; padding:5px");
        if(getRecurrenceType().equalsIgnoreCase(StyleKey.MONTHLY.toString()))
            this.getStyleClasses().put(StyleKey.MONTHLY.toString(),   "display: block; background:#f4f4f4; border:solid; border-color:#CCCCCC; border-width:1px; padding:5px");
        if(getRecurrenceType().equalsIgnoreCase(StyleKey.YEARLY.toString()))
            this.getStyleClasses().put(StyleKey.YEARLY.toString(),   "display: block; background:#f4f4f4; border:solid; border-color:#CCCCCC; border-width:1px; padding:5px");
        
        Set s = this.getStyleClasses().keySet();
        Iterator itr = s.iterator();
        while(itr.hasNext()) {
            String obj = (String)itr.next();
            LOG.info("STYLE : ==== :" + obj.toString() + " ===== :" + getStyleClasses().get(obj));
        }
    }

    public Date getScheduleEndDateDaily() {
        return scheduleEndDateDaily;
    }

    public void setScheduleEndDateDaily(Date scheduleEndDateDaily) {
        this.scheduleEndDateDaily = scheduleEndDateDaily;
        LOG.info("ScheduleStartDate Date is : =============== :" + scheduleEndDateDaily.toString());
    }
}
