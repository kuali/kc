/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestLifecycle;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the mechanism to shut down the persistent portions of lifecycles at the end of a full test run.
 */
public class KcUnitTestRunListener extends RunListener {
    protected final Log LOG = LogFactory.getLog(this.getClass());
    
    KcUnitTestLifecycle lifecycle;
    List<Failure> assumptionFailures = new ArrayList<Failure>();
    List<Failure> failures = new ArrayList<Failure>();
    List<Description> ignoredTests = new ArrayList<Description>();
    
    public KcUnitTestRunListener (KcUnitTestLifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    /**
     * @see org.junit.runner.notification.RunListener#testRunFinished(org.junit.runner.Result)
     */
    @Override
    public void testRunFinished(Result result) throws Exception {
        lifecycle.stopPerSuite();
        outputReport();
    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        assumptionFailures.add(failure);
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        failures.add(failure);
    }

    @Override
    public void testIgnored(Description description) throws Exception {
        ignoredTests.add(description);
    }
    
    private void outputReport() {
        if (LOG.isInfoEnabled()) {
            StringBuilder builder = new StringBuilder();
            builder.append("Test Run Report:\n");
            builder.append("\nIgnored Tests:\n");
            if (ignoredTests.size() == 0) {
                builder.append("\tNo Ignored Tests\n");
            }
            else {
                for (Description description : ignoredTests) {
                    builder.append('\t').append(description.toString()).append('\n');
                }
            }
            builder.append("\nFailed Assumption Tests\n");
            if (assumptionFailures.size() == 0) {
                builder.append("\tNo Failed Assumption Tests\n");
            }
            else {
                builder.append("\nFailed Assumption Tests:\n");
                for (Failure failure : assumptionFailures) {
                    builder.append('\t').append(failure.toString()).append('\n');
                }
            }
            builder.append("\nFailed Tests:\n");
            if (failures.size() == 0) {
                builder.append("\tNo Failed Tests\n");
            }
            else {
                for (Failure failure : failures) {
                    builder.append('\t').append(failure.toString()).append('\n');
                }
            }
            LOG.info(builder.toString());
        }
    }
    
}
