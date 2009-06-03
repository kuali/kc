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
package org.kuali.kra.award.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.SponsorTerm;

/**
 * This class...
 */
@SuppressWarnings("serial")
public abstract class AwardTermBase extends KraPersistableBusinessObjectBase implements AwardSynchronizable{
    private Integer sponsorTermId; 
    private SponsorTerm sponsorTerm;
    
    /**
     * 
     * @see org.kuali.kra.award.bo.AwardSynchronizable#getSyncBaseClass()
     */
    public Class getSyncBaseClass() {
        return AwardTermBase.class;
    }
    /**
     * 
     * @see org.kuali.kra.award.bo.AwardSynchronizable#getAwardSyncClass()
     */
    public Class getAwardSyncClass() {
        return AwardSponsorTerm.class;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("awardSponsorTermId", getSponsorTermId());
        return hashMap;
    }

    /**
     * Gets the sponsorTermId attribute. 
     * @return Returns the sponsorTermId.
     */
    public Integer getSponsorTermId() {
        return sponsorTermId;
    }

    /**
     * Sets the sponsorTermId attribute value.
     * @param sponsorTermId The sponsorTermId to set.
     */
    public void setSponsorTermId(Integer sponsorTermId) {
        this.sponsorTermId = sponsorTermId;
    }

    /**
     * Gets the sponsorTerm attribute. 
     * @return Returns the sponsorTerm.
     */
    public SponsorTerm getSponsorTerm() {
        return sponsorTerm;
    }

    /**
     * Sets the sponsorTerm attribute value.
     * @param sponsorTerm The sponsorTerm to set.
     */
    public void setSponsorTerm(SponsorTerm sponsorTerm) {
        this.sponsorTerm = sponsorTerm;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sponsorTerm == null) ? 0 : sponsorTerm.hashCode());
        result = prime * result + ((sponsorTermId == null) ? 0 : sponsorTermId.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AwardTermBase other = (AwardTermBase) obj;
        if (sponsorTerm == null) {
            if (other.sponsorTerm != null)
                return false;
        }
        else if (!sponsorTerm.equals(other.sponsorTerm))
            return false;
        if (sponsorTermId == null) {
            if (other.sponsorTermId != null)
                return false;
        }
        else if (!sponsorTermId.equals(other.sponsorTermId))
            return false;
        return true;
    }

}
