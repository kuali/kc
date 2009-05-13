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
package org.kuali.kra.service;

import org.junit.Test;
import org.kuali.kra.KraServiceLocatorConfigurer;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.impl.SimpleTestServiceFirstImpl;
import org.kuali.kra.service.impl.SimpleTestServiceSecondImpl;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Test class for overriding SpringContext using KraServiceLocatorConfigurer
 */
public class SpringContextOverrideTest extends org.junit.Assert {
    
    @Test
    public void testNewCustomSpringBeans() throws Exception {
        
        ConfigurableApplicationContext existingAppContext = KraServiceLocator.getAppContextWithoutInitializing();
        assertNull(existingAppContext);
        
        assertEquals(0, KraServiceLocatorConfigurer.getContextDepth());
        ConfigurableApplicationContext customAppContext = KraServiceLocatorConfigurer.getEmptyApplicationContext();
        customAppContext.getBeanFactory().registerSingleton("simpleTestService", new SimpleTestServiceFirstImpl());
        KraServiceLocatorConfigurer.pushApplicationContext(customAppContext);
        assertEquals(1, KraServiceLocatorConfigurer.getContextDepth());
        
        TesterClass testerClass = new TesterClass();
        assertEquals(1, testerClass.getValue());
        
        KraServiceLocatorConfigurer.popApplicationContext();
        
        existingAppContext = KraServiceLocator.getAppContextWithoutInitializing();
        assertEquals(0, KraServiceLocatorConfigurer.getContextDepth());
        assertNull(existingAppContext);
    }
    
    @Test
    public void testOverrideExistingSpringBeans() throws Exception {
        
        // Set up initial app context
        KraServiceLocatorConfigurer.pushApplicationContext("TestSpringBeans.xml");
        
        TesterClass testerClass = new TesterClass();
        assertEquals(1, testerClass.getValue());
        
        // Replace the app context with a new empty context with the old one as its parent.  Should still find the bean.
        KraServiceLocatorConfigurer.pushApplicationContext("EmptySpringBeans.xml");
        assertEquals(1, testerClass.getValue());
        
        // Override the parent context definition in the child context.
        ConfigurableApplicationContext customAppContext = KraServiceLocatorConfigurer.getEmptyApplicationContext();
        customAppContext.getBeanFactory().registerSingleton("simpleTestService", new SimpleTestServiceSecondImpl());
        KraServiceLocatorConfigurer.pushApplicationContext(customAppContext);
        assertEquals(2, testerClass.getValue());
        assertEquals(3, KraServiceLocatorConfigurer.getContextDepth());
        
        // Now remove the new one - should be back to old impl
        KraServiceLocatorConfigurer.popApplicationContext();
        assertEquals(1, testerClass.getValue());
    }
    
    
    protected class TesterClass {
        
        protected TesterClass() {}
        
        protected int getValue() {
            SimpleTestService testService = KraServiceLocator.getService(SimpleTestService.class);
            return testService.getValue();
        }
        
    }

}
