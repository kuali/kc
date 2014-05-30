/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.budget.framework.nonpersonnel;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.propdev.impl.hierarchy.HierarchyMaintainable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class BudgetLineItem extends BudgetLineItemBase implements HierarchyMaintainable, Comparable<BudgetLineItem> {

    private List<BudgetLineItemCalculatedAmount> budgetLineItemCalculatedAmounts;

    private List<BudgetPersonnelDetails> budgetPersonnelDetailsList;

    private boolean budgetPersonnelLineItemDeleted;

    private List<BudgetRateAndBase> budgetRateAndBaseList;

    private Date oldStartDate;

    private Date oldEndDate;
    
    private Integer subAwardNumber;

    private String hierarchyProposalNumber;

    private boolean hiddenInHierarchy;
    
    private transient boolean displayTotalDetail;
    private transient ScaleTwoDecimal objectTotal;

    public BudgetLineItem() {
        super();
        budgetPersonnelDetailsList = new ArrayList<BudgetPersonnelDetails>();
        budgetLineItemCalculatedAmounts = new ArrayList<BudgetLineItemCalculatedAmount>();
        budgetRateAndBaseList = new ArrayList<BudgetRateAndBase>();
        displayTotalDetail = false;
        objectTotal = new ScaleTwoDecimal(0);
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
            getBudgetPersonnelDetailsList().add(getNewBudgetPersonnelLineItem());
        }
        return getBudgetPersonnelDetailsList().get(index);
    }

    /**
     * 
     * This method is to create new BudgetpersonnelDetails object
     * @return
     */
    public BudgetPersonnelDetails getNewBudgetPersonnelLineItem() {
        return new BudgetPersonnelDetails();
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

    /**
     * Sets the hierarchyProposalNumber attribute value.
     * @param hierarchyProposalNumber The hierarchyProposalNumber to set.
     */
    public void setHierarchyProposalNumber(String hierarchyProposalNumber) {
        this.hierarchyProposalNumber = hierarchyProposalNumber;
    }

    /**
     * Gets the hierarchyProposalNumber attribute. 
     * @return Returns the hierarchyProposalNumber.
     */
    public String getHierarchyProposalNumber() {
        return hierarchyProposalNumber;
    }

    /**
     * Gets the hiddenInHierarchy attribute. 
     * @return Returns the hiddenInHierarchy.
     */
    public boolean isHiddenInHierarchy() {
        return hiddenInHierarchy;
    }

    /**
     * Sets the hiddenInHierarchy attribute value.
     * @param hiddenInHierarchy The hiddenInHierarchy to set.
     */
    public void setHiddenInHierarchy(boolean hiddenInHierarchy) {
        this.hiddenInHierarchy = hiddenInHierarchy;
    }

    public boolean isDisplayTotalDetail() {
        return displayTotalDetail;
    }

    public void setDisplayTotalDetail(boolean displayTotalDetail) {
        this.displayTotalDetail = displayTotalDetail;
    }

    public ScaleTwoDecimal getObjectTotal() {
        return objectTotal;
    }

    public void setObjectTotal(ScaleTwoDecimal objectTotal) {
        this.objectTotal = objectTotal;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBudgetPeriodId() == null) ? 0 : getBudgetPeriodId().hashCode());
        result = prime * result + ((getLineItemNumber() == null) ? 0 : getLineItemNumber().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        BudgetLineItem other = (BudgetLineItem) obj;
        if (getBudgetPeriodId() == null) {
            if (other.getBudgetPeriodId() != null) return false;
        } else if (!getBudgetPeriodId().equals(other.getBudgetPeriodId())) return false;
        if (getLineItemNumber() == null) {
            if (other.getLineItemNumber() != null) return false;
        } else if (!getLineItemNumber().equals(other.getLineItemNumber())) return false;
        return true;
    }

    public AbstractBudgetCalculatedAmount getNewBudgetLineItemCalculatedAmount() {
        return new BudgetLineItemCalculatedAmount();
    }
    
    /**
     * Calculated comparable based on the cost element, then on the line item sequence.
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(BudgetLineItem o) {
        int compare = this.getCostElementName().compareTo(o.getCostElementName());
        if (compare == 0) {
            compare = this.getLineItemNumber().compareTo(o.getLineItemNumber());
        }

        return compare;
    }
    
    public boolean isSubAwardLineItem() {
        return subAwardNumber != null;
    }

    public Integer getSubAwardNumber() {
        return subAwardNumber;
    }

    public void setSubAwardNumber(Integer subAwardNumber) {
        this.subAwardNumber = subAwardNumber;
    }

}