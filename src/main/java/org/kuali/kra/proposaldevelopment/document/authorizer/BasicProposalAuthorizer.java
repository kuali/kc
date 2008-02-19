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
package org.kuali.kra.proposaldevelopment.document.authorizer;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalAuthorizer;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;
import org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService;

/**
 * The Basic Proposal Authorizer checks to see if the user has 
 * the required permission to perform a given task on a proposal.
 */
public class BasicProposalAuthorizer extends ProposalAuthorizer {
   
    private String taskName = null;
    private String permissionName = null;
    
    /**
     * Set the name of the task.  Injected by the Spring Framework.
     * @param taskName the name of the task
     */
    public void setTask(String taskName) {
        this.taskName = taskName;
    }
    
    /**
     * Set the name of the required permission.  Injected by the Spring Framework.
     * @param permissionName the name of the permission
     */
    public void setPermission(String permissionName) {
        this.permissionName = permissionName;
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.document.authorization.ProposalAuthorizer#isResponsible(org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask)
     */
    public boolean isResponsible(ProposalTask task) {
        return StringUtils.equals(taskName, task.getTaskName());
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.document.authorization.ProposalAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask)
     */
    public boolean isAuthorized(String username, ProposalTask task) {
        ProposalAuthorizationService proposalAuthorizationService = KraServiceLocator.getService(ProposalAuthorizationService.class);
        ProposalDevelopmentDocument doc = task.getProposalDevelopmentDocument();
        return proposalAuthorizationService.hasPermission(username, doc, permissionName);
    }
}
