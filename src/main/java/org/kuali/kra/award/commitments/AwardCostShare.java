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
package org.kuali.kra.award.commitments;

import java.sql.Date;
import java.util.Collection;

import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.award.home.ValuableItem;
import org.kuali.kra.bo.CostShareType;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class is business object representation of an Award Cost Share
 */
public class AwardCostShare extends AwardAssociate implements ValuableItem {

    private static final long serialVersionUID = -839007857238262207L;

    private Long awardCostShareId;

    private String projectPeriod;

    private KualiDecimal costSharePercentage;

    private Integer costShareTypeCode;

    private Date verificationDate;

    private KualiDecimal costShareMet;

    private String source;

    private String destination;

    private KualiDecimal commitmentAmount;

    private CostShareType costShareType;

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
    public CostShareType getCostShareType() {
        return costShareType;
    }

    /**
     * This method...
     * @param costShareTypeCode
     */
    @SuppressWarnings("unchecked")
    public void setCostShareTypeCode(Integer costShareTypeCode) {
        this.costShareTypeCode = costShareTypeCode;
        BusinessObjectService costShareTypeService = getBusinessObjectService();
        Collection<CostShareType> costShareTypes = (Collection<CostShareType>) costShareTypeService.findAll(CostShareType.class);
        for (CostShareType costShareType : costShareTypes) {
            if (costShareType.getCostShareTypeCode().equals(costShareTypeCode)) {
                setCostShareType(costShareType);
            }
        }
    }

    /**
     * This method returns the primary key of the Cost Share BO.
     * @return
     */
    public Integer getCostShareTypeCode() {
        if (costShareType == null) {
            return null;
        } else {
            return costShareTypeCode;
        }
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
    public String getProjectPeriod() {
        return projectPeriod;
    }

    /**
     * This method...
     * @param projectPeriod
     */
    public void setProjectPeriod(String projectPeriod) {
        this.projectPeriod = projectPeriod;
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
     * @see org.kuali.kra.award.home.ValuableItem#getAmount()
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
        int result = super.hashCode();
        result = prime * result + ((awardCostShareId == null) ? 0 : awardCostShareId.hashCode());
        result = prime * result + ((commitmentAmount == null) ? 0 : commitmentAmount.hashCode());
        result = prime * result + ((costSharePercentage == null) ? 0 : costSharePercentage.hashCode());
        result = prime * result + ((costShareType == null) ? 0 : costShareType.hashCode());
        result = prime * result + ((costShareTypeCode == null) ? 0 : costShareTypeCode.hashCode());
        result = prime * result + ((destination == null) ? 0 : destination.hashCode());
        result = prime * result + ((projectPeriod == null) ? 0 : projectPeriod.hashCode());
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) {
            return false;
        }
        final AwardCostShare other = (AwardCostShare) obj;
        if (awardCostShareId == null) {
            if (other.awardCostShareId != null) return false;
        } else if (!awardCostShareId.equals(other.awardCostShareId)) return false;
        if (commitmentAmount == null) {
            if (other.commitmentAmount != null) return false;
        } else if (!commitmentAmount.equals(other.commitmentAmount)) return false;
        if (costSharePercentage == null) {
            if (other.costSharePercentage != null) return false;
        } else if (!costSharePercentage.equals(other.costSharePercentage)) return false;
        if (costShareType == null) {
            if (other.costShareType != null) return false;
        } else if (!costShareType.equals(other.costShareType)) return false;
        if (costShareTypeCode == null) {
            if (other.costShareTypeCode != null) return false;
        } else if (!costShareTypeCode.equals(other.costShareTypeCode)) return false;
        if (destination == null) {
            if (other.destination != null) return false;
        } else if (!destination.equals(other.destination)) return false;
        if (projectPeriod == null) {
            if (other.projectPeriod != null) return false;
        } else if (!projectPeriod.equals(other.projectPeriod)) return false;
        if (source == null) {
            if (other.source != null) return false;
        } else if (!source.equals(other.source)) return false;
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

    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.awardCostShareId = null;
    }
}
