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
package org.kuali.coeus.common.framework.version.history;

import org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.util.ObjectUtils;

import java.sql.Date;

public class VersionHistory extends KcPersistableBusinessObjectBase {

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
     * Default c'tor
     */
    public VersionHistory() {
    }

    /**
     * Full c'tor
     * @param newActiveVersion
     * @param status
     * @param userId
     */
    public VersionHistory(SequenceOwner<? extends SequenceOwner<?>> sequenceOwner, VersionStatus status, String userId, Date versionDate) {
        setSequenceOwner(sequenceOwner);
        if (sequenceOwner != null) {
            setSequenceOwnerSequenceNumber(sequenceOwner.getSequenceNumber());
            setSequenceOwnerClassName(sequenceOwner.getClass().getName());
            setSequenceOwnerVersionNameField(sequenceOwner.getVersionNameField());
            setSequenceOwnerVersionNameValue(ObjectUtils.getPropertyValue(sequenceOwner, sequenceOwner.getVersionNameField()).toString());
        }
        setStatus(status);
        setUserId(userId);
        setVersionDate(versionDate);
    }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof VersionHistory)) return false;
        VersionHistory other = (VersionHistory) obj;
        if (sequenceOwnerClassName == null) {
            if (other.sequenceOwnerClassName != null) return false;
        } else if (!sequenceOwnerClassName.equals(other.sequenceOwnerClassName)) return false;
        if (sequenceOwnerSequenceNumber == null) {
            if (other.sequenceOwnerSequenceNumber != null) return false;
        } else if (!sequenceOwnerSequenceNumber.equals(other.sequenceOwnerSequenceNumber)) return false;
        if (sequenceOwnerVersionNameValue == null) {
            if (other.sequenceOwnerVersionNameValue != null) return false;
        } else if (!sequenceOwnerVersionNameValue.equals(other.sequenceOwnerVersionNameValue)) return false;
        if (statusForOjb == null) {
            if (other.statusForOjb != null) return false;
        } else if (!statusForOjb.equals(other.statusForOjb)) return false;
        if (userId == null) {
            if (other.userId != null) return false;
        } else if (!userId.equals(other.userId)) return false;
        if (versionDate == null) {
            if (other.versionDate != null) return false;
        } else if (!versionDate.equals(other.versionDate)) return false;
        return true;
    }

    /**
     * Gets the sequenceOwner attribute. 
     * @return Returns the sequenceOwner.
     * @throws ClassNotFoundException 
     */
    public SequenceOwner<? extends SequenceOwner<?>> getSequenceOwner() {
        if (sequenceOwner == null) {
            try {
                KcServiceLocator.getService(VersionHistoryService.class)
                    .loadSequenceOwner((Class<? extends SequenceOwner>) Class.forName(this.getSequenceOwnerClassName()), this);
            }
            catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
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
        return status == VersionStatus.ACTIVE;
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
