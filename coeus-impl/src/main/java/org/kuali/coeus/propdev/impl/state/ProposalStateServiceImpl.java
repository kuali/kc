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
package org.kuali.coeus.propdev.impl.state;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyService;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("proposalStateService")
public class ProposalStateServiceImpl implements ProposalStateService {
    
	@Autowired
	@Qualifier("proposalHierarchyService")
	private ProposalHierarchyService proposalHierarchyService;

    @Autowired
    @Qualifier("kcWorkflowService")
    private KcWorkflowService kcWorkflowService;

    @Override
    public String getProposalStateTypeCode(ProposalDevelopmentDocument proposalDevelopmentDocument, boolean isRejectAction) {
        final WorkflowDocument wd = proposalDevelopmentDocument.getDocumentHeader().getWorkflowDocument();
        if (wd.isInitiated()) {
            return ProposalState.IN_PROGRESS;
        } else if (wd.isSaved()) {
            return computeProposalStateForSaved(proposalDevelopmentDocument, isRejectAction);
        } else if (wd.isEnroute()) {
            return computeProposalStateForEnRoute(proposalDevelopmentDocument, isRejectAction);
        } else if (wd.isApproved()) {
            return computeProposalStateForApproved(proposalDevelopmentDocument);
        } else if (wd.isDisapproved()) {
            return computeProposalStateForDisapproved(proposalDevelopmentDocument);
        } else if (wd.isCanceled()) {
            return ProposalState.CANCELED;
        } else {
            return ProposalState.DOCUMENT_ERROR;
        }
    }

    protected String computeProposalStateForSaved(ProposalDevelopmentDocument proposalDevelopmentDocument, boolean isRejectAction) {
        if (isSubmitted(proposalDevelopmentDocument)) {
            return ProposalState.APPROVAL_NOT_INITIATED_SUBMITTED;
        } else if (isRejectAction) {
            return ProposalState.REVISIONS_REQUESTED;
        } else {
            return ProposalState.IN_PROGRESS;
        }
    }

    protected String computeProposalStateForEnRoute(ProposalDevelopmentDocument proposalDevelopmentDocument, boolean isRejectAction) {
        String proposalStateTypeCode = proposalDevelopmentDocument.getDevelopmentProposal().getProposalStateTypeCode();
        if (isRejectAction) {
        	return ProposalState.REVISIONS_REQUESTED;
        } else if ((isSubmitted(proposalDevelopmentDocument) && !isFinalApproval(proposalDevelopmentDocument.getDocumentHeader().getWorkflowDocument())) ||
                StringUtils.equals(proposalStateTypeCode,ProposalState.APPROVAL_PENDING_SUBMITTED)) {
            return ProposalState.APPROVAL_PENDING_SUBMITTED;
        } else {
            return ProposalState.APPROVAL_PENDING;
        }
    }

    protected boolean isFinalApproval(WorkflowDocument workflowDocument) {
        return StringUtils.isNotEmpty(workflowDocument.getDocumentId())
                && getKcWorkflowService().isFinalApproval(workflowDocument);
    }

    protected String computeProposalStateForApproved(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        String proposalStateTypeCode = proposalDevelopmentDocument.getDevelopmentProposal().getProposalStateTypeCode();
        if (isSubmitted(proposalDevelopmentDocument)) {
            if (StringUtils.equals(proposalStateTypeCode,ProposalState.APPROVAL_PENDING_SUBMITTED) ||
                    StringUtils.equals(proposalStateTypeCode,ProposalState.APPROVED_POST_SUBMISSION)) {
                return ProposalState.APPROVED_POST_SUBMISSION;
            } else {
                return ProposalState.APPROVED_AND_SUBMITTED;
            }
        } else {
            return ProposalState.APPROVAL_GRANTED;
        }
    }

    protected String computeProposalStateForDisapproved(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        if (isSubmitted(proposalDevelopmentDocument)) {
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

    public KcWorkflowService getKcWorkflowService() {
        return kcWorkflowService;
    }

    public void setKcWorkflowService(KcWorkflowService kcWorkflowService) {
        this.kcWorkflowService = kcWorkflowService;
    }
}
