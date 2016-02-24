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
package org.kuali.coeus.common.framework.ruleengine;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.impl.gv.GlobalVariableServiceImpl;

public class KcBusinessRulesEngineTest {

	private KcBusinessRulesEngineImpl kcBusinessRuleEngineImpl;
	
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
	
	@Before
	public void setup() {
		kcBusinessRuleEngineImpl = new KcBusinessRulesEngineImpl();
		kcBusinessRuleEngineImpl.setGlobalVariableService(new GlobalVariableServiceImpl());
	}
	
	@Test
	public void testSuperEvent() throws NoSuchMethodException, SecurityException {
		final KcTestRule rule = context.mock(KcTestRule.class);
		final KcTestEvent1 event = new KcTestEvent1();
        context.checking(new Expectations() {{
            one(rule).kcTestMethod1(event); will(returnValue(true));
        }});
		kcBusinessRuleEngineImpl.registerEvent("Test Rule", rule, rule.getClass().getMethod("kcTestMethod1", KcTestEvent1.class));
		kcBusinessRuleEngineImpl.registerEvent("Test Rule", rule, rule.getClass().getMethod("kcTestMethod2", KcTestEvent2.class));
		assertTrue(kcBusinessRuleEngineImpl.applyRules(event));
	}
	
	@Test
	public void testChildEvent() throws NoSuchMethodException, SecurityException {
		final KcTestRule rule = context.mock(KcTestRule.class);
		final KcTestEvent2 event = new KcTestEvent2();
        context.checking(new Expectations() {{
            one(rule).kcTestMethod1(event); will(returnValue(true));
            one(rule).kcTestMethod2(event); will(returnValue(false));
        }});
		kcBusinessRuleEngineImpl.registerEvent("Test Rule", rule, rule.getClass().getMethod("kcTestMethod1", KcTestEvent1.class));
		kcBusinessRuleEngineImpl.registerEvent("Test Rule", rule, rule.getClass().getMethod("kcTestMethod2", KcTestEvent2.class));
		assertFalse(kcBusinessRuleEngineImpl.applyRules(event));		
	}
	
	static class KcTestEvent1 {
		
	}
	
	static class KcTestEvent2 extends KcTestEvent1 {
		
	}
	
	static interface KcTestRule {
		public boolean kcTestMethod1(KcTestEvent1 event);
		public boolean kcTestMethod2(KcTestEvent2 event);
	}
}
