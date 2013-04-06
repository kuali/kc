/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.test.infrastructure.lifecycle;

/**
 * This interface models a unit test lifecycle which has a "per test" component, a "per class" component and a "per suite" component.
 */
public interface KcUnitTestLifecycle {
    /**
     * This method starts the "per test" portion of the lifecycle.
     */
    public void startPerTest(boolean transactional);

    /**
     * This method stops the "per test" portion of the lifecycle
     */
    public void stopPerTest();

    /**
     * This method starts the "per class" portion of the lifecycle
     */
    public void startPerClass();

    /**
     * This method stops the "per class" portion of the lifecycle
     */
    public void stopPerClass();

    /**
     * This method starts the "per suite" portion of the lifecycle
     */
    public void startPerSuite();

    /**
     * This method stops the "per suite" portion of the lifecycle
     */
    public void stopPerSuite();

    /**
     * This method indicates whether the "per test" portion of the lifecycle is running
     * 
     * @return the state of the "per test" portion of the lifecycle
     */
    public boolean isPerTestStarted();

    /**
     * This method indicates whether the "per class" portion of the lifecycle is running
     * 
     * @return the state of the "per class" portion of the lifecycle
     */
    public boolean isPerClassStarted();

    /**
     * This method indicates whether the "per suite" portion of the lifecycle is running
     * 
     * @return the state of the "per suite" portion of the lifecycle
     */
    public boolean isPerSuiteStarted();

}
