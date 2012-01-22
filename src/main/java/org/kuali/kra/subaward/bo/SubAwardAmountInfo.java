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

import org.apache.struts.upload.FormFile;
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.bo.KcAttachment;
import org.kuali.kra.infrastructure.KraServiceLocator;

import java.util.LinkedHashMap;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;

import org.kuali.kra.service.KcAttachmentService;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.rice.core.api.util.type.KualiDecimal;

public class SubAwardAmountInfo extends SubAwardAssociate implements KcAttachment{ 

    private static final long serialVersionUID = 1L;

    private Integer subAwardAmountInfoId;
    private Long subAwardId; 
    private String subAwardCode;
    private Integer lineNumber;

    private KualiDecimal obligatedAmount;

    private KualiDecimal obligatedChange;

    private KualiDecimal anticipatedAmount;

    private KualiDecimal anticipatedChange;

    private Date effectiveDate;

    private String comments;

    private String fileName;

    private byte[] document;

    private String mimeType;

    private AttachmentFile file;
    transient private FormFile newFile;
    private SubAward subAward;
    private Long fileId;
    private String contentType;

    public SubAwardAmountInfo() { 

    }

    public Integer getSubAwardAmountInfoId() {
        return subAwardAmountInfoId;
    }

    public void setSubAwardAmountInfoId(Integer subAwardAmountInfoId) {
        this.subAwardAmountInfoId = subAwardAmountInfoId;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
    /**
     * 
     * This method used to populate the attachment to subAwardInfo object by reading FormFile 
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

    public KualiDecimal getObligatedAmount() {
        return obligatedAmount;
    }

    public void setObligatedAmount(KualiDecimal obligatedAmount) {
        this.obligatedAmount = obligatedAmount;
    }

    public KualiDecimal getObligatedChange() {
        return obligatedChange;
    }

    public void setObligatedChange(KualiDecimal obligatedChange) {
        this.obligatedChange = obligatedChange;
    }

    public KualiDecimal getAnticipatedAmount() {
        return anticipatedAmount;
    }

    public void setAnticipatedAmount(KualiDecimal anticipatedAmount) {
        this.anticipatedAmount = anticipatedAmount;
    }

    public KualiDecimal getAnticipatedChange() {
        return anticipatedChange;
    }

    public void setAnticipatedChange(KualiDecimal anticipatedChange) {
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

    public FormFile getNewFile() {
        return this.newFile;
    }

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
    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.subAwardAmountInfoId = null;
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