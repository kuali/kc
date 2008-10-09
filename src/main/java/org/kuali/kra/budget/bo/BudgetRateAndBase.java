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
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.util.LinkedHashMap;
import org.kuali.kra.budget.BudgetDecimal;

@IdClass(org.kuali.kra.budget.bo.id.BudgetRateAndBaseId.class)
@Entity
@Table(name="BUDGET_RATE_AND_BASE")
public class BudgetRateAndBase extends AbstractBudgetRateAndBase {
	@Column(name="BASE_COST")
	private BudgetDecimal baseCost;


	public BudgetDecimal getBaseCost() {
		return baseCost;
	}

	public void setBaseCost(BudgetDecimal baseCost) {
		this.baseCost = baseCost;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = super.toStringMapper();
		hashMap.put("baseCost", getBaseCost());
		return hashMap;
	}

}

