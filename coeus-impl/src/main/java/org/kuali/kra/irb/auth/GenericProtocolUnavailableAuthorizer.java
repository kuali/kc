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

import org.kuali.kra.infrastructure.PermissionConstants;

/**
 * 
 * This class is the authorizer for all the generic actions and the action is currently not available.
 */
public class GenericProtocolUnavailableAuthorizer extends GenericProtocolAuthorizer {
    @Override
    public boolean isAuthorized(String userId, ProtocolTask task) {
        return !canExecuteAction(task.getProtocol(), super.convertGenericTaskNameToProtocolActionType()) 
            && hasPermission(userId, task.getProtocol(), PermissionConstants.MAINTAIN_PROTOCOL_SUBMISSIONS);
    }
    
}
