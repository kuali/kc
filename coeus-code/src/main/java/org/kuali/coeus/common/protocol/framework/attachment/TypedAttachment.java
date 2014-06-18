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
package org.kuali.coeus.common.protocol.framework.attachment;

import org.kuali.coeus.common.protocol.framework.attachment.ProtocolAttachmentTypeBase;

/**
 * Defines a Attachment that has a {@link ProtocolAttachmentTypeBase type} with a description.
 */
public interface TypedAttachment {

    /**
     * Gets the ProtocolBase Attachment Base Type.
     * @return the ProtocolBase Attachment Base Type
     */
    ProtocolAttachmentTypeBase getType();
    
    /**
     * Sets the ProtocolBase Attachment Base Type.
     * @param type the ProtocolBase Attachment Base Type
     */
    void setType(ProtocolAttachmentTypeBase type);
    
    /**
     * Gets the type Code . 
     * @return the type Code.
     */
    String getTypeCode();

    /**
     * Sets the type Code.
     * @param typeCode the type Code.
     */
    void setTypeCode(String typeCode);
    
    /**
     * Gets the Document Id.  The document id is used to allow multiple typed attachments to exist.
     * So two attachments of the same type will have different document numbers.
     * @return the  Document Id
     */
    Integer getDocumentId();
    
    /**
     * Sets the Document Id.
     * @param documentId the  Document Id
     * @see #getDocumentId()
     */
    void setDocumentId(Integer documentId);
    
    /**
     * Gets the  Description.
     * @return the  Description
     */
    String getDescription();
    
    /**
     * Sets the  Description.
     * @param description the  Description
     */
    void setDescription(String description);
    
    /**
     * The group code that the ProtocolBase Attachment belongs to.
     * The group code relates to {@link org.kuali.coeus.common.protocol.framework.attachment.ProtocolAttachmentGroupBase ProtocolAttachmentGroup}.
     * 
     * @return the group code.
     */
    String getGroupCode();
    
    /**
     * Contains all the property names that will be used by implementations.
     * This is required in order to meet the JavaBeans spec.
     */
    public static enum PropertyName {
        TYPE_CODE("typeCode"), GROUP_CODE("groupCode"), DESCRIPTION("description"), DOCUMENT_ID("documentId");
        
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
