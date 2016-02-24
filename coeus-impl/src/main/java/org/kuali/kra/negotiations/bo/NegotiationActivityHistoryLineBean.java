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
package org.kuali.kra.negotiations.bo;

import org.kuali.rice.kns.web.ui.ResultRow;

import java.io.Serializable;
import java.sql.Date;

/**
 * 
 * This class contains all the attributes for a line in the activity history table.
 */
public class NegotiationActivityHistoryLineBean extends ResultRow implements Comparable<NegotiationActivityHistoryLineBean>, Serializable {

    private static final long serialVersionUID = 1497207089018881667L;
    
    private String activityType;
    private String location;
    private Date startDate;
    private Date endDate;
    private String activityDays;
    private Date efectiveLocationStartDate;
    private Date efectiveLocationEndDate;
    private String locationDays;
    private String lineNumber;
    

    public NegotiationActivityHistoryLineBean() {
        super(null, "", "");
    }
    
    /**
     * 
     * Constructs a NegotiationActivityHistoryLineBean.java.
     * @param negotiationActivity
     */
    public NegotiationActivityHistoryLineBean(NegotiationActivity negotiationActivity) {
        this();
        this.setActivityType(negotiationActivity.getActivityType().getDescription());
        this.setLocation(negotiationActivity.getLocation().getDescription());
        this.setStartDate(negotiationActivity.getStartDate());
        this.setEndDate(negotiationActivity.getEndDate());
        this.setActivityDays(negotiationActivity.getNumberOfDays());
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

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public int compareTo(NegotiationActivityHistoryLineBean o) {
        int retVal = this.getLocation().compareTo(o.getLocation());
        if (retVal == 0 && this.startDate != null) {
            retVal = this.getStartDate().compareTo(o.getStartDate());
        }
        if (retVal == 0 && this.getEndDate() != null && o.getEndDate() != null) {
            retVal = this.getEndDate().compareTo(o.getEndDate());
        }
        if (retVal == 0 && this.getActivityType() != null) {
            retVal = this.getActivityType().compareTo(o.getActivityType());
        }
        return retVal;
    }
}
