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
