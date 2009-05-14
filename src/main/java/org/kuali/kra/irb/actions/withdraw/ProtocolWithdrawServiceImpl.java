/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.actions.withdraw;

import java.sql.Timestamp;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * The ProtocolWithdrawService implementation.
 */
public class ProtocolWithdrawServiceImpl implements ProtocolWithdrawService {

    private static final String NEXT_ACTION_ID_KEY = "actionId";
    
    private BusinessObjectService businessObjectService;

    /**
     * Set the business object service.
     * @param businessObjectService the business object service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * @see org.kuali.kra.irb.actions.withdraw.ProtocolWithdrawService#withdraw(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.actions.withdraw.ProtocolWithdrawBean)
     */
    public void withdraw(Protocol protocol, ProtocolWithdrawBean withdrawBean) {
        ProtocolAction protocolAction = createProtocolAction(protocol, withdrawBean);
        protocol.getProtocolActions().add(protocolAction);
        businessObjectService.save(protocol.getProtocolDocument());
    }
    
    /**
     * Create a Protocol Action for a Withdrawal that will be written to the database.
     * @param protocol the protocol
     * @param withdrawBean the withdrawal data
     * @return
     */
    public ProtocolAction createProtocolAction(Protocol protocol, ProtocolWithdrawBean withdrawBean) {
        ProtocolAction protocolAction = new ProtocolAction();
        protocolAction.setProtocolId(protocol.getProtocolId());
        protocolAction.setProtocolNumber(protocol.getProtocolNumber());
        protocolAction.setSequenceNumber(protocol.getSequenceNumber());
        protocolAction.setActionId(getNextValue(protocol, NEXT_ACTION_ID_KEY));
        protocolAction.setActionDate(new Timestamp(System.currentTimeMillis()));
        protocolAction.setProtocolActionTypeCode(ProtocolActionType.REQUEST_TO_WITHDRAW);
        protocolAction.setComments(withdrawBean.getReason());
        protocolAction.setSubmissionIdFk(protocol.getProtocolSubmission().getSubmissionId());
        protocolAction.setSubmissionNumber(protocol.getProtocolSubmission().getSubmissionNumber());
        return protocolAction;
    }
    
    /**
     * Get the next value in a sequence.
     * @param protocol the protocol
     * @param key the unique key of the sequence
     * @return the next value
     */
    private Integer getNextValue(Protocol protocol, String key) {
        ProtocolDocument protocolDocument = protocol.getProtocolDocument();
        return protocolDocument.getDocumentNextValue(key);
    }
}
