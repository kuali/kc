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
