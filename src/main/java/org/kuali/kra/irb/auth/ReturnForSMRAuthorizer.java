/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.auth;

import org.kuali.kra.committee.bo.CommitteeDecisionMotionType;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;

/**
 * Is the user allowed to return protocols for specific minor revisions?
 */
public class ReturnForSMRAuthorizer extends ProtocolAuthorizer {

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.auth.ProtocolAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.irb.auth.ProtocolTask)
     */
    public boolean isAuthorized(String userId, ProtocolTask task) {
        ProtocolAction lastAction = task.getProtocol().getLastProtocolAction();
        ProtocolSubmission lastSubmission = task.getProtocol().getProtocolSubmission();
        
        return canPerform(lastAction, lastSubmission) && hasPermission(userId, task.getProtocol(), PermissionConstants.MAINTAIN_PROTOCOL_SUBMISSIONS);
    }
    
    private boolean canPerform(ProtocolAction lastAction, ProtocolSubmission lastSubmission) {
        boolean canPerform = false;
        
        if (lastAction != null && lastSubmission != null) {
            
            boolean traditionalSubPerform  = ProtocolActionType.RECORD_COMMITTEE_DECISION.equals(lastAction.getProtocolActionTypeCode())
            && CommitteeDecisionMotionType.SPECIFIC_MINOR_REVISIONS.equals(lastSubmission.getCommitteeDecisionMotionTypeCode());
            
            boolean exemptExpeditePerform = false;
            if (lastSubmission.getProtocolReviewType() != null){
                exemptExpeditePerform =  isExpeditedOrExempt(lastSubmission.getProtocolReviewType().getReviewTypeCode()) && ProtocolActionType.SUBMIT_TO_IRB.equals(lastAction.getProtocolActionTypeCode());
            }
            
            
            canPerform = traditionalSubPerform || exemptExpeditePerform;
        }
        
        return canPerform;
    }
    
    private boolean isExpeditedOrExempt(String reviewTypeCode){
        return ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE.equals(reviewTypeCode) 
        || ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE.equals(reviewTypeCode);
    }
    
}
