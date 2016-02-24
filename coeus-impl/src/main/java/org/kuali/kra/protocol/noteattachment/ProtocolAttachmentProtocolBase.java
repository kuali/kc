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
package org.kuali.kra.protocol.noteattachment;

import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.util.ObjectUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class represents the ProtocolBase Attachment ProtocolBase.
 */
public abstract class ProtocolAttachmentProtocolBase extends ProtocolAttachmentBase {

    private static final long serialVersionUID = -7115904344245464654L;

    public static final String INCOMPLETE_STATUS_CODE = "2";
    public static final String COMPLETE_STATUS_CODE = "1";
    
    // 1-Complete, 2-Incomplete.  an attachment status must be 'complete' before this protocol can be submitted.  
    protected String statusCode;

    protected ProtocolAttachmentStatusBase status;

    protected String contactName;

    protected String contactEmailAddress;

    protected String contactPhoneNumber;

    protected String comments;

    protected String typeCode;

    protected ProtocolAttachmentTypeBase type;

    protected String description;

    // documentStatusCode : 1-Draft, 2-Finalized, 3-Deleted  
    // All new files are 'Draft'.  When protocol is versioned, all 'Draft' become 'Finalized'  
    // 'delete' will set this code to 'Deleted'.  
    protected String documentStatusCode;
    
    protected Integer attachmentVersion;

    protected Timestamp createTimestamp;

    protected List<ProtocolAttachmentProtocolBase> versions;

    // an indicator to decide whether to display this file in protocol attachment panel or not  
    protected boolean active = true;

    // an indicator of whether this file has been changed/replaced or not.  This is if documentstatus is 1 or 3.  
    // if it is changed, then the updateuser and updatetimestamp of this record will be updated.  
    protected boolean changed = false;

    private transient KcPersonService kcPersonService;

    /**
     * empty ctor to satisfy JavaBean convention.
     */
    protected ProtocolAttachmentProtocolBase() {
        super();
        attachmentVersion = 1;
    }

    /**
     * Convenience ctor to add the protocol as an owner.
     * 
     * <p>
     * This ctor does not validate any of the properties.
     * </p>
     * 
     * @param protocol the protocol.
     */
    protected ProtocolAttachmentProtocolBase(final ProtocolBase protocol) {
        super(protocol);
        attachmentVersion = 1;
    }

    /**
     * Gets the ProtocolBase Attachment ProtocolBase Status.
     * @return the ProtocolBase Attachment ProtocolBase Status
     */
    public ProtocolAttachmentStatusBase getStatus() {
        return this.status;
    }

    /**
     * Sets the ProtocolBase Attachment ProtocolBase Status.
     * @param status the ProtocolBase Attachment ProtocolBase Status
     */
    public void setStatus(ProtocolAttachmentStatusBase status) {
        this.status = status;
    }

    /**
     * Gets the ProtocolBase Attachment ProtocolBase Contact Name.
     * @return the ProtocolBase Attachment ProtocolBase Contact Name
     */
    public String getContactName() {
        return this.contactName;
    }

    /**
     * Sets the ProtocolBase Attachment ProtocolBase Contact Name.
     * @param contactName the ProtocolBase Attachment ProtocolBase Contact Name
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Gets the ProtocolBase Attachment ProtocolBase Contact Email Address.
     * @return the ProtocolBase Attachment ProtocolBase Contact Email Address
     */
    public String getContactEmailAddress() {
        return this.contactEmailAddress;
    }

    /**
     * Sets the ProtocolBase Attachment ProtocolBase Contact Email Address.
     * @param contactEmailAddress the ProtocolBase Attachment ProtocolBase Contact Email Address
     */
    public void setContactEmailAddress(String contactEmailAddress) {
        this.contactEmailAddress = contactEmailAddress;
    }

    /**
     * Gets the ProtocolBase Attachment ProtocolBase Contact Phone Number.
     * @return the ProtocolBase Attachment ProtocolBase Contact Phone Number
     */
    public String getContactPhoneNumber() {
        return this.contactPhoneNumber;
    }

    /**
     * Sets the ProtocolBase Attachment ProtocolBase Contact Phone Number.
     * @param contactPhoneNumber the ProtocolBase Attachment ProtocolBase Contact Phone Number
     */
    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    /**
     * Gets the ProtocolBase Attachment ProtocolBase Comments.
     * @return the ProtocolBase Attachment ProtocolBase Comments
     */
    public String getComments() {
        return this.comments;
    }

    /**
     * Sets the ProtocolBase Attachment ProtocolBase comments.
     * @param comments the ProtocolBase Attachment ProtocolBase comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Gets the status Code. 
     * @return the status Code.
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * Sets the status Code.
     * @param statusCode the status Code.
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public ProtocolAttachmentTypeBase getType() {
        return this.type;
    }

    @Override
    public void setType(ProtocolAttachmentTypeBase type) {
        this.type = type;
    }

    @Override
    public String getTypeCode() {
        return this.typeCode;
    }

    @Override
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    @Override
    public abstract String getGroupCode();

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public abstract String getAttachmentDescription();

    /**
     * Gets the versions attribute. 
     * @return Returns the versions.
     */
    public List<ProtocolAttachmentProtocolBase> getVersions() {
        if (this.versions == null) {
            this.versions = new ArrayList<ProtocolAttachmentProtocolBase>();
        }
        this.versions.clear();
        // TODO : since this will be called by tag, so should not call service  
        //this.versions.addAll(KcServiceLocator.getService(ProtocolAttachmentService.class).getAttachmentsWithOlderFileVersions(this, ProtocolAttachmentProtocolBase.class));  
        // need this refresh here.  change and save will not update this list automatically.  not sure why ?  
        // this is still calling persistenceservice eventually  
        // probably do it in postsave  
        //this.getProtocol().refreshReferenceObject("attachmentProtocols");  
        for (ProtocolAttachmentProtocolBase attachment : this.getProtocol().getAttachmentProtocols()) {
            if (attachment.getDocumentId().equals(this.getDocumentId())) {
                this.versions.add(attachment);
            }
        }
        if (this.versions.size() == 1) {
            this.versions.clear();
        }
        Collections.sort(this.versions, new Comparator<ProtocolAttachmentProtocolBase>() {

            public int compare(ProtocolAttachmentProtocolBase attachment1, ProtocolAttachmentProtocolBase attachment2) {
                return attachment2.getUpdateTimestamp().compareTo(attachment1.getUpdateTimestamp());
            }
        });
        return this.versions;
    }

    /**
     * Sets the versions attribute value.
     * @param versions The versions to set.
     */
    public void setVersions(List<ProtocolAttachmentProtocolBase> versions) {
        this.versions = versions;
    }

    @Override
    public boolean supportsVersioning() {
        return true;
    }

    public abstract boolean isDraft();
    
    public abstract void setDraft();

    public abstract boolean isFinal();
    
    public abstract void setFinal();

    public abstract boolean isDeleted();

    public abstract void setDeleted();
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        result = prime * result + ((contactEmailAddress == null) ? 0 : contactEmailAddress.hashCode());
        result = prime * result + ((contactName == null) ? 0 : contactName.hashCode());
        result = prime * result + ((contactPhoneNumber == null) ? 0 : contactPhoneNumber.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        //        result = prime * result + ((documentId == null) ? 0 : documentId.hashCode());  
        result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
        result = prime * result + ((typeCode == null) ? 0 : typeCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ProtocolAttachmentProtocolBase other = (ProtocolAttachmentProtocolBase) obj;
        if (comments == null) {
            if (other.comments != null) {
                return false;
            }
        } else if (!comments.equals(other.comments)) {
            return false;
        }
        if (contactEmailAddress == null) {
            if (other.contactEmailAddress != null) {
                return false;
            }
        } else if (!contactEmailAddress.equals(other.contactEmailAddress)) {
            return false;
        }
        if (contactName == null) {
            if (other.contactName != null) {
                return false;
            }
        } else if (!contactName.equals(other.contactName)) {
            return false;
        }
        if (contactPhoneNumber == null) {
            if (other.contactPhoneNumber != null) {
                return false;
            }
        } else if (!contactPhoneNumber.equals(other.contactPhoneNumber)) {
            return false;
        }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (statusCode == null) {
            if (other.statusCode != null) {
                return false;
            }
        } else if (!statusCode.equals(other.statusCode)) {
            return false;
        }
        if (typeCode == null) {
            if (other.typeCode != null) {
                return false;
            }
        } else if (!typeCode.equals(other.typeCode)) {
            return false;
        }
        return true;
    }

    /**
     * Contains all the property names in this class.
     */
    public static enum PropertyName {

        COMMENTS("comments"), EMAIL("contactEmailAddress"), CONTACT_NAME("contactName"), PHONE("contactPhoneNumber"), STATUS_CODE("statusCode"), DOCUMENT_STATUS_CODE("documentStatusCode"), ATTACHMENT_VERSION("attachmentVersion"), CREATE_TIMESTAMP("createTimestamp");

        private final String name;

        /**
         * Sets the enum properties.
         * @param name the name.
         */
        PropertyName(final String name) {
            this.name = name;
        }

        /**
         * Gets the property name.
         * @return the the property name.
         */
        public String getPropertyName() {
            return this.name;
        }

        /**
         * Gets the {@link #getPropertyName() propertyName()}.
         * @return {@link #getPropertyName() propertyName()}
         */
        @Override
        public String toString() {
            return this.name;
        }
    }

    public String getDocumentStatusCode() {
        return documentStatusCode;
    }

    public void setDocumentStatusCode(String documentStatusCode) {
        this.documentStatusCode = documentStatusCode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        if (getDocumentStatusCode() == null || updateTimestamp == null || getUpdateTimestamp() == null || isAttachmentUpdated()) {
            super.setUpdateTimestamp(updateTimestamp);
            // timestamp is updated after user, so setchanged to false.  
            setChanged(false);
        }
    }

    @Override
    public void setUpdateUser(String updateUser) {
        if (getDocumentStatusCode() == null || updateUser == null || getUpdateUser() == null || isAttachmentUpdated()) {
            super.setUpdateUser(updateUser);
        }
    }

    /*
     * utility method to see if this attachment has been updated.
     */
    private boolean isAttachmentUpdated() {
        return ((isDraft() || isDeleted()) && isChanged());
    }

    public Integer getAttachmentVersion() {
        return attachmentVersion;
    }

    public void setAttachmentVersion(Integer attachmentVersion) {
        this.attachmentVersion = attachmentVersion;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        if (getCreateTimestamp() == null || createTimestamp == null) {
            this.createTimestamp = createTimestamp;
        }
    }

    @Override
    protected void prePersist() {
        super.prePersist();
        if (getCreateTimestamp() == null) {
            setCreateTimestamp(((DateTimeService) KcServiceLocator.getService(Constants.DATE_TIME_SERVICE_NAME)).getCurrentTimestamp());
        }
    }

    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }

    /**
     *
     * This is ahelper method to get author person name
     * @return
     */
    public String getAuthorPersonName() {
        KcPerson person = this.getKcPersonService().getKcPersonByUserName(getUpdateUser());
        return ObjectUtils.isNull(person) ? "Person not found" : person.getFullName();
    }
}
