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
import org.kuali.kra.protocol.auth.CreateContinuationAuthorizerBase;

public class CreateIacucContinuationAuthorizer extends CreateContinuationAuthorizerBase {

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
    protected String getActionTypeContinuationCreatedHook() {
        return IacucProtocolActionType.CONTINUATION;
        
    }

    @Override
    protected String getPermissionCreateContinuationHook() {
        return PermissionConstants.CREATE_IACUC_CONT_REVIEW;
    }

    @Override
    protected String getPermissionCreateAnyContinuationHook() {
        return PermissionConstants.CREATE_ANY_IACUC_CONT_REV_AM;
    }

}
