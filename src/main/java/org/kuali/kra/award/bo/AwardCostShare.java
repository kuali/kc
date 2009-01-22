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
    private static final long serialVersionUID = -839007857238262207L;
    
    private Long awardCostShareId;
    private String fiscalYear;
    private KualiDecimal costSharePercentage;
    private Integer costShareTypeCode;
    private String source;
    private String destination;
    private KualiDecimal commitmentAmount;
    private Award award;
    private CostShareType costShareType;
    private String awardNumber;
    private Integer sequenceNumber;
    
    

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
     * 
     * Constructs a AwardCostShare.java.
     */
    public AwardCostShare() {
        super();                
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
            sequenceNumber = (award.getSequenceNumber());
            awardNumber = (award.getAwardNumber());
        }
    }


    /**
     * This method...
     * @return
     */
    public CostShareType getCostShareType() {
        return costShareType;
    }
    
    /**
     * This method...
     * @param costShareTypeCode
     */
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
    
    /**
     * This method...
     * @return
     */
    public Integer getCostShareTypeCode() {
        return costShareType.getCostShareTypeCode();
    }


    /**
     * This method...
     * @param costShareType
     */
    public void setCostShareType(CostShareType costShareType) {
        this.costShareType = costShareType;
    }


    /**
     * This method...
     * @return
     */
    public int getSequenceNumber() {
        return sequenceNumber;
     }


    /**
     * This method...
     * @param sequenceNumber
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        // do nothing
    }


    /**
     * This method...
     * @return
     */
    public String getFiscalYear() {
        return fiscalYear;
    }


    /**
     * This method...
     * @param fiscalYear
     */
    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }


    /**
     * This method...
     * @return
     */
    public KualiDecimal getCostSharePercentage() {
        return costSharePercentage;
    }


    /**
     * This method...
     * @param costSharePercentage
     */
    public void setCostSharePercentage(KualiDecimal costSharePercentage) {
        this.costSharePercentage = costSharePercentage;
    }


    /**
     * This method...
     * @return
     */
    public String getSource() {
        return source;
    }


    /**
     * This method...
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }


    /**
     * This method...
     * @return
     */
    public String getDestination() {
        return destination;
    }


    /**
     * This method...
     * @param destination
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }


    /**
     * This method...
     * @return
     */
    public KualiDecimal getCommitmentAmount() {
        return commitmentAmount;
    }


    /**
     * This method...
     * @param commitmentAmount
     */
    public void setCommitmentAmount(KualiDecimal commitmentAmount) {
        this.commitmentAmount = commitmentAmount;
    }
    
    
    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    protected LinkedHashMap<String,Object> toStringMapper() {        
        LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();
        hashMap.put("awardCostShareId", getAwardCostShareId());
        hashMap.put("awardNumber", getAwardNumber());
        hashMap.put("sequenceNumber", getSequenceNumber());
        hashMap.put("fiscalYear", getFiscalYear());
        hashMap.put("costSharePercentage", getCostSharePercentage());
        hashMap.put("source", getSource());
        hashMap.put("destination", getDestination());
        hashMap.put("commitmentAmount", getCommitmentAmount());
        return hashMap;

}


    /**
     * This method...
     * @return
     */
    public Long getAwardCostShareId() {
        return awardCostShareId;
    }


    /**
     * This method...
     * @param awardCostShareId
     */
    public void setAwardCostShareId(Long awardCostShareId) {
        this.awardCostShareId = awardCostShareId;
    }
    
    /**
     * This method...
     * @return
     */
    protected BusinessObjectService getBusinessObjectService() {
        return KNSServiceLocator.getBusinessObjectService();
    }
}
