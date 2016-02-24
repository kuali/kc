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
package org.kuali.kra.award.paymentreports.awardreports.reporting;

import java.io.Serializable;
import java.sql.Date;

/**
 * 
 * This class is used for the multi edit of report tracking records.
 */
public class ReportTrackingBean implements Serializable {


    private static final long serialVersionUID = -2415483170400832422L;
    private String preparerId;
    private String preparerName;
    private String statusCode;
    private Date activityDate;
    private String comments;
    public String getPreparerId() {
        return preparerId;
    }
    public void setPreparerId(String preparerId) {
        this.preparerId = preparerId;
    }
    
    public String getPreparerName() {
        return preparerName;
    }
    public void setPreparerName(String preparerName) {
        this.preparerName = preparerName;
    }
    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    public Date getActivityDate() {
        return activityDate;
    }
    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    
}
