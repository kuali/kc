/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.rolodex.nonorg.NonOrganizationalRolodex;
import org.kuali.coeus.common.impl.rolodex.RolodexDao;
import org.kuali.coeus.common.impl.rolodex.nonorg.NonOrganizationalRolodexLookupableHelperServiceImpl;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.bo.BusinessObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
/**
 * Tests the {@link NonOrganizationalLookupableHelperServiceImpl}.
 */
public class NonOrganizationalRolodexLookupableHelperServiceImplTest extends KcIntegrationTestBase {
    
    private NonOrganizationalRolodexLookupableHelperServiceImpl nonOrganizationalRolodexLookupableHelperServiceImpl;

    @Before
    public void setUp() throws Exception {
        nonOrganizationalRolodexLookupableHelperServiceImpl = new NonOrganizationalRolodexLookupableHelperServiceImpl();
        nonOrganizationalRolodexLookupableHelperServiceImpl.setBusinessObjectClass(NonOrganizationalRolodex.class);
        nonOrganizationalRolodexLookupableHelperServiceImpl.setRolodexDao(KcServiceLocator.getService(RolodexDao.class));
    }

    @After
    public void tearDown() throws Exception {
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