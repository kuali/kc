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
package org.kuali.kra.irb.actions.request;

import org.apache.log4j.Logger;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolSubmissionBuilder;
import org.kuali.kra.irb.actions.notification.NotificationEventBase;
import org.kuali.kra.irb.actions.notification.ProtocolActionsNotificationService;
import org.kuali.kra.irb.actions.notification.RequestActionType;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * Protocol Request Service Implementation.
 */
public class ProtocolRequestServiceImpl implements ProtocolRequestService {
    
    private static final Logger LOG = Logger.getLogger(ProtocolRequestServiceImpl.class);
    private BusinessObjectService businessObjectService;
    private ProtocolActionService protocolActionService;
    private ProtocolActionsNotificationService protocolActionsNotificationService;

    /**
     * Set the business object service.
     * @param businessObjectService the business office service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * Set the Protocol Action Service.
     * @param protocolActionService
     */
    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }

    /**
     * @see org.kuali.kra.irb.actions.request.ProtocolRequestService#submitRequest(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.actions.request.ProtocolRequestBean)
     */
    public void submitRequest(Protocol protocol, ProtocolRequestBean requestBean) {
        /*
         * The submission is created first so that its new primary key can be added
         * to the protocol action entry.
         */
        String prevSubmissionStatusCode = protocol.getProtocolSubmission().getSubmissionStatusCode();

        ProtocolSubmission submission = createProtocolSubmission(protocol, requestBean);
        String submissionTypeCode = submission.getSubmissionTypeCode();
        protocol.setProtocolSubmission(submission);
        
        ProtocolAction protocolAction = new ProtocolAction(protocol, submission, requestBean.getProtocolActionTypeCode());        
        protocolAction.setComments(requestBean.getReason());
        protocolAction.setProtocol(protocol);
        
        //Creating an audit trail
        protocolAction.setPrevProtocolStatusCode(protocol.getProtocolStatusCode());
        protocolAction.setPrevSubmissionStatusCode(prevSubmissionStatusCode);
        protocolAction.setSubmissionTypeCode(submissionTypeCode);
        
        protocol.getProtocolActions().add(protocolAction);
        
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
        
        businessObjectService.save(protocol.getProtocolDocument());
        try {
            sendRequestNotification(protocol, requestBean);
        } catch (Exception e) {
            LOG.info("Request notification exception " + e.getStackTrace());
        }
    }
    
    /**
     * Create a Protocol Submission.
     * @param protocol the protocol
     * @param requestBean the request data
     * @return a protocol submission
     */
    private ProtocolSubmission createProtocolSubmission(Protocol protocol, ProtocolRequestBean requestBean) {
        ProtocolSubmissionBuilder submissionBuilder = new ProtocolSubmissionBuilder(protocol, requestBean.getSubmissionTypeCode());
        submissionBuilder.setProtocolReviewTypeCode(ProtocolReviewType.FULL_TYPE_CODE);
        submissionBuilder.setSubmissionStatus(ProtocolSubmissionStatus.PENDING);
        submissionBuilder.setCommittee(requestBean.getCommitteeId());
        submissionBuilder.addAttachment(requestBean.getFile());
        return submissionBuilder.create();
    }
    
    /*
     * send Request notification for different event
     * TODO : can we share this method with withdraw notification and other action notification ?
     */
    private void sendRequestNotification(Protocol protocol, ProtocolRequestBean requestBean) throws Exception {

        RequestActionType requestActionType = RequestActionType.getRequestActionType(requestBean.getProtocolActionTypeCode());
        NotificationEventBase event = requestActionType.getEventClass().newInstance();
       // RequestToCloseEvent event1 = new RequestToCloseEvent();
        event.setProtocol(protocol);
        protocolActionsNotificationService.sendActionsNotification(protocol, event);
    }

    public void setProtocolActionsNotificationService(ProtocolActionsNotificationService protocolActionsNotificationService) {
        this.protocolActionsNotificationService = protocolActionsNotificationService;
    }

}
