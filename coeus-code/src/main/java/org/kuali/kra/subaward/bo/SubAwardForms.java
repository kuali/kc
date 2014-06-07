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
package org.kuali.kra.subaward.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.upload.FormFile;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.bo.PersistableAttachment;

public class SubAwardForms extends KcPersistableBusinessObjectBase implements PersistableAttachment{
    
    private static final long serialVersionUID = 1L;

    private String formId; 
    private String description; 
    private byte[] attachmentContent;
    private String fileName;
    private String contentType;
    private transient FormFile templateFile;    
    private Integer templateTypeCode;
    private SubawardTemplateType subAwardTemplateType;
    private Boolean selectToPrint = false;

    public final Boolean getSelectToPrint() {
        return selectToPrint;
    }

    public final void setSelectToPrint(Boolean selectToPrint) {
        this.selectToPrint = selectToPrint;
    }

    public SubAwardForms() {
        super();
    }

  /**.
     * This is the Getter Method for SubAwardTemplateType
     * @return Returns the SubAwardTemplateType.
       */
    public SubawardTemplateType getSubAwardTemplateType() {
        return subAwardTemplateType;
    }

    /**.
     * This is the Setter Method for subAwardTemplateType
     * @param subAwardTemplateType The subAwardTemplateType to set.
     */
    public void setSubAwardTemplateType(SubawardTemplateType subAwardTemplateType) {
        this.subAwardTemplateType = subAwardTemplateType;
    }
    
    /**.
     * This is the Getter Method for templateTypeCode
     * @return Returns the templateTypeCode.
       */
    public Integer getTemplateTypeCode() {
        return templateTypeCode;
    }

    /**.
     * This is the Setter Method for templateTypeCode
     * @param templateTypeCode The templateTypeCode to set.
     */
    public void setTemplateTypeCode(Integer templateTypeCode) {
        this.templateTypeCode = templateTypeCode;
    }


  /**.
   * This is the Getter Method for formId
   * @return Returns the formId.
	 */
	public String getFormId() {
		return formId;
	}


	/**.
	 * This is the Setter Method for formId
	 * @param formId The formId to set.
	 */
	public void setFormId(String formId) {
		this.formId = formId;
	}


	/**.
	 * This is the Getter Method for description
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}


	/**.
	 * This is the Setter Method for description
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**.
	 * This is the Getter Method for attachmentContent
	 * @return Returns the attachmentContent.
	 */
	public byte[] getAttachmentContent() {
		return attachmentContent;
	}


	/**.
	 * This is the Setter Method for attachmentContent
	 * @param attachmentContent The attachmentContent to set.
	 */
	public void setAttachmentContent(byte[] attachmentContent) {
		this.attachmentContent = attachmentContent;
	}


	/**.
	 * This is the Getter Method for fileName
	 * @return Returns the fileName.
	 */
	public String getFileName() {
		return fileName;
	}


	/**.
	 * This is the Setter Method for fileName
	 * @param fileName The fileName to set.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	/**.
	 * This is the Getter Method for contentType
	 * @return Returns the contentType.
	 */
	public String getContentType() {
		return contentType;
	}


	/**.
	 * This is the Setter Method for contentType
	 * @param contentType The contentType to set.
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}


	/**.
	 * This is the Getter Method for templateFile
	 * @return Returns the templateFile.
	 */
	public FormFile getTemplateFile() {
		return templateFile;
	}


	/**.
	 * This is the Setter Method for templateFile
	 * @param templateFile The templateFile to set.
	 */
	public void setTemplateFile(FormFile templateFile) {
		this.templateFile = templateFile;
	}
   

}
