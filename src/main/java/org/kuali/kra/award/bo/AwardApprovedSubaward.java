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

import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * This class is the Business Object representation of an Award Approved Subaward
 */
public class AwardApprovedSubaward extends KraPersistableBusinessObjectBase implements ValuableItem {
    
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5025168632828604306L;
    private Long awardApprovedSubawardId;
    private String organizationName;
    private KualiDecimal amount;
    private Award award;
    private String awardNumber;
    private Integer sequenceNumber;
    


    public AwardApprovedSubaward() {
        setAmount(new KualiDecimal(0.00));
    }

    /**
     * This method...
     * @return
     */
    public Long getAwardApprovedSubawardId() {
        return awardApprovedSubawardId;
    }



    /**
     * This method...
     * @param awardApprovedSubawardId
     */
    public void setAwardApprovedSubawardId(Long awardApprovedSubawardId) {
        this.awardApprovedSubawardId = awardApprovedSubawardId;
    }



    /**
     * This method...
     * @return
     */
    public String getOrganizationName() {
        return organizationName;
    }



    /**
     * This method...
     * @param organizationName
     */
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }



    /**
     * This method...
     * @return
     */
    public KualiDecimal getAmount() {
        return amount;
    }



    /**
     * This method...
     * @param amount
     */
    public void setAmount(KualiDecimal amount) {
        this.amount = amount;
    }



    /**
     * This method...
     * @return
     */
    public Award getAward() {
        return award;
    }



    /**
     * This method...
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
     * This method...
     * @return
     */
    public String getAwardNumber() {
        return awardNumber;
    }



    /**
     * This method...
     * @param awardNumber
     */
    public void setAwardNumber(String awardNumber) {
        //do nothing
    }



    /**
     * This method...
     * @return
     */
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }



    /**
     * This method...
     * @param sequenceNumber
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        //do nothing
    }
    
    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    protected LinkedHashMap<String,Object> toStringMapper() {
        LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();
        hashMap.put("awardApprovedSubawardId", getAwardApprovedSubawardId());
        hashMap.put("organizationName", getOrganizationName());
        hashMap.put("awardNumber", getAwardNumber());
        hashMap.put("sequenceNumber", getSequenceNumber());
        hashMap.put("amount", getAmount());
        return hashMap;
    }


    //CSOFF
    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((amount == null) ? 0 : amount.hashCode());
        result = PRIME * result + ((award == null) ? 0 : award.hashCode());
        result = PRIME * result + ((awardApprovedSubawardId == null) ? 0 : awardApprovedSubawardId.hashCode());
        result = PRIME * result + ((awardNumber == null) ? 0 : awardNumber.hashCode());
        result = PRIME * result + ((organizationName == null) ? 0 : organizationName.hashCode());
        result = PRIME * result + ((sequenceNumber == null) ? 0 : sequenceNumber.hashCode());
        return result;
    }


    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        final AwardApprovedSubaward OTHER = (AwardApprovedSubaward) obj;
        if (amount == null) {
            if (OTHER.amount != null){
                return false;
            }
        }else if (!amount.equals(OTHER.amount)){
            return false;
        }
        if (award == null) {
            if (OTHER.award != null){
                return false;
            }
        }else if (!award.equals(OTHER.award)){
            return false;
        }
        if (awardApprovedSubawardId == null) {
            if (OTHER.awardApprovedSubawardId != null){
                return false;
            }
        }else if (!awardApprovedSubawardId.equals(OTHER.awardApprovedSubawardId)){
            return false;
        }
        if (awardNumber == null) {
            if (OTHER.awardNumber != null){
                return false;
            }
        }else if (!awardNumber.equals(OTHER.awardNumber)){
            return false;
        }
        if (organizationName == null) {
            if (OTHER.organizationName != null){
                return false;
            }
        }else if (!organizationName.equals(OTHER.organizationName)){
            return false;
        }
        if (sequenceNumber == null) {
            if (OTHER.sequenceNumber != null){
                return false;
            }
        }else if (!sequenceNumber.equals(OTHER.sequenceNumber)){
            return false;
        }
        return true;
    }
    //CSON

}
