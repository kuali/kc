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
package org.kuali.coeus.sys.framework.service;

import org.apache.commons.lang3.StringUtils;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.kuali.rice.core.api.resourceloader.ResourceLoader;
import org.kuali.rice.core.framework.resourceloader.SpringResourceLoader;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Service locator used for research administration.
 */
public final class KcServiceLocator {
    
    private static ApplicationContext appContext;

    /**
     * private utility class ctor.
     * @throws UnsupportedOperationException if called
     */
    private KcServiceLocator() {
        throw new UnsupportedOperationException("do not call");
    }
    
    
    /**
     * Sets the appContext attribute value.
     * @param appContext The appContext to set.
     */
    public static void setAppContext(ApplicationContext appContext) {
        KcServiceLocator.appContext = appContext;
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
        } catch (Exception ex) {
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
     * On KC, this is usually the case.
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

        return KcServiceLocator.<T>getService(name);
    }

    /**
     * Retrieves all services from Spring that is of a specific type or subclass of.
     *
     * @param serviceClass the class type
     * @param <T> the generic type
     * @return a list of services of a type or subclass of
     */
    public static <T> List<T> getServicesOfType(final Class<T> serviceClass) {
        if (serviceClass == null) {
            throw new IllegalArgumentException("the service class is null.");
        }

        return Stream.concat(
                appContext.getBeansOfType(serviceClass).values().stream(),
                flatten(GlobalResourceLoader.getResourceLoader())
                        .filter(rl -> rl instanceof SpringResourceLoader)
                        .map(rl ->  (SpringResourceLoader) rl )
                        .flatMap(rl -> rl.getContext().getBeansOfType(serviceClass).values().stream()))
        .collect(Collectors.toList());
    }

    private static Stream<ResourceLoader> flatten(ResourceLoader root) {
        return Stream.concat(
                Stream.of(root),
                root.getResourceLoaders().stream().flatMap(KcServiceLocator::flatten));
    }
}
