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

import java.io.Serializable;

/**
 * Each RoleState corresponds to one specific user and one specific role.  
 * It stores the current state of the database, i.e. if "saved" is true, then
 * the user is assigned to this role in the database.  It also stores what
 * the end-user wants the role assignment to be in the "assigned" property.
 * If "assigned" is set to true, the end-user wants this user-role assignment
 * to exists in the database; otherwise it should not be in the database.
 * 
 * When the Permissions web page is viewed for the first time, the "saved"
 * and "assigned" flags will have the same value.  The end-user can then
 * edit the roles for the users which will result in the "assigned" flags
 * changing in value.  
 * 
 * When the document is saved, the "saved" and "assigned" flags are checked
 * to see what roles need to be added and/or removed.
 */
public class RoleState implements Serializable {

    private Role role;
    private boolean saved = false;
    private boolean assigned = false;
    
    public RoleState(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
    
    public boolean isSaved() {
        return saved;
    }

    /**
     * When save state is set, the assigned state must be given
     * the same value.  The saveState() is invoked under two scenarios.
     * The first is when the user-role assignments are read from the
     * database and the roleState is created.  The second is when the
     * database is updated with new user-role assignments.  In both
     * cases, the saved and assigned must be the same to reflect what
     * is in the database.  The end-user is then free to change the
     * assigned value to indicate what should be changed for the next
     * database save.
     * @param saved
     */
    public void setSaved(boolean saved) {
        this.saved = saved;
        this.assigned = saved;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }
    
    /**
     * Does this role have to be added to the database?
     * @return true if needs to be added; otherwise false
     */
    public boolean needsToBeAdded() {
        return !saved && assigned;
    }
    
    /**
     * Does this role have to be removed from the database?
     * @return true if needs to be removed; otherwise false
     */
    public boolean needsToBeRemoved() {
        return saved && !assigned;
    }
}
