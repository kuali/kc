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
package org.kuali.coeus.propdev.impl.docperm;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.NarrativeRight;
import org.kuali.coeus.propdev.impl.attachment.NarrativeUserRights;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.infrastructure.*;

import java.util.List;

/**
 * Business Rule to determine the legality of modifying the access
 * to a Proposal Development Document.
 * 
 */
public class ProposalDevelopmentPermissionsRule extends KcTransactionalDocumentRuleBase implements PermissionsRule {
    

    private transient KcPersonService kcPersonService;
    private transient KcWorkflowService kcWorkflowService;
    private transient SystemAuthorizationService systemAuthorizationService;
    private transient GlobalVariableService globalVariableService;
    
    @Override
    public boolean processAddProposalUserBusinessRules(ProposalDevelopmentDocument document, List<ProposalUserRoles> proposalUserRolesList, ProposalUserRoles proposalUser) {
        boolean isValid = true;
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
        else if (getKcWorkflowService().isInWorkflow(document)) {
            if (!isAddingViewerOnly(proposalUser)) {
                isValid = false;
                this.reportError(Constants.PERMISSION_PROPOSAL_USERS_PROPERTY_KEY + ".roleName", KeyConstants.ERROR_PERMISSION_VIEWER_ONLY_KEY);
            }
        }
        
        return isValid;
    }
    
    @Override
    public boolean processDeleteProposalUserBusinessRules(ProposalDevelopmentDocument document, List<ProposalUserRoles> proposalUserRolesList, int index) {
        boolean isValid = true;
        KcWorkflowService kraWorkflowService = getKcWorkflowService();
        ProposalUserRoles proposalUserRole = proposalUserRolesList.get(index);
        String username = proposalUserRole.getUsername();

        if (hasModifyNarrativePermission(username, proposalUserRolesList)) {
            isValid &= !testForLastModifier(username, document.getDevelopmentProposal().getNarratives(), Constants.PERMISSION_PROPOSAL_USERS_COLLECTION_ID_KEY, "Proposal Attachment");
            isValid &= !testForLastModifier(username, document.getDevelopmentProposal().getInstituteAttachments(), Constants.PERMISSION_PROPOSAL_USERS_COLLECTION_ID_KEY, "Internal Attachment");
        }
        
        // The user cannot delete the last Aggregator on a proposal.
        if (isLastAggregator(username, proposalUserRolesList)) {
            isValid = false;
            getGlobalVariableService().getMessageMap().putErrorForSectionId(Constants.PERMISSION_PROPOSAL_USERS_COLLECTION_ID_KEY, KeyConstants.ERROR_LAST_AGGREGATOR);
        } else if (isAggregatorInitiator(document, proposalUserRole)) {
            isValid = false;
            getGlobalVariableService().getMessageMap().putErrorForSectionId(Constants.PERMISSION_PROPOSAL_USERS_COLLECTION_ID_KEY, KeyConstants.ERROR_PROP_DEV_PERM_INITIATOR);
        }
        
        // Can only add viewers after doc is workflowed
        else if (kraWorkflowService.isInWorkflow(document)) {
            isValid = false;
            getGlobalVariableService().getMessageMap().putErrorForSectionId(Constants.PERMISSION_PROPOSAL_USERS_COLLECTION_ID_KEY, KeyConstants.ERROR_PERMISSION_VIEWER_ONLY_KEY);
        }
        
        return isValid;
    }
    
    @Override
    public boolean processEditProposalUserRolesBusinessRules(ProposalDevelopmentDocument document, List<ProposalUserRoles> proposalUserRolesList, ProposalUserRoles editRoles) {
        boolean isValid = true;
        String username = editRoles.getUsername();

        if (isRemovingModifyNarrativePermission(proposalUserRolesList, editRoles)) {
            isValid &= !testForLastModifier(username, document.getDevelopmentProposal().getNarratives(), Constants.PERMISSION_PROPOSAL_USERS_COLLECTION_ID_KEY, "Proposal Attachment");
            isValid &= !testForLastModifier(username, document.getDevelopmentProposal().getInstituteAttachments(), Constants.PERMISSION_PROPOSAL_USERS_COLLECTION_ID_KEY, "Internal Attachment");
        }
            
        // The user cannot delete the last Aggregator on a proposal.
        else if (!isAggregatorRolePresent(proposalUserRolesList)) {
            isValid = false;
            getGlobalVariableService().getMessageMap().putErrorForSectionId(Constants.PERMISSION_PROPOSAL_USERS_COLLECTION_ID_KEY, KeyConstants.ERROR_LAST_AGGREGATOR);
        }

        return isValid;
    }


    protected boolean isAggregatorRolePresent(List<ProposalUserRoles> proposalUserRolesList) {
        for (ProposalUserRoles proposalUserRoles : proposalUserRolesList) {
            if (hasAggregator(proposalUserRoles)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method tests if the user is the only user with modify narrative rights for the narrative
     */
    private boolean isOnlyModifier(String username, Narrative narrative) {
        boolean retval = true;
        KcPerson person;
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

    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        
        return this.kcPersonService;
    }

    protected KcWorkflowService getKcWorkflowService (){
        if (kcWorkflowService == null)
            kcWorkflowService = KcServiceLocator.getService(KcWorkflowService.class);
        return kcWorkflowService;
    }
    protected SystemAuthorizationService getSystemAuthorizationService (){
        if (systemAuthorizationService == null)
            systemAuthorizationService =KcServiceLocator.getService(SystemAuthorizationService.class);
        return systemAuthorizationService;
    }
    /**
     * This method tests if the user is having modify narrative permissions removed.  It does this by seeing if the user in the 
     * ProposalUserRoles has modify narrative permissions in ProposalUserRoles but not in the list of ProposalUserRoles.
     */
    private boolean isRemovingModifyNarrativePermission(List<ProposalUserRoles> proposalUserRolesList, ProposalUserRoles editRoles) {
        boolean newListContainsModifyNarrative = false;
        SystemAuthorizationService systemAuthorizationService = getSystemAuthorizationService();
        List<String> matchingRoleNames = systemAuthorizationService.getRoleNamesForPermission(PermissionConstants.MODIFY_NARRATIVE, Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT);

        for (String roleName : editRoles.getRoleNames()) {
            if(matchingRoleNames.contains(roleName)) {
                newListContainsModifyNarrative = true;
                break;
            }
        }

        return hasModifyNarrativePermission(editRoles.getUsername(), proposalUserRolesList) && !newListContainsModifyNarrative;
    }
    
    /**
     * This method checks if the user has Modify Narrative permission in the passed list of ProposalUserRoles
     */
    private boolean hasModifyNarrativePermission(String username, List<ProposalUserRoles> proposalUserRolesList) {
        SystemAuthorizationService systemAuthorizationService =getSystemAuthorizationService();
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
     */
    private boolean testForLastModifier(String username, List<Narrative> attachments, String errorLocationKey, String errorLabel) {
        int index = 1;
        boolean reportedError = false;
        for (Narrative attachment : attachments) {
            if (isOnlyModifier(username, attachment)) {
                reportedError = true;
                getGlobalVariableService().getMessageMap().putErrorForSectionId(errorLocationKey, KeyConstants.ERROR_REQUIRE_ONE_NARRATIVE_MODIFY_WITH_ARG,errorLabel + " " + index);
            }
            index++;
        }
        return reportedError;
    }
    
    /**
     * Has the Aggregator role been selected?
     */
    private boolean hasAggregator(ProposalUserRoles editRoles) {
        for (String roleName : editRoles.getRoleNames()) {
            if (isAggregatorRole(roleName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Is this a valid username?  The User must reside in the database.
     */
    private boolean isValidUser(String username) {
        return getKcPersonService().getKcPersonByUserName(username) != null;
    }

    /**
     * Is this a duplicate user?  In other words, has this user
     * already been added to the list of users who can access the
     * proposal?
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
     */
    private boolean isLastAggregator(String username, List<ProposalUserRoles> proposalUserRolesList) {
        for (ProposalUserRoles userRoles : proposalUserRolesList) {
            if (!StringUtils.equals(username, userRoles.getUsername())) {
                List<String> roleNames = userRoles.getRoleNames();
                for (String roleName : roleNames) {
                    if (isAggregatorRole(roleName)) {
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
                if (isAggregatorRole(roleName)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean isAggregatorRole(String roleName) {
        return StringUtils.equals(roleName, RoleConstants.AGGREGATOR_DOCUMENT_LEVEL);
    }
    
    /**
     * This method tests if the role for the ProposalUser is Viewer
     */
    private boolean isAddingViewerOnly(ProposalUserRoles proposalUser) {
        return proposalUser.getRoleNames().size() == 1 && (StringUtils.equals(proposalUser.getRoleNames().get(0), RoleConstants.VIEWER) || StringUtils.equals(proposalUser.getRoleNames().get(0), RoleConstants.VIEWER_DOCUMENT_LEVEL));
    }

    public GlobalVariableService getGlobalVariableService() {
        if (this.globalVariableService == null) {
            this.globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
        }
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }
}
