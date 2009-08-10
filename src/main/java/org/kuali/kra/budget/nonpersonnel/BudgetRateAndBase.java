/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.budget.nonpersonnel;

import java.util.LinkedHashMap;

import org.kuali.kra.budget.BudgetDecimal;

public class BudgetRateAndBase extends AbstractBudgetRateAndBase {
	/**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -6003003851261499575L;
    private BudgetDecimal baseCost;


	public BudgetDecimal getBaseCost() {
		return baseCost;
	}

	public void setBaseCost(BudgetDecimal baseCost) {
		this.baseCost = baseCost;
	}


	@SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = super.toStringMapper();
		hashMap.put("baseCost", getBaseCost());
		return hashMap;
	}

}
