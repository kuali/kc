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

import org.kuali.kra.kim.pojo.Group;
import org.kuali.kra.kim.pojo.Namespace;
import org.kuali.kra.kim.pojo.Person;
import org.kuali.kra.kim.pojo.Role;

/**
 * Service API for accessing KIM services.  This contract should be used by all Kuali software that 
 * needs to leverage identity management features. 
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public interface KIMService {
    /**
     * KIM service API method that returns a complete collection of Person objects for the application.
     * 
     * @return         List of Person objects for the application
     * 
     */
    public List<Person> getPersons();
    /**
     * KIM service API method that returns a complete collection of Person user names for the 
     * application.
     * 
     * @return         List of Person user names for the application
     * 
     */
    public List<String> getPersonUsernames();
    /**
     * KIM service API method that returns a complete collection of Role objects for the application.
     * 
     * @return         List of Role objects for the application
     * 
     */
    public List<Role> getRoles();
    /**
     * KIM service API method that returns a complete collection of Role names for the application.
     * 
     * @return         List of Role objects for the application
     * 
     */
    public List<String> getRoleNames();
    /**
     * KIM service API method that returns a collection of Group objects that satisfy
     * a given Role.
     *  
     * @return         List of Group names satisfying the role
     * 
     */
    public List<Group> getGroups();
    /**
     * KIM service API method that returns a collection of Group names that satisfy
     * a given Role.
     *   
     * @return         List of Group names satisfying the role
     * 
     */
    public List<String> getGroupNames();
    /**
     * KIM service API method that returns the complete collection of Namespace objects
     * 
     * @return         List of Namespace objects
     * 
     */
    public List<Namespace> getNamespaces();
    /**
     * KIM service API method that returns associated List of names for all Namespace objects
     * 
     * @return         List of Namespace objects (String)
     * 
     */
    public List<String> getNamespaceNames();
}
