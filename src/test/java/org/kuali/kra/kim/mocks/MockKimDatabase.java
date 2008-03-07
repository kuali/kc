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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.kim.pojo.PersonQualifiedRole;

/**
 * This is a base class for a Mock KIM Service.  It allows the
 * tester to add roles/permissions to be used by the actual service.
 */
public final class MockKimDatabase {
    
    /**
     * A role and its list of permissions.
     */
    public class MockRole {
        private String roleName;
        private List<String> permissions;
        
        public MockRole(String roleName, List<String> permissions) {
            this.roleName = roleName;
            this.permissions = permissions;
        }
        
        public String getRoleName() {
            return roleName;
        }
        
        public boolean hasPermission(String permission) {
            for (String p : permissions) {
                if (StringUtils.equals(p, permission)) { 
                    return true;
                }
            }
            return false;
        }
    }
    
    private List<MockRole> roles = new ArrayList<MockRole>();
    private List<PersonQualifiedRole> qualifiedRoles = new ArrayList<PersonQualifiedRole>();
    
    /**
     * Get all of the mock roles in the mock database.
     * @return
     */
    public List<MockRole> getRoles() {
        return roles;
    }
    
    /**
     * Get all of the qualified roles from the mock database.
     * @return
     */
    public List<PersonQualifiedRole> getQualifiedRoles() {
        return qualifiedRoles;
    }
    
    /**
     * Add a role and its permissions into the Mock database.
     * @param roleName
     * @param permissions
     */
    public void addRole(String roleName, List<String> permissions) {
        MockRole role = new MockRole(roleName, permissions);
        roles.add(role);
    }
    
    /**
     * Find a mock role based upon the role's name.
     * @param roleName
     * @return
     */
    public MockRole findRole(String roleName) {
        for (MockRole role : roles) {
            if (StringUtils.equals(roleName, role.getRoleName())) {
                return role;
            }
        }
        return null;
    }
    
    /**
     * Add a qualified role into the mock database.
     * @param personUserName
     * @param roleName
     * @param qualifiedRoleAttributes
     */
    public void addQualifiedRole(String personUserName, String roleName, Map<String, String> qualifiedRoleAttributes) {
        PersonQualifiedRole pr = new PersonQualifiedRole();
        pr.setUsername(personUserName);
        pr.setRoleName(roleName);
        pr.setQualifiedRoleAttributes(qualifiedRoleAttributes);
        qualifiedRoles.add(pr);
    }
    
    /**
     * Remove a qualified role from the mock database.
     * @param personUserName
     * @param roleName
     * @param qualifiedRoleAttributes
     */
    public void removeQualifiedRole(String personUserName, String roleName, Map<String, String> qualifiedRoleAttributes) {
        for (PersonQualifiedRole pr : getQualifiedRoles()) {
            if (StringUtils.equals(pr.getUsername(), personUserName) &&
                StringUtils.equals(pr.getRoleName(), roleName) &&
                partialMatch(qualifiedRoleAttributes, pr.getQualifiedRoleAttributes())) {
                
                    qualifiedRoles.remove(pr);
                    break;
            }
        }
    }

    /**
     * Determine if a person has a given qualified role.
     * @param personUserName
     * @param roleName
     * @param qualifiedRoleAttributes
     * @return
     */
    public boolean hasQualifiedRole(String personUserName, String roleName, Map<String, String> qualifiedRoleAttributes) {
        for (PersonQualifiedRole pr : getQualifiedRoles()) {
            if (StringUtils.equals(pr.getUsername(), personUserName) &&
                StringUtils.equals(pr.getRoleName(), roleName) &&
                partialMatch(qualifiedRoleAttributes, pr.getQualifiedRoleAttributes())) {
                
                    return true;
            }
        }
        return false;
    }
    
    /**
     * Is the attributes a subset of the qualifiedAttributes?
     * @param attributes
     * @param qualifiedAttributes
     * @return
     */
    private boolean partialMatch(Map<String, String> attributes, Map<String, String> qualifiedAttributes) {
        if (attributes != null) {
            Iterator<Entry<String, String>> iterator = attributes.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> entry = iterator.next();
                if (!contains(qualifiedAttributes, entry.getKey(), entry.getValue())) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Does the map of qualifiedAttributes contain the given attrName/attrValue pair?
     * @param qualifiedAttributes
     * @param attrName
     * @param attrValue
     * @return
     */
    private boolean contains(Map<String, String> qualifiedAttributes, String attrName, String attrValue) {
        Iterator<Entry<String, String>> iterator = qualifiedAttributes.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> entry = iterator.next();
            if (StringUtils.equals(entry.getKey(), attrName) &&
                StringUtils.equals(entry.getValue(), attrValue)) {
                return true;
            }
        }
        return false;
    }
}
