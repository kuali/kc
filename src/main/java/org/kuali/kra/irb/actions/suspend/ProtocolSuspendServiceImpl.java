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
package org.kuali.kra.irb.actions.suspend;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolGenericActionBean;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.rice.kim.service.RoleService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * The ProtocolSuspendService implementation.
 */
public class ProtocolSuspendServiceImpl implements ProtocolSuspendService {
    
    private static final String NAMESPACE = "KC-PROTOCOL";

    private BusinessObjectService businessObjectService;
    
    /**
     * Set the business object service.
     * @param businessObjectService the business object service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * @see org.kuali.kra.irb.actions.suspend.ProtocolSuspendService#suspend(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.actions.ProtocolGenericActionBean)
     */
    public void suspend(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        addAction(protocol, ProtocolActionType.SUSPENDED, actionBean.getComments(), actionBean.getActionDate());
        if (isIrbAdministrator()) {
            protocol.setProtocolStatusCode(ProtocolStatus.SUSPENDED_BY_IRB);
        } 
        else {
            protocol.setProtocolStatusCode(ProtocolStatus.SUSPENDED_BY_PI);
        }
        protocol.refreshReferenceObject("protocolStatus");
        businessObjectService.save(protocol);
    }
    
    /**
     * @see org.kuali.kra.irb.actions.suspend.ProtocolSuspendService#suspendByDsmb(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.actions.ProtocolGenericActionBean)
     */
    public void suspendByDsmb(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        addAction(protocol, ProtocolActionType.SUSPENDED_BY_DSMB, actionBean.getComments(), actionBean.getActionDate());
        protocol.setProtocolStatusCode(ProtocolStatus.SUSPENDED_BY_DSMB);
        protocol.refreshReferenceObject("protocolStatus");
        businessObjectService.save(protocol);
    }
    
    private void addAction(Protocol protocol, String actionTypeCode, String comments, Date actionDate) {
        ProtocolAction protocolAction = new ProtocolAction(protocol, null, actionTypeCode);
        protocolAction.setComments(comments);
        protocolAction.setActionDate(new Timestamp(actionDate.getTime()));
        protocol.getProtocolActions().add(protocolAction);
    }
    
    private boolean isIrbAdministrator() {
        String principalId = GlobalVariables.getUserSession().getPrincipalId();
        Collection<String> ids = getRoleService().getRoleMemberPrincipalIds(NAMESPACE, RoleConstants.IRB_ADMINISTRATOR, null);
        return ids.contains(principalId);
    }
    
    private RoleService getRoleService() {
        return KraServiceLocator.getService("kimRoleManagementService");
    }
}
