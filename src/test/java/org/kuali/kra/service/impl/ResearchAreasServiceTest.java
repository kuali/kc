/*
 * Copyright 2005-2010 The Kuali Foundation
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
    private static String RESEARCH_AREAS_04_CHILDREN="<h3>04.02 %3A Architecture %4A true</h3><h3>04.03 %3A CityUrban %4A true</h3><h3>04.04 %3A Environmental Design %4A true</h3><h3>04.05 %3A Interior Architecture %4A true</h3><h3>04.06 %3A Landscape Architecture %4A true</h3><h3>04.08 %3A Architectural History and Criticism %4A true</h3><h3>04.09 %3A Architectural TechnologyTechnician %4A true</h3><h3>04.99 %3A Architecture and Related Services %4A true</h3>";
    

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
            will(returnValue(new ResearchArea("03.99","03.","", true)));

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
            will(returnValue(new ResearchArea("04.99","04.","", true)));

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
        org.junit.Assert.assertEquals(researchAreasService.getSubResearchAreasForTreeView("04.", false), RESEARCH_AREAS_04_CHILDREN);
    
    }
        
    private List<ResearchArea> getSubResearchAreasFor04() {
        List<ResearchArea> researchAreasList = new ArrayList<ResearchArea>();
        researchAreasList.add(new ResearchArea("04.02","04.", "Architecture", true));
        researchAreasList.add(new ResearchArea("04.03","04.", "CityUrban", true));
        researchAreasList.add(new ResearchArea("04.04","04.", "Environmental Design", true));
        researchAreasList.add(new ResearchArea("04.05","04.", "Interior Architecture", true));
        researchAreasList.add(new ResearchArea("04.06","04.", "Landscape Architecture", true));
        researchAreasList.add(new ResearchArea("04.08","04.", "Architectural History and Criticism", true));
        researchAreasList.add(new ResearchArea("04.09","04.", "Architectural TechnologyTechnician", true));
        researchAreasList.add(new ResearchArea("04.99","04.", "Architecture and Related Services", true));
        return researchAreasList;
    }
    
    private List<ResearchArea> getSubResearchAreasFor03() {
        List<ResearchArea> researchAreasList = new ArrayList<ResearchArea>();
        researchAreasList.add(new ResearchArea("03.99","03.", "Architecture and Related Services", true));
        return researchAreasList;
    }

    @Test 
    public void testRaCreate() throws Exception {
        String raChangeXML =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<RaChanges>" +
                "<RaChangesElement>" +
                    "<RaCreate>" +
                        "<Code>04.123</Code>" +
                        "<ParentCode>04.</ParentCode>" +
                        "<Description>Sample research area</Description>" +
                        "<Active>true</Active>" +
                    "</RaCreate>" +
                "</RaChangesElement>" +
            "</RaChanges>";
        
        ResearchAreasServiceImpl researchAreasService = new ResearchAreasServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            final ResearchArea createResearchArea = new ResearchArea("04.123", "04.", "Sample research area", true);
            oneOf(businessObjectService).save(createResearchArea);

            final ResearchArea parentResearchArea = new ResearchArea("04.", "", "Parent", true);
            oneOf(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, "04.");
            will(returnValue(parentResearchArea));
            
            oneOf(businessObjectService).save(parentResearchArea);
        }});
        researchAreasService.setBusinessObjectService(businessObjectService);

        researchAreasService.saveResearchAreas(raChangeXML);
    }
    
    @Test 
    public void testRaUpdateDescription() throws Exception {
        String raChangeXML =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<RaChanges>" +
                "<RaChangesElement>" +
                    "<RaUpdateDescription>" +
                        "<Code>05.12</Code>" +
                        "<Description>new description</Description>" +
                    "</RaUpdateDescription>" +
                "</RaChangesElement>" +
            "</RaChanges>";
        
        ResearchAreasServiceImpl researchAreasService = new ResearchAreasServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            final ResearchArea updateDescriptionResearchArea = new ResearchArea("05.12", "05.", "Sample research area", true);
            oneOf(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, "05.12");
            will(returnValue(updateDescriptionResearchArea));
            
            updateDescriptionResearchArea.setDescription("new description");
            oneOf(businessObjectService).save(updateDescriptionResearchArea);
        }});
        researchAreasService.setBusinessObjectService(businessObjectService);

        researchAreasService.saveResearchAreas(raChangeXML);
    }

    @Test 
    public void testRaUpdateActiveIndicator() throws Exception {
        String raChangeXML =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<RaChanges>" +
                "<RaChangesElement>" +
                    "<RaUpdateActiveIndicator>" +
                        "<Code>05.12</Code>" +
                        "<Active>false</Active>" +
                    "</RaUpdateActiveIndicator>" +
                "</RaChangesElement>" +
            "</RaChanges>";
        
        ResearchAreasServiceImpl researchAreasService = new ResearchAreasServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            final ResearchArea updateActiveResearchArea = new ResearchArea("05.12", "05.", "Sample research area", true);
            oneOf(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, "05.12");
            will(returnValue(updateActiveResearchArea));
            
            updateActiveResearchArea.setActive(false);
            oneOf(businessObjectService).save(updateActiveResearchArea);
            
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("parentResearchAreaCode", "05.12");
            oneOf(businessObjectService).findMatching(ResearchArea.class, fieldValues);
            will(returnValue(new ArrayList()));
        }});
        researchAreasService.setBusinessObjectService(businessObjectService);

        researchAreasService.saveResearchAreas(raChangeXML);
    }

    @Test 
    public void testRaUpdateParent() throws Exception {
        String raChangeXML =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<RaChanges>" +
                "<RaChangesElement>" +
                    "<RaUpdateParent>" +
                        "<Code>03.24</Code>" +
                        "<OldParent>03.</OldParent>" +
                        "<NewParent>07.</NewParent>" +
                    "</RaUpdateParent>" +
                "</RaChangesElement>" +
            "</RaChanges>";
        
        ResearchAreasServiceImpl researchAreasService = new ResearchAreasServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            final ResearchArea updateParentResearchArea = new ResearchArea("03.24", "03.", "Sample research area", true);
            oneOf(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, "03.24");
            will(returnValue(updateParentResearchArea));
            
            updateParentResearchArea.setParentResearchAreaCode("07.");
            oneOf(businessObjectService).save(updateParentResearchArea);
            
            final ResearchArea newParentResearchArea = new ResearchArea("07.", "", "Parent", true);
            newParentResearchArea.setHasChildrenFlag(false);
            oneOf(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, "07.");
            will(returnValue(newParentResearchArea));
            
            oneOf(businessObjectService).save(newParentResearchArea);

            final Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("parentResearchAreaCode", "03.");
            oneOf(businessObjectService).countMatching(ResearchArea.class, fieldValues);
            will(returnValue(0));
            
            final ResearchArea oldParentResearchArea = new ResearchArea("03.", "", "Parent", true);
            oldParentResearchArea.setHasChildrenFlag(true);
            oneOf(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, "03.");
            will(returnValue(oldParentResearchArea));
            
            oneOf(businessObjectService).save(oldParentResearchArea);
        }});
        researchAreasService.setBusinessObjectService(businessObjectService);

        researchAreasService.saveResearchAreas(raChangeXML);
    }

    @Test 
    public void testRaDelete() throws Exception {
        String raChangeXML =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<RaChanges>" +
                "<RaChangesElement>" +
                    "<RaDelete>" +
                        "<Code>07.88</Code>" +
                    "</RaDelete>" +
                "</RaChangesElement>" +
            "</RaChanges>";

        ResearchAreasServiceImpl researchAreasService = new ResearchAreasServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            final ResearchArea deleteResearchArea = new ResearchArea("07.88", "07.", "Sample research area", true);
            oneOf(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, "07.88");
            will(returnValue(deleteResearchArea));
            oneOf(businessObjectService).delete(deleteResearchArea);

            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("parentResearchAreaCode", "07.88");
            oneOf(businessObjectService).findMatching(ResearchArea.class, fieldValues);
            will(returnValue(new ArrayList()));
            
            final Map<String, String> fieldValues2 = new HashMap<String, String>();
            fieldValues2.put("parentResearchAreaCode", "07.");
            oneOf(businessObjectService).countMatching(ResearchArea.class, fieldValues2);
            will(returnValue(0));
            
            final ResearchArea parentResearchArea = new ResearchArea("07.", "", "Parent", true);
            parentResearchArea.setHasChildrenFlag(true);
            oneOf(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, "07.");
            will(returnValue(parentResearchArea));
            
            oneOf(businessObjectService).save(parentResearchArea);
        }});
        researchAreasService.setBusinessObjectService(businessObjectService);

        researchAreasService.saveResearchAreas(raChangeXML);
    }

    @Test 
    public void testMultipleRaChanges() throws Exception {
        String raChangeXML =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<RaChanges>" +
                "<RaChangesElement>" +
                    "<RaCreate>" +
                        "<Code>04.123</Code>" +
                        "<ParentCode>04.</ParentCode>" +
                        "<Description>Sample research area</Description>" +
                        "<Active>true</Active>" +
                    "</RaCreate>" +
                "</RaChangesElement>" +
                "<RaChangesElement>" +
                    "<RaUpdateDescription>" +
                        "<Code>05.12</Code>" +
                        "<Description>new description</Description>" +
                    "</RaUpdateDescription>" +
                    "<RaUpdateActiveIndicator>" +
                        "<Code>05.12</Code>" +
                        "<Active>false</Active>" +
                    "</RaUpdateActiveIndicator>" +
                "</RaChangesElement>" +
                "<RaChangesElement>" +
                    "<RaUpdateParent>" +
                        "<Code>03.24</Code>" +
                        "<OldParent>03.</OldParent>" +
                        "<NewParent>07.</NewParent>" +
                    "</RaUpdateParent>" +
                "</RaChangesElement>" +
                "<RaChangesElement>" +
                    "<RaDelete>" +
                        "<Code>07.88</Code>" +
                    "</RaDelete>" +
                "</RaChangesElement>" +
            "</RaChanges>";
        
        ResearchAreasServiceImpl researchAreasService = new ResearchAreasServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            // create
            final ResearchArea createResearchArea = new ResearchArea("04.123", "04.", "Sample research area", true);
            oneOf(businessObjectService).save(createResearchArea);

            final ResearchArea parentResearchArea2 = new ResearchArea("04.", "", "Parent", true);
            oneOf(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, "04.");
            will(returnValue(parentResearchArea2));
            
            oneOf(businessObjectService).save(parentResearchArea2);

            // description
            final ResearchArea updateDescriptionResearchArea = new ResearchArea("05.12", "05.", "Sample research area", true);
            oneOf(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, "05.12");
            will(returnValue(updateDescriptionResearchArea));
            
            updateDescriptionResearchArea.setDescription("new description");
            oneOf(businessObjectService).save(updateDescriptionResearchArea);

            // active
            final ResearchArea updateActiveResearchArea = new ResearchArea("05.12", "05.", "Sample research area", true);
            oneOf(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, "05.12");
            will(returnValue(updateActiveResearchArea));
            
            updateActiveResearchArea.setActive(false);
            oneOf(businessObjectService).save(updateActiveResearchArea);

            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("parentResearchAreaCode", "05.12");
            oneOf(businessObjectService).findMatching(ResearchArea.class, fieldValues);
            will(returnValue(new ArrayList()));

            // parent
            final ResearchArea updateParentResearchArea = new ResearchArea("03.24", "03.", "Sample research area", true);
            oneOf(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, "03.24");
            will(returnValue(updateParentResearchArea));
            
            updateParentResearchArea.setParentResearchAreaCode("07.");
            oneOf(businessObjectService).save(updateParentResearchArea);
            
            final ResearchArea newParentResearchArea = new ResearchArea("07.", "", "Parent", true);
            newParentResearchArea.setHasChildrenFlag(false);
            oneOf(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, "07.");
            will(returnValue(newParentResearchArea));
            
            oneOf(businessObjectService).save(newParentResearchArea);

            final Map<String, String> fieldValues2 = new HashMap<String, String>();
            fieldValues2.put("parentResearchAreaCode", "03.");
            oneOf(businessObjectService).countMatching(ResearchArea.class, fieldValues2);
            will(returnValue(0));
            
            final ResearchArea oldParentResearchArea = new ResearchArea("03.", "", "Parent", true);
            oldParentResearchArea.setHasChildrenFlag(true);
            oneOf(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, "03.");
            will(returnValue(oldParentResearchArea));
            
            oneOf(businessObjectService).save(oldParentResearchArea);

            // delete
            final ResearchArea deleteResearchArea = new ResearchArea("07.88", "07.", "Sample research area", true);
            oneOf(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, "07.88");
            will(returnValue(deleteResearchArea));
            oneOf(businessObjectService).delete(deleteResearchArea);

            Map<String, String> fieldValues4 = new HashMap<String, String>();
            fieldValues4.put("parentResearchAreaCode", "07.88");
            oneOf(businessObjectService).findMatching(ResearchArea.class, fieldValues4);
            will(returnValue(new ArrayList()));
            
            final Map<String, String> fieldValues3 = new HashMap<String, String>();
            fieldValues3.put("parentResearchAreaCode", "07.");
            oneOf(businessObjectService).countMatching(ResearchArea.class, fieldValues3);
            will(returnValue(0));
            
            final ResearchArea parentResearchArea = new ResearchArea("07.", "", "Parent", true);
            parentResearchArea.setHasChildrenFlag(true);
            oneOf(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, "07.");
            will(returnValue(parentResearchArea));
            
            oneOf(businessObjectService).save(parentResearchArea);

        }});
        researchAreasService.setBusinessObjectService(businessObjectService);

        researchAreasService.saveResearchAreas(raChangeXML);
    }
}
