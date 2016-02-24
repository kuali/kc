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

import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;

/**
 * The Web Authorization Service is used by the Struts Actions to determine if a
 * user can execute a specific task.  
 */
public interface WebAuthorizationService {

    /**
     * Can the user execute the requested task.  
     * @param userId the unique username of the user
     * @param actionClass the name of the Struts Action class
     * @param methodName the name of the Struts Action Method to be invoked (corresponds to the task)
     * @param form the form
     * @param request the HTTP request
     * @return true if the user is authorized; otherwise false
     */
    public boolean isAuthorized(String userId, Class actionClass, String methodName, ActionForm form, HttpServletRequest request);
}
