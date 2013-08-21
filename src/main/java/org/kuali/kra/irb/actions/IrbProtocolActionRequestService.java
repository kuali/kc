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
     * on a protocol
     * This method...
     * @param protocolForm
     * @return
     */
    public boolean isExpeditedApprovalAuthorized(ProtocolForm protocolForm);
    
    /**
     * This method is to check if user is authorized to perform full approval action
     * on a protocol
     * @param protocolForm
     * @return
     */
    public boolean isFullApprovalAuthorized(ProtocolForm protocolForm);

    /**
     * This method is to check whether user is authorized to create renewal
     * @param protocolForm
     * @return
     */
    public boolean isCreateRenewalAuthorized(ProtocolForm protocolForm);

    public boolean isAssignToAgendaAuthorized(ProtocolForm protocolForm);
    
    public boolean isCreateAmendmentAuthorized(ProtocolForm protocolForm);
    
    public boolean isCreateRenewalWithAmendmentAuthorized(ProtocolForm protocolForm);

    public boolean isWithdrawProtocolAuthorized(ProtocolForm protocolForm);
    
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
    
}
