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
package org.kuali.kra.award.commitments;

import java.sql.Date;
import java.util.Collection;
import java.util.LinkedHashMap;

import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.ValuableItem;
import org.kuali.kra.bo.CostShareType;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * This class is business object representation of an Award Cost Share
 */
public class AwardCostShare extends KraPersistableBusinessObjectBase implements ValuableItem {
    private static final long serialVersionUID = -839007857238262207L;
    
    private Long awardCostShareId;
    private String fiscalYear;
    private KualiDecimal costSharePercentage;
    @SuppressWarnings("unused")
    private Integer costShareTypeCode;
    private Date verificationDate;
    private KualiDecimal costShareMet;
    private String source;
    private String destination;
    private KualiDecimal commitmentAmount;
    private Award award;
    private CostShareType costShareType;
    private String awardNumber;
    private Integer sequenceNumber;
    
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
        Collection<CostShareType> costShareTypes = 
            (Collection<CostShareType>) costShareTypeService.findAll(CostShareType.class);
        for(CostShareType costShareType : costShareTypes){
            if(costShareType.getCostShareTypeCode().equals(costShareTypeCode)){
                setCostShareType(costShareType);
            }
        }
    }
    
    /**
     * This method returns the primary key of the Cost Share BO
     * @return
     */
    public Integer getCostShareTypeCode() {
        if (costShareType == null) {
            return null;
        }
        else {
            return costShareType.getCostShareTypeCode();
        }
    }


    /**
     * This method...
     * @param costShareType
     */
    public void setCostShareType(CostShareType costShareType) {
        this.costShareType = costShareType;
        this.costShareTypeCode = costShareType.getCostShareTypeCode();
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
     * @see org.kuali.rice.kns.bo.BusinessObjectBase#toStringMapper()
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
        hashMap.put("verificationDate", getVerificationDate());
        hashMap.put("costShareMet", getCostShareMet());
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
        return (BusinessObjectService) KraServiceLocator.getService("businessObjectService");
    }

    /**
     * @see org.kuali.kra.award.bo.ValuableItem#getAmount()
     */
    public KualiDecimal getAmount() {
        return getCommitmentAmount();
    }
    
  //CHECKSTYLE_OFF: NPathComplexity|MethodLength|CyclomaticComplexity|LocalFinalVariableName|JavaNCSS|NeedBraces|RightCurly
    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((award == null) ? 0 : award.hashCode());
        result = prime * result + ((awardCostShareId == null) ? 0 : awardCostShareId.hashCode());
        result = prime * result + ((awardNumber == null) ? 0 : awardNumber.hashCode());
        result = prime * result + ((commitmentAmount == null) ? 0 : commitmentAmount.hashCode());
        result = prime * result + ((costSharePercentage == null) ? 0 : costSharePercentage.hashCode());
        result = prime * result + ((costShareType == null) ? 0 : costShareType.hashCode());
        result = prime * result + ((costShareTypeCode == null) ? 0 : costShareTypeCode.hashCode());
        result = prime * result + ((destination == null) ? 0 : destination.hashCode());
        result = prime * result + ((fiscalYear == null) ? 0 : fiscalYear.hashCode());
        result = prime * result + ((sequenceNumber == null) ? 0 : sequenceNumber.hashCode());
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final AwardCostShare other = (AwardCostShare) obj;
        if (award == null) {
            if (other.award != null)
                return false;
        }
        else if (!award.equals(other.award))
            return false;
        if (awardCostShareId == null) {
            if (other.awardCostShareId != null)
                return false;
        }
        else if (!awardCostShareId.equals(other.awardCostShareId))
            return false;
        if (awardNumber == null) {
            if (other.awardNumber != null)
                return false;
        }
        else if (!awardNumber.equals(other.awardNumber))
            return false;
        if (commitmentAmount == null) {
            if (other.commitmentAmount != null)
                return false;
        }
        else if (!commitmentAmount.equals(other.commitmentAmount))
            return false;
        if (costSharePercentage == null) {
            if (other.costSharePercentage != null)
                return false;
        }
        else if (!costSharePercentage.equals(other.costSharePercentage))
            return false;
        if (costShareType == null) {
            if (other.costShareType != null)
                return false;
        }
        else if (!costShareType.equals(other.costShareType))
            return false;
        if (costShareTypeCode == null) {
            if (other.costShareTypeCode != null)
                return false;
        }
        else if (!costShareTypeCode.equals(other.costShareTypeCode))
            return false;
        if (destination == null) {
            if (other.destination != null)
                return false;
        }
        else if (!destination.equals(other.destination))
            return false;
        if (fiscalYear == null) {
            if (other.fiscalYear != null)
                return false;
        }
        else if (!fiscalYear.equals(other.fiscalYear))
            return false;
        if (sequenceNumber == null) {
            if (other.sequenceNumber != null)
                return false;
        }
        else if (!sequenceNumber.equals(other.sequenceNumber))
            return false;
        if (source == null) {
            if (other.source != null)
                return false;
        }
        else if (!source.equals(other.source))
            return false;
        return true;
    }
  //CHECKSTYLE_ON: NPathComplexity|MethodLength|CyclomaticComplexity|LocalFinalVariableName|JavaNCSS|NeedBraces|RightCurly

    /**
     * Gets the verificationDate attribute. 
     * @return Returns the verificationDate.
     */
    public Date getVerificationDate() {
        return verificationDate;
    }

    /**
     * Sets the verificationDate attribute value.
     * @param verificationDate The verificationDate to set.
     */
    public void setVerificationDate(Date verificationDate) {
        this.verificationDate = verificationDate;
    }

    /**
     * Gets the costShareMet attribute. 
     * @return Returns the costShareMet.
     */
    public KualiDecimal getCostShareMet() {
        return costShareMet;
    }

    /**
     * Sets the costShareMet attribute value.
     * @param costShareMet The costShareMet to set.
     */
    public void setCostShareMet(KualiDecimal costShareMet) {
        this.costShareMet = costShareMet;
    }
}
