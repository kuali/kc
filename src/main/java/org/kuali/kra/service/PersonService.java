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
package org.kuali.kra.service;

import java.util.Collection;

import org.kuali.kra.bo.Person;

/**
 * The Person Service provides methods for obtaining Person business objects.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface PersonService {
    
    /**
     * Gets the full name of a person.  NOTE: Used by DWR.
     * @param username the person's username
     * @return the person's full name
     */
    public String getPersonFullname(String username);
    
    /**
     * Get a person based upon its unique user id (personId).
     * 
     * @param userId the person's unique user id.
     * @return the person or null if not found.
     */
    public Person getPerson(String userId);
    
    /**
     * Get the persons in a unit.
     * 
     * @param unitNumber the unit's number
     * @return all of the persons in the given unit
     */
    public Collection<Person> getPersonsInUnit(String unitNumber);

    /**
     * Get a person based upon the person's unique username.
     * @param username the person's username
     * @return the person or null if not found
     */
    public Person getPersonByName(String username);
}
