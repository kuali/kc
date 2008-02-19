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
package org.kuali.kra.proposaldevelopment.document.authorization;

import org.kuali.kra.authorization.Task;
import org.kuali.kra.authorization.TaskAuthorizer;

/**
 * A Proposal Authorizer determines if a user can perform
 * a given task on a proposal.  A single task corresponds
 * to a Struts action method.  
 */
public abstract class ProposalAuthorizer implements TaskAuthorizer {

    /**
     * @see org.kuali.kra.authorization.TaskAuthorizer#isResponsible(org.kuali.kra.authorization.Task)
     */
    public final boolean isResponsible(Task task) {
        
        // Proposal Authorizers are only responsible for Proposal Tasks.
        
        if (task instanceof ProposalTask) {
            return isResponsible((ProposalTask) task);
        }
        return false;
    }
    
    /**
     * @see org.kuali.kra.authorization.TaskAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.authorization.Task)
     */
    public final boolean isAuthorized(String username, Task task) {
        return isAuthorized(username, (ProposalTask) task);
    }

    /**
     * Determines if the Proposal Authorizer is responsible for authorization.
     * This method must return true in order to reliably invoke the isAuthorized()
     * method.
     * @param task the proposal task
     * @return true if the Proposal Authorizer can determine the authorization 
     *         for this task; otherwise false
     */
    public abstract boolean isResponsible(ProposalTask task);
    
    /**
     * Is the user authorized to execute the given proposal task?
     * @param username the user's unique username
     * @param task the proposal task
     * @return true if the user is authorized; otherwise false
     */
    public abstract boolean isAuthorized(String username, ProposalTask task);
}
