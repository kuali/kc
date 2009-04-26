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

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.service.BudgetService;
import org.kuali.kra.infrastructure.KraServiceLocator;

public abstract class BudgetLineItemBase extends KraPersistableBusinessObjectBase {
    private Long budgetPeriodId;
    
    private Integer budgetPeriod;
	private Integer lineItemNumber;
	private String proposalNumber;
	private Integer budgetVersionNumber;
	private Boolean applyInRateFlag;
	private String budgetJustification;
	private String costElement;
	private BudgetDecimal costSharingAmount = BudgetDecimal.ZERO;
	private Date endDate;
	private BudgetDecimal lineItemCost = BudgetDecimal.ZERO;
	private String lineItemDescription;
	private Boolean onOffCampusFlag;
	private Date startDate;
	private BudgetDecimal underrecoveryAmount = BudgetDecimal.ZERO;
	
    private String budgetCategoryCode;
    private Integer basedOnLineItem;
    private Integer quantity;
	private BudgetDecimal directCost;
	private BudgetDecimal indirectCost;
    private BudgetCategory budgetCategory;
    private Integer lineItemSequence;
	
	private CostElement costElementBO;

    private BudgetDecimal totalCostSharingAmount;
    private boolean validToApplyInRate;

	public BudgetLineItemBase(){
	}
	
	public abstract List getBudgetCalculatedAmounts();
    /**
     * Gets the directCost attribute. 
     * @return Returns the directCost.
     */
    public BudgetDecimal getDirectCost() {
        return BudgetDecimal.returnZeroIfNull(directCost);
    }

    /**
     * Sets the directCost attribute value.
     * @param directCost The directCost to set.
     */
    public void setDirectCost(BudgetDecimal directCost) {
        this.directCost = directCost;
    }

    /**
     * Gets the indirectCost attribute. 
     * @return Returns the indirectCost.
     */
    public BudgetDecimal getIndirectCost() {
        return BudgetDecimal.returnZeroIfNull(indirectCost);
    }

    /**
     * Sets the indirectCost attribute value.
     * @param indirectCost The indirectCost to set.
     */
    public void setIndirectCost(BudgetDecimal indirectCost) {
        this.indirectCost = indirectCost;
    }

    public Integer getBudgetPeriod() {
		return budgetPeriod;
	}

	public void setBudgetPeriod(Integer budgetPeriod) {
		this.budgetPeriod = budgetPeriod;
	}

	public Integer getLineItemNumber() {
		return lineItemNumber;
	}

	public void setLineItemNumber(Integer lineItemNumber) {
		this.lineItemNumber = lineItemNumber;
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

	public Boolean getApplyInRateFlag() {
		return applyInRateFlag;
	}

	public void setApplyInRateFlag(Boolean applyInRateFlag) {
		this.applyInRateFlag = applyInRateFlag;
	}

	public Integer getBasedOnLineItem() {
		return basedOnLineItem;
	}

	public void setBasedOnLineItem(Integer basedOnLineItem) {
		this.basedOnLineItem = basedOnLineItem;
	}

	public String getBudgetCategoryCode() {
		return budgetCategoryCode;
	}

	public void setBudgetCategoryCode(String budgetCategoryCode) {
		this.budgetCategoryCode = budgetCategoryCode;
	}

	public String getBudgetJustification() {
		return budgetJustification;
	}

	public void setBudgetJustification(String budgetJustification) {
		this.budgetJustification = budgetJustification;
	}

	public String getCostElement() {
		return costElement;
	}

	public void setCostElement(String costElement) {
		this.costElement = costElement;
	}

	public BudgetDecimal getCostSharingAmount() {
		return BudgetDecimal.returnZeroIfNull(costSharingAmount);
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

	public BudgetDecimal getLineItemCost() {
		return BudgetDecimal.returnZeroIfNull(lineItemCost);
	}

	public void setLineItemCost(BudgetDecimal lineItemCost) {
		this.lineItemCost = lineItemCost;
	}

	public String getLineItemDescription() {
		return lineItemDescription;
	}

	public void setLineItemDescription(String lineItemDescription) {
		this.lineItemDescription = lineItemDescription;
	}

	public Integer getLineItemSequence() {
		return lineItemSequence;
	}

	public void setLineItemSequence(Integer lineItemSequence) {
		this.lineItemSequence = lineItemSequence;
	}

	public Boolean getOnOffCampusFlag() {
		return onOffCampusFlag;
	}

	public void setOnOffCampusFlag(Boolean onOffCampusFlag) {
		this.onOffCampusFlag = onOffCampusFlag;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public BudgetDecimal getUnderrecoveryAmount() {
		return BudgetDecimal.returnZeroIfNull(underrecoveryAmount);
	}

	public void setUnderrecoveryAmount(BudgetDecimal underrecoveryAmount) {
		this.underrecoveryAmount = underrecoveryAmount;
	}


	@Override
	@SuppressWarnings("unchecked")
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("budgetPeriod", getBudgetPeriod());
		hashMap.put("lineItemNumber", getLineItemNumber());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("budgetVersionNumber", getBudgetVersionNumber());
		hashMap.put("applyInRateFlag", getApplyInRateFlag());
		hashMap.put("basedOnLineItem", getBasedOnLineItem());
		hashMap.put("budgetCategoryCode", getBudgetCategoryCode());
		hashMap.put("budgetJustification", getBudgetJustification());
		hashMap.put("costElement", getCostElement());
		hashMap.put("costSharingAmount", getCostSharingAmount());
		hashMap.put("endDate", getEndDate());
		hashMap.put("lineItemCost", getLineItemCost());
		hashMap.put("lineItemDescription", getLineItemDescription());
		hashMap.put("lineItemSequence", getLineItemSequence());
		hashMap.put("onOffCampusFlag", getOnOffCampusFlag());
		hashMap.put("quantity", getQuantity());
		hashMap.put("startDate", getStartDate());
		hashMap.put("underrecoveryAmount", getUnderrecoveryAmount());
        hashMap.put("directCost", getDirectCost());
        hashMap.put("indirectCost", getIndirectCost());
        return hashMap;
	}

    public BudgetCategory getBudgetCategory() {
        return budgetCategory;
    }

    public void setBudgetCategory(BudgetCategory budgetCategory) {
        this.budgetCategory = budgetCategory;
    }

    public CostElement getCostElementBO() {
        return costElementBO;
    }

    public void setCostElementBO(CostElement costElementBO) {
        this.costElementBO = costElementBO;
    }

    public BudgetDecimal getTotalCostSharingAmount() {
        return BudgetDecimal.returnZeroIfNull(totalCostSharingAmount);
    }

    public void setTotalCostSharingAmount(BudgetDecimal totalCostSharingAmount) {
        this.totalCostSharingAmount = totalCostSharingAmount;
    }

    public Long getBudgetPeriodId() {
        return budgetPeriodId;
    }

    public void setBudgetPeriodId(Long budgetPeriodId) {
        this.budgetPeriodId = budgetPeriodId;
    }
    
    public boolean isValidToApplyInRate() {
        return KraServiceLocator.getService(BudgetService.class).ValidInflationCeRate(this);
    }
    public void setValidToApplyInRate(boolean validToApplyInRate) {
        this.validToApplyInRate = validToApplyInRate;
    }

}
