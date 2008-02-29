/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.budget.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class BudgetPerson extends KraPersistableBusinessObjectBase {
    
	private Date effectiveDate;
	private String jobCode;
	private String nonEmployeeFlag;
	private String personId;
	private String proposalNumber;
	private Integer budgetVersionNumber;
	private String appointmentType;
	private KualiDecimal calculationBase;
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

	public KualiDecimal getCalculationBase() {
		return calculationBase;
	}

	public void setCalculationBase(KualiDecimal calculationBase) {
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
		LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
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
