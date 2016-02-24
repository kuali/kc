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
import org.kuali.kra.award.paymentreports.ValidClassReportFrequency;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
    
public class FrequencyCodeValuesFinderTest extends KcIntegrationTestBase {
    
    FrequencyCodeValuesFinder frequencyCodeValuesFinder;
    List<KeyValue> frequencyCodes;
    Collection<ValidClassReportFrequency> validClassReportFrequencies;
    
    @Before
    public void setUp() throws Exception {        
        frequencyCodeValuesFinder = new FrequencyCodeValuesFinder("4","9");
        frequencyCodes = new ArrayList<KeyValue>();        
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
        Assert.assertEquals(5,frequencyCodes.size());
        
        for(KeyValue KeyValue:frequencyCodes){
            Assert.assertNotNull(KeyValue.getKey());
            Assert.assertNotNull(KeyValue.getValue());
        }
    }
    
    @Test
    public final void testGetRelevantValidClassReportFrequencies(){
        frequencyCodeValuesFinder = new FrequencyCodeValuesFinder("1","55");
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
        for(KeyValue KeyValue:frequencyCodes){
            Assert.assertNotNull(KeyValue.getKey());
            Assert.assertNotNull(KeyValue.getValue());
        }
    }
    
}

