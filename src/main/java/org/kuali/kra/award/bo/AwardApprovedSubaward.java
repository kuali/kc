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

public class AwardApprovedSubaward extends KraPersistableBusinessObjectBase {
    
    
    private Long awardApprovedSubawardId;
    private String organizationName;
    private KualiDecimal amount;
    private Award award;
    private String awardNumber;
    private Integer sequenceNumber;
    




    public Long getAwardApprovedSubawardId() {
        return awardApprovedSubawardId;
    }



    public void setAwardApprovedSubawardId(Long awardApprovedSubawardId) {
        this.awardApprovedSubawardId = awardApprovedSubawardId;
    }



    public String getOrganizationName() {
        return organizationName;
    }



    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }



    public KualiDecimal getAmount() {
        return amount;
    }



    public void setAmount(KualiDecimal amount) {
        this.amount = amount;
    }



    public Award getAward() {
        return award;
    }



    public void setAward(Award award) {
        this.award = award;
    }



    public String getAwardNumber() {
        return awardNumber;
    }



    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }



    public Integer getSequenceNumber() {
        return sequenceNumber;
    }



    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
    
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();
        hashMap.put("awardApprovedSubawardId", getAwardApprovedSubawardId());
        hashMap.put("organizationName", getOrganizationName());
        hashMap.put("awardNumber", getAwardNumber());
        hashMap.put("sequenceNumber", getSequenceNumber());
        hashMap.put("amount", getAmount());
        return hashMap;
    }

}
