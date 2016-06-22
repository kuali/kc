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
package org.kuali.coeus.common.budget.impl.print;


import junit.framework.Assert;
import org.junit.Test;
import org.kuali.coeus.common.budget.api.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategory;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetRateAndBase;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelRateAndBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.ArrayList;
import java.util.List;

public class BudgetSummaryPrintTest extends BudgetPrintTestBase {

    private static final String PERSONNEL_CATEGORY_CODE = "P";

	@Test
    public void testPrintAllRatesCheckedNonPersonnel() {
        BudgetSummaryXmlStream stream = new BudgetSummaryXmlStreamMock();
        Budget budget = new Budget();
        final BudgetPeriod budgetPeriod = getBudgetPeriod();
        budget.getBudgetPeriods().add(budgetPeriod);
        budgetPeriod.getBudgetLineItem(0).setBudgetCategoryCode("N");
        List<BudgetLineItemCalculatedAmount> lineItemCalculatedAmounts1 = new ArrayList<>();
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount = getBudgetLineItemCalculatedAmount("1", "1", "MTDC", ScaleTwoDecimal.ONE_HUNDRED);
        lineItemCalculatedAmounts1.add(lineItemCalculatedAmount);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount1 = getBudgetLineItemCalculatedAmount("10", "1", "Lab Allocation - Salaries", ScaleTwoDecimal.ONE_HUNDRED);
        lineItemCalculatedAmounts1.add(lineItemCalculatedAmount1);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount2 = getBudgetLineItemCalculatedAmount("11", "1", "Lab Allocation - M&S", ScaleTwoDecimal.ONE_HUNDRED);
        lineItemCalculatedAmounts1.add(lineItemCalculatedAmount2);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount3 = getBudgetLineItemCalculatedAmount("12", "1", "Lab Allocation - Utilities", ScaleTwoDecimal.ONE_HUNDRED);
        lineItemCalculatedAmounts1.add(lineItemCalculatedAmount3);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount4 = getBudgetLineItemCalculatedAmount("5", "3", "EB on LA", ScaleTwoDecimal.ONE_HUNDRED);
        lineItemCalculatedAmounts1.add(lineItemCalculatedAmount4);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount5 = getBudgetLineItemCalculatedAmount("8", "2", "Vacation on LA", ScaleTwoDecimal.ONE_HUNDRED);
        lineItemCalculatedAmounts1.add(lineItemCalculatedAmount5);
        budgetPeriod.getBudgetLineItem(0).setBudgetLineItemCalculatedAmounts(lineItemCalculatedAmounts1);

        List<BudgetRateAndBase> rateAndBases = new ArrayList<>();
        final BudgetRateAndBase mtdcBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(56L), "1", "1", new ScaleTwoDecimal(560L),
                                                        new ScaleTwoDecimal(1000L), RateClassType.OVERHEAD.getRateClassType(), getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(mtdcBudgetRateAndBase);

        BudgetRateAndBase laSalBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "10", "1", new ScaleTwoDecimal(100L),
                                                        new ScaleTwoDecimal(1000L), RateClassType.LA_SALARIES.getRateClassType(), getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(laSalBudgetRateAndBase);

        BudgetRateAndBase laMSBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "11", "1", new ScaleTwoDecimal(100L),
                                                    new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType(), getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(laMSBudgetRateAndBase);
        BudgetRateAndBase laUtilBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "12", "1", new ScaleTwoDecimal(100L),
                                                    new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType(), getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(laUtilBudgetRateAndBase);
        BudgetRateAndBase ebLABudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(24L), "5", "3", new ScaleTwoDecimal(24L),
                                                    new ScaleTwoDecimal(100L), RateClassType.LAB_ALLOCATION.getRateClassType(), getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(ebLABudgetRateAndBase);
        BudgetRateAndBase vacLABudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(9L), "8", "2", new ScaleTwoDecimal(9L),
                                                    new ScaleTwoDecimal(100L), RateClassType.VACATION.getRateClassType(), getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(vacLABudgetRateAndBase);

        budgetPeriod.getBudgetLineItem(0).setBudgetRateAndBaseList(rateAndBases);

        stream.setBudget(budget);
        stream.setBudgetPeriod(budgetPeriod);
        List<ReportTypeVO> tempReportTypeVOList = new ArrayList<>();
        stream.setBudgetRateAndBaseListForBudgetOHRateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));
        stream.setBudgetRateAndBaseListForBudgetLARateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));
        stream.setBudgetRateAndBaseListForBudgetVacRateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));

        ReportTypeVO reportTypeVO = tempReportTypeVOList.get(0);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(1000L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(560L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(56L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(1);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(1000L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(100L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(10L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(2);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(1000L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(100L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(10L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(3);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(1000L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(100L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(10L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(4); //ebla
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(100L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(24L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(24L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(5);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(100L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(9L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(9L)) == 0);
    }

    @Test
    public void testPrintAllCheckedPersonnel() {
        BudgetSummaryXmlStream stream = new BudgetSummaryXmlStreamMock();
        Budget budget = new Budget();
        final BudgetPeriod budgetPeriod = getBudgetPeriod();
        budget.getBudgetPeriods().add(budgetPeriod);
        ReportTypeVO reportTypeVO;
        budgetPeriod.getBudgetLineItems().clear();
        budgetPeriod.getBudgetLineItems().add(getPersonnelLineItem(budgetPeriod, getDate(2015, 1, 1), getDate(2016, 6, 30)));
        budgetPeriod.getBudgetLineItem(0).setBudgetCategoryCode(PERSONNEL_CATEGORY_CODE);
        final BudgetCategory budgetCategory = new BudgetCategory();
        budgetCategory.setBudgetCategoryTypeCode(PERSONNEL_CATEGORY_CODE);
		budgetPeriod.getBudgetLineItem(0).setBudgetCategory(budgetCategory);
        List<BudgetLineItemCalculatedAmount> lineItemCalculatedAmounts = new ArrayList<>();
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount = getBudgetLineItemCalculatedAmount("1", "1", "MTDC", ScaleTwoDecimal.ONE_HUNDRED);
        lineItemCalculatedAmounts.add(lineItemCalculatedAmount);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount1 = getBudgetLineItemCalculatedAmount("10", "1", "Lab Allocation - Salaries", ScaleTwoDecimal.ONE_HUNDRED);
        lineItemCalculatedAmounts.add(lineItemCalculatedAmount1);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount2 = getBudgetLineItemCalculatedAmount("11", "1", "Lab Allocation - M&S", ScaleTwoDecimal.ONE_HUNDRED);
        lineItemCalculatedAmounts.add(lineItemCalculatedAmount2);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount3 = getBudgetLineItemCalculatedAmount("12", "1", "Lab Allocation - Utilities", ScaleTwoDecimal.ONE_HUNDRED);
        lineItemCalculatedAmounts.add(lineItemCalculatedAmount3);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount4 = getBudgetLineItemCalculatedAmount("5", "3", "EB on LA", ScaleTwoDecimal.ONE_HUNDRED);
        lineItemCalculatedAmounts.add(lineItemCalculatedAmount4);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount5 = getBudgetLineItemCalculatedAmount("8", "2", "Vacation on LA", ScaleTwoDecimal.ONE_HUNDRED);
        lineItemCalculatedAmounts.add(lineItemCalculatedAmount5);
        budgetPeriod.getBudgetLineItem(0).setBudgetLineItemCalculatedAmounts(lineItemCalculatedAmounts);

        budget.getBudgetPersonnelDetailsList().add(getPersonnelDetails());

        BudgetPersonnelRateAndBase mtdcBudgetRateAndBase = getNewBudgetPersonnelRateAndBase(new ScaleTwoDecimal(56L), "1", "1", new ScaleTwoDecimal(560L),
                                                            new ScaleTwoDecimal(1000L), RateClassType.OVERHEAD.getRateClassType());
        budgetPeriod.getBudgetLineItem(0).getBudgetPersonnelDetailsList().get(0).getBudgetPersonnelRateAndBaseList().add(mtdcBudgetRateAndBase);

        BudgetPersonnelRateAndBase laSalariesBudgetRateAndBase = getNewBudgetPersonnelRateAndBase(new ScaleTwoDecimal(20L), "10", "1", new ScaleTwoDecimal(200L),
                                                                    new ScaleTwoDecimal(1000L), RateClassType.LA_SALARIES.getRateClassType());
        budgetPeriod.getBudgetLineItem(0).getBudgetPersonnelDetailsList().get(0).getBudgetPersonnelRateAndBaseList().add(laSalariesBudgetRateAndBase);

        BudgetPersonnelRateAndBase laMSBudgetRateAndBase = getNewBudgetPersonnelRateAndBase(new ScaleTwoDecimal(10L), "11", "1", new ScaleTwoDecimal(100L),
                                                            new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType());
        budgetPeriod.getBudgetLineItem(0).getBudgetPersonnelDetailsList().get(0).getBudgetPersonnelRateAndBaseList().add(laMSBudgetRateAndBase);

        BudgetPersonnelRateAndBase laUtilitiesBudgetRateAndBase = getNewBudgetPersonnelRateAndBase(new ScaleTwoDecimal(10L), "12", "1", new ScaleTwoDecimal(100L),
                                                                    new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType());
        budgetPeriod.getBudgetLineItem(0).getBudgetPersonnelDetailsList().get(0).getBudgetPersonnelRateAndBaseList().add(laUtilitiesBudgetRateAndBase);

        BudgetPersonnelRateAndBase ebOnLA = getNewBudgetPersonnelRateAndBase(new ScaleTwoDecimal(24L), "5", "3", new ScaleTwoDecimal(24L), new ScaleTwoDecimal(100L), RateClassType.EMPLOYEE_BENEFITS.getRateClassType());
        budgetPeriod.getBudgetLineItem(0).getBudgetPersonnelDetailsList().get(0).getBudgetPersonnelRateAndBaseList().add(ebOnLA);

        BudgetPersonnelRateAndBase vacationOnLA = getNewBudgetPersonnelRateAndBase(new ScaleTwoDecimal(9L), "8", "2", new ScaleTwoDecimal(9L),
                                                    new ScaleTwoDecimal(100L), RateClassType.VACATION.getRateClassType());
        budgetPeriod.getBudgetLineItem(0).getBudgetPersonnelDetailsList().get(0).getBudgetPersonnelRateAndBaseList().add(vacationOnLA);

        List<ReportTypeVO> tempReportTypeVOList = new ArrayList<>();
        stream.setBudgetPersRateAndBaseListForBudgetOHRateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));
        stream.setBudgetPersRateAndBaseListForBudgetEBRateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));
        stream.setBudgetPersRateAndBaseListForBudgetLARateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));
        stream.setBudgetPersRateAndBaseListForBudgetVacRateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));

        reportTypeVO = tempReportTypeVOList.get(0);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(1000L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(560L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(56L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(1); //ebla
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(100L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(24L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(24L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(2);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(1000L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(200L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(20L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(3);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(1000L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(100L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(10L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(4);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(1000L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(100L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(10L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(5);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(100L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(9L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(9L)) == 0);
    }

    @Test
    public void testPrintAllRatesZeroNonPersonnel() {
        BudgetSummaryXmlStream stream = new BudgetSummaryXmlStreamMock();
        Budget budget = new Budget();
        final BudgetPeriod budgetPeriod = getBudgetPeriod();
        budget.getBudgetPeriods().add(budgetPeriod);
        budgetPeriod.getBudgetLineItem(0).setBudgetCategoryCode("N");
        List<BudgetLineItemCalculatedAmount> lineItemCalculatedAmounts1 = new ArrayList<>();
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount = getBudgetLineItemCalculatedAmount("1", "1", "MTDC", ScaleTwoDecimal.ZERO);
        lineItemCalculatedAmounts1.add(lineItemCalculatedAmount);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount1 = getBudgetLineItemCalculatedAmount("10", "1", "Lab Allocation - Salaries", ScaleTwoDecimal.ZERO);
        lineItemCalculatedAmounts1.add(lineItemCalculatedAmount1);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount2 = getBudgetLineItemCalculatedAmount("11", "1", "Lab Allocation - M&S", ScaleTwoDecimal.ZERO);
        lineItemCalculatedAmounts1.add(lineItemCalculatedAmount2);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount3 = getBudgetLineItemCalculatedAmount("12", "1", "Lab Allocation - Utilities", ScaleTwoDecimal.ZERO);
        lineItemCalculatedAmounts1.add(lineItemCalculatedAmount3);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount4 = getBudgetLineItemCalculatedAmount("5", "3", "EB on LA", ScaleTwoDecimal.ZERO);
        lineItemCalculatedAmounts1.add(lineItemCalculatedAmount4);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount5 = getBudgetLineItemCalculatedAmount("8", "2", "Vacation on LA", ScaleTwoDecimal.ZERO);
        lineItemCalculatedAmounts1.add(lineItemCalculatedAmount5);
        budgetPeriod.getBudgetLineItem(0).setBudgetLineItemCalculatedAmounts(lineItemCalculatedAmounts1);

        List<BudgetRateAndBase> rateAndBases = new ArrayList<>();
        final BudgetRateAndBase mtdcBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(0L), "1", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.OVERHEAD.getRateClassType(), getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(mtdcBudgetRateAndBase);

        BudgetRateAndBase laSalBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(0L), "10", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.LA_SALARIES.getRateClassType(), getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(laSalBudgetRateAndBase);

        BudgetRateAndBase laMSBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(0L), "11", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType(), getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(laMSBudgetRateAndBase);
        BudgetRateAndBase laUtilBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(0L), "12", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType(), getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(laUtilBudgetRateAndBase);
        BudgetRateAndBase ebLABudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(0L), "5", "3", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(100L), RateClassType.LAB_ALLOCATION.getRateClassType(), getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(ebLABudgetRateAndBase);
        BudgetRateAndBase vacLABudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(0L), "8", "2", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(100L), RateClassType.VACATION.getRateClassType(), getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(vacLABudgetRateAndBase);

        budgetPeriod.getBudgetLineItem(0).setBudgetRateAndBaseList(rateAndBases);

        stream.setBudget(budget);
        stream.setBudgetPeriod(budgetPeriod);
        List<ReportTypeVO> tempReportTypeVOList = new ArrayList<>();
        stream.setBudgetRateAndBaseListForBudgetOHRateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));
        stream.setBudgetRateAndBaseListForBudgetLARateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));
        stream.setBudgetRateAndBaseListForBudgetVacRateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));

        ReportTypeVO reportTypeVO = tempReportTypeVOList.get(0);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(1000L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(0L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(1);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(1000L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(0L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(2);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(1000L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(0L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(3);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(1000L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(0L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(4); //ebla
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(100L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(0L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(5);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(100L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(0L)) == 0);
    }

    @Test
    public void testPrintAllUnCheckedNonPersonnel() {
        BudgetSummaryXmlStream stream = new BudgetSummaryXmlStreamMock();
        Budget budget = new Budget();
        final BudgetPeriod budgetPeriod = getBudgetPeriod();
        budget.getBudgetPeriods().add(budgetPeriod);
        budgetPeriod.getBudgetLineItem(0).setBudgetCategoryCode("N");
        List<BudgetLineItemCalculatedAmount> lineItemCalculatedAmounts1 = new ArrayList<>();
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount = getBudgetLineItemCalculatedAmount("1", "1", "MTDC", ScaleTwoDecimal.ZERO);
        lineItemCalculatedAmount.setApplyRateFlag(false);
        lineItemCalculatedAmounts1.add(lineItemCalculatedAmount);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount1 = getBudgetLineItemCalculatedAmount("10", "1", "Lab Allocation - Salaries", ScaleTwoDecimal.ONE_HUNDRED);
        lineItemCalculatedAmount1.setApplyRateFlag(false);
        lineItemCalculatedAmounts1.add(lineItemCalculatedAmount1);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount2 = getBudgetLineItemCalculatedAmount("11", "1", "Lab Allocation - M&S", ScaleTwoDecimal.ONE_HUNDRED);
        lineItemCalculatedAmount2.setApplyRateFlag(false);
        lineItemCalculatedAmounts1.add(lineItemCalculatedAmount2);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount3 = getBudgetLineItemCalculatedAmount("12", "1", "Lab Allocation - Utilities", ScaleTwoDecimal.ONE_HUNDRED);
        lineItemCalculatedAmount3.setApplyRateFlag(false);
        lineItemCalculatedAmounts1.add(lineItemCalculatedAmount3);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount4 = getBudgetLineItemCalculatedAmount("5", "3", "EB on LA", ScaleTwoDecimal.ONE_HUNDRED);
        lineItemCalculatedAmount4.setApplyRateFlag(false);
        lineItemCalculatedAmounts1.add(lineItemCalculatedAmount4);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount5 = getBudgetLineItemCalculatedAmount("8", "2", "Vacation on LA", ScaleTwoDecimal.ONE_HUNDRED);
        lineItemCalculatedAmount5.setApplyRateFlag(false);
        lineItemCalculatedAmounts1.add(lineItemCalculatedAmount5);
        budgetPeriod.getBudgetLineItem(0).setBudgetLineItemCalculatedAmounts(lineItemCalculatedAmounts1);

        List<BudgetRateAndBase> rateAndBases = new ArrayList<>();
        final BudgetRateAndBase mtdcBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(56L), "1", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.OVERHEAD.getRateClassType(), getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(mtdcBudgetRateAndBase);

        BudgetRateAndBase laSalBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "10", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.LA_SALARIES.getRateClassType(), getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(laSalBudgetRateAndBase);

        BudgetRateAndBase laMSBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "11", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType(), getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(laMSBudgetRateAndBase);
        BudgetRateAndBase laUtilBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "12", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType(), getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(laUtilBudgetRateAndBase);
        BudgetRateAndBase ebLABudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(24L), "5", "3", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(100L), RateClassType.LAB_ALLOCATION.getRateClassType(), getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(ebLABudgetRateAndBase);
        BudgetRateAndBase vacLABudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(9L), "8", "2", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(100L), RateClassType.VACATION.getRateClassType(), getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(vacLABudgetRateAndBase);

        budgetPeriod.getBudgetLineItem(0).setBudgetRateAndBaseList(rateAndBases);

        stream.setBudget(budget);
        stream.setBudgetPeriod(budgetPeriod);
        List<ReportTypeVO> tempReportTypeVOList = new ArrayList<>();
        stream.setBudgetRateAndBaseListForBudgetOHRateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));
        stream.setBudgetRateAndBaseListForBudgetLARateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));
        stream.setBudgetRateAndBaseListForBudgetVacRateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));

        ReportTypeVO reportTypeVO = tempReportTypeVOList.get(0);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(56L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(1);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(10L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(2);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(10L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(3);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(10L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(4); //ebla
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(24L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(5);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(9L)) == 0);
    }

    @Test
    public void testPrintAllUncheckedPersonnel() {
        BudgetSummaryXmlStream stream = new BudgetSummaryXmlStreamMock();
        Budget budget = new Budget();
        final BudgetPeriod budgetPeriod = getBudgetPeriod();
        budget.getBudgetPeriods().add(budgetPeriod);
        ReportTypeVO reportTypeVO;
        budgetPeriod.getBudgetLineItems().clear();
        budgetPeriod.getBudgetLineItems().add(getPersonnelLineItem(budgetPeriod, getDate(2015, 1, 1), getDate(2016, 6, 30)));
        budgetPeriod.getBudgetLineItem(0).setBudgetCategoryCode(PERSONNEL_CATEGORY_CODE);
        final BudgetCategory budgetCategory = new BudgetCategory();
        budgetCategory.setBudgetCategoryTypeCode(PERSONNEL_CATEGORY_CODE);
		budgetPeriod.getBudgetLineItem(0).setBudgetCategory(budgetCategory);
        List<BudgetLineItemCalculatedAmount> lineItemCalculatedAmounts = new ArrayList<>();
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount = getBudgetLineItemCalculatedAmount("1", "1", "MTDC", ScaleTwoDecimal.ONE_HUNDRED);
        lineItemCalculatedAmount.setApplyRateFlag(false);
        lineItemCalculatedAmounts.add(lineItemCalculatedAmount);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount1 = getBudgetLineItemCalculatedAmount("10", "1", "Lab Allocation - Salaries", ScaleTwoDecimal.ZERO);
        lineItemCalculatedAmount1.setApplyRateFlag(false);
        lineItemCalculatedAmounts.add(lineItemCalculatedAmount1);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount2 = getBudgetLineItemCalculatedAmount("11", "1", "Lab Allocation - M&S", ScaleTwoDecimal.ONE_HUNDRED);
        lineItemCalculatedAmount2.setApplyRateFlag(false);
        lineItemCalculatedAmounts.add(lineItemCalculatedAmount2);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount3 = getBudgetLineItemCalculatedAmount("12", "1", "Lab Allocation - Utilities", ScaleTwoDecimal.ONE_HUNDRED);
        lineItemCalculatedAmount3.setApplyRateFlag(false);
        lineItemCalculatedAmounts.add(lineItemCalculatedAmount3);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount4 = getBudgetLineItemCalculatedAmount("5", "3", "EB on LA", ScaleTwoDecimal.ONE_HUNDRED);
        lineItemCalculatedAmount4.setApplyRateFlag(false);
        lineItemCalculatedAmounts.add(lineItemCalculatedAmount4);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount5 = getBudgetLineItemCalculatedAmount("8", "2", "Vacation on LA", ScaleTwoDecimal.ONE_HUNDRED);
        lineItemCalculatedAmount5.setApplyRateFlag(false);
        lineItemCalculatedAmounts.add(lineItemCalculatedAmount5);
        budgetPeriod.getBudgetLineItem(0).setBudgetLineItemCalculatedAmounts(lineItemCalculatedAmounts);

        budget.getBudgetPersonnelDetailsList().add(getPersonnelDetails());

        BudgetPersonnelRateAndBase mtdcBudgetRateAndBase = getNewBudgetPersonnelRateAndBase(new ScaleTwoDecimal(56L), "1", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.OVERHEAD.getRateClassType());
        budgetPeriod.getBudgetLineItem(0).getBudgetPersonnelDetailsList().get(0).getBudgetPersonnelRateAndBaseList().add(mtdcBudgetRateAndBase);

        BudgetPersonnelRateAndBase laSalariesBudgetRateAndBase = getNewBudgetPersonnelRateAndBase(new ScaleTwoDecimal(20L), "10", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.LA_SALARIES.getRateClassType());
        budgetPeriod.getBudgetLineItem(0).getBudgetPersonnelDetailsList().get(0).getBudgetPersonnelRateAndBaseList().add(laSalariesBudgetRateAndBase);

        BudgetPersonnelRateAndBase laMSBudgetRateAndBase = getNewBudgetPersonnelRateAndBase(new ScaleTwoDecimal(10L), "11", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType());
        budgetPeriod.getBudgetLineItem(0).getBudgetPersonnelDetailsList().get(0).getBudgetPersonnelRateAndBaseList().add(laMSBudgetRateAndBase);

        BudgetPersonnelRateAndBase laUtilitiesBudgetRateAndBase = getNewBudgetPersonnelRateAndBase(new ScaleTwoDecimal(10L), "12", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType());
        budgetPeriod.getBudgetLineItem(0).getBudgetPersonnelDetailsList().get(0).getBudgetPersonnelRateAndBaseList().add(laUtilitiesBudgetRateAndBase);

        BudgetPersonnelRateAndBase ebOnLA = getNewBudgetPersonnelRateAndBase(new ScaleTwoDecimal(24L), "5", "3", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(100L), RateClassType.EMPLOYEE_BENEFITS.getRateClassType());
        budgetPeriod.getBudgetLineItem(0).getBudgetPersonnelDetailsList().get(0).getBudgetPersonnelRateAndBaseList().add(ebOnLA);

        BudgetPersonnelRateAndBase vacationOnLA = getNewBudgetPersonnelRateAndBase(new ScaleTwoDecimal(9L), "8", "2", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(100L), RateClassType.VACATION.getRateClassType());
        budgetPeriod.getBudgetLineItem(0).getBudgetPersonnelDetailsList().get(0).getBudgetPersonnelRateAndBaseList().add(vacationOnLA);

        List<ReportTypeVO> tempReportTypeVOList = new ArrayList<>();
        stream.setBudgetPersRateAndBaseListForBudgetOHRateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));
        stream.setBudgetPersRateAndBaseListForBudgetEBRateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));
        stream.setBudgetPersRateAndBaseListForBudgetLARateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));
        stream.setBudgetPersRateAndBaseListForBudgetVacRateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));

        reportTypeVO = tempReportTypeVOList.get(0);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(56L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(1); //ebla
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(24L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(2);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(20L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(3);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(10L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(4);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(10L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(5);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(9L)) == 0);
    }

    @Test
    public void testPrintAllRatesZeroPersonnel() {
        BudgetSummaryXmlStream stream = new BudgetSummaryXmlStreamMock();
        Budget budget = new Budget();
        final BudgetPeriod budgetPeriod = getBudgetPeriod();
        budget.getBudgetPeriods().add(budgetPeriod);
        ReportTypeVO reportTypeVO;
        budgetPeriod.getBudgetLineItems().clear();
        budgetPeriod.getBudgetLineItems().add(getPersonnelLineItem(budgetPeriod, getDate(2015, 1, 1), getDate(2016, 6, 30)));
        budgetPeriod.getBudgetLineItem(0).setBudgetCategoryCode(PERSONNEL_CATEGORY_CODE);
        final BudgetCategory budgetCategory = new BudgetCategory();
        budgetCategory.setBudgetCategoryTypeCode(PERSONNEL_CATEGORY_CODE);
		budgetPeriod.getBudgetLineItem(0).setBudgetCategory(budgetCategory);
        List<BudgetLineItemCalculatedAmount> lineItemCalculatedAmounts = new ArrayList<>();
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount = getBudgetLineItemCalculatedAmount("1", "1", "MTDC", ScaleTwoDecimal.ZERO);
        lineItemCalculatedAmounts.add(lineItemCalculatedAmount);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount1 = getBudgetLineItemCalculatedAmount("10", "1", "Lab Allocation - Salaries", ScaleTwoDecimal.ZERO);
        lineItemCalculatedAmounts.add(lineItemCalculatedAmount1);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount2 = getBudgetLineItemCalculatedAmount("11", "1", "Lab Allocation - M&S", ScaleTwoDecimal.ZERO);
        lineItemCalculatedAmounts.add(lineItemCalculatedAmount2);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount3 = getBudgetLineItemCalculatedAmount("12", "1", "Lab Allocation - Utilities", ScaleTwoDecimal.ZERO);
        lineItemCalculatedAmounts.add(lineItemCalculatedAmount3);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount4 = getBudgetLineItemCalculatedAmount("5", "3", "EB on LA", ScaleTwoDecimal.ZERO);
        lineItemCalculatedAmounts.add(lineItemCalculatedAmount4);
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount5 = getBudgetLineItemCalculatedAmount("8", "2", "Vacation on LA", ScaleTwoDecimal.ZERO);
        lineItemCalculatedAmounts.add(lineItemCalculatedAmount5);
        budgetPeriod.getBudgetLineItem(0).setBudgetLineItemCalculatedAmounts(lineItemCalculatedAmounts);

        budget.getBudgetPersonnelDetailsList().add(getPersonnelDetails());

        BudgetPersonnelRateAndBase mtdcBudgetRateAndBase = getNewBudgetPersonnelRateAndBase(new ScaleTwoDecimal(0L), "1", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.OVERHEAD.getRateClassType());
        budgetPeriod.getBudgetLineItem(0).getBudgetPersonnelDetailsList().get(0).getBudgetPersonnelRateAndBaseList().add(mtdcBudgetRateAndBase);

        BudgetPersonnelRateAndBase laSalariesBudgetRateAndBase = getNewBudgetPersonnelRateAndBase(new ScaleTwoDecimal(0L), "10", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.LA_SALARIES.getRateClassType());
        budgetPeriod.getBudgetLineItem(0).getBudgetPersonnelDetailsList().get(0).getBudgetPersonnelRateAndBaseList().add(laSalariesBudgetRateAndBase);

        BudgetPersonnelRateAndBase laMSBudgetRateAndBase = getNewBudgetPersonnelRateAndBase(new ScaleTwoDecimal(0L), "11", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType());
        budgetPeriod.getBudgetLineItem(0).getBudgetPersonnelDetailsList().get(0).getBudgetPersonnelRateAndBaseList().add(laMSBudgetRateAndBase);

        BudgetPersonnelRateAndBase laUtilitiesBudgetRateAndBase = getNewBudgetPersonnelRateAndBase(new ScaleTwoDecimal(0L), "12", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType());
        budgetPeriod.getBudgetLineItem(0).getBudgetPersonnelDetailsList().get(0).getBudgetPersonnelRateAndBaseList().add(laUtilitiesBudgetRateAndBase);

        BudgetPersonnelRateAndBase ebOnLA = getNewBudgetPersonnelRateAndBase(new ScaleTwoDecimal(0L), "5", "3", new ScaleTwoDecimal(0L),
                                                new ScaleTwoDecimal(100L), RateClassType.EMPLOYEE_BENEFITS.getRateClassType());
        budgetPeriod.getBudgetLineItem(0).getBudgetPersonnelDetailsList().get(0).getBudgetPersonnelRateAndBaseList().add(ebOnLA);

        BudgetPersonnelRateAndBase vacationOnLA = getNewBudgetPersonnelRateAndBase(new ScaleTwoDecimal(0L), "8", "2", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(100L), RateClassType.VACATION.getRateClassType());
        budgetPeriod.getBudgetLineItem(0).getBudgetPersonnelDetailsList().get(0).getBudgetPersonnelRateAndBaseList().add(vacationOnLA);

        List<ReportTypeVO> tempReportTypeVOList = new ArrayList<>();
        stream.setBudgetPersRateAndBaseListForBudgetOHRateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));
        stream.setBudgetPersRateAndBaseListForBudgetEBRateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));
        stream.setBudgetPersRateAndBaseListForBudgetLARateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));
        stream.setBudgetPersRateAndBaseListForBudgetVacRateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));

        reportTypeVO = tempReportTypeVOList.get(0);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(1000L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(0L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(1); //ebla
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(100L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(0L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(2);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(1000L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(0L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(3);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(1000L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(0L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(4);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(1000L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(0L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(5);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(100L)) == 0);
        Assert.assertTrue(reportTypeVO.getCalculatedCost().compareTo(new ScaleTwoDecimal(0L)) == 0);
        Assert.assertTrue(reportTypeVO.getAppliedRate().compareTo(new ScaleTwoDecimal(0L)) == 0);
    }

    @Test
    public void testPrintNullCalcAmountsPersonnel() {
        BudgetSummaryXmlStream stream = new BudgetSummaryXmlStreamMock();
        Budget budget = new Budget();
        final BudgetPeriod budgetPeriod = getBudgetPeriod();
        budget.getBudgetPeriods().add(budgetPeriod);
        ReportTypeVO reportTypeVO;
        budgetPeriod.getBudgetLineItems().clear();
        budgetPeriod.getBudgetLineItems().add(getPersonnelLineItem(budgetPeriod, getDate(2015, 1, 1), getDate(2016, 6, 30)));
        budgetPeriod.getBudgetLineItem(0).setBudgetCategoryCode(PERSONNEL_CATEGORY_CODE);
        final BudgetCategory budgetCategory = new BudgetCategory();
        budgetCategory.setBudgetCategoryTypeCode(PERSONNEL_CATEGORY_CODE);
		budgetPeriod.getBudgetLineItem(0).setBudgetCategory(budgetCategory);
        List<BudgetLineItemCalculatedAmount> lineItemCalculatedAmounts = new ArrayList<>();
        budgetPeriod.getBudgetLineItem(0).setBudgetLineItemCalculatedAmounts(lineItemCalculatedAmounts);

        budget.getBudgetPersonnelDetailsList().add(getPersonnelDetails());

        BudgetPersonnelRateAndBase mtdcBudgetRateAndBase = getNewBudgetPersonnelRateAndBase(new ScaleTwoDecimal(0L), "1", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.OVERHEAD.getRateClassType());
        budgetPeriod.getBudgetLineItem(0).getBudgetPersonnelDetailsList().get(0).getBudgetPersonnelRateAndBaseList().add(mtdcBudgetRateAndBase);

        BudgetPersonnelRateAndBase laSalariesBudgetRateAndBase = getNewBudgetPersonnelRateAndBase(new ScaleTwoDecimal(0L), "10", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.LA_SALARIES.getRateClassType());
        budgetPeriod.getBudgetLineItem(0).getBudgetPersonnelDetailsList().get(0).getBudgetPersonnelRateAndBaseList().add(laSalariesBudgetRateAndBase);

        BudgetPersonnelRateAndBase laMSBudgetRateAndBase = getNewBudgetPersonnelRateAndBase(new ScaleTwoDecimal(0L), "11", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType());
        budgetPeriod.getBudgetLineItem(0).getBudgetPersonnelDetailsList().get(0).getBudgetPersonnelRateAndBaseList().add(laMSBudgetRateAndBase);

        BudgetPersonnelRateAndBase laUtilitiesBudgetRateAndBase = getNewBudgetPersonnelRateAndBase(new ScaleTwoDecimal(0L), "12", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType());
        budgetPeriod.getBudgetLineItem(0).getBudgetPersonnelDetailsList().get(0).getBudgetPersonnelRateAndBaseList().add(laUtilitiesBudgetRateAndBase);

        BudgetPersonnelRateAndBase ebOnLA = getNewBudgetPersonnelRateAndBase(new ScaleTwoDecimal(0L), "5", "3", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(100L), RateClassType.EMPLOYEE_BENEFITS.getRateClassType());
        budgetPeriod.getBudgetLineItem(0).getBudgetPersonnelDetailsList().get(0).getBudgetPersonnelRateAndBaseList().add(ebOnLA);

        BudgetPersonnelRateAndBase vacationOnLA = getNewBudgetPersonnelRateAndBase(new ScaleTwoDecimal(0L), "8", "2", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(100L), RateClassType.VACATION.getRateClassType());
        budgetPeriod.getBudgetLineItem(0).getBudgetPersonnelDetailsList().get(0).getBudgetPersonnelRateAndBaseList().add(vacationOnLA);

        List<ReportTypeVO> tempReportTypeVOList = new ArrayList<>();
        stream.setBudgetPersRateAndBaseListForBudgetOHRateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));
        stream.setBudgetPersRateAndBaseListForBudgetEBRateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));
        stream.setBudgetPersRateAndBaseListForBudgetLARateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));
        stream.setBudgetPersRateAndBaseListForBudgetVacRateAndBase(tempReportTypeVOList, budgetPeriod.getBudgetLineItem(0));

        reportTypeVO = tempReportTypeVOList.get(0);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(0L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(1);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(0L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(2);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(0L)) == 0);


        reportTypeVO = tempReportTypeVOList.get(3);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(0L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(4);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(0L)) == 0);

        reportTypeVO = tempReportTypeVOList.get(5);
        Assert.assertTrue(reportTypeVO.getSalaryRequested().compareTo(new ScaleTwoDecimal(0L)) == 0);
    }
}
