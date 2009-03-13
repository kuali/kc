/*
 * Copyright 2006-2008 The Kuali Foundation
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
import java.util.LinkedHashMap;

import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This class represents the AwardPaymentSchedule business object.
 */
public class AwardPaymentSchedule extends KraPersistableBusinessObjectBase { 
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1387310207139506329L;
    private Long awardPaymentScheduleId;
    private String awardNumber; 
    private Integer sequenceNumber; 
    private Date dueDate; 
    private KualiDecimal amount; 
    private Date submitDate; 
    private String submittedBy; 
    private String invoiceNumber; 
    private String statusDescription;
    private String status;
    
    private Award award; 
    
    /**
     * 
     * Constructs a AwardPaymentSchedule.java.
     */
    public AwardPaymentSchedule() { 

    }
    
    public AwardPaymentSchedule(Date dueDate, String awardNumber, Integer sequenceNumber){
        this.awardNumber = awardNumber;
        this.sequenceNumber = sequenceNumber; 
        this.dueDate = dueDate;
    }
    
    public Long getAwardPaymentScheduleId() {
        return awardPaymentScheduleId;
    }

    public void setAwardPaymentScheduleId(Long awardPaymentScheduleId) {
        this.awardPaymentScheduleId = awardPaymentScheduleId;
    }    

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
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

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
        if(award == null) {
            sequenceNumber = null;
            awardNumber = null;
        } else {
            sequenceNumber = award.getSequenceNumber();
            awardNumber = award.getAwardNumber();
        }
    }

    @Override 
    protected LinkedHashMap<String,Object> toStringMapper() {
        LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();
        hashMap.put("awardPaymentScheduleId", getAwardPaymentScheduleId());        
        hashMap.put("awardNumber", getAwardNumber());
        hashMap.put("sequenceNumber", getSequenceNumber());
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
     * Gets the awardNumber attribute. 
     * @return Returns the awardNumber.
     */
    public String getAwardNumber() {
        return awardNumber;
    }

    /**
     * Sets the awardNumber attribute value.
     * @param awardNumber The awardNumber to set.
     */
    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
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
        result = PRIME * result + ((awardNumber == null) ? 0 : awardNumber.hashCode());
        result = PRIME * result + ((dueDate == null) ? 0 : dueDate.hashCode());
        result = PRIME * result + ((sequenceNumber == null) ? 0 : sequenceNumber.hashCode());
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
        if (awardNumber == null) {
            if (awardPaymentSchedule.awardNumber != null){
                return false;
            }   
        }else if (!awardNumber.equals(awardPaymentSchedule.awardNumber)){
            return false;
        }   
        if (dueDate == null) {
            if (awardPaymentSchedule.dueDate != null){
                return false;
            }   
        }else if (!dueDate.equals(awardPaymentSchedule.dueDate)){
            return false;
        }   
        if (sequenceNumber == null) {
            if (awardPaymentSchedule.sequenceNumber != null){
                return false;
            }   
        }else if (!sequenceNumber.equals(awardPaymentSchedule.sequenceNumber)){
            return false;
        }   
        return true;
    }
    
}