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
package org.kuali.kra.iacuc.actions;

import java.util.List;

import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.protocol.actions.ProtocolActionRequestService;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewerBeanBase;

public interface IacucProtocolActionRequestService extends ProtocolActionRequestService {

    public boolean isFullApprovalAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to create amendment
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isCreateAmendmentAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to create renewal
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isCreateRenewalAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to create renewal with amendment
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isCreateRenewalWithAmendmentAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to create continuation
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isCreateContinuationAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to create continuation with amendment
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isCreateContinuationWithAmendmentAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to assign protocol to agenda
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isAssignToAgendaAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to remove protocol from agenda
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isRemoveFromAgendaAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to perform protocol review not required action
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isProtocolReviewNotRequiredAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to grant admin approval
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isGrantAdminApprovalAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to return protocol to PI
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isReturnToPIAuthorized(IacucProtocolForm protocolForm);

    /**
     * This method is to check whether user is authorized to perform "request to" action
     * Also apply rules if any.
     * @param protocolForm
     * @param taskName
     * @return
     */
    public boolean isRequestActionAuthorized(IacucProtocolForm protocolForm, String taskName);
    
    /**
     * This method is to check whether user is authorized to disapprove a protocol
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isDisapproveProtocolAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to expire a protocol
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isExpireProtocolAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to terminate a protocol
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isTerminateProtocolAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized send protocol acknowledgment
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isAcknowledgementAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to suspend protocol
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isSuspendProtocolAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to put protocol on hold
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isHoldAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to remove hold set on a protocol
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isLiftHoldAuthorized(IacucProtocolForm protocolForm);

    /**
     * This method is to check whether user is authorized to return protocol for Specific Minor Revision
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isReturnForSMRAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to return protocol for Substantive Major Revision
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isReturnForSRRAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to deactivate protocol
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isDeactivateAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to open protocol for admin correction
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isOpenProtocolForAdminCorrectionAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to abandon protocol
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isAbandonAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to modify submission
     * Also apply rules if any.
     * @param protocolForm
     * @param reviewers
     * @return
     */
    public boolean isModifySubmissionActionAuthorized(IacucProtocolForm protocolForm, List<ProtocolReviewerBeanBase> reviewers);
    
    /**
     * This method is to check whether user is authorized to table protocol
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isTableProtocolAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to withdraw protocol administratively
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isAdministrativelyWithdrawProtocolAuthorized(IacucProtocolForm protocolForm);

    /**
     * This method is to check whether user is authorized to withdraw protocol
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isWithdrawProtocolAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to mark protocol as incomplete 
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isAdministrativelyMarkIncompleteProtocolAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to make sub-committee decision
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isSubmitCommitteeDecisionAuthorized(IacucProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to assign protocol to a committee
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isAssignCommitteeAuthorized(IacucProtocolForm protocolForm);

    public boolean isWithdrawRequestActionAuthorized(IacucProtocolForm protocolForm);

    
    /**
     * This method is to grant full approval
     * @param protocolForm
     * @throws Exception
     */
    public void grantFullApproval(IacucProtocolForm protocolForm) throws Exception;

    /**
     * This method to submit protocol for review
     * @param protocolForm
     * @param reviewers
     * @throws Exception
     */
    public void submitForReview(IacucProtocolForm protocolForm, List<ProtocolReviewerBeanBase> reviewers) throws Exception;

    /**
     * This method to create amendment
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String createAmendment(IacucProtocolForm protocolForm) throws Exception;
    
    /**
     * This method renew protocol
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String createRenewal(IacucProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to renew protocol with amendment
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String createRenewalWithAmendment(IacucProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to create continuation
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String createContinuation(IacucProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to create continuation with amendment
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String createContinuationWithAmendment(IacucProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to assign protocol to agenda
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String assignToAgenda(IacucProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to remove a protocol from agenda
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String removeFromAgenda(IacucProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to perform review not required action
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String protocolReviewNotRequired(IacucProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to grant admin approval
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String grantAdminApproval(IacucProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to perform "request to" actions
     * @param protocolForm
     * @param taskName
     * @return
     * @throws Exception
     */
    public String performRequestAction(IacucProtocolForm protocolForm, String taskName) throws Exception;
 
    /**
     * This method is to disapprove a protocol
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String disapproveProtocol(IacucProtocolForm protocolForm) throws Exception;

    /**
     * This method is to expire a protocol
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String expireProtocol(IacucProtocolForm protocolForm) throws Exception;
    
    /**
     * This method terminate a protocol
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String terminateProtocol(IacucProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to suspend a protocol
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String suspendProtocol(IacucProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to send acknowledgment
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String acknowledgement(IacucProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to put protocol on hold
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String hold(IacucProtocolForm protocolForm) throws Exception;

    /**
     * This method is to release the protocol
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String liftHold(IacucProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to return protocol for Specific Minor Revision
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String returnForSMR(IacucProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to return protocol for Substantive Major Revision
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String returnForSRR(IacucProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to return protocol back to PI
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String returnToPI(IacucProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to deactivate a protocol
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String deactivate(IacucProtocolForm protocolForm) throws Exception;
            
    /**
     * This method is to open protocol for admin correction
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String openProtocolForAdminCorrection(IacucProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to abandon a protocol
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String abandon(IacucProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to modify submission
     * @param protocolForm
     * @param reviewers
     * @return
     * @throws Exception
     */
    public String modifySubmissionAction(IacucProtocolForm protocolForm, List<ProtocolReviewerBeanBase> reviewers) throws Exception;
    
    /**
     * This method is to table protocol
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String tableProtocol(IacucProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to withdraw protocol administratively
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String administrativelyWithdrawProtocol(IacucProtocolForm protocolForm) throws Exception;

    /**
     * This method is to withdraw protocol 
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String withdrawProtocol(IacucProtocolForm protocolForm) throws Exception;

    /**
     * This method is to notify protocol 
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String notifyProtocol(IacucProtocolForm protocolForm) throws Exception;
    
    /**
     * This method to send review notification
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String sendReviewDeterminationNotificationAction(IacucProtocolForm protocolForm) throws Exception;
 
    /**
     * This method mark protocol as incomplete
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String administrativelyMarkIncompleteProtocol(IacucProtocolForm protocolForm) throws Exception;
 
    /**
     * This method is to submit committee decision
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String submitCommitteeDecision(IacucProtocolForm protocolForm) throws Exception;
 
    /**
     * This method is to assign committee to protocol
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String assignCommittee(IacucProtocolForm protocolForm) throws Exception;
    
    /**
     * Withdraw a previously submitted request
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String withdrawRequestAction(IacucProtocolForm protocolForm) throws Exception;

    public String performNotificationRendering(IacucProtocolForm protocolForm, List<ProtocolReviewerBeanBase> beans);

}
