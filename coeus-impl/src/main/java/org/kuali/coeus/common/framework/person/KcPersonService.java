/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.coeus.common.framework.person;

import org.kuali.rice.kim.api.identity.Person;

import java.util.List;
import java.util.Map;

/**
 * Service for working with KcPerson objects.
 */
public interface KcPersonService {
    
    /**
     * Gets a KcPerson from a user name (kim principal name).
     * @param userName the user name
     * @return the KcPerson
     * @throws IllegalArgumentException if the userName is null or empty
     */
    KcPerson getKcPersonByUserName(String userName);
    
    /**
     * Gets a KcPerson from a person id (kim principal id).
     * @param personId the person id
     * @return the KcPerson
     * @throws IllegalArgumentException if the personId is null or empty
     */
    KcPerson getKcPersonByPersonId(String personId);
    
    
    void modifyFieldValues(final Map<String, String> fieldValues);
    
    List<KcPerson> createKcPersonsFromPeople(List<Person> people);
}
