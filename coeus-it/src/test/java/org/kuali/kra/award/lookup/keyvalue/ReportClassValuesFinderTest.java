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

