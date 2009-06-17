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
package org.kuali.kra.web.struts.authorization;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

/**
 * The Web Authorization Service is used by the Struts Actions to determine if a
 * user can execute a specific task.  
 */
public interface WebAuthorizationService {

    /**
     * Can the user execute the requested task.  
     * @param username the unique username of the user
     * @param actionClass the name of the Struts Action class
     * @param methodName the name of the Struts Action Method to be invoked (corresponds to the task)
     * @param form the form
     * @param request the HTTP request
     * @return true if the user is authorized; otherwise false
     */
    public boolean isAuthorized(String username, Class actionClass, String methodName, ActionForm form, HttpServletRequest request);
}
