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
package org.kuali.kra.proposaldevelopment.service.impl;

import org.kuali.kra.proposaldevelopment.bo.ProposalState;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService;
import org.kuali.kra.proposaldevelopment.service.ProposalStateService;
import org.kuali.rice.kew.api.WorkflowDocument;

/**
 * Proposal State Service Implementation.
 */
public class ProposalStateServiceImpl implements ProposalStateService {
    
    private ProposalHierarchyService proposalHierarchyService;
    
    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalStateService#getProposalStateTypeCode(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, boolean)
     */
    public String getProposalStateTypeCode(ProposalDevelopmentDocument proposalDevelopmentDocument, boolean isRouteStatusChanged, boolean isRejectAction ) {
        String proposalStateTypeCode = null;
        WorkflowDocument wd = proposalDevelopmentDocument.getDocumentHeader().getWorkflowDocument();
        
        if (wd.isInitiated()) {
            proposalStateTypeCode = computeProposalStateForInitiated(proposalDevelopmentDocument);
        } else if (wd.isSaved()) {
            proposalStateTypeCode = computeProposalStateForSaved(proposalDevelopmentDocument);
        } else if( isRejectAction && wd.isEnroute()  ) {
            proposalStateTypeCode = computeProposalStateForRejected( proposalDevelopmentDocument );
        } else if (wd.isEnroute()) {
            proposalStateTypeCode = computeProposalStateForEnRoute(proposalDevelopmentDocument);
        } else if (wd.isApproved()) {
            proposalStateTypeCode = computeProposalStateForApproved(proposalDevelopmentDocument, isRouteStatusChanged);
        } else if (wd.isDisapproved()) {
            proposalStateTypeCode = computeProposalStateForDisapproved(proposalDevelopmentDocument, isRouteStatusChanged);
        } else if (wd.isCanceled()) {
            proposalStateTypeCode = computeProposalStateForCanceled(proposalDevelopmentDocument);
        } else {
            proposalStateTypeCode = computeProposalStateForException(proposalDevelopmentDocument);
        }
        return proposalStateTypeCode;
    }
    
    /**
     * Compute the proposal state when the proposal is in the workflow INITIATED state.
     * @param proposalDevelopmentDocument the proposal development document
     * @return IN_PROGRESS
     */
    protected String computeProposalStateForInitiated(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        return ProposalState.IN_PROGRESS;
    }
    
    /**
     * Compute the proposal state when the proposal is in the workflow SAVED state.
     * @param proposalDevelopmentDocument the proposal development document
     * @return APPROVAL_NOT_INITIATED_SUBMITTED or IN_PROGRESS
     */
    protected String computeProposalStateForSaved(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        if (isSubmitted(proposalDevelopmentDocument)) {
            return ProposalState.APPROVAL_NOT_INITIATED_SUBMITTED;
        } else {
            return ProposalState.IN_PROGRESS;
        }
    }
    
    /**
     * Compute the proposal state when the proposal is in the workflow ENROUTE state.
     * @param proposalDevelopmentDocument the proposal development document
     * @return APPROVAL_PENDING_SUBMITTED or APPROVAL_PENDING
     */
    protected String computeProposalStateForEnRoute(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        if (isSubmitted(proposalDevelopmentDocument)) {
            return ProposalState.APPROVAL_PENDING_SUBMITTED;
        } else {
            return ProposalState.APPROVAL_PENDING;
        }
    }
    
    /**
     * Compute the proposal state when the proposal is in the workflow APPROVED state.
     * @param proposalDevelopmentDocument the proposal development document
     * @param isRouteStatusChanged was the route status just changed (if false, the proposal was submitted to the sponsor)
     * @return APPROVED_AND_SUBMITTED, APPROVED_POST_SUBMISSION, or APPROVAL_GRANTED
     */
    protected String computeProposalStateForApproved(ProposalDevelopmentDocument proposalDevelopmentDocument, boolean isRouteStatusChanged) {
        if (isSubmitted(proposalDevelopmentDocument)) {
            if (isRouteStatusChanged) {
                return ProposalState.APPROVED_POST_SUBMISSION;
            } else {
                return ProposalState.APPROVED_AND_SUBMITTED;
            }
        } else {
            return ProposalState.APPROVAL_GRANTED;
        }
    }
    
    /**
     * Compute the proposal state when the proposal is in the workflow DISAPPROVED state.
     * @param proposalDevelopmentDocument the proposal development document
     * @param isRouteStatusChanged was the route status just changed (if false, the proposal was submitted to the sponsor)
     * @return DISAPPROVED or DISAPPROVED_POST_SUBMISSION
     */
    protected String computeProposalStateForDisapproved(ProposalDevelopmentDocument proposalDevelopmentDocument, boolean isRouteStatusChanged) {
        if (isSubmitted(proposalDevelopmentDocument) && isRouteStatusChanged) {
            return ProposalState.DISAPPROVED_POST_SUBMISSION;
        } else {
            return ProposalState.DISAPPROVED;
        }
    }
   
    /**
     * Compute the proposal state when the proposal has been rejected ( sent to initial node ).
     * @param proposalDevelopmentDocument the proposal development document
     * @param isRouteStatusChanged was the route status just changed (if false, the proposal was submitted to the sponsor)
     * @return DISAPPROVED or DISAPPROVED_POST_SUBMISSION
     */
    protected String computeProposalStateForRejected(ProposalDevelopmentDocument proposalDevelopmentDocument ) {
        return ProposalState.REVISIONS_REQUESTED;
    }
   
    
   
    /**
     * Compute the proposal state when the proposal is in the workflow CANCELED state.
     * @param proposalDevelopmentDocument the proposal development document
     * @return CANCELED
     */
    protected String computeProposalStateForCanceled(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        return ProposalState.CANCELED;
    }

    
    /**
     * Compute the proposal state when the proposal is in the workflow EXCEPTION state.
     * @param proposalDevelopmentDocument the proposal development document
     * @return DOCUMENT_ERROR
     */
    protected String computeProposalStateForException(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        return ProposalState.DOCUMENT_ERROR;
    }
    
    /**
     * Has the proposal been submitted to the sponsor?
     * @param proposalDevelopmentDocument the proposal development document
     * @return true if submitted to the sponsor; otherwise false
     */
    protected boolean isSubmitted(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        return proposalDevelopmentDocument.getDevelopmentProposal().getSubmitFlag();
    }

    public ProposalHierarchyService getProposalHierarchyService() {
        return proposalHierarchyService;
    }

    public void setProposalHierarchyService(ProposalHierarchyService proposalHierarchyService) {
        this.proposalHierarchyService = proposalHierarchyService;
    } 
    
}
