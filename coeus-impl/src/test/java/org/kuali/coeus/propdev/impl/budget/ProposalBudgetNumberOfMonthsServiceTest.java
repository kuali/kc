/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
		assertEquals(resultValue, 1.03, 0);
	}

	@Test
	public void test_getNumberOfMonthInDecimal() {
		Date startDate = java.sql.Date.valueOf("2015-01-01");
		Date endDate = java.sql.Date.valueOf("2016-03-31");
		double resultValue = proposalBudgetNumberOfMonthsService
				.getNumberOfMonth(startDate, endDate);
		assertEquals(resultValue, 15.0, 0);
	}
	
	@Test
	public void test_getNumberOfMonth_emptyDate() {
		Double dateDifference = proposalBudgetNumberOfMonthsService
				.getNumberOfMonth(null, null);
		assertEquals(0.00, dateDifference, 0);
	}

	@Test
	public void test_getNumberOfMonthsInvalid() {
		Date startDate = java.sql.Date.valueOf("2015-01-20");
		Date endDate = java.sql.Date.valueOf("2016-01-13");
		double resultValue = proposalBudgetNumberOfMonthsService
				.getNumberOfMonth(startDate, endDate);
		assertNotEquals(resultValue, 12.13, 0);
		assertEquals(resultValue, 11.81, 0);
	}
}
