/**
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.sys.framework.service;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.api.config.ConfigurationException;
import org.kuali.rice.core.api.config.property.ConfigContext;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Exports services in the {@link org.kuali.rice.core.api.config.property.ConfigContext#getCurrentContextConfig()} as beans available to Spring.
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class ConfigContextConfigObjectFactoryBean implements FactoryBean<Object>, InitializingBean {

	private String objectName;
	private boolean singleton;
	private boolean mustExist;

	public ConfigContextConfigObjectFactoryBean() {
		this.mustExist = true;
	}

	public Object getObject() throws Exception {
		Object o =  ConfigContext.getCurrentContextConfig().getObject(this.getObjectName());

		if (mustExist && o == null) {
			throw new IllegalStateException("Service must exist and no service could be located with name='" + this.getObjectName() + "'");
		}

		return o;
	}

    public Class<?> getObjectType() {
        if (getObjectName() == null) {
            return null;
        } else {
            try {
                // getObject throws java.lang.Exception
                return getObject().getClass();
            } catch (Exception e) {
                return null;
            }
        }
    }

	public boolean isSingleton() {
		return singleton;
	}

    public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public void setSingleton(boolean singleton) {
		this.singleton = singleton;
	}

	public boolean isMustExist() {
		return mustExist;
	}

	public void setMustExist(boolean mustExist) {
		this.mustExist = mustExist;
	}

	public void afterPropertiesSet() throws Exception {
		if (StringUtils.isBlank(this.getObjectName())) {
			throw new ConfigurationException("No objectName given.");
		}
	}

}
