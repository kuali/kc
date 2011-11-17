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
package org.kuali.kra.award.paymentreports.awardreports.reporting;

import java.io.Serializable;
import java.sql.Date;

/**
 * 
 * This class is used for the multi edit of report tracking records.
 */
public class ReportTrackingBean implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
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
