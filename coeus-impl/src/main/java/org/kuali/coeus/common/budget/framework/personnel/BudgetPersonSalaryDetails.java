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
    
    @Column(name = "PERSON_SEQUENCE_NUMBER", insertable = false, updatable = false)
    private Integer personSequenceNumber;
    
    @Column(name = "BUDGET_ID", insertable = false, updatable = false)
    private Long budgetId;
    
    @Column(name = "BUDGET_PERIOD")
    private Integer budgetPeriod;
    
    @Column(name = "PERSON_ID")
    private String personId;
    
    @Column(name = "BASE_SALARY")
    private ScaleTwoDecimal baseSalary = ScaleTwoDecimal.ZERO;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumns({ @JoinColumn(name = "BUDGET_ID", referencedColumnName = "BUDGET_ID"), 
    	@JoinColumn(name = "PERSON_SEQUENCE_NUMBER", referencedColumnName = "PERSON_SEQUENCE_NUMBER") })
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
