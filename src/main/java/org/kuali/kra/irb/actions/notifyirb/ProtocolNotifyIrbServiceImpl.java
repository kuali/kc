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
package org.kuali.kra.irb.actions.notifyirb;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolSubmissionBuilder;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * Protocol Request Service Implementation.
 */
public class ProtocolNotifyIrbServiceImpl implements ProtocolNotifyIrbService {
    
    private BusinessObjectService businessObjectService;

    /**
     * Set the business object service.
     * @param businessObjectService the business office service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
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
        
        businessObjectService.save(protocol.getProtocolDocument());
    }
    
    /**
     * Create a Protocol Submission.
     * @param protocol the protocol
     * @param requestBean the request data
     * @return a protocol submission
     */
    private ProtocolSubmission createProtocolSubmission(Protocol protocol, ProtocolNotifyIrbBean notifyIrbBean) {
        ProtocolSubmissionBuilder submissionBuilder = new ProtocolSubmissionBuilder(protocol, ProtocolSubmissionType.NOTIFY_IRB);
        submissionBuilder.setProtocolReviewTypeCode(ProtocolReviewType.FULL_TYPE_CODE);
        submissionBuilder.setSubmissionTypeQualifierCode(notifyIrbBean.getSubmissionQualifierTypeCode());
        submissionBuilder.setCommittee(notifyIrbBean.getCommitteeId());
        submissionBuilder.setComments(notifyIrbBean.getComment());
        return submissionBuilder.create();
    }
}
