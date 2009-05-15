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

/**
 * Defines a Attachment that has a {@link ProtocolAttachmentType type} with a description.
 */
public interface TypedAttachment {

    /**
     * Gets the Protocol Attachment Base Type.
     * @return the Protocol Attachment Base Type
     */
    ProtocolAttachmentType getType();
    
    /**
     * Sets the Protocol Attachment Base Type.
     * @param type the Protocol Attachment Base Type
     */
    void setType(ProtocolAttachmentType type);
    
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
     * Gets the Protocol Attachment Base Description.
     * @return the Protocol Attachment Base Description
     */
    String getDescription();
    
    /**
     * Sets the Protocol Attachment Base Description.
     * @param description the Protocol Attachment Base Description
     */
    void setDescription(String description);
    
    /**
     * The group code that the Protocol Attachment belongs to.
     * The group code relates to {@link ProtocolAttachmentGroup ProtocolAttachmentGroup}.
     * 
     * @return the group code.
     */
    String getGroupCode();
    
    /**
     * Contains all the property names that will be used by implementations.
     * This is required in order to meet the JavaBeans spec.
     */
    public static enum PropertyName {
        TYPE_CODE("typeCode"), GROUP_CODE("groupCode"), DESCRIPTION("description");
        
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
