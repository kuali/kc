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
package org.kuali.coeus.award.dto;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.sql.Date;

public class AwardFandADistributionDto {

    private Long awardDirectFandADistributionId;

    private Integer amountSequenceNumber;

    private Long awardAmountInfoId;

    private Integer budgetPeriod;

    private Date startDate;

    private Date endDate;

    private ScaleTwoDecimal directCost;

    private ScaleTwoDecimal indirectCost;

    public Long getAwardDirectFandADistributionId() {
        return awardDirectFandADistributionId;
    }

    public void setAwardDirectFandADistributionId(Long awardDirectFandADistributionId) {
        this.awardDirectFandADistributionId = awardDirectFandADistributionId;
    }

    public Integer getAmountSequenceNumber() {
        return amountSequenceNumber;
    }

    public void setAmountSequenceNumber(Integer amountSequenceNumber) {
        this.amountSequenceNumber = amountSequenceNumber;
    }

    public Long getAwardAmountInfoId() {
        return awardAmountInfoId;
    }

    public void setAwardAmountInfoId(Long awardAmountInfoId) {
        this.awardAmountInfoId = awardAmountInfoId;
    }

    public Integer getBudgetPeriod() {
        return budgetPeriod;
    }

    public void setBudgetPeriod(Integer budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ScaleTwoDecimal getDirectCost() {
        return directCost;
    }

    public void setDirectCost(ScaleTwoDecimal directCost) {
        this.directCost = directCost;
    }

    public ScaleTwoDecimal getIndirectCost() {
        return indirectCost;
    }

    public void setIndirectCost(ScaleTwoDecimal indirectCost) {
        this.indirectCost = indirectCost;
    }
}
