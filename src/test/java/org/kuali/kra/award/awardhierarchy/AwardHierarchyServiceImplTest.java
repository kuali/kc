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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyTestHelper.MockVersionHistoryService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.service.BusinessObjectService;

public class AwardHierarchyServiceImplTest extends KcUnitTestBase {
    AwardHierarchyServiceImpl service;
    private AwardHierarchyTestHelper helper;

    private AwardHierarchy rootNode;
    private static final String NEXT_AWARD_NUMBER_FOR_NEW_HIERARCHY = "100002-00001";

    @Before  
    public void setUp() throws Exception {
        super.setUp();
        service = new AwardHierarchyServiceImpl();
        service.setAwardNumberService(new AwardHierarchyTestHelper.MockAwardNumberService());
        BusinessObjectService boService = new AwardHierarchyTestHelper.MockBusinessObjectService();
        service.setBusinessObjectService(boService);
        service.setDocumentService(new AwardHierarchyTestHelper.MockDocumentService());
        service.setVersioningService(new AwardHierarchyTestHelper.MockVersioningService());
        MockVersionHistoryService versionHistoryService = new AwardHierarchyTestHelper.MockVersionHistoryService();
        versionHistoryService.setBusinessObjectService(boService);
        service.setVersionHistoryService(versionHistoryService);
        helper = new AwardHierarchyTestHelper(service);
        rootNode = helper.createFullAwardHierarchy(100001L, 10, 5);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        service = null;  
        super.tearDown();
    }

    @Test
    public void testFindingNode_NotPersisted() {
        helper.testFindingNode_NotPersisted();
    }

    @Test
    public void testCopyingAwardAsNewHierarchy() {
        stageNewAwardNumberForNewHierarchy();
        helper.testCopyingAwardAsNewHierarchy();
    }

    @Test
    public void testCopyingAwardAsChildOfAnAwardInAnotherHierarchy() {
        helper.testCopyingAwardAsChildOfAnAwardInAnotherHierarchy();
    }

    @Test
    public void testCopyingAwardAndDescendantsAsChildOfAnAwardInAnotherHierarchy() {
        helper.testCopyingAwardAndDescendantsAsChildOfAnAwardInAnotherHierarchy();
    }

    @Test
    public void testCopyingAwardAndDescendantsAsChildOfAnAwardInCurrentHierarchy() {
        helper.testCopyingAwardAndDescendantsAsChildOfAnAwardInCurrentHierarchy();
    }

    @Test
    public void testCopyingAwardAndDescendantsAsNewHierarchy() {
        stageNewAwardNumberForNewHierarchy();
        helper.testCopyingAwardAndDescendantsAsNewHierarchy();
    }

    @Test
    public void testCopyingAwardAsChildOfAnAwardInCurrentHierarchy() {
        helper.testCopyingAwardAsChildOfAnAwardInCurrentHierarchy();
    }

    @Test
    public void testCreatingBasicHierarchy() {
        helper.testCreatingBasicHierarchy();
    }

    @Test
    public void testCreatingFullHierarchy() {
        helper.testCreatingFullHierarchy();
    }

    @Test
    public void testCreatingANewChildAward() {
        helper.testCreatingANewChildAward();    
    }

    @Test
    public void testCreatingNewChildAwardBasedOnAnotherAwardInHierarchy() {
        helper.testCreatingNewChildAwardBasedOnAnotherAwardInHierarchy();
    }

    @Test
    public void testCreatingNewAwardBasedOnParent() {
        helper.testCreatingNewAwardBasedOnParent();
    }

    @Test
    public void testCreatingRootNode_NullAwardNumber() {
        helper.testCreatingRootNode_NullAwardNumber();
    }

//    @Test
//    public void testCreateNewChildAward() {
//        helper.testCreateNewChildAward();
//    }

    @Test
    public void testGettingHierarchyAsMap_UsingVarunification() {
        helper.testGettingHierarchyAsMap_UsingVarunification();
    }

    private void stageNewAwardNumberForNewHierarchy() {
        ((AwardHierarchyTestHelper.MockAwardNumberService) service.awardNumberService).nextAwardNumber = NEXT_AWARD_NUMBER_FOR_NEW_HIERARCHY;
    }


}
