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
package org.kuali.kra.award.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.service.AwardAuthorizationService;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.infrastructure.AwardRoleConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.kim.pojo.QualifiedRole;
import org.kuali.kra.kim.service.PersonService;
import org.kuali.kra.kim.service.QualifiedRoleService;
import org.kuali.kra.service.UnitAuthorizationService;

/**
 * The Award Authorization Service Implementation.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class AwardAuthorizationServiceImpl implements AwardAuthorizationService {
    
    private static final String AWARD_KEY = "kra.award";
    
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
     * @see org.kuali.kra.award.service.AwardAuthorizationService#getUserNames(org.kuali.kra.award.bo.Award, java.lang.String)
     */
    public List<String> getUserNames(Award award, String roleName) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(AWARD_KEY, award.getAwardNumber());
        return kimQualifiedRoleService.getPersonUsernames(roleName, qualifiedRoleAttributes);
    }
    
    /**
     * @see org.kuali.kra.award.service.AwardAuthorizationService#addRole(java.lang.String, java.lang.String, org.kuali.kra.award.bo.Award)
     */
    public void addRole(String username, String roleName, Award award) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(AWARD_KEY, award.getAwardNumber());
        kimPersonService.addQualifiedRole(username, roleName, qualifiedRoleAttributes);
    }
    
    /**
     * @see org.kuali.kra.award.service.AwardAuthorizationService#removeRole(java.lang.String, java.lang.String, org.kuali.kra.award.bo.Award)
     */
    public void removeRole(String username, String roleName, Award award) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(AWARD_KEY, award.getAwardNumber());
        kimPersonService.removeQualifiedRole(username, roleName, qualifiedRoleAttributes);
    }
    
    /**
     * @see org.kuali.kra.award.service.AwardAuthorizationService#hasPermission(java.lang.String, org.kuali.kra.award.bo.Award, java.lang.String)
     */
    public boolean hasPermission(String username, Award award, String permissionName) {
        boolean userHasPermission = false;
        if (isValidPerson(username)) {
            Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
            qualifiedRoleAttributes.put(AWARD_KEY, award.getAwardNumber());
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
     * @see org.kuali.kra.award.service.AwardAuthorizationService#hasRole(java.lang.String, org.kuali.kra.award.bo.Award, java.lang.String)
     */
    public boolean hasRole(String username, Award award, String roleName) {
        if (isValidPerson(username)) {
            Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
            qualifiedRoleAttributes.put(AWARD_KEY, award.getAwardNumber());
            return kimPersonService.hasQualifiedRole(username, roleName, qualifiedRoleAttributes);
        }
        return false;
    }
    
    /**
     * @see org.kuali.kra.award.service.AwardAuthorizationService#getRoles(java.lang.String, org.kuali.kra.award.bo.Award)
     */
    public List<String> getRoles(String username, Award award) {
        List<String> roleNames = new ArrayList<String>();
        if (isValidPerson(username)) {
            String awardNbr = award.getAwardNumber();
            if (awardNbr != null) {
                List<QualifiedRole> roles = kimPersonService.getQualifiedRoles(username);
                for (QualifiedRole role : roles) {
                    Map<String, String> attrs = role.getQualifiedRoleAttributes();
                    if (attrs.containsKey(AWARD_KEY)) {
                        String value = attrs.get(AWARD_KEY);
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
     * @see org.kuali.kra.award.service.AwardAuthorizationService#getPersonsInRole(org.kuali.kra.award.bo.Award, java.lang.String)
     */
    public List<Person> getPersonsInRole(Award award, String roleName) {
        List<Person> persons = new ArrayList<Person>();
        Map<String, String> qualifiedRoleAttrs = new HashMap<String, String>();
        qualifiedRoleAttrs.put(AWARD_KEY, award.getAwardNumber());
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
     * @see org.kuali.kra.award.service.AwardAuthorizationService#getAllRolePersons(org.kuali.kra.award.bo.Award)
     */
    public List<RolePersons> getAllRolePersons(Award award) {
        List<RolePersons> rolePersonsList = new ArrayList<RolePersons>();
      
        List<String> usernames = getUserNames(award, AwardRoleConstants.AWARD_AGGREGATOR.getAwardRole());
        RolePersons rolePersons = new RolePersons();
        rolePersons.setAggregator(usernames);
        rolePersonsList.add(rolePersons);
        
        usernames = getUserNames(award, AwardRoleConstants.AWARD_VIEWER.getAwardRole());
        rolePersons = new RolePersons();
        rolePersons.setViewer(usernames);
        rolePersonsList.add(rolePersons);

        return rolePersonsList;
    }
}
