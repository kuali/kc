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
package org.kuali.kra.irb.actions;

import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.protocol.actions.ProtocolActionRequestService;

public interface IrbProtocolActionRequestService extends ProtocolActionRequestService {
    
    /**
     * This method is to check if user is authorized to perform expedited approval action
     * on a protocol.
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isExpeditedApprovalAuthorized(ProtocolForm protocolForm);
    
    /**
     * This method is to check if user is authorized to perform full approval action
     * on a protocol
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isFullApprovalAuthorized(ProtocolForm protocolForm);

    /**
     * This method is to check whether user is authorized to create renewal
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isCreateRenewalAuthorized(ProtocolForm protocolForm);

    /**
     * This method is to check whether user is authorized to assign protocol to agenda
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isAssignToAgendaAuthorized(ProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to create amendment
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isCreateAmendmentAuthorized(ProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to create renewal with amendment
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isCreateRenewalWithAmendmentAuthorized(ProtocolForm protocolForm);

    /**
     * This method is to check whether user is authorized to withdraw protocol
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isWithdrawProtocolAuthorized(ProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to perform response approval
     * action
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isResponseApprovalAuthorized(ProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to perform "request to" action
     * Also apply rules if any.
     * @param protocolForm
     * @param taskName
     * @return
     */
    public boolean isRequestActionAuthorized(ProtocolForm protocolForm, String taskName);
    
    /**
     * This method is to check whether user is authorized to close protocol
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isCloseProtocolAuthorized(ProtocolForm protocolForm);

    /**
     * This method is to check whether user is authorized to perform close enrollment action
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isCloseEnrollmentAuthorized(ProtocolForm protocolForm);

    /**
     * This method is to check whether user is authorized to defer protocol
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isDeferProtocolAuthorized(ProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to disapprove protocol
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isDisapproveProtocolAuthorized(ProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to expire protocol
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isExpireProtocolAuthorized(ProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to grant exemption
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isGrantExemptionAuthorized(ProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to perform IRB acknowledgment
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isIrbAcknowledgementAuthorized(ProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to perform data analysis
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isPermitDataAnalysisAuthorized(ProtocolForm protocolForm);

    /**
     * This method is to check whether user is authorized to reopen protocol
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isReopenEnrollmentAuthorized(ProtocolForm protocolForm);

    /**
     * This method is to check whether user is authorized to perform Minor Revision
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isReturnForSMRAuthorized(ProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to perform Substantive Revision
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isReturnForSRRAuthorized(ProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to return protocol to PI
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isReturnToPIAuthorized(ProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to suspend protocol
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isSuspendAuthorized(ProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to perform suspend by DSMB action
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isSuspendByDsmbAuthorized(ProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to terminate protocol.
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isTerminateAuthorized(ProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to manage comments
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isManageCommentsAuthorized(ProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to perform admin correction.
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isOpenProtocolForAdminCorrectionAuthorized(ProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to submit committee decision
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isSubmitCommitteeDecisionAuthorized(ProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to abandon protocol.
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isAbandonAuthorized(ProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to perform review not required action.
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isProtocolReviewNotRequiredAuthorized(ProtocolForm protocolForm);
    
    /**
     * This method is to check whether user is authorized to assign reviewers
     * Also apply rules if any.
     * @param protocolForm
     * @return
     */
    public boolean isAssignReviewersAuthorized(ProtocolForm protocolForm);
    

    public boolean isWithdrawRequestActionAuthorized(ProtocolForm protocolForm);


    /**
     * This method is to grant expedited approval on irb protocol
     * @param protocolForm
     * @throws Exception
     */
    public void grantExpeditedApproval(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to grant full approval on irb protocol
     * @param protocolForm
     * @throws Exception
     */
    public void grantFullApproval(ProtocolForm protocolForm) throws Exception;
    
    
    /**
     * This method is to submit irb protocol and return if we need to notify user
     * @param protocolForm
     * @return boolean to indicate whether to prompt user
     * @throws Exception
     */
    public boolean submitForReviewAndPromptToNotifyUser(ProtocolForm protocolForm, boolean sendNotification) throws Exception;
    
    /**
     * This method is to create protocol renewal
     * Notification prompt name indicates the forward name to prompt user
     * @param protocolForm
     * @return String path to prompt/redirect user
     * @throws Exception
     */
    public String createRenewal(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to assign protocol to agenda
     * @param protocolForm
     * @return String path to prompt/redirect user
     * @throws Exception
     */
    public String assignToAgenda(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to create protocol amendment
     * @param protocolForm
     * @return String path to prompt/redirect user
     * @throws Exception
     */
    public String createAmendment(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to create protocol renewal with amendment
     * @param protocolForm
     * @return String path to prompt/redirect user
     * @throws Exception
     */
    public String createRenewalWithAmendment(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to withdraw a protocol
     * @param protocolForm
     * @return String path to prompt/redirect user
     * @throws Exception
     */
    public String withdrawProtocol(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to grant response approval
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String grantResponseApproval(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to perform "request to" actions
     * @param protocolForm
     * @param taskName
     * @return
     * @throws Exception
     */
    public String performRequestAction(ProtocolForm protocolForm, String taskName) throws Exception;

    /**
     * This method is to withdraw a previously submitted "request to" action
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String withdrawRequestAction(ProtocolForm protocolForm) throws Exception;

    /**
     * This method is to close protocol
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String closeProtocol(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to close enrollment
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String closeEnrollment(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to defer protocol
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String deferProtocol(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to disapprove protocol
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String disapproveProtocol(ProtocolForm protocolForm) throws Exception;

    /**
     * This method is to expire protocol
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String expireProtocol(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to grant exemption
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String grantExemption(ProtocolForm protocolForm) throws Exception;
    
    
    /**
     * This method is to perform IRB acknowledgment
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String irbAcknowledgement(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to perform data analysis
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String permitDataAnalysis(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to reopen for enrollment
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String reopenEnrollment(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to perform Specific Minor Revision
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String returnForSMR(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to perform Substantive Revision Required
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String returnForSRR(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to return protocol to PI
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String returnToPI(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to suspend a protocol
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String suspend(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to suspend a protocol - suspend by DSMB action
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String suspendByDsmb(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to termnate a protocol
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String terminate(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to perform manage comments action
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String manageComments(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to perform admin correction on a protocol
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String openProtocolForAdminCorrection(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is triggered when committee decision is made
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String submitCommitteeDecision(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to abandon a protocol
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String abandon(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to perform notify irb protocol action
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String notifyIrbProtocol(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to perform notify committee protocol action
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String notifyCommitteeProtocol(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to perform protocol review not required action
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String protocolReviewNotRequired(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to trigger correspondence when reviewers are assigned
     * @param protocolForm
     * @return
     * @throws Exception
     */
    public String assignReviewers(ProtocolForm protocolForm) throws Exception;
        
    /**
     * This method is to trigger correspondence when assigned review is complete
     * @param protocolForm
     * @throws Exception
     */
    public void assignedReviewComplete(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to trigger correspondence for assigned review rejected / returned
     * to reviewer action
     * @param protocolForm
     * @throws Exception
     */
    public void assignedReviewRejected(ProtocolForm protocolForm) throws Exception;

    /**
     * This method is to trigger correspondence for assigned review deleted action
     * @param protocolForm
     * @throws Exception
     */
    public void assignedReviewDeleted(ProtocolForm protocolForm) throws Exception;
    
    /**
     * This method is to trigger correspondence for funding source action
     * @param protocolForm
     * @throws Exception
     */
    public void generateFundingSource(ProtocolForm protocolForm) throws Exception;
    
}
