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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("kcBusinessRulesEngine")
public class KcBusinessRulesEngineImpl implements KcBusinessRulesEngine {
	
	private static final Log LOG = LogFactory.getLog(KcBusinessRulesEngineImpl.class);
	
	private Map<Class<?>, List<RuleMethod>> rules = new ConcurrentHashMap<>();
	
	@Autowired
	@Qualifier("globalVariableService")
	private GlobalVariableService globalVariableService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean applyRules(Object event) {
		KcEventResult result = runApplicableRules(event);
		mergeMapOfLists(globalVariableService.getMessageMap().getErrorMessages(), result.getMessageMap().getErrorMessages());
		mergeMapOfLists(globalVariableService.getMessageMap().getWarningMessages(), result.getMessageMap().getWarningMessages());
		mergeMapOfLists(globalVariableService.getMessageMap().getInfoMessages(), result.getMessageMap().getInfoMessages());
		mergeAuditMap(globalVariableService.getAuditErrorMap(), result.getAuditMap());
		return result.getSuccess();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public KcEventResult applyRulesWithResult(Object event) {
		return runApplicableRules(event);
	}
	
	protected KcEventResult runApplicableRules(Object event) {
		List<KcEventResult> results = new ArrayList<>();
		Boolean result = true;
		List<RuleMethod> applicableRules = getApplicableRules(event.getClass());
		if (!applicableRules.isEmpty()) {
			for (RuleMethod ruleMethod : applicableRules) {
				try {
					if (KcEventResult.class.isAssignableFrom(ruleMethod.method.getReturnType())) {
						results.add((KcEventResult) ruleMethod.method.invoke(ruleMethod.rule, event));
					} else if (Boolean.class.isAssignableFrom(ruleMethod.method.getReturnType()) || boolean.class.isAssignableFrom(ruleMethod.method.getReturnType())) {
						result &= (Boolean) ruleMethod.method.invoke(ruleMethod.rule, event);
					}
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}
		} else {
			throw new RuntimeException("Kc business rule event called but no events registered to handle event.");
		}
		return mergeResults(result, results);
	}
	
	protected List<RuleMethod> getApplicableRules(Class<?> eventClass) {
		Class<?> currentEventClass = eventClass;
		List<RuleMethod> applicableRules = new ArrayList<>();
		while (currentEventClass != null && currentEventClass != Object.class) {
			List<RuleMethod> currentClassRules = rules.get(currentEventClass);
			if (currentClassRules != null) {
				applicableRules.addAll(rules.get(currentEventClass));
			}
			currentEventClass = currentEventClass.getSuperclass();
		}
		return applicableRules;
	}
	
	protected KcEventResult mergeResults(Boolean result, List<KcEventResult> results) {
		KcEventResult mergedResult = new KcEventResult(result);
		for (KcEventResult curResult : results) {
			mergeResult(mergedResult, curResult);
		}
		return mergedResult;
	}
	
	protected void mergeResult(KcEventResult mergedResult, KcEventResult result) {
		mergedResult.setSuccess(mergedResult.getSuccess() && result.getSuccess());
		
		mergeMapOfLists(mergedResult.getMessageMap().getErrorMessages(), result.getMessageMap().getErrorMessages());
		mergeMapOfLists(mergedResult.getMessageMap().getWarningMessages(), result.getMessageMap().getWarningMessages());
		mergeMapOfLists(mergedResult.getMessageMap().getInfoMessages(), result.getMessageMap().getInfoMessages());
		
		mergeAuditMap(mergedResult.getAuditMap(), result.getAuditMap());
	}
	
	protected void mergeMapOfLists(Map<String, List<ErrorMessage>> mergedMap, Map<String, List<ErrorMessage>> origMap) {
		for (Map.Entry<String, List<ErrorMessage>> entry : origMap.entrySet()) {
			if (mergedMap.containsKey(entry.getKey())) {
				mergedMap.get(entry.getKey()).addAll(entry.getValue());
			} else {
				mergedMap.put(entry.getKey(), new ArrayList<>(entry.getValue()));
			}
		}
	}
	
	protected void mergeAuditMap(Map<String, AuditCluster> mergedMap, Map<String, AuditCluster> origMap) {
		for (Map.Entry<String, AuditCluster> entry : origMap.entrySet()) {
			if (mergedMap.containsKey(entry.getKey())) {
				mergedMap.get(entry.getKey()).getAuditErrorList().addAll(entry.getValue().getAuditErrorList());
			} else {
				mergedMap.put(entry.getKey(), entry.getValue());
			}
		}		
	}	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerBusinessRuleClass(String ruleName, Object businessRuleClass) {
		if (businessRuleClass == null) {
			throw new IllegalArgumentException("businessRuleClass is null");
		}
		final Class<? extends Object> ruleClass = businessRuleClass.getClass();
		for (final Method curMethod : ruleClass.getMethods()) {
			KcEventMethod methodAnnotation = curMethod.getAnnotation(KcEventMethod.class);
			if (methodAnnotation != null) {
				registerEvent(ruleName, businessRuleClass, curMethod);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerEvent(String ruleName, Object rule, Method method) {
		if (rule == null) {
			throw new IllegalArgumentException("rule is null");
		}
		if (method == null) {
			throw new IllegalArgumentException("method is null");
		}
		if (method.getParameterTypes().length != 1) {
			throw new IllegalArgumentException(rule.getClass().getSimpleName() + "." + method.getName() + " cannot be registered with the KcBusinessRulesEngines as it does not take a single argument.");
		}
		if (!Boolean.class.isAssignableFrom(method.getReturnType()) && !boolean.class.isAssignableFrom(method.getReturnType()) && !KcEventResult.class.isAssignableFrom(method.getReturnType())) {
			throw new IllegalArgumentException(rule.getClass().getSimpleName() + "." + method.getName() + " cannot be registered with the KcBusinessRuleEngine as its return value is not boolean or KcEventResult type.");
		}
		
		registerEvent(method.getParameterTypes()[0], rule, method);
		LOG.info("Rule handler with " + ruleName + " name has registered a rule method: " + method.getName() + "(" + method.getParameterTypes()[0].getSimpleName() + ")");
	}
	
	protected void registerEvent(Class<?> event, Object rule, Method method) {
		if (rules.get(event) == null) {
			rules.put(event, new ArrayList<RuleMethod>());
		}
		rules.get(event).add(new RuleMethod(rule, method));
	}
	
	public Map<Class<?>, List<RuleMethod>> getRules() {
		return rules;
	}

	public void setRules(Map<Class<?>, List<RuleMethod>> rules) {
		this.rules = rules;
	}
	
	static class RuleMethod {
		public Object rule;
		public Method method;
		public RuleMethod(Object rule, Method method) {
			this.rule = rule;
			this.method = method;
		}
	}

	public GlobalVariableService getGlobalVariableService() {
		return globalVariableService;
	}

	public void setGlobalVariableService(GlobalVariableService globalVariableService) {
		this.globalVariableService = globalVariableService;
	}

}
