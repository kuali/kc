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
import org.kuali.coeus.common.framework.attachment.KcAttachmentDataDao;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.sql.Date;

public class SubAwardFfataReporting extends KcPersistableBusinessObjectBase implements KcFile {

    private Integer subAwardFfataReportingId;
    private Long subAwardId;
    private Integer subAwardAmountInfoId;
    private String otherTransactionDescription;
    private Date dateSubmitted;
    private String submitterId;
    private String comments;
    private String fileDataId;
    private String fileName;
    private String mimeType;

    private SubAwardAmountInfo subAwardAmountInfo;

    private transient SoftReference<byte[]> fileData;
    private transient String oldFileDataId;
    private transient FormFile newFile;
    private transient KcPerson submitter;
    private transient KcAttachmentDataDao kcAttachmentDataDao;

    public Integer getSubAwardFfataReportingId() {
        return subAwardFfataReportingId;
    }

    public void setSubAwardFfataReportingId(Integer subAwardFfataReportingId) {
        this.subAwardFfataReportingId = subAwardFfataReportingId;
    }

    public Long getSubAwardId() {
        return subAwardId;
    }

    public void setSubAwardId(Long subAwardId) {
        this.subAwardId = subAwardId;
    }

    public Integer getSubAwardAmountInfoId() {
        return subAwardAmountInfoId;
    }

    public void setSubAwardAmountInfoId(Integer subAwardAmountInfoId) {
        this.subAwardAmountInfoId = subAwardAmountInfoId;
    }

    public String getOtherTransactionDescription() {
        return otherTransactionDescription;
    }

    public void setOtherTransactionDescription(String otherTransactionDescription) {
        this.otherTransactionDescription = otherTransactionDescription;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public String getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(String submitterId) {
        this.submitterId = submitterId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public SubAwardAmountInfo getSubAwardAmountInfo() {
        return subAwardAmountInfo;
    }

    public void setSubAwardAmountInfo(SubAwardAmountInfo subAwardAmountInfo) {
        this.subAwardAmountInfo = subAwardAmountInfo;
    }

    public FormFile getNewFile() {
        return newFile;
    }

    public void setNewFile(FormFile newFile) {
        this.newFile = newFile;
    }
    public void populateAttachment() {
        FormFile newFile = getNewFile();
        if (newFile == null) {
            return;
        }
        byte[] newFileData;
        try {
            newFileData = newFile.getFileData();
            setFileData(newFileData);
            if (newFileData.length > 0) {
                setFileName(newFile.getFileName());
                setMimeType(newFile.getContentType());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public byte[] getFileData() {
        if (fileDataId != null) {
            if (fileData != null) {
                byte[] existingData = fileData.get();
                if (existingData != null) {
                    return existingData;
                }
            }
            //if we didn't have a softreference, grab the data from the db
            byte[] newData = getKcAttachmentDataDao().getData(fileDataId);
            fileData = new SoftReference<>(newData);
            return newData;
        }
        return null;
    }

    public void setFileData(byte[] fileData) {
        setFileDataId(getKcAttachmentDataDao().saveData(fileData, null));
        this.fileData = new SoftReference<>(fileData);
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

    public KcPerson getSubmitter() {
        if (!StringUtils.equals(submitterId, submitter != null ? submitter.getPersonId() : null)) {
            this.submitter = StringUtils.isNotBlank(submitterId) ?
                    KcServiceLocator.getService(KcPersonService.class).getKcPersonByPersonId(submitterId) : null;
        }

        return submitter;
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
        return getFileData();
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
}
