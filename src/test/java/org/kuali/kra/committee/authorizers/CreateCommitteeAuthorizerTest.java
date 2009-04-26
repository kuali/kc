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
package org.kuali.kra.committee.authorizers;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.authorization.ApplicationTask;
import org.kuali.kra.authorizer.CreateCommitteeAuthorizer;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.service.UnitAuthorizationService;

/**
 * Test the Create Committee Authorizer.
 */
@RunWith(JMock.class)
public class CreateCommitteeAuthorizerTest {

    private static final String USERNAME = "quickstart";
    
    private Mockery context = new JUnit4Mockery();
    
    @Test
    public void testHasPermission() {
        CreateCommitteeAuthorizer authorizer = new CreateCommitteeAuthorizer();
        
        final UnitAuthorizationService unitAuthorizationService = context.mock(UnitAuthorizationService.class);
        context.checking(new Expectations() {{
            one(unitAuthorizationService).hasPermission(USERNAME, PermissionConstants.ADD_COMMITTEE); will(returnValue(true));
        }});
        authorizer.setUnitAuthorizationService(unitAuthorizationService);
        
        ApplicationTask task = new ApplicationTask(TaskName.ADD_COMMITTEE);
        assertEquals(true, authorizer.isAuthorized(USERNAME, task));
    }
    
    @Test
    public void testDoesNotHavePermission() {
        CreateCommitteeAuthorizer authorizer = new CreateCommitteeAuthorizer();
        
        final UnitAuthorizationService unitAuthorizationService = context.mock(UnitAuthorizationService.class);
        context.checking(new Expectations() {{
            one(unitAuthorizationService).hasPermission(USERNAME, PermissionConstants.ADD_COMMITTEE); will(returnValue(false));
        }});
        authorizer.setUnitAuthorizationService(unitAuthorizationService);
        
        ApplicationTask task = new ApplicationTask(TaskName.ADD_COMMITTEE);
        assertEquals(false, authorizer.isAuthorized(USERNAME, task));
    }
}
