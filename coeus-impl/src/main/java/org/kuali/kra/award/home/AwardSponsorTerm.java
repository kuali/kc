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
package org.kuali.kra.award.home;

import org.kuali.coeus.common.framework.sponsor.term.SponsorTerm;
import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncableProperty;


/**
 * This class is business object representation of AwardSponsorTerm.
 */
public class AwardSponsorTerm extends AwardAssociate {

    private static final long serialVersionUID = -7613461089397009434L;

    private Long awardSponsorTermId;

    //@AwardSyncable( scopes = {AwardTemplateSyncScope.CONTAINING_CLASS_INHERIT} )   
    @AwardSyncableProperty(key = true)
    private Long sponsorTermId;

    private SponsorTerm sponsorTerm;


    public AwardSponsorTerm() {
        super();
    }

    /**
     * Constructs a AwardSponsorTerm.java.
     * @param sponsorTermId
     * @param sponsorTerm
     */
    public AwardSponsorTerm(Long sponsorTermId, SponsorTerm sponsorTerm) {
        this.sponsorTermId = sponsorTermId;
        this.sponsorTerm = sponsorTerm;
    }

    /**
     * Gets the awardTermId attribute. 
     * @return Returns the awardTermId.
     */
    public Long getAwardSponsorTermId() {
        return awardSponsorTermId;
    }

    /**
     * Sets the awardTermId attribute value.
     * @param awardSponsorTermId The awardTermId to set.
     */
    public void setAwardSponsorTermId(Long awardSponsorTermId) {
        this.awardSponsorTermId = awardSponsorTermId;
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
     * This method returns sponsorTermTypeCode associated with sponsorTerm.
     * @return
     */
    public String getSponsorTermTypeCode() {
        return sponsorTerm.getSponsorTermTypeCode();
    }

    /**
     * This method returns sponsorTermCode associated with sponsorTerm.
     * @return
     */
    public String getSponsorTermCode() {
        return sponsorTerm.getSponsorTermCode();
    }

    /**
     * This method returns description associated with sponsorTerm.
     * @return
     */
    public String getDescription() {
        return sponsorTerm.getDescription();
    }

    //    /**  
    //     * This method returns the Kra business object service.  
    //     * @return  
    //     */  
    //    BusinessObjectService getKraBusinessObjectService() {  
    //        if(businessObjectService == null){  
    //            businessObjectService =   
    //                (BusinessObjectService) KcServiceLocator.getService("businessObjectService");  
    //        }  
    //        return businessObjectService;  
    //    }  
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * super.hashCode();
        result = prime * result + ((awardSponsorTermId == null) ? 0 : awardSponsorTermId.hashCode());

        result = prime * result + ((sponsorTerm == null) ? 0 : sponsorTerm.hashCode());
        result = prime * result + ((sponsorTermId == null) ? 0 : sponsorTermId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) {
            return false;
        }
        final AwardSponsorTerm other = (AwardSponsorTerm) obj;
        if (awardSponsorTermId == null) {
            if (other.awardSponsorTermId != null) return false;
        } else if (!awardSponsorTermId.equals(other.awardSponsorTermId)) return false;
        if (sponsorTerm == null) {
            if (other.sponsorTerm != null) return false;
        } else if (!sponsorTerm.equals(other.sponsorTerm)) return false;
        if (sponsorTermId == null) {
            if (other.sponsorTermId != null) return false;
        } else if (!sponsorTermId.equals(other.sponsorTermId)) return false;
        return true;
    }

    @Override
    public void resetPersistenceState() {
        this.awardSponsorTermId = null;
    }
}
