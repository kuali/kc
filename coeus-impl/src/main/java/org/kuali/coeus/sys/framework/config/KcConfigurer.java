/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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