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

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.protocol.auth.GenericProtocolAuthorizer;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * This class is the authorizer for all the generic actions.
 */
public class IacucGenericProtocolAuthorizer extends GenericProtocolAuthorizer {
    
    /**
     * deactivate protocol generic action.
     */
    public static final String EXPIRE_PROTOCOL = "iacucProtocolExpire";
    public static final String EXPIRE_UNAVAILABLE_PROTOCOL = "iacucProtocolExpireUnavailable";
    public static final String SUSPEND_PROTOCOL = "iacucProtocolSuspend";
    public static final String SUSPEND_UNAVAILABLE_PROTOCOL = "iacucProtocolUnavailableSuspend";
    public static final String TERMINATE_PROTOCOL = "iacucProtocolTerminate";
    public static final String TERMINATE_UNAVAILBLE_PROTOCOL = "iacucProtocolTerminateUnavailable";

    protected static final Map<String, String> TASK_NAME_TO_ACTION_TYPE_MAP = new HashMap<String,String>();
    static {
        TASK_NAME_TO_ACTION_TYPE_MAP.put(EXPIRE_PROTOCOL, IacucProtocolActionType.EXPIRED);
        TASK_NAME_TO_ACTION_TYPE_MAP.put(EXPIRE_UNAVAILABLE_PROTOCOL, IacucProtocolActionType.EXPIRED);
        TASK_NAME_TO_ACTION_TYPE_MAP.put(SUSPEND_PROTOCOL, IacucProtocolActionType.SUSPENDED);
        TASK_NAME_TO_ACTION_TYPE_MAP.put(SUSPEND_UNAVAILABLE_PROTOCOL, IacucProtocolActionType.SUSPENDED);
        TASK_NAME_TO_ACTION_TYPE_MAP.put(TERMINATE_PROTOCOL, IacucProtocolActionType.TERMINATED);
        TASK_NAME_TO_ACTION_TYPE_MAP.put(TERMINATE_UNAVAILBLE_PROTOCOL, IacucProtocolActionType.TERMINATED);   
    }
    
    protected static final Map<String, String> TASK_NAME_TO_ROLE_NAME_MAP = new HashMap<String,String>();
    static {
        TASK_NAME_TO_ROLE_NAME_MAP.put(EXPIRE_PROTOCOL, PermissionConstants.PERFORM_IACUC_ACTIONS_ON_PROTO);
        TASK_NAME_TO_ROLE_NAME_MAP.put(EXPIRE_UNAVAILABLE_PROTOCOL, PermissionConstants.PERFORM_IACUC_ACTIONS_ON_PROTO);
        TASK_NAME_TO_ROLE_NAME_MAP.put(SUSPEND_PROTOCOL, PermissionConstants.PERFORM_IACUC_ACTIONS_ON_PROTO);
        TASK_NAME_TO_ROLE_NAME_MAP.put(SUSPEND_UNAVAILABLE_PROTOCOL, PermissionConstants.PERFORM_IACUC_ACTIONS_ON_PROTO);
        TASK_NAME_TO_ROLE_NAME_MAP.put(TERMINATE_PROTOCOL, PermissionConstants.PERFORM_IACUC_ACTIONS_ON_PROTO);
        TASK_NAME_TO_ROLE_NAME_MAP.put(TERMINATE_UNAVAILBLE_PROTOCOL, PermissionConstants.PERFORM_IACUC_ACTIONS_ON_PROTO);   
    }
    
    private static final String ERROR_MESSAGE = "Please set genericTaskName with one of the static strings in this class.";
    
    private String genericTaskName;

    
    @Override
    public boolean isAuthorized(String userId, ProtocolTaskBase task) {
        if (StringUtils.isEmpty(this.genericTaskName)) {
            this.genericTaskName = task.getTaskName();
        }
        return canExecuteAction(task.getProtocol(), convertGenericTaskNameToProtocolActionType()) 
            && hasPermission(userId, task.getProtocol(), convertGenericTaskNameToPermissionNameType());
    }
    
    /**
     * 
     * This method converts a Generic Task Name to a Protocol Action Type.
     * @return a ProtocolActionType String
     */
     @Override
     protected String convertGenericTaskNameToProtocolActionType() {
        if (TASK_NAME_TO_ACTION_TYPE_MAP.containsKey(this.genericTaskName)) {
            return TASK_NAME_TO_ACTION_TYPE_MAP.get(this.genericTaskName);
        } else {
            throw new IllegalArgumentException(ERROR_MESSAGE + "  this.genericTaskName: " + this.genericTaskName);
        }      
    }
     
    protected String convertGenericTaskNameToPermissionNameType() {
         if (TASK_NAME_TO_ROLE_NAME_MAP.containsKey(this.genericTaskName)) {
             return TASK_NAME_TO_ROLE_NAME_MAP.get(this.genericTaskName);
         } else {
             throw new IllegalArgumentException(ERROR_MESSAGE + "  this.genericTaskName: " + this.genericTaskName);
         }      
    }
    
    /**
     * 
     * This method sets the genericTaskName variable.  Please use the static strings in this class.
     * @param genericTaskName String.
     */
    @Override
    public void setGenericTaskName(String genericTaskName) {
        if (TASK_NAME_TO_ACTION_TYPE_MAP.containsKey(genericTaskName)) {
            this.genericTaskName = genericTaskName;
        } else {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }
}
