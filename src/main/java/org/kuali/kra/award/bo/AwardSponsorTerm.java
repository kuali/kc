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

import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.SponsorTerm;
import org.kuali.kra.infrastructure.KraServiceLocator;

/**
 * This class is business object representation of AwardSponsorTerm.
 */
public class AwardSponsorTerm extends KraPersistableBusinessObjectBase {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -7613461089397009434L;
    
    BusinessObjectService businessObjectService;
   
    private Long awardSponsorTermId;
    private Long sponsorTermId;
    private Award award;
    private String awardNumber;
    private Integer sequenceNumber;
    private SponsorTerm sponsorTerm;
    


    /**
     * Gets the awardTermId attribute. 
     * @return Returns the awardTermId.
     */
    public Long getAwardSponsorTermId() {
        return awardSponsorTermId;
    }

    /**
     * Sets the awardTermId attribute value.
     * @param awardTermId The awardTermId to set.
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
     * This method returns the awardNumber
     * @return
     */
    public String getAwardNumber() {
        return awardNumber;
     }
    
    /**
     * This method sets the awardNumber
     * @param awardNumber
     */
    public void setAwardNumber(String awardNumber) {
        //do nothing
     }
    

    /**
     * This method returns the award
     * @return
     */
    public Award getAward() {
        return award;
    }


    /**
     * This method sets the award, awardNumber and sequenceNumber
     * @param award
     */
    public void setAward(Award award) {
        this.award = award;
        if(award == null) {
            sequenceNumber = null;
            awardNumber = null;
        } else {
            sequenceNumber = award.getSequenceNumber();
            awardNumber = award.getAwardNumber();
        }
    }

    /**
     * This method returns the sequence number
     * @return
     */
    public int getSequenceNumber() {
        return sequenceNumber;
     }


    /**
     * This method does nothing
     * @param sequenceNumber
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        // do nothing
    }
    
    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    protected LinkedHashMap<String,Object> toStringMapper() {        
        LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();
        hashMap.put("awardSponsorTermId", getAwardSponsorTermId());
        hashMap.put("sponsorTermId", getSponsorTermId());
        hashMap.put("awardNumber", getAwardNumber());
        hashMap.put("sequenceNumber", getSequenceNumber());
        return hashMap;

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
    
    /**
     * This method returns the Kra business object service.
     * @return
     */
    BusinessObjectService getKraBusinessObjectService() {
        if(businessObjectService == null){
            businessObjectService = 
                (BusinessObjectService) KraServiceLocator.getService("businessObjectService");
        }
        return businessObjectService;
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

}
