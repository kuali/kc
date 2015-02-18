package org.kuali.coeus.sys.framework.security;

import org.apache.commons.lang3.StringUtils;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class SpringRestSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("kualiConfigurationService")
	private ConfigurationService configurationService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		String userName = configurationService.getPropertyValueAsString("kc.rest.admin.username");
		String password = configurationService.getPropertyValueAsString("kc.rest.admin.password");
		if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
			auth.inMemoryAuthentication().withUser(userName).password(password).roles("ADMIN");
		}
	}
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  http.authorizeRequests().regexMatchers(".*/v1/.*").hasRole("ADMIN").and().httpBasic();
	}

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
}
