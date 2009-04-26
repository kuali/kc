/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.service.impl.OrganizationServiceImpl;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * Test the methods in OrganizationServiceImpl.
 */
@RunWith(JMock.class)
public class OrganizationServiceTest {

    private Mockery context = new JUnit4Mockery();
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
