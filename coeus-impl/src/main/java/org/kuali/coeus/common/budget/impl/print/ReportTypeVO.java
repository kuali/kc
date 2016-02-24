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
package org.kuali.coeus.common.budget.impl.print;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.sql.Date;

public class ReportTypeVO {

    private Long budgetLineItemId;
	private String rateClassDesc;
	private String rateTypeDesc;
	private Date startDate;
	private Date endDate;
	private ScaleTwoDecimal appliedRate;
	private ScaleTwoDecimal salaryRequested;
	private ScaleTwoDecimal calculatedCost;
	private Boolean onOffCampusFlag;
	private String costElementDesc;
	private String budgetCategoryDesc;
	private ScaleTwoDecimal costSharingAmount;
	private String personName;
	private ScaleTwoDecimal percentEffort;
	private ScaleTwoDecimal percentCharged;
	private String budgetCategoryCode;
	private Integer investigatorFlag;
	private ScaleTwoDecimal vacationRate;
	private ScaleTwoDecimal employeeBenefitRate;
	private ScaleTwoDecimal fringe;

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

	public ScaleTwoDecimal getAppliedRate() {
		return appliedRate;
	}

	public void setAppliedRate(ScaleTwoDecimal appliedRate) {
		this.appliedRate = appliedRate;
	}

	public ScaleTwoDecimal getSalaryRequested() {
		return salaryRequested;
	}

	public void setSalaryRequested(ScaleTwoDecimal salaryRequested) {
		this.salaryRequested = salaryRequested;
	}

	public ScaleTwoDecimal getCalculatedCost() {
		return calculatedCost;
	}

	public void setCalculatedCost(ScaleTwoDecimal calculatedCost) {
		this.calculatedCost = calculatedCost;
	}

	public Boolean getOnOffCampusFlag() {
		return onOffCampusFlag;
	}

	public void setOnOffCampusFlag(Boolean onOffCampusFlag) {
		this.onOffCampusFlag = onOffCampusFlag;
	}

	public String getRateClassDesc() {
		return rateClassDesc;
	}

	public void setRateClassDesc(String rateClassDesc) {
		this.rateClassDesc = rateClassDesc;
	}

	public String getRateTypeDesc() {
		return rateTypeDesc;
	}

	public void setRateTypeDesc(String rateTypeDesc) {
		this.rateTypeDesc = rateTypeDesc;
	}

	public String getCostElementDesc() {
		return costElementDesc;
	}

	public void setCostElementDesc(String costElementDesc) {
		this.costElementDesc = costElementDesc;
	}

	public String getBudgetCategoryDesc() {
		return budgetCategoryDesc;
	}

	public void setBudgetCategoryDesc(String budgetCategoryDesc) {
		this.budgetCategoryDesc = budgetCategoryDesc;
	}

	public ScaleTwoDecimal getCostSharingAmount() {
		return costSharingAmount;
	}

	public void setCostSharingAmount(ScaleTwoDecimal costSharingAmount) {
		this.costSharingAmount = costSharingAmount;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public ScaleTwoDecimal getPercentEffort() {
		return percentEffort;
	}

	public void setPercentEffort(ScaleTwoDecimal percentEffort) {
		this.percentEffort = percentEffort;
	}

	public ScaleTwoDecimal getPercentCharged() {
		return percentCharged;
	}

	public void setPercentCharged(ScaleTwoDecimal percentCharged) {
		this.percentCharged = percentCharged;
	}

	public String getBudgetCategoryCode() {
		return budgetCategoryCode;
	}

	public void setBudgetCategoryCode(String budgetCategoryCode) {
		this.budgetCategoryCode = budgetCategoryCode;
	}

	public Integer getInvestigatorFlag() {
		return investigatorFlag;
	}

	public void setInvestigatorFlag(Integer investigatorFlag) {
		this.investigatorFlag = investigatorFlag;
	}

	public ScaleTwoDecimal getFringe() {
		return fringe;
	}

	public void setFringe(ScaleTwoDecimal fringe) {
		this.fringe = fringe;
	}

	public ScaleTwoDecimal getVacationRate() {
		return vacationRate;
	}

	public void setVacationRate(ScaleTwoDecimal vacationRate) {
		this.vacationRate = vacationRate;
	}

	public ScaleTwoDecimal getEmployeeBenefitRate() {
		return employeeBenefitRate;
	}

	public void setEmployeeBenefitRate(ScaleTwoDecimal employeeBenefitRate) {
		this.employeeBenefitRate = employeeBenefitRate;
	}

    public Long getBudgetLineItemId() {
        return budgetLineItemId;
    }

    public void setBudgetLineItemId(Long budgetLineItemId) {
        this.budgetLineItemId = budgetLineItemId;
    }
}
