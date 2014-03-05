/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.bo.BusinessObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class UnitLookupableHelperServiceImplTest extends KcIntegrationTestBase {
    
    private static final int SEARCH_RESULTS_NO_CAMPUS_CODE_COUNT = 13;
    private static final int SEARCH_RESULTS_CAMPUS_CODE_COUNT = 4;
    private static final String CAMPUS_CODE_FIELD = "code";

    private static final String CAMPUS_CODE = "BL";
    
    private UnitLookupableHelperServiceImpl service;
    

    @Before
    public void setUp() throws Exception {

        service = new UnitLookupableHelperServiceImpl();
    }

    @After
    public void tearDown() throws Exception {

        service = null;
    }
    
    @Test
    public void testNoCampusCodeSearchResults() {
        service.setBusinessObjectClass(Unit.class);
        
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(CAMPUS_CODE_FIELD, Constants.EMPTY_STRING);
        List<? extends BusinessObject> searchResults = service.getSearchResults(fieldValues);
        assertEquals(SEARCH_RESULTS_NO_CAMPUS_CODE_COUNT, searchResults.size());
    }
    
    @Test
    public void testCampusCodeSearchResults() {
        service.setBusinessObjectClass(Unit.class);
        
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(CAMPUS_CODE_FIELD, CAMPUS_CODE);
        List<? extends BusinessObject> searchResults = service.getSearchResults(fieldValues);
        assertEquals(SEARCH_RESULTS_CAMPUS_CODE_COUNT, searchResults.size());
        
        for (BusinessObject searchResult : searchResults) {
            Unit unit = (Unit) searchResult;
            assertTrue(StringUtils.startsWith(unit.getUnitNumber(), CAMPUS_CODE));
        }
    }
}