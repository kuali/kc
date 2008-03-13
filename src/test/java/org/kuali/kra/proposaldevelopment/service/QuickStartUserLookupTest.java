/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.bo.user.AuthenticationUserId;
import org.kuali.core.bo.user.UserId;
import org.kuali.kra.KraTestBase;

import edu.iu.uis.eden.KEWServiceLocator;
import edu.iu.uis.eden.clientapp.vo.NetworkIdVO;
import edu.iu.uis.eden.exception.EdenUserNotFoundException;
import edu.iu.uis.eden.user.WorkflowUser;

public class QuickStartUserLookupTest extends KraTestBase {

    /*
     * re: JIRA KRACOEUS-635
     */
    @Test(expected=EdenUserNotFoundException.class)
    public void testFindingQuickstartUser_TruncatedUserId() throws Exception {
        UserId userId = new AuthenticationUserId("quicksta");
        KEWServiceLocator.getUserService().getWorkflowUser(new NetworkIdVO(userId.toString()));
        fail("We should get an exception");
    }
    
    /*
     * re: JIRA KRACOEUS-635
     */
    @Test
    public void testFindingQuickstartUser_CompleteUserId() throws Exception {
        UserId userId = new AuthenticationUserId("quickstart");
        WorkflowUser user = KEWServiceLocator.getUserService().getWorkflowUser(new NetworkIdVO(userId.toString()));
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
