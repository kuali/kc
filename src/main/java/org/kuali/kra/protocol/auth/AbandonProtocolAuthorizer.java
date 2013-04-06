/*
 * Copyright 2005-2013 The Kuali Foundation
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

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * This class to check whether user has authorization to abandon protocol
 */
public class AbandonProtocolAuthorizer extends ProtocolAuthorizerBase {

    private static final List<String> APPROVE_ACTION_TYPES;
    static {
        final List<String> codes = new ArrayList<String>();     
        codes.add(ProtocolActionType.APPROVED);
        codes.add(ProtocolActionType.EXPEDITE_APPROVAL);
        codes.add(ProtocolActionType.GRANT_EXEMPTION);
        APPROVE_ACTION_TYPES = codes;
    }

    /**
     * @see org.kuali.kra.protocol.auth.ProtocolAuthorizerBase#isAuthorized(java.lang.String, org.kuali.kra.protocol.auth.ProtocolTaskBase)
     */
    public boolean isAuthorized(String userId, ProtocolTaskBase task) {
        // TODO : permission : PI and protocol has never been approved. protocol status is SRR/SMR
        return canExecuteAction(task.getProtocol(), ProtocolActionType.ABANDON_PROTOCOL) 
            && (hasPermission(userId, task.getProtocol(), PermissionConstants.SUBMIT_PROTOCOL)
                    || hasPermission(userId, task.getProtocol(), PermissionConstants.MODIFY_ANY_PROTOCOL));
    }
    
    /*
     * check if this protocol has not been approved
     */
    private boolean isInitialProtocol(ProtocolBase protocol) {
        boolean initialProtocol = true;
        return initialProtocol;
    }
    
    /*
     * check if user is PI
     */
    private boolean isPrincipalInvestigator(ProtocolBase protocol) {
        Person user = GlobalVariables.getUserSession().getPerson();
        boolean isPi = false;
        if (user.getPrincipalId().equals(protocol.getPrincipalInvestigator().getPersonId())) {
            isPi = true;
        }
        return isPi;
    }

}



