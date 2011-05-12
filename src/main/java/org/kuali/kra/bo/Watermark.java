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

import java.util.LinkedHashMap;

import org.apache.struts.upload.FormFile;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
/**
 * 
 * This class for fetching watermark object from database.
 */
public class Watermark extends KraPersistableBusinessObjectBase {
    private static final long serialVersionUID = 7376543184312622270L;
	
	private Long watermarkId;

	private String statusCode;	
    private String watermarkText;
	private boolean watermarkStatus;
	private String fontSize;
	private String fontColour;
	private String watermarkType;
	private byte[] attachmentImage;
	private FormFile watermarkImageFile;
	
	public Long getWatermarkId() {
        return watermarkId;
    }


    public void setWatermarkId(Long watermarkId) {
        this.watermarkId = watermarkId;
    }

   
  
    public FormFile getWatermarkImageFile() {
        return watermarkImageFile;
    }


    public void setWatermarkImageFile(FormFile watermarkImageFile) {
        this.watermarkImageFile = watermarkImageFile;
    }


    public byte[] getAttachmentImage() {
        return this.attachmentImage;
    }


    public void setAttachmentImage(byte[] attachmentImage) {
        this.attachmentImage = attachmentImage;
    }


    public String getFontSize() {
        return fontSize;
    }


    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }


    public String getFontColour() {
        return fontColour;
    }


    public void setFontColour(String fontColour) {
        this.fontColour = fontColour;
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

  
    @Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap propMap = new LinkedHashMap();
		propMap.put("watermarkId", this.getWatermarkId());

		propMap.put("statusCode", this.getStatusCode());
		propMap.put("watermarkText", this.getWatermarkText());
		propMap.put("watermarkStatus", this.isWatermarkStatus());
		propMap.put("watermarkType", this.getWatermarkType());
		propMap.put("fontSize", this.getFontSize());
		propMap.put("fontColour", this.getFontColour());
		propMap.put("updateTimestamp", this.getUpdateTimestamp());
		propMap.put("updateUser", this.getUpdateUser());
		propMap.put("watermarkImageFile", this.getWatermarkImageFile());
		return propMap;
	}
}
