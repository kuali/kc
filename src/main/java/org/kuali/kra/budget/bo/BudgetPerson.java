package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;
import java.sql.Date;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class BudgetPerson extends KraPersistableBusinessObjectBase {
	private Date effectiveDate;
	private String jobCode;
	private String nonEmployeeFlag;
	private String personId;
	private String proposalNumber;
	private Integer budgetVersionNumber;
	private String appointmentType;
	private Double calculationBase;
	private String personName;

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getNonEmployeeFlag() {
		return nonEmployeeFlag;
	}

	public void setNonEmployeeFlag(String nonEmployeeFlag) {
		this.nonEmployeeFlag = nonEmployeeFlag;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public Integer getBudgetVersionNumber() {
		return budgetVersionNumber;
	}

	public void setBudgetVersionNumber(Integer budgetVersionNumber) {
		this.budgetVersionNumber = budgetVersionNumber;
	}

	public String getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}

	public Double getCalculationBase() {
		return calculationBase;
	}

	public void setCalculationBase(Double calculationBase) {
		this.calculationBase = calculationBase;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("effectiveDate", getEffectiveDate());
		hashMap.put("jobCode", getJobCode());
		hashMap.put("nonEmployeeFlag", getNonEmployeeFlag());
		hashMap.put("personId", getPersonId());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("budgetVersionNumber", getBudgetVersionNumber());
		hashMap.put("appointmentType", getAppointmentType());
		hashMap.put("calculationBase", getCalculationBase());
		hashMap.put("personName", getPersonName());
		return hashMap;
	}
}
