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

import org.kuali.coeus.common.framework.auth.task.GenericTaskAuthorizer;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.actions.ProtocolActionType;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * This class is the authorizer for all the generic actions.
 */
public class GenericProtocolAuthorizer extends ProtocolAuthorizerBase implements GenericTaskAuthorizer {
    
    /**
     * close protocol generic action.
     */
    public static final String CLOSE_PROTOCOL = "protocolClose";
    /**
     * close protocol enrollment protocol generic action.
     */
    public static final String CLOSE_ENROLLMENT_PROTOCOL = "protocolCloseEnrollment";
    /**
     * expire protocol generic action.
     */
    public static final String EXPIRE_PROTOCOL = "protocolExpire";
    /**
     * allow data analysis only generic action.
     */
    public static final String PERMIT_DATA_ANALYSIS = "protocolPermitDataAnalysis";
    /**
     * reopen protocol generic action.
     */
    public static final String REOPEN_PROTOCOL = "protocolReopen";
    /**
     * suspend protocol generic action.
     */
    public static final String SUSPEND_PROTOCOL = "protocolSuspend";
    /**
     * suspend protocol by dsmb generic action.
     */
    public static final String SUSPEND_PROTOCOL_BY_DSMB = "protocolSuspendByDsmb";
    /**
     * terminate protocol generic action.
     */
    public static final String TERMINATE_PROTOCOL = "protocolTerminate";
    
    private static final Map<String, String> TASK_NAME_TO_ACTION_TYPE_MAP = new HashMap<String,String>();
    static {
        TASK_NAME_TO_ACTION_TYPE_MAP.put(CLOSE_PROTOCOL, ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED);
        TASK_NAME_TO_ACTION_TYPE_MAP.put(CLOSE_ENROLLMENT_PROTOCOL, ProtocolActionType.CLOSED_FOR_ENROLLMENT);
        TASK_NAME_TO_ACTION_TYPE_MAP.put(EXPIRE_PROTOCOL, ProtocolActionType.EXPIRED);
        TASK_NAME_TO_ACTION_TYPE_MAP.put(PERMIT_DATA_ANALYSIS, ProtocolActionType.DATA_ANALYSIS_ONLY);
        TASK_NAME_TO_ACTION_TYPE_MAP.put(REOPEN_PROTOCOL, ProtocolActionType.REOPEN_ENROLLMENT);
        TASK_NAME_TO_ACTION_TYPE_MAP.put(SUSPEND_PROTOCOL, ProtocolActionType.SUSPENDED);
        TASK_NAME_TO_ACTION_TYPE_MAP.put(SUSPEND_PROTOCOL_BY_DSMB, ProtocolActionType.SUSPENDED_BY_DSMB);
        TASK_NAME_TO_ACTION_TYPE_MAP.put(TERMINATE_PROTOCOL, ProtocolActionType.TERMINATED);     
    }
    
    private static final String ERROR_MESSAGE = "Please set genericTaskName with one of the static strings in this class.";
    
    private String genericTaskName;

    
    @Override
    public boolean isAuthorized(String userId, ProtocolTaskBase task) {
        return canExecuteAction(task.getProtocol(), convertGenericTaskNameToProtocolActionType()) 
            && hasPermission(userId, task.getProtocol(), PermissionConstants.MAINTAIN_PROTOCOL_SUBMISSIONS);
    }
    
    /**
     * 
     * This method converts a Generic Task Name to a ProtocolBase Action Type.
     * @return a ProtocolActionType String
     */
     protected String convertGenericTaskNameToProtocolActionType() {
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
    @Override
    public void setGenericTaskName(String genericTaskName) {
        if (TASK_NAME_TO_ACTION_TYPE_MAP.containsKey(genericTaskName)) {
            this.genericTaskName = genericTaskName;
        } else {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }
}
