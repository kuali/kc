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
package org.kuali.coeus.sys.framework.service;

import org.apache.commons.lang3.StringUtils;
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
