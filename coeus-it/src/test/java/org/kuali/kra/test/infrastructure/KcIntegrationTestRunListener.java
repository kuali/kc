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
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.kuali.kra.test.infrastructure.lifecycle.KcIntegrationTestLifecycle;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the mechanism to shut down the persistent portions of lifecycles at the end of a full test run.
 */
public class KcIntegrationTestRunListener extends RunListener {
    private static final Log LOG = LogFactory.getLog(KcIntegrationTestRunListener.class);
    
    KcIntegrationTestLifecycle lifecycle;
    List<Failure> assumptionFailures = new ArrayList<Failure>();
    List<Failure> failures = new ArrayList<Failure>();
    List<Description> ignoredTests = new ArrayList<Description>();
    
    public KcIntegrationTestRunListener(KcIntegrationTestLifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

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
