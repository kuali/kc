/*
 * Copyright 2007 The Kuali Foundation
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
package org.kuali.kra.kim.service;

import java.util.List;
import java.util.Map;

import org.kuali.kra.kim.pojo.Person;
import org.kuali.kra.kim.pojo.QualifiedRole;

/**
 * Service API for accessing KIM Person services.  This contract should be used by all 
 * Kuali software which needs to leverage identity management features that require fine-grained
 * Person attributes. 
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public interface PersonService {
    /**
     * KIM Person service API method that determines if a given user is authorized for a given
     * permission.
     * 
     * @param   personUserName       user name identifying a unique KIM Person
     * @param   namespaceName        name identifying a unique namespace
     * @param   permissionName       name identifying a unique permission
     * @return                       boolean indicating if Person is authorized
     * 
     */
    public boolean hasPermission(String personUserName, String namespaceName, String permissionName);
    /**
     * KIM Person service API method that determines if a given user is member of a given
     * group.
     * 
     * @param   personUserName       User name identifying a unique KIM Person
     * @param   groupName            name identifying a unique Group
     * @return                       boolean indicating if Person is member of Group
     * 
     */
    public boolean isMemberOfGroup(String personUserName, String groupName);
    /**
     * KIM Person service API method that determines if a given user possesses all given Person
     * attributes.
     * 
     * @param   personUserName       user name identifying a unique KIM Person
     * @param   namespaceName        name identifying a unique namespace
     * @param   personAttributes     Map<String, String> of role attribute name/value pairs
     *                               to match a Person
     * @return                       boolean indicating if Person possesses all given attributes
     * 
     */
    public boolean hasAttributes(String personUserName, String namespaceName, Map<String, String> personAttributes);
    /**
     * KIM Person service API method that determines if a given user has been assigned to a given
     * role.
     * 
     * @param   personUserName       user name identifying a unique KIM Person
     * @param   roleName             name identifying a unique Role
     * @return                       boolean indicating if Person has been assigned to Role
     * 
     */
    public boolean hasRole(String personUserName, String roleName);
    
    /**
     * Add a person to a role.
     * 
     * @param   personUserName      user name identifying a unique KIM Person
     * @param   roleName            name identifying a unique Role
     */
    public void addRole(String personUserName, String roleName);
    
    /**
     * Remove a person From a role.
     * 
     * @param   personUserName      user name identifying a unique KIM Person
     * @param   roleName            name identifying a unique Role
     */
    public void removeRole(String personUserName, String roleName);
    
    /**
     * KIM Person service API method that determines if a given user possesses all given Role
     * attributes.
     * 
     * @param   personUserName       user name identifying a unique KIM Person
     * @param   roleName             name identifying a unique Role
     * @param   personAttributes     Map<String, String> of role attribute name/value pairs
     *                               to qualify a Person
     * @return                       boolean indicating if Person possesses all given Role attributes
     * 
     */
    public boolean hasQualifiedRole(String personUserName, String roleName, 
	    Map<String, String> personAttributes);
    
    /**
     * Get the Qualified Roles that a Person is in.
     * 
     * @param   personUserName      user name identifying a unique KIM Person
     * @return                      the list of Qualified Roles
     */
    public List<QualifiedRole> getQualifiedRoles(String personUserName);
    
    /**
     * This method...
     * @param personUserName
     * @param roleName
     * @param qualifiedRoleAttributes
     */
    public void addQualifiedRole(String personUserName, String roleName, Map<String, String> qualifiedRoleAttributes);
    /**
     * This method...
     * @param personUserName
     * @param roleName
     * @param qualifiedRoleAttributes
     */
    public void removeQualifiedRole(String personUserName, String roleName, Map<String, String> qualifiedRoleAttributes);
    /**
     * KIM Person service API method that determines if a given user possesses all given Role
     * attributes.
     * 
     * @param   personUserName       User name identifying a unique KIM Person
     * @param   namespaceName        name identifying a unique namespace
     * @param   attributeName        Name of attribute 
     * @return                       String value associated with attribute
     * 
     */
    public String getAttributeValue(String personUserName, String namespaceName, String attributeName);
    /**
     * KIM Person service API method that returns all Person objects matching all given Person
     * attributes.
     * 
     * @param   namespaceName        name identifying a unique namespace
     * @param   personAttributes     Map<String, String> of role attribute name/value pairs
     *                               to qualify a Person
     * @return                       boolean indicating if Person possesses all given Role attributes
     * 
     */
    public List<Person> getPersons(String namespaceName, Map<String, String> personAttributes);
    /**
     * KIM Person service API method that returns associated List of usernames for all Person objects
     * matching all given Person attributes.
     * 
     * @param   namespaceName        name identifying a unique namespace
     * @param   personAttributes     Map<String, String> of role attribute name/value pairs
     *                               to qualify a Person
     * @return                       boolean indicating if Person possesses all given Role attributes
     * 
     */
    public List<String> getPersonUserNames(String namespaceName, Map<String, String> personAttributes);
    /**
     * KIM Person service API method that determines if a Person possesses a given permission and
     * set of role attributes.
     * 
     * @param   personUserName           User name identifying a unique KIM Person
     * @param   namespaceName            name identifying a unique namespace
     * @param   permissionName           name identifying a unique permission
     * @param   qualifiedRoleAttributes  Map<String, String> of role attribute name/value pairs
     *                                   to qualify a Person
     * @return                           boolean indicating if Person possesses the permission and 
     *                                   all given Role attributes
     * 
     */
    public boolean hasQualifiedPermission(String personUserName, String namespaceName, String permissionName, 
	    Map<String, String> qualifiedRoleAttributes);
}
