/*
 * Copyright 2005-2010 The Kuali Foundation
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

import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.rice.kew.api.KewApiConstants;

/**
 * Determine if a user can assign a protocol to a committee/schedule and the action is currently not available.
 */
public class ProtocolDeferUnavailableAuthorizer extends ProtocolAuthorizer {

    /**
     * @see org.kuali.kra.irb.auth.ProtocolAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.irb.auth.ProtocolTask)
     */
    public boolean isAuthorized(String userId, ProtocolTask task) {
        return hasPermission(userId, task.getProtocol(), PermissionConstants.PERFORM_IRB_ACTIONS_ON_PROTO) &&
               (!canExecuteAction(task.getProtocol(), ProtocolActionType.DEFERRED) ||
                task.getProtocol().getProtocolDocument().getDocumentHeader().getWorkflowDocument().getStatus().getCode()
                    .equals(KewApiConstants.ROUTE_HEADER_FINAL_CD));
    }
}
