/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.negotiations.bo;

import java.io.Serializable;
import java.sql.Date;

/**
 * 
 * This class contains all the attributes for a line in the activity history table.
 */
public class NegotiationActivityHistoryLineBean implements Comparable<NegotiationActivityHistoryLineBean>, Serializable {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1497207089018881667L;
    private static final String EMPTY_STRING = "";
    
    private String activityType;
    private String location;
    private Date startDate;
    private Date endDate;
    private String activityDays;
    private Date efectiveLocationStartDate;
    private Date efectiveLocationEndDate;
    private String locationDays;
    
    /**
     * 
     * Constructs a NegotiationActivityHistoryLineBean.java.
     */
    public NegotiationActivityHistoryLineBean() {
        
    }
    
    /**
     * 
     * Constructs a NegotiationActivityHistoryLineBean.java.
     * @param negotiationActivity
     */
    public NegotiationActivityHistoryLineBean(NegotiationActivity negotiationActivity) {
        this.setActivityType(negotiationActivity.getActivityType().getDescription());
        this.setLocation(negotiationActivity.getLocation().getDescription());
        this.setStartDate(negotiationActivity.getStartDate());
        this.setEndDate(negotiationActivity.getEndDate());
        this.setActivityDays(negotiationActivity.getNumberOfDays());
        //this.setEfectiveLocationStartDate()
        //this.setEfectiveLocationEndDate()
        this.setLocationDays(negotiationActivity.getNumberOfDays());
    }

    public String getActivityType() {
        return activityType;
    }



    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }



    public String getLocation() {
        return location;
    }



    public void setLocation(String location) {
        this.location = location;
    }



    public Date getStartDate() {
        return startDate;
    }



    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }



    public Date getEndDate() {
        return endDate;
    }



    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }



    public String getActivityDays() {
        return activityDays;
    }



    public void setActivityDays(String activityDays) {
        this.activityDays = activityDays;
    }



    public Date getEfectiveLocationStartDate() {
        return efectiveLocationStartDate;
    }



    public void setEfectiveLocationStartDate(Date efectiveLocationStartDate) {
        this.efectiveLocationStartDate = efectiveLocationStartDate;
    }



    public Date getEfectiveLocationEndDate() {
        return efectiveLocationEndDate;
    }



    public void setEfectiveLocationEndDate(Date efectiveLocationEndDate) {
        this.efectiveLocationEndDate = efectiveLocationEndDate;
    }



    public String getLocationDays() {
        return locationDays;
    }



    public void setLocationDays(String locationDays) {
        this.locationDays = locationDays;
    }



    @Override
    public int compareTo(NegotiationActivityHistoryLineBean o) {
        int retVal = this.getLocation().compareTo(o.getLocation());
        if (retVal == 0) {
            retVal = this.getStartDate().compareTo(o.getStartDate());
        }
        if (retVal == 0) {
            retVal = this.getEndDate().compareTo(o.getEndDate());
        }
        if (retVal == 0) {
            retVal = this.getActivityType().compareTo(o.getActivityType());
        }
        return retVal;
    }
}
