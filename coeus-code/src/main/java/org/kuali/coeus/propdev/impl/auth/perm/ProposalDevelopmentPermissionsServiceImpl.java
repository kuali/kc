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

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.proposaldevelopment.web.bean.ProposalUserRoles;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;

import java.util.ArrayList;
import java.util.List;

public class ProposalDevelopmentPermissionsServiceImpl implements ProposalDevelopmentPermissionsService {

    private KcAuthorizationService kraAuthorizationService;
    
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
        KcAuthorizationService kraAuthorizationService = KcServiceLocator.getService(KcAuthorizationService.class);
        List<String> roleNames = proposalUser.getRoleNames();
        for (String roleName :roleNames) {
            kraAuthorizationService.removeRole(getPersonId(proposalUser.getUsername()), roleName, doc); 
        }
    }
    
    protected String getPersonId(String username) {
        PersonService personService = KcServiceLocator.getService(PersonService.class);
        Person person = personService.getPersonByPrincipalName(username);
        return person.getPrincipalId();
    }
    
    public void saveProposalUser(ProposalUserRoles proposalUser, ProposalDevelopmentDocument doc) {
        KcAuthorizationService kraAuthorizationService = KcServiceLocator.getService(KcAuthorizationService.class);
        // Assign the user to the new roles for the proposal.
        
        List<String> roleNames = proposalUser.getRoleNames();
        for (String roleName :roleNames) {
            kraAuthorizationService.addRole(getPersonId(proposalUser.getUsername()), roleName, doc);
        }
    }

    public KcAuthorizationService getKraAuthorizationService() {
        return kraAuthorizationService;
    }

    public void setKraAuthorizationService(KcAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }

}
