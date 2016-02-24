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
package org.kuali.kra.award.paymentreports.awardreports.reporting.service;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;

import java.sql.Date;
import java.util.Calendar;

public class SentReportNotification extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 3646683642938736073L;

    private Long awardReportNotifSentId;
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

    public Long getAwardReportNotifSentId() {
        return awardReportNotifSentId;
    }

    public void setAwardReportNotifSentId(Long awardReportNotifSentId) {
        this.awardReportNotifSentId = awardReportNotifSentId;
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
