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
package org.kuali.kra.protocol.actions.withdraw;

import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolVersionService;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondenceBase;
import org.kuali.kra.protocol.actions.submit.ProtocolActionService;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

/**
 * The ProtocolWithdrawService implementation.
 */
public abstract class ProtocolWithdrawServiceImplBase implements ProtocolWithdrawService {

    protected DocumentService documentService;
    protected BusinessObjectService businessObjectService;
    protected ProtocolActionService protocolActionService;
    protected ProtocolVersionService protocolVersionService;
    
    protected ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService;
    
    protected ProtocolOnlineReviewService protocolOnlineReviewService;
 
    
    protected static final String WITHDRAW_FINALIZE_OLR_ANNOTATION = "Online Review finalized as part of withdraw action on protocol.";
    
    /**
     * Set the document service.
     * @param documentService
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    /**
     * Set the business object service.
     * @param businessObjectService the business object service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void setProtocolActionCorrespondenceGenerationService(ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService) {
        this.protocolActionCorrespondenceGenerationService = protocolActionCorrespondenceGenerationService;
    }
    
    /**
     * Set the ProtocolBase Action Service.
     * @param protocolActionService
     */
    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }
    
    /**
     * Inject ProtocolBase Version Service.
     * @param protocolVersionService
     */
    public void setProtocolVersionService(ProtocolVersionService protocolVersionService) {
        this.protocolVersionService = protocolVersionService;
    }

    
    public abstract ProtocolDocumentBase withdraw(ProtocolBase protocol, ProtocolWithdrawBean withdrawBean) throws Exception;        
    
    /**
     * 
     * This method will generate a correspondence document, and attach it to the protocol.
     * @param protocol a ProtocolBase object.
     */
    protected void generateCorrespondenceDocumentAndAttach(ProtocolBase protocol, ProtocolWithdrawBean withdrawBean) throws PrintingException {
        ProtocolActionsCorrespondenceBase correspondence = withdrawBean.getCorrespondence();         
        correspondence.setProtocol(protocol);
        protocolActionCorrespondenceGenerationService.generateCorrespondenceDocumentAndAttach(correspondence);
    } 

    /**
     * By canceling the protocol in workflow, we are preventing it from being
     * reviewed by the IRB office.  A user will then be able to continue editing
     * the protocol in order to submit it again at a later time.
     * @param protocol
     * @throws WorkflowException
     */
    protected void cancelWorkflow(ProtocolBase protocol) throws WorkflowException {
        documentService.cancelDocument(protocol.getProtocolDocument(), null);
    }
  

    /**
     * Get the submission that is being withdrawn.  Since a protocol can have
     * multiple submissions, go backwards until we find a submission that can
     * be withdrawn
     * @param protocol
     * @return
     */
    protected ProtocolSubmissionBase getSubmission(ProtocolBase protocol) {
        for (ProtocolSubmissionBase submission : protocol.getProtocolSubmissions()) {
            if (isWithdrawable(submission)) {
                return submission;
            }
        }
        return null;
    }

    /**
     * A submission is only withdrawable if it corresponds to a request to review
     * the submission.  The submissions that meet this criteria are the initial 
     * request for review, amendments, and renewals that are still in the pending
     * or submitted to committee states.   Submissions such as Notify IRB
     * cannot be withdrawn.
     * @param submission
     * @return
     */
    protected boolean isWithdrawable(ProtocolSubmissionBase submission) {
        return isAllowedStatus(submission) && isNormalSubmission(submission);
    }
    
    protected abstract boolean isAllowedStatus(ProtocolSubmissionBase submission);
    
    protected abstract boolean isNormalSubmission(ProtocolSubmissionBase submission);

    public void setProtocolOnlineReviewService(ProtocolOnlineReviewService protocolOnlineReviewService) {
        this.protocolOnlineReviewService = protocolOnlineReviewService;
    }

    // new addition developed for IACUC, has to be backfitted to IRB
    public abstract ProtocolDocumentBase administrativelyWithdraw(ProtocolBase protocol, ProtocolAdministrativelyWithdrawBean administrativelyWithdrawBean) throws Exception;
    
    // new addition developed for IACUC, has to be backfitted to IRB
    public abstract ProtocolDocumentBase administrativelyMarkIncomplete(ProtocolBase protocol, ProtocolAdministrativelyIncompleteBean markIncompleteBean) throws Exception;
 
 
}
