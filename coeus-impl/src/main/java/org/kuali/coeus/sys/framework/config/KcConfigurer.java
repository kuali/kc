/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
import org.kuali.rice.core.api.config.module.RunMode;
import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.core.api.resourceloader.ResourceLoader;
import org.kuali.rice.core.framework.config.module.ModuleConfigurer;
import org.kuali.rice.core.framework.resourceloader.RiceResourceLoaderFactory;
import org.kuali.rice.core.framework.resourceloader.SpringResourceLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.util.Collections;
import java.util.List;

public class KcConfigurer extends ModuleConfigurer {
    
    private String bootstrapSpringFile;
    private String dispatchServletName;
    private List<String> filtersToMap;
    private String moduleTitle;
    
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
        final String files = ConfigContext.getCurrentContextConfig().getProperty("kc." + getModuleName() + ".additionalSpringFiles");
        return files == null ? Collections.<String>emptyList() : parseFileList(files);
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
	        for (String filterName : filtersToMap) {
	            FilterRegistration filter = getServletContext().getFilterRegistration(filterName);
	            filter.addMappingForServletNames(null, true, dispatchServletName);
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
}
