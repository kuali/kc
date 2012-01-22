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

import java.util.LinkedHashMap;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;

import org.apache.struts.upload.FormFile;
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.bo.KcAttachment;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KcAttachmentService;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.rice.core.api.util.type.KualiDecimal;

public class SubAwardAmountReleased  extends SubAwardAssociate implements KcAttachment{ 

    private static final long serialVersionUID = 1L;

    private Integer subAwardAmtReleasedId;
    private Long subAwardId; 
    private String subAwardCode;
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
    private AttachmentFile file;
    private SubAward SubAward;
    private String contentType;
    transient private FormFile newFile;

    public SubAwardAmountReleased() {
    }

    public Integer getSubAwardAmtReleasedId() {
        return subAwardAmtReleasedId;
    }

    public void setSubAwardAmtReleasedId(Integer subAwardAmtReleasedId) {
        this.subAwardAmtReleasedId = subAwardAmtReleasedId;
    }

    public Long getSubAwardId() {
        return subAwardId;
    }

    public void setSubAwardId(Long subAwardId) {
        this.subAwardId = subAwardId;
    }

    public String getSubAwardCode() {
        return subAwardCode;
    }

    public void setSubAwardCode(String subAwardCode) {
        this.subAwardCode = subAwardCode;
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

    public KualiDecimal getAmountReleased() {
        return amountReleased;
    }

    public void setAmountReleased(KualiDecimal amountReleased) {
        this.amountReleased = amountReleased;
    }
    /**
     * Gets the  Attachment File.
     */
    public AttachmentFile getFile() {
        return this.file;
    }
    
    /**
     * Sets the  Attachment File.
     */
    public void setFile(AttachmentFile file) {
        this.file = file;
    }

    /**
     * 
     * This method used to populate the attachment to subAwardReleased object by reading FormFile 
     */
    public void populateAttachment() {
        FormFile newFile = getNewFile();
        if(newFile==null) return;
        byte[] newFileData;
        try {
            newFileData = newFile.getFileData();
            setDocument(newFileData);
            if (newFileData.length > 0) {
                setContentType(newFile.getContentType());
                setFileName(newFile.getFileName());
            }
        }catch (FileNotFoundException e) {
        }catch (IOException e) {
        }
    }
    
    public FormFile getNewFile() {
        return this.newFile;
    }

    
    public void setNewFile(FormFile newFile) {
        this.newFile = newFile;
    }
    
    @Override
    public void resetPersistenceState() {
        
        this.subAwardAmtReleasedId = null;
    }

    @Override
    public String getName() {
        return getFileName();
    }

    @Override
    public String getType() {
        return getContentType();
}
    @Override
    public byte[] getData() {
        return getDocument();
    }

    @Override
    public String getIconPath() {
        return KraServiceLocator.getService(KcAttachmentService.class).getFileTypeIcon(this);
    }
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }
}