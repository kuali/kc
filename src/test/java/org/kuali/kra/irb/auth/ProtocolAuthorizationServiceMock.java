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
package org.kuali.kra.irb.auth;

import java.util.List;

import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.irb.Protocol;

public class ProtocolAuthorizationServiceMock implements ProtocolAuthorizationService {

    private boolean hasPermission;
    
    public ProtocolAuthorizationServiceMock(boolean hasPermission) {
        this.hasPermission = hasPermission;
    }
    
    public void addRole(String username, String roleName, Protocol protocol) {
     
    }

    public List<RolePersons> getAllRolePersons(Protocol protocol) {
        return null;
    }

    public List<Person> getPersonsInRole(Protocol protocol, String roleName) {
        return null;
    }

    public List<String> getRoles(String username, Protocol protocol) {
        return null;
    }

    public List<String> getUserNames(Protocol protocol, String roleName) {
        return null;
    }

    public boolean hasPermission(String username, Protocol protocol, String permissionName) {
        return hasPermission;
    }

    public boolean hasRole(String username, Protocol protocol, String roleName) {
        return false;
    }

    public void removeRole(String username, String roleName, Protocol protocol) {
        
    }
}
