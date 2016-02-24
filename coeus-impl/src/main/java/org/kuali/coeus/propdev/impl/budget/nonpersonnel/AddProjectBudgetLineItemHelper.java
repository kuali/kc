/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.propdev.impl.budget.nonpersonnel;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.kuali.coeus.common.budget.api.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetFormulatedCostDetail;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.rate.AbstractBudgetRate;
import org.kuali.coeus.common.budget.framework.rate.ValidCeRateType;
import org.kuali.coeus.propdev.impl.core.AddLineHelper;

import java.util.ArrayList;
import java.util.List;


public class AddProjectBudgetLineItemHelper extends AddLineHelper {

    private String editLineIndex;
    private BudgetLineItem budgetLineItem;
    private BudgetPeriod currentTabBudgetPeriod;
    private String budgetCategoryTypeCode;
    private BudgetFormulatedCostDetail budgetFormulatedCostDetail;
    private String budgetFormulatedCostIndex;

    public AddProjectBudgetLineItemHelper() {
       super();
       initBudgetLineItemDetails();
    }
    
    public void reset() {
        super.reset();
        initBudgetLineItemDetails();
    }
    
    private void initBudgetLineItemDetails() {
        editLineIndex = null;
        budgetLineItem = new BudgetLineItem();
        currentTabBudgetPeriod = new BudgetPeriod();
        budgetCategoryTypeCode = "";
    }

	public String getEditLineIndex() {
		return editLineIndex;
	}

	public void setEditLineIndex(String editLineIndex) {
		this.editLineIndex = editLineIndex;
	}

	public BudgetLineItem getBudgetLineItem() {
		return budgetLineItem;
	}

	public void setBudgetLineItem(BudgetLineItem budgetLineItem) {
		this.budgetLineItem = budgetLineItem;
	}

	public BudgetPeriod getCurrentTabBudgetPeriod() {
		return currentTabBudgetPeriod;
	}

	public void setCurrentTabBudgetPeriod(BudgetPeriod currentTabBudgetPeriod) {
		this.currentTabBudgetPeriod = currentTabBudgetPeriod;
	}

	public String getBudgetCategoryTypeCode() {
		return budgetCategoryTypeCode;
	}

	public void setBudgetCategoryTypeCode(String budgetCategoryTypeCode) {
		this.budgetCategoryTypeCode = budgetCategoryTypeCode;
	}

    public List<AbstractBudgetRate> getInflationType() {
        List<AbstractBudgetRate> budgetRates = new ArrayList<AbstractBudgetRate>();
        if (budgetLineItem.getCostElementBO() != null) {
            String rateTypeCode = getInflationRateTypeCode(budgetLineItem.getCostElementBO().getValidCeRateTypes());
            if (budgetLineItem.getBudget() != null) {
                budgetRates.addAll(getInflationRates(budgetLineItem.getBudget().getAllBudgetRates(), rateTypeCode));
            }
        }
        return budgetRates;
    }

    protected String getInflationRateTypeCode(List<ValidCeRateType> validCeRateTypes) {
        for (ValidCeRateType validCeRateType : budgetLineItem.getCostElementBO().getValidCeRateTypes()) {
            if (StringUtils.equals(validCeRateType.getRateClassType(), RateClassType.INFLATION.getRateClassType())) {
                return validCeRateType.getRateTypeCode();
            }
        }
        return null;
    }

    protected List<AbstractBudgetRate> getInflationRates(List<AbstractBudgetRate> budgetRates, String rateTypeCode) {
        List<AbstractBudgetRate> inflationRates = new ArrayList<AbstractBudgetRate>();
        for (AbstractBudgetRate budgetRate : budgetLineItem.getBudget().getBudgetRates()) {
            DateTime budgetRateStartDate = new DateTime(budgetRate.getStartDate().getTime());
            DateTime budgetRateEndDate = budgetRateStartDate.plusYears(1).minusDays(1);
            Interval periodInterval = new Interval(budgetLineItem.getStartDate().getTime(), budgetLineItem.getEndDate().getTime());
            Interval rateInterval = new Interval(budgetRateStartDate.getMillis(),budgetRateEndDate.getMillis());
            if (StringUtils.equals(rateTypeCode, budgetRate.getRateTypeCode()) &&
                    StringUtils.equals(budgetRate.getRateClassType(),RateClassType.INFLATION.getRateClassType()) &&
                    budgetLineItem.getOnOffCampusFlag().equals(budgetRate.getOnOffCampusFlag()) &&
                    periodInterval.overlaps(rateInterval)) {
                inflationRates.add(budgetRate);
            }
        }
        return inflationRates;
    }

    public BudgetFormulatedCostDetail getBudgetFormulatedCostDetail() {
        return budgetFormulatedCostDetail;
    }

    public void setBudgetFormulatedCostDetail(BudgetFormulatedCostDetail budgetFormulatedCostDetail) {
        this.budgetFormulatedCostDetail = budgetFormulatedCostDetail;
    }

    public String getBudgetFormulatedCostIndex() {
        return budgetFormulatedCostIndex;
    }

    public void setBudgetFormulatedCostIndex(String budgetFormulatedCostIndex) {
        this.budgetFormulatedCostIndex = budgetFormulatedCostIndex;
    }
}
