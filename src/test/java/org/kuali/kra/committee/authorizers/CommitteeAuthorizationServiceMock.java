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
package org.kuali.kra.committee.authorizers;

import java.util.List;

import org.kuali.kra.bo.Person;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.service.CommitteeAuthorizationService;

public class CommitteeAuthorizationServiceMock implements CommitteeAuthorizationService {

    private boolean hasPermission;
    
    public CommitteeAuthorizationServiceMock(boolean hasPermission) {
        this.hasPermission = hasPermission;
    }
    
    public void addRole(String username, String roleName, Committee committee) {
   
    }

    public List<Person> getPersonsInRole(Committee committee, String roleName) {
        return null;
    }

    public List<String> getRoles(String username, Committee committee) {
        return null;
    }

    public List<String> getUserNames(Committee committee, String roleName) {
        return null;
    }

    public boolean hasPermission(String username, Committee committee, String permissionName) {
        return hasPermission;
    }

    public boolean hasRole(String username, Committee committee, String roleName) {
        return false;
    }

    public void removeRole(String username, String roleName, Committee committee) {
    
    }
}
