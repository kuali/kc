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
package org.kuali.kra.award.paymentreports.paymentschedule;

import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.award.paymentreports.ReportStatus;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 
 * This class represents the AwardPaymentSchedule business object.
 */

public class AwardPaymentSchedule extends AwardAssociate {


    private static final long serialVersionUID = 1387310207139506329L;
    private Long awardPaymentScheduleId; 
    private Date dueDate; 
    private ScaleTwoDecimal amount;
    private Date submitDate; 
    private String submittedBy; 
    private String invoiceNumber; 
    private String statusDescription;
    private String status;
    private Integer overdue;
    private String lastUpdateUser;
    private Timestamp lastUpdateTimestamp;
    private String reportStatusCode;
    private String submittedByPersonId;
    private String awardReportTermDescription;

    /**
     * submittedByPerson is not persisted!
     */
    private KcPerson submittedByPerson;
    
    
    private ReportStatus reportStatus;

    private transient KcPersonService kcPersonService;
    

    public AwardPaymentSchedule() { 
    }
    
    public AwardPaymentSchedule(Date dueDate, String awardNumber, Integer sequenceNumber){
        setAwardNumber(awardNumber);
        setSequenceNumber(sequenceNumber);
        this.dueDate = dueDate;
    }
    
    public Long getAwardPaymentScheduleId() {
        return awardPaymentScheduleId;
    }

    public void setAwardPaymentScheduleId(Long awardPaymentScheduleId) {
        this.awardPaymentScheduleId = awardPaymentScheduleId;
    }    

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public ScaleTwoDecimal getAmount() {
        return amount;
    }
    
    @Override
    public void resetPersistenceState() {
        this.awardPaymentScheduleId = null;
    }

    public void setAmount(ScaleTwoDecimal amount) {
        this.amount = amount;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    /**
     * Gets the status attribute. 
     * @return Returns the status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status attribute value.
     * @param status The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((getAwardNumber() == null) ? 0 : getAwardNumber().hashCode());
        result = PRIME * result + ((dueDate == null) ? 0 : dueDate.hashCode());
        result = PRIME * result + ((getSequenceNumber() == null) ? 0 : getSequenceNumber().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }   
        if (obj == null){
            return false;
        }   
        if (!(obj instanceof AwardPaymentSchedule)){
            return false;
        }
        return equals((AwardPaymentSchedule) obj);
    }
    
    /**
     * 
     * Convenience method to check equality of another AwardPaymentSchedule
     * @param awardPaymentSchedule
     * @return
     */
    public boolean equals(AwardPaymentSchedule awardPaymentSchedule) {
        if (getAwardNumber() == null) {
            if (awardPaymentSchedule.getAwardNumber() != null) {
                return false;
            }   
        } else if (!getAwardNumber().equals(awardPaymentSchedule.getAwardNumber())) {
            return false;
        }   
        if (dueDate == null) {
            if (awardPaymentSchedule.dueDate != null) {
                return false;
            }   
        } else if (!dueDate.equals(awardPaymentSchedule.dueDate)) {
            return false;
        }   
        if (getSequenceNumber() == null) {
            if (awardPaymentSchedule.getSequenceNumber() != null) {
                return false;
            }   
        } else if (!getSequenceNumber().equals(awardPaymentSchedule.getSequenceNumber())) {
            return false;
        }   
        return true;
    }
    
    public boolean checkForUpdates (AwardPaymentSchedule awardPaymentSchedule) {
        boolean retVal = true;
        if (awardPaymentSchedule == null || !objectCompare(this.getAwardPaymentScheduleId(), awardPaymentSchedule.getAwardPaymentScheduleId())) {
            throw new IllegalArgumentException("The passed in award payment schedule is null, or it has a different AwardPaymentScheduleId than this object.");
        }
        retVal &= objectCompare(this.getInvoiceNumber(), awardPaymentSchedule.getInvoiceNumber());
        retVal &= objectCompare(this.getDueDate(), awardPaymentSchedule.getDueDate());
        retVal &= objectCompare(this.getOverdue(), awardPaymentSchedule.getOverdue());
        retVal &= objectCompare(this.getStatusDescription(), awardPaymentSchedule.getStatusDescription());
        retVal &= objectCompare(this.getStatus(), awardPaymentSchedule.getStatus());
        retVal &= objectCompare(this.getAmount(), awardPaymentSchedule.getAmount());
        retVal &= objectCompare(this.getSubmittedBy(), awardPaymentSchedule.getSubmittedBy());
        retVal &= objectCompare(this.getSubmitDate(), awardPaymentSchedule.getSubmitDate());
        return !retVal;
    }
    
    /**
     * 
     * This method returns true if both objects are the same.  If both objects are null, they are the same.  If both objects are not
     * null and the object's equality method returns true, they are the same.  All other cases, they are not the same.
     * @param ob1
     * @param ob2
     * @return
     */
    private boolean objectCompare(Object ob1, Object ob2) {
        boolean retVal = false;
        if (ob1 == null && ob2 == null) {
            retVal = true; 
        } else  if (ob1 != null && ob2 != null && ob1.equals(ob2)) {
            retVal = true;
        }
        return retVal;
    }

    public Integer getOverdue() {
        return overdue;
    }

    public void setOverdue(Integer overdue) {
        this.overdue = overdue;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public Timestamp getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public void setLastUpdateTimestamp(Timestamp lastUpdateTimestamp) {
        this.lastUpdateTimestamp = lastUpdateTimestamp;
    }

    public String getReportStatusCode() {
        return reportStatusCode;
    }

    public void setReportStatusCode(String reportStatusCode) {
        this.reportStatusCode = reportStatusCode;
    }

    public String getSubmittedByPersonId() {
        return submittedByPersonId;
    }

    public void setSubmittedByPersonId(String submittedByPersonId) {
        this.submittedByPersonId = submittedByPersonId;
    }
    
    /**
     * 
     * This method returns a KC Person for the getSubmittedByPersonId().
     * @return
     */
    public KcPerson getSubmittedByPerson() {
        if (submittedByPerson == null && getSubmittedByPersonId() != null) {
            submittedByPerson = this.getKcPersonService().getKcPersonByPersonId(getSubmittedByPersonId());
        }
        return submittedByPerson;
    }
    
    public String getSubmittedByPersonUsername() {
        KcPerson submitter = getSubmittedByPerson();
        return submitter == null ? "" : submitter.getUserName();
    }
    
    public String getSubmittedByPersonFullname() {
        KcPerson submitter = getSubmittedByPerson();
        return submitter == null ? "" : submitter.getFullName();
    }

    public ReportStatus getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(ReportStatus reportStatus) {
        this.reportStatus = reportStatus;
    }

    /**
     * Looks up and returns the KcPersonService.
     * @return the person service.
     */
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }

    public String getAwardReportTermDescription() {
        return awardReportTermDescription;
    }

    public void setAwardReportTermDescription(String awardReportTermDescription) {
        this.awardReportTermDescription = awardReportTermDescription;
    }
}
