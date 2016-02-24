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

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

/**
 * This class is a custom JUnit4 Runner.  It overrides a few methods to insert custom KC functionality.
 */
public class KcIntegrationTestRunner extends BlockJUnit4ClassRunner {
    private static boolean listenerAdded = false;

    /**
     * Constructs a KcIntegrationTestRunner to run <code>klass</code>.
     * 
     * @see org.junit.runners.BlockJUnit4ClassRunner#BlockJUnit4ClassRunner(java.lang.Class)
     */
    public KcIntegrationTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    /**
     * Overridden to call setMethod if test class is implementer of <code>KcIntegrationTestMethodAware</code>
     * 
     * @see org.junit.runners.BlockJUnit4ClassRunner#methodInvoker(org.junit.runners.model.FrameworkMethod, java.lang.Object)
     */
    @Override
    protected Statement methodInvoker(FrameworkMethod method, Object test) {
        if (test instanceof KcIntegrationTestMethodAware) {
            ((KcIntegrationTestMethodAware) test).setTestMethod(method.getMethod().getName());
        }
        return super.methodInvoker(method, test);
    }

    /**
     * Overridden to add listener to catch <code>testRunFinished</code> events. Not supposed to be here, but the "right way" to do
     * it is to add the listener to an instance of <code>JUnitCore</code> and then run all your tests using that. Doesn't work with
     * the multiple ways we run our tests including via Eclipse or within Hudson.
     * 
     * @see org.junit.runners.ParentRunner#run(org.junit.runner.notification.RunNotifier)
     */
    @Override
    public void run(RunNotifier notifier) {
        if (!listenerAdded) {
            notifier.addListener(KcIntegrationTestBase.getRunListener());
            listenerAdded = true;
        }
        super.run(notifier);
    }


}
