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
package org.kuali.kra.service;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.impl.org.OrganizationServiceImpl;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Test the methods in OrganizationServiceImpl.
 */
@RunWith(JMock.class)
public class OrganizationServiceTest {

    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    private static final String ORGANIZATION_ID = "organizationId";
    private static final String ORGANIZATION_VALID_ID_VALUE = "000001";
    private static final String ORGANIZATION_INVALID_ID_VALUE = "100001";
    
    /**
     * Verify that the correct organization is returned if it is found.
     */
    @Test
    public void testGetOrganizationFound() {
        mockOrganization(ORGANIZATION_VALID_ID_VALUE, true);
    }
    
    /**
     * Verify that null is returned if the organization is not found.
     */
    @Test
    public void testGetOrganizationNotFound() {
        mockOrganization(ORGANIZATION_INVALID_ID_VALUE, false);
    }
    
    /**
     * This method is to mock OrganizationServiceImpl
     * Test both valid and invalid organization here
     * @param organizationIdValue
     * @param validOrganization
     */
    private void mockOrganization(String organizationIdValue, boolean validOrganization) {
        OrganizationServiceImpl organizationServiceImpl = new OrganizationServiceImpl();
        
        final Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(ORGANIZATION_ID, organizationIdValue);
        final Organization organization = getOrganization(validOrganization);
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(businessObjectService).findByPrimaryKey(Organization.class, fieldValues); will(returnValue(organization));
        }});
        organizationServiceImpl.setBusinessObjectService(businessObjectService);
        
        assertEquals(organization, organizationServiceImpl.getOrganization(organizationIdValue));
    }
    
    /**
     * This method is to get Organization 
     * New instance for valid organization and null for invalid organization
     * @param validOrganization
     * @return
     */
    private Organization getOrganization(boolean validOrganization) {
        if(validOrganization) {
            return new Organization();
        }else {
            return null;
        }
    }
    
}
