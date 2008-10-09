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

import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.persistence.FetchType;
import javax.persistence.Basic;
import javax.persistence.Lob;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@IdClass(org.kuali.kra.budget.bo.id.BudgetLineItemId.class)
@Entity
@Table(name="BUDGET_DETAILS")
public class BudgetLineItem extends BudgetLineItemBase {
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.budget.bo.BudgetLineItemCalculatedAmount.class, mappedBy="budgetLineItem")
	private List<BudgetLineItemCalculatedAmount> budgetLineItemCalculatedAmounts;
    
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.budget.bo.BudgetPersonnelDetails.class, mappedBy="budgetLineItem")
	private List<BudgetPersonnelDetails> budgetPersonnelDetailsList;
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.budget.bo.BudgetRateAndBase.class, mappedBy="budgetLineItem")
	private List<BudgetRateAndBase> budgetRateAndBaseList;

	@Transient
    private Date oldStartDate;
	
	@Transient
    private Date oldEndDate;
    
	@Transient
    private boolean budgetPersonnelLineItemDeleted;
	
	public BudgetLineItem(){
	    super();
	    budgetPersonnelDetailsList = new ArrayList<BudgetPersonnelDetails>();
	    budgetLineItemCalculatedAmounts = new ArrayList<BudgetLineItemCalculatedAmount>();
	    budgetRateAndBaseList = new ArrayList<BudgetRateAndBase>();
	}
	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = super.toStringMapper();
		hashMap.put("budgetPersonnelDetailsList", getBudgetPersonnelDetailsList());
        hashMap.put("budgetLineItemCalculatedAmounts",getBudgetLineItemCalculatedAmounts());
		return hashMap;
	}

    public List<BudgetPersonnelDetails> getBudgetPersonnelDetailsList() {
        return budgetPersonnelDetailsList;
    }

    public void setBudgetPersonnelDetailsList(List<BudgetPersonnelDetails> budgetPersonnelDetailsList) {
        this.budgetPersonnelDetailsList = budgetPersonnelDetailsList;
    }

    /**
     * Gets BudgetPersonnelDetails from BudgetPersonnelDetails list at index.
     * 
     * @param index
     * @return BudgetPersonnelDetails at index
     */
    public BudgetPersonnelDetails getBudgetPersonnelDetails(int index) {
        while (getBudgetPersonnelDetailsList().size() <= index) {
            getBudgetPersonnelDetailsList().add(new BudgetPersonnelDetails());
        }
        return getBudgetPersonnelDetailsList().get(index);
    }
    /**
     * Gets the budgetLineItemCalculatedAmounts attribute. 
     * @return Returns the budgetLineItemCalculatedAmounts.
     */
    public List<BudgetLineItemCalculatedAmount> getBudgetLineItemCalculatedAmounts() {
        return budgetLineItemCalculatedAmounts;
    }
    /**
     * Sets the budgetLineItemCalculatedAmounts attribute value.
     * @param budgetLineItemCalculatedAmounts The budgetLineItemCalculatedAmounts to set.
     */
    public void setBudgetLineItemCalculatedAmounts(List<BudgetLineItemCalculatedAmount> budgetLineItemCalculatedAmounts) {
        this.budgetLineItemCalculatedAmounts = budgetLineItemCalculatedAmounts;
    }
    @Override
    public List getBudgetCalculatedAmounts() {
        return getBudgetLineItemCalculatedAmounts();
    }
    /**
     * Gets the budgetPersonnelLineItemDeleted attribute. 
     * @return Returns the budgetPersonnelLineItemDeleted.
     */
    public boolean isBudgetPersonnelLineItemDeleted() {
        return budgetPersonnelLineItemDeleted;
    }
    /**
     * Sets the budgetPersonnelLineItemDeleted attribute value.
     * @param budgetPersonnelLineItemDeleted The budgetPersonnelLineItemDeleted to set.
     */
    public void setBudgetPersonnelLineItemDeleted(boolean budgetPersonnelLineItemDeleted) {
        this.budgetPersonnelLineItemDeleted = budgetPersonnelLineItemDeleted;
    }
    /**
     * Gets the budgetRateAndBaseList attribute. 
     * @return Returns the budgetRateAndBaseList.
     */
    public List<BudgetRateAndBase> getBudgetRateAndBaseList() {
        return budgetRateAndBaseList;
    }
    /**
     * Sets the budgetRateAndBaseList attribute value.
     * @param budgetRateAndBaseList The budgetRateAndBaseList to set.
     */
    public void setBudgetRateAndBaseList(List<BudgetRateAndBase> budgetRateAndBaseList) {
        this.budgetRateAndBaseList = budgetRateAndBaseList;
    }
    public Date getOldStartDate() {
        return oldStartDate;
    }
    public void setOldStartDate(Date oldStartDate) {
        this.oldStartDate = oldStartDate;
    }
    public Date getOldEndDate() {
        return oldEndDate;
    }
    public void setOldEndDate(Date oldEndDate) {
        this.oldEndDate = oldEndDate;
    }

}

