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
package org.kuali.kra.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.Task;
import org.kuali.kra.authorization.TaskAuthorizer;
import org.kuali.kra.authorization.TaskAuthorizerGroup;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Task Authorization Service Implementation.
 */
public class TaskAuthorizationServiceImpl implements TaskAuthorizationService {
    
    private Set<String> taskAuthorizerGroupNames = new HashSet<String>();
    private List<TaskAuthorizerGroup> taskAuthorizerGroups = new ArrayList<TaskAuthorizerGroup>();
    
    /**
     * Delegate the authorization work to a Task Authorizer who is 
     * responsible for the given task.  If there are no Task Authorizers 
     * for the task, then the user will be authorized by default.
     * 
     * @see org.kuali.kra.service.TaskAuthorizationService#isAuthorized(java.lang.String, org.kuali.kra.authorization.Task)
     */
    @Transactional
    public boolean isAuthorized(String userId, Task task) {
        boolean isAuthorized = true;
        String groupName = task.getGroupName();
        for (TaskAuthorizerGroup taskAuthorizerGroup : getTaskAuthorizerGroups()) {
            if (StringUtils.equals(taskAuthorizerGroup.getGroupName(), groupName)) {
                TaskAuthorizer taskAuthorizer;
                if (task.getGenericTaskName() == null || "".equals(task.getGenericTaskName().trim())) {
                    taskAuthorizer = taskAuthorizerGroup.getTaskAuthorizer(task.getTaskName()); 
                } else {
                    if (taskAuthorizerGroup.getTaskAuthorizer(task.getTaskName()) instanceof org.kuali.kra.irb.auth.GenericProtocolAuthorizer) {
                        taskAuthorizer = (org.kuali.kra.irb.auth.GenericProtocolAuthorizer) taskAuthorizerGroup.getTaskAuthorizer(task.getTaskName());
                        ((org.kuali.kra.irb.auth.GenericProtocolAuthorizer) taskAuthorizer).setGenericTaskName(task.getGenericTaskName());
                    } else if (taskAuthorizerGroup.getTaskAuthorizer(task.getTaskName()) instanceof org.kuali.kra.protocol.auth.GenericProtocolAuthorizer) {
                        taskAuthorizer = (org.kuali.kra.protocol.auth.GenericProtocolAuthorizer) taskAuthorizerGroup.getTaskAuthorizer(task.getTaskName());
                        ((org.kuali.kra.protocol.auth.GenericProtocolAuthorizer) taskAuthorizer).setGenericTaskName(task.getGenericTaskName());
                    } else {
                        taskAuthorizer = null;
                        RuntimeException rte = new RuntimeException("An unexpected GenericProtocolAuthorizer was found, " + taskAuthorizerGroup.getTaskAuthorizer(task.getTaskName()).getClass());
                        rte.printStackTrace();
                    }
                }
                
                if (taskAuthorizer != null) {
                    isAuthorized = taskAuthorizer.isAuthorized(userId, task);
                }
                break;
            }
        }
        return isAuthorized;
    }
    
    public List<TaskAuthorizerGroup> getTaskAuthorizerGroups() {
        if (taskAuthorizerGroups.isEmpty()) {
            for (String taskAuthorizerGroupName : taskAuthorizerGroupNames) {
                taskAuthorizerGroups.add(GlobalResourceLoader.<TaskAuthorizerGroup>getService(taskAuthorizerGroupName));
            }
        }
        
        return taskAuthorizerGroups;
    }
    
    public void setTaskAuthorizerGroups(List<TaskAuthorizerGroup> taskAuthorizerGroups) {
        this.taskAuthorizerGroups = taskAuthorizerGroups;
    }
    
    public Set<String> getTaskAuthorizerGroupNames() {
        return taskAuthorizerGroupNames;
    }
    
    public void setTaskAuthorizerGroupNames(Set<String> taskAuthorizerGroupNames) {
        this.taskAuthorizerGroupNames = taskAuthorizerGroupNames;
    }
    
}