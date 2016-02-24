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
import org.kuali.kra.irb.actions.ProtocolActionType;

/**
 * 
 * This class to check whether user has authorization to abandon protocol
 */
public class AbandonProtocolAuthorizer extends ProtocolAuthorizer {

    @Override
    public boolean isAuthorized(String userId, ProtocolTask task) {
        // TODO : permission : PI and protocol has never been approved. protocol status is SRR/SMR
        return canExecuteAction(task.getProtocol(), ProtocolActionType.ABANDON_PROTOCOL) 
            && (hasPermission(userId, task.getProtocol(), PermissionConstants.SUBMIT_PROTOCOL)
                    || hasPermission(userId, task.getProtocol(), PermissionConstants.MODIFY_ANY_PROTOCOL));
    }

}



