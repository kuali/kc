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
package org.kuali.kra.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kim.bo.entity.KimEntity;
import org.kuali.rice.kim.bo.entity.KimPrincipal;
import org.kuali.rice.kim.bo.impl.PersonImpl;
import org.kuali.rice.kim.service.IdentityService;
import org.kuali.rice.kim.service.PersonService;

/**
 * Service for working with KcPerson objects.
 */
public class KcPersonServiceImpl implements KcPersonService {
    
    private IdentityService identityService;
    
    @SuppressWarnings("unchecked")
    private PersonService personService;
    
    /** {@inheritDoc} */
    public List<KcPerson> getKcPersons(final Map<String, String> fieldValues) {
        if (fieldValues == null) {
            throw new IllegalArgumentException("the fieldValues are null");
        }
        
        this.modifyFieldValues(fieldValues);
        
        final List<PersonImpl> people = personService.findPeople(fieldValues, true);
        
        return this.createKcPersonsFromPeople(people);
    }
    
    /**
     * Modifies field values so that different field keys can be used for a lookup.
     * @param fieldValues the field values to modify
     */
    protected void modifyFieldValues(final Map<String, String> fieldValues) {
        //convert username and kcpersonid to proper naming such the person service can use them
        if (StringUtils.isNotBlank(fieldValues.get("userName"))){
            String userNameSearchValue = fieldValues.get("userName");
            fieldValues.put("principalName", userNameSearchValue);  
        }
        
        if (StringUtils.isNotBlank(fieldValues.get("personId"))){
            String personIdSearchValue = fieldValues.get("personId");
            fieldValues.put("principalId", personIdSearchValue);
        }
        
        if (StringUtils.isNotBlank(fieldValues.get("officePhone"))){
            String officePhoneSerachValue = fieldValues.get("officePhone");
            fieldValues.put("phoneNumber", officePhoneSerachValue);
        }
        
        if (StringUtils.isNotBlank(fieldValues.get("organizationIdentifier"))){
            String primaryDeptCodeSearchValue = fieldValues.get("organizationIdentifier");
            fieldValues.put("primaryDepartmentCode", primaryDeptCodeSearchValue);
        } 
    }
    
    /** {@inheritDoc} */
    public KcPerson getKcPersonByUserName(final String userName) {
        if (StringUtils.isEmpty(userName)) {
            throw new IllegalArgumentException("the userName is null or empty");
        }
        KimEntity entity = this.identityService.getEntityInfoByPrincipalName(userName);
        if (entity == null) {
            return null;
        }
        return KcPerson.fromEntityAndUserName(entity, userName);
    }
    
    /** {@inheritDoc} */
    public KcPerson getKcPersonByPersonId(final String personId) {
        if (StringUtils.isEmpty(personId)) {
            throw new IllegalArgumentException("the personId is null or empty");
        }
        
        return KcPerson.fromEntityAndPersonId(this.identityService.getEntityInfoByPrincipalId(personId), personId);
    }
    
    /**
     * Creates a List of KcPersons from a list of KIM entities.
     * 
     * @param entities the list of entities
     * @return the list of Kc persons
     */
    protected List<KcPerson> createKcPersonsFrom(List<? extends KimEntity> entities) {
        List<KcPerson> persons = new ArrayList<KcPerson>();
        
        for (KimEntity entity : entities) {
            for (KimPrincipal principal : entity.getPrincipals()) {
                persons.add(KcPerson.fromEntityAndPersonId(entity, principal.getPrincipalId()));
            }
        }
        
        return persons;
    }
    
    protected List<KcPerson> createKcPersonsFromPeople(List<PersonImpl> people) {
        List<KcPerson> persons = new ArrayList<KcPerson>();
        
        for (PersonImpl person : people) {
            persons.add(KcPerson.fromPersonId(person.getPrincipalId()));
            /*for (KimPrincipal principal : person.getPrincipals()) {
                persons.add(KcPerson.fromEntityAndPersonId(entity, principal.getPrincipalId()));
            }*/
        }
        
        return persons;
    }

    /**
     * Sets the Identity Service.
     * @param identityService the Identity Service.
     */
    public void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }
    
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
    
}
