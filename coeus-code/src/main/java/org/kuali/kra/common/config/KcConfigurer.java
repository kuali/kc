/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.common.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.kuali.rice.core.api.config.module.RunMode;
import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.core.api.resourceloader.ResourceLoader;
import org.kuali.rice.core.framework.config.module.ModuleConfigurer;
import org.kuali.rice.core.framework.resourceloader.BaseResourceLoader;
import org.kuali.rice.core.framework.resourceloader.RiceResourceLoaderFactory;
import org.kuali.rice.core.framework.resourceloader.SpringResourceLoader;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class KcConfigurer extends ModuleConfigurer {
    
    private List<String> springBeanFiles = new ArrayList<String>();
    private String dispatchServletName;
    private List<String> filtersToMap = Arrays.asList(new String[]{"BootstrapFilter", "UserLoginFilter", "UserPreferencesFilter"});
    private String moduleTitle;
    
    private ResourceLoader rootResourceLoader;

    public KcConfigurer() {
        super();
        setValidRunModes(Arrays.asList(RunMode.LOCAL, RunMode.THIN));
    }

    public KcConfigurer(String moduleName, String moduleTitle) {
        super(moduleName);
        this.moduleTitle = moduleTitle;
    } 
    
    @Override
    public List<String> getPrimarySpringFiles() {
        return springBeanFiles;
    }   
    
    @Override
    protected ResourceLoader createResourceLoader(ServletContext servletContext, List<String> files, String moduleName) {
        BaseResourceLoader rl = (BaseResourceLoader) RiceResourceLoaderFactory.createRootRiceResourceLoader(servletContext, files, getModuleName());
        rootResourceLoader = rl;
        return rl;
    }
    
    @Override
    protected void doAdditionalModuleStartLogic() throws Exception {
        DispatcherServlet loaderServlet = new DispatcherServlet((WebApplicationContext) ((SpringResourceLoader) rootResourceLoader.getResourceLoaders().get(0)).getContext());
        ServletRegistration registration = getServletContext().addServlet(dispatchServletName, loaderServlet);
        registration.addMapping("/" + dispatchServletName + "/*");
        for (String filterName : filtersToMap) {
            FilterRegistration filter = getServletContext().getFilterRegistration(filterName);
            filter.addMappingForServletNames(null, true, dispatchServletName);
        }
    }
    
    public void setModuleName(String moduleName) {
        super.setModuleName(moduleName);
    }

    public List<String> getSpringBeanFiles() {
        return springBeanFiles;
    }

    public void setSpringBeanFiles(List<String> springBeanFiles) {
        this.springBeanFiles = springBeanFiles;
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