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
package org.kuali.kra.irb.actions.close;

import java.sql.Date;
import java.sql.Timestamp;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolGenericActionBean;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * The ProtocolCloseService implementation.
 */
public class ProtocolCloseServiceImpl implements ProtocolCloseService {

    private BusinessObjectService businessObjectService;
    
    /**
     * Set the business object service.
     * @param businessObjectService the business object service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
   
    /**
     * @see org.kuali.kra.irb.actions.close.ProtocolCloseService#close(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.actions.ProtocolGenericActionBean)
     */
    public void close(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        addAction(protocol, ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, actionBean.getComments(), actionBean.getActionDate());
        protocol.setProtocolStatusCode(ProtocolStatus.CLOSED_ADMINISTRATIVELY);
        protocol.refreshReferenceObject("protocolStatus");
        businessObjectService.save(protocol);
    }
    
    private void addAction(Protocol protocol, String actionTypeCode, String comments, Date actionDate) {
        ProtocolAction protocolAction = new ProtocolAction(protocol, null, actionTypeCode);
        protocolAction.setComments(comments);
        protocolAction.setActionDate(new Timestamp(actionDate.getTime()));
        protocol.getProtocolActions().add(protocolAction);
    }
}
