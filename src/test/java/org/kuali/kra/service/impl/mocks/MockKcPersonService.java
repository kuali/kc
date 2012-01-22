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
package org.kuali.kra.service.impl.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kim.api.identity.Person;

public class MockKcPersonService implements KcPersonService {
    
    private KcPerson person;
    
    private List<KcPerson> personList;

    public KcPerson getKcPersonByPersonId(String personId) {
        // TODO Auto-generated method stub
        return getPerson();
    }

    public KcPerson getKcPersonByUserName(String userName) {
        // TODO Auto-generated method stub
        return getPerson();
    }

    public List<KcPerson> getKcPersons(Map<String, String> fieldValues) {
        // TODO Auto-generated method stub
        return personList;
    }
    
    private KcPerson getPerson(){
        person = new KcPerson();
        person.setPersonId("1");
        return person;
    }
    
    public void addPerson(String personName){
        if(personList == null){
            personList = new ArrayList<KcPerson>();
        }
        personList.add(new KcPerson());
    }

    @Override
    public void modifyFieldValues(Map<String, String> fieldValues) {
        
    }

    @Override
    public List<KcPerson> createKcPersonsFromPeople(List<Person> people) {
        return null;
    }

    
}
