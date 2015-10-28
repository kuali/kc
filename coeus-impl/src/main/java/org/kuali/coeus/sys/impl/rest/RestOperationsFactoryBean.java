package org.kuali.coeus.sys.impl.rest;

import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

@Component("restOperations")
public class RestOperationsFactoryBean implements FactoryBean<RestOperations> {
	
	private static final String TRUST_SELFSIGNED_CONFIG = "kc.rest.ssl.trust.selfsigned";

	@Autowired
	@Qualifier("kualiConfigurationService")
	private ConfigurationService configurationService;
	
	@Autowired
	@Qualifier("standardRestOperations")
	private RestOperations restOperations;
	
	@Autowired
	@Qualifier("developmentRestOperations")
	private RestOperations devRestOperations;

	@Override
	public RestOperations getObject() throws Exception {
		if (trustSelfSignedCerts()) {
			return devRestOperations;
		} else {
			return restOperations;
		}
		
	}

	protected boolean trustSelfSignedCerts() {
		return configurationService.getPropertyValueAsBoolean(TRUST_SELFSIGNED_CONFIG);
	}

	@Override
	public Class<?> getObjectType() {
		return RestOperations.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public RestOperations getRestOperations() {
		return restOperations;
	}

	public void setRestOperations(RestOperations restOperations) {
		this.restOperations = restOperations;
	}

	public RestOperations getDevRestOperations() {
		return devRestOperations;
	}

	public void setDevRestOperations(RestOperations devRestOperations) {
		this.devRestOperations = devRestOperations;
	}
}
