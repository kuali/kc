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
package org.kuali.kra.award.home;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * 
 * This class represents the ContactType business object and is mapped
 * with CONTACT_TYPE table.
 */
public class ContactType extends KcPersistableBusinessObjectBase implements ContactRole {


    private static final long serialVersionUID = 8720276596982712409L;

    private String contactTypeCode;

    private String description;


    public ContactType() {
    }

    public ContactType(String contactTypeCode, String description) {
        this.contactTypeCode = contactTypeCode;
        this.description = description;
    }

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

    @Override
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

    @Override
    public String getRoleCode() {
        return getContactTypeCode();
    }
}
