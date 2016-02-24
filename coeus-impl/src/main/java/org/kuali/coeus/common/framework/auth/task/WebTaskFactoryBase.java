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
