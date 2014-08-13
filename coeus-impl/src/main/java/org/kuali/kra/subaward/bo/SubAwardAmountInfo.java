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
package org.kuali.kra.subaward.bo;

import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;

/**
 * This class represents a subAwardAmountInfo. It describes the amount
 * released and allowed for the subAward.
 */
public class SubAwardAmountInfo extends
 SubAwardAssociate implements KcFile {

    private static final long serialVersionUID = 1L;

    private Integer subAwardAmountInfoId;
    
    private Long subAwardId; 
    
    private String subAwardCode;
    
    private Integer lineNumber;

    private ScaleTwoDecimal obligatedAmount;

    private ScaleTwoDecimal obligatedChange;

    private ScaleTwoDecimal anticipatedAmount;

    private ScaleTwoDecimal anticipatedChange;

    private Date effectiveDate;

    private String comments;

    private String fileName;

    private byte[] document;

    private String mimeType;

    private AttachmentFile file;
    
    transient private FormFile newFile;
    
    private Long fileId;
    
    private String contentType;

    private Date modificationEffectiveDate ;
    
    private String modificationID;
    
    private Date periodofPerformanceStartDate;
    
    private Date periodofPerformanceEndDate;
    
    /**
     * the SubAwardAmountInfo constructor.
     */
    public SubAwardAmountInfo() {

    }
    /**
     * Gets the subAwardAmountInfoId.
     * @return subAwardAmountInfoId.
     */
    public Integer getSubAwardAmountInfoId() {
        return subAwardAmountInfoId;
    }
    /**
     * Sets the  subAwardAmountInfoId attribute value.
     * @param subAwardAmountInfoId The subAwardAmountInfoId to set.
     */
    public void setSubAwardAmountInfoId(Integer subAwardAmountInfoId) {
        this.subAwardAmountInfoId = subAwardAmountInfoId;
    }
    /**
     * Gets the subAwardId.
     * @return subAwardId.
     */
    public Long getSubAwardId() {
        return subAwardId;
    }
    /**
     * Sets the  subAwardId attribute value.
     * @param subAwardId The subAwardId to set.
     */
    public void setSubAwardId(Long subAwardId) {
        this.subAwardId = subAwardId;
    }
    /**
     * Gets the subAwardCode.
     * @return subAwardCode.
     */
    public String getSubAwardCode() {
        return subAwardCode;
    }
    /**
     * Sets the  subAwardCode attribute value.
     * @param subAwardCode The subAwardCode to set.
     */
    public void setSubAwardCode(String subAwardCode) {
        this.subAwardCode = subAwardCode;
    }

    /**
     * Gets the lineNumber.
     * @return lineNumber.
     */
    public Integer getLineNumber() {
        return lineNumber;
    }
    /**
     * Sets the  lineNumber attribute value.
     * @param lineNumber The lineNumber to set.
     */
    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }
    /**
     * Gets the effectiveDate.
     * @return effectiveDate.
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }
    /**
     * Sets the  effectiveDate attribute value.
     * @param effectiveDate The effectiveDate to set.
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    /**
     * Gets the comments.
     * @return comments.
     */
    public String getComments() {
        return comments;
    }
    /**
     * Sets the  comments attribute value.
     * @param comments The comments to set.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    /**
     * Gets the fileName.
     * @return fileName.
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * Sets the  fileName attribute value.
     * @param fileName The fileName to set.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /**
     * Gets the document.
     * @return document.
     */
    public byte[] getDocument() {
        return document;
    }
    /**
     * Sets the  document attribute value.
     * @param document The document to set.
     */
    public void setDocument(byte[] document) {
        this.document = document;
    }
    /**
     * Gets the mimeType.
     * @return mimeType.
     */ 
    public String getMimeType() {
        return mimeType;
    }
    /**
     * Sets the  mimeType attribute value.
     * @param mimeType The mimeType to set.
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
    /**.
     *
     * This method used to populate the attachment
     *to subAwardInfo object by reading FormFile
     */
    public void populateAttachment() {
        FormFile newFile = getNewFile();
        if (newFile == null) {
        return;
        }
        byte[] newFileData;
        try {
            newFileData = newFile.getFileData();
            setDocument(newFileData);
            if (newFileData.length > 0) {
                setContentType(newFile.getContentType());
                setFileName(newFile.getFileName());
                setMimeType(contentType);
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
    /**
     * Gets the obligatedAmount.
     * @return obligatedAmount.
     */
    public ScaleTwoDecimal getObligatedAmount() {
        return obligatedAmount;
    }
    /**
     * Sets the  obligatedAmount attribute value.
     * @param obligatedAmount The obligatedAmount to set.
     */
    public void setObligatedAmount(ScaleTwoDecimal obligatedAmount) {
        this.obligatedAmount = obligatedAmount;
    }
    /**
     * Gets the obligatedChange.
     * @return obligatedChange.
     */
    public ScaleTwoDecimal getObligatedChange() {
        return obligatedChange;
    }
    /**
     * Sets the  obligatedChange attribute value.
     * @param obligatedChange The obligatedChange to set.
     */
    public void setObligatedChange(ScaleTwoDecimal obligatedChange) {
        this.obligatedChange = obligatedChange;
    }
    /**
     * Gets the anticipatedAmount.
     * @return anticipatedAmount.
     */
    public ScaleTwoDecimal getAnticipatedAmount() {
        return anticipatedAmount;
    }
    /**
     * Sets the  anticipatedAmount attribute value.
     * @param anticipatedAmount The anticipatedAmount to set.
     */
    public void setAnticipatedAmount(ScaleTwoDecimal anticipatedAmount) {
        this.anticipatedAmount = anticipatedAmount;
    }
    /**
     * Gets the anticipatedChange.
     * @return anticipatedChange.
     */
    public ScaleTwoDecimal getAnticipatedChange() {
        return anticipatedChange;
    }
    /**
     * Sets the  anticipatedChange attribute value.
     * @param anticipatedChange The anticipatedChange to set.
     */
    public void setAnticipatedChange(ScaleTwoDecimal anticipatedChange) {
        this.anticipatedChange = anticipatedChange;
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
     * Gets the newFile.
     * @return newFile.
     */
    public FormFile getNewFile() {
        return this.newFile;
    }
    /**
     * Sets the  newFile.
     */
    public void setNewFile(FormFile newFile) {
        this.newFile = newFile;
    }

    /**
     * Gets the file Id.
     * @return the file Id.
     */
    public Long getFileId() {
        return this.fileId;
    }

    /**
     * Sets the file Id.
     * @param fileId the file Id.
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }
    @Override
    public void resetPersistenceState() {
        this.subAwardAmountInfoId = null;
    }

    @Override
    public String getName() {
        return getFileName();
    }

    @Override
    public String getType() {
        return getMimeType();
    }

    @Override
    public byte[] getData() {
        return getDocument();
    }

    /**
     * Sets the contentType.
     * @param contentType the contentType.
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    /**
     * Gets the contentType.
     * @return contentType.
     */
    public String getContentType() {
        return contentType;
    }
    /**
     * Gets the modificationEffectiveDate.
     * @return modificationEffectiveDate.
     */
    public Date getModificationEffectiveDate() {
        return modificationEffectiveDate;
    }
    /**
     * Sets the modificationEffectiveDate.
     * @param modificationEffectiveDate the modificationEffectiveDate.
     */
    public void setModificationEffectiveDate(Date modificationEffectiveDate) {
        this.modificationEffectiveDate = modificationEffectiveDate;
    }
    /**
     * Gets the modificationID.
     * @return modificationID.
     */
    public String getModificationID() {
        return modificationID;
    }
    /**
     * Sets the modificationID.
     * @param modificationID the modificationID.
     */
    public void setModificationID(String modificationID) {
        this.modificationID = modificationID;
    }
    /**
     * Gets the periodofPerformanceStartDate.
     * @return periodofPerformanceStartDate.
     */
    public Date getPeriodofPerformanceStartDate() {
        return periodofPerformanceStartDate;
    }
    /**
     * Sets the periodofPerformanceStartDate.
     * @param periodofPerformanceStartDate the periodofPerformanceStartDate.
     */
    public void setPeriodofPerformanceStartDate(Date periodofPerformanceStartDate) {
        this.periodofPerformanceStartDate = periodofPerformanceStartDate;
    }
    /**
     * Gets the periodofPerformanceEndDate.
     * @return periodofPerformanceEndDate.
     */
    public Date getPeriodofPerformanceEndDate() {
        return periodofPerformanceEndDate;
    }
    /**
     * Sets the periodofPerformanceEndDate.
     * @param periodofPerformanceEndDate the periodofPerformanceEndDate.
     */
    public void setPeriodofPerformanceEndDate(Date periodofPerformanceEndDate) {
        this.periodofPerformanceEndDate = periodofPerformanceEndDate;
    }
}