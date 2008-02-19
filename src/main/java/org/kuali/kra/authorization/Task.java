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
package org.kuali.kra.authorization;

/**
 * A Task represents a single request from a user.  A task directly 
 * corresponds to a single Struts Action method, i.e. the name of the
 * task and the name of the method must be exactly the same.  This
 * base class can be used as is or extended in order to provide more
 * data pertaining to the actual task.
 */
public class Task {

    private String taskName;
    
    /**
     * Constructs a Task.
     * @param taskName the name of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Get the name of the task.
     * @return the task's name
     */
    public String getTaskName() {
        return taskName;
    }
    
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return taskName;
    }
}
