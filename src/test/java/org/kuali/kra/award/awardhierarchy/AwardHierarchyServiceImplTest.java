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
package org.kuali.kra.award.awardhierarchy;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.kuali.kra.award.AwardNumberService;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardServiceImpl;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.service.VersioningService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public class AwardHierarchyServiceImplTest extends KcUnitTestBase {
    
    private static final int NUMBER_OF_CHILDREN_A = 20;
    private static final int NUMBER_OF_GRANDCHILDREN_A = 10;
    private static final int NUMBER_OF_CHILDREN_B = 10;
    private static final int NUMBER_OF_GRANDCHILDREN_B = 5;
    private static final long BASE_AWARD_NUMBER = 100001;
    private static final int BASE_HIERARCHY_SEQUENCE = 1;
    
    private static final String DUMMY_AWARD_NUMBER = "999999-99999";
    private static final String AWARD_NUMBER_PATTERN = "%06d-%05d";
    private static final String DEFAULT_ROOT_AWARD_NUMBER = "100001-00001";
    private static final String NEXT_AWARD_NUMBER_FOR_NEW_HIERARCHY = "100002-00001";
    
    private AwardHierarchyServiceImpl service;

    private List<Award> awardList = new ArrayList<Award>();
    private AwardDocument placeholderDocument;

    private AwardHierarchy rootNodeA;
    private AwardHierarchy rootNodeB;
    
    private Mockery context = new JUnit4Mockery();

    @Before  
    public void setUp() throws Exception {
        super.setUp();
        service = new AwardHierarchyServiceImpl();
        service.setAwardNumberService(getMockAwardNumberService());
        service.setBusinessObjectService(getBusinessObjectService());
        service.setDocumentService(getDocumentService());
        service.setVersioningService(KraServiceLocator.getService(VersioningService.class));
        service.setVersionHistoryService(KraServiceLocator.getService(VersionHistoryService.class));
        service.setAwardService(new AwardServiceMock());
         
        awardList = new ArrayList<Award>();
        rootNodeA = createFullAwardHierarchy(100001L, NUMBER_OF_CHILDREN_A, NUMBER_OF_GRANDCHILDREN_A);
        addAwards(rootNodeA);
        rootNodeB = createFullAwardHierarchy(200001L, NUMBER_OF_CHILDREN_B, NUMBER_OF_GRANDCHILDREN_B);
        addAwards(rootNodeB);
        service.persistAwardHierarchy(rootNodeA, AwardHierarchyService.RECURS_HIERARCHY);
        service.persistAwardHierarchy(rootNodeB, AwardHierarchyService.RECURS_HIERARCHY);
    }
    
    @After
    public void tearDown() throws Exception {
        service = null;  
        super.tearDown();
    }

    @Test
    public void testFindingNode_NotPersisted() {
        AwardHierarchy node = service.loadAwardHierarchy("999999-99999");
        Assert.assertNull(node);
    }

    @Test
    public void testCopyingAwardAsNewHierarchy() {
        AwardHierarchy newRootNode = service.copyAwardAsNewHierarchy(rootNodeA.getChildren().get(1));
        Assert.assertFalse(newRootNode.hasChildren());
        newRootNode.setVersionHistoryService(getVersionHistoryService());
        Award newAward = newRootNode.getAward();
        String awardNumber = newAward.getAwardNumber();
        Assert.assertEquals("00001", awardNumber.substring(awardNumber.indexOf("-") + 1));
        Assert.assertEquals(1, newAward.getSequenceNumber().intValue());
    }

    @Test
    public void testCopyingAwardAsChildOfAnAwardInAnotherHierarchy() {
        AwardHierarchy sourceNode = rootNodeA.getChildren().get(1);
        AwardHierarchy targetParentNode = rootNodeB.getChildren().get(2);
        int originalNumberOfChildrenOnTargetNode = targetParentNode.getChildren().size();
        AwardHierarchy newNode = service.copyAwardAsChildOfAnAwardInAnotherHierarchy(sourceNode, targetParentNode);
        Assert.assertEquals(targetParentNode, newNode.getParent());
        Assert.assertEquals(originalNumberOfChildrenOnTargetNode + 1, targetParentNode.childCount());
    }

    @Test
    public void testCopyingAwardAndDescendantsAsChildOfAnAwardInAnotherHierarchy() {
        AwardHierarchy sourceNode = rootNodeA.getChildren().get(1);
        AwardHierarchy targetParentNode = rootNodeB.getChildren().get(2);
        int originalNumberOfChildrenOnTargetNode = targetParentNode.getChildren().size();
        AwardHierarchy newNode = service.copyAwardAndDescendantsAsChildOfAnAwardInAnotherHierarchy(sourceNode, targetParentNode);
        Assert.assertEquals(targetParentNode, newNode.getParent());
        Assert.assertTrue(newNode.hasChildren());
        Assert.assertEquals(sourceNode.childCount(), newNode.childCount());
        Assert.assertEquals(originalNumberOfChildrenOnTargetNode + 1, newNode.getParent().childCount());
        Assert.assertEquals(newNode.getAward().getTitle(), sourceNode.getAward().getTitle());
    }

    @Test
    public void testCopyingAwardAndDescendantsAsChildOfAnAwardInCurrentHierarchy() {
        AwardHierarchy sourceNode = rootNodeA.getChildren().get(1);
        AwardHierarchy targetParentNode = rootNodeA.getChildren().get(2).getChildren().get(1);
        int originalNumberOfChildrenOnTargetNode = targetParentNode.childCount();
        AwardHierarchy newBranchNode = service.copyAwardAndDescendantsAsChildOfAnAwardInCurrentHierarchy(sourceNode, targetParentNode);
        Assert.assertEquals(targetParentNode, newBranchNode.getParent());
        Assert.assertTrue(newBranchNode.hasChildren());
        Assert.assertEquals(originalNumberOfChildrenOnTargetNode + 1, newBranchNode.getParent().childCount());
        Assert.assertEquals(sourceNode.childCount(), newBranchNode.childCount());
        Assert.assertEquals(newBranchNode.getAward().getTitle(), sourceNode.getAward().getTitle());
        for(int i = 0; i < sourceNode.childCount(); i++) {
            Assert.assertEquals(sourceNode.getChildren().get(i).getAward().getTitle(), newBranchNode.getChildren().get(i).getAward().getTitle());
        }
    }

    @Test
    public void testCopyingAwardAndDescendantsAsNewHierarchy() {
        AwardHierarchy targetNode = rootNodeA.getChildren().get(1);
        AwardHierarchy newNode = service.copyAwardAndAllDescendantsAsNewHierarchy(targetNode);
        Assert.assertTrue(newNode.isRootNode());
        Assert.assertTrue(newNode.hasChildren());
        Assert.assertEquals(targetNode.getChildren().size(), newNode.getChildren().size());
        Assert.assertEquals(targetNode.getAward().getTitle(), newNode.getAward().getTitle());
        for(int i = 0; i < targetNode.getChildren().size(); i++) {
            Assert.assertEquals(targetNode.getChildren().get(i).getAward().getTitle(), newNode.getChildren().get(i).getAward().getTitle());
        }
    }

    @Test
    public void testCopyingAwardAsChildOfAnAwardInCurrentHierarchy() {
        AwardHierarchy sourceNode = rootNodeA.getChildren().get(1);
        AwardHierarchy targetParentNode = rootNodeA.getChildren().get(2);
        int originalNumberOfChildrenOnTargetNode = targetParentNode.getChildren().size();
        AwardHierarchy newNode = service.copyAwardAsChildOfAnAwardInCurrentHierarchy(sourceNode, targetParentNode);
        Assert.assertEquals(targetParentNode, newNode.getParent());
        Assert.assertEquals(originalNumberOfChildrenOnTargetNode + 1, targetParentNode.childCount());
    }

    @Test
    public void testCreatingBasicHierarchy() {
        AwardHierarchy rootNodeA = createAwardHierarchy(DEFAULT_ROOT_AWARD_NUMBER);
        rootNodeA.setVersionHistoryService(getVersionHistoryService());
        assertNotNull(rootNodeA);
        String awardNumber = rootNodeA.getAwardNumber();
        assertEquals(awardNumber, rootNodeA.getAwardNumber());
        assertEquals(awardNumber, rootNodeA.getRootAwardNumber());
        assertEquals(AwardHierarchy.ROOTS_PARENT_AWARD_NUMBER, rootNodeA.getParentAwardNumber());
    }

    @Test
    public void testCreatingFullHierarchy() {
        AwardHierarchy rootNode = service.loadFullHierarchyFromAnyNode(generateAwardNumber(BASE_AWARD_NUMBER, BASE_HIERARCHY_SEQUENCE));

        assertNotNull(rootNode);
        assertEquals(rootNodeA, rootNode);
        assertTrue(rootNodeA.hasChildren());
        assertEquals(NUMBER_OF_CHILDREN_A, rootNodeA.getChildren().size());
        int hierarchySequenceNumber = BASE_HIERARCHY_SEQUENCE + 1;
        for(AwardHierarchy child: rootNodeA.getChildren()) {
            assertEquals(rootNodeA.getAwardNumber(), child.getRootAwardNumber());
            assertEquals(rootNodeA.getAwardNumber(), child.getParentAwardNumber());
            assertEquals(generateAwardNumber(BASE_AWARD_NUMBER, hierarchySequenceNumber++), child.getAwardNumber());
            for(AwardHierarchy grandChild: child.getChildren()) {
                assertEquals(rootNodeA.getAwardNumber(), grandChild.getRootAwardNumber());
                assertEquals(child.getAwardNumber(), grandChild.getParentAwardNumber());
                assertEquals(generateAwardNumber(BASE_AWARD_NUMBER, hierarchySequenceNumber++), grandChild.getAwardNumber());
            }
        }
    }

    @Test
    public void testCreatingANewChildAward() {
        AwardHierarchy targetNode = rootNodeA.getChildren().get(0);
        String expectedAwardNumber = targetNode.generateNextAwardNumberInSequence();
        AwardHierarchy newNode = service.createNewChildAward(targetNode);
        Assert.assertEquals(targetNode, newNode.getParent());
        Assert.assertEquals(targetNode.getRoot(), newNode.getRoot());
        Assert.assertEquals(expectedAwardNumber, newNode.getAwardNumber());
        Assert.assertNotNull(newNode.getAward());
    }

    @Test
    public void testCreatingNewChildAwardBasedOnAnotherAwardInHierarchy() {
        AwardHierarchy targetNode = rootNodeA.getChildren().get(2).getChildren().get(1);
        AwardHierarchy copyNode = rootNodeA.getChildren().get(1);
        AwardHierarchy newChildNode = service.createNewAwardBasedOnAnotherAwardInHierarchy(copyNode, targetNode);
        Assert.assertEquals(targetNode.getAwardNumber(), newChildNode.getParent().getAwardNumber());
        Assert.assertEquals(rootNodeA.getAwardNumber(), newChildNode.getRoot().getAwardNumber());
        Assert.assertEquals(copyNode.getAward().getTitle(), newChildNode.getAward().getTitle());
        Assert.assertEquals(copyNode.getOriginatingAwardNumber(), newChildNode.getOriginatingAwardNumber());
    }

    @Test
    public void testCreatingNewAwardBasedOnParent() {
        AwardHierarchy targetNode = rootNodeA.getChildren().get(1);
        String expectedAwardNumber = targetNode.generateNextAwardNumberInSequence();
        AwardHierarchy newNode = service.createNewAwardBasedOnParent(targetNode);
        Assert.assertEquals(targetNode, newNode.getParent());
        Assert.assertEquals(targetNode.getRoot(), newNode.getRoot());
        Assert.assertEquals(expectedAwardNumber, newNode.getAwardNumber());
        Award newAward = newNode.getAward();
        Assert.assertEquals(1, newAward.getSequenceNumber().intValue());
        Assert.assertEquals(targetNode.getAward().getTitle(), newAward.getTitle());
    }

    @Test
    public void testCreatingRootNode_NullAwardNumber() {
        assertNull(service.loadAwardHierarchy(null));
    }

    @Test
    @Ignore
    public void testCreateNewChildAward() {
        AwardHierarchy root = createFullAwardHierarchy(BASE_AWARD_NUMBER, NUMBER_OF_CHILDREN_A, NUMBER_OF_GRANDCHILDREN_A);
        AwardHierarchy childNode = root.getChildren().get(1);
        int numGrandChildren = childNode.getChildren().size();
        AwardHierarchy newGrandChild = service.createNewChildAward(childNode);
        Assert.assertEquals(numGrandChildren + 1, childNode.getChildren().size());
        Assert.assertNotNull(newGrandChild.getAward());
    }

    @Test
    public void testGettingHierarchyAsMap_UsingVarunification() {
        Map<String, AwardHierarchy> refMap = rootNodeA.getMapOfNodesInHierarchy();
        List<String> awardNumbers = new ArrayList<String>();
        Map<String, AwardHierarchy> nodeMap = service.getAwardHierarchy(rootNodeA.getAwardNumber(), awardNumbers);
        Assert.assertEquals(awardNumbers.size(), nodeMap.size());
        for(String awardNumber : nodeMap.keySet()) {
            Assert.assertEquals(refMap.get(awardNumber), nodeMap.get(awardNumber));
        }
    }
    
    private void addAwards(AwardHierarchy rootNode) throws Exception {
        List<AwardHierarchy> nodes = rootNode.getFlattenedListOfNodesInHierarchy();
        for (AwardHierarchy node: nodes) {
            Award award = createAward(node.getAwardNumber());
            if (placeholderDocument != null) {
                placeholderDocument.getAwardList().add(award);
                award.setAwardDocument(placeholderDocument);
                getDocumentService().saveDocument(placeholderDocument);
            } else {
                awardList.add(award);
            }
            node.setAward(award);
            getBusinessObjectService().save(node);
        }
    }
    
    private AwardHierarchy createAwardHierarchy(String awardNumber) {
        Award award = createAward(awardNumber);
        AwardHierarchy node = service.createBasicHierarchy(award.getAwardNumber());
        node.setVersionHistoryService(getVersionHistoryService());
        return node;
    }
    
    private AwardHierarchy createFullAwardHierarchy(Long baseAwardNumber, int numberOfChildNodes, int numberOfGrandChildren) {
        AwardHierarchy rootNodeA = createRootHierarchyNode(baseAwardNumber);
        List<AwardHierarchy> childNodes = new ArrayList<AwardHierarchy>();
        for(int i = 0, sequenceNo = i + 2; i < numberOfChildNodes; i++) {
            AwardHierarchy childBranchNode = new AwardHierarchy(rootNodeA, rootNodeA, generateAwardNumber(baseAwardNumber, sequenceNo++), DUMMY_AWARD_NUMBER);
            childBranchNode.setBusinessObjectService(getBusinessObjectService());
            childBranchNode.setVersionHistoryService(getVersionHistoryService());
            childNodes.add(childBranchNode);
            List<AwardHierarchy> grandchildNodes = new ArrayList<AwardHierarchy>();
            for(int j = 0; j < numberOfGrandChildren; j++) {
                AwardHierarchy grandChildNode = new AwardHierarchy(rootNodeA, childBranchNode, generateAwardNumber(baseAwardNumber, sequenceNo++), DUMMY_AWARD_NUMBER);
                childBranchNode.setBusinessObjectService(getBusinessObjectService());
                grandChildNode.setVersionHistoryService(getVersionHistoryService());
                grandchildNodes.add(grandChildNode);
            }
            childBranchNode.setChildren(grandchildNodes);
        }
        rootNodeA.setChildren(childNodes);
        return rootNodeA;
    }
    
    private AwardHierarchy createRootHierarchyNode(Long baseAwardNumber) {
        Award rootAward = new Award();
        rootAward.setAwardNumber(generateAwardNumber(baseAwardNumber, BASE_HIERARCHY_SEQUENCE));
        AwardHierarchy rootNode = AwardHierarchy.createRootNode(rootAward);
        rootNode.setVersionHistoryService(getVersionHistoryService());
        rootNode.setBusinessObjectService(getBusinessObjectService());
        return rootNode;
    }
    
    private Award createAward(String awardNumber) {
        Award award = new Award();
        award.setAwardNumber(awardNumber);
        award.setSequenceNumber(1);
        award.setAwardId(null);
        award.setProjectEndDate(new Date(System.currentTimeMillis()));
        award.setSponsorCode("000162");
        award.setStatusCode(1);
        award.setActivityTypeCode("1");
        award.setAwardTypeCode(1);
        award.setTitle("Sample Award: " + awardNumber);
        return award;
    }
    
    private String generateAwardNumber(long baseAwardNumber, int hierarchySequenceNumber) {
        return String.format(AWARD_NUMBER_PATTERN, baseAwardNumber, hierarchySequenceNumber);
    }
    
    private AwardNumberService getMockAwardNumberService() {
        final AwardNumberService service = context.mock(AwardNumberService.class);
        
        context.checking(new Expectations() {{
            one(service).getNextAwardNumber();
            will(returnValue(NEXT_AWARD_NUMBER_FOR_NEW_HIERARCHY));
        }});
        
        return service;
    }
    
    private VersionHistoryService getVersionHistoryService() {
        return KraServiceLocator.getService(VersionHistoryService.class);
    }
    
    private class AwardServiceMock extends AwardServiceImpl {
        public void updateAwardSequenceStatus(Award award, VersionStatus status) {
            //do nothing.
        }
    }    

}