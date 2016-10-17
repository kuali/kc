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
import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.subaward.service.SubAwardService;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.kew.api.document.DocumentStatus;
import org.kuali.rice.krad.bo.PersistableAttachment;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

public class SubAwardAmountReleased  extends SubAwardAssociate implements KcFile, PersistableAttachment {

    private static final long serialVersionUID = 1L;
    private Integer subAwardAmtReleasedId;
    private ScaleTwoDecimal amountReleased;
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
    private transient FormFile newFile;
    private transient SubAwardService subAwardService;

    public Integer getSubAwardAmtReleasedId() {
        return subAwardAmtReleasedId;
    }

    public void setSubAwardAmtReleasedId(Integer subAwardAmtReleasedId) {
        this.subAwardAmtReleasedId = subAwardAmtReleasedId;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ScaleTwoDecimal getAmountReleased() {
        return amountReleased;
    }

    public void setAmountReleased(ScaleTwoDecimal amountReleased) {
        this.amountReleased = amountReleased;
    }

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
    
    public FormFile getNewFile() {
        return this.newFile;
    }

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
    public byte[] getAttachmentContent() {
        return getDocument();
    }
    @Override
    public void setAttachmentContent(byte[] arg0) {
        //do nothing as this will be called by the maint framework
        //in many cases with null when it is inappropriate to do so.
        //this.document = arg0;
    }

    @Override
    public String getContentType() {
        return getMimeType();
    }

    @Override
    public void setContentType(String contentType) {
        setMimeType(contentType);
    }

    @Override
    public SubAward getSubAward() {
        if (getSubAwardId() != null) {
            return getSubAwardService().getActiveSubAward(getSubAwardId());
        } else {
            return null;
        }
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
