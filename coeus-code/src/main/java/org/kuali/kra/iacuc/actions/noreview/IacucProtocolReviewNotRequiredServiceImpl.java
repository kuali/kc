/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.iacuc.actions.noreview;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.protocol.actions.submit.ProtocolActionService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.sql.Timestamp;

/**
 * This class manages the action of marking a protocol as review not required.
 */
public class IacucProtocolReviewNotRequiredServiceImpl implements IacucProtocolReviewNotRequiredService {
    
    private BusinessObjectService businessObjectService;
    private ProtocolActionService protocolActionService;

    public ProtocolActionService getProtocolActionService() {
        return protocolActionService;
    }

    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }


    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    @Override
    public void reviewNotRequired(IacucProtocolDocument protocolDocument, IacucProtocolReviewNotRequiredBean actionBean) {
        IacucProtocol protocol = protocolDocument.getIacucProtocol();
        IacucProtocolAction protocolAction = new IacucProtocolAction(protocol, (IacucProtocolSubmission) protocol.getProtocolSubmission(), IacucProtocolActionType.IACUC_REVIEW_NOT_REQUIRED);
        protocolAction.setComments(actionBean.getComments());
        protocolAction.setActionDate(new Timestamp(actionBean.getActionDate().getTime()));
        protocol.getProtocolActions().add(protocolAction);
        protocolActionService.updateProtocolStatus(protocolAction, protocol);     
        protocol.setApprovalDate(actionBean.getDecisionDate());
        protocol.refreshReferenceObject("protocolStatus");
        businessObjectService.save(protocolDocument.getProtocol());
    }
}