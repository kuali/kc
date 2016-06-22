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
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetRateAndBase;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.printing.schema.ReportType;

import java.util.ArrayList;
import java.util.List;

public class BudgetSummaryPrintFringeCalculationTest extends BudgetPrintTestBase {

    /*
    Tests the case where there are two line items for the same object code with each line item representing
    a different fiscal year. One 2016,1,1 to 2016,6,30 and another from 2016,7,1 to 2016,12,31
     */
    @Test
    public void testCalculateFringePersonnel2LineItemsFor2FiscalYears() {
        BudgetSummaryXmlStreamMock stream = new BudgetSummaryXmlStreamMock();
        Budget budget = new Budget();
        final BudgetPeriod budgetPeriod = getBudgetPeriod();
        budget.getBudgetPeriods().add(budgetPeriod);
        budgetPeriod.getBudgetLineItems().clear();
        budgetPeriod.getBudgetLineItems().add(getPersonnelLineItem(budgetPeriod, getDate(2016, 1, 1), getDate(2016, 6, 30)));
        budgetPeriod.getBudgetLineItem(0).setBudgetCategoryCode("P");
        budgetPeriod.getBudgetLineItems().add(getPersonnelLineItem(budgetPeriod, getDate(2016, 7, 1), getDate(2016, 12, 31)));
        budgetPeriod.getBudgetLineItem(1).setBudgetCategoryCode("P");

        budget.getBudgetPersonnelDetailsList().add(getPersonnelDetails(getDate(2016, 7, 1), getDate(2016, 12, 31)));

        List<BudgetRateAndBase> rateAndBases = new ArrayList<>();
        final BudgetRateAndBase mtdcBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(56L), "1", "1", new ScaleTwoDecimal(560L),
                                                                                new ScaleTwoDecimal(1000L), RateClassType.OVERHEAD.getRateClassType(),
                                                                                getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(mtdcBudgetRateAndBase);

        BudgetRateAndBase laSalBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "10", "1", new ScaleTwoDecimal(100L),
                                                                            new ScaleTwoDecimal(1000L), RateClassType.LA_SALARIES.getRateClassType(),
                                                                            getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(laSalBudgetRateAndBase);

        BudgetRateAndBase laMSBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "11", "1", new ScaleTwoDecimal(100L),
                                                                            new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType(),
                                                                            getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(laMSBudgetRateAndBase);

        BudgetRateAndBase laUtilBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "12", "1", new ScaleTwoDecimal(100L),
                                                                            new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType(),
                                                                            getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(laUtilBudgetRateAndBase);
        BudgetRateAndBase ebLABudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(24L), "5", "3", new ScaleTwoDecimal(24L),
                new ScaleTwoDecimal(100L), RateClassType.EMPLOYEE_BENEFITS.getRateClassType(),
                getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(ebLABudgetRateAndBase);
        BudgetRateAndBase vacLABudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(9L), "8", "2", new ScaleTwoDecimal(9L),
                                                                            new ScaleTwoDecimal(100L), RateClassType.VACATION.getRateClassType(),
                                                                            getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(vacLABudgetRateAndBase);

        budgetPeriod.getBudgetLineItem(0).setBudgetRateAndBaseList(rateAndBases);

        List<BudgetRateAndBase> rateAndBases1 = new ArrayList<>();
        final BudgetRateAndBase mtdcBudgetRateAndBase1 = getNewBudgetRateAndBase(new ScaleTwoDecimal(56L), "1", "1", new ScaleTwoDecimal(560L),
                new ScaleTwoDecimal(1000L), RateClassType.OVERHEAD.getRateClassType(),
                getDate(2016, 7, 1), getDate(2016, 12, 31));
        rateAndBases1.add(mtdcBudgetRateAndBase1);

        BudgetRateAndBase laSalBudgetRateAndBase1 = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "10", "1", new ScaleTwoDecimal(100L),
                new ScaleTwoDecimal(1000L), RateClassType.LA_SALARIES.getRateClassType(),
                getDate(2016, 7, 1), getDate(2016, 12, 31));
        rateAndBases1.add(laSalBudgetRateAndBase1);

        BudgetRateAndBase laMSBudgetRateAndBase1 = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "11", "1", new ScaleTwoDecimal(100L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType(),
                getDate(2016, 7, 1), getDate(2016, 12, 31));
        rateAndBases1.add(laMSBudgetRateAndBase1);

        BudgetRateAndBase laUtilBudgetRateAndBase1 = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "12", "1", new ScaleTwoDecimal(100L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType(),
                getDate(2016, 7, 1), getDate(2016, 12, 31));
        rateAndBases1.add(laUtilBudgetRateAndBase1);

        BudgetRateAndBase ebLABudgetRateAndBase1 = getNewBudgetRateAndBase(new ScaleTwoDecimal(24L), "5", "3", new ScaleTwoDecimal(24L),
                new ScaleTwoDecimal(100L), RateClassType.EMPLOYEE_BENEFITS.getRateClassType(),
                getDate(2016, 7, 1), getDate(2016, 12, 31));
        rateAndBases1.add(ebLABudgetRateAndBase1);

        BudgetRateAndBase vacLABudgetRateAndBase1 = getNewBudgetRateAndBase(new ScaleTwoDecimal(9L), "8", "2", new ScaleTwoDecimal(9L),
                new ScaleTwoDecimal(100L), RateClassType.VACATION.getRateClassType(),
                getDate(2016, 7, 1), getDate(2016, 12, 31));
        rateAndBases1.add(vacLABudgetRateAndBase1);

        budgetPeriod.getBudgetLineItem(1).setBudgetRateAndBaseList(rateAndBases1);
        stream.setBudgetPeriod(budgetPeriod);

        List<ReportType> reportTypeList = new ArrayList<>();
        List<ReportTypeVO> reportTypeVOList = new ArrayList<>();

        stream.setBudgetLASalaryForBudgetRateAndBase(reportTypeList, reportTypeVOList);
        Assert.assertTrue(stream.getFringe().compareTo(new ScaleTwoDecimal(66L)) == 0);
    }

    /*
        Tests the case where there are two line items for the same object code with each line item representing
        a different fiscal year. One 2016,1,1 to 2016,6,30 and another from 2016,7,1 to 2016,12,31
         */
    @Test
    public void testCalculateFringeNonPersonnel2LineItemsFor2FiscalYears() {
        BudgetSummaryXmlStreamMock stream = new BudgetSummaryXmlStreamMock();
        Budget budget = new Budget();
        final BudgetPeriod budgetPeriod = getBudgetPeriod();
        budget.getBudgetPeriods().add(budgetPeriod);
        budgetPeriod.getBudgetLineItems().clear();
        budgetPeriod.getBudgetLineItems().add(getTravelLineItem(budgetPeriod, getDate(2016, 1, 1), getDate(2016, 6, 30)));
        budgetPeriod.getBudgetLineItem(0).setBudgetCategoryCode("N");
        budgetPeriod.getBudgetLineItems().add(getTravelLineItem(budgetPeriod, getDate(2016, 7, 1), getDate(2016, 12, 31)));
        budgetPeriod.getBudgetLineItem(1).setBudgetCategoryCode("N");

        budget.getBudgetPersonnelDetailsList().add(getPersonnelDetails(getDate(2016, 7, 1), getDate(2016, 12, 31)));

        List<BudgetRateAndBase> rateAndBases = new ArrayList<>();
        final BudgetRateAndBase mtdcBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(56L), "1", "1", new ScaleTwoDecimal(560L),
                new ScaleTwoDecimal(1000L), RateClassType.OVERHEAD.getRateClassType(),
                getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(mtdcBudgetRateAndBase);

        BudgetRateAndBase laSalBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "10", "1", new ScaleTwoDecimal(100L),
                new ScaleTwoDecimal(1000L), RateClassType.LA_SALARIES.getRateClassType(),
                getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(laSalBudgetRateAndBase);

        BudgetRateAndBase laMSBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "11", "1", new ScaleTwoDecimal(100L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType(),
                getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(laMSBudgetRateAndBase);

        BudgetRateAndBase laUtilBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "12", "1", new ScaleTwoDecimal(100L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType(),
                getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(laUtilBudgetRateAndBase);
        BudgetRateAndBase ebLABudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(24L), "5", "3", new ScaleTwoDecimal(24L),
                new ScaleTwoDecimal(100L), RateClassType.EMPLOYEE_BENEFITS.getRateClassType(),
                getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(ebLABudgetRateAndBase);
        BudgetRateAndBase vacLABudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(9L), "8", "2", new ScaleTwoDecimal(9L),
                new ScaleTwoDecimal(100L), RateClassType.VACATION.getRateClassType(),
                getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(vacLABudgetRateAndBase);

        budgetPeriod.getBudgetLineItem(0).setBudgetRateAndBaseList(rateAndBases);

        List<BudgetRateAndBase> rateAndBases1 = new ArrayList<>();
        final BudgetRateAndBase mtdcBudgetRateAndBase1 = getNewBudgetRateAndBase(new ScaleTwoDecimal(56L), "1", "1", new ScaleTwoDecimal(560L),
                new ScaleTwoDecimal(1000L), RateClassType.OVERHEAD.getRateClassType(),
                getDate(2016, 7, 1), getDate(2016, 12, 31));
        rateAndBases1.add(mtdcBudgetRateAndBase1);

        BudgetRateAndBase laSalBudgetRateAndBase1 = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "10", "1", new ScaleTwoDecimal(100L),
                new ScaleTwoDecimal(1000L), RateClassType.LA_SALARIES.getRateClassType(),
                getDate(2016, 7, 1), getDate(2016, 12, 31));
        rateAndBases1.add(laSalBudgetRateAndBase1);

        BudgetRateAndBase laMSBudgetRateAndBase1 = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "11", "1", new ScaleTwoDecimal(100L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType(),
                getDate(2016, 7, 1), getDate(2016, 12, 31));
        rateAndBases1.add(laMSBudgetRateAndBase1);

        BudgetRateAndBase laUtilBudgetRateAndBase1 = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "12", "1", new ScaleTwoDecimal(100L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType(),
                getDate(2016, 7, 1), getDate(2016, 12, 31));
        rateAndBases1.add(laUtilBudgetRateAndBase1);

        BudgetRateAndBase ebLABudgetRateAndBase1 = getNewBudgetRateAndBase(new ScaleTwoDecimal(24L), "5", "3", new ScaleTwoDecimal(24L),
                new ScaleTwoDecimal(100L), RateClassType.EMPLOYEE_BENEFITS.getRateClassType(),
                getDate(2016, 7, 1), getDate(2016, 12, 31));
        rateAndBases1.add(ebLABudgetRateAndBase1);

        BudgetRateAndBase vacLABudgetRateAndBase1 = getNewBudgetRateAndBase(new ScaleTwoDecimal(9L), "8", "2", new ScaleTwoDecimal(9L),
                new ScaleTwoDecimal(100L), RateClassType.VACATION.getRateClassType(),
                getDate(2016, 7, 1), getDate(2016, 12, 31));
        rateAndBases1.add(vacLABudgetRateAndBase1);

        budgetPeriod.getBudgetLineItem(1).setBudgetRateAndBaseList(rateAndBases1);
        stream.setBudgetPeriod(budgetPeriod);

        List<ReportType> reportTypeList = new ArrayList<>();
        List<ReportTypeVO> reportTypeVOList = new ArrayList<>();

        stream.setBudgetLASalaryForBudgetRateAndBase(reportTypeList, reportTypeVOList);
        Assert.assertTrue(stream.getFringe().compareTo(new ScaleTwoDecimal(66L)) == 0);
    }

    /*
    Tests the case where there is one line item for the same object code with 2 sets of bases one for each fiscal year.
    One 2016,1,1 to 2016,6,30 and another from 2016,7,1 to 2016,12,31
     */
    @Test
    public void testCalculateFringePersonnel1LineItemFor2FiscalYears() {
        BudgetSummaryXmlStreamMock stream = new BudgetSummaryXmlStreamMock();
        Budget budget = new Budget();
        final BudgetPeriod budgetPeriod = getBudgetPeriod();
        budget.getBudgetPeriods().add(budgetPeriod);
        budgetPeriod.getBudgetLineItems().clear();
        budgetPeriod.getBudgetLineItems().add(getPersonnelLineItem(budgetPeriod, getDate(2016, 1, 1), getDate(2016, 6, 30)));
        budgetPeriod.getBudgetLineItem(0).setBudgetCategoryCode("P");

        budget.getBudgetPersonnelDetailsList().add(getPersonnelDetails(getDate(2016, 1, 1), getDate(2016, 6, 30)));

        List<BudgetRateAndBase> rateAndBases = new ArrayList<>();
        final BudgetRateAndBase mtdcBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(56L), "1", "1", new ScaleTwoDecimal(560L),
                new ScaleTwoDecimal(1000L), RateClassType.OVERHEAD.getRateClassType(),
                getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(mtdcBudgetRateAndBase);

        BudgetRateAndBase laSalBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "10", "1", new ScaleTwoDecimal(100L),
                new ScaleTwoDecimal(1000L), RateClassType.LA_SALARIES.getRateClassType(),
                getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(laSalBudgetRateAndBase);

        BudgetRateAndBase laMSBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "11", "1", new ScaleTwoDecimal(100L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType(),
                getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(laMSBudgetRateAndBase);

        BudgetRateAndBase laUtilBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "12", "1", new ScaleTwoDecimal(100L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType(),
                getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(laUtilBudgetRateAndBase);
        BudgetRateAndBase ebLABudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(24L), "5", "3", new ScaleTwoDecimal(24L),
                new ScaleTwoDecimal(100L), RateClassType.EMPLOYEE_BENEFITS.getRateClassType(),
                getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(ebLABudgetRateAndBase);
        BudgetRateAndBase vacLABudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(9L), "8", "2", new ScaleTwoDecimal(9L),
                new ScaleTwoDecimal(100L), RateClassType.VACATION.getRateClassType(),
                getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(vacLABudgetRateAndBase);

        budgetPeriod.getBudgetLineItem(0).setBudgetRateAndBaseList(rateAndBases);

        List<BudgetRateAndBase> rateAndBases1 = new ArrayList<>();
        final BudgetRateAndBase mtdcBudgetRateAndBase1 = getNewBudgetRateAndBase(new ScaleTwoDecimal(56L), "1", "1", new ScaleTwoDecimal(560L),
                new ScaleTwoDecimal(1000L), RateClassType.OVERHEAD.getRateClassType(),
                getDate(2016, 7, 1), getDate(2016, 12, 31));
        rateAndBases1.add(mtdcBudgetRateAndBase1);

        BudgetRateAndBase laSalBudgetRateAndBase1 = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "10", "1", new ScaleTwoDecimal(100L),
                new ScaleTwoDecimal(1000L), RateClassType.LA_SALARIES.getRateClassType(),
                getDate(2016, 7, 1), getDate(2016, 12, 31));
        rateAndBases1.add(laSalBudgetRateAndBase1);

        BudgetRateAndBase laMSBudgetRateAndBase1 = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "11", "1", new ScaleTwoDecimal(100L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType(),
                getDate(2016, 7, 1), getDate(2016, 12, 31));
        rateAndBases1.add(laMSBudgetRateAndBase1);

        BudgetRateAndBase laUtilBudgetRateAndBase1 = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "12", "1", new ScaleTwoDecimal(100L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType(),
                getDate(2016, 7, 1), getDate(2016, 12, 31));
        rateAndBases1.add(laUtilBudgetRateAndBase1);

        BudgetRateAndBase ebLABudgetRateAndBase1 = getNewBudgetRateAndBase(new ScaleTwoDecimal(24L), "5", "3", new ScaleTwoDecimal(24L),
                new ScaleTwoDecimal(100L), RateClassType.EMPLOYEE_BENEFITS.getRateClassType(),
                getDate(2016, 7, 1), getDate(2016, 12, 31));
        rateAndBases1.add(ebLABudgetRateAndBase1);

        BudgetRateAndBase vacLABudgetRateAndBase1 = getNewBudgetRateAndBase(new ScaleTwoDecimal(9L), "8", "2", new ScaleTwoDecimal(9L),
                new ScaleTwoDecimal(100L), RateClassType.VACATION.getRateClassType(),
                getDate(2016, 7, 1), getDate(2016, 12, 31));
        rateAndBases1.add(vacLABudgetRateAndBase1);

        // adding both base arrays to same line item since there's 2 fiscal years, there's no list of bases.
        budgetPeriod.getBudgetLineItem(0).getBudgetRateAndBaseList().addAll(rateAndBases1);
        stream.setBudgetPeriod(budgetPeriod);

        List<ReportType> reportTypeList = new ArrayList<>();
        List<ReportTypeVO> reportTypeVOList = new ArrayList<>();

        stream.setBudgetLASalaryForBudgetRateAndBase(reportTypeList, reportTypeVOList);
        Assert.assertTrue(stream.getFringe().compareTo(new ScaleTwoDecimal(66L)) == 0);
    }

    /*
   Tests the case where there is one line item for the same object code with 2 sets of bases one for each fiscal year.
   One 2016,1,1 to 2016,6,30 and another from 2016,7,1 to 2016,12,31
    */
    @Test
    public void testCalculateFringeNonPersonnel1LineItemsFor2FiscalYears() {
        BudgetSummaryXmlStreamMock stream = new BudgetSummaryXmlStreamMock();
        Budget budget = new Budget();
        final BudgetPeriod budgetPeriod = getBudgetPeriod();
        budget.getBudgetPeriods().add(budgetPeriod);
        budgetPeriod.getBudgetLineItems().clear();
        budgetPeriod.getBudgetLineItems().add(getTravelLineItem(budgetPeriod, getDate(2016, 1, 1), getDate(2016, 6, 30)));
        budgetPeriod.getBudgetLineItem(0).setBudgetCategoryCode("N");

        budget.getBudgetPersonnelDetailsList().add(getPersonnelDetails(getDate(2016, 1, 1), getDate(2016, 6, 30)));

        List<BudgetRateAndBase> rateAndBases = new ArrayList<>();
        final BudgetRateAndBase mtdcBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(56L), "1", "1", new ScaleTwoDecimal(560L),
                new ScaleTwoDecimal(1000L), RateClassType.OVERHEAD.getRateClassType(),
                getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(mtdcBudgetRateAndBase);

        BudgetRateAndBase laSalBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "10", "1", new ScaleTwoDecimal(100L),
                new ScaleTwoDecimal(1000L), RateClassType.LA_SALARIES.getRateClassType(),
                getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(laSalBudgetRateAndBase);

        BudgetRateAndBase laMSBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "11", "1", new ScaleTwoDecimal(100L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType(),
                getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(laMSBudgetRateAndBase);

        BudgetRateAndBase laUtilBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "12", "1", new ScaleTwoDecimal(100L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType(),
                getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(laUtilBudgetRateAndBase);
        BudgetRateAndBase ebLABudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(24L), "5", "3", new ScaleTwoDecimal(24L),
                new ScaleTwoDecimal(100L), RateClassType.EMPLOYEE_BENEFITS.getRateClassType(),
                getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(ebLABudgetRateAndBase);
        BudgetRateAndBase vacLABudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(9L), "8", "2", new ScaleTwoDecimal(9L),
                new ScaleTwoDecimal(100L), RateClassType.VACATION.getRateClassType(),
                getDate(2016, 1, 1), getDate(2016, 6, 30));
        rateAndBases.add(vacLABudgetRateAndBase);

        budgetPeriod.getBudgetLineItem(0).setBudgetRateAndBaseList(rateAndBases);

        List<BudgetRateAndBase> rateAndBases1 = new ArrayList<>();
        final BudgetRateAndBase mtdcBudgetRateAndBase1 = getNewBudgetRateAndBase(new ScaleTwoDecimal(56L), "1", "1", new ScaleTwoDecimal(560L),
                new ScaleTwoDecimal(1000L), RateClassType.OVERHEAD.getRateClassType(),
                getDate(2016, 7, 1), getDate(2016, 12, 31));
        rateAndBases1.add(mtdcBudgetRateAndBase1);

        BudgetRateAndBase laSalBudgetRateAndBase1 = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "10", "1", new ScaleTwoDecimal(100L),
                new ScaleTwoDecimal(1000L), RateClassType.LA_SALARIES.getRateClassType(),
                getDate(2016, 7, 1), getDate(2016, 12, 31));
        rateAndBases1.add(laSalBudgetRateAndBase1);

        BudgetRateAndBase laMSBudgetRateAndBase1 = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "11", "1", new ScaleTwoDecimal(100L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType(),
                getDate(2016, 7, 1), getDate(2016, 12, 31));
        rateAndBases1.add(laMSBudgetRateAndBase1);

        BudgetRateAndBase laUtilBudgetRateAndBase1 = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "12", "1", new ScaleTwoDecimal(100L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType(),
                getDate(2016, 7, 1), getDate(2016, 12, 31));
        rateAndBases1.add(laUtilBudgetRateAndBase1);

        BudgetRateAndBase ebLABudgetRateAndBase1 = getNewBudgetRateAndBase(new ScaleTwoDecimal(24L), "5", "3", new ScaleTwoDecimal(24L),
                new ScaleTwoDecimal(100L), RateClassType.EMPLOYEE_BENEFITS.getRateClassType(),
                getDate(2016, 7, 1), getDate(2016, 12, 31));
        rateAndBases1.add(ebLABudgetRateAndBase1);

        BudgetRateAndBase vacLABudgetRateAndBase1 = getNewBudgetRateAndBase(new ScaleTwoDecimal(9L), "8", "2", new ScaleTwoDecimal(9L),
                new ScaleTwoDecimal(100L), RateClassType.VACATION.getRateClassType(),
                getDate(2016, 7, 1), getDate(2016, 12, 31));
        rateAndBases1.add(vacLABudgetRateAndBase1);

        // adding both base arrays to same line item since there's 2 fiscal years, there's no list of bases.
        budgetPeriod.getBudgetLineItem(0).getBudgetRateAndBaseList().addAll(rateAndBases1);
        stream.setBudgetPeriod(budgetPeriod);

        List<ReportType> reportTypeList = new ArrayList<>();
        List<ReportTypeVO> reportTypeVOList = new ArrayList<>();

        stream.setBudgetLASalaryForBudgetRateAndBase(reportTypeList, reportTypeVOList);
        Assert.assertTrue(stream.getFringe().compareTo(new ScaleTwoDecimal(66L)) == 0);
    }
}
