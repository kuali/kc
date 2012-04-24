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
/**
 * This class represents a subAwardAmountReleased.  It mainly deals with the
 * Amount released for a subAward.
 */
public class SubAwardAmountReleased  extends
SubAwardAssociate implements KcAttachment {

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
/**.
     * This is the SubAwardAmountReleased() constructor
     */
    public SubAwardAmountReleased() {
    }
    /**
     * Get the SubAwardAmtReleasedId.
     * @return the subAwardAmtReleasedIds.
     */
    public Integer getSubAwardAmtReleasedId() {
        return subAwardAmtReleasedId;
    }
    /**
     * Set the subAwardAmtReleasedId..
     * @param subAwardAmtReleasedId the subAwardAmtReleasedId to be set
*/
    public void setSubAwardAmtReleasedId(Integer subAwardAmtReleasedId) {
        this.subAwardAmtReleasedId = subAwardAmtReleasedId;
    }
    /**
     * Get the subAwardId.
     * @return the subAwardId.
*/
    public Long getSubAwardId() {
        return subAwardId;
    }
    /**
     * Set the subAwardId attribute value..
     * @param subAwardId the subAwardId to be set
     */
    public void setSubAwardId(Long subAwardId) {
        this.subAwardId = subAwardId;
    }
    /**
     * Get the subAwardCode.
     * @return the subAwardCode.
     */
    public String getSubAwardCode() {
        return subAwardCode;
    }
    /**
     * Set the subAwardCode attribute value..
     * @param subAwardCode the subAwardCode to set
     */
    public void setSubAwardCode(String subAwardCode) {
        this.subAwardCode = subAwardCode;
    }

    /**
     * Get the lineNumber.
     * @return the lineNumber.
     */
    public Integer getLineNumber() {
        return lineNumber;
    }
    /**
     * Set the lineNumber..
     * @param lineNumber the lineNumber to be set
     */
    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }
    /**
     * Get the effectiveDate.
     * @return the effectiveDate.
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }
    /**
     * Set the effectiveDate attribute value..
     * @param effectiveDate the effectiveDate to be set
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    /**
     * Get the comments.
     * @return the comments.
     */
    public String getComments() {
        return comments;
    }
    /**
     * Set the comments attribute value..
     * @param comments the comments to be set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    /**
     * Get the invoiceNumber.
     * @return the invoiceNumber.
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }
    /**
     * Set the invoiceNumber attribute value..
     * @param invoiceNumber the invoiceNumber to be set
     */
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    /**
     * Get the startDate.
     * @return the startDate.
     */
    public Date getStartDate() {
        return startDate;
    }
    /**
     * Set the startDate..
     * @param startDate the startDate to be set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    /**
     * Get the endDate.
     * @return the endDate.
     */
    public Date getEndDate() {
        return endDate;
    }
    /**
     * Set the endDate attribute value..
     * @param endDate the endDate to be set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    /**
     * Get the statusCode.
     * @return the statusCode.
     */
    public boolean getStatusCode() {
        return statusCode;
    }
    /**
     * Set the statusCode attribute value..
     * @param statusCode the statusCode to be set
     */
    public void setStatusCode(boolean statusCode) {
        this.statusCode = statusCode;
    }
    /**
     * Get the approvalComments.
     * @return the approvalComments.
     */
    public String getApprovalComments() {
        return approvalComments;
    }
    /**
     * Set the approvalComments..
     * @param approvalComments the approvalComments to be set
     */
    public void setApprovalComments(String approvalComments) {
        this.approvalComments = approvalComments;
    }
    /**
     * Get the approvedByUser.
     * @return the approvedByUser.
     */
    public String getApprovedByUser() {
        return approvedByUser;
    }
    /**
     * Set the approvedByUser attribute value..
     * @param approvedByUser the approvedByUser to be set
     */
    public void setApprovedByUser(String approvedByUser) {
        this.approvedByUser = approvedByUser;
    }
    /**
     * Get the approvalDate.
     * @return the approvalDate.
     */
    public Date getApprovalDate() {
        return approvalDate;
    }
    /**
     * Set the approvalDate..
     * @param approvalDate the approvalDate to be set
     */
    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }
    /**
     * Get the document.
     * @return the document.
     */
    public byte[] getDocument() {
        return document;
    }
    /**
     * Set the document attribute value..
     * @param document the document to be set
     */
    public void setDocument(byte[] document) {
        this.document = document;
    }
    /**
     * Get the fileName.
     * @return the fileName.
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * Set the fileName..
     * @param fileName the fileName to be set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /**
     * Get the createdBy user.
     * @return the createdBy.
     */
    public String getCreatedBy() {
        return createdBy;
    }
    /**
     * Set the createdBy attribute value..
     * @param createdBy the createdBy to be set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    /**
     * Get the createdDate.
     * @return the createdDate.
     */
    public Date getCreatedDate() {
        return createdDate;
    }
    /**
     * Set the createdDate..
     * @param createdDate the createdDate to be set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    /**
     * Get the mimeType.
     * @return the mimeType.
     */
    public String getMimeType() {
        return mimeType;
    }
    /**
     * Set the mimeType..
     * @param mimeType the mimeType to be set
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
    /**
     * Get the amountReleased.
     * @return the amountReleased.
     */
    public KualiDecimal getAmountReleased() {
        return amountReleased;
    }
    /**
     * Set the amountReleased..
     * @param amountReleased the amountReleased to be set
     */
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
     * Set the file..
     * @param file the file to be set
     */
    public void setFile(AttachmentFile file) {
        this.file = file;
    }

    /**.
     *
     * This method used to populate the attachment
     *  to subAwardReleased object by reading FormFile
     */
    public void populateAttachment() {
        FormFile newFile = getNewFile();
        if (newFile == null) { return; }
        byte[] newFileData;
        try {
            newFileData = newFile.getFileData();
            setDocument(newFileData);
            if (newFileData.length > 0) {
                setContentType(newFile.getContentType());
                setFileName(newFile.getFileName());
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
    /**
     * Get the new File.
     * @return the newFile.
     */
    public FormFile getNewFile() {
        return this.newFile;
    }

    /**
     * Set the newfile..
     * @param newfile the newfile to be set
     */
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
return KraServiceLocator.getService(
KcAttachmentService.class).getFileTypeIcon(this);
    }
    /**
     * Set the ContentType attribute value..
     * @param contentType the contentType to be set
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    /**
     * Get the subaward's ContentType.
     * @return the ContentType.
     */
    public String getContentType() {
        return contentType;
    }
}