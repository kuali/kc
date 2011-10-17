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
package org.kuali.kra.subaward.bo;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import java.util.LinkedHashMap;
import java.sql.Date;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.rice.kns.util.KualiDecimal;

public class SubAwardAmountReleased extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private Integer subAwardAmtReleasedId; 
    private Integer subAwardId; 
    private String subAwardCode; 
    private Integer sequenceNumber; 
    private Integer lineNumber; 
    private KualiDecimal amountReleased; 
   

    private Date effectiveDate; 
    private String comments; 
    private String invoiceNumber; 
    private Date startDate; 
    private Date endDate; 
    private boolean statusCode; 
    private String approvalComments; 
    private String approvedByUser; 
    private Date approvalDate; 
    private byte[] document; 
    private String fileName; 
    private String createdBy; 
    private Date createdDate; 
    private String mimeType; 
    
    private SubAward SubAward; 
    
    public SubAwardAmountReleased() { 

    } 
    
    public Integer getSubAwardAmtReleasedId() {
        return subAwardAmtReleasedId;
    }

    public void setSubAwardAmtReleasedId(Integer subAwardAmtReleasedId) {
        this.subAwardAmtReleasedId = subAwardAmtReleasedId;
    }

    public Integer getSubAwardId() {
        return subAwardId;
    }

    public void setSubAwardId(Integer subAwardId) {
        this.subAwardId = subAwardId;
    }

    public String getSubAwardCode() {
        return subAwardCode;
    }

    public void setSubAwardCode(String subAwardCode) {
        this.subAwardCode = subAwardCode;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }


    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
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

    public boolean getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(boolean statusCode) {
        this.statusCode = statusCode;
    }

    public String getApprovalComments() {
        return approvalComments;
    }

    public void setApprovalComments(String approvalComments) {
        this.approvalComments = approvalComments;
    }

    public String getApprovedByUser() {
        return approvedByUser;
    }

    public void setApprovedByUser(String approvedByUser) {
        this.approvedByUser = approvedByUser;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public SubAward getSubAward() {
        return SubAward;
    }

    public void setSubAward(SubAward SubAward) {
        this.SubAward = SubAward;
    }
    
    public KualiDecimal getAmountReleased() {
        return amountReleased;
    }

    public void setAmountReleased(KualiDecimal amountReleased) {
        this.amountReleased = amountReleased;
    }
    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("subAwardAmtReleasedId", this.getSubAwardAmtReleasedId());
        hashMap.put("subAwardId", this.getSubAwardId());
        hashMap.put("subAwardCode", this.getSubAwardCode());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("lineNumber", this.getLineNumber());
        hashMap.put("amountReleased", this.getAmountReleased());
        hashMap.put("effectiveDate", this.getEffectiveDate());
        hashMap.put("comments", this.getComments());
        hashMap.put("invoiceNumber", this.getInvoiceNumber());
        hashMap.put("startDate", this.getStartDate());
        hashMap.put("endDate", this.getEndDate());
        hashMap.put("statusCode", this.getStatusCode());
        hashMap.put("approvalComments", this.getApprovalComments());
        hashMap.put("approvedByUser", this.getApprovedByUser());
        hashMap.put("approvalDate", this.getApprovalDate());
        hashMap.put("document", this.getDocument());
        hashMap.put("fileName", this.getFileName());
        hashMap.put("createdBy", this.getCreatedBy());
        hashMap.put("createdDate", this.getCreatedDate());
        hashMap.put("mimeType", this.getMimeType());
        return hashMap;
    }
    
}