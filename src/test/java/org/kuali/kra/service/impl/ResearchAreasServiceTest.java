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
    private static String RESEARCH_AREAS_04_CHILDREN="<h3>04.02 %3A Architecture</h3><h3>04.03 %3A CityUrban</h3><h3>04.04 %3A Environmental Design</h3><h3>04.05 %3A Interior Architecture</h3><h3>04.06 %3A Landscape Architecture</h3><h3>04.08 %3A Architectural History and Criticism</h3><h3>04.09 %3A Architectural TechnologyTechnician</h3><h3>04.99 %3A Architecture and Related Services</h3>";
    

    /**
     * 
     * This method to test that the new researchareacode is ok because the one found is DB
     * is being removed, but has not been physically removed from DB yet.
     * @throws Exception
     */
    @Test 
    public void testIsResearchAreaExistFalse() throws Exception {
        ResearchAreasServiceImpl researchAreasService = new ResearchAreasServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("researchAreaCode", "03.99");
            one(businessObjectService).findByPrimaryKey(ResearchArea.class, fieldValues);
            will(returnValue(new ResearchArea("03.99","03.","")));

            fieldValues = new HashMap<String, Object>();
            fieldValues.put("parentResearchAreaCode", "03.");
            one(businessObjectService).findMatchingOrderBy(ResearchArea.class, fieldValues, "researchAreaCode", true);
            will(returnValue(getSubResearchAreasFor03()));
            
        }});
        researchAreasService.setBusinessObjectService(businessObjectService);
        org.junit.Assert.assertFalse(researchAreasService.isResearchAreaExist("03.99", "03."));
    
    }
    
    /**
     * 
     * This method to test that the new researchareacode is Not ok because it is found in DB
     * @throws Exception
     */
    @Test 
    public void testIsResearchAreaExistTrue() throws Exception {
        ResearchAreasServiceImpl researchAreasService = new ResearchAreasServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("researchAreaCode", "04.99");
            one(businessObjectService).findByPrimaryKey(ResearchArea.class, fieldValues);
            will(returnValue(new ResearchArea("04.99","04.","")));

            fieldValues = new HashMap<String, Object>();
            fieldValues.put("parentResearchAreaCode", "03.");
            one(businessObjectService).findMatchingOrderBy(ResearchArea.class, fieldValues, "researchAreaCode", true);
            will(returnValue(getSubResearchAreasFor03()));
            
            fieldValues = new HashMap<String, Object>();
            fieldValues.put("parentResearchAreaCode", "03.99");
            one(businessObjectService).findMatchingOrderBy(ResearchArea.class, fieldValues, "researchAreaCode", true);
            will(returnValue(new ArrayList<ResearchArea>()));
        }});
        researchAreasService.setBusinessObjectService(businessObjectService);
        org.junit.Assert.assertTrue(researchAreasService.isResearchAreaExist("04.99", "03."));
    
    }
    
    /**
     * 
     * This method is to get the sub-researchareas of this researchareacode
     * @throws Exception
     */
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
    
    private List<ResearchArea> getSubResearchAreasFor03() {
        List<ResearchArea> researchAreasList = new ArrayList<ResearchArea>();
        researchAreasList.add(new ResearchArea("03.99","03.", "Architecture and Related Services"));
        return researchAreasList;
    }

}
