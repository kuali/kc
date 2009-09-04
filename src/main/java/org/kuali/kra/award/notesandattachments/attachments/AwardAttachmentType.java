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
package org.kuali.kra.award.notesandattachments.attachments;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentType;

/**
 * This class...
 */
public class AwardAttachmentType extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3918563746120540897L;

    private String typeCode;
    private String description;

    /**
     * empty ctor to satisfy JavaBean convention.
     */
    public AwardAttachmentType() {
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
    public AwardAttachmentType(String typeCode, String description) {
        this.typeCode = typeCode;
        this.description = description;
    }
    
    /**
     * Gets the protocol attachment type code.
     * @return the protocol attachment type code
     */
    public String getTypeCode() {
        return this.typeCode;
    }

    /**
     * Sets the protocol attachment type code.
     * @param code the protocol attachment type code
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * Gets the protocol attachment type description.
     * @return the protocol attachment type description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the protocol attachment type description.
     * @param description the protocol attachment type description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("typeCode", this.getTypeCode());
        hashMap.put("description", this.getDescription());
        return hashMap;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.typeCode == null) ? 0 : this.typeCode.hashCode());
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
        if (!(obj instanceof ProtocolAttachmentType)) {
            return false;
        }
        AwardAttachmentType other = (AwardAttachmentType) obj;
        if (this.typeCode == null) {
            if (other.typeCode != null) {
                return false;
            }
        } else if (!this.typeCode.equals(other.typeCode)) {
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
