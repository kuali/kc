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
package org.kuali.kra.lookup;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex;
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
 * Tests the {@link NonOrganizationalRolodexLookupableHelperServiceImpl}.
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
