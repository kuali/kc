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

import org.hamcrest.Matchers;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.dao.ResearchAreaReferencesDao;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.service.ResearchAreaCurrentReferencerHolder;
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
                        "<Active>true</Active>" +
                    "</RaUpdateActiveIndicator>" +
                "</RaChangesElement>" +
            "</RaChanges>";
        
        ResearchAreasServiceImpl researchAreasService = new ResearchAreasServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            final ResearchArea updateActiveResearchArea = new ResearchArea("05.12", "05.", "Sample research area", false);
            oneOf(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, "05.12");
            will(returnValue(updateActiveResearchArea));
            
            updateActiveResearchArea.setActive(true);
            oneOf(businessObjectService).save(updateActiveResearchArea);
            /*
             * TODO remove this
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("parentResearchAreaCode", "05.12");
            oneOf(businessObjectService).findMatching(ResearchArea.class, fieldValues);
            will(returnValue(new ArrayList()));
            */
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
    
    
    /**
     * 
     * This test method will mock the following tree hierarchy of research areas
     *                           0
     *                        /  |  \
     *                      0.0  0.1  0.2
     *                     /    /   \        
     *                 0.0.0  0.1.0  0.1.1
     *                         
     * @throws Exception
     */
    @Test 
    public void testCheckResearchAreaAndDescendantsNotReferenced() throws Exception {
        //define the nodes of the tree
        final ResearchArea researchArea_0 = new ResearchArea("0", "", "", true);
        final ArrayList<ResearchArea> childrenOf_0 = new ArrayList<ResearchArea>();
        
        final ResearchArea researchArea_0_0 = new ResearchArea("0.0", "", "", true);
        final ArrayList<ResearchArea>  childrenOf_0_0 = new ArrayList<ResearchArea>();
        
        final ResearchArea researchArea_0_1 = new ResearchArea("0.1", "", "", true);
        final ArrayList<ResearchArea> childrenOf_0_1 = new ArrayList<ResearchArea>();
        
        final ResearchArea researchArea_0_2 = new ResearchArea("0.2", "", "", true);
        final ArrayList<ResearchArea> childrenOf_0_2 = new ArrayList<ResearchArea>();
        
        final ResearchArea researchArea_0_0_0 = new ResearchArea("0.0.0", "", "", true);
        final ArrayList<ResearchArea> childrenOf_0_0_0 = new ArrayList<ResearchArea>();
        
        final ResearchArea researchArea_0_1_0 = new ResearchArea("0.1.0", "", "", true);
        final ArrayList<ResearchArea> childrenOf_0_1_0 = new ArrayList<ResearchArea>();
        
        final ResearchArea researchArea_0_1_1 = new ResearchArea("0.1.1", "", "", true);
        final ArrayList<ResearchArea> childrenOf_0_1_1 = new ArrayList<ResearchArea>();
        
        
        // set up the relationships between the nodes
        childrenOf_0.add(researchArea_0_0);
        childrenOf_0.add(researchArea_0_1);
        childrenOf_0.add(researchArea_0_2);           
        
        childrenOf_0_0.add(researchArea_0_0_0);
        
        childrenOf_0_1.add(researchArea_0_1_0);
        childrenOf_0_1.add(researchArea_0_1_1);
               
        
        
        ResearchAreasServiceImpl researchAreasService = new ResearchAreasServiceImpl();
        // finally mock the BO service to simulate the tree hierarchy
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            
            Map<String, String> fieldValues_0 = new HashMap<String, String>();
            fieldValues_0.put("parentResearchAreaCode", researchArea_0.getResearchAreaCode());
            allowing(businessObjectService).findMatching(ResearchArea.class, fieldValues_0);
            will(returnValue(childrenOf_0));
            allowing(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, researchArea_0.getResearchAreaCode());
            will(returnValue(researchArea_0));
            
            Map<String, String> fieldValues_0_0 = new HashMap<String, String>();
            fieldValues_0_0.put("parentResearchAreaCode", researchArea_0_0.getResearchAreaCode());
            allowing(businessObjectService).findMatching(ResearchArea.class, fieldValues_0_0);
            will(returnValue(childrenOf_0_0));
            allowing(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, researchArea_0_0.getResearchAreaCode());
            will(returnValue(researchArea_0_0));
            
            Map<String, String> fieldValues_0_0_0 = new HashMap<String, String>();
            fieldValues_0_0_0.put("parentResearchAreaCode", researchArea_0_0_0.getResearchAreaCode());
            allowing(businessObjectService).findMatching(ResearchArea.class, fieldValues_0_0_0);
            will(returnValue(childrenOf_0_0_0));
            allowing(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, researchArea_0_0_0.getResearchAreaCode());
            will(returnValue(researchArea_0_0_0));
            
            Map<String, String> fieldValues_0_1 = new HashMap<String, String>();
            fieldValues_0_1.put("parentResearchAreaCode", researchArea_0_1.getResearchAreaCode());
            allowing(businessObjectService).findMatching(ResearchArea.class, fieldValues_0_1);
            will(returnValue(childrenOf_0_1));
            allowing(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, researchArea_0_1.getResearchAreaCode());
            will(returnValue(researchArea_0_1));
            
            Map<String, String> fieldValues_0_1_0 = new HashMap<String, String>();
            fieldValues_0_1_0.put("parentResearchAreaCode", researchArea_0_1_0.getResearchAreaCode());
            allowing(businessObjectService).findMatching(ResearchArea.class, fieldValues_0_1_0);
            will(returnValue(childrenOf_0_1_0));
            allowing(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, researchArea_0_1_0.getResearchAreaCode());
            will(returnValue(researchArea_0_1_0));
            
            Map<String, String> fieldValues_0_1_1 = new HashMap<String, String>();
            fieldValues_0_1_1.put("parentResearchAreaCode", researchArea_0_1_1.getResearchAreaCode());
            allowing(businessObjectService).findMatching(ResearchArea.class, fieldValues_0_1_1);
            will(returnValue(childrenOf_0_1_1));
            allowing(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, researchArea_0_1_1.getResearchAreaCode());
            will(returnValue(researchArea_0_1_1));
            
            Map<String, String> fieldValues_0_2 = new HashMap<String, String>();
            fieldValues_0_2.put("parentResearchAreaCode", researchArea_0_2.getResearchAreaCode());
            allowing(businessObjectService).findMatching(ResearchArea.class, fieldValues_0_2);
            will(returnValue(childrenOf_0_2));
            allowing(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, researchArea_0_2.getResearchAreaCode());
            will(returnValue(researchArea_0_2));
            
        }});
        researchAreasService.setBusinessObjectService(businessObjectService);        
        // At this point the research area tree hierarchy is ready, now define various test cases by setting various DAO mock instances        
        
        //basic test
        final ResearchAreaReferencesDao no_references_Dao = context.mock(ResearchAreaReferencesDao.class, "name1");
        context.checking(new Expectations() {{
            allowing(no_references_Dao).isResearchAreaReferencedByAnyCommittee(with(any(String.class)));
            allowing(no_references_Dao).isResearchAreaReferencedByAnyCommitteeMember(with(any(String.class)));
            allowing(no_references_Dao).isResearchAreaReferencedByAnyProtocol(with(any(String.class)));
        }});
        researchAreasService.setResearchAreaReferencesDao(no_references_Dao);
        
        final ResearchAreaReferencesDao only_0_1_1_references_Dao = context.mock(ResearchAreaReferencesDao.class, "name2");
        context.checking(new Expectations() {{
            allowing(only_0_1_1_references_Dao).isResearchAreaReferencedByAnyCommittee(with(any(String.class)));
            will(returnValue(false));
            allowing(only_0_1_1_references_Dao).isResearchAreaReferencedByAnyCommitteeMember(with(any(String.class)));
            will(returnValue(false));
            allowing(only_0_1_1_references_Dao).isResearchAreaReferencedByAnyProtocol(with(Matchers.not("0.1.1")));
            will(returnValue(false));
            allowing(only_0_1_1_references_Dao).isResearchAreaReferencedByAnyProtocol("0.1.1");
            will(returnValue(true));
            
        }});
        researchAreasService.setResearchAreaReferencesDao(only_0_1_1_references_Dao);
        Assert.assertFalse(researchAreasService.checkResearchAreaAndDescendantsNotReferenced(researchArea_0.getResearchAreaCode()));
        Assert.assertFalse(researchAreasService.checkResearchAreaAndDescendantsNotReferenced(researchArea_0_1.getResearchAreaCode()));
        Assert.assertFalse(researchAreasService.checkResearchAreaAndDescendantsNotReferenced(researchArea_0_1_1.getResearchAreaCode()));
        
        Assert.assertTrue(researchAreasService.checkResearchAreaAndDescendantsNotReferenced(researchArea_0_0.getResearchAreaCode()));
        Assert.assertTrue(researchAreasService.checkResearchAreaAndDescendantsNotReferenced(researchArea_0_2.getResearchAreaCode()));
        Assert.assertTrue(researchAreasService.checkResearchAreaAndDescendantsNotReferenced(researchArea_0_0_0.getResearchAreaCode()));
        Assert.assertTrue(researchAreasService.checkResearchAreaAndDescendantsNotReferenced(researchArea_0_1_0.getResearchAreaCode()));
        
        // Also test that the depth-first recursion optimization works correctly; once an area is found to be referenced, no 
        // other areas are checked, instead the recursion simply unwinds from that point. This is checked by the 'never' 
        // in the invocation count of the expectations below
        final ResearchAreaReferencesDao only_0_0_0_references_Dao = context.mock(ResearchAreaReferencesDao.class, "name3");
        context.checking(new Expectations() {{
            
            oneOf(only_0_0_0_references_Dao).isResearchAreaReferencedByAnyCommittee("0");
            will(returnValue(false));
            oneOf(only_0_0_0_references_Dao).isResearchAreaReferencedByAnyCommitteeMember("0");
            will(returnValue(false));
            oneOf(only_0_0_0_references_Dao).isResearchAreaReferencedByAnyProtocol("0");
            will(returnValue(false));
            
            oneOf(only_0_0_0_references_Dao).isResearchAreaReferencedByAnyCommittee("0.0");
            will(returnValue(false));
            oneOf(only_0_0_0_references_Dao).isResearchAreaReferencedByAnyCommitteeMember("0.0");
            will(returnValue(false));
            oneOf(only_0_0_0_references_Dao).isResearchAreaReferencedByAnyProtocol("0.0");
            will(returnValue(false));
            
            oneOf(only_0_0_0_references_Dao).isResearchAreaReferencedByAnyCommittee("0.0.0");
            will(returnValue(false));
            oneOf(only_0_0_0_references_Dao).isResearchAreaReferencedByAnyCommitteeMember("0.0.0");
            will(returnValue(true));
            
            never(only_0_0_0_references_Dao).isResearchAreaReferencedByAnyCommittee("0.1");            
            never(only_0_0_0_references_Dao).isResearchAreaReferencedByAnyCommitteeMember("0.1");            
            never(only_0_0_0_references_Dao).isResearchAreaReferencedByAnyProtocol("0.1");
            
            never(only_0_0_0_references_Dao).isResearchAreaReferencedByAnyCommittee("0.1.0");            
            never(only_0_0_0_references_Dao).isResearchAreaReferencedByAnyCommitteeMember("0.1.0");            
            never(only_0_0_0_references_Dao).isResearchAreaReferencedByAnyProtocol("0.1.0");
            
            never(only_0_0_0_references_Dao).isResearchAreaReferencedByAnyCommittee("0.1.1");            
            never(only_0_0_0_references_Dao).isResearchAreaReferencedByAnyCommitteeMember("0.1.1");            
            never(only_0_0_0_references_Dao).isResearchAreaReferencedByAnyProtocol("0.1.1");
            
            never(only_0_0_0_references_Dao).isResearchAreaReferencedByAnyCommittee("0.2");            
            never(only_0_0_0_references_Dao).isResearchAreaReferencedByAnyCommitteeMember("0.2");            
            never(only_0_0_0_references_Dao).isResearchAreaReferencedByAnyProtocol("0.2");
            
        }});
        researchAreasService.setResearchAreaReferencesDao(only_0_0_0_references_Dao);
        Assert.assertFalse(researchAreasService.checkResearchAreaAndDescendantsNotReferenced(researchArea_0.getResearchAreaCode()));
    }

    
    
    /**
     * 
     * This test method will mock the following tree hierarchy of research areas
     *                           0
     *                        /  |  \
     *                      0.0  0.1  0.2
     *                     /    /   \        
     *                 0.0.0  0.1.0  0.1.1
     *                         
     * @throws Exception
     */
    @Test
    public void testGetAnyCurrentReferencerForResearchAreaOrDescendant() throws Exception{
        //define the nodes of the tree
        final ResearchArea researchArea_0 = new ResearchArea("0", "", "", true);
        final ArrayList<ResearchArea> childrenOf_0 = new ArrayList<ResearchArea>();
        
        final ResearchArea researchArea_0_0 = new ResearchArea("0.0", "", "", true);
        final ArrayList<ResearchArea>  childrenOf_0_0 = new ArrayList<ResearchArea>();
        
        final ResearchArea researchArea_0_1 = new ResearchArea("0.1", "", "", true);
        final ArrayList<ResearchArea> childrenOf_0_1 = new ArrayList<ResearchArea>();
        
        final ResearchArea researchArea_0_2 = new ResearchArea("0.2", "", "", true);
        final ArrayList<ResearchArea> childrenOf_0_2 = new ArrayList<ResearchArea>();
        
        final ResearchArea researchArea_0_0_0 = new ResearchArea("0.0.0", "", "", true);
        final ArrayList<ResearchArea> childrenOf_0_0_0 = new ArrayList<ResearchArea>();
        
        final ResearchArea researchArea_0_1_0 = new ResearchArea("0.1.0", "", "", true);
        final ArrayList<ResearchArea> childrenOf_0_1_0 = new ArrayList<ResearchArea>();
        
        final ResearchArea researchArea_0_1_1 = new ResearchArea("0.1.1", "", "", true);
        final ArrayList<ResearchArea> childrenOf_0_1_1 = new ArrayList<ResearchArea>();
        
        
        // set up the relationships between the nodes
        childrenOf_0.add(researchArea_0_0);
        childrenOf_0.add(researchArea_0_1);
        childrenOf_0.add(researchArea_0_2);           
        
        childrenOf_0_0.add(researchArea_0_0_0);
        
        childrenOf_0_1.add(researchArea_0_1_0);
        childrenOf_0_1.add(researchArea_0_1_1);
        
        // finally mock the BO service to simulate the tree hierarchy
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            
            Map<String, Object> fieldValues_0 = new HashMap<String, Object>();
            fieldValues_0.put("parentResearchAreaCode", researchArea_0.getResearchAreaCode());
            fieldValues_0.put("active", true);
            allowing(businessObjectService).findMatching(ResearchArea.class, fieldValues_0);
            will(returnValue(childrenOf_0));
           
            
            Map<String, Object> fieldValues_0_0 = new HashMap<String, Object>();
            fieldValues_0_0.put("parentResearchAreaCode", researchArea_0_0.getResearchAreaCode());
            fieldValues_0_0.put("active", true);
            allowing(businessObjectService).findMatching(ResearchArea.class, fieldValues_0_0);
            will(returnValue(childrenOf_0_0));
            
            Map<String, Object> fieldValues_0_0_0 = new HashMap<String, Object>();
            fieldValues_0_0_0.put("parentResearchAreaCode", researchArea_0_0_0.getResearchAreaCode());
            fieldValues_0_0_0.put("active", true);
            allowing(businessObjectService).findMatching(ResearchArea.class, fieldValues_0_0_0);
            will(returnValue(childrenOf_0_0_0));
            
            Map<String, Object> fieldValues_0_1 = new HashMap<String, Object>();
            fieldValues_0_1.put("parentResearchAreaCode", researchArea_0_1.getResearchAreaCode());
            fieldValues_0_1.put("active", true);
            allowing(businessObjectService).findMatching(ResearchArea.class, fieldValues_0_1);
            will(returnValue(childrenOf_0_1));
            
            Map<String, Object> fieldValues_0_1_0 = new HashMap<String, Object>();
            fieldValues_0_1_0.put("parentResearchAreaCode", researchArea_0_1_0.getResearchAreaCode());
            fieldValues_0_1_0.put("active", true);
            allowing(businessObjectService).findMatching(ResearchArea.class, fieldValues_0_1_0);
            will(returnValue(childrenOf_0_1_0));
            
            Map<String, Object> fieldValues_0_1_1 = new HashMap<String, Object>();
            fieldValues_0_1_1.put("parentResearchAreaCode", researchArea_0_1_1.getResearchAreaCode());
            fieldValues_0_1_1.put("active", true);
            allowing(businessObjectService).findMatching(ResearchArea.class, fieldValues_0_1_1);
            will(returnValue(childrenOf_0_1_1));
             
            Map<String, Object> fieldValues_0_2 = new HashMap<String, Object>();
            fieldValues_0_2.put("parentResearchAreaCode", researchArea_0_2.getResearchAreaCode());
            fieldValues_0_2.put("active", true);
            allowing(businessObjectService).findMatching(ResearchArea.class, fieldValues_0_2);
            will(returnValue(childrenOf_0_2));
            
            
        }});
        // At this point the research area tree hierarchy is ready, now define various test cases 
        
        // case: no references
        ResearchAreasServiceImpl researchAreasService = new ResearchAreasServiceImpl() {
            
            public Protocol getCurrentProtocolReferencingResearchArea(String researchAreaCode) {
                return null;
            }
            
            public Committee getCurrentCommitteeReferencingResearchArea(String researchAreaCode) {
                return null;
            }

            public CommitteeMembership getCurrentCommitteeMembershipReferencingResearchArea(String researchAreaCode) {
                return null;
            }

        };
        researchAreasService.setBusinessObjectService(businessObjectService);
        Assert.assertTrue(researchAreasService.getAnyCurrentReferencerForResearchAreaOrDescendant(researchArea_0.getResearchAreaCode()) == ResearchAreaCurrentReferencerHolder.NO_REFERENCER);
        
        
        // case: an RA is referenced by a protocol
        final Protocol p1 = new Protocol() {

            private static final long serialVersionUID = -1273061983131550371L;
            
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                //do nothing
            }
        };
        p1.setProtocolNumber("abcdef");
        
        researchAreasService = new ResearchAreasServiceImpl() {           
            public Protocol getCurrentProtocolReferencingResearchArea(String researchAreaCode) {
                if(researchAreaCode.equals(researchArea_0_1_1.getResearchAreaCode())) {
                    return p1;
                }
                else {
                    return null;
                }
            }
            
            public Committee getCurrentCommitteeReferencingResearchArea(String researchAreaCode) {
                return null;
            }

            public CommitteeMembership getCurrentCommitteeMembershipReferencingResearchArea(String researchAreaCode) {
                return null;
            }
        };
        researchAreasService.setBusinessObjectService(businessObjectService);        
        ResearchAreaCurrentReferencerHolder referencer = researchAreasService.getAnyCurrentReferencerForResearchAreaOrDescendant(researchArea_0.getResearchAreaCode());
        Assert.assertTrue(referencer.getResearchAreaCode().equals(researchArea_0_1_1.getResearchAreaCode()));
        Assert.assertTrue(referencer.getCurrentReferencingProtocol() == p1);
        Assert.assertTrue(referencer.getCurrentReferencingCommittee() == null);
        Assert.assertTrue(referencer.getCurrentReferencingCommitteeMembership() == null);
        Assert.assertTrue(referencer.getMessage().equals( "Research area " + researchArea_0_1_1.getResearchAreaCode() + " is referenced by current version of protocol with number " + p1.getProtocolNumber()));
        
        Assert.assertTrue(researchAreasService.getAnyCurrentReferencerForResearchAreaOrDescendant(researchArea_0_0.getResearchAreaCode()) == ResearchAreaCurrentReferencerHolder.NO_REFERENCER);
        Assert.assertTrue(researchAreasService.getAnyCurrentReferencerForResearchAreaOrDescendant(researchArea_0_2.getResearchAreaCode()) == ResearchAreaCurrentReferencerHolder.NO_REFERENCER);
        
        
        // case: An RA is referenced by a committee (and another one down-the-line is referenced by a protocol). Check that the RAs following the one referenced by the committee (in depth-first order) are not checked, instead the recursion simply unwinds from that point. 
        // This is specified by the 'never' in the invocation count of the expectations below
        final Committee c1 = new Committee();
        c1.setCommitteeId("444");
        researchAreasService = new ResearchAreasServiceImpl() {           
            public Protocol getCurrentProtocolReferencingResearchArea(String researchAreaCode) {
                if(researchAreaCode.equals(researchArea_0_1_1.getResearchAreaCode())) {
                    return p1;
                }
                else {
                    return null;
                }
            }
            
            public Committee getCurrentCommitteeReferencingResearchArea(String researchAreaCode) {
                if(researchAreaCode.equals(researchArea_0_0_0.getResearchAreaCode())) {
                    return c1;
                }
                else {
                    return null;
                }
            }

            public CommitteeMembership getCurrentCommitteeMembershipReferencingResearchArea(String researchAreaCode) {
                return null;
            }
        };
     
        final BusinessObjectService businessObjectServiceNew = context.mock(BusinessObjectService.class, "new");
        context.checking(new Expectations() {{
            
            Map<String, Object> fieldValues_0 = new HashMap<String, Object>();
            fieldValues_0.put("parentResearchAreaCode", researchArea_0.getResearchAreaCode());
            fieldValues_0.put("active", true);
            oneOf(businessObjectServiceNew).findMatching(ResearchArea.class, fieldValues_0);
            will(returnValue(childrenOf_0));
           
            
            Map<String, Object> fieldValues_0_0 = new HashMap<String, Object>();
            fieldValues_0_0.put("parentResearchAreaCode", researchArea_0_0.getResearchAreaCode());
            fieldValues_0_0.put("active", true);
            oneOf(businessObjectServiceNew).findMatching(ResearchArea.class, fieldValues_0_0);
            will(returnValue(childrenOf_0_0));
            
            Map<String, Object> fieldValues_0_0_0 = new HashMap<String, Object>();
            fieldValues_0_0_0.put("parentResearchAreaCode", researchArea_0_0_0.getResearchAreaCode());
            fieldValues_0_0_0.put("active", true);
            never(businessObjectServiceNew).findMatching(ResearchArea.class, fieldValues_0_0_0);
            
            Map<String, Object> fieldValues_0_1 = new HashMap<String, Object>();
            fieldValues_0_1.put("parentResearchAreaCode", researchArea_0_1.getResearchAreaCode());
            fieldValues_0_1.put("active", true);
            never(businessObjectServiceNew).findMatching(ResearchArea.class, fieldValues_0_1);
            
            
            Map<String, Object> fieldValues_0_1_0 = new HashMap<String, Object>();
            fieldValues_0_1_0.put("parentResearchAreaCode", researchArea_0_1_0.getResearchAreaCode());
            fieldValues_0_1_0.put("active", true);
            never(businessObjectServiceNew).findMatching(ResearchArea.class, fieldValues_0_1_0);
            
            Map<String, Object> fieldValues_0_1_1 = new HashMap<String, Object>();
            fieldValues_0_1_1.put("parentResearchAreaCode", researchArea_0_1_1.getResearchAreaCode());
            fieldValues_0_1_1.put("active", true);
            never(businessObjectServiceNew).findMatching(ResearchArea.class, fieldValues_0_1_1);
             
            Map<String, Object> fieldValues_0_2 = new HashMap<String, Object>();
            fieldValues_0_2.put("parentResearchAreaCode", researchArea_0_2.getResearchAreaCode());
            fieldValues_0_2.put("active", true);
            never(businessObjectServiceNew).findMatching(ResearchArea.class, fieldValues_0_2);
                       
        }});
        
        researchAreasService.setBusinessObjectService(businessObjectServiceNew);        
        referencer = researchAreasService.getAnyCurrentReferencerForResearchAreaOrDescendant(researchArea_0.getResearchAreaCode());
        Assert.assertTrue(referencer.getResearchAreaCode().equals(researchArea_0_0_0.getResearchAreaCode()));
        Assert.assertTrue(referencer.getCurrentReferencingProtocol() == null);
        Assert.assertTrue(referencer.getCurrentReferencingCommittee() == c1);
        Assert.assertTrue(referencer.getCurrentReferencingCommitteeMembership() == null);
        Assert.assertTrue(referencer.getMessage().equals("Research area " + researchArea_0_0_0.getResearchAreaCode() + " is referenced by current version of committee with ID " + c1.getCommitteeId()));
        
        //TODO case: check committee membership and parent committee
    }
   
    
    
    /**
     * 
     * This test method will mock the following tree hierarchy of research areas
     *                           0
     *                        /  |  \
     *                      0.0  0.1  0.2
     *                     /    /   \        
     *                 0.0.0  0.1.0  0.1.1
     *                         
     * @throws Exception
     */
    @Test 
    public void testDeactivateResearchAreaAndDescendants() throws Exception {
      //define the nodes of the tree
        final ResearchArea researchArea_0 = new ResearchArea("0", "", "", true);
        final ArrayList<ResearchArea> childrenOf_0 = new ArrayList<ResearchArea>();
        
        final ResearchArea researchArea_0_0 = new ResearchArea("0.0", "", "", true);
        final ArrayList<ResearchArea>  childrenOf_0_0 = new ArrayList<ResearchArea>();
        
        final ResearchArea researchArea_0_1 = new ResearchArea("0.1", "", "", true);
        final ArrayList<ResearchArea> childrenOf_0_1 = new ArrayList<ResearchArea>();
        
        final ResearchArea researchArea_0_2 = new ResearchArea("0.2", "", "", true);
        final ArrayList<ResearchArea> childrenOf_0_2 = new ArrayList<ResearchArea>();
        
        final ResearchArea researchArea_0_0_0 = new ResearchArea("0.0.0", "", "", true);
        final ArrayList<ResearchArea> childrenOf_0_0_0 = new ArrayList<ResearchArea>();
        
        final ResearchArea researchArea_0_1_0 = new ResearchArea("0.1.0", "", "", true);
        final ArrayList<ResearchArea> childrenOf_0_1_0 = new ArrayList<ResearchArea>();
        
        final ResearchArea researchArea_0_1_1 = new ResearchArea("0.1.1", "", "", true);
        final ArrayList<ResearchArea> childrenOf_0_1_1 = new ArrayList<ResearchArea>();
        
        
        // set up the relationships between the nodes
        childrenOf_0.add(researchArea_0_0);
        childrenOf_0.add(researchArea_0_1);
        childrenOf_0.add(researchArea_0_2);           
        
        childrenOf_0_0.add(researchArea_0_0_0);
        
        childrenOf_0_1.add(researchArea_0_1_0);
        childrenOf_0_1.add(researchArea_0_1_1);
               
        
        
        ResearchAreasServiceImpl researchAreasService = new ResearchAreasServiceImpl();
        // finally mock the BO service to simulate the tree hierarchy and also to specify the invocations of various BOservice methods
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            
            Map<String, String> fieldValues_0 = new HashMap<String, String>();
            fieldValues_0.put("parentResearchAreaCode", researchArea_0.getResearchAreaCode());
            oneOf(businessObjectService).findMatching(ResearchArea.class, fieldValues_0);
            will(returnValue(childrenOf_0));
            oneOf(businessObjectService).save(researchArea_0);
            oneOf(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, researchArea_0.getResearchAreaCode());
            will(returnValue(researchArea_0));
            
            Map<String, String> fieldValues_0_0 = new HashMap<String, String>();
            fieldValues_0_0.put("parentResearchAreaCode", researchArea_0_0.getResearchAreaCode());
            oneOf(businessObjectService).findMatching(ResearchArea.class, fieldValues_0_0);
            will(returnValue(childrenOf_0_0));
            oneOf(businessObjectService).save(researchArea_0_0);
            
            Map<String, String> fieldValues_0_0_0 = new HashMap<String, String>();
            fieldValues_0_0_0.put("parentResearchAreaCode", researchArea_0_0_0.getResearchAreaCode());
            oneOf(businessObjectService).findMatching(ResearchArea.class, fieldValues_0_0_0);
            will(returnValue(childrenOf_0_0_0));
            oneOf(businessObjectService).save(researchArea_0_0_0);            
            
            Map<String, String> fieldValues_0_1 = new HashMap<String, String>();
            fieldValues_0_1.put("parentResearchAreaCode", researchArea_0_1.getResearchAreaCode());
            oneOf(businessObjectService).findMatching(ResearchArea.class, fieldValues_0_1);
            will(returnValue(childrenOf_0_1));
            oneOf(businessObjectService).save(researchArea_0_1);            
            
            Map<String, String> fieldValues_0_1_0 = new HashMap<String, String>();
            fieldValues_0_1_0.put("parentResearchAreaCode", researchArea_0_1_0.getResearchAreaCode());
            oneOf(businessObjectService).findMatching(ResearchArea.class, fieldValues_0_1_0);
            will(returnValue(childrenOf_0_1_0));
            oneOf(businessObjectService).save(researchArea_0_1_0);            
            
            Map<String, String> fieldValues_0_1_1 = new HashMap<String, String>();
            fieldValues_0_1_1.put("parentResearchAreaCode", researchArea_0_1_1.getResearchAreaCode());
            oneOf(businessObjectService).findMatching(ResearchArea.class, fieldValues_0_1_1);
            will(returnValue(childrenOf_0_1_1));
            oneOf(businessObjectService).save(researchArea_0_1_1);
            
            Map<String, String> fieldValues_0_2 = new HashMap<String, String>();
            fieldValues_0_2.put("parentResearchAreaCode", researchArea_0_2.getResearchAreaCode());
            oneOf(businessObjectService).findMatching(ResearchArea.class, fieldValues_0_2);
            will(returnValue(childrenOf_0_2));
            oneOf(businessObjectService).save(researchArea_0_2);
                        
        }});
        researchAreasService.setBusinessObjectService(businessObjectService);        
        
        
        Assert.assertTrue(researchArea_0.isActive());
        Assert.assertTrue(researchArea_0_0.isActive());
        Assert.assertTrue(researchArea_0_0_0.isActive());
        Assert.assertTrue(researchArea_0_1.isActive());
        Assert.assertTrue(researchArea_0_1_0.isActive());
        Assert.assertTrue(researchArea_0_1_1.isActive());
        Assert.assertTrue(researchArea_0_2.isActive());
        
        researchAreasService.deactivateResearchAreaAndDescendants(researchArea_0.getResearchAreaCode());
        
        Assert.assertFalse(researchArea_0.isActive());
        Assert.assertFalse(researchArea_0_0.isActive());
        Assert.assertFalse(researchArea_0_0_0.isActive());
        Assert.assertFalse(researchArea_0_1.isActive());
        Assert.assertFalse(researchArea_0_1_0.isActive());
        Assert.assertFalse(researchArea_0_1_1.isActive());
        Assert.assertFalse(researchArea_0_2.isActive());
                
    }
    
    
    
    
    @Test 
    public void testDeleteResearchAreaAndDescendants() throws Exception {
        ResearchAreasServiceImpl researchAreasService = new ResearchAreasServiceImpl();
        final ResearchArea deleteResearchArea = new ResearchArea("07.88", "07.", "Sample research area", true);
        final ResearchArea parentResearchArea = new ResearchArea("07.", "", "Parent", true);
        parentResearchArea.setHasChildrenFlag(true);
        
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            oneOf(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, "07.88");
            will(returnValue(deleteResearchArea));
            oneOf(businessObjectService).delete(deleteResearchArea);

            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("parentResearchAreaCode", "07.88");
            oneOf(businessObjectService).findMatching(ResearchArea.class, fieldValues);
            will(returnValue(new ArrayList()));
            
            Map<String, String> fieldValues2 = new HashMap<String, String>();
            fieldValues2.put("parentResearchAreaCode", "07.");
            oneOf(businessObjectService).countMatching(ResearchArea.class, fieldValues2);
            will(returnValue(0));           
            
            oneOf(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, "07.");
            will(returnValue(parentResearchArea));
            
            oneOf(businessObjectService).save(parentResearchArea);
        }});
        researchAreasService.setBusinessObjectService(businessObjectService);

        researchAreasService.deleteResearchAreaAndDescendants("07.88");
        Assert.assertFalse(parentResearchArea.getHasChildrenFlag());
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
                        "<Active>true</Active>" +
                    "</RaUpdateActiveIndicator>" +
                "</RaChangesElement>" +
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
            // create
            final ResearchArea createResearchArea = new ResearchArea("04.123", "04.", "Sample research area", true);
            oneOf(businessObjectService).save(createResearchArea);

            final ResearchArea parentResearchArea2 = new ResearchArea("04.", "", "Parent", true);
            oneOf(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, "04.");
            will(returnValue(parentResearchArea2));
            
            oneOf(businessObjectService).save(parentResearchArea2);

            // description
            final ResearchArea updateResearchArea = new ResearchArea("05.12", "05.", "Sample research area", false);
            oneOf(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, "05.12");
            will(returnValue(updateResearchArea));
            
            updateResearchArea.setDescription("new description");
            oneOf(businessObjectService).save(updateResearchArea);

            // active
            oneOf(businessObjectService).findBySinglePrimaryKey(ResearchArea.class, "05.12");
            will(returnValue(updateResearchArea));
            
            updateResearchArea.setActive(true);
            oneOf(businessObjectService).save(updateResearchArea);
            
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

        }});
        researchAreasService.setBusinessObjectService(businessObjectService);

        researchAreasService.saveResearchAreas(raChangeXML);
    }
}
