/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.bo;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * This class is business object representation of a Sponsor Term
 */
public class SponsorTerm extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -1780708005730780612L;

    private Long sponsorTermId;

    private String sponsorTermCode;

    private String sponsorTermTypeCode;

    private String description;

    private SponsorTermType sponsorTermType;

    /**
     * Constructs a SponsorTerm.java.
     */
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
        return StringEscapeUtils.escapeHtml(description);
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

    /**
     * @see java.lang.Object#hashCode()
     */
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

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
