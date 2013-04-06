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

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.kuali.rice.core.framework.config.module.ModuleConfigurer;
import org.kuali.rice.krad.service.DataDictionaryService;
import org.kuali.rice.krad.service.KRADServiceLocatorInternal;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.SmartApplicationListener;

public class AbstractConfigurer extends ModuleConfigurer implements SmartApplicationListener {
    
    private String moduleTitle;

    public AbstractConfigurer(String moduleName, String moduleTitle) {
        super(moduleName);
        
        this.moduleTitle = moduleTitle;
    }
    
    public String getModuleTitle() {
        return moduleTitle;
    }
    
    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }
    
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof ContextRefreshedEvent) {
            loadDataDictionary();
            publishDataDictionaryComponents();
        }
    }
    
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        return true;
    }

    @Override
    public boolean supportsSourceType(Class<?> aClass) {
        return true;
    }

    @Override
    public int getOrder() {
        // return a lower value which will give the data dictionary indexing higher precedence since DD indexing should
        // be started as soon as it can be
        return -1000;
    }
    
    /**
     * Used to "poke" the Data Dictionary again after the Spring Context is initialized.  This is to
     * allow for modules loaded with KualiModule after the KNS has already been initialized to work.
     *
     * Also initializes the DateTimeService
     */
    protected void loadDataDictionary() {
        if (isLoadDataDictionary()) {
            LOG.info(getModuleTitle() + " Configurer - Loading DD");
            DataDictionaryService dds = KRADServiceLocatorWeb.getDataDictionaryService();
            if (dds == null) {
                dds = (DataDictionaryService) GlobalResourceLoader
                        .getService(KRADServiceLocatorWeb.DATA_DICTIONARY_SERVICE);
            }
            dds.getDataDictionary().parseDataDictionaryConfigurationFiles(false);

            if (isValidateDataDictionary()) {
                LOG.info(getModuleTitle() + " Configurer - Validating DD");
                dds.getDataDictionary().validateDD(isValidateDataDictionaryEboReferences());
            }

            // KULRICE-4513 After the Data Dictionary is loaded and validated, perform Data Dictionary bean overrides.
            dds.getDataDictionary().performBeanOverrides();
        }
    }
    
    protected void publishDataDictionaryComponents() {
        if (isComponentPublishingEnabled()) {
            long delay = getComponentPublishingDelay();
            LOG.info("Publishing of Data Dictionary components is enabled, scheduling publish after " + delay + " millisecond delay");
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            try {
                scheduler.schedule(new Runnable() {
                    @Override
                    public void run() {
                        long start = System.currentTimeMillis();
                        LOG.info("Executing scheduled Data Dictionary component publishing...");
                        try {
                            KRADServiceLocatorInternal.getDataDictionaryComponentPublisherService().publishAllComponents();
                        } catch (RuntimeException e) {
                            LOG.error("Failed to publish data dictionary components.", e);
                            throw e;
                        } finally {
                            long end = System.currentTimeMillis();
                            LOG.info("... finished scheduled execution of Data Dictionary component publishing.  Took " + (end-start) + " milliseconds");
                        }
                    }
                }, delay, TimeUnit.MILLISECONDS);
            } finally {
                scheduler.shutdown();
            }
        }
    }
    
    public boolean isLoadDataDictionary() {
        return ConfigContext.getCurrentContextConfig().getBooleanProperty("load.data.dictionary", true);
    }

    public boolean isValidateDataDictionary() {
        return ConfigContext.getCurrentContextConfig().getBooleanProperty("validate.data.dictionary", false);
    }
    
    public boolean isValidateDataDictionaryEboReferences() {
        return ConfigContext.getCurrentContextConfig().getBooleanProperty("validate.data.dictionary.ebo.references",
                false);
    }

    public boolean isComponentPublishingEnabled() {
        return ConfigContext.getCurrentContextConfig().getBooleanProperty(
                KRADConstants.Config.COMPONENT_PUBLISHING_ENABLED, false);
    }

    public long getComponentPublishingDelay() {
        return ConfigContext.getCurrentContextConfig().getNumericProperty(KRADConstants.Config.COMPONENT_PUBLISHING_DELAY, 0);
    }
    
}