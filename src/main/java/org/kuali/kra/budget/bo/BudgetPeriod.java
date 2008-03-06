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

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;

public class BudgetPeriod extends KraPersistableBusinessObjectBase {
    private static final long serialVersionUID = -7318331486891820078L;
    
    private Integer budgetPeriod;
	private String proposalNumber;
	private Integer budgetVersionNumber;
	private String comments;
	private BudgetDecimal costSharingAmount;
	private Date endDate;
	private Date startDate;
	private BudgetDecimal totalCost;
	private BudgetDecimal totalCostLimit;
	private BudgetDecimal totalDirectCost;
	private BudgetDecimal totalIndirectCost;
	private BudgetDecimal underrecoveryAmount;
	private List<BudgetLineItem> budgetLineItems;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

	public BudgetPeriod(){
	    budgetLineItems = new ArrayList<BudgetLineItem>();
	}
	
	public Integer getBudgetPeriod() {
	    return budgetPeriod;
	}
	
	public String getLabel() {	    
        return new StringBuilder()
		            .append(budgetPeriod)
		            .append(": ")
		            .append(dateFormatter.format(startDate))
		            .append(" - ")
		            .append(dateFormatter.format(endDate))
		            .toString();
	}

	public void setBudgetPeriod(Integer budgetPeriod) {
		this.budgetPeriod = budgetPeriod;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public BudgetDecimal getCostSharingAmount() {
		return costSharingAmount == null ?  new BudgetDecimal(0) : costSharingAmount;
	}

	public void setCostSharingAmount(BudgetDecimal costSharingAmount) {
		this.costSharingAmount = costSharingAmount;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public BudgetDecimal getTotalCost() {
		return totalCost == null ?  new BudgetDecimal(0) : totalCost;
	}

	public void setTotalCost(BudgetDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public BudgetDecimal getTotalCostLimit() {
		return totalCostLimit == null ?  new BudgetDecimal(0) : totalCostLimit;
	}

	public void setTotalCostLimit(BudgetDecimal totalCostLimit) {
		this.totalCostLimit = totalCostLimit;
	}

	public BudgetDecimal getTotalDirectCost() {
		return totalDirectCost == null ?  new BudgetDecimal(0) : totalDirectCost;
	}

	public void setTotalDirectCost(BudgetDecimal totalDirectCost) {
		this.totalDirectCost = totalDirectCost;
	}

	public BudgetDecimal getTotalIndirectCost() {
		return totalIndirectCost == null ?  new BudgetDecimal(0) : totalIndirectCost;
	}

	public void setTotalIndirectCost(BudgetDecimal totalIndirectCost) {
		this.totalIndirectCost = totalIndirectCost;
	}

	public BudgetDecimal getUnderrecoveryAmount() {
		return underrecoveryAmount == null ?  new BudgetDecimal(0) : underrecoveryAmount;
	}

	public void setUnderrecoveryAmount(BudgetDecimal underrecoveryAmount) {
		this.underrecoveryAmount = underrecoveryAmount;
	}


	@SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("budgetPeriod", getBudgetPeriod());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("budgetBudgetVersionNumber", getBudgetVersionNumber());
		hashMap.put("comments", getComments());
		hashMap.put("costSharingAmount", getCostSharingAmount());
		hashMap.put("endDate", getEndDate());
		hashMap.put("startDate", getStartDate());
		hashMap.put("totalCost", getTotalCost());
		hashMap.put("totalCostLimit", getTotalCostLimit());
		hashMap.put("totalDirectCost", getTotalDirectCost());
		hashMap.put("totalIndirectCost", getTotalIndirectCost());
		hashMap.put("underrecoveryAmount", getUnderrecoveryAmount());
		return hashMap;
	}

    /**
     * Gets the budgetLineItems attribute. 
     * @return Returns the budgetLineItems.
     */
    public List<BudgetLineItem> getBudgetLineItems() {
        return budgetLineItems;
    }

    /**
     * Sets the budgetLineItems attribute value.
     * @param budgetLineItems The budgetLineItems to set.
     */
    public void setBudgetLineItems(List<BudgetLineItem> budgetLineItems) {
        this.budgetLineItems = budgetLineItems;
    }

    public boolean getBudgetLineItemStatus() {
        //return getBudgetSummaryService().budgetLineItemExists(getBudgetPeriod());
        boolean lineItemExists = false;
        for(BudgetLineItem periodLineItem: budgetLineItems) {
            Integer lineItemPeriod = periodLineItem.getBudgetPeriod();
            if(budgetPeriod == lineItemPeriod) {
                lineItemExists = true;
                break;
            }
        }
        return lineItemExists;
    }

}
