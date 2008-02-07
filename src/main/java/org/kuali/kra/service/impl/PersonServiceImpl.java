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
package org.kuali.kra.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.bo.Person;
import org.kuali.kra.service.PersonService;

public class PersonServiceImpl implements PersonService {
    
    private BusinessObjectService businessObjectService;
    
    /**
     * Sets the businessObjectService attribute value.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * @see org.kuali.kra.service.PersonService#getPersonFullname(java.lang.String)
     */
    public String getPersonFullname(String username) {
        String fullname = null;
        Person person = getPersonByName(username);
        if (person != null) {
            fullname = person.getFullName();
        }
        return fullname;
    }
    
    /**
     * @see org.kuali.kra.service.PersonService#getPerson(java.lang.String)
     */
    public Person getPerson(String userId) {
        Person person = null;

        if (StringUtils.isNotEmpty(userId)) {
            Map<String, Object> primaryKeys = new HashMap<String, Object>();
            primaryKeys.put("personId", userId);
            person = (Person) businessObjectService.findByPrimaryKey(Person.class, primaryKeys);
        }

        return person;
    }

    /**
     * @see org.kuali.kra.service.PersonService#getPersonsInUnit(java.lang.String)
     */
    public Collection<Person> getPersonsInUnit(String unitNumber) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("homeUnit", unitNumber);
        
        return (Collection<Person>) businessObjectService.findMatching(Person.class, fieldValues);
    }
    
    /**
     * @see org.kuali.kra.service.PersonService#getPersonByName(java.lang.String)
     */
    public Person getPersonByName(String username) {
        Person person = null;

        if (StringUtils.isNotEmpty(username)) {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("userName", username);
            Collection<Person> persons = businessObjectService.findMatching(Person.class, fieldValues);
            if (persons.size() == 1) {
                person = persons.iterator().next();
            }
        }

        return person;
    }
}
