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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunListener;
import org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestMainLifecycle;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.MessageMap;

/**
 * This class serves as a base test class for all KC unit tests. It handles ensuring all of the necessary lifecycles are properly
 * launched, started and stopped.
 */
@RunWith(KcUnitTestRunner.class)
public class KcUnitTestBase extends Assert implements KcUnitTestMethodAware {
    // non static logger to allow it to be named after the runtime class
    protected final Log LOG = LogFactory.getLog(this.getClass());

    private static KcUnitTestMainLifecycle LIFECYCLE = new KcUnitTestMainLifecycle();
    private static RunListener RUN_LISTENER = new KcUnitTestRunListener(LIFECYCLE);
    private static final String DEFAULT_USER = "quickstart";

    private long startTime;
    private long totalMem;
    private long freeMem;
    
    private final String memStatFormat = "[%1$-7s] total: %2$10d, free: %3$10d";

    private Method method;
    
    /**
     * This method executes before each unit test and ensures the necessary lifecycles have been started
     */
    @Before
    public final void baseBeforeTest() {
        logBeforeRun();
        LIFECYCLE.startPerTest();
        GlobalVariables.setMessageMap(new MessageMap());
        GlobalVariables.setUserSession(new UserSession(DEFAULT_USER));
    }

    /**
     * This method executes after each unit test and makes sure the necessary lifecycles have been stopped
     */
    @After
    public final void baseAfterTest() {
        GlobalVariables.setMessageMap(new MessageMap());
        GlobalVariables.setUserSession(null);
        LIFECYCLE.stopPerTest();
        logAfterRun();
    }
    
    @BeforeClass
    public static final void baseBeforeClass() {
        if (!LIFECYCLE.isPerSuiteStarted()) {
            LIFECYCLE.startPerSuite();
        }
        LIFECYCLE.startPerClass();
    }
    
    @AfterClass
    public static final void baseAfterClass() {
        LIFECYCLE.stopPerClass();
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
        
    /**
     * This method returns the <code>RunListener</code> needed to ensure the KC persistent lifecycles shut down properly
     * @return the RunListener responsible for shutting down all KC persistent lifecycles
     */
    public static RunListener getRunListener() {
        return RUN_LISTENER;
    }
    
    protected void logBeforeRun() {
        if (LOG.isInfoEnabled()) {
            statsBegin();
            LOG.info("##############################################################");
            LOG.info("# Starting test " + getFullTestName() + "...");
            LOG.info("##############################################################");
        }
    }

    protected void logAfterRun() {
        if (LOG.isInfoEnabled()) {
            LOG.info("##############################################################");
            LOG.info("# ...finished test " + getFullTestName());
            for (String stat : statsEnd()) {
                LOG.info("# " + stat);
            }
            LOG.info("##############################################################");
        }
    }
    
    
    private void statsBegin() {
        startTime = System.currentTimeMillis();
        totalMem = Runtime.getRuntime().totalMemory();
        freeMem = Runtime.getRuntime().freeMemory();
    }

    protected String[] statsEnd() {
        long currentTime = System.currentTimeMillis();
        long currentTotalMem = Runtime.getRuntime().totalMemory();
        long currentFreeMem = Runtime.getRuntime().freeMemory();
        return new String[]{
                String.format(memStatFormat, "MemPre", totalMem, freeMem),
                String.format(memStatFormat, "MemPost", currentTotalMem, currentFreeMem),
                String.format(memStatFormat, "MemDiff", totalMem-currentTotalMem, freeMem-currentFreeMem),
                String.format("[ElapsedTime] %1$d ms", currentTime-startTime)
        };
    }
    
    protected String getFullTestName() {
        return getClass().getSimpleName() + "." + method.getName();
    }

}
