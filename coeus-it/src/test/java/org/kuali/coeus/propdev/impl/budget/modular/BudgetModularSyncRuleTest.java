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
package org.kuali.coeus.propdev.impl.budget.modular;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRulesEngine;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

public class BudgetModularSyncRuleTest extends KcIntegrationTestBase {
	
	public KcBusinessRulesEngine rulesEngine;
	
	@Before
	public void init() {
		rulesEngine = KcServiceLocator.getService(KcBusinessRulesEngine.class);
	}
	
	@Test
	public void testBudgetSyncModularNoPeriods() {
		ProposalDevelopmentBudgetExt budget = new ProposalDevelopmentBudgetExt();
		assertFalse(rulesEngine.applyRules(new SyncModularBudgetKcEvent(budget)));
	}
	
	@Test
	public void testBudgetSyncModularEmptyPeriod() {
		ProposalDevelopmentBudgetExt budget = new ProposalDevelopmentBudgetExt();
		budget.add(new BudgetPeriod());
		assertFalse(rulesEngine.applyRules(new SyncModularBudgetKcEvent(budget)));
	}
	
	@Test
	public void testBudgetSyncModularPeriodWithLineItem() {
		ProposalDevelopmentBudgetExt budget = new ProposalDevelopmentBudgetExt();
		budget.add(new BudgetPeriod());
		budget.getBudgetPeriod(0).getBudgetLineItems().add(new BudgetLineItem());
		assertTrue(rulesEngine.applyRules(new SyncModularBudgetKcEvent(budget)));
	}
}
