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
import org.kuali.kra.irb.actions.ProtocolStatus;

/**
 * Test the Protocol/Amendment/Renewal Delete Authorizer.
 */
public class ProtocolAmendRenewDeleteAuthorizerTest extends ProtocolAuthorizerTestBase {
    
    @Test
    public void testHasPermission() throws Exception {
        runStatusAuthorizerTest(PROTOCOL_NUMBER, ProtocolStatus.IN_PROGRESS, true, true);
    }
    
    @Test
    public void testNoPermission() throws Exception {
        runStatusAuthorizerTest(PROTOCOL_NUMBER, ProtocolStatus.IN_PROGRESS, false, false);
    }
    
    @Test
    public void testNotInProgress() throws Exception {
        runStatusAuthorizerTest(PROTOCOL_NUMBER, ProtocolStatus.SUBMITTED_TO_IRB, true, false);
    }
    
    @Override
    protected ProtocolAuthorizer createProtocolAuthorizer(ProtocolDocument protocolDocument, boolean hasPermission, boolean isActionAllowed, boolean isInWorkflow) {
        ProtocolAuthorizer authorizer = new ProtocolAmendRenewDeleteAuthorizer();
        authorizer.setKraAuthorizationService(buildKraAuthorizationService(protocolDocument, PermissionConstants.DELETE_PROTOCOL, hasPermission));
        return authorizer;
    }
    
    @Override
    protected String getTaskName() {
        return TaskName.PROTOCOL_AMEND_RENEW_DELETE;
    }
    
}
