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
package org.kuali.kra.subaward.document.authorizer;

import org.kuali.kra.authorization.Task;
import org.kuali.kra.authorization.TaskAuthorizerImpl;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.document.authorization.SubAwardTask;

public abstract class SubAwardAuthorizer extends TaskAuthorizerImpl{

    private KraAuthorizationService kraAuthorizationService;
    
    @Override
    public boolean isAuthorized(String userId, Task task) {
        return isAuthorized(userId, (SubAwardTask) task); 
    }
    /**
     * Is the user authorized to execute the given Subaward task?
     * @param username the user's unique username
     * @param task the Subaward task
     * @return true if the user is authorized; otherwise false
     */
    public abstract boolean isAuthorized(String userId, SubAwardTask task);
    
    
    /**
     * Set the Kra Authorization Service.  Usually injected by the Spring Framework.
     * @param kraAuthorizationService
     */
    public void setKraAuthorizationService(KraAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }
    
    /**
     * Does the given user has the permission for this Subaward?
     * @param username the unique username of the user
     * @param Subaward the Subaward
     * @param permissionName the name of the permission
     * @return true if the person has the permission; otherwise false
     */
    protected final boolean hasPermission(String userId, SubAward subAward, String permissionName) {
        return kraAuthorizationService.hasPermission(userId, subAward, permissionName);
    }
}
