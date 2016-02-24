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
