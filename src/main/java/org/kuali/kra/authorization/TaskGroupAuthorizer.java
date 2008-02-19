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

import java.util.List;

/**
 * A Task Group Authorizer groups a set of Task Authorizers.  By
 * using the Task Group Authorizer, a hierarchical structure of
 * Authorizers can be created.
 */
public class TaskGroupAuthorizer implements TaskAuthorizer {
    
    private List<TaskAuthorizer> taskAuthorizers = null;
    
    /**
     * Constructs a TaskGroupAuthorizer.
     */
    public TaskGroupAuthorizer() {
      
    }
    
    /**
     * Sets the Task Authorizers.  Injected by the Spring Framework.
     * @param taskAuthorizers the list of Task Authorizers
     */
    public void setTaskAuthorizers(List<TaskAuthorizer> taskAuthorizers) {
        this.taskAuthorizers = taskAuthorizers;
    }
    
    /**
     * @see org.kuali.kra.authorization.TaskAuthorizer#isResponsible(org.kuali.kra.authorization.Task)
     */
    public boolean isResponsible(Task task) {
        
        // If any of our Authorizers is responsible, 
        // then the Group Authorizer is responsible.
        
        if (taskAuthorizers != null) {
           for (TaskAuthorizer taskAuthorizer : taskAuthorizers) {
               if (taskAuthorizer.isResponsible(task)) {
                   return true;
               }
           }
        }
        return false;
    }
    
    /**
     * @see org.kuali.kra.authorization.TaskAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.authorization.Task)
     */
    public boolean isAuthorized(String username, Task task) {
        
        // Find the responsible Authorizer and authorize the user.
        
        if (taskAuthorizers != null) {
            for (TaskAuthorizer taskAuthorizer : taskAuthorizers) {
                if (taskAuthorizer.isResponsible(task)) {
                    return taskAuthorizer.isAuthorized(username, task);
                }
            }
        }
        return false;
    }
}
