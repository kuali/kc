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

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.dao.SponsorHierarchyDao;

import static org.junit.Assert.*;

public class SponsorHierarchyDaoJpaIntegrationTest {
    private static final String SPONSOR_HIERARCHY_DAO_BEAIN_ID = "sponsorHierarchyDao";
    
    private SponsorHierarchyDao dao;
    
    @Before
    public void setUp() throws Exception {
        dao = (SponsorHierarchyDao) SpringHelper.getBean(SPONSOR_HIERARCHY_DAO_BEAIN_ID);
    }
    
    @After 
    public void tearDown() throws Exception {
        dao = null;
    }
    
    @Test
    public void testGettingTopSponsorHierarchy() {
        Iterator iter = dao.getTopSponsorHierarchy();
        assertTrue(iter.hasNext());
    }
    
//    @Test
//    public void testGettingAllSponsors() {
//        fail();
//    }
//    
//    @Test
//    public void testGettingSponsorCodesForGroup() {
//        fail();
//    }
//    
//    @Test
//    public void testGettingSponsorCodesForDeletedGroup() {
//        fail();
//    }
//    
//    @Test
//    public void testGettingSubGroups() {
//        fail();
//    }
//    
//    @Test
//    public void testRunningScripts() {
//        fail();
//    }
}
