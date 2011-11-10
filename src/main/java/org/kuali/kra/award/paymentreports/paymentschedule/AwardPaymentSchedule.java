/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.paymentreports.paymentschedule;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;

import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.award.paymentreports.ReportStatus;
import org.kuali.kra.bo.KcPerson;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * 
 * This class represents the AwardPaymentSchedule business object.
 */
/**
 * This class...
 */
public class AwardPaymentSchedule extends AwardAssociate { 
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1387310207139506329L;
    private Long awardPaymentScheduleId; 
    private Date dueDate; 
    private KualiDecimal amount; 
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
    /**
     * submittedByPerson is not persisted!
     */
    private KcPerson submittedByPerson;
    
    
    private ReportStatus reportStatus;
    
    /**
     * 
     * Constructs a AwardPaymentSchedule.java.
     */
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

    public KualiDecimal getAmount() {
        return amount;
    }
    
    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.awardPaymentScheduleId = null;
    }

    public void setAmount(KualiDecimal amount) {
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

    @Override 
    protected LinkedHashMap<String,Object> toStringMapper() {
        LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();
        hashMap.put("awardPaymentScheduleId", getAwardPaymentScheduleId());
        hashMap.put("dueDate", getDueDate());
        hashMap.put("amount", getAmount());
        hashMap.put("submitDate", getSubmitDate());
        hashMap.put("submittedBy", getSubmittedBy());
        hashMap.put("invoiceNumber", getInvoiceNumber());
        hashMap.put("statusDescription", getStatusDescription());
        hashMap.put("status", getStatus());
        return hashMap;
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

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((getAwardNumber() == null) ? 0 : getAwardNumber().hashCode());
        result = PRIME * result + ((dueDate == null) ? 0 : dueDate.hashCode());
        result = PRIME * result + ((getSequenceNumber() == null) ? 0 : getSequenceNumber().hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
}