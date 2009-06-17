/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.common.permissions.web.bean;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.bo.Person;

/**
 * A User is a mapping of a single Person to a list of Roles.  It
 * has two purposes.  First, it is used by the Users panel on the
 * Permissions tab web page.  Secondly, it is used by the Business
 * Rules, e.g. to verify that we don't add a duplicate user, etc.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class User {
    
    private Person person;
    private List<Role> roles = new ArrayList<Role>();
    
    public User(Person person) {
        this.person = person;
    }

    public Person getPerson() { 
        return person;
    }
    
    public List<Role> getRoles() {
        return roles;
    }
    
    public void addRole(Role role) {
        roles.add(role);
    }
}
