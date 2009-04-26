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
package org.kuali.kra.common.permissions.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Person;

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

    private Person person;
    private List<RoleState> roleStates = new ArrayList<RoleState>();
    
    /**
     * Constructs a UserState.
     * @param person the person
     * @param roles all of the possible roles for a person
     */
    public UserState(Person person, List<Role> roles) {
        this.person = person;
        for (Role role : roles) {
            roleStates.add(new RoleState(role));
        }
    }
    
    public Person getPerson() {
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
