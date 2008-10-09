/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.dao.jpa;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHelper {
    private static final String SPRING_BEANS_XML = "SpringBeans.xml";
    
    private static ApplicationContext ctx;
    
    private SpringHelper() {
    }
    
    public static ApplicationContext getApplicationContext() {
        if(ctx == null) {
            ctx = new ClassPathXmlApplicationContext(new String[] {SPRING_BEANS_XML});
        }
        return ctx;
    }
    
    public static Object getBean(String beanName) {
        if(beanName == null) {
            return null;
        }
        return getApplicationContext().getBean(beanName);
    }
}
