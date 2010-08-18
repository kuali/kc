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
package org.kuali.kra.irb.actions.notifyirb;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolSubmissionBuilder;
import org.kuali.kra.irb.actions.notification.NotifyIrbEvent;
import org.kuali.kra.irb.actions.notification.ProtocolActionsNotificationService;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * Protocol Request Service Implementation.
 */
public class ProtocolNotifyIrbServiceImpl implements ProtocolNotifyIrbService {
    
    private static final Log LOG = LogFactory.getLog(ProtocolNotifyIrbServiceImpl.class);
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
    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }

    /**
     * @see org.kuali.kra.irb.actions.notifyirb.ProtocolNotifyIrbService#submitIrbNotification(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.actions.notifyirb.ProtocolNotifyIrbBean)
     */
    public void submitIrbNotification(Protocol protocol, ProtocolNotifyIrbBean notifyIrbBean) {
        /*
         * The submission is created first so that its new primary key can be added
         * to the protocol action entry.
         */
        ProtocolSubmission submission = createProtocolSubmission(protocol, notifyIrbBean);
        
        ProtocolAction protocolAction = new ProtocolAction(protocol, submission, ProtocolActionType.NOTIFY_IRB);
        protocolAction.setComments(notifyIrbBean.getComment());
        protocol.getProtocolActions().add(protocolAction);
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
        businessObjectService.save(protocol.getProtocolDocument());
        try {
            //sendNotifyIrbNotification(protocol, notifyIrbBean);
            protocolActionsNotificationService.sendActionsNotification(protocol, new NotifyIrbEvent(protocol));
        } catch (Exception e) {
            LOG.info("Notify Irb Notification exception " + e.getStackTrace());
        }
    }
    
    /**
     * Create a Protocol Submission.
     * @param protocol the protocol
     * @param requestBean the request data
     * @return a protocol submission
     */
    private ProtocolSubmission createProtocolSubmission(Protocol protocol, ProtocolNotifyIrbBean notifyIrbBean) {
        ProtocolSubmissionBuilder submissionBuilder = new ProtocolSubmissionBuilder(protocol, ProtocolSubmissionType.NOTIFY_IRB);
        //submissionBuilder.setProtocolReviewTypeCode(ProtocolReviewType.FULL_TYPE_CODE);
        submissionBuilder.setProtocolReviewTypeCode(ProtocolReviewType.FYI_TYPE_CODE);
        submissionBuilder.setSubmissionTypeQualifierCode(notifyIrbBean.getSubmissionQualifierTypeCode());
        submissionBuilder.setSubmissionStatus(ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        submissionBuilder.setCommittee(notifyIrbBean.getCommitteeId());
        submissionBuilder.setComments(notifyIrbBean.getComment());
        submissionBuilder.addAttachment(notifyIrbBean.getFile());
        return submissionBuilder.create();
    }

    public void setProtocolActionsNotificationService(ProtocolActionsNotificationService protocolActionsNotificationService) {
        this.protocolActionsNotificationService = protocolActionsNotificationService;
    }
}
