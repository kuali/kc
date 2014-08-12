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

import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;

/**
 * A Web Task Factory is responsible for building a Task.
 */
public interface WebTaskFactory {
    
    /**
     * Create a Task.
     * @param form the form
     * @param request the HTTP request
     * @return the new Task
     */
    public Task createTask(ActionForm form, HttpServletRequest request);
}
