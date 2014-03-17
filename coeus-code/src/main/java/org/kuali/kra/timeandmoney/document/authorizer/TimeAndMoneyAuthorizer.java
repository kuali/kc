/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.timeandmoney.document.authorizer;

import org.kuali.coeus.sys.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.auth.task.Task;
import org.kuali.coeus.sys.framework.auth.task.TaskAuthorizerBase;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.document.authorization.TimeAndMoneyTask;

/**
 * An Time And Money Authorizer determines if a user can perform a given task on a Time and Money document.  
 */
public abstract class TimeAndMoneyAuthorizer extends TaskAuthorizerBase {
    
    private KcAuthorizationService kraAuthorizationService;
    
    @Override
    public final boolean isAuthorized(String userId, Task task) {
        return isAuthorized(userId, (TimeAndMoneyTask) task);
    }

    /**
     * Is the user authorized to execute the given Time and Money task?
     * @param username the user's unique username
     * @param task the Time And Money task
     * @return true if the user is authorized; otherwise false
     */
    public abstract boolean isAuthorized(String userId, TimeAndMoneyTask task);
    
    /**
     * Set the Kra Authorization Service.  Usually injected by the Spring Framework.
     * @param kraAuthorizationService
     */
    public void setKraAuthorizationService(KcAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }
    
    /**
     * Does the given user has the permission for this Time And Money?
     * @param username the unique username of the user
     * @param timeAndMoneyDocument the Time And Money
     * @param permissionName the name of the permission
     * @return true if the person has the permission; otherwise false
     */
    protected final boolean hasPermission(String userId, TimeAndMoneyDocument timeAndMoneyDocument, String permissionName) {
        return kraAuthorizationService.hasPermission(userId, timeAndMoneyDocument, permissionName);
    }
}
