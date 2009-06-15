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

import java.util.LinkedHashMap;

import org.kuali.kra.irb.Protocol;

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
    private String description;
    
    /**
     * empty ctor to satisfy JavaBean convention.
     */
    public ProtocolAttachmentProtocol() {
        super();
    }
    
    /**
     * Convenience ctor to set the protocol.
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
    
    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = super.toStringMapper();

        hashMap.put(PropertyName.COMMENTS.getPropertyName(), this.getComments());
        hashMap.put(PropertyName.EMAIL.getPropertyName(), this.getContactEmailAddress());
        hashMap.put(PropertyName.CONTACT_NAME.getPropertyName(), this.getContactName());
        hashMap.put(PropertyName.PHONE.getPropertyName(), this.getContactPhoneNumber());
        hashMap.put(PropertyName.STATUS_CODE.getPropertyName(), this.getStatus());
        hashMap.put(TypedAttachment.PropertyName.TYPE_CODE.getPropertyName(), this.getTypeCode());
        hashMap.put(TypedAttachment.PropertyName.GROUP_CODE.getPropertyName(), this.getGroupCode());
        hashMap.put(TypedAttachment.PropertyName.DESCRIPTION.getPropertyName(), this.getDescription());
        return hashMap;
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

    public void init(Protocol protocol) {
        setProtocolNumber(protocol.getProtocolNumber());
        setProtocol(protocol);
    }
}
