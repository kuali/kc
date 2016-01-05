/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.auth;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.auth.KcKradTransactionalDocumentAuthorizerBase;
import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.propdev.impl.attachment.NarrativeRight;
import org.kuali.coeus.propdev.impl.attachment.NarrativeUserRights;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentConstants;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentUtils;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyException;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyService;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.state.ProposalState;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.coeus.propdev.impl.state.ProposalStateConstants;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.institutionalproposal.InstitutionalProposalConstants;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.auth.perm.ProposalDevelopmentPermissionsService;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetConstants.AuthConstants;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.DocumentRequestAuthorizationCache;

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

    private KcAuthorizationService kcAuthorizationService;

    private UnitAuthorizationService unitAuthorizationService;

    private KcWorkflowService kcWorkflowService;

    private KcDocumentRejectionService kcDocumentRejectionService;

    private ProposalHierarchyService proposalHierarchyService;

    private ProposalDevelopmentPermissionsService proposalDevelopmentPermissionsService;

    @Override
    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<>();
         
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
    
    public boolean canCreateInstitutionalProposal(Document document, Person user) {
        boolean hasPermission = getPermissionService().hasPermission(user.getPrincipalId(), InstitutionalProposalConstants.INSTITUTIONAL_PROPOSAL_NAMESPACE,
    			PermissionConstants.CREATE_INSTITUTIONAL_PROPOSAL);
        if (hasPermission) {
        	hasPermission = getPermissionService().hasPermission(user.getPrincipalId(),
                    InstitutionalProposalConstants.INSTITUTIONAL_PROPOSAL_NAMESPACE,
                    PermissionConstants.SUBMIT_INSTITUTIONAL_PROPOSAL);
    	}
        return hasPermission;
    }
    
    public boolean canNotifyProposalPerson(Document document, Person user) {
        return getKcAuthorizationService().hasPermission(user.getPrincipalId(),(Permissionable)document,PermissionConstants.NOTIFY_PROPOSAL_PERSONS);
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

        if (editModes.contains(AuthorizationConstants.EditMode.FULL_ENTRY)) {
            editModes.add(ProposalDevelopmentConstants.AuthConstants.MODIFY_PROPOSAL_EDIT_MODE);
        }
        
        if (editModes.contains(AuthorizationConstants.EditMode.VIEW_ONLY)) {
            editModes.add(ProposalDevelopmentConstants.AuthConstants.VIEW_ONLY_PROPOSAL_EDIT_MODE);
        }
        
        if (isAuthorizedToAddBudget(doc, user)) {
            editModes.add(AuthConstants.ADD_BUDGET_EDIT_MODE);
        }

        if (isAuthorizedToOpenBudget(doc, user)) {
            editModes.add(AuthConstants.VIEW_BUDGET_EDIT_MODE);
        }
                
        if (isAuthorizedToModifyBudget(doc, user)) {
            editModes.add(AuthConstants.MODIFY_BUDGET_EDIT_MODE);
        }
                
        if (isAuthorizedToModifyProposalRoles(doc, user)) {
            editModes.add("modifyPermissions");
        }
                
        if (isAuthorizedToAddNarrative(doc, user)) {
            editModes.add("addNarratives");
        }

        if (isAuthorizedToReplaceNarrative(doc, user)) {
            editModes.add("replaceNarratives");
        }
                   
        if (isAuthorizedToCertify(doc, user)) {
            editModes.add("certify");
        }

        if (isAuthorizedToCopyProposal(doc, user)) {
            editModes.add("canCopyProposal");
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
        
        if (isAuthorizedToModifyS2s(doc, user)) {
        	editModes.add(ProposalDevelopmentConstants.AuthConstants.MODIFY_S2S);
        }
        
        if (isAuthorizedToMaintainProposalHierarchy(doc, user)) {
            editModes.add("maintainProposalHierarchy");
        }
        
        if (isAuthorizedToRejectProposal(doc, user)) {
            editModes.add("rejectProposal");
        }

        if (isAuthorizedToAddAddressBook(doc,user)) {
            editModes.add("addAddressBook");
        }

        if (canSaveCertification(doc, user)) {
            editModes.add(ProposalDevelopmentConstants.AuthConstants.CAN_SAVE_CERTIFICATION);
        }
        setNarrativePermissions(user, doc, editModes);
    } 
    
    private void setNarrativePermissions(Person user, ProposalDevelopmentDocument doc, Set<String> editModes) {

        List<Narrative> narratives = doc.getDevelopmentProposal().getNarratives();
        synchronized (narratives) {
            for (Narrative narrative : narratives) {
                String prefix = "proposalAttachment." + narrative.getModuleNumber() + ".";
                if (isAuthorizedToViewNarrative(narrative, user)) {
                    editModes.add(prefix + "download");
                }
                if (isAuthorizedToReplaceNarrative(narrative, user)) {
                    editModes.add(prefix + "replace");
                }
                if (isAuthorizedToDeleteNarrative(narrative, user)) {
                    editModes.add(prefix + "delete");
                }
                if (isAuthorizedToModifyNarrative(narrative, user)) {
                    editModes.add(prefix + "modifyStatus");
                }
                if (isAuthorizedToModifyNarrative(narrative, user)) {
                    editModes.add(prefix + "modifyRights");
                }
            }

            narratives = doc.getDevelopmentProposal().getInstituteAttachments();
            for (Narrative narrative : narratives) {
                String prefix = "instituteAttachment." + narrative.getModuleNumber() + ".";
                if (isAuthorizedToViewNarrative(narrative, user)) {
                    editModes.add(prefix + "download");
                }
                if (isAuthorizedToReplaceNarrative(narrative, user) ) {
                    editModes.add(prefix + "replace");
                }
                if (isAuthorizedToDeleteNarrative(narrative, user)) {
                    editModes.add(prefix + "delete");
                }
                if (isAuthorizedToModifyNarrative(narrative, user)) {
                    editModes.add(prefix + "modifyRights");
                }
            }


            int i = 0;
            boolean canReplace = isAuthorizedToReplacePersonnelAttachement(doc, user);
            for (ProposalPersonBiography ppb : doc.getDevelopmentProposal().getPropPersonBios()) {
                ppb.setPositionNumber(i);
                String prefix = "biographyAttachments." + ppb.getPositionNumber() + ".";
                if (canReplace) {
                    editModes.add(prefix + "replace");
                }

                i++;
            }
        }
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
        DocumentRequestAuthorizationCache.WorkflowDocumentInfo workflowDocumentInfo =
                getDocumentRequestAuthorizationCache(document).getWorkflowDocumentInfo();

        return canEdit(document, user) && !workflowDocumentInfo.isInitiated() || workflowDocumentInfo.isCanceled();
    }
    
    @Override
    public boolean canRoute(Document document, Person user) {
        return isAuthorizedToSubmitToWorkflow(document, user) && isAuthorizedToHierarchyChildWorkflowAction(document, user);
    }
    
    @Override
    public boolean canAnnotate(Document document, Person user) {
        return canRoute(document, user) && isAuthorizedToHierarchyChildWorkflowAction(document, user);
    }
    
    @Override
    public boolean canCopy(Document document, Person user) {
        return false;
    }
    
    @Override
    public boolean canApprove( Document document, Person user ) {
        return super.canApprove(document, user) && isAuthorizedToHierarchyChildWorkflowAction(document, user);
    }
    
    @Override
    public boolean canDisapprove( Document document, Person user ) {
        return super.canDisapprove(document, user) && isAuthorizedToHierarchyChildWorkflowAction(document, user);
    }
    
    @Override
    public boolean canBlanketApprove( Document document, Person user ) {
        return super.canBlanketApprove(document, user) && isAuthorizedToHierarchyChildWorkflowAction(document, user);
    }
    
    @Override
    public boolean canAcknowledge( Document document, Person user ) {
        return super.canAcknowledge(document, user) && isAuthorizedToHierarchyChildAckWorkflowAction(document, user);
    }
    
    @Override
    public boolean canAddNoteAttachment(Document document, String attachmentTypeCode, Person user) {
        return isAuthorizedToAddNote(document, user);
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
        return super.canFyi(document, user) && isAuthorizedToHierarchyChildWorkflowAction(document, user);
    }

    @Override
    public boolean canSendNoteFyi(Document document, Person user) {
        return false;
    }
    
    @Override
    public boolean canRecall(Document document, Person user) {
        return isAuthorizedToRecallProposal(document, user);
    }

    
    public boolean hasCertificationPermissions(ProposalDevelopmentDocument document, Person user,ProposalPerson proposalPerson){
    	return getProposalDevelopmentPermissionsService().hasCertificationPermissions(document, user, proposalPerson);
    }

    public boolean canSaveCertificationForPerson(ProposalDevelopmentDocument document, Person user,ProposalPerson proposalPerson){
        return isProposalStateEditableForCertification(document.getDevelopmentProposal()) && hasCertificationPermissions(document,user,proposalPerson);
    }
    protected boolean canSaveCertification(ProposalDevelopmentDocument document, Person user) {
    	if(isProposalStateEditableForCertification(document.getDevelopmentProposal())) {
    		if (document.getDevelopmentProposal().getProposalPersons().stream()
    				.filter(person -> getProposalDevelopmentPermissionsService().hasCertificationPermissions(document, user, person))
    				.anyMatch(person -> true)) { 
    			return true; 
    		}    		
    	}
        return false;
    }
    
    protected boolean canViewCertification(ProposalDevelopmentDocument document, Person user) {
    	return getKcAuthorizationService().hasPermission(user.getPrincipalId(), document, PermissionConstants.VIEW_CERTIFICATION);
    }

    protected boolean isProposalStateEditableForCertification(DevelopmentProposal developmentProposal) {
    	return getProposalStatesEditableForCertification().contains(developmentProposal.getProposalStateTypeCode());
    }
    
    protected Set<String> getProposalStatesEditableForCertification() {
        Set<String> proposalStates = new HashSet<String>();
        if(isCertificationRequiredOnlyBeforeApproval()) {
        	proposalStates.add(ProposalState.IN_PROGRESS);
        	proposalStates.add(ProposalState.REVISIONS_REQUESTED);
        	proposalStates.add(ProposalState.APPROVAL_PENDING);
        	proposalStates.add(ProposalState.APPROVAL_PENDING_SUBMITTED);
        }else {
        	proposalStates.add(ProposalState.IN_PROGRESS);
        	proposalStates.add(ProposalState.REVISIONS_REQUESTED);
        }
        return proposalStates;
    }
   
    protected boolean isCertificationRequiredOnlyBeforeApproval() {
        String keyPersonCertDefferalParam =  ProposalDevelopmentUtils.getProposalDevelopmentDocumentParameter(ProposalDevelopmentUtils.KEY_PERSON_CERTIFICATION_DEFERRAL_PARM);
        if(keyPersonCertDefferalParam.equalsIgnoreCase(ProposalDevelopmentConstants.ParameterValues.KEY_PERSON_CERTIFICATION_BEFORE_APPROVE)) {
        	return true;
        }else {
        	return false;
        }
    }
    
    protected boolean isAuthorizedToReplaceNarrative(Narrative narrative, Person user) {
        final ProposalDevelopmentDocument pdDocument = (ProposalDevelopmentDocument) narrative.getDevelopmentProposal().getDocument();

        boolean hasPermission = false;
        if (!pdDocument.getDevelopmentProposal().getSubmitFlag() && getModifyNarrativePermission(pdDocument, user)) {
            hasPermission = hasNarrativeRight(user.getPrincipalId(), narrative, NarrativeRight.MODIFY_NARRATIVE_RIGHT) || isAuthorizedToAlterProposalData(pdDocument, user);
        }

        return hasPermission;
    }

    protected boolean isAuthorizedToModifyNarrative(Narrative narrative, Person user) {
        final ProposalDevelopmentDocument pdDocument = (ProposalDevelopmentDocument) narrative.getDevelopmentProposal().getDocument();

        boolean rejectedDocument = getKcDocumentRejectionService().isDocumentOnInitialNode(pdDocument.getDocumentHeader().getWorkflowDocument());
        boolean hasPermission = false;
        boolean inWorkflow = getKcWorkflowService().isInWorkflow(pdDocument);

        if ((!inWorkflow || rejectedDocument) && !pdDocument.getDevelopmentProposal().getSubmitFlag()) {
            hasPermission = getModifyNarrativePermission(pdDocument, user);
        } else if(inWorkflow && !rejectedDocument && !pdDocument.getDevelopmentProposal().getSubmitFlag()) {
            if(getModifyNarrativePermission(pdDocument, user)) {
                hasPermission = getModifyNarrativePermission(pdDocument, user);
            }
        }
        return hasPermission;
    }

    protected boolean isAuthorizedToViewNarrative(Narrative narrative, Person user) {
        final ProposalDevelopmentDocument pdDocument = (ProposalDevelopmentDocument) narrative.getDevelopmentProposal().getDocument();

        // First, the user must have the VIEW_NARRATIVE permission.  This is really
        // a sanity check.  If they have the VIEW or MODIFY_NARRATIVE_RIGHT, then they are
        // required to have the VIEW_NARRATIVE permission.

        boolean hasPermission = false;
        if (getKcAuthorizationService().hasPermission(user.getPrincipalId(), pdDocument, PermissionConstants.VIEW_NARRATIVE)) {
            hasPermission = hasNarrativeRight(user.getPrincipalId(), narrative, NarrativeRight.VIEW_NARRATIVE_RIGHT)
                    || hasNarrativeRight(user.getPrincipalId(), narrative, NarrativeRight.MODIFY_NARRATIVE_RIGHT);
        }

        if (!hasPermission) {
            hasPermission = getUnitAuthorizationService().hasPermission(user.getPrincipalId(), pdDocument.getDevelopmentProposal().getOwnedByUnitNumber(), Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,
                    PermissionConstants.VIEW_NARRATIVE)
                    || getKcWorkflowService().hasWorkflowPermission(user.getPrincipalId(), pdDocument);
        }

        return hasPermission;
    }

    public boolean isAuthorizedToDeleteNarrative(Narrative narrative, Person user) {
        final ProposalDevelopmentDocument pdDocument = (ProposalDevelopmentDocument) narrative.getDevelopmentProposal().getDocument();

        // First, the user must have the MODIFY_NARRATIVE permission.  This is really
        // a sanity check.  If they have the MODIFY_NARRATIVE_RIGHT, then they are
        // required to have the MODIFY_NARRATIVE permission.

        KcDocumentRejectionService documentRejectionService = getKcDocumentRejectionService();
        boolean rejectedDocument = documentRejectionService.isDocumentOnInitialNode(pdDocument.getDocumentHeader().getWorkflowDocument());
        boolean hasPermission = false;

        boolean inWorkflow = getKcWorkflowService().isInWorkflow(pdDocument);

        if ((!inWorkflow || rejectedDocument) && !pdDocument.getDevelopmentProposal().getSubmitFlag()) {
            if (getModifyNarrativePermission(pdDocument, user)) {
                hasPermission = hasNarrativeRight(user.getPrincipalId(), narrative, NarrativeRight.MODIFY_NARRATIVE_RIGHT);
            }
        }
        return hasPermission;
    }

    /**
     * Does the user have the given narrative right for the given narrative?
     * @param userId the username of the user
     * @param narrative the narrative
     * @param narrativeRight the narrative right we are looking for
     * @return true if the user has the narrative right for the narrative
     */
    protected final boolean hasNarrativeRight(String userId, Narrative narrative, NarrativeRight narrativeRight) {
        List<NarrativeUserRights> userRightsList = narrative.getNarrativeUserRights();
        for (NarrativeUserRights userRights : userRightsList) {
            if (StringUtils.equals(userId, userRights.getUserId())) {
                if (StringUtils.equals(userRights.getAccessType(), narrativeRight.getAccessType())) {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean isAuthorizedToModifyBudget(Document document, Person user) {
        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);

        DocumentRequestAuthorizationCache documentRequestAuthorizationCache = getDocumentRequestAuthorizationCache(pdDocument);

        boolean hasModifyBudgetPermission;

        String modifyBudgetCacheKey = "ModifyBudget|" + pdDocument.getDocumentNumber() + "|" + user.getPrincipalId();
        if (documentRequestAuthorizationCache.hasPermissionResult(modifyBudgetCacheKey)) {
            hasModifyBudgetPermission = documentRequestAuthorizationCache.getPermissionResult(modifyBudgetCacheKey);
        }
        else {
            hasModifyBudgetPermission =  getKcAuthorizationService().hasPermission(user.getPrincipalId(), pdDocument, PermissionConstants.MODIFY_BUDGET);
            documentRequestAuthorizationCache.addPermissionResult(modifyBudgetCacheKey, hasModifyBudgetPermission);
        }

        boolean rejectedDocument = getKcDocumentRejectionService().isDocumentOnInitialNode(pdDocument.getDocumentHeader().getWorkflowDocument());
        return ( (!getKcWorkflowService().isInWorkflow(pdDocument) || rejectedDocument) && hasModifyBudgetPermission);
    }
    
    Boolean getCachedPermissionResult(Document document, String permissionCacheKey) {
    	DocumentRequestAuthorizationCache documentRequestAuthorizationCache = getDocumentRequestAuthorizationCache(document);
    	if (documentRequestAuthorizationCache.hasPermissionResult(permissionCacheKey)) {
    		return documentRequestAuthorizationCache.getPermissionResult(permissionCacheKey);
    	} else {
    		return null;
    	}
    }
    
    void addCachedPermissionResult(Document document, String permissionCacheKey, boolean result) {
    	DocumentRequestAuthorizationCache documentRequestAuthorizationCache = getDocumentRequestAuthorizationCache(document);
    	documentRequestAuthorizationCache.addPermissionResult(permissionCacheKey, result);
    }
    
    String buildPermissionCacheKey(Document document, Person user, String permissionCacheName) {
    	return permissionCacheName + "|" + document.getDocumentNumber() + "|" + user.getPrincipalId();
    }
    
    protected boolean hasModifyS2sEnroutePermission(Document document, Person user) {
		Boolean cachedResult = getCachedPermissionResult(document, buildPermissionCacheKey(document, user, PermissionConstants.MODIFY_S2S_ENROUTE));
    	if (cachedResult != null) {
    		return cachedResult;
    	}
    	
    	ProposalDevelopmentDocument pdDocument = (ProposalDevelopmentDocument) document;
    	boolean result = getKcAuthorizationService().hasPermission(user.getPrincipalId(), pdDocument, PermissionConstants.MODIFY_S2S_ENROUTE);
    	addCachedPermissionResult(document, buildPermissionCacheKey(document, user, PermissionConstants.MODIFY_S2S_ENROUTE), result);
    	
    	return result;
    }

    protected boolean isAuthorizedToOpenBudget(Document document, Person user) {
        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);

        return getKcAuthorizationService().hasPermission(user.getPrincipalId(), pdDocument, PermissionConstants.VIEW_BUDGET)
                || getKcWorkflowService().hasWorkflowPermission(user.getPrincipalId(), pdDocument);
    }

    protected boolean isAuthorizedToAddBudget(Document document, Person user) {
        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);

        boolean rejectedDocument = getKcDocumentRejectionService().isDocumentOnInitialNode(pdDocument.getDocumentHeader().getWorkflowDocument());

        return (!getKcWorkflowService().isInWorkflow(pdDocument) || rejectedDocument) && !pdDocument.isViewOnly() && !pdDocument.getDevelopmentProposal().getSubmitFlag() && !pdDocument.getDevelopmentProposal().isParent();

    }

    protected boolean isAuthorizedToAddNarrative(Document document, Person user) {
        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);

        boolean rejectedDocument = getKcDocumentRejectionService().isDocumentOnInitialNode(pdDocument.getDocumentHeader().getWorkflowDocument());
        boolean hasPermission = false;
        if ((!getKcWorkflowService().isInWorkflow(pdDocument) || rejectedDocument) && !pdDocument.isViewOnly() && !pdDocument.getDevelopmentProposal().getSubmitFlag()) {
            hasPermission = getModifyNarrativePermission(pdDocument, user);
        }
        return hasPermission;
    }

    protected boolean isAuthorizedToReplaceNarrative(Document document, Person user) {
        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document); 
        boolean hasPermission = false;
        if (!pdDocument.getDevelopmentProposal().getSubmitFlag() && pdDocument.getDocumentHeader().getWorkflowDocument().isEnroute()) {
            hasPermission = getModifyNarrativePermission(pdDocument, user) || isAuthorizedToAlterProposalData(document, user);
        }      
        return hasPermission;
    }

    protected boolean isAuthorizedToCertify(Document document, Person user) {
        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);
        return getKcAuthorizationService().hasPermission(user.getPrincipalId(), pdDocument, PermissionConstants.CERTIFY);
    }

    protected boolean isAuthorizedToCopyProposal(Document document, Person user) {
        return getUnitAuthorizationService().hasPermission(user.getPrincipalId(), Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, PermissionConstants.CREATE_PROPOSAL);
    }

    protected boolean isAuthorizedToReplacePersonnelAttachement(Document document, Person user) {
        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);

        return getModifyNarrativePermission(pdDocument, user) || isAuthorizedToAlterProposalData(document, user);
    }

    protected boolean isAuthorizedToRecallProposal(Document document, Person user) {
        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);
        return pdDocument.getDocumentHeader().hasWorkflowDocument() && pdDocument.getDocumentHeader().getWorkflowDocument().isEnroute()
                && getKcAuthorizationService().hasPermission(user.getPrincipalId(), pdDocument, PermissionConstants.RECALL_DOCUMENT)
                && !isRevisionRequested(pdDocument.getDevelopmentProposal().getProposalStateTypeCode());
    }

    protected boolean isRevisionRequested(String code) {
        return StringUtils.equalsIgnoreCase(code, ProposalState.REVISIONS_REQUESTED);
    }

    protected boolean isAuthorizedToRejectProposal(Document document, Person user) {
        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);
        DocumentRequestAuthorizationCache.WorkflowDocumentInfo workflowDocumentInfo =
                getDocumentRequestAuthorizationCache(document).getWorkflowDocumentInfo();

        return ((!workflowDocumentInfo.isCompletionRequested() && workflowDocumentInfo.isApprovalRequested()) || canReject(user)) &&
                !getKcDocumentRejectionService().isDocumentOnInitialNode(pdDocument.getDocumentHeader().getWorkflowDocument())
                && workflowDocumentInfo.isEnroute();
    }

    protected boolean canReject(Person user) {
        return getPermissionService().hasPermission(user.getPrincipalId(), Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,
                PermissionConstants.REJECT_PROPOSAL_DEVELOPMENT_DOCUMENT);
    }

    protected boolean isAuthorizedToSubmitToWorkflow(Document document, Person user) {
        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);
        return !getKcWorkflowService().isInWorkflow(pdDocument) &&
                getKcAuthorizationService().hasPermission(user.getPrincipalId(), pdDocument, PermissionConstants.SUBMIT_PROPOSAL) &&
                !pdDocument.getDevelopmentProposal().isChild();
    }

    protected boolean isAuthorizedToHierarchyChildWorkflowAction(Document document, Person user) {
        boolean authorized = true;
        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);

        if(pdDocument.getDevelopmentProposal().isChild() ) {
            try {
                final WorkflowDocument parentWDoc  = getProposalHierarchyService().getParentWorkflowDocument(pdDocument);
                    if(!parentWDoc.isInitiated()) {
                        authorized = false;
                    }
            } catch (ProposalHierarchyException e) {
                LOG.error( String.format( "Could not find parent workflow document for proposal document number:%s, which claims to be a child. Returning false.", pdDocument.getDocumentHeader().getDocumentNumber()), e);
                authorized = false;
            }
        }

        return authorized;
    }

    protected boolean isAuthorizedToHierarchyChildAckWorkflowAction(Document document, Person user) {
        boolean authorized = true;
        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);

        if(pdDocument.getDevelopmentProposal().isChild() ) {
            try {
                WorkflowDocument parentWDoc  = getProposalHierarchyService().getParentWorkflowDocument(pdDocument);
                if((!parentWDoc.isAcknowledgeRequested()) || parentWDoc.isInitiated()) {
                    authorized = false;
                }
            } catch (ProposalHierarchyException e) {
                LOG.error( String.format( "Could not find parent workflow document for proposal document number:%s, which claims to be a child. Returning false.", pdDocument.getDocumentHeader().getDocumentNumber()),e);
                authorized = false;
            }
        }

        return authorized;
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

    protected boolean getModifyNarrativePermission(ProposalDevelopmentDocument document, Person user) {
        DocumentRequestAuthorizationCache documentRequestAuthorizationCache = getDocumentRequestAuthorizationCache(document);

        boolean hasModifyNarrativePermission;

        String modifyNarrativeCacheKey = "ModifyNarrative|" + document.getDocumentNumber() + "|" + user.getPrincipalId();
        if (documentRequestAuthorizationCache.hasPermissionResult(modifyNarrativeCacheKey)) {
            hasModifyNarrativePermission = documentRequestAuthorizationCache.getPermissionResult(modifyNarrativeCacheKey);
        } else {
            hasModifyNarrativePermission = getKcAuthorizationService().hasPermission(user.getPrincipalId(), document, PermissionConstants.MODIFY_NARRATIVE);
            documentRequestAuthorizationCache.addPermissionResult(modifyNarrativeCacheKey, hasModifyNarrativePermission);
        }
        if (!hasModifyNarrativePermission) {
        	hasModifyNarrativePermission = !document.getDevelopmentProposal().getSubmitFlag() && document.getDocumentHeader().getWorkflowDocument().isEnroute()
        			&& getKcAuthorizationService().hasPermission(user.getPrincipalId(), document, PermissionConstants.ALTER_PROPOSAL_DATA);
        	documentRequestAuthorizationCache.addPermissionResult(modifyNarrativeCacheKey, hasModifyNarrativePermission);
        }


        return hasModifyNarrativePermission;
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

    public boolean isAuthorizedToPrint(Document document, Person user) {
        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);
        return getKcAuthorizationService().hasPermission(user.getPrincipalId(), pdDocument, PermissionConstants.PRINT_PROPOSAL);
    }

    protected boolean isAuthorizedToView(Document document, Person user) {
        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);
        return getKcAuthorizationService().hasPermission(user.getPrincipalId(), pdDocument, PermissionConstants.VIEW_PROPOSAL)
                || getKcWorkflowService().hasWorkflowPermission(user.getPrincipalId(), pdDocument);
    }
    
    protected boolean isAuthorizedToModifyS2s(Document document, Person user) {
    	return canEdit(document, user)
			|| (this.hasModifyS2sEnroutePermission(document, user) 
				&& !((ProposalDevelopmentDocument) document).getDevelopmentProposal().getSubmitFlag());
    }

    protected boolean isAuthorizedToAddNote(Document document, Person user) {

        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);

        String proposalNbr = pdDocument.getDevelopmentProposal().getProposalNumber();

        final boolean hasPermission;
        if (proposalNbr == null) {
            hasPermission = hasPermissionByOwnedByUnit(document, user);
        } else {

            hasPermission = getKcAuthorizationService().hasPermission(user.getPrincipalId(), pdDocument, PermissionConstants.VIEW_PROPOSAL)
                    || getKcWorkflowService().hasWorkflowPermission(user.getPrincipalId(), document);
        }
        return hasPermission;
    }

    protected boolean isAuthorizedToAddAddressBook(Document doc, Person user) {
        return getPermissionService().hasPermission(user.getPrincipalId(), Constants.MODULE_NAMESPACE_UNIT,PermissionConstants.ADD_ADDRESS_BOOK);
    }

    protected boolean isAuthorizedToModify(Document document, Person user) {
        DocumentRequestAuthorizationCache documentRequestAuthorizationCache = getDocumentRequestAuthorizationCache(document);

        String resultCacheKey = "IsAuthorizedToModify|" + document.getDocumentNumber() + "|" + user.getPrincipalId();
        if (documentRequestAuthorizationCache.hasPermissionResult(resultCacheKey)) {
            return documentRequestAuthorizationCache.getPermissionResult(resultCacheKey);
        }

        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);
        final DevelopmentProposal proposal = pdDocument.getDevelopmentProposal();

        if (!isEditableState(proposal.getProposalStateTypeCode())) {
            return false;
        }

        final String proposalNbr = proposal.getProposalNumber();

        boolean hasPermission;
        if (proposalNbr == null) {
            hasPermission = hasPermissionByOwnedByUnit(document, user);
        } else {
            /*
             * After the initial save, the proposal can only be modified if it is not in workflow
             * and the user has the require permission.
             */

           final boolean hasBeenRejected = ProposalState.REVISIONS_REQUESTED.equals(proposal.getProposalStateTypeCode());

            hasPermission = !pdDocument.isViewOnly() &&
                    getKcAuthorizationService().hasPermission(user.getPrincipalId(), pdDocument, PermissionConstants.MODIFY_PROPOSAL) &&
                    (!getKcWorkflowService().isInWorkflow(document) || hasBeenRejected) &&
                    !proposal.getSubmitFlag();
        }

        if (proposal.isChild() && hasPermission) {
            hasPermission = isAuthorizedToModify(proposal.getParent().getDocument(), user);
        }

        documentRequestAuthorizationCache.addPermissionResult(resultCacheKey, hasPermission);

        return hasPermission;
    }

    protected boolean hasPermissionByOwnedByUnit(Document document, Person user) {
        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);
        final DevelopmentProposal proposal = pdDocument.getDevelopmentProposal();

        String unitNumber = proposal.getOwnedByUnitNumber();

        // If the unit number is not specified, we will let the save operation continue because it
        // will fail with an error.  But if the user tries to save a proposal for a wrong unit, then
        // we will indicate that the user does not have permission to do that.
        return (unitNumber != null && getUnitAuthorizationService().hasPermission(user.getPrincipalId(), unitNumber, Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, PermissionConstants.CREATE_PROPOSAL)
                || unitNumber == null);
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
        if (proposalHierarchyService == null) {
            proposalHierarchyService = KcServiceLocator.getService(ProposalHierarchyService.class);
        }
        return proposalHierarchyService;
    }

    public void setProposalHierarchyService (ProposalHierarchyService proposalHierarchyService){
        this.proposalHierarchyService = proposalHierarchyService;
    }
    
    public ProposalDevelopmentPermissionsService getProposalDevelopmentPermissionsService() {
    	if(proposalDevelopmentPermissionsService == null){
    		proposalDevelopmentPermissionsService = KcServiceLocator.getService(ProposalDevelopmentPermissionsService.class);
    	}
		return proposalDevelopmentPermissionsService;
	}

	public void setProposalDevelopmentPermissionsService(
			ProposalDevelopmentPermissionsService proposalDevelopmentPermissionsService) {
		this.proposalDevelopmentPermissionsService = proposalDevelopmentPermissionsService;
	}
}
