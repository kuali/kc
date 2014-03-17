/*
 * Copyright 2005-2014 The Kuali Foundation
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
