package org.kuali.coeus.common.impl.security;

import org.kuali.coeus.sys.framework.security.SpringRestSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class CommonSpringRestSecurity extends SpringRestSecurity {
	//noop class to pull SpringRestSecurity class into the award spring context
}
