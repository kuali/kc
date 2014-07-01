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
package org.kuali.coeus.common.impl.rpt.cust;

import org.apache.struts.upload.FormFile;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.bo.PersistableAttachment;

public class CustReportDetails extends KcPersistableBusinessObjectBase implements PersistableAttachment{
    
    private static final long serialVersionUID = 1L;

    private Integer reportId; 
    
    private String reportLabel; 
    
    private String reportLabelDisplay;
    
    private String reportDescription; 
    
    private Integer reportTypeCode; 
    
    private String permissionName;
    
    private String fileName;
    
    private String contentType;
    
    private byte[] attachmentContent; 
    
    private CustReportType custReportType;
    
    private transient FormFile templateFile; 
    
    
    public CustReportDetails() { 

    }

    /**
     * Gets the reportId attribute. 
     * @return Returns the reportId.
     */
    public Integer getReportId() {
        return reportId;
    }

    /**
     * Sets the reportId attribute value.
     * @param reportId The reportId to set.
     */
    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    /**
     * Gets the reportLabel attribute. 
     * @return Returns the reportLabel.
     */
    public String getReportLabel() {
        return reportLabel;
    }

    /**
     * Sets the reportLabel attribute value.
     * @param reportLabel The reportLabel to set.
     */
    public void setReportLabel(String reportLabel) {
        this.reportLabel = reportLabel;
    }

    /**
     * Gets the reportDescription attribute. 
     * @return Returns the reportDescription.
     */
    public String getReportDescription() {
        return reportDescription;
    }

    /**
     * Sets the reportDescription attribute value.
     * @param reportDescription The reportDescription to set.
     */
    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    /**
     * Gets the reportTypeCode attribute. 
     * @return Returns the reportTypeCode.
     */
    public Integer getReportTypeCode() {
        return reportTypeCode;
    }

    /**
     * Sets the reportTypeCode attribute value.
     * @param reportTypeCode The reportTypeCode to set.
     */
    public void setReportTypeCode(Integer reportTypeCode) {
        this.reportTypeCode = reportTypeCode;
    }

    /**
     * Gets the name attribute. 
     * @return Returns the name.
     */
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * Sets the name attribute value.
     * @param name The name to set.
     */
    public void setPermissionName(String permissionName) {
            this.permissionName = permissionName;
    }

    /**
     * Gets the fileName attribute. 
     * @return Returns the fileName.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the fileName attribute value.
     * @param fileName The fileName to set.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets the contentType attribute. 
     * @return Returns the contentType.
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Sets the contentType attribute value.
     * @param contentType The contentType to set.
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Gets the attachmentContent attribute. 
     * @return Returns the attachmentContent.
     */
    public byte[] getAttachmentContent() {
        return attachmentContent;
    }

    /**
     * Sets the attachmentContent attribute value.
     * @param attachmentContent The attachmentContent to set.
     */
    public void setAttachmentContent(byte[] attachmentContent) {
        this.attachmentContent = attachmentContent;
    }

    /**
     * Gets the custReportType attribute. 
     * @return Returns the custReportType.
     */
    public CustReportType getCustReportType() {
        return custReportType;
    }

    /**
     * Sets the custReportType attribute value.
     * @param custReportType The custReportType to set.
     */
    public void setCustReportType(CustReportType custReportType) {
        this.custReportType = custReportType;
    }

    /**
     * Gets the templateFile attribute. 
     * @return Returns the templateFile.
     */
    public FormFile getTemplateFile() {
        return templateFile;
    }

    /**
     * Sets the templateFile attribute value.
     * @param templateFile The templateFile to set.
     */
    public void setTemplateFile(FormFile templateFile) {
        this.templateFile = templateFile;
    }

    /**
     * Sets the reportLabelDisplay attribute value.
     * @param reportLabelDisplay The reportLabelDisplay to set.
     */
    public void setReportLabelDisplay(String reportLabelDisplay) {
        this.reportLabelDisplay = reportLabelDisplay;
    }

    /**
     * Gets the reportLabelDisplay attribute. 
     * @return Returns the reportLabelDisplay.
     */
    public String getReportLabelDisplay() {
        return reportLabelDisplay;
    }
}