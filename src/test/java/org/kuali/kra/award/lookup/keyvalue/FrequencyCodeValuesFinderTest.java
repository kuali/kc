/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KcraNoDataTestBase;
import org.kuali.kra.award.paymentreports.ValidClassReportFrequency;
import org.kuali.rice.kns.web.ui.KeyLabelPair;
    
public class FrequencyCodeValuesFinderTest extends KcraNoDataTestBase{
    
    FrequencyCodeValuesFinder frequencyCodeValuesFinder;
    List<KeyLabelPair> frequencyCodes;
    Collection<ValidClassReportFrequency> validClassReportFrequencies;
    
    @Before
    public void setUp() throws Exception {        
        frequencyCodeValuesFinder = new FrequencyCodeValuesFinder("1","55");
        frequencyCodes = new ArrayList<KeyLabelPair>();        
        validClassReportFrequencies = new ArrayList<ValidClassReportFrequency>();        
    }

    @After
    public void tearDown() throws Exception {
        
        frequencyCodeValuesFinder = null;
        frequencyCodes = null;
    }

    @Test
    public final void testGetKeyValues() {
        frequencyCodes = frequencyCodeValuesFinder.getKeyValues();
        Assert.assertEquals(4,frequencyCodes.size());
        
        for(KeyLabelPair keyLabelPair:frequencyCodes){
            Assert.assertNotNull(keyLabelPair.getKey());
            Assert.assertNotNull(keyLabelPair.getLabel());
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
        Assert.assertEquals(3, frequencyCodeValuesFinder.getUniqueRelevantFrequencyCodes(validClassReportFrequencies).size());
    }
    
    @Test
    public final void testThis(){
        frequencyCodeValuesFinder.setReportClassCode("1");
        frequencyCodeValuesFinder.setReportCode("27");
        frequencyCodes = frequencyCodeValuesFinder.getKeyValues();
        Assert.assertEquals(3,frequencyCodes.size());
        for(KeyLabelPair keyLabelPair:frequencyCodes){
            Assert.assertNotNull(keyLabelPair.getKey());
            Assert.assertNotNull(keyLabelPair.getLabel());
            System.out.println(keyLabelPair.getKey());
            System.out.println(keyLabelPair.getLabel());
        }
    }
    
}

