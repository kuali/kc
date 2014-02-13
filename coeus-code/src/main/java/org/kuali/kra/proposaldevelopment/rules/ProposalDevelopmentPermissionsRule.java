/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.rules;

import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.sys.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.infrastructure.*;
import org.kuali.kra.proposaldevelopment.bo.*;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.PermissionsRule;
import org.kuali.kra.proposaldevelopment.web.bean.ProposalUserRoles;
import org.kuali.kra.service.KcPersonService;

import java.util.List;

/**
 * Business Rule to determine the legality of modifying the access
 * to a Proposal Development Document.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentPermissionsRule extends KcTransactionalDocumentRuleBase implements PermissionsRule {
    

    private transient KcPersonService kcPersonService;
    
    /**
     * @see org.kuali.kra.proposaldevelopment.rule.PermissionsRule#processAddProposalUserBusinessRules(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, java.util.List, org.kuali.kra.proposaldevelopment.bo.ProposalUser)
     */
    public boolean processAddProposalUserBusinessRules(ProposalDevelopmentDocument document, List<ProposalUserRoles> proposalUserRolesList, ProposalUser proposalUser) {
        boolean isValid = true;
        
        KcWorkflowService kraWorkflowService = KcServiceLocator.getService(KcWorkflowService.class);
        
        //KRACOEUS-5530 Check if user name is Null or Empty
        
        if(StringUtils.isEmpty(proposalUser.getUsername())){
            isValid = false;
            this.reportError(Constants.PERMISSION_PROPOSAL_USERS_PROPERTY_KEY + ".username", 
                             KeyConstants.ERROR_EMPTY_USERNAME);
        }
       
        // The given username must be valid, i.e. it must correspond
        // to a person in the database.
            
        else if (!isValidUser(proposalUser.getUsername())) {
            isValid = false;
            this.reportError(Constants.PERMISSION_PROPOSAL_USERS_PROPERTY_KEY + ".username", 
                             KeyConstants.ERROR_UNKNOWN_USERNAME);
        }
            
        // Don't add the same user to the list.  The "edit roles" button
        // must be used to modify roles for an existing user.
            
        else if (isDuplicate(proposalUser.getUsername(), proposalUserRolesList)) {
            isValid = false;
            this.reportError(Constants.PERMISSION_PROPOSAL_USERS_PROPERTY_KEY + ".username", 
                             KeyConstants.ERROR_DUPLICATE_PROPOSAL_USER);
        }
        
        // Once workflowed, only Viewers can be added.
        else if (kraWorkflowService.isInWorkflow(document)) {
            if (!isAddingViewerOnly(proposalUser)) {
                isValid = false;
                this.reportError(Constants.PERMISSION_PROPOSAL_USERS_PROPERTY_KEY + ".roleName", KeyConstants.ERROR_PERMISSION_VIEWER_ONLY_KEY);
            }
        }
        
        return isValid;
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.rule.PermissionsRule#processDeleteProposalUserBusinessRules(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, java.util.List, int)
     */
    public boolean processDeleteProposalUserBusinessRules(ProposalDevelopmentDocument document, List<ProposalUserRoles> proposalUserRolesList, int index) {
        boolean isValid = true;
        KcWorkflowService kraWorkflowService = KcServiceLocator.getService(KcWorkflowService.class);
        ProposalUserRoles proposalUserRole = proposalUserRolesList.get(index);
        String username = proposalUserRole.getUsername();

        if (hasModifyNarrativePermission(username, proposalUserRolesList)) {
            isValid &= !testForLastModifier(username, document.getDevelopmentProposal().getNarratives(), Constants.PERMISSION_PROPOSAL_USERS_PROPERTY_KEY, "Proposal Attachment");
            isValid &= !testForLastModifier(username, document.getDevelopmentProposal().getInstituteAttachments(), Constants.PERMISSION_PROPOSAL_USERS_PROPERTY_KEY, "Internal Attachment");
        }
        
        // The user cannot delete the last Aggregator on a proposal.
            
        if (isLastAggregator(username, proposalUserRolesList)) {
            isValid = false;
            this.reportError(Constants.PERMISSION_PROPOSAL_USERS_PROPERTY_KEY, 
                             KeyConstants.ERROR_LAST_AGGREGATOR);
        } else if (isAggregatorInitiator(document, proposalUserRole)) {
            isValid = false;
            this.reportError(Constants.PERMISSION_PROPOSAL_USERS_PROPERTY_KEY, 
                    KeyConstants.ERROR_PROP_DEV_PERM_INITIATOR);            
        }
        
        // Can only add viewers after doc is workflowed
        else if (kraWorkflowService.isInWorkflow(document)) {
            isValid = false;
            this.reportError(Constants.EDIT_ROLES_PROPERTY_KEY, 
                    KeyConstants.ERROR_PERMISSION_VIEWER_ONLY_KEY);
        }
        
        return isValid;
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.rule.PermissionsRule#processEditProposalUserRolesBusinessRules(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, java.util.List, org.kuali.kra.proposaldevelopment.bo.ProposalUserEditRoles)
     */
    public boolean processEditProposalUserRolesBusinessRules(ProposalDevelopmentDocument document, List<ProposalUserRoles> proposalUserRolesList, ProposalUserEditRoles editRoles) {
        boolean isValid = true;
        KcWorkflowService kraWorkflowService = KcServiceLocator.getService(KcWorkflowService.class);
        String username = editRoles.getUsername();
        if (isRemovingModifyNarrativePermission(proposalUserRolesList, editRoles)) {
            isValid &= !testForLastModifier(username, document.getDevelopmentProposal().getNarratives(), Constants.EDIT_ROLES_PROPERTY_KEY, "Proposal Attachment");
            isValid &= !testForLastModifier(username, document.getDevelopmentProposal().getInstituteAttachments(), Constants.EDIT_ROLES_PROPERTY_KEY, "Internal Attachment");
        }

        // The Aggregator encompasses all of the other roles.  Therefore, if the
        // user selects the Aggregator role, don't allow any of the other roles
        // to be selected.
        
        if (hasAggregator(editRoles) && hasNonAggregator(editRoles)) {
            isValid = false;
            this.reportError(Constants.EDIT_ROLES_PROPERTY_KEY, 
                             KeyConstants.ERROR_AGGREGATOR_INCLUSIVE);
        }
            
        // The user cannot delete the last Aggregator on a proposal.
            
        else if (!hasAggregator(editRoles) && isLastAggregator(username, proposalUserRolesList)) {
            isValid = false;
            this.reportError(Constants.EDIT_ROLES_PROPERTY_KEY, 
                             KeyConstants.ERROR_LAST_AGGREGATOR);
        }

        // Can only add viewers after doc is workflowed
        else if (kraWorkflowService.isInWorkflow(document)) {
            isValid = false;
            this.reportError(Constants.EDIT_ROLES_PROPERTY_KEY, 
                    KeyConstants.ERROR_PERMISSION_VIEWER_ONLY_KEY);
        }
        
        return isValid;
    }
    
    /**
     * This method tests if the user is the only user with modify narrative rights for the narrative
     * @param username the user
     * @param narrative the narrative
     * @return true if the user is the only one with modify rights for the narrative
     */
    private boolean isOnlyModifier(String username, Narrative narrative) {
        boolean retval = true;
        KcPerson person = null;
        for (NarrativeUserRights narrativeUserRights : narrative.getNarrativeUserRights()) {
            person = getKcPersonService().getKcPersonByPersonId(narrativeUserRights.getUserId());
            if(!StringUtils.equals(username, person.getUserName()) 
                    && StringUtils.equals(narrativeUserRights.getAccessType(), NarrativeRight.MODIFY_NARRATIVE_RIGHT.getAccessType())) {
                retval = false;
                break;
            }
        }
        return retval;
    }
    
    /**
     * Gets the KC Person Service.
     * @return KC Person Service.
     */
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        
        return this.kcPersonService;
    }

    /**
     * This method tests if the user is having modify narrative permissions removed.  It does this by seeing if the user in the 
     * ProposalUserEditRoles has modify narrative permissions in ProposalUserEditRoles but not in the list of ProposalUserRoles.
     * @param proposalUserRolesList the list of all users' existing roles
     * @param editRoles the proposed new roles for the user in question
     * @return true if the user has modify narrative permissions in the list but not in the ProposalUserEditRoles
     */
    private boolean isRemovingModifyNarrativePermission(List<ProposalUserRoles> proposalUserRolesList, ProposalUserEditRoles editRoles) {
        boolean newListContainsModifyNarrative = false;
        SystemAuthorizationService systemAuthorizationService = KcServiceLocator.getService(SystemAuthorizationService.class);
        List<String> matchingRoleNames = systemAuthorizationService.getRoleNamesForPermission(PermissionConstants.MODIFY_NARRATIVE, Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT);

        for (ProposalRoleState roleState : editRoles.getRoleStates()) {
            if(roleState.getState()) {
                if(matchingRoleNames.contains(roleState.getName())) {
                    newListContainsModifyNarrative = true;
                    break;
                }
            }
        }

        return hasModifyNarrativePermission(editRoles.getUsername(), proposalUserRolesList) && !newListContainsModifyNarrative;
    }
    
    /**
     * This method checks if the user has Modify Narrative permission in the passed list of ProposalUserRoles
     * @param username the user
     * @param proposalUserRolesList the list of ProposalUserRoles to check
     * @return true if the user has Modify Narrative permissions in the list of ProposalUserRoles
     */
    private boolean hasModifyNarrativePermission(String username, List<ProposalUserRoles> proposalUserRolesList) {
        SystemAuthorizationService systemAuthorizationService = KcServiceLocator.getService(SystemAuthorizationService.class);
        List<String> matchingRoleNames = systemAuthorizationService.getRoleNamesForPermission(PermissionConstants.MODIFY_NARRATIVE, Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT);
        for (ProposalUserRoles proposalUserRoles : proposalUserRolesList) {
            if (proposalUserRoles.getUsername().equals(username)) {
                for (String roleName : proposalUserRoles.getRoleNames()) {
                    if(matchingRoleNames.contains(roleName)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * This method cycles through a list of attachments and checks if the user is the last user with modify permissions
     * on any of them.  It uses the passed label to report an error for each and returns true if an error is reportes
     * @param username The user
     * @param attachments A list of Narratives to test
     * @param errorLocationKey The location key to use when reporting errors
     * @param errorLabel The label to use when reporting errors
     * @return true if any of the attachments has the user as the only user with midufy permissions
     */
    private boolean testForLastModifier(String username, List<Narrative> attachments, String errorLocationKey, String errorLabel) {
        int index = 1;
        boolean reportedError = false;
        for (Narrative attachment : attachments) {
            if (isOnlyModifier(username, attachment)) {
                reportedError = true;
                this.reportError(errorLocationKey,
                        KeyConstants.ERROR_REQUIRE_ONE_NARRATIVE_MODIFY_WITH_ARG,
                        errorLabel + " " + index);
            }
            index++;
        }
        return reportedError;
    }
    
    /**
     * Has the Aggregator role been selected?
     * @param editRoles the Proposal Edit Roles
     * @return true if the Aggregator is selected; otherwise false
     */
    private boolean hasAggregator(ProposalUserEditRoles editRoles) {
        List<ProposalRoleState> roleStates = editRoles.getRoleStates();
        for (ProposalRoleState roleState : roleStates) {
            if (roleState.getState()) {
                String roleName = roleState.getName();
                if (StringUtils.equals(roleName, RoleConstants.AGGREGATOR)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Has any role other than Aggregator been selected?
     * @param editRoles the Proposal Edit Roles
     * @return true if a role other than Aggregator has been selected; otherwise false
     */
    private boolean hasNonAggregator(ProposalUserEditRoles editRoles) {
        List<ProposalRoleState> roleStates = editRoles.getRoleStates();
        for (ProposalRoleState roleState : roleStates) {
            if (roleState.getState()) {
                String roleName = roleState.getName();
                if (!StringUtils.equals(roleName, RoleConstants.AGGREGATOR)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Is this a valid username?  The User must reside in the database.
     * @param username the username
     * @return true if valid; otherwise false
     */
    private boolean isValidUser(String username) {
        return getKcPersonService().getKcPersonByUserName(username) != null;
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
    
    private boolean isAggregatorInitiator(ProposalDevelopmentDocument document, ProposalUserRoles proposalUserRole) {
        KcPerson initiator = getKcPersonService().getKcPersonByPersonId(document.getDocumentHeader().getWorkflowDocument().getInitiatorPrincipalId());
        if (StringUtils.equals(initiator.getUserName(), proposalUserRole.getUsername())) {
            for (String roleName : proposalUserRole.getRoleNames()) {
                if (StringUtils.equals(roleName, RoleConstants.AGGREGATOR)) {
                    return true;
                }
            }
        }
        return false;
    }    
    
    /**
     * This method tests if the role for the ProposalUser is Viewer
     * @param proposalUser the ProposalUser
     * @return true if the role is Viewer
     */
    private boolean isAddingViewerOnly(ProposalUser proposalUser) {
        return StringUtils.equals(proposalUser.getRoleName(), RoleConstants.VIEWER);
    }
}
