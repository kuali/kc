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
package org.kuali.coeus.common.impl.person;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.framework.multicampus.MultiCampusConstants;
import org.kuali.coeus.common.framework.multicampus.MultiCampusIdentityService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.entity.EntityContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service for working with KcPerson objects.
 */
@Component("kcPersonService")
public class KcPersonServiceImpl implements KcPersonService {
    
	@Autowired
	@Qualifier("identityService")
    private IdentityService identityService;
    
	@Autowired
	@Qualifier("parameterService")
    private ParameterService parameterService;
    
    @Autowired
    @Qualifier("multiCampusIdentityService")
    private MultiCampusIdentityService multiCampusIdentityService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    /**
     * Modifies field values so that different field keys can be used for a lookup.
     * @param fieldValues the field values to modify
     */
    public void modifyFieldValues(final Map<String, String> fieldValues) {
        boolean multiCampusEnabled = parameterService.getParameterValueAsBoolean(
                Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, MultiCampusConstants.PARAMETER_MULTI_CAMPUS_ENABLED);
        
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
    
    @Override
    public KcPerson getKcPersonByUserName(final String userName) {
        KcPerson person = null;
        
        if (StringUtils.isEmpty(userName)) {
            throw new IllegalArgumentException("the userName is null or empty");
        }
        
        boolean multiCampusEnabled = parameterService.getParameterValueAsBoolean(
                Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, MultiCampusConstants.PARAMETER_MULTI_CAMPUS_ENABLED);
        
        if (multiCampusEnabled) {
            String campusCode = (String) globalVariableService.getUserSession().retrieveObject(MultiCampusConstants.USER_CAMPUS_CODE_KEY);
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
    
    @Override
    public KcPerson getKcPersonByPersonId(final String personId) {
        if (StringUtils.isEmpty(personId)) {
            throw new IllegalArgumentException("the personId is null or empty");
        }
        
        return KcPerson.fromEntityAndPersonId(this.identityService.getEntityByPrincipalId(personId), personId);
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

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    public void setMultiCampusIdentityService(MultiCampusIdentityService multiCampusIdentityService) {
        this.multiCampusIdentityService = multiCampusIdentityService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public IdentityService getIdentityService() {
        return identityService;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public MultiCampusIdentityService getMultiCampusIdentityService() {
        return multiCampusIdentityService;
    }
}
