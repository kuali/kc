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

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;

public class BudgetLineItemCalculatedAmount extends KraPersistableBusinessObjectBase {
	private Integer budgetPeriod;
	private Integer lineItemNumber;
	private String proposalNumber;
	private String rateClassCode;
	private String rateTypeCode;
	private Integer budgetVersionNumber;
	private Boolean applyRateFlag;
	private BudgetDecimal calculatedCost;
	private BudgetDecimal calculatedCostSharing;
	private String rateClassType;
	private Integer rateNumber;
	private RateClass rateClass;
	private RateType rateType;

	/**
     * Gets the rateClass attribute. 
     * @return Returns the rateClass.
     */
    public RateClass getRateClass() {
        return rateClass;
    }

    /**
     * Sets the rateClass attribute value.
     * @param rateClass The rateClass to set.
     */
    public void setRateClass(RateClass rateClass) {
        this.rateClass = rateClass;
    }

    /**
     * Gets the rateType attribute. 
     * @return Returns the rateType.
     */
    public RateType getRateType() {
        return rateType;
    }

    /**
     * Sets the rateType attribute value.
     * @param rateType The rateType to set.
     */
    public void setRateType(RateType rateType) {
        this.rateType = rateType;
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

	public String getRateClassCode() {
		return rateClassCode;
	}

	public void setRateClassCode(String rateClassCode) {
		this.rateClassCode = rateClassCode;
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

	public Boolean getApplyRateFlag() {
		return applyRateFlag;
	}

    public void setApplyRateFlag(Boolean applyRateFlag) {
		this.applyRateFlag = applyRateFlag;
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


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("budgetPeriod", getBudgetPeriod());
		hashMap.put("lineItemNumber", getLineItemNumber());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("rateClassCode", getRateClassCode());
		hashMap.put("rateTypeCode", getRateTypeCode());
		hashMap.put("versionNumber", getVersionNumber());
		hashMap.put("applyRateFlag", getApplyRateFlag());
		hashMap.put("calculatedCost", getCalculatedCost());
		hashMap.put("calculatedCostSharing", getCalculatedCostSharing());
		return hashMap;
	}

    /**
     * Gets the rateClassType attribute. 
     * @return Returns the rateClassType.
     */
    public String getRateClassType() {
        return rateClassType;
    }

    /**
     * Sets the rateClassType attribute value.
     * @param rateClassType The rateClassType to set.
     */
    public void setRateClassType(String rateClassType) {
        this.rateClassType = rateClassType;
    }

    /**
     * Gets the rateNumber attribute. 
     * @return Returns the rateNumber.
     */
    public Integer getRateNumber() {
        return rateNumber;
    }

    /**
     * Sets the rateNumber attribute value.
     * @param rateNumber The rateNumber to set.
     */
    public void setRateNumber(Integer rateNumber) {
        this.rateNumber = rateNumber;
    }

}
