/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.SponsorService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

/**
 * This class tests KraPersistableBusinessObjectBase.
 */
public class SponsorServiceImplTest extends KcUnitTestBase {

    private static final String TEST_SPONSOR_CODE = "005891";
    private static final String TEST_SPONSOR_NAME = "Baystate Medical Center";
    private static final String INVALID_SPONSOR_CODE = "XXXX";
    private static final String TOP_SPONSOR_HIERARCHY = "Administering Activity;1;COI Disclosures;1;NIH Multiple PI;1;NIH Other Significant Contributor;1;Printing;1;Routing;1;Sponsor Groups";
    private SponsorService sponsorService;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        sponsorService = this.getRegularSponsorService();
    }
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        sponsorService = null;
    }
    
    @Test public void testGetSponsorName() throws Exception {
        sponsorService = this.getRegularSponsorService();
        assertEquals(TEST_SPONSOR_NAME, sponsorService.getSponsorName(TEST_SPONSOR_CODE));
    }

    @Test public void testGetSponsorNameInvalidCode() throws Exception {
        sponsorService = this.getRegularSponsorService();
        assertNull(sponsorService.getSponsorName(INVALID_SPONSOR_CODE));
    }
    @Test
    public void testNotEmptyGetTopSponsorHierarch() {
        sponsorService = this.getRegularSponsorService();
        assertEquals(TOP_SPONSOR_HIERARCHY, sponsorService.getTopSponsorHierarchy()); 
    }
    @Test
    public void testEmptyGetTopSponsorHierarch() {
        sponsorService = this.getEmptySponsorService();
        assertEquals("", sponsorService.getTopSponsorHierarchy()); 
    }
    private SponsorService getEmptySponsorService() {
        return new SponsorServiceImpl() {
            public Collection getTopSponsorHierarchyList(){
                return new ArrayList();
            }
        };
    }
    private SponsorService getRegularSponsorService() {
        return KraServiceLocator.getService(SponsorService.class); 
    }


}
