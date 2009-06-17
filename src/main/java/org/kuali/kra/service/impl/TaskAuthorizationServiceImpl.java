/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.Task;
import org.kuali.kra.authorization.TaskAuthorizer;
import org.kuali.kra.authorization.TaskAuthorizerGroup;
import org.kuali.kra.service.TaskAuthorizationService;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Task Authorization Service Implementation.
 */
public class TaskAuthorizationServiceImpl implements TaskAuthorizationService {

    private List<TaskAuthorizerGroup> taskAuthorizerGroups = null;
    
    /**
     * Set the list of Task Authorizer Groups.  Injected by the Spring Framework.
     * @param taskAuthorizerGroups the list of Task Authorizer Groups
     */
    public void setTaskAuthorizerGroups(List<TaskAuthorizerGroup> taskAuthorizerGroups) {
        this.taskAuthorizerGroups = taskAuthorizerGroups;
    }
    
    /**
     * Delegate the authorization work to a Task Authorizer who is 
     * responsible for the given task.  If there are no Task Authorizers 
     * for the task, then the user will be authorized by default.
     * 
     * @see org.kuali.kra.service.TaskAuthorizationService#isAuthorized(java.lang.String, org.kuali.kra.authorization.Task)
     */
    @Transactional
    public boolean isAuthorized(String username, Task task) {
        boolean isAuthorized = true;
        if (taskAuthorizerGroups != null) {
            String groupName = task.getGroupName();
            for (TaskAuthorizerGroup taskAuthorizerGroup : taskAuthorizerGroups) {
                if (StringUtils.equals(taskAuthorizerGroup.getGroupName(), groupName)) {
                    TaskAuthorizer taskAuthorizer = taskAuthorizerGroup.getTaskAuthorizer(task.getTaskName());
                    if (taskAuthorizer != null) {
                        isAuthorized = taskAuthorizer.isAuthorized(username, task);
                    }
                    break;
                }
            }
        }
        return isAuthorized;
    }

    /**
     * @see org.kuali.kra.service.TaskAuthorizationService#isTaskDefined(java.lang.String, java.lang.String)
     */
    @Transactional
    public boolean isTaskDefined(String taskGroupName, String taskName) {
        if (taskAuthorizerGroups != null) {
            for (TaskAuthorizerGroup taskAuthorizerGroup : taskAuthorizerGroups) {
                if (StringUtils.equals(taskAuthorizerGroup.getGroupName(), taskGroupName)) {
                    TaskAuthorizer taskAuthorizer = taskAuthorizerGroup.getTaskAuthorizer(taskName);
                    return (taskAuthorizer != null);
                }
            }
        }
        return false;
    }
}
