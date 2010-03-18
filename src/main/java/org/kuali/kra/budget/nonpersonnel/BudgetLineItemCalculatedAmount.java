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

import org.kuali.kra.infrastructure.DeepCopyIgnore;


public class BudgetLineItemCalculatedAmount extends AbstractBudgetCalculatedAmount {

    @DeepCopyIgnore
	private Long budgetLineItemCalculatedAmountId;
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -1755216989884993632L;
    
    public BudgetLineItemCalculatedAmount(){
        
    }

    @SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = super.toStringMapper();
		hashMap.put("budgetPeriod", getBudgetPeriod());
		hashMap.put("lineItemNumber", getLineItemNumber());
//		hashMap.put("proposalNumber", getProposalNumber());
//        hashMap.put("budgetVersionNumber", getBudgetVersionNumber());
		hashMap.put("rateClassCode", getRateClassCode());
		hashMap.put("rateTypeCode", getRateTypeCode());
		hashMap.put("versionNumber", getVersionNumber());
		hashMap.put("applyRateFlag", getApplyRateFlag());
		hashMap.put("calculatedCost", getCalculatedCost());
		hashMap.put("calculatedCostSharing", getCalculatedCostSharing());
		return hashMap;
	}


    /**
     * Gets the budgetLineItemCalculatedAmountId attribute. 
     * @return Returns the budgetLineItemCalculatedAmountId.
     */
    public Long getBudgetLineItemCalculatedAmountId() {
        return budgetLineItemCalculatedAmountId;
    }


    /**
     * Sets the budgetLineItemCalculatedAmountId attribute value.
     * @param budgetLineItemCalculatedAmountId The budgetLineItemCalculatedAmountId to set.
     */
    public void setBudgetLineItemCalculatedAmountId(Long budgetLineItemCalculatedAmountId) {
        this.budgetLineItemCalculatedAmountId = budgetLineItemCalculatedAmountId;
    }

}
