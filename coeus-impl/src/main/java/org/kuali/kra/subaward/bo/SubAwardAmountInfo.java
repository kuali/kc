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

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.coeus.common.framework.attachment.KcAttachmentDataDao;
import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.subaward.fdp.SubAwardModificationType;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.sql.Date;

/**
 * This class represents a subAwardAmountInfo. It describes the amount
 * released and allowed for the subAward.
 */
public class SubAwardAmountInfo extends KcPersistableBusinessObjectBase implements KcFile {

    private static final long serialVersionUID = 1L;

    private Integer subAwardAmountInfoId;
    
    private Long subAwardId; 
    
    private String subAwardCode;
    
    private Integer sequenceNumber;
    
    private Integer lineNumber;

    private ScaleTwoDecimal obligatedAmount;

    private ScaleTwoDecimal obligatedChange;

    private ScaleTwoDecimal anticipatedAmount;

    private ScaleTwoDecimal anticipatedChange;

    private Date effectiveDate;

    private String comments;

    private String fileName;
    
    private String fileDataId;
    
    private String oldFileDataId;

    private transient SoftReference<byte[]> document;

    private String mimeType;

    private AttachmentFile file;
    
    transient private FormFile newFile;

    private Date modificationEffectiveDate ;
    
    private String modificationID;
    
    private String modificationTypeCode;
    private SubAwardModificationType modificationType;
    
    private Date periodofPerformanceStartDate;
    
    private Date periodofPerformanceEndDate;
    
    private SubAward subAward;
    
    private transient KcAttachmentDataDao kcAttachmentDataDao;
    
    /**
     * the SubAwardAmountInfo constructor.
     */
    public SubAwardAmountInfo() {

    }
    /**
     * Gets the subAwardAmountInfoId.
     * @return subAwardAmountInfoId.
     */
    public Integer getSubAwardAmountInfoId() {
        return subAwardAmountInfoId;
    }
    /**
     * Sets the  subAwardAmountInfoId attribute value.
     * @param subAwardAmountInfoId The subAwardAmountInfoId to set.
     */
    public void setSubAwardAmountInfoId(Integer subAwardAmountInfoId) {
        this.subAwardAmountInfoId = subAwardAmountInfoId;
    }
    /**
     * Gets the subAwardId.
     * @return subAwardId.
     */
    public Long getSubAwardId() {
        return subAwardId;
    }
    /**
     * Sets the  subAwardId attribute value.
     * @param subAwardId The subAwardId to set.
     */
    public void setSubAwardId(Long subAwardId) {
        this.subAwardId = subAwardId;
    }
    /**
     * Gets the subAwardCode.
     * @return subAwardCode.
     */
    public String getSubAwardCode() {
        return subAwardCode;
    }
    /**
     * Sets the  subAwardCode attribute value.
     * @param subAwardCode The subAwardCode to set.
     */
    public void setSubAwardCode(String subAwardCode) {
        this.subAwardCode = subAwardCode;
    }

    /**
     * Gets the lineNumber.
     * @return lineNumber.
     */
    public Integer getLineNumber() {
        return lineNumber;
    }
    /**
     * Sets the  lineNumber attribute value.
     * @param lineNumber The lineNumber to set.
     */
    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }
    /**
     * Gets the effectiveDate.
     * @return effectiveDate.
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }
    /**
     * Sets the  effectiveDate attribute value.
     * @param effectiveDate The effectiveDate to set.
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    /**
     * Gets the comments.
     * @return comments.
     */
    public String getComments() {
        return comments;
    }
    /**
     * Sets the  comments attribute value.
     * @param comments The comments to set.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    /**
     * Gets the fileName.
     * @return fileName.
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * Sets the  fileName attribute value.
     * @param fileName The fileName to set.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getDocument() {
        if (document != null) {
            byte[] existingData = document.get();
            if (existingData != null) {
                return existingData;
            }
        }
        //if we didn't have a softreference, grab the data from the db
        byte[] newData = getKcAttachmentDataDao().getData(fileDataId);
        document = new SoftReference<byte[]>(newData);
        return newData;
    }

    public void setDocument(byte[] document) {
        if (document == null || document.length == 0) {
            setFileDataId(null);
        } else {
            setFileDataId(getKcAttachmentDataDao().saveData(document, null));
        }
        this.document = new SoftReference<byte[]>(document);
    }

    /**
     * Gets the mimeType.
     * @return mimeType.
     */ 
    public String getMimeType() {
        return mimeType;
    }
    /**
     * Sets the  mimeType attribute value.
     * @param mimeType The mimeType to set.
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
    /**.
     *
     * This method used to populate the attachment
     *to subAwardInfo object by reading FormFile
     */
    public void populateAttachment() {
        FormFile newFile = getNewFile();
        if (newFile == null) {
        return;
        }
        byte[] newFileData;
        try {
            newFileData = newFile.getFileData();
            setDocument(newFileData);
            if (newFileData.length > 0) {
                setFileName(newFile.getFileName());
                setMimeType(newFile.getContentType());
            }
        } catch (IOException e) {
        	throw new RuntimeException(e);
        }
    }
    /**
     * Gets the obligatedAmount.
     * @return obligatedAmount.
     */
    public ScaleTwoDecimal getObligatedAmount() {
        return obligatedAmount;
    }
    /**
     * Sets the  obligatedAmount attribute value.
     * @param obligatedAmount The obligatedAmount to set.
     */
    public void setObligatedAmount(ScaleTwoDecimal obligatedAmount) {
        this.obligatedAmount = obligatedAmount;
    }
    /**
     * Gets the obligatedChange.
     * @return obligatedChange.
     */
    public ScaleTwoDecimal getObligatedChange() {
        return obligatedChange;
    }
    /**
     * Sets the  obligatedChange attribute value.
     * @param obligatedChange The obligatedChange to set.
     */
    public void setObligatedChange(ScaleTwoDecimal obligatedChange) {
        this.obligatedChange = obligatedChange;
    }
    /**
     * Gets the anticipatedAmount.
     * @return anticipatedAmount.
     */
    public ScaleTwoDecimal getAnticipatedAmount() {
        return anticipatedAmount;
    }
    /**
     * Sets the  anticipatedAmount attribute value.
     * @param anticipatedAmount The anticipatedAmount to set.
     */
    public void setAnticipatedAmount(ScaleTwoDecimal anticipatedAmount) {
        this.anticipatedAmount = anticipatedAmount;
    }
    /**
     * Gets the anticipatedChange.
     * @return anticipatedChange.
     */
    public ScaleTwoDecimal getAnticipatedChange() {
        return anticipatedChange;
    }
    /**
     * Sets the  anticipatedChange attribute value.
     * @param anticipatedChange The anticipatedChange to set.
     */
    public void setAnticipatedChange(ScaleTwoDecimal anticipatedChange) {
        this.anticipatedChange = anticipatedChange;
    }

    /**
     * Gets the  Attachment File.
     */
    public AttachmentFile getFile() {
        return this.file;
    }

    /**
     * Sets the  Attachment File.
     */
    public void setFile(AttachmentFile file) {
        this.file = file;
    }
    /**
     * Gets the newFile.
     * @return newFile.
     */
    public FormFile getNewFile() {
        return this.newFile;
    }
    /**
     * Sets the  newFile.
     */
    public void setNewFile(FormFile newFile) {
        this.newFile = newFile;
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

    /**
     * Gets the modificationEffectiveDate.
     * @return modificationEffectiveDate.
     */
    public Date getModificationEffectiveDate() {
        return modificationEffectiveDate;
    }
    /**
     * Sets the modificationEffectiveDate.
     * @param modificationEffectiveDate the modificationEffectiveDate.
     */
    public void setModificationEffectiveDate(Date modificationEffectiveDate) {
        this.modificationEffectiveDate = modificationEffectiveDate;
    }
    /**
     * Gets the modificationID.
     * @return modificationID.
     */
    public String getModificationID() {
        return modificationID;
    }
    /**
     * Sets the modificationID.
     * @param modificationID the modificationID.
     */
    public void setModificationID(String modificationID) {
        this.modificationID = modificationID;
    }
    /**
     * Gets the periodofPerformanceStartDate.
     * @return periodofPerformanceStartDate.
     */
    public Date getPeriodofPerformanceStartDate() {
        return periodofPerformanceStartDate;
    }
    /**
     * Sets the periodofPerformanceStartDate.
     * @param periodofPerformanceStartDate the periodofPerformanceStartDate.
     */
    public void setPeriodofPerformanceStartDate(Date periodofPerformanceStartDate) {
        this.periodofPerformanceStartDate = periodofPerformanceStartDate;
    }
    /**
     * Gets the periodofPerformanceEndDate.
     * @return periodofPerformanceEndDate.
     */
    public Date getPeriodofPerformanceEndDate() {
        return periodofPerformanceEndDate;
    }
    /**
     * Sets the periodofPerformanceEndDate.
     * @param periodofPerformanceEndDate the periodofPerformanceEndDate.
     */
    public void setPeriodofPerformanceEndDate(Date periodofPerformanceEndDate) {
        this.periodofPerformanceEndDate = periodofPerformanceEndDate;
    }
	public SubAward getSubAward() {
		return subAward;
	}
	public void setSubAward(SubAward subAward) {
		this.subAward = subAward;
		if (subAward != null) {
			this.subAwardId = subAward.getSubAwardId();
			this.sequenceNumber = subAward.getSequenceNumber();
			this.subAwardCode = subAward.getSubAwardCode();
		} else {
			this.subAwardId = null;
			this.sequenceNumber = null;
			this.subAwardCode = null;
		}
	}
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	
	public String getFileDataId() {
		return fileDataId;
	}
	public void setFileDataId(String fileDataId) {
		if (!StringUtils.equals(this.fileDataId, fileDataId)) {
			oldFileDataId = this.fileDataId;
		}
		this.fileDataId = fileDataId;
	}
	
	@Override
    public void postRemove() {
		super.postRemove();
        if (getFileDataId() != null) {
            getKcAttachmentDataDao().removeData(getFileDataId());
        }
    }
	
	@Override
	public void postUpdate() {
		super.postUpdate();
		if (oldFileDataId != null && !StringUtils.equals(fileDataId, oldFileDataId)) {
			getKcAttachmentDataDao().removeData(oldFileDataId);
			oldFileDataId = null;
		}
	}
	
	public KcAttachmentDataDao getKcAttachmentDataDao() {
		if (kcAttachmentDataDao == null) {
			kcAttachmentDataDao = KcServiceLocator.getService(KcAttachmentDataDao.class);
		}
		return kcAttachmentDataDao;
	}
	public void setKcAttachmentDataDao(KcAttachmentDataDao kcAttachmentDao) {
		this.kcAttachmentDataDao = kcAttachmentDao;
	}
	public String getModificationTypeCode() {
		return modificationTypeCode;
	}
	public void setModificationTypeCode(String modificationTypeCode) {
		this.modificationTypeCode = modificationTypeCode;
	}
	public SubAwardModificationType getModificationType() {
		return modificationType;
	}
	public void setModificationType(SubAwardModificationType modificationType) {
		this.modificationType = modificationType;
	}
}
