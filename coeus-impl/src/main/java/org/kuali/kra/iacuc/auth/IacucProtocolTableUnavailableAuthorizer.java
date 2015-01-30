/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
import org.kuali.kra.infrastructure.PermissionConstants;

/**
 * Determine if a user can assign a protocol to a committee/schedule and the action is currently not available.
 */
public class IacucProtocolTableUnavailableAuthorizer extends IacucProtocolTableAuthorizerBase {


    public boolean isAuthorized(String userId, IacucProtocolTask task) {
        return !( 
                  canExecuteAction(task.getProtocol(), IacucProtocolActionType.TABLED) &&
                  checkIfSubmissionCanBeBumped(task.getProtocol())
                 ) &&
               hasPermission(userId, task.getProtocol(), PermissionConstants.PERFORM_IACUC_ACTIONS_ON_PROTO);
    }

    
}
