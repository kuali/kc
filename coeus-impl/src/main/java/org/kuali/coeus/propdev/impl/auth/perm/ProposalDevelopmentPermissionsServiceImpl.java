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
package org.kuali.coeus.propdev.impl.auth.perm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.propdev.impl.attachment.LegacyNarrativeService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.docperm.*;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.krad.service.KualiRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("proposalDevelopmentPermissionsService")
public class ProposalDevelopmentPermissionsServiceImpl implements ProposalDevelopmentPermissionsService {

    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentPermissionsServiceImpl.class);

    @Autowired
    @Qualifier("kcAuthorizationService")
    private KcAuthorizationService kraAuthorizationService;

    @Autowired
    @Qualifier("personService")
    private PersonService personService;

    @Autowired
    @Qualifier("kualiRuleService")
    private KualiRuleService kualiRuleService;

    @Autowired
    @Qualifier("legacyNarrativeService")
    private LegacyNarrativeService narrativeService;

    @Autowired
    @Qualifier("proposalRoleService")
    private ProposalRoleService proposalRoleService;

    @Override
    public List<ProposalUserRoles> getPermissions(ProposalDevelopmentDocument document) {
        Map<String, ProposalUserRoles> pendingRoleMap = new TreeMap<String, ProposalUserRoles>();

        // Add persons into the ProposalUserRolesList for each of the roles.
        Collection<Role> roles = proposalRoleService.getRolesForDisplay();
        for (Role role : roles) {
            List<String> personIds = kraAuthorizationService.getPrincipalsInRole(role.getName(), document);
            for (String personId : personIds) {
                Person person = personService.getPerson(personId);
                if (person != null) {
                    ProposalUserRoles proposalUserRole = pendingRoleMap.get(person.getPrincipalName());
                    if (proposalUserRole != null) {
                        proposalUserRole.addRoleName(role.getName());
                    } else {
                        ProposalUserRoles newRole = new ProposalUserRoles();
                        newRole.setUsername(person.getPrincipalName());
                        newRole.setFullname(getFullName(person.getFirstName(), person.getMiddleName(), person.getLastName()));
                        newRole.addRoleName(role.getName());
                        pendingRoleMap.put(person.getPrincipalName(), newRole);
                    }
                } else {
                    LOG.error("Attempting to get roles for null user role!");
                }
            }
        }
        return new ArrayList<>(pendingRoleMap.values());
    }

    public String getFullName(String first, String middle, String last) {
        final String middleName = middle != null ? middle + " " : "";

        return (first + " " + middleName + last).trim();
    }

    @Override
    public void savePermissions(ProposalDevelopmentDocument document, List<ProposalUserRoles> persistedUsers,
            List<ProposalUserRoles> newUsers) {
        List<ProposalUserRoles> proposalUsersToDelete = new ArrayList<ProposalUserRoles>(persistedUsers);
        proposalUsersToDelete.removeAll(newUsers);
        for (ProposalUserRoles proposalUser : proposalUsersToDelete) {
            deleteProposalUser(proposalUser, document);
        }
        
        List<ProposalUserRoles> proposalUsersToAdd = new ArrayList<ProposalUserRoles>(newUsers);
        proposalUsersToAdd.removeAll(persistedUsers);
        for (ProposalUserRoles proposalUser : proposalUsersToAdd) {
            saveProposalUser(proposalUser, document);
        }

    }

    public void deleteProposalUser(ProposalUserRoles proposalUser, ProposalDevelopmentDocument doc) {
        List<String> roleNames = proposalUser.getRoleNames();
        for (String roleName :roleNames) {
            kraAuthorizationService.removeDocumentLevelRole(getPersonId(proposalUser.getUsername()), roleName, doc);
        }
    }
    
    protected String getPersonId(String username) {
        Person person = personService.getPersonByPrincipalName(username);
        return person.getPrincipalId();
    }
    
    public void saveProposalUser(ProposalUserRoles proposalUser, ProposalDevelopmentDocument doc) {
        // Assign the user to the new roles for the proposal.
        
        List<String> roleNames = proposalUser.getRoleNames();
        for (String roleName :roleNames) {
            kraAuthorizationService.addDocumentLevelRole(getPersonId(proposalUser.getUsername()), roleName, doc);
        }
    }

    @Override
    public boolean validateAddPermissions(ProposalDevelopmentDocument document, List<ProposalUserRoles> proposalUserRolesList, ProposalUserRoles proposalUser){
        return getKualiRuleService().applyRules(new AddProposalUserEvent(document, proposalUserRolesList, proposalUser));
    }

    @Override
    public boolean validateDeletePermissions(ProposalDevelopmentDocument document, List<ProposalUserRoles> proposalUserRolesList, int index){
        return getKualiRuleService().applyRules(new DeleteProposalUserEvent(document, proposalUserRolesList, index));
    }

    @Override
    public boolean validateUpdatePermissions(ProposalDevelopmentDocument document, List<ProposalUserRoles> proposalUserRolesList, ProposalUserRoles proposalUser){
        return getKualiRuleService().applyRules(new EditUserProposalRolesEvent(document, proposalUserRolesList, proposalUser));
    }

    @Override
    public void processAddPermission(ProposalDevelopmentDocument document, ProposalUserRoles proposalUser) {
        getNarrativeService().addPerson(proposalUser.getUsername(), document, proposalUser.getRoleNames());
    }

    @Override
    public void processDeletePermission(ProposalDevelopmentDocument document, ProposalUserRoles proposalUser) {
        getNarrativeService().deletePerson(getPersonService().getPersonByPrincipalName(proposalUser.getUsername()).getPrincipalId(), document);
    }

    @Override
    public void processUpdatePermission(ProposalDevelopmentDocument document, ProposalUserRoles proposalUser) {
        getNarrativeService().readjustRights(getPersonId(proposalUser.getUsername()), document, proposalUser.getRoleNames());
    }

    public KcAuthorizationService getKraAuthorizationService() {
        return kraAuthorizationService;
    }

    public void setKraAuthorizationService(KcAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public KualiRuleService getKualiRuleService() {
        return kualiRuleService;
    }

    public void setKualiRuleService(KualiRuleService kualiRuleService) {
        this.kualiRuleService = kualiRuleService;
    }

    public LegacyNarrativeService getNarrativeService() {
        return narrativeService;
    }

    public void setNarrativeService(LegacyNarrativeService narrativeService) {
        this.narrativeService = narrativeService;
    }

    public ProposalRoleService getProposalRoleService() {
        return proposalRoleService;
    }

    public void setProposalRoleService(ProposalRoleService proposalRoleService) {
        this.proposalRoleService = proposalRoleService;
    }
}
