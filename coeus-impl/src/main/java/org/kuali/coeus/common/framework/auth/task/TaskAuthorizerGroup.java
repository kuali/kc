/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.framework.auth.task;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * A Task Authorizer Group groups a set of Task Authorizers.
 */
public class TaskAuthorizerGroup {
    
    private String groupName;
    private List<TaskAuthorizer> taskAuthorizers = null;
    

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
