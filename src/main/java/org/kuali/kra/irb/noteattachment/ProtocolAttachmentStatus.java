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

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * This class represents the Protocol Attachment Status.
 */
public class ProtocolAttachmentStatus extends KraPersistableBusinessObjectBase {
    
    private static final long serialVersionUID = 2053606476193782286L;

    private String code;
    private String description;

    /**
     * empty ctor to satisfy JavaBean convention.
     */
    public ProtocolAttachmentStatus() {
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
    public ProtocolAttachmentStatus(String code, String description) {
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

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("code", this.getCode());
        hashMap.put("description", this.getDescription());
        return hashMap;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.code == null) ? 0 : this.code.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ProtocolAttachmentStatus)) {
            return false;
        }
        ProtocolAttachmentStatus other = (ProtocolAttachmentStatus) obj;
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
