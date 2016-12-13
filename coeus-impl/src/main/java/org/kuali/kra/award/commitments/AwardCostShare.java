/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.award.commitments;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.award.home.ValuableItem;
import org.kuali.kra.bo.CostShareType;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.sql.Date;
import java.util.Collection;

public class AwardCostShare extends AwardAssociate implements ValuableItem {

    private static final long serialVersionUID = -839007857238262207L;

    private Long awardCostShareId;

    private String projectPeriod;

    private ScaleTwoDecimal costSharePercentage;

    private Integer costShareTypeCode;

    private Date verificationDate;

    private ScaleTwoDecimal costShareMet;

    private String source;

    private String destination;

    private ScaleTwoDecimal commitmentAmount;

    private String unitNumber;

    private Unit unit;

    private CostShareType costShareType;


    public AwardCostShare() {
        super();
    }

    public CostShareType getCostShareType() {
        return costShareType;
    }

    public void setCostShareTypeCode(Integer costShareTypeCode) {
        this.costShareTypeCode = costShareTypeCode;
        Collection<CostShareType> costShareTypes = getBusinessObjectService().findAll(CostShareType.class);
        for (CostShareType costShareType : costShareTypes) {
            if (costShareType.getCostShareTypeCode().equals(costShareTypeCode)) {
                setCostShareType(costShareType);
            }
        }
    }

    public Integer getCostShareTypeCode() {
        if (costShareType == null) {
            return null;
        } else {
            return costShareTypeCode;
        }
    }

    public void setCostShareType(CostShareType costShareType) {
        this.costShareType = costShareType;
    }

    public String getProjectPeriod() {
        return projectPeriod;
    }

    public void setProjectPeriod(String projectPeriod) {
        this.projectPeriod = projectPeriod;
    }

    public ScaleTwoDecimal getCostSharePercentage() {
        return costSharePercentage;
    }

    public void setCostSharePercentage(ScaleTwoDecimal costSharePercentage) {
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

    public ScaleTwoDecimal getCommitmentAmount() {
        return commitmentAmount;
    }

    public void setCommitmentAmount(ScaleTwoDecimal commitmentAmount) {
        this.commitmentAmount = commitmentAmount;
    }

    public Long getAwardCostShareId() {
        return awardCostShareId;
    }

    public void setAwardCostShareId(Long awardCostShareId) {
        this.awardCostShareId = awardCostShareId;
    }

    protected BusinessObjectService getBusinessObjectService() {
        return (BusinessObjectService) KcServiceLocator.getService("businessObjectService");
    }

    @Override
    public ScaleTwoDecimal getAmount() {
        return getCommitmentAmount();
    }

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
        result = prime * result + ((unitNumber == null) ? 0 : unitNumber.hashCode());
        return result;
    }

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
        if (unitNumber == null) {
            if (other.unitNumber != null) return false;
        } else if (!unitNumber.equals(other.unitNumber)) return false;
        return true;
    }

    public Date getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(Date verificationDate) {
        this.verificationDate = verificationDate;
    }

    public ScaleTwoDecimal getCostShareMet() {
        return costShareMet;
    }

    public void setCostShareMet(ScaleTwoDecimal costShareMet) {
        this.costShareMet = costShareMet;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public Unit getUnit() {
        if (unit == null && StringUtils.isNotBlank(getUnitNumber()) || (unit != null && !unit.getUnitNumber().equals(getUnitNumber()))) {
            refreshReferenceObject("unit");
        }

        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getUnitName() {
        Unit unit = getUnit();
        return unit != null ? unit.getUnitName() : null;
    }

    @Override
    public void resetPersistenceState() {
        this.awardCostShareId = null;
    }
}
