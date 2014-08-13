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
import org.kuali.kra.irb.actions.ProtocolActionType;

/**
 * Is the user allowed to create a renewal for the protocol?
 */
public class CreateRenewalAuthorizer extends ProtocolAuthorizer {

    @Override
    public boolean isAuthorized(String userId, ProtocolTask task) {
        
        return  !isAmendmentOrRenewal(task.getProtocol()) &&
                canExecuteAction(task.getProtocol(), ProtocolActionType.RENEWAL_CREATED) &&
                (hasPermission(userId, task.getProtocol(), PermissionConstants.CREATE_RENEWAL) 
                    || hasPermission(userId, task.getProtocol(), PermissionConstants.CREATE_ANY_RENEWAL)) &&
                !(isRequestForSuspension(task.getProtocol()) & !isIrbAdmin(userId));
    }
}
