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
package org.kuali.kra.protocol.auth;

import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;


/**
 * Is the user allowed to create an amendment for the protocol and the action is currently not available?
 */
public abstract class CreateAmendmentUnavailableAuthorizerBase extends ProtocolAuthorizerBase {

    @Override
    public boolean isAuthorized(String userId, ProtocolTaskBase task) {
        return hasPermission(userId, task.getProtocol(), getPermissionCreateAmendmentHook()) &&
               ( isAmendmentOrRenewal(task.getProtocol()) ||
                 (isRequestForSuspension(findSubmisionHook(task.getProtocol()), getProtocolSubmissionTypeHook()) & !isAdmin(userId, getAdminNamespaceHook(), getAdminRoleHook())) ||
                 !canExecuteAction(task.getProtocol(), getActionTypeAmendmentCreatedHook())
               );
    }

    protected abstract String getActionTypeAmendmentCreatedHook();
    protected abstract String getPermissionCreateAmendmentHook();
    protected abstract String getAdminNamespaceHook();
    protected abstract String getAdminRoleHook();
    protected abstract String getProtocolSubmissionTypeHook();
    protected abstract ProtocolSubmissionBase findSubmisionHook(ProtocolBase protocol);
    
}
