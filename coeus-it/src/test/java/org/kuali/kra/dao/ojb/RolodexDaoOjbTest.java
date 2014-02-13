/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.dao.ojb;

import org.apache.ojb.broker.query.Criteria;
import org.junit.Test;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.dao.RolodexDao;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
/**
 * Test the Data Access Object implementation for <code>{@link Rolodex}</code> business objects
 * 
 */
public class RolodexDaoOjbTest extends KcIntegrationTestBase {
    
    @Test
    public void getNonOrganizationalRolodexCriteriaWithNoName() {
        Map fieldValues = new HashMap();
        fieldValues.put("organization", "National*"); // Search for organizations that start with National
        
        Criteria criteria = getRolodexDao().getNonOrganizationalRolodexCriteria(NonOrganizationalRolodex.class, fieldValues, false);

        assertFalse(-1 == criteria.toString().indexOf("LIKE NATIONAL%"));
        assertFalse(-1 == criteria.toString().indexOf("firstName IS NOT NULL"));
        assertFalse(-1 == criteria.toString().indexOf("lastName IS NOT NULL"));
        assertFalse(-1 == criteria.toString().indexOf("active <> false"));
        
        assertEquals(criteria.toString(), "[UPPER(organization) LIKE NATIONAL%, firstName IS NOT NULL , lastName IS NOT NULL , active <> false]");
    }

    @Test(expected=IllegalArgumentException.class)
    public void getNonOrganizationalRolodexCriteriaNullClass() {
        Map fieldValues = new HashMap();
        fieldValues.put("organization", "National*"); // Search for organizations that start with National
        
        Criteria criteria = getRolodexDao().getNonOrganizationalRolodexCriteria(null, fieldValues, false);
    }
    
    @Test(expected=RuntimeException.class)
    public void getNonOrganizationalRolodexCriteriaPrimaryKeys() {
        Map fieldValues = new HashMap();
        fieldValues.put("organization", "National*"); // Search for organizations that start with National
        
        Criteria criteria = getRolodexDao().getNonOrganizationalRolodexCriteria(NonOrganizationalRolodex.class, fieldValues, true);
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
    
    private RolodexDaoOjb getRolodexDao() {
        return (RolodexDaoOjb) getService(RolodexDao.class);
    }
}
