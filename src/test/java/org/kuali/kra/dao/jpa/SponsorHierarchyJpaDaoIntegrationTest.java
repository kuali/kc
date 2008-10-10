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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.dao.SponsorHierarchyDao;
import org.kuali.rice.exceptions.RiceRuntimeException;

public class SponsorHierarchyJpaDaoIntegrationTest {
    private static final String[] LEVEL_NAMES = new String[] {"Industrial"};

    private static final int SPONOSR_HIERARCHY_LEVEL = 1;

    private static final String GOOD_HIERARCHY_NAME = "MIT Brown Book";

    private static final String BAD_HIERARCHY_NAME = "BAD_NAME";

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
        assertTrue(dao.getTopSponsorHierarchy().hasNext());
    }
    
    @Test
    public void testGettingAllSponsors() {
        assertFalse(dao.getAllSponsors(BAD_HIERARCHY_NAME).hasNext());
        assertTrue(dao.getAllSponsors(GOOD_HIERARCHY_NAME).hasNext());        
    }
    
    @Test
    public void testGettingSponsorCodesForGroup() {
        assertFalse(dao.getSponsorCodesForGroup(BAD_HIERARCHY_NAME, SPONOSR_HIERARCHY_LEVEL, LEVEL_NAMES).length() > 0);
        assertTrue(dao.getSponsorCodesForGroup(GOOD_HIERARCHY_NAME, SPONOSR_HIERARCHY_LEVEL, LEVEL_NAMES).length() > 0);
    }
    
    @Test
    public void testGettingSponsorCodesForDeletedGroup() {
        assertFalse(dao.getSponsorCodesForDeletedGroup(BAD_HIERARCHY_NAME, SPONOSR_HIERARCHY_LEVEL, LEVEL_NAMES).length() > 0);
        assertTrue(dao.getSponsorCodesForDeletedGroup(GOOD_HIERARCHY_NAME, SPONOSR_HIERARCHY_LEVEL, LEVEL_NAMES).length() > 0);
    }
    
    @Test
    public void testGettingSubGroups() {
        assertFalse(dao.getSubGroups(BAD_HIERARCHY_NAME, SPONOSR_HIERARCHY_LEVEL, LEVEL_NAMES).length() > 0);
        assertTrue(dao.getSubGroups(GOOD_HIERARCHY_NAME, SPONOSR_HIERARCHY_LEVEL, LEVEL_NAMES).length() > 0);
    }
    
    @Test
    public void testRunningScripts() {
        dao.runScripts(new String[] {"select * from DUAL"} );
    }
    
    @Test(expected=RiceRuntimeException.class)
    public void testRunningScripts_ProducesException_InvalidTable() {
        dao.runScripts(new String[] {"select * from FOO_BAR"} );
    }
    
    @Test(expected=RiceRuntimeException.class)
    public void testRunningScripts_ProducesException_InvalidOperation() {
        dao.runScripts(new String[] {"DROP TABLE FOO_BAR"} );
    }
}
