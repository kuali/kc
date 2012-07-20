/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.protocol.auth.GenericProtocolAuthorizer;

/**
 * 
 * This class is the authorizer for all the generic actions.
 */
public class IacucGenericProtocolAuthorizer extends GenericProtocolAuthorizer {
    
    /**
     * deactivate protocol generic action.
     */
    public static final String DEACTIVATE_PROTOCOL = "iacucProtocolDeactivate";
    
    private static final Map<String, String> TASK_NAME_TO_ACTION_TYPE_MAP = new HashMap<String,String>();
    static {
        TASK_NAME_TO_ACTION_TYPE_MAP.put(DEACTIVATE_PROTOCOL, IacucProtocolActionType.REQUEST_DEACTIVATE);
//        TASK_NAME_TO_ACTION_TYPE_MAP.put(CLOSE_ENROLLMENT_PROTOCOL, ProtocolActionType.CLOSED_FOR_ENROLLMENT);
//        TASK_NAME_TO_ACTION_TYPE_MAP.put(EXPIRE_PROTOCOL, ProtocolActionType.EXPIRED);
//        TASK_NAME_TO_ACTION_TYPE_MAP.put(PERMIT_DATA_ANALYSIS, ProtocolActionType.DATA_ANALYSIS_ONLY);
//        TASK_NAME_TO_ACTION_TYPE_MAP.put(REOPEN_PROTOCOL, ProtocolActionType.REOPEN_ENROLLMENT);
//        TASK_NAME_TO_ACTION_TYPE_MAP.put(SUSPEND_PROTOCOL, ProtocolActionType.SUSPENDED);
//        TASK_NAME_TO_ACTION_TYPE_MAP.put(SUSPEND_PROTOCOL_BY_DSMB, ProtocolActionType.SUSPENDED_BY_DSMB);
//        TASK_NAME_TO_ACTION_TYPE_MAP.put(TERMINATE_PROTOCOL, ProtocolActionType.TERMINATED);     
    }
    
    private static final String ERROR_MESSAGE = "Please set genericTaskName with one of the static strings in this class.";
    
    private String genericTaskName;

    
    /** {@inheritDoc} */
    public boolean isAuthorized(String userId, IacucProtocolTask task) {
        return canExecuteAction(task.getProtocol(), convertGenericTaskNameToProtocolActionType()) 
            && hasPermission(userId, task.getProtocol(), PermissionConstants.MAINTAIN_IACUC_PROTOCOL_SUBMISSIONS);
    }
    
    /**
     * 
     * This method converts a Generic Task Name to a Protocol Action Type.
     * @return a ProtocolActionType String
     */
     String convertGenericTaskNameToProtocolActionType() {
        if (TASK_NAME_TO_ACTION_TYPE_MAP.containsKey(this.genericTaskName)) {
            return TASK_NAME_TO_ACTION_TYPE_MAP.get(this.genericTaskName);
        } else {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }      
    }
    
    /**
     * 
     * This method sets the genericTaskName variable.  Please use the static strings in this class.
     * @param genericTaskName String.
     */
    public void setGenericTaskName(String genericTaskName) {
        if (TASK_NAME_TO_ACTION_TYPE_MAP.containsKey(genericTaskName)) {
            this.genericTaskName = genericTaskName;
        } else {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }
}
