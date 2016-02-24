/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
