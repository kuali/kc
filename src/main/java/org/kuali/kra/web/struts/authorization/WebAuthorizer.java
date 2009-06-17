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

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.InitializingBean;

/**
 * A Web Authorizer maps to a Struts Action class where one or more its methods
 * requires authorization.
 */
public class WebAuthorizer implements InitializingBean {

    private String classname;
    private Map<String, WebTaskFactory> mappings;
    
    /**
     * Set the name of the Struts Action class that requires authorization.  Injected
     * by the Spring Framework.
     * @param classname the Struts Action classname
     */
    public void setClassname(String classname) {
        this.classname = classname;
    }
    
    /**
     * Set the mappings.  Each entry in the map correlates a Struts Action method
     * to a Task Factory.  When the action method is to be invoked, it's Task Factory
     * is used to build a Task.  That Task is then used to determine if the user is
     * allowed to perform that task.  This mapping is injected by the Spring Framework.
     * @param mappings the set of mappings of Struts Action methods to Task Factories
     */
    public void setMappings(Map<String, WebTaskFactory> mappings) {
        this.mappings = mappings;
    }
    
    /**
     * Get the Struts Action classname.
     * @return the Struts Action classname
     */
    public String getClassname() {
        return classname;
    }

    /**
     * Get the Task Factory for a particular Struts Action method.
     * @param methodName the name of the Struts Action method
     * @return the corresponding Task Factory or null if not found
     */
    public WebTaskFactory getTaskFactory(String methodName) {
        return mappings.get(methodName);
    }

    /**
     * To prevent errors in SpringBeans.xml, we must verify that the specified
     * classname and its methods are actually present in the code.  By using
     * the Java methods forName() and getDeclaredMethod(), an exception is thrown
     * if the class or method does not exist.
     * 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        
        Class clazz = Class.forName(classname);
        
        Iterator<String> iter = mappings.keySet().iterator();
        while (iter.hasNext()) {
            String methodName = iter.next();
            clazz.getDeclaredMethod(methodName, ActionMapping.class, ActionForm.class, HttpServletRequest.class, HttpServletResponse.class);
        }
    }
}
