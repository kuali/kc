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
package org.kuali.kra.irb.actions.noreview;

import java.sql.Timestamp;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class manages the action of marking a protocol as review not required.
 */
public class ProtocolReviewNotRequiredServiceImpl implements ProtocolReviewNotRequiredService {
    
    private BusinessObjectService businessObjectService;
    private ProtocolActionService protocolActionService;

    public ProtocolActionService getProtocolActionService() {
        return protocolActionService;
    }

    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }

    

    /**
     * 
     * This method...
     * @param businessObjectService
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * @see org.kuali.kra.irb.actions.noreview.ProtocolReviewNotRequiredService#reviewNotRequired(org.kuali.kra.irb.ProtocolDocument, org.kuali.kra.irb.actions.noreview.ProtocolReviewNotRequiredBean)
     */
    public void reviewNotRequired(ProtocolDocument protocolDocument, ProtocolReviewNotRequiredBean actionBean) {
        Protocol protocol = protocolDocument.getProtocol();
        ProtocolAction protocolAction = new ProtocolAction(protocol, null, ProtocolActionType.IRB_REVIEW_NOT_REQUIRED);
        protocolAction.setComments(actionBean.getComments());
        protocolAction.setActionDate(new Timestamp(actionBean.getActionDate().getTime()));
        protocol.getProtocolActions().add(protocolAction);
        
        protocolActionService.updateProtocolStatus(protocolAction, protocol);     
        
        protocol.setApprovalDate(actionBean.getDecisionDate());
        
        protocol.refreshReferenceObject("protocolStatus");
        businessObjectService.save(protocolDocument.getProtocol());
    }
}