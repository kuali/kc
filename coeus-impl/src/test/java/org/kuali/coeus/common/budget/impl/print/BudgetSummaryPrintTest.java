package org.kuali.coeus.common.budget.impl.print;


import junit.framework.Assert;
import org.junit.Test;
import org.kuali.coeus.common.budget.api.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategory;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetRateAndBase;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelRateAndBase;
import org.kuali.coeus.common.budget.framework.rate.RateClass;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BudgetSummaryPrintTest {

    class BudgetRateAndBaseMock extends BudgetRateAndBase {
        @Override
        public void refreshReferenceObject(String referenceObjectName) {
            //noop
        }
    }

    class BudgetPersonnelRateAndBaseMock extends BudgetPersonnelRateAndBase {
        @Override
        public void refreshNonUpdateableReferences() {
            //noop
        }
    }

    class BudgetSummaryXmlStreamMock extends BudgetSummaryXmlStream {
        protected String getRateTypeDesc(String rateClassCode, String rateTypeCode) {
            return "";
        }

        protected String getLiVacOnLaRateTypeCode() {
            return "3";
        }
    }

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
                                                        new ScaleTwoDecimal(1000L), RateClassType.OVERHEAD.getRateClassType());
        rateAndBases.add(mtdcBudgetRateAndBase);

        BudgetRateAndBase laSalBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "10", "1", new ScaleTwoDecimal(100L),
                                                        new ScaleTwoDecimal(1000L), RateClassType.LA_SALARIES.getRateClassType());
        rateAndBases.add(laSalBudgetRateAndBase);

        BudgetRateAndBase laMSBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "11", "1", new ScaleTwoDecimal(100L),
                                                    new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType());
        rateAndBases.add(laMSBudgetRateAndBase);
        BudgetRateAndBase laUtilBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "12", "1", new ScaleTwoDecimal(100L),
                                                    new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType());
        rateAndBases.add(laUtilBudgetRateAndBase);
        BudgetRateAndBase ebLABudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(24L), "5", "3", new ScaleTwoDecimal(24L),
                                                    new ScaleTwoDecimal(100L), RateClassType.LAB_ALLOCATION.getRateClassType());
        rateAndBases.add(ebLABudgetRateAndBase);
        BudgetRateAndBase vacLABudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(9L), "8", "2", new ScaleTwoDecimal(9L),
                                                    new ScaleTwoDecimal(100L), RateClassType.VACATION.getRateClassType());
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
        budgetPeriod.getBudgetLineItems().add(getPersonnelLineItem(budgetPeriod));
        budgetPeriod.getBudgetLineItem(0).setBudgetCategoryCode("P");
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
                new ScaleTwoDecimal(1000L), RateClassType.OVERHEAD.getRateClassType());
        rateAndBases.add(mtdcBudgetRateAndBase);

        BudgetRateAndBase laSalBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(0L), "10", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.LA_SALARIES.getRateClassType());
        rateAndBases.add(laSalBudgetRateAndBase);

        BudgetRateAndBase laMSBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(0L), "11", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType());
        rateAndBases.add(laMSBudgetRateAndBase);
        BudgetRateAndBase laUtilBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(0L), "12", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType());
        rateAndBases.add(laUtilBudgetRateAndBase);
        BudgetRateAndBase ebLABudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(0L), "5", "3", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(100L), RateClassType.LAB_ALLOCATION.getRateClassType());
        rateAndBases.add(ebLABudgetRateAndBase);
        BudgetRateAndBase vacLABudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(0L), "8", "2", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(100L), RateClassType.VACATION.getRateClassType());
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
                new ScaleTwoDecimal(1000L), RateClassType.OVERHEAD.getRateClassType());
        rateAndBases.add(mtdcBudgetRateAndBase);

        BudgetRateAndBase laSalBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "10", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.LA_SALARIES.getRateClassType());
        rateAndBases.add(laSalBudgetRateAndBase);

        BudgetRateAndBase laMSBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "11", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType());
        rateAndBases.add(laMSBudgetRateAndBase);
        BudgetRateAndBase laUtilBudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(10L), "12", "1", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(1000L), RateClassType.LAB_ALLOCATION.getRateClassType());
        rateAndBases.add(laUtilBudgetRateAndBase);
        BudgetRateAndBase ebLABudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(24L), "5", "3", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(100L), RateClassType.LAB_ALLOCATION.getRateClassType());
        rateAndBases.add(ebLABudgetRateAndBase);
        BudgetRateAndBase vacLABudgetRateAndBase = getNewBudgetRateAndBase(new ScaleTwoDecimal(9L), "8", "2", new ScaleTwoDecimal(0L),
                new ScaleTwoDecimal(100L), RateClassType.VACATION.getRateClassType());
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
        budgetPeriod.getBudgetLineItems().add(getPersonnelLineItem(budgetPeriod));
        budgetPeriod.getBudgetLineItem(0).setBudgetCategoryCode("P");
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
        budgetPeriod.getBudgetLineItems().add(getPersonnelLineItem(budgetPeriod));
        budgetPeriod.getBudgetLineItem(0).setBudgetCategoryCode("P");
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
        budgetPeriod.getBudgetLineItems().add(getPersonnelLineItem(budgetPeriod));
        budgetPeriod.getBudgetLineItem(0).setBudgetCategoryCode("P");
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

    protected BudgetPersonnelRateAndBase getNewBudgetPersonnelRateAndBase(ScaleTwoDecimal applicableRate, String rateClassCode, String rateTypeCode,
                                                                          ScaleTwoDecimal calculatedCost, ScaleTwoDecimal baseCost, String rateClassTypeCode) {
    BudgetPersonnelRateAndBase rateAndBase = new BudgetPersonnelRateAndBaseMock();
        rateAndBase.setRateClassCode(rateClassCode);
        rateAndBase.setRateTypeCode(rateTypeCode);
        rateAndBase.setAppliedRate(applicableRate);
        rateAndBase.setCalculatedCost(calculatedCost);
        rateAndBase.setSalaryRequested(baseCost);
        rateAndBase.setRateClass(new RateClass());
        rateAndBase.getRateClass().setRateClassTypeCode(rateClassTypeCode);
        return rateAndBase;
    }

    protected BudgetPersonnelDetails getPersonnelDetails() {
        BudgetPersonnelDetails details = new BudgetPersonnelDetails();
        details.setJobCode("AA000");
        details.setPercentCharged(new ScaleTwoDecimal(100L));
        details.setPercentEffort(new ScaleTwoDecimal(100L));
        details.setPersonId("1000001");
        details.setSalaryRequested(new ScaleTwoDecimal(100000L));
        return details;
    }

    protected BudgetLineItem getPersonnelLineItem(BudgetPeriod budgetPeriod) {
        BudgetLineItem lineItem = new BudgetLineItem();
        lineItem.setBudgetCategory(createBudgetCategory("26", "Test", "E"));
        lineItem.setBudgetCategoryCode("26");
        lineItem.setCostElement("400350");
        lineItem.setLineItemCost(new ScaleTwoDecimal(10000.00));
        lineItem.setEndDate(getDate(2016, 6, 30));
        lineItem.setStartDate(getDate(2015, 7, 1));
        lineItem.setCostElementBO(getCostElementTravel());
        lineItem.setApplyInRateFlag(Boolean.TRUE);
        lineItem.setOnOffCampusFlag(Boolean.TRUE);
        lineItem.setBudgetPeriodBO(budgetPeriod);
        lineItem.setBudgetPeriod(1);
        lineItem.setLineItemNumber(3);
        BudgetPersonnelDetails budgetPersonnelDetails = new BudgetPersonnelDetails();
        budgetPersonnelDetails.setLineItemNumber(3);
        budgetPersonnelDetails.setOnOffCampusFlag(Boolean.TRUE);
        budgetPersonnelDetails.setEndDate(getDate(2016, 6, 30));
        budgetPersonnelDetails.setStartDate(getDate(2015, 7, 1));
        budgetPersonnelDetails.setApplyInRateFlag(Boolean.TRUE);
        budgetPersonnelDetails.setJobCode("AA000");
        budgetPersonnelDetails.setPercentCharged(ScaleTwoDecimal.ONE_HUNDRED);
        budgetPersonnelDetails.setPercentEffort(ScaleTwoDecimal.ONE_HUNDRED);
        budgetPersonnelDetails.setPersonId("10000000002");
        budgetPersonnelDetails.setSalaryRequested(new ScaleTwoDecimal(102000L));
        budgetPersonnelDetails.setCostElement("400350");
        budgetPersonnelDetails.setCostElementBO(getCostElementPersonnel());
        budgetPersonnelDetails.setBudgetLineItem(lineItem);
        budgetPersonnelDetails.setBudgetPeriod(1);
        budgetPersonnelDetails.setBudgetPeriodBO(budgetPeriod);
        lineItem.getBudgetPersonnelDetailsList().add(budgetPersonnelDetails);
        lineItem.setCostElementBO(getCostElementPersonnel());
        return lineItem;
    }

    protected CostElement getCostElementPersonnel() {
        CostElement costElement = new CostElement();
        costElement.setCostElement("400350");
        costElement.setBudgetCategoryCode("26");
        costElement.setDescription("Research Staff - On");
        costElement.setOnOffCampusFlag(Boolean.TRUE);
        return costElement;
    }

    protected BudgetRateAndBase getNewBudgetRateAndBase(ScaleTwoDecimal applicableRate, String rateClassCode, String rateTypeCode,
                                                        ScaleTwoDecimal calculatedCost, ScaleTwoDecimal baseCost, String rateClassType) {
        BudgetRateAndBaseMock rateAndBase = new BudgetRateAndBaseMock();
        rateAndBase.setCalculatedCost(calculatedCost);
        rateAndBase.setBaseCost(baseCost);
        rateAndBase.setRateClassCode(rateClassCode);
        rateAndBase.setRateTypeCode(rateTypeCode);
        rateAndBase.setAppliedRate(applicableRate);
        rateAndBase.setStartDate(getDate(2016, 1, 1));
        rateAndBase.setEndDate(getDate(2016, 1, 30));
        rateAndBase.setOnOffCampusFlag(Boolean.TRUE);
        RateClass rateClass = new RateClass();
        rateAndBase.setRateClass(rateClass);
        rateAndBase.getRateClass().setRateClassTypeCode(rateClassType);

        return rateAndBase;
    }

    protected BudgetLineItemCalculatedAmount getBudgetLineItemCalculatedAmount(String rateClassCode, String rateTypeCode, String rateTypeDescription, ScaleTwoDecimal calculatedCost) {
        BudgetLineItemCalculatedAmount lineItemCalculatedAmount1 = new BudgetLineItemCalculatedAmount();
        lineItemCalculatedAmount1.setApplyRateFlag(Boolean.TRUE);
        lineItemCalculatedAmount1.setRateClassCode(rateClassCode);
        lineItemCalculatedAmount1.setRateTypeCode(rateTypeCode);
        lineItemCalculatedAmount1.setRateTypeDescription(rateTypeDescription);
        lineItemCalculatedAmount1.setCalculatedCost(calculatedCost);
        return lineItemCalculatedAmount1;
    }

    protected BudgetPeriod getBudgetPeriod() {
        BudgetPeriod budgetPeriod = new BudgetPeriod();
        budgetPeriod.setEndDate(getDate(2016, 6, 30));
        budgetPeriod.setStartDate(getDate(2015, 7, 1));
        final BudgetLineItem budgetLineItem = getTravelLineItem(budgetPeriod);
        budgetLineItem.setBudgetPeriodBO(budgetPeriod);
        budgetLineItem.setBudgetPeriod(1);
        budgetLineItem.setLineItemNumber(1);
        budgetPeriod.getBudgetLineItems().add(budgetLineItem);
        return  budgetPeriod;
    }

    private java.sql.Date getDate(Integer year, Integer month, Integer day) {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.YEAR, year);
        cal.set(cal.MONTH, month);
        cal.set(cal.DATE, day);
        cal.set(cal.HOUR_OF_DAY, 0);
        cal.set(cal.MINUTE, 0);
        cal.set(cal.SECOND, 0);
        cal.set(cal.MILLISECOND, 0);
        return new java.sql.Date(cal.getTime().getTime());
    }

    protected BudgetLineItem getTravelLineItem(BudgetPeriod budgetPeriod) {
        BudgetLineItem lineItem = new BudgetLineItem();
        lineItem.setBudgetCategory(createBudgetCategory("20", "Test", "E"));
        lineItem.setBudgetCategoryCode("20");
        lineItem.setCostElement("420050");
        lineItem.setLineItemCost(new ScaleTwoDecimal(10000.00));
        lineItem.setEndDate(getDate(2016, 6, 30));
        lineItem.setStartDate(getDate(2015, 7, 1));
        lineItem.setCostElementBO(getCostElementTravel());
        lineItem.setApplyInRateFlag(Boolean.TRUE);
        lineItem.setOnOffCampusFlag(Boolean.TRUE);
        lineItem.setBudgetPeriodBO(budgetPeriod);
        lineItem.setBudgetPeriod(1);
        lineItem.setLineItemNumber(2);
        return lineItem;
    }

    private BudgetCategory createBudgetCategory(String code, String description, String budgetCategoryTypeCode) {
        BudgetCategory category = new BudgetCategory();
        category.setCode(code);
        category.setDescription(description);
        category.setBudgetCategoryTypeCode(budgetCategoryTypeCode);
        return category;
    }

    protected CostElement getCostElementTravel() {
        CostElement costElement = new CostElement();
        costElement.setCostElement("420050");
        costElement.setBudgetCategoryCode("20");
        costElement.setDescription("Travel");
        costElement.setOnOffCampusFlag(Boolean.TRUE);
        return costElement;
    }
}
