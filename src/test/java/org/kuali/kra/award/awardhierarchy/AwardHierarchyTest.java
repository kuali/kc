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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.service.impl.VersionHistoryServiceImpl;
import org.kuali.kra.service.impl.adapters.BusinessObjectServiceAdapter;

public class AwardHierarchyTest {
    private static final String AWARD_NUMBER_PATTERN = "%06d-%05d";
    private static final long BASE_AWARD_NUMBER = 100001;
    private static final int BASE_HIERACHY_SEQUENCE = 1;

    private static final int NUMBER_OF_CHILDREN = 10;
    private static final int NUMBER_OF_GRANDCHILDREN = 5;

    @Test
    public void testCreatingRootNode() {
        Award award = createRootAward();
        AwardHierarchy root = AwardHierarchy.createRootNode(award); 
        assertNotNull(root);
        assertEquals(generateAwardNumber(BASE_AWARD_NUMBER, BASE_HIERACHY_SEQUENCE), root.getAwardNumber());
        assertEquals(root.getAwardNumber(), root.getRootAwardNumber());
        assertEquals(AwardHierarchy.ROOTS_PARENT_AWARD_NUMBER, root.getParentAwardNumber());
    }

    @Test
    public void testFindingRootNode() {
        AwardHierarchy rootNode = createFullHierarchy();
        AwardHierarchy testNode = rootNode;
        Assert.assertEquals(rootNode, testNode.findRootNode());
        testNode = rootNode.getChildren().get(1).getChildren().get(2);
        Assert.assertEquals(rootNode, testNode.findRootNode());
    }

    @Test
    public void testFindingNodeInHierarchy() {
        AwardHierarchy rootNode = createFullHierarchy();
        AwardHierarchy startNode = rootNode;
        AwardHierarchy testNode = startNode.getChildren().get(1).getChildren().get(2);
        Assert.assertEquals(testNode, startNode.findNodeInHierarchy(testNode.getAwardNumber()));
        AwardHierarchy lastChildNode = rootNode.getChildren().get(rootNode.getChildren().size() - 1);
        AwardHierarchy lastGrandChildNode = lastChildNode.getChildren().get(lastChildNode.getChildren().size() - 1);
        Assert.assertEquals(testNode, lastGrandChildNode.findNodeInHierarchy(testNode.getAwardNumber()));
    }

    @Test
    public void testFindingAssociatedAward_ActiveAward() {
        Long awardId = 1001L;
        Assert.assertEquals(awardId, prepareSingleNode(VersionStatus.ACTIVE, awardId).getAward().getAwardId());
    }

    @Test
    public void testFindingAssociatedAward_PendingAward() {
        Long awardId = 1001L;
        Assert.assertEquals(awardId, prepareSingleNode(VersionStatus.PENDING, awardId).getAward().getAwardId());
    }

    @Test
    public void testFindingAssociatedAward_PendingAndActiveAwardsExist() {
        List<Award> awards = new ArrayList<Award>();
        List<VersionHistory> versionHistories = new ArrayList<VersionHistory>();

        Long awardId_Active = 1001L;
        Award award = new Award();
        award.setAwardId(awardId_Active);
        award.setAwardNumber(generateAwardNumber(BASE_AWARD_NUMBER, BASE_HIERACHY_SEQUENCE));
        awards.add(award);
        VersionHistory versionHistory = new VersionHistory(award, VersionStatus.ACTIVE, "quickstart", new Date(System.currentTimeMillis()));
        versionHistories.add(versionHistory);

        Long awardId_Pending = 1002L;
        award = new Award();
        award.setAwardId(awardId_Pending);
        award.setAwardNumber(generateAwardNumber(BASE_AWARD_NUMBER, BASE_HIERACHY_SEQUENCE + 1));
        awards.add(award);
        versionHistory = new VersionHistory(award, VersionStatus.PENDING, "quickstart", new Date(System.currentTimeMillis()));
        versionHistories.add(versionHistory);

        AwardHierarchy node = new AwardHierarchy(award.getAwardNumber(), AwardHierarchy.ROOTS_PARENT_AWARD_NUMBER, award.getAwardNumber());
        node.setAward(null);

        node.setVersionHistoryService(prepareVersionHistoryService(awards, versionHistories));

        Assert.assertEquals(awardId_Pending, node.getAward().getAwardId());
    }

    @Test
    public void testGettingFlattenedListOfNodesInHiearchy() {
        Assert.assertEquals(NUMBER_OF_CHILDREN * NUMBER_OF_GRANDCHILDREN + NUMBER_OF_CHILDREN + 1,
                            createFullHierarchy().getFlattenedListOfNodesInHierarchy().size());
    }

    @Test
    public void testGeneratingNextAwardNumberInSequence() {
         Assert.assertEquals(String.format("100001-%05d", NUMBER_OF_CHILDREN * NUMBER_OF_GRANDCHILDREN + NUMBER_OF_CHILDREN + 2),
                                createFullHierarchy().generateNextAwardNumberInSequence());
    }

    private AwardHierarchy createFullHierarchy() {
        Award award = createRootAward();
        AwardHierarchy rootNodeA = AwardHierarchy.createRootNode(award);
        List<AwardHierarchy> childNodes = new ArrayList<AwardHierarchy>();
        for(int i = 0, sequenceNo = i + 2; i < NUMBER_OF_CHILDREN; i++) {
            AwardHierarchy childBranchNode = new AwardHierarchy(rootNodeA, rootNodeA, generateAwardNumber(BASE_AWARD_NUMBER, sequenceNo++),
                                                                rootNodeA.getAwardNumber());
            childNodes.add(childBranchNode);
            List<AwardHierarchy> grandchildNodes = new ArrayList<AwardHierarchy>();
            for(int j = 0; j < NUMBER_OF_GRANDCHILDREN; j++) {
                grandchildNodes.add(new AwardHierarchy(rootNodeA, childBranchNode, generateAwardNumber(BASE_AWARD_NUMBER, sequenceNo++), rootNodeA.getAwardNumber()));
            }
            childBranchNode.setChildren(grandchildNodes);
        }
        rootNodeA.setChildren(childNodes);
        return rootNodeA;
    }

    private Award createRootAward() {
        Award award = new Award();
        award.setAwardNumber(generateAwardNumber(BASE_AWARD_NUMBER, BASE_HIERACHY_SEQUENCE));
        return award;
    }

    private String generateAwardNumber(long baseAwardNumber, int hierarchySequenceNumber) {
        return String.format(AWARD_NUMBER_PATTERN, baseAwardNumber, hierarchySequenceNumber);
    }

    private AwardHierarchy prepareSingleNode(VersionStatus status, Long awardId) {
        Award award = new Award();
        award.setAwardId(awardId);
        award.setAwardNumber(generateAwardNumber(BASE_AWARD_NUMBER, BASE_HIERACHY_SEQUENCE));
        VersionHistory versionHistory = new VersionHistory(award, status, "quickstart", new Date(System.currentTimeMillis()));
        AwardHierarchy node = new AwardHierarchy(award.getAwardNumber(), AwardHierarchy.ROOTS_PARENT_AWARD_NUMBER, award.getAwardNumber());
        node.setAward(null);
        node.setVersionHistoryService(prepareVersionHistoryService(award, versionHistory));
        return node;
    }

    private VersionHistoryServiceImpl prepareVersionHistoryService(final Award award, final VersionHistory versionHistory) {
        List<Award> awards = new ArrayList<Award>();
        awards.add(award);
        List<VersionHistory> versionHistories = new ArrayList<VersionHistory>();
        versionHistories.add(versionHistory);
        return prepareVersionHistoryService(awards, versionHistories);
    }

    private VersionHistoryServiceImpl prepareVersionHistoryService(final Collection<Award> awards, final Collection<VersionHistory> versionHistories) {
        BusinessObjectServiceAdapter bosAdapter = new BusinessObjectServiceAdapter() {
            @Override
            public Collection findMatching(Class klass, Map fieldValues) {
                if(klass.equals(Award.class)) {
                    return awards;
                } else if(klass.equals(VersionHistory.class)) {
                    return versionHistories;
                } else {
                    return null;
                }
            }
        };
        VersionHistoryServiceImpl vhs= new VersionHistoryServiceImpl();
        vhs.setBusinessObjectService(bosAdapter);
        return vhs;
    }
}
