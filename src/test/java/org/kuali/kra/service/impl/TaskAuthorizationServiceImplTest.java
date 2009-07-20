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
package org.kuali.kra.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.authorization.Task;
import org.kuali.kra.authorization.TaskAuthorizer;
import org.kuali.kra.authorization.TaskAuthorizerGroup;
import org.kuali.kra.authorization.TaskAuthorizerImpl;
import org.kuali.kra.service.TaskAuthorizationService;

/**
 * Test the Task Authorization Service Implementation.
 */
public class TaskAuthorizationServiceImplTest extends KraTestBase {

    /**
     * The inner Test Authorizer class is used to create a
     * bunch of simple authorizers.
     */
    class TestAuthorizer extends TaskAuthorizerImpl {

        private boolean result;
        
        /**
         * Constructs a TaskAuthorizationServiceImplTest.
         * @param actionName the name of the action
         * @param taskName the name of the task
         * @param result the result to return from isAuthorized()
         */
        public TestAuthorizer(String taskName, boolean result) {
            this.setTaskName(taskName);
            this.result = result;
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
        
        List<TaskAuthorizerGroup> taskAuthorizerGroups = new ArrayList<TaskAuthorizerGroup>();
        
        TaskAuthorizerGroup taskAuthorizerGroup = new TaskAuthorizerGroup();
        taskAuthorizerGroup.setGroupName("1");
        List<TaskAuthorizer> taskAuthorizers = new ArrayList<TaskAuthorizer>();
        TaskAuthorizer task1_a = new TestAuthorizer("A", true);
        TaskAuthorizer task1_b = new TestAuthorizer("B", false);
        TaskAuthorizer task1_c = new TestAuthorizer("C", true);
        TaskAuthorizer task1_d = new TestAuthorizer("D", false);
        taskAuthorizers.add(task1_a);
        taskAuthorizers.add(task1_b);
        taskAuthorizers.add(task1_c);
        taskAuthorizers.add(task1_d);
        taskAuthorizerGroup.setTaskAuthorizers(taskAuthorizers);
        taskAuthorizerGroups.add(taskAuthorizerGroup);
        
        taskAuthorizerGroup = new TaskAuthorizerGroup();
        taskAuthorizerGroup.setGroupName("2");
        taskAuthorizers = new ArrayList<TaskAuthorizer>();
        TaskAuthorizer task2_a = new TestAuthorizer("A", false);
        TaskAuthorizer task2_b = new TestAuthorizer("B", true);
        TaskAuthorizer task2_c = new TestAuthorizer("C", false);
        TaskAuthorizer task2_d = new TestAuthorizer("D", true);
        taskAuthorizers.add(task2_a);
        taskAuthorizers.add(task2_b);
        taskAuthorizers.add(task2_c);
        taskAuthorizers.add(task2_d);
        taskAuthorizerGroup.setTaskAuthorizers(taskAuthorizers);
        taskAuthorizerGroups.add(taskAuthorizerGroup);
        
        TaskAuthorizationServiceImpl service = new TaskAuthorizationServiceImpl();
        service.setTaskAuthorizerGroups(taskAuthorizerGroups);
        return service;
    }
}
