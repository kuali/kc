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
 * Base implementation class for Test Factories.
 */
public abstract class WebTaskFactoryBase implements WebTaskFactory {

    private String taskName;
    
    /**
     * Set the name of the Task.  Injected by the Spring Framework.
     * @param taskName the name of the task
     */
    public final void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    
    /**
     * Get the name of the task.
     * @return the task's name
     */
    public final String getTaskName() {
        return taskName;
    }
    
    /**
     * Get the name of the task's group.
     * @return the task's group name
     */
    public abstract String getTaskGroupName();

}