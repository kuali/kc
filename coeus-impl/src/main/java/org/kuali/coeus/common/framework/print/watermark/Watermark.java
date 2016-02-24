/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.framework.print.watermark;

import org.apache.struts.upload.FormFile;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.bo.PersistableAttachment;

/**
 * 
 * This class for fetching watermark object from database.
 */
public class Watermark extends KcPersistableBusinessObjectBase implements PersistableAttachment {

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
    private String groupName;

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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
