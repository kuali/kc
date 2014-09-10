package org.kuali.coeus.common.framework.ruleengine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service("kcBusinessRulesEngine")
public class KcBusinessRulesEngineImpl implements KcBusinessRulesEngine {
	
	private Map<String, List<RuleMethod>> rules = new HashMap<>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean applyRules(KcEvent event) {
		boolean result = true;
		if (rules.containsKey(event.getEventName()) && !rules.get(event.getEventName()).isEmpty()) {
			for (RuleMethod ruleMethod : rules.get(event.getEventName())) {
				try {
					result &= (Boolean) ruleMethod.method.invoke(ruleMethod.rule, event);
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}
		} else {
			throw new RuntimeException("Kc business rule event called but no events registered to handle event.");
		}
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void registerBusinessRuleClass(Object businessRuleClass) {
		final Class<? extends Object> ruleClass = businessRuleClass.getClass();
		for (final Method curMethod : ruleClass.getMethods()) {
			KcEventMethod methodAnnotation = curMethod.getAnnotation(KcEventMethod.class);
			if (methodAnnotation != null) {
				for (final String eventName : methodAnnotation.events()) {
					registerEvent(eventName, businessRuleClass, curMethod);
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerEvent(KcEvent event, Object rule, Method method) {
		registerEvent(event.getEventName(), rule, method);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerEvent(String eventName, Object rule, Method method) {
		if (rules.get(eventName) == null) {
			rules.put(eventName, new ArrayList<RuleMethod>());
		}
		rules.get(eventName).add(new RuleMethod(rule, method));
		
	}
	
	public Map<String, List<RuleMethod>> getRules() {
		return rules;
	}

	public void setRules(Map<String, List<RuleMethod>> rules) {
		this.rules = rules;
	}
	
	private class RuleMethod {
		public Object rule;
		public Method method;
		public RuleMethod(Object rule, Method method) {
			this.rule = rule;
			this.method = method;
		}
	}

}
