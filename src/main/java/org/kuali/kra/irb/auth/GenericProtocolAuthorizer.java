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

import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.actions.ProtocolActionType;

/**
 * 
 * This class is the authorizer for all the generic actions.
 */
public class GenericProtocolAuthorizer extends ProtocolAuthorizer {
    
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
    
    private static final String ERROR_MESSAGE = "Please set genericTaskName with one of the static strings in this class.";
    
    private String genericTaskName;

    /** {@inheritDoc} */
    public boolean isAuthorized(String userId, ProtocolTask task) {
        return canExecuteAction(task.getProtocol(), convertGenericTaskNameToProtocolActionType()) 
            && hasPermission(userId, task.getProtocol(), PermissionConstants.MAINTAIN_PROTOCOL_SUBMISSIONS);
    }
    
    
    private String convertGenericTaskNameToProtocolActionType() {
        if (CLOSE_PROTOCOL.equals(this.genericTaskName)) {
            return ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED;
        } else if (CLOSE_ENROLLMENT_PROTOCOL.equals(this.genericTaskName)) {
            return ProtocolActionType.CLOSED_FOR_ENROLLMENT;
        } else if (EXPIRE_PROTOCOL.equals(this.genericTaskName)) {
            return ProtocolActionType.EXPIRED;
        } else if (PERMIT_DATA_ANALYSIS.equals(this.genericTaskName)) {
            return ProtocolActionType.DATA_ANALYSIS_ONLY;
        } else if (REOPEN_PROTOCOL.equals(this.genericTaskName)) {
            return ProtocolActionType.REOPEN_ENROLLMENT;
        } else if (SUSPEND_PROTOCOL.equals(this.genericTaskName)) {
            return ProtocolActionType.SUSPENDED;
        } else if (SUSPEND_PROTOCOL_BY_DSMB.equals(this.genericTaskName)) {
            return ProtocolActionType.SUSPENDED_BY_DSMB;
        } else if (TERMINATE_PROTOCOL.equals(this.genericTaskName)) {
            return ProtocolActionType.TERMINATED;
        } else {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        
    }
    
    /**
     * 
     * This method sets the genericTaskName variable.  Please use on the static strings in this class.
     * @param genericTaskName String.
     */
    public void setGenericTaskName(String genericTaskName) {
        if ( CLOSE_PROTOCOL.equals(genericTaskName)
                || CLOSE_ENROLLMENT_PROTOCOL.equals(genericTaskName)
                || EXPIRE_PROTOCOL.equals(genericTaskName)
                || PERMIT_DATA_ANALYSIS.equals(genericTaskName)
                || REOPEN_PROTOCOL.equals(genericTaskName)
                || SUSPEND_PROTOCOL.equals(genericTaskName)
                || SUSPEND_PROTOCOL_BY_DSMB.equals(genericTaskName)
                || TERMINATE_PROTOCOL.equals(genericTaskName)) {
            this.genericTaskName = genericTaskName;
        } else {
            throw new IllegalArgumentException(ERROR_MESSAGE); 
        }
    }
}