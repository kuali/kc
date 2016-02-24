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
package org.kuali.coeus.common.permissions.impl.web.bean;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A UserState maintains the current roles for a person as well as the
 * new roles.  First, it must be understood how the Permissions tab web page
 * works.  The end-user can add/delete users to/from documents as well as
 * change the roles that a user has.  Those changes, though, do not take
 * effect until a save is executed.  Therefore, we must maintain the current
 * state as saved in the database AND the new state as defined by the changes
 * made by the end-user.  When a save is executed, the current and new states 
 * are compared to determine the changes that are required to the database.
 * 
 * To accomplish the above, each UserState maintains a person and all of the
 * possible roles for that person.  Each possible role is kept in the RoleState 
 * which stores the current ("saved") and new states ("assigned") for that role.  
 * By storing all of the possible roles that can be assigned to the user, the 
 * process of updating the database becomes a simple task.  For example, if the 
 * new state is set to true and the current state is false, we know that the 
 * user must be assigned to that role.  Likewise, if the reverse is true, we know 
 * that the the user must be removed from that role.
 */
public class UserState implements Serializable {

    private KcPerson person;
    private List<RoleState> roleStates = new ArrayList<RoleState>();
    
    /**
     * Constructs a UserState.
     * @param person the person
     * @param roles all of the possible roles for a person
     */
    public UserState(KcPerson person, List<Role> roles) {
        this.person = person;
        for (Role role : roles) {
            roleStates.add(new RoleState(role));
        }
    }
    
    public KcPerson getPerson() {
        return person;
    }
    
    public List<RoleState> getRoleStates() {
        return roleStates;
    }
    
    /**
     * Set the saved state for a role.
     * @param roleName the name of the role
     * @param saved the new saved state
     */
    public void setSaved(String roleName, boolean saved) {
        for (RoleState roleState : roleStates) {
            if (StringUtils.equals(roleState.getRole().getName(), roleName)) {
                roleState.setSaved(saved);
            }
        }
    }
    
    /**
     * Set the assigned value of a given role.
     * @param roleName the name of the role
     * @param assigned the new assigned value
     */
    public void setAssigned(String roleName, boolean assigned) {
        for (RoleState roleState : roleStates) {
            if (StringUtils.equals(roleState.getRole().getName(), roleName)) {
                roleState.setAssigned(assigned);
            }
        }
    }

    /**
     * Is the user assigned to the given role?
     * @param roleName the name of the role
     * @return true if the user is assigned to the role; otherwise false
     */
    public boolean isRoleAssigned(String roleName) {
        for (RoleState roleState : roleStates) {
            if (StringUtils.equals(roleState.getRole().getName(), roleName)) {
                return roleState.isAssigned();
            }
        }
        return false;
    }

    /**
     * Clear the assignments.
     */
    public void clearAssignments() {
        for (RoleState roleState : roleStates) {
            roleState.setAssigned(false);
        }
    }

    /**
     * Is the user assigned to any role?
     * @return true if if the user is assigned to any role; otherwise false
     */
    public boolean hasAnyRole() {
        for (RoleState roleState : roleStates) {
            if (roleState.isAssigned()) {
                return true;
            }
        }
        return false;
    }
}
