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
