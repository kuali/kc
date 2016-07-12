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

import com.codiform.moo.annotation.Property;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.sql.Date;

public class AwardBudgetGeneralInfoDto {
    private Long awardId;
    private Long budgetId;
    private Integer budgetVersionNumber;
    private String budgetInitiator;
    private String awardBudgetStatusCode;
    @JsonIgnore
    @Property(translate = true)
    private AwardBudgetStatusDto awardBudgetStatus;
    private String awardBudgetTypeCode;
    @JsonIgnore
    @Property(translate = true)
    private AwardBudgetTypeDto awardBudgetType;
    private ScaleTwoDecimal obligatedTotal = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal obligatedAmount = ScaleTwoDecimal.ZERO;
    private String description;
    private String onOffCampusFlag;
    private Date endDate;
    private Date startDate;
    private ScaleTwoDecimal totalCost;
    private ScaleTwoDecimal totalDirectCost;
    private ScaleTwoDecimal totalIndirectCost;
    private ScaleTwoDecimal costSharingAmount;
    private ScaleTwoDecimal underrecoveryAmount;
    private ScaleTwoDecimal totalCostLimit;
    private ScaleTwoDecimal residualFunds;
    private String ohRateClassCode;
    @JsonIgnore
    @Property(translate = true, source = "rateClass")
    private RateClassDto ohRateClass;
    private String ohRateTypeCode;
    private String comments;
    private Boolean modularBudgetFlag = Boolean.FALSE;
    private String urRateClassCode;
    private ScaleTwoDecimal totalDirectCostLimit;

    public Long getAwardId() {
        return awardId;
    }

    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    public Integer getBudgetVersionNumber() {
        return budgetVersionNumber;
    }

    public void setBudgetVersionNumber(Integer budgetVersionNumber) {
        this.budgetVersionNumber = budgetVersionNumber;
    }

    public String getBudgetInitiator() {
        return budgetInitiator;
    }

    public void setBudgetInitiator(String budgetInitiator) {
        this.budgetInitiator = budgetInitiator;
    }

    public String getAwardBudgetStatusCode() {
        return awardBudgetStatusCode;
    }

    public void setAwardBudgetStatusCode(String awardBudgetStatusCode) {
        this.awardBudgetStatusCode = awardBudgetStatusCode;
    }

    public String getAwardBudgetTypeCode() {
        return awardBudgetTypeCode;
    }

    public void setAwardBudgetTypeCode(String awardBudgetTypeCode) {
        this.awardBudgetTypeCode = awardBudgetTypeCode;
    }

    public ScaleTwoDecimal getObligatedTotal() {
        return obligatedTotal;
    }

    public void setObligatedTotal(ScaleTwoDecimal obligatedTotal) {
        this.obligatedTotal = obligatedTotal;
    }

    public ScaleTwoDecimal getObligatedAmount() {
        return obligatedAmount;
    }

    public void setObligatedAmount(ScaleTwoDecimal obligatedAmount) {
        this.obligatedAmount = obligatedAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOnOffCampusFlag() {
        return onOffCampusFlag;
    }

    public void setOnOffCampusFlag(String onOffCampusFlag) {
        this.onOffCampusFlag = onOffCampusFlag;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public ScaleTwoDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(ScaleTwoDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public ScaleTwoDecimal getTotalDirectCost() {
        return totalDirectCost;
    }

    public void setTotalDirectCost(ScaleTwoDecimal totalDirectCost) {
        this.totalDirectCost = totalDirectCost;
    }

    public ScaleTwoDecimal getTotalIndirectCost() {
        return totalIndirectCost;
    }

    public void setTotalIndirectCost(ScaleTwoDecimal totalIndirectCost) {
        this.totalIndirectCost = totalIndirectCost;
    }

    public ScaleTwoDecimal getCostSharingAmount() {
        return costSharingAmount;
    }

    public void setCostSharingAmount(ScaleTwoDecimal costSharingAmount) {
        this.costSharingAmount = costSharingAmount;
    }

    public ScaleTwoDecimal getUnderrecoveryAmount() {
        return underrecoveryAmount;
    }

    public void setUnderrecoveryAmount(ScaleTwoDecimal underrecoveryAmount) {
        this.underrecoveryAmount = underrecoveryAmount;
    }

    public ScaleTwoDecimal getTotalCostLimit() {
        return totalCostLimit;
    }

    public void setTotalCostLimit(ScaleTwoDecimal totalCostLimit) {
        this.totalCostLimit = totalCostLimit;
    }

    public ScaleTwoDecimal getResidualFunds() {
        return residualFunds;
    }

    public void setResidualFunds(ScaleTwoDecimal residualFunds) {
        this.residualFunds = residualFunds;
    }

    public String getOhRateClassCode() {
        return ohRateClassCode;
    }

    public void setOhRateClassCode(String ohRateClassCode) {
        this.ohRateClassCode = ohRateClassCode;
    }

    public String getOhRateTypeCode() {
        return ohRateTypeCode;
    }

    public void setOhRateTypeCode(String ohRateTypeCode) {
        this.ohRateTypeCode = ohRateTypeCode;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getModularBudgetFlag() {
        return modularBudgetFlag;
    }

    public void setModularBudgetFlag(Boolean modularBudgetFlag) {
        this.modularBudgetFlag = modularBudgetFlag;
    }

    public String getUrRateClassCode() {
        return urRateClassCode;
    }

    public void setUrRateClassCode(String urRateClassCode) {
        this.urRateClassCode = urRateClassCode;
    }

    public ScaleTwoDecimal getTotalDirectCostLimit() {
        return totalDirectCostLimit;
    }

    public void setTotalDirectCostLimit(ScaleTwoDecimal totalDirectCostLimit) {
        this.totalDirectCostLimit = totalDirectCostLimit;
    }

    @JsonProperty
    public AwardBudgetStatusDto getAwardBudgetStatus() {
        return awardBudgetStatus;
    }
    @JsonIgnore
    public void setAwardBudgetStatus(AwardBudgetStatusDto awardBudgetStatus) {
        this.awardBudgetStatus = awardBudgetStatus;
    }
    @JsonProperty
    public AwardBudgetTypeDto getAwardBudgetType() {
        return awardBudgetType;
    }
    @JsonIgnore
    public void setAwardBudgetType(AwardBudgetTypeDto awardBudgetType) {
        this.awardBudgetType = awardBudgetType;
    }

    @JsonProperty
    public RateClassDto getOhRateClass() {
        return ohRateClass;
    }
    @JsonIgnore
    public void setOhRateClass(RateClassDto ohRateClass) {
        this.ohRateClass = ohRateClass;
    }

}