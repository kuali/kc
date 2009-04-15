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
package org.kuali.kra.web.struts.authorization.impl;

import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.web.struts.authorization.WebTaskFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * Base implementation class for Test Factories.
 */
public abstract class WebTaskFactoryImpl implements WebTaskFactory, InitializingBean {

    private String taskName;
    private TaskAuthorizationService taskAuthorizationService;
    
    /**
     * Set the Task Authorization Service.  Injected by the Spring Framework.
     * @param taskAuthorizationService the Task Authorization Service
     */
    public final void setTaskAuthorizationService(TaskAuthorizationService taskAuthorizationService) {
        this.taskAuthorizationService = taskAuthorizationService;
    }
    
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
    
    /**
     * This is a sanity check to verify that the SpringBeans.xml file contained
     * the name of a task that really does exist in the system.
     * 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        if (!taskAuthorizationService.isTaskDefined(getTaskGroupName(), getTaskName())) {
            throw new RuntimeException("Undefined Task: " + getTaskGroupName() + "." + getTaskName());
        }
    }
}
