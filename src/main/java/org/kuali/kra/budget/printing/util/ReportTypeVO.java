package org.kuali.kra.budget.printing.util;

import java.sql.Date;

import org.kuali.kra.budget.BudgetDecimal;

public class ReportTypeVO {

	private String rateClassDesc;
	private String rateTypeDesc;
	private Date startDate;
	private Date endDate;
	private BudgetDecimal appliedRate;
	private BudgetDecimal salaryRequested;
	private BudgetDecimal calculatedCost;
	private Boolean onOffCampusFlag;
	private String costElementDesc;
	private String budgetCategoryDesc;
	private BudgetDecimal costSharingAmount;
	private String personName;
	private BudgetDecimal percentEffort;
	private BudgetDecimal percentCharged;
	private String budgetCategoryCode;
	private Integer investigatorFlag;
	private BudgetDecimal vacationRate;
	private BudgetDecimal employeeBenefitRate;
	private BudgetDecimal fringe;

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

	public BudgetDecimal getAppliedRate() {
		return appliedRate;
	}

	public void setAppliedRate(BudgetDecimal appliedRate) {
		this.appliedRate = appliedRate;
	}

	public BudgetDecimal getSalaryRequested() {
		return salaryRequested;
	}

	public void setSalaryRequested(BudgetDecimal salaryRequested) {
		this.salaryRequested = salaryRequested;
	}

	public BudgetDecimal getCalculatedCost() {
		return calculatedCost;
	}

	public void setCalculatedCost(BudgetDecimal calculatedCost) {
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

	public BudgetDecimal getCostSharingAmount() {
		return costSharingAmount;
	}

	public void setCostSharingAmount(BudgetDecimal costSharingAmount) {
		this.costSharingAmount = costSharingAmount;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public BudgetDecimal getPercentEffort() {
		return percentEffort;
	}

	public void setPercentEffort(BudgetDecimal percentEffort) {
		this.percentEffort = percentEffort;
	}

	public BudgetDecimal getPercentCharged() {
		return percentCharged;
	}

	public void setPercentCharged(BudgetDecimal percentCharged) {
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

	public BudgetDecimal getFringe() {
		return fringe;
	}

	public void setFringe(BudgetDecimal fringe) {
		this.fringe = fringe;
	}

	public BudgetDecimal getVacationRate() {
		return vacationRate;
	}

	public void setVacationRate(BudgetDecimal vacationRate) {
		this.vacationRate = vacationRate;
	}

	public BudgetDecimal getEmployeeBenefitRate() {
		return employeeBenefitRate;
	}

	public void setEmployeeBenefitRate(BudgetDecimal employeeBenefitRate) {
		this.employeeBenefitRate = employeeBenefitRate;
	}
}
