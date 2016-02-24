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
package org.kuali.coeus.sys.framework.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.rice.core.api.config.ConfigurationException;
import org.kuali.rice.core.api.config.module.RunMode;
import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.core.api.resourceloader.ResourceLoader;
import org.kuali.rice.core.framework.config.module.ModuleConfigurer;
import org.kuali.rice.core.framework.resourceloader.RiceResourceLoaderFactory;
import org.kuali.rice.core.framework.resourceloader.SpringResourceLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KcConfigurer extends ModuleConfigurer {
	
	private static final String ADDITIONAL_SPRING_FILES = ".additionalSpringFiles";
	private static final String KC_PREFIX = "kc.";
	private static final String SPRING_SECURITY_FILTER_PROXY = ".springSecurityFilterProxy";
	private static final String SPRING_SECURITY_FILTER_CHAIN = "springSecurityFilterChain";

	protected final Log LOG = LogFactory.getLog(KcConfigurer.class);
    
    private String bootstrapSpringFile;
    private String dispatchServletName;
    private List<String> dispatchServletMappings = new ArrayList<String>();
    private List<String> filtersToMap = new ArrayList<String>();
    private String moduleTitle;
    private boolean enableSpringSecurity;
    private boolean mapFilters = true;
    
    private ResourceLoader rootResourceLoader;

    public KcConfigurer() {
        setValidRunModes(Collections.singletonList(RunMode.LOCAL));
    }

    public KcConfigurer(String moduleName, String moduleTitle) {
        super(moduleName);
        this.moduleTitle = moduleTitle;
    }

    @Override
    public List<String> getPrimarySpringFiles() {
        return Collections.singletonList(bootstrapSpringFile);
    }

    @Override
    public List<String> getAdditionalSpringFiles() {
        final String files = ConfigContext.getCurrentContextConfig().getProperty(KC_PREFIX + getModuleName() + ADDITIONAL_SPRING_FILES);
        return files == null ? Collections.<String>emptyList() : parseFileList(files);
    }
    
    @Override
    public RunMode getRunMode() {
    	try {
    		return super.getRunMode();
    	} catch (ConfigurationException e) {
    		//for KC modules assume LOCAL if not provided since thats the only valid run mode anyway
    		LOG.info("Assuming LOCAL mode for " + getModuleName() + " as one wasn't provided.", e);
    		return RunMode.LOCAL; 
    	}
    }
    
    @Override
    protected ResourceLoader createResourceLoader(ServletContext servletContext, List<String> files, String moduleName) {
        rootResourceLoader = RiceResourceLoaderFactory.createRootRiceResourceLoader(servletContext, files, getModuleName());
        return rootResourceLoader;
    }
    
    @Override
    protected void doAdditionalModuleStartLogic() throws Exception {
    	if (StringUtils.isNotBlank(dispatchServletName)) {
	        DispatcherServlet loaderServlet = new DispatcherServlet((WebApplicationContext) ((SpringResourceLoader) rootResourceLoader.getResourceLoaders().get(0)).getContext());
	        ServletRegistration registration = getServletContext().addServlet(dispatchServletName, loaderServlet);
	        registration.addMapping("/" + dispatchServletName + "/*");
	        if (dispatchServletMappings != null) {
	        	dispatchServletMappings.stream().map(mapping -> "/" + mapping + "/*").forEach(registration::addMapping);
	        }
	        if (mapFilters) {
		        for (String filterName : filtersToMap) {
		            FilterRegistration filter = getServletContext().getFilterRegistration(filterName);
		            filter.addMappingForServletNames(null, true, dispatchServletName);
		        }
	        }
	        if (enableSpringSecurity) {
	        	DelegatingFilterProxy filterProxy = new DelegatingFilterProxy(SPRING_SECURITY_FILTER_CHAIN, (WebApplicationContext) ((SpringResourceLoader) rootResourceLoader.getResourceLoaders().get(0)).getContext());
	        	FilterRegistration.Dynamic securityFilter = getServletContext().addFilter(KC_PREFIX + getModuleName() + SPRING_SECURITY_FILTER_PROXY, filterProxy);
	        	securityFilter.addMappingForServletNames(null, true, dispatchServletName);
	        }
    	}
    }

    @Override
    public void setModuleName(String moduleName) {
        super.setModuleName(moduleName);
    }

    public String getBootstrapSpringFile() {
        return bootstrapSpringFile;
    }

    public void setBootstrapSpringFile(String bootstrapSpringFile) {
        this.bootstrapSpringFile = bootstrapSpringFile;
    }

    public String getDispatchServletName() {
        return dispatchServletName;
    }

    public void setDispatchServletName(String dispatchServletName) {
        this.dispatchServletName = dispatchServletName;
    }

    public List<String> getFiltersToMap() {
        return filtersToMap;
    }

    public void setFiltersToMap(List<String> filtersToMap) {
        this.filtersToMap = filtersToMap;
    }

    protected ResourceLoader getRootResourceLoader() {
        return rootResourceLoader;
    }

    protected void setRootResourceLoader(ResourceLoader rootResourceLoader) {
        this.rootResourceLoader = rootResourceLoader;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }
    
    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

	public boolean isEnableSpringSecurity() {
		return enableSpringSecurity;
	}

	public void setEnableSpringSecurity(boolean enableSpringSecurity) {
		this.enableSpringSecurity = enableSpringSecurity;
	}

	public List<String> getDispatchServletMappings() {
		return dispatchServletMappings;
	}

	public void setDispatchServletMappings(List<String> dispatchServletMappings) {
		this.dispatchServletMappings = dispatchServletMappings;
	}

	public boolean isMapFilters() {
		return mapFilters;
	}

	public void setMapFilters(boolean mapFilters) {
		this.mapFilters = mapFilters;
	}

}
