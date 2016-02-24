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
package org.kuali.coeus.common.impl.person;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.framework.multicampus.MultiCampusConstants;
import org.kuali.coeus.common.framework.multicampus.MultiCampusIdentityService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.entity.EntityContract;
import org.kuali.rice.krad.lookup.CollectionIncomplete;
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
        List<KcPerson> persons = new ArrayList<>();
        if (people instanceof CollectionIncomplete) {
            persons = new CollectionIncomplete<>(new ArrayList<>(),((CollectionIncomplete) people).getActualSizeIfTruncated());
        }
        for (Person person : people) {
            if (person.getEntityTypeCode().equals(KimConstants.EntityTypes.PERSON)) {
                persons.add(KcPerson.fromPersonId(person.getPrincipalId()));
            }
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
