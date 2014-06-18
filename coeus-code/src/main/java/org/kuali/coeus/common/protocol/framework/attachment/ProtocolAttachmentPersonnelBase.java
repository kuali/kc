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

import org.kuali.kra.SkipVersioning;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;

import java.sql.Timestamp;

/**
 * This class represents the ProtocolBase Attachment Personnel.
 */
public abstract class ProtocolAttachmentPersonnelBase extends ProtocolAttachmentBase {

    private static final long serialVersionUID = -7115904344245464654L;

    protected Integer personId;

    @SkipVersioning
    protected ProtocolPersonBase person;

    protected String typeCode;

    protected ProtocolAttachmentTypeBase type;

    protected String description;

    /**
     * empty ctor to satisfy JavaBean convention.
     */
    public ProtocolAttachmentPersonnelBase() {
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
    public ProtocolAttachmentPersonnelBase(final ProtocolBase protocol) {
        super(protocol);
    }

    /**
     * Gets the ProtocolBase Attachment Personnel Person.
     * @return the ProtocolBase Attachment Personnel Person
     */
    public ProtocolPersonBase getPerson() {
        if (person == null && personId != null) {
            this.refreshReferenceObject("person");
        }
        return this.person;
    }

    /**
     * Sets the ProtocolBase Attachment Personnel Person.
     * @param person the ProtocolBase Attachment Personnel Person
     */
    public void setPerson(ProtocolPersonBase person) {
        this.person = person;
    }

    /**
     * Gets the person Id. 
     * @return the person Id.
     */
    public Integer getPersonId() {
        return this.personId;
    }

    /**
     * Sets the person Id.
     * @param personId the person Id.
     */
    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    @Override
    public ProtocolAttachmentTypeBase getType() {
        return this.type;
    }

    @Override
    public void setType(ProtocolAttachmentTypeBase type) {
        this.type = type;
    }

    @Override
    public String getTypeCode() {
        return this.typeCode;
    }

    @Override
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    @Override
    public abstract String getGroupCode();

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public abstract String getAttachmentDescription();

    @Override
    public boolean supportsVersioning() {
        return false;
    }

    /** 
     * {@inheritDoc}
     * also nulling the person id because when saving after versioning, the person id is reverting to the wrong BO.
     */
    @Override
    public void resetPersistenceState() {
        super.resetPersistenceState();
        this.setPersonId(null);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        //        result = prime * result + ((documentId == null) ? 0 : documentId.hashCode());  
        result = prime * result + ((personId == null) ? 0 : personId.hashCode());
        result = prime * result + ((typeCode == null) ? 0 : typeCode.hashCode());
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
        ProtocolAttachmentPersonnelBase other = (ProtocolAttachmentPersonnelBase) obj;
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (personId == null) {
            if (other.personId != null) {
                return false;
            }
        } else if (!personId.equals(other.personId)) {
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

        PERSON_ID("personId");

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

    @Override
    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        if (updateTimestamp == null || getUpdateTimestamp() == null) {
            super.setUpdateTimestamp(updateTimestamp);
        }
    }

    @Override
    public void setUpdateUser(String updateUser) {
        if (updateUser == null || getUpdateUser() == null) {
            super.setUpdateUser(updateUser);
        }
    }

    public void init(ProtocolPersonBase protocolPerson) {
        setPerson(protocolPerson);
        setPersonId(protocolPerson.getProtocolPersonId());
        setId(null);
        setProtocolNumber(protocolPerson.getProtocol().getProtocolNumber());
    }
}
