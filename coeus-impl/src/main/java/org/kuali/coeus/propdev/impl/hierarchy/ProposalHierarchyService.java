/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.hierarchy;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.common.budget.framework.personnel.HierarchyPersonnelSummary;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface ProposalHierarchyService {
    
    //Constants for Proposal Routing
    public static final String HIERARCHY_CHILD_ENROUTE_APPSTATUS="message.proposalDevelopment.workflow.applicationStatus.parentEnroute";
    public static final String HIERARCHY_CHILD_CANCEL_APPSTATUS="message.proposalDevelopment.workflow.applicationStatus.parentCancel";
    public static final String HIERARCHY_CHILD_DISAPPROVE_APPSTATUS="message.proposalDevelopment.workflow.applicationStatus.parentDisapprove";
    public static final String HIERARCHY_CHILD_PROCESSED_APPSTATUS="message.proposalDevelopment.workflow.applicationStatus.parentProcessed";
    public static final String HIERARCHY_CHILD_FINAL_APPSTATUS="message.proposalDevelopment.workflow.applicationStatus.parentFinal";
    public static final String HIERARCHY_CHILD_REJECTED_APPSTATUS="message.proposalDevelopment.workflow.applicationStatus.parentRejected";
    
    public static final String HIERARCHY_REJECTED_APPSTATUS="message.proposalDevelopment.workflow.applicationStatus.rejected";
    public static final String HIERARCHY_ENROUTE_APPSTATUS="message.proposalDevelopment.workflow.applicationStatus.enroute";
    public static final String HIERARCHY_CANCEL_APPSTATUS="message.proposalDevelopment.workflow.applicationStatus.cancel";
    public static final String HIERARCHY_DISAPPROVE_APPSTATUS="message.proposalDevelopment.workflow.applicationStatus.disapprove";
    public static final String HIERARCHY_PROCESSED_APPSTATUS="message.proposalDevelopment.workflow.applicationStatus.processed";
    public static final String HIERARCHY_FINAL_APPSTATUS="message.proposalDevelopment.workflow.applicationStatus.final";
    
    public static final String HIERARCHY_ROUTING_PARENT_DISAPPROVED_ANNOTATION = "message.proposalDevelopment.workflow.annotation.parentDisapproved";
    public static final String HIERARCHY_ROUTING_PARENT_APPROVED_ANNOTATION = "message.proposalDevelopment.workflow.annotation.parentApproved";
    public static final String HIERARCHY_ROUTING_PARENT_CANCELLED_ANNOTATION = "message.proposalDevelopment.workflow.annotation.parentCanceled";
    public static final String HIERARCHY_ROUTING_PARENT_SUBMITTED_ANNOTATION = "message.proposalDevelopment.workflow.annotation.parentSubmitted";
    public static final String HIERARCHY_ROUTING_PARENT_RESUBMITTED_ANNOTATION = "message.proposalDevelopment.workflow.annotation.parentResubmitted";
    public static final String HIERARCHY_ROUTING_PARENT_REJECTED_ANNOTATION = "message.proposalDevelopment.workflow.annotation.parentRejected";
    public static final String PROPOSAL_ROUTING_REJECTED_ANNOTATION = "message.proposalDevelopment.workflow.annotation.rejected";
    
    public static final String PROPOSAL_DEVELOPMENT_DOCUMENT_TYPE = "ProposalDevelopmentDocument";
    
    
    /**
     * This method takes a proposal, creates a Hierarchy
     * and links the proposal as the initial child.
     * 
     * @param initialChild
     * @return the proposal number of the new hierarchy
     * @throws ProposalHierarchyException if the proposal is already a member of a hierarchy
     */
    public String createHierarchy(DevelopmentProposal initialChild, String userId) throws ProposalHierarchyException;

    /**
     * This method links a proposal to a Hierarchy.
     * 
     * @param hierarchyProposal the hierarchy to link the new child to
     * @param newChildProposal the proposal to link to the hierarchy
     * @param hierarchyBudgetTypeCode the type of budget syncing to perform with the child
     * @throws ProposalHierarchyException if hierarchyProposal is not a valid Hierarchy
     * or if newChildProposal is already a member of a hierarchy or does not exist
     */
    public void linkToHierarchy(DevelopmentProposal hierarchyProposal, DevelopmentProposal newChildProposal, String hierarchyBudgetTypeCode) throws ProposalHierarchyException;

    /**
     * This method removes childProposal from the hierarchy of which it is a member
     * 
     * @param childProposal the proposal to remove
     * @throws ProposalHierarchyException if childProposal is not a member of a hierarchy
     */
    public void removeFromHierarchy(DevelopmentProposal childProposal) throws ProposalHierarchyException;

    /**
     * This method synchronizes the contents of one child into its hierarchy.  If the child has changed since its last synchronization, the parent is reaggregated.
     * @param childProposal the child proposal in question
     * @throws ProposalHierarchyException if childProposal is not a member of a hierarchy
     */
    public void synchronizeChild(DevelopmentProposal childProposal) throws ProposalHierarchyException;
    
    /**
     * This method syncs only the budget from the child proposal specificed to the hierarchy budget.
     * @param childProposal
     * @throws ProposalHierarchyException
     */
    public void synchronizeChildProposalBudget(ProposalDevelopmentBudgetExt budget, DevelopmentProposal childProposal) throws ProposalHierarchyException;  
    
    /**
     * This method synchronizes the contents of all children into the hierarchy.  If any child has changed since its last synchronization, the parent is reaggregated.
     * @param developmentProposal the hierarchy in question
     * @throws ProposalHierarchyException if hierarchyProposalDocument is not a valid Hierarchy
     */
    public void synchronizeAllChildren(DevelopmentProposal developmentProposal) throws ProposalHierarchyException;

    public DevelopmentProposal getDevelopmentProposal(String proposalNumber);
    public DevelopmentProposal lookupParent(DevelopmentProposal childProposal) throws ProposalHierarchyException;
    public List<HierarchyPersonnelSummary> getHierarchyPersonnelSummaries(String parentProposalNumber) throws ProposalHierarchyException;
    public List<HierarchyProposalSummary> getHierarchyProposalSummaries(String proposalNumber) throws ProposalHierarchyException;
    public List<ProposalHierarchyErrorWarningDto> validateChildBudgetPeriods(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, boolean allowEndDateChange) throws ProposalHierarchyException;
    public List<DevelopmentProposal> getHierarchyProposals(DevelopmentProposal developmentProposal);

    
    /**
     * Get the parent workflow document of the hierarchy child document.
     * This is a utility method.
     * 
     * @param doc The child in question
     * @return The KualiWorklowDocument of the child's parent.
     * @throws ProposalHierarchyException if the provided proposal is not in a hierarchy.
     */
    public WorkflowDocument getParentWorkflowDocument( ProposalDevelopmentDocument doc ) throws ProposalHierarchyException;

    /**
     * Get the parent document of the hierarchy child document.
     * This is a utility method.
     * 
     * @param doc The child in question
     * @return The Document of the child's parent.
     * @throws ProposalHierarchyException if the provided proposal is not in a hierarchy.
     */
    public ProposalDevelopmentDocument getParentDocument( ProposalDevelopmentDocument doc ) throws ProposalHierarchyException;

    /**
     * Get a list of DevelopmentProposals that are children of proposal number provided.
     * @param parentProposalNumber the proposal number of the hierarchy parent.
     * @return List of DevelopmentProposals
     * @throws ProposalHierarchyException if the provided proposal is not a hierarchy.
     */
    public List<DevelopmentProposal> getHierarchyChildren( String parentProposalNumber ) throws ProposalHierarchyException;    

    /**
     * Reject a proposal development document by proposal number. This will return a proposal to state almost but not quite like initiated state.
     * If the proposal is a hierarchy all of the children will be returned to the initiated state as well.
     * @param proposalNumber the proposalNumber you wish to reject
     * @param reason the reason why it is rejected.  Will be added to the route log.
     * @param principalId The princpal to reject the document as.
     * @param rejectFile The file uploaded when the proposoal development was rejected.
     * @throws WorkflowException if there is a problem getting the workflow document, or rejecting the document.
     * @throws ProposalHierarchyException 
     * @throws IOException if there is a problem with the upload file.
     */
    public void rejectProposalDevelopmentDocument( String proposalNumber, String reason, String principalId, MultipartFile rejectFile)
    throws WorkflowException, ProposalHierarchyException, IOException;
    
    /**
     * Given the proposalDevelopmentDocument, RouteStatusChangeDTO, and the current user principal name, route all of the child proposal appropriately. 
     * @param proposalDevelopmentDocument The heirarchy being routed.
     * @param dto the route status change dto object.
     * @throws ProposalHierarchyException If there is a problem routing the children.
     */
    public void routeHierarchyChildren(ProposalDevelopmentDocument proposalDevelopmentDocument, DocumentRouteStatusChange dto ) throws ProposalHierarchyException;
    
    public boolean allChildBudgetsAreComplete(String parentProposalNumber);
    
    public boolean validateRemovePermissions(DevelopmentProposal childProposal, String principalId);

    public void calculateAndSetProposalAppDocStatus(ProposalDevelopmentDocument doc, DocumentRouteStatusChange dto) throws ProposalHierarchyException;
    
    /**
     * Gets the budget for hierarchy sync. This will be the budget marked final or the most recently created budget.
     * @param childProposal
     * @return
     * @throws ProposalHierarchyException
     */
    public ProposalDevelopmentBudgetExt getSyncableBudget(DevelopmentProposal childProposal) throws ProposalHierarchyException;
    
    public HierarchyProposalSummary getProposalSummary(String proposalNumber) throws ProposalHierarchyException;

    public List<ProposalHierarchyErrorWarningDto> validateChildCandidate(DevelopmentProposal proposal);

    public List<ProposalHierarchyErrorWarningDto> validateChildForSync (DevelopmentProposal child, DevelopmentProposal hierarchy, boolean allowEndDateChange);

    public List<ProposalHierarchyErrorWarningDto> validateChildForRemoval(DevelopmentProposal child);

    public List<ProposalHierarchyErrorWarningDto> validateParent(DevelopmentProposal proposal);

    public List<ProposalHierarchyErrorWarningDto> validateChildCandidateForHierarchy(DevelopmentProposal hierarchy, DevelopmentProposal child, boolean allowEndDateChange);

    public List<ProposalHierarchyErrorWarningDto> validateLinkToHierarchy(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal);

    public boolean isSynchronized(DevelopmentProposal childProposal);

    public List<ProposalHierarchyErrorWarningDto> validateSponsor(DevelopmentProposal childProposal, DevelopmentProposal parentProposal);

    public boolean personInMultipleProposals(String personId, DevelopmentProposal childProposal);

    public List<ProposalHierarchyErrorWarningDto> validateIsAggregatorOnChild(DevelopmentProposal childProposal);
    }
