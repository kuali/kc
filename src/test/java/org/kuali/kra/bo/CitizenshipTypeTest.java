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
package org.kuali.kra.bo;

import static org.junit.Assert.assertEquals;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup11V11.CitizenshipDataType;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup11V11.CitizenshipDataType.Enum;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.service.BusinessObjectService;

public class CitizenshipTypeTest extends KcUnitTestBase {
    
    private BusinessObjectService service;
    
    private int TEST_CODE = -1;
    private String TEST_DESCRIPTION = "test citizenship";

    @Before
    public void setUp() throws Exception {
        service = KraServiceLocator.getService(BusinessObjectService.class);
    }

    @After
    public void tearDown() throws Exception {
        service = null;
    }
    
    @Test
    public void makeNewCitizenshipType() throws Exception {
        CitizenshipType ct = new CitizenshipType();
        ct.setCitizenshipTypeCode(TEST_CODE);
        ct.setDescription(TEST_DESCRIPTION);
        ct.setActive(true);
        assertNull(ct.getObjectId());
        service.save(ct);
        assertNotNull(ct.getObjectId());
    }
    
    @Test
    public void findAndUpdateTestCitizenshipType() throws Exception {
        Map arguments = new HashMap();
        arguments.put("CITIZENSHIP_TYPE_CODE", "1");
        CitizenshipType ct = (CitizenshipType)service.findByPrimaryKey(CitizenshipType.class, arguments);
        assertEquals("US CITIZEN OR NONCITIZEN NATIONAL", ct.getDescription());
        assertEquals(new Long(1), ct.getVersionNumber());
        
        String testDescr = "something cool";
        
        ct.setDescription(testDescr);
        service.save(ct);
        
        CitizenshipType ct2 = (CitizenshipType)service.findByPrimaryKey(CitizenshipType.class, arguments);
        assertEquals(testDescr, ct2.getDescription());
    }
    
    @Test
    public void testGetEnumValueOfCitizenshipType1() throws Exception {
        Map arguments = new HashMap();
        arguments.put("CITIZENSHIP_TYPE_CODE", "1");
        CitizenshipType ct = (CitizenshipType)service.findByPrimaryKey(CitizenshipType.class, arguments); 
        assertEquals(CitizenshipDataType.INT_U_S_CITIZEN_OR_NONCITIZEN_NATIONAL, ct.getEnumValueOfCitizenshipType().intValue());
    }
       
    @Test
    public void testGetEnumValueOfCitizenshipType2() throws Exception {
        Map arguments = new HashMap();
        arguments.put("CITIZENSHIP_TYPE_CODE", "2");
        CitizenshipType ct = (CitizenshipType)service.findByPrimaryKey(CitizenshipType.class, arguments); 
        assertEquals(CitizenshipDataType.INT_PERMANENT_RESIDENT_OF_U_S, ct.getEnumValueOfCitizenshipType().intValue());
    }
    
    @Test
    public void testGetEnumValueOfCitizenshipType3() throws Exception {
        Map arguments = new HashMap();
        arguments.put("CITIZENSHIP_TYPE_CODE", "3");
        CitizenshipType ct = (CitizenshipType)service.findByPrimaryKey(CitizenshipType.class, arguments); 
        assertEquals(CitizenshipDataType.INT_NON_U_S_CITIZEN_WITH_TEMPORARY_VISA, ct.getEnumValueOfCitizenshipType().intValue());
    }
    
    @Test
    public void testGetEnumValueOfCitizenshipType4() throws Exception {
        CitizenshipType testType = new CitizenshipType();
        testType.setCitizenshipTypeCode(-101);
        testType.setDescription("super awesome cool description");
        try {
            Enum results = testType.getEnumValueOfCitizenshipType();
        } catch (IllegalArgumentException iae) {
            assertEquals("Invalid citizenship type provided", iae.getMessage());
            return;
        }
        //the getEnumValue function should throw an exception, if not, it accepted an invalid citisenship type
        assertTrue(false);
    }
}
