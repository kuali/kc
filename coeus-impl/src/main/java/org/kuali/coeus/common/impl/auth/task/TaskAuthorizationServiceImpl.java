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
package org.kuali.coeus.common.impl.auth.task;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.auth.task.*;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * The Task Authorization Service Implementation.
 */
@Component("taskAuthorizationService")
public class TaskAuthorizationServiceImpl implements TaskAuthorizationService, InitializingBean {

    private static final Log LOG = LogFactory.getLog(TaskAuthorizationServiceImpl.class);

    @Value("#{taskAuthorizerGroupNames}")
    private Set<String> taskAuthorizerGroupNames = new HashSet<>();

    private List<TaskAuthorizerGroup> taskAuthorizerGroups = new ArrayList<TaskAuthorizerGroup>();
    
    /**
     * Delegate the authorization work to a Task Authorizer who is 
     * responsible for the given task.  If there are no Task Authorizers 
     * for the task, then the user will be authorized by default.
     * 
     * @see org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService#isAuthorized(java.lang.String, org.kuali.coeus.common.framework.auth.task.Task)
     */
    @Transactional
    public boolean isAuthorized(String userId, Task task) {
        boolean isAuthorized = true;
        String groupName = task.getGroupName();
        for (TaskAuthorizerGroup taskAuthorizerGroup : getTaskAuthorizerGroups()) {
            if (StringUtils.equals(taskAuthorizerGroup.getGroupName(), groupName)) {
                TaskAuthorizer taskAuthorizer;
                if (task.getGenericTaskName() == null || "".equals(task.getGenericTaskName().trim())) {
                    taskAuthorizer = taskAuthorizerGroup.getTaskAuthorizer(task.getTaskName()); 
                } else {
                    if (taskAuthorizerGroup.getTaskAuthorizer(task.getTaskName()) instanceof GenericTaskAuthorizer) {
                        taskAuthorizer = taskAuthorizerGroup.getTaskAuthorizer(task.getTaskName());
                        ((GenericTaskAuthorizer) taskAuthorizer).setGenericTaskName(task.getGenericTaskName());
                    } else {
                        taskAuthorizer = null;
                        LOG.error("An unexpected GenericProtocolAuthorizer was found, " + taskAuthorizerGroup.getTaskAuthorizer(task.getTaskName()).getClass());
                    }
                }
                
                if (taskAuthorizer != null) {
                    isAuthorized = taskAuthorizer.isAuthorized(userId, task);
                }
                break;
            }
        }
        return isAuthorized;
    }
    
    public List<TaskAuthorizerGroup> getTaskAuthorizerGroups() {
        //logic to get around lazy init issues where the GRL will return a null
        //TaskAuthorizerGroup because Spring hasn't initialized it yet
        if (taskAuthorizerGroups.size() != taskAuthorizerGroupNames.size()) {
            for (String taskAuthorizerGroupName : taskAuthorizerGroupNames) {
                final TaskAuthorizerGroup group = GlobalResourceLoader.getService(taskAuthorizerGroupName);
                if (group != null) {
                    if (!contains(taskAuthorizerGroups, group)) {
                        taskAuthorizerGroups.add(group);
                    }
                }
            }
        }

        return taskAuthorizerGroups;
    }

    private boolean contains(Collection<TaskAuthorizerGroup> groups, TaskAuthorizerGroup grp) {
        for (TaskAuthorizerGroup group : groups) {
            if (group.getGroupName().equals(grp.getGroupName())) {
                return true;
            }
        }

        return false;
    }

    public void setTaskAuthorizerGroups(List<TaskAuthorizerGroup> taskAuthorizerGroups) {
        this.taskAuthorizerGroups = taskAuthorizerGroups;
    }

    public Set<String> getTaskAuthorizerGroupNames() {
        return taskAuthorizerGroupNames;
    }
    
    public void setTaskAuthorizerGroupNames(Set<String> taskAuthorizerGroupNames) {
        this.taskAuthorizerGroupNames = taskAuthorizerGroupNames;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (taskAuthorizerGroupNames == null || taskAuthorizerGroupNames.isEmpty()) {
            throw new IllegalStateException("taskAuthorizerGroupNames not set");
        }
    }
}
