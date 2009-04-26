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
package org.kuali.kra.committee.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.Person;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.service.CommitteeAuthorizationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.kim.pojo.QualifiedRole;
import org.kuali.kra.kim.service.PersonService;
import org.kuali.kra.kim.service.QualifiedRoleService;
import org.kuali.kra.service.UnitAuthorizationService;

/**
 * The Committee Authorization Service Implementation.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class CommitteeAuthorizationServiceImpl implements CommitteeAuthorizationService {
    
    private static final String COMMITTEE_KEY = "kra.committee";
    
    private UnitAuthorizationService unitAuthorizationService;
    private PersonService kimPersonService;
    private QualifiedRoleService kimQualifiedRoleService;
    private org.kuali.kra.service.PersonService personService;

    /**
     * Set the Unit Authorization Service.  Injected by Spring.
     * @param unitAuthorizationService the Unit Authorization Service
     */
    public void setUnitAuthorizationService(UnitAuthorizationService unitAuthorizationService) {
        this.unitAuthorizationService = unitAuthorizationService;
    }
    
    /**
     * Set the KRA Person Service.  Injected by Spring.
     * @param personService the KRA Person Service
     */
    public void setPersonService(org.kuali.kra.service.PersonService personService) {
        this.personService = personService;
    }
    
    /**
     * Set the KIM Person Service.  Injected by Spring.
     * @param personService the KIM Person Service
     */
    public void setKimPersonService(PersonService personService) {
        this.kimPersonService = personService;
    }
    
    /**
     * Set the KIM Qualified Role Service.  Injected by Spring.
     * @param qualifiedRoleService the KIM Qualified Role Service
     */
    public void setKimQualifiedRoleService(QualifiedRoleService qualifiedRoleService) {
        this.kimQualifiedRoleService = qualifiedRoleService;
    }
    
    /**
     * @see org.kuali.kra.committee.service.CommitteeAuthorizationService#getUserNames(org.kuali.kra.committee.bo.Committee, java.lang.String)
     */
    public List<String> getUserNames(Committee committee, String roleName) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(COMMITTEE_KEY, committee.getId().toString());
        return kimQualifiedRoleService.getPersonUsernames(roleName, qualifiedRoleAttributes);
    }
   
    /**
     * @see org.kuali.kra.committee.service.CommitteeAuthorizationService#addRole(java.lang.String, java.lang.String, org.kuali.kra.committee.bo.Committee)
     */
    public void addRole(String username, String roleName, Committee committee) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(COMMITTEE_KEY, committee.getId().toString());
        kimPersonService.addQualifiedRole(username, roleName, qualifiedRoleAttributes);
    }
   
    /**
     * @see org.kuali.kra.committee.service.CommitteeAuthorizationService#removeRole(java.lang.String, java.lang.String, org.kuali.kra.committee.bo.Committee)
     */
    public void removeRole(String username, String roleName, Committee committee) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(COMMITTEE_KEY, committee.getId().toString());
        kimPersonService.removeQualifiedRole(username, roleName, qualifiedRoleAttributes);
    }
    
    /**
     * @see org.kuali.kra.committee.service.CommitteeAuthorizationService#hasPermission(java.lang.String, org.kuali.kra.committee.bo.Committee, java.lang.String)
     */
    public boolean hasPermission(String username, Committee committee, String permissionName) {
        boolean userHasPermission = false;
        if (isValidPerson(username)) {
            Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
            qualifiedRoleAttributes.put(COMMITTEE_KEY, committee.getId().toString());
            userHasPermission = kimPersonService.hasQualifiedPermission(username, Constants.KRA_NAMESPACE, permissionName, qualifiedRoleAttributes);
            if (!userHasPermission) {
                Person person = personService.getPersonByName(username);
                if (person != null) {
                    String unitNumber = person.getHomeUnit();
                    userHasPermission = unitAuthorizationService.hasPermission(username, unitNumber, permissionName);
                }
            }
        }
        return userHasPermission;
    }
   
    private boolean isValidPerson(String username) {
        return personService.isActiveByName(username);
    }
    
    /**
     * @see org.kuali.kra.committee.service.CommitteeAuthorizationService#hasRole(java.lang.String, org.kuali.kra.committee.bo.Committee, java.lang.String)
     */
    public boolean hasRole(String username, Committee committee, String roleName) {
        if (isValidPerson(username)) {
            Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
            qualifiedRoleAttributes.put(COMMITTEE_KEY, committee.getId().toString());
            return kimPersonService.hasQualifiedRole(username, roleName, qualifiedRoleAttributes);
        }
        return false;
    }
   
    /**
     * @see org.kuali.kra.committee.service.CommitteeAuthorizationService#getRoles(java.lang.String, org.kuali.kra.committee.bo.Committee)
     */
    public List<String> getRoles(String username, Committee committee) {
        List<String> roleNames = new ArrayList<String>();
        if (isValidPerson(username)) {
            String committeeNbr = committee.getId().toString();
            if (committeeNbr != null) {
                roleNames = getRoleNames(username, committeeNbr);
            }
        }
        return roleNames;
    }
    
    /*
     * Get the names of the roles for the user in the given committee.
     */
    private List<String> getRoleNames(String userName, String committeeNbr) {
        List<String> roleNames = new ArrayList<String>();
        List<QualifiedRole> roles = kimPersonService.getQualifiedRoles(userName);
        for (QualifiedRole role : roles) {
            Map<String, String> attrs = role.getQualifiedRoleAttributes();
            if (attrs.containsKey(COMMITTEE_KEY)) {
                String value = attrs.get(COMMITTEE_KEY);
                if (value.equals(committeeNbr)) {
                    roleNames.add(role.getRoleName());
                }
            }
        }
        return roleNames;
    }
    
    /**
     * @see org.kuali.kra.committee.service.CommitteeAuthorizationService#getPersonsInRole(org.kuali.kra.committee.bo.Committee, java.lang.String)
     */
    public List<Person> getPersonsInRole(Committee committee, String roleName) {
        List<Person> persons = new ArrayList<Person>();
        Map<String, String> qualifiedRoleAttrs = new HashMap<String, String>();
        qualifiedRoleAttrs.put(COMMITTEE_KEY, committee.getId().toString());
        List<String> usernames = kimQualifiedRoleService.getPersonUsernames(roleName, qualifiedRoleAttrs);
        for (String username : usernames) {
            Person person = personService.getPersonByName(username);
            if (person != null && person.getActive()) {
                persons.add(person);
            }
        }
        return persons;
    }
}
