/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.infrastructure;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

/**
 * Service locator used for research administration.
 */
public final class KraServiceLocator {
    
    private static ApplicationContext appContext;

    /**
     * private utility class ctor.
     * @throws UnsupportedOperationException if called
     */
    private KraServiceLocator() {
        throw new UnsupportedOperationException("do not call");
    }
    
    
    /**
     * Sets the appContext attribute value.
     * @param appContext The appContext to set.
     */
    public static void setAppContext(ApplicationContext appContext) {
        KraServiceLocator.appContext = appContext;
    }


    /**
     * Gets the appContext attribute. 
     * @return Returns the appContext.
     */
    public static ApplicationContext getAppContext() {
        return appContext;
    }


    /**
     * Lookups a service by name.
     * 
     * @param serviceName name of the Interface class of the service you want
     * @param <T> the type of service you want.
     * @return the service
     * @throws IllegalArgumentException if the service name is blank.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getService(final String serviceName) {
        
        if (StringUtils.isBlank(serviceName)) {
            throw new IllegalArgumentException("the service name is blank.");
        }
        
        try {
            return (T) getAppContext().getBean(serviceName);
        } catch (NoSuchBeanDefinitionException e) {
            // If we don't find this service locally, look for it in the Rice context
            return (T)GlobalResourceLoader.<T>getService(serviceName);
        } catch (Exception ex) {
            //ex.printStackTrace();
            return (T)GlobalResourceLoader.<T>getService(serviceName);
        }
    }

    /**
     * <p>
     * Uses the service interface to find the first service that matches it by name as a default service.
     * There may be many services for a given interface. Only use this method if you are interested in
     * finding a service that matches the convention described.
     * </p>
     * 
     * <p>
     * The service name and the service interface name are the same when converted to lowercase.
     * Again, this method should only be used in the special case where this convention applies.
     * On KRA, this is usually the case.
     * </p>
     * 
     * @param serviceClass Interface class of the service you want
     * @param <T> the type of service you want.
     * @return the service
     * @throws IllegalArgumentException if the service class is null.
     */
    public static <T> T getService(final Class<T> serviceClass) {
        
        if (serviceClass == null) {
            throw new IllegalArgumentException("the service class is null.");
        }
        
        final String name = serviceClass.getSimpleName().substring(0, 1).toLowerCase()
            + serviceClass.getSimpleName().substring(1);

        //cannot use type inference for T here or continuum fails to compile under sun's jdk 1.5.0.8
        return KraServiceLocator.<T>getService(name);
    }

}
