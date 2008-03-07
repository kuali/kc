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
package org.kuali.kra.service.impl.mocks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Person;
import org.kuali.kra.service.PersonService;

/**
 * This is a Mock for the PersonService.  Functionality is
 * only being added on a as needed basis.
 */
public class MockPersonService implements PersonService {

    private List<Person> persons = new ArrayList<Person>();
    
    /**
     * Add a person into the mock for testing purposes.
     * @param username
     */
    public void addPerson(String username) {
        Person person = new Person();
        person.setUserName(username);
        persons.add(person);
    }
    
    /**
     * @see org.kuali.kra.service.PersonService#getPerson(java.lang.String)
     */
    public Person getPerson(String userId) {
        return null;
    }

    /**
     * @see org.kuali.kra.service.PersonService#getPersonByName(java.lang.String)
     */
    public Person getPersonByName(String username) {
        for (Person person : persons) {
            if (StringUtils.equals(person.getUserName(), username)) {
                return person;
            }
        }
        return null;
    }

    /**
     * @see org.kuali.kra.service.PersonService#getPersonFullname(java.lang.String)
     */
    public String getPersonFullname(String username) {
        return null;
    }

    /**
     * @see org.kuali.kra.service.PersonService#getPersonsInUnit(java.lang.String)
     */
    public Collection<Person> getPersonsInUnit(String unitNumber) {
        return null;
    }
}
