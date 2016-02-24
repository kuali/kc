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
package org.kuali.coeus.propdev.impl.budget;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.query.QueryList;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.rate.ValidCeRateType;
import org.kuali.coeus.common.budget.impl.calculator.*;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.fail;


public class BudgetCalculatorTest {


    public static final String MTDC_RATE_CLASS_CODE = "1";
    public static final String TDC_RATE_CLASS_CODE = "2";
    public static final String FUNSN_RATE_CLASS_CODE = "4";
    public static final String ACTIVITY_TYPE_CODE_RESEARCH = "1";
    public static final String FISCAL_YEAR_2015 = "2015";
    public static final String RATE_TYPE_CODE_2 = "2";
    public static final String RATE_TYPE_CODE_1 = "1";

    @Test
    public void calculateUnderrecoveryURrateMTDCoverheadFUNSN() throws ParseException {
        // MTDC - FUNSN
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-M-yyyy");
        Date date1 = dateformat.parse("01-7-2015");
        Date date2 = dateformat.parse("30-6-2016");
        Boundary boundary = getBoundary(date1, date2);

        BreakUpInterval breakUpInterval = new BreakUpInterval();
        breakUpInterval.setBoundary(boundary);
        breakUpInterval.setApplicableAmt(new ScaleTwoDecimal(1000L));
        breakUpInterval.setApplicableAmtCostSharing(ScaleTwoDecimal.ZERO);
        breakUpInterval.setBudgetProposalLaRates(new QueryList<>());
        QueryList<BudgetRate> proposalRates = new QueryList();
        BudgetRate proposalRate1 = getProposalRate(FUNSN_RATE_CLASS_CODE, RATE_TYPE_CODE_2, FISCAL_YEAR_2015, "1", 10L, 10L);
        proposalRates.add(proposalRate1);
        breakUpInterval.setBudgetProposalRates(proposalRates);


        Calendar cal = Calendar.getInstance();
        final java.sql.Date july2014 = getDate(2014, cal.JULY, 1);
        Long applicableRate = 10L;
        QueryList lineItemPropRates = new QueryList<>();
        BudgetRate lineItemPropRate1 = getLineItemPropRate(FUNSN_RATE_CLASS_CODE, ACTIVITY_TYPE_CODE_RESEARCH, FISCAL_YEAR_2015, RATE_TYPE_CODE_2, july2014, applicableRate);
        BudgetRate lineItemPropRate2 = getLineItemPropRate(MTDC_RATE_CLASS_CODE, ACTIVITY_TYPE_CODE_RESEARCH, FISCAL_YEAR_2015, RATE_TYPE_CODE_1, getDate(2015, cal.JANUARY, 1), 56L);

        lineItemPropRates.add(lineItemPropRate1);
        lineItemPropRates.add(lineItemPropRate2);

        BudgetLineItem li = new BudgetLineItem();
        li.setLineItemCost(new ScaleTwoDecimal(10000L));

        CostElement costElement = getCostElementEquipmentNotMTDC();
        li.setCostElementBO(costElement);

        Budget budget = getBudget(FUNSN_RATE_CLASS_CODE, MTDC_RATE_CLASS_CODE);
        LineItemCalculator lineItemCalculator = new LineItemCalculator(budget, li);
        lineItemCalculator.setUnderrecoveryRateBean(lineItemPropRates, boundary, breakUpInterval);

        RateAndCost rateAndCost = new RateAndCost();
        rateAndCost.setApplyRateFlag(Boolean.TRUE);
        rateAndCost.setBaseAmount(new ScaleTwoDecimal(10000L));
        rateAndCost.setBaseCostSharingAmount(new ScaleTwoDecimal(0L));
        rateAndCost.setRateClassCode(FUNSN_RATE_CLASS_CODE);
        rateAndCost.setRateTypeCode(RATE_TYPE_CODE_2);
        BreakupIntervalServiceImpl breakupService = new BreakupIntervalServiceImpl();
        breakupService.calculateUnderRecovery(breakUpInterval, rateAndCost);
        Assert.assertTrue(rateAndCost.getUnderRecovery().compareTo(new ScaleTwoDecimal(-1000L)) == 0);
    }

    protected BudgetRate getLineItemPropRate(String FUNSN_RATE_CLASS_CODE, String ACTIVITY_TYPE_CODE_RESEARCH,
                                             String FISCAL_YEAR_2015, String RATE_TYPE_CODE_2, java.sql.Date july2014, Long applicableRate) {
        BudgetRate lineItemPropRate1 = new BudgetRate();
        lineItemPropRate1.setActivityTypeCode(ACTIVITY_TYPE_CODE_RESEARCH);
        lineItemPropRate1.setApplicableRate(new ScaleTwoDecimal(applicableRate));
        lineItemPropRate1.setFiscalYear(FISCAL_YEAR_2015);
        lineItemPropRate1.setRateClassCode(FUNSN_RATE_CLASS_CODE);
        lineItemPropRate1.setRateTypeCode(RATE_TYPE_CODE_2);
        lineItemPropRate1.setStartDate(july2014);
        lineItemPropRate1.setInstituteRate(new ScaleTwoDecimal(applicableRate));
        lineItemPropRate1.setActive(Boolean.TRUE);
        return lineItemPropRate1;
    }

    @Test
    public void calculateUnderrecoveryURrateTDCoverheadFUNSN() throws ParseException {
        // TDC - FUNSN
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-M-yyyy");
        Date date1 = dateformat.parse("01-7-2015");
        Date date2 = dateformat.parse("30-6-2016");
        Boundary boundary = getBoundary(date1, date2);

        BreakUpInterval breakUpInterval = new BreakUpInterval();
        breakUpInterval.setBoundary(boundary);
        breakUpInterval.setApplicableAmt(new ScaleTwoDecimal(1000L));
        breakUpInterval.setApplicableAmtCostSharing(ScaleTwoDecimal.ZERO);
        breakUpInterval.setBudgetProposalLaRates(new QueryList<>());
        QueryList<BudgetRate> proposalRates = new QueryList();
        BudgetRate proposalRate1 = getProposalRate(FUNSN_RATE_CLASS_CODE, RATE_TYPE_CODE_2, FISCAL_YEAR_2015, "1", 10L, 10L);
        proposalRates.add(proposalRate1);
        breakUpInterval.setBudgetProposalRates(proposalRates);


        Calendar cal = Calendar.getInstance();
        final java.sql.Date july2014 = getDate(2014, cal.JULY, 1);
        QueryList lineItemPropRates = new QueryList<>();
        BudgetRate lineItemPropRate1 = getLineItemPropRate(FUNSN_RATE_CLASS_CODE, ACTIVITY_TYPE_CODE_RESEARCH, FISCAL_YEAR_2015, RATE_TYPE_CODE_2, july2014, 10L);
        BudgetRate lineItemPropRate2 = getLineItemPropRate(TDC_RATE_CLASS_CODE, ACTIVITY_TYPE_CODE_RESEARCH, FISCAL_YEAR_2015, RATE_TYPE_CODE_1, getDate(2015, cal.JANUARY, 1), 20L);

        lineItemPropRates.add(lineItemPropRate1);
        lineItemPropRates.add(lineItemPropRate2);

        BudgetLineItem li = new BudgetLineItem();
        li.setLineItemCost(new ScaleTwoDecimal(10000L));

        CostElement costElement = getCostElementEquipmentNotMTDC();
        li.setCostElementBO(costElement);

        Budget budget = getBudget(FUNSN_RATE_CLASS_CODE, TDC_RATE_CLASS_CODE);
        LineItemCalculator lineItemCalculator = new LineItemCalculator(budget, li);
        lineItemCalculator.setUnderrecoveryRateBean(lineItemPropRates, boundary, breakUpInterval);

        RateAndCost rateAndCost = new RateAndCost();
        rateAndCost.setApplyRateFlag(Boolean.TRUE);
        rateAndCost.setBaseAmount(new ScaleTwoDecimal(10000L));
        rateAndCost.setBaseCostSharingAmount(new ScaleTwoDecimal(0L));
        rateAndCost.setRateClassCode(FUNSN_RATE_CLASS_CODE);
        rateAndCost.setRateTypeCode(RATE_TYPE_CODE_2);
        BreakupIntervalServiceImpl breakupService = new BreakupIntervalServiceImpl();
        breakupService.calculateUnderRecovery(breakUpInterval, rateAndCost);
        Assert.assertTrue(rateAndCost.getUnderRecovery().compareTo(new ScaleTwoDecimal(1000L)) == 0);

    }

    protected CostElement getCostElementEquipmentNotMTDC() {
        CostElement costElement = getCostElement("421818", "Equipment-notMTDC");
        List<ValidCeRateType> validCeRateTypes = new ArrayList<>();
        ValidCeRateType validRate1 = getValidCeRateType(costElement, TDC_RATE_CLASS_CODE, RATE_TYPE_CODE_1);
        ValidCeRateType validRate2 = getValidCeRateType(costElement, FUNSN_RATE_CLASS_CODE, RATE_TYPE_CODE_2);
        validCeRateTypes.add(validRate1);
        validCeRateTypes.add(validRate2);
        costElement.setValidCeRateTypes(validCeRateTypes);
        return costElement;
    }

    protected Boundary getBoundary(Date date1, Date date2) {
        Boundary boundary = new Boundary();
        boundary.setStartDate(date1);
        boundary.setEndDate(date2);
        boundary.setApplicableCost(new ScaleTwoDecimal(1000L));
        boundary.setApplicableCostSharing(ScaleTwoDecimal.ZERO);
        return boundary;
    }

    protected CostElement getCostElement(String costElementCode, String description) {
        CostElement costElement = new CostElement();
        costElement.setCostElement(costElementCode);
        costElement.setDescription(description);
        return costElement;
    }

    protected Budget getBudget(String ohRateClassCode, String urRateClassCode) {
        Budget budget = new Budget();
        budget.setOhRateClassCode(ohRateClassCode);
        budget.setUrRateClassCode(urRateClassCode);
        return budget;
    }

    protected ValidCeRateType getValidCeRateType(CostElement costElement, String rateClassCode, String rateTypeCode) {
        ValidCeRateType validRate1 = new ValidCeRateType();
        validRate1.setRateClassCode(rateClassCode);
        validRate1.setRateTypeCode(rateTypeCode);
        validRate1.setCostElementBo(costElement);
        return validRate1;
    }

    protected BudgetRate getProposalRate(String rateClassCode, String rateTypeCode, String fiscalYear, String activityTypeCode, Long applicableRate, Long instituteRate) {
        BudgetRate proposalRate1 = new BudgetRate();
        proposalRate1.setActivityTypeCode(activityTypeCode);
        proposalRate1.setApplicableRate(new ScaleTwoDecimal(applicableRate));
        proposalRate1.setFiscalYear(fiscalYear);
        proposalRate1.setOnOffCampusFlag(Boolean.TRUE);
        proposalRate1.setRateClassCode(rateClassCode);
        proposalRate1.setRateTypeCode(rateTypeCode);
        proposalRate1.setInstituteRate(new ScaleTwoDecimal(instituteRate));
        proposalRate1.setActive(Boolean.TRUE);
        return proposalRate1;
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

    private Date getDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            fail(dateString + " is not parsable");
            return null;
        }
    }
}
