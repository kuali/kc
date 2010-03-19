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
package org.kuali.kra.award.awardhierarchy;

import org.junit.Assert;
import static org.junit.Assert.*;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.award.AwardNumberService;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.service.impl.VersionHistoryServiceImpl;
import org.kuali.kra.service.impl.adapters.BusinessObjectServiceAdapter;
import org.kuali.kra.service.impl.adapters.DocumentServiceAdapter;
import org.kuali.kra.service.impl.adapters.VersioningServiceAdapter;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.bo.DocumentHeader;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;

import java.sql.Date;
import java.util.*;

class AwardHierarchyTestHelper {
    final static int NUMBER_OF_CHILDREN_A = 20;
    final static int NUMBER_OF_GRANDCHILDREN_A = 10;
    final static int NUMBER_OF_CHILDREN_B = 10;
    final static int NUMBER_OF_GRANDCHILDREN_B = 5;
    static final long BASE_AWARD_NUMBER = 100001;
    static final int BASE_HIERARCHY_SEQUENCE = 1;
    
    static final String DUMMY_AWARD_NUMBER = "999999-99999";
    static final String AWARD_NUMBER_PATTERN = "%06d-%05d";
    static final String DEFAULT_ROOT_AWARD_NUMBER = "100001-00001";
    
    AwardHierarchyService service;

    private AwardHierarchy rootNodeA;
    private AwardHierarchy rootNodeB;
    private AwardDocument placeholderDocument;
    List<Award> awardList = new ArrayList<Award>();

    AwardHierarchyTestHelper(AwardHierarchyService awardHierarchyService) {
        this(awardHierarchyService, null);
    }

    AwardHierarchyTestHelper(AwardHierarchyService awardHierarchyService, AwardDocument placeholderAwardDocument) {
        this.service = awardHierarchyService;
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void testCopyingAwardAsNewHierarchy() {
        AwardHierarchy newRootNode = service.copyAwardAsNewHierarchy(rootNodeA.getChildren().get(1));
        Assert.assertFalse(newRootNode.hasChildren());
        newRootNode.setVersionHistoryService(getVersionHistoryService());
        Award newAward = newRootNode.getAward();
        String awardNumber = newAward.getAwardNumber();
        Assert.assertEquals("00001", awardNumber.substring(awardNumber.indexOf("-") + 1));
        Assert.assertEquals(1, newAward.getSequenceNumber().intValue());
    }

    void testCopyingAwardAsChildOfAnAwardInCurrentHierarchy() {
        AwardHierarchy sourceNode = rootNodeA.getChildren().get(1);
        AwardHierarchy targetParentNode = rootNodeA.getChildren().get(2);
        int originalNumberOfChildrenOnTargetNode = targetParentNode.getChildren().size();
        AwardHierarchy newNode = service.copyAwardAsChildOfAnAwardInCurrentHierarchy(sourceNode, targetParentNode);
        Assert.assertEquals(targetParentNode, newNode.getParent());
        Assert.assertEquals(originalNumberOfChildrenOnTargetNode + 1, targetParentNode.childCount());
    }

    void testCopyingAwardAsChildOfAnAwardInAnotherHierarchy() {
        AwardHierarchy sourceNode = rootNodeA.getChildren().get(1);
        AwardHierarchy targetParentNode = rootNodeB.getChildren().get(2);
        int originalNumberOfChildrenOnTargetNode = targetParentNode.getChildren().size();
        AwardHierarchy newNode = service.copyAwardAsChildOfAnAwardInAnotherHierarchy(sourceNode, targetParentNode);
        Assert.assertEquals(targetParentNode, newNode.getParent());
        Assert.assertEquals(originalNumberOfChildrenOnTargetNode + 1, targetParentNode.childCount());
    }

    void testCopyingAwardAndDescendantsAsChildOfAnAwardInAnotherHierarchy() {
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

    void testCopyingAwardAndDescendantsAsChildOfAnAwardInCurrentHierarchy() {
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

    void testCopyingAwardAndDescendantsAsNewHierarchy() {
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

    void testCreatingBasicHierarchy() {
        AwardHierarchy rootNodeA = createAwardHierarchy(DEFAULT_ROOT_AWARD_NUMBER);
        rootNodeA.setVersionHistoryService(getVersionHistoryService());
        assertNotNull(rootNodeA);
        String awardNumber = rootNodeA.getAwardNumber();
        assertEquals(awardNumber, rootNodeA.getAwardNumber());
        assertEquals(awardNumber, rootNodeA.getRootAwardNumber());
        assertEquals(AwardHierarchy.ROOTS_PARENT_AWARD_NUMBER, rootNodeA.getParentAwardNumber());
    }

    void testCreatingFullHierarchy() {
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

    void testCreatingNewAwardBasedOnParent() {
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

    void testCreatingRootNode_NullAwardNumber() {
        assertNull(service.loadAwardHierarchy(null));
    }

    void testCreateNewChildAward() {
        AwardHierarchy root = createFullAwardHierarchy(BASE_AWARD_NUMBER, NUMBER_OF_CHILDREN_A, NUMBER_OF_GRANDCHILDREN_A);
        AwardHierarchy childNode = root.getChildren().get(1);
        int numGrandChildren = childNode.getChildren().size();
        AwardHierarchy newGrandChild = service.createNewChildAward(childNode);
        Assert.assertEquals(numGrandChildren + 1, childNode.getChildren().size());
        Assert.assertNotNull(newGrandChild.getAward());
    }

    void testCreatingANewChildAward() {
        AwardHierarchy targetNode = rootNodeA.getChildren().get(0);
        String expectedAwardNumber = targetNode.generateNextAwardNumberInSequence();
        AwardHierarchy newNode = service.createNewChildAward(targetNode);
        Assert.assertEquals(targetNode, newNode.getParent());
        Assert.assertEquals(targetNode.getRoot(), newNode.getRoot());
        Assert.assertEquals(expectedAwardNumber, newNode.getAwardNumber());
        Assert.assertNotNull(newNode.getAward());
    }

    void testCreatingNewChildAwardBasedOnAnotherAwardInHierarchy() {
        AwardHierarchy targetNode = rootNodeA.getChildren().get(2).getChildren().get(1);
        AwardHierarchy copyNode = rootNodeA.getChildren().get(1);
        AwardHierarchy newChildNode = service.createNewAwardBasedOnAnotherAwardInHierarchy(copyNode, targetNode);
        Assert.assertEquals(targetNode.getAwardNumber(), newChildNode.getParent().getAwardNumber());
        Assert.assertEquals(rootNodeA.getAwardNumber(), newChildNode.getRoot().getAwardNumber());
        Assert.assertEquals(copyNode.getAward().getTitle(), newChildNode.getAward().getTitle());
        Assert.assertEquals(copyNode.getOriginatingAwardNumber(), newChildNode.getOriginatingAwardNumber());
    }

    AwardHierarchy createAwardHierarchy(String awardNumber) {
        Award award = createAward(awardNumber);
        AwardHierarchy node = service.createBasicHierarchy(award.getAwardNumber());
        node.setVersionHistoryService(getVersionHistoryService());
        return node;
    }

    Award createAward(String awardNumber) {
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

    AwardHierarchy createFullAwardHierarchy(Long baseAwardNumber, int numberOfChildNodes, int numberOfGrandChildren) {
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

    void testFindingNode_NotPersisted() {
        AwardHierarchy node = service.loadAwardHierarchy("999999-99999");
        Assert.assertNull(node);
    }

    String generateAwardNumber(long baseAwardNumber, int hierarchySequenceNumber) {
        return String.format(AWARD_NUMBER_PATTERN, baseAwardNumber, hierarchySequenceNumber);
    }

    void testGettingHierarchyAsMap_UsingVarunification() {
        Map<String, AwardHierarchy> refMap = rootNodeA.getMapOfNodesInHierarchy();
        List<String> awardNumbers = new ArrayList<String>();
        Map<String, AwardHierarchy> nodeMap = service.getAwardHierarchy(rootNodeA.getAwardNumber(), awardNumbers);
        Assert.assertEquals(awardNumbers.size(), nodeMap.size());
        for(String awardNumber : nodeMap.keySet()) {
            Assert.assertEquals(refMap.get(awardNumber), nodeMap.get(awardNumber));
        }
    }

    private AwardHierarchy createRootHierarchyNode(Long baseAwardNumber) {
        Award rootAward = new Award();
        rootAward.setAwardNumber(generateAwardNumber(baseAwardNumber, BASE_HIERARCHY_SEQUENCE));
        AwardHierarchy rootNode = AwardHierarchy.createRootNode(rootAward);
        rootNode.setVersionHistoryService(getVersionHistoryService());
        rootNode.setBusinessObjectService(getBusinessObjectService());
        return rootNode;
    }

    static class MockAwardNumberService implements AwardNumberService {
        String nextAwardNumber;
        String nextAwardNumberInHierarchy;

        public String getNextAwardNumber() {
            return nextAwardNumber;
        }
        public String getNextAwardNumberInHierarchy(String awardNumber) {
            return nextAwardNumberInHierarchy;
        }

    }

    private void addAwards(AwardHierarchy rootNode) throws Exception {
        List<AwardHierarchy> nodes = rootNode.getFlattenedListOfNodesInHierarchy();
        for(AwardHierarchy node: nodes) {
            Award award = createAward(node.getAwardNumber());
            if(placeholderDocument != null) {
                placeholderDocument.getAwardList().add(award);
                getDocumentService().saveDocument(placeholderDocument);
            } else {
                awardList.add(award);
            }
            node.setAward(award);
            getBusinessObjectService().save(node);
        }
    }

    private void init() throws Exception {
        awardList = new ArrayList<Award>();
        rootNodeA = createFullAwardHierarchy(100001L, NUMBER_OF_CHILDREN_A, NUMBER_OF_GRANDCHILDREN_A);
        addAwards(rootNodeA);
        rootNodeB = createFullAwardHierarchy(200001L, NUMBER_OF_CHILDREN_B, NUMBER_OF_GRANDCHILDREN_B);
        addAwards(rootNodeB);
        service.persistAwardHierarchy(rootNodeA, AwardHierarchyService.RECURS_HIERARCHY);
        service.persistAwardHierarchy(rootNodeB, AwardHierarchyService.RECURS_HIERARCHY);
    }

//    private VersionHistoryService getVersionHistoryService() {
//        return ((VersionHistoryService) service).versionHistoryService;
//    }

    private BusinessObjectService getBusinessObjectService() {
        return ((AwardHierarchyServiceImpl) service).businessObjectService;
    }

    private VersionHistoryService getVersionHistoryService() {
        VersionHistoryServiceImpl vhs = new VersionHistoryServiceImpl();
        vhs.setBusinessObjectService(getBusinessObjectService());
        return vhs;
    }
    
    private DocumentService getDocumentService() {
        return ((AwardHierarchyServiceImpl) service).documentService;
    }

    static class MockVersioningService extends VersioningServiceAdapter {
        @SuppressWarnings("unchecked")
        @Override
        public <T extends SequenceOwner<?>> T createNewVersion(T oldVersion) throws VersionException {
            Award originalAward = (Award) oldVersion;
            Award newAward = new Award();
            newAward.setAwardNumber(originalAward.getAwardNumber());
            newAward.setSequenceNumber(originalAward.getSequenceNumber() + 1);
            newAward.setTitle(originalAward.getTitle());
            return (T) newAward;
        }
    }

    static class MockBusinessObjectService extends BusinessObjectServiceAdapter {
        Map<String, AwardHierarchy> awardHierarchyMap = new TreeMap<String, AwardHierarchy>();
        Map<String, Award> awardMap = new TreeMap<String, Award>();

        @SuppressWarnings("unchecked")
        @Override
        public PersistableBusinessObject findByPrimaryKey(Class klass, Map identifiers) {
            if(klass.equals(Award.class)) {
                String awardNumber = (String) identifiers.get("awardNumber");
                return awardNumber != null ? awardMap.get(awardNumber) : null;
            }
            if(klass.equals(AwardHierarchy.class)) {
                String awardNumber = (String) identifiers.get("awardNumber");
                return awardNumber != null ? awardHierarchyMap.get(awardNumber) : null;
            }
            return null;
        }

        @Override
        public void save(PersistableBusinessObject bo) {
            if(bo instanceof AwardHierarchy) {
                AwardHierarchy awardHierarchy = (AwardHierarchy) bo;
                awardHierarchyMap.put(awardHierarchy.getAwardNumber(), awardHierarchy);
            }
            if(bo instanceof Award) {
                Award award = (Award) bo;
                award.setAwardId(award.getAwardId() == null ? 1L : award.getAwardId() + 1);
                awardMap.put(award.getAwardNumber(), award);
            }
        }

        @Override
        public void save(List bizObjects) {
            for(Object bo: bizObjects) {
                save((PersistableBusinessObject) bo);
            }
        }

        @SuppressWarnings("unchecked")
        @Override
        public Collection findMatching(Class klass, Map fieldValues) {
            Collection c;
            if(klass.equals(AwardHierarchy.class)) {
                List<AwardHierarchy> awardHierarchyNodes = new ArrayList<AwardHierarchy>();
                awardHierarchyNodes.add(awardHierarchyMap.get(fieldValues.get("awardNumber")));
                c = awardHierarchyNodes;
            } else  if(klass.equals(Award.class)) {
                List<Award> awards = new ArrayList<Award>();
                awards.add(awardMap.get(fieldValues.get("awardNumber")));
                c = awards;
            } else if(klass.equals(DocumentHeader.class)) {
                c = new ArrayList<DocumentHeader>();
            } else {
                c = null;
            }
            return c;
        }

        /**
         * @see org.kuali.kra.service.impl.adapters.BusinessObjectServiceAdapter#findMatchingOrderBy(java.lang.Class, java.util.Map, java.lang.String, boolean)
         */
        @SuppressWarnings("unchecked")
        @Override
        public Collection findMatchingOrderBy(Class klass, Map fieldValues, String sortField, boolean sortAscending) {
            if(klass.equals(AwardHierarchy.class)) {
                String parentAwardNumber = (String) fieldValues.get("parentAwardNumber");
                Map<String, AwardHierarchy> matching = new TreeMap<String, AwardHierarchy>();
                for(AwardHierarchy node: awardHierarchyMap.values()) {
                    if(node.getParentAwardNumber().equals(parentAwardNumber)) {
                        matching.put(node.getAwardNumber(), node);
                    }
                }
                List<AwardHierarchy> list = new ArrayList<AwardHierarchy>();
                for(String awardNumber: matching.keySet()) {
                    list.add(matching.get(awardNumber));
                }
                return list;
            }
            if(klass.equals(Award.class)) {
                List<Award> list = new ArrayList<Award>();
                list.add(awardMap.get(fieldValues.get("awardNumber")));
                return list;
            }
            return null;
        }
    }

    static class MockDocumentService extends DocumentServiceAdapter {
        @Override
        public Document getByDocumentHeaderId(String documentHeaderId) throws WorkflowException {
            return null;
        }

        @SuppressWarnings("unchecked")
        @Override
        public Document getNewDocument(Class documentClass) throws WorkflowException {
            return null;
        }

    }
}
