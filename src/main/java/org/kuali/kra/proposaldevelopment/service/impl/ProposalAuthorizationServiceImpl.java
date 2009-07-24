/*
 * Copyright 2006-2009 The Kuali Foundation
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
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalAuthorizationServiceImpl.class);
    
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
        qualifiedRoleAttributes.put(Constants.KIM_QUAL_ATTR_PROPOSAL_KEY, doc.getDevelopmentProposal().getProposalNumber());
        return kimQualifiedRoleService.getPersonUsernames(roleName, qualifiedRoleAttributes);
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService#addRole(java.lang.String, java.lang.String, org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public void addRole(String username, String roleName, ProposalDevelopmentDocument doc) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(Constants.KIM_QUAL_ATTR_PROPOSAL_KEY, doc.getDevelopmentProposal().getProposalNumber());
        kimPersonService.addQualifiedRole(username, roleName, qualifiedRoleAttributes);
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService#removeRole(java.lang.String, java.lang.String, org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public void removeRole(String username, String roleName, ProposalDevelopmentDocument doc) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(Constants.KIM_QUAL_ATTR_PROPOSAL_KEY, doc.getDevelopmentProposal().getProposalNumber());
        kimPersonService.removeQualifiedRole(username, roleName, qualifiedRoleAttributes);
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService#hasPermission(java.lang.String, org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, java.lang.String)
     */
    public boolean hasPermission(String username, ProposalDevelopmentDocument doc, String permissionName) {
        boolean userHasPermission = false;
        if (isValidPerson(username)) {
            Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
            qualifiedRoleAttributes.put(Constants.KIM_QUAL_ATTR_PROPOSAL_KEY, doc.getDevelopmentProposal().getProposalNumber());
            userHasPermission = kimPersonService.hasQualifiedPermission(username, Constants.KRA_NAMESPACE, permissionName, qualifiedRoleAttributes);
            if (!userHasPermission) {
                String unitNumber = doc.getDevelopmentProposal().getOwnedByUnitNumber();
                userHasPermission = unitAuthorizationService.hasPermission(username, unitNumber, permissionName);
            }
        }
        return userHasPermission;
    }
   
    private boolean isValidPerson(String username) {
        return personService.isActiveByName(username);
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService#hasRole(java.lang.String, org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, java.lang.String)
     */
    public boolean hasRole(String username, ProposalDevelopmentDocument doc, String roleName) {
        boolean userHasPermission = false;
        if (isValidPerson(username)) {
            Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
            qualifiedRoleAttributes.put(Constants.KIM_QUAL_ATTR_PROPOSAL_KEY, doc.getDevelopmentProposal().getProposalNumber());
            return kimPersonService.hasQualifiedRole(username, roleName, qualifiedRoleAttributes);
        }
        return false;
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService#getRoles(java.lang.String, org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public List<String> getRoles(String username, ProposalDevelopmentDocument doc) {
        List<String> roleNames = new ArrayList<String>();
        if (isValidPerson(username)) {
            String proposalNbr = doc.getDevelopmentProposal().getProposalNumber();
            if (proposalNbr != null) {
                List<QualifiedRole> roles = kimPersonService.getQualifiedRoles(username);
                for (QualifiedRole role : roles) {
                    Map<String, String> attrs = role.getQualifiedRoleAttributes();
                    if (attrs.containsKey(Constants.KIM_QUAL_ATTR_PROPOSAL_KEY)) {
                        String value = attrs.get(Constants.KIM_QUAL_ATTR_PROPOSAL_KEY);
                        if (value.equals(proposalNbr)) {
                            roleNames.add(role.getRoleName());
                        }
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
        qualifiedRoleAttrs.put(Constants.KIM_QUAL_ATTR_PROPOSAL_KEY, doc.getDevelopmentProposal().getProposalNumber());
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
     * @see org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService#getAllRolePersons(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public List<RolePersons> getAllRolePersons(ProposalDevelopmentDocument doc) {
        String[] roleNames = { RoleConstants.AGGREGATOR, 
                RoleConstants.BUDGET_CREATOR, 
                RoleConstants.NARRATIVE_WRITER, 
                RoleConstants.VIEWER, 
                "approver"
        };
        List<RolePersons> rolePersonsList = new ArrayList<RolePersons>();
        for (String roleName : roleNames) {
            if(roleName==RoleConstants.AGGREGATOR)
            {
                Map<String, String> qualifiedRoleAttrs = new HashMap<String, String>(); 
                qualifiedRoleAttrs.put(Constants.KIM_QUAL_ATTR_PROPOSAL_KEY, doc.getDevelopmentProposal().getProposalNumber());
                List<String> usernames = kimQualifiedRoleService.getPersonUsernames(roleName, qualifiedRoleAttrs);
                RolePersons rolePersons = new RolePersons();

                rolePersons.setAggregator(usernames);
                rolePersonsList.add(rolePersons);
            }else if(roleName==RoleConstants.BUDGET_CREATOR)
            {
                Map<String, String> qualifiedRoleAttrs = new HashMap<String, String>(); 
                qualifiedRoleAttrs.put(Constants.KIM_QUAL_ATTR_PROPOSAL_KEY, doc.getDevelopmentProposal().getProposalNumber());
                List<String> usernames = kimQualifiedRoleService.getPersonUsernames(roleName, qualifiedRoleAttrs);
                RolePersons rolePersons = new RolePersons();

                rolePersons.setBudgetcreator(usernames);
                rolePersonsList.add(rolePersons);
            } else if(roleName==RoleConstants.NARRATIVE_WRITER)
            {
                Map<String, String> qualifiedRoleAttrs = new HashMap<String, String>(); 
                qualifiedRoleAttrs.put(Constants.KIM_QUAL_ATTR_PROPOSAL_KEY, doc.getDevelopmentProposal().getProposalNumber());
                List<String> usernames = kimQualifiedRoleService.getPersonUsernames(roleName, qualifiedRoleAttrs);
                RolePersons rolePersons = new RolePersons();

                rolePersons.setNarrativewriter(usernames);
                rolePersonsList.add(rolePersons);

            }else if(roleName==RoleConstants.VIEWER){
                Map<String, String> qualifiedRoleAttrs = new HashMap<String, String>(); 
                qualifiedRoleAttrs.put(Constants.KIM_QUAL_ATTR_PROPOSAL_KEY, doc.getDevelopmentProposal().getProposalNumber());
                List<String> usernames = kimQualifiedRoleService.getPersonUsernames(roleName, qualifiedRoleAttrs);
                RolePersons rolePersons = new RolePersons();
                rolePersons.setViewer(usernames);
                rolePersonsList.add(rolePersons);

            }                

        }

        return rolePersonsList;
    }
}
