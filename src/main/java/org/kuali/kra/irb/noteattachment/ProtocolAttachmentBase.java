/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.noteattachment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

import org.apache.struts.upload.FormFile;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolSeparateAssociate;

/**
 * This is the base class for all Protocol Attachments.
 */
public abstract class ProtocolAttachmentBase extends ProtocolSeparateAssociate {

    private static final long serialVersionUID = -2519574730475246022L;
    
    private Long fileId;

    private ProtocolAttachmentFile file;
    private transient FormFile newFile;
    
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
    public ProtocolAttachmentBase(final Protocol protocol) {
        super(protocol);
    }
    
    /**
     * Gets the Protocol Attachment Base File.
     * @return the Protocol Attachment Base File
     */
    public ProtocolAttachmentFile getFile() {
        return this.file;
    }
    
    /**
     * Sets the Protocol Attachment Base File.
     * @param file the Protocol Attachment Base File
     */
    public void setFile(ProtocolAttachmentFile file) {
        this.file = file;
    }
    
    /**
     * Gets the Protocol Attachment Base New File.
     * @return the Protocol Attachment Base New File
     */
    public FormFile getNewFile() {
        return this.newFile;
    }

    /**
     * Sets the Protocol Attachment Base New File.
     * @param newFile the Protocol Attachment Base New File
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
    
    /** {@inheritDoc} */
    @Override
    public void resetPersistenceState() {
        super.resetPersistenceState();

        this.setFileId(null);
        if (this.getFile() != null) {
            this.file.setId(null);
        }
    }
    
    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        final LinkedHashMap<String, Object> hashMap = super.toStringMapper();
        hashMap.put(PropertyName.FILE_ID.getPropertyName(), this.getFileId());
        return hashMap;
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
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.file == null) ? 0 : this.file.hashCode());
        result = prime * result + ((this.fileId == null) ? 0 : this.fileId.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
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
        final ProtocolAttachmentBase other = (ProtocolAttachmentBase) obj;
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
        return true;
    }
    
    /**
     * Contains all the property names in this class.
     */
    public static enum PropertyName {
        FILE_ID("fileId");
        
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
}
