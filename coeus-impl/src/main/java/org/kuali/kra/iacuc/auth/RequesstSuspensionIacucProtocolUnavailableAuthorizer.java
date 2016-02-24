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
package org.kuali.kra.iacuc.auth;

import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.assignCmt.IacucProtocolAssignCmtService;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionType;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;

public class RequesstSuspensionIacucProtocolUnavailableAuthorizer extends IacucProtocolAuthorizer {
   
    private IacucProtocolAssignCmtService assignToCmtService;
    
    public void setAssignToCmtService(IacucProtocolAssignCmtService iacucProtocolAssignCmtService) {
        this.assignToCmtService = iacucProtocolAssignCmtService;
    }
    
    protected ProtocolSubmissionBase findSubmisionHook(ProtocolBase protocol) {
        return assignToCmtService.getLastSubmission(protocol);
    }
    
    @Override
    public boolean isAuthorized(String userId, IacucProtocolTask task) {
        return  (!canExecuteAction(task.getProtocol(), IacucProtocolActionType.IACUC_REQUEST_SUSPEND) ||
                 (isRequestForSuspension(findSubmisionHook(task.getProtocol()), IacucProtocolSubmissionType.REQUEST_SUSPEND) 
                        & !isAdmin(userId, RoleConstants.DEPARTMENT_ROLE_TYPE, RoleConstants.IACUC_ADMINISTRATOR))
                ) &&
                (hasPermission(userId, task.getProtocol(), PermissionConstants.SUBMIT_IACUC_PROTOCOL)
                        || hasPermission(userId, task.getProtocol(), PermissionConstants.SUBMIT_ANY_IACUC_PROTOCOL)
                );
    }

}
