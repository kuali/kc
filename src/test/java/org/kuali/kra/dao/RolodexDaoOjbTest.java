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
package org.kuali.kra.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ojb.broker.query.Criteria;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.bo.NonOrganizationalRolodex;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

/**
 * Test the Data Access Object implementation for <code>{@link Rolodex}</code> business objects
 * 
 */
public class RolodexDaoOjbTest extends KraTestBase {
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After 
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void getNonOrganizationalRolodexCriteriaWithNoName() {
        Map fieldValues = new HashMap();
        fieldValues.put("organization", "National*"); // Search for organizations that start with National
        
        Criteria criteria = getRolodexDao().getNonOrganizationalRolodexCriteria(NonOrganizationalRolodex.class, fieldValues, false);

        assertFalse(-1 == criteria.toString().indexOf("LIKE NATIONAL%"));
        assertFalse(-1 == criteria.toString().indexOf("firstName IS NOT NULL"));
        assertFalse(-1 == criteria.toString().indexOf("lastName IS NOT NULL"));
        assertEquals(criteria.toString(), "[UPPER(organization) LIKE NATIONAL%, firstName IS NOT NULL , lastName IS NOT NULL ]");
    }

    @Test
    public void getNonOrganizationalRolodexCriteriaWithFirstName() {
        Map fieldValues = new HashMap();
        fieldValues.put("organization", "National*"); // Search for organizations that start with National
        fieldValues.put("firstName", "David"); // Search for organizations that start with National
        
        Criteria criteria = getRolodexDao().getNonOrganizationalRolodexCriteria(NonOrganizationalRolodex.class, fieldValues, false);        
        assertNotNull(criteria);
        assertFalse(-1 == criteria.toString().indexOf("LIKE NATIONAL%"));
        assertFalse(-1 == criteria.toString().indexOf("LIKE DAVID"));
        assertFalse(-1 == criteria.toString().indexOf("firstName IS NOT NULL"));
        assertFalse(-1 == criteria.toString().indexOf("lastName IS NOT NULL"));
    }
    
    private RolodexDao getRolodexDao() {
        return getService(RolodexDao.class);
    }
}
