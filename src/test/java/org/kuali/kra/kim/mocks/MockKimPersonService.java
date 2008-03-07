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
import org.kuali.kra.kim.mocks.MockKimDatabase.MockRole;
import org.kuali.kra.kim.pojo.Person;
import org.kuali.kra.kim.pojo.PersonQualifiedRole;
import org.kuali.kra.kim.pojo.QualifiedRole;
import org.kuali.kra.kim.service.PersonService;

/**
 * A Mock for the KIM Person Service.
 */
public class MockKimPersonService extends MockKimService implements PersonService {
    
    /**
     * @see org.kuali.kra.kim.service.PersonService#addRole(java.lang.String, java.lang.String)
     */
    public void addRole(String personUserName, String roleName) {
        // TODO Auto-generated method stub
        
    }

    /**
     * @see org.kuali.kra.kim.service.PersonService#getAttributeValue(java.lang.String, java.lang.String, java.lang.String)
     */
    public String getAttributeValue(String personUserName, String namespaceName, String attributeName) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.kuali.kra.kim.service.PersonService#getPersonUserNames(java.lang.String, java.util.Map)
     */
    public List<String> getPersonUserNames(String namespaceName, Map<String, String> personAttributes) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.kuali.kra.kim.service.PersonService#getPersons(java.lang.String, java.util.Map)
     */
    public List<Person> getPersons(String namespaceName, Map<String, String> personAttributes) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.kuali.kra.kim.service.PersonService#getQualifiedRoles(java.lang.String)
     */
    public List<QualifiedRole> getQualifiedRoles(String personUserName) {
        List<QualifiedRole> qroles = new ArrayList<QualifiedRole>();
        for (PersonQualifiedRole pr : database.getQualifiedRoles()) {
            if (StringUtils.equals(pr.getUsername(), personUserName)) {
                QualifiedRole qr = new QualifiedRole();
                qr.setRoleName(pr.getRoleName());
                qr.setQualifiedRoleAttributes(pr.getQualifiedRoleAttributes());
                qroles.add(qr);
            }
        }
        return qroles;
    }

    /**
     * @see org.kuali.kra.kim.service.PersonService#hasAttributes(java.lang.String, java.lang.String, java.util.Map)
     */
    public boolean hasAttributes(String personUserName, String namespaceName, Map<String, String> personAttributes) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @see org.kuali.kra.kim.service.PersonService#hasPermission(java.lang.String, java.lang.String, java.lang.String)
     */
    public boolean hasPermission(String personUserName, String namespaceName, String permissionName) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @see org.kuali.kra.kim.service.PersonService#hasQualifiedPermission(java.lang.String, java.lang.String, java.lang.String, java.util.Map)
     */
    public boolean hasQualifiedPermission(String personUserName, String namespaceName, String permissionName,
            Map<String, String> qualifiedRoleAttributes) {
        
        for (PersonQualifiedRole pr : database.getQualifiedRoles()) {
            if (StringUtils.equals(pr.getUsername(), personUserName)) {
                if (partialMatch(qualifiedRoleAttributes, pr.getQualifiedRoleAttributes())) {
                    MockRole role = database.findRole(pr.getRoleName());
                    if (role != null && role.hasPermission(permissionName)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * @see org.kuali.kra.kim.service.PersonService#hasQualifiedRole(java.lang.String, java.lang.String, java.util.Map)
     */
    public boolean hasQualifiedRole(String personUserName, String roleName, Map<String, String> personAttributes) {
        return database.hasQualifiedRole(personUserName, roleName, personAttributes);
    }

    /**
     * @see org.kuali.kra.kim.service.PersonService#hasRole(java.lang.String, java.lang.String)
     */
    public boolean hasRole(String personUserName, String roleName) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @see org.kuali.kra.kim.service.PersonService#isMemberOfGroup(java.lang.String, java.lang.String)
     */
    public boolean isMemberOfGroup(String personUserName, String groupName) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @see org.kuali.kra.kim.service.PersonService#removeQualifiedRole(java.lang.String, java.lang.String, java.util.Map)
     */
    public void removeQualifiedRole(String personUserName, String roleName, Map<String, String> qualifiedRoleAttributes) {
        database.removeQualifiedRole(personUserName, roleName, qualifiedRoleAttributes);
    }

    /**
     * @see org.kuali.kra.kim.service.PersonService#removeRole(java.lang.String, java.lang.String)
     */
    public void removeRole(String personUserName, String roleName) {
        // TODO Auto-generated method stub
        
    }

    public void addQualifiedRole(String personUserName, String roleName, Map<String, String> qualifiedRoleAttributes) {
        database.addQualifiedRole(personUserName, roleName, qualifiedRoleAttributes);
    }
}
