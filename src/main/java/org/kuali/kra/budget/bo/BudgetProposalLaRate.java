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

import org.kuali.kra.bo.InstituteLaRate;
import org.kuali.kra.budget.BudgetDecimal;

public class BudgetProposalLaRate extends InstituteLaRate {
	private String proposalNumber;
	private Integer budgetVersionNumber;
	private BudgetDecimal applicableRate;
    private BudgetDecimal oldApplicableRate;

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

	public BudgetDecimal getApplicableRate() {
		return applicableRate;
	}

	public void setApplicableRate(BudgetDecimal applicableRate) {
		this.applicableRate = applicableRate;
		setOldApplicableRate(applicableRate);
	}

	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = super.toStringMapper();
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("budgetVersionNumber", getBudgetVersionNumber());
		hashMap.put("applicableRate", getApplicableRate());
		return hashMap;
	}

    public BudgetDecimal getOldApplicableRate() {
        return oldApplicableRate;
    }

    public void setOldApplicableRate(BudgetDecimal oldApplicableRate) {
        if(this.oldApplicableRate == null) {
            this.oldApplicableRate = oldApplicableRate;
        }
    }
}
