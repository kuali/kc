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

import java.util.LinkedHashMap;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;

@MappedSuperclass
public abstract class AbstractBudgetRateAndBase extends KraPersistableBusinessObjectBase {
    
    @Id
    @Column(name="BUDGET_PERIOD_NUMBER")
    private Long budgetPeriodId;

    @Id
    @Column(name="LINE_ITEM_NUMBER")
	private Integer lineItemNumber;
    
	@Id
	@Column(name="RATE_CLASS_CODE")
	private String rateClassCode;
	
	@Id
	@Column(name="RATE_NUMBER")
	private Integer rateNumber;
	
	@Id
	@Column(name="RATE_TYPE_CODE")
	private String rateTypeCode;
	
	@Column(name="PROPOSAL_NUMBER")
	private String proposalNumber;
	
	@Column(name="VERSION_NUMBER")
	private Integer budgetVersionNumber;
	
	@Column(name="BUDGET_PERIOD")
	private Integer budgetPeriod;
	
	@Column(name="APPLIED_RATE")
	private BudgetDecimal appliedRate;
	
	@Column(name="BASE_COST_SHARING")
	private BudgetDecimal baseCostSharing;
	
	@Column(name="CALCULATED_COST")
	private BudgetDecimal calculatedCost;
	
	@Column(name="CALCULATED_COST_SHARING")
	private BudgetDecimal calculatedCostSharing;
	
	@Column(name="END_DATE")
	private Date endDate;
	
	@Column(name="ON_OFF_CAMPUS_FLAG")
	private Boolean onOffCampusFlag;
	
	@Column(name="START_DATE")
	private Date startDate;
	
    private RateClass rateClass;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinColumns({@JoinColumn(name = "BUDGET_PERIOD_NUMBER", insertable = false, updatable = false), 
                  @JoinColumn(name="LINE_ITEM_NUMBER", insertable = false, updatable = false)})
    private BudgetLineItem budgetLineItem;

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

	public String getRateClassCode() {
		return rateClassCode;
	}

	public void setRateClassCode(String rateClassCode) {
		this.rateClassCode = rateClassCode;
	}

	public Integer getRateNumber() {
		return rateNumber;
	}

	public void setRateNumber(Integer rateNumber) {
		this.rateNumber = rateNumber;
	}

	public String getRateTypeCode() {
		return rateTypeCode;
	}

	public void setRateTypeCode(String rateTypeCode) {
		this.rateTypeCode = rateTypeCode;
	}

	public Integer getBudgetVersionNumber() {
		return budgetVersionNumber;
	}

	public void setBudgetVersionNumber(Integer budgetVersionNumber) {
		this.budgetVersionNumber = budgetVersionNumber;
	}

	public BudgetDecimal getAppliedRate() {
		return BudgetDecimal.returnZeroIfNull(appliedRate);
	}

	public void setAppliedRate(BudgetDecimal appliedRate) {
		this.appliedRate = appliedRate;
	}

	public BudgetDecimal getBaseCostSharing() {
		return BudgetDecimal.returnZeroIfNull(baseCostSharing);
	}

	public void setBaseCostSharing(BudgetDecimal baseCostSharing) {
		this.baseCostSharing = baseCostSharing;
	}

	public BudgetDecimal getCalculatedCost() {
		return calculatedCost;
	}

	public void setCalculatedCost(BudgetDecimal calculatedCost) {
		this.calculatedCost = calculatedCost;
	}

	public BudgetDecimal getCalculatedCostSharing() {
		return calculatedCostSharing;
	}

	public void setCalculatedCostSharing(BudgetDecimal calculatedCostSharing) {
		this.calculatedCostSharing = calculatedCostSharing;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getOnOffCampusFlag() {
		return onOffCampusFlag;
	}

	public void setOnOffCampusFlag(Boolean onOffCampusFlag) {
		this.onOffCampusFlag = onOffCampusFlag;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
    
	public RateClass getRateClass() {
        return rateClass;
    }

    public void setRateClass(RateClass rateClass) {
        this.rateClass = rateClass;
    }

    public BudgetLineItem getBudgetLineItem() {
        return budgetLineItem;
    }

    public void setBudgetLineItem(BudgetLineItem budgetLineItem) {
        this.budgetLineItem = budgetLineItem;
    }

    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("budgetPeriod", getBudgetPeriod());
		hashMap.put("lineItemNumber", getLineItemNumber());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("rateClassCode", getRateClassCode());
		hashMap.put("rateNumber", getRateNumber());
		hashMap.put("rateTypeCode", getRateTypeCode());
		hashMap.put("budgetVersionNumber", getBudgetVersionNumber());
		hashMap.put("appliedRate", getAppliedRate());
		hashMap.put("baseCostSharing", getBaseCostSharing());
		hashMap.put("calculatedCost", getCalculatedCost());
		hashMap.put("calculatedCostSharing", getCalculatedCostSharing());
		hashMap.put("endDate", getEndDate());
		hashMap.put("onOffCampusFlag", getOnOffCampusFlag());
		hashMap.put("startDate", getStartDate());
		return hashMap;
	}

    /**
     * Gets the budgetPeriodId attribute. 
     * @return Returns the budgetPeriodId.
     */
    public Long getBudgetPeriodId() {
        return budgetPeriodId;
    }

    /**
     * Sets the budgetPeriodId attribute value.
     * @param budgetPeriodId The budgetPeriodId to set.
     */
    public void setBudgetPeriodId(Long budgetPeriodId) {
        this.budgetPeriodId = budgetPeriodId;
    }
}
