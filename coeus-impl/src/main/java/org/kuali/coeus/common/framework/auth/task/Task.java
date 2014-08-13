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
package org.kuali.coeus.common.framework.auth.task;

/**
 * A Task represents a single operation that can be performed by
 * a user.  This base class can be used as is or extended in order 
 * to provide more data pertaining to the actual task.
 */
public class Task {

    private String groupName;
    private String taskName; 
    private String genericTaskName;
    
    /**
     * Constructs a Task.
     * @param groupName the name of the group
     * @param taskName the name of the task
     */
    public Task(String groupName, String taskName) {
        this.groupName = groupName;
        this.taskName = taskName;
    }
    
    public Task(String groupName, String taskName, String genericTaskName) {
        this(groupName, taskName);
        this.genericTaskName = genericTaskName;
    }
    
    /**
     * Get the name of the group.
     * @return the task's group name
     */
    public String getGroupName() {
        return groupName;
    }
    
    /**
     * Get the name of the task.
     * @return the task's name
     */
    public String getTaskName() {
        return taskName;
    }
    
    public String getGenericTaskName(){
        return this.genericTaskName;
    }
    
    @Override
    public String toString() {
        return groupName + "." + taskName;
    }
}
