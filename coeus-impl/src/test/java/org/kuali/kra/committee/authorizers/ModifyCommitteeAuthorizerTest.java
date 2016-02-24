/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.committee.authorizers;

import org.junit.Test;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeTaskBase;
import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.document.authorizer.ModifyCommitteeAuthorizer;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.service.impl.mocks.KraAuthorizationServiceMock;
import org.kuali.rice.kew.api.exception.WorkflowException;

import static org.junit.Assert.assertEquals;

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
        CommitteeTaskBase<Committee> task = new CommitteeTaskBase<Committee>(TaskGroupName.COMMITTEE, TaskName.MODIFY_COMMITTEE, committee) {};
        assertEquals(true, authorizer.isAuthorized(USERNAME, task));
    }
    
    @Test
    public void testNotCreatePermission() throws WorkflowException {
        ModifyCommitteeAuthorizer authorizer = new ModifyCommitteeAuthorizer();
        
        final UnitAuthorizationService unitAuthorizationService = new UnitAuthorizationServiceMock(false);
        authorizer.setUnitAuthorizationService(unitAuthorizationService);
        
        Committee committee = createCommittee(null, false);
        CommitteeTaskBase<Committee> task = new CommitteeTaskBase<Committee>(TaskGroupName.COMMITTEE, TaskName.MODIFY_COMMITTEE, committee) {};
        assertEquals(false, authorizer.isAuthorized(USERNAME, task));
    }
    
    @Test
    public void testModifyPermission() throws WorkflowException {
        ModifyCommitteeAuthorizer authorizer = new ModifyCommitteeAuthorizer();
        
        final Committee committee = createCommittee(1L, false);
        
        final UnitAuthorizationService unitAuthorizationService = new UnitAuthorizationServiceMock(true);
        authorizer.setUnitAuthorizationService(unitAuthorizationService);

        final KcAuthorizationService kraAuthorizationService = new KraAuthorizationServiceMock(true);
        authorizer.setKraAuthorizationService(kraAuthorizationService);
        
        CommitteeTaskBase<Committee> task = new CommitteeTaskBase<Committee>(TaskGroupName.COMMITTEE, TaskName.MODIFY_COMMITTEE, committee) {};
        assertEquals(true, authorizer.isAuthorized(USERNAME, task));
    }
    
    @Test
    public void testNotModifyPermission() throws WorkflowException {
        ModifyCommitteeAuthorizer authorizer = new ModifyCommitteeAuthorizer();
        
        final Committee committee = createCommittee(1L, false);
        
        final UnitAuthorizationService unitAuthorizationService = new UnitAuthorizationServiceMock(true);
        authorizer.setUnitAuthorizationService(unitAuthorizationService);

        final KcAuthorizationService kraAuthorizationService = new KraAuthorizationServiceMock(false);
        authorizer.setKraAuthorizationService(kraAuthorizationService);
        
        CommitteeTaskBase<Committee> task = new CommitteeTaskBase<Committee>(TaskGroupName.COMMITTEE, TaskName.MODIFY_COMMITTEE, committee) {};
        assertEquals(false, authorizer.isAuthorized(USERNAME, task));
    }
    
    @Test
    public void testViewOnly() throws WorkflowException {
        ModifyCommitteeAuthorizer authorizer = new ModifyCommitteeAuthorizer();
        
        final Committee committee = createCommittee(1L, true);
        
        final UnitAuthorizationService unitAuthorizationService = new UnitAuthorizationServiceMock(true);
        authorizer.setUnitAuthorizationService(unitAuthorizationService);

        final KcAuthorizationService kraAuthorizationService = new KraAuthorizationServiceMock(true);
        authorizer.setKraAuthorizationService(kraAuthorizationService);
        
        CommitteeTaskBase<Committee> task = new CommitteeTaskBase<Committee>(TaskGroupName.COMMITTEE, TaskName.MODIFY_COMMITTEE, committee) {};
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
