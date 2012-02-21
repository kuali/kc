/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.bo;

import org.apache.struts.upload.FormFile;
import org.kuali.rice.krad.bo.PersistableAttachment;

/**
 * 
 * This class for fetching watermark object from database.
 */
public class Watermark extends KraPersistableBusinessObjectBase implements PersistableAttachment {

    private static final long serialVersionUID = 7376543184312622270L;

    private Long watermarkId;

    private String fileName;

    private String contentType;

    private byte[] attachmentContent;
    private String statusCode;
    private String watermarkText;
    private boolean watermarkStatus;
    private String fontSize;
    private String positionFontSize;
    private String fontColor;
    private String watermarkType;
    private transient FormFile templateFile;
    private String watermarkPosition;
    private String watermarkAlignment;


    public Long getWatermarkId() {
        return watermarkId;
    }

    public void setWatermarkId(Long watermarkId) {
        this.watermarkId = watermarkId;
    }

    public byte[] getAttachmentContent() {
        return this.attachmentContent;
    }

    public void setAttachmentContent(byte[] attachmentContent) {
        this.attachmentContent = attachmentContent;
    }

    public FormFile getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(FormFile templateFile) {
        this.templateFile = templateFile;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getPositionFontSize() {
        return positionFontSize;
    }

    public void setPositionFontSize(String positionFontSize) {
        this.positionFontSize = positionFontSize;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getWatermarkType() {
        return watermarkType;
    }

    public void setWatermarkType(String watermarkType) {
        this.watermarkType = watermarkType;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getWatermarkText() {
        return watermarkText;
    }

    public void setWatermarkText(String watermarkText) {
        this.watermarkText = watermarkText;
    }

    public boolean isWatermarkStatus() {
        return watermarkStatus;
    }

    public void setWatermarkStatus(boolean watermarkStatus) {
        this.watermarkStatus = watermarkStatus;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getWatermarkPosition() {
        return watermarkPosition;
    }


    public void setWatermarkPosition(String watermarkPosition) {
        this.watermarkPosition = watermarkPosition;
    }


    public String getWatermarkAlignment() {
        return watermarkAlignment;
    }


    public void setWatermarkAlignment(String watermarkAlignment) {
        this.watermarkAlignment = watermarkAlignment;
    }

}
