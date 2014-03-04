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
package org.kuali.kra.protocol.summary;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.sql.Timestamp;

public class AttachmentSummary implements Serializable {

    private static final long serialVersionUID = -6058410492582759356L;
    
    private Long attachmentId;
    private String fileName;
    private boolean fileNameChanged;

    private String fileType;
    private boolean fileTypeChanged;
    
    private long dataLength;
    
    private String attachmentType;
    private boolean attachmentTypeChanged;
    
    private String description;
    private boolean descriptionChanged;
    
    private String updateUser;
    private boolean userChanged;
    
    private Timestamp updateTimestamp;
    private boolean dateChanged;

    public AttachmentSummary() {
        
    }
    
    public Long getAttachmentId() {
        return attachmentId;
    }
    
    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    
    public long getDataLength() {
        return dataLength;
    }
    
    public void setDataLength(long dataLength) {
        this.dataLength = dataLength;
    }

    public boolean isFileNameChanged() {
        return fileNameChanged;
    }

    public void compare(ProtocolSummary other) {
        AttachmentSummary otherAttachment = other.findAttachment(fileName, fileType, dataLength);
        if (otherAttachment == null) {
            fileNameChanged = true;
            fileTypeChanged = true;
            attachmentTypeChanged = true;
            descriptionChanged = true;
            dateChanged = true;
            userChanged = true;
        } else {
            fileNameChanged = (!StringUtils.equals(fileName, otherAttachment.getFileName()));
            fileTypeChanged = (!StringUtils.equals(fileType, otherAttachment.getFileType()));
            attachmentTypeChanged = (!StringUtils.equals(attachmentType, otherAttachment.getAttachmentType()));
            descriptionChanged = (!StringUtils.equals(description, otherAttachment.getDescription()));
            dateChanged = !((updateTimestamp == otherAttachment.getUpdateTimestamp()) ||
                            (updateTimestamp != null && updateTimestamp.equals(otherAttachment.getUpdateTimestamp())));
            userChanged = (!StringUtils.equals(updateUser, otherAttachment.getUpdateUser()));
        }
    }

    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public boolean isFileTypeChanged() {
        return fileTypeChanged;
    }

    public void setFileTypeChanged(boolean fileTypeChanged) {
        this.fileTypeChanged = fileTypeChanged;
    }

    public boolean isAttachmentTypeChanged() {
        return attachmentTypeChanged;
    }

    public void setAttachmentTypeChanged(boolean attachmentTypeChanged) {
        this.attachmentTypeChanged = attachmentTypeChanged;
    }

    public boolean isDescriptionChanged() {
        return descriptionChanged;
    }

    public void setDescriptionChanged(boolean descriptionChanged) {
        this.descriptionChanged = descriptionChanged;
    }

    public void setFileNameChanged(boolean fileNameChanged) {
        this.fileNameChanged = fileNameChanged;
    }

    public boolean isUserChanged() {
        return userChanged;
    }

    public void setUserChanged(boolean userChanged) {
        this.userChanged = userChanged;
    }

    public boolean isDateChanged() {
        return dateChanged;
    }

    public void setDateChanged(boolean dateChanged) {
        this.dateChanged = dateChanged;
    }
    
}
