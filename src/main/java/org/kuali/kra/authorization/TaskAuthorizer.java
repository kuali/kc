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
 * A Task Authorizer determines if a user can execute a given task.
 * Task Authorizers can be responsible for zero or more tasks.   
 */
public interface TaskAuthorizer {
    
    /**
     * Determines if the Task Authorizer is responsible for authorization.
     * This method must return true in order to reliably invoke the isAuthorized()
     * method.
     * @param task the task
     * @return true if the Task Authorizer can determine the authorization 
     *         for this task; otherwise false
     */
    public boolean isResponsible(Task task);
    
    /**
     * Is the user authorized to execute the given task?
     * @param username the user's unique username
     * @param task the task
     * @return true if the user is authorized; otherwise false
     */
    public boolean isAuthorized(String username, Task task);
}
