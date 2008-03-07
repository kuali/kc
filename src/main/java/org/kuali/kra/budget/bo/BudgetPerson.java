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
import java.sql.Date;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.budget.BudgetDecimal;

public class BudgetPerson extends KraPersistableBusinessObjectBase {
	private Date effectiveDate;
	private String jobCode;
	private Boolean nonEmployeeFlag;
	private String personId;
    private Integer rolodexId;
	private String proposalNumber;
	private Integer budgetVersionNumber;
	private String appointmentTypeCode;
	private BudgetDecimal calculationBase;
	private String personName;
	private AppointmentType appointmentType;
	private Integer personSequenceNumber;
	private Person person;
	private Rolodex rolodex;

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/*
	 * Helper method for calculator. This is used for sorting and filtering after combining with rates purpose
	 */
    public Date getStartDate() {
        return effectiveDate;
    }
    
	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public Boolean getNonEmployeeFlag() {
		return nonEmployeeFlag;
	}

	public void setNonEmployeeFlag(Boolean nonEmployeeFlag) {
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

	public AppointmentType getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(AppointmentType appointmentType) {
		this.appointmentType = appointmentType;
	}

	public BudgetDecimal getCalculationBase() {
		return calculationBase;
	}

	public void setCalculationBase(BudgetDecimal calculationBase) {
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
		hashMap.put("appointmentTypeCode", getAppointmentTypeCode());
		hashMap.put("calculationBase", getCalculationBase());
		hashMap.put("personName", getPersonName());
		return hashMap;
	}

    /**
     * Gets the appointmentTypeCode attribute. 
     * @return Returns the appointmentTypeCode.
     */
    public String getAppointmentTypeCode() {
        return appointmentTypeCode;
    }

    /**
     * Sets the appointmentTypeCode attribute value.
     * @param appointmentTypeCode The appointmentTypeCode to set.
     */
    public void setAppointmentTypeCode(String appointmentTypeCode) {
        this.appointmentTypeCode = appointmentTypeCode;
    }

    /**
     * Gets the rolodexId attribute. 
     * @return Returns the rolodexId.
     */
    public Integer getRolodexId() {
        return rolodexId;
    }

    /**
     * Sets the rolodexId attribute value.
     * @param rolodexId The rolodexId to set.
     */
    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    /**
     * Gets the person attribute. 
     * @return Returns the person.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Sets the person attribute value.
     * @param person The person to set.
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Gets the roldex attribute. 
     * @return Returns the roldex.
     */
    public Rolodex getRoldex() {
        return rolodex;
    }

    /**
     * Sets the rolodex attribute value.
     * @param rolodex The rolodex to set.
     */
    public void setRoldex(Rolodex rolodex) {
        this.rolodex = rolodex;
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
     * Gets the rolodex attribute. 
     * @return Returns the rolodex.
     */
    public Rolodex getRolodex() {
        return rolodex;
    }

    /**
     * Sets the rolodex attribute value.
     * @param rolodex The rolodex to set.
     */
    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }
}
