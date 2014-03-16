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

import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.framework.attachment.KcAttachment;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.subaward.service.SubAwardService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.kew.api.document.DocumentStatus;
import org.kuali.rice.krad.bo.PersistableAttachment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
/**
 * This class represents a subAwardAmountReleased.  It mainly deals with the
 * Amount released for a subAward.
 */
public class SubAwardAmountReleased  extends SubAwardAssociate implements KcAttachment, PersistableAttachment {

    private static final long serialVersionUID = 1L;
    private Integer subAwardAmtReleasedId;
    private KualiDecimal amountReleased;
    private Date effectiveDate;
    private String comments;
    private String invoiceNumber;
    private Date startDate;
    private Date endDate;
    private byte[] document;
    private String fileName;
    private String mimeType;
    private Timestamp createdDate;
    private String createdBy;
    private String documentNumber;
    private String invoiceStatus;
    transient private FormFile newFile;
    private transient SubAwardService subAwardService;
/**.
     * This is the SubAwardAmountReleased() constructor
     */
    public SubAwardAmountReleased() {
    }
    /**
     * Get the SubAwardAmtReleasedId.
     * @return the subAwardAmtReleasedIds.
     */
    public Integer getSubAwardAmtReleasedId() {
        return subAwardAmtReleasedId;
    }
    /**
     * Set the subAwardAmtReleasedId..
     * @param subAwardAmtReleasedId the subAwardAmtReleasedId to be set
*/
    public void setSubAwardAmtReleasedId(Integer subAwardAmtReleasedId) {
        this.subAwardAmtReleasedId = subAwardAmtReleasedId;
    }
    /**
     * Get the effectiveDate.
     * @return the effectiveDate.
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }
    /**
     * Set the effectiveDate attribute value..
     * @param effectiveDate the effectiveDate to be set
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    /**
     * Get the comments.
     * @return the comments.
     */
    public String getComments() {
        return comments;
    }
    /**
     * Set the comments attribute value..
     * @param comments the comments to be set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    /**
     * Get the invoiceNumber.
     * @return the invoiceNumber.
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }
    /**
     * Set the invoiceNumber attribute value..
     * @param invoiceNumber the invoiceNumber to be set
     */
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    /**
     * Get the startDate.
     * @return the startDate.
     */
    public Date getStartDate() {
        return startDate;
    }
    /**
     * Set the startDate..
     * @param startDate the startDate to be set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    /**
     * Get the endDate.
     * @return the endDate.
     */
    public Date getEndDate() {
        return endDate;
    }
    /**
     * Set the endDate attribute value..
     * @param endDate the endDate to be set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    /**
     * Get the amountReleased.
     * @return the amountReleased.
     */
    public KualiDecimal getAmountReleased() {
        return amountReleased;
    }
    /**
     * Set the amountReleased..
     * @param amountReleased the amountReleased to be set
     */
    public void setAmountReleased(KualiDecimal amountReleased) {
        this.amountReleased = amountReleased;
    }
    /**
     * Gets the  Attachment File.
     */

    /**.
     *
     * This method used to populate the attachment
     *  to subAwardReleased object by reading FormFile
     */
    public void populateAttachment() {
        FormFile newFile = getNewFile();
        if (newFile == null) { return; }
        byte[] newFileData;
        try {
            newFileData = newFile.getFileData();
            setDocument(newFileData);
            if (newFileData.length > 0) {
                mimeType = newFile.getContentType();
                fileName = newFile.getFileName();
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
    /**
     * Get the document.
     * @return the document.
     */
    public byte[] getDocument() {
        return document;
    }
    /**
     * Set the document attribute value..
     * @param document the document to be set
     */
    public void setDocument(byte[] document) {
        this.document = document;
    }
    /**
     * Get the fileName.
     * @return the fileName.
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * Set the fileName..
     * @param fileName the fileName to be set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /**
     * Get the mimeType.
     * @return the mimeType.
     */
    public String getMimeType() {
        return mimeType;
    }
    /**
     * Set the mimeType..
     * @param mimeType the mimeType to be set
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
    
    /**
     * Get the new File.
     * @return the newFile.
     */
    public FormFile getNewFile() {
        return this.newFile;
    }

    /**
     * Set the newfile..
     * @param newfile the newfile to be set
     */
    public void setNewFile(FormFile newFile) {
        this.newFile = newFile;
    }

    @Override
    public void resetPersistenceState() {

        this.subAwardAmtReleasedId = null;
    }

    @Override
    public String getName() {
        return getFileName();
    }

    @Override
    public String getType() {
        return getMimeType();
}
    @Override
    public byte[] getData() {
        return getDocument();
    }

    @Override
    public String getIconPath() {
return KcServiceLocator.getService(
        KcAttachmentService.class).getFileTypeIcon(this);
    }
    public byte[] getAttachmentContent() {
        return getDocument();
    }
    public void setAttachmentContent(byte[] arg0) {
        //do nothing as this will be called by the maint framework
        //in many cases with null when it is inappropriate to do so.
        //this.document = arg0;
    }
    public String getContentType() {
        return getMimeType();
    }
    public void setContentType(String contentType) {
        setMimeType(contentType);
    }
    
    
    public SubAward getSubAward() {
        return getSubAwardService().getActiveSubAward(getSubAwardId());
    }
    public SubAwardService getSubAwardService() {
        if (subAwardService == null) {
            subAwardService = KcServiceLocator.getService(SubAwardService.class);
        }
        return subAwardService;
    }
    public void setSubAwardService(SubAwardService subAwardService) {
        this.subAwardService = subAwardService;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public String getDocumentNumber() {
        return documentNumber;
    }
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
    public String getInvoiceStatus() {
        return invoiceStatus;
    }
    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }
    public String getInvoiceStatusLabel() {
        if (invoiceStatus != null) {
            return DocumentStatus.fromCode(invoiceStatus).getLabel();
        } else {
            return null;
        }
    }
    public void setInvoiceStatusLabel(String invoiceStatusLabel) {
        //do nothing
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
        result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
        result = prime * result + ((documentNumber == null) ? 0 : documentNumber.hashCode());
        result = prime * result + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
        result = prime * result + ((invoiceNumber == null) ? 0 : invoiceNumber.hashCode());
        result = prime * result + ((invoiceStatus == null) ? 0 : invoiceStatus.hashCode());
        result = prime * result + ((mimeType == null) ? 0 : mimeType.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((subAwardAmtReleasedId == null) ? 0 : subAwardAmtReleasedId.hashCode());
        return result;
    }
    
    /**
     * Typical equals method, but if primary key is not null and is equal then assume equals.
     * @see org.kuali.kra.subaward.bo.SubAwardAssociate#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;        
        SubAwardAmountReleased other = (SubAwardAmountReleased) obj;
        if (subAwardAmtReleasedId == null) {
            if (other.subAwardAmtReleasedId != null)
                return false;
        }
        else { 
            if (!subAwardAmtReleasedId.equals(other.subAwardAmtReleasedId))
                return false;
            else {
                return true;
            }
        }
        if (createdBy == null) {
            if (other.createdBy != null)
                return false;
        }
        else if (!createdBy.equals(other.createdBy))
            return false;
        if (createdDate == null) {
            if (other.createdDate != null)
                return false;
        }
        else if (!createdDate.equals(other.createdDate))
            return false;
        if (documentNumber == null) {
            if (other.documentNumber != null)
                return false;
        }
        else if (!documentNumber.equals(other.documentNumber))
            return false;
        if (effectiveDate == null) {
            if (other.effectiveDate != null)
                return false;
        }
        else if (!effectiveDate.equals(other.effectiveDate))
            return false;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        }
        else if (!endDate.equals(other.endDate))
            return false;
        if (fileName == null) {
            if (other.fileName != null)
                return false;
        }
        else if (!fileName.equals(other.fileName))
            return false;
        if (invoiceNumber == null) {
            if (other.invoiceNumber != null)
                return false;
        }
        else if (!invoiceNumber.equals(other.invoiceNumber))
            return false;
        if (invoiceStatus == null) {
            if (other.invoiceStatus != null)
                return false;
        }
        else if (!invoiceStatus.equals(other.invoiceStatus))
            return false;
        if (mimeType == null) {
            if (other.mimeType != null)
                return false;
        }
        else if (!mimeType.equals(other.mimeType))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        }
        else if (!startDate.equals(other.startDate))
            return false;

        return true;
    }
    public Timestamp getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}