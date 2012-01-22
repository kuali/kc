/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.lookup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.dao.RolodexDao;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.krad.bo.BusinessObject;

/**
 * Tests the {@link NonOrganizationalLookupableHelperServiceImpl}.
 */
public class NonOrganizationalRolodexLookupableHelperServiceImplTest extends KcUnitTestBase {
    
    private NonOrganizationalRolodexLookupableHelperServiceImpl nonOrganizationalRolodexLookupableHelperServiceImpl;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        nonOrganizationalRolodexLookupableHelperServiceImpl = new NonOrganizationalRolodexLookupableHelperServiceImpl();
        nonOrganizationalRolodexLookupableHelperServiceImpl.setBusinessObjectClass(NonOrganizationalRolodex.class);
        nonOrganizationalRolodexLookupableHelperServiceImpl.setRolodexDao(KraServiceLocator.getService(RolodexDao.class));
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        nonOrganizationalRolodexLookupableHelperServiceImpl = null;
    }
    
    /**
     * Verifies that the non-organizational search for an {@code organization} matching 'Lockheed*' and a {@code firstName} matching 'Chris' returns one result.
     */
    @Test
    public void getResultsNonOrganizational() {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("organization", "Lockheed*");
        fieldValues.put("firstName", "Chris");

        List<? extends BusinessObject> results = nonOrganizationalRolodexLookupableHelperServiceImpl.getSearchResults(fieldValues);

        assertEquals(1, results.size());
    }
    
    /**
     * Verifies that the non-organizational search for an {@code organization} matching 'George*' returns six results.
     */
    @Test
    public void getResultsOrganizationalOnly() {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("organization", "George*");

        List<? extends BusinessObject> results = nonOrganizationalRolodexLookupableHelperServiceImpl.getSearchResults(fieldValues);
        
        assertEquals(6, results.size());
    }

}