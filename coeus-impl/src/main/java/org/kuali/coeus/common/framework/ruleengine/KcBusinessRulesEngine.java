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

import java.lang.reflect.Method;

/**
 * Event based business rules engine. Each rule and event must be registered with this
 * class prior to being called.
 */
public interface KcBusinessRulesEngine {

	/**
	 * Using the events class type, applyRules determines and executes all registered rule methods and returns
	 * a single boolean result which is the ANDing of all method return values.
	 * @param event
	 * @return
	 */
	public Boolean applyRules(Object event);
	
	/**
	 * Similar to {@link #applyRules(Object)} but returns a single KcEventResult containing
	 * the success of all associated rules and all messages and audit results.
	 * @param event
	 * @return
	 */
	public KcEventResult applyRulesWithResult(Object event);
	
	/**
	 * A class with methods annotated with KcEventMethod and accepting a KcEvent will
	 * be automatically registered with this engine.
	 * @param businessRuleClass
	 */
	public void registerBusinessRuleClass(String ruleName, Object businessRuleClass);
	
	/**
	 * Register the rule method with the eventName provided.
	 * @param eventName
	 * @param rule
	 * @param method
	 */
	public void registerEvent(String ruleName, Object rule, Method method);
}
