/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.auth;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewModule;

/**
 * The Modify Amendment Authorizer determines if
 * the user can modify a particular module within the amendment.
 */
public abstract class ModifyAmendmentAuthorizer extends ModifyProtocolAuthorizer {

    private String moduleTypeCode;
    
    protected ModifyAmendmentAuthorizer(String moduleTypeCode) {
        this.moduleTypeCode = moduleTypeCode;
    }
    
    /**
     * @see org.kuali.kra.irb.auth.ProtocolAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.irb.auth.ProtocolTask)
     */
    @Override
    public boolean isAuthorized(String username, ProtocolTask task) {
        Protocol protocol = task.getProtocol();
        boolean hasPermission = super.isAuthorized(username, task);
        if (hasPermission && isAmendmentOrRenewal(protocol)) {
            hasPermission = canModifyModule(protocol, moduleTypeCode);
        }
        return hasPermission;
    }
    
    /**
     * For amendment (or a renewal with an amendment), the user can only
     * modify certain modules (parts) of the protocol.  Determine if a certain
     * module can be modified or not.
     * @param protocol the amendment protocol
     * @param moduleTypeCode the module type code
     * @return true if the module can be modified; otherwise false
     */
    private boolean canModifyModule(Protocol protocol, String moduleTypeCode) {
        for (ProtocolAmendRenewModule module : protocol.getProtocolAmendRenewal().getModules()) {
            if (StringUtils.equals(moduleTypeCode, module.getProtocolModuleTypeCode())) {
                return true;
            }
        }
        return false;
    }
}
