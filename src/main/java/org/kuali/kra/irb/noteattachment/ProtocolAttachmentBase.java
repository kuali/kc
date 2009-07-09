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
import java.util.List;

import org.apache.struts.upload.FormFile;
import org.kuali.kra.SeparatelySequenceableAssociate;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolAssociate;

/**
 * This is the base class for all Protocol Attachments.
 */
public abstract class ProtocolAttachmentBase extends ProtocolAssociate implements SeparatelySequenceableAssociate<Protocol> {

    private static final long serialVersionUID = -2519574730475246022L;
    private static final Integer INITIAL_VERSION = Integer.valueOf(1);
    
    private Long id;
    
    private Long protocolId;
    private Protocol protocol;
       
    private Integer attachmentVersionNumber = INITIAL_VERSION;
    
    private Long fileId;

    private ProtocolAttachmentFile file;
    private transient FormFile newFile;
    
    private List<Protocol> sequenceOwners;
    
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
        this.setProtocol(protocol);
    }
    
    /**
     * Gets the Protocol Attachment Base id.
     * @return the Protocol Attachment Base id
     */
    public Long getId() {
        return this.id;
    }
    
    /**
     * Sets the Protocol Attachment Base id.
     * @param id the Protocol Attachment Base id
     */
    public void setId(Long id) {
        this.id = id;
    }
       
    /**
     * Gets the Protocol id.
     * @return the Protocol
     */
    public Long getProtocolId() {
        return this.protocolId;
    }
    
    /**
     * Sets the Protocol & and the protocolNumber from the passed in protocol.
     * @param protocolId the Protocol id
     */
    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }
    
    /**
     * Gets the Protocol.
     * @return the Protocol
     */
    public Protocol getProtocol() {
        return this.protocol;
    }
    
    /**
     * Sets the Protocol & and the protocolNumber from the passed in protocol.
     * @param protocol the Protocol
     */
    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
        this.initProtocolInfo(protocol);
    }
    
    /**
     * Gets the Protocol Attachment Base Version Number.
     * @return the Protocol Attachment Base Version Number
     */
    public Integer getAttachmentVersionNumber() {
        return this.attachmentVersionNumber;
    }
    
    /**
     * Sets the Protocol Attachment Base Attachment Version Number.
     * @param attachmentVersionNumber the Protocol Attachment Base Attachment Version Number
     */
    public void setAttachmentVersionNumber(Integer attachmentVersionNumber) {
        this.attachmentVersionNumber = attachmentVersionNumber;
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
    public Protocol getLatestOwner() {
        return this.protocol;
    }
    
    /** 
     * Cannot return null.
     * {@inheritDoc}
     */
    public List<Protocol> getSequenceOwners() {
        if (this.sequenceOwners == null) { 
            this.sequenceOwners = new ArrayList<Protocol>();
        }
        return this.sequenceOwners;
    }
    
    /** {@inheritDoc} */
    public void setSequenceOwners(List<Protocol> owners) {
        this.sequenceOwners = owners;
    }
    
    /** {@inheritDoc} */
    public void resetPersistenceState() {
        this.setId(null);

        this.setFileId(null);
        if (this.getFile() != null) {
            this.file.setId(null);
        }
    }
    
    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        final LinkedHashMap<String, Object> hashMap = super.toStringMapper();
        hashMap.put(PropertyName.ATTACHMENT_VERSION.getPropertyName(), this.getAttachmentVersionNumber());
        hashMap.put(PropertyName.FILE_ID.getPropertyName(), this.getFileId());
        hashMap.put(PropertyName.ID.getPropertyName(), this.getId());
        return hashMap;
    }
    
    /**
     * Sets the protocol id and protocolNumber from the passed in protocol.
     * @param aProtocol the Protocol
     */
    private void initProtocolInfo(Protocol aProtocol) {       
        this.setProtocolId(aProtocol != null ? aProtocol.getProtocolId() : null);
        this.setProtocolNumber(aProtocol != null ? aProtocol.getProtocolNumber() : null);
        this.setSequenceNumber(aProtocol != null ? aProtocol.getSequenceNumber() : null);
        
        if (aProtocol != null) {
            addSequenceOwner(aProtocol);
        }
    }
    
    public void addSequenceOwner(Protocol aProtocol) {
        if (!this.getSequenceOwners().contains(aProtocol)) {
            final List<Protocol> owners = this.getSequenceOwners();
            owners.add(aProtocol);
            this.setSequenceOwners(owners);
        }
    }
    
    public void removeSequenceOwner(Protocol aProtocol) {
        final List<Protocol> owners = this.getSequenceOwners();
        
        //removing any duplicates just in case
        boolean removed = owners.remove(this);
        while (removed) {
            removed = owners.remove(this);
        }
        
        this.setSequenceOwners(owners);
    }
    
    /** 
     * inits the object for a different Protocol.
     * @param aProtocol the protocol
     */
    public void init(Protocol aProtocol) {
        this.setId(null);
        this.setProtocol(aProtocol);
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
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.attachmentVersionNumber == null) ? 0 : this.attachmentVersionNumber.hashCode());
        result = prime * result + ((this.file == null) ? 0 : this.file.hashCode());
        result = prime * result + ((this.fileId == null) ? 0 : this.fileId.hashCode());
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.protocolId == null) ? 0 : this.protocolId.hashCode());
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
        if (this.attachmentVersionNumber == null) {
            if (other.attachmentVersionNumber != null) {
                return false;
            }
        } else if (!this.attachmentVersionNumber.equals(other.attachmentVersionNumber)) {
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
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        if (this.protocolId == null) {
            if (other.protocolId != null) {
                return false;
            }
        } else if (!this.protocolId.equals(other.protocolId)) {
            return false;
        }
        return true;
    }
    
    /**
     * Contains all the property names in this class.
     */
    public static enum PropertyName {
        ATTACHMENT_VERSION("attachmentVersionNumber"),
        FILE_ID("fileId"), ID("id"), PROTOCOL_ID("protocolId");
        
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
