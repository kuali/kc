/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.test.infrastructure;

import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunListener;

/**
 * This class serves as a base test class for all KC unit tests. It handles ensuring all of the necessary lifecycles are properly
 * launched, started and stopped.
 */
@RunWith(KcUnitTestRunner.class)
public class KcUnitTestBase extends Assert implements KcUnitTestMethodAware {
    private Method method;

    /**
     * This method executes before each unit test and ensures the necessary lifecycles have been started and launched
     */
    @Before
    public final void beforeMethod() {
        if (method.isAnnotationPresent(KcUnitTestReqs.class)) {
            for (KcUnitTestReqs.Req req : method.getAnnotation(KcUnitTestReqs.class).value()) {
                if (!req.isLaunched()) {
                    req.launch();
                }
                req.start();
            }
        }
    }

    /**
     * This method executes after each unit test and makes sure the necessary lifecycles have been stopped
     */
    @After
    public final void afterMethod() {
        if (method.isAnnotationPresent(KcUnitTestReqs.class)) {
            for (KcUnitTestReqs.Req req : method.getAnnotation(KcUnitTestReqs.class).value()) {
                req.stop();
            }
        }
    }

    /**
     * This method is the canonical <code>@Before</code> method, included here to maintain compatibility with existing subclasses
     * calling <code>super.setUp()</code>.
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        // no-op
    }

    /**
     * This method is the canonical <code>@After</code> method, included here to maintain compatibility with existing subclasses
     * calling <code>super.tearDown()</code>.
     * 
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        // no-op
    }

    /**
     * This method is called by the <code>KCUnitTestRunner</code> and passes the method being called so the required lifecycles can
     * be determined.
     * 
     * @param method the <code>Method</code> being called by the current test
     * 
     * @see org.kuali.kra.test.infrastructure.KcUnitTestMethodAware#setTestMethod(java.lang.reflect.Method)
     */
    public void setTestMethod(Method method) {
        this.method = method;
    }
    
    /*
     * This class provides the mechanism to shut down the persistent portions of lifecycles at the end of a full test run.
     */
    private static RunListener runListener = new RunListener() {
        /**
         * @see org.junit.runner.notification.RunListener#testRunFinished(org.junit.runner.Result)
         */
        @Override
        public void testRunFinished(Result result) throws Exception {
            KcUnitTestReqs.Req.shutdownAll();
            super.testRunFinished(result);
        }  
    };
    
    /**
     * This method returns the <code>RunListener</code> needed to ensure the KC persistent lifecycles shut down properly
     * @return the RunListener responsible for shutting down all KC persistent lifecycles
     */
    public static RunListener getRunListener() {
        return runListener;
    }
}
