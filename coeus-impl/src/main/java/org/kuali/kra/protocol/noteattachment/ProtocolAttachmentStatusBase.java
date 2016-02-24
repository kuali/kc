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

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * This class represents the ProtocolBase Attachment Status.
 */
public abstract class ProtocolAttachmentStatusBase extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 2053606476193782286L;

    public static final String DRAFT = "1";
    public static final String FINALIZED = "2";
    public static final String DELETED = "3";
    public static final String SUPERCEDED = "4";
    public static final String EXPIRED = "5";    
    
    private String code;

    private String description;


    /**
     * empty ctor to satisfy JavaBean convention.
     */
    protected ProtocolAttachmentStatusBase() {
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
    public ProtocolAttachmentStatusBase(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Gets the protocol attachment status code.
     * @return the protocol attachment status code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Sets the protocol attachment status code.
     * @param code the protocol attachment status code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the protocol attachment status description.
     * @return the protocol attachment status description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the protocol attachment status description.
     * @param description the protocol attachment status description
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
        if (!(obj instanceof ProtocolAttachmentStatusBase)) {
            return false;
        }
        ProtocolAttachmentStatusBase other = (ProtocolAttachmentStatusBase) obj;
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
