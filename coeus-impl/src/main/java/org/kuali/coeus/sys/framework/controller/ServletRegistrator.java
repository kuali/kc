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


import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServletRegistrator implements InitializingBean, ServletContextAware {

    private ServletContext servletContext;
    private Servlet servlet;
    private String servletName;
    private boolean asyncSupported;
    private Map<String, String> initParameters;
    private int loadOnStartup;
    private MultipartConfigElement multipartConfigElement;
    private String roleName;
    private ServletSecurityElement servletSecurityElement;
    private List<String> urlMappings;
    private List<String> filtersToMap;
    private boolean mapFilters;

    @Override
    public void afterPropertiesSet() throws Exception {
        ServletRegistration.Dynamic sr = servletContext.addServlet(servletName, servlet);
        sr.setAsyncSupported(asyncSupported);

        if (initParameters != null) {
            sr.setInitParameters(initParameters);
        }

        sr.setLoadOnStartup(loadOnStartup);

        if (multipartConfigElement != null) {
            sr.setMultipartConfig(multipartConfigElement);
        }

        if (roleName != null) {
            sr.setRunAsRole(roleName);
        }

        if (servletSecurityElement != null) {
            sr.setServletSecurity(servletSecurityElement);
        }

        sr.addMapping(urlMappings.toArray(new String[] {}));
        
        if (mapFilters && filtersToMap != null) {
	        for (String filterName : filtersToMap) {
	            FilterRegistration filter = getServletContext().getFilterRegistration(filterName);
	            filter.addMappingForServletNames(null, true, servletName);
	        }
        }
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public Servlet getServlet() {
        return servlet;
    }

    public void setServlet(Servlet servlet) {
        this.servlet = servlet;
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public boolean isAsyncSupported() {
        return asyncSupported;
    }

    public void setAsyncSupported(boolean asyncSupported) {
        this.asyncSupported = asyncSupported;
    }

    public Map<String, String> getInitParameters() {
        return initParameters;
    }

    public void setInitParameters(Map<String, String> initParameters) {
        this.initParameters = initParameters;
    }

    public int getLoadOnStartup() {
        return loadOnStartup;
    }

    public void setLoadOnStartup(int loadOnStartup) {
        this.loadOnStartup = loadOnStartup;
    }

    public MultipartConfigElement getMultipartConfigElement() {
        return multipartConfigElement;
    }

    public void setMultipartConfigElement(MultipartConfigElement multipartConfigElement) {
        this.multipartConfigElement = multipartConfigElement;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public ServletSecurityElement getServletSecurityElement() {
        return servletSecurityElement;
    }

    public void setServletSecurityElement(ServletSecurityElement servletSecurityElement) {
        this.servletSecurityElement = servletSecurityElement;
    }

    public List<String> getUrlMappings() {
        return urlMappings;
    }

    public void setUrlMappings(List<String> urlMappings) {
        this.urlMappings = urlMappings;
    }

	public List<String> getFiltersToMap() {
		return filtersToMap;
	}

	public void setFiltersToMap(List<String> filtersToMap) {
		this.filtersToMap = filtersToMap;
	}

	public boolean isMapFilters() {
		return mapFilters;
	}

	public void setMapFilters(boolean mapFilters) {
		this.mapFilters = mapFilters;
	}
}
