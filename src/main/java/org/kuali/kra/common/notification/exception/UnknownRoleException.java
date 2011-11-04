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
package org.kuali.kra.common.notification.exception;

/**
 * This exception is thrown if the given role id is not known to the given context.
 */
public class UnknownRoleException extends Exception {

    private static final long serialVersionUID = -1925770520412550327L;
    
    private final String roleId;
    private final String context;
    
    /**
     * Constructs an Unknown Role exception.
     * 
     * @param roleId
     * @param context
     */
    public UnknownRoleException(final String roleId, final String context) {
        super("Role " + roleId + " not recognized for context " + context);
        this.roleId = roleId;
        this.context = context;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getContext() {
        return context;
    }

}