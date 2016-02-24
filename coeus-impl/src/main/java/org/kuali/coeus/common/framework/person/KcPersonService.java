/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
