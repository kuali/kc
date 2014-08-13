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
import org.kuali.kra.protocol.auth.CreateAmendmentUnavailableAuthorizerBase;

public class CreateIacucAmendmentUnavailableAuthorizer extends CreateAmendmentUnavailableAuthorizerBase {

    private IacucProtocolAssignCmtService assignToCmtService;
    
    public void setAssignToCmtService(IacucProtocolAssignCmtService iacucProtocolAssignCmtService) {
        this.assignToCmtService = iacucProtocolAssignCmtService;
    }
    
    @Override
    protected String getAdminNamespaceHook() {
        return RoleConstants.DEPARTMENT_ROLE_TYPE;
    }
    
    @Override
    protected String getAdminRoleHook() {
        return RoleConstants.IACUC_ADMINISTRATOR;
    }
    
    @Override
    protected String getProtocolSubmissionTypeHook() {
        return IacucProtocolSubmissionType.REQUEST_SUSPEND;
    }
        
    @Override
    protected ProtocolSubmissionBase findSubmisionHook(ProtocolBase protocol) {
        return assignToCmtService.getLastSubmission(protocol);
    }
    
    @Override
    protected String getActionTypeAmendmentCreatedHook() {
        return IacucProtocolActionType.AMENDMENT_CREATED;
    }

    @Override
    protected String getPermissionCreateAmendmentHook() {
        return PermissionConstants.CREATE_IACUC_AMENDMENT;
    }

}
