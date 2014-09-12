package org.kuali.coeus.common.framework.ruleengine;

import java.lang.reflect.Method;

/**
 * Event based business rules engine. Each rule and event must be registered with this
 * class prior to being called.
 */
public interface KcBusinessRulesEngine {

	/**
	 * Using KcEvent.eventName, applyRules determines and executes all registered rule methods and returns
	 * a single boolean result which is the ANDing of all method return values.
	 * @param event
	 * @return
	 */
	public Boolean applyRules(KcEvent event);
	
	/**
	 * Similar to {@link #applyRules(KcEvent)} but returns a single KcEventResult containing
	 * the success of all associated rules and all messages and audit results.
	 * @param event
	 * @return
	 */
	public KcEventResult applyRulesWithResult(KcEvent event);
	
	/**
	 * A class with methods annotated with KcEventMethod and accepting a KcEvent will
	 * be automatically registered with this engine.
	 * @param businessRuleClass
	 */
	public void registerBusinessRuleClass(Object businessRuleClass);
	
	/**
	 * Registers the rule method with the associated event.eventName
	 * @param event
	 * @param rule
	 * @param method
	 */
	public void registerEvent(KcEventBase event, Object rule, Method method);
	
	/**
	 * Register the rule method with the eventName provided.
	 * @param eventName
	 * @param rule
	 * @param method
	 */
	public void registerEvent(String eventName, Object rule, Method method);
}
