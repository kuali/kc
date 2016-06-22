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

import org.kuali.coeus.common.budget.framework.calculator.ValidCalcType;
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
import org.kuali.kra.printing.schema.ReportType;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public abstract class BudgetPrintTestBase {
	
	protected static final String PERSONNEL_CATEGORY_CODE = "P";

    class BudgetRateAndBaseMock extends BudgetRateAndBase {
        @Override
        public void refreshReferenceObject(String referenceObjectName) {
            //noop
        }
        @Override
        public void refreshNonUpdateableReferences() {
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
        ScaleTwoDecimal fringe;
        ScaleTwoDecimal calculatedCost;
        ScaleTwoDecimal calculatedCostSharing;
        ScaleTwoDecimal salary;

        protected String getRateTypeDesc(String rateClassCode, String rateTypeCode) {
            return "";
        }

        protected String getLiVacOnLaRateTypeCode() {
            return "3";
        }

        protected void addReportType(List<ReportType> reportTypeList, ReportTypeVO reportTypeVO, Date startDate, Date endDate,
                                     ScaleTwoDecimal fringe, ScaleTwoDecimal calculatedCost, ScaleTwoDecimal calculatedCostSharing,
                                     ScaleTwoDecimal salary) {
            this.fringe = fringe;
            this.calculatedCost = calculatedCost;
            this.calculatedCostSharing = calculatedCostSharing;
            this.salary = salary;

        }

        public ScaleTwoDecimal getFringe() {
            return fringe;
        }

        public ScaleTwoDecimal getCalculatedCost() {
            return calculatedCost;
        }

        public ScaleTwoDecimal getCalculatedCostSharing() {
            return calculatedCostSharing;
        }

        public ScaleTwoDecimal getSalary() {
            return salary;
        }

        @Override
        protected ValidCalcType getDependentValidRateClassTypeForLA(String rateClassType) {

            ValidCalcType validCalcType = new ValidCalcType();
            if (rateClassType.equalsIgnoreCase("E")) {
                validCalcType.setRateClassCode("5");
                validCalcType.setRateTypeCode("3");
                validCalcType.setRateClassType("E");
            }
            if (rateClassType.equalsIgnoreCase("V")) {
                validCalcType.setRateClassCode("8");
                validCalcType.setRateTypeCode("2");
                validCalcType.setRateClassType("V");
            }
            return validCalcType;
        }


    }

    class BudgetLineItemMock extends BudgetLineItem {
        @Override
        public void refreshReferenceObject(String referenceObjectName) {
            //noop
        }
    }

    protected BudgetPersonnelRateAndBase getNewBudgetPersonnelRateAndBase(ScaleTwoDecimal applicableRate, String rateClassCode, String rateTypeCode,
                                                                          ScaleTwoDecimal calculatedCost, ScaleTwoDecimal baseCost, String rateClassTypeCode, BudgetLineItem lineItem) {
        BudgetPersonnelRateAndBase rateAndBase = new BudgetPersonnelRateAndBaseMock();
        rateAndBase.setRateClassCode(rateClassCode);
        rateAndBase.setRateTypeCode(rateTypeCode);
        rateAndBase.setAppliedRate(applicableRate);
        rateAndBase.setCalculatedCost(calculatedCost);
        rateAndBase.setCalculatedCostSharing(ScaleTwoDecimal.ZERO);
        rateAndBase.setSalaryRequested(baseCost);
        rateAndBase.setRateClass(new RateClass());
        rateAndBase.getRateClass().setRateClassTypeCode(rateClassTypeCode);
        rateAndBase.setStartDate(lineItem.getStartDate());
        rateAndBase.setEndDate(lineItem.getEndDate());
        return rateAndBase;
    }

    protected BudgetPersonnelDetails getPersonnelDetails(Date startDate, Date endDate) {
        BudgetPersonnelDetails details = new BudgetPersonnelDetails();
        details.setJobCode("AA000");
        details.setPercentCharged(new ScaleTwoDecimal(100L));
        details.setPercentEffort(new ScaleTwoDecimal(100L));
        details.setPersonId("1000001");
        details.setPersonNumber(2);
        details.setSalaryRequested(new ScaleTwoDecimal(100000L));
        if (startDate != null) {
        	details.setStartDate(startDate);
        }
        if (endDate != null) {
        	details.setEndDate(endDate);
        }
        return details;
    }

    protected BudgetLineItem getPersonnelLineItem(BudgetPeriod budgetPeriod, Date startDate, Date endDate) {
        BudgetLineItem lineItem = new BudgetLineItemMock();
        lineItem.setBudgetCategory(createBudgetCategory("26", "Test", "E"));
        lineItem.setBudgetCategoryCode("26");
        lineItem.setCostElement("400350");
        lineItem.setLineItemCost(new ScaleTwoDecimal(10000.00));
        lineItem.setEndDate(endDate);
        lineItem.setStartDate(startDate);
        lineItem.setCostElementBO(getCostElementTravel());
        lineItem.setApplyInRateFlag(Boolean.TRUE);
        lineItem.setOnOffCampusFlag(Boolean.TRUE);
        lineItem.setBudgetPeriodBO(budgetPeriod);
        lineItem.setBudgetPeriod(1);
        lineItem.setBudgetLineItemId(5L);
        lineItem.setLineItemNumber(3);
		lineItem.setBudgetCategoryCode(PERSONNEL_CATEGORY_CODE);
        final BudgetCategory budgetCategory = new BudgetCategory();
        budgetCategory.setBudgetCategoryTypeCode(PERSONNEL_CATEGORY_CODE);
		lineItem.setBudgetCategory(budgetCategory);

        BudgetPersonnelDetails budgetPersonnelDetails = new BudgetPersonnelDetails();
        budgetPersonnelDetails.setLineItemNumber(3);
        budgetPersonnelDetails.setPersonNumber(1);
        budgetPersonnelDetails.setOnOffCampusFlag(Boolean.TRUE);
        budgetPersonnelDetails.setEndDate(endDate);
        budgetPersonnelDetails.setStartDate(startDate);
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
                                                        ScaleTwoDecimal calculatedCost, ScaleTwoDecimal baseCost, String rateClassType, Date startDate, Date endDate) {
        BudgetRateAndBaseMock rateAndBase = new BudgetRateAndBaseMock();
        rateAndBase.setCalculatedCost(calculatedCost);
        rateAndBase.setBaseCost(baseCost);
        rateAndBase.setCalculatedCostSharing(ScaleTwoDecimal.ZERO);
        rateAndBase.setRateClassCode(rateClassCode);
        rateAndBase.setRateTypeCode(rateTypeCode);
        rateAndBase.setAppliedRate(applicableRate);
        rateAndBase.setStartDate(startDate);
        rateAndBase.setEndDate(endDate);
        rateAndBase.setOnOffCampusFlag(Boolean.TRUE);
        RateClass rateClass = new RateClass();
        rateAndBase.setRateClass(rateClass);
        rateAndBase.getRateClass().setRateClassTypeCode(rateClassType);

        return rateAndBase;
    }

    protected BudgetLineItemCalculatedAmount getBudgetLineItemCalculatedAmount(String rateClassCode, String rateTypeCode,
                                                                               String rateTypeDescription, ScaleTwoDecimal calculatedCost) {
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
        final BudgetLineItem budgetLineItem = getTravelLineItem(budgetPeriod, getDate(2015, 7, 1), getDate(2016, 6, 30));
        budgetLineItem.setBudgetPeriodBO(budgetPeriod);
        budgetLineItem.setBudgetPeriod(1);
        budgetLineItem.setLineItemNumber(1);
        budgetPeriod.getBudgetLineItems().add(budgetLineItem);
        return  budgetPeriod;
    }

    protected java.sql.Date getDate(Integer year, Integer month, Integer day) {
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

    protected BudgetLineItem getTravelLineItem(BudgetPeriod budgetPeriod, Date startDate, Date endDate) {
        BudgetLineItem lineItem = new BudgetLineItemMock();
        lineItem.setBudgetCategory(createBudgetCategory("20", "Test", "E"));
        lineItem.setBudgetCategoryCode("20");
        lineItem.setCostElement("420050");
        lineItem.setLineItemCost(new ScaleTwoDecimal(10000.00));
        lineItem.setEndDate(endDate);
        lineItem.setStartDate(startDate);
        lineItem.setCostElementBO(getCostElementTravel());
        lineItem.setApplyInRateFlag(Boolean.TRUE);
        lineItem.setOnOffCampusFlag(Boolean.TRUE);
        lineItem.setBudgetPeriodBO(budgetPeriod);
        lineItem.setBudgetPeriod(1);
        lineItem.setLineItemNumber(2);
        lineItem.setBudgetLineItemId(6L);
        return lineItem;
    }

    protected BudgetCategory createBudgetCategory(String code, String description, String budgetCategoryTypeCode) {
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
