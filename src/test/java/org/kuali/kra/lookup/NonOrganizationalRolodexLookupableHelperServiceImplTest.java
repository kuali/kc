/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.lookup.KualiLookupableImpl;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.bo.Rolodex;

import static org.kuali.kra.infrastructure.KraServiceLocator.getTypedService;

/**
 * Class for testing units of functionality for the <code>{@link NonOrganizationalLookupableHelperServiceImpl}
 * 
 */
public class NonOrganizationalRolodexLookupableHelperServiceImplTest extends KraTestBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(NonOrganizationalRolodexLookupableHelperServiceImplTest.class);

    /**
     * 
     * @throws Exception 
     * @see org.kuali.kra.KraTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    /**
     * 
     * @throws Exception 
     * @see org.kuali.kra.KraTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void getResultsNonOrganizational() {
        Map fieldValues = new HashMap();
        fieldValues.put("organization", "Lockheed*"); // Search for organizations that start with National
        fieldValues.put("firstName", "Chris"); // Search for organizations that start with National
        
        KualiLookupableImpl lookupableService = getTypedService("nonOrganizationalRolodexLookupable");
        lookupableService.setBusinessObjectClass(NonOrganizationalRolodex.class);
        
        Collection results = lookupableService.getSearchResults(fieldValues);

        assertEquals(1, results.size());
    }
    
    @Test
    public void getResultsOrganizationalOnly() {
        Map fieldValues = new HashMap();
        fieldValues.put("organization", "Lockheed*"); // Search for organizations that start with National
        
        KualiLookupableImpl lookupableService = getTypedService("nonOrganizationalRolodexLookupable");
        lookupableService.setBusinessObjectClass(NonOrganizationalRolodex.class);
        
        Collection results = lookupableService.getSearchResults(fieldValues);
        
        assertEquals(2, results.size());
    }
}
