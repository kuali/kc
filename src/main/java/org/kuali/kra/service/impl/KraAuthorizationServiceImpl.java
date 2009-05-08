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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.common.permissions.Permissionable;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.kim.pojo.QualifiedRole;
import org.kuali.kra.kim.service.PersonService;
import org.kuali.kra.kim.service.QualifiedRoleService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.UnitAuthorizationService;

/**
 * The Kra Authorization Service Implementation.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class KraAuthorizationServiceImpl implements KraAuthorizationService {
    
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
     * @see org.kuali.kra.award.service.KraAuthorizationService#getUserNames(org.kuali.kra.common.permissions.Permissionable, java.lang.String)
     */
    public List<String> getUserNames(Permissionable permissionable, String roleName) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(permissionable.getDocumentKey(), permissionable.getDocumentNumberForPermission());
        return kimQualifiedRoleService.getPersonUsernames(roleName, qualifiedRoleAttributes);
    }
    
    /**
     * @see org.kuali.kra.award.service.KraAuthorizationService#addRole(java.lang.String, java.lang.String, org.kuali.kra.common.permissions.Permissionable)
     */
    public void addRole(String username, String roleName, Permissionable permissionable) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(permissionable.getDocumentKey(), permissionable.getDocumentNumberForPermission());
        kimPersonService.addQualifiedRole(username, roleName, qualifiedRoleAttributes);
    }
    
    /**
     * @see org.kuali.kra.award.service.KraAuthorizationService#removeRole(java.lang.String, java.lang.String, org.kuali.kra.common.permissions.Permissionable)
     */
    public void removeRole(String username, String roleName, Permissionable permissionable) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(permissionable.getDocumentKey(), permissionable.getDocumentNumberForPermission());
        kimPersonService.removeQualifiedRole(username, roleName, qualifiedRoleAttributes);
    }
    
    /**
     * @see org.kuali.kra.award.service.KraAuthorizationService#hasPermission(java.lang.String, org.kuali.kra.common.permissions.Permissionable, java.lang.String)
     */
    public boolean hasPermission(String username, Permissionable permissionable, String permissionName) {
        boolean userHasPermission = false;
        if (isValidPerson(username)) {
            Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
            qualifiedRoleAttributes.put(permissionable.getDocumentKey(), permissionable.getDocumentNumberForPermission());
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
     * @see org.kuali.kra.award.service.KraAuthorizationService#hasRole(java.lang.String, org.kuali.kra.common.permissions.Permissionable, java.lang.String)
     */
    public boolean hasRole(String username, Permissionable permissionable, String roleName) {
        if (isValidPerson(username)) {
            Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
            qualifiedRoleAttributes.put(permissionable.getDocumentKey(), permissionable.getDocumentNumberForPermission());
            return kimPersonService.hasQualifiedRole(username, roleName, qualifiedRoleAttributes);
        }
        return false;
    }
    
    /**
     * @see org.kuali.kra.award.service.KraAuthorizationService#getRoles(java.lang.String, org.kuali.kra.common.permissions.Permissionable)
     */
    public List<String> getRoles(String username, Permissionable permissionable) {
        List<String> roleNames = new ArrayList<String>();
        if (isValidPerson(username)) {
            String awardNbr = permissionable.getDocumentNumberForPermission();
            if (awardNbr != null) {
                List<QualifiedRole> roles = kimPersonService.getQualifiedRoles(username);
                for (QualifiedRole role : roles) {
                    Map<String, String> attrs = role.getQualifiedRoleAttributes();
                    if (attrs.containsKey(permissionable.getDocumentKey())) {
                        String value = attrs.get(permissionable.getDocumentKey());
                        if (value.equals(awardNbr)) {
                            roleNames.add(role.getRoleName());
                        }
                    }
                }
            }
        }
        return roleNames;
    }
    
    /**
     * @see org.kuali.kra.award.service.KraAuthorizationService#getPersonsInRole(org.kuali.kra.common.permissions.Permissionable, java.lang.String)
     */
    public List<Person> getPersonsInRole(Permissionable permissionable, String roleName) {
        List<Person> persons = new ArrayList<Person>();
        Map<String, String> qualifiedRoleAttrs = new HashMap<String, String>();
        qualifiedRoleAttrs.put(permissionable.getDocumentKey(), permissionable.getDocumentNumberForPermission());
        List<String> usernames = kimQualifiedRoleService.getPersonUsernames(roleName, qualifiedRoleAttrs);
        for (String username : usernames) {
            Person person = personService.getPersonByName(username);
            if (person != null && person.getActive()) {
                persons.add(person);
            }
        }
        return persons;
    }
    
    /**
     * @see org.kuali.kra.award.service.KraAuthorizationService#getAllRolePersons(org.kuali.kra.common.permissions.Permissionable)
     */
    public List<RolePersons> getAllRolePersons(Permissionable permissionable) {
        List<RolePersons> rolePersonsList = new ArrayList<RolePersons>();
        
        String[] roleNames = permissionable.getRoleNames();
        
        for (String roleName : roleNames) {
            List<String> usernames = getUserNames(permissionable, roleName);
            RolePersons rolePersons = new RolePersons();
            rolePersonsList.add(rolePersons);
 
            if(roleName.contains(RoleConstants.AGGREGATOR)) {
                rolePersons.setAggregator(usernames);
            } else if(roleName.contains(RoleConstants.VIEWER)) {
                rolePersons.setViewer(usernames); 
            } else if(roleName.contains(RoleConstants.NARRATIVE_WRITER)) {
                rolePersons.setNarrativewriter(usernames);
            } else if(roleName.contains(RoleConstants.BUDGET_CREATOR)) {
                rolePersons.setBudgetcreator(usernames);
            }
        }
        
        return rolePersonsList;
    }
    
    
}
