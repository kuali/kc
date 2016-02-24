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
package org.kuali.coeus.common.notification.impl.exception;

/**
 * This exception is thrown if the given role id is not known to the given context.
 */
public class UnknownRoleException extends RuntimeException {

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
