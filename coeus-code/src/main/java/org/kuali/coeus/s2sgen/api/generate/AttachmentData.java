/*
 * Copyright 2005-2014 The Kuali Foundation.
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
package org.kuali.coeus.s2sgen.api.generate;

/**
 * 
 * This class is meant to store and retrieve attachments and its associated properties
 */
public class AttachmentData {
    private String fileName;
    private String contentId;
    private byte[] content;
    private String contentType;
    private String hashValue;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String FileName) {
        this.fileName = FileName;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] Content) {
        this.content = Content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String ContentType) {
        this.contentType = ContentType;
    }

    public String getHashValue() {
        return hashValue;
    }

    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }

}
