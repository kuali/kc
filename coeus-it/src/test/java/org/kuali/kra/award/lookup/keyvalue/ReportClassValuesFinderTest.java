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
package org.kuali.kra.award.lookup.keyvalue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.paymentreports.ReportClass;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.service.KNSServiceLocator;

import java.util.ArrayList;
import java.util.List;

public class ReportClassValuesFinderTest extends KcIntegrationTestBase {
    
    ReportClassValuesFinder reportClassValuesFinder;
    List<KeyValue> reportClasses;

    @Before
    public void setUp() throws Exception {
        reportClassValuesFinder = new ReportClassValuesFinder();
        reportClasses = new ArrayList<KeyValue>();
        reportClasses = reportClassValuesFinder.getKeyValues();
    }

    @After
    public void tearDown() throws Exception {
        reportClassValuesFinder = null;
        reportClasses = null;
    }

    @Test
    public final void testGetKeyValues() {
        int count = KNSServiceLocator.getBusinessObjectService().findAll(ReportClass.class).size();
        Assert.assertEquals(count, reportClasses.size());
        
        for(KeyValue KeyValue:reportClasses){
            Assert.assertNotNull(KeyValue.getKey());
            Assert.assertNotNull(KeyValue.getValue());
        }
    }
}

