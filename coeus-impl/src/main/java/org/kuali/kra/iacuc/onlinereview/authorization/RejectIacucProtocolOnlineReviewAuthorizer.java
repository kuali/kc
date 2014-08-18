/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.iacuc.onlinereview.authorization;

import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;

public class RejectIacucProtocolOnlineReviewAuthorizer extends IacucProtocolOnlineReviewAuthorizer {

    private KcWorkflowService kraWorkflowService;

    public boolean isAuthorized(String userId, IacucProtocolOnlineReviewTask task) {
        boolean hasPermission = false;
        ProtocolOnlineReviewBase protocolOnlineReview = task.getProtocolOnlineReview();
        
        if ( protocolOnlineReview.getProtocolOnlineReviewId() == null ) {
            //we never authorize edits on a review, the reviews are created
            //by the system on behalf of the users.
        } else {
            hasPermission = getKraAuthorizationService().hasPermission(userId, protocolOnlineReview.getProtocol(), PermissionConstants.MAINTAIN_IACUC_ONLINE_REVIEWS);
            hasPermission &= !protocolOnlineReview.getProtocolOnlineReviewDocument().isViewOnly();
            hasPermission &= kraWorkflowService.isEnRoute(task.getProtocolOnlineReviewDocument());
            hasPermission &= kraWorkflowService.isUserApprovalRequested(task.getProtocolOnlineReviewDocument(), userId);
        }

        return hasPermission;
    }

    public KcWorkflowService getKraWorkflowService() {
        return kraWorkflowService;
    }

    public void setKraWorkflowService(KcWorkflowService kraWorkflowService) {
        this.kraWorkflowService = kraWorkflowService;
    }
}
