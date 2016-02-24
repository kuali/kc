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
package co.kuali.coeus.sys.impl.monitor;


import org.kuali.coeus.sys.framework.gv.GlobalVariableService;

import org.kuali.coeus.sys.framework.util.HttpUtils;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * This filter authorizes access to monitoring URLs.
 */
public class MonitoringFilter implements Filter {

    private static final String KIM_MONITORING_VIEW_ID = "monitoring";
    private static final String MONITORING_CONFIG_PARAM = "kc.monitoring.enabled";

    private FilterConfig filterConfig;
    private ConfigurationService configurationService;
    private PermissionService permissionService;
    private GlobalVariableService globalVariableService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final UserSession session = getGlobalVariableService().getUserSession() != null ?
                getGlobalVariableService().getUserSession() : (UserSession) ((HttpServletRequest) request).getSession().getAttribute(KRADConstants.USER_SESSION_KEY);
        if (session == null || !getPermissionService().isAuthorizedByTemplate(session.getPrincipalId(),
                KRADConstants.KRAD_NAMESPACE,
                KimConstants.PermissionTemplateNames.OPEN_VIEW,
                Collections.singletonMap(KimConstants.AttributeConstants.VIEW_ID, KIM_MONITORING_VIEW_ID),
                Collections.emptyMap())) {
            HttpUtils.disableCache((HttpServletResponse) response);
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        if (!getConfigurationService().getPropertyValueAsBoolean(MONITORING_CONFIG_PARAM)) {
            HttpUtils.disableCache((HttpServletResponse) response);
            response.getWriter().write("Monitoring has been disabled.");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    public PermissionService getPermissionService() {
        return permissionService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }
}
