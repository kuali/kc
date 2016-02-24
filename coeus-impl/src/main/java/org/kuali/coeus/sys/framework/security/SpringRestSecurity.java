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
package org.kuali.coeus.sys.framework.security;

import org.apache.commons.lang3.StringUtils;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter.XFrameOptionsMode;

@Configuration
@EnableWebMvcSecurity
@Deprecated
public class SpringRestSecurity extends WebSecurityConfigurerAdapter {
	
	private static final String V1_REST_SERVICES_REGEX = ".*/v1/.*";
	private static final String API_REST_SERVICES_REGEX = ".*/api/.*";
	private static final String ADMIN_ROLE = "ADMIN";
	private static final String KC_REST_ADMIN_PASSWORD = "kc.rest.admin.password";
	private static final String KC_REST_ADMIN_USERNAME = "kc.rest.admin.username";
	@Autowired
	@Qualifier("kualiConfigurationService")
	private ConfigurationService configurationService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		String userName = configurationService.getPropertyValueAsString(KC_REST_ADMIN_USERNAME);
		String password = configurationService.getPropertyValueAsString(KC_REST_ADMIN_PASSWORD);
		if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
			auth.inMemoryAuthentication().withUser(userName).password(password).roles(ADMIN_ROLE);
		}
	}
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().xssProtection().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsMode.SAMEORIGIN));
	    http.authorizeRequests().regexMatchers(V1_REST_SERVICES_REGEX).hasRole(ADMIN_ROLE).and().httpBasic();
	    http.authorizeRequests().regexMatchers(API_REST_SERVICES_REGEX).hasRole(ADMIN_ROLE).and().httpBasic();
	}

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
}
