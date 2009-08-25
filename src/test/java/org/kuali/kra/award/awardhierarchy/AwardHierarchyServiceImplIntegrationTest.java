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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.infrastructure.KraServiceLocator;

public class AwardHierarchyServiceImplIntegrationTest extends KraTestBase {
    private AwardHierarchyService service;
    private AwardHierarchyTestHelper helper;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        service = (AwardHierarchyService) KraServiceLocator.getService(AwardHierarchyService.class);
        helper = new AwardHierarchyTestHelper(service);
    }
    
    @After
    public void tearDown() throws Exception {
        service = null;
        helper = null;
        super.tearDown();
    }

    @Test
    public void testCreatingRootNode() {
        helper.testCreatingRootNode();
    }
    
    @Test
    public void testCreatingHierarchy() {
        helper.testCreatingHierarchy();
    }
    
    @Test
    public void testCreatingRootNode_NullAwardNumber() {
        helper.testCreatingRootNode_NullAwardNumber();
    }
}