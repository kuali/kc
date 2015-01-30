/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.bo;

import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup11V11.CitizenshipDataType;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup11V11.CitizenshipDataType.Enum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.person.attr.CitizenshipType;
import org.kuali.coeus.s2sgen.impl.citizenship.CitizenshipTypeService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
public class CitizenshipTypeTest extends KcIntegrationTestBase {
    
    private BusinessObjectService businessObjectService;
    
    private int TEST_CODE = -1;
    private String TEST_DESCRIPTION = "test citizenship";
    
    private CitizenshipTypeService citizenshipService;

    @Before
    public void setUp() throws Exception {
        businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        citizenshipService = KcServiceLocator.getService(CitizenshipTypeService.class);
    }

    @After
    public void tearDown() throws Exception {
        businessObjectService = null;
        citizenshipService = null;
    }
    
    @Test
    public void makeNewCitizenshipType() throws Exception {
        CitizenshipType ct = new CitizenshipType();
        ct.setCode(TEST_CODE);
        ct.setDescription(TEST_DESCRIPTION);
        ct.setActive(true);
        assertNull(ct.getObjectId());
        businessObjectService.save(ct);
        assertNotNull(ct.getObjectId());
    }
    
    @Test
    public void findAndUpdateTestCitizenshipType() throws Exception {
        Map arguments = new HashMap();
        arguments.put("CITIZENSHIP_TYPE_CODE", "1");
        CitizenshipType ct = (CitizenshipType)businessObjectService.findByPrimaryKey(CitizenshipType.class, arguments);
        assertEquals("US CITIZEN OR NONCITIZEN NATIONAL", ct.getDescription());
        assertEquals(new Long(1), ct.getVersionNumber());
        
        String testDescr = "something cool";
        
        ct.setDescription(testDescr);
        businessObjectService.save(ct);
        
        CitizenshipType ct2 = (CitizenshipType)businessObjectService.findByPrimaryKey(CitizenshipType.class, arguments);
        assertEquals(testDescr, ct2.getDescription());
    }
    
    @Test
    public void testGetEnumValueOfCitizenshipType1() throws Exception {
        Map arguments = new HashMap();
        arguments.put("CITIZENSHIP_TYPE_CODE", "1");
        CitizenshipType ct = (CitizenshipType)businessObjectService.findByPrimaryKey(CitizenshipType.class, arguments); 
        Enum result = citizenshipService.getEnumValueOfCitizenshipType(ct);
        assertEquals(CitizenshipDataType.INT_U_S_CITIZEN_OR_NONCITIZEN_NATIONAL, result.intValue());
    }
       
    @Test
    public void testGetEnumValueOfCitizenshipType2() throws Exception {
        Map arguments = new HashMap();
        arguments.put("CITIZENSHIP_TYPE_CODE", "2");
        CitizenshipType ct = (CitizenshipType)businessObjectService.findByPrimaryKey(CitizenshipType.class, arguments); 
        Enum result = citizenshipService.getEnumValueOfCitizenshipType(ct);
        assertEquals(CitizenshipDataType.INT_PERMANENT_RESIDENT_OF_U_S, result.intValue());
    }
    
    @Test
    public void testGetEnumValueOfCitizenshipType3() throws Exception {
        Map arguments = new HashMap();
        arguments.put("CITIZENSHIP_TYPE_CODE", "3");
        CitizenshipType ct = (CitizenshipType)businessObjectService.findByPrimaryKey(CitizenshipType.class, arguments);
        Enum result = citizenshipService.getEnumValueOfCitizenshipType(ct);
        assertEquals(CitizenshipDataType.INT_NON_U_S_CITIZEN_WITH_TEMPORARY_VISA, result.intValue());
    }
    
    @Test
    public void testGetEnumValueOfCitizenshipType4() throws Exception {
        CitizenshipType testType = new CitizenshipType();
        testType.setCode(-101);
        testType.setDescription("super awesome cool description");
        try {
            Enum result = citizenshipService.getEnumValueOfCitizenshipType(testType);
        } catch (IllegalArgumentException iae) {
            assertEquals("Invalid citizenship type provided", iae.getMessage());
            return;
        }
        //the getEnumValue function should throw an exception, if not, it accepted an invalid citisenship type
        assertTrue(false);
    }
}
