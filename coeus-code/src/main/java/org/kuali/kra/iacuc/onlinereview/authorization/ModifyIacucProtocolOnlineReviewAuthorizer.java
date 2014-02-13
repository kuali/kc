/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocumentBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.DocumentService;

public class ModifyIacucProtocolOnlineReviewAuthorizer extends IacucProtocolOnlineReviewAuthorizer {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ModifyIacucProtocolOnlineReviewAuthorizer.class);

    private KcWorkflowService kraWorkflowService;

    public boolean isAuthorized(String userId, IacucProtocolOnlineReviewTask task) {
        boolean hasPermission = true;
        ProtocolOnlineReviewBase protocolOnlineReview = task.getProtocolOnlineReview();
        ProtocolOnlineReviewDocumentBase protocolDoc = null;
        try {
            protocolDoc = (ProtocolOnlineReviewDocumentBase) KcServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(protocolOnlineReview.getProtocolOnlineReviewDocument().getDocumentNumber());
        }
        catch (WorkflowException e) {
            LOG.error(String.format("Could not find ProtocolOnlineReviewBase, document number %s",protocolOnlineReview.getProtocolOnlineReviewDocument().getDocumentNumber()));
            return false;
        }
        
        if ( protocolOnlineReview.getProtocolOnlineReviewId() == null ) {
            //we never authorize edits on a review, the reviews are created
            //by the system on behalf of the users.
            return false;
        } else {
            
            hasPermission = !protocolOnlineReview.getProtocolOnlineReviewDocument().isViewOnly() 
                            && ((hasPermission(userId, protocolOnlineReview, PermissionConstants.MAINTAIN_IACUC_ONLINE_REVIEWS)
                                    && kraWorkflowService.isEnRoute(protocolDoc))
                                || (hasPermission(userId, protocolOnlineReview, PermissionConstants.MAINTAIN_IACUC_PROTOCOL_ONLINE_REVIEW_COMMENTS)
                                    && kraWorkflowService.isUserApprovalRequested(protocolDoc, userId) )
                                   );
                          
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
