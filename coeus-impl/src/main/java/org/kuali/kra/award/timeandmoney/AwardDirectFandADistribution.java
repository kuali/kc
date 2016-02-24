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
package org.kuali.kra.award.timeandmoney;

import org.kuali.kra.award.AwardAssociate;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.sql.Date;

/**
 * This class is the BO representation of an Award Amount F and A Distribution.
 */
public class AwardDirectFandADistribution extends AwardAssociate {


    private static final long serialVersionUID = 1548622355738763084L;

    private Long awardDirectFandADistributionId;

    private Integer amountSequenceNumber;

    private Long awardAmountInfoId;

    private Integer budgetPeriod;

    private Date startDate;

    private Date endDate;

    private ScaleTwoDecimal directCost;

    private ScaleTwoDecimal indirectCost;

    public AwardDirectFandADistribution() {
        setDirectCost(new ScaleTwoDecimal(0.00));
        setIndirectCost(new ScaleTwoDecimal(0.00));
    }

    public AwardDirectFandADistribution(int budgetPeriodNum, Date periodStartDate, Date periodEndDate) {
        setDirectCost(new ScaleTwoDecimal(0.00));
        setIndirectCost(new ScaleTwoDecimal(0.00));
        setBudgetPeriod(budgetPeriodNum);
        setStartDate(periodStartDate);
        setEndDate(periodEndDate);
    }

    /**
     * FIXME: Field may be dead code
     * This method added because repository validation fails if no getter present for repository declared field
     * @return
     */
    public Long getAwardAmountInfoId() {
        return awardAmountInfoId;
    }

    /**
     * Gets the awardDirectFandADistributionId attribute. 
     * @return Returns the awardDirectFandADistributionId.
     */
    public Long getAwardDirectFandADistributionId() {
        return awardDirectFandADistributionId;
    }

    /**
     * Sets the awardDirectFandADistributionId attribute value.
     * @param awardDirectFandADistributionId The awardDirectFandADistributionId to set.
     */
    public void setAwardDirectFandADistributionId(Long awardDirectFandADistributionId) {
        this.awardDirectFandADistributionId = awardDirectFandADistributionId;
    }

    /**
     * Gets the amountSequenceNumber attribute. 
     * @return Returns the amountSequenceNumber.
     */
    public Integer getAmountSequenceNumber() {
        return amountSequenceNumber;
    }

    /**
     * Sets the amountSequenceNumber attribute value.
     * @param amountSequenceNumber The amountSequenceNumber to set.
     */
    public void setAmountSequenceNumber(Integer amountSequenceNumber) {
        this.amountSequenceNumber = amountSequenceNumber;
    }

    /**
     * Gets the budgetPeriod attribute. 
     * @return Returns the budgetPeriod.
     */
    public Integer getBudgetPeriod() {
        return budgetPeriod;
    }

    /**
     * Sets the budgetPeriod attribute value.
     * @param budgetPeriod The budgetPeriod to set.
     */
    public void setBudgetPeriod(Integer budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }

    /**
     * Gets the startDate attribute. 
     * @return Returns the startDate.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the startDate attribute value.
     * @param startDate The startDate to set.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the endDate attribute. 
     * @return Returns the endDate.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the endDate attribute value.
     * @param endDate The endDate to set.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the directCost attribute. 
     * @return Returns the directCost.
     */
    public ScaleTwoDecimal getDirectCost() {
        return directCost;
    }

    /**
     * Sets the directCost attribute value.
     * @param directCost The directCost to set.
     */
    public void setDirectCost(ScaleTwoDecimal directCost) {
        this.directCost = directCost;
    }

    /**
     * Gets the indirectCost attribute. 
     * @return Returns the indirectCost.
     */
    public ScaleTwoDecimal getIndirectCost() {
        return indirectCost;
    }

    /**
     * Sets the indirectCost attribute value.
     * @param indirectCost The indirectCost to set.
     */
    public void setIndirectCost(ScaleTwoDecimal indirectCost) {
        this.indirectCost = indirectCost;
    }

    /**
     * Gets the serialVersionUID attribute. 
     * @return Returns the serialVersionUID.
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public void resetPersistenceState() {
        this.awardDirectFandADistributionId = null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((amountSequenceNumber == null) ? 0 : amountSequenceNumber.hashCode());
        result = prime * result + ((awardAmountInfoId == null) ? 0 : awardAmountInfoId.hashCode());
        result = prime * result + ((awardDirectFandADistributionId == null) ? 0 : awardDirectFandADistributionId.hashCode());
        result = prime * result + ((budgetPeriod == null) ? 0 : budgetPeriod.hashCode());
        result = prime * result + ((directCost == null) ? 0 : directCost.hashCode());
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + ((indirectCost == null) ? 0 : indirectCost.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        final AwardDirectFandADistribution other = (AwardDirectFandADistribution) obj;
        if (amountSequenceNumber == null) {
            if (other.amountSequenceNumber != null) return false;
        } else if (!amountSequenceNumber.equals(other.amountSequenceNumber)) return false;
        if (awardAmountInfoId == null) {
            if (other.awardAmountInfoId != null) return false;
        } else if (!awardAmountInfoId.equals(other.awardAmountInfoId)) return false;
        if (awardDirectFandADistributionId == null) {
            if (other.awardDirectFandADistributionId != null) return false;
        } else if (!awardDirectFandADistributionId.equals(other.awardDirectFandADistributionId)) return false;
        if (budgetPeriod == null) {
            if (other.budgetPeriod != null) return false;
        } else if (!budgetPeriod.equals(other.budgetPeriod)) return false;
        if (directCost == null) {
            if (other.directCost != null) return false;
        } else if (!directCost.equals(other.directCost)) return false;
        if (endDate == null) {
            if (other.endDate != null) return false;
        } else if (!endDate.equals(other.endDate)) return false;
        if (indirectCost == null) {
            if (other.indirectCost != null) return false;
        } else if (!indirectCost.equals(other.indirectCost)) return false;
        if (startDate == null) {
            if (other.startDate != null) return false;
        } else if (!startDate.equals(other.startDate)) return false;
        return true;
    }
}
