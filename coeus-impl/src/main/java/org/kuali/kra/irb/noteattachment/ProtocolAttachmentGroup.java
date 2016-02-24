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

import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentGroupBase;

/**
 * This class represents the Protocol Attachment Group.
 */
public class ProtocolAttachmentGroup extends ProtocolAttachmentGroupBase {

    private static final long serialVersionUID = 2053606476193782286L;

    private String code;

    private String description;

    /**
     * empty ctor to satisfy JavaBean convention.
     */
    public ProtocolAttachmentGroup() {
        super();
    }

    /**
     * Convenience ctor to set the relevant properties of this class.
     * 
     * <p>
     * This ctor does not validate any of the properties.
     * </p>
     * 
     * @param code the code.
     * @param description the description.
     */
    public ProtocolAttachmentGroup(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Gets the protocol attachment group code.
     * @return the protocol attachment group code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Sets the protocol attachment group code.
     * @param code the protocol attachment group code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the protocol attachment group description.
     * @return the protocol attachment group description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the protocol attachment group description.
     * @param description the protocol attachment group description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.code == null) ? 0 : this.code.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
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
        if (!(obj instanceof ProtocolAttachmentGroup)) {
            return false;
        }
        ProtocolAttachmentGroup other = (ProtocolAttachmentGroup) obj;
        if (this.code == null) {
            if (other.code != null) {
                return false;
            }
        } else if (!this.code.equals(other.code)) {
            return false;
        }
        if (this.description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!this.description.equals(other.description)) {
            return false;
        }
        return true;
    }
}
