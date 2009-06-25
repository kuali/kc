/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kuali.kra.award.home;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This class represents the ContactType business object and is mapped
 * with CONTACT_TYPE table.
 */
public class ContactType extends KraPersistableBusinessObjectBase implements ContactRole { 
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8720276596982712409L;
    private String contactTypeCode; 
    private String description; 
    
    /**
     * Constructs a ContactType.java
     */
    public ContactType() { 

    } 
    
    /**
     * Convenience constructor
     * @param contactTypeCode
     * @param description
     */
    public ContactType(String contactTypeCode, String description) {
        this.contactTypeCode = contactTypeCode;
        this.description = description;
    }

    /**
     * 
     * This method...
     * @return
     */
    public String getContactTypeCode() {
        return contactTypeCode;
    }

    /**
     * 
     * @param contactTypeCode
     */
    public void setContactTypeCode(String contactTypeCode) {
        this.contactTypeCode = contactTypeCode;
    }

    /**
     * 
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }    
    
    /**
     * 
     * @see org.kuali.rice.kns.bo.BusinessObjectBase#toStringMapper()
     */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("contactTypeCode", getContactTypeCode());
        hashMap.put("description", getDescription());
        return hashMap;
    }

    /**
     * @see org.kuali.kra.award.home.ContactRole#getRoleDescription()
     */
    public String getRoleDescription() {
        return description;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((contactTypeCode == null) ? 0 : contactTypeCode.hashCode());
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        ContactType other = (ContactType) obj;
        if (contactTypeCode == null) {
            if (other.contactTypeCode != null) {
                return false;
            }
        } else if (!contactTypeCode.equals(other.contactTypeCode)) {
            return false;
        }
        return true;
    }

    /**
     * @see org.kuali.kra.award.home.ContactRole#getRoleCode()
     */
    public String getRoleCode() {
        return getContactTypeCode();
    }
    
}