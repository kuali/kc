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
