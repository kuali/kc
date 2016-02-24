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
package org.kuali.kra.irb.auth;

import org.kuali.coeus.common.framework.auth.task.GenericTaskAuthorizer;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.actions.ProtocolActionType;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * This class is the authorizer for all the generic actions.
 */
public class GenericProtocolAuthorizer extends ProtocolAuthorizer implements GenericTaskAuthorizer {
    
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
    public boolean isAuthorized(String userId, ProtocolTask task) {
        return canExecuteAction(task.getProtocol(), convertGenericTaskNameToProtocolActionType()) 
            && hasPermission(userId, task.getProtocol(), PermissionConstants.MAINTAIN_PROTOCOL_SUBMISSIONS);
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
    @Override
    public void setGenericTaskName(String genericTaskName) {
        if (TASK_NAME_TO_ACTION_TYPE_MAP.containsKey(genericTaskName)) {
            this.genericTaskName = genericTaskName;
        } else {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }
}
