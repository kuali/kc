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
package org.kuali.kra.iacuc.actions.withdraw;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionStatus;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionType;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.actions.withdraw.ProtocolAdministrativelyIncompleteBean;
import org.kuali.kra.protocol.actions.withdraw.ProtocolAdministrativelyWithdrawBean;
import org.kuali.kra.protocol.actions.withdraw.ProtocolWithdrawBean;
import org.kuali.kra.protocol.actions.withdraw.ProtocolWithdrawServiceImplBase;

import java.sql.Date;




public class IacucProtocolWithdrawServiceImpl extends ProtocolWithdrawServiceImplBase implements IacucProtocolWithdrawService {
    
    @Override
    public ProtocolDocumentBase administrativelyMarkIncomplete(ProtocolBase protocol, ProtocolAdministrativelyIncompleteBean markIncompleteBean) throws Exception {
        String protocolWithdrawnActionTypeCode = IacucProtocolActionType.ADMINISTRATIVELY_INCOMPLETE;
        String protocolWithdrawnStatusCode = IacucProtocolStatus.ADMINISTRATIVELY_INCOMPLETE;
        String protocolWithdrawnSubmissionStatusCode = IacucProtocolSubmissionStatus.ADMINISTRATIVELY_INCOMPLETE;
        return this.commonWithdrawLogic(protocol, markIncompleteBean, protocolWithdrawnActionTypeCode, protocolWithdrawnStatusCode, protocolWithdrawnSubmissionStatusCode);
    }

    
    @Override
    public ProtocolDocumentBase administrativelyWithdraw(ProtocolBase protocol, ProtocolAdministrativelyWithdrawBean administrativelyWithdrawBean) throws Exception {
        String protocolWithdrawnActionTypeCode = IacucProtocolActionType.ADMINISTRATIVELY_WITHDRAWN;
        String protocolWithdrawnStatusCode = IacucProtocolStatus.ADMINISTRATIVELY_WITHDRAWN;
        String protocolWithdrawnSubmissionStatusCode = IacucProtocolSubmissionStatus.WITHDRAWN;
        return this.commonWithdrawLogic(protocol, administrativelyWithdrawBean, protocolWithdrawnActionTypeCode, protocolWithdrawnStatusCode, protocolWithdrawnSubmissionStatusCode);
    }
    
    
    @Override
    public ProtocolDocumentBase withdraw(ProtocolBase protocol, ProtocolWithdrawBean withdrawBean) throws Exception {
        String protocolWithdrawnActionTypeCode = IacucProtocolActionType.IACUC_WITHDRAWN;
        String protocolWithdrawnStatusCode = IacucProtocolStatus.WITHDRAWN;
        String protocolWithdrawnSubmissionStatusCode = IacucProtocolSubmissionStatus.WITHDRAWN; 
        return this.commonWithdrawLogic(protocol, withdrawBean,  protocolWithdrawnActionTypeCode, protocolWithdrawnStatusCode, protocolWithdrawnSubmissionStatusCode);
    }
    

    
    // the common withdrawal logic used by three service methods above
    private ProtocolDocumentBase commonWithdrawLogic(ProtocolBase protocol, 
                                                 ProtocolWithdrawBean withdrawBean, 
                                                 String protocolWithdrawnActionTypeCode,
                                                 String protocolWithdrawnStatusCode,
                                                 String protocolWithdrawnSubmissionStatusCode) throws Exception {        
        
        ProtocolSubmissionBase submission = getSubmission(protocol);
        ProtocolActionBase protocolAction = new IacucProtocolAction((IacucProtocol) protocol, (IacucProtocolSubmission) submission, protocolWithdrawnActionTypeCode);
        protocolAction.setComments(withdrawBean.getReason());
        protocol.getProtocolActions().add(protocolAction);

        boolean isVersion = IacucProtocolStatus.IN_PROGRESS.equals(protocol.getProtocolStatusCode()) ||
                            IacucProtocolStatus.SUBMITTED_TO_IACUC.equals(protocol.getProtocolStatusCode()) ||
                            IacucProtocolStatus.TABLED.equals(protocol.getProtocolStatusCode());
        
        protocolActionService.updateProtocolStatus(protocolAction, protocol);

        if (submission != null) {
            submission.setSubmissionDate(new Date(System.currentTimeMillis()));
            submission.setSubmissionStatusCode(protocolWithdrawnSubmissionStatusCode);
            // need to finalize any outstanding review documents.
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
            IacucProtocolDocument newProtocolDocument = (IacucProtocolDocument) protocolVersionService.versionProtocolDocument(protocol.getProtocolDocument());
            newProtocolDocument.getProtocol().setProtocolStatusCode(protocolWithdrawnStatusCode);
            // to force it to retrieve from list.
            newProtocolDocument.getProtocol().setProtocolSubmission(null);
            // update some info
            newProtocolDocument.getProtocol().setApprovalDate(null);
            newProtocolDocument.getProtocol().setLastApprovalDate(null);
            newProtocolDocument.getProtocol().setExpirationDate(null);

            newProtocolDocument.getProtocol().refreshReferenceObject("protocolStatus");
            documentService.saveDocument(newProtocolDocument);

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
        return StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.PENDING) ||
               StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
    }
    
    /**
     * Does the submission type allow us to withdraw the protocol?
     * @param submission
     * @return true if withdrawable; otherwise false
     */
    protected boolean isNormalSubmission(ProtocolSubmissionBase submission) {
        return StringUtils.equals(submission.getSubmissionTypeCode(), IacucProtocolSubmissionType.AMENDMENT) ||
               StringUtils.equals(submission.getSubmissionTypeCode(), IacucProtocolSubmissionType.INITIAL_SUBMISSION) ||
               StringUtils.equals(submission.getSubmissionTypeCode(), IacucProtocolSubmissionType.CONTINUATION) ||
               StringUtils.equals(submission.getSubmissionTypeCode(), IacucProtocolSubmissionType.CONTINUATION_WITH_AMENDMENT);
    }

    


}
