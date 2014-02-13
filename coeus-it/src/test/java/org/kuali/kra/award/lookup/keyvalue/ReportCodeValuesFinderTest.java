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
import org.kuali.kra.award.paymentreports.ValidClassReportFrequency;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReportCodeValuesFinderTest extends KcIntegrationTestBase {
    
    ReportCodeValuesFinder reportCodeValuesFinder;
    List<KeyValue> reportCodes;
    Collection<ValidClassReportFrequency> validClassReportFrequencies;

    @Before
    public void setUp() throws Exception {
        reportCodeValuesFinder = new ReportCodeValuesFinder("1");
        reportCodes = new ArrayList<KeyValue>();        
        validClassReportFrequencies = new ArrayList<ValidClassReportFrequency>();      
    }

    @After
    public void tearDown() throws Exception {
        reportCodeValuesFinder = null;
        reportCodes = null;
    }

    @Test
    public final void testGetKeyValues() {
        reportCodes = reportCodeValuesFinder.getKeyValues();
        Assert.assertEquals(11,reportCodes.size());
        
        for(KeyValue KeyValue:reportCodes){
            Assert.assertNotNull(KeyValue.getKey());
            Assert.assertNotNull(KeyValue.getValue());
        }
    }
    
    @Test
    public final void testGetRelevantValidClassReportFrequencies(){
        validClassReportFrequencies.add(new ValidClassReportFrequency("1", "1", "1"));
        validClassReportFrequencies.add(new ValidClassReportFrequency("1", "1", "13"));
        validClassReportFrequencies.add(new ValidClassReportFrequency("1", "55", "4"));
        validClassReportFrequencies.add(new ValidClassReportFrequency("1", "55", "7"));
        validClassReportFrequencies.add(new ValidClassReportFrequency("1", "55", "8"));
        validClassReportFrequencies.add(new ValidClassReportFrequency("2", "5", "16"));
        
        Assert.assertEquals(2, reportCodeValuesFinder.getUniqueRelevantReportClassCodes(
                                                          validClassReportFrequencies).size());
    }
    

}

