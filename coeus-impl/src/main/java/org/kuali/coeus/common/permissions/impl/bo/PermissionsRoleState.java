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
package org.kuali.coeus.common.permissions.impl.bo;

import org.kuali.coeus.common.permissions.impl.web.bean.Role;
import org.kuali.rice.krad.bo.BusinessObjectBase;

/**
 * The PermissionsRoleState is used by the Permissions Edit Role 
 * feature.  The Edit Role form gives a list of role names along
 * with a checkbox for each currently selected role for the user. 
 * An instance of this class is used for each role.  That boolean
 * "state" attribute indicates whether the checkbox has been
 * selected or not. 
 */
@SuppressWarnings("serial")
public class PermissionsRoleState extends BusinessObjectBase {

    private Role role;
    private Boolean state = false;
    
    public PermissionsRoleState(Role role) {
        this.role = role;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Role getRole() {
        return role;
    }
    
    public void refresh() {
        // do nothing
    }
}
