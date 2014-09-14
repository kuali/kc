package org.kuali.coeus.common.framework.ruleengine;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContextAware;

/**
 * By implementing InitializingBean, classes extending from this will
 * be registered with the KcBusinessRulesService automatically.
 */
public abstract class KcBusinessRuleBase implements InitializingBean {
	
	@Autowired
	@Qualifier("kcBusinessRulesEngine")
	private KcBusinessRulesEngine kcBusinessRulesEngine;
	
    @Override
    public void afterPropertiesSet() throws Exception {
        //kcBusinessRulesEngine.registerBusinessRuleClass(this);
    }

	protected KcBusinessRulesEngine getKcBusinessRulesEngine() {
		return kcBusinessRulesEngine;
	}

	public void setKcBusinessRulesEngine(KcBusinessRulesEngine kcBusinessRulesEngine) {
		this.kcBusinessRulesEngine = kcBusinessRulesEngine;
	}
}

