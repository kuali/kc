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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.rice.kns.service.BusinessObjectService;

@RunWith(JMock.class)
public class ResearchAreasServiceTest {
    
    private Mockery context = new JUnit4Mockery();
    private static String INITIAL_RESEARCH_AREAS="000001 : All Research Areas;1;01. : AGRICULTURE;1;03. : NATURAL RESOURCES AND CONSERVATION;1;04. : ARCHITECTURE AND RELATED SERVICES;1;05. : AREA";
    private static String RESEARCH_AREAS_04_CHILDREN="04.02 : Architecture,04.03 : CityUrban,04.04 : Environmental Design,04.05 : Interior Architecture,04.06 : Landscape Architecture,04.08 : Architectural History and Criticism,04.09 : Architectural TechnologyTechnician,04.99 : Architecture and Related Services";
    private static String ASCENDANTS="000001;1;04.;1;04.99";
    

    @Test 
    public void testGetInitialResearchAreasList() throws Exception {
        ResearchAreasServiceImpl researchAreasService = new ResearchAreasServiceImpl();
        
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("parentResearchAreaCode", "000000");
            List<ResearchArea> researchAreasList = new ArrayList<ResearchArea>();
            ResearchArea researchAreas = new ResearchArea();
            researchAreas.setResearchAreaCode("000001");
            researchAreas.setParentResearchAreaCode("000000");
            researchAreas.setDescription("All Research Areas");
            researchAreasList.add(researchAreas);

            one(businessObjectService).findMatchingOrderBy(ResearchArea.class, fieldValues, "researchAreaCode", true);
            will(returnValue(researchAreasList));
            fieldValues = new HashMap<String, Object>();
            fieldValues.put("parentResearchAreaCode", "000001");
            one(businessObjectService).findMatchingOrderBy(ResearchArea.class, fieldValues, "researchAreaCode", true);
            will(returnValue(getSubResearchAreasFor000001()));
        }});
        researchAreasService.setBusinessObjectService(businessObjectService);
        org.junit.Assert.assertEquals(researchAreasService.getInitialResearchAreasList(), INITIAL_RESEARCH_AREAS);

    }
    
    @Test 
    public void testGetSubResearchAreasForTreeView() throws Exception {
        ResearchAreasServiceImpl researchAreasService = new ResearchAreasServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("parentResearchAreaCode", "04.");
            one(businessObjectService).findMatchingOrderBy(ResearchArea.class, fieldValues, "researchAreaCode", true);
            will(returnValue(getSubResearchAreasFor04()));
        }});
        researchAreasService.setBusinessObjectService(businessObjectService);
        org.junit.Assert.assertEquals(researchAreasService.getSubResearchAreasForTreeView("04."), RESEARCH_AREAS_04_CHILDREN);
    
    }
    
    @Test 
    public void testAscendantList() throws Exception {
        ResearchAreasServiceImpl researchAreasService = new ResearchAreasServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("researchAreaCode", "04.9999");
            one(businessObjectService).findByPrimaryKey(ResearchArea.class, fieldValues);
            will(returnValue(new ResearchArea("04.9999","04.99","")));

            fieldValues = new HashMap<String, Object>();
            fieldValues.put("researchAreaCode", "04.99");
            one(businessObjectService).findByPrimaryKey(ResearchArea.class, fieldValues);
            will(returnValue(new ResearchArea("04.99","04.","")));
            
            fieldValues = new HashMap<String, Object>();
            fieldValues.put("researchAreaCode", "04.");
            one(businessObjectService).findByPrimaryKey(ResearchArea.class, fieldValues);
            will(returnValue(new ResearchArea("04.","000001","")));
            
            fieldValues = new HashMap<String, Object>();
            fieldValues.put("researchAreaCode", "000001");
            one(businessObjectService).findByPrimaryKey(ResearchArea.class, fieldValues);
            will(returnValue(new ResearchArea("000001","000000","")));
        }});
        researchAreasService.setBusinessObjectService(businessObjectService);
        org.junit.Assert.assertEquals(researchAreasService.getAscendantList("04.9999"), ASCENDANTS);    
    }

    
    private List<ResearchArea> getSubResearchAreasFor000001() {
        List<ResearchArea> researchAreasList = new ArrayList<ResearchArea>();
        researchAreasList.add(new ResearchArea("01.","000001", "AGRICULTURE"));
        researchAreasList.add(new ResearchArea("03.","000001", "NATURAL RESOURCES AND CONSERVATION"));
        researchAreasList.add(new ResearchArea("04.","000001", "ARCHITECTURE AND RELATED SERVICES"));
        researchAreasList.add(new ResearchArea("05.","000001", "AREA"));
        return researchAreasList;

    }

    private List<ResearchArea> getSubResearchAreasFor04() {
        List<ResearchArea> researchAreasList = new ArrayList<ResearchArea>();
        researchAreasList.add(new ResearchArea("04.02","04.", "Architecture"));
        researchAreasList.add(new ResearchArea("04.03","04.", "CityUrban"));
        researchAreasList.add(new ResearchArea("04.04","04.", "Environmental Design"));
        researchAreasList.add(new ResearchArea("04.05","04.", "Interior Architecture"));
        researchAreasList.add(new ResearchArea("04.06","04.", "Landscape Architecture"));
        researchAreasList.add(new ResearchArea("04.08","04.", "Architectural History and Criticism"));
        researchAreasList.add(new ResearchArea("04.09","04.", "Architectural TechnologyTechnician"));
        researchAreasList.add(new ResearchArea("04.99","04.", "Architecture and Related Services"));
        return researchAreasList;
    }
}
