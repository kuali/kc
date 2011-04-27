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
package org.kuali.kra.irb.summary;

import java.io.Serializable;
import java.sql.Timestamp;

public class AttachmentSummary implements Serializable {

    private static final long serialVersionUID = -6058410492582759356L;
    
    private Long attachmentId;
    private String fileName;
    private String fileType;
    private long dataLength;
    
    private boolean fileNameChanged;
    private String attachmentType;
    private String description;
    
    private String updateUser;
    private Timestamp updateTimestamp;

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
        fileNameChanged = (otherAttachment == null);
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
}
