/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
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
import org.kuali.kra.bo.Person;
import org.kuali.kra.dao.PersonDao;
import org.kuali.kra.service.PersonService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.springframework.transaction.annotation.Transactional;

public class PersonServiceImpl implements PersonService {
    
    private Map<String, String> userNameCache = new HashMap<String, String>();
    
    private BusinessObjectService businessObjectService;
    private PersonDao personDao;
    
    /**
     * Sets the businessObjectService attribute value.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
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
    
    /**
     * @see org.kuali.kra.service.PersonService#isActiveByName(java.lang.String)
     */
    public boolean isActiveByName(String username) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("userName", username);
        fieldValues.put("active", Boolean.TRUE);
        int cnt = businessObjectService.countMatching(Person.class, fieldValues);
        return cnt > 0;
    }

    // we have a problem if the username changes.
    /**
     * We need performance.  We can't keep querying the database for a 
     * @see org.kuali.kra.service.PersonService#getPersonUserName(java.lang.String)
     */
    @Transactional
    public String getPersonUserName(String userId) {
        return personDao.getUserName(userId);
    }
    
    /**
     * Get a username from the cache.
     * @param userId the user's id
     * @return the username or null if not found
     */
    private synchronized String getUserNameFromCache(String userId) {
        return userNameCache.get(userId);
    }
    
    /**
     * Add a username to the cache using the person's id as the key.
     * @param userId the user's id
     * @param userName the username
     */
    private synchronized void addUserNameToCache(String userId, String userName) {
        userNameCache.put(userId, userName);
    }
}
