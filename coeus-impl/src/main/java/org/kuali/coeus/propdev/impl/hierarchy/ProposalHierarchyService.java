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
package org.kuali.coeus.propdev.impl.hierarchy;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.common.budget.framework.personnel.HierarchyPersonnelSummary;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.state.ProposalState;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ProposalHierarchyService {
    
    String HIERARCHY_REJECTED_APPSTATUS="message.proposalDevelopment.workflow.applicationStatus.rejected";
    String HIERARCHY_ENROUTE_APPSTATUS="message.proposalDevelopment.workflow.applicationStatus.enroute";
    String HIERARCHY_CANCEL_APPSTATUS="message.proposalDevelopment.workflow.applicationStatus.cancel";
    String HIERARCHY_DISAPPROVE_APPSTATUS="message.proposalDevelopment.workflow.applicationStatus.disapprove";
    String HIERARCHY_PROCESSED_APPSTATUS="message.proposalDevelopment.workflow.applicationStatus.processed";
    String HIERARCHY_FINAL_APPSTATUS="message.proposalDevelopment.workflow.applicationStatus.final";

    String PROPOSAL_ROUTING_REJECTED_ANNOTATION = "message.proposalDevelopment.workflow.annotation.rejected";
    
    String PROPOSAL_DEVELOPMENT_DOCUMENT_TYPE = "ProposalDevelopmentDocument";
    
    
    /**
     * This method takes a proposal, creates a Hierarchy
     * and links the proposal as the initial child.
     *
     * @return the proposal number of the new hierarchy
     * @throws ProposalHierarchyException if the proposal is already a member of a hierarchy
     */
    String createHierarchy(DevelopmentProposal initialChild, String userId) throws ProposalHierarchyException;

    /**
     * This method links a proposal to a Hierarchy.
     * 
     * @param hierarchyProposal the hierarchy to link the new child to
     * @param newChildProposal the proposal to link to the hierarchy
     * @param hierarchyBudgetTypeCode the type of budget syncing to perform with the child
     * @throws ProposalHierarchyException if hierarchyProposal is not a valid Hierarchy
     * or if newChildProposal is already a member of a hierarchy or does not exist
     */
    void linkToHierarchy(DevelopmentProposal hierarchyProposal, DevelopmentProposal newChildProposal, String hierarchyBudgetTypeCode) throws ProposalHierarchyException;

    /**
     * This method removes childProposal from the hierarchy of which it is a member
     * 
     * @param childProposal the proposal to remove
     * @throws ProposalHierarchyException if childProposal is not a member of a hierarchy
     */
    DevelopmentProposal removeFromHierarchy(DevelopmentProposal childProposal) throws ProposalHierarchyException;

    /**
     * This method synchronizes the contents of one child into its hierarchy.  If the child has changed since its last synchronization, the parent is reaggregated.
     * @param childProposal the child proposal in question
     * @throws ProposalHierarchyException if childProposal is not a member of a hierarchy
     */
    void synchronizeChild(DevelopmentProposal childProposal) throws ProposalHierarchyException;
      
    /**
     * This method synchronizes the contents of all children into the hierarchy.  If any child has changed since its last synchronization, the parent is reaggregated.
     * @param developmentProposal the hierarchy in question
     * @throws ProposalHierarchyException if hierarchyProposalDocument is not a valid Hierarchy
     */
    void synchronizeAllChildren(DevelopmentProposal developmentProposal) throws ProposalHierarchyException;

    DevelopmentProposal getDevelopmentProposal(String proposalNumber);
    DevelopmentProposal lookupParent(DevelopmentProposal childProposal) throws ProposalHierarchyException;
    List<HierarchyPersonnelSummary> getHierarchyPersonnelSummaries(String parentProposalNumber) throws ProposalHierarchyException;
    List<HierarchyProposalSummary> getHierarchyProposalSummaries(String proposalNumber) throws ProposalHierarchyException;
    List<ProposalHierarchyErrorWarningDto> validateChildBudgetPeriods(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, boolean allowEndDateChange) throws ProposalHierarchyException;
    List<DevelopmentProposal> getHierarchyProposals(DevelopmentProposal developmentProposal);

    
    /**
     * Get the parent workflow document of the hierarchy child document.
     * This is a utility method.
     * 
     * @param doc The child in question
     * @return The KualiWorklowDocument of the child's parent.
     * @throws ProposalHierarchyException if the provided proposal is not in a hierarchy.
     */
    WorkflowDocument getParentWorkflowDocument( ProposalDevelopmentDocument doc ) throws ProposalHierarchyException;

    /**
     * Get the parent document of the hierarchy child document.
     * This is a utility method.
     * 
     * @param doc The child in question
     * @return The Document of the child's parent.
     * @throws ProposalHierarchyException if the provided proposal is not in a hierarchy.
     */
    ProposalDevelopmentDocument getParentDocument( ProposalDevelopmentDocument doc ) throws ProposalHierarchyException;

    /**
     * Get a list of DevelopmentProposals that are children of proposal number provided.
     * @param parentProposalNumber the proposal number of the hierarchy parent.
     * @return List of DevelopmentProposals
     * @throws ProposalHierarchyException if the provided proposal is not a hierarchy.
     */
    List<DevelopmentProposal> getHierarchyChildren( String parentProposalNumber ) throws ProposalHierarchyException;

    /**
     * Reject a proposal development document by proposal number. This will return a proposal to state almost but not quite like initiated state.
     * If the proposal is a hierarchy all of the children will be returned to the initiated state as well.
     * @param proposalNumber the proposalNumber you wish to reject
     * @param reason the reason why it is rejected.  Will be added to the route log.
     * @param principalId The princpal to reject the document as.
     * @param rejectFile The file uploaded when the proposoal development was rejected.
     * @throws WorkflowException if there is a problem getting the workflow document, or rejecting the document.
     * @throws ProposalHierarchyException
     */
    void rejectProposalDevelopmentDocument( String proposalNumber, String reason, String principalId, MultipartFile rejectFile)
    throws WorkflowException, ProposalHierarchyException;

    void createAndSaveActionNarrative(String reason, String title, MultipartFile file, String narrativeTypeCode, ProposalDevelopmentDocument pDoc);

    boolean validateRemovePermissions(DevelopmentProposal childProposal, String principalId);

    void calculateAndSetProposalAppDocStatus(ProposalDevelopmentDocument doc, DocumentRouteStatusChange dto) throws ProposalHierarchyException;
    
    /**
     * Gets the budget for hierarchy sync. This will be the budget marked final or the most recently created budget.
     */
    ProposalDevelopmentBudgetExt getSyncableBudget(DevelopmentProposal childProposal) throws ProposalHierarchyException;
    
    HierarchyProposalSummary getProposalSummary(String proposalNumber) throws ProposalHierarchyException;

    List<ProposalHierarchyErrorWarningDto> validateChildCandidate(DevelopmentProposal proposal);

    List<ProposalHierarchyErrorWarningDto> validateChildForSync (DevelopmentProposal child, DevelopmentProposal hierarchy, boolean allowEndDateChange);

    List<ProposalHierarchyErrorWarningDto> validateChildForRemoval(DevelopmentProposal child);

    List<ProposalHierarchyErrorWarningDto> validateParent(DevelopmentProposal proposal);

    List<ProposalHierarchyErrorWarningDto> validateChildCandidateForHierarchy(DevelopmentProposal hierarchy, DevelopmentProposal child, boolean allowEndDateChange);

    List<ProposalHierarchyErrorWarningDto> validateLinkToHierarchy(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal);

    boolean isSynchronized(DevelopmentProposal childProposal);

    List<ProposalHierarchyErrorWarningDto> validateSponsor(DevelopmentProposal childProposal, DevelopmentProposal parentProposal);

    boolean employeePersonInMultipleProposals(String personId, DevelopmentProposal childProposal);

    boolean nonEmployeePersonInMultipleProposals(Integer rolodexId, DevelopmentProposal childProposal);

    boolean needToExtendProjectDate(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal);
    
    boolean needToExtendProjectDate(DevelopmentProposal hierarchyProposal);

    List<ProposalHierarchyErrorWarningDto> validateIsAggregatorOnChild(DevelopmentProposal childProposal);
    
    void synchronizeChildBudget(DevelopmentProposal hierarchyProposal, ProposalDevelopmentBudgetExt budget);

    DevelopmentProposal getHierarchy(String hierarchyProposalNumber);

    ProposalState getProposalState(String proposalNumber);

    void reinstateDegreeInfo(DevelopmentProposal proposal);

}
