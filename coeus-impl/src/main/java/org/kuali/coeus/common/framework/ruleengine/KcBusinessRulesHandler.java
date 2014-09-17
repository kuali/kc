package org.kuali.coeus.common.framework.ruleengine;

import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class KcBusinessRulesHandler implements ApplicationContextAware, InitializingBean {

	private ApplicationContext applicationContext;
	
	@Autowired
	@Qualifier("kcBusinessRulesEngine")
	private KcBusinessRulesEngine kcBusinessRulesEngine;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String, Object> businessRules = applicationContext.getBeansWithAnnotation(KcBusinessRule.class);
		for (Map.Entry<String, Object> entry : businessRules.entrySet()) {
			kcBusinessRulesEngine.registerBusinessRuleClass(entry.getKey(), entry.getValue());
		}
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public KcBusinessRulesEngine getKcBusinessRulesEngine() {
		return kcBusinessRulesEngine;
	}

	public void setKcBusinessRulesEngine(KcBusinessRulesEngine kcBusinessRulesEngine) {
		this.kcBusinessRulesEngine = kcBusinessRulesEngine;
	}


}
