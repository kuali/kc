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
package org.kuali.kra.iacuc.onlinereview.authorization;

import org.kuali.kra.authorization.Task;
import org.kuali.kra.authorization.TaskAuthorizerImpl;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReviewService;
import org.kuali.kra.protocol.onlinereview.authorization.ProtocolOnlineReviewTask;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.service.KraAuthorizationService;

public abstract class IacucProtocolOnlineReviewAuthorizer extends TaskAuthorizerImpl {
    private KraAuthorizationService kraAuthorizationService;
    private IacucProtocolOnlineReviewService iacucProtocolOnlineReviewService;
   
    
    /**
     * @see org.kuali.kra.authorization.TaskAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.authorization.Task)
     */
    public final boolean isAuthorized(String userId, Task task) {
        return isAuthorized(userId, (IacucProtocolOnlineReviewTask) task);
    }

//    public final boolean isAuthorized(String userId, IacucProtocolOnlineReviewTask task) {
//        return isAuthorized(userId, task);
//    }
    public abstract boolean isAuthorized(String userId, IacucProtocolOnlineReviewTask task);

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
     * Gets the kraAuthorizationService attribute. 
     * @return Returns the kraAuthorizationService.
     */
    public KraAuthorizationService getKraAuthorizationService() {
        return kraAuthorizationService;
    }


    public void setIacucProtocolOnlineReviewService(IacucProtocolOnlineReviewService iacucProtocolOnlineReviewService) {
        this.iacucProtocolOnlineReviewService = iacucProtocolOnlineReviewService;
    }


}
