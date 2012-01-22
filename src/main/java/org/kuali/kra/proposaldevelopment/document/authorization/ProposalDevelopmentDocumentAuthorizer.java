/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.document.authorization;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.kuali.kra.authorization.ApplicationTask;
import org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalState;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.document.Document;

/**
 * The Proposal Development Document Authorizer.  Primarily responsible for determining if
 * a user has permission to create/modify/view proposals.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {

    
    org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDevelopmentDocumentAuthorizer.class);
    
    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<String>();
         
        ProposalDevelopmentDocument proposalDoc = (ProposalDevelopmentDocument) document;
        String proposalNbr = proposalDoc.getDevelopmentProposal().getProposalNumber();
        
        // The getEditMode() method is invoked when a proposal is accessed for creation and when it
        // is accessed for modification.  New proposals under creation don't have a proposal number.
        // For a new proposal, we have to know if the user has the permission to create a proposal.
        // For a current proposal, we have to know if the user the permission to modify or view the proposal.
        
        String userId = user.getPrincipalId();
        if (proposalNbr == null) {
            if (canCreateProposal(user)) {
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
                setPermissions(userId, proposalDoc, editModes);
            } 
            else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
        } 
        else {
            if (canEdit(document, user)) {  
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
                setPermissions(userId, proposalDoc, editModes);
            }
            else if (canExecuteProposalTask(userId, proposalDoc, TaskName.VIEW_PROPOSAL)) {
                editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
                setPermissions(userId, proposalDoc, editModes);
            }
            else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
    
	        if (isBudgetComplete(proposalDoc)) {
	            if (editModes.contains("addBudget")) {
	                editModes.add("modifyCompletedBudgets");
	            }
        	    editModes.remove("modifyProposalBudget");
            	editModes.remove("addBudget");
        	}
        }
        
        return editModes;
    }

    /**
     * Set the permissions to be used during the creation of the web pages.  
     * The JSP files can access the editModeMap (editingMode) to determine what
     * to display to the user.  For example, a JSP file may contain the following:
     * 
     *     <kra:section permission="modifyProposal">
     *         .
     *         .
     *         .
     *     </kra:section>
     * 
     * In the above example, the contents are only rendered if the user is allowed
     * to modify the proposal.  Note that permissions are always signified as 
     * either TRUE or FALSE.
     * 
     * @param username the user's unique username
     * @param doc the Proposal Development Document
     * @param editModeMap the edit mode map
     */
    private void setPermissions(String userId, ProposalDevelopmentDocument doc, Set<String> editModes) {
        if (editModes.contains(AuthorizationConstants.EditMode.FULL_ENTRY)) {
            editModes.add("modifyProposal");
        }
        
        if (canExecuteTask(userId, doc, TaskName.ADD_BUDGET)) {
            editModes.add("addBudget");
        }
                
        if (canExecuteTask(userId, doc, TaskName.OPEN_BUDGETS)) {
            editModes.add("openBudgets");
        }
                
        if (canExecuteTask(userId, doc, TaskName.MODIFY_BUDGET)) {
            editModes.add("modifyProposalBudget");
        }
                
        if (canExecuteTask(userId, doc, TaskName.MODIFY_PROPOSAL_ROLES)) {
            editModes.add("modifyPermissions");
        }
                
        if (canExecuteTask(userId, doc, TaskName.ADD_NARRATIVE)) {
            editModes.add("addNarratives");
        }
                   
        if (canExecuteTask(userId, doc, TaskName.CERTIFY)) {
            editModes.add("certify");
        }
                
        if (canExecuteTask(userId, doc, TaskName.PRINT_PROPOSAL)) {
            editModes.add("printProposal");
        }
                
        if (canExecuteTask(userId, doc, TaskName.ALTER_PROPOSAL_DATA)) {
            editModes.add("alterProposalData");
        }
                
        if (canExecuteTask(userId, doc, TaskName.SHOW_ALTER_PROPOSAL_DATA)) {
            editModes.add("showAlterProposalData");
        }
                
        if (canExecuteTask(userId, doc, TaskName.SUBMIT_TO_SPONSOR)) {
            editModes.add("submitToSponsor");
        }
        if (canExecuteTask(userId, doc, TaskName.MAINTAIN_PROPOSAL_HIERARCHY)) {
            editModes.add("maintainProposalHierarchy");
        }
        setNarrativePermissions(userId, doc, editModes);
    } 
    
    private void setNarrativePermissions(String userId, ProposalDevelopmentDocument doc, Set<String> editModes) {
        List<Narrative> narratives = doc.getDevelopmentProposal().getNarratives();
        for (Narrative narrative : narratives) {
            String prefix = "proposalAttachment." + narrative.getModuleNumber() + ".";
            if (narrative.getDownloadAttachment(userId)) {
                editModes.add(prefix + "download");
            }
            if (narrative.getReplaceAttachment(userId)) {
                editModes.add(prefix + "replace");
            }
            if (narrative.getDeleteAttachment(userId)) {
                editModes.add(prefix + "delete");
            }
            if (narrative.getModifyNarrativeRights(userId)) {
                editModes.add(prefix + "modifyRights");
            }
        }
        
        narratives = doc.getDevelopmentProposal().getInstituteAttachments();
        for (Narrative narrative : narratives) {
            String prefix = "instituteAttachment." + narrative.getModuleNumber() + ".";
            if (narrative.getDownloadAttachment(userId)) {
                editModes.add(prefix + "download");
            }
            if (narrative.getReplaceAttachment(userId) ) {
                editModes.add(prefix + "replace");
            }
            if (narrative.getDeleteAttachment(userId)) {
                editModes.add(prefix + "delete");
            }
            if (narrative.getModifyNarrativeRights(userId)) {
                editModes.add(prefix + "modifyRights");
            }
        }
    }

    /**
     * Can the user execute the given task?
     * @param username the user's username
     * @param doc the proposal development document
     * @param taskName the name of the task
     * @return "TRUE" if has permission; otherwise "FALSE"
     */
    private boolean canExecuteTask(String userId, ProposalDevelopmentDocument doc, String taskName) {
        return canExecuteProposalTask(userId, doc, taskName);
    }
    
    /**
     * Does the user have the given permission for the given proposal?
     * @param username the user's username
     * @param doc the proposal development document
     * @param taskName the name of the task
     * @return true if has permission; otherwise false
     */
    private boolean canExecuteProposalTask(String userId, ProposalDevelopmentDocument doc, String taskName) {
        ProposalTask task = new ProposalTask(taskName, doc);       
        TaskAuthorizationService taskAuthenticationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(userId, task);
    }
    
    public boolean canInitiate(String documentTypeName, Person user) {
        return canCreateProposal(user);
    }
    
    public boolean canOpen(Document document, Person user) {
        ProposalDevelopmentDocument proposalDocument = (ProposalDevelopmentDocument) document;
        if (proposalDocument.getDevelopmentProposal().getProposalNumber() == null) {
            return canCreateProposal(user);
        }
        return canExecuteProposalTask(user.getPrincipalId(), proposalDocument, TaskName.VIEW_PROPOSAL);
    }
    
    /**
     * Does the user have permission to create a proposal.  Use the Unit Authorization Service to determine
     * if the user has the CREATE_PROPOSAL permission in any unit.
     * @param user the user
     * @return true if the user has the CREATE_PROPOSAL permission in at least one unit; otherwise false
     */
    private boolean canCreateProposal(Person user) {
        ApplicationTask task = new ApplicationTask(TaskName.CREATE_PROPOSAL);       
        TaskAuthorizationService taskAuthenticationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(user.getPrincipalId(), task);
    }
    
    @Override
    public boolean canEdit(Document document, Person user) {
        ProposalDevelopmentDocument proposalDocument = (ProposalDevelopmentDocument) document;
        if (proposalDocument.getDevelopmentProposal().getProposalState().getStateTypeCode().equals(ProposalState.CANCELED)) {
            return false;
        }
        return canExecuteProposalTask(user.getPrincipalId(), (ProposalDevelopmentDocument) document, TaskName.MODIFY_PROPOSAL);
    }
    
    @Override
    public boolean canSave(Document document, Person user) {
        return canEdit(document, user);
    }
    
    @Override
    public boolean canCancel(Document document, Person user) {
        return canEdit(document, user) && super.canCancel(document, user);
    }
    
    @Override
    public boolean canReload(Document document, Person user) {
        WorkflowDocument workflow = document.getDocumentHeader().getWorkflowDocument();
        return canEdit(document, user) || workflow.isCanceled();
    }
    
    @Override
    public boolean canRoute(Document document, Person user) {
        return canExecuteProposalTask(user.getPrincipalId(), (ProposalDevelopmentDocument) document, TaskName.SUBMIT_TO_WORKFLOW) && canExecuteProposalTask( user.getPrincipalName(), (ProposalDevelopmentDocument)document, TaskName.PROPOSAL_HIERARCHY_CHILD_WORKFLOW_ACTION);
    }
    
    @Override
    public boolean canAnnotate(Document document, Person user) {
        return canRoute(document, user) && canExecuteProposalTask( user.getPrincipalName(), (ProposalDevelopmentDocument)document, TaskName.PROPOSAL_HIERARCHY_CHILD_WORKFLOW_ACTION);
    }
    
    @Override
    public boolean canCopy(Document document, Person user) {
        return false;
    }
    
    @Override
    public boolean canApprove( Document document, Person user ) {
        return super.canApprove(document,user) && canExecuteProposalTask( user.getPrincipalName(), (ProposalDevelopmentDocument)document, TaskName.PROPOSAL_HIERARCHY_CHILD_WORKFLOW_ACTION);
    }
    
    @Override
    public boolean canDisapprove( Document document, Person user ) {
        return super.canDisapprove(document, user) && canExecuteProposalTask( user.getPrincipalName(), (ProposalDevelopmentDocument)document, TaskName.PROPOSAL_HIERARCHY_CHILD_WORKFLOW_ACTION);
    }
    
    @Override
    public boolean canBlanketApprove( Document document, Person user ) {
        WorkflowDocument workflowDocument = document.getDocumentHeader().getWorkflowDocument();
        return workflowDocument.isEnroute() && super.canBlanketApprove(document, user) && canExecuteProposalTask( user.getPrincipalName(), (ProposalDevelopmentDocument)document, TaskName.PROPOSAL_HIERARCHY_CHILD_WORKFLOW_ACTION);
    }
    
    @Override
    public boolean canAcknowledge( Document document, Person user ) {
        return super.canAcknowledge(document, user) && canExecuteProposalTask( user.getPrincipalName(), (ProposalDevelopmentDocument)document, TaskName.PROPOSAL_HIERARCHY_CHILD_ACKNOWLEDGE_ACTION);
    }
    
    protected boolean isBudgetComplete(BudgetParentDocument parentDocument) {
        if (!parentDocument.isComplete()) {
            return false;
        }
        for (BudgetDocumentVersion budgetVersion: parentDocument.getBudgetDocumentVersions()) {
            if (budgetVersion.getBudgetVersionOverview().isFinalVersionFlag()) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean canAddNoteAttachment(Document document, String attachmentTypeCode, Person user) {
        return canExecuteProposalTask(user.getPrincipalId(), (ProposalDevelopmentDocument) document, TaskName.PROPOSAL_ADD_NOTE_ATTACHMENT);
    }
    
    @Override
    public boolean canDeleteNoteAttachment(Document document, String attachmentTypeCode, String createdBySelfOnly, Person user) {
        return canExecuteProposalTask(user.getPrincipalId(), (ProposalDevelopmentDocument) document, TaskName.MODIFY_PROPOSAL);
    }
    
    @Override
    public boolean canViewNoteAttachment(Document document, String attachmentTypeCode, Person user) {
        return canExecuteProposalTask(user.getPrincipalId(), (ProposalDevelopmentDocument) document, TaskName.VIEW_PROPOSAL);
    }

    @Override
    public boolean canFyi( Document document, Person user ) {
        return super.canFyi(document, user) && canExecuteProposalTask( user.getPrincipalName(), (ProposalDevelopmentDocument)document, TaskName.PROPOSAL_HIERARCHY_CHILD_WORKFLOW_ACTION);
    }

    @Override
    public boolean canSendNoteFyi(Document document, Person user) {
        return false;
    }

}
