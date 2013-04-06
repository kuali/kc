/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.protocol.onlinereview.authorization;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocumentBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.DocumentService;

/**
 * The Modify ProtocolBase Online Review Authorizer checks to see if the user has 
 * permission to modify a protocol online review. 
 * 
 * Authorization depends on the users role.  If the user is a member of the IRB Administrator
 * role for the related protocol's unit, then that user is always authorized to alter the protocol online review.
 * If the user is the online reviewer, then they are allowed to edit the document provided that
 * they have an outstanding approve request on the document.
 *  
 */
public class ModifyProtocolOnlineReviewAuthorizer extends ProtocolOnlineReviewAuthorizerBase {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ModifyProtocolOnlineReviewAuthorizer.class);
    /**
     * @see org.kuali.kra.protocol.auth.ProtocolAuthorizerBase#isAuthorized(java.lang.String, org.kuali.kra.protocol.auth.ProtocolTaskBase)
     */
    public boolean isAuthorized(String userId, ProtocolOnlineReviewTask task) {
        boolean hasPermission = true;
        ProtocolOnlineReviewBase protocolOnlineReview = task.getProtocolOnlineReview();
        ProtocolOnlineReviewDocumentBase protocolDoc = null;
        try {
            protocolDoc = (ProtocolOnlineReviewDocumentBase)KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(protocolOnlineReview.getProtocolOnlineReviewDocument().getDocumentNumber());
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
                            && ((hasPermission(userId, protocolOnlineReview, PermissionConstants.MAINTAIN_ONLINE_REVIEWS)
                                    && kraWorkflowService.isEnRoute(protocolDoc))
                                || (hasPermission(userId, protocolOnlineReview, PermissionConstants.MAINTAIN_PROTOCOL_ONLINE_REVIEW_COMMENTS)
                                    && kraWorkflowService.isUserApprovalRequested(protocolDoc, userId) )
                                   );
                          
        }
        return hasPermission;
    }


}
