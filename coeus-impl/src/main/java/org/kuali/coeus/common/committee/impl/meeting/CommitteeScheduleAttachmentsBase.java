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
package org.kuali.coeus.common.committee.impl.meeting;

/*
 *  this is BO for committee schedule attachments
 */

import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public abstract class CommitteeScheduleAttachmentsBase extends KcPersistableBusinessObjectBase implements KcFile  {


    private static final long serialVersionUID = -5308464510751671978L;

    private Integer attachmentId;
    private String attachmentsTypeCode;
    private String description;
    private String fileName;
    private String attachmentsDescription;
    private String mimeType;
    transient private FormFile newFile;
    private byte[] document;
    private String contentType;
    private AttachmentFile file;
    private Integer lineNumber;
    private CommitteeScheduleBase committeeSchedule;
    private String newUpdateUser;
    private Timestamp newUpdateTimestamp;
    
    /**
     * Gets the committeeSchedule attribute. 
     * @return Returns the committeeSchedule.
     */
    public CommitteeScheduleBase getCommitteeSchedule() {
        return committeeSchedule;
    }
    /**
     * Sets the committeeSchedule attribute. 
     * @return Returns the committeeSchedule.
     */
    public void setCommitteeSchedule(CommitteeScheduleBase committeeSchedule) {
        this.committeeSchedule = committeeSchedule;
    }
    
    /**
     * Gets the newUpdateUser attribute. 
     * @return Returns the newUpdateUser.
     */
    public String getNewUpdateUser() {
        return newUpdateUser;
    }
    
    /**
     * Sets the newUpdateUser attribute. 
     * @return Returns the newUpdateUser.
     */
    public void setNewUpdateUser(String newUpdateUser) {
        this.newUpdateUser = newUpdateUser;
    }
    
    /**
     * Gets the newUpdateTimestamp attribute. 
     * @return Returns the newUpdateTimestamp.
     */
    public Timestamp getNewUpdateTimestamp() {
        return newUpdateTimestamp;
    }
    
    /**
     * Sets the newUpdateTimestamp attribute. 
     * @return Returns the newUpdateTimestamp.
     */
    public void setNewUpdateTimestamp(Timestamp newUpdateTimestamp) {
        this.newUpdateTimestamp = newUpdateTimestamp;
    }
   
    /**
     * Gets the attachmentsDescription attribute. 
     * @return Returns the attachmentsDescription.
     */
    public String getAttachmentsDescription() {
        return attachmentsDescription;
    }
    
    /**
     * Sets the attachmentsDescription attribute. 
     * @return Returns the attachmentsDescription.
     */
    public void setAttachmentsDescription(String attachmentsDescription) {
        this.attachmentsDescription = attachmentsDescription;
    }
    
    public CommitteeScheduleAttachmentsBase() {
        
     }
    
    public CommitteeScheduleAttachmentsBase(String attachmentsTypeCode) {
         this.attachmentsTypeCode = attachmentsTypeCode;
     }
     
    private AttachmentsEntryType attachmentsEntryType;
     CommitteeScheduleBase committeeschedule;
   
     /**
      * Gets the attachmentsTypeCode attribute. 
      * @return Returns the attachmentsTypeCode.
      */
    public String getAttachmentsTypeCode() {
        return attachmentsTypeCode;
    }
    
    public void setAttachmentsTypeCode(String attachmentsTypeCode) {
        this.attachmentsTypeCode = attachmentsTypeCode;
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("attachmentsTypeCode", attachmentsTypeCode);
        AttachmentsEntryType attachmentsEntryType =  KcServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(AttachmentsEntryType.class, fieldValues);
        if(attachmentsEntryType!=null){
            this.attachmentsEntryType = attachmentsEntryType;
            if(this.attachmentsEntryType !=null)
            this.attachmentsEntryType.setDescription(attachmentsEntryType.getDescription()) ;
        }
    }

    /**
     * Gets the attachmentsEntryType attribute. 
     * @return Returns the attachmentsEntryType.
     */
    public AttachmentsEntryType getAttachmentsEntryType() {
        return attachmentsEntryType;
    }
    
    /**
     * Sets the attachmentsEntryType attribute. 
     * @return Returns the attachmentsEntryType.
     */
    public void setAttachmentsEntryType(AttachmentsEntryType attachmentsEntryType) {
        this.attachmentsEntryType = attachmentsEntryType;
    }

    /**
     * Gets the file attribute. 
     * @return Returns the file.
     */
    public AttachmentFile getFile() {
        return file;
    }
    
    /**
     * Sets the file attribute. 
     * @return Returns the file.
     */
    public void setFile(AttachmentFile file) {
        this.file = file;
    }
    
    /**
     * Gets the lineNumber attribute. 
     * @return Returns the lineNumber.
     */
    public Integer getLineNumber() {
        return lineNumber;
    }
    
    /**
     * Sets the lineNumber attribute. 
     * @return Returns the lineNumber.
     */
    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }
   
    /**
     * Gets the contentType attribute. 
     * @return Returns the contentType.
     */
    public String getContentType() {
        return contentType;
    }
    
    /**
     * Sets the contentType attribute. 
     * @return Returns the contentType.
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    
    /**
     * Gets the document attribute. 
     * @return Returns the document.
     */
    public byte[] getDocument() {
        return document;
    }
    
    /**
     * Sets the document attribute. 
     * @return Returns the document.
     */
    public void setDocument(byte[] document) {
        this.document = document;
    }
   
    /**
     * Gets the attachmentId attribute. 
     * @return Returns the attachmentId.
     */
    public Integer getAttachmentId() {
        return attachmentId;
    }
    
    /**
     * Sets the attachmentId attribute. 
     * @return Returns the attachmentId.
     */
    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }
    /**
     * Gets the description attribute. 
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Sets the description attribute. 
     * @return Returns the description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Gets the fileName attribute. 
     * @return Returns the fileName.
     */
    public String getFileName() {
        return fileName;
    }
    
    /**
     * Sets the fileName attribute. 
     * @return Returns the fileName.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
  
    /**
     * Gets the mimeType attribute. 
     * @return Returns the mimeType.
     */
    public String getMimeType() {
        return mimeType;
    }
    
    /**
     * Sets the mimeType attribute. 
     * @return Returns the mimeType.
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
   
    /**
     * Gets the mimeType attribute. 
     * @return Returns the mimeType.
     */
    public FormFile getNewFile() {
        return newFile;
    }
    
    /**
     * Sets the mimeType attribute. 
     * @return Returns the mimeType.
     */
    public void setNewFile(FormFile newFile) {
        this.newFile = newFile;
    }
    
    /**
     * Gets the committeeschedule attribute. 
     * @return Returns the committeeschedule.
     */
    public CommitteeScheduleBase getCommitteeschedule() {
        return committeeschedule;
    }
    
    /**
     * Sets the committeeschedule attribute. 
     * @return Returns the committeeschedule.
     */
    public void setCommitteeschedule(CommitteeScheduleBase committeeschedule) {
        this.committeeschedule = committeeschedule;
    }

    /**
     * 
     * This method used to populate the attachment 
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
}
