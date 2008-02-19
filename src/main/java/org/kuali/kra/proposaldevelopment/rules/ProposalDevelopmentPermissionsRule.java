/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.proposaldevelopment.rules;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.proposaldevelopment.bo.ProposalUser;
import org.kuali.kra.proposaldevelopment.bo.ProposalUserEditRoles;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.PermissionsRule;
import org.kuali.kra.proposaldevelopment.web.bean.ProposalUserRoles;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.kra.service.PersonService;

/**
 * Business Rule to determine the legality of modifying the access
 * to a Proposal Development Document.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentPermissionsRule extends ResearchDocumentRuleBase implements PermissionsRule {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDevelopmentPermissionsRule.class);

    /**
     * @see org.kuali.kra.proposaldevelopment.rule.PermissionsRule#processAddProposalUserBusinessRules(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, java.util.List, org.kuali.kra.proposaldevelopment.bo.ProposalUser)
     */
    public boolean processAddProposalUserBusinessRules(ProposalDevelopmentDocument document, List<ProposalUserRoles> proposalUserRolesList, ProposalUser proposalUser) {
        boolean isValid = true;
       
        // The user must have permission to set user access to the proposal.
        
        if (!hasPermission(document, PermissionConstants.MAINTAIN_PROPOSAL_ACCESS)) {
            isValid = false;
            this.reportError(Constants.PERMISSION_USERS_PROPERTY_KEY, 
                             KeyConstants.ERROR_NO_PERMISSION);
        }
        else {
            
            // The given username must be valid, i.e. it must correspond
            // to a person in the database.
            
            if (!isValidUser(proposalUser.getUsername())) {
                isValid = false;
                this.reportError(Constants.PERMISSION_USERS_PROPERTY_KEY + ".username", 
                                 KeyConstants.ERROR_UNKNOWN_USERNAME);
            }
            
            // Don't add the same user to the list.  The "edit roles" button
            // must be used to modify roles for an existing user.
            
            else if (isDuplicate(proposalUser.getUsername(), proposalUserRolesList)) {
                isValid = false;
                this.reportError(Constants.PERMISSION_USERS_PROPERTY_KEY + ".username", 
                                 KeyConstants.ERROR_DUPLICATE_PROPOSAL_USER);
            }
        }
        
        return isValid;
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.rule.PermissionsRule#processDeleteProposalUserBusinessRules(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, java.util.List, int)
     */
    public boolean processDeleteProposalUserBusinessRules(ProposalDevelopmentDocument document, List<ProposalUserRoles> proposalUserRolesList, int index) {
        boolean isValid = true;
        
        // The user must have permission to set user access to the proposal.
        
        if (!hasPermission(document, PermissionConstants.MAINTAIN_PROPOSAL_ACCESS)) {
            isValid = false;
            this.reportError(Constants.PERMISSION_USERS_PROPERTY_KEY, 
                             KeyConstants.ERROR_NO_PERMISSION);
        } 
        else {
            
            // The user cannot delete the last Aggregator on a proposal.
            
            ProposalUserRoles userRoles = proposalUserRolesList.get(index);
            if (isLastAggregator(userRoles.getUsername(), proposalUserRolesList)) {
                isValid = false;
                this.reportError(Constants.PERMISSION_USERS_PROPERTY_KEY, 
                                 KeyConstants.ERROR_LAST_AGGREGATOR);
            }
        }
        
        return isValid;
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.rule.PermissionsRule#processEditProposalUserRolesBusinessRules(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, java.util.List, org.kuali.kra.proposaldevelopment.bo.ProposalUserEditRoles)
     */
    public boolean processEditProposalUserRolesBusinessRules(ProposalDevelopmentDocument document, List<ProposalUserRoles> proposalUserRolesList, ProposalUserEditRoles editRoles) {
        boolean isValid = true;
        
        // The user must have permission to set user access to the proposal.
        
        if (!hasPermission(document, PermissionConstants.MAINTAIN_PROPOSAL_ACCESS)) {
            isValid = false;
            this.reportError(Constants.EDIT_ROLES_PROPERTY_KEY, 
                             KeyConstants.ERROR_NO_PERMISSION);
        }
        else {
            
            // The Aggregator encompasses all of the other roles.  Therefore, if the
            // user selects the Aggregator role, don't allow any of the other roles
            // to be selected.
            
            if (editRoles.getAggregator() && (editRoles.getBudgetCreator() || editRoles.getNarrativeWriter() || editRoles.getViewer())) {
                isValid = false;
                this.reportError(Constants.EDIT_ROLES_PROPERTY_KEY, 
                                 KeyConstants.ERROR_AGGREGATOR_INCLUSIVE);
            }
            
            // The user cannot delete the last Aggregator on a proposal.
            
            else if (!editRoles.getAggregator() && isLastAggregator(editRoles.getUsername(), proposalUserRolesList)) {
                isValid = false;
                this.reportError(Constants.EDIT_ROLES_PROPERTY_KEY, 
                                 KeyConstants.ERROR_LAST_AGGREGATOR);
            }
        }
        return isValid;
    }

    /**
     * Is this a valid username?  The User must reside in the database.
     * @param username the username
     * @return true if valid; otherwise false
     */
    private boolean isValidUser(String username) {
        PersonService personService = KraServiceLocator.getService(PersonService.class);
        return personService.getPersonByName(username) != null;
    }

    /**
     * Is this a duplicate user?  In other words, has this user
     * already been added to the list of users who can access the
     * proposal?
     * @param username the user's username
     * @param proposalUserRolesList the list of user roles
     * @return true if the user is already in the list; otherwise false
     */
    private boolean isDuplicate(String username, List<ProposalUserRoles> proposalUserRolesList) {
        for (ProposalUserRoles userRoles : proposalUserRolesList) {
            if (StringUtils.equals(username, userRoles.getUsername())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Is this user the last Aggregator in the list?  There must always be at least
     * one user with the Aggregator role.  Any attemp to delete that user or remove
     * the Aggregator role from that user must be prevented.
     * @param username the user to ignore in the list
     * @param proposalUserRolesList the list of user roles
     * @return true if the user is the last Aggregator; otherwise false
     */
    private boolean isLastAggregator(String username, List<ProposalUserRoles> proposalUserRolesList) {
        for (ProposalUserRoles userRoles : proposalUserRolesList) {
            if (!StringUtils.equals(username, userRoles.getUsername())) {
                List<String> roleNames = userRoles.getRoleNames();
                for (String roleName : roleNames) {
                    if (StringUtils.equals(roleName, RoleConstants.AGGREGATOR)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
