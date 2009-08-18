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
package org.kuali.kra.irb.auth;

import org.kuali.kra.infrastructure.PermissionConstants;

/**
 * The Modify Protocol Permissions Authorizer checks to see if the user has 
 * permission to maintain protocol access, i.e. assign Users to Protocol Roles.
 */
public class ModifyProtocolPermissionsAuthorizer extends ProtocolAuthorizer {

    @Override
    public boolean isAuthorized(String userName, ProtocolTask task) {
        return !task.getProtocol().getProtocolDocument().isViewOnly() &&
               !kraWorkflowService.isInWorkflow(task.getProtocol().getProtocolDocument()) &&
               hasPermission(userName, task.getProtocol(), PermissionConstants.MAINTAIN_PROTOCOL_ACCESS);
    }
}
