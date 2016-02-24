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
package org.kuali.kra.test.infrastructure.lifecycle;

/**
 * This interface models a unit test lifecycle which has a "per test" component, a "per class" component and a "per suite" component.
 */
public interface KcIntegrationTestLifecycle {
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
