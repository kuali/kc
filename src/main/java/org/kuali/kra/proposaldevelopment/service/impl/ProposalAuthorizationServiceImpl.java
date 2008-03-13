/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.kim.pojo.QualifiedRole;
import org.kuali.kra.kim.service.PersonService;
import org.kuali.kra.kim.service.QualifiedRoleService;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService;
import org.kuali.kra.service.UnitAuthorizationService;

/**
 * The Proposal Authorization Service Implementation.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalAuthorizationServiceImpl implements ProposalAuthorizationService {
    
    private static final String PROPOSAL_KEY = "kra.proposal";
    
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
     * @see org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService#getUserNames(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, java.lang.String)
     */
    public List<String> getUserNames(ProposalDevelopmentDocument doc, String roleName) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(PROPOSAL_KEY, doc.getProposalNumber());
        return kimQualifiedRoleService.getPersonUsernames(roleName, qualifiedRoleAttributes);
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService#addRole(java.lang.String, java.lang.String, org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public void addRole(String username, String roleName, ProposalDevelopmentDocument doc) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(PROPOSAL_KEY, doc.getProposalNumber());
        kimPersonService.addQualifiedRole(username, roleName, qualifiedRoleAttributes);
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService#removeRole(java.lang.String, java.lang.String, org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public void removeRole(String username, String roleName, ProposalDevelopmentDocument doc) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(PROPOSAL_KEY, doc.getProposalNumber());
        kimPersonService.removeQualifiedRole(username, roleName, qualifiedRoleAttributes);
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService#hasPermission(java.lang.String, org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, java.lang.String)
     */
    public boolean hasPermission(String username, ProposalDevelopmentDocument doc, String permissionName) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(PROPOSAL_KEY, doc.getProposalNumber());
        boolean userHasPermission = kimPersonService.hasQualifiedPermission(username, Constants.KRA_NAMESPACE, permissionName, qualifiedRoleAttributes);
        if (!userHasPermission) {
            String unitNumber = doc.getOwnedByUnitNumber();
            userHasPermission = unitAuthorizationService.hasPermission(username, unitNumber, permissionName);
        }
        return userHasPermission;
    }
   
    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService#hasRole(java.lang.String, org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, java.lang.String)
     */
    public boolean hasRole(String username, ProposalDevelopmentDocument doc, String roleName) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(PROPOSAL_KEY, doc.getProposalNumber());
        return kimPersonService.hasQualifiedRole(username, roleName, qualifiedRoleAttributes);
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService#getRoles(java.lang.String, org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public List<String> getRoles(String username, ProposalDevelopmentDocument doc) {
        List<String> roleNames = new ArrayList<String>();
        String proposalNbr = doc.getProposalNumber();
        if (proposalNbr != null) {
            List<QualifiedRole> roles = kimPersonService.getQualifiedRoles(username);
            for (QualifiedRole role : roles) {
                Map<String, String> attrs = role.getQualifiedRoleAttributes();
                if (attrs.containsKey(PROPOSAL_KEY)) {
                    String value = attrs.get(PROPOSAL_KEY);
                    if (value.equals(proposalNbr)) {
                        roleNames.add(role.getRoleName());
                    }
                }
            }
        }
        return roleNames;
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService#getPersonsInRole(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, java.lang.String)
     */
    public List<Person> getPersonsInRole(ProposalDevelopmentDocument doc, String roleName) {
        List<Person> persons = new ArrayList<Person>();
        Map<String, String> qualifiedRoleAttrs = new HashMap<String, String>();
        qualifiedRoleAttrs.put(PROPOSAL_KEY, doc.getProposalNumber());
        List<String> usernames = kimQualifiedRoleService.getPersonUsernames(roleName, qualifiedRoleAttrs);
        for (String username : usernames) {
            Person person = personService.getPersonByName(username);
            if (person != null) {
                persons.add(person);
            }
        }
        return persons;
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService#getAllRolePersons(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public List<RolePersons> getAllRolePersons(ProposalDevelopmentDocument doc) {
        String[] roleNames = { RoleConstants.AGGREGATOR, 
                               RoleConstants.BUDGET_CREATOR, 
                               RoleConstants.NARRATIVE_WRITER, 
                               RoleConstants.VIEWER, 
                               RoleConstants.UNASSIGNED };
        
        List<RolePersons> rolePersonsList = new ArrayList<RolePersons>();
        
        for (String roleName : roleNames) {
            List<Person> persons = getPersonsInRole(doc, roleName);
            RolePersons rolePersons = new RolePersons();
            rolePersons.setRoleName(roleName);
            rolePersons.setPersons(persons);
            rolePersonsList.add(rolePersons);
        }
        
        return rolePersonsList;
    }
}
