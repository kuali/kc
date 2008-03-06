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

import java.util.LinkedHashMap;
import java.util.List;
import java.sql.Date;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;

public class BudgetPersonnelDetails extends BudgetLineItemBase {
    
    private Integer personNumber;
	private BudgetDecimal costSharingPercent=BudgetDecimal.ZERO;
	private String jobCode;
    private Boolean nonEmployeeFlag;
	private BudgetDecimal percentCharged=BudgetDecimal.ZERO;
	private BudgetDecimal percentEffort=BudgetDecimal.ZERO;
	private String periodType;
	private String personId;
	private BudgetDecimal salaryRequested=BudgetDecimal.ZERO;
	private Integer sequenceNumber;
	private Integer personSequenceNumber;
	private BudgetPerson budgetPerson;

	public Integer getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(Integer personNumber) {
		this.personNumber = personNumber;
	}

	public BudgetDecimal getCostSharingPercent() {
		return costSharingPercent;
	}

	public void setCostSharingPercent(BudgetDecimal costSharingPercent) {
		this.costSharingPercent = costSharingPercent;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public BudgetDecimal getPercentCharged() {
		return percentCharged;
	}

	public void setPercentCharged(BudgetDecimal percentCharged) {
		this.percentCharged = percentCharged;
	}

	public BudgetDecimal getPercentEffort() {
		return percentEffort;
	}

	public void setPercentEffort(BudgetDecimal percentEffort) {
		this.percentEffort = percentEffort;
	}

	public String getPeriodType() {
		return periodType;
	}

	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public BudgetDecimal getSalaryRequested() {
		return salaryRequested;
	}

	public void setSalaryRequested(BudgetDecimal salaryRequested) {
		this.salaryRequested = salaryRequested;
	}

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = super.toStringMapper();
		hashMap.put("personNumber", getPersonNumber());
		hashMap.put("jobCode", getJobCode());
		hashMap.put("percentCharged", getPercentCharged());
		hashMap.put("percentEffort", getPercentEffort());
		hashMap.put("periodType", getPeriodType());
		hashMap.put("personId", getPersonId());
		hashMap.put("salaryRequested", getSalaryRequested());
		hashMap.put("sequenceNumber", getSequenceNumber());
		hashMap.put("startDate", getStartDate());
		hashMap.put("underrecoveryAmount", getUnderrecoveryAmount());
		return hashMap;
	}

    /**
     * Gets the nonEmployeeFlag attribute. 
     * @return Returns the nonEmployeeFlag.
     */
    public Boolean getNonEmployeeFlag() {
        return nonEmployeeFlag;
    }

    /**
     * Sets the nonEmployeeFlag attribute value.
     * @param nonEmployeeFlag The nonEmployeeFlag to set.
     */
    public void setNonEmployeeFlag(Boolean nonEmployeeFlag) {
        this.nonEmployeeFlag = nonEmployeeFlag;
    }

    /**
     * Gets the personSequenceNumber attribute. 
     * @return Returns the personSequenceNumber.
     */
    public Integer getPersonSequenceNumber() {
        return personSequenceNumber;
    }

    /**
     * Sets the personSequenceNumber attribute value.
     * @param personSequenceNumber The personSequenceNumber to set.
     */
    public void setPersonSequenceNumber(Integer personSequenceNumber) {
        this.personSequenceNumber = personSequenceNumber;
    }

    /**
     * Gets the budgetPerson attribute. 
     * @return Returns the budgetPerson.
     */
    public BudgetPerson getBudgetPerson() {
        return budgetPerson;
    }

    /**
     * Sets the budgetPerson attribute value.
     * @param budgetPerson The budgetPerson to set.
     */
    public void setBudgetPerson(BudgetPerson budgetPerson) {
        this.budgetPerson = budgetPerson;
    }

//    /**
//     * Gets the budgetPersonnelCalculatedAmounts attribute. 
//     * @return Returns the budgetPersonnelCalculatedAmounts.
//     */
//    public List<BudgetPersonnelCalculatedAmount> getBudgetPersonnelCalculatedAmounts() {
//        return budgetPersonnelCalculatedAmounts;
//    }
//
//    /**
//     * Sets the budgetPersonnelCalculatedAmounts attribute value.
//     * @param budgetPersonnelCalculatedAmounts The budgetPersonnelCalculatedAmounts to set.
//     */
//    public void setBudgetPersonnelCalculatedAmounts(List<BudgetPersonnelCalculatedAmount> budgetPersonnelCalculatedAmounts) {
//        this.budgetPersonnelCalculatedAmounts = budgetPersonnelCalculatedAmounts;
//    }
}
