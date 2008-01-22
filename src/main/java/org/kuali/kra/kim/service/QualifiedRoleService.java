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

import org.kuali.kra.kim.pojo.Group;
import org.kuali.kra.kim.pojo.Person;
import org.kuali.kra.kim.pojo.Role;

/**
 * Service API for accessing KIM QualifiedRole services.  This contract should be used by all 
 * Kuali software which needs to leverage identity management features that require fine-grained
 * QualifiedRole attributes. 
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public interface QualifiedRoleService {
    /**
     * KIM QualifiedRole service API method that returns the complete List of Person objects
     * that possess a given Role and matching role attributes
     * 
     * @param   roleName                 name identifying Role
     * @param   qualifiedRoleAttributes  Map<String, String> of role attribute name/value pairs
     *                                   to qualify a Person
     * @return                           List of Person objects that satisfy both Role and
     *                                   qualifying role attributes
     * 
     */
    public List<Person> getPersons(String roleName, Map<String, String> qualifiedRoleAttributes);
    /**
     * KIM QualifiedRole service API method that returns associated List of user names for
     * all Person objects that possess a given Role and matching role attributes
     * 
     * @param   roleName                 name identifying Role
     * @param   qualifiedRoleAttributes  Map<String, String> of role attribute name/value pairs
     *                                   to qualify a Person
     * @return                           associated List user names for Person objects that satisfy 
     *                                   both Role and qualifying role attributes
     * 
     */
    public List<String> getPersonUsernames(String roleName, Map<String, String> qualifiedRoleAttributes);
    /**
     * KIM QualifiedRole service API method that returns the complete List of Group objects
     * matching a given role and qualified role attributes.
     * 
     * @param   roleName                 name identifying Role
     * @param   qualifiedRoleAttributes  Map<String, String> of role attribute name/value pairs
     *                                   to qualify a group
     * @return                           List of all Group objects matching the role and 
     *                                   role attributes
     * 
     */
    public List<Group> getGroups(String roleName, Map<String, String> qualifiedRoleAttributes);
    /**
     * KIM QualifiedRole service API method that returns associated List of group names
     * for all Group objects matching a given role and qualified role attributes.
     * 
     * @param   roleName                 name identifying Role
     * @param   qualifiedRoleAttributes  Map<String, String> of role attribute name/value pairs
     *                                   to qualify a group
     * @return                           associated List of names for all Groups matching 
     *                                   the role and role attributes
     * 
     */
    public List<String> getGroupNames(String roleName, Map<String, String> qualifiedRoleAttributes);
    /**
     * KIM QualifiedRole service API method that returns unique List of all Role objects
     * matching qualified role attributes.
     * 
     * @param   qualifiedRoleAttributes  Map<String, String> of role attribute name/value pairs
     *                                   to qualify a group
     * @return                           unique set of all Role objects matching the role attributes
     * 
     */
    public List<Role> getRoles(Map<String, String> qualifiedRoleAttributes);
    /**
     * KIM QualifiedRole service API method that returns unique List of all Role names
     * matching qualified role attributes.
     * 
     * @param   qualifiedRoleAttributes  Map<String, String> of role attribute name/value pairs
     *                                   to qualify a group
     * @return                           unique set of all Role names matching the role attributes
     * 
     */
    public List<String> getRoleNames(Map<String, String> qualifiedRoleAttributes);

}
