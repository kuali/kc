/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.award.notesandattachments.attachments;

import java.sql.Timestamp;

import org.apache.struts.upload.FormFile;
import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;

/**
 * This class...
 */
public class AwardAttachment extends AwardAssociate implements Comparable<AwardAttachment> {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 502762283098287794L;

    private Long awardAttachmentId;

    private Long fileId;

    private Long awardId;

    private AttachmentFile file;

    private transient FormFile newFile;

    private String typeCode;

    private AwardAttachmentType type;

    private Integer documentId;

    private String description;

    /**
     * empty ctor to satisfy JavaBean convention.
     */
    public AwardAttachment() {
        super();
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
    public AwardAttachment(final Award award) {
        this.setAward(award);
    }

    /**
     * Gets the awardId attribute. 
     * @return Returns the awardId.
     */
    public Long getAwardId() {
        return awardId;
    }

    /**
     * Sets the awardId attribute value.
     * @param awardId The awardId to set.
     */
    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }

    public String getTypeCode() {
        return this.typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Integer getDocumentId() {
        return this.documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttachmentDescription() {
        return "Award Attachment";
    }

    /**
     * Gets the Award Attachment File.
     */
    public AttachmentFile getFile() {
        return this.file;
    }

    /**
     * Sets the Award Attachment File.
     */
    public void setFile(AttachmentFile file) {
        this.file = file;
    }

    /**
     * Gets the Award Attachment New File.
     * @return the Award Attachment New File
     */
    public FormFile getNewFile() {
        return this.newFile;
    }

    /**
     * Sets the Award Attachment New File.
     * @param newFile the Award Attachment New File
     */
    public void setNewFile(FormFile newFile) {
        this.newFile = newFile;
    }

    /**
     * Gets the file Id. 
     * @return the file Id.
     */
    public Long getFileId() {
        return this.fileId;
    }

    /**
     * Sets the file Id.
     * @param fileId the file Id.
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    /**
     * Gets the  id.
     * @return the  id
     */
    public Long getAwardAttachmentId() {
        return this.awardAttachmentId;
    }

    /**
     * Sets the id.
     * @param id the id
     */
    public void setAwardAttachmentId(Long awardAttachmentId) {
        this.awardAttachmentId = awardAttachmentId;
    }

    /**
     * Gets the type attribute. 
     * @return Returns the type.
     */
    public AwardAttachmentType getAwardAttachmentType() {
        return type;
    }

    /**
     * Gets the type attribute. 
     * @return Returns the type.
     */
    public AwardAttachmentType getType() {
        return type;
    }

    /**
     * Sets the type attribute value.
     * @param type The type to set.
     */
    public void setType(AwardAttachmentType type) {
        this.type = type;
    }

    /** {@inheritDoc} */
    public boolean supportsVersioning() {
        return false;
    }

    /** 
     * {@inheritDoc}
     * also nulling the person id because when saving after versioning, the person id is reverting to the wrong BO.
     */
    public void resetPersistenceState() {
        this.setAwardAttachmentId(null);
    }

    /**
     * Checks if an attachment is new (not persisted yet).
     * @return true if new false if not
     */
    public boolean isNew() {
        return this.getAwardAttachmentId() == null;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((documentId == null) ? 0 : documentId.hashCode());
        result = prime * result + ((typeCode == null) ? 0 : typeCode.hashCode());
        result = prime * result + ((this.file == null) ? 0 : this.file.hashCode());
        result = prime * result + ((this.fileId == null) ? 0 : this.fileId.hashCode());
        result = prime * result + ((this.awardAttachmentId == null) ? 0 : this.awardAttachmentId.hashCode());
        return result;
    }

    /** {@inheritDoc} */
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
        AwardAttachment other = (AwardAttachment) obj;
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (documentId == null) {
            if (other.documentId != null) {
                return false;
            }
        } else if (!documentId.equals(other.documentId)) {
            return false;
        }
        if (typeCode == null) {
            if (other.typeCode != null) {
                return false;
            }
        } else if (!typeCode.equals(other.typeCode)) {
            return false;
        }
        if (this.file == null) {
            if (other.file != null) {
                return false;
            }
        } else if (!this.file.equals(other.file)) {
            return false;
        }
        if (this.fileId == null) {
            if (other.fileId != null) {
                return false;
            }
        } else if (!this.fileId.equals(other.fileId)) {
            return false;
        }
        if (this.awardAttachmentId == null) {
            if (other.awardAttachmentId != null) {
                return false;
            }
        } else if (!this.awardAttachmentId.equals(other.awardAttachmentId)) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @see org.kuali.kra.bo.KraPersistableBusinessObjectBase#beforeUpdate(org.apache.ojb.broker.PersistenceBroker)
     */
    @Override
    protected void preUpdate() {
        super.preUpdate();
        if (this.getVersionNumber() == null) {
            this.setVersionNumber(new Long(0));
        }
    }

    /**
     * 
     * This method returns the full name of the update user.
     * @return
     */
    public String getUpdateUserName() {
        Person updateUser = KraServiceLocator.getService(PersonService.class).getPersonByPrincipalName(this.getUpdateUser());
        return updateUser != null ? updateUser.getName() : this.getUpdateUser();
    }

    /**
     * This sets the update time stamp only if it hasn't already been set.
     * @see org.kuali.kra.bo.KraPersistableBusinessObjectBase#setUpdateTimestamp(java.sql.Timestamp)
     */
    @Override
    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        if (getUpdateTimestamp() == null) {
            super.setUpdateTimestamp(updateTimestamp);
        }
    }

    /**
     * Implements comparable based on the awardAttachmentId.
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(AwardAttachment o) {
        return this.getAwardAttachmentId().compareTo(o.getAwardAttachmentId());
    }
}
