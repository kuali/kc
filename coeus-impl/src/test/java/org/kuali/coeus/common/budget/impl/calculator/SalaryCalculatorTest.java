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
package org.kuali.coeus.common.budget.impl.calculator;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.coeus.common.budget.api.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.AppointmentType;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.rate.RateClass;
import org.kuali.coeus.common.budget.framework.rate.ValidCeRateType;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.core.impl.datetime.DateTimeServiceImpl;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.stream.Stream;

public class SalaryCalculatorTest {

    public static final String RATE_CLASS_CODE = "7";
    public static final String RATE_TYPE_CODE = "1";
    public static final String COST_ELEMENT = "1234";
    public static final String JOB_CODE = "A01";
    public static final int PERSON_SEQUENCE_NUMBER = 1;

    private boolean isAnniversarySalaryDateEnabled;

    public class MockDateTimeService extends DateTimeServiceImpl {
        @Override
        public String toDateString(java.util.Date date) {
            return toString(date, "MM/dd/yyyy");
        }

        @Override
        public java.sql.Date convertToSqlDate(String dateString)
                throws ParseException {
            if (StringUtils.isBlank(dateString)) {
                throw new IllegalArgumentException("invalid (blank) dateString");
            }
            java.util.Date date = parseAgainstFormatArray(dateString, new String[]{"MM/dd/yyyy"});
            return new java.sql.Date(date.getTime());
        }
    }

    public class MockSalaryCalculator extends SalaryCalculator {
        public MockSalaryCalculator(Budget budget, BudgetPersonnelDetails personnelLineItem) throws Exception {
            super(budget, personnelLineItem);
            this.setDateTimeService(new MockDateTimeService());
        }

        @Override
        protected boolean isAnniversarySalaryDateEnabled() {
            return isAnniversarySalaryDateEnabled;
        }

        @Override
        protected void populateAppointmentType(BudgetPerson budgetPerson) {
            //no-op
        }
    }

    public Date createDateFromString(String date) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return new Date(dateFormat.parse(date).getTime());
    }

    public BudgetRate createBudgetRate(String startDate, int rate) throws Exception {
        BudgetRate budgetRate = new BudgetRate();
        budgetRate.setRateClassCode(RATE_CLASS_CODE);
        budgetRate.setRateTypeCode(RATE_TYPE_CODE);
        budgetRate.setStartDate(createDateFromString(startDate));
        budgetRate.setOnOffCampusFlag(true);
        budgetRate.setApplicableRate(new ScaleTwoDecimal(rate));
        return budgetRate;
    }

    public BudgetPerson createBudgetPerson(String personId, String effectiveDate, double calculationBase, double appointmentDuration, String anniversaryDate) throws Exception {
        BudgetPerson budgetPerson = new BudgetPerson();
        budgetPerson.setPersonId(personId);
        budgetPerson.setEffectiveDate(createDateFromString(effectiveDate));
        budgetPerson.setCalculationBase(new ScaleTwoDecimal(calculationBase));
        budgetPerson.setAppointmentType(createAppointmentType(new ScaleTwoDecimal(appointmentDuration)));
        budgetPerson.setPersonSequenceNumber(PERSON_SEQUENCE_NUMBER);
        if (anniversaryDate != null) {
            budgetPerson.setSalaryAnniversaryDate(createDateFromString(anniversaryDate));
        }
        return budgetPerson;
    }

    public AppointmentType createAppointmentType(ScaleTwoDecimal duration) {
        AppointmentType appointmentType = new AppointmentType();
        appointmentType.setDuration(duration);
        return appointmentType;
    }

    public BudgetPersonnelDetails createBudgetPersonnelDetails(int budgetPeriod, BudgetPerson budgetPerson, String startDate, String endDate) throws Exception {
        BudgetPersonnelDetails budgetPersonnelDetails = new BudgetPersonnelDetails();
        budgetPersonnelDetails.setBudgetPerson(budgetPerson);
        budgetPersonnelDetails.setCostElement(COST_ELEMENT);
        budgetPersonnelDetails.setApplyInRateFlag(true);
        budgetPersonnelDetails.setStartDate(createDateFromString(startDate));
        budgetPersonnelDetails.setEndDate(createDateFromString(endDate));
        budgetPersonnelDetails.setPersonId(budgetPerson.getPersonId());
        budgetPersonnelDetails.setPersonSequenceNumber(PERSON_SEQUENCE_NUMBER);
        budgetPersonnelDetails.setJobCode(JOB_CODE);
        budgetPersonnelDetails.setPercentCharged(new ScaleTwoDecimal(100));
        budgetPersonnelDetails.setPercentEffort(new ScaleTwoDecimal(100));
        budgetPersonnelDetails.setBudgetPeriod(budgetPeriod);
        return budgetPersonnelDetails;
    }

    public CostElement createCostElement() {
        CostElement costElement = new CostElement();
        costElement.setOnOffCampusFlag(true);
        costElement.getValidCeRateTypes().add(createValidCeRateType());
        costElement.setCostElement(COST_ELEMENT);
        return costElement;
    }

    public ValidCeRateType createValidCeRateType() {
        ValidCeRateType validCeRateType = new ValidCeRateType();
        validCeRateType.setRateClass(createRateClass());
        validCeRateType.setRateClassCode(RATE_CLASS_CODE);
        validCeRateType.setRateTypeCode(RATE_TYPE_CODE);
        return validCeRateType;
    }

    public RateClass createRateClass() {
        RateClass rateClass = new RateClass();
        rateClass.setRateClassTypeCode(RateClassType.INFLATION.getRateClassType());
        return rateClass;
    }

    public BudgetLineItem createBudgetLineItem(String startDate, String endDate, BudgetPersonnelDetails... details) throws Exception {
        BudgetLineItem budgetLineItem = new BudgetLineItem();
        budgetLineItem.setStartDate(createDateFromString(startDate));
        budgetLineItem.setEndDate(createDateFromString(endDate));
        budgetLineItem.setBudgetPersonnelDetailsList(Arrays.asList(details));
        budgetLineItem.setCostElementBO(createCostElement());
        Stream.of(details).forEach(budgetPersonnelDetail -> budgetPersonnelDetail.setBudgetLineItem(budgetLineItem));
        return budgetLineItem;
    }

    public BudgetPeriod createBudgetPeriod(int budgetPeriodNumber, String startDate, String endDate, BudgetLineItem... budgetLineItems) throws Exception {
        BudgetPeriod budgetPeriod = new BudgetPeriod();
        budgetPeriod.setBudgetPeriod(budgetPeriodNumber);
        budgetPeriod.setStartDate(createDateFromString(startDate));
        budgetPeriod.setEndDate(createDateFromString(endDate));
        budgetPeriod.setBudgetLineItems(Arrays.asList(budgetLineItems));
        return budgetPeriod;
    }

    public Budget createBudget(String startDate, String endDate, BudgetPerson budgetPerson, BudgetRate... budgetRates) throws Exception {
        Budget budget = new Budget();
        budget.setStartDate(createDateFromString(startDate));
        budget.setEndDate(createDateFromString(endDate));
        budget.getBudgetPersons().add(budgetPerson);
        budget.getBudgetRates().addAll(Arrays.asList(budgetRates));


        return budget;
    }

    ScaleTwoDecimal getCalculateSalary(Budget budget, BudgetPersonnelDetails budgetPersonnelDetails) throws Exception {
        SalaryCalculator salaryCalculator = new MockSalaryCalculator(budget, budgetPersonnelDetails);
        salaryCalculator.calculate();
        return budgetPersonnelDetails.getSalaryRequested();
    }

    @Test
    public void noAnniversary_calendarYear_inflationMiddle() throws Exception {
        isAnniversarySalaryDateEnabled = false;
        BudgetPerson budgetPerson = createBudgetPerson("1", "01/01/2017", 100000, 12, null);

        Budget budget = createBudget("01/01/2017", "12/31/2018", budgetPerson,
                createBudgetRate("07/01/2016", 3),
                createBudgetRate("07/01/2017", 3),
                createBudgetRate("07/01/2018", 3));


        BudgetPersonnelDetails budgetPersonnelDetails1 = createBudgetPersonnelDetails(1, budgetPerson, "01/01/2017", "12/31/2017");
        BudgetPersonnelDetails budgetPersonnelDetails2 = createBudgetPersonnelDetails(2, budgetPerson, "01/01/2018", "12/31/2018");
        BudgetPersonnelDetails budgetPersonnelDetails3 = createBudgetPersonnelDetails(3, budgetPerson, "01/01/2019", "12/31/2019");
        BudgetPersonnelDetails budgetPersonnelDetails4 = createBudgetPersonnelDetails(4, budgetPerson, "01/01/2020", "12/31/2020");


        BudgetLineItem budgetLineItem1 = createBudgetLineItem("01/01/2017", "12/31/2017", budgetPersonnelDetails1);
        BudgetLineItem budgetLineItem2 = createBudgetLineItem("01/01/2018", "12/31/2018", budgetPersonnelDetails2);
        BudgetLineItem budgetLineItem3 = createBudgetLineItem("01/01/2019", "12/31/2019", budgetPersonnelDetails3);
        BudgetLineItem budgetLineItem4 = createBudgetLineItem("01/01/2020", "12/31/2020", budgetPersonnelDetails4);

        budget.getBudgetPeriods().add(createBudgetPeriod(1, "01/01/2017", "12/31/2017", budgetLineItem1));
        budget.getBudgetPeriods().add(createBudgetPeriod(2, "01/01/2018", "12/31/2018", budgetLineItem2));
        budget.getBudgetPeriods().add(createBudgetPeriod(3, "01/01/2019", "12/31/2019", budgetLineItem3));
        budget.getBudgetPeriods().add(createBudgetPeriod(4, "01/01/2020", "12/31/2020", budgetLineItem4));

        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails1), new ScaleTwoDecimal(101500));
        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails2), new ScaleTwoDecimal(104545));
        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails3), new ScaleTwoDecimal(106090));
        //no inflation rate for this year so in shouldn't inflate
        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails4), new ScaleTwoDecimal(106090));
    }

    @Test
    public void noAnniversary_calendarYearSplitOnInflationDate_inflationMiddle() throws Exception {
        isAnniversarySalaryDateEnabled = false;
        BudgetPerson budgetPerson = createBudgetPerson("1", "01/01/2017", 100000, 12, null);

        Budget budget = createBudget("01/01/2017", "12/31/2018", budgetPerson,
                createBudgetRate("07/01/2016", 3),
                createBudgetRate("07/01/2017", 3),
                createBudgetRate("07/01/2018", 3));


        BudgetPersonnelDetails budgetPersonnelDetails1 = createBudgetPersonnelDetails(1, budgetPerson, "01/01/2017", "06/30/2017");
        BudgetPersonnelDetails budgetPersonnelDetails2 = createBudgetPersonnelDetails(1, budgetPerson, "07/01/2017", "12/31/2017");
        BudgetPersonnelDetails budgetPersonnelDetails3 = createBudgetPersonnelDetails(2, budgetPerson, "01/01/2018", "06/30/2018");
        BudgetPersonnelDetails budgetPersonnelDetails4 = createBudgetPersonnelDetails(2, budgetPerson, "07/01/2018", "12/31/2018");


        BudgetLineItem budgetLineItem1 = createBudgetLineItem("01/01/2017", "12/31/2017",
                budgetPersonnelDetails1,
                budgetPersonnelDetails2);
        BudgetLineItem budgetLineItem2 = createBudgetLineItem("01/01/2018", "12/31/2018",
                budgetPersonnelDetails3,
                budgetPersonnelDetails4);


        budget.getBudgetPeriods().add(createBudgetPeriod(1, "01/01/2017", "12/31/2017", budgetLineItem1));
        budget.getBudgetPeriods().add(createBudgetPeriod(2, "01/01/2018", "12/31/2018", budgetLineItem2));

        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails1), new ScaleTwoDecimal(50000));
        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails2), new ScaleTwoDecimal(51500));
        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails3), new ScaleTwoDecimal(51500));
        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails4), new ScaleTwoDecimal(53045));
    }

    @Test
    public void noAnniversary_calendarYear_inflationStart() throws Exception {
        isAnniversarySalaryDateEnabled = false;
        BudgetPerson budgetPerson = createBudgetPerson("1", "01/01/2017", 100000, 12, null);

        Budget budget = createBudget("01/01/2017", "12/31/2018", budgetPerson,
                createBudgetRate("01/01/2016", 3),
                createBudgetRate("01/01/2017", 3),
                createBudgetRate("01/01/2018", 3),
                createBudgetRate("01/01/2019", 3));


        BudgetPersonnelDetails budgetPersonnelDetails1 = createBudgetPersonnelDetails(1, budgetPerson, "01/01/2017", "12/31/2017");
        BudgetPersonnelDetails budgetPersonnelDetails2 = createBudgetPersonnelDetails(2, budgetPerson, "01/01/2018", "12/31/2018");
        BudgetPersonnelDetails budgetPersonnelDetails3 = createBudgetPersonnelDetails(3, budgetPerson, "01/01/2019", "12/31/2019");
        BudgetPersonnelDetails budgetPersonnelDetails4 = createBudgetPersonnelDetails(4, budgetPerson, "01/01/2020", "12/31/2020");


        BudgetLineItem budgetLineItem1 = createBudgetLineItem("01/01/2017", "12/31/2017", budgetPersonnelDetails1);
        BudgetLineItem budgetLineItem2 = createBudgetLineItem("01/01/2018", "12/31/2018", budgetPersonnelDetails2);
        BudgetLineItem budgetLineItem3 = createBudgetLineItem("01/01/2019", "12/31/2019", budgetPersonnelDetails3);
        BudgetLineItem budgetLineItem4 = createBudgetLineItem("01/01/2020", "12/31/2020", budgetPersonnelDetails4);

        budget.getBudgetPeriods().add(createBudgetPeriod(1, "01/01/2017", "12/31/2017", budgetLineItem1));
        budget.getBudgetPeriods().add(createBudgetPeriod(2, "01/01/2018", "12/31/2018", budgetLineItem2));
        budget.getBudgetPeriods().add(createBudgetPeriod(3, "01/01/2019", "12/31/2019", budgetLineItem3));
        budget.getBudgetPeriods().add(createBudgetPeriod(4, "01/01/2020", "12/31/2020", budgetLineItem4));

        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails1), new ScaleTwoDecimal(100000));
        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails2), new ScaleTwoDecimal(103000));
        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails3), new ScaleTwoDecimal(106090));
        //no inflation rate for this year so in shouldn't inflate
        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails4), new ScaleTwoDecimal(106090));
    }

    @Test
    public void noAnniversary_calendarYearSplitOnInflationDate_inflationBegin() throws Exception {
        isAnniversarySalaryDateEnabled = false;
        BudgetPerson budgetPerson = createBudgetPerson("1", "01/01/2017", 100000, 12, null);

        Budget budget = createBudget("01/01/2017", "12/31/2018", budgetPerson,
                createBudgetRate("01/01/2016", 3),
                createBudgetRate("01/01/2017", 3),
                createBudgetRate("01/01/2018", 3));


        BudgetPersonnelDetails budgetPersonnelDetails1 = createBudgetPersonnelDetails(1, budgetPerson, "01/01/2017", "06/30/2017");
        BudgetPersonnelDetails budgetPersonnelDetails2 = createBudgetPersonnelDetails(1, budgetPerson, "07/01/2017", "12/31/2017");
        BudgetPersonnelDetails budgetPersonnelDetails3 = createBudgetPersonnelDetails(2, budgetPerson, "01/01/2018", "06/30/2018");
        BudgetPersonnelDetails budgetPersonnelDetails4 = createBudgetPersonnelDetails(2, budgetPerson, "07/01/2018", "12/31/2018");


        BudgetLineItem budgetLineItem1 = createBudgetLineItem("01/01/2017", "12/31/2017",
                budgetPersonnelDetails1,
                budgetPersonnelDetails2);
        BudgetLineItem budgetLineItem2 = createBudgetLineItem("01/01/2018", "12/31/2018",
                budgetPersonnelDetails3,
                budgetPersonnelDetails4);


        budget.getBudgetPeriods().add(createBudgetPeriod(1, "01/01/2017", "12/31/2017", budgetLineItem1));
        budget.getBudgetPeriods().add(createBudgetPeriod(2, "01/01/2018", "12/31/2018", budgetLineItem2));

        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails1), new ScaleTwoDecimal(50000));
        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails2), new ScaleTwoDecimal(50000));
        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails3), new ScaleTwoDecimal(51500));
        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails4), new ScaleTwoDecimal(51500));
    }

    @Test
    public void anniversay_calendarYearSplitOnInflationDate_anniversaryMiddle() throws Exception {
        isAnniversarySalaryDateEnabled = true;
        BudgetPerson budgetPerson = createBudgetPerson("1", "01/01/2017", 100000, 12, "07/01/2000");

        Budget budget = createBudget("01/01/2017", "12/31/2018", budgetPerson,
                createBudgetRate("01/01/2016", 3),
                createBudgetRate("01/01/2017", 3),
                createBudgetRate("01/01/2018", 3));


        BudgetPersonnelDetails budgetPersonnelDetails1 = createBudgetPersonnelDetails(1, budgetPerson, "01/01/2017", "12/31/2017");
        BudgetPersonnelDetails budgetPersonnelDetails2 = createBudgetPersonnelDetails(2, budgetPerson, "01/01/2018", "12/31/2018");


        BudgetLineItem budgetLineItem1 = createBudgetLineItem("01/01/2017", "12/31/2017",
                budgetPersonnelDetails1);
        BudgetLineItem budgetLineItem2 = createBudgetLineItem("01/01/2018", "12/31/2018",
                budgetPersonnelDetails2);


        budget.getBudgetPeriods().add(createBudgetPeriod(1, "01/01/2017", "12/31/2017", budgetLineItem1));
        budget.getBudgetPeriods().add(createBudgetPeriod(2, "01/01/2018", "12/31/2018", budgetLineItem2));

        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails1), new ScaleTwoDecimal(101500));
        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails2), new ScaleTwoDecimal(104545));
    }

    @Test
    public void anniversay_calendarYearSplitOnInflationDate_anniversaryBegin() throws Exception {
        isAnniversarySalaryDateEnabled = true;
        BudgetPerson budgetPerson = createBudgetPerson("1", "01/01/2017", 100000, 12, "01/01/2000");

        Budget budget = createBudget("01/01/2017", "12/31/2018", budgetPerson,
                createBudgetRate("07/01/2016", 3),
                createBudgetRate("07/01/2017", 3),
                createBudgetRate("07/01/2018", 3),
                createBudgetRate("07/01/2018", 3));


        BudgetPersonnelDetails budgetPersonnelDetails1 = createBudgetPersonnelDetails(1, budgetPerson, "01/01/2017", "12/31/2017");
        BudgetPersonnelDetails budgetPersonnelDetails2 = createBudgetPersonnelDetails(2, budgetPerson, "01/01/2018", "12/31/2018");
        BudgetPersonnelDetails budgetPersonnelDetails3 = createBudgetPersonnelDetails(3, budgetPerson, "01/01/2019", "12/31/2019");


        BudgetLineItem budgetLineItem1 = createBudgetLineItem("01/01/2017", "12/31/2017",
                budgetPersonnelDetails1);
        BudgetLineItem budgetLineItem2 = createBudgetLineItem("01/01/2018", "12/31/2018",
                budgetPersonnelDetails2);
        BudgetLineItem budgetLineItem3 = createBudgetLineItem("01/01/2019", "12/31/2019",
                budgetPersonnelDetails3);


        budget.getBudgetPeriods().add(createBudgetPeriod(1, "01/01/2017", "12/31/2017", budgetLineItem1));
        budget.getBudgetPeriods().add(createBudgetPeriod(2, "01/01/2018", "12/31/2018", budgetLineItem2));
        budget.getBudgetPeriods().add(createBudgetPeriod(3, "01/01/2019", "12/31/2019", budgetLineItem3));

        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails1), new ScaleTwoDecimal(100000));
        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails2), new ScaleTwoDecimal(103000));
        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails3), new ScaleTwoDecimal(106090));
    }

    public void juneRateStartDateTest(BudgetPerson budgetPerson, ScaleTwoDecimal period1Expected, ScaleTwoDecimal period2Expected, ScaleTwoDecimal period3Expected) throws Exception {
        Budget budget = createBudget("01/01/2016", "12/31/2018", budgetPerson,
                createBudgetRate("06/01/2015", 4),
                createBudgetRate("06/01/2016", 4),
                createBudgetRate("06/01/2017", 4),
                createBudgetRate("06/01/2018", 4),
                createBudgetRate("06/01/2019", 4),
                createBudgetRate("06/01/2020", 4));


        BudgetPersonnelDetails budgetPersonnelDetails1 = createBudgetPersonnelDetails(1, budgetPerson, "07/01/2016", "7/31/2016");
        BudgetPersonnelDetails budgetPersonnelDetails2 = createBudgetPersonnelDetails(2, budgetPerson, "07/01/2017", "07/31/2017");
        BudgetPersonnelDetails budgetPersonnelDetails3 = createBudgetPersonnelDetails(3, budgetPerson, "07/01/2018", "07/31/2018");


        BudgetLineItem budgetLineItem1 = createBudgetLineItem("01/01/2016", "12/31/2016",
                budgetPersonnelDetails1);
        BudgetLineItem budgetLineItem2 = createBudgetLineItem("01/01/2017", "12/31/2017",
                budgetPersonnelDetails2);
        BudgetLineItem budgetLineItem3 = createBudgetLineItem("01/01/2018", "12/31/2018",
                budgetPersonnelDetails3);


        budget.getBudgetPeriods().add(createBudgetPeriod(1, "01/01/2016", "12/31/2016", budgetLineItem1));
        budget.getBudgetPeriods().add(createBudgetPeriod(2, "01/01/2017", "12/31/2017", budgetLineItem2));
        budget.getBudgetPeriods().add(createBudgetPeriod(3, "01/01/2018", "12/31/2018", budgetLineItem3));

        Assert.assertEquals(period1Expected, getCalculateSalary(budget, budgetPersonnelDetails1));
        Assert.assertEquals(period2Expected, getCalculateSalary(budget, budgetPersonnelDetails2));
        Assert.assertEquals(period3Expected, getCalculateSalary(budget, budgetPersonnelDetails3));
    }

    @Test
    public void anniversaryDateBeforeStartAndAfterEffective9m() throws Exception {
        isAnniversarySalaryDateEnabled = true;
        juneRateStartDateTest(createBudgetPerson("1", "01/01/2016", 90000, 9, "06/01/2010"),
                new ScaleTwoDecimal(10400),
                new ScaleTwoDecimal(10816),
                new ScaleTwoDecimal(11248.64));
    }

    @Test
    public void anniversaryDateEqualToEffective9m() throws Exception {
        isAnniversarySalaryDateEnabled = true;
        juneRateStartDateTest(createBudgetPerson("1", "06/01/2016", 90000, 9, "06/01/2000"),
                new ScaleTwoDecimal(10000),
                new ScaleTwoDecimal(10400),
                new ScaleTwoDecimal(10816));
    }

    @Test
    public void anniversaryDateEqualToEffective9m2() throws Exception {
        isAnniversarySalaryDateEnabled = true;
        juneRateStartDateTest(createBudgetPerson("1", "01/01/2016", 90000, 9, "01/01/2010"),
                new ScaleTwoDecimal(10000),
                new ScaleTwoDecimal(10400),
                new ScaleTwoDecimal(10816));
    }

    @Test
    public void anniversaryDateEqualToEffective9m3() throws Exception {
        isAnniversarySalaryDateEnabled = true;
        juneRateStartDateTest(createBudgetPerson("1", "03/01/2016", 90000, 9, "03/01/2015"),
                new ScaleTwoDecimal(10000),
                new ScaleTwoDecimal(10400),
                new ScaleTwoDecimal(10816));
    }

    @Test
    public void anniversaryDateEqualsStartAndAfterEffective9m() throws Exception {
        isAnniversarySalaryDateEnabled = true;
        juneRateStartDateTest(createBudgetPerson("1", "01/01/2016", 90000, 9, "07/01/2010"),
                new ScaleTwoDecimal(10400),
                new ScaleTwoDecimal(10816),
                new ScaleTwoDecimal(11248.64));
    }

    @Test
    public void anniversaryDateAfterEndDate9m() throws Exception {
        isAnniversarySalaryDateEnabled = true;
        juneRateStartDateTest(createBudgetPerson("1", "07/01/2016", 90000, 9, "12/09/2000"),
                new ScaleTwoDecimal(10000),
                new ScaleTwoDecimal(10400),
                new ScaleTwoDecimal(10816));
    }

    public void januaryRateStartDateTest(BudgetPerson budgetPerson, ScaleTwoDecimal period1Expected, ScaleTwoDecimal period2Expected, ScaleTwoDecimal period3Expected) throws Exception {
        Budget budget = createBudget("01/01/2016", "12/31/2018", budgetPerson,
                createBudgetRate("01/01/2015", 4),
                createBudgetRate("01/01/2016", 4),
                createBudgetRate("01/01/2017", 4),
                createBudgetRate("01/01/2018", 4),
                createBudgetRate("01/01/2019", 4),
                createBudgetRate("01/01/2020", 4));


        BudgetPersonnelDetails budgetPersonnelDetails1 = createBudgetPersonnelDetails(1, budgetPerson, "07/01/2016", "7/31/2016");
        BudgetPersonnelDetails budgetPersonnelDetails2 = createBudgetPersonnelDetails(2, budgetPerson, "07/01/2017", "07/31/2017");
        BudgetPersonnelDetails budgetPersonnelDetails3 = createBudgetPersonnelDetails(3, budgetPerson, "07/01/2018", "07/31/2018");


        BudgetLineItem budgetLineItem1 = createBudgetLineItem("01/01/2016", "12/31/2016",
                budgetPersonnelDetails1);
        BudgetLineItem budgetLineItem2 = createBudgetLineItem("01/01/2017", "12/31/2017",
                budgetPersonnelDetails2);
        BudgetLineItem budgetLineItem3 = createBudgetLineItem("01/01/2018", "12/31/2018",
                budgetPersonnelDetails3);


        budget.getBudgetPeriods().add(createBudgetPeriod(1, "01/01/2016", "12/31/2016", budgetLineItem1));
        budget.getBudgetPeriods().add(createBudgetPeriod(2, "01/01/2017", "12/31/2017", budgetLineItem2));
        budget.getBudgetPeriods().add(createBudgetPeriod(3, "01/01/2018", "12/31/2018", budgetLineItem3));

        Assert.assertEquals(period1Expected, getCalculateSalary(budget, budgetPersonnelDetails1));
        Assert.assertEquals(period2Expected, getCalculateSalary(budget, budgetPersonnelDetails2));
        Assert.assertEquals(period3Expected, getCalculateSalary(budget, budgetPersonnelDetails3));
    }

    @Test
    public void anniversaryDateEqualsEffective12m() throws Exception {
        isAnniversarySalaryDateEnabled = true;
        januaryRateStartDateTest(createBudgetPerson("1", "01/01/2016", 120000, 12, "01/01/2010"),
                new ScaleTwoDecimal(10000),
                new ScaleTwoDecimal(10400),
                new ScaleTwoDecimal(10816));
    }

    @Test
    public void anniversaryDateEqualsEffective12m2() throws Exception {
        isAnniversarySalaryDateEnabled = true;
        januaryRateStartDateTest(createBudgetPerson("1", "03/01/2016", 120000, 12, "03/01/2000"),
                new ScaleTwoDecimal(10000),
                new ScaleTwoDecimal(10400),
                new ScaleTwoDecimal(10816));
    }

    @Test
    public void anniversaryDateBeforeStartDateAfterEffective12m() throws Exception {
        isAnniversarySalaryDateEnabled = true;
        januaryRateStartDateTest(createBudgetPerson("1", "01/27/2016", 120000, 12, "06/25/2010"),
                new ScaleTwoDecimal(10400),
                new ScaleTwoDecimal(10816),
                new ScaleTwoDecimal(11248.64));
    }



    @Test
    public void brokenStuffTest() throws Exception {
        isAnniversarySalaryDateEnabled = true;
        BudgetPerson budgetPerson = createBudgetPerson("1", "07/01/2015", 80953.50, 12, null);

        Budget budget = createBudget("03/02/2016", "03/01/2020", budgetPerson,
                createBudgetRate("07/01/2015", 3),
                createBudgetRate("07/01/2016", 3),
                createBudgetRate("07/01/2017", 3),
                createBudgetRate("07/01/2018", 3),
                createBudgetRate("07/01/2019", 3),
                createBudgetRate("07/01/2020", 3));


        BudgetPersonnelDetails budgetPersonnelDetails1 = createBudgetPersonnelDetails(1, budgetPerson, "03/02/2016", "09/01/2017");
        BudgetPersonnelDetails budgetPersonnelDetails2 = createBudgetPersonnelDetails(2, budgetPerson, "09/02/2017", "03/01/2019");
        BudgetPersonnelDetails budgetPersonnelDetails3 = createBudgetPersonnelDetails(3, budgetPerson, "03/02/2019", "03/01/2020");


        BudgetLineItem budgetLineItem1 = createBudgetLineItem("03/02/2016", "09/01/2017",
                budgetPersonnelDetails1);
        BudgetLineItem budgetLineItem2 = createBudgetLineItem("09/02/2017", "03/01/2019",
                budgetPersonnelDetails2);
        BudgetLineItem budgetLineItem3 = createBudgetLineItem("03/02/2019", "03/01/2020",
                budgetPersonnelDetails3);


        budget.getBudgetPeriods().add(createBudgetPeriod(1, "03/02/2016", "09/01/2017", budgetLineItem1));
        budget.getBudgetPeriods().add(createBudgetPeriod(2, "09/02/2017", "03/01/2019", budgetLineItem2));
        budget.getBudgetPeriods().add(createBudgetPeriod(3, "03/02/2019", "03/01/2020", budgetLineItem3));

        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails1), new ScaleTwoDecimal(124701.48));
        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails2), new ScaleTwoDecimal(130542.26));
    }


    @Test
    public void brokenStuffTest2() throws Exception {
        isAnniversarySalaryDateEnabled = true;
        BudgetPerson budgetPerson = createBudgetPerson("1", "01/01/2015", 10000, 12, null);

        Budget budget = createBudget("01/01/2015", "12/31/2020", budgetPerson,
                createBudgetRate("01/01/2015", 3),
                createBudgetRate("01/01/2016", 3),
                createBudgetRate("01/01/2017", 3),
                createBudgetRate("01/01/2018", 3),
                createBudgetRate("01/01/2019", 3),
                createBudgetRate("01/01/2020", 3));


        BudgetPersonnelDetails budgetPersonnelDetails1 = createBudgetPersonnelDetails(1, budgetPerson, "01/01/2015", "12/31/2016");
        BudgetPersonnelDetails budgetPersonnelDetails2 = createBudgetPersonnelDetails(2, budgetPerson, "01/01/2017", "12/31/2018");


        BudgetLineItem budgetLineItem1 = createBudgetLineItem("01/01/2015", "12/31/2016",
                budgetPersonnelDetails1);
        BudgetLineItem budgetLineItem2 = createBudgetLineItem("01/01/2017", "12/31/2018",
                budgetPersonnelDetails2);


        budget.getBudgetPeriods().add(createBudgetPeriod(1, "01/01/2015", "12/31/2016", budgetLineItem1));
        budget.getBudgetPeriods().add(createBudgetPeriod(2, "01/01/2017", "12/31/2018", budgetLineItem2));

        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails1), new ScaleTwoDecimal(20300));
        Assert.assertEquals(getCalculateSalary(budget, budgetPersonnelDetails2), new ScaleTwoDecimal(21536.27));
    }
}