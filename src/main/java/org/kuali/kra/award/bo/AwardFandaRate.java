/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kuali.kra.award.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * 
 * This class represents the AwardFandaRate Business Object.
 */
public class AwardFandaRate extends KraPersistableBusinessObjectBase { 

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private Long awardFandaRateId;
    private String awardNumber;
    private Integer sequenceNumber;
    private KualiDecimal applicableFandaRate; 
    private Integer fandaRateTypeCode; 
    private String fiscalYear; 
    private String onCampusFlag; 
    private KualiDecimal underrecoveryOfIndirectCost; 
    private String sourceAccount; 
    private String destinationAccount; 
    private Date startDate; 
    private Date endDate;
    private Award award; 
    
    /**
     * 
     * Constructs a AwardFandaRate.java.
     */
    public AwardFandaRate() { 

    } 
    
    /**
     * 
     * This method...
     * @return
     */
    public Long getAwardFandaRateId() {
        return awardFandaRateId;
    }

    /**
     * 
     * This method...
     * @param awardFandaRateId
     */
    public void setAwardFandaRateId(Long awardFandaRateId) {
        this.awardFandaRateId = awardFandaRateId;
    }

    /**
     * 
     * This method...
     * @return
     */
    public KualiDecimal getApplicableFandaRate() {
        return applicableFandaRate;
    }

    /**
     * 
     * This method...
     * @param applicableFandaRate
     */
    public void setApplicableFandaRate(KualiDecimal applicableFandaRate) {
        this.applicableFandaRate = applicableFandaRate;
    }

    /**
     * 
     * This method...
     * @return
     */
    public Integer getFandaRateTypeCode() {
        return fandaRateTypeCode;
    }

    /**
     * 
     * This method...
     * @param fandaRateTypeCode
     */
    public void setFandaRateTypeCode(Integer fandaRateTypeCode) {
        this.fandaRateTypeCode = fandaRateTypeCode;
    }

    /**
     * 
     * This method...
     * @return
     */
    public String getFiscalYear() {
        return fiscalYear;
    }

    /**
     * 
     * This method...
     * @param fiscalYear
     */
    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    /**
     * 
     * This method...
     * @return
     */
    public String getOnCampusFlag() {
        return onCampusFlag;
    }

    /**
     * 
     * This method...
     * @param onCampusFlag
     */
    public void setOnCampusFlag(String onCampusFlag) {
        this.onCampusFlag = onCampusFlag;
    }

    /**
     * 
     * This method...
     * @return
     */
    public KualiDecimal getUnderrecoveryOfIndirectCost() {
        return underrecoveryOfIndirectCost;
    }

    /**
     * 
     * This method...
     * @param underrecoveryOfIndirectCost
     */
    public void setUnderrecoveryOfIndirectCost(KualiDecimal underrecoveryOfIndirectCost) {
        this.underrecoveryOfIndirectCost = underrecoveryOfIndirectCost;
    }

    /**
     * 
     * This method...
     * @return
     */
    public String getSourceAccount() {
        return sourceAccount;
    }

    /**
     * 
     * This method...
     * @param sourceAccount
     */
    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    /**
     * 
     * This method...
     * @return
     */
    public String getDestinationAccount() {
        return destinationAccount;
    }

    /**
     * 
     * This method...
     * @param destinationAccount
     */
    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    /**
     * 
     * This method...
     * @return
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 
     * This method...
     * @param startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 
     * This method...
     * @return
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 
     * This method...
     * @param endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 
     * @see org.kuali.rice.kns.bo.BusinessObjectBase#toStringMapper()
     */
    @Override 
    protected LinkedHashMap<String,Object> toStringMapper() {
        LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();
        hashMap.put("awardFandaRateId", getAwardFandaRateId());
        hashMap.put("applicableFandaRate", getApplicableFandaRate());
        hashMap.put("fandaRateTypeCode", getFandaRateTypeCode());
        hashMap.put("fiscalYear", getFiscalYear());
        hashMap.put("onCampusFlag", getOnCampusFlag());
        hashMap.put("underrecoveryOfIndirectCost", getUnderrecoveryOfIndirectCost());
        hashMap.put("sourceAccount", getSourceAccount());
        hashMap.put("destinationAccount", getDestinationAccount());
        hashMap.put("startDate", getStartDate());
        hashMap.put("endDate", getEndDate());
        return hashMap;
    }

    /**
     * 
     * This method...
     * @return
     */
    public Award getAward() {
        return award;
    }

    /**
     * 
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

    public String getAwardNumber() {
        return awardNumber;
    }

    public void setAwardNumber(String awardNumber) {
        //do nothing
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        //do nothing
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((applicableFandaRate == null) ? 0 : applicableFandaRate.hashCode());
        result = prime * result + ((awardNumber == null) ? 0 : awardNumber.hashCode());
        result = prime * result + ((destinationAccount == null) ? 0 : destinationAccount.hashCode());
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + ((fandaRateTypeCode == null) ? 0 : fandaRateTypeCode.hashCode());
        result = prime * result + ((fiscalYear == null) ? 0 : fiscalYear.hashCode());
        result = prime * result + ((onCampusFlag == null) ? 0 : onCampusFlag.hashCode());
        result = prime * result + ((sequenceNumber == null) ? 0 : sequenceNumber.hashCode());
        result = prime * result + ((sourceAccount == null) ? 0 : sourceAccount.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((underrecoveryOfIndirectCost == null) ? 0 : underrecoveryOfIndirectCost.hashCode());
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
        if (!(obj instanceof AwardFandaRate)){
            return false;
        }
        
        return equals((AwardFandaRate) obj);        
    }
    
    /**
     * 
     * Convenience method to check equality of another AwardFandaRate
     * @param awardFandaRate
     * @return
     */
    public boolean equals(AwardFandaRate awardFandaRate) {
        if (applicableFandaRate == null) {
            if (awardFandaRate.applicableFandaRate != null){
                return false;
            }                
        }else if (!applicableFandaRate.equals(awardFandaRate.applicableFandaRate)){
            return false;
        }            
        if (awardNumber == null) {
            if (awardFandaRate.awardNumber != null){
                return false;
            }                
        }else if (!awardNumber.equals(awardFandaRate.awardNumber)){
            return false;
        }            
        if (destinationAccount == null) {
            if (awardFandaRate.destinationAccount != null){
                return false;
            }                
        }else if (!destinationAccount.equals(awardFandaRate.destinationAccount)){
            return false;
        }            
        if (endDate == null) {
            if (awardFandaRate.endDate != null){
                return false;
            }   
        }else if (!endDate.equals(awardFandaRate.endDate)){
            return false;
        }   
        if (fandaRateTypeCode == null) {
            if (awardFandaRate.fandaRateTypeCode != null){
                return false;
            }   
        }else if (!fandaRateTypeCode.equals(awardFandaRate.fandaRateTypeCode)){
            return false;
        }            
        if (fiscalYear == null) {
            if (awardFandaRate.fiscalYear != null){
                return false;
            }   
        }else if (!fiscalYear.equals(awardFandaRate.fiscalYear)){
            return false;
        }            
        if (onCampusFlag == null) {
            if (awardFandaRate.onCampusFlag != null){
                return false;
            }   
        }else if (!onCampusFlag.equals(awardFandaRate.onCampusFlag)){
            return false;
        }            
        if (sequenceNumber == null) {
            if (awardFandaRate.sequenceNumber != null){
                return false;
            }   
        }else if (!sequenceNumber.equals(awardFandaRate.sequenceNumber)){
            return false;
        }            
        if (sourceAccount == null) {
            if (awardFandaRate.sourceAccount != null){
                return false;
            }   
        }else if (!sourceAccount.equals(awardFandaRate.sourceAccount)){
            return false;
        }   
        if (startDate == null) {
            if (awardFandaRate.startDate != null){
                return false;
            }   
        }else if (!startDate.equals(awardFandaRate.startDate)){
            return false;
        }            
        if (underrecoveryOfIndirectCost == null) {
            if (awardFandaRate.underrecoveryOfIndirectCost != null){
                return false;
            }   
        }else if (!underrecoveryOfIndirectCost.equals(awardFandaRate.underrecoveryOfIndirectCost)){
            return false;
        }   
        return true;
    }
    
}
