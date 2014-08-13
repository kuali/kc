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
package org.kuali.kra.award.paymentreports.awardreports.reporting.service;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;

import java.sql.Date;
import java.util.Calendar;

public class SentReportNotification extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 3646683642938736073L;
    
    private Long awardReportTermId;
    private String awardNumber;
    private Date dueDate;
    private Date dateSent;
    private String actionCode;
    
    public SentReportNotification() {
        dateSent = new Date(Calendar.getInstance().getTimeInMillis());
    }
    
    public SentReportNotification(String actionCode, ReportTracking report) {
        dateSent = new Date(Calendar.getInstance().getTimeInMillis());
        this.actionCode = actionCode;
        this.awardReportTermId = report.getAwardReportTermId();
        this.awardNumber = report.getAwardNumber();
        this.dueDate = report.getDueDate();
    }
    
    public Long getAwardReportTermId() {
        return awardReportTermId;
    }
    public void setAwardReportTermId(Long awardReportTermId) {
        this.awardReportTermId = awardReportTermId;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public Date getDateSent() {
        return dateSent;
    }
    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }
    public String getActionCode() {
        return actionCode;
    }
    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getAwardNumber() {
        return awardNumber;
    }

    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }
    
}
