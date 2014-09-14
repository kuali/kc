package org.kuali.coeus.common.framework.ruleengine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.krad.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("kcBusinessRulesEngine")
public class KcBusinessRulesEngineImpl implements KcBusinessRulesEngine {
	
	private Map<Class<?>, List<RuleMethod>> rules = new HashMap<>();
	
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
		List<RuleMethod> result = new ArrayList<>();
		if (eventClass.getSuperclass() != null && eventClass.getSuperclass() != Object.class) {
			result.addAll(getApplicableRules(eventClass.getSuperclass()));
		}
		result.addAll(rules.get(eventClass));
		return result;
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
	public void registerBusinessRuleClass(Object businessRuleClass) {
		if (businessRuleClass == null) {
			throw new IllegalArgumentException("businessRuleClass is null");
		}
		final Class<? extends Object> ruleClass = businessRuleClass.getClass();
		for (final Method curMethod : ruleClass.getMethods()) {
			KcEventMethod methodAnnotation = curMethod.getAnnotation(KcEventMethod.class);
			if (methodAnnotation != null) {
				registerEvent(businessRuleClass, curMethod);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerEvent(Object rule, Method method) {
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
	}
	
	protected void registerEvent(Class<?> event, Object rule, Method method) {
		if (rules.get(event) == null) {
			rules.put(event, new ArrayList<RuleMethod>());
		}
		rules.get(event).add(new RuleMethod(rule, method));
		if (event.getSuperclass() != null && event.getSuperclass() != Object.class) {
			registerEvent(event.getSuperclass(), rule, method);
		}
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
