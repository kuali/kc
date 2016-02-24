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
package org.kuali.kra.award.notesandattachments.attachments;

import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AwardAttachment extends AwardAssociate implements Comparable<AwardAttachment> {


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
    
    private String documentStatusCode;
    
    private boolean modifyAttachment=false;

    private Timestamp lastUpdateTimestamp;

    private String lastUpdateUser;

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

    public String getDocumentStatusCode() {
		return documentStatusCode;
	}

	public void setDocumentStatusCode(String documentStatusCode) {
		this.documentStatusCode = documentStatusCode;
	}

	public boolean isModifyAttachment() {
		return modifyAttachment;
	}

	public void setModifyAttachment(boolean modifyAttachment) {
		this.modifyAttachment = modifyAttachment;
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

    @Override
    protected void preUpdate() {
        super.preUpdate();
        if (this.getVersionNumber() == null) {
            this.setVersionNumber(new Long(0));
        }
    }

    public String getLastUpdateUserName() {
        Person updateUser = KcServiceLocator.getService(PersonService.class).getPersonByPrincipalName(this.getLastUpdateUser());
        return updateUser != null ? updateUser.getName() : this.getUpdateUser();
    }

    public Timestamp getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public void setLastUpdateTimestamp(Timestamp lastUpdateTimestamp) {
        this.lastUpdateTimestamp = lastUpdateTimestamp;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    /**
     * Implements comparable based on the awardAttachmentId.
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(AwardAttachment o) {
        return this.getAwardAttachmentId().compareTo(o.getAwardAttachmentId());
    }
    
    @Override
    protected void preRemove() {
        super.preRemove();
        //if we have a file and its linked to other versions make sure its not deleted with this version.
        if (getFileId() != null) {
            Map<String, Object> values = new HashMap<String, Object>();
            values.put("fileId", getFileId());
            List<AwardAttachment> otherAttachmentVersions = 
                (List<AwardAttachment>) KcServiceLocator.getService(BusinessObjectService.class).findMatching(AwardAttachment.class, values);
            if (otherAttachmentVersions.size() > 1) {
                setFile(null);
                setFileId(null);
            }
        }
    }

    @Override
    protected void prePersist() {
        super.prePersist();
        if (lastUpdateUser == null) {
            setLastUpdateUser(getUpdateUser());
        }
        if (lastUpdateTimestamp == null) {
            setLastUpdateTimestamp(getUpdateTimestamp());
        }
    }
}
