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

import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.core.framework.config.module.ModuleConfigurer;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
                            KRADServiceLocatorWeb.getDataDictionaryComponentPublisherService().publishAllComponents();
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