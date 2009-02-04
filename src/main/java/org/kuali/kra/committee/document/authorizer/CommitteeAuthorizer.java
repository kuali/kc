/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.committee.document.authorizer;

import org.kuali.kra.authorization.Task;
import org.kuali.kra.authorization.TaskAuthorizerImpl;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.document.authorization.CommitteeTask;
import org.kuali.kra.committee.service.CommitteeAuthorizationService;

/**
 * A Committee Authorizer determines if a user can perform
 * a given task on a committee.  
 */
public abstract class CommitteeAuthorizer extends TaskAuthorizerImpl {
    
    private CommitteeAuthorizationService committeeAuthorizationService;
    
    /**
     * @see org.kuali.kra.authorization.TaskAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.authorization.Task)
     */
    public final boolean isAuthorized(String username, Task task) {
        return isAuthorized(username, (CommitteeTask) task);
    }

    /**
     * Is the user authorized to execute the given committee task?
     * @param username the user's unique username
     * @param task the committee task
     * @return true if the user is authorized; otherwise false
     */
    public abstract boolean isAuthorized(String username, CommitteeTask task);
    
    /**
     * Set the Committee Authorization Service.  Usually injected by the Spring Framework.
     * @param committeeAuthorizationService
     */
    public void setCommitteeAuthorizationService(CommitteeAuthorizationService committeeAuthorizationService) {
        this.committeeAuthorizationService = committeeAuthorizationService;
    }
    
    /**
     * Does the given user has the permission for this committee?
     * @param username the unique username of the user
     * @param committee the committee
     * @param permissionName the name of the permission
     * @return true if the person has the permission; otherwise false
     */
    protected final boolean hasPermission(String username, Committee committee, String permissionName) {
        return committeeAuthorizationService.hasPermission(username, committee, permissionName);
    }
}
