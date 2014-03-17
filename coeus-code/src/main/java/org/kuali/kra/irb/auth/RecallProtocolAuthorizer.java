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

import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.Protocol;

/**
 * The Modify Protocol Authorizer checks to see if the user has 
 * permission to modify a protocol. Authorization depends upon whether
 * the protocol is being created or modified.  For creation, the
 * user needs the CREATE_PROTOCOL permission.  If the protocol is being
 * modified, the user only needs to have the MODIFY_PROTOCOL permission 
 * for that protocol.
 */
public class RecallProtocolAuthorizer extends ProtocolAuthorizer {

    @Override
    public boolean isAuthorized(String userId, ProtocolTask task) {
        boolean hasPermission = true;
        Protocol protocol = task.getProtocol();
        hasPermission = !isPessimisticLocked(protocol.getProtocolDocument())
                && protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument().isEnroute()
                && !protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument().getCurrentNodeNames().contains("IRBReview")
                && hasPermission(userId, protocol, PermissionConstants.RECALL_DOCUMENT);
        return hasPermission;
    }
    
}
