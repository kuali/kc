/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.kew.service.KEWServiceLocator;
import org.kuali.rice.kim.api.identity.Person;

public class QuickStartUserLookupTest extends KcUnitTestBase {

    /*
     * re: JIRA KRACOEUS-635
     */
    @Test(expected=RiceRuntimeException.class)
    public void testFindingQuickstartUser_TruncatedUserId() throws Exception {
        Person user = KEWServiceLocator.getIdentityHelperService().getPersonByPrincipalName("quicksta");
        fail("We should get an exception");
    }
    
    /*
     * re: JIRA KRACOEUS-635
     */
    @Test
    public void testFindingQuickstartUser_CompleteUserId() throws Exception {
        Person user = KEWServiceLocator.getIdentityHelperService().getPersonByPrincipalName("quickstart");
        Assert.assertNotNull(user);
    }
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
}
