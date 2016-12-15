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
package org.kuali.coeus.common.budget.impl.core;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.query.QueryList;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.rate.RateClass;
import org.kuali.coeus.common.budget.framework.rate.ValidCeRateType;
import org.kuali.coeus.common.budget.impl.calculator.SalaryCalculator;

import java.sql.Date;
import java.util.*;

public class BudgetInflationTest {
    public static final Date NOW = new Date(Calendar.getInstance().getTimeInMillis());
    MockSalaryCalculator salaryCalculator;
    BudgetPersonnelDetails personnelLineItem;

    public class MockSalaryCalculator extends SalaryCalculator {
        public MockSalaryCalculator(Budget budget, BudgetPersonnelDetails personnelLineItem) {
            super(budget, personnelLineItem);
        }

        @Override
        public QueryList<BudgetRate> getInflationRates() {
            QueryList<BudgetRate> inflationRates = new QueryList<>();
            BudgetRate budgetRate1 = new BudgetRate();
            budgetRate1.setRateClassCode("7");
            budgetRate1.setRateTypeCode("4");
            budgetRate1.setActivityTypeCode("1");
            budgetRate1.setStartDate(NOW);
            budgetRate1.setOnOffCampusFlag(true);
            inflationRates.add(budgetRate1);
            return inflationRates;
        }

        @Override
        protected QueryList<BudgetRate> filterInflationRates(CostElement costElement, boolean applyInflationRate) {
            return super.filterInflationRates(costElement, applyInflationRate);
        }
    }


    @Before
    public void setUp() throws Exception {
        personnelLineItem = new BudgetPersonnelDetails();
        personnelLineItem.setStartDate(NOW);
        personnelLineItem.setEndDate(NOW);
        salaryCalculator = new MockSalaryCalculator(new Budget(), personnelLineItem);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testBudgetInflation() {
        CostElement costElement = new CostElement();
        List<ValidCeRateType> validCeRateTypes1 = new ArrayList<>();

        ValidCeRateType inflationRate = getValidCeRate(org.kuali.coeus.common.budget.api.rate.RateClassType.INFLATION.getRateClassType());
        ValidCeRateType otherRate = getValidCeRate(org.kuali.coeus.common.budget.api.rate.RateClassType.OTHER.getRateClassType());
        validCeRateTypes1.add(inflationRate);
        validCeRateTypes1.add(otherRate);
        costElement.setValidCeRateTypes(validCeRateTypes1);
        costElement.setOnOffCampusFlag(true);
        QueryList<BudgetRate> rates = salaryCalculator.filterInflationRates(costElement, true);
        Assert.assertTrue(rates.size() == 1);

        rates = salaryCalculator.filterInflationRates(costElement, false);
        Assert.assertTrue(rates.size() == 0);

        List<ValidCeRateType> validCeRateTypes2 = new ArrayList<>();
        validCeRateTypes2.add(otherRate);
        validCeRateTypes2.add(otherRate);
        costElement.setValidCeRateTypes(validCeRateTypes2);
        costElement.setOnOffCampusFlag(true);
        rates = salaryCalculator.filterInflationRates(costElement, true);
        Assert.assertTrue(rates.size() == 0);

        costElement.setValidCeRateTypes(new ArrayList<>());
        rates = salaryCalculator.filterInflationRates(costElement, true);
        Assert.assertTrue(rates.size() == 0);

        rates = salaryCalculator.filterInflationRates(costElement, false);
        Assert.assertTrue(rates.size() == 0);

    }

    protected ValidCeRateType getValidCeRate(String rateClassType) {
        ValidCeRateType validCeRateType2 = new ValidCeRateType();
        RateClass inflationRateClass2 = new RateClass();
        inflationRateClass2.setRateClassTypeCode(rateClassType);
        inflationRateClass2.setRateClassType(new RateClassType());
        validCeRateType2.setRateClass(inflationRateClass2);
        validCeRateType2.setRateTypeCode("4");
        validCeRateType2.setRateClassCode("7");
        return validCeRateType2;
    }

}
