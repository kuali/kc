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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

class AwardHierarchyTestHelper {
    private static final String AWARD_NUMBER_PATTERN = "%06d-%05d";
    private static final long BASE_AWARD_NUMBER = 100001;
    private static final int BASE_HIERACHY_SEQUENCE = 1;
    
    private AwardHierarchyService service;
    
    AwardHierarchyTestHelper(AwardHierarchyService service) {
        this.service = service;
    }
    
    @Test
    public void testCreatingRootNode() {
        AwardHierarchy rootNodeA = createAndSaveSingleNodeHierarchy();
        AwardHierarchy rootNode = service.getAwardHierarchy(generateAwardNumber(BASE_AWARD_NUMBER, BASE_HIERACHY_SEQUENCE));
        assertNotNull(rootNode);
        assertEquals(rootNodeA, rootNode);
    }
    
    @Test
    public void testCreatingHierarchy() {
        final int NUMBER_OF_CHILDREN = 10;
        final int NUMBER_OF_GRANDCHILDREN = 5;
        AwardHierarchy rootNodeA = createAndSaveFullAwardHierarchy(NUMBER_OF_CHILDREN, NUMBER_OF_GRANDCHILDREN);
        AwardHierarchy rootNode = service.getAwardHierarchy(generateAwardNumber(BASE_AWARD_NUMBER, BASE_HIERACHY_SEQUENCE), true);
        
        assertNotNull(rootNode);
        assertEquals(rootNodeA, rootNode);
        assertTrue(rootNodeA.hasChildren());
        assertEquals(NUMBER_OF_CHILDREN, rootNodeA.getChildren().size());
        int hierarchySequenceNumber = BASE_HIERACHY_SEQUENCE + 1;
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
    public void testCreatingRootNode_NullAwardNumber() {
        assertNull(service.getAwardHierarchy(null));
    }
    
    private AwardHierarchy createAndSaveSingleNodeHierarchy() {
        AwardHierarchy rootNodeA = AwardHierarchy.createRootNode(generateAwardNumber(BASE_AWARD_NUMBER, BASE_HIERACHY_SEQUENCE));
        service.persistAwardHierarchy(rootNodeA);
        return rootNodeA;
    }
    
    private AwardHierarchy createAndSaveFullAwardHierarchy(int numberOfNodes, int numberOfGrandChildren) {
        AwardHierarchy rootNodeA = AwardHierarchy.createRootNode(generateAwardNumber(BASE_AWARD_NUMBER, BASE_HIERACHY_SEQUENCE));
        List<AwardHierarchy> childNodes = new ArrayList<AwardHierarchy>();
        for(int i = 0, sequenceNo = i + 2; i < numberOfNodes; i++) {
            AwardHierarchy childBranchNode = new AwardHierarchy(rootNodeA, rootNodeA, generateAwardNumber(BASE_AWARD_NUMBER, sequenceNo++));
            childNodes.add(childBranchNode);
            List<AwardHierarchy> grandchildNodes = new ArrayList<AwardHierarchy>();
            for(int j = 0; j < numberOfGrandChildren; j++) {
                grandchildNodes.add(new AwardHierarchy(rootNodeA, childBranchNode, generateAwardNumber(BASE_AWARD_NUMBER, sequenceNo++)));
            }
            childBranchNode.setChildren(grandchildNodes);
        }
        rootNodeA.setChildren(childNodes);
        service.persistAwardHierarchy(rootNodeA, AwardHierarchyService.RECURSE_HIERARCHY);
        return rootNodeA;
    }
    
    private String generateAwardNumber(long baseAwardNumber, int hierarchySequenceNumber) {
        return String.format(AWARD_NUMBER_PATTERN, baseAwardNumber, hierarchySequenceNumber);
    }
}
