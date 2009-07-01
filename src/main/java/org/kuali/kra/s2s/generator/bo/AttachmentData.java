/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.s2s.generator.bo;

/**
 * 
 * This class is meant to store and retrieve attachments and its associated properties
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class AttachmentData {
    private String fileName;
    private String contentId;
    private byte[] content;
    private String contentType;
    private String hashValue;

    /** Creates a new instance of AttachmentBean */
    public AttachmentData() {
    }

    /**
     * Getter for property FileName.
     * @return Value of property FileName.
     */
    public java.lang.String getFileName() {
        return fileName;
    }

    /**
     * Setter for property FileName.
     * @param FileName New value of property FileName.
     */
    public void setFileName(java.lang.String FileName) {
        this.fileName = FileName;
    }

    /**
     * Getter for property ContentId.
     * @return Value of property ContentId.
     */
    public java.lang.String getContentId() {
        return contentId;
    }

    /**
     * Setter for property contentId.
     * @param ContentId New value of property contentId.
     */
    public void setContentId(java.lang.String contentId) {
        this.contentId = contentId;
    }

    /**
     * Getter for property Content.
     * @return Value of property Content.
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * Setter for property Content.
     * @param Content New value of property Content.
     */
    public void setContent(byte[] Content) {
        this.content = Content;
    }

    /**
     * Getter for property ContentType.
     * @return Value of property ContentType.
     */
    public java.lang.String getContentType() {
        return contentType;
    }

    /**
     * Setter for property ContentType.
     * @param ContentType New value of property ContentType.
     */
    public void setContentType(java.lang.String ContentType) {
        this.contentType = ContentType;
    }

    /**
     * Getter for property hashValue.
     * @return Value of property hashValue.
     */
    public java.lang.String getHashValue() {
        return hashValue;
    }

    /**
     * Setter for property hashValue.
     * @param hashValue New value of property hashValue.
     */
    public void setHashValue(java.lang.String hashValue) {
        this.hashValue = hashValue;
    }

}
