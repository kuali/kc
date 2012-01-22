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
package org.kuali.kra.irb.onlinereview.authorization;

import org.kuali.kra.authorization.Task;
import org.kuali.kra.authorization.TaskAuthorizerImpl;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.service.KraAuthorizationService;

/**
 * A Protocol Authorizer determines if a user can perform
 * a given task on a protocol.  
 */
public abstract class ProtocolOnlineReviewAuthorizer extends TaskAuthorizerImpl {
    
    private KraAuthorizationService kraAuthorizationService;
    private ProtocolOnlineReviewService protocolOnlineReviewService;
   
    
    /**
     * @see org.kuali.kra.authorization.TaskAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.authorization.Task)
     */
    public final boolean isAuthorized(String userId, Task task) {
        return isAuthorized(userId, (ProtocolOnlineReviewTask) task);
    }

    
    /**
     * Is the user authorized to execute the given protocol task?
     * @param username the user's unique username
     * @param task the protocol task
     * @return true if the user is authorized; otherwise false
     */
    public abstract boolean isAuthorized(String userId, ProtocolOnlineReviewTask task);
    
    /**
     * Set the Kra Authorization Service.  Usually injected by the Spring Framework.
     * @param kraAuthorizationService
     */
    public void setKraAuthorizationService(KraAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }
    
    /**
     * Does the given user have the permission for this protocol?
     * @param username the unique username of the user
     * @param protocol the protocol
     * @param permissionName the name of the permission
     * @return true if the person has the permission; otherwise false
     */
    protected final boolean hasPermission(String userId, ProtocolOnlineReview protocolOnlineReview, String permissionName) {
        return kraAuthorizationService.hasPermission(userId, protocolOnlineReview, permissionName);
    }


    /**
     * Gets the protocolOnlineReviewService attribute. 
     * @return Returns the protocolOnlineReviewService.
     */
    public ProtocolOnlineReviewService getProtocolOnlineReviewService() {
        return protocolOnlineReviewService;
    }


    /**
     * Sets the protocolOnlineReviewService attribute value.
     * @param protocolOnlineReviewService The protocolOnlineReviewService to set.
     */
    public void setProtocolOnlineReviewService(ProtocolOnlineReviewService protocolOnlineReviewService) {
        this.protocolOnlineReviewService = protocolOnlineReviewService;
    }


    /**
     * Gets the kraAuthorizationService attribute. 
     * @return Returns the kraAuthorizationService.
     */
    public KraAuthorizationService getKraAuthorizationService() {
        return kraAuthorizationService;
    }


}
