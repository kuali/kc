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

import java.sql.Date;
import java.util.LinkedHashMap;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.rice.kns.util.KualiDecimal;


public class SubAwardFundingSource extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private Integer subAwardFundingSourceId; 
    private Integer subAwardId; 
    private String subAwardCode; 
    private Integer sequenceNumber; 
    private Long awardId; 
    private String accountNumber;
    private Integer statusCode;
    private String sponsorCode;
    private SubAward subAward;
    private Award award;
    private KualiDecimal amountObligatedToDate;
    private Date obligationExpirationDate;
    private String awardNumber;
    private AwardAmountInfo awardAmountInfo;
    public SubAwardFundingSource() { 

    } 
    public Long getAwardId() {
        return awardId;
    }

    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

   
    public Integer getSubAwardFundingSourceId() {
        return subAwardFundingSourceId;
    }

    public void setSubAwardFundingSourceId(Integer subAwardFundingSourceId) {
        this.subAwardFundingSourceId = subAwardFundingSourceId;
    }

    public Integer getSubAwardId() {
        return subAwardId;
    }

    public void setSubAwardId(Integer subAwardId) {
        this.subAwardId = subAwardId;
    }

    public String getSubAwardCode() {
        return subAwardCode;
    }

    public void setSubAwardCode(String subAwardCode) {
        this.subAwardCode = subAwardCode;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }



    public SubAward getSubAward() {
        return subAward;
    }

    public void setSubAward(SubAward subAward) {
        this.subAward = subAward;
    }
    
    public Date getObligationExpirationDate() {
        return obligationExpirationDate;
    }
    public void setObligationExpirationDate(Date obligationExpirationDate) {
        this.obligationExpirationDate = obligationExpirationDate;
    }
    
    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("subAwardFundingSourceId", this.getSubAwardFundingSourceId());
        hashMap.put("subAwardId", this.getSubAwardId());
        hashMap.put("subAwardCode", this.getSubAwardCode());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("awardId", this.getAwardId());
        hashMap.put("statusCode", this.getStatusCode());
        hashMap.put("sponsorCode", this.getSponsorCode());
        hashMap.put("accountNumber", this.getAccountNumber());
        hashMap.put("amountObligatedToDate", this.getAmountObligatedToDate());
        hashMap.put("obligationExpirationDate", this.getObligationExpirationDate());
        return hashMap;
    }
    public void setAward(Award award) {
        this.award = award;
    }
    public Award getAward() {
        return award;
    }
    public void setAmountObligatedToDate(KualiDecimal amountObligatedToDate) {
        this.amountObligatedToDate = amountObligatedToDate;
    }
    public KualiDecimal getAmountObligatedToDate() {
        return amountObligatedToDate;
    }
    public void setAwardAmountInfo(AwardAmountInfo awardAmountInfo) {
        this.awardAmountInfo = awardAmountInfo;
    }
    public AwardAmountInfo getAwardAmountInfo() {
        return awardAmountInfo;
    }
    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }
    public String getAwardNumber() {
        return awardNumber;
    }
    
}