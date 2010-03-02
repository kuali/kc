/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.hierarchy.service;

import java.util.List;

import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.hierarchy.ProposalHierarchyErrorDto;
import org.kuali.kra.proposaldevelopment.hierarchy.ProposalHierarchyException;
import org.kuali.kra.proposaldevelopment.hierarchy.bo.HierarchyProposalSummary;
import org.kuali.rice.kew.dto.DocumentRouteStatusChangeDTO;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

/**
 * This class...
 */
public interface ProposalHierarchyService {

    //Constants for Proposal Hierarchy Child Routing
    public static final String PROPOSAL_HIERARCHY_PARENT_ENROUTE="Parent Enroute";
    public static final String PROPOSAL_HIERARCHY_PARENT_CANCEL="Parent Cancel";
    public static final String PROPOSAL_HIERARCHY_PARENT_DISAPPROVE="Parent Disapproved";
    public static final String PROPOSAL_HIERARCHY_PARENT_FINAL="Parent Final";
    
    public static final String HIERARCHY_ROUTING_PARENT_DISAPPROVED_ANNOTATION = "SYSTEM DISAPPROVED DOCUMENT - HIERARCHY PARENT WAS DISAPPROVED.";
    public static final String HIERARCHY_ROUTING_PARENT_APPROVED_ANNOTATION = "SYSTEM APPROVED DOCUMENT - HIERARCHY PARENT FINAL APPROVAL.";
    public static final String HIERARCHY_ROUTING_PARENT_CANCELLED_ANNOTATION = "SYSTEM CANCELED DOCUMENT - HIERARCHY PARENT WAS CANCELLED.";
    public static final String HIERARCHY_ROUTING_PARENT_SUBMITTED_ANNOTATION = "SYSTEM SUBMITTED DOCUMENT -  HIERARCHY PARENT WAS SUBMITTED.";
    public static final String HIERARCHY_ROUTING_PARENT_RESUBMITTED_ANNOTATION = "SYSTEM RE-SUBMITTED DOCUMENT -  HIERARCHY PARENT WAS RE-SUBMITTED.";
    public static final String HIERARCHY_ROUTING_PARENT_REJECTED_ANNOTATION = "SYSTEM REJECTED DOCUMENT -  HIERARCHY PARENT WAS REJECTED.";

    /**
     * This method takes a proposal, creates a Hierarchy
     * and links the proposal as the initial child.
     * 
     * @param initialChildProposal 
     * @return the proposal number of the new hierarchy
     * @throws ProposalHierarchyException if the proposal is already a member of a hierarchy
     */
    public String createHierarchy(DevelopmentProposal initialChild) throws ProposalHierarchyException;

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
     * This method synchronizes the contents of all children into the hierarchy.  If any child has changed since its last synchronization, the parent is reaggregated.
     * @param hierarchyProposalDocument the hierarchy in question
     * @throws ProposalHierarchyException if hierarchyProposalDocument is not a valid Hierarchy
     */
    public void synchronizeAllChildren(ProposalDevelopmentDocument hierarchyProposalDocument) throws ProposalHierarchyException;

    public DevelopmentProposal getDevelopmentProposal(String proposalNumber);
    public DevelopmentProposal lookupParent(DevelopmentProposal childProposal) throws ProposalHierarchyException;
    public List<HierarchyProposalSummary> getHierarchyProposalSummaries(String proposalNumber) throws ProposalHierarchyException;
    public ProposalHierarchyErrorDto validateChildBudgetPeriods(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, boolean allowEndDateChange) throws ProposalHierarchyException;
    
    
    /**
     * Get the parent workflow document of the hierarchy child document.
     * This is a utility method.
     * 
     * @param doc The child in question
     * @return The KualiWorklowDocument of the child's parent.
     * @throws ProposalHierarchyException if the provided proposal is not in a hierarchy.
     */
    public KualiWorkflowDocument getParentWorkflowDocument( ProposalDevelopmentDocument doc ) throws ProposalHierarchyException;

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
     * Calculate the AppDocStatus that should be applied to children of 
     * a parent moving from oldStatus to newStatus.
     * 
     * @param oldStatus The old workflow status of the parent document.
     * @param newStatus The new workflow status of the parent document.
     * 
     * @return The AppWorkDocStatus that should be set in a children.
     */
    public String getHierarchyChildRouteCode( String oldStatus, String newStatus );

    /**
     * Get a list of DevelopmentProposals that are children of proposal number provided.
     * @param parentProposalNumber the proposal number of the hierarchy parent.
     * @return List of DevelopmentProposals
     * @throws ProposalHierarchyException if the provided proposal is not a hierarchy.
     */
    public List<DevelopmentProposal> getHierarchyChildren( String parentProposalNumber ) throws ProposalHierarchyException;    
    
    
    /**
     * Get a list of ProposalDevelopmentDocuments that are children of proposal number provided.
     * @param parentProposalNumber the proposal number of the hierarchy parent.
     * @return List of ProposalDevelopmentDocuments
     * @throws ProposalHierarchyException if the provided proposal is not a hierarchy.
     */
    public List<ProposalDevelopmentDocument> getChildProposalDevelopmentDocuments( String parentProposalNumber ) throws ProposalHierarchyException;    
    
    
    /**
     * Reject a proposal development document by proposal number. This will return a proposal to state almost but not quite like initiated state.
     * If the proposal is a hierarchy all of the children will be returned to the initiated state as well.
     * @param proposalNumber the proposalNumber you wish to reject
     * @param reason the reason why it is rejected.  Will be added to the route log.
     * @param principalName The princpalName to reject the document as.
     * @throws WorkflowException if there is a problem getting the workflow document, or rejecting the document.
     * @throws ProposalHierarchyException 
     */
    public void rejectProposalDevelopmentDocument( String proposalNumber, String reason, String principalName ) throws WorkflowException, ProposalHierarchyException;
    
    
    /**
     * Get the initial node name of the ProposalDevelopmentDocument.
     * @return The initial node name of the ProposalDevelopmentDocument obtained directly from KEW.
     */
    public String getProposalDevelopmentInitialNodeName();

    /**
     * Calculate the proposal state type cope for a proposal.
     * 
     * @param proposalDevelopmentDocument the proposal document to calculate the state for.
     * @param isRouteStatusChanged boolean - is the route status changing?
     * @return String route status code.
     */
    public String getProposalStateTypeCode(ProposalDevelopmentDocument proposalDevelopmentDocument, boolean routeStatusChanged );

    /**
     * Given the proposalDevelopmentDocument, RouteStatusChangeDTO, and the current user principal name, route all of the child proposal appropriately. 
     * @param proposalDevelopmentDocument The heirarchy being routed.
     * @param dto the route status change dto object.
     * @param currentUserPrincipalName the name of the user to perform submit or approve operations on the child document.
     * @throws ProposalHierarchyException If there is a problem routing the children.
     */
    public void routeHierarchyChildren(ProposalDevelopmentDocument proposalDevelopmentDocument, DocumentRouteStatusChangeDTO dto, String currentUserPrincipalName) throws ProposalHierarchyException;
    
    public boolean allChildBudgetsAreComplete(String parentProposalNumber);
    
    public boolean validateRemovePermissions(DevelopmentProposal childProposal, String principalId);
}
