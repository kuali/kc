/*
 * Copyright 2007 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl2.php
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS
 * IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package org.kuali.kra.test;

import java.lang.reflect.Method;

import org.apache.commons.beanutils.MethodUtils;
import org.junit.internal.runners.InitializationError;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.notification.RunNotifier;

/**
 * A Runner which sets up Rice unit tests appropriately. 
 * 1) It invokes setName() on the Test (if the method exists) and sets it to the name of the test method being invoked. 
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 * @since 0.9
 */
public class UnitTestClassRunner extends JUnit4ClassRunner {
    //private PerTestDataLoaderLifecycle perTestDataLoaderLifecycle;
    private Method currentMethod;
    
    public UnitTestClassRunner(final Class<?> testClass) throws InitializationError {
        super(testClass);
        
    }

    @Override
    protected void invokeTestMethod(Method method, RunNotifier runNotifier) {
        this.currentMethod = method;
        try {
            //perTestDataLoaderLifecycle = new PerTestDataLoaderLifecycle(method);
            super.invokeTestMethod(method, runNotifier);
        } finally {
            this.currentMethod = null;
        }
    }

    @Override
    protected Object createTest() throws Exception {
        Object test = super.createTest();
        setTestName(test, currentMethod);
        setTestMethod(test, currentMethod);
        //setTestPerTestDataLoaderLifecycle(test);
        return test;
    }

    /**
     * Sets the {@link java.lang.reflect.Method} on the test case if it is {@link MethodAware}
     * @param method the current method to be run
     */
    protected void setTestMethod(Object test, Method method) {
        try {
            if (test instanceof MethodAware) {
                ((MethodAware) test).setTestMethod(method);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // something went horribly wrong?
        }
    }

    protected void setTestName(final Object test, final Method testMethod) {
        try {
            String name = testMethod == null ? "" : testMethod.getName();
            final Method setNameMethod = MethodUtils.getAccessibleMethod(test.getClass(), "setName", new Class[]{String.class});
            if (setNameMethod != null) {
                setNameMethod.invoke(test, new Object[]{name});
            }
        } catch (final Exception e) {
            // no setName method or we failed to invoke it so we can't set the name
        }
    }

}
