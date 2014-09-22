/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.auth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.auth.KcKradTransactionalDocumentAuthorizerBase;
import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.propdev.impl.auth.task.ProposalTask;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentConstants;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyException;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyService;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.state.ProposalState;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.document.Document;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The Proposal Development Document Authorizer.  Primarily responsible for determining if
 * a user has permission to create/modify/view proposals.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentDocumentAuthorizer extends KcKradTransactionalDocumentAuthorizerBase {

    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentDocumentAuthorizer.class);

    private TaskAuthorizationService taskAuthenticationService;
    
    private KcAuthorizationService kcAuthorizationService;

    private UnitAuthorizationService unitAuthorizationService;

    private KcWorkflowService kcWorkflowService;

    private KcDocumentRejectionService kcDocumentRejectionService;

    private ProposalHierarchyService proposalHierarchyService;

    @Override
    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<String>();
         
        ProposalDevelopmentDocument proposalDoc = (ProposalDevelopmentDocument) document;
        DevelopmentProposal developmentProposal = proposalDoc.getDevelopmentProposal();
		String proposalNbr = developmentProposal.getProposalNumber();
        
        // The getEditMode() method is invoked when a proposal is accessed for creation and when it
        // is accessed for modification.  New proposals under creation don't have a proposal number.
        // For a new proposal, we have to know if the user has the permission to create a proposal.
        // For a current proposal, we have to know if the user the permission to modify or view the proposal.
        
        if (proposalNbr == null) {
            if (isAuthorizedToCreate(document, user)) {
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
                setPermissions(user, proposalDoc, editModes);
            } 
            else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
        } 
        else {
            if (canEdit(document, user)) {  
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
                setPermissions(user, proposalDoc, editModes);
            }
            else if (isAuthorizedToView(document, user)) {
                editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
                setPermissions(user, proposalDoc, editModes);
            }
            else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
    
	        if (isBudgetComplete(developmentProposal)) {
	            if (editModes.contains("addBudget")) {
	                editModes.add("modifyCompletedBudgets");
	            }
        	    editModes.remove("modifyProposalBudget");
            	editModes.remove("addBudget");
        	}
        }
        
        return editModes;
    }

    @Override
    public boolean canDeleteDocument(Document document, Person user) {
    	if(document != null && document instanceof Permissionable)
    		return getKcAuthorizationService().hasPermission(user.getPrincipalId(), (Permissionable)document, PermissionConstants.DELETE_PROPOSAL);
    	else 
    		return false;
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
     * @param user the user
     * @param doc the Proposal Development Document
     * @param editModes the edit mode map
     */
    private void setPermissions(Person user, ProposalDevelopmentDocument doc, Set<String> editModes) {
        final String userId = user.getPrincipalId();

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
                
        if (isAuthorizedToModifyProposalRoles(doc, user)) {
            editModes.add("modifyPermissions");
        }
                
        if (canExecuteTask(userId, doc, TaskName.ADD_NARRATIVE)) {
            editModes.add("addNarratives");
        }
                   
        if (canExecuteTask(userId, doc, TaskName.CERTIFY)) {
            editModes.add("certify");
        }
        
        if (canExecuteTask(userId, doc, TaskName.MODIFY_NARRATIVE_STATUS)) {
            editModes.add("modifyNarrativeStatus");
        }
                
        if (isAuthorizedToPrint(doc, user)) {
            editModes.add("printProposal");
        }
                
        if (isAuthorizedToAlterProposalData(doc, user)) {
            editModes.add("alterProposalData");
        }
                
        if (isAuthorizedToShowAlterProposalData(doc, user)) {
            editModes.add("showAlterProposalData");
        }
                
        if (isAuthorizedToSubmitToSponsor(doc, user)) {
            editModes.add("submitToSponsor");
        }
        if (isAuthorizedToMaintainProposalHierarchy(doc, user)) {
            editModes.add("maintainProposalHierarchy");
        }
        
        if (canExecuteTask(userId, doc, TaskName.REJECT_PROPOSAL)) {
            editModes.add(TaskName.REJECT_PROPOSAL);
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
            if (narrative.getModifyAttachmentStatus(userId)) {
                editModes.add(prefix + "modifyStatus");
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
        

        int i = 0;
        boolean canReplace = getTaskAuthorizationService().isAuthorized(userId, new ProposalTask(TaskName.REPLACE_PERSONNEL_ATTACHMENT, doc));
        for (ProposalPersonBiography ppb : doc.getDevelopmentProposal().getPropPersonBios()) {
            ppb.setPositionNumber(i);
            String prefix = "biographyAttachments." + ppb.getPositionNumber() + ".";
            if (canReplace) {
                editModes.add(prefix + "replace");
            }
            
            i++;
        }
    }

    /**
     * Can the user execute the given task?
     * @param userId the user's username
     * @param doc the proposal development document
     * @param taskName the name of the task
     * @return "TRUE" if has permission; otherwise "FALSE"
     */
    private boolean canExecuteTask(String userId, ProposalDevelopmentDocument doc, String taskName) {
        return canExecuteProposalTask(userId, doc, taskName);
    }
    
    /**
     * Does the user have the given permission for the given proposal?
     * @param userId the user's username
     * @param doc the proposal development document
     * @param taskName the name of the task
     * @return true if has permission; otherwise false
     */
    private boolean canExecuteProposalTask(String userId, ProposalDevelopmentDocument doc, String taskName) {
        ProposalTask task = new ProposalTask(taskName, doc);       
        TaskAuthorizationService taskAuthenticationService = getTaskAuthenticationService();
        return taskAuthenticationService.isAuthorized(userId, task);
    }
    
    public boolean canOpen(Document document, Person user) {
        ProposalDevelopmentDocument proposalDocument = (ProposalDevelopmentDocument) document;
        if (proposalDocument.getDevelopmentProposal().getProposalNumber() == null) {
            return isAuthorizedToCreate(document, user);
        }
        return isAuthorizedToView(document, user);
    }
    
    @Override
    public boolean canEdit(Document document, Person user) {
        return isAuthorizedToModify(document, user);
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
        return canEdit(document, user) && !workflow.isInitiated() || workflow.isCanceled();
    }
    
    @Override
    public boolean canRoute(Document document, Person user) {
        return canExecuteProposalTask(user.getPrincipalId(), (ProposalDevelopmentDocument) document, TaskName.SUBMIT_TO_WORKFLOW) && canExecuteProposalTask(user.getPrincipalName(), (ProposalDevelopmentDocument) document, TaskName.PROPOSAL_HIERARCHY_CHILD_WORKFLOW_ACTION);
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
        return super.canBlanketApprove(document, user) && canExecuteProposalTask( user.getPrincipalName(), (ProposalDevelopmentDocument)document, TaskName.PROPOSAL_HIERARCHY_CHILD_WORKFLOW_ACTION);
    }
    
    @Override
    public boolean canAcknowledge( Document document, Person user ) {
        return super.canAcknowledge(document, user) && canExecuteProposalTask( user.getPrincipalName(), (ProposalDevelopmentDocument)document, TaskName.PROPOSAL_HIERARCHY_CHILD_ACKNOWLEDGE_ACTION);
    }
    
    protected boolean isBudgetComplete(DevelopmentProposal developmentProposal) {
    	return developmentProposal.getFinalBudget() != null;
    }
    
    @Override
    public boolean canAddNoteAttachment(Document document, String attachmentTypeCode, Person user) {
        return canExecuteProposalTask(user.getPrincipalId(), (ProposalDevelopmentDocument) document, TaskName.PROPOSAL_ADD_NOTE_ATTACHMENT);
    }
    
    @Override
    public boolean canDeleteNoteAttachment(Document document, String attachmentTypeCode, String createdBySelfOnly, Person user) {
        boolean retVal = false;
        Boolean allowNotesDeletion = getParameterService().getParameterValueAsBoolean(ProposalDevelopmentDocument.class, KeyConstants.ALLOW_PROPOSAL_DEVELOPMENT_NOTES_DELETION); 
        if(allowNotesDeletion != null && allowNotesDeletion) {
            retVal = super.canDeleteNoteAttachment(document, attachmentTypeCode, createdBySelfOnly, user);
        }
        return retVal;
    }
    
    @Override
    public boolean canViewNoteAttachment(Document document, String attachmentTypeCode, Person user) {
        return isAuthorizedToView(document, user);
    }

    @Override
    public boolean canFyi( Document document, Person user ) {
        return super.canFyi(document, user) && canExecuteProposalTask( user.getPrincipalName(), (ProposalDevelopmentDocument)document, TaskName.PROPOSAL_HIERARCHY_CHILD_WORKFLOW_ACTION);
    }

    @Override
    public boolean canSendNoteFyi(Document document, Person user) {
        return false;
    }
    
    @Override
    public boolean canRecall(Document document, Person user) {
        return canExecuteProposalTask(user.getPrincipalId(), (ProposalDevelopmentDocument)document, TaskName.RECALL_PROPOSAL);
    }

    public boolean hasCertificationPermissions(ProposalDevelopmentDocument document, Person user, ProposalPerson proposalPerson){
        if (getParameterService().getParameterValueAsBoolean(ProposalDevelopmentDocument.class, ProposalDevelopmentConstants.Parameters.KEY_PERSON_CERTIFICATION_SELF_CERTIFY_ONLY)) {
            boolean isKeyPersonnel = proposalPerson.getPerson().getPersonId().equals(user.getPrincipalId());
            boolean canCertify = getKcAuthorizationService().hasPermission(user.getPrincipalId(), document, PermissionConstants.CERTIFY);

            return isKeyPersonnel || canCertify;
        }
        return true;
    }

    protected boolean isAuthorizedToMaintainProposalHierarchy(Document document, Person user) {
        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);
        return getKcAuthorizationService().hasPermission(user.getPrincipalId(), pdDocument, PermissionConstants.MAINTAIN_PROPOSAL_HIERARCHY);
    }

    protected boolean isAuthorizedToAlterProposalData(Document document, Person user) {
        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);
        //standard is authorized calculation without taking child status into account.
        boolean ret = getKcWorkflowService().isEnRoute(pdDocument) &&
                !pdDocument.getDevelopmentProposal().getSubmitFlag() &&
                getKcAuthorizationService().hasPermission(user.getPrincipalId(), pdDocument, PermissionConstants.ALTER_PROPOSAL_DATA);

        //check to see if the parent is enroute, if so deny the edit attempt.
        if(pdDocument.getDevelopmentProposal().isChild() ) {
            try {
                if (getProposalHierarchyService().getParentWorkflowDocument(pdDocument).isEnroute()) {
                    ret = false;
                }
            } catch (ProposalHierarchyException e) {
                LOG.error(String.format( "Exception looking up parent of DevelopmentProposal %s, authorizer is going to deny edit access to this child.", pdDocument.getDevelopmentProposal().getProposalNumber()), e);
                ret = false;
            }
        }
        return ret;
    }

    protected boolean isAuthorizedToShowAlterProposalData(Document document, Person user) {
        return getKcWorkflowService().isInWorkflow(document);
    }

    protected boolean isAuthorizedToModifyProposalRoles(Document document, Person user) {
        return (hasFullAuthorization(document, user) || hasAddViewerAuthorization(document, user));
    }

    /**
     * This method checks if the user has full (pre-workflow/pre-submission) proposal access maintenance rights
     * @param user the user requesting access
     * @param document the document object
     * @return true if the user has full (pre-workflow/pre-submission) proposal access maintenance rights
     */
    protected boolean hasFullAuthorization(Document document, Person user) {
        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);
        return !pdDocument.isViewOnly()
                && getKcAuthorizationService().hasPermission(user.getPrincipalId(), pdDocument, PermissionConstants.MAINTAIN_PROPOSAL_ACCESS)
                && !getKcWorkflowService().isInWorkflow(pdDocument)
                && !pdDocument.getDevelopmentProposal().getSubmitFlag();
    }

    /**
     * This method checks if the user has rights to add proposal viewers.
     * @param user the user requesting access
     * @param document the document object
     */
    protected boolean hasAddViewerAuthorization (Document document, Person user) {
        boolean hasPermission = false;
        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);

        if (getKcAuthorizationService().hasPermission(user.getPrincipalId(), pdDocument, PermissionConstants.ADD_PROPOSAL_VIEWER)
                && getKcWorkflowService().isInWorkflow(pdDocument)) {
            // once workflowed (OSP Administrator and Aggregator have ADD_PROPOSAL_VIEWER permission)
            hasPermission = true;
        } else if (getKcWorkflowService().hasWorkflowPermission(user.getPrincipalId(), pdDocument)
                && getKcWorkflowService().isEnRoute(pdDocument)) {
            // Approvers (users in workflow) have permission while EnRoute
            hasPermission = true;
        }

        return hasPermission;
    }

    protected boolean isAuthorizedToCreate(Document document, Person user) {
        return getUnitAuthorizationService().hasPermission(user.getPrincipalId(), Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, PermissionConstants.CREATE_PROPOSAL);
    }

    protected boolean isAuthorizedToSubmitToSponsor(Document document, Person user) {
        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);
        return getKcAuthorizationService().hasPermission(user.getPrincipalId(), pdDocument, PermissionConstants.SUBMIT_TO_SPONSOR) &&
                !pdDocument.getDevelopmentProposal().isChild();
    }

    protected boolean isAuthorizedToPrint(Document document, Person user) {
        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);
        return getKcAuthorizationService().hasPermission(user.getPrincipalId(), pdDocument, PermissionConstants.PRINT_PROPOSAL);
    }

    protected boolean isAuthorizedToView(Document document, Person user) {
        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);
        return getKcAuthorizationService().hasPermission(user.getPrincipalId(), pdDocument, PermissionConstants.VIEW_PROPOSAL)
                || getKcWorkflowService().hasWorkflowPermission(user.getPrincipalId(), pdDocument);
    }

    protected boolean isAuthorizedToModify(Document document, Person user) {

        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);
        final DevelopmentProposal proposal = pdDocument.getDevelopmentProposal();

        if (!isEditableState(proposal.getProposalStateTypeCode())) {
            return false;
        }

        final String proposalNbr = proposal.getProposalNumber();

        final boolean hasPermission;
        if (proposalNbr == null) {
            String unitNumber = proposal.getOwnedByUnitNumber();

            // If the unit number is not specified, we will let the save operation continue because it
            // will fail with an error.  But if the user tries to save a proposal for a wrong unit, then
            // we will indicate that the user does not have permission to do that.
            hasPermission = (unitNumber != null && getUnitAuthorizationService().hasPermission(user.getPrincipalId(), unitNumber, Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, PermissionConstants.CREATE_PROPOSAL)
                    || unitNumber == null);
        } else {
            /*
             * After the initial save, the proposal can only be modified if it is not in workflow
             * and the user has the require permission.
             */
            final boolean hasBeenRejected = getKcDocumentRejectionService().isDocumentOnInitialNode(document);
            hasPermission = !pdDocument.isViewOnly() &&
                    getKcAuthorizationService().hasPermission(user.getPrincipalId(), pdDocument, PermissionConstants.MODIFY_PROPOSAL) &&
                    (!getKcWorkflowService().isInWorkflow(document) || hasBeenRejected) &&
                    !proposal.getSubmitFlag();
        }
        return hasPermission;
    }

    protected boolean isEditableState(String propsalState) {
        return !ProposalState.CANCELED.equals(propsalState) && !ProposalState.DISAPPROVED.equals(propsalState);
    }

	public KcAuthorizationService getKcAuthorizationService() {
		if (kcAuthorizationService == null) {
            kcAuthorizationService = KcServiceLocator.getService(KcAuthorizationService.class);
        }
		return kcAuthorizationService;
	}

	public void setKcAuthorizationService(KcAuthorizationService kcAuthorizationService) {
		this.kcAuthorizationService = kcAuthorizationService;
	}

    protected  TaskAuthorizationService getTaskAuthenticationService (){
        if (taskAuthenticationService == null) {
            taskAuthenticationService = KcServiceLocator.getService(TaskAuthorizationService.class);
        }
        return taskAuthenticationService;
    }

    public void setTaskAuthenticationService(TaskAuthorizationService taskAuthenticationService) {
        this.taskAuthenticationService = taskAuthenticationService;
    }

    public UnitAuthorizationService getUnitAuthorizationService() {
        if (unitAuthorizationService == null) {
            unitAuthorizationService = KcServiceLocator.getService(UnitAuthorizationService.class);
        }
        return unitAuthorizationService;
    }

    public void setUnitAuthorizationService(UnitAuthorizationService unitAuthorizationService) {
        this.unitAuthorizationService = unitAuthorizationService;
    }

    public KcWorkflowService getKcWorkflowService() {
        if (kcWorkflowService == null) {
            kcWorkflowService = KcServiceLocator.getService(KcWorkflowService.class);
        }
        return kcWorkflowService;
    }

    public void setKcWorkflowService(KcWorkflowService kcWorkflowService) {
        this.kcWorkflowService = kcWorkflowService;
    }

    public KcDocumentRejectionService getKcDocumentRejectionService() {
        if (kcDocumentRejectionService == null) {
            kcDocumentRejectionService = KcServiceLocator.getService(KcDocumentRejectionService.class);
        }
        return kcDocumentRejectionService;
    }

    public void setKcDocumentRejectionService(KcDocumentRejectionService kcDocumentRejectionService) {
        this.kcDocumentRejectionService = kcDocumentRejectionService;
    }

    protected ProposalHierarchyService getProposalHierarchyService (){
        if (kcDocumentRejectionService == null) {
            proposalHierarchyService = KcServiceLocator.getService(ProposalHierarchyService.class);
        }
        return proposalHierarchyService;
    }

    public void setProposalHierarchyService (ProposalHierarchyService proposalHierarchyService){
        this.proposalHierarchyService = proposalHierarchyService;
    }
}
