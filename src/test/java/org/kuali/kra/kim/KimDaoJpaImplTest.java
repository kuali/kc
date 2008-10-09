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
package org.kuali.kra.kim;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.dao.jpa.SpringHelper;
import org.kuali.kra.kim.bo.KimQualifiedRolePerson;
import org.kuali.kra.kim.service.impl.KimDao;

public class KimDaoJpaImplTest {
    private static final long PROPOSAL_CREATOR_ROLE_ID = 1L;
    private static final long AGGREGATOR_ROLE_ID = 2L;
    
    private static final long CREATE_PERMISSION_ID = 1L;
    private static final long UPDATE_PERMISSION_ID = 2L;

    private static final String KIM_DAO_BEAN_ID = "kimDao";
    
    private KimDao dao;
    
    @Before
    public void setUp() throws Exception {
        dao = (KimDao) SpringHelper.getBean(KIM_DAO_BEAN_ID);
    }
    
    @After 
    public void tearDown() throws Exception {
        dao = null;
    }
    
    @Test
    public void testGettingPersonQualifiedRoles() {
        // TODO: This test is a weak verification that this doesn't blow up under JPA. There were no well defined inputs and no results were returned, either by OJB or JPA. 
        Map<String,String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put("age", "46");
        qualifiedRoleAttributes.put("sex", "M");
        Collection<KimQualifiedRolePerson> results = dao.getPersonQualifiedRoles(143L, qualifiedRoleAttributes);
        Assert.assertNotNull(results);
    }
    
    @Test
    public void testHasPermissions() {
        Collection<Long> roleIds = new ArrayList<Long>();
        
        roleIds.add(PROPOSAL_CREATOR_ROLE_ID);
        Assert.assertTrue(dao.hasPermission(roleIds, CREATE_PERMISSION_ID));
        
        roleIds.add(PROPOSAL_CREATOR_ROLE_ID);
        Assert.assertFalse(dao.hasPermission(roleIds, UPDATE_PERMISSION_ID));
        
        roleIds.clear();
        
        roleIds.add(AGGREGATOR_ROLE_ID);
        Assert.assertTrue(dao.hasPermission(roleIds, UPDATE_PERMISSION_ID));
        
        roleIds.add(AGGREGATOR_ROLE_ID);
        Assert.assertFalse(dao.hasPermission(roleIds, CREATE_PERMISSION_ID));    
        
        roleIds.clear();
        
        roleIds.add(PROPOSAL_CREATOR_ROLE_ID);
        roleIds.add(AGGREGATOR_ROLE_ID);
        Assert.assertTrue(dao.hasPermission(roleIds, CREATE_PERMISSION_ID));
        
        roleIds.add(PROPOSAL_CREATOR_ROLE_ID);
        roleIds.add(AGGREGATOR_ROLE_ID);
        Assert.assertTrue(dao.hasPermission(roleIds, UPDATE_PERMISSION_ID));
        
    }
}
