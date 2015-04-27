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
package org.kuali.coeus.propdev.impl.state;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyService;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("proposalStateService")
public class ProposalStateServiceImpl implements ProposalStateService {
    
	@Autowired
	@Qualifier("proposalHierarchyService")
	private ProposalHierarchyService proposalHierarchyService;
    
    @Override
    public String getProposalStateTypeCode(ProposalDevelopmentDocument proposalDevelopmentDocument, boolean isRouteStatusChanged, boolean isRejectAction ) {
        WorkflowDocument wd = proposalDevelopmentDocument.getDocumentHeader().getWorkflowDocument();
        
        if (wd.isInitiated()) {
            return ProposalState.IN_PROGRESS;
        } else if (wd.isSaved()) {
            return computeProposalStateForSaved(proposalDevelopmentDocument);
        } else if( isRejectAction && wd.isEnroute()  ) {
            return ProposalState.REVISIONS_REQUESTED;
        } else if (wd.isEnroute()) {
            return computeProposalStateForEnRoute(proposalDevelopmentDocument);
        } else if (wd.isApproved()) {
            return computeProposalStateForApproved(proposalDevelopmentDocument, isRouteStatusChanged);
        } else if (wd.isDisapproved()) {
            return computeProposalStateForDisapproved(proposalDevelopmentDocument, isRouteStatusChanged);
        } else if (wd.isCanceled()) {
            return ProposalState.CANCELED;
        } else {
            return ProposalState.DOCUMENT_ERROR;
        }
    }

    protected String computeProposalStateForSaved(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        if (isSubmitted(proposalDevelopmentDocument)) {
            return ProposalState.APPROVAL_NOT_INITIATED_SUBMITTED;
        } else {
            return ProposalState.IN_PROGRESS;
        }
    }

    protected String computeProposalStateForEnRoute(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        if (isSubmitted(proposalDevelopmentDocument)) {
            return ProposalState.APPROVAL_PENDING_SUBMITTED;
        } else {
            return ProposalState.APPROVAL_PENDING;
        }
    }

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

    protected String computeProposalStateForDisapproved(ProposalDevelopmentDocument proposalDevelopmentDocument, boolean isRouteStatusChanged) {
        if (isSubmitted(proposalDevelopmentDocument) && isRouteStatusChanged) {
            return ProposalState.DISAPPROVED_POST_SUBMISSION;
        } else {
            return ProposalState.DISAPPROVED;
        }
    }

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
