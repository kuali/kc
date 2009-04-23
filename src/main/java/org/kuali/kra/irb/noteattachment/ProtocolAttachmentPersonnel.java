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

import org.kuali.kra.irb.personnel.ProtocolPerson;

/**
 * This class represents the Protocol Attachment Personnel.
 */
public class ProtocolAttachmentPersonnel extends ProtocolAttachmentBase {

    private static final long serialVersionUID = -7115904344245464654L;
    private static final String GROUP_CODE = "2";
    
    private ProtocolPerson person;
    
    /**
     * empty ctor to satisfy JavaBean convention.
     */
    public ProtocolAttachmentPersonnel() {
        super();
    }
    
    /**
     * Convenience ctor to set the protocol id.
     * 
     * <p>
     * This ctor does not validate any of the properties.
     * </p>
     * 
     * @param protocolId the protocol Id.
     */
    public ProtocolAttachmentPersonnel(final Long protocolId) {
        super(protocolId);
    }

    /**
     * Gets the Protocol Attachment Personnel Person.
     * @return the Protocol Attachment Personnel Person
     */
    public ProtocolPerson getPerson() {
        return this.person;
    }

    /**
     * Sets the Protocol Attachment Personnel Person.
     * @param person the Protocol Attachment Personnel Person
     */
    public void setPerson(ProtocolPerson person) {
        this.person = person;
    }

    /** {@inheritDoc} */
    @Override
    public String getGroupCode() {
        return GROUP_CODE;
    }
    
    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = super.toStringMapper();
        hashMap.put(PropertyName.PERSON.getPropertyName(), this.getPerson());
        return hashMap;
    }
    
    /**
     * Contains all the property names in this class.
     */
    public static enum PropertyName {
        PERSON("person");
        
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
