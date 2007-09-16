/*
 * Copyright 2007 The Kuali Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.infrastructure;

import org.kuali.rice.KNSServiceLocator;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KraServiceLocator {

    public static final String SPRING_BEANS = "classpath:SpringBeans.xml";

    private static ConfigurableApplicationContext appContext;

    private static void initialize() {
        if (appContext == null) {
            appContext = new ClassPathXmlApplicationContext(SPRING_BEANS);
        }
    }

    public static ConfigurableApplicationContext getAppContext() {
        initialize();
        return appContext;
    }

    public static Object getService(String name) {
        Object service = null;
        
        try {
            service = getAppContext().getBean(name);
        } catch (NoSuchBeanDefinitionException e) {
            // If we don't find this service locally, look for it in the KNS context
            service = KNSServiceLocator.getService(name);
        }
        
        return service;
    }

    public static <T> T getTypedService(String name) {
        T service = null;
        
        try {
            service = (T) getAppContext().getBean(name);
        } catch (NoSuchBeanDefinitionException e) {
            // If we don't find this service locally, look for it in the KNS context
            service = (T) KNSServiceLocator.getService(name);
        }
        
        return service;
    }

    /**
     * Uses the service interface to find the first service that matches it by name as a default service. There 
     * may be many services for a given interface. Only use this method if you are interested in finding a service
     * that matches the convention described.<br/>
     * <br/>
     * The service name and the service interface name are the same when converted to lowercase. Again, this method
     * should only be used in the special case where this convention applies. On KRA, this is usually the case.
     *
     * @param Interface class of the service you want
     * @return T the type of the service
     */
    public static <T> T getService(Class<T> serviceClass) {
        T service = null;
        String name = serviceClass.getSimpleName().substring(0, 1).toLowerCase() + serviceClass.getSimpleName().substring(1);

        try {
            service = (T) getAppContext().getBean(name);
        } catch (NoSuchBeanDefinitionException e) {
            // If we don't find this service locally, look for it in the KNS context
            service = KNSServiceLocator.getBean(serviceClass, name);
        }
        
        return service;
    }

}
