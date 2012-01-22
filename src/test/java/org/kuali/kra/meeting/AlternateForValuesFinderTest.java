/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.meeting;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.kuali.rice.core.api.util.KeyValue;

public class AlternateForValuesFinderTest {


    @Test
    public void testGetKeyValues() throws Exception {
        AlternateForValuesFinder alternateForValuesFinder = new AlternateForValuesFinder();
        alternateForValuesFinder.setAbsenteeList("001#f#Test 1#m#002#f#Test 2");
        List<KeyValue> keyValues = alternateForValuesFinder.getKeyValues();
        Assert.assertEquals(keyValues.size(), 3);
        Assert.assertEquals(keyValues.get(1).getKey(), "001");
        Assert.assertEquals(keyValues.get(1).getValue(), "Test 1");
        Assert.assertEquals(keyValues.get(2).getKey(), "002");
        Assert.assertEquals(keyValues.get(2).getValue(), "Test 2");
    }


}
