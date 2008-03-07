/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.kim.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.kim.pojo.Group;
import org.kuali.kra.kim.pojo.Person;
import org.kuali.kra.kim.pojo.PersonQualifiedRole;
import org.kuali.kra.kim.pojo.Role;
import org.kuali.kra.kim.service.QualifiedRoleService;

/**
 * A Mock for the KIM Qualified Role Service.
 */
public class MockKimQualifiedRoleService extends MockKimService implements QualifiedRoleService {

    /**
     * @see org.kuali.kra.kim.service.QualifiedRoleService#getGroupNames(java.lang.String, java.util.Map)
     */
    public List<String> getGroupNames(String roleName, Map<String, String> qualifiedRoleAttributes) {
        return null;
    }

    /**
     * @see org.kuali.kra.kim.service.QualifiedRoleService#getGroups(java.lang.String, java.util.Map)
     */
    public List<Group> getGroups(String roleName, Map<String, String> qualifiedRoleAttributes) {
        return null;
    }

    /**
     * @see org.kuali.kra.kim.service.QualifiedRoleService#getPersonUsernames(java.lang.String, java.util.Map)
     */
    public List<String> getPersonUsernames(String roleName, Map<String, String> qualifiedRoleAttributes) {
        List<String> names = new ArrayList<String>();
        for (PersonQualifiedRole pr : database.getQualifiedRoles()) {
            if (StringUtils.equals(pr.getRoleName(), roleName)) {
                if (partialMatch(qualifiedRoleAttributes, pr.getQualifiedRoleAttributes())) {
                    names.add(pr.getUsername());
                }
            }
        }
        return names;
    }

    /**
     * @see org.kuali.kra.kim.service.QualifiedRoleService#getPersons(java.lang.String, java.util.Map)
     */
    public List<Person> getPersons(String roleName, Map<String, String> qualifiedRoleAttributes) {
        return null;
    }

    /**
     * @see org.kuali.kra.kim.service.QualifiedRoleService#getRoleNames(java.util.Map)
     */
    public List<String> getRoleNames(Map<String, String> qualifiedRoleAttributes) {
        return null;
    }

    /**
     * @see org.kuali.kra.kim.service.QualifiedRoleService#getRoles(java.util.Map)
     */
    public List<Role> getRoles(Map<String, String> qualifiedRoleAttributes) {
        return null;
    }
}
