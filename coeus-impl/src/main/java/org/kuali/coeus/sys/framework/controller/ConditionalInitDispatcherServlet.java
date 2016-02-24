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
package org.kuali.coeus.sys.framework.controller;

import org.apache.commons.lang3.StringUtils;
import org.kuali.rice.core.api.config.property.ConfigContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

/**
 * A dispatcher servlet that only initializes if a specific config property exists with a
 * specific value.
 *
 * The config property to check is configured by setting an init property {@value #INIT_CONFIG_PROPERTY_NAME}.
 * The config property value to check is configured by an init property {@value #INIT_CONFIG_PROPERTY_NAME}.
 */
public class ConditionalInitDispatcherServlet extends DispatcherServlet {

    public static final String INIT_CONFIG_PROPERTY_NAME = "initConfigPropertyName";
    public static final String INIT_CONFIG_PROPERTY_VALUE = "initConfigPropertyValue";

    @Override
    public void init(ServletConfig config) throws ServletException {

        if (doInit(config)) {
            super.init(config);
        } else {
            //logging the same way the super class logs using context logger
            config.getServletContext().log("Not Initialized Spring FrameworkServlet '" + getServletName(config) + "'");
        }
    }

    private String getServletName(ServletConfig config) {
        return (config != null ? config.getServletName() : null);
    }

    private boolean doInit(ServletConfig config) {
        final String initConfigPropertyName = config.getInitParameter(INIT_CONFIG_PROPERTY_NAME);
        final String initConfigPropertyValue = config.getInitParameter(INIT_CONFIG_PROPERTY_VALUE);

        boolean init = false;
        if (StringUtils.isNotBlank(initConfigPropertyName)) {
            final String configProperty = getConfigPropertyValue(initConfigPropertyName);
            if (StringUtils.equals(initConfigPropertyValue, configProperty)) {
                init = true;
            }
        }
        return init;
    }

    private String getConfigPropertyValue(String name) {
        return ConfigContext.getCurrentContextConfig().getProperty(name);
    }
}
