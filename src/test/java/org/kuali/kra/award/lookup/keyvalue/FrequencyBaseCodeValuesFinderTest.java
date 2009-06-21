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
import org.kuali.kra.award.paymentreports.ValidFrequencyBase;
import org.kuali.rice.kns.web.ui.KeyLabelPair;
    
public class FrequencyBaseCodeValuesFinderTest extends KcraNoDataTestBase{
    
    FrequencyBaseCodeValuesFinder frequencyBaseCodeValuesFinder;
    List<KeyLabelPair> frequencyBaseCodes;
    Collection<ValidFrequencyBase> validFrequencyBases;
    
    @Before
    public void setUp() throws Exception {
        frequencyBaseCodeValuesFinder = new FrequencyBaseCodeValuesFinder("13");        
        frequencyBaseCodes = new ArrayList<KeyLabelPair>();
        validFrequencyBases = new ArrayList<ValidFrequencyBase>();
        
    }

    @After
    public void tearDown() throws Exception {
        
        frequencyBaseCodeValuesFinder = null;
        frequencyBaseCodes = null;
        validFrequencyBases = null;
    }

    @Test
    public final void testGetKeyValues() {
        frequencyBaseCodes = frequencyBaseCodeValuesFinder.getKeyValues();
        Assert.assertEquals(3,frequencyBaseCodes.size());
        
        for(KeyLabelPair keyLabelPair:frequencyBaseCodes){
            Assert.assertNotNull(keyLabelPair.getKey());
            Assert.assertNotNull(keyLabelPair.getLabel());
        }
    }
        
    @Test
    public final void testGetRelevantValidClassReportFrequencies(){        
        validFrequencyBases.add(new ValidFrequencyBase("13", "14"));
        validFrequencyBases.add(new ValidFrequencyBase("13", "1"));
        validFrequencyBases.add(new ValidFrequencyBase("13", "55"));
        validFrequencyBases.add(new ValidFrequencyBase("14", "55"));
        validFrequencyBases.add(new ValidFrequencyBase("14", "2"));
        validFrequencyBases.add(new ValidFrequencyBase("15", "5"));
        Assert.assertEquals(3, frequencyBaseCodeValuesFinder.getUniqueRelevantFrequencyBaseCodes(validFrequencyBases).size());
    }
    
}

