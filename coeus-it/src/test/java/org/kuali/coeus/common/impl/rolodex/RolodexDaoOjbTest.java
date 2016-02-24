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
package org.kuali.coeus.common.impl.rolodex;

import org.apache.ojb.broker.query.Criteria;
import org.junit.Test;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex;
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
