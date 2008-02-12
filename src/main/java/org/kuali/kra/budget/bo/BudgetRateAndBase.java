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

import java.util.LinkedHashMap;
import java.sql.Date;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;

public class BudgetRateAndBase extends KraPersistableBusinessObjectBase {
	private Integer budgetPeriod;
	private Integer lineItemNumber;
	private String proposalNumber;
	private String rateClassCode;
	private Integer rateNumber;
	private String rateTypeCode;
	private Integer budgetVersionNumber;
	private BudgetDecimal appliedRate;
	private BudgetDecimal baseCost;
	private BudgetDecimal baseCostSharing;
	private BudgetDecimal calculatedCost;
	private BudgetDecimal calculatedCostSharing;
	private Date endDate;
	private Boolean onOffCampusFlag;
	private Date startDate;

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
		return appliedRate;
	}

	public void setAppliedRate(BudgetDecimal appliedRate) {
		this.appliedRate = appliedRate;
	}

	public BudgetDecimal getBaseCost() {
		return baseCost;
	}

	public void setBaseCost(BudgetDecimal baseCost) {
		this.baseCost = baseCost;
	}

	public BudgetDecimal getBaseCostSharing() {
		return baseCostSharing;
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
		hashMap.put("baseCost", getBaseCost());
		hashMap.put("baseCostSharing", getBaseCostSharing());
		hashMap.put("calculatedCost", getCalculatedCost());
		hashMap.put("calculatedCostSharing", getCalculatedCostSharing());
		hashMap.put("endDate", getEndDate());
		hashMap.put("onOffCampusFlag", getOnOffCampusFlag());
		hashMap.put("startDate", getStartDate());
		return hashMap;
	}
}
