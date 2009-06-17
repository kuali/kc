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
package org.kuali.kra.service;

import org.kuali.kra.authorization.Task;

/**
 * The Task Authorization Service is responsible for determining
 * if a user has the authority to execute a given task.
 */
public interface TaskAuthorizationService {

    /**
     * Is the user authorized to perform the given task?
     * @param username the user's unique username
     * @param task the task to perform
     * @return true if the user is authorized; otherwise false
     */
    public boolean isAuthorized(String username, Task task);

    /**
     * Is the given task defined within the system?
     * @param taskGroupName the task's group name
     * @param taskName the task's name
     * @return true if defined; otherwise false
     */
    public boolean isTaskDefined(String taskGroupName, String taskName);
}
