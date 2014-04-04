/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.iacuc.auth;

import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.assignCmt.IacucProtocolAssignCmtService;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionType;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;

public class RequesstSuspensionIacucProtocolAuthorizer extends IacucProtocolAuthorizer {
    
    private IacucProtocolAssignCmtService assignToCmtService;
    
    public void setAssignToCmtService(IacucProtocolAssignCmtService iacucProtocolAssignCmtService) {
        this.assignToCmtService = iacucProtocolAssignCmtService;
    }
    
    protected ProtocolSubmissionBase findSubmisionHook(ProtocolBase protocol) {
        return assignToCmtService.getLastSubmission(protocol);
    }
    
    @Override
    public boolean isAuthorized(String userId, IacucProtocolTask task) {
        return canExecuteAction(task.getProtocol(), IacucProtocolActionType.IACUC_REQUEST_SUSPEND) &&
               !(isRequestForSuspension(findSubmisionHook(task.getProtocol()), IacucProtocolSubmissionType.REQUEST_SUSPEND) 
                        & !isAdmin(userId, RoleConstants.DEPARTMENT_ROLE_TYPE, RoleConstants.IACUC_ADMINISTRATOR)) &&
               (hasPermission(userId, task.getProtocol(), PermissionConstants.SUBMIT_IACUC_PROTOCOL)
                        || hasPermission(userId, task.getProtocol(), PermissionConstants.SUBMIT_ANY_IACUC_PROTOCOL));
    }

}
