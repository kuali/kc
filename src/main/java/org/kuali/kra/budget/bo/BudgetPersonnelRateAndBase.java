/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget.bo;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.util.LinkedHashMap;
import java.sql.Date;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;

@IdClass(org.kuali.kra.budget.bo.id.BudgetPersonnelRateAndBaseId.class)
@Entity
@Table(name="BUDGET_PER_DET_RATE_AND_BASE")
public class BudgetPersonnelRateAndBase extends AbstractBudgetRateAndBase {
    
    @Column(name="PERSON_ID")
	private String personId;
    
    @Id
	@Column(name="PERSON_NUMBER")
	private Integer personNumber;
    
    @Column(name="SALARY_REQUESTED")
	private BudgetDecimal salaryRequested;
    
//    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
//    @JoinColumns({@JoinColumn(name="BUDGET_PERIOD_NUMBER", insertable = false, updatable = false), 
//                  @JoinColumn(name="LINE_ITEM_NUMBER", insertable = false, updatable = false),
//                  @JoinColumn(name="PERSON_NUMBER", insertable=false, updatable=false)})
    private BudgetPersonnelDetails budgetPersonnelDetails;
    
    /**
     * Gets the salaryRequested attribute. 
     * @return Returns the salaryRequested.
     */
    public BudgetDecimal getSalaryRequested() {
        return salaryRequested;
    }
    /**
     * Sets the salaryRequested attribute value.
     * @param salaryRequested The salaryRequested to set.
     */
    public void setSalaryRequested(BudgetDecimal salaryRequested) {
        this.salaryRequested = salaryRequested;
    }
    
    public BudgetPersonnelDetails getBudgetPersonnelDetails() {
        return budgetPersonnelDetails;
    }
    public void setBudgetPersonnelDetails(BudgetPersonnelDetails budgetPersonnelDetails) {
        this.budgetPersonnelDetails = budgetPersonnelDetails;
    }
    
    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = super.toStringMapper();
        hashMap.put("personNumber", getPersonNumber());
        hashMap.put("personId", getPersonId());
        hashMap.put("salaryRequested", getSalaryRequested());
        return hashMap;
    }
    /**
     * Gets the personNumber attribute. 
     * @return Returns the personNumber.
     */
    public Integer getPersonNumber() {
        return personNumber;
    }
    /**
     * Sets the personNumber attribute value.
     * @param personNumber The personNumber to set.
     */
    public void setPersonNumber(Integer personNumber) {
        this.personNumber = personNumber;
    }
    /**
     * Gets the personId attribute. 
     * @return Returns the personId.
     */
    public String getPersonId() {
        return personId;
    }
    /**
     * Sets the personId attribute value.
     * @param personId The personId to set.
     */
    public void setPersonId(String personId) {
        this.personId = personId;
    }

}

