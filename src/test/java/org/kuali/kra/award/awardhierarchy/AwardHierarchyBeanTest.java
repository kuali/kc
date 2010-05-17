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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.home.Award;

public class AwardHierarchyBeanTest {
    private AwardHierarchyBean bean;
    private AwardHierarchyServiceImpl service;
    private Award rootAward;
    static final String UNKNOWN_TARGET_NODE_AWARD_NUMBER = "123456-78901";
    private static final String CHILD_AWARD_NUMBER = "100001-00002";
    private static final String ROOT_AWARD_NUMBER = "100001-00001";

    @Before
    public void setUp() {
        service = new AwardHierarchyServiceImpl();
        service.setAwardNumberService(new AwardHierarchyTestHelper.MockAwardNumberService());
        service.setBusinessObjectService(new AwardHierarchyTestHelper.MockBusinessObjectService());
        service.setDocumentService(new AwardHierarchyTestHelper.MockDocumentService());
        service.setVersioningService(new AwardHierarchyTestHelper.MockVersioningService());
        rootAward = new Award();
        rootAward.setAwardNumber(ROOT_AWARD_NUMBER);
        createAwardHierarchyBean();
    }

    @After
    public void tearDown() {
        bean = null;
    }

    @Test
    public void testSaveHierarchyChanges_NoError() {
        Assert.assertTrue(bean.saveHierarchyChanges());
    }

    @Test(expected=MissingHierarchyException.class)
    public void testCreatingNewChildAwardBasedOnAnotherAwardInHierarchy_BadInput() {
        String targetAwardNumber = bean.getRootNode().getAwardNumber();
        String awardNumberOfAwardToBeUsedAsTemplate = UNKNOWN_TARGET_NODE_AWARD_NUMBER;
        bean.createNewChildAwardBasedOnAnotherAwardInHierarchy(targetAwardNumber, awardNumberOfAwardToBeUsedAsTemplate);
    }

    @Test
    public void testGettingNodeForCurrentAward() { 
        Assert.assertEquals(rootAward.getAwardNumber(), bean.getCurrentAwardHierarchyNode().getAwardNumber());
    }

    private void createAwardHierarchyBean() {
        bean = new AwardHierarchyBeanForUnitTest(null, service);
        bean.rootNodes.put(rootAward.getAwardNumber(), AwardHierarchy.createRootNode(rootAward));
    }

    private class AwardHierarchyBeanForUnitTest extends AwardHierarchyBean {
        Award award;
        AwardHierarchyBeanForUnitTest(AwardForm awardForm, AwardHierarchyService service) { super(awardForm, service); }

        @Override
        Award getAward() { // since we can't create AwardDocument in unit test, override this method to provide Award
            return award != null ? award : rootAward;
        }

    }
}
