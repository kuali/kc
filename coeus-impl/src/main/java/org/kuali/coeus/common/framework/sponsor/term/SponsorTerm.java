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

import org.apache.commons.lang3.StringEscapeUtils;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * This class is business object representation of a Sponsor Term
 */
public class SponsorTerm extends KcPersistableBusinessObjectBase {


    private static final long serialVersionUID = -1780708005730780612L;

    private Long sponsorTermId;

    private String sponsorTermCode;

    private String sponsorTermTypeCode;

    private String description;

    private SponsorTermType sponsorTermType;


    public SponsorTerm() {
    }

    /**
     * Gets the sponsorTermId attribute. 
     * @return Returns the sponsorTermId.
     */
    public Long getSponsorTermId() {
        return sponsorTermId;
    }

    /**
     * Sets the sponsorTermId attribute value.
     * @param sponsorTermId The sponsorTermId to set.
     */
    public void setSponsorTermId(Long sponsorTermId) {
        this.sponsorTermId = sponsorTermId;
    }

    /**
     * Gets the sponsorTermCode attribute. 
     * @return Returns the sponsorTermCode.
     */
    public String getSponsorTermCode() {
        return sponsorTermCode;
    }

    /**
     * Sets the sponsorTermCode attribute value.
     * @param sponsorTermCode The sponsorTermCode to set.
     */
    public void setSponsorTermCode(String sponsorTermCode) {
        this.sponsorTermCode = sponsorTermCode;
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

    /**
     * Gets the description attribute. 
     * @return Returns the description.
     */
    public String getEscapedDescription() {
        return StringEscapeUtils.escapeHtml4(description);
    }

    /**
     * Gets the sponsorTermType attribute. 
     * @return Returns the sponsorTermType.
     */
    public SponsorTermType getSponsorTermType() {
        return sponsorTermType;
    }

    /**
     * Sets the sponsorTermType attribute value.
     * @param sponsorTermType The sponsorTermType to set.
     */
    public void setSponsorTermType(SponsorTermType sponsorTermType) {
        this.sponsorTermType = sponsorTermType;
    }

    /**
     * Gets the serialVersionUID attribute. 
     * @return Returns the serialVersionUID.
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((sponsorTermCode == null) ? 0 : sponsorTermCode.hashCode());
        result = prime * result + ((sponsorTermId == null) ? 0 : sponsorTermId.hashCode());
        result = prime * result + ((sponsorTermType == null) ? 0 : sponsorTermType.hashCode());
        result = prime * result + ((sponsorTermTypeCode == null) ? 0 : sponsorTermTypeCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        SponsorTerm other = (SponsorTerm) obj;
        if (description == null) {
            if (other.description != null) return false;
        } else if (!description.equals(other.description)) return false;
        if (sponsorTermCode == null) {
            if (other.sponsorTermCode != null) return false;
        } else if (!sponsorTermCode.equals(other.sponsorTermCode)) return false;
        if (sponsorTermId == null) {
            if (other.sponsorTermId != null) return false;
        } else if (!sponsorTermId.equals(other.sponsorTermId)) return false;
        if (sponsorTermType == null) {
            if (other.sponsorTermType != null) return false;
        } else if (!sponsorTermType.equals(other.sponsorTermType)) return false;
        if (sponsorTermTypeCode == null) {
            if (other.sponsorTermTypeCode != null) return false;
        } else if (!sponsorTermTypeCode.equals(other.sponsorTermTypeCode)) return false;
        return true;
    }
}
