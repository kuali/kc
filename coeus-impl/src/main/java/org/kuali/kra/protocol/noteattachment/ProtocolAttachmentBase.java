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

import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.protocol.ProtocolAssociateBase;
import org.kuali.kra.protocol.ProtocolBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * This is the base class for all ProtocolBase Attachments.
 */
public abstract class ProtocolAttachmentBase extends ProtocolAssociateBase implements TypedAttachment {

    private static final long serialVersionUID = -2519574730475246022L;

    private Long id;

    private Long fileId;

    private Integer documentId;

    private transient AttachmentFile file;

    private transient FormFile newFile;

    @SkipVersioning
    private transient String updateUserFullName;

    /**
     * empty ctor to satisfy JavaBean convention.
     */
    public ProtocolAttachmentBase() {
        super();
    }

    /**
     * Convenience ctor to set the protocol, protocol id and the protocolNumber from the passed in protocol.
     * 
     * <p>
     * This ctor does not validate any of the properties.
     * </p>
     * 
     * @param protocol the protocol.
     */
    public ProtocolAttachmentBase(final ProtocolBase protocol) {
        super(protocol);
    }

    /**
     * Gets the ProtocolBase Attachment Base File.
     * @return the ProtocolBase Attachment Base File
     */
    public AttachmentFile getFile() {
        if (this.fileId != null && this.file == null) {
            refreshReferenceObject("file");
        }
        return this.file;
    }

    /**
     * Sets the ProtocolBase Attachment Base File.
     * @param file the ProtocolBase Attachment Base File
     */
    public void setFile(AttachmentFile file) {
        this.file = file;
    }

    /**
     * Gets the ProtocolBase Attachment Base New File.
     * @return the ProtocolBase Attachment Base New File
     */
    public FormFile getNewFile() {
        return this.newFile;
    }

    /**
     * Sets the ProtocolBase Attachment Base New File.
     * @param newFile the ProtocolBase Attachment Base New File
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
     * A human readable description of the attachment type.
     * @return a description
     */
    public abstract String getAttachmentDescription();

    @Override
    public void resetPersistenceState() {
        this.setId(null);
    }

    /**
     * Gets the  id.
     * @return the  id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the id.
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Adds an attachment to a Collection.
     * @param <T> the type of attachment
     * @param attachment the attachment.
     * @param toCollection the Collection.
     * @throws IllegalArgumentException if the attachment or the list is null.
     */
    public static <T extends ProtocolAttachmentBase> void addAttachmentToCollection(T attachment, Collection<T> toCollection) {
        if (attachment == null) {
            throw new IllegalArgumentException("the attachment is null");
        }
        if (toCollection == null) {
            throw new IllegalArgumentException("the toList is null");
        }
        toCollection.add(attachment);
    }

    /**
     * Adds an attachment to a Collection.
     * @param <T> the type of attachment
     * @param attachment the attachment.
     * @param fromCollection the Collection.
     * @throws IllegalArgumentException if the attachment or the list is null.
     */
    public static <T extends ProtocolAttachmentBase> void removeAttachmentFromCollection(T attachment, Collection<T> fromCollection) {
        if (attachment == null) {
            throw new IllegalArgumentException("the attachment is null");
        }
        if (fromCollection == null) {
            throw new IllegalArgumentException("the toList is null");
        }
        fromCollection.remove(attachment);
    }

    /**
     * Checks if an attachment is new (not persisted yet).
     * @return true if new false if not
     */
    public boolean isNew() {
        return this.getId() == null;
    }

    /**
     * Returns a new collection containing only new attachments.
     * @param <T> the type of attachments in the collection.
     * @param attachments the current collection
     * @return an collection containing only new attachments
     */
    public static <T extends ProtocolAttachmentBase> Collection<T> filterNewAttachments(final Collection<T> attachments) {
        final Collection<T> newAttachments = new ArrayList<T>();
        for (final T attachment : attachments) {
            if (attachment.isNew()) {
                newAttachments.add(attachment);
            }
        }
        return newAttachments;
    }

    /**
     * Returns a new collection containing only exiting attachments.
     * @param <T> the type of attachments in the collection.
     * @param attachments the current collection
     * @return an collection containing only exiting attachments
     */
    public static <T extends ProtocolAttachmentBase> Collection<T> filterExistingAttachments(final Collection<T> attachments) {
        final Collection<T> existingAttachments = new ArrayList<T>(attachments);
        existingAttachments.removeAll(filterNewAttachments(attachments));
        return existingAttachments;
    }

    /**
     * Method to check whether an attachment supports versioning.  Currently, all attachments
     * have structural capabilities to version; however, currently we are not versioning all attachment types
     * to be consistent with other parts of KC.
     * 
     * @return true is attachment supports versioning.
     */
    public abstract boolean supportsVersioning();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.file == null) ? 0 : this.file.hashCode());
        result = prime * result + ((this.fileId == null) ? 0 : this.fileId.hashCode());
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
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
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        ProtocolAttachmentBase other = (ProtocolAttachmentBase) obj;
        if (this.file == null) {
            if (other.file != null) {
                return false;
            }
        } else if (!isSameFile(this.file, other.file)) {
            return false;
        }
        if (this.fileId == null) {
            if (other.fileId != null) {
                return false;
            }
        } else if (!this.fileId.equals(other.fileId)) {
            return false;
        }
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    /*
     * file1.equals(file2) may have issue with getclass() comparison. sometimes class may be AttachmentFile
     * some maybe Attachment$EnhancerCGLIB.  so retest equal with following for now.
     */
    private boolean isSameFile(AttachmentFile obj, AttachmentFile other) {
        if (!Arrays.equals(obj.getData(), other.getData())) {
            return false;
        } else {
            if (obj.getId() != null && other.getId() != null && !obj.getId().equals(other.getId())) {
                return false;
            } else if ((obj.getId() != null && other.getId() == null) || (obj.getId() == null && other.getId() != null)) {
                return false;
            }
        }
        if (obj.getName() == null) {
            if (other.getName() != null) {
                return false;
            }
        } else if (!obj.getName().equals(other.getName())) {
            return false;
        }
        if (obj.getType() == null) {
            if (other.getType() != null) {
                return false;
            }
        } else if (!obj.getType().equals(other.getType())) {
            return false;
        }
        return true;
    }

    /**
     * Contains all the property names in this class.
     */
    public static enum PropertyName {

        FILE_ID("fileId"), ID("id");

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

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    /**
     * Gets the updateUserFullName attribute. 
     * @return Returns the updateUserFullName.
     */
    public String getUpdateUserFullName() {
        return updateUserFullName;
    }

    /**
     * Sets the updateUserFullName attribute value.
     * @param updateUserFullName The updateUserFullName to set.
     */
    public void setUpdateUserFullName(String updateUserFullName) {
        this.updateUserFullName = updateUserFullName;
    }
}
