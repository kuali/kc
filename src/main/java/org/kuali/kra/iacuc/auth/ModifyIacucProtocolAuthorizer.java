/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.iacuc.auth;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;

public class ModifyIacucProtocolAuthorizer  extends IacucProtocolAuthorizer {

    public boolean isAuthorized(String userId, IacucProtocolTask task) {
        boolean hasPermission = true;
        IacucProtocol protocol = task.getProtocol();
        Long protocolId = protocol.getProtocolId();
        if (protocolId == null) {
            
            // We have to consider the case when we are saving the protocol for the first time.
            
            hasPermission = hasUnitPermission(userId, Constants.MODULE_NAMESPACE_IACUC, PermissionConstants.CREATE_IACUC_PROTOCOL);
        } 
        else {
            /*
             * After the initial save, the protocol can only be modified has the required permission.
             */
            hasPermission = !protocol.getIacucProtocolDocument().isViewOnly() && 
                            !isPessimisticLocked(protocol.getIacucProtocolDocument()) &&
                            (!kraWorkflowService.isInWorkflow(protocol.getIacucProtocolDocument()) || protocol.isCorrectionMode()) &&
                            hasPermission(userId, protocol, PermissionConstants.MODIFY_IACUC_PROTOCOL);
    
        }
        return hasPermission;
    }


}
