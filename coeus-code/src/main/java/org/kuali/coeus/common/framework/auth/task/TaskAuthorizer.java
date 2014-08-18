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
 * A Task Authorizer determines if a user can execute a specific task.
 */
public interface TaskAuthorizer {
    
    /**
     * Get the name of the task.
     * @return the task's name
     */
    public String getTaskName();
    
    /**
     * Is the user authorized to execute the given task?
     * @param userId the user's unique username
     * @param task the task
     * @return true if the user is authorized; otherwise false
     */
    public boolean isAuthorized(String userId, Task task);
}
