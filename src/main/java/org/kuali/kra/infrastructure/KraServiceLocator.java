/*
 * Copyright 2006-2009 The Kuali Foundation
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

import org.kuali.rice.kns.service.KNSServiceLocator;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KraServiceLocator {

    public static final String COMMON_SPRING_BEANS = "SpringBeans.xml";
    public static final String AWARD_SPRING_BEANS = "org/kuali/kra/award/AwardSpringBeans.xml";
    public static final String IRB_SPRING_BEANS = "org/kuali/kra/irb/IrbSpringBeans.xml";
    public static final String COMMITTEE_SPRING_BEANS = "org/kuali/kra/committee/CommitteeSpringBeans.xml";
    public static final String QUESTIONNAIRE_SPRING_BEANS = "org/kuali/kra/questionnaire/QuestionnaireSpringBeans.xml";

    private static ConfigurableApplicationContext appContext;

    private static void initialize() {
        if (appContext == null) {
            String[] springFiles = new String[] {COMMON_SPRING_BEANS, AWARD_SPRING_BEANS, IRB_SPRING_BEANS, COMMITTEE_SPRING_BEANS, QUESTIONNAIRE_SPRING_BEANS};
            appContext = new ClassPathXmlApplicationContext(springFiles);
        }
    }

    public static ConfigurableApplicationContext getAppContext() {
        initialize();
        return appContext;
    }
    
    public static ConfigurableApplicationContext getAppContextWithoutInitializing() {
        return appContext;
    }
    
    protected static void setApplicationContext(ConfigurableApplicationContext context) {
        appContext = context;
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
            ConfigurableApplicationContext appContext = getAppContext();
            service = (T) getAppContext().getBean(name);
        } catch (NoSuchBeanDefinitionException e) {
            // If we don't find this service locally, look for it in the KNS context
            service = KNSServiceLocator.getNervousSystemContextBean(serviceClass, name);
        }
        
        return service;
    }

}
