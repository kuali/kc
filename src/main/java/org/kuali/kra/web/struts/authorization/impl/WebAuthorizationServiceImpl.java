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

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.kuali.kra.authorization.Task;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.web.struts.authorization.WebAuthorizationService;
import org.kuali.kra.web.struts.authorization.WebAuthorizer;
import org.kuali.kra.web.struts.authorization.WebTaskFactory;

/**
 * Implementation of the Web Authorization Service.
 */
public class WebAuthorizationServiceImpl implements WebAuthorizationService {

    private TaskAuthorizationService taskAuthorizationService;
    private List<WebAuthorizer> webAuthorizers;
    
    /**
     * Set the Task Authorization Service.  Injected by the Spring Framework.
     * @param taskAuthorizationService the Task Authorization Service
     */
    public void setTaskAuthorizationService(TaskAuthorizationService taskAuthorizationService) {
        this.taskAuthorizationService = taskAuthorizationService;
    }
    
    /**
     * Set the Web Authorizers.  Injected by the Spring Framework.
     * @param webAuthorizers the Web Authorizers
     */
    public void setWebAuthorizers(List<WebAuthorizer> webAuthorizers) {
        this.webAuthorizers = webAuthorizers;
    }
    
    /**
     * To determine if the user can execute the given Struts Action method, we will use
     * the Web Authorizers corresponding to the Struts Action classes.  We start with the
     * given Struts Action class and recursively move up the class hierarchy until we 
     * find a Web Authorizer that contains a entry for the given Struts Action method.
     * If we never find a Web Authorizer entry, the user is authorized by default.  If
     * an entry in a Web Authorizer is found, it's Task Factory is used to build a Task
     * and the Task Authorization is invoked to determine if the user can perform the
     * task.
     * 
     * @see org.kuali.kra.web.struts.authorization.WebAuthorizationService#isAuthorized(java.lang.String, java.lang.Class, java.lang.String, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
     */
    public boolean isAuthorized(String username, Class actionClass, String methodName, ActionForm form, HttpServletRequest request) {
        boolean isAuthorized = true;
        
        if (actionClass != null) {
            String classname = actionClass.getCanonicalName();
            
            WebAuthorizer webAuthorizer = getWebAuthorizer(classname);
            if (webAuthorizer == null) {
                isAuthorized = isAuthorized(username, actionClass.getSuperclass(), methodName, form, request);
            }
            else {
                WebTaskFactory taskFactory = webAuthorizer.getTaskFactory(methodName);
                if (taskFactory == null) {
                    isAuthorized = isAuthorized(username, actionClass.getSuperclass(), methodName, form, request);
                }
                else if (taskAuthorizationService != null) {
                    Task task = taskFactory.createTask(form, request);
                    isAuthorized = taskAuthorizationService.isAuthorized(username, task);
                }
            }
        }
        return isAuthorized;
    }

    /**
     * Get a Web Authorizer based upon the name of the Struts Action class.
     * @param classname the Struts Action classname
     * @return the corresponding Web Authorizer or null if not found
     */
    private WebAuthorizer getWebAuthorizer(String classname) {
        for (WebAuthorizer webAuthorizer : webAuthorizers) {
            if (StringUtils.equals(webAuthorizer.getClassname(), classname)) {
                return webAuthorizer;
            }
        }
        return null;
    }
}
