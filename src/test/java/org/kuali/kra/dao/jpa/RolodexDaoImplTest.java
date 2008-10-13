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
package org.kuali.kra.dao.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.dao.RolodexDao;

public class RolodexDaoImplTest {
    private static final String FIRST_NAME_CRITERIA = "firstName";
    private static final String TEST_FIRST_NAME = "Marie";
    
    private static final String POSTAL_CODE_CRITERIA = "postalCode";
    private static final String TEST_POSTAL_CODE = "11787";
    
    private static final String ROLODEX_DAO_BEAN_ID = "rolodexDao";
    
    private RolodexDao dao;
        
    private Map<String, String> fieldValues;
    
    @Before
    public void setUp() throws Exception {
        dao = (RolodexDao) SpringHelper.getBean(ROLODEX_DAO_BEAN_ID);
        fieldValues = new HashMap<String, String>();
    }
    
    @After 
    public void tearDown() throws Exception {
        dao = null;
        fieldValues.clear();
        fieldValues = null;
    }
    
    @Test
    public void testGettingNonOrganizationalRolodexResults_NoExtraCriteria() throws Exception {
        List<NonOrganizationalRolodex> list = dao.getNonOrganizationalRolodexResults(fieldValues, false);
        assertEquals(23, list.size());
    }
    
    @Test
    public void testGettingNonOrganizationalRolodexResults_OneExtraCriteria() throws Exception {
        fieldValues.put(POSTAL_CODE_CRITERIA, TEST_POSTAL_CODE);
        List<NonOrganizationalRolodex> list = dao.getNonOrganizationalRolodexResults(fieldValues, false);
        assertEquals(2, list.size());
    }
    
    @Test
    public void testGettingNonOrganizationalRolodexResults_TwoExtraCriteria() throws Exception {
        fieldValues.put(POSTAL_CODE_CRITERIA, TEST_POSTAL_CODE);
        fieldValues.put(FIRST_NAME_CRITERIA, TEST_FIRST_NAME);
        List<NonOrganizationalRolodex> list = dao.getNonOrganizationalRolodexResults(fieldValues, false);
        assertEquals(1, list.size());
    }
    
    @Test
    public void testTablePerClassMapping() throws Exception {
        EntityManager em = ((EntityManagerFactory) SpringHelper.getBean("entityManagerFactory")).createEntityManager();
        Long rolodexCount = (Long) em.createQuery("select count(r) from Rolodex r").getSingleResult();
        assertTrue(rolodexCount > 0);
        Long nonOrganizationalRolodexCount = (Long) em.createQuery("select count(nr) from NonOrganizationalRolodex nr").getSingleResult();
        assertTrue(nonOrganizationalRolodexCount > 0);
        BigDecimal totalCount = (BigDecimal) em.createNativeQuery("select count(ROLODEX_ID) from ROLODEX").getSingleResult();
        assertEquals(new BigDecimal(rolodexCount), totalCount);
    }
    
}
