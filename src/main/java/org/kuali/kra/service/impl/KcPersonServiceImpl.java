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
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.MultiCampusIdentityService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kim.api.identity.entity.EntityContract;
import org.kuali.rice.kim.api.identity.principal.PrincipalContract;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Service for working with KcPerson objects.
 */
public class KcPersonServiceImpl implements KcPersonService {
    
    private IdentityService identityService;
    
    @SuppressWarnings("unchecked")
    private PersonService personService;
    
    private ParameterService parameterService;
    
    private MultiCampusIdentityService multiCampusIdentityService;
    
    /** {@inheritDoc} */
    public List<KcPerson> getKcPersons(final Map<String, String> fieldValues) {
        if (fieldValues == null) {
            throw new IllegalArgumentException("the fieldValues are null");
        }
        
        this.modifyFieldValues(fieldValues);
        
        final List<Person> people = personService.findPeople(fieldValues, true);
        
        return this.createKcPersonsFromPeople(people);
    }
    
    /**
     * Modifies field values so that different field keys can be used for a lookup.
     * @param fieldValues the field values to modify
     */
    public void modifyFieldValues(final Map<String, String> fieldValues) {
        boolean multiCampusEnabled = parameterService.getParameterValueAsBoolean(
                Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, Constants.PARAMETER_MULTI_CAMPUS_ENABLED);
        
        //convert username and kcpersonid to proper naming such the person service can use them
        if (StringUtils.isNotBlank(fieldValues.get("userName"))){
            String userNameSearchValue = fieldValues.get("userName");
            if (multiCampusEnabled) {
                String campusCode = fieldValues.get("campusCode");
                userNameSearchValue = this.multiCampusIdentityService.getMultiCampusPrincipalName(userNameSearchValue, campusCode);
            }
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
        KcPerson person = null;
        
        if (StringUtils.isEmpty(userName)) {
            throw new IllegalArgumentException("the userName is null or empty");
        }
        
        boolean multiCampusEnabled = parameterService.getParameterValueAsBoolean(
                Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, Constants.PARAMETER_MULTI_CAMPUS_ENABLED);
        
        if (multiCampusEnabled) {
            String campusCode = (String) GlobalVariables.getUserSession().retrieveObject(Constants.USER_CAMPUS_CODE_KEY);
            String multiCampusUserName = this.multiCampusIdentityService.getMultiCampusPrincipalName(userName, campusCode);
            EntityContract entity = this.identityService.getEntityByPrincipalName(multiCampusUserName);
            if (entity != null) {
                person = KcPerson.fromEntityAndUserName(entity, multiCampusUserName);
            }
        } else {
            EntityContract entity = this.identityService.getEntityByPrincipalName(userName);
            if (entity != null) {
                person = KcPerson.fromEntityAndUserName(entity, userName);
            }
        }

        return person;
    }
    
    /** {@inheritDoc} */
    public KcPerson getKcPersonByPersonId(final String personId) {
        if (StringUtils.isEmpty(personId)) {
            throw new IllegalArgumentException("the personId is null or empty");
        }
        
        return KcPerson.fromEntityAndPersonId(this.identityService.getEntityByPrincipalId(personId), personId);
    }
    
    /**
     * Creates a List of KcPersons from a list of KIM entities.
     * 
     * @param entities the list of entities
     * @return the list of Kc persons
     */
    protected List<KcPerson> createKcPersonsFrom(List<? extends EntityContract> entities) {
        List<KcPerson> persons = new ArrayList<KcPerson>();
        
        for (EntityContract entity : entities) {
            for (PrincipalContract principal : entity.getPrincipals()) {
                persons.add(KcPerson.fromEntityAndPersonId(entity, principal.getPrincipalId()));
            }
        }
        
        return persons;
    }
    
    public List<KcPerson> createKcPersonsFromPeople(List<Person> people) {
        List<KcPerson> persons = new ArrayList<KcPerson>();
        
        for (Person person : people) {
            persons.add(KcPerson.fromPersonId(person.getPrincipalId()));
            /*for (PrincipalContract principal : person.getPrincipals()) {
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

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    public void setMultiCampusIdentityService(MultiCampusIdentityService multiCampusIdentityService) {
        this.multiCampusIdentityService = multiCampusIdentityService;
    }
    
}
