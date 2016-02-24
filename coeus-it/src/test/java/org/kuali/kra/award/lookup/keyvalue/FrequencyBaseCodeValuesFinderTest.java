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
import org.kuali.kra.award.paymentreports.ValidFrequencyBase;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
    
public class FrequencyBaseCodeValuesFinderTest extends KcIntegrationTestBase {
    
    FrequencyBaseCodeValuesFinder frequencyBaseCodeValuesFinder;
    List<KeyValue> frequencyBaseCodes;
    Collection<ValidFrequencyBase> validFrequencyBases;
    
    @Before
    public void setUp() throws Exception {
        frequencyBaseCodeValuesFinder = new FrequencyBaseCodeValuesFinder("13");
        frequencyBaseCodes = new ArrayList<KeyValue>();
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
        
        for(KeyValue KeyValue:frequencyBaseCodes){
            Assert.assertNotNull(KeyValue.getKey());
            Assert.assertNotNull(KeyValue.getValue());
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

