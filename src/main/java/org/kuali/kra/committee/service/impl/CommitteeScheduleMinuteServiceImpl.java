/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.committee.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.committee.service.CommitteeScheduleMinuteService;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kim.service.PersonService;

public class CommitteeScheduleMinuteServiceImpl implements CommitteeScheduleMinuteService {

    private PersonService<Person> personService;
    
    protected final Log LOG = LogFactory.getLog(getClass()); 
    private static final String PERSON_NOT_FOUND_FORMAT_STRING = "%s (not found)";
    
    
    
    public void setMinuteFullUserNames(List<CommitteeScheduleMinute> committeeScheduleMinutes) {
        for (CommitteeScheduleMinute minute : committeeScheduleMinutes) {
            if (LOG.isDebugEnabled()) { 
                LOG.debug(String.format("Looking up person for update user %s.", minute.getUpdateUser()));
                LOG.debug(String.format("Looking up person for update user %s.", minute.getCreateUser()));
            }
            
            
            Person person = personService.getPersonByPrincipalName(minute.getUpdateUser());
            minute.setUpdateUserFullName(person==null?String.format(PERSON_NOT_FOUND_FORMAT_STRING, minute.getUpdateUser()):person.getName());
            person = personService.getPersonByPrincipalName(minute.getCreateUser());
            minute.setCreateUserFullName(person==null?String.format(PERSON_NOT_FOUND_FORMAT_STRING, minute.getCreateUser()):person.getName());
        }
    }



    /**
     * Sets the personService attribute value.
     * @param personService The personService to set.
     */
    public void setPersonService(PersonService<Person> personService) {
        this.personService = personService;
    }
    
}
