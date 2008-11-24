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

import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This class represents the AwardIndirectCostRate Business Object.
 */
public class AwardIndirectCostRate extends KraPersistableBusinessObjectBase { 

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private Long awardIndirectCostRateId;
    private KualiDecimal applicableIndirectCostRate; 
    private Integer indirectCostRateTypeCode; 
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
     * Constructs a AwardIndirectCostRate.java.
     */
    public AwardIndirectCostRate() { 

    } 
    
    /**
     * 
     * This method...
     * @return
     */
    public Long getAwardIndirectCostRateId() {
        return awardIndirectCostRateId;
    }

    /**
     * 
     * This method...
     * @param awardIndirectCostRateId
     */
    public void setAwardIndirectCostRateId(Long awardIndirectCostRateId) {
        this.awardIndirectCostRateId = awardIndirectCostRateId;
    }

    /**
     * 
     * This method...
     * @return
     */
    public KualiDecimal getApplicableIndirectCostRate() {
        return applicableIndirectCostRate;
    }

    /**
     * 
     * This method...
     * @param applicableIndirectCostRate
     */
    public void setApplicableIndirectCostRate(KualiDecimal applicableIndirectCostRate) {
        this.applicableIndirectCostRate = applicableIndirectCostRate;
    }

    /**
     * 
     * This method...
     * @return
     */
    public Integer getIdcRateTypeCode() {
        return indirectCostRateTypeCode;
    }

    /**
     * 
     * This method...
     * @param indirectCostRateTypeCode
     */
    public void setIdcRateTypeCode(Integer indirectCostRateTypeCode) {
        this.indirectCostRateTypeCode = indirectCostRateTypeCode;
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
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override 
    protected LinkedHashMap<String,Object> toStringMapper() {
        LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();
        hashMap.put("awardIndirectCostRateId", getAwardIndirectCostRateId());
        hashMap.put("applicableIndirectCostRate", getApplicableIndirectCostRate());
        hashMap.put("idcRateTypeCode", getIdcRateTypeCode());
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
    }
    
}
