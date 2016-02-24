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
