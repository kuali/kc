/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.service.impl;

import java.util.List;

import org.kuali.kra.authorization.Task;
import org.kuali.kra.authorization.TaskAuthorizer;
import org.kuali.kra.service.TaskAuthorizationService;

// benefits:
//     1. reusable; used outside of struts actions and business rules
//     2. centralized groups
//     3. can inject with spring in the future if needed
//     4. separate from data validation (business rules)
/**
 * The Task Authorization Service Implementation.
 */
public class TaskAuthorizationServiceImpl implements TaskAuthorizationService {

    private List<TaskAuthorizer> taskAuthorizers = null;
    
    /**
     * Delegate the authorization work to a Task Authorizer who is 
     * responsible for the given task.  If there are no Task Authorizers 
     * for the task, then the user will be authorized by default.
     * 
     * @see org.kuali.kra.service.TaskAuthorizationService#isAuthorized(java.lang.String, org.kuali.kra.authorization.Task)
     */
    public boolean isAuthorized(String username, Task task) {
        if (taskAuthorizers != null) {
            for (TaskAuthorizer taskAuthorizer : taskAuthorizers) {
                if (taskAuthorizer.isResponsible(task)) {
                    return taskAuthorizer.isAuthorized(username, task);
                }
            }
        }
        return true;
    }

    /**
     * Set the list of Task Authorizers.  Injected by the Spring Framework.
     * @param taskAuthorizers the list of Task Authorizers
     */
    public void setTaskAuthorizers(List<TaskAuthorizer> taskAuthorizers) {
        this.taskAuthorizers = taskAuthorizers;
    }
}
