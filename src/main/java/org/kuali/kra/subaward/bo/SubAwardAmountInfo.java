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
import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.bo.AttachmentFile;
import java.util.LinkedHashMap;
import java.sql.Date;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.rice.kns.util.KualiDecimal;

public class SubAwardAmountInfo extends SubAwardAssociate{ 
    
    private static final long serialVersionUID = 1L;

    private Integer subAwardAmountInfoId; 
    private Integer subAwardId; 
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
    private transient FormFile newFile;
    private SubAward subAward; 
    private Long fileId;
    

    public SubAwardAmountInfo() { 
        
    } 
      
    public Integer getSubAwardAmountInfoId() {
        return subAwardAmountInfoId;
    }

    public void setSubAwardAmountInfoId(Integer subAwardAmountInfoId) {
        this.subAwardAmountInfoId = subAwardAmountInfoId;
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


    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("subAwardAmountInfoId", this.getSubAwardAmountInfoId());
        hashMap.put("subAwardId", this.getSubAwardId());
        hashMap.put("subAwardCode", this.getSubAwardCode());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("lineNumber", this.getLineNumber());
        hashMap.put("obligatedAmount", this.getObligatedAmount());
        hashMap.put("obligatedChange", this.getObligatedChange());
        hashMap.put("anticipatedAmount", this.getAnticipatedAmount());
        hashMap.put("anticipatedChange", this.getAnticipatedChange());
        hashMap.put("effectiveDate", this.getEffectiveDate());
        hashMap.put("comments", this.getComments());
        hashMap.put("fileName", this.getFileName());
        hashMap.put("document", this.getDocument());
        hashMap.put("mimeType", this.getMimeType());
        hashMap.put("fileId", this.getFileId());
        return hashMap;
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
  
    
}