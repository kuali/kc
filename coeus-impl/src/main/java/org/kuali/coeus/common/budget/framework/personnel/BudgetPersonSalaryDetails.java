/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.budget.framework.personnel;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "BUDGET_PERSON_SALARY_DETAILS")
public class BudgetPersonSalaryDetails extends KcPersistableBusinessObjectBase {
    
    @PortableSequenceGenerator(name = "SEQ_BUDGET_PER_SAL_DET_ID")
    @GeneratedValue(generator = "SEQ_BUDGET_PER_SAL_DET_ID")
    @Id
    @Column(name = "BUDGET_PERSON_SALARY_DETAIL_ID")
    private Long budgetPersonSalaryDetailId;  
    
    @Column(name = "PERSON_SEQUENCE_NUMBER")
    private Integer personSequenceNumber;
    
    @Column(name = "BUDGET_ID")
    private Long budgetId;
    
    @Column(name = "BUDGET_PERIOD")
    private Integer budgetPeriod;
    
    @Column(name = "PERSON_ID")
    private String personId;
    
    @Column(name = "BASE_SALARY")
    private ScaleTwoDecimal baseSalary = ScaleTwoDecimal.ZERO;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumns({ @JoinColumn(name = "BUDGET_ID", referencedColumnName = "BUDGET_ID", insertable = false, updatable = false), 
    	@JoinColumn(name = "PERSON_SEQUENCE_NUMBER", referencedColumnName = "PERSON_SEQUENCE_NUMBER", insertable = false, updatable = false) })
    private BudgetPerson budgetPerson;

    public Long getBudgetPersonSalaryDetailId() {
        return budgetPersonSalaryDetailId;
    }

    public void setBudgetPersonSalaryDetailId(Long budgetPersonSalaryDetailId) {
        this.budgetPersonSalaryDetailId = budgetPersonSalaryDetailId;
    }

    public Integer getPersonSequenceNumber() {
        return personSequenceNumber;
    }

    public void setPersonSequenceNumber(Integer personSequenceNumber) {
        this.personSequenceNumber = personSequenceNumber;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    public Integer getBudgetPeriod() {
        return budgetPeriod;
    }

    public void setBudgetPeriod(Integer budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }

    public ScaleTwoDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(ScaleTwoDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonId() {
        return personId;
    }

	public BudgetPerson getBudgetPerson() {
		return budgetPerson;
	}

	public void setBudgetPerson(BudgetPerson budgetPerson) {
		this.budgetPerson = budgetPerson;
	}

}
