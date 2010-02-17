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
package org.kuali.kra.irb.actions.genericactions;

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
 * 
 * This class handles the generic actions that can be made to a protocol.  A generic action contain a comment, action date, and a 
 * state change.
 */
public class GenericActionServiceImpl implements GenericActionService {
    
    private static final String NAMESPACE = "KC-PROTOCOL";
    
    private BusinessObjectService businessObjectService;

    /**
     * Set the business object service.
     * @param businessObjectService the business object service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**{@inheritDoc}**/
    public void approve(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        addAction(protocol, ProtocolActionType.APPROVED, actionBean.getComments(), actionBean.getActionDate());
        protocol.setProtocolStatusCode(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT);
        protocol.refreshReferenceObject("protocolStatus");
        businessObjectService.save(protocol);
    }
    
    /**{@inheritDoc}**/
    public void close(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        addAction(protocol, ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, actionBean.getComments(), actionBean.getActionDate());
        protocol.setProtocolStatusCode(ProtocolStatus.CLOSED_ADMINISTRATIVELY);
        protocol.refreshReferenceObject("protocolStatus");
        businessObjectService.save(protocol);
    }
    
    /**{@inheritDoc}**/
    public void closeEnrollment(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        addAction(protocol, ProtocolActionType.CLOSED_FOR_ENROLLMENT, actionBean.getComments(), actionBean.getActionDate());
        protocol.setProtocolStatusCode(ProtocolStatus.ACTIVE_CLOSED_TO_ENROLLMENT);
        protocol.refreshReferenceObject("protocolStatus");
        businessObjectService.save(protocol);
    }
    
    /**{@inheritDoc}**/
    public void expire(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        addAction(protocol, ProtocolActionType.EXPIRED, actionBean.getComments(), actionBean.getActionDate());
        protocol.setProtocolStatusCode(ProtocolStatus.EXPIRED);
        protocol.refreshReferenceObject("protocolStatus");
        businessObjectService.save(protocol);
    }

    /**{@inheritDoc}**/
    public void permitDataAnalysis(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        addAction(protocol, ProtocolActionType.DATA_ANALYSIS_ONLY, actionBean.getComments(), actionBean.getActionDate());
        protocol.setProtocolStatusCode(ProtocolStatus.ACTIVE_DATA_ANALYSIS_ONLY);
        protocol.refreshReferenceObject("protocolStatus");
        businessObjectService.save(protocol);
    }

    /**{@inheritDoc}**/
    public void reopen(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        addAction(protocol, ProtocolActionType.REOPEN_ENROLLMENT, actionBean.getComments(), actionBean.getActionDate());
        protocol.setProtocolStatusCode(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT);
        protocol.refreshReferenceObject("protocolStatus");
        businessObjectService.save(protocol);
    }

    /**{@inheritDoc}**/
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
    
    /**{@inheritDoc}**/
    public void suspendByDsmb(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        addAction(protocol, ProtocolActionType.SUSPENDED_BY_DSMB, actionBean.getComments(), actionBean.getActionDate());
        protocol.setProtocolStatusCode(ProtocolStatus.SUSPENDED_BY_DSMB);
        protocol.refreshReferenceObject("protocolStatus");
        businessObjectService.save(protocol);
    }
    
    private boolean isIrbAdministrator() {
        String principalId = GlobalVariables.getUserSession().getPrincipalId();
        Collection<String> ids = getRoleService().getRoleMemberPrincipalIds(NAMESPACE, RoleConstants.IRB_ADMINISTRATOR, null);
        return ids.contains(principalId);
    }
    
    private RoleService getRoleService() {
        return KraServiceLocator.getService("kimRoleManagementService");
    }
    
    /**{@inheritDoc}**/
    public void terminate(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        addAction(protocol, ProtocolActionType.TERMINATED, actionBean.getComments(), actionBean.getActionDate());
        protocol.setProtocolStatusCode(ProtocolStatus.TERMINATED_BY_IRB);
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