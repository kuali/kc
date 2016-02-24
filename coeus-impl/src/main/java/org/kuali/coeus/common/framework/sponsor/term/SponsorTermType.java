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
package org.kuali.coeus.common.framework.sponsor.term;


import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * This class is business object representation of a Sponsor Term Type.
 */
public class SponsorTermType extends KcPersistableBusinessObjectBase {


    private static final long serialVersionUID = -6111334757622425480L;

    private String sponsorTermTypeCode;

    private String description;


    public SponsorTermType() {
    }

    /**
     * Gets the sponsorTermTypeCode attribute. 
     * @return Returns the sponsorTermTypeCode.
     */
    public String getSponsorTermTypeCode() {
        return sponsorTermTypeCode;
    }

    /**
     * Sets the sponsorTermTypeCode attribute value.
     * @param sponsorTermTypeCode The sponsorTermTypeCode to set.
     */
    public void setSponsorTermTypeCode(String sponsorTermTypeCode) {
        this.sponsorTermTypeCode = sponsorTermTypeCode;
    }

    /**
     * Gets the description attribute. 
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description attribute value.
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((sponsorTermTypeCode == null) ? 0 : sponsorTermTypeCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        SponsorTermType other = (SponsorTermType) obj;
        if (description == null) {
            if (other.description != null) return false;
        } else if (!description.equals(other.description)) return false;
        if (sponsorTermTypeCode == null) {
            if (other.sponsorTermTypeCode != null) return false;
        } else if (!sponsorTermTypeCode.equals(other.sponsorTermTypeCode)) return false;
        return true;
    }
}
