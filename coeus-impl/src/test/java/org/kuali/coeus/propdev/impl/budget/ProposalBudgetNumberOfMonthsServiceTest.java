package org.kuali.coeus.propdev.impl.budget;

import static org.junit.Assert.assertEquals;
import java.sql.Date;
import org.junit.Before;
import org.junit.Test;

public class ProposalBudgetNumberOfMonthsServiceTest {
	private ProposalBudgetNumberOfMonthsServiceImpl proposalBudgetNumberOfMonthsService = null;

	@Before
	public void setUp() throws Exception {
		proposalBudgetNumberOfMonthsService = new ProposalBudgetNumberOfMonthsServiceImpl();
	}

	@Test
	public void test_getNumberOfMonth() {
		Date startDate = java.sql.Date.valueOf("1990-11-30");
		Date endDate = java.sql.Date.valueOf("1990-12-30");
		double resultValue = proposalBudgetNumberOfMonthsService
				.getNumberOfMonth(startDate, endDate);
		assertEquals(resultValue, 1, 0);
	}

	@Test
	public void test_getNumberOfMonth_emptyDate() {
		Double dateDifference = proposalBudgetNumberOfMonthsService
				.getNumberOfMonth(null, null);
		assertEquals(0.00, dateDifference, 0);
	}

	@Test
	public void test_countMonthsStepOne() {
		Date startDate = java.sql.Date.valueOf("1990-11-30");
		Date endDate = java.sql.Date.valueOf("1990-12-30");
		double resultValue = proposalBudgetNumberOfMonthsService
				.countMonthsStepOne(startDate, endDate);
		assertEquals(resultValue, 1, 0);
	}

	@Test
	public void test_countMonthsStepOne_startDateGreaterThanEndDate() {
		Date startDate = java.sql.Date.valueOf("1990-12-30");
		Date endDate = java.sql.Date.valueOf("1990-11-30");
		double resultValue = proposalBudgetNumberOfMonthsService
				.countMonthsStepOne(startDate, endDate);
		assertEquals(resultValue, 0, 0);
	}

	@Test
	public void test_countMonthsStepOne_equalDates() {
		Date startDate = java.sql.Date.valueOf("1990-11-30");
		Date endDate = java.sql.Date.valueOf("1990-11-30");
		double resultValue = proposalBudgetNumberOfMonthsService
				.countMonthsStepOne(startDate, endDate);
		assertEquals(resultValue, 0, 0);
	}

	@Test
	public void test_countMonthsStepTwo_startDateGreaterThanEndDate() {
		Date startDate = java.sql.Date.valueOf("1990-12-30");
		Date endDate = java.sql.Date.valueOf("1990-11-30");
		double resultValue = proposalBudgetNumberOfMonthsService
				.countMonthsStepOne(startDate, endDate);
		assertEquals(resultValue, 0, 0);
	}

	@Test
	public void test_countMonthsStepTwo_equalDates() {
		Date startDate = java.sql.Date.valueOf("1990-11-30");
		Date endDate = java.sql.Date.valueOf("1990-11-30");
		double resultValue = proposalBudgetNumberOfMonthsService
				.countMonthsStepOne(startDate, endDate);
		assertEquals(resultValue, 0, 0);
	}

	@Test
	public void test_countMonthsStepTwo() {
		Date startDate = java.sql.Date.valueOf("1990-10-30");
		Date endDate = java.sql.Date.valueOf("1990-12-30");
		double resultValue = proposalBudgetNumberOfMonthsService
				.countMonthsStepOne(startDate, endDate);
		assertEquals(resultValue, 2, 0);
	}

	@Test
	public void test_getMonthDailyPercentageArray() {
		double[] dailyPercentageArray = proposalBudgetNumberOfMonthsService
				.getMonthDailyPercentageArray(1, false);
		assertEquals(dailyPercentageArray.length, 28, 0);
	}

	@Test
	public void test_getMonthDailyPercentageArray_leapYear() {
		double[] dailyPercentageArray = proposalBudgetNumberOfMonthsService
				.getMonthDailyPercentageArray(1, true);
		assertEquals(dailyPercentageArray.length, 29, 0);
	}

	@Test
	public void test_rollMonths() {
		Date startDate = java.sql.Date.valueOf("1990-12-30");
		Date rollMonthDate = ProposalBudgetNumberOfMonthsServiceImpl
				.rollMonths(startDate, 5);
		assertEquals(rollMonthDate, java.sql.Date.valueOf("1991-05-30"));
	}

	@Test
	public void test_rollMonths_increasedYear() {
		Date startDate = java.sql.Date.valueOf("1990-12-30");
		Date rollMonthDate = ProposalBudgetNumberOfMonthsServiceImpl
				.rollMonths(startDate, 14);
		assertEquals(rollMonthDate, java.sql.Date.valueOf("1992-02-29"));
	}

	@Test
	public void test_rollDays() {
		Date startDate = java.sql.Date.valueOf("1990-12-30");
		Date rollDate = ProposalBudgetNumberOfMonthsServiceImpl.rollDays(
				startDate, 5);
		assertEquals(rollDate, java.sql.Date.valueOf("1991-01-04"));
	}

	@Test
	public void test_rollDays_increasedDays() {
		Date startDate = java.sql.Date.valueOf("1990-12-30");
		Date rollDate = ProposalBudgetNumberOfMonthsServiceImpl.rollDays(
				startDate, 400);
		assertEquals(rollDate, java.sql.Date.valueOf("1992-02-03"));
	}

	@Test
	public void test_rollDate() {
		Date startDate = java.sql.Date.valueOf("1990-12-30");
		Date rollDate = ProposalBudgetNumberOfMonthsServiceImpl.rollDate(
				startDate, 5, 5);
		assertEquals(rollDate, java.sql.Date.valueOf("1991-01-04"));
	}
}
