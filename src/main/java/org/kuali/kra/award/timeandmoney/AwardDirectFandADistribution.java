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
package org.kuali.kra.award.timeandmoney;

import java.sql.Date;

import org.kuali.kra.award.AwardAssociate;
import org.kuali.rice.core.api.util.type.KualiDecimal;

/**
 * This class is the BO representation of an Award Amount F and A Distribution.
 */
public class AwardDirectFandADistribution extends AwardAssociate {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1548622355738763084L;

    private Long awardDirectFandADistributionId;

    private Integer amountSequenceNumber;

    private Long awardAmountInfoId;

    private Integer budgetPeriod;

    private Date startDate;

    private Date endDate;

    private KualiDecimal directCost;

    private KualiDecimal indirectCost;

    public AwardDirectFandADistribution() {
        setDirectCost(new KualiDecimal(0.00));
        setIndirectCost(new KualiDecimal(0.00));
    }

    public AwardDirectFandADistribution(int budgetPeriodNum, Date periodStartDate, Date periodEndDate) {
        setDirectCost(new KualiDecimal(0.00));
        setIndirectCost(new KualiDecimal(0.00));
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
    public KualiDecimal getDirectCost() {
        return directCost;
    }

    /**
     * Sets the directCost attribute value.
     * @param directCost The directCost to set.
     */
    public void setDirectCost(KualiDecimal directCost) {
        this.directCost = directCost;
    }

    /**
     * Gets the indirectCost attribute. 
     * @return Returns the indirectCost.
     */
    public KualiDecimal getIndirectCost() {
        return indirectCost;
    }

    /**
     * Sets the indirectCost attribute value.
     * @param indirectCost The indirectCost to set.
     */
    public void setIndirectCost(KualiDecimal indirectCost) {
        this.indirectCost = indirectCost;
    }

    /**
     * Gets the serialVersionUID attribute. 
     * @return Returns the serialVersionUID.
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.awardDirectFandADistributionId = null;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
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

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
