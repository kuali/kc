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
