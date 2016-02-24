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

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolStatus;

/**
 * Is the user allowed to delete a protocol, amendment or renewal?
 */
public class ProtocolAmendRenewDeleteAuthorizer extends ProtocolAuthorizer {

    @Override
    public boolean isAuthorized(String userId, ProtocolTask task) {
        return !task.getProtocol().getProtocolDocument().isViewOnly() &&
               inProgress(task.getProtocol()) &&
               hasPermission(userId, task.getProtocol(), PermissionConstants.DELETE_PROTOCOL);
    }
    
    private boolean inProgress(Protocol protocol) {
        return StringUtils.equals(protocol.getProtocolStatusCode(), ProtocolStatus.IN_PROGRESS) ||
               StringUtils.equals(protocol.getProtocolStatusCode(), ProtocolStatus.AMENDMENT_IN_PROGRESS) ||
               StringUtils.equals(protocol.getProtocolStatusCode(), ProtocolStatus.RENEWAL_IN_PROGRESS);
    }
}
