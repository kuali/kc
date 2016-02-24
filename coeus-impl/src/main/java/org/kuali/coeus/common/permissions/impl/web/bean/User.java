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

import org.kuali.coeus.common.framework.person.KcPerson;

import java.util.ArrayList;
import java.util.List;

/**
 * A User is a mapping of a single Person to a list of Roles.  It
 * has two purposes.  First, it is used by the Users panel on the
 * Permissions tab web page.  Secondly, it is used by the Business
 * Rules, e.g. to verify that we don't add a duplicate user, etc.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class User {
    
    private KcPerson person;
    private List<Role> roles = new ArrayList<Role>();
    
    public User(KcPerson person) {
        this.person = person;
    }

    public KcPerson getPerson() { 
        return person;
    }
    
    public List<Role> getRoles() {
        return roles;
    }
    
    public void addRole(Role role) {
        roles.add(role);
    }
}
