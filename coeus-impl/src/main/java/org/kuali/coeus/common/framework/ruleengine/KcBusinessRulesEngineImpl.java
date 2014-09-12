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
	
	private Map<String, List<RuleMethod>> rules = new HashMap<>();
	
	@Autowired
	@Qualifier("globalVariableService")
	private GlobalVariableService globalVariableService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean applyRules(KcEvent event) {
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
	public KcEventResult applyRulesWithResult(KcEvent event) {
		return runApplicableRules(event);
	}
	
	protected KcEventResult runApplicableRules(KcEvent event) {
		List<KcEventResult> results = new ArrayList<>();
		Boolean result = true;
		if (rules.containsKey(event.getEventName()) && !rules.get(event.getEventName()).isEmpty()) {
			for (RuleMethod ruleMethod : rules.get(event.getEventName())) {
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
	public void registerEvent(KcEventBase event, Object rule, Method method) {
		registerEvent(event.getEventName(), rule, method);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerEvent(String eventName, Object rule, Method method) {
		if (StringUtils.isBlank(eventName)) {
			throw new IllegalArgumentException("eventName is blank");
		}
		if (rule == null) {
			throw new IllegalArgumentException("rule is null");
		}
		if (method == null) {
			throw new IllegalArgumentException("method is null");
		}
		if (method.getParameterTypes().length != 1 || !KcEvent.class.isAssignableFrom(method.getParameterTypes()[0])) {
			throw new IllegalArgumentException(rule.getClass().getSimpleName() + "." + method.getName() + " cannot be registered with the KcBusinessRulesEngines as its arguments are not a single KcEvent.");
		}
		if (!Boolean.class.isAssignableFrom(method.getReturnType()) && !boolean.class.isAssignableFrom(method.getReturnType()) && !KcEventResult.class.isAssignableFrom(method.getReturnType())) {
			throw new IllegalArgumentException(rule.getClass().getSimpleName() + "." + method.getName() + " cannot be registered with the KcBusinessRuleEngine as its return value is not boolean or KcEventResult type.");
		}
		
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
