package org.kuali.coeus.propdev.impl.budget.subaward;

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRulesEngine;
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
		subAward.setSubAwardXmlFileData("");
		kcBusinessRuleEngine.applyRules(new BudgetSubAwardsEvent(subAward));
	}

	public KcBusinessRulesEngine getKcBusinessRuleEngine() {
		return kcBusinessRuleEngine;
	}

	public void setKcBusinessRuleEngine(KcBusinessRulesEngine kcBusinessRuleEngine) {
		this.kcBusinessRuleEngine = kcBusinessRuleEngine;
	}
}
