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
package org.kuali.kra.test.infrastructure;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunListener;
import org.kuali.kra.test.infrastructure.lifecycle.KcIntegrationTestMainLifecycle;
import org.kuali.rice.coreservice.api.parameter.Parameter;
import org.kuali.rice.coreservice.framework.CoreFrameworkServiceLocator;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.MessageMap;

import java.util.HashMap;

/**
 * This class serves as a base test class for all KC unit tests. It handles ensuring all of the necessary lifecycles are properly
 * launched, started and stopped.
 */
@RunWith(KcIntegrationTestRunner.class)
public class KcIntegrationTestBase implements KcIntegrationTestMethodAware {
    // non static Log to allow it to be named after the runtime class
    protected final Log LOG = LogFactory.getLog(this.getClass());

    private static final KcIntegrationTestMainLifecycle LIFECYCLE = new KcIntegrationTestMainLifecycle();
    private static final RunListener RUN_LISTENER = new KcIntegrationTestRunListener(LIFECYCLE);
    private static final String DEFAULT_USER = "quickstart";
    private static final String MEM_STAT_FORMAT = "[%1$-7s] total: %2$10d, free: %3$10d";

    private long startTime;
    private long totalMem;
    private long freeMem;
    
    private String method;
    
    /**
     * This method executes before each unit test and ensures the necessary lifecycles have been started.
     */
    @Before
    public final void baseBeforeTest() {
        logBeforeRun();
        LIFECYCLE.startPerTest(true);
        GlobalVariables.setMessageMap(new MessageMap());
        GlobalVariables.setAuditErrorMap(new HashMap<String, AuditCluster>());
        GlobalVariables.setUserSession(new UserSession(DEFAULT_USER));
    }

    /**
     * This method executes after each unit test and makes sure the necessary lifecycles have been stopped
     */
    @After
    public final void baseAfterTest() {
        GlobalVariables.setMessageMap(new MessageMap());
        GlobalVariables.setAuditErrorMap(new HashMap<String, AuditCluster>());
        GlobalVariables.setUserSession(null);
        LIFECYCLE.stopPerTest();
        logAfterRun();
    }
    
    @BeforeClass
    public static void baseBeforeClass() {
        if (!LIFECYCLE.isPerSuiteStarted()) {
            LIFECYCLE.startPerSuite();
        }
        LIFECYCLE.startPerClass();
    }
    
    @AfterClass
    public static void baseAfterClass() {
        LIFECYCLE.stopPerClass();
    }

    /**
     * This method returns the <code>RunListener</code> needed to ensure the KC persistent lifecycles shut down properly
     * @return the RunListener responsible for shutting down all KC persistent lifecycles
     */
    static RunListener getRunListener() {
        return RUN_LISTENER;
    }

    /**
     * This method is called by the <code>KcIntegrationTestRunner</code> and passes the method being called so the required lifecycles can
     * be determined.
     * 
     * @param method the <code>Method</code> being called by the current test
     * 
     * @see KcIntegrationTestMethodAware#setTestMethod(java.lang.String)
     */
    public final void setTestMethod(String method) {
        this.method = method;
    }
    
    private void logBeforeRun() {
        if (LOG.isInfoEnabled()) {
            statsBegin();
            LOG.info("##############################################################");
            LOG.info("# Starting test " + getFullTestName() + "...");
            LOG.info("##############################################################");
        }
    }

    private void logAfterRun() {
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

    private String[] statsEnd() {
        long currentTime = System.currentTimeMillis();
        long currentTotalMem = Runtime.getRuntime().totalMemory();
        long currentFreeMem = Runtime.getRuntime().freeMemory();
        return new String[]{
                String.format(MEM_STAT_FORMAT, "MemPre", totalMem, freeMem),
                String.format(MEM_STAT_FORMAT, "MemPost", currentTotalMem, currentFreeMem),
                String.format(MEM_STAT_FORMAT, "MemDiff", totalMem-currentTotalMem, freeMem-currentFreeMem),
                String.format("[ElapsedTime] %1$d ms", currentTime-startTime)
        };
    }
    
    private String getFullTestName() {
        return getClass().getSimpleName() + "." + (method != null ? method : "UNKNOWN");
    }

    protected void updateParameterForTesting(Class componentClass, String parameterName, String newValue) {
        final ParameterService parameterService = CoreFrameworkServiceLocator.getParameterService();

        Parameter parameter = parameterService.getParameter(componentClass, parameterName);
        Parameter.Builder parameterForUpdate = Parameter.Builder.create(parameter);
        parameterForUpdate.setValue(newValue);
        parameterService.updateParameter(parameterForUpdate.build());
    }
}
