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
package org.kuali.kra.irb.actions.withdraw;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.assignagenda.ProtocolAssignToAgendaService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.actions.withdraw.ProtocolAdministrativelyIncompleteBean;
import org.kuali.kra.protocol.actions.withdraw.ProtocolAdministrativelyWithdrawBean;
import org.kuali.kra.protocol.actions.withdraw.ProtocolWithdrawBean;
import org.kuali.kra.protocol.actions.withdraw.ProtocolWithdrawServiceImplBase;

import java.sql.Date;

/**
 * The ProtocolWithdrawService implementation.
 */
public class ProtocolWithdrawServiceImpl extends ProtocolWithdrawServiceImplBase implements ProtocolWithdrawService {

    private ProtocolAssignToAgendaService protocolAssignToAgendaService;
    
    @Override
    public ProtocolDocumentBase administrativelyMarkIncomplete(ProtocolBase protocol, ProtocolAdministrativelyIncompleteBean markIncompleteBean) throws Exception {
        // TODO yet to implement this functionality in IRB, maybe after backfit we can refactor the withdraw method into a commonWithdrawLogic as is done for IACUC service impl.
        return null;
    }

    
    @Override
    public ProtocolDocumentBase administrativelyWithdraw(ProtocolBase protocol, ProtocolAdministrativelyWithdrawBean administrativelyWithdrawBean) throws Exception {
        // TODO yet to implement this functionality in IRB, maybe after backfit we can refactor the withdraw method into a commonWithdrawLogic as is done for IACUC service impl.
        return null;
    }
    
    
    @Override
    public ProtocolDocumentBase withdraw(ProtocolBase protocol, ProtocolWithdrawBean withdrawBean) throws Exception {
        ProtocolSubmission submission = (ProtocolSubmission)getSubmission(protocol);
        ProtocolAction protocolAction = new ProtocolAction((Protocol)protocol, submission, ProtocolActionType.WITHDRAWN);
        protocolAction.setComments(withdrawBean.getReason());
        protocol.getProtocolActions().add(protocolAction);

        boolean isVersion = ProtocolStatus.IN_PROGRESS.equals(protocol.getProtocolStatusCode())
              || ProtocolStatus.SUBMITTED_TO_IRB.equals(protocol.getProtocolStatusCode());
        protocolActionService.updateProtocolStatus(protocolAction, protocol);

       
        if (submission != null) {
            submission.setSubmissionDate(new Date(System.currentTimeMillis()));
            submission.setSubmissionStatusCode(ProtocolSubmissionStatus.WITHDRAWN);
            // need to finalize any outstanding review documents.
            protocolOnlineReviewService.finalizeOnlineReviews(submission, WITHDRAW_FINALIZE_OLR_ANNOTATION);
        }
        businessObjectService.save(protocol.getProtocolDocument());
        if (isVersion) {
            /*
             * Cancelling the workflow document is how we withdraw it.
             */
            cancelWorkflow(protocol);

            
            /*
             * Create a new protocol document for the user to edit so they can re-submit at a later time.
             */
            ProtocolDocument newProtocolDocument = (ProtocolDocument)protocolVersionService.versionProtocolDocument(protocol.getProtocolDocument());
            newProtocolDocument.getProtocol().setProtocolStatusCode(ProtocolStatus.WITHDRAWN);
            // to force it to retrieve from list.
            newProtocolDocument.getProtocol().setProtocolSubmission(null);
            // update some info
            newProtocolDocument.getProtocol().setApprovalDate(null);
            newProtocolDocument.getProtocol().setLastApprovalDate(null);
            newProtocolDocument.getProtocol().setExpirationDate(null);

            newProtocolDocument.getProtocol().refreshReferenceObject("protocolStatus");
            documentService.saveDocument(newProtocolDocument);

            // if there is an assign to agenda protocol action, remove it.
            ProtocolAction assignToAgendaProtocolAction = (ProtocolAction) protocolAssignToAgendaService
                    .getAssignedToAgendaProtocolAction(newProtocolDocument.getProtocol());
            if (assignToAgendaProtocolAction != null) {
                newProtocolDocument.getProtocol().getProtocolActions().remove(assignToAgendaProtocolAction);
                businessObjectService.delete(assignToAgendaProtocolAction);
            }
            newProtocolDocument.getProtocol().refreshReferenceObject("protocolStatus");
            documentService.saveDocument(newProtocolDocument);
            return newProtocolDocument;
        }
        return protocol.getProtocolDocument();
    }
    
    
    /**
     * Does the submission status allow us to withdraw the protocol?
     * @param submission
     * @return true if withdrawable; otherwise false
     */
    protected boolean isAllowedStatus(ProtocolSubmissionBase submission) {
        return StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.PENDING) ||
               StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
    }
    
    /**
     * Does the submission type allow us to withdraw the protocol?
     * @param submission
     * @return true if withdrawable; otherwise false
     */
    protected boolean isNormalSubmission(ProtocolSubmissionBase submission) {
        return StringUtils.equals(submission.getSubmissionTypeCode(), ProtocolSubmissionType.AMENDMENT) ||
               StringUtils.equals(submission.getSubmissionTypeCode(), ProtocolSubmissionType.INITIAL_SUBMISSION) ||
               StringUtils.equals(submission.getSubmissionTypeCode(), ProtocolSubmissionType.CONTINUATION) ||
               StringUtils.equals(submission.getSubmissionTypeCode(), ProtocolSubmissionType.CONTINUATION_WITH_AMENDMENT);
    }


    public ProtocolAssignToAgendaService getProtocolAssignToAgendaService() {
        return protocolAssignToAgendaService;
    }


    public void setProtocolAssignToAgendaService(ProtocolAssignToAgendaService protocolAssignToAgendaService) {
        this.protocolAssignToAgendaService = protocolAssignToAgendaService;
    }
}
