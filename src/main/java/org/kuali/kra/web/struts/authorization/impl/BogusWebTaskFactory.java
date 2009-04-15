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

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.kuali.kra.authorization.Task;

/**
 * The Bogus Web Task Factory is a hack due to the Spring Framework.  In 
 * order to use Spring's parent feature in the XML file, the parent class 
 * must be instantiated even it is never used.  This is that bogus parent class.
 */
public class BogusWebTaskFactory extends WebTaskFactoryImpl {
    
    /**
     * @see org.kuali.kra.web.struts.authorization.impl.WebTaskFactoryImpl#getTaskGroupName()
     */
    public String getTaskGroupName() {
        return null;
    }
    
    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
       // must override in order to do nothing
    }

    /**
     * @see org.kuali.kra.web.struts.authorization.WebTaskFactory#createTask(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
     */
    public Task createTask(ActionForm form, HttpServletRequest request) {
        return null;
    }
}
