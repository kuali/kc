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
package org.kuali.kra.proposaldevelopment.service.impl;

import org.kuali.core.workflow.service.KualiWorkflowDocument;
import org.kuali.kra.proposaldevelopment.bo.ProposalState;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalStateService;

/**
 * Proposal State Service Implementation.
 */
public class ProposalStateServiceImpl implements ProposalStateService {
    
    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalStateService#getProposalStateTypeCode(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public String getProposalStateTypeCode(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        String proposalStateTypeCode = null;
        KualiWorkflowDocument wd = proposalDevelopmentDocument.getDocumentHeader().getWorkflowDocument();
        if (wd.stateIsInitiated()) {
            proposalStateTypeCode = computeProposalStateForInitiated(proposalDevelopmentDocument);
        } else if (wd.stateIsSaved()) {
            proposalStateTypeCode = computeProposalStateForSaved(proposalDevelopmentDocument);
        } else if (wd.stateIsEnroute()) {
            proposalStateTypeCode = computeProposalStateForEnRoute(proposalDevelopmentDocument);
        } else if (wd.stateIsApproved()) {
            proposalStateTypeCode = computeProposalStateForApproved(proposalDevelopmentDocument);
        } else if (wd.stateIsDisapproved()) {
            proposalStateTypeCode = computeProposalStateForDisapproved(proposalDevelopmentDocument);
        } else if (wd.stateIsCanceled()) {
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
    private String computeProposalStateForInitiated(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        return ProposalState.IN_PROGRESS;
    }
    
    /**
     * Compute the proposal state when the proposal is in the workflow SAVED state.
     * @param proposalDevelopmentDocument the proposal development document
     * @return APPROVAL_NOT_INITIATED_SUBMITTED or IN_PROGRESS
     */
    private String computeProposalStateForSaved(ProposalDevelopmentDocument proposalDevelopmentDocument) {
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
    private String computeProposalStateForEnRoute(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        if (isSubmitted(proposalDevelopmentDocument)) {
            return ProposalState.APPROVAL_PENDING_SUBMITTED;
        } else {
            return ProposalState.APPROVAL_PENDING;
        }
    }
    
    /**
     * Compute the proposal state when the proposal is in the workflow APPROVED state.
     * @param proposalDevelopmentDocument the proposal development document
     * @return APPROVED_AND_SUBMITTED or APPROVAL_GRANTED
     */
    private String computeProposalStateForApproved(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        if (isSubmitted(proposalDevelopmentDocument)) {
            return ProposalState.APPROVED_AND_SUBMITTED;
        } else {
            return ProposalState.APPROVAL_GRANTED;
        }
    }
    
    /**
     * Compute the proposal state when the proposal is in the workflow DISAPPROVED state.
     * @param proposalDevelopmentDocument the proposal development document
     * @return DISAPPROVED
     */
    private String computeProposalStateForDisapproved(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        return ProposalState.DISAPPROVED;
    }
    
    /**
     * Compute the proposal state when the proposal is in the workflow CANCELED state.
     * @param proposalDevelopmentDocument the proposal development document
     * @return CANCELED
     */
    private String computeProposalStateForCanceled(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        return ProposalState.CANCELED;
    }
    
    /**
     * Compute the proposal state when the proposal is in the workflow EXCEPTION state.
     * @param proposalDevelopmentDocument the proposal development document
     * @return DOCUMENT_ERROR
     */
    private String computeProposalStateForException(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        return ProposalState.DOCUMENT_ERROR;
    }
    
    /**
     * Has the proposal been submitted to the sponsor?
     * @param proposalDevelopmentDocument the proposal development document
     * @return true if submitted to the sponsor; otherwise false
     */
    private boolean isSubmitted(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        return proposalDevelopmentDocument.getSubmitFlag();
    }   
}
