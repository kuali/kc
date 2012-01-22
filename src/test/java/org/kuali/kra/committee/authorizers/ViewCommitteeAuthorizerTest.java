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
package org.kuali.kra.committee.authorizers;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test the View Committee Authorizer.
 */
@RunWith(JMock.class)
public class ViewCommitteeAuthorizerTest {

    private static final String USERNAME = "quickstart";
    private static final String USER_QUICKSTART_PERSONID = "00000001";
   
    private Mockery context = new JUnit4Mockery();
    
    @Test
    public void testViewPermission() {
//        ViewCommitteeAuthorizer authorizer = new ViewCommitteeAuthorizer();
//        
//        final Committee committee = new Committee();
//        
//        final KraAuthorizationService committeeAuthorizationService = context.mock(KraAuthorizationService.class);
//        context.checking(new Expectations() {{
//            one(committeeAuthorizationService).hasPermission(USER_QUICKSTART_PERSONID, committee, PermissionConstants.VIEW_COMMITTEE); will(returnValue(true));
//        }});
//        authorizer.setKraAuthorizationService(committeeAuthorizationService);
//        
//        CommitteeTask task = new CommitteeTask(TaskName.VIEW_COMMITTEE, committee);
//        assertEquals(true, authorizer.isAuthorized(USER_QUICKSTART_PERSONID, task));
    }
    
    @Test
    public void testNotViewPermission() {
//        ViewCommitteeAuthorizer authorizer = new ViewCommitteeAuthorizer();
//        
//        final Committee committee = new Committee();
//        
//        final KraAuthorizationService committeeAuthorizationService = context.mock(KraAuthorizationService.class);
//        context.checking(new Expectations() {{
//            one(committeeAuthorizationService).hasPermission(USER_QUICKSTART_PERSONID, committee, PermissionConstants.VIEW_COMMITTEE); will(returnValue(false));
//        }});
//        authorizer.setKraAuthorizationService(committeeAuthorizationService);
//        
//        CommitteeTask task = new CommitteeTask(TaskName.VIEW_COMMITTEE, committee);
//        assertEquals(false, authorizer.isAuthorized(USER_QUICKSTART_PERSONID, task));
    }
}
