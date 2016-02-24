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
package org.kuali.kra.irb.actions.noreview;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.sql.Timestamp;

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

    


    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    @Override
    public void reviewNotRequired(ProtocolDocument protocolDocument, ProtocolReviewNotRequiredBean actionBean) {
        Protocol protocol = protocolDocument.getProtocol();
        ProtocolAction protocolAction = new ProtocolAction(protocol, protocol.getProtocolSubmission(), ProtocolActionType.IRB_REVIEW_NOT_REQUIRED);
        protocolAction.setComments(actionBean.getComments());
        protocolAction.setActionDate(new Timestamp(actionBean.getActionDate().getTime()));
        protocol.getProtocolActions().add(protocolAction);
        
        protocolActionService.updateProtocolStatus(protocolAction, protocol);     
        
        protocol.setApprovalDate(actionBean.getDecisionDate());
        
        protocol.refreshReferenceObject("protocolStatus");
        businessObjectService.save(protocolDocument.getProtocol());
    }
}
