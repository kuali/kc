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

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.document.authorization.CommitteeTask;
import org.kuali.kra.committee.document.authorizer.ModifyCommitteeAuthorizer;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.UnitAuthorizationService;
import org.kuali.kra.service.impl.mocks.KraAuthorizationServiceMock;
import org.kuali.rice.kew.api.exception.WorkflowException;

/**
 * Test the Modify Committee Authorizer.
 */
public class ModifyCommitteeAuthorizerTest {

    private static final String USERNAME = "quickstart";
    
    @Test
    public void testCreatePermission() throws WorkflowException {
        ModifyCommitteeAuthorizer authorizer = new ModifyCommitteeAuthorizer();
        
        final UnitAuthorizationService unitAuthorizationService = new UnitAuthorizationServiceMock(true);
        authorizer.setUnitAuthorizationService(unitAuthorizationService);
        
        Committee committee = createCommittee(null, false);
        CommitteeTask task = new CommitteeTask(TaskName.MODIFY_COMMITTEE, committee);
        assertEquals(true, authorizer.isAuthorized(USERNAME, task));
    }
    
    @Test
    public void testNotCreatePermission() throws WorkflowException {
        ModifyCommitteeAuthorizer authorizer = new ModifyCommitteeAuthorizer();
        
        final UnitAuthorizationService unitAuthorizationService = new UnitAuthorizationServiceMock(false);
        authorizer.setUnitAuthorizationService(unitAuthorizationService);
        
        Committee committee = createCommittee(null, false);
        CommitteeTask task = new CommitteeTask(TaskName.MODIFY_COMMITTEE, committee);
        assertEquals(false, authorizer.isAuthorized(USERNAME, task));
    }
    
    @Test
    public void testModifyPermission() throws WorkflowException {
        ModifyCommitteeAuthorizer authorizer = new ModifyCommitteeAuthorizer();
        
        final Committee committee = createCommittee(1L, false);
        
        final UnitAuthorizationService unitAuthorizationService = new UnitAuthorizationServiceMock(true);
        authorizer.setUnitAuthorizationService(unitAuthorizationService);

        final KraAuthorizationService kraAuthorizationService = new KraAuthorizationServiceMock(true);
        authorizer.setKraAuthorizationService(kraAuthorizationService);
        
        CommitteeTask task = new CommitteeTask(TaskName.MODIFY_COMMITTEE, committee);
        assertEquals(true, authorizer.isAuthorized(USERNAME, task));
    }
    
    @Test
    public void testNotModifyPermission() throws WorkflowException {
        ModifyCommitteeAuthorizer authorizer = new ModifyCommitteeAuthorizer();
        
        final Committee committee = createCommittee(1L, false);
        
        final UnitAuthorizationService unitAuthorizationService = new UnitAuthorizationServiceMock(true);
        authorizer.setUnitAuthorizationService(unitAuthorizationService);

        final KraAuthorizationService kraAuthorizationService = new KraAuthorizationServiceMock(false);
        authorizer.setKraAuthorizationService(kraAuthorizationService);
        
        CommitteeTask task = new CommitteeTask(TaskName.MODIFY_COMMITTEE, committee);
        assertEquals(false, authorizer.isAuthorized(USERNAME, task));
    }
    
    @Test
    public void testViewOnly() throws WorkflowException {
        ModifyCommitteeAuthorizer authorizer = new ModifyCommitteeAuthorizer();
        
        final Committee committee = createCommittee(1L, true);
        
        final UnitAuthorizationService unitAuthorizationService = new UnitAuthorizationServiceMock(true);
        authorizer.setUnitAuthorizationService(unitAuthorizationService);

        final KraAuthorizationService kraAuthorizationService = new KraAuthorizationServiceMock(true);
        authorizer.setKraAuthorizationService(kraAuthorizationService);
        
        CommitteeTask task = new CommitteeTask(TaskName.MODIFY_COMMITTEE, committee);
        assertEquals(false, authorizer.isAuthorized(USERNAME, task));
    }
    
    private Committee createCommittee(Long id, boolean viewOnly) throws WorkflowException {
       
        CommitteeDocument doc = new CommitteeDocument();
        doc.getCommittee().setId(id);
        doc.getCommittee().setCommitteeId("Committee Test");
        doc.setViewOnly(viewOnly);
        
        return doc.getCommittee();
    }
}
