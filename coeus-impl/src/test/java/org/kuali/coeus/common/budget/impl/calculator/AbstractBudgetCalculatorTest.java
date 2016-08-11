package org.kuali.coeus.common.budget.impl.calculator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.rate.RateClass;
import org.kuali.coeus.common.budget.framework.rate.RateType;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.budget.AwardBudgetLineItemExt;
import org.kuali.kra.award.commitments.FandaRateType;
import org.kuali.rice.krad.service.LegacyDataAdapter;

public class AbstractBudgetCalculatorTest {

	private static final String MTDC_RATE_TYPE = "1";
	private static final String TDC_RATE_TYPE = "2";
	private static final String AWARD_MTDC_RATE_CLASS = "13";
	private static final String OVERHEAD_RATE_CLASS_TYPE = "O";

	@Test
	public void testAddOHBudgetLineItemCalculatedAmountForAward_rateTypeMappedDoesNotExistOnBudget() {
		RateType type = new RateType();
		type.setRateClassCode(AWARD_MTDC_RATE_CLASS);
		type.setRateTypeCode(TDC_RATE_TYPE);
		
		List<BudgetRate> budgetRates = new ArrayList<>();
		budgetRates.add(createBudgetRate(OVERHEAD_RATE_CLASS_TYPE, AWARD_MTDC_RATE_CLASS, MTDC_RATE_TYPE, true, new ScaleTwoDecimal(20.00)));
		budgetRates.add(createBudgetRate(OVERHEAD_RATE_CLASS_TYPE, AWARD_MTDC_RATE_CLASS, MTDC_RATE_TYPE, false, new ScaleTwoDecimal(20.00)));
		AwardBudgetExt budget = mock(AwardBudgetExt.class);
		when(budget.getBudgetRates()).thenReturn(budgetRates);
		when(budget.getOhRatesNonEditable()).thenReturn(true);
		
		AwardBudgetLineItemExt lineItem = new AwardBudgetLineItemExt();
		lineItem.setOnOffCampusFlag(true);
		
		LegacyDataAdapter dataAdapter = mock(LegacyDataAdapter.class);
		when(dataAdapter.findBySinglePrimaryKey(FandaRateType.class, MTDC_RATE_TYPE)).thenReturn(new FandaRateType());
		
		LineItemCalculator calculator = new LineItemCalculator(budget, lineItem);
		calculator.setLegacyDataAdapter(dataAdapter);
		
		calculator.addOHBudgetLineItemCalculatedAmountForAward(type.getRateClassCode(), type, OVERHEAD_RATE_CLASS_TYPE);
		assertEquals(0, lineItem.getBudgetCalculatedAmounts().size());

		type.setRateTypeCode(MTDC_RATE_TYPE);
		calculator.addOHBudgetLineItemCalculatedAmountForAward(type.getRateClassCode(), type, OVERHEAD_RATE_CLASS_TYPE);
		assertEquals(1, lineItem.getBudgetCalculatedAmounts().size());
		assertEquals(AWARD_MTDC_RATE_CLASS, lineItem.getBudgetCalculatedAmounts().get(0).getRateClassCode());
		assertEquals(MTDC_RATE_TYPE, lineItem.getBudgetCalculatedAmounts().get(0).getRateTypeCode());
	}

	protected BudgetRate createBudgetRate(String rateClassTypeCode, String rateClassCode, String rateTypeCode, Boolean onOffCampusFlag, ScaleTwoDecimal rate) {
		RateClass rateClass = new RateClass();
		rateClass.setRateClassTypeCode(rateClassTypeCode);
		BudgetRate budgetRate = new BudgetRate();
		budgetRate.setRateClassCode(rateClassCode);
		budgetRate.setRateTypeCode(rateTypeCode);
		budgetRate.setOnOffCampusFlag(onOffCampusFlag);
		budgetRate.setApplicableRate(rate);
		budgetRate.setRateClass(rateClass);
		return budgetRate;
	}

}
