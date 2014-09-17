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
