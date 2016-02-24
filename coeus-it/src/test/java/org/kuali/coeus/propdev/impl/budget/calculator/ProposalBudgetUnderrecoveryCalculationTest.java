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
package org.kuali.coeus.propdev.impl.budget.calculator;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategory;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemBase;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.query.QueryList;
import org.kuali.coeus.common.budget.framework.rate.*;
import org.kuali.coeus.common.budget.impl.calculator.BudgetCalculationServiceImpl;
import org.kuali.coeus.common.budget.impl.calculator.LineItemCalculator;
import org.kuali.coeus.common.budget.impl.calculator.PersonnelLineItemCalculator;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProposalBudgetUnderrecoveryCalculationTest extends KcIntegrationTestBase {

    public static final String OVERHEAD_RATE_CLASS_TYPE = "O";
    private Budget budget;
    public static final String MTDC_RATE_CLASS_CODE = "1";
    public static final String TDC_RATE_CLASS_CODE = "2";
    public static final String FUNSN_RATE_CLASS_CODE = "4";
    public static final String ACTIVITY_TYPE_CODE_RESEARCH = "1";
    public static final String FISCAL_YEAR_2015 = "2015";
    public static final String RATE_TYPE_CODE_2 = "2";
    public static final String RATE_TYPE_CODE_1 = "1";

    class LineItemCalculatorMock extends LineItemCalculator {
        public LineItemCalculatorMock(Budget budget, BudgetLineItem bli) {
            super(budget, bli);
        }
        protected boolean isProposalParent() {
            return false;
        }
        protected boolean performSync() {
            return false;
        }
        protected String getActivityTypeCodeFromParent() {
            return "1";
        }
        protected boolean isProposalBudget() {
            return true;
        }
        protected BudgetCalculationService getBudgetCalculationService() {
            return new BudgetCalculationServiceImplMock();
        }
    }

    class PersonnelLineItemCalculatorMock extends PersonnelLineItemCalculator {
        public PersonnelLineItemCalculatorMock(Budget budget, BudgetLineItemBase bli) {
            super(budget, bli);
        }
        protected boolean isProposalParent() {
            return false;
        }
        protected boolean isPerformSync() {
            return true;
        }
        protected String getActivityTypeCodeFromParent() {
            return "1";
        }
        protected boolean isProposalBudget() {
            return true;
        }
        protected BudgetCalculationService getBudgetCalculationService() {
            return new BudgetCalculationServiceImplMock();
        }
    }

    class BudgetCalculationServiceImplMock extends BudgetCalculationServiceImpl {
        @Override
        public void calculateBudgetLineItem(Budget budget,BudgetPersonnelDetails budgetLineItem){
            new PersonnelLineItemCalculatorMock(budget,budgetLineItem).calculate();
        }
    }


    @Before
    public void setUp() throws Exception {
        budget = new Budget();
        budget.getBudgetPeriods().add(getBudgetPeriod());
        budget.setBudgetLaRates(new ArrayList<>());
        budget.setActivityTypeCode("1");
    }

    @Test
    public void testUnderrecoveryScenarioOverheadMTDCunderrecoveryTDC() {
        budget.setOhRateClassCode(MTDC_RATE_CLASS_CODE);
        budget.setUrRateClassCode(TDC_RATE_CLASS_CODE);
        Calendar cal = Calendar.getInstance();
        final java.sql.Date july2014 = getDate(2014, cal.JULY, 1);
        Long applicableRate = 10L;
        QueryList lineItemPropRates = new QueryList<>();
        BudgetRate lineItemPropRate1 = getLineItemPropRate(TDC_RATE_CLASS_CODE, ACTIVITY_TYPE_CODE_RESEARCH, FISCAL_YEAR_2015, RATE_TYPE_CODE_1, july2014, applicableRate);
        BudgetRate lineItemPropRate2 = getLineItemPropRate(MTDC_RATE_CLASS_CODE, ACTIVITY_TYPE_CODE_RESEARCH, FISCAL_YEAR_2015, RATE_TYPE_CODE_1, getDate(2015, cal.JANUARY, 1), 56L);
        lineItemPropRates.add(lineItemPropRate1);
        lineItemPropRates.add(lineItemPropRate2);
        budget.setBudgetRates(lineItemPropRates);
        new LineItemCalculatorMock(budget,budget.getBudgetLineItems().get(0)).calculate();
        Assert.assertTrue(budget.getBudgetLineItems().get(0).getUnderrecoveryAmount().equals(new ScaleTwoDecimal(1000L)));
        addTravelLineItem(budget);
        new LineItemCalculatorMock(budget,budget.getBudgetLineItems().get(1)).calculate();
        Assert.assertTrue(budget.getBudgetLineItems().get(1).getUnderrecoveryAmount().equals(new ScaleTwoDecimal(-4600L)));
        addPersonnelLineItem(budget);
        new LineItemCalculatorMock(budget,budget.getBudgetLineItems().get(2)).calculate();
        Assert.assertTrue(budget.getBudgetLineItems().get(2).getUnderrecoveryAmount().equals(new ScaleTwoDecimal(-4600L)));

    }

    @Test
    public void testUnderrecoveryScenarioOverheadMTDCunderrecoveryMTDC() {
        budget.setOhRateClassCode(MTDC_RATE_CLASS_CODE);
        budget.setUrRateClassCode(MTDC_RATE_CLASS_CODE);
        Calendar cal = Calendar.getInstance();
        final java.sql.Date july2014 = getDate(2014, cal.JULY, 1);
        Long applicableRate = 10L;
        QueryList lineItemPropRates = new QueryList<>();
        BudgetRate lineItemPropRate2 = getLineItemPropRate(MTDC_RATE_CLASS_CODE, ACTIVITY_TYPE_CODE_RESEARCH, FISCAL_YEAR_2015, RATE_TYPE_CODE_1, getDate(2015, cal.JANUARY, 1), 56L);
        lineItemPropRates.add(lineItemPropRate2);
        budget.setBudgetRates(lineItemPropRates);
        new LineItemCalculatorMock(budget,budget.getBudgetLineItems().get(0)).calculate();
        Assert.assertTrue(budget.getBudgetLineItems().get(0).getUnderrecoveryAmount().equals(new ScaleTwoDecimal(0L)));
        addTravelLineItem(budget);
        new LineItemCalculatorMock(budget,budget.getBudgetLineItems().get(1)).calculate();
        Assert.assertTrue(budget.getBudgetLineItems().get(1).getUnderrecoveryAmount().equals(new ScaleTwoDecimal(0L)));
        addPersonnelLineItem(budget);
        new LineItemCalculatorMock(budget,budget.getBudgetLineItems().get(2)).calculate();
        Assert.assertTrue(budget.getBudgetLineItems().get(2).getUnderrecoveryAmount().equals(new ScaleTwoDecimal(0L)));

    }

    @Test
    public void testUnderrecoveryScenarioOverheadTDCunderrecoveryMTDC() {
        budget.setOhRateClassCode(TDC_RATE_CLASS_CODE);
        budget.setUrRateClassCode(MTDC_RATE_CLASS_CODE);
        Calendar cal = Calendar.getInstance();
        final java.sql.Date july2014 = getDate(2014, cal.JULY, 1);
        Long applicableRate = 10L;
        QueryList lineItemPropRates = new QueryList<>();
        BudgetRate lineItemPropRate1 = getLineItemPropRate(TDC_RATE_CLASS_CODE, ACTIVITY_TYPE_CODE_RESEARCH, FISCAL_YEAR_2015, RATE_TYPE_CODE_1, july2014, applicableRate);
        BudgetRate lineItemPropRate2 = getLineItemPropRate(MTDC_RATE_CLASS_CODE, ACTIVITY_TYPE_CODE_RESEARCH, FISCAL_YEAR_2015, RATE_TYPE_CODE_1, getDate(2015, cal.JANUARY, 1), 56L);
        lineItemPropRates.add(lineItemPropRate1);
        lineItemPropRates.add(lineItemPropRate2);
        budget.setBudgetRates(lineItemPropRates);
        new LineItemCalculatorMock(budget,budget.getBudgetLineItems().get(0)).calculate();
        Assert.assertTrue(budget.getBudgetLineItems().get(0).getUnderrecoveryAmount().equals(new ScaleTwoDecimal(-1000L)));
        addTravelLineItem(budget);
        new LineItemCalculatorMock(budget,budget.getBudgetLineItems().get(1)).calculate();
        Assert.assertTrue(budget.getBudgetLineItems().get(1).getUnderrecoveryAmount().equals(new ScaleTwoDecimal(4600L)));
        addPersonnelLineItem(budget);
        new LineItemCalculatorMock(budget,budget.getBudgetLineItems().get(2)).calculate();
        Assert.assertTrue(budget.getBudgetLineItems().get(2).getUnderrecoveryAmount().equals(new ScaleTwoDecimal(4600L)));

    }

    public void testUnderrecoveryScenarioOverheadFUNSNunderrecoveryTDC() {
        budget.setOhRateClassCode(FUNSN_RATE_CLASS_CODE);
        budget.setUrRateClassCode(TDC_RATE_CLASS_CODE);
        Calendar cal = Calendar.getInstance();
        final java.sql.Date july2014 = getDate(2014, cal.JULY, 1);
        QueryList lineItemPropRates = new QueryList<>();
        BudgetRate lineItemPropRate1 = getLineItemPropRate(FUNSN_RATE_CLASS_CODE, ACTIVITY_TYPE_CODE_RESEARCH, FISCAL_YEAR_2015, RATE_TYPE_CODE_1, july2014, 56L);
        BudgetRate lineItemPropRate2 = getLineItemPropRate(TDC_RATE_CLASS_CODE, ACTIVITY_TYPE_CODE_RESEARCH, FISCAL_YEAR_2015, RATE_TYPE_CODE_1, getDate(2015, cal.JANUARY, 1), 5L);
        BudgetRate lineItemPropRate3 = getLineItemPropRate(FUNSN_RATE_CLASS_CODE, ACTIVITY_TYPE_CODE_RESEARCH, FISCAL_YEAR_2015, RATE_TYPE_CODE_2, july2014, 10L);
        lineItemPropRates.add(lineItemPropRate1);
        lineItemPropRates.add(lineItemPropRate2);
        lineItemPropRates.add(lineItemPropRate3);
        budget.setBudgetRates(lineItemPropRates);
        //new LineItemCalculatorMock(budget,budget.getBudgetLineItems().get(0)).calculate();
        //Assert.assertTrue(budget.getBudgetLineItems().get(0).getUnderrecoveryAmount().equals(new ScaleTwoDecimal(-500L)));
        budget.getBudgetPeriods().get(0).getBudgetLineItems().clear();
        addTravelLineItem(budget);
        //new LineItemCalculatorMock(budget,budget.getBudgetLineItems().get(1)).calculate();
        //Assert.assertTrue(budget.getBudgetLineItems().get(1).getUnderrecoveryAmount().equals(new ScaleTwoDecimal(-500L)));
        budget.getBudgetPeriods().get(0).getBudgetLineItems().clear();
        addPersonnelLineItem(budget);
        new LineItemCalculatorMock(budget,budget.getBudgetLineItems().get(0)).calculate();
        Assert.assertTrue(budget.getBudgetLineItems().get(0).getUnderrecoveryAmount().equals(new ScaleTwoDecimal(-5100L)));

    }

    protected BudgetRate getLineItemPropRate(String RATE_CLASS_CODE, String ACTIVITY_TYPE_CODE,
                                             String FISCAL_YEAR_2015, String RATE_TYPE_CODE, java.sql.Date july2014, Long applicableRate) {
        BudgetRate lineItemPropRate1 = new BudgetRate();
        lineItemPropRate1.setActivityTypeCode(ACTIVITY_TYPE_CODE);
        lineItemPropRate1.setApplicableRate(new ScaleTwoDecimal(applicableRate));
        lineItemPropRate1.setFiscalYear(FISCAL_YEAR_2015);
        lineItemPropRate1.setRateClassCode(RATE_CLASS_CODE);
        lineItemPropRate1.setRateTypeCode(RATE_TYPE_CODE);
        lineItemPropRate1.setRateType(getRateType(RATE_TYPE_CODE));
        lineItemPropRate1.setStartDate(july2014);
        lineItemPropRate1.setInstituteRate(new ScaleTwoDecimal(applicableRate));
        lineItemPropRate1.setActive(Boolean.TRUE);
        lineItemPropRate1.setOnOffCampusFlag(Boolean.TRUE);
        return lineItemPropRate1;
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

    protected BudgetPeriod getBudgetPeriod() {
        BudgetPeriod budgetPeriod = new BudgetPeriod();
        budgetPeriod.setEndDate(getDate(2016, 6, 30));
        budgetPeriod.setStartDate(getDate(2015, 7, 1));
        final BudgetLineItem budgetLineItem = getEquipmentNoMTDCLineItem();
        budgetLineItem.setBudgetPeriodBO(budgetPeriod);
        budgetLineItem.setBudgetPeriod(1);
        budgetLineItem.setLineItemNumber(1);
        budgetPeriod.getBudgetLineItems().add(budgetLineItem);
        return budgetPeriod;
    }

    protected void addPersonnelLineItem(Budget budget) {
        budget.getBudgetPeriods().get(0).getBudgetLineItems().add(getPersonnelLineItem(budget.getBudgetPeriods().get(0)));
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

        ValidCeRateType validCeRateTypeTDC = new ValidCeRateType();
        validCeRateTypeTDC.setCostElementBo(costElement);
        validCeRateTypeTDC.setCostElement("400350");
        validCeRateTypeTDC.setRateClassCode(TDC_RATE_CLASS_CODE);
        validCeRateTypeTDC.setRateClass(getRateClass(TDC_RATE_CLASS_CODE, "TDC"));
        validCeRateTypeTDC.setRateTypeCode(RATE_TYPE_CODE_1);
        validCeRateTypeTDC.setRateType(getRateType(RATE_TYPE_CODE_1));
        costElement.getValidCeRateTypes().add(validCeRateTypeTDC);

        ValidCeRateType validCeRateTypeFUNSN = new ValidCeRateType();
        validCeRateTypeFUNSN.setCostElementBo(costElement);
        validCeRateTypeFUNSN.setCostElement("400350");
        validCeRateTypeFUNSN.setRateClassCode(FUNSN_RATE_CLASS_CODE);
        validCeRateTypeFUNSN.setRateTypeCode(RATE_TYPE_CODE_1);
        validCeRateTypeFUNSN.setRateType(getRateType(RATE_TYPE_CODE_1));
        validCeRateTypeFUNSN.setRateClass(getRateClass(FUNSN_RATE_CLASS_CODE, "FUNSN"));
        costElement.getValidCeRateTypes().add(validCeRateTypeFUNSN);

        ValidCeRateType validCeRateTypeMTDC = new ValidCeRateType();
        validCeRateTypeMTDC.setCostElementBo(costElement);
        validCeRateTypeMTDC.setCostElement("400350");
        validCeRateTypeMTDC.setRateClassCode(MTDC_RATE_CLASS_CODE);
        validCeRateTypeMTDC.setRateTypeCode(RATE_TYPE_CODE_1);
        validCeRateTypeMTDC.setRateType(getRateType(RATE_TYPE_CODE_1));
        validCeRateTypeMTDC.setRateClass(getRateClass(MTDC_RATE_CLASS_CODE, "MTDC"));
        costElement.getValidCeRateTypes().add(validCeRateTypeMTDC);


        return costElement;
    }

    protected void addTravelLineItem(Budget budget) {
        budget.getBudgetPeriods().get(0).getBudgetLineItems().add(getTravelLineItem(budget.getBudgetPeriods().get(0)));
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

    protected CostElement getCostElementTravel() {
        CostElement costElement = new CostElement();
        costElement.setCostElement("420050");
        costElement.setBudgetCategoryCode("20");
        costElement.setDescription("Travel");
        costElement.setOnOffCampusFlag(Boolean.TRUE);

        ValidCeRateType validCeRateTypeTDC = new ValidCeRateType();
        validCeRateTypeTDC.setCostElementBo(costElement);
        validCeRateTypeTDC.setCostElement("420050");
        validCeRateTypeTDC.setRateClassCode(TDC_RATE_CLASS_CODE);
        validCeRateTypeTDC.setRateClass(getRateClass(TDC_RATE_CLASS_CODE, "TDC"));
        validCeRateTypeTDC.setRateTypeCode(RATE_TYPE_CODE_1);
        validCeRateTypeTDC.setRateType(getRateType(RATE_TYPE_CODE_1));
        costElement.getValidCeRateTypes().add(validCeRateTypeTDC);

        ValidCeRateType validCeRateTypeFUNSN = new ValidCeRateType();
        validCeRateTypeFUNSN.setCostElementBo(costElement);
        validCeRateTypeFUNSN.setCostElement("420050");
        validCeRateTypeFUNSN.setRateClassCode(FUNSN_RATE_CLASS_CODE);
        validCeRateTypeFUNSN.setRateTypeCode(RATE_TYPE_CODE_2);
        validCeRateTypeFUNSN.setRateType(getRateType(RATE_TYPE_CODE_2));
        validCeRateTypeFUNSN.setRateClass(getRateClass(FUNSN_RATE_CLASS_CODE, "FUNSN"));
        costElement.getValidCeRateTypes().add(validCeRateTypeFUNSN);

        ValidCeRateType validCeRateTypeMTDC = new ValidCeRateType();
        validCeRateTypeMTDC.setCostElementBo(costElement);
        validCeRateTypeMTDC.setCostElement("420050");
        validCeRateTypeMTDC.setRateClassCode(MTDC_RATE_CLASS_CODE);
        validCeRateTypeMTDC.setRateTypeCode(RATE_TYPE_CODE_1);
        validCeRateTypeMTDC.setRateType(getRateType(RATE_TYPE_CODE_1));
        validCeRateTypeMTDC.setRateClass(getRateClass(MTDC_RATE_CLASS_CODE, "MTDC"));
        costElement.getValidCeRateTypes().add(validCeRateTypeMTDC);


        return costElement;
    }

    protected RateType getRateType(String rateTypeCode) {
        RateType rateType = new RateType();
        rateType.setRateTypeCode(rateTypeCode);
        return rateType;
    }

    protected BudgetLineItem getEquipmentNoMTDCLineItem() {
        BudgetLineItem lineItem = new BudgetLineItem();
        lineItem.setBudgetCategory(createBudgetCategory("20", "Test", "E"));
        lineItem.setBudgetCategoryCode("20");
        lineItem.setCostElement("421818");
        lineItem.setLineItemCost(new ScaleTwoDecimal(10000.00));
        lineItem.setEndDate(getDate(2016, 6, 30));
        lineItem.setStartDate(getDate(2015, 7, 1));
        lineItem.setCostElementBO(getCostElementEquipmentNoMTDC());
        lineItem.setApplyInRateFlag(Boolean.TRUE);
        lineItem.setOnOffCampusFlag(Boolean.TRUE);
        return lineItem;
    }

    private BudgetCategory createBudgetCategory(String code, String description, String budgetCategoryTypeCode) {
        BudgetCategory category = new BudgetCategory();
        category.setCode(code);
        category.setDescription(description);
        category.setBudgetCategoryTypeCode(budgetCategoryTypeCode);
        return category;
    }

    protected CostElement getCostElementEquipmentNoMTDC() {
        CostElement costElement = new CostElement();
        costElement.setCostElement("421818");
        costElement.setBudgetCategoryCode("20");
        costElement.setDescription("Equipment - Not MTDC");
        costElement.setOnOffCampusFlag(Boolean.TRUE);

        ValidCeRateType validCeRateTypeMTDC = new ValidCeRateType();
        validCeRateTypeMTDC.setCostElementBo(costElement);
        validCeRateTypeMTDC.setCostElement("421818");
        validCeRateTypeMTDC.setRateClassCode(TDC_RATE_CLASS_CODE);
        validCeRateTypeMTDC.setRateClass(getRateClass(TDC_RATE_CLASS_CODE, "TDC"));
        validCeRateTypeMTDC.setRateType(getRateType(RATE_TYPE_CODE_1));
        validCeRateTypeMTDC.setRateTypeCode(RATE_TYPE_CODE_1);
        costElement.getValidCeRateTypes().add(validCeRateTypeMTDC);

        ValidCeRateType validCeRateTypeFUNSN = new ValidCeRateType();
        validCeRateTypeFUNSN.setCostElementBo(costElement);
        validCeRateTypeFUNSN.setCostElement("421818");
        validCeRateTypeFUNSN.setRateClassCode(FUNSN_RATE_CLASS_CODE);
        validCeRateTypeFUNSN.setRateTypeCode(RATE_TYPE_CODE_2);
        validCeRateTypeFUNSN.setRateType(getRateType(RATE_TYPE_CODE_2));
        validCeRateTypeFUNSN.setRateClass(getRateClass(FUNSN_RATE_CLASS_CODE, "FUNSN"));
        costElement.getValidCeRateTypes().add(validCeRateTypeFUNSN);
        return costElement;
    }

    protected RateClass getRateClass(String rateClassCode, String description) {
        RateClass rateClass = new RateClass();
        rateClass.setCode(rateClassCode);
        rateClass.setDescription(description);
        rateClass.setRateClassTypeCode(OVERHEAD_RATE_CLASS_TYPE);
        RateClassType rateClassType = new RateClassType();
        rateClassType.setCode(OVERHEAD_RATE_CLASS_TYPE);
        rateClassType.setDescription("F & A");
        rateClass.setRateClassType(rateClassType);
        return  rateClass;
    }
}
