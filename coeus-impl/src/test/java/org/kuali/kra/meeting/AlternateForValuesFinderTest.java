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
package org.kuali.kra.meeting;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.coeus.common.committee.impl.meeting.AlternateForValuesFinder;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.List;

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
