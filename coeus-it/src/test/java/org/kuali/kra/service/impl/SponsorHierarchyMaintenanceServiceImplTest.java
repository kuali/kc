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
package org.kuali.kra.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.impl.sponsor.SponsorHierarchyMaintenanceService;
import org.kuali.coeus.common.impl.sponsor.SponsorHierarchyMaintenanceServiceImpl;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
/**
 * This class tests KraPersistableBusinessObjectBase.
 */
public class SponsorHierarchyMaintenanceServiceImplTest extends KcIntegrationTestBase {

    private static final String TEST_SPONSOR_CODE = "005891";
    private static final String TEST_SPONSOR_NAME = "Baystate Medical Center";
    private static final String INVALID_SPONSOR_CODE = "XXXX";
    private static final String TOP_SPONSOR_HIERARCHY = "Administering Activity;1;COI Disclosures;1;NIH Multiple PI;1;NIH Other Significant Contributor;1;Printing;1;Routing;1;Sponsor Groups";
    private SponsorHierarchyMaintenanceService sponsorService;
    
    @Before
    public void setUp() throws Exception {
        sponsorService = this.getRegularSponsorService();
    }
    @After
    public void tearDown() throws Exception {
        sponsorService = null;
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
    private SponsorHierarchyMaintenanceService getEmptySponsorService() {
        return new SponsorHierarchyMaintenanceServiceImpl() {
            public Collection getTopSponsorHierarchyList(){
                return new ArrayList();
            }
        };
    }
    private SponsorHierarchyMaintenanceService getRegularSponsorService() {
        return KcServiceLocator.getService(SponsorHierarchyMaintenanceService.class);
    }


}
