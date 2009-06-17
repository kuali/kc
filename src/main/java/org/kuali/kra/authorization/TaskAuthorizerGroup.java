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
package org.kuali.kra.authorization;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * A Task Authorizer Group groups a set of Task Authorizers.
 */
public class TaskAuthorizerGroup {
    
    private String groupName;
    private List<TaskAuthorizer> taskAuthorizers = null;
    
    /**
     * Constructs a TaskAuthorizerGroup.
     */
    public TaskAuthorizerGroup() {
      
    }
    
    /**
     * Set the group name.
     * @param groupName the new group name
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    /**
     * Get the group name.
     * @return the group name
     */
    public String getGroupName() {
        return this.groupName;
    }
    
    /**
     * Sets the Task Authorizers.  Injected by the Spring Framework.
     * @param taskAuthorizers the list of Task Authorizers
     */
    public void setTaskAuthorizers(List<TaskAuthorizer> taskAuthorizers) {
        this.taskAuthorizers = taskAuthorizers;
    }
    
    /**
     * Get a Task Authorizer for the given task name.
     * @param taskName the name of the task
     * @return the task authorizer or null if not found
     */
    public TaskAuthorizer getTaskAuthorizer(String taskName) {
        for (TaskAuthorizer taskAuthorizer : taskAuthorizers) {
            if (StringUtils.equals(taskName, taskAuthorizer.getTaskName())) {
                return taskAuthorizer;
            }
        }
        return null;
    }
}
