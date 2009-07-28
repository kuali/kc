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
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDao;

/**
 * This class represents the Protocol Attachment Protocol.
 */
public class ProtocolAttachmentProtocol extends ProtocolAttachmentBase implements TypedAttachment {

    private static final long serialVersionUID = -7115904344245464654L;
    private static final String GROUP_CODE = "1";
    
    private String statusCode;
    private ProtocolAttachmentStatus status;
    
    private String contactName;
    private String contactEmailAddress;
    private String contactPhoneNumber;
    private String comments;
    
    private String typeCode;
    private ProtocolAttachmentType type;
    private Integer documentId;
    private String description;
    
    private List<ProtocolAttachmentProtocol> versions;
    
    /**
     * empty ctor to satisfy JavaBean convention.
     */
    public ProtocolAttachmentProtocol() {
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
    public ProtocolAttachmentProtocol(final Protocol protocol) {
        super(protocol);
    }
    
    /**
     * Gets the Protocol Attachment Protocol Status.
     * @return the Protocol Attachment Protocol Status
     */
    public ProtocolAttachmentStatus getStatus() {
        return this.status;
    }
    
    /**
     * Sets the Protocol Attachment Protocol Status.
     * @param status the Protocol Attachment Protocol Status
     */
    public void setStatus(ProtocolAttachmentStatus status) {
        this.status = status;
    }
    
    /**
     * Gets the Protocol Attachment Protocol Contact Name.
     * @return the Protocol Attachment Protocol Contact Name
     */
    public String getContactName() {
        return this.contactName;
    }
    
    /**
     * Sets the Protocol Attachment Protocol Contact Name.
     * @param contactName the Protocol Attachment Protocol Contact Name
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    
    /**
     * Gets the Protocol Attachment Protocol Contact Email Address.
     * @return the Protocol Attachment Protocol Contact Email Address
     */
    public String getContactEmailAddress() {
        return this.contactEmailAddress;
    }
    
    /**
     * Sets the Protocol Attachment Protocol Contact Email Address.
     * @param contactEmailAddress the Protocol Attachment Protocol Contact Email Address
     */
    public void setContactEmailAddress(String contactEmailAddress) {
        this.contactEmailAddress = contactEmailAddress;
    }
    
    /**
     * Gets the Protocol Attachment Protocol Contact Phone Number.
     * @return the Protocol Attachment Protocol Contact Phone Number
     */
    public String getContactPhoneNumber() {
        return this.contactPhoneNumber;
    }
    
    /**
     * Sets the Protocol Attachment Protocol Contact Phone Number.
     * @param contactPhoneNumber the Protocol Attachment Protocol Contact Phone Number
     */
    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }
    
    /**
     * Gets the Protocol Attachment Protocol Comments.
     * @return the Protocol Attachment Protocol Comments
     */
    public String getComments() {
        return this.comments;
    }
    
    /**
     * Sets the Protocol Attachment Protocol comments.
     * @param comments the Protocol Attachment Protocol comments
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

    /** {@inheritDoc} */
    public ProtocolAttachmentType getType() {
        return this.type;
    }

    /** {@inheritDoc} */
    public void setType(ProtocolAttachmentType type) {
        this.type = type;
    }

    /** {@inheritDoc} */
    public String getTypeCode() {
        return this.typeCode;
    }

    /** {@inheritDoc} */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
    
    /** {@inheritDoc} */
    public Integer getDocumentId() {
        return this.documentId;
    }
    
    /** {@inheritDoc} */
    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }
    
    /** {@inheritDoc} */
    public String getGroupCode() {
        return GROUP_CODE;
    }
    
    /** {@inheritDoc} */
    public String getDescription() {
        return this.description;
    }

    /** {@inheritDoc} */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /** {@inheritDoc} */
    @Override
    public String getAttachmentDescription() {
        return "Protocol Attachment";
    }
    
    /**
     * Gets the versions attribute. 
     * @return Returns the versions.
     */
    public List<ProtocolAttachmentProtocol> getVersions() {
        if (this.versions == null) {
            this.versions = new ArrayList<ProtocolAttachmentProtocol>();
        }      
        this.versions.clear();
        this.versions.addAll(KraServiceLocator.getService(ProtocolAttachmentService.class).getAttachmentsWithOlderFileVersions(this, ProtocolAttachmentProtocol.class));
        
        return this.versions;
    }

    /**
     * Sets the versions attribute value.
     * @param versions The versions to set.
     */
    public void setVersions(List<ProtocolAttachmentProtocol> versions) {
        this.versions = versions;
    }

    /** {@inheritDoc} */
    @Override
    public boolean supportsVersioning() {
        return true;
    }
    
    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        final LinkedHashMap<String, Object> hashMap = super.toStringMapper();

        hashMap.put(PropertyName.COMMENTS.getPropertyName(), this.getComments());
        hashMap.put(PropertyName.EMAIL.getPropertyName(), this.getContactEmailAddress());
        hashMap.put(PropertyName.CONTACT_NAME.getPropertyName(), this.getContactName());
        hashMap.put(PropertyName.PHONE.getPropertyName(), this.getContactPhoneNumber());
        hashMap.put(PropertyName.STATUS_CODE.getPropertyName(), this.getStatus());
        hashMap.put(TypedAttachment.PropertyName.TYPE_CODE.getPropertyName(), this.getTypeCode());
        hashMap.put(TypedAttachment.PropertyName.DOCUMENT_ID.getPropertyName(), this.getDocumentId());
        hashMap.put(TypedAttachment.PropertyName.GROUP_CODE.getPropertyName(), this.getGroupCode());
        hashMap.put(TypedAttachment.PropertyName.DESCRIPTION.getPropertyName(), this.getDescription());
        return hashMap;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        result = prime * result + ((contactEmailAddress == null) ? 0 : contactEmailAddress.hashCode());
        result = prime * result + ((contactName == null) ? 0 : contactName.hashCode());
        result = prime * result + ((contactPhoneNumber == null) ? 0 : contactPhoneNumber.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((documentId == null) ? 0 : documentId.hashCode());
        result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
        result = prime * result + ((typeCode == null) ? 0 : typeCode.hashCode());
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
        ProtocolAttachmentProtocol other = (ProtocolAttachmentProtocol) obj;
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
        if (documentId == null) {
            if (other.documentId != null) {
                return false;
            }
        } else if (!documentId.equals(other.documentId)) {
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
        COMMENTS("comments"), EMAIL("contactEmailAddress"), CONTACT_NAME("contactName"),
        PHONE("contactPhoneNumber"), STATUS_CODE("statusCode");
        
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
