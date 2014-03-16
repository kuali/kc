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
package org.kuali.kra.bo.versioning;

import org.kuali.coeus.common.framework.sequence.owner.SequenceOwner;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import java.sql.Date;

public class VersionHistorySearchBo extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = -4851728455206181532L;

    private Long versionHistoryId;

    private String sequenceOwnerClassName;

    private String sequenceOwnerVersionNameField;

    private String sequenceOwnerVersionNameValue;

    private Integer sequenceOwnerSequenceNumber;

    private String statusForOjb;

    private String userId;

    private Date versionDate;

    private transient VersionStatus status;

    private transient SequenceOwner<? extends SequenceOwner<?>> sequenceOwner;


    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sequenceOwnerClassName == null) ? 0 : sequenceOwnerClassName.hashCode());
        result = prime * result + ((sequenceOwnerSequenceNumber == null) ? 0 : sequenceOwnerSequenceNumber.hashCode());
        result = prime * result + ((sequenceOwnerVersionNameValue == null) ? 0 : sequenceOwnerVersionNameValue.hashCode());
        result = prime * result + ((statusForOjb == null) ? 0 : statusForOjb.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((versionDate == null) ? 0 : versionDate.hashCode());
        return result;
    }

    /**
     * Gets the sequenceOwner attribute. 
     * @return Returns the sequenceOwner.
     */
    public SequenceOwner<? extends SequenceOwner<?>> getSequenceOwner() {
        return sequenceOwner;
    }

    /**
     * Gets the className attribute. 
     * @return Returns the className.
     */
    public String getSequenceOwnerClassName() {
        return sequenceOwnerClassName;
    }

    /**
     * Gets the sequenceNumber attribute. 
     * @return Returns the sequenceNumber.
     */
    public Integer getSequenceOwnerSequenceNumber() {
        return sequenceOwnerSequenceNumber;
    }

    /**
     * Gets the sequenceOwnerReferenceIdentifierFieldName attribute. 
     * @return Returns the sequenceOwnerReferenceIdentifierFieldName.
     */
    public String getSequenceOwnerVersionNameField() {
        return sequenceOwnerVersionNameField;
    }

    /**
     * Gets the sequenceOwnerVersionNameValue attribute. 
     * @return Returns the sequenceOwnerVersionNameValue.
     */
    public String getSequenceOwnerVersionNameValue() {
        return sequenceOwnerVersionNameValue;
    }

    /**
     * Gets the status attribute. 
     * @return Returns the status.
     */
    public VersionStatus getStatus() {
        if (status == null && statusForOjb != null) {
            status = VersionStatus.valueOf(statusForOjb);
        }
        return status;
    }

    /**
     * Gets the statusForOjb attribute. 
     * @return Returns the statusForOjb.
     */
    public String getStatusForOjb() {
        if (statusForOjb != null) {
            status = VersionStatus.valueOf(statusForOjb);
        }
        return statusForOjb;
    }

    /**
     * Gets the userId attribute. 
     * @return Returns the userId.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Gets the versionDate attribute. 
     * @return Returns the versionDate.
     */
    public Date getVersionDate() {
        return versionDate;
    }

    /**
     * Gets the versionHistoryId attribute. 
     * @return Returns the versionHistoryId.
     */
    public Long getVersionHistoryId() {
        return versionHistoryId;
    }

    /**
     * Gets the activeVersion attribute. 
     * @return Returns the activeVersion.
     */
    public boolean isActiveVersion() {
        return getStatus() == VersionStatus.ACTIVE;
    }

    /**
     * Sets the sequenceOwner attribute value.
     * @param sequenceOwner The sequenceOwner to set.
     */
    public void setSequenceOwner(SequenceOwner<? extends SequenceOwner<?>> sequenceOwner) {
        this.sequenceOwner = sequenceOwner;
    }

    /**
     * Sets the className attribute value.
     * @param className The className to set.
     */
    public void setSequenceOwnerClassName(String className) {
        this.sequenceOwnerClassName = className;
    }

    /**
     * Sets the sequenceNumber attribute value.
     * @param sequenceNumber The sequenceNumber to set.
     */
    public void setSequenceOwnerSequenceNumber(Integer sequenceNumber) {
        this.sequenceOwnerSequenceNumber = sequenceNumber;
    }

    /**
     * Sets the sequenceOwnerReferenceIdentifierFieldName attribute value.
     * @param sequenceOwnerReferenceIdentifierFieldName The sequenceOwnerReferenceIdentifierFieldName to set.
     */
    public void setSequenceOwnerVersionNameField(String sequenceOwnerReferenceIdentifierFieldName) {
        this.sequenceOwnerVersionNameField = sequenceOwnerReferenceIdentifierFieldName;
    }

    /**
     * Sets the sequenceOwnerVersionNameValue attribute value.
     * @param sequenceOwnerVersionNameValue The sequenceOwnerVersionNameValue to set.
     */
    public void setSequenceOwnerVersionNameValue(String sequenceOwnerVersionNameValue) {
        this.sequenceOwnerVersionNameValue = sequenceOwnerVersionNameValue;
    }

    /**
     * Sets the status attribute value.
     * @param status The status to set.
     */
    public void setStatus(VersionStatus status) {
        if (status != null) {
            statusForOjb = status.name();
        }
        this.status = status;
    }

    /**
     * Sets the statusForOjb attribute value.
     * @param statusForOjb The statusForOjb to set.
     */
    public void setStatusForOjb(String statusForOjb) {
        this.statusForOjb = statusForOjb;
        status = statusForOjb != null ? VersionStatus.valueOf(statusForOjb) : null;
    }

    /**
     * Sets the userId attribute value.
     * @param userId The userId to set.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Sets the versionDate attribute value.
     * @param versionDate The versionDate to set.
     */
    public void setVersionDate(Date versionDate) {
        this.versionDate = versionDate;
    }

    /**
     * Sets the versionHistoryId attribute value.
     * @param versionHistoryId The versionHistoryId to set.
     */
    public void setVersionHistoryId(Long versionHistoryId) {
        this.versionHistoryId = versionHistoryId;
    }
}
