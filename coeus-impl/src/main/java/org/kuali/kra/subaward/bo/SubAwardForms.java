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
package org.kuali.kra.subaward.bo;

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
