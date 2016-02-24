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
package org.kuali.kra.irb.auth;

import org.junit.Test;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.ProtocolDocument;

/**
 * Test the Modify Protocol Authorizer.
 */
public class ModifyProtocolAuthorizerTest extends ProtocolAuthorizerTestBase {
    
    @Test
    public void testCreatePermission() throws Exception {
        runModifyProtocolAuthorizerTest(PROTOCOL_NUMBER, true, false, true, false, true);
    }
    
    @Test
    public void testNotCreatePermission() throws Exception {
        runModifyProtocolAuthorizerTest(PROTOCOL_NUMBER, true, false, false, false, false);
    }
    
    
    @Test
    public void testModifyPermission() throws Exception {
        runModifyProtocolAuthorizerTest(PROTOCOL_NUMBER, false, false, true, false, true);
    }
    
    @Test
    public void testNotModifyPermission() throws Exception {
        runModifyProtocolAuthorizerTest(PROTOCOL_NUMBER, false, false, false, false, false);
    }
    
    @Test
    public void testViewOnly() throws Exception {
        runModifyProtocolAuthorizerTest(PROTOCOL_NUMBER, false, true, true, false, false);
    }
    
    @Test
    public void testInWorkflow() throws Exception {
        runModifyProtocolAuthorizerTest(PROTOCOL_NUMBER, false, false, false, true, false);
    }
    
    @Override
    protected ProtocolAuthorizer createProtocolAuthorizer(ProtocolDocument protocolDocument, boolean hasPermission, boolean isActionAllowed, boolean isInWorkflow) {
        ModifyProtocolAuthorizer authorizer = new ModifyProtocolAuthorizer();
        authorizer.setUnitAuthorizationService(buildUnitAuthorizationService(PermissionConstants.CREATE_PROTOCOL, hasPermission));
        authorizer.setKraAuthorizationService(buildKraAuthorizationService(protocolDocument, PermissionConstants.MODIFY_PROTOCOL, hasPermission));
        authorizer.setKraWorkflowService(buildKraWorkflowService(protocolDocument, isInWorkflow));
        return authorizer;
    }
    
    @Override
    protected String getTaskName() {
        return TaskName.MODIFY_PROTOCOL;
    }

}
