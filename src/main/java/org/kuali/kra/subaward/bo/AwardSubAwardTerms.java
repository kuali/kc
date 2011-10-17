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
package org.kuali.kra.subaward.bo;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import java.util.LinkedHashMap;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.subaward.bo.SubAwardApprovalType;
public class AwardSubAwardTerms extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private Integer awardSubAwardTermsId; 
    private Integer awardId; 
    private String mitAwardNumber; 
    private Integer sequenceNumber; 
    private Integer subAwardApprovalCode; 
    
    private Award award; 
    private SubAwardApprovalType subAwardApprovalType; 
    
    public AwardSubAwardTerms() { 

    } 
    
    public Integer getAwardSubAwardTermsId() {
        return awardSubAwardTermsId;
    }

    public void setAwardSubAwardTermsId(Integer awardSubAwardTermsId) {
        this.awardSubAwardTermsId = awardSubAwardTermsId;
    }

    public Integer getAwardId() {
        return awardId;
    }

    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }

    public String getMitAwardNumber() {
        return mitAwardNumber;
    }

    public void setMitAwardNumber(String mitAwardNumber) {
        this.mitAwardNumber = mitAwardNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Integer getSubAwardApprovalCode() {
        return subAwardApprovalCode;
    }

    public void setSubAwardApprovalCode(Integer subAwardApprovalCode) {
        this.subAwardApprovalCode = subAwardApprovalCode;
    }

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }   

    public SubAwardApprovalType getSubAwardApprovalType() {
        return subAwardApprovalType;
    }

    public void setSubAwardApprovalType(SubAwardApprovalType subAwardApprovalType) {
        this.subAwardApprovalType = subAwardApprovalType;
    }

   

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("awardSubAwardTermsId", this.getAwardSubAwardTermsId());
        hashMap.put("awardId", this.getAwardId());
        hashMap.put("mitAwardNumber", this.getMitAwardNumber());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("subAwardApprovalCode", this.getSubAwardApprovalCode());
        return hashMap;
    }
    
}