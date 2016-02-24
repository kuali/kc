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
package org.kuali.coeus.propdev.impl.budget.subaward;

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRulesEngine;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

public class BudgetSubAwardsRuleTest extends KcIntegrationTestBase {

	public KcBusinessRulesEngine kcBusinessRuleEngine;
	
	@Before
	public void setup() {
		kcBusinessRuleEngine = KcServiceLocator.getService(KcBusinessRulesEngine.class);
	}
	
	@Test
	public void testRunningBudgetSubAwardsRule() {
		BudgetSubAwards subAward = new BudgetSubAwards();
		subAward.setSubAwardXmlFileData("123");
		kcBusinessRuleEngine.applyRules(new BudgetSubAwardsEvent(subAward, new ProposalDevelopmentBudgetExt(), ""));
	}

	public KcBusinessRulesEngine getKcBusinessRuleEngine() {
		return kcBusinessRuleEngine;
	}

	public void setKcBusinessRuleEngine(KcBusinessRulesEngine kcBusinessRuleEngine) {
		this.kcBusinessRuleEngine = kcBusinessRuleEngine;
	}
}
