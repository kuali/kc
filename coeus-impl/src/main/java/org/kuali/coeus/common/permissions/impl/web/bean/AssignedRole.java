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

import java.util.ArrayList;
import java.util.List;

/**
 * An AssignedRole is a mapping of a single role with the users who 
 * are assigned to that role.  This is used for the Assigned Roles
 * panel in the Permissions tab web page.  
 */
public class AssignedRole {
    
    private Role role;
    private List<String> userNames = new ArrayList<String>();
    private List<User> users = new ArrayList<User>();
    
    public AssignedRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public List<String> getUserNames() {
        return userNames;
    }
    
    /**
     * Add a userName to the list of users for the role.
     * The userName is added in alphabetical order.
     * @param userName the userName to add
     */
    public void add(User user) {
        int index = users.size();
        for (int i = 0; i < users.size(); i++) {
            if (user.getPerson().getLastName().compareTo(users.get(i).getPerson().getLastName())  < 0) {
                index = i;
                break;
            }
        }
        users.add(index, user);
        userNames.add(index, user.getPerson().getFullName());
    }
}
