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

import javax.persistence.EntityManagerFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class BudgetDocumentNextValuesDaoTest extends AbstractDependencyInjectionSpringContextTests {

    private EntityManagerFactory emf;
    
    @BeforeClass
    public void init() {
        ApplicationContext ctx = getApplicationContext();
        emf = (EntityManagerFactory) ctx.getBean("entityManagerFactory");
    }
    
    
    @Test
    public void testLoadingBudgetDocumentNextValues() {
        fail();
    }
    
    protected String[] getConfigLocations() {
        return new String[] { "classpath:SpringBeans.xml" };
    }
    
}
