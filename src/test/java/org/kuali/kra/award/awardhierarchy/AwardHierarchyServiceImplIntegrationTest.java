package org.kuali.kra.award.awardhierarchy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

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
public class AwardHierarchyServiceImplIntegrationTest extends KcUnitTestBase {
    private AwardHierarchyTestHelper helper;
    private AwardHierarchyService awardHierarchyService;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        awardHierarchyService = KraServiceLocator.getService(AwardHierarchyService.class);
        helper = new AwardHierarchyTestHelper(awardHierarchyService);
    }

    @After
    public void tearDown() throws Exception {
        awardHierarchyService = null;
        super.tearDown();
    }

    @Test
    public void testCopyingAwardAsChildOfAnAwardInAnotherHierarchy() {
        helper.testCopyingAwardAsChildOfAnAwardInAnotherHierarchy();
    }

    @Test
    public void testCopyingAwardAsChildOfAnAwardInCurrentHierarchy() {
        helper.testCopyingAwardAsChildOfAnAwardInCurrentHierarchy();
    }

    @Test
    public void testCopyingAwardAsNewHierarchy() {
        helper.testCopyingAwardAsNewHierarchy();
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
        helper.testCopyingAwardAndDescendantsAsNewHierarchy();
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
}
