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
 * This class represents the Protocol Attachment Type Group.
 */
public class ProtocolAttachmentTypeGroup extends KraPersistableBusinessObjectBase {
    
    private static final long serialVersionUID = 2053606476193782286L;

    private Long id;

    private String typeCode;
    private ProtocolAttachmentType type;
 
    private String groupCode;
    private ProtocolAttachmentGroup group;
    
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
     * Gets the Protocol Attachment Type Code.
     * @return Protocol Attachment Type Code
     */
    public String getTypeCode() {
        return this.typeCode;
    }

    /**
     * Sets the Protocol Attachment Type Code.
     * @param typeCode Protocol Attachment Type Code
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
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
     * Gets the Protocol Attachment Group Code.
     * @return Protocol Attachment Group Code
     */
    public String getGroupCode() {
        return this.groupCode;
    }

    /**
     * Sets the Protocol Attachment Group Code.
     * @param groupCode Protocol Attachment Group Code
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
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
    
    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("code", this.getId());
        return hashMap;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.groupCode == null) ? 0 : this.groupCode.hashCode());
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.group == null) ? 0 : this.group.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.typeCode == null) ? 0 : this.typeCode.hashCode());
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
        if (!(obj instanceof ProtocolAttachmentTypeGroup)) {
            return false;
        }
        ProtocolAttachmentTypeGroup other = (ProtocolAttachmentTypeGroup) obj;
        if (this.groupCode == null) {
            if (other.groupCode != null) {
                return false;
            }
        } else if (!this.groupCode.equals(other.groupCode)) {
            return false;
        }
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        if (this.group == null) {
            if (other.group != null) {
                return false;
            }
        } else if (!this.group.equals(other.group)) {
            return false;
        }
        if (this.type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!this.type.equals(other.type)) {
            return false;
        }
        if (this.typeCode == null) {
            if (other.typeCode != null) {
                return false;
            }
        } else if (!this.typeCode.equals(other.typeCode)) {
            return false;
        }
        return true;
    } 
}
