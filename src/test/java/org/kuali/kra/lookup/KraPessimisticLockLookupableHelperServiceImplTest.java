/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.lookup;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the {@link KraPessimisticLockLookupableHelperServiceImpl KraPessimisticLockLookupableHelperServiceImpl} class.
 */
public class KraPessimisticLockLookupableHelperServiceImplTest {
    
    private KraPessimisticLockLookupableHelperServiceImpl lockService;
    private Method addSingleDayDateRange;
    
    @Before
    public void setupService() throws Exception {
        this.lockService = new KraPessimisticLockLookupableHelperServiceImpl();
        this.addSingleDayDateRange = this.lockService.getClass().getDeclaredMethod("addSingleDayDateRange", Map.class);
        this.addSingleDayDateRange.setAccessible(true);
    }
    
    /**
     * Tests that a date range is getting set from a valid date. 
     * 
     * @throws Exception if bad happens
     */
    @Test
    public void testDateRangeValidDate() throws Exception {
        Map<String, String> fields = new HashMap<String, String>();
        fields.put("generatedTimestamp", "12/31/1981");
        
        this.addSingleDayDateRange.invoke(this.lockService, fields);
        
        Assert.assertEquals("does not contain valid date range " + fields.get("generatedTimestamp"),
            "12/31/1981..01/01/1982", fields.get("generatedTimestamp"));
    }
    
    /**
     * Tests that the time field is not getting changed on an invalid date.
     *  
     * @throws Exception if bad happens
     */
    @Test
    public void testDateRangeInvalidDate() throws Exception {
        Map<String, String> fields = new HashMap<String, String>();
        fields.put("generatedTimestamp", "foo");
        
        this.addSingleDayDateRange.invoke(this.lockService, fields);
        
        Assert.assertEquals("should not have changed time field " + fields.get("generatedTimestamp"),
            "foo", fields.get("generatedTimestamp"));
    }
    
    /**
     * Tests that the time field is not getting changed when no date exists.
     * 
     * @throws Exception if bad happens
     */
    @Test
    public void testDateRangeNoDate() throws Exception {
        Map<String, String> fields = new HashMap<String, String>();
        this.addSingleDayDateRange.invoke(this.lockService, fields);
        
        Assert.assertNull("should not contain a time " + fields.get("generatedTimestamp"), fields.get("generatedTimestamp"));
    }
}
