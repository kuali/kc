/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.irb.actions;

import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.protocol.actions.ProtocolActionRequestService;

public interface IrbProtocolActionRequestService extends ProtocolActionRequestService {
    
    /**
     * This method is to check if user is authorized to perform expedited approval action
     * on a protocol.
     * Also apply rules if any.
     * This method...
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
    public boolean submitForReviewAndPromptToNotifyUser(ProtocolForm protocolForm) throws Exception;
    
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
     * @param notificationPromptName
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
    
    
    
}
