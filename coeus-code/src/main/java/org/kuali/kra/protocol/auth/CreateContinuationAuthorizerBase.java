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
package org.kuali.kra.protocol.auth;

import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;


public abstract class CreateContinuationAuthorizerBase extends ContinuationAuthorizer {

    @Override
    public boolean isAuthorized(String userId, ProtocolTaskBase task) {
        return !isAmendmentOrRenewalOrContinuation(task.getProtocol()) &&
               canExecuteAction(task.getProtocol(), getActionTypeContinuationCreatedHook()) &&
               (hasPermission(userId, task.getProtocol(), getPermissionCreateContinuationHook())
                    || hasPermission(userId, task.getProtocol(), getPermissionCreateAnyContinuationHook()))&&
                    !(isRequestForSuspension(findSubmisionHook(task.getProtocol()), getProtocolSubmissionTypeHook())
                            & !isAdmin(userId, getAdminNamespaceHook(), getAdminRoleHook()));
    }

    protected abstract String getActionTypeContinuationCreatedHook();
    protected abstract String getPermissionCreateContinuationHook();
    protected abstract String getPermissionCreateAnyContinuationHook();
    protected abstract String getAdminNamespaceHook();
    protected abstract String getAdminRoleHook();
    protected abstract String getProtocolSubmissionTypeHook();
    protected abstract ProtocolSubmissionBase findSubmisionHook(ProtocolBase protocol);

}
