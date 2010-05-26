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
package org.kuali.kra.test.infrastructure.lifecycle;

/**
 * This interface models a unit test lifecycle which has both a "per test" aspect (start and stop) and a persistent or "per run"
 * apsect (launch and shutdown)
 */
public interface KcUnitTestLifecycle {
    /**
     * This method starts the "per test" portion of the lifecycle.
     */
    public void start();

    /**
     * This method stops the "per test" portion of the lifecycle
     */
    public void stop();

    /**
     * This method launches the persistent or "per run" portion of the lifecycle
     */
    public void launch();

    /**
     * This method shuts down the persistent or "per run" portion of the lifecycle
     */
    public void shutdown();

    /**
     * This method indicates whether the "per test" portion of the lifecycle is running
     * 
     * @return the state of the "per test" portion of the lifecycle
     */
    public boolean isStarted();

    /**
     * This method indicates whether the persistent or "per run" portion of the lifecycle is running
     * 
     * @return the state of the persistent or "per run" portion of the lifecycle
     */
    public boolean isLaunched();
}
