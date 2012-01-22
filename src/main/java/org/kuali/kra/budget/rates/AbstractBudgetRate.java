/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.budget.rates;

import org.apache.commons.lang.ObjectUtils;
import org.kuali.kra.bo.AbstractInstituteRate;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.RateClassType;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetAssociateInterface;

public abstract class AbstractBudgetRate extends AbstractInstituteRate implements BudgetAssociateInterface {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -7152006670699620080L;

    private Long budgetId;

    private Budget budget;

    private BudgetDecimal applicableRate;

    private BudgetDecimal oldApplicableRate;

    private boolean rateChanged;

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
        setNonEditableRateFlag(abstractInstituteRate.getNonEditableRateFlag());
    }

    @Override
    public boolean getNonEditableRateFlag() {
        boolean nonEditableRate = false;
        if (getBudget() != null) {
            if (RateClassType.EMPLOYEE_BENEFITS.getRateClassType().equals(getRateClassType())) {
                nonEditableRate = getBudget().getEbRatesNonEditable();
            } else if (RateClassType.OVERHEAD.getRateClassType().equals(getRateClassType())) {
                nonEditableRate = getBudget().getOhRatesNonEditable();
            }
        }
        return nonEditableRate;
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
        if (!ObjectUtils.equals(this.applicableRate, applicableRate)) {
            setRateChanged(true);
        }
        this.applicableRate = applicableRate;
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

    /**
     * Gets the budget attribute. 
     * @return Returns the budget.
     */
    public Budget getBudget() {
        return budget;
    }

    /**
     * Sets the budget attribute value.
     * @param budget The budget to set.
     */
    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    /**
     * Gets the budgetId attribute. 
     * @return Returns the budgetId.
     */
    public Long getBudgetId() {
        return budgetId;
    }

    /**
     * Sets the budgetId attribute value.
     * @param budgetId The budgetId to set.
     */
    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((affectedBudgetPeriod == null) ? 0 : affectedBudgetPeriod.hashCode());
        result = prime * result + ((applicableRate == null) ? 0 : applicableRate.hashCode());
        result = prime * result + ((budget == null) ? 0 : budget.hashCode());
        result = prime * result + ((budgetId == null) ? 0 : budgetId.hashCode());
        result = prime * result + ((budgetPeriod == null) ? 0 : budgetPeriod.hashCode());
        result = prime * result + (displayLocation ? 1231 : 1237);
        result = prime * result + ((oldApplicableRate == null) ? 0 : oldApplicableRate.hashCode());
        result = prime * result + ((trackAffectedPeriod == null) ? 0 : trackAffectedPeriod.hashCode());
        result = prime * result + ((viewLocation == null) ? 0 : viewLocation.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        AbstractBudgetRate other = (AbstractBudgetRate) obj;
        if (affectedBudgetPeriod == null) {
            if (other.affectedBudgetPeriod != null) return false;
        } else if (!affectedBudgetPeriod.equals(other.affectedBudgetPeriod)) return false;
        if (applicableRate == null) {
            if (other.applicableRate != null) return false;
        } else if (!applicableRate.equals(other.applicableRate)) return false;
        if (budget == null) {
            if (other.budget != null) return false;
        } else if (!budget.equals(other.budget)) return false;
        if (budgetId == null) {
            if (other.budgetId != null) return false;
        } else if (!budgetId.equals(other.budgetId)) return false;
        if (budgetPeriod == null) {
            if (other.budgetPeriod != null) return false;
        } else if (!budgetPeriod.equals(other.budgetPeriod)) return false;
        if (displayLocation != other.displayLocation) return false;
        if (oldApplicableRate == null) {
            if (other.oldApplicableRate != null) return false;
        } else if (!oldApplicableRate.equals(other.oldApplicableRate)) return false;
        if (trackAffectedPeriod == null) {
            if (other.trackAffectedPeriod != null) return false;
        } else if (!trackAffectedPeriod.equals(other.trackAffectedPeriod)) return false;
        if (viewLocation == null) {
            if (other.viewLocation != null) return false;
        } else if (!viewLocation.equals(other.viewLocation)) return false;
        return true;
    }

    public boolean isRateChanged() {
        return rateChanged;
    }

    public void setRateChanged(boolean rateChanged) {
        this.rateChanged = rateChanged;
    }
}
