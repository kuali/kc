/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.authorization.Task;
import org.kuali.kra.authorization.TaskAuthorizer;
import org.kuali.kra.authorization.TaskGroupAuthorizer;
import org.kuali.kra.service.TaskAuthorizationService;

/**
 * Test the Task Authorization Service Implementation.
 */
public class TaskAuthorizationServiceImplTest extends KraTestBase {

    /**
     * The inner Test Authorizer class is used to create a
     * bunch of simple authorizers.
     */
    class TestAuthorizer implements TaskAuthorizer {

        private String actionName;
        private String taskName;
        private boolean result;
        
        /**
         * Constructs a TaskAuthorizationServiceImplTest.
         * @param actionName the name of the action
         * @param taskName the name of the task
         * @param result the result to return from isAuthorized()
         */
        public TestAuthorizer(String actionName, String taskName, boolean result) {
            this.actionName = actionName;
            this.taskName = taskName;
            this.result = result;
        }

        /**
         * @see org.kuali.kra.authorization.TaskAuthorizer#isResponsible(org.kuali.kra.authorization.Task)
         */
        public boolean isResponsible(Task task) {
            return StringUtils.equals(actionName, task.getActionName()) &&
                   StringUtils.equals(taskName, task.getTaskName());
        }
        
        /**
         * @see org.kuali.kra.authorization.TaskAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.authorization.Task)
         */
        public boolean isAuthorized(String username, Task task) {
            return result;
        }
    }
    
    /**
     * Test the Task Authorization Service.  Build a tree of 
     * Task Authorizers within Task Group Authorizers.  We will
     * then query the service and see if we get back the results
     * we expect.
     */
    @Test
    public void testService() {
        TaskAuthorizationService service = buildService();
        
        Task task = new Task("1", "A");
        assertTrue(service.isAuthorized("xxx", task));
        
        task = new Task("1", "B");
        assertFalse(service.isAuthorized("xxx", task));
        
        task = new Task("1", "C");
        assertTrue(service.isAuthorized("xxx", task));
        
        task = new Task("1", "D");
        assertFalse(service.isAuthorized("xxx", task));
        
        task = new Task("2", "A");
        assertFalse(service.isAuthorized("xxx", task));
        
        task = new Task("2", "B");
        assertTrue(service.isAuthorized("xxx", task));
        
        task = new Task("2", "C");
        assertFalse(service.isAuthorized("xxx", task));
        
        task = new Task("2", "D");
        assertTrue(service.isAuthorized("xxx", task));
        
        task = new Task("3", "A");
        assertTrue(service.isAuthorized("xxx", task));
    }
    
    /**
     * Build the tree of Task Authorizers.
     * @return
     */
    private TaskAuthorizationService buildService() {
        TaskAuthorizer task1_a = new TestAuthorizer("1", "A", true);
        TaskAuthorizer task1_b = new TestAuthorizer("1", "B", false);
        TaskAuthorizer task1_c = new TestAuthorizer("1", "C", true);
        TaskAuthorizer task1_d = new TestAuthorizer("1", "D", false);
        
        TaskAuthorizer task2_a = new TestAuthorizer("2", "A", false);
        TaskAuthorizer task2_b = new TestAuthorizer("2", "B", true);
        TaskAuthorizer task2_c = new TestAuthorizer("2", "C", false);
        TaskAuthorizer task2_d = new TestAuthorizer("2", "D", true);
        
        List<TaskAuthorizer> authorizers = new ArrayList<TaskAuthorizer>();
        authorizers.add(task1_a);
        authorizers.add(task1_b);
        TaskGroupAuthorizer group1_a = new TaskGroupAuthorizer();
        group1_a.setTaskAuthorizers(authorizers);
        
        authorizers = new ArrayList<TaskAuthorizer>();
        authorizers.add(task1_c);
        authorizers.add(task1_d);
        TaskGroupAuthorizer group1_b = new TaskGroupAuthorizer();
        group1_b.setTaskAuthorizers(authorizers);
        
        authorizers = new ArrayList<TaskAuthorizer>();
        authorizers.add(group1_a);
        authorizers.add(group1_b);
        TaskGroupAuthorizer group1 = new TaskGroupAuthorizer();
        group1.setTaskAuthorizers(authorizers);
        
        authorizers = new ArrayList<TaskAuthorizer>();
        authorizers.add(task2_a);
        authorizers.add(task2_b);
        TaskGroupAuthorizer group2_a = new TaskGroupAuthorizer();
        group2_a.setTaskAuthorizers(authorizers);
        
        authorizers = new ArrayList<TaskAuthorizer>();
        authorizers.add(task2_c);
        authorizers.add(task2_d);
        TaskGroupAuthorizer group2_b = new TaskGroupAuthorizer();
        group2_b.setTaskAuthorizers(authorizers);
        
        authorizers = new ArrayList<TaskAuthorizer>();
        authorizers.add(group2_a);
        authorizers.add(group2_b);
        TaskGroupAuthorizer group2 = new TaskGroupAuthorizer();
        group2.setTaskAuthorizers(authorizers);
        
        authorizers = new ArrayList<TaskAuthorizer>();
        authorizers.add(group1);
        authorizers.add(group2);
        TaskAuthorizationServiceImpl service = new TaskAuthorizationServiceImpl();
        service.setTaskAuthorizers(authorizers);
        
        return service;
    }
}
