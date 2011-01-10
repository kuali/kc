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
    
    private String TEST_CODE = "tst";
    private String TEST_DESCRIPTION = "test citizenship";

    @Before
    public void setUp() throws Exception {
        service = KraServiceLocator.getService(BusinessObjectService.class);
    }

    @After
    public void tearDown() throws Exception {
        service = null;
    }
    
    //@Test
    /*
    public void makeNewCitizenshipType() throws Exception {
        CitizenshipType ct = new CitizenshipType();
        ct.setCitizenTypeCode(TEST_CODE);
        ct.setDescription(TEST_DESCRIPTION);
        ct.setActive(true);
        assertNull(ct.getObjectId());
        service.save(ct);
        assertNotNull(ct.getObjectId());
    }*/
    
    //@Test
    /*
    public void findAndUpdateTestCitizenshipType() throws Exception {
        Map arguments = new HashMap();
        arguments.put("CITIZENSHIP_TYPE_CODE", "ABW");
        CitizenshipType ct = (CitizenshipType)service.findByPrimaryKey(CitizenshipType.class, arguments);
        assertEquals("Aruba", ct.getDescription());
        assertEquals(new Long(1), ct.getVersionNumber());
        
        ct.setDescription("Aruba Tester");
        service.save(ct);
        
        CitizenshipType ct2 = (CitizenshipType)service.findByPrimaryKey(CitizenshipType.class, arguments);
        assertEquals("Aruba Tester", ct2.getDescription());
    }*/
}
