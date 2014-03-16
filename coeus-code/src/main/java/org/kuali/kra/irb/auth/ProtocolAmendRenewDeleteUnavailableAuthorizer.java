/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.auth;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolStatus;

/**
 * Is the user allowed to delete a protocol, amendment or renewal and the action is currently not available?
 */
public class ProtocolAmendRenewDeleteUnavailableAuthorizer extends ProtocolAuthorizer {

    /**
     * @see org.kuali.kra.irb.auth.ProtocolAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.irb.auth.ProtocolTask)
     */
    public boolean isAuthorized(String userId, ProtocolTask task) {
        return hasPermission(userId, task.getProtocol(), PermissionConstants.DELETE_PROTOCOL) &&
               (task.getProtocol().getProtocolDocument().isViewOnly() ||
                !inProgress(task.getProtocol()));
    }
    
    private boolean inProgress(Protocol protocol) {
        return StringUtils.equals(protocol.getProtocolStatusCode(), ProtocolStatus.IN_PROGRESS) ||
               StringUtils.equals(protocol.getProtocolStatusCode(), ProtocolStatus.AMENDMENT_IN_PROGRESS) ||
               StringUtils.equals(protocol.getProtocolStatusCode(), ProtocolStatus.RENEWAL_IN_PROGRESS);
    }
}
