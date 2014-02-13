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
package org.kuali.kra.award.paymentreports;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingSearchViews;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.List;
import static org.junit.Assert.*;
public class ReportTrackingViewValuesFinderTest extends KcIntegrationTestBase {
    
    ReportTrackingViewValuesFinder finder;

    @Before
    public void setUp() throws Exception {
        finder = new ReportTrackingViewValuesFinder();
    }

    @After
    public void tearDown() throws Exception {
        finder = null;
    }

    @Test
    public void testGetKeyValues() {
        List<KeyValue> labelPairs = finder.getKeyValues();
        assertEquals(4, labelPairs.size());
    }

    @Test
    public void testGetReportTrackingSearchViews() {
        ReportTrackingSearchViews views = finder.getReportTrackingSearchViews();
        assertEquals(3, views.getCustomViewIndex());
    }

}
