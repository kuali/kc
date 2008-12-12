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

import java.util.Collection;
import java.util.LinkedHashMap;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.bo.CostShareType;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.rice.KNSServiceLocator;

public class AwardCostShare extends KraPersistableBusinessObjectBase {
    
    private Long awardCostShareId;
    private String fiscalYear;
    private KualiDecimal costSharePercentage;
    private Integer costShareTypeCode;
    private String source;
    private String destination;
    private KualiDecimal commitmentAmount;
    private Award award;
    private CostShareType costShareType;
    private String awardNumber;//temp
    private Integer sequenceNumber;//temp
    
    

    public String getAwardNumber() {
        return awardNumber;
     }
    
    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
     }
    
    /**
     * 
     * Constructs a AwardCostShare.java.
     */
    public AwardCostShare() {
        super();                
    }

    public Award getAward() {
        return award;
    }


    public void setAward(Award award) {
        this.award = award;
    }


    public CostShareType getCostShareType() {
        return costShareType;
    }
    
    @SuppressWarnings("unchecked")
    public void setCostShareTypeCode(Integer costShareTypeCode){
        BusinessObjectService costShareTypeService = getBusinessObjectService();
        Collection<CostShareType> costShareTypes = (Collection<CostShareType>) costShareTypeService.findAll(CostShareType.class);
        for(CostShareType costShareType : costShareTypes){
            if(costShareType.getCostShareTypeCode().equals(costShareTypeCode)){
                setCostShareType(costShareType);
                this.costShareTypeCode = costShareType.getCostShareTypeCode();
            }
        }
    }
    
    public Integer getCostShareTypeCode() {
        return costShareType.getCostShareTypeCode();
    }


    public void setCostShareType(CostShareType costShareType) {
        this.costShareType = costShareType;
    }


    public int getSequenceNumber() {
        return sequenceNumber;
     }


    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }


    public String getFiscalYear() {
        return fiscalYear;
    }


    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }


    public KualiDecimal getCostSharePercentage() {
        return costSharePercentage;
    }


    public void setCostSharePercentage(KualiDecimal costSharePercentage) {
        this.costSharePercentage = costSharePercentage;
    }


    public String getSource() {
        return source;
    }


    public void setSource(String source) {
        this.source = source;
    }


    public String getDestination() {
        return destination;
    }


    public void setDestination(String destination) {
        this.destination = destination;
    }


    public KualiDecimal getCommitmentAmount() {
        return commitmentAmount;
    }


    public void setCommitmentAmount(KualiDecimal commitmentAmount) {
        this.commitmentAmount = commitmentAmount;
    }
    
    
    @Override
    protected LinkedHashMap<String,Object> toStringMapper() {        
        LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();
        hashMap.put("awardCostShareId", getAwardCostShareId());
        hashMap.put("costShareTypeCode", getCostShareTypeCode());
        hashMap.put("awardNumber", getAwardNumber());
        hashMap.put("sequenceNumber", getSequenceNumber());
        hashMap.put("fiscalYear", getFiscalYear());
        hashMap.put("costSharePercentage", getCostSharePercentage());
        hashMap.put("source", getSource());
        hashMap.put("destination", getDestination());
        hashMap.put("commitmentAmount", getCommitmentAmount());
        return hashMap;

}


    public Long getAwardCostShareId() {
        return awardCostShareId;
    }


    public void setAwardCostShareId(Long awardCostShareId) {
        this.awardCostShareId = awardCostShareId;
    }
    
    protected BusinessObjectService getBusinessObjectService() {
        return KNSServiceLocator.getBusinessObjectService();
    }
}
