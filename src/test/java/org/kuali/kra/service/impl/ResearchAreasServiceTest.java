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
package org.kuali.kra.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.UserSession;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TestUtilities;
import org.kuali.kra.service.CustomAttributeService;
import org.kuali.kra.service.ResearchAreasService;
import org.kuali.rice.KNSServiceLocator;

public class ResearchAreasServiceTest extends KraTestBase {
    
    private ResearchAreasService researchAreasService = null;
    private BusinessObjectService businessObjectService = null;
    private static String INITIAL_RESEARCH_AREAS="000001 : All Research Areas;1;01. : AGRICULTURE;1;03. : NATURAL RESOURCES AND CONSERVATION;1;04. : ARCHITECTURE AND RELATED SERVICES;1;05. : AREA";
    private static String RESEARCH_AREAS_04_CHILDREN="04.02 : Architecture,04.03 : CityUrban,04.04 : Environmental Design,04.05 : Interior Architecture,04.06 : Landscape Architecture,04.08 : Architectural History and Criticism,04.09 : Architectural TechnologyTechnician,04.99 : Architecture and Related Services";
    private static String ASCENDANTS="000001;1;04.;1;04.99";
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        researchAreasService = KraServiceLocator.getService(ResearchAreasService.class);
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        researchAreasService = null;
        businessObjectService = null;
        super.tearDown();
    }

    @Test 
    public void testGetInitialResearchAreasList() throws Exception {
        assertEquals(researchAreasService.getInitialResearchAreasList(), INITIAL_RESEARCH_AREAS);
    
    }
    @Test 
    public void testGetSubResearchAreasForTreeView() throws Exception {
        assertEquals(researchAreasService.getSubResearchAreasForTreeView("04."), RESEARCH_AREAS_04_CHILDREN);
    
    }
    
    @Test 
    public void testAscendantList() throws Exception {
        assertEquals(researchAreasService.getAscendantList("04.9999"), ASCENDANTS);    
    }

}
