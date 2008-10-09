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

import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.util.LinkedHashMap;

@IdClass(org.kuali.kra.budget.bo.id.BudgetPersonnelCalculatedAmountId.class)
@Entity
@Table(name="BUDGET_PERSONNEL_CAL_AMTS")
public class BudgetPersonnelCalculatedAmount extends AbstractBudgetCalculatedAmount {
    
	@Id
	@Column(name="PERSON_NUMBER")
	private Integer personNumber;
	
//	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
//	@JoinColumns({@JoinColumn(name="BUDGET_PERIOD_NUMBER", insertable = false, updatable = false), 
//	              @JoinColumn(name="LINE_ITEM_NUMBER", insertable = false, updatable = false),
//	              @JoinColumn(name="PERSON_NUMBER", insertable=false, updatable=false)})
	private BudgetPersonnelDetails budgetPersonnelDetails;
	   

	public Integer getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(Integer personNumber) {
		this.personNumber = personNumber;
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
		return hashMap;
	}
}

