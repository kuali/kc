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
package org.kuali.kra.irb.noteattachment;

import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentTypeGroupBase;

/**
 * This class represents the Protocol Attachment Type Group.
 */
public class ProtocolAttachmentTypeGroup extends ProtocolAttachmentTypeGroupBase {

    private static final long serialVersionUID = 2053606476193782286L;

    private Long id;

    private String typeCode;

    private ProtocolAttachmentType type;

    private String groupCode;

    private ProtocolAttachmentGroup group;

    /**
     * empty ctor to satisfy JavaBean convention.
     */
    public ProtocolAttachmentTypeGroup() {
        super();
    }

    /**
     * Convenience ctor to set the relevant properties of this class.
     * 
     * <p>
     * This ctor does not validate any of the properties.
     * </p>
     * 
     * @param type the type.
     * @param group the group.
     */
    public ProtocolAttachmentTypeGroup(ProtocolAttachmentType type, ProtocolAttachmentGroup group) {
        this.type = type;
        this.group = group;
    }

    /**
     * Gets the protocol attachment type group id.
     * @return the protocol attachment type group id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the protocol attachment type group id.
     * @param id the protocol attachment type group id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the Protocol Attachment Type.
     * @return Protocol Attachment Type
     */
    public ProtocolAttachmentType getType() {
        return this.type;
    }

    /**
     * Sets the Protocol Attachment Type.
     * @param type Protocol Attachment Type
     */
    public void setType(ProtocolAttachmentType type) {
        this.type = type;
    }

    /**
     * Gets the Protocol Attachment Group.
     * @return Protocol Attachment Group
     */
    public ProtocolAttachmentGroup getGroup() {
        return this.group;
    }

    /**
     * Sets the Protocol Attachment Group.
     * @param group Protocol Attachment Group
     */
    public void setGroup(ProtocolAttachmentGroup group) {
        this.group = group;
    }

    /**
     * Gets the typeCode attribute. 
     * @return Returns the typeCode.
     */
    public String getTypeCode() {
        return this.typeCode;
    }

    /**
     * Sets the typeCode attribute value.
     * @param typeCode The typeCode to set.
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * Gets the group Code. 
     * @return the group Code.
     */
    public String getGroupCode() {
        return this.groupCode;
    }

    /**
     * Sets the group Code.
     * @param groupCode the group Code.
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.group == null) ? 0 : this.group.hashCode());
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ProtocolAttachmentTypeGroup)) {
            return false;
        }
        ProtocolAttachmentTypeGroup other = (ProtocolAttachmentTypeGroup) obj;
        if (this.group == null) {
            if (other.group != null) {
                return false;
            }
        } else if (!this.group.equals(other.group)) {
            return false;
        }
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        if (this.type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!this.type.equals(other.type)) {
            return false;
        }
        return true;
    }
}
