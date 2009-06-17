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
package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.AbstractInstituteRate;
import org.kuali.kra.budget.BudgetDecimal;
public abstract class AbstractBudgetRate extends AbstractInstituteRate {
	private String proposalNumber;
	private Integer budgetVersionNumber;
	private BudgetDecimal applicableRate;
    private BudgetDecimal oldApplicableRate;
    private String viewLocation;
    private boolean displayLocation = true;
    private String budgetPeriod;
    private String affectedBudgetPeriod;
    private String trackAffectedPeriod;

    
    public AbstractBudgetRate() {
        super();
    }
    
    public AbstractBudgetRate(String unitNumber, AbstractInstituteRate abstractInstituteRate) {
        this();
        copyValues(unitNumber, abstractInstituteRate);
    }

    private void copyValues(String unitNumber, AbstractInstituteRate abstractInstituteRate) {
        setApplicableRate(abstractInstituteRate.getInstituteRate());
        setFiscalYear(abstractInstituteRate.getFiscalYear());
        setInstituteRate(abstractInstituteRate.getInstituteRate());
        setOnOffCampusFlag(abstractInstituteRate.getOnOffCampusFlag());
        setRateClass(abstractInstituteRate.getRateClass());
        setRateClassCode(abstractInstituteRate.getRateClassCode());
        setRateType(abstractInstituteRate.getRateType());
        setRateTypeCode(abstractInstituteRate.getRateTypeCode());
        setStartDate(abstractInstituteRate.getStartDate());
        setUnitNumber(unitNumber);
        setOldApplicableRate(abstractInstituteRate.getInstituteRate());
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

	public BudgetDecimal getApplicableRate() {
		return BudgetDecimal.returnZeroIfNull(applicableRate);
	}

    public BudgetDecimal getExactApplicableRate() {
        return applicableRate;
    }

    public boolean isApplicableRateNull() {
        return applicableRate == null;
    }

    public void setExactApplicableRate(BudgetDecimal applicableRate) {
        setApplicableRate(applicableRate);
    }
    
    public void setApplicableRate(BudgetDecimal applicableRate) {
        setOldApplicableRate(this.applicableRate);
        this.applicableRate = applicableRate;
	}

	@Override 
	@SuppressWarnings("unchecked")
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = super.toStringMapper();
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("budgetVersionNumber", getBudgetVersionNumber());
		hashMap.put("applicableRate", getApplicableRate());
		return hashMap;
	}

    public BudgetDecimal getOldApplicableRate() {
        return BudgetDecimal.returnZeroIfNull(oldApplicableRate);
    }

    public void setOldApplicableRate(BudgetDecimal oldApplicableRate) {
        this.oldApplicableRate = oldApplicableRate;
    }

    public String getViewLocation() {
        return viewLocation;
    }

    public void setViewLocation(String viewLocation) {
        this.viewLocation = viewLocation;
    }

    public boolean isDisplayLocation() {
        return displayLocation;
    }

    public void setDisplayLocation(boolean displayLocation) {
        this.displayLocation = displayLocation;
    }

    public String getBudgetPeriod() {
        return budgetPeriod;
    }

    public void setBudgetPeriod(String budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }

    public final String getAffectedBudgetPeriod() {
        return affectedBudgetPeriod;
    }

    public final void setAffectedBudgetPeriod(String affectedBudgetPeriod) {
        this.affectedBudgetPeriod = affectedBudgetPeriod;
    }

    public final String getTrackAffectedPeriod() {
        return trackAffectedPeriod;
    }

    public final void setTrackAffectedPeriod(String trackAffectedPeriod) {
        this.trackAffectedPeriod = trackAffectedPeriod;
    }
}
