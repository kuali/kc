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
package org.kuali.kra.irb.actions.genericactions;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplate;
import org.kuali.kra.printing.PrintingException;
import org.kuali.rice.kim.service.RoleService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * 
 * This class handles the generic actions that can be made to a protocol.  A generic action contain a comment, action date, and a 
 * state change.
 */
public class ProtocolGenericActionServiceImpl implements ProtocolGenericActionService {
    
    private static final String NAMESPACE = "KC-PROTOCOL";
    
    private BusinessObjectService businessObjectService;
    private RoleService kimRoleManagementService;
    private ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService;
    private ProtocolActionService protocolActionService;

    /**
     * Set the business object service.
     * @param businessObjectService the business object service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * This method sets the kimRoleManagementService.
     * @param kimRoleManagementService RoleService
     */
    public void setKimRoleManagementService(RoleService kimRoleManagementService) {
        this.kimRoleManagementService = kimRoleManagementService;
    }
    
    public void setProtocolActionCorrespondenceGenerationService(ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService) {
        this.protocolActionCorrespondenceGenerationService = protocolActionCorrespondenceGenerationService;
    }
    
    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }
    
    /**{@inheritDoc}**/
    public void close(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        String attachmentDescription = "Closed Correspondence Template Document";
        performGenericAction(protocol, actionBean, ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, ProtocolStatus.CLOSED_ADMINISTRATIVELY, attachmentDescription);
    }
    
    /**{@inheritDoc}**/
    public void closeEnrollment(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        String attachmentDescription = "Closed for Enrollment Correspondence Template Document";
        performGenericAction(protocol, actionBean, ProtocolActionType.CLOSED_FOR_ENROLLMENT, ProtocolStatus.ACTIVE_CLOSED_TO_ENROLLMENT, attachmentDescription);
    }
    
    /**{@inheritDoc}**/
    public void expire(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        String attachmentDescription = "Expired Correspondence Template Document";
        performGenericAction(protocol, actionBean, ProtocolActionType.EXPIRED, ProtocolStatus.EXPIRED, attachmentDescription);
    }

    /**{@inheritDoc}**/
    public void permitDataAnalysis(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        String attachmentDescription = "Data Analysis Only Correspondence Template Document";
        performGenericAction(protocol, actionBean, ProtocolActionType.DATA_ANALYSIS_ONLY, ProtocolStatus.ACTIVE_DATA_ANALYSIS_ONLY, attachmentDescription);
    }

    /**{@inheritDoc}**/
    public void reopen(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        String attachmentDescription = "Reopened Correspondence Template Document";
        performGenericAction(protocol, actionBean, ProtocolActionType.REOPEN_ENROLLMENT, ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, attachmentDescription);
    }

    /**{@inheritDoc}**/
    public void suspend(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        if (isIrbAdministrator()) {
            String attachmentDescription = "Suspended by IRB Correspondence Template Document";
            performGenericAction(protocol, actionBean, ProtocolActionType.SUSPENDED, ProtocolStatus.SUSPENDED_BY_IRB, attachmentDescription);
        } else {
            String attachmentDescription = "Suspended by PI Correspondence Template Document";
            performGenericAction(protocol, actionBean, ProtocolActionType.SUSPENDED, ProtocolStatus.SUSPENDED_BY_PI, attachmentDescription);
        }
    }
    
    /**{@inheritDoc}**/
    public void suspendByDsmb(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        String attachmentDescription = "Suspended by DSMB Correspondence Template Document";
        performGenericAction(protocol, actionBean, ProtocolActionType.SUSPENDED_BY_DSMB, ProtocolStatus.SUSPENDED_BY_DSMB, attachmentDescription);
    }
    
    private boolean isIrbAdministrator() {
        String principalId = GlobalVariables.getUserSession().getPrincipalId();
        Collection<String> ids = this.kimRoleManagementService.getRoleMemberPrincipalIds(NAMESPACE, RoleConstants.IRB_ADMINISTRATOR, null);
        return ids.contains(principalId);
    }
    
    /**{@inheritDoc}**/
    public void terminate(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        String attachmentDescription = "Terminated Correspondence Template Document";
        performGenericAction(protocol, actionBean, ProtocolActionType.TERMINATED, ProtocolStatus.TERMINATED_BY_IRB, attachmentDescription);
    }
    
    /**
     * 
     * This method performs the Generic action persistence.  A state change, action date, and a comment, that's it
     * @param protocol
     * @param actionBean
     * @param protocolActionType
     * @param newProtocolStatus
     * @throws Exception
     */
    private void performGenericAction(Protocol protocol, ProtocolGenericActionBean actionBean, 
            String protocolActionType, String newProtocolStatus, String attachmentDescription) throws Exception {
        ProtocolAction protocolAction = new ProtocolAction(protocol, null, protocolActionType);
        protocolAction.setComments(actionBean.getComments());
        protocolAction.setActionDate(new Timestamp(actionBean.getActionDate().getTime()));
        protocol.getProtocolActions().add(protocolAction);
        
        protocol.setProtocolStatusCode(newProtocolStatus);
        protocol.refreshReferenceObject("protocolStatus");
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
        businessObjectService.save(protocol);
        generateCorrespondenceDocumentAndAttach(protocol, protocolActionType, attachmentDescription);
    }
    
    private void generateCorrespondenceDocumentAndAttach(Protocol protocol, String protocolActionType, String attachmentDescription) throws PrintingException {
        List<ProtocolCorrespondenceTemplate> approvedTemplates = 
            protocolActionCorrespondenceGenerationService.getCorrespondenceTemplates(protocolActionType);
        //String attachmentDescription = "Approved Correspondence Template Document";
        protocolActionCorrespondenceGenerationService.generateCorrespondenceDocumentAndAttach(protocol, approvedTemplates, attachmentDescription);
    }
}
